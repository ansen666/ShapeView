package com.ansen.testshape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ansen.shape.AnsenFrameLayout;
import com.ansen.shape.AnsenImageView;
import com.ansen.shape.AnsenLinearLayout;
import com.ansen.shape.AnsenTextView;
import com.ansen.shape.AnsenView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AnsenTextView tvDynamicAlteration;
    private boolean change=false;

    private AnsenTextView tvMale,tvWoman,tvTag,atvBorderText,tvSelectSex,tvAgreement;
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

        tvSelectSex=findViewById(R.id.tv_select_sex);
        tvSelectSex.setShowDrawable(false);

        tvAgreement=findViewById(R.id.tv_agreement);
        tvAgreement.setText(getClickableHtml(getString(R.string.privacy_policy_content)));
        tvAgreement.setClickable(true);
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());

        findViewById(R.id.aiv_one).setOnClickListener(this);
        findViewById(R.id.tv_follow).setOnClickListener(this);
        findViewById(R.id.tv_age_three).setOnClickListener(this);
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
        }else if(v.getId()==R.id.tv_select){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.iv_image){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.aiv_one){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_follow){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_age_three){
            v.setSelected(!v.isSelected());
        }else if(v.getId()==R.id.tv_select_sex){
            tvSelectSex.setShowDrawable(true);
            tvSelectSex.setSelected(!tvSelectSex.isSelected());
        }else if(v.getId()==R.id.tv_agreement){
            v.setSelected(!v.isSelected());
        }
    }


    /**
     * 格式化超链接文本内容并设置点击处理
     */
    public CharSequence getClickableHtml(String html) {
        Spanned spannedHtml = Html.fromHtml(html);
        SpannableStringBuilder clickableHtmlBuilder = new SpannableStringBuilder(spannedHtml);
        URLSpan[] urls = clickableHtmlBuilder.getSpans(0, spannedHtml.length(), URLSpan.class);
        for (final URLSpan span : urls) {
            setLinkClickable(clickableHtmlBuilder, span);
        }
        return clickableHtmlBuilder;
    }

    /**
     * 设置点击超链接对应的处理内容
     */
    public void setLinkClickable(final SpannableStringBuilder clickableHtmlBuilder, final URLSpan urlSpan) {
        int start = clickableHtmlBuilder.getSpanStart(urlSpan);
        int end = clickableHtmlBuilder.getSpanEnd(urlSpan);
        int flags = clickableHtmlBuilder.getSpanFlags(urlSpan);

        ClickableSpan clickableSpan = new ClickableSpan() {
            public void onClick(View view) {
                Log.i("ansen",urlSpan.getURL());
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(getResources().getColor(R.color.colorAccent));
                //超链接形式的下划线，false 表示不显示下划线，true表示显示下划线,其实默认也是true，如果要下划线的话可以不设置
                ds.setUnderlineText(false);
            }
        };
//        Log.i("url:" + urlSpan.getURL());
        clickableHtmlBuilder.setSpan(clickableSpan, start, end, flags);
    }
}
