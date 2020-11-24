package com.ansen.shape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.ansen.shape.module.ShapeAttribute;
import com.ansen.shape.util.ShapeConstant;
import com.ansen.shape.util.ShapeUtil;

public class AnsenTextView extends AppCompatTextView implements IAnsenShapeView {
    private ShapeAttribute attribute;

    private LinearGradient shader;
    private Paint borderPaint;

    public AnsenTextView(Context context) {
        this(context, null);
    }

    public AnsenTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public AnsenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        attribute = ShapeUtil.getShapeAttribute(context, attrs);

        if (!attribute.borderGradient && !attribute.textGradient) {//有边框跟文字渐变就不设置背景了
            ShapeUtil.setBackground(this, attribute);
        }

        updateText();
        updateTextSize();
        updateTextColor();
        updateDrawable();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int textWidth = getMeasuredWidth();
        if (textWidth > 0 && attribute != null && (attribute.borderGradient || attribute.textGradient)) {
            setTextColorOrientation();
        }

        if (attribute.textGradient) {//文字渐变 需要放 super.onDraw前面
            getPaint().setShader(shader);
        }

        super.onDraw(canvas);

//        Log.i("ansen","onDraw width:"+getMeasuredWidth());
        if (textWidth > 0 && attribute.borderGradient) {//绘制渐变圆角边框
//            canvas.save();
//            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            if (borderPaint == null) {
                borderPaint = new Paint();
                borderPaint.setStyle(Paint.Style.STROKE);
                borderPaint.setStrokeWidth(attribute.getStrokeWidth());
                borderPaint.setAntiAlias(true);//抗锯齿
            }
            borderPaint.setShader(shader);//设置渐变背景

            Rect rect = new Rect();
            canvas.getClipBounds(rect);
            RectF rectF = new RectF(rect);
            float radius = attribute.cornersRadius;

            canvas.drawRoundRect(rectF, radius, radius, borderPaint);
        }
    }

    private void setTextColorOrientation() {
        int[] textColors = {attribute.startColor, attribute.endColor};
        if (attribute.getCenterColor() != 0) {
            textColors = new int[]{attribute.startColor, attribute.centerColor, attribute.endColor};
        }
        int startX = 0, startY = 0, endX = 0, endY = 0;
        switch (attribute.colorOrientation) {
            case 2:
                startX = getMeasuredHeight();
                break;
            case 3:
                endY = getMeasuredHeight();
                break;
            case 4:
                startY = getMeasuredHeight();
                break;
            case 5:
                startX = getMeasuredWidth();
                endY = getMeasuredHeight();
                break;
            case 6:
                startX = getMeasuredWidth();
                startY = getMeasuredHeight();
                break;
            case 7:
                startY = getMeasuredHeight();
                endX = getMeasuredWidth();
                break;
            case 8:
                endX = getMeasuredWidth();
                endY = getMeasuredHeight();
                break;
            default:
                endX = getMeasuredWidth();
                break;
        }
        shader = new LinearGradient(startX, startY, endX, endY, textColors, null, Shader.TileMode.CLAMP);
    }

    @Override
    public void resetBackground() {
        ShapeUtil.setBackground(this, attribute);
    }

    @Override
    public void setSolidColor(int solidColor) {
        attribute.solidColor = solidColor;
    }

    @Override
    public void setPressedSolidColor(int pressedSolidColor) {
        attribute.pressedSolidColor=pressedSolidColor;
    }

    @Override
    public void setStartColor(int startColor) {
        attribute.startColor = startColor;
    }

    @Override
    public void setCenterColor(int centerColor) {
        attribute.centerColor = centerColor;
    }

    @Override
    public void setEndColor(int endColor) {
        attribute.endColor = endColor;
    }

    @Override
    public void setColorOrientation(GradientDrawable.Orientation orientation) {
        attribute.colorOrientation = ShapeUtil.getOrientation(orientation);
    }

    @Override
    public void setStrokeColor(int strokeColor) {
        attribute.strokeColor = strokeColor;
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        attribute.strokeWidth = strokeWidth;
    }

    @Override
    public void setCornersRadius(float cornersRadius) {
        attribute.cornersRadius = cornersRadius;
    }

    @Override
    public void setTopLeftRadius(float topLeftRadius) {
        attribute.topLeftRadius = topLeftRadius;
    }

    @Override
    public void setTopRightRadius(float topRightRadius) {
        attribute.topRightRadius = topRightRadius;
    }

    @Override
    public void setBottomLeftRadius(float bottomLeftRadius) {
        attribute.bottomLeftRadius = bottomLeftRadius;
    }

    @Override
    public void setBottomRightRadius(float bottomRightRadius) {
        attribute.bottomRightRadius = bottomRightRadius;
    }

    @Override
    public void setShape(int shape) {
        attribute.shape = shape;
    }


    @Override
    public void setSelected(boolean selected) {
        setSelected(selected, false);
    }

    /**
     * 如果需要更新背景调用这个方法
     *
    /**
     * 这个方法弃用 直接调用View.setSelected
     * @param selected
     * @param updateBackground 是否需要更新背景
     */
    public void setSelected(boolean selected, boolean updateBackground) {
        boolean change = selected != isSelected();
    }

    @Deprecated
    public void setSelected(boolean selected,boolean updateBackground) {
        super.setSelected(selected);
    }


        if (!change) {//没有发生过变化
//            Log.i("ansen","选中状态没有发生过变化 不更新");
            return;
        }

        if (updateBackground) {
    //view选中状态变更回调
    protected void dispatchSetSelected(boolean selected){
        super.dispatchSetSelected(selected);

        if(selected==attribute.selected){//没有发生过变化 不需要更新
            return ;
        }

//        Log.i("ansen","dispatchSetSelected selected:"+selected);
        if(attribute.selectStartColor!=0||attribute.selectCenterColor!=0
                ||attribute.selectEndColor!=0||attribute.selectStrokeColor!=0
            ||attribute.selectSolidColor!=0){

            resetBackground();
        }

        attribute.selected = selected;

        int textColor = attribute.getTextColor();
        if (textColor != 0) {
            setTextColor(textColor);
        }

        updateText();
        updateTextColor();
        updateTextSize();
        updateDrawable();
    }

    public void updateText() {
        if (!TextUtils.isEmpty(attribute.getText())) {
            setText(attribute.getText());
        }
    }


    public void updateTextColor(){
        int textColor=attribute.getTextColor();
        if(textColor!=0){
            setTextColor(textColor);
        }
    }

    public void updateTextSize(){
        int textSize=attribute.getTextSize();
        if(textSize!=0){
            setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        }
    }



    public void updateDrawable(){
        Drawable drawable=attribute.getDrawable();
        if(drawable!=null){
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            if (attribute.drawableDirection == ShapeConstant.TextView.LEFT) {
                setCompoundDrawables(drawable, null, null, null);
            } else if (attribute.drawableDirection == ShapeConstant.TextView.TOP) {
                setCompoundDrawables(null, drawable, null, null);
            } else if (attribute.drawableDirection == ShapeConstant.TextView.RIGHT) {
                setCompoundDrawables(null, null, drawable, null);
            } else if (attribute.drawableDirection == ShapeConstant.TextView.BOTTOM) {
                setCompoundDrawables(null, null, null, drawable);
            }
        }
    }

    /**
     * 设置图片方向(设置完成之后需要调用setSelected/updateDrawable才生效)
     *
     * @param drawableDirection ShapeConstant.TextView类下四个常量:LEFT/TOP/RIGHT/BOTTOM
     */
    public void setDrawableDirection(int drawableDirection) {
        attribute.drawableDirection = drawableDirection;
    }

    public void setUnselectDrawable(int resId) {
        attribute.unselectDrawable = getContext().getResources().getDrawable(resId);
    }

    public void setSelectDrawable(int resId) {
        attribute.selectDrawable = getContext().getResources().getDrawable(resId);
    }

    public void setUnselectDrawable(Drawable unselectDrawable) {
        attribute.unselectDrawable = unselectDrawable;
    }

    public void setSelectDrawable(Drawable selectDrawable) {
        attribute.selectDrawable = selectDrawable;
    }

    /**
     * @param size 单位是sp
     */
    public void setTextSize(int size){
        attribute.textSize=spToPixel(getContext(),size);
    }

    /**
     * @param size 单位是sp
     */
    public void setSelectTextSize(int size){
        attribute.selectTextSize=spToPixel(getContext(),size);
    }

    //将sp转换成pixel
    private int spToPixel(Context context, float spValue) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        int pixelValue=(int) (spValue * scaledDensity + 0.5f);
        return pixelValue;
    }
}
