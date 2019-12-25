package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：注册
 * 作者：jiaopeirong on 2018/8/12 22:52
 * 邮箱：chinajpr@163.com
 */
public class RegisterNewActivity extends BaseActivity {
    @BindView(R.id.pwdLl)
    LinearLayout pwdLl;
    @BindView(R.id.account)
    LinearLayout account;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.codeLl)
    LinearLayout codeLl;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.pwd)
    EditText pwd;
    private TimeCount time;

    @Override
    public int getLayout() {
        return R.layout.activity_register_new2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.send, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                time.start();
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).forgetCode("86", phone.getText().toString().trim());
                break;
            case R.id.register:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        if (codeBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(codeBean.getData());
                            register();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).checkVrfCode("86", phone.getText().toString().trim(), code.getText().toString().trim());
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
                T.showShort(registerBean.getMessage());
                if (registerBean.getStatusCode()== Constant.SUCCESS && registerBean.getData().isRegistered() == true){
                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                    SharePreferenceUtils.getBaseSharePreference().saveToken(registerBean.getToken());
                    SharePreferenceUtils.getBaseSharePreference().saveAccount(registerBean.getData().getPhone());
                    startActivity(LoginActivity.class);

                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).register(SharePreferenceUtils.getBaseSharePreference().readUserId(), "TEST", pwd.getText().toString(), code.getText().toString());
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
            send.setClickable(false);
            send.setText(getResources().getString(R.string.resend2) + "("+millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            send.setText(getResources().getString(R.string.RegainValidationCode));
            send.setClickable(true);

        }
    }
}
