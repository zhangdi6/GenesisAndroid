package com.iruiyou.pet;

import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.baijiayun.BJYPlayerSDK;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.iruiyou.common.Configuration;
import com.iruiyou.common.RyCommon;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.db.DBHelper;
import com.iruiyou.pet.rongyun.ApplyMessage;
import com.iruiyou.pet.rongyun.CustomizeMessage;
import com.iruiyou.pet.rongyun.CustomizeMessageItemProvider;
import com.iruiyou.pet.rongyun.NewFollowerMessage;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.RyAppContext;
import com.iruiyou.pet.utils.StringUtil;
import com.tencent.bugly.Bugly;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdk.basic.YouzanBasicSDKAdapter;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

//import com.umeng.analytics.MobclickAgent;
//import com.umeng.commonsdk.UMConfigure;

/**
 * 类描述:
 * 作者：Created by JiaoPeiRong on 2017/4/23 14:01
 * 邮箱：chinajpr@163.com
 */
public class App extends MultiDexApplication implements RongIMClient.OnReceiveMessageListener {
    private static final String TAG = "App";
    private static App instance;
    private static ConfigBean configBean;
   // public static boolean isLogin = false ;
    public static int choice = 0 ;

//    static {//static 代码段可以防止内存泄露
//        //设置全局的Header构建器
//        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
//            @Override
//            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//                return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);//指定为经典Header，默认是 贝塞尔雷达Header
//            }
//        });
//        //设置全局的Footer构建器
//        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
//            @Override
//            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//                //指定为经典Footer，默认是 BallPulseFooter
//                return new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate);
//            }
//        });
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
        DBHelper.getInstance();
//        MultiDex.install(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        //百家云
        new BJYPlayerSDK.Builder(this)
                .setDevelopMode(false)//设置开发者模式
//                .setCustomDomain("demo123")//设置专属域名
                .setEncrypt(true)//设置加密
                .build();
    }

    /**
     * 初始化
     */
    private void init() {
        YouzanSDK.init(this, "f4296b80214b434915", new YouzanBasicSDKAdapter());
        Bugly.init(this, "c602096231", false);
        RyCommon.getInstance().init(this, Configuration.getConfiguration(), null);
        initConfigData();
        //如果AndroidManifest.xml清单配置中没有设置appkey和channel，则可以在这里设置
        //        UMConfigure.init(this, "58edcfeb310c93091c000be2", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
        //umeng设置场景类型
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//        //umeng设置secretkey ：需先进行企业认证，目前没企业认证，不开启
////        MobclickAgent.setSecret(this, "s10bacedtyz");
//        //后台运行40秒后算一个全新的session
//        MobclickAgent.setSessionContinueMillis(1000*40);
//        // 禁止默认的页面统计功能
//        MobclickAgent.openActivityDurationTrack(false);
        /**2 初始化并配置*/
        RongIM.init(this);
        RyAppContext.init(this);
        RongIM.registerMessageType(CustomizeMessage.class);
        RongIM.registerMessageType(ApplyMessage.class);
        //NewFollowerMessage
        RongIM.registerMessageType(NewFollowerMessage.class);
        PlatformConfig.setWeixin(Constant.WEIXIN_APP_ID, Constant.WEIXIN_APP_SEC);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5d5b5e064ca357e19c000af0", "", UMConfigure.DEVICE_TYPE_PHONE, "");
        RongIM.registerMessageTemplate(new CustomizeMessageItemProvider());

        //强烈建议在应用对应的Application#onCreate()方法中调用，避免出现content为null的异常
        TTAdSdk.init(this,
                new TTAdConfig.Builder()
                        .appId("5028392")
                        .useTextureView(true) //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
                        .appName("脉场")
                        .titleBarTheme(TTAdConstant.TITLE_BAR_THEME_DARK)
                        .allowShowNotify(true) //是否允许sdk展示通知栏提示
                        .allowShowPageWhenScreenLock(true) //是否在锁屏场景支持展示广告落地页
                        .debug(true) //测试阶段打开，可以通过日志排查问题，上线时去除该调用
                        .directDownloadNetworkType(TTAdConstant.NETWORK_STATE_WIFI, TTAdConstant.NETWORK_STATE_3G) //允许直接下载的网络状态集合
                        .supportMultiProcess(false) //是否支持多进程，true支持
                        //.httpStack(new MyOkStack3())//自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
                        .build());

    }

    public static App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {//解决Youzan错误
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public boolean onReceived(Message message, int i) {

        return false;
    }

    private static void initConfigData() {
        String configStr = SharePreferenceUtils.getBaseSharePreference().getConfigData();
        if (StringUtil.isNotEmpty(configStr)) {
            configBean = GsonUtil.GsonToBean(configStr, ConfigBean.class);
        }
    }

    public static void setConfigBean(ConfigBean configBean) {
        App.configBean = configBean;
    }

    public static ConfigBean getConfigBean() {
        if (configBean == null) {
            initConfigData();
        }
        return configBean;
    }


}
