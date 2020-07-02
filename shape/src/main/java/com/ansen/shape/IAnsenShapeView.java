package com.ansen.shape;

import android.graphics.drawable.GradientDrawable;

/**
 * @author Ansen
 * @create time 2019-10-28
 */
public interface IAnsenShapeView {
    /**
     * 动态设置属性之后，调用这个方法
     */
    void resetBackground();

    void setSolidColor(int solidColor);

    void setPressedSolidColor(int pressedSolidColor);

    void setStartColor(int startColor);

    void setCenterColor(int centerColor);

    void setEndColor(int endColor);

    /**
     * 对应系统的枚举类型 Orientation.TOP_BOTTOM、Orientation.BOTTOM_TOP
     * @param orientation
     */
    void setColorOrientation(GradientDrawable.Orientation orientation);

    void setStrokeColor(int strokeColor);

    void setStrokeWidth(float strokeWidth);

    void setCornersRadius(float cornersRadius);

    void setTopLeftRadius(float topLeftRadius);

    void setTopRightRadius(float topRightRadius);

    void setBottomLeftRadius(float bottomLeftRadius);

    void setBottomRightRadius(float bottomRightRadius);

    void setShape(int shape);
}
