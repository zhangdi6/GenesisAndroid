package com.iruiyou.pet.activity;

import android.content.Intent;
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
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * 作者：jiaopeirong on 2018/5/24 21:31
 * 邮箱：chinajpr@163.com
 */
public class ForgetPwdNewActivity extends BaseActivity {


    @BindView(R.id.account)
    LinearLayout account;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.codeLl)
    LinearLayout codeLl;
    @BindView(R.id.next)
    TextView next;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.phone)
    EditText phone;
    private TimeCount time;
    private CodeBean codeBean;
    private String countryCode[] = {"86"};

    @Override
    public int getLayout() {
        return R.layout.activity_forget;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        time = new TimeCount(60000, 1000);
    }

    @OnClick({R.id.send, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                time.start();
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
//                        codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                        T.showShort(codeBean.getError());
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).forgetCode(countryCode[0], phone.getText().toString().trim());
                break;
            case R.id.next:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                        if (codeBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(codeBean.getData());
                            Intent intent = new Intent(ForgetPwdNewActivity.this , ForgetPwdNewActivity2.class);
                            intent.putExtra(TagConstants.CODE ,  countryCode[0]);
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
            send.setText("(" + millisUntilFinished / 1000 + ")"+getResources().getString(R.string.resend3));
        }

        @Override
        public void onFinish() {
            send.setText(getResources().getString(R.string.resend2));
            send.setClickable(true);

        }
    }
}
