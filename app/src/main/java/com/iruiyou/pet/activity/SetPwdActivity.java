package com.iruiyou.pet.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.Validation;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ResetPasswordWithOldBean;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置密码
 * 作者：jiaopeirong on 2018/5/24 21:27
 * 邮箱：chinajpr@163.com
 */
public class SetPwdActivity extends BaseActivity {
    @BindView(R.id.pwd)
    ClearEditText pwd;
    @BindView(R.id.pwdAgain)
    ClearEditText pwdAgain;
    @BindView(R.id.cbPwdDuplicate)
    CheckBox cbPwdDuplicate;
    @BindView(R.id.cbPwdHideDisplaynew)
    CheckBox cbPwdHideDisplaynew;
    @BindView(R.id.sure)
    Button sure;
    private String phone;
    private String code;
    private String pwdAgainCopy = "";
    private String pwdCopy = "";

    @Override
    public int getLayout() {
        return R.layout.activity_set_pwd;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.setPassword));
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
//        phone = getIntent().getStringExtra(TagConstants.PHONE);
        phone = SharePreferenceUtils.getBaseSharePreference().readAccount();
//        code = getIntent().getStringExtra(TagConstants.CODE);
//        code = code.replace("+","");
        code = SharePreferenceUtils.getBaseSharePreference().readCountryCode();

        pwd.addTextChangedListener(etPwdWatcher);
        pwdAgain.addTextChangedListener(pwdAgainWatcher);
        //给CheckBox设置事件监听
        cbPwdHideDisplaynew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    //设置EditText文本为可见的
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                pwd.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = pwd.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
        cbPwdDuplicate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    //设置EditText文本为可见的
                    pwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    pwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                pwdAgain.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = pwdAgain.getText();
                if (charSequence != null) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

    /**
     * 密码输入监听
     */
    private TextWatcher etPwdWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            pwdCopy = editable.toString();
            if(editable.toString().length()>=6 && pwdAgainCopy.length()>=6){
                sure.setEnabled(true);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
                //設置佈局背景色方法一
                Drawable drawable = getResources().getDrawable(R.drawable.bt_psw_details_shape2);
                sure.setBackgroundDrawable(drawable);
            }else {
                sure.setEnabled(false);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
                //設置佈局背景色方法一
                Drawable drawable = getResources().getDrawable(R.drawable.bt_psw_details_shape);
                sure.setBackgroundDrawable(drawable);
            }
        }
    };

    /**
     * 重复密码输入监听
     */
    private TextWatcher pwdAgainWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            pwdAgainCopy = editable.toString();
            if(editable.toString().length()>=6 && pwdCopy.length() >= 6){
                sure.setEnabled(true);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
                //設置佈局背景色方法一
                Drawable drawable = getResources().getDrawable(R.drawable.bt_psw_details_shape2);
                sure.setBackgroundDrawable(drawable);
            }else {
                sure.setEnabled(false);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
                //設置佈局背景色方法一
                Drawable drawable = getResources().getDrawable(R.drawable.bt_psw_details_shape);
                sure.setBackgroundDrawable(drawable);
            }
        }
    };

    @OnClick({R.id.ll_title_left_view, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.sure:
                if (!Validation.isPassword(pwd.getText().toString())) {
                    T.showShort(getResources().getString(R.string.PleaseEnterTheBitPasswordOld));
                    return;
                }
                if(!Validation.isPassword(pwdAgain.getText().toString())){
                    T.showShort(getResources().getString(R.string.PleaseEnterTheBitPasswordNew));
                    return;
                }
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
//                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
//                        if (loginBean.getStatusCode() == 0) {
//                            SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
//                            T.showShort(getResources().getString(R.string.PasswordModificationSucceeded));
//                            startActivity(MCLoginActivity.class);
//                            AppManager.getAppManager().finishOtherActivity(MainActivity.class);
//                            finish();
//                        }
                        ResetPasswordWithOldBean resetPasswordWithOldBean = GsonUtils.parseJson(resulte, ResetPasswordWithOldBean.class);
                        T.showShort(resetPasswordWithOldBean.getMessage());
                        if(resetPasswordWithOldBean.getStatusCode() == Constant.SUCCESS){
                            SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
//                            T.showShort(getResources().getString(R.string.PasswordModificationSucceeded));
                            startActivity(MCLoginActivity.class);
                            AppManager.getAppManager().finishOtherActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
//                }, this).resetPassword(phone, pwd.getText().toString(), code);
                }, this).resetPasswordWithOld(pwd.getText().toString(), pwdAgain.getText().toString());

                break;
        }
    }
}