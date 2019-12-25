package com.iruiyou.http.retrofit_rx.subscribers;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.RxRetrofitApp;
import com.iruiyou.http.retrofit_rx.bean.LoginMsg;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.exception.CodeException;
import com.iruiyou.http.retrofit_rx.exception.HttpTimeException;
import com.iruiyou.http.retrofit_rx.http.cookie.CookieResulte;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.AppUtil;
import com.iruiyou.http.retrofit_rx.utils.CookieDbUtil;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.http.retrofit_rx.utils.L;
import com.iruiyou.http.retrofit_rx.view.CustomProgress;

import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.Subscriber;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by WZG on 2016/7/16.
 */
public class ProgressSubscriber<T> extends Subscriber<T> {
    /*是否弹框*/
    private boolean showPorgress = true;
    //    回调接口
//    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;//软引用不能随便用，使用软引用后，mSubscriberOnNextListener经常为null,导致回调失败
    private HttpOnNextListener mSubscriberOnNextListener;
    //    软引用反正内存泄露
//    private SoftReference<Context> mActivity;
    private Context mActivity;
    //    加载框可自己定义
    private ProgressDialog pd;
    private CustomProgress cp;
    /*请求数据*/
    private BaseApi api;
    //是否进行base64解码?自己服务器的数据都进行了base64解码,不是自己服务器的数据不用进行base64解码
    private boolean isBase64 = true;

    /**
     * 构造
     *
     * @param api
     */
    public ProgressSubscriber(BaseApi api, HttpOnNextListener listenerSoftReference, Context
            mActivity) {
        this.api = api;
        this.mSubscriberOnNextListener = listenerSoftReference;
        this.mActivity = mActivity;
        this.isBase64 = api.isBase64();
        setShowPorgress(api.isShowProgress());
        if (api.isShowProgress()) {
            initProgressDialog(api.isCancel());
        }
    }

    /**
     * 初始化加载框
     */
    private void initProgressDialog(boolean cancel) {
        ///----------  王新亚 修改 ----start----------------
//        Context context = mActivity;
        ///----------  王新亚 修改 ----end----------------


//        if (pd == null && context != null) {
//            pd = new ProgressDialog(context);
//            pd.setCancelable(cancel);
//            if (cancel) {
//                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                    @Override
//                    public void onCancel(DialogInterface dialogInterface) {
//                        onCancelProgress();
//                    }
//                });
//            }
//        }

        ///----------  王新亚 修改 ----start----------------

//        cp = CustomProgress.getInstance(context);
//        if (cancel){
//            cp.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialogInterface) {
//                    onCancelProgress();
//                }
//            });
//        }
        ///----------  王新亚 修改 ----end----------------

    }


    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!isShowPorgress()) return;
//        if (true)return;
        Context context = mActivity;
        if (cp == null || context == null) {
            return;
        }
        if (!cp.isShowing()) {
            cp.show(null);
        }
//        if (pd == null || context == null) return;
//        if (!pd.isShowing()) {
//            pd.show();
//        }
    }


    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!isShowPorgress()) return;
//        if (pd != null && pd.isShowing()) {
//            pd.dismiss();
//        }
        if (cp != null && cp.isShowing()) {
            cp.hideProgress();
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {
        showProgressDialog();
        /*缓存并且有网*/
        if (api.isCache() && AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
             /*获取缓存数据*/
            CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            if (cookieResulte != null) {
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNetWorkTime()) {
                    if (mSubscriberOnNextListener != null) {
                        L.json(cookieResulte.getResulte());
                        mSubscriberOnNextListener.onNext(cookieResulte.getResulte(), api.getMethod());
                    }
                    onCompleted();
                    unsubscribe();
                }
            }
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        /*需要緩存并且本地有缓存才返回*/
        if (api.isCache()) {
            getCache();
        } else {
            errorDo(e);
        }
        dismissProgressDialog();
    }

    /**
     * 获取cache数据
     */
    private void getCache() {
        Observable.just(api.getUrl()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                errorDo(e);
            }

            @Override
            public void onNext(String s) {
                           /*获取缓存数据*/
                CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(s);
                if (cookieResulte == null) {
                    throw new HttpTimeException(HttpTimeException.NO_CHACHE_ERROR);
                }
                long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                if (time < api.getCookieNoNetWorkTime()) {
                    if (mSubscriberOnNextListener != null) {
                        L.json(cookieResulte.getResulte());
                        mSubscriberOnNextListener.onNext(cookieResulte.getResulte(), api.getMethod());
                    }
                } else {
                    CookieDbUtil.getInstance().deleteCookie(cookieResulte);
                    throw new HttpTimeException(HttpTimeException.CHACHE_TIMEOUT_ERROR);
                }
            }
        });
    }

    /**
     * 错误统一处理
     *
     * @param e
     */
    private void errorDo(Throwable e) {
        L.d(e.getMessage());
        Context context = mActivity;
        if (context == null) return;
        HttpOnNextListener httpOnNextListener = mSubscriberOnNextListener;
        if (httpOnNextListener == null) return;
        if (e instanceof ApiException) {
            httpOnNextListener.onError((ApiException) e);
        } else if (e instanceof HttpTimeException) {
            HttpTimeException exception = (HttpTimeException) e;
            httpOnNextListener.onError(new ApiException(exception, CodeException.RUNTIME_ERROR, exception.getMessage()));
        } else {
            httpOnNextListener.onError(new ApiException(e, CodeException.UNKNOWN_ERROR, e.getMessage()));
        }
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        String result = t.toString();
        if (isBase64){
            result = decrypt(t.toString());
        }
        L.d(result);
        L.json(result);
         /*缓存处理*/
        if (api.isCache()) {
            CookieResulte resulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
            long time = System.currentTimeMillis();
            /*保存和更新本地数据*/
            if (resulte == null) {
                resulte = new CookieResulte(api.getUrl(), result, time);
                CookieDbUtil.getInstance().saveCookie(resulte);
            } else {
                resulte.setResulte(result);
                resulte.setTime(time);
                CookieDbUtil.getInstance().updateCookie(resulte);
            }
        }
        if (mSubscriberOnNextListener != null) {
            //是自己服务器的数据,进行预处理
            if (isBase64){
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String error_code = jsonObject.getString("error_code");
                    if (error_code.equals("0")) {
                        mSubscriberOnNextListener.onNext(result, api.getMethod());
                    } else if (error_code.equals("999")) {
                        EventBusUtils.getInstance().postEvent(new LoginMsg());
                    } else {
                        mSubscriberOnNextListener.onError(new ApiException(new Throwable("错误码："+error_code)));
                    }

                    if (jsonObject.get("error_msg") != null && !jsonObject.get("error_msg").equals("")&&(RxRetrofitApp.getApplication()!=null)) {
                        Toast.makeText(RxRetrofitApp.getApplication(), jsonObject.get("error_msg").toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //不是自己服务器的数据,不进行预处理(格式不一样)
            } else {
                mSubscriberOnNextListener.onNext(result, api.getMethod());
            }
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    public boolean isShowPorgress() {
        return showPorgress;
    }

    /**
     * 是否需要弹框设置
     *
     * @param showPorgress
     */
    public void setShowPorgress(boolean showPorgress) {
        this.showPorgress = showPorgress;
    }

    public String decrypt(String data) {
        return new String(Base64.decode(data.getBytes(), Base64.DEFAULT));
    }
}