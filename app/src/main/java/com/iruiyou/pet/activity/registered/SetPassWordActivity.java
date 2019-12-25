package com.iruiyou.pet.activity.registered;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.common.utils.AppManager;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ForgetActivity3;
import com.iruiyou.pet.activity.LoginOrRegisterActivity;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:登陆
 * 创建日期:2018/8/27 on 16:21
 * 作者:JiaoPeiRong
 */
public class SetPassWordActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;

    @Override
    public int getLayout() {
        return R.layout.activity_set_pass_word;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        AppManager.getAppManager().finishOtherActivity(SetPassWordActivity.class);
        ButterKnife.bind(this);

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
        }
    };

    @OnClick({R.id.ll_title_left_view, R.id.forgetPwd, R.id.login, R.id.countryCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                startActivity(LoginOrRegisterActivity.class);
                finish();
                break;
            case R.id.forgetPwd:
                startActivity(ForgetActivity3.class);
                break;
            case R.id.login:
//                new UserTask(new HttpOnNextListener() {
//                    @Override
//                    public void onNext(String resulte, String method) {
//                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
//                        T.showShort(loginBean.getMessage());
//                        if (loginBean.getStatusCode() == 0) {
//                            SharePreferenceUtils.getBaseSharePreference().saveInviteCode(loginBean.getData().getInviteCode());
//                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginBean.getData().getName());
////                            aCache.put(TagConstants.LoginTag, loginBean);
////                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
//                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginBean.getData().get_id());
//                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginBean.getToken());
//                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
//                            SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginBean.getData().getCountryCode());
//                            startActivity(MainActivity.class);
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        T.showShort(e.getMessage());
//                    }
//                }, this).login(phone.getText().toString(), password.getText().toString(), mCountry[0]);
                break;
//            case R.id.register:
//                startActivity(RegisterActivity3.class);
//                break;
            case R.id.countryCode:
//                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
                break;
        }
    }

}
