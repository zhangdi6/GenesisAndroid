package com.iruiyou.pet.activity.registerlast;

/**
 * @author： sgf
 * @date： 2018/9/28
 * @describe：监听接口请求返回
 */
public interface ResultDataLinsenter {
    /**
     * 网络请求成功
     * @param o
     */
    void success(Object o);


    /**
     * 网络请求失败
     * @param e
     */
    void filed(Exception e);
}
