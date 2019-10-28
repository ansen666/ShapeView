package com.ansen.testshape;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ansen.shape.AnsenTextView;
import com.ansen.shape.AnsenView;

public class MainActivity extends AppCompatActivity {
    private AnsenView ansenView;
    private boolean change=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ansenView=findViewById(R.id.view_line);
        findViewById(R.id.tv_dynamic_alteration).setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //改变View的渐变色
            if(change){
                ansenView.setStartColor(0xFFFF8B59);
                ansenView.setEndColor(0xFFF64848);
            }else{
                ansenView.setStartColor(0xFFFF68FF);
                ansenView.setEndColor(0xFF973DFF);
            }
            change=!change;
            ansenView.resetBackground();//设置了属性之后需要调用这个方法
        }
    };
}
