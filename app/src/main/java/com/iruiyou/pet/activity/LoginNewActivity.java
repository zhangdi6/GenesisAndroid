package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：登陆页面
 * 作者：jiaopeirong on 2018/8/12 17:46
 * 邮箱：chinajpr@163.com
 */
public class LoginNewActivity extends BaseActivity {
    @BindView(R.id.pwdLl)
    LinearLayout pwdLl;
    @BindView(R.id.account)
    LinearLayout account;
    @BindView(R.id.forgetPwd)
    TextView forgetPwd;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.phone)
    EditText phone;

    @Override
    public int getLayout() {
        return R.layout.activity_login_new;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().finishOtherActivity(LoginNewActivity.class);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.forgetPwd, R.id.register, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.forgetPwd:
                startActivity(ForgetPwdNewActivity.class);
                break;
            case R.id.register:
                startActivity(RegisterNewActivity.class);
                break;
            case R.id.login:
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
                        T.showShort(loginBean.getMessage());
                        if (loginBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginBean.getData().getRealName());
//                            aCache.put(TagConstants.LoginTag, loginBean);
//                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginBean.getData().get_id());
                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginBean.getToken());
                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
                            startActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).login(phone.getText().toString(), pwd.getText().toString(), "86");
                break;
        }
    }

}
