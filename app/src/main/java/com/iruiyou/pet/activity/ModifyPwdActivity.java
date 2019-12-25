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
import com.iruiyou.pet.bean.LoginBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 类描述:修改密码
 * 作者：Created by JiaoPeiRong on 2018/5/7 15:56
 * 邮箱：chinajpr@163.com
 */

public class ModifyPwdActivity extends BaseActivity {

    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.cbPwdHideDisplay)
    CheckBox cbPwdHideDisplay;
    @BindView(R.id.sure)
    Button sure;

    @Override
    public int getLayout() {
        return R.layout.activity_old_pwd;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
        setTitle(getResources().getString(R.string.ChangePassword));
        pwd.addTextChangedListener(etPwdWatcher);
        //给CheckBox设置事件监听
        cbPwdHideDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
            if(editable.toString().length()>6){
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
                    T.showShort(getResources().getString(R.string.PleaseEnterTheBitPassword));
                    return;
                }
                request();
                break;
        }
    }

    /**
     * 验证密码---走的登陆接口
     */
    private void request() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
                if (loginBean.getStatusCode() == 0) {
                    startActivity(SetPwdActivity.class);
//                    T.showShort(loginBean.getMessage());
                }else {
                    T.showShort(loginBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).login(SharePreferenceUtils.getBaseSharePreference().readAccount(), pwd.getText().toString(),"86");
    }
}
