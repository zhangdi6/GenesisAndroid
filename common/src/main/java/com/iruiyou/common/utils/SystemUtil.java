package com.iruiyou.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.iruiyou.common.RyCommon;
import com.ta.utdid2.android.utils.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * 获取手机系统信息 添加权限<uses-permission
 * android:name="android.permission.READ_PHONE_STATE" />
 */
public class SystemUtil {

    /**
     * 获取 app版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前应用开发的版本号
     */

    public static int getVersionCode(Context context) {

        try {

            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0).versionCode;

        } catch (NameNotFoundException e) {

            e.printStackTrace();

        }
        return 0;

    }

    /**
     * 获取手机品牌
     *
     * @return
     */
    public static String getPhoneBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取系统版版号
     *
     * @return
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }


    /**
     * 启动默认浏览器打开连接
     *
     * @param context
     * @param url
     */
    public static void openBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * 获取程序的名字
     *
     * @param context
     * @return
     */
    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                    .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                    context.getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        String applicationName = (String) packageManager
                .getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取imei
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return Objects.requireNonNull(telephonyManager).getDeviceId();

    }

    /**
     * 获取手机ip地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        Pattern p = Pattern
                                .compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
                        Matcher m = p.matcher(inetAddress.getHostAddress());
                        if (m.matches())
                            return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "";
    }


    /**
     * .获取手机MAC地址 只有手机开启wifi才能获取到mac地址 <!-- 获取mac地址权限 --> <uses-permission
     * android:name="android.permission.ACCESS_WIFI_STATE" />
     */
    public static String getMacAddress(Context context) {
        String result = "";
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = Objects.requireNonNull(wifiManager).getConnectionInfo();
        result = wifiInfo.getMacAddress();
        return result;
    }

    /**
     * 获取mac地址的新方法
     * @return
     * @throws SocketException
     */
    public static String macAddress() throws SocketException {
        String address = null;
        // 把当前机器上的访问网络接口的存入 Enumeration集合中
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface netWork = interfaces.nextElement();
            // 如果存在硬件地址并可以使用给定的当前权限访问，则返回该硬件地址（通常是 MAC）。
            byte[] by = netWork.getHardwareAddress();
            if (by == null || by.length == 0) {
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (byte b : by) {
                builder.append(String.format("%02X:", b));
            }
            if (builder.length() > 0) {
                builder.deleteCharAt(builder.length() - 1);
            }
            String mac = builder.toString();
            Log.d("mac", "interfaceName="+netWork.getName()+", mac="+mac);
            // 从路由器上在线设备的MAC地址列表，可以印证设备Wifi的 name 是 wlan0
            if (netWork.getName().equals("wlan0")) {
                Log.d("mac", " interfaceName ="+netWork.getName()+", mac="+mac);
                address = mac;
            }
        }
        return address;
    }

    /**
     * 判断当前activity是否在前台运行
     *
     * @return
     */
    public static boolean isRunningForeground(Context context) {
        String packageName = getPackageName(context);
        String topActivityClassName = getTopActivityName(context);
        return packageName != null && topActivityClassName != null
                && topActivityClassName.startsWith(packageName);
    }

    /**
     * 获取当前显示activity名字
     */
    public static String getTopActivityName(Context context) {
        String topActivityClassName = null;
        ActivityManager activityManager = (ActivityManager) (context
                .getSystemService(Context.ACTIVITY_SERVICE));
        List<RunningTaskInfo> runningTaskInfos = Objects.requireNonNull(activityManager)
                .getRunningTasks(1);
        if (runningTaskInfos != null) {
            ComponentName f = runningTaskInfos.get(0).topActivity;
            topActivityClassName = f.getClassName();
        }
        return topActivityClassName;
    }

    /**
     * 获取当前包名
     *
     * @param context
     * @return
     */
    public static String getPackageName(Context context) {
        String packageName = context.getPackageName();
        return packageName;
    }

    /**
     * 判断App是否在前台运行
     *
     * @param context
     * @return
     */
    public static boolean isAppRunningForeground(Context context) {
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = Objects.requireNonNull(am).getRunningTasks(1).get(0).topActivity;
        String currentPackageName = cn.getPackageName();
        return !TextUtils.isEmpty(currentPackageName)
                && currentPackageName.equals(getPackageName(context));

    }

    /**
     * 拨打电话
     *
     * @param context
     * @param phoneNum
     */
    public static void callPhone(Context context, String phoneNum) {
        if(!StringUtils.isEmpty(phoneNum)&&(context!=null))
        {
            phoneNum = phoneNum.trim();// 删除字符串首部和尾部的空格
            if(!StringUtils.isEmpty(phoneNum))
            {
                // 调用系统的拨号服务实现电话拨打功能
                // 封装一个拨打电话的intent，并且将电话号码包装成一个Uri对象传入
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
                        + phoneNum));
                context.startActivity(intent);// 内部类
            }
        }
    }

    /**
     * 拨打电话
     *
     * @param context
     * @param tels    已“，”分割的tel
     */
    public static void callPhones(final Context context, String tels) {
        if (tels != null && !tels.equals("")) {
            tels = tels.replace("'", "");
            final String[] ts = tels.split(",");
            if (ts.length > 1) {
                Dialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("请选择：")
                        .setItems(ts, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                callPhone(context, ts[which]);
                            }
                        }).setNegativeButton("取消", null).create();
                alertDialog.show();
            } else {
                callPhone(context, tels);
            }
        }

    }

    /**
     * 发送短信
     */
    public static void sendSMS(Context context, String tel, String text) {
        if (tel != null && !tel.equals("")) {
            Uri uri = Uri.parse("smsto:" + tel);
            Intent it = new Intent(Intent.ACTION_SENDTO, uri);
            it.putExtra("sms_body", text);
            context.startActivity(it);
        }
    }

    /**
     * 隐藏输入法
     *
     * @param activity
     */
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.
                SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                Objects.requireNonNull(inputMethodManager).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 退出程序
     */
    public static void exitApp() {
//        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 判断是否安装某个app
     *
     * @param context
     * @param packagename
     * @return true--安装,false--未安装
     */
    public static boolean isAppInstalled(Context context, String packagename) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
            e.printStackTrace();
        }
        return packageInfo != null;
    }


    /*******************后期添加*******************/
    private static TelephonyManager tm = (TelephonyManager) RyCommon.getInstance().getConfiguration().getContext().getSystemService(TELEPHONY_SERVICE);

    /**
     * 获取imei
     *
     * @return
     */
    public static String getIMEI() {
        TelephonyManager manager = (TelephonyManager) RyCommon.getInstance().getConfiguration().getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String imeiCode =null;
        try {
            imeiCode="IMEI"+ Objects.requireNonNull(manager).getDeviceId();
        }catch(Exception e){
//            imeiCode="mac" + getMacAddress(RyCommon.getInstance().getConfiguration().getContext());
            try {
                imeiCode = "mac" + macAddress();
            } catch (SocketException e1) {
                e1.printStackTrace();
            }
        }
        return imeiCode;
//        return tm.getDeviceId();
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
        PackageInfo pi;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
        } catch (Exception e) {
            e.printStackTrace();
            pi=null;
        }
        return pi;
    }



}
