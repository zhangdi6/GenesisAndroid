package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.AppManager;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.utils.AnimationUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:选择注册登录页面
 * 创建日期:2018/8/27 on 16:11
 * 作者:JiaoPeiRong
 */
public class LoginOrRegisterActivity extends BaseActivity {
    @BindView(R.id.nameChina)
    TextView nameChina;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.loginBtn)
    TextView loginBtn;
    @BindView(R.id.registerBtn)
    TextView registerBtn;
    @BindView(R.id.imCircular)
    ImageView imCircular;
    @BindView(R.id.imNecktie)
    ImageView imNecktie;
    private ConfigBean configBean;

    @Override
    public int getLayout() {
        return R.layout.activity_login_register;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().finishOtherActivity(LoginOrRegisterActivity.class);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
//        imCircular.startAnimation(AnimationUtils.setScaleAnimation());
        //获取缓存的数据-职业
//        configBean = (ConfigBean) ACache.get(this).getAsObject(TagConstants.config);
        configBean= App.getConfigBean();
//        int readShowEdit = SharePreferenceUtils.getBaseSharePreference().readShowEdit();
//        if((configBean!=null)&&(configBean.getData()!=null)&&readShowEdit>=configBean.getData().getMinShowEdit())

        loginBtn.setVisibility(View.GONE);
        registerBtn.setVisibility(View.GONE);
       /* String uid = SharePreferenceUtils.getBaseSharePreference().readUserId();
        if(StringUtil.isNotEmpty(uid)){
            loginBtn.setVisibility(View.GONE);
            registerBtn.setVisibility(View.GONE);
        }else {
            loginBtn.setVisibility(View.VISIBLE);
            registerBtn.setVisibility(View.VISIBLE);
        }*/
        imNecktie.startAnimation(AnimationUtils.setScaleAnimation());//添加动画效果
//        imCircular.startAnimation(AnimationUtils.setScaleAnimation2());//添加动画效果
        loginBtn.startAnimation(AnimationUtils.setAlphaAnimation(0,1));//添加动画效果
        registerBtn.startAnimation(AnimationUtils.setAlphaAnimation(0,1));
        imCircular.startAnimation(AnimationUtils.inFromBottomAnimation1(this));
        tvContent.startAnimation(AnimationUtils.inFromBottomAnimation3(this));
        nameChina.startAnimation(AnimationUtils.inFromBottomAnimation2(this));
        loginBtn.startAnimation(AnimationUtils.inFromBottomAnimation(this));
        registerBtn.startAnimation(AnimationUtils.inFromBottomAnimation(this));


       /* ACache aCache = ACache.get(this);
        LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
        if(loginNewBean!=null&&loginNewBean.getData()!=null&&loginNewBean.getData().getBasicInfo()!=null){
            LoginNewBean.DataBean.BasicInfoBean basicInfoBean = loginNewBean.getData().getBasicInfo();
            if(StringUtil.isNotEmpty(basicInfoBean.getRealName())&&StringUtil.isNotEmpty(basicInfoBean.getHeadImg())&&(basicInfoBean.getProfessionalIdentity()>0)){
                delay();
            }else {
                StartActivityManager.startRegisterLastActivity2(LoginOrRegisterActivity.this, "", "", "", "", "");
                finish();
            }
        }else{
            loginBtn.setVisibility(View.VISIBLE);
            registerBtn.setVisibility(View.VISIBLE);
        }*/
      delay();
    }
    //延时三秒
    private void delay(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
//                int readShowEdit = SharePreferenceUtils.getBaseSharePreference().readShowEdit();
//                if(readShowEdit!=-1){
//                    if((configBean!=null)&&(configBean.getData()!=null))
//                    {
//                        if(configBean.getData().getMinShowEdit()>readShowEdit)
//                        {
//                            startActivity(RegisterLastActivity2.class);
//                        }
//                        else
//                        {
//                            startActivity(MainActivity.class);
//                        }
//                    }
//                }

                startActivity(MainActivity.class);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 2000);//3秒后执行TimeTask的run方法
    }

    @OnClick({R.id.loginBtn, R.id.registerBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                startActivity(MCLoginActivity.class);
                break;
            case R.id.registerBtn:
//                startActivity(RegisterActivity3.class);
                StartActivityManager.startForgetActivity(this, Constant.REGISTER,"");
//                startActivity(RegisterCodeActivity.class);
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_LoginOrRegisterActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_LoginOrRegisterActivity);
        MobclickAgent.onPause(this);
    }
}
