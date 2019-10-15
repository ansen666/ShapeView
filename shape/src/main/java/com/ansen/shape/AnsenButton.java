package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.ansen.shape.util.ShapeUtil;

public class AnsenButton extends AppCompatButton {
    public AnsenButton(Context context) {
        this(context,null);
    }

    public AnsenButton(Context context, AttributeSet attrs){
        this(context, attrs,android.R.attr.buttonStyle);
    }

    public AnsenButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
