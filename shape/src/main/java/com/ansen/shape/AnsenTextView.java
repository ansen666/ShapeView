package com.ansen.shape;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ansen.shape.module.ShapeAttribute;
import com.ansen.shape.util.ShapeConstant;
import com.ansen.shape.util.ShapeUtil;

public class AnsenTextView extends AppCompatTextView implements IAnsenShapeView{
    private ShapeAttribute shapeAttribute;

    public AnsenTextView(Context context) {
        this(context,null);
    }

    public AnsenTextView(Context context, AttributeSet attrs){
        this(context, attrs,android.R.attr.textViewStyle);
    }

    public AnsenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        shapeAttribute=ShapeUtil.getShapeAttribute(context,attrs);
        ShapeUtil.setBackground(this,shapeAttribute);

        updateText();
        updateDrawable();
    }

    @Override
    public void resetBackground() {
        ShapeUtil.setBackground(this,shapeAttribute);
    }

    @Override
    public void setSolidColor(int solidColor) {
        shapeAttribute.solidColor=solidColor;
    }

    @Override
    public void setStartColor(int startColor) {
        shapeAttribute.startColor=startColor;
    }

    @Override
    public void setCenterColor(int centerColor) {
        shapeAttribute.centerColor=centerColor;
    }

    @Override
    public void setEndColor(int endColor) {
        shapeAttribute.endColor=endColor;
    }

    @Override
    public void setColorOrientation(GradientDrawable.Orientation orientation) {
        shapeAttribute.colorOrientation=ShapeUtil.getOrientation(orientation);
    }

    @Override
    public void setStrokeColor(int strokeColor) {
        shapeAttribute.strokeColor=strokeColor;
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        shapeAttribute.strokeWidth=strokeWidth;
    }

    @Override
    public void setCornersRadius(float cornersRadius) {
        shapeAttribute.cornersRadius=cornersRadius;
    }

    @Override
    public void setTopLeftRadius(float topLeftRadius) {
        shapeAttribute.topLeftRadius=topLeftRadius;
    }

    @Override
    public void setTopRightRadius(float topRightRadius) {
        shapeAttribute.topRightRadius=topRightRadius;
    }

    @Override
    public void setBottomLeftRadius(float bottomLeftRadius) {
        shapeAttribute.bottomLeftRadius=bottomLeftRadius;
    }

    @Override
    public void setBottomRightRadius(float bottomRightRadius) {
        shapeAttribute.bottomRightRadius=bottomRightRadius;
    }

    @Override
    public void setShape(int shape) {
        shapeAttribute.shape=shape;
    }

    @Override
    public void setSelected(boolean selected) {
        setSelected(selected,false);
    }

    /**
     * 如果需要更新背景调用这个方法
     * @param selected
     * @param updateBackground
     */
    public void setSelected(boolean selected,boolean updateBackground) {
        boolean change=selected!=isSelected();

        super.setSelected(selected);

        if(!change){//没有发生过变化
//            Log.i("ansen","选中状态没有发生过变化 不更新");
            return ;
        }

        if(updateBackground){
            resetBackground();
        }

        shapeAttribute.selected=selected;

        int textColor=shapeAttribute.getTextColor();
        if(textColor!=0){
            setTextColor(textColor);
        }

        updateText();
        updateDrawable();
    }

    public void updateText(){
        if(!TextUtils.isEmpty(shapeAttribute.getText())){
            setText(shapeAttribute.getText());
        }
    }

    public void updateDrawable(){
        Drawable drawable=shapeAttribute.getDrawable();
        if(drawable!=null){
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

            if(shapeAttribute.drawableDirection == ShapeConstant.TextView.LEFT){
                setCompoundDrawables(drawable, null, null, null);
            }else if(shapeAttribute.drawableDirection == ShapeConstant.TextView.TOP){
                setCompoundDrawables(null, drawable, null, null);
            }else if(shapeAttribute.drawableDirection == ShapeConstant.TextView.RIGHT){
                setCompoundDrawables(null, null, drawable, null);
            }else if(shapeAttribute.drawableDirection == ShapeConstant.TextView.BOTTOM){
                setCompoundDrawables(null, null, null, drawable);
            }
        }
    }

    /**
     * 设置图片方向(设置完成之后需要调用setSelected/updateDrawable才生效)
     * @param drawableDirection ShapeConstant.TextView类下四个常量:LEFT/TOP/RIGHT/BOTTOM
     */
    public void setDrawableDirection(int drawableDirection) {
        shapeAttribute.drawableDirection = drawableDirection;
    }


}
