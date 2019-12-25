package com.iruiyou.common;

import android.app.Application;

import com.iruiyou.common.analytics.AnalyticsCenter;
import com.iruiyou.http.retrofit_rx.RxRetrofitApp;

/**
 * RyCommon控制中心
 * 作者: Created by JiaoPeiRong on 2018/4/23
 * 邮箱:chinajpr@163.com
 */

public class RyCommon {
    private static RyCommon ryCommon = null;
    private Configuration configuration;

    /**
     * 获取单一实例
     * @return
     */
    public static RyCommon getInstance(){
        if (ryCommon == null){
            synchronized (RyCommon.class){
                if (ryCommon == null){
                    ryCommon = new RyCommon();
                }
            }
        }
        return ryCommon;
    }

    /**
     * 私有化实例
     */
    private RyCommon(){

    }

    /**
     * 控制器初始化
     * @param context
     * @param configuration
     * @param initializeListener
     */
    public void init(Application context , Configuration configuration , InitializeListener initializeListener){
        if (context == null || configuration == null){
            return;
        }
        configuration.setContext(context);
        this.configuration = configuration;
        //初始化网络框架
        RxRetrofitApp.init(context);
        //初始化友盟
        AnalyticsCenter.init(context);
        //屏幕适配框架
        //AutoLayoutConifg.getInstance().useDeviceSize();

    }

    public Configuration getConfiguration() {
        return configuration;
    }


}
