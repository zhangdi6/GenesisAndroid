package com.iruiyou.pet.utils;

import android.content.Context;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 *  sgf 动画工具类
 *
 * 调用方式： view.startAnimation(AnimationUtils.method(...))
 */
public class AnimationUtils {
    /**
     * 渐变的一种效果
     *
     * @param fromAlpha
     * @param toAlpha
     */
    public static Animation setAlphaAnimation(float fromAlpha, float toAlpha) {
        Animation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(2500);
        return alphaAnimation;
    }

    /**
     * 缩放的一种效果
     */
    public static Animation setScaleAnimation(){
        //自身从大到小
//        Animation   alphaAnimation = new ScaleAnimation(1f,0.2f,1f,0.2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //自身从小到大
        Animation   alphaAnimation = new ScaleAnimation(0.2f,1f,0.2f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //父控件从小到大
//        Animation alphaAnimation = new ScaleAnimation(2.0f,1f,2.0f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        alphaAnimation.setDuration(800);
        return alphaAnimation;
    }
    /**
     * 缩放的一种效果
     */
    public static Animation setScaleAnimation2(){
        //自身从大到小
//        Animation   alphaAnimation = new ScaleAnimation(1f,0.2f,1f,0.2f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //自身从小到大
//        Animation   alphaAnimation = new ScaleAnimation(0.2f,1f,0.2f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //父控件从小到大
        Animation alphaAnimation = new ScaleAnimation(11.0f,1f,11.0f,1f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        alphaAnimation.setDuration(900);
        return alphaAnimation;
    }

    /**
     * 定义从屏幕顶部进入的动画效果
     *
     * @param context
     */
    public static Animation inFromTopAnimation(Context context) {
        Animation inFromTop = new TranslateAnimation(0.0f, 0.0f, -DensityUtils.getScreenHeight(context), 0.0f);
        inFromTop.setFillAfter(true);
        inFromTop.setDuration(500);
        inFromTop.setRepeatMode(Animation.ZORDER_BOTTOM);
        return inFromTop;
    }

    /**
     * 定义从屏幕顶部退出的动画效果
     *
     * @param context
     */
    public static Animation outToTopAnimation(Context context) {
        Animation outToTop = new TranslateAnimation(0.0f, 0.0f, 0.0f, -DensityUtils.getScreenHeight(context));
        outToTop.setFillAfter(true);
        outToTop.setDuration(500);
        outToTop.setRepeatMode(Animation.ZORDER_BOTTOM);
        return outToTop;
    }

    /**
     * 定义从屏幕底部进入的动画效果
     *
     * @param context
     */
    public static Animation inFromBottomAnimation(Context context) {
        Animation inFromBottom = new TranslateAnimation(0.0f, 0.0f, DensityUtils.getScreenHeight(context), 0.0f);
        inFromBottom.setFillAfter(true);
        inFromBottom.setDuration(2500);
        inFromBottom.setRepeatMode(Animation.ZORDER_TOP);
        return inFromBottom;
    }
    /**
     * 定义从屏幕底部进入的动画效果
     *
     * @param context
     */
    public static Animation inFromBottomAnimation1(Context context) {
        Animation inFromBottom = new TranslateAnimation(0.0f, 0.0f, DensityUtils.getScreenHeight(context), 0.0f);
        inFromBottom.setFillAfter(true);
        inFromBottom.setDuration(900);
        inFromBottom.setRepeatMode(Animation.ZORDER_TOP);
        return inFromBottom;
    }
    /**
     * 定义从屏幕底部进入的动画效果
     *
     * @param context
     */
    public static Animation inFromBottomAnimation2(Context context) {
        Animation inFromBottom = new TranslateAnimation(0.0f, 0.0f, DensityUtils.getScreenHeight(context), 0.0f);
        inFromBottom.setFillAfter(true);
        inFromBottom.setDuration(1200);
        inFromBottom.setRepeatMode(Animation.ZORDER_TOP);
        return inFromBottom;
    }
    /**
     * 定义从屏幕底部进入的动画效果
     *
     * @param context
     */
    public static Animation inFromBottomAnimation3(Context context) {
        Animation inFromBottom = new TranslateAnimation(0.0f, 0.0f, DensityUtils.getScreenHeight(context), 0.0f);
        inFromBottom.setFillAfter(true);
        inFromBottom.setDuration(2000);
        inFromBottom.setRepeatMode(Animation.ZORDER_TOP);
        return inFromBottom;
    }

    /**
     * 定义从屏幕底部退出的动画效果
     *
     * @param context
     */
    public static Animation outToBottomAnimation(Context context) {
        Animation outToBottom = new TranslateAnimation(0.0f, 0.0f, 0.0f, DensityUtils.getScreenHeight(context));
        outToBottom.setFillAfter(true);
        outToBottom.setDuration(500);
        outToBottom.setRepeatMode(Animation.ZORDER_NORMAL);
        return outToBottom;
    }

    /**
     * 定义从左侧进入的动画效果
     */
    public static Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }


    /**
     * 定义从左侧退出的动画效果
     */
    public static Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }


    /**
     * 定义从右侧进入的动画效果
     */
    public static Animation inFromRightAnimation() {
        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    /**
     * 定义从右侧退出时的动画效果
     */
    public static Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }
}
