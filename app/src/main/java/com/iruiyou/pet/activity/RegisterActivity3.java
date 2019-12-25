package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.bean.RegisterBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:注册
 * 创建日期:2018/8/27 on 17:17
 * 作者:JiaoPeiRong
 */
public class RegisterActivity3 extends BaseActivity {
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
    @BindView(R.id.tvCodePhone)
    TextView tvCodePhone;
//    @BindView(R.id.countryTv)
//    TextView countryTv;
//    @BindView(R.id.phoneNum)
//    TextInputEditText phoneNum;
    @BindView(R.id.code)
    TextInputEditText code;
    @BindView(R.id.getCode)
    TextView getCode;
    @BindView(R.id.pwd)
    TextInputEditText pwd;
    @BindView(R.id.register)
    TextView register;
//    @BindView(R.id.countryCode)
//    TextView countryCode;
//    @BindView(R.id.invitationCode)
//    TextInputEditText invitationCode;
    @BindView(R.id.cbPwdHideDisplay)
    CheckBox cbPwdHideDisplay;
    private TimeCount time;
    String mCountry[] = {"86"};
    private String phoneContent = "";
    private String codeContent = "";
    private String passwordContent = "";
    private String invitationContent = "";
    private String phone,nationalCode;
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_register33;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = RegisterActivity3.this;
        register.setEnabled(false);
        phone = getIntent().getStringExtra("PHONE");
        nationalCode = getIntent().getStringExtra("nationalCode");
        tvCodePhone.setText("+"+nationalCode+"\t"+phone);
        time = new TimeCount(60000, 1000);
        time.start();
        titleNameText.setText(getResources().getString(R.string.register22));
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .fitsSystemWindows(true)
                .init();
        initData();
    }

    private void initData() {

        code.addTextChangedListener(codeWatcher);
        pwd.addTextChangedListener(passwordWatcher);
        cbPwdHideDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    //设置EditText文本为可见的
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                pwd.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = pwd.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

    /**
     * 验证码监听
     */
    private TextWatcher codeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            codeContent = editable.toString();
            if(editable.toString().length()>0 &&!TextUtils.isEmpty(passwordContent)){
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            }else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };

    /**
     * 密码监听
     */
    private TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            passwordContent = editable.toString();
            if(editable.toString().length()>=6 &&!TextUtils.isEmpty(codeContent)){
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            }else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };

    /**
     * 设置登录按钮背景颜色
     * @param type 是否可点击
     * @param background  背景颜色
     */
    private void setLoginBackground(boolean type, int background) {
        register.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        register.setBackgroundDrawable(drawable);
    }

    @OnClick({ R.id.getCode, R.id.register, R.id.ll_title_left_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
//            case R.id.countryCode:
//                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
//                break;
            case R.id.getCode:

                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        if (codeBean.getStatusCode() == Constant.SUCCESS) {
                            time.start();
                        }else {
                            T.showShort(codeBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).sendVrfCode(mCountry[0], phone);
                break;
            case R.id.register:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        T.showShort(codeBean.getMessage());
                        if (codeBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(codeBean.getData());
                            register();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).checkVrfCode(mCountry[0], phone, code.getText().toString().trim());
                break;
//            case R.id.login:
//                startActivity(MCLoginActivity.class);
//                break;
            case R.id.ll_title_left_view:
                finish();
                break;
        }
    }


    /**
     * 注册
     */
    private void register() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                RegisterBean registerBean = GsonUtils.parseJson(resulte, RegisterBean.class);
                if (registerBean.getStatusCode() == Constant.SUCCESS) {
//                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                    SharePreferenceUtils.getBaseSharePreference().saveToken(registerBean.getToken());
                    SharePreferenceUtils.getBaseSharePreference().saveAccount(registerBean.getData().getPhone());
                    startActivity(MCLoginActivity.class);
//                    StartActivityManager.startRegisterCodeActivity(context,nationalCode,phone,code.getText().toString().trim(),pwd.getText().toString().trim());

                }
                T.showShort(registerBean.getMessage());
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).register(SharePreferenceUtils.getBaseSharePreference().readUserId(), "--1", pwd.getText().toString(), code.getText().toString().trim());
//        }, this).register2(code.getText().toString().trim(), nationalCode, phone,pwd.getText().toString().trim());

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
            getCode.setClickable(false);
            getCode.setText(Html.fromHtml(StringUtil.setStrGray3(millisUntilFinished / 1000 + "s")));
        }

        @Override
        public void onFinish() {
            getCode.setText(getResources().getString(R.string.RegainValidationCode));
            getCode.setClickable(true);

        }
    }
}
