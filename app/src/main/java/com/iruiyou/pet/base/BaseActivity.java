package com.iruiyou.pet.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.iruiyou.common.R;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.T;
import com.iruiyou.pet.activity.MCLoginActivity;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
//import com.umeng.analytics.MobclickAgent;
//import com.umeng.commonsdk.UMConfigure;

//import com.gyf.barlibrary.ImmersionBar;

/**
 * Activity的基类
 *
 * @author jiao 2015.12.2
 */
public abstract class BaseActivity extends RxAppCompatActivity implements
        OnClickListener {

    private boolean titleLoaded = false; // 标题是否加载成功
    protected View titleLeftView;// 左控件
//    private View titleView;// 整体控件
    private TextView tv_title;// 标题
    private TextView tvTitleRight;// 右标题
    private TextView titleRightText2;
    private ImageView ivTitleRight;// 右图片
    protected ImageView imageLeft;
    private ImmersionBar mImmersionBar;
    private LinearLayout llTvBg;//右侧标题背景
    protected TextView mUnreadNumView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        AppManager.getAppManager().addActivity(this);

//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE,"");
        //umeng设置场景类型
//        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
//        //umeng设置secretkey ：需先进行企业认证，目前没企业认证，不开启
////        MobclickAgent.setSecret(this, "s10bacedtyz");
//        //后台运行40秒后算一个全新的session
//        MobclickAgent.setSessionContinueMillis(1000*40);
//        // 禁止默认的页面统计功能
//        MobclickAgent.openActivityDurationTrack(false);
        //隐藏ActionBar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //去除状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //单点登录，互踢下线
        RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
            @Override
            public void onChanged(ConnectionStatus connectionStatus) {
                if (connectionStatus == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
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

        if (getLayout() != 0) {
            setContentView(getLayout());
        }
//        translucentStatusBar();
//        initStatusBar();
        loadTitle();

        mImmersionBar = ImmersionBar.with(this).fitsSystemWindows(true).statusBarDarkFont(true).statusBarColor(R.color.white) ;
        mImmersionBar.init();
        OnActCreate(savedInstanceState);

    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        //状态栏一体化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }

    /************************* 重写方法区 *****************************/

    /**
     * 返回本界面的布局文件
     *
     * @return
     */
    public abstract int getLayout();

    /**
     * 子类OnCreate方法
     *
     * @param savedInstanceState
     */
    public abstract void OnActCreate(Bundle savedInstanceState);

    /**
     * 控件的点击事件
     *
     * @param v
     */
    public void OnViewClick(View v) {
    }

    /*********************** 父类方法区 ******************************/

    /**
     * 加载标题
     */
    private void loadTitle() {
        titleLeftView = findViewById(R.id.ll_title_left_view);
//        titleView = findViewById(R.id.titleview);
        titleRightText2=findViewById(R.id.title_right_text2);
        tv_title = findViewById(R.id.title_name_text);
        tvTitleRight = findViewById(R.id.title_right_text);
        ivTitleRight = findViewById(R.id.title_right_img);
        imageLeft=findViewById(R.id.image_left);
        llTvBg = findViewById(R.id.llTvBg);
        if(titleLeftView!=null)
        {
            titleLoaded = true;
            setLeftFinish();
        }
    }

    public void setTitleRightText2OnClick(View.OnClickListener onClick){
        if(titleLoaded&&onClick!=null){
            titleRightText2.setOnClickListener(onClick);
        }
    }

    public void setTitleRightText2Visiable(int visiable){
        if(titleLoaded){
            titleRightText2.setVisibility(visiable);
        }
    }

    public void setTitleRightText2(String value){
        if(titleLoaded){
            titleRightText2.setText(value);
        }
    }

    public void setTitleRightText2Color(int colorResource){
        if(titleLoaded){
            titleRightText2.setTextColor(colorResource);
        }
    }

    /**
     * 隐藏输入法
     */
    public void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if ((imm!=null)&&imm.isActive() && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onClick(View v) {
        // 过滤要处理的控件
//        switch (v.getId()) {
//            case R.id.ll_title_left_view:
//                // 返回按钮
//                finish();
//                break;
//
//            default:
//                break;
//        }
        if (v.getId() == R.id.ll_title_left_view) {
            finish();
        }
        OnViewClick(v);
    }

    /********************** 公共方法区 *******************************/

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (titleLoaded) {
            tv_title.setText(title);
        }
    }

    /**
     * 隐藏标题
     */
    public void hideTitle() {
//        if (titleLoaded) {
//            titleView.setVisibility(View.GONE);
//        }
    }

    public void setrightTextBg(int resourceId){

    }

    public void setLeftImage(Drawable drawable){
        if (titleLoaded) {
            imageLeft.setImageDrawable(drawable);
        }
    }

    /**
     * 设置右图片
     *
     * @param resId
     */
    public void setRightImage(int resId) {
        if (titleLoaded) {
            ivTitleRight.setBackgroundResource(resId);
        }
    }

    public void setRightImageVisiable(int visiable){
        ivTitleRight.setVisibility(visiable);
    }

    /**
     * 右图片的点击事件
     *
     * @param onClickListener
     */
    public void setRightImageClick(OnClickListener onClickListener) {
        if (titleLoaded) {
            ivTitleRight.setOnClickListener(onClickListener);
        }
    }

    /**
     * 隐藏右文字
     */
    public void hideRight() {
        if (titleLoaded) {
            tvTitleRight.setVisibility(View.GONE);
        }
    }
    /**
     * 隐藏右背景
     */
    public void hideRightBg() {
        if (titleLoaded) {
            llTvBg.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右文字监听
     *
     * @param listener
     */
    public void setRightViewListener(OnClickListener listener) {
        if (titleLoaded) {
            tvTitleRight.setOnClickListener(listener);
        }
    }

    /**
     * 显示右文本
     */
    public void showRightText() {
        if (titleLoaded) {
            tvTitleRight.setVisibility(View.VISIBLE);
        }
    }
    /**
     * 显示右背景
     */
    public void showRightBg() {
        if (titleLoaded) {
            llTvBg.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右文本
     *
     * @param text
     */
    public void setRightText(String text, String color) {
        if (titleLoaded) {
            showRightText();
            tvTitleRight.setText(text);
            if (color != null && !color.equals("")) {

                tvTitleRight.setTextColor(Color.parseColor(color));
            }
        }
    }
    /**
     * 设置右背景颜色
     *
     * @param bg
     */
    public void setRightBg(int bg) {
        if (titleLoaded) {
            showRightBg();
            //設置佈局背景色方法一
            Drawable drawable = getResources().getDrawable(bg);
            llTvBg.setBackgroundDrawable(drawable);
        }
    }

    public void setRightText(String text) {
        if (titleLoaded) {
            showRightText();
            tvTitleRight.setText(text);

        }
    }

    /**
     * 隐藏左控件
     */
    public void hideLeft() {
        if (titleLoaded) {
            titleLeftView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置左控件的点击事件
     */
    public void setLeftClickListener(OnClickListener listener) {
        if (titleLoaded) {
            titleLeftView.setOnClickListener(listener);
        }
    }

    public void setLeftFinish() {
        if (titleLoaded) {
            titleLeftView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    /**
     * 给控件设置监听
     *
     * @param resId
     * @param listener
     */
    public View setViewClick(int resId, OnClickListener listener) {
        View view = findViewById(resId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return view;
    }

    /**
     * 给控件设置监听
     *
     * @param resId
     * @param
     */
    public View setViewClick(int resId) {
        return setViewClick(resId, this);
    }

    /**
     * 跳转一个界面不传递数据
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent();
        intent.setClass(this, clazz);
        startActivity(intent);
    }

    /**
     * 沉浸式状态栏
     */
    private void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
