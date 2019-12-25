package com.iruiyou.pet.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;

import java.util.Objects;
import java.util.Random;

/**
 * 输入框的飞入动画效果
 * Created by sgf
 */
public class SgfBiuEditText extends AppCompatEditText {
    private ViewGroup contentContainer;
    private int height;
    private String cacheStr = "";
    private int textCount=0;

    public SgfBiuEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        setlistener();
    }

    private void setlistener() {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (cacheStr.length() < s.length()) {
                    char last = s.charAt(s.length() - 1);
                    textCount++;
                    update(last, true);
                } else {
                    char last = cacheStr.charAt(cacheStr.length() - 1);
                    textCount++;
                    update(last, false);
                }
                cacheStr = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void update(char last, boolean isUp) {
        final TextView textView = new TextView(getContext());
//        Random random=new Random();//随机数
        if(textCount%3==0){//设置不同的颜色
            textView.setTextColor(getResources().getColor(R.color._26c68a));
        }  if(textCount%3==1){
            textView.setTextColor(getResources().getColor(R.color._ffca48));
        } if(textCount%3==2){
            textView.setTextColor(getResources().getColor(R.color.red));
        }else{
            int min=1;//以产生[10,99]范围内的随机数为例
            int max=60;
            Random random = new Random();
            int num = random.nextInt(max)%(max-min+1) + min;
            if(num<=10){
                textView.setTextColor(getResources().getColor(R.color.blue));
            }else if(num<=20){
                textView.setTextColor(getResources().getColor(R.color.color_orange));
            }else if(num<=30){
                textView.setTextColor(getResources().getColor(R.color._262a30));
            }else if(num<=40){
                textView.setTextColor(getResources().getColor(R.color._85cdff));
            }else if(num<=50){
                textView.setTextColor(getResources().getColor(R.color._7a7a7a));
            }else {
                textView.setTextColor(getResources().getColor(R.color._1669d9));
            }
//            textView.setTextColor(getResources().getColor(R.color.blue));
        }
//        textView.setTextColor(getResources().getColor(android.R.color.holo_blue_light));//设置输入字体的颜色
        textView.setTextSize(30);
        textView.setText(String.valueOf(last));
        textView.setGravity(Gravity.CENTER);
        contentContainer.addView(textView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.measure(0, 0);


        int pos = getSelectionStart();
        Layout layout = getLayout();
        int line = layout.getLineForOffset(pos);
        int baseline = layout.getLineBaseline(line);
        int ascent = layout.getLineAscent(line);

        float startX = 0;
        float startY = 0;
        float endX = 0;
        float endY = 0;
        if (isUp) {
            startX = layout.getPrimaryHorizontal(pos) + 100;
            startY = height / 3 * 2;
            endX = startX;
            endY = baseline + ascent;
        } else {
            endX = new Random().nextInt(contentContainer.getWidth());
            endY = height / 3 * 2;
            startX = layout.getPrimaryHorizontal(pos) + 70;
            startY = baseline + ascent;
        }


        final AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "translationX", startX, endX);
        ObjectAnimator animY = ObjectAnimator.ofFloat(textView, "translationY", startY, endY);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(textView, "scaleX", 0.6f, 1.2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(textView, "scaleY", 0.6f, 1.2f);

        animY.setInterpolator(new DecelerateInterpolator());
        animSet.setDuration(600);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                contentContainer.removeView(textView);
            }
        });
        animSet.playTogether(animX, animY, scaleX, scaleY);
        animSet.start();
    }


    private void init() {
        contentContainer = ((MainActivity) getContext()).findViewById(android.R.id.content);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        height = Objects.requireNonNull(windowManager).getDefaultDisplay().getHeight();
    }

}

