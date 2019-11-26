package com.ansen.testshape;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ansen.shape.AnsenFrameLayout;
import com.ansen.shape.AnsenTextView;
import com.ansen.shape.AnsenView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnsenTextView tvDynamicAlteration;
    private boolean change=false;

    private AnsenTextView tvMale,tvWoman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDynamicAlteration=findViewById(R.id.tv_dynamic_alteration);
        tvDynamicAlteration.setOnClickListener(this);

        tvMale=findViewById(R.id.tv_male);
        tvMale.setSelected(true);//默认选中男
        tvWoman=findViewById(R.id.tv_woman);

        tvMale.setOnClickListener(this);
        tvWoman.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_dynamic_alteration){
            //改变View的渐变色
            if(change){
                tvDynamicAlteration.setStartColor(0xFFFF8B59);
                tvDynamicAlteration.setEndColor(0xFFF64848);
                //背景渐变色方向从上到下
                tvDynamicAlteration.setColorOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
            }else{
                tvDynamicAlteration.setStartColor(0xFFFF68FF);
                tvDynamicAlteration.setEndColor(0xFF973DFF);
                //背景渐变色方向从右边到左边
                tvDynamicAlteration.setColorOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
            }

            change=!change;
            tvDynamicAlteration.resetBackground();//设置了属性之后需要调用这个方法

        }else if(v.getId()==R.id.tv_change_select){//改变选中效果
            Log.i("ansen","点击之后");
            ((AnsenTextView)v).setSelected(!v.isSelected(),true);
        }else if(v.getId()==R.id.tv_one){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_male){//男选中
            tvMale.setSelected(true);
            tvWoman.setSelected(false);
        }else if(v.getId()==R.id.tv_woman){//女选中
            tvMale.setSelected(false);
            tvWoman.setSelected(true);
        }
    }
}
