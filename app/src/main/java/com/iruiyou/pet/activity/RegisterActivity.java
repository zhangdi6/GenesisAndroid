package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.Validation;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.RegisterBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册页面
 */
public class RegisterActivity extends BaseActivity {


    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.pwdAgain)
    EditText pwdAgain;
    @BindView(R.id.nick)
    EditText nick;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.login)
    Button login;

    @Override
    public int getLayout() {
        return R.layout.activity_register3;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.login:
                if (!Validation.isPassword(pwd.getText().toString())) {
                    T.showShort("请输入6-12位密码");
                    return;
                }
                if (!pwd.getText().toString().equals(pwdAgain.getText().toString())) {
                    T.showShort("两次输入的密码不一致");
                    return;
                }
                //验证码
//                if (true)
                {
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            RegisterBean registerBean = GsonUtils.parseJson(resulte, RegisterBean.class);
                            T.showShort(registerBean.getMessage());
                            SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().saveToken(registerBean.getToken());
                            SharePreferenceUtils.getBaseSharePreference().saveAccount(registerBean.getData().getPhone());
                            startActivity(LoginActivity.class);
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                        }
                    }, this).register(SharePreferenceUtils.getBaseSharePreference().readUserId(), nick.getText().toString(),pwd.getText().toString(), code.getText().toString());

                }
                break;
            case R.id.back:
                finish();
                break;
        }

    }
}
