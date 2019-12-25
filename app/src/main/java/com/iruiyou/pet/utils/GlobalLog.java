package com.iruiyou.pet.utils;

import android.util.Log;

/**
 *  sgf 输出工具类
 */
public class GlobalLog {

    private static final String TAG = "rf";
    private static boolean showLog = false;

    public static void enableLogging(boolean paramBoolean) {
        showLog = paramBoolean;
    }

    public static void i(String TAG, String paramString) {
        if (showLog)
            Log.i(TAG, paramString == null ? "" : paramString);
    }

    public static void e(String TAG, String paramString) {
        if (showLog)
            Log.e(TAG, "Error : " + paramString);
    }

    public static void e(String TAG, String paramString,
                         Throwable paramThrowable) {
        if (showLog)
            Log.e(TAG, "Error : " + paramString, paramThrowable);
    }

    public static void w(String TAG, String paramString) {
        if (showLog)
            Log.w(TAG,paramString);
    }

    public static void w(String TAG, String paramString,
                         Throwable paramThrowable) {
        if (showLog)
            Log.w(TAG, paramString, paramThrowable);
    }

    public static void d(String TAG, String paramString) {
        if (showLog)
            Log.d(TAG, paramString == null ? "" : paramString);
    }

    public static void v(String TAG, String paramString) {
        if (showLog)
            Log.v(TAG, paramString == null ? "" : paramString);
    }
}