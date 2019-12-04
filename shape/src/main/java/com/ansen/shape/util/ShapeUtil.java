package com.ansen.shape.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.ansen.shape.R;
import com.ansen.shape.module.ShapeAttribute;

public class ShapeUtil{
    public static ShapeAttribute getShapeAttribute(Context context,AttributeSet attrs){
        ShapeAttribute shapeAttribute=new ShapeAttribute();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);

        shapeAttribute.solidColor=typedArray.getColor(R.styleable.ShapeView_solid_color, Color.TRANSPARENT);
        shapeAttribute.selectSolidColor=typedArray.getColor(R.styleable.ShapeView_select_solid_color, Color.TRANSPARENT);

        shapeAttribute.startColor=typedArray.getColor(R.styleable.ShapeView_start_color,Color.TRANSPARENT);
        shapeAttribute.centerColor=typedArray.getColor(R.styleable.ShapeView_center_color,Color.TRANSPARENT);
        shapeAttribute.endColor=typedArray.getColor(R.styleable.ShapeView_end_color,Color.TRANSPARENT);

        shapeAttribute.selectStartColor=typedArray.getColor(R.styleable.ShapeView_select_start_color, Color.TRANSPARENT);
        shapeAttribute.selectCenterColor=typedArray.getColor(R.styleable.ShapeView_select_center_color, Color.TRANSPARENT);
        shapeAttribute.selectEndColor=typedArray.getColor(R.styleable.ShapeView_select_end_color, Color.TRANSPARENT);

        shapeAttribute.colorOrientation=typedArray.getInt(R.styleable.ShapeView_color_orientation,1);//默认从左到右

        shapeAttribute.strokeColor = typedArray.getColor(R.styleable.ShapeView_stroke_color,Color.TRANSPARENT);
        shapeAttribute.selectStrokeColor = typedArray.getColor(R.styleable.ShapeView_select_stroke_color,Color.TRANSPARENT);
        shapeAttribute.strokeWidth=typedArray.getDimension(R.styleable.ShapeView_stroke_width,0.0F);
        shapeAttribute.selectStrokeWidth=typedArray.getDimension(R.styleable.ShapeView_select_stroke_width,0.0F);

        shapeAttribute.cornersRadius=typedArray.getDimension(R.styleable.ShapeView_corners_radius, 0.0F);
        shapeAttribute.topLeftRadius=typedArray.getDimension(R.styleable.ShapeView_top_left_radius, 0.0F);
        shapeAttribute.topRightRadius=typedArray.getDimension(R.styleable.ShapeView_top_right_radius, 0.0F);
        shapeAttribute.bottomLeftRadius=typedArray.getDimension(R.styleable.ShapeView_bottom_left_radius, 0.0F);
        shapeAttribute.bottomRightRadius=typedArray.getDimension(R.styleable.ShapeView_bottom_right_radius, 0.0F);

        shapeAttribute.shape=typedArray.getInt(R.styleable.ShapeView_shape_view,0);

        //TextView/EditView属性
        shapeAttribute.textColor = typedArray.getColor(R.styleable.ShapeView_text_color,Color.TRANSPARENT);
        shapeAttribute.selectTextColor = typedArray.getColor(R.styleable.ShapeView_select_text_color,Color.TRANSPARENT);
        shapeAttribute.unselectDrawable=typedArray.getDrawable(R.styleable.ShapeView_unselect_drawable);
        shapeAttribute.selectDrawable=typedArray.getDrawable(R.styleable.ShapeView_select_drawable);
        shapeAttribute.drawableDirection=typedArray.getInt(R.styleable.ShapeView_drawable_direction,0);//默认为0 显示左边

        Log.i("ansen","unselectDrawable:"+shapeAttribute.unselectDrawable);

        typedArray.recycle();
        return shapeAttribute;
    }

    public static void setBackground(View view,ShapeAttribute shapeAttribute){
        if(view==null||shapeAttribute==null){
            return ;
        }

        shapeAttribute.selected=view.isSelected();

        GradientDrawable gradientDrawable=new GradientDrawable();

        int startColor=shapeAttribute.getStartColor();
        int centerColor=shapeAttribute.getCenterColor();
        int endColor=shapeAttribute.getEndColor();
        int solidColor=shapeAttribute.getSolidColor();

        Log.i("ansen","是否选中:"+shapeAttribute.selected+" startColor:"+startColor+" centerColor:"+centerColor+" solidColor:"+solidColor+" endColor:"+endColor);

        if(startColor!=0&&endColor!=0){//开始颜色跟结束颜色都不为空 设置背景渐变色
            if(centerColor!=0){
                gradientDrawable.setColors(new int[]{startColor,centerColor,endColor});
            }else{
                gradientDrawable.setColors(new int[]{startColor,endColor});
            }

            gradientDrawable.setOrientation(getOrientation(shapeAttribute.colorOrientation));//设置颜色渐变方向
        }else if(solidColor!=0){//设置背景颜色
            gradientDrawable.setColor(solidColor);
        }

        int strokeColor=shapeAttribute.getStrokeColor();
        float strokeWidth=shapeAttribute.getStrokeWidth();

        Log.i("ansen","strokeColor:"+strokeColor+" strokeWidth:"+strokeWidth);

        if(strokeColor!=0&&strokeWidth!=0){//设置边框
            gradientDrawable.setStroke((int)strokeWidth,strokeColor);
        }

        gradientDrawable.setShape(shapeAttribute.shape);//设置形状(矩形、椭圆形、一条线、环形)

        float topLeftRadius=shapeAttribute.topLeftRadius;
        float topRightRadius=shapeAttribute.topRightRadius;
        float bottomLeftRadius=shapeAttribute.bottomLeftRadius;
        float bottomRightRadius=shapeAttribute.bottomRightRadius;

        if(topLeftRadius!=0||topRightRadius!=0||bottomLeftRadius!=0||bottomRightRadius!=0){//设置某个角弧度
            gradientDrawable.setCornerRadii(new float[]{topLeftRadius,topLeftRadius,topRightRadius,topRightRadius,
                    bottomRightRadius,bottomRightRadius,bottomLeftRadius,bottomLeftRadius});
        }else if(shapeAttribute.cornersRadius>0){
            gradientDrawable.setCornerRadius(shapeAttribute.cornersRadius);//设置弧度
        }

        view.setBackground(gradientDrawable);
    }

    private static GradientDrawable.Orientation getOrientation(int orientation){
        if(orientation==1){
            return GradientDrawable.Orientation.LEFT_RIGHT;
        }else if(orientation==2){
            return GradientDrawable.Orientation.RIGHT_LEFT;
        }else if(orientation==3){
            return GradientDrawable.Orientation.TOP_BOTTOM;
        }else if(orientation==4){
            return GradientDrawable.Orientation.BOTTOM_TOP;
        }else if(orientation==5){
            return GradientDrawable.Orientation.TR_BL;
        }else if(orientation==6){
            return GradientDrawable.Orientation.BR_TL;
        }else if(orientation==7){
            return GradientDrawable.Orientation.BL_TR;
        }else if(orientation==8){
            return GradientDrawable.Orientation.TL_BR;
        }
        return GradientDrawable.Orientation.LEFT_RIGHT;
    }

    public static int getOrientation(GradientDrawable.Orientation orientation){
        if(orientation==GradientDrawable.Orientation.LEFT_RIGHT){
            return 1;
        }else if(orientation==GradientDrawable.Orientation.RIGHT_LEFT){
            return 2;
        }else if(orientation==GradientDrawable.Orientation.TOP_BOTTOM){
            return 3;
        }else if(orientation==GradientDrawable.Orientation.BOTTOM_TOP){
            return 4;
        }else if(orientation==GradientDrawable.Orientation.TR_BL){
            return 5;
        }else if(orientation==GradientDrawable.Orientation.BR_TL){
            return 6;
        }else if(orientation==GradientDrawable.Orientation.BL_TR){
            return 7;
        }else if(orientation==GradientDrawable.Orientation.TL_BR){
            return 8;
        }
        return 1;
    }
}
