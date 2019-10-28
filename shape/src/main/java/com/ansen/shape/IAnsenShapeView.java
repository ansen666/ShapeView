package com.ansen.shape;

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

    void setStartColor(int startColor);

    void setCenterColor(int centerColor);

    void setEndColor(int endColor);

    void setOrientation(int orientation);

    void setStrokeColor(int strokeColor);

    void setStrokeWidth(float strokeWidth);

    void setCornersRadius(float cornersRadius);

    void setTopLeftRadius(float topLeftRadius);

    void setTopRightRadius(float topRightRadius);

    void setBottomLeftRadius(float bottomLeftRadius);

    void setBottomRightRadius(float bottomRightRadius);

    void setShape(int shape);
}
