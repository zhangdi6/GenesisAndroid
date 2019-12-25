package com.iruiyou.common;

import android.content.Context;

import java.lang.ref.SoftReference;

/**
 * 类描述:初始化
 * 作者：Created by JiaoPeiRong on 2017/4/23 11:55
 * 邮箱：chinajpr@163.com
 */

public class Configuration {
    private static final Configuration configuration = new Configuration();
    private SoftReference<Context> context;

    public static Configuration getConfiguration(){
        return configuration;
    }

    public Context getContext() {
        return context.get();
    }

    public void setContext(Context context) {
        this.context = new SoftReference<>(context);
    }
}
