package com.iruiyou.http.retrofit_rx.Api;

import com.iruiyou.http.retrofit_rx.RxRetrofitApp;
import com.iruiyou.http.retrofit_rx.http.HttpManager;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextSubListener;
import com.iruiyou.http.retrofit_rx.utils.NetUtils;
import com.iruiyou.http.retrofit_rx.utils.T;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 * Created by WZG on 2016/7/16.
 */
public class HttpManagerApi extends BaseApi {
    private HttpManager manager;

    public HttpManagerApi(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextListener, appCompatActivity);
    }

    public HttpManagerApi(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextSubListener, appCompatActivity);
    }

    protected Retrofit getPositionRetrofit(){
        return manager.getReTrofit(getConnectionTime(), getDoumiRequestUrl());
    }

    protected Retrofit getRetrofit() {
        return manager.getReTrofit(getConnectionTime(), getBaseUrl());
    }


    protected void doHttpDeal(Observable observable) {
        if (!NetUtils.isNetworkAvailable(RxRetrofitApp.getApplication())) {
            try {
                T.showShort("No connection network");
            } catch (Exception e){
            }
        }
        manager.httpDeal(observable, this);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        return null;
    }
}
