package com.iruiyou.http.retrofit_rx;

import android.app.Application;

import com.orhanobut.logger.Logger;


/**
 * 全局app
 * Created by WZG on 2016/12/12.
 */

public class RxRetrofitApp  {
    private static Application application;
    private static boolean debug;

    public static void init(Application app){
        setApplication(app);
        setDebug(true);
        //初始化Log
        Logger.init("RyCommon");
    }

    public static void init(Application app,boolean debug){
        setApplication(app);
        setDebug(debug);
    }

    public static Application getApplication() {
        return application;
    }

    private static void setApplication(Application application) {
        RxRetrofitApp.application = application;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        RxRetrofitApp.debug = debug;
    }
}
