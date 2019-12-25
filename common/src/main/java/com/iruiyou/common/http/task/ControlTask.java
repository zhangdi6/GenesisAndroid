package com.iruiyou.common.http.task;

import com.iruiyou.http.retrofit_rx.Api.HttpManagerApi;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextSubListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * 多api共存方案
 * Created by WZG on 2017/4/13.
 */

public class ControlTask extends HttpManagerApi {

    public ControlTask(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        super(onNextListener, appCompatActivity);
        /*统一设置*/
        setCache(false);
    }

    public ControlTask(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        super(onNextSubListener, appCompatActivity);
        /*统一设置*/
        setCache(false);
    }



}
