package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.ansen.shape.util.ShapeUtil;

public class AnsenFrameLayout extends FrameLayout {
    public AnsenFrameLayout(Context context) {
        this(context,null);
    }

    public AnsenFrameLayout(Context context, AttributeSet attrs){
        this(context, attrs,0);
    }

    public AnsenFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
