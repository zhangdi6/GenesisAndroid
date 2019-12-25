package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.common.utils.Validation;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.utils.DialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * 作者：jiaopeirong on 2018/5/24 21:31
 * 邮箱：chinajpr@163.com
 */
public class ForgetPwdActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.country)
    TextView country;
    @BindView(R.id.countryName)
    TextView countryName;
    private TimeCount time;
    private CodeBean codeBean;
    private String countryCode[] = {"86"};

    @Override
    public int getLayout() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("忘记密码");
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.send, R.id.next, R.id.countryName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                if (!Validation.isPhoneNum(phone.getText().toString())) {
                    T.showShort("请输入正确的手机号！");
                    return;
                }
                time.start();
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                        T.showShort(codeBean.getError());
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).forgetCode(countryCode[0], phone.getText().toString().trim());
                break;
            case R.id.next:
                if (!Validation.isCaptcha(code.getText().toString())) {
                    T.showShort("请输入正确的验证码");
                    return;
                }
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        if (codeBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(codeBean.getData());
                            Intent intent = new Intent(ForgetPwdActivity.this , SetPwdActivity.class);
                            intent.putExtra(TagConstants.CODE ,  country.getText().toString());
                            intent.putExtra(TagConstants.PHONE , phone.getText().toString());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).checkVrfCode(countryCode[0], phone.getText().toString().trim(), code.getText().toString().trim());
                break;
            case R.id.countryName:
                DialogUtils.countrySelect(this, country, countryName,countryCode);
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
            send.setClickable(false);
            send.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            send.setText("重新获取验证码");
            send.setClickable(true);

        }
    }
}
