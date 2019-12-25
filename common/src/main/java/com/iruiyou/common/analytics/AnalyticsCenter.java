

package com.iruiyou.common.analytics;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

/**
 * 统计中心类
 */
public class AnalyticsCenter {
    /**
     * 自定义事件
     */
    public static final String EV_SHARE = "share";

    /**
     * 初始化
     */
    public static void init(Context context) {
        MobclickAgent.UMAnalyticsConfig umAnalyticsConfig = new MobclickAgent.UMAnalyticsConfig
                (context, "5d5b5e064ca357e19c000af0", "", MobclickAgent.EScenarioType.E_UM_NORMAL , false);//  58fca10107fe65458100121e
        MobclickAgent.startWithConfigure(umAnalyticsConfig);
    }

    /**
     * 唤醒activity时调用
     *
     * @param activity
     */
    public static void onActivityResume(Activity activity) {
        MobclickAgent.onResume(activity);
    }

    /**
     * 暂停activity时调用
     *
     * @param activity
     */
    public static void onActivityPause(Activity activity) {
        MobclickAgent.onPause(activity);
    }

    /**
     * 页面resume时调用
     *
     * @param name
     */
    public static void onPageStart(String name) {
        MobclickAgent.onPageStart(name);
    }

    /**
     * 页面pause时调用
     *
     * @param name
     */
    public static void onPageEnd(String name) {
        MobclickAgent.onPageEnd(name);
    }


    /**
     * 用户登录时调用
     *
     * @param userId
     */
    public static void onLogin(String userId) {
        MobclickAgent.onProfileSignIn(userId);
    }

    /**
     * 用户登录时调用
     *
     * @param provider 第三方登录时，用来判断第三方
     * @param userId
     */
    public static void onLogin(String provider, String userId) {
        MobclickAgent.onProfileSignIn(provider, userId);
    }

    /**
     * 登出时调用
     */
    public static void onLogoff() {
        MobclickAgent.onProfileSignOff();
    }

    /**
     * 用户kill或exit方式退出app前调用
     *
     * @param context
     */
    public static void onKillProcess(Context context) {
        MobclickAgent.onKillProcess(context);
    }

    /**
     * 自定义事件
     *
     * @param activity
     * @param eventId
     */
    public static void onEvent(Activity activity, String eventId) {
        MobclickAgent.onEvent(activity, eventId);
    }

    /**
     * 统计错误
     *
     * @param context
     * @param error
     */
    public static void reportError(Context context, String error) {
        MobclickAgent.reportError(context, error);
    }

    /**
     * 统计异常
     *
     * @param context
     * @param e
     */
    public static void reportException(Context context, Throwable e) {
        MobclickAgent.reportError(context, e);
    }

}
