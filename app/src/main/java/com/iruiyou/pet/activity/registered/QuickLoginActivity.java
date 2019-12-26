package com.iruiyou.pet.activity.registered;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ForgetActivity3;
import com.iruiyou.pet.activity.MCLoginActivity;
import com.iruiyou.pet.activity.WebActivity;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.RegisterBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.SoftKeyboardUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.j256.ormlite.stmt.query.In;

public class QuickLoginActivity extends BaseActivity {


    private LinearLayout mBackView;
    private TextView mTitle;
    private TextView mLogin_user_pwd;
    private TextView mBtn_next;
    private TextView mContryCode;
    private TextView countryTv;
    private LinearLayout llPhone;
    private TextInputEditText mPhone_edittext;

    @BindView(R.id.agree)
    TextView agree;

    String mCountry[] = {"86"};

    @Override
    public int getLayout() {
        return R.layout.activity_registered_mobiles;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initView();
        initClick();
        agree.setText("注册表示同意《脉场协议》");
        String s = agree.getText().toString();
        SpannableString spannableString = new SpannableString(s);
        ForegroundColorSpan text = new ForegroundColorSpan(Color.parseColor("#3fa5f2"));
        spannableString.setSpan(text,6,s.length(),0);
        agree.setText(spannableString);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//     /genesis/protocol
               // StartActivityManager.startWebViewActivity(QuickLoginActivity.this, "脉场用户协议", "https://www.maichangapp.com/genesis/protocol", false, true);
                Intent intent = new Intent(QuickLoginActivity.this, WebActivity.class);
                intent.putExtra("url","https://www.maichangapp.com/genesis/protocol");
                startActivity(intent);
            }
        });

    }

    private void initClick() {
        //默认下一步不可点击
     //   mBtn_next.setEnabled(false);

        //点击返回按钮
        mBackView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mTitle.setText("快速登录");
        //设置下一步btn的监听
        initData();

        //设置使用密码登录的按钮，蓝色的那个
        mLogin_user_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuickLoginActivity.this, MCLoginActivity.class);

                startActivity(intent);

            }
        });


    }

    private void initView() {
        mBackView = (LinearLayout) findViewById(R.id.ll_title_left_view);
        mTitle = (TextView) findViewById(R.id.title_name_text);
        mLogin_user_pwd = (TextView) findViewById(R.id.registed_login);
        mBtn_next = (TextView) findViewById(R.id.register);
        countryTv = (TextView) findViewById(R.id.countryTv);
        mContryCode = (TextView) findViewById(R.id.countryCode);

        llPhone = (LinearLayout) findViewById(R.id.llPhone);
        mPhone_edittext = (TextInputEditText) findViewById(R.id.phoneNum);

        String s = SharePreferenceUtils.getBaseSharePreference().readAccount();
        if (s!=null&&!s.equals("")){
            mPhone_edittext.setText(s);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String s = SharePreferenceUtils.getBaseSharePreference().readAccount();
        if (s!=null&&!s.equals("")){
            mPhone_edittext.setText(s);
        }
    }

    private void initData() {


        mPhone_edittext.setSelection(mPhone_edittext.getText().length());
        if(mPhone_edittext.getText().length()>0){
            setLoginBackground(true, R.drawable.bt_psw_details_shape2);
        }
        mPhone_edittext.addTextChangedListener(phoneWatcher);
        SoftKeyboardUtils.setEditTextState(mPhone_edittext);



        mBtn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        RegisterBean registerBean = GsonUtils.parseJson(resulte, RegisterBean.class);
                        Log.e("chumu", "onNext: "+registerBean.toString());
                        if (registerBean.getStatusCode()== Constant.SUCCESS && registerBean.getData().isRegistered()) {

                            Intent intent = new Intent(QuickLoginActivity.this, ForgetActivity3.class);
                            intent.putExtra("FLAG", Constant.HAS_PWD_LOGIN);
                            intent.putExtra("pp",registerBean.getData().getPhone());
                            intent.putExtra("nationalCode",registerBean.getData().getCountryCode());
                            startActivity(intent);

                        }
                        else if (!registerBean.getData().isRegistered()){//注册

                            Intent intent = new Intent(QuickLoginActivity.this, ForgetActivity3.class);
                            intent.putExtra("FLAG", Constant.REGISTER);
                            intent.putExtra("pp",registerBean.getData().getPhone());
                            intent.putExtra("nationalCode",registerBean.getData().getCountryCode());
                            startActivity(intent);
                        }
                        /*T.showShort(registerBean.getMessage());*/
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, QuickLoginActivity.this)
                        .register2( mPhone_edittext.getText().toString().trim(), "86");
//
                Log.e("chumu", "onClick: "+ SharePreferenceUtils.getBaseSharePreference().readUserId()+"q   "+
                        mPhone_edittext.getText().toString().trim()+"q     "+ mContryCode.getText().toString().trim());


            }
        });

        mContryCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtils.countrySelect(QuickLoginActivity.this, mContryCode, countryTv, mCountry);
            }
        });

        llPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SoftKeyboardUtils.isSoftShowing(QuickLoginActivity.this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(QuickLoginActivity.this);
                }
            }
        });
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
            if (editable.toString().length() > 0 ) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };

    /**
     * 设置登录按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setLoginBackground(boolean type, int background) {
        mBtn_next.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        mBtn_next.setBackgroundDrawable(drawable);
    }


}
