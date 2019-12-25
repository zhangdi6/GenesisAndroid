package com.iruiyou.http.retrofit_rx.utils;

import android.widget.Toast;

import com.iruiyou.http.retrofit_rx.RxRetrofitApp;


/**
 * Toast统一管理类
 */
public class T {
    private static Toast toast;

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        if(null==toast&&RxRetrofitApp.getApplication()!=null)
        {
            toast = Toast.makeText(RxRetrofitApp.getApplication(), message, Toast.LENGTH_SHORT);
        }
        else if(toast!=null)
        {
            toast.setText(message);
        }
        if(toast!=null)
        {
            toast.show();
        }
    }

}
