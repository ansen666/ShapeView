package com.ansen.shape.util;

import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.ansen.shape.R;

public class ShapeUtil {
    public static void setBackground(View view,AttributeSet attrs){
        TypedArray typedArray = view.getContext().obtainStyledAttributes(attrs, R.styleable.ShapeView);
        int solidColor=typedArray.getColor(R.styleable.ShapeView_solid_color,-1);

        int startColor=typedArray.getColor(R.styleable.ShapeView_start_color,-1);
        int centerColor=typedArray.getColor(R.styleable.ShapeView_center_color,-1);
        int endColor=typedArray.getColor(R.styleable.ShapeView_end_color,-1);

        int orientation=typedArray.getInt(R.styleable.ShapeView_orientation,1);//默认从左到右

        int strokeColor = typedArray.getColor(R.styleable.ShapeView_stroke_color,-1);
        float strokeWidth=typedArray.getDimension(R.styleable.ShapeView_stroke_width,0.0F);

        float cornersRadius=typedArray.getDimension(R.styleable.ShapeView_corners_radius, 0.0F);
        float topLeftRadius=typedArray.getDimension(R.styleable.ShapeView_top_left_radius, 0.0F);
        float topRightRadius=typedArray.getDimension(R.styleable.ShapeView_top_right_radius, 0.0F);
        float bottomLeftRadius=typedArray.getDimension(R.styleable.ShapeView_bottom_left_radius, 0.0F);
        float bottomRightRadius=typedArray.getDimension(R.styleable.ShapeView_bottom_right_radius, 0.0F);

        int shape=typedArray.getInt(R.styleable.ShapeView_shape,0);

        typedArray.recycle();

        GradientDrawable gradientDrawable=new GradientDrawable();

        if(startColor!=-1&&endColor!=-1){//开始颜色跟结束颜色都不为空 设置背景渐变色
            if(centerColor!=-1){
                gradientDrawable.setColors(new int[]{startColor,centerColor,endColor});
            }else{
                gradientDrawable.setColors(new int[]{startColor,endColor});
            }

            gradientDrawable.setOrientation(ShapeUtil.getOrientation(orientation));//设置渐变方向
        }else if(solidColor!=-1){//设置背景颜色
            gradientDrawable.setColor(solidColor);
        }

        if(strokeColor!=-1&&strokeWidth!=0){//设置边框
            gradientDrawable.setStroke((int)strokeWidth,strokeColor);
        }

        gradientDrawable.setShape(shape);//设置形状(矩形、椭圆形、一条线、环形)

        if(topLeftRadius!=0||topRightRadius!=0||bottomLeftRadius!=0||bottomRightRadius!=0){//设置某个角弧度
            gradientDrawable.setCornerRadii(new float[]{topLeftRadius,topLeftRadius,topRightRadius,topRightRadius,
                    bottomRightRadius,bottomRightRadius,bottomLeftRadius,bottomLeftRadius});
        }else if(cornersRadius>0){
            gradientDrawable.setCornerRadius(cornersRadius);//设置弧度
        }
        view.setBackground(gradientDrawable);
    }

    public static GradientDrawable.Orientation getOrientation(int orientation){
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
}
