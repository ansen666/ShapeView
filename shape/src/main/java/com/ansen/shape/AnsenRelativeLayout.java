package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ansen.shape.util.ShapeUtil;

public class AnsenRelativeLayout extends RelativeLayout {
    public AnsenRelativeLayout(Context context) {
        this(context,null);
    }

    public AnsenRelativeLayout(Context context, AttributeSet attrs){
        this(context, attrs,0);
    }

    public AnsenRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
