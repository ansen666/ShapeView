package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ansen.shape.util.ShapeUtil;

public class AnsenLinearLayout extends LinearLayout {
    public AnsenLinearLayout(Context context) {
        this(context,null);
    }

    public AnsenLinearLayout(Context context, AttributeSet attrs){
        this(context, attrs,0);
    }

    public AnsenLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
