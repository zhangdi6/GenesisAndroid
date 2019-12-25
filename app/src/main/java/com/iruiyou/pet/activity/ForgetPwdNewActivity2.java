package com.iruiyou.pet.activity;

import android.os.Bundle;
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
import com.iruiyou.pet.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * 作者：jiaopeirong on 2018/5/24 21:31
 * 邮箱：chinajpr@163.com
 */
public class ForgetPwdNewActivity2 extends BaseActivity {
    @BindView(R.id.pwdLl)
    LinearLayout pwdLl;
    @BindView(R.id.account)
    LinearLayout account;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.pwd2)
    EditText pwd2;
    @BindView(R.id.pwd)
    EditText pwd;
    private String phone;
    private String code;

    @Override
    public int getLayout() {
        return R.layout.activity_forget2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra(TagConstants.PHONE);
        code = getIntent().getStringExtra(TagConstants.CODE);
        code = code.replace("+", "");
    }


    @OnClick(R.id.login)
    public void onViewClicked() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
                if (loginBean.getStatusCode() == 0) {
                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                    T.showShort(getResources().getString(R.string.PasswordModificationSucceeded));
                    startActivity(LoginNewActivity.class);
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).resetPassword(phone, pwd.getText().toString(), code);
    }

}
