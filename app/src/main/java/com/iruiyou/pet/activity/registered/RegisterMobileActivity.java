package com.iruiyou.pet.activity.registered;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.LoginOrRegisterActivity;
import com.iruiyou.pet.activity.RegisterActivity3;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:注册
 * 创建日期:2018/8/27 on 16:21
 * 作者:sgf
 */
public class RegisterMobileActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.countryTv)
    TextView countryTv;
    @BindView(R.id.phoneNum)
    TextInputEditText phoneNum;
    @BindView(R.id.countryCode)
    TextView countryCode;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    private RegisterMobileActivity.TimeCount time;
    private String mCountry[] = {"86"};
    private int num = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_registered_mobile;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {

        AppManager.getAppManager().finishOtherActivity(RegisterMobileActivity.class);
        ButterKnife.bind(this);

        time = new RegisterMobileActivity.TimeCount(60000, 1000);
        titleNameText.setText(getResources().getString(R.string.register22));
        initData();
    }

    private void initData() {
        String phone = SharePreferenceUtils.getBaseSharePreference().readAccount();
        if (!TextUtils.isEmpty(phone)) {
            phoneNum.setText(phone);
            phoneNum.setSelection(phoneNum.getText().length());
            if(phoneNum.getText().length()>0){
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            }
        }
        phoneNum.addTextChangedListener(phoneWatcher);
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

    @OnClick({R.id.ll_title_left_view, R.id.register, R.id.countryCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:

                startActivity(LoginOrRegisterActivity.class);
                finish();
                break;
            case R.id.register:

                Log.d("-----------------------","注册");
                if (num == 1) {
                    T.showShort("验证码发送过于频繁");
                } else if (num == 0) {
                    time.start();
                    startActivity(RegisterActivity3.class);
                }
                break;
            case R.id.countryCode:
                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
                break;
        }
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

}
