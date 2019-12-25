package com.iruiyou.http.retrofit_rx.Api;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 * Created by WZG on 2016/7/16.
 */
public abstract class BaseApi {
    public static final boolean isDebug=false;

    /*是否能取消加载框*/
    private boolean cancel = false;
    /*是否显示加载框*/
    private boolean showProgress = true;
    /*是否需要缓存处理*/
    private boolean cache = false;

    private static String doumiRequestUrl ="";
//    public static String baseUrlNoApi ="";

    /**
     * 测试版
     */
//        public static String baseUrlNoApi = "http://47.98.204.88/";
  //  public static String baseUrlNoApi = "http://47.95.237.181:8080/";
//    public static String baseUrlNoApi = "http://local2www.free.idcfengye.com/";
//    public static String baseUrlNoApi = "http://192.168.199.191:3002/";
//    public static String baseUrlNoApi = "http://local2www.free.idcfengye.com/";

//    private static String doumiRequestUrl = "https://47.95.237.181:4002";

    /**
     * 正式线上
     */
//    public static String baseUrlNoApi = "http://47.111.100.29/";

    public static String baseUrlNoApi = "https://www.maichangapp.com/";

//    public static String baseUrlNoApi = "https://bit-fun.com/";

//    static {
//        if(isDebug){
//            baseUrlNoApi = "http://47.95.237.181:8080/";
////            baseUrlNoApi = "http://192.168.199.191:3002/";
//        }else{
//            baseUrlNoApi = "https://maichangapp.com/";
//        }
//    }


    /*基础url*/
    public static String baseUrl = baseUrlNoApi + "api/";
//    public static String baseUrl = "https://bit-fun.com/";
//    public static String baseUrl = "http://local2www.free.ngrok.cc/";
    /*方法-如果需要缓存必须设置这个参数；不需要不用設置*/
    private String method = "";
    /*超时时间-默认6秒*/
    private int connectionTime = 30;
    /*有网情况下的本地缓存时间默认60秒*/
    private int cookieNetWorkTime = 60;
    /*无网络的情况下本地缓存时间默认30天*/
    private int cookieNoNetWorkTime = 24 * 60 * 60 * 30;
    /* retry次数*/
    private int retryCount = 0;
    /*retry延迟*/
    private long retryDelay = 100;
    /*retry叠加延迟*/
    private long retryIncreaseDelay = 100;
    /*是否对返回数据做base64解密*/
    private boolean isBase64 = false;

    public String getDoumiRequestUrl(){
        if(isDebug){
            doumiRequestUrl =  "http://47.95.237.181:4002";

        }else{
            doumiRequestUrl =  "http://47.111.100.29:4002";

        }
        return doumiRequestUrl;
    }


    /**
     * 设置参数
     *
     * @param retrofit
     * @return
     */
    public abstract Observable getObservable(Retrofit retrofit);

    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public int getConnectionTime() {
        return connectionTime;
    }

    public void setConnectionTime(int connectionTime) {
        this.connectionTime = connectionTime;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        BaseApi.baseUrl = baseUrl;
    }

    public String getUrl() {
        return baseUrl + getMethod();
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public long getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(long retryDelay) {
        this.retryDelay = retryDelay;
    }

    public long getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(long retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    public boolean isBase64() {
        return isBase64;
    }

    public void setBase64(boolean base64) {
        isBase64 = base64;
    }
}
