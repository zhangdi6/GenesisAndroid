package com.iruiyou.common.utils;

import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author jiao on 2016/8/2 14:32
 * @E-mail: jiaopeirong@iruiyou.com
 * 类说明:动画工具类
 */
public class AnimatorUtils {
    private static AnimatorUtils animatorUtils = null;

    public static AnimatorUtils getInstance() {
        if (null == animatorUtils) {
            synchronized (AnimatorUtils.class) {
                if (null == animatorUtils) {
                    animatorUtils = new AnimatorUtils();
                }
            }
        }
        return animatorUtils;
    }

    /**
     * 私有化构造
     */
    private AnimatorUtils() {

    }

    /**
     * 左右震动
     *
     * @param view
     */
    public void setShakeAnimator(View view) {
        ObjectAnimator.ofFloat(view, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0).
                setDuration(1000).start();
    }

    /**
     * 点击变暗效果
     * 请在控件点击前调用,否则无效
     * @param view
     */
    public void clickAnimation(final View... view) {
        for (View v : view) {
            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            ObjectAnimator.ofFloat(v, "alpha", 1, 0.3f).setDuration(300).start();
                            break;
                        case MotionEvent.ACTION_UP:
                            ObjectAnimator.ofFloat(v, "alpha", 0.3f, 1).setDuration(300).start();
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            ObjectAnimator.ofFloat(v, "alpha", 0.3f, 1).setDuration(300).start();
                            break;
                    }
                    return false;
                }
            });
        }

    }

}
