package com.iruiyou.common.utils;

import android.widget.Toast;

import com.iruiyou.common.RyCommon;

/**
 * Toast统一管理类
 */
public class T {
    private static Toast toast;

    /**
     * 短时间显示Toast
     */
    public static void showShort(CharSequence message) {
        if (message.equals("无效的token")){

        }else{
            show(message,Toast.LENGTH_SHORT);
        }

    }

    /**
     * 长时间显示Toast
     */
    public static void showLong(CharSequence message) {
        show(message,Toast.LENGTH_LONG);
    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(CharSequence message, int duration) {
        if ((RyCommon.getInstance()!=null)&&(RyCommon.getInstance().getConfiguration()!=null)&&(RyCommon.getInstance().getConfiguration().getContext()!=null)) {
            toast = Toast.makeText(RyCommon.getInstance().getConfiguration().getContext(), message, duration);
        }
        if(toast!=null){
            toast.show();
        }

    }

    /**
     * 自定义显示Toast时间
     */
    public static void show(int message, int duration) {
        if ((RyCommon.getInstance()!=null)&&(RyCommon.getInstance().getConfiguration()!=null)&&(RyCommon.getInstance().getConfiguration().getContext()!=null)) {
            toast = Toast.makeText(RyCommon.getInstance().getConfiguration().getContext(), message, duration);
        }
        if(toast!=null){
            toast.show();
        }
    }

}
