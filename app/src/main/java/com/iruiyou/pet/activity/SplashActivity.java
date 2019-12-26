package com.iruiyou.pet.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.view.ContextThemeWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.utils.SystemBarTintManager;
import com.iruiyou.pet.utils.UpdateManagers;
import com.iruiyou.pet.utils.permission.PermissionHelper;
import com.iruiyou.pet.utils.permission.PermissionInterface;

import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;


/**
 * 开屏页
 *
 * @author NIT
 */
public class SplashActivity extends BaseActivity implements PermissionInterface {

    @BindView(R.id.rl_ad)
    RelativeLayout rlAd;
    @BindView(R.id.iv_ad)
    ImageView ivAd;

    @BindView(R.id.mai_frameLayout)
    FrameLayout linear_start_content;

    private ImageView iv;
    private LinearLayout ll;
    private long sleepTime = 1500;
    private View statusBarView;
    private AlertDialog dialog;
    private PermissionHelper mPermissionHelper;
    private boolean permisstionSuccess=false;
    private boolean isAdCompelete=false;
    private TTAdNative mTTAdNative;
    private AdSlot adSlot;
    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        //设置状态栏颜;
//        getWindow().getDecorView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                initStatusBar();
//                getWindow().getDecorView().removeOnLayoutChangeListener(this);
//            }  });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.drawable.shape_statusbar);//状态栏所需颜色,这里可以设置渐变色
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M)
            {
                //初始化并发起权限申请
                mPermissionHelper = new PermissionHelper(this, this);
                mPermissionHelper.requestPermissions();
            }
            else
            {
                permisstionSuccess=true;
            }
        }
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if ("zh".equals(language) || "zh_CN".equals(language) || "zh_TW".equals(language)) {
            SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.ZH);
        } else {
            SharePreferenceUtils.getBaseSharePreference().saveLanguage(TagConstants.EN);
        }
        getData();

        int min = 1;
        int max = 2;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        if (num == 1) {
            ivAd.setImageResource(R.drawable.ad_img_1);
        } else if (num == 2) {
            ivAd.setImageResource(R.drawable.ad_img_2);
        }
        rlAd.setVisibility(View.GONE);


       /* FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mai_frameLayout,new MaiFragment());
        transaction.commit();*/
        adSlot = new AdSlot.Builder()
                // 必选参数 设置您的CodeId
                .setCodeId("828392468")
                // 必选参数 设置广告图片的最大尺寸及期望的图片宽高比，单位Px
                // 注：如果您在头条广告平台选择了原生广告，返回的图片尺寸可能会与您期望的尺寸有较大差异
                .setImageAcceptedSize(DensityUtil.getScreenWidth(), DensityUtil.getScreenHeight())
                // 可选参数 设置是否支持deeplink
                .setSupportDeepLink(true)
                // 可选参数，针对信息流广告设置每次请求的广告返回个数，最多支持3个
                .setAdCount(3)
                //设置期望视频播放的方向，为TTAdConstant.HORIZONTAL或TTAdConstant.VERTICAL
                .setOrientation(TTAdConstant.VERTICAL)
                //激励视频奖励透传参数，字符串，如果用json对象，必须使用序列化为String类型,可为空
                .setMediaExtra("")
                .build();

        mTTAdNative = TTAdSdk.getAdManager().createAdNative(SplashActivity.this);//baseContext建议为activity

    }


    private void loadAd(){
        mTTAdNative.loadSplashAd(adSlot, new TTAdNative.SplashAdListener(){

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onTimeout() {

            }

            @Override
            public void onSplashAdLoad(TTSplashAd ad) {
                if (ad == null) {
                    return;
                }
                //获取SplashView
                View view = ad.getSplashView();
                linear_start_content.removeAllViews();
                linear_start_content.addView(view);
                //设置SplashView的交互监听器
                ad.setSplashInteractionListener(new TTSplashAd.AdInteractionListener() {
                    @Override
                    public void onAdClicked(View view, int type) {
//                        Log.d(TAG, "onAdClicked");
//                        showToast("开屏广告点击");
                    }

                    @Override
                    public void onAdShow(View view, int type) {
//                        Log.d(TAG, "onAdShow");
//                        showToast("开屏广告展示");
                    }

                    @Override
                    public void onAdSkip() {
//                        Log.d(TAG, "onAdSkip");
//                        showToast("开屏广告跳过");
                        isAdCompelete = true;
                        if ((mPermissionHelper == null) || (permisstionSuccess)) {
                            goActivity();
                        }
                    }

                    @Override
                    public void onAdTimeOver() {
//                        Log.d(TAG, "onAdTimeOver");
//                        showToast("开屏广告倒计时结束");
                        isAdCompelete = true;
                        if ((mPermissionHelper == null) || (permisstionSuccess)) {
                            goActivity();
                        }
                    }
                });
            }
        });
    }

//    private void initStatusBar() {
//        if (statusBarView == null) {
//            //利用反射机制修改状态栏背景
//            int identifier = getResources().getIdentifier("statusBarBackground", "id", "android");
//            statusBarView = getWindow().findViewById(identifier);
//        }
//        if (statusBarView != null) {
//            statusBarView.setBackgroundResource(R.drawable.shape_statusbar);
//        }
//    }

    @Override
    public void OnViewClick(View v) {

    }

    @Override
    protected void onStart() {
        super.onStart();
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                long startTime = System.currentTimeMillis();
//                long custTime = System.currentTimeMillis() - startTime;
//
//                if (sleepTime - custTime > 0) {
//                    try {
//                        Thread.sleep(sleepTime);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

    /*
     * 沉浸式布局
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 获取数据
     */
    private void getData() {
        final long startTime = System.currentTimeMillis();
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                String errorMessage = "数据初始化错误，请稍后再试！";
                boolean isSuccess = false;
                if (StringUtil.isNotEmpty(resulte)) {
                    ConfigBean configBean = GsonUtils.parseJson(resulte, ConfigBean.class);
                    if (configBean != null) {
                        if ((Constant.SUCCESS == configBean.getStatusCode())) {
                            isSuccess = true;
                            if(configBean.getData()!=null&&(configBean.getData().getUpdate()!=null&&(configBean.getData().getUpdate().isOption()||
                                    configBean.getData().getUpdate().isRequire()))&&(StringUtil.isNotEmpty(configBean.getData().getUpdatePathAndroid()))&&(configBean.getData().getUpdate().getFileSize()>0)){
                                //版本更新
                                updateVersion(configBean,configBean.getData().getUpdatePathAndroid(),resulte);
                            }else{
                                long custTime = System.currentTimeMillis() - startTime;
                                SharePreferenceUtils.getBaseSharePreference().saveConfigData(resulte);
                                App.setConfigBean(configBean);
                                loadAd();
//                                if (custTime - startTime < sleepTime) {
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            isAdCompelete=true;
//                                            if((mPermissionHelper==null)||(permisstionSuccess))
//                                            {
//                                               goActivity();
//                                            }
//                                        }
//                                    }, 2000);
//                                } else {
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            isAdCompelete=true;
//                                            if((mPermissionHelper==null)||(permisstionSuccess))
//                                            {
//                                               goActivity();
//                                            }
//                                        }
//                                    }, 2000);
//                                }
                            }
                        } else {
                            if (StringUtil.isNotEmpty(configBean.getMessage())) {
                                errorMessage = configBean.getMessage();
                            }
                        }
                    }

                }
                if (!isSuccess) {
                    /*T.showShort(errorMessage);*/
                }

            }

            @Override
            public void onError(ApiException e) {

                /*T.showShort(e.getMessage());*/

            }
        }, this).config();
    }

    /**
     * 版本更新
     */
    private void updateVersion(ConfigBean configBean, String url, String result) {
        dialog = new AlertDialog.Builder(new ContextThemeWrapper(SplashActivity.this, R.style.UpdateDialogStyle)).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(LayoutInflater.from(SplashActivity.this).inflate(R.layout.updatedialog, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.updatedialog);

        Button btn_update_confirm = dialog.findViewById(R.id.btn_update_confirm);
        Button btn_update_cancel = dialog.findViewById(R.id.btn_update_cancel);
        Button btn_update_ignore = dialog.findViewById(R.id.btn_update_ignore);

        btn_update_ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if(configBean.getData().getUpdate().isRequire()){
                    Toast.makeText(SplashActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                } else if(configBean.getData().getUpdate().isOption()){
                    if(StringUtil.isNotEmpty(result)){
                        SharePreferenceUtils.getBaseSharePreference().saveConfigData(result);
                        App.setConfigBean(configBean);
                        loadAd();
                    }
                }
            }
        });

        //取消弹话框
        btn_update_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if(configBean.getData().getUpdate().isRequire()){
                    Toast.makeText(SplashActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                } else if(configBean.getData().getUpdate().isOption()){
                    if(StringUtil.isNotEmpty(result)){
                        SharePreferenceUtils.getBaseSharePreference().saveConfigData(result);
                        App.setConfigBean(configBean);
                        loadAd();
                    }
                }
            }
        });


        //确定弹话框
        btn_update_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                new UpdateManagers(SplashActivity.this,configBean.getData().getUpdate().getFileSize(),configBean.getData().getUpdate().isRequire()).checkUpdate("ww", "", url, "", "");
            }
        });

        if(configBean.getData().getUpdate()!=null){
            if(configBean.getData().getUpdate().isRequire()){
                btn_update_ignore.setVisibility(View.GONE);
                btn_update_cancel.setVisibility(View.VISIBLE);
                dialog.setCancelable(false);
            }else if(configBean.getData().getUpdate().isOption()){
                btn_update_ignore.setVisibility(View.VISIBLE);
                btn_update_cancel.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 跳转
     */
    private void goActivity() {
        if (SharePreferenceUtils.getBaseSharePreference().readIsShowWelcome().equals("1")) {
            startActivity(LoginOrRegisterActivity.class);
            SharePreferenceUtils.getBaseSharePreference().saveIsShowWelcome("-1");
            finish();
        } else {
            if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                Log.i("cvb", "goActivity: "+ SharePreferenceUtils.getBaseSharePreference().readlogin());
            // 非第一次进入程序，跳转主页面
           /* if (!SharePreferenceUtils.getBaseSharePreference().readAccount().equals("")
                    && !SharePreferenceUtils.getBaseSharePreference().readPassword().equals("")) {
//                ConfigBean configBean = App.getConfigBean();
//                if ((configBean!=null)&&(configBean.getData()!=null)&&(SharePreferenceUtils.getBaseSharePreference().readShowEdit() < configBean.getData().getMinShowEdit())) {
//                    StartActivityManager.startRegisterLastActivity2(SplashActivity.this, "", "", "", "", "");
//                } else {
//                    startActivity(MainActivity.class);
//                }

                //ACache aCache = ACache.get(this);
                //LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
                String phone= SharePreferenceUtils.getBaseSharePreference().readAccount();
                String passwordValue = SharePreferenceUtils.getBaseSharePreference().readPassword();

                UserTask userTask = new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
//                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
//                        T.showShort(loginBean.getMessage());
//                        if (loginBean.getStatusCode() == Constant.SUCCESS) {
//                            SharePreferenceUtils.getBaseSharePreference().saveInviteCode(loginBean.getData().getInviteCode());
//                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginBean.getData().getRealName());
////                            aCache.put(TagConstants.LoginTag, loginBean);
////                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
//                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginBean.getData().get_id());
//                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginBean.getToken());
//                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
//                            SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginBean.getData().getCountryCode());
//                            startActivity(MainActivity.class);
//                            finish();
//                        }else if(loginBean.getStatusCode() == Constant.TIPS1){
//
//                        }
                        LoginNewBean loginNewBean = GsonUtils.parseJson(resulte, LoginNewBean.class);
                        ACache aCache = ACache.get(SplashActivity.this);
                        if (loginNewBean.getStatusCode() == Constant.SUCCESS) {
                            aCache.put(TagConstants.loginfig, loginNewBean);

                            //单点登录，互踢下线
                            RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
                                @Override
                                public void onChanged(ConnectionStatus connectionStatus) {
                                    if (connectionStatus == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
                                       // App.isLogin=false;
                                        SharePreferenceUtils.getBaseSharePreference().saveState(false);
//                            EventBus.getDefault().post(new ExitEvent()); // 利用eventbus结束掉所有界面
                                        AppManager.getAppManager().finishOtherActivity(BaseActivity.class);
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                T.showShort("Your account is logged elsewhere, and you are forced to go offline");
                                            }
                                        });
                                        startActivity(new Intent(getApplicationContext(), MCLoginActivity.class)); //重新回到登录界面

                                    }
                                }


                            });


                            SharePreferenceUtils.getBaseSharePreference().saveInvitedCode(loginNewBean.getData().getUserInfo().getInvitedCode());
                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone);
                            SharePreferenceUtils.getBaseSharePreference().savePassword(passwordValue);
                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginNewBean.getData().getBasicInfo().getRealName());
//                            aCache.put(TagConstants.LoginTag, loginBean);
//                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
                            SharePreferenceUtils.getBaseSharePreference().saveBasicId(loginNewBean.getData().getBasicInfo().get_id());//用户资料id
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginNewBean.getData().getUserInfo().get_id());
                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginNewBean.getToken());
                            SharePreferenceUtils.getBaseSharePreference().saveIMToken(loginNewBean.getRcToken());
                            SharePreferenceUtils.getBaseSharePreference().saveUserChannel(loginNewBean.getData().getUserInfo().getUserChannel());
                            SharePreferenceUtils.getBaseSharePreference().saveShowEdit(loginNewBean.getData().getBasicInfo().getShowEdit());//用户id


//                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
                            getDataHeadName(loginNewBean.getData().getBasicInfo().getRealName(), BaseApi.baseUrlNoApi + loginNewBean.getData().getUserInfo().getHeadImg());
                            SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginNewBean.getData().getUserInfo().getCountryCode());

                            LoginNewBean.DataBean.BasicInfoBean basicInfoBean = loginNewBean.getData().getBasicInfo();
                            if(StringUtil.isNotEmpty(basicInfoBean.getRealName())&&StringUtil.isNotEmpty(basicInfoBean.getHeadImg())&&(basicInfoBean.getProfessionalIdentity()>0)){
                                startActivity(MainActivity.class);
                                hideInputMethod();
                                finish();
                              //  App.isLogin=true;
                                SharePreferenceUtils.getBaseSharePreference().saveState(true);
                            }else {
                                startActivity(MainActivity.class);
                                hideInputMethod();
                                finish();
                               // App.isLogin=true;
                                SharePreferenceUtils.getBaseSharePreference().saveState(true);
//                                StartActivityManager.startRegisterLastActivity2(context, "", "", "", "", "");
//                                finish();
                            }
                            //  >=进首页
//                            if ((configBean!=null)&&(configBean.getData()!=null)&&loginNewBean.getData().getBasicInfo().getShowEdit() < configBean.getData().getMinShowEdit()) {
//                                StartActivityManager.startRegisterLastActivity2(context, "", "", "", "", "");
//                                finish();
//                            } else {
//                                startActivity(MainActivity.class);
//                                finish();
//                            }
                        }
                        else if(!TextUtils.isEmpty(loginNewBean.getMessage()))
                        {
                             T.showShort(loginNewBean.getMessage());
                        }
                        DialogUtil.getInstance().closeLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException e) {
                        *//* T.showShort(e.getMessage());*//*
                        DialogUtil.getInstance().closeLoadingDialog();
                    }
//                }, this).login(phone.getText().toString(), password.getText().toString(), mCountry[0]);
                }, this);//.loginWithPassword(phone.getText().toString(), password.getText().toString(), mCountry[0]);
                if(StringUtil.isNotEmpty(passwordValue)){
                    if(passwordValue.equals("zxcvbnm")&&BaseApi.isDebug){
                        userTask.loginWithOutPassword(phone,passwordValue,"86");
                    } else {
                        Log.e("chuchu",phone);
                        Log.e("chuchu",passwordValue);
                        userTask.loginWithPassword(phone,passwordValue,"86");
                       // App.isLogin=true;
                        SharePreferenceUtils.getBaseSharePreference().saveState(true);
                    }
                }
*/
              } else {
                startActivity(LoginOrRegisterActivity.class);
            }


        }

    }

    /**
     * 设置融云回调，设置用户头像昵称信息
     *
     * @param name
     * @param pic
     */
    private void getDataHeadName(String name, String pic) {
        String imToken = SharePreferenceUtils.getBaseSharePreference().readIMToken();
//        String  a = BaseApi.baseUrlNoApi ;//+ checkFriendsBean.getData().get(0).getBasicInfoB().getHeadImg();
        RongIM.connect(imToken, new RongIMClient.ConnectCallback() {//设置融云回调，设置用户头像昵称信息
            @Override
            public void onTokenIncorrect() {
               // Log.d(TAG, "###onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
               // Log.d(TAG, "###onSuccess---s" + s);
//                T.showShort("Success");
                //设置信息和uerid 匹配
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(s, name, Uri.parse(pic)));//设置到头像昵称到融云上
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(s, name, Uri.parse(pic)));//刷新同步头像昵称到融云
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
              //  Log.d(TAG, "###onError--errorCode=" + errorCode);
//                T.showShort("onError");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults))
        {
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public int getPermissionsRequestCode() {
        return 0;
    }

    @Override
    public String[] getPermissions() {
        return new String[]{Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.CAMERA,Manifest.permission.READ_CONTACTS};
    }


    @Override
    public void requestPermissionsSuccess() {
        permisstionSuccess=true;
        if(isAdCompelete)
        {
            goActivity();
        }
    }

    @Override
    public void requestPermissionsFail() {
        permisstionSuccess=false;
    }
}
