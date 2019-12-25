package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.common.utils.Validation;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 类描述:登录
 * 作者：Created by JiaoPeiRong on 2017/4/29 10:47
 * 邮箱：chinajpr@163.com
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.del)
    ImageView del;
    @BindView(R.id.look)
    CheckBox look;
    @BindView(R.id.forget)
    TextView forget;
    @BindView(R.id.register)
    TextView register;
    private ACache aCache;

    @Override
    public int getLayout() {
        return R.layout.activity_login2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        AppManager.getAppManager().finishOtherActivity(LoginActivity.class);
        aCache = ACache.get(this);
        phone.setText(SharePreferenceUtils.getBaseSharePreference().readAccount());
        pwd.setText(SharePreferenceUtils.getBaseSharePreference().readPassword());
        setlistener();
    }

    /**
     * 设置监听
     */
    private void setlistener() {
        look.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick({R.id.login, R.id.register, R.id.forget, R.id.del, R.id.back})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.login:
                if (!Validation.isPassword(phone.getText().toString())) {
                    T.showShort("请输入正确的手机号！");
                    return;
                }
                if (!Validation.isPassword(pwd.getText().toString())) {
                    T.showShort("请输入6-12位数字和字母组合");
                    return;
                }
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
                        T.showShort(loginBean.getMessage());
                        if (loginBean.getStatusCode() == 0) {
                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginBean.getData().getRealName());
                            aCache.put(TagConstants.LoginTag, loginBean);
                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginBean.getData().get_id());
                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginBean.getToken());
                            SharePreferenceUtils.getBaseSharePreference().saveUserChannel(loginBean.getData().getUserChannel());
                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
                            startActivity(MainActivity.class);
                            finish();
                           // App.isLogin=true;
                            SharePreferenceUtils.getBaseSharePreference().saveState(true);
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, this).loginWithPassword(phone.getText().toString(), pwd.getText().toString(), "86");

                break;
            case R.id.register:
                startActivity(CodeActivity.class);
                break;

            case R.id.forget:
                startActivity(ForgetPwdActivity.class);
                break;
            case R.id.del:
                phone.setText("");
                break;
            case R.id.back:
                finish();
                break;
        }

    }

}
