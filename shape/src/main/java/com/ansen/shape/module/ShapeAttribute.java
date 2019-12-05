package com.ansen.shape.module;

import android.graphics.drawable.Drawable;

/**
 * 自定义属性
 * @author Ansen
 * @create time 2019-10-28
 */
public class ShapeAttribute {
    public ShapeAttribute(){

    }

    public boolean selected=false;//是否选中

    public int solidColor;//填充颜色
    public int selectSolidColor;//选中填充色

    public int startColor;//开始渐变颜色
    public int centerColor;//中间渐变色
    public int endColor;//结束渐变色
    public int selectStartColor;
    public int selectCenterColor;
    public int selectEndColor;

    public int pressedSolidColor;//按下填充色

    public int colorOrientation;//颜色渐变方向

    public int strokeColor;//边框
    public int selectStrokeColor;//选中边框颜色

    public float strokeWidth;//边框宽度
    public float selectStrokeWidth;//选中边框宽度

    public float cornersRadius;//四个角弧度
    public float topLeftRadius;//左上角
    public float topRightRadius;//右上角
    public float bottomLeftRadius;//左下角
    public float bottomRightRadius;//右下角

    public int shape;//形状

    //TextView/EditView属性
    public String text;
    public String selectText;
    public int textColor;
    public int selectTextColor;

    public Drawable unselectDrawable;
    public Drawable selectDrawable;
    public int drawableDirection;//图标方向

    public int getSolidColor() {
        if(selected){
            return selectSolidColor;
        }
        return solidColor;
    }

    public int getStartColor() {
        if(selected){
            return selectStartColor;
        }
        return startColor;
    }

    public int getCenterColor() {
        if(selected){
            return selectCenterColor;
        }
        return centerColor;
    }

    public int getEndColor() {
        if(selected){
            return selectEndColor;
        }
        return endColor;
    }

    public int getStrokeColor() {
        if(selected){
            return selectStrokeColor;
        }
        return strokeColor;
    }

    public float getStrokeWidth() {
        if(selected){
            return selectStrokeWidth;
        }
        return strokeWidth;
    }

    public int getTextColor() {
        if(selected){
            return selectTextColor;
        }
        return textColor;
    }

    public String getText() {
        if(selected){
            return selectText;
        }
        return text;
    }

    public Drawable getDrawable() {
        if(selected){
            return selectDrawable;
        }
        return unselectDrawable;
    }

    public int getPressedSolidColor() {
        return pressedSolidColor;
    }
}
