package com.ansen.testshape;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ansen.shape.AnsenTextView;
import com.ansen.shape.AnsenView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnsenTextView tvDynamicAlteration;
    private boolean change=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDynamicAlteration=findViewById(R.id.tv_dynamic_alteration);
        tvDynamicAlteration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_dynamic_alteration){
            //改变View的渐变色
            if(change){
                tvDynamicAlteration.setStartColor(0xFFFF8B59);
                tvDynamicAlteration.setEndColor(0xFFF64848);
            }else{
                tvDynamicAlteration.setStartColor(0xFFFF68FF);
                tvDynamicAlteration.setEndColor(0xFF973DFF);
            }
            change=!change;
            tvDynamicAlteration.resetBackground();//设置了属性之后需要调用这个方法
        }
    }
}
