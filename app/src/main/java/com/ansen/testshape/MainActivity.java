package com.ansen.testshape;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ansen.shape.AnsenFrameLayout;
import com.ansen.shape.AnsenLinearLayout;
import com.ansen.shape.AnsenTextView;
import com.ansen.shape.AnsenView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnsenTextView tvDynamicAlteration;
    private boolean change=false;

    private AnsenTextView tvMale,tvWoman,tvTag,atvBorderText;
    private AnsenLinearLayout llGoddess,llOrdinaryGirls;

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

        tvTag=findViewById(R.id.tv_tag);
        tvTag.setOnClickListener(this);

        llGoddess=findViewById(R.id.ll_goddess);
        llGoddess.setSelected(true);
        llOrdinaryGirls=findViewById(R.id.ll_ordinary_girls);

        llGoddess.setOnClickListener(this);
        llOrdinaryGirls.setOnClickListener(this);

        atvBorderText=findViewById(R.id.atv_border_text);
        atvBorderText.setOnClickListener(this);
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
                tvDynamicAlteration.setStartColor(0xFFFF68FF);//直接写颜色
                tvDynamicAlteration.setEndColor(getResources().getColor(R.color.violet));//从资源文件获取颜色

                //背景渐变色方向从右边到左边
                tvDynamicAlteration.setColorOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
            }

            change=!change;
            tvDynamicAlteration.resetBackground();//设置了属性之后需要调用这个方法
        }else if(v.getId()==R.id.tv_change_select){//改变选中效果
            Log.i("ansen","点击之后");
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_change_select_two){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_one){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_male){//男选中
            tvMale.setSelected(true);
            tvWoman.setSelected(false);
        }else if(v.getId()==R.id.tv_woman){//女选中
            tvMale.setSelected(false);
            tvWoman.setSelected(true);
        }else if(v.getId()==R.id.tv_press_one){
            Toast.makeText(this,"按压效果",Toast.LENGTH_SHORT).show();
        }else if(v.getId()==R.id.tv_tag){
            tvTag.setStrokeColor(getResources().getColor(R.color.tag_clicl_color));
            tvTag.resetBackground();//设置了属性之后需要调用这个方法
        }else if(v.getId()==R.id.ll_goddess){
            llGoddess.setSelected(true);
            llOrdinaryGirls.setSelected(false);
        }else if(v.getId()==R.id.ll_ordinary_girls){
            llGoddess.setSelected(false);
            llOrdinaryGirls.setSelected(true);
        }else if(v.getId()==R.id.atv_border_text){
//            Log.i("ansen","点击");
            atvBorderText.setSelected(!atvBorderText.isSelected());
            if(atvBorderText.isSelected()){
                atvBorderText.setStartColor(0xFF5BC9FF);
                atvBorderText.setCenterColor(0);//0就是不设置
                atvBorderText.setEndColor(0xFF4669F6);
            }else{
                atvBorderText.setStartColor(0xFFFFE61B);
                atvBorderText.setCenterColor(0xFF5BC9FF);
                atvBorderText.setEndColor(0xFFF80FE4);
            }
            atvBorderText.invalidate();//重绘
        }else if(v.getId()==R.id.tv_age){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_age_two){
            v.setSelected(!v.isSelected());
        }
    }
}
