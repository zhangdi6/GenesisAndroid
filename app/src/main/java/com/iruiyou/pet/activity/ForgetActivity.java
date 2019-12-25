package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.SendForSignupBean;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：注册和忘记密码 下一步
 * 作者：sgf on 2018/9/21
 * 邮箱：chinajpr@163.com
 */
public class ForgetActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleLine)
    View titleLine;
    @BindView(R.id.phoneNum)
    ClearEditText phoneNum;
    @BindView(R.id.complete)
    TextView complete;
    @BindView(R.id.countryTv)
    TextView countryTv;
    @BindView(R.id.countryCode)
    TextView countryCode;
    private TimeCount time;
    private String mCountry[] = {"86"};
    private int num = 0;
    private String r_fflag;
    private Context context;
    private String phone;

    @Override
    public int getLayout() {
        return R.layout.activity_forget1;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = ForgetActivity.this;
        complete.setEnabled(false);
        r_fflag = getIntent().getStringExtra("FLAG");
        phone = getIntent().getStringExtra("phone");

        if(r_fflag.equals(Constant.FORGET)){//忘记密码
            titleNameText.setText(getResources().getString(R.string.forgetPwd));
        }else if(r_fflag.equals(Constant.REGISTER)){
            titleNameText.setText(getResources().getString(R.string.register22));
        }

        time = new TimeCount(60000, 1000);
        initData();
    }

    private void initData() {
        if(r_fflag.equals(Constant.FORGET)) {//忘记密码
//            String phone = SharePreferenceUtils.getBaseSharePreference().readAccount();
            if (!TextUtils.isEmpty(phone)) {
                phoneNum.setText(phone);
            }
        }
        phoneNum.setSelection(phoneNum.getText().length());
        if(phoneNum.getText().length()>0){
            setLoginBackground(true, R.drawable.bt_psw_details_shape2);
        }
        phoneNum.addTextChangedListener(phoneWatcher);
        SoftKeyboardUtils.setEditTextState(phoneNum);
    }

    /**
     * 手机号码监听
     */
    private TextWatcher phoneWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0 ) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };

    /**
     * 设置登录按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setLoginBackground(boolean type, int background) {
        complete.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        complete.setBackgroundDrawable(drawable);
    }


    @OnClick({R.id.ll_title_left_view, R.id.complete, R.id.countryCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                if(SoftKeyboardUtils.isSoftShowing(this)){//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.complete:

               if(r_fflag.equals(Constant.FORGET)) {//忘记密码
//                startActivity(ForgetActivity3.class);
                  sendCode();
            }else if(r_fflag.equals(Constant.REGISTER)){
                distinguishCode();
            }
                break;

            case R.id.countryCode:
                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
                break;
        }
    }

    /**
     * 注册时判断手机号码是否注册
     */
    private void distinguishCode() {
        //发送短信验证码
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                if (codeBean.getStatusCode() == Constant.SUCCESS) {
////                    startActivity(RegisterActivity3.class);
//                    StartActivityManager.startRegisterActivity3(context,phoneNum.
//                    getText().toString().trim(),countryCode.getText().toString().trim().substring(1));
//                }else {
//                    T.showShort(codeBean.getMessage());
//                }
//            }
//            @Override
//            public void onError(ApiException e) {
//                T.showShort(e.getMessage());
//            }
//        }, this).sendVrfCode(mCountry[0], phoneNum.getText().toString().trim());
        DialogUtil.getInstance().showLoadingDialog(this);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                SendForSignupBean sendForSignupBean = GsonUtils.parseJson(resulte, SendForSignupBean.class);

                //没有注册过
                if (sendForSignupBean.getStatusCode() == Constant.SUCCESS) {
//                    time.start();
                    if(BaseApi.isDebug) {
                        T.showLong(sendForSignupBean.getMessage()+"验证码为："+
                                sendForSignupBean.getData().getVrfCode());
                    }

                    StartActivityManager.startForgetActivity3(context,
                 Constant.REGISTER,countryCode.getText().toString().trim().substring(1),
                 phoneNum.getText().toString().trim());

                //已经注册过了
                }else if (sendForSignupBean.getStatusCode()==1){

                    StartActivityManager.startForgetActivity3(context,
                            Constant.HAS_PWD_LOGIN,countryCode.getText().toString().trim().substring(1),
                            phoneNum.getText().toString().trim());
                }else{

                }
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, this).sendForSignup(mCountry[0], phoneNum.getText().toString().trim());
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                T.showShort(codeBean.getMessage());
//                if (codeBean.getStatusCode() == Constant.SUCCESS) {
////                    time.start();
//                    T.showShort(codeBean.getMessage());
//                    StartActivityManager.startForgetActivity3(context, Constant.FORGET,countryCode.getText().toString().trim().substring(1));
//                }else if(codeBean.getStatusCode() == Constant.TIPS1){
//
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                T.showShort(e.getMessage());
//            }
//        }, this).forgetCode(mCountry[0], phoneNum.getText().toString().trim());
        DialogUtil.getInstance().showLoadingDialog(this);

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                SendForSignupBean sendForSignupBean = GsonUtils.parseJson(resulte, SendForSignupBean.class);
                T.showShort(sendForSignupBean.getMessage());
                if (sendForSignupBean.getStatusCode() == Constant.SUCCESS) {
//                    time.start();
                    T.showShort(sendForSignupBean.getMessage());
                    StartActivityManager.startForgetActivity3(context, Constant.FORGET,countryCode.getText().toString().trim().substring(1),phoneNum.getText().toString().trim());
                }
                DialogUtil.getInstance().closeLoadingDialog();

            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
                T.showShort(e.getMessage());
            }
        }, this).sendForForget(mCountry[0], phoneNum.getText().toString().trim());
    }
    /**
     * 60s倒计时
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
//            complete.setClickable(false);
            num = 1;
        }

        @Override
        public void onFinish() {
//            complete.setClickable(true);
            num = 0;

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_ForgetActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_ForgetActivity);
        MobclickAgent.onPause(this);
    }
}
