package com.ansen.shape.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ansen.shape.R;
import com.ansen.shape.module.ShapeAttribute;

public class ShapeUtil{
    public static ShapeAttribute getShapeAttribute(Context context, AttributeSet attrs) {
        ShapeAttribute shapeAttribute = new ShapeAttribute();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);

        shapeAttribute.solidColor = typedArray.getColor(R.styleable.ShapeView_solid_color, Color.TRANSPARENT);
        shapeAttribute.selectSolidColor = typedArray.getColor(R.styleable.ShapeView_select_solid_color, Color.TRANSPARENT);
        shapeAttribute.pressedSolidColor = typedArray.getColor(R.styleable.ShapeView_pressed_solid_color, Color.TRANSPARENT);

        shapeAttribute.startColor = typedArray.getColor(R.styleable.ShapeView_start_color, Color.TRANSPARENT);
        shapeAttribute.centerColor = typedArray.getColor(R.styleable.ShapeView_center_color, Color.TRANSPARENT);
        shapeAttribute.endColor = typedArray.getColor(R.styleable.ShapeView_end_color, Color.TRANSPARENT);

        shapeAttribute.selectStartColor = typedArray.getColor(R.styleable.ShapeView_select_start_color, Color.TRANSPARENT);
        shapeAttribute.selectCenterColor = typedArray.getColor(R.styleable.ShapeView_select_center_color, Color.TRANSPARENT);
        shapeAttribute.selectEndColor = typedArray.getColor(R.styleable.ShapeView_select_end_color, Color.TRANSPARENT);

        shapeAttribute.pressedStartColor = typedArray.getColor(R.styleable.ShapeView_pressed_start_color, Color.TRANSPARENT);
        shapeAttribute.pressedCenterColor = typedArray.getColor(R.styleable.ShapeView_pressed_center_color, Color.TRANSPARENT);
        shapeAttribute.pressedEndColor = typedArray.getColor(R.styleable.ShapeView_pressed_end_color, Color.TRANSPARENT);

        shapeAttribute.colorOrientation = typedArray.getInt(R.styleable.ShapeView_color_orientation, 1);//默认从左到右

        shapeAttribute.strokeColor = typedArray.getColor(R.styleable.ShapeView_stroke_color, Color.TRANSPARENT);
        shapeAttribute.selectStrokeColor = typedArray.getColor(R.styleable.ShapeView_select_stroke_color, Color.TRANSPARENT);
        shapeAttribute.strokeWidth = typedArray.getDimension(R.styleable.ShapeView_stroke_width, 0.0F);
        shapeAttribute.selectStrokeWidth = typedArray.getDimension(R.styleable.ShapeView_select_stroke_width, 0.0F);
        shapeAttribute.strokeDirection = typedArray.getInt(R.styleable.ShapeView_stroke_direction, 0);//默认为0 显示全边框

        shapeAttribute.cornersRadius = typedArray.getDimension(R.styleable.ShapeView_corners_radius, 0.0F);
        shapeAttribute.topLeftRadius = typedArray.getDimension(R.styleable.ShapeView_top_left_radius, 0.0F);
        shapeAttribute.topRightRadius = typedArray.getDimension(R.styleable.ShapeView_top_right_radius, 0.0F);
        shapeAttribute.bottomLeftRadius = typedArray.getDimension(R.styleable.ShapeView_bottom_left_radius, 0.0F);
        shapeAttribute.bottomRightRadius = typedArray.getDimension(R.styleable.ShapeView_bottom_right_radius, 0.0F);

        shapeAttribute.shape = typedArray.getInt(R.styleable.ShapeView_shape_view, 0);
        shapeAttribute.scaleType=typedArray.getInt(R.styleable.ShapeView_scale_type, ShapeConstant.ScaleType.TOP);

        shapeAttribute.strokeSpace=typedArray.getDimension(R.styleable.ShapeView_stroke_space, 0.0F);

        //TextView/EditView属性
        shapeAttribute.text = typedArray.getString(R.styleable.ShapeView_text);
        shapeAttribute.selectText = typedArray.getString(R.styleable.ShapeView_select_text);

        shapeAttribute.textColor = typedArray.getColor(R.styleable.ShapeView_text_color, Color.TRANSPARENT);
        shapeAttribute.selectTextColor = typedArray.getColor(R.styleable.ShapeView_select_text_color, Color.TRANSPARENT);

        shapeAttribute.textSize=typedArray.getDimensionPixelSize(R.styleable.ShapeView_text_size,0);
        shapeAttribute.selectTextSize=typedArray.getDimensionPixelSize(R.styleable.ShapeView_select_text_size,0);
//        Log.i("ansen","size:"+shapeAttribute.textSize+" selectTextSize:"+shapeAttribute.selectTextSize);

        shapeAttribute.unselectDrawable = typedArray.getDrawable(R.styleable.ShapeView_unselect_drawable);
        shapeAttribute.selectDrawable = typedArray.getDrawable(R.styleable.ShapeView_select_drawable);
//        Log.i("ansen","selectDrawable:"+shapeAttribute.selectDrawable);
        shapeAttribute.drawableDirection = typedArray.getInt(R.styleable.ShapeView_drawable_direction, 0);//默认为0 显示左边

        shapeAttribute.borderGradient = typedArray.getBoolean(R.styleable.ShapeView_border_gradient, false);
        shapeAttribute.textGradient = typedArray.getBoolean(R.styleable.ShapeView_text_gradient, false);

//        Log.i("ansen","startColor:"+shapeAttribute.startColor+" selectStartColor"+shapeAttribute.selectStartColor);

        typedArray.recycle();
        return shapeAttribute;
    }

    public static void setBackground(View view, ShapeAttribute shapeAttribute) {
        if (view == null || shapeAttribute == null) {
            return;
        }

        shapeAttribute.selected = view.isSelected();
        if (shapeAttribute.isPressed()) {//有按下颜色
            StateListDrawable sb = new StateListDrawable();

            shapeAttribute.pressed = false;
            GradientDrawable normalDrawable = getBaseGradientDrawable(shapeAttribute);

            shapeAttribute.pressed = true;
            GradientDrawable pressedDrawable = getBaseGradientDrawable(shapeAttribute);

            //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
            //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
            sb.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
            //没有任何状态时显示的图片，就设置空集合，默认状态
            sb.addState(new int[]{}, normalDrawable);

            view.setBackground(sb);
        } else {
            StateListDrawable sb = new StateListDrawable();
            
            if (shapeAttribute.strokeDirection != 0) { // 标签效果不给支持
                Drawable strokeDrawable = getStrokeLayerDrawable(shapeAttribute);
                sb.addState(new int[]{}, strokeDrawable);
            } else {
                GradientDrawable normal = getBaseGradientDrawable(shapeAttribute);
                sb.addState(new int[]{}, normal);
            }
            view.setBackground(sb);
        }
    }

    /**
     * 上下左右边框 默认全边框
     *
     * @param shapeAttribute
     * @return
     */
    private static Drawable getStrokeLayerDrawable(ShapeAttribute shapeAttribute) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        Drawable[] layers = {getBaseGradientDrawable(shapeAttribute)};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        setstrokeDirection(layerDrawable, shapeAttribute);
        stateListDrawable.addState(new int[]{}, layerDrawable);
        return stateListDrawable;
    }

    private static GradientDrawable getBaseGradientDrawable(ShapeAttribute shapeAttribute) {
        GradientDrawable gradientDrawable = new GradientDrawable();

        int startColor = shapeAttribute.getStartColor();
        int centerColor = shapeAttribute.getCenterColor();
        int endColor = shapeAttribute.getEndColor();
        int solidColor = shapeAttribute.getSolidColor();

        if (startColor != 0 && endColor != 0) {//开始颜色跟结束颜色都不为空 设置背景渐变色
            if (centerColor != 0) {
                gradientDrawable.setColors(new int[]{startColor, centerColor, endColor});
            } else {
                gradientDrawable.setColors(new int[]{startColor, endColor});
            }
            gradientDrawable.setOrientation(getOrientation(shapeAttribute.colorOrientation));//设置颜色渐变方向
        } else if (solidColor != 0) {//设置背景颜色
            gradientDrawable.setColor(solidColor);
        }

        int strokeColor = shapeAttribute.getStrokeColor();
        float strokeWidth = shapeAttribute.getStrokeWidth();

        if (strokeColor != 0 && strokeWidth != 0) {//设置边框
            gradientDrawable.setStroke((int) strokeWidth, strokeColor);
        }

        gradientDrawable.setShape(shapeAttribute.shape);//设置形状(矩形、椭圆形、一条线、环形)

        if (shapeAttribute.strokeDirection != 0) {
            return gradientDrawable;//设置了边框方向 就不画圆角了
        }

        float topLeftRadius = shapeAttribute.topLeftRadius;
        float topRightRadius = shapeAttribute.topRightRadius;
        float bottomLeftRadius = shapeAttribute.bottomLeftRadius;
        float bottomRightRadius = shapeAttribute.bottomRightRadius;

        if (topLeftRadius != 0 || topRightRadius != 0 || bottomLeftRadius != 0 || bottomRightRadius != 0) {//设置某个角弧度
            gradientDrawable.setCornerRadii(new float[]{topLeftRadius, topLeftRadius, topRightRadius, topRightRadius, bottomRightRadius, bottomRightRadius, bottomLeftRadius, bottomLeftRadius});
        } else if (shapeAttribute.cornersRadius > 0) {
            gradientDrawable.setCornerRadius(shapeAttribute.cornersRadius);//设置弧度
        }
        return gradientDrawable;
    }

    public static GradientDrawable.Orientation getOrientation(int orientation) {
        if (orientation == 1) {
            return GradientDrawable.Orientation.LEFT_RIGHT;
        } else if (orientation == 2) {
            return GradientDrawable.Orientation.RIGHT_LEFT;
        } else if (orientation == 3) {
            return GradientDrawable.Orientation.TOP_BOTTOM;
        } else if (orientation == 4) {
            return GradientDrawable.Orientation.BOTTOM_TOP;
        } else if (orientation == 5) {
            return GradientDrawable.Orientation.TR_BL;
        } else if (orientation == 6) {
            return GradientDrawable.Orientation.BR_TL;
        } else if (orientation == 7) {
            return GradientDrawable.Orientation.BL_TR;
        } else if (orientation == 8) {
            return GradientDrawable.Orientation.TL_BR;
        }
        return GradientDrawable.Orientation.LEFT_RIGHT;
    }

    public static int getOrientation(GradientDrawable.Orientation orientation) {
        if (orientation == GradientDrawable.Orientation.LEFT_RIGHT) {
            return 1;
        } else if (orientation == GradientDrawable.Orientation.RIGHT_LEFT) {
            return 2;
        } else if (orientation == GradientDrawable.Orientation.TOP_BOTTOM) {
            return 3;
        } else if (orientation == GradientDrawable.Orientation.BOTTOM_TOP) {
            return 4;
        } else if (orientation == GradientDrawable.Orientation.TR_BL) {
            return 5;
        } else if (orientation == GradientDrawable.Orientation.BR_TL) {
            return 6;
        } else if (orientation == GradientDrawable.Orientation.BL_TR) {
            return 7;
        } else if (orientation == GradientDrawable.Orientation.TL_BR) {
            return 8;
        }
        return 1;
    }

    public static void setstrokeDirection(LayerDrawable layerDrawable, ShapeAttribute shapeAttribute) {
        float strokeWidth = shapeAttribute.getStrokeWidth();

        int liftStrokeWidth = -(int) strokeWidth;
        int rightStrokeWidth = -(int) strokeWidth;
        int topStrokeWidth = -(int) strokeWidth;
        int bottomStrokeWidth = -(int) strokeWidth;

        if ((shapeAttribute.strokeDirection & ShapeConstant.Orientation.LEFT) == ShapeConstant.Orientation.LEFT) {
            liftStrokeWidth = 0;
        }
        if ((shapeAttribute.strokeDirection & ShapeConstant.Orientation.TOP) == ShapeConstant.Orientation.TOP) {
            topStrokeWidth = 0;
        }
        if ((shapeAttribute.strokeDirection & ShapeConstant.Orientation.BOTTOM) == ShapeConstant.Orientation.BOTTOM) {
            bottomStrokeWidth = 0;
        }
        if ((shapeAttribute.strokeDirection & ShapeConstant.Orientation.RIGHT) == ShapeConstant.Orientation.RIGHT) {
            rightStrokeWidth = 0;
        }
        layerDrawable.setLayerInset(0, liftStrokeWidth, topStrokeWidth, rightStrokeWidth, bottomStrokeWidth);
    }
}
