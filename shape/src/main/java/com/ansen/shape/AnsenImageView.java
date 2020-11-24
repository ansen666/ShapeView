package com.ansen.shape;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import com.ansen.shape.module.ShapeAttribute;
import com.ansen.shape.util.ShapeConstant;
import com.ansen.shape.util.ShapeUtil;

/**
 * 引用代码:https://github.com/ai2101039/YLCircleImageView
 * @author Ansen
 * @create time 2020/4/18
 */
public class AnsenImageView extends AppCompatImageView {
    private Paint paint;//用于绘制Layer
    private Paint borderPaint;//用于绘制描边

    private ShapeAttribute attribute;
    private boolean circle;//是否有弧度，即是否需要绘制圆弧

    public AnsenImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        attribute= ShapeUtil.getShapeAttribute(context,attrs);
        ShapeUtil.setBackground(this,attribute);

        initData();
        updateSrc();
    }

    //view选中状态变更回调
    protected void dispatchSetSelected(boolean selected){
        super.dispatchSetSelected(selected);

        if(selected==attribute.selected){//没有发生过变化 不需要更新
            return ;
        }
        attribute.selected=selected;
        updateSrc();
    }

    public void updateSrc(){
        Drawable drawable=attribute.getDrawable();
        if(drawable!=null){
            setImageDrawable(drawable);
        }

        //重新绘制边框
        borderPaint.setStrokeWidth(attribute.getStrokeWidth());
        borderPaint.setColor(attribute.getStrokeColor());
        invalidate();
    }

    public void initData() {
        initRadius();
        //  判断是否需要调用绘制函数
        circle = attribute.strokeWidth != 0 || attribute.strokeSpace != 0 ||
                attribute.topLeftRadius != 0 ||
                attribute.topRightRadius != 0 ||
                attribute.bottomLeftRadius != 0 ||
                attribute.bottomRightRadius != 0;

        //  设置画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(attribute.getStrokeWidth());
        borderPaint.setColor(attribute.getStrokeColor());

        if (circle) {
            //  为什么设置这一条，因为Glide中，在into 源码内
            //  不同的 ScaleType 会对drawable进行压缩，一旦压缩了，我们在onDraw里面获取图片的大小就没有意义了
            setScaleType(ScaleType.MATRIX);
        }
    }

    /**
     * 初始化半径
     */
    private void initRadius() {
        //  该处便于代码编写 如XML设置 radius = 20，topLeftRadius = 10，最终结果是  10 20 20 20
        if (attribute.cornersRadius != 0) {
            attribute.topLeftRadius = attribute.topLeftRadius == 0 ? attribute.cornersRadius : attribute.topLeftRadius;
            attribute.topRightRadius = attribute.topRightRadius == 0 ? attribute.cornersRadius : attribute.topRightRadius;
            attribute.bottomLeftRadius = attribute.bottomLeftRadius == 0 ? attribute.cornersRadius : attribute.bottomLeftRadius;
            attribute.bottomRightRadius = attribute.bottomRightRadius == 0 ? attribute.cornersRadius : attribute.bottomRightRadius;
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        //  使用局部变量，降低函数调用次数
        int vw = getMeasuredWidth();
        int vh = getMeasuredHeight();

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        //  绘制描边
        if (attribute.getStrokeWidth() != 0) {
            RectF rectF = new RectF(paddingLeft, paddingTop, vw - paddingRight, vh - paddingBottom);
            //  描边会有一半处于框体之外
            float i = attribute.getStrokeWidth() / 2;
            //  移动矩形，以便于描边都处于view内
            rectF.inset(i, i);
            //  绘制描边，半径需要进行偏移 i
            drawPath(canvas, rectF, borderPaint, i);
        }

        if ((null != drawable && circle)) {
            RectF rectF = new RectF(paddingLeft, paddingTop, vw - paddingRight, vh - paddingBottom);
            //  矩形需要缩小的值
            float i = attribute.getStrokeWidth() + attribute.strokeSpace;
            //  这里解释一下，为什么要减去一个像素，因为像素融合时，由于锯齿的存在和图片像素不高，会导致图片和边框出现1像素的间隙
            //  大家可以试一下，去掉这一句，然后用高清图就不会出问题，用非高清图就会出现
            i = i > 1 ? i - 1 : 0;
            //  矩形偏移
            rectF.inset(i, i);
            int layerId = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG);
            //  多边形
            drawPath(canvas, rectF, paint, i);
            //  设置像素融合模式
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            //  drawable转为 bitmap
            Bitmap bitmap = drawableToBitmap(drawable);
            //  根据图片的大小，控件的大小，图片的展示形式，然后来计算图片的src取值范围
            Rect src = getSrc(bitmap, (int) rectF.width(), (int) rectF.height());
            //  dst取整个控件，也就是表示，我们的图片要占满整个控件
            canvas.drawBitmap(bitmap, src, rectF, paint);
            paint.setXfermode(null);
            canvas.restoreToCount(layerId);
        } else {
            super.onDraw(canvas);
        }
    }

    /**
     * 这里详细说一下，我们的目标就是在 bitmap 中找到一个 和 view 宽高比例相等的 一块矩形 tempRect，然后截取出来 放到整个view中
     * tempRect 总是会存在
     *
     * @param bitmap bitmap
     * @param rw     绘制区域的宽度
     * @param rh     绘制区域的高度
     * @return 矩形
     */
    private Rect getSrc(@NonNull Bitmap bitmap, int rw, int rh) {
        //  bw bh,bitmap 的宽高
        //  vw vh,view 的宽高
        int bw = bitmap.getWidth();
        int bh = bitmap.getHeight();

        int left = 0, top = 0, right = 0, bottom = 0;

        //  判断 bw/bh 与 rw/rh
        int temp1 = bw * rh;
        int temp2 = rw * bh;

        //  相似矩形的宽高
        int[] tempRect = {bw, bh};

        if (temp1 == temp2) {
            return new Rect(0, 0, bw, bh);
        }
        //  tempRect 的宽度比 bw 小
        else if (temp1 > temp2) {
            int tempBw = temp2 / rh;
            tempRect[0] = tempBw;
        }
        //  tempRect 的宽度比 bw 大
        else if (temp1 < temp2) {
            int tempBh = temp1 / rw;
            tempRect[1] = tempBh;
        }

        //  tempRect 的宽度与 bw 的比值
        Boolean compare = bw > tempRect[0];

        switch (attribute.scaleType) {
            case ShapeConstant.ScaleType.TOP:
                //  从上往下展示，我们这里的效果是不止从上往下，compare = true，还要居中
                left = compare ? (bw - tempRect[0]) / 2 : 0;
                top = 0;
                right = compare ? (bw + tempRect[0]) / 2 : tempRect[0];
                bottom = tempRect[1];
                break;
            case ShapeConstant.ScaleType.CENTER:
                //  居中
                left = compare ? (bw - tempRect[0]) / 2 : 0;
                top = compare ? 0 : (bh - tempRect[1]) / 2;
                right = compare ? (bw + tempRect[0]) / 2 : tempRect[0];
                bottom = compare ? tempRect[1] : (bh + tempRect[1]) / 2;
                break;
            case ShapeConstant.ScaleType.BOTTOM:
                left = compare ? (bw - tempRect[0]) / 2 : 0;
                top = compare ? 0 : bh - tempRect[1];
                right = compare ? (bw + tempRect[0]) / 2 : tempRect[0];
                bottom = compare ? tempRect[1] : bh;
                break;
            case ShapeConstant.ScaleType.FITXY:
                left = 0;
                top = 0;
                right = bw;
                bottom = bh;
                break;
            default:
        }

        return new Rect(left, top, right, bottom);
    }

    /**
     * 绘制多边形
     *
     * @param canvas 画布
     * @param rectF  矩形
     * @param paint  画笔
     * @param offset 半径偏移量
     */
    private void drawPath(Canvas canvas, RectF rectF, Paint paint, float offset) {
        Path path = new Path();
        path.addRoundRect(rectF,
                new float[]{
                        offsetRadius(attribute.topLeftRadius, offset), offsetRadius(attribute.topLeftRadius, offset),
                        offsetRadius(attribute.topRightRadius, offset), offsetRadius(attribute.topRightRadius, offset),
                        offsetRadius(attribute.bottomRightRadius, offset), offsetRadius(attribute.bottomRightRadius, offset),
                        offsetRadius(attribute.bottomLeftRadius, offset), offsetRadius(attribute.bottomLeftRadius, offset)}, Path.Direction.CW);
        path.close();
        canvas.drawPath(path, paint);
    }

    /**
     * 计算半径偏移值，必须大于等于0
     *
     * @param radius 半径
     * @param offset 偏移量
     * @return 偏移半径
     */
    private float offsetRadius(float radius, float offset) {
        return Math.max(radius - offset, 0);
    }

    /**
     * drawable 转 bitmap
     * 这个函数可以放在Util类里面，算是一个公共函数
     *
     * @param drawable 要转换的Drawable
     * @return 转换完成的Bitmap
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /////////////////////////////  set/get   /////////////////////////////
    public void setRadius(float radius) {
        setTopLeftRadius(radius);
        setTopRightRadius(radius);
        setBottomLeftRadius(radius);
        setBottomRightRadius(radius);
    }

    public void setTopLeftRadius(float topLeftRadius) {
        attribute.topLeftRadius=topLeftRadius;
    }

    public void setTopRightRadius(float topRightRadius) {
        attribute.topRightRadius = topRightRadius;
    }

    public void setBottomLeftRadius(float bottomLeftRadius) {
        attribute.bottomLeftRadius = bottomLeftRadius;
    }

    public void setBottomRightRadius(float bottomRightRadius) {
        attribute.bottomRightRadius = bottomRightRadius;
    }

    public void setScaleType(int styleType) {
        attribute.scaleType = styleType;
    }

    public void setCircle(boolean circle) {
        this.circle = circle;
    }

    public void setStrokeWidth(float borderWidth) {
        attribute.strokeWidth = borderWidth;
        borderPaint.setStrokeWidth(borderWidth);
    }

    public void setStrokeSpace(float borderSpace) {
        attribute.strokeSpace = borderSpace;
    }

    public void setStrokeColor(int borderColor) {
        attribute.strokeColor = borderColor;
        borderPaint.setColor(borderColor);
    }

    public Paint getPaint() {
        return paint;
    }

    public Paint getBorderPaint() {
        return borderPaint;
    }
}
