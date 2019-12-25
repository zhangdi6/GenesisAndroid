package com.iruiyou.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import com.iruiyou.common.RyCommon;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 类描述:app工具类
 * 作者：Created by JiaoPeiRong on 2017/5/7 10:11
 * 邮箱：chinajpr@163.com
 */

public class AppUtils {
    private static TelephonyManager tm = (TelephonyManager) RyCommon.getInstance().getConfiguration().getContext().getSystemService(TELEPHONY_SERVICE);

    /**
     * 获取imei
     *
     * @return
     */
    public static String getIMEI() {
        return tm.getDeviceId();
    }

    /**
     * 获取android 操作系统的版本
     *
     * @return
     */
    public static String getOsVersion() {
        return tm.getDeviceSoftwareVersion();
    }

    /**
     * 获取版本号
     * @return
     */
    public static String getAppVersion(){
        return getPackageInfo(RyCommon.getInstance().getConfiguration().getContext()).versionName;
    }

    /**
     * 获取包信息
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi=null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }
}
