package com.ansen.shape;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.ansen.shape.util.ShapeUtil;

public class AnsenEditText extends AppCompatEditText {
    public AnsenEditText(Context context) {
        this(context,null);
    }

    public AnsenEditText(Context context, AttributeSet attrs){
        this(context, attrs,android.R.attr.editTextStyle);
    }

    public AnsenEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        ShapeUtil.setBackground(this,attrs);
    }
}
