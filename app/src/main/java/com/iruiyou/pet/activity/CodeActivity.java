package com.iruiyou.pet.activity;

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
import com.iruiyou.common.utils.Validation;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
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
 * 类描述：验证码
 * 作者：jiaopeirong on 2018/5/23 22:26
 * 邮箱：chinajpr@163.com
 */
public class CodeActivity extends BaseActivity {
    private static final String userAgree = BaseApi.baseUrlNoApi+"planet/protocol";
    private static final String privacy = BaseApi.baseUrlNoApi+"planet/privacy";
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
    @BindView(R.id.login)
    TextView login;
    private TimeCount time;
    CodeBean codeBean;
    private String countryCode[] = {"86"};
    private String countryCode2 = "86";

    @Override
    public int getLayout() {
        return R.layout.activity_register3;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.send, R.id.next, R.id.login, R.id.countryName, R.id.back})
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
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).sendVrfCode(countryCode[0], phone.getText().toString().trim());
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
                            startActivity(RegisterActivity.class);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).checkVrfCode(countryCode[0], phone.getText().toString().trim(), code.getText().toString().trim());
                break;
            case R.id.login:
                startActivity(LoginActivity.class);
                break;
            case R.id.countryName:
                DialogUtils.countrySelect(this, country, countryName, countryCode);
                break;
            case R.id.back:
                finish();
                break;
//            case R.id.tvUserArgee:
//                Intent i = new Intent(this, AgreementWebActivity.class);
//                i.putExtra(TagConstants.WebTag, userAgree);
//                startActivity(i);
//                break;
//            case R.id.privacy:
//                Intent ii = new Intent(this, AgreementWebActivity.class);
//                ii.putExtra(TagConstants.WebTag, privacy);
//                startActivity(ii);
//                break;
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
