package com.iruiyou.http.retrofit_rx.utils;

import android.text.TextUtils;

import com.iruiyou.http.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * 类描述:
 * 作者：Created by JiaoPeiRong on 2017/4/25 22:23
 * 邮箱：chinajpr@163.com
 */

public class L {
    private static final boolean isShow = BuildConfig.ENABLE_DEBUG;
    public static void d(String log) {
        if (isShow&&(!TextUtils.isEmpty(log))){
            Logger.d(log);
        }
    }

    public static void json(String json){
        if (isShow&&(!TextUtils.isEmpty(json))){
            Logger.json(json);
        }
    }

    public static void e(String log){
        if (isShow&&(!TextUtils.isEmpty(log))){
            Logger.e(log);
        }
    }

    public static void d(String tag , String log){
        if (isShow&&(!TextUtils.isEmpty(log))){
            Logger.t(tag).d(log);
        }
    }
}
