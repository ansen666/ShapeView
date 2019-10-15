package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.ansen.shape.util.ShapeUtil;

public class AnsenTextView extends AppCompatTextView {
    public AnsenTextView(Context context) {
        this(context,null);
    }

    public AnsenTextView(Context context, AttributeSet attrs){
        this(context, attrs,android.R.attr.textViewStyle);
    }

    public AnsenTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
