package com.iruiyou.pet.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.bean.ForgetBean;
import com.iruiyou.pet.bean.LoginBean;
import com.iruiyou.pet.bean.PhoneVefBean;
import com.iruiyou.pet.bean.RegisterNewBeanNew;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：设置密码（注册，忘记密码共用的第二个入口）
 * 作者：sgf on 2018/8/27 23:15
 * 邮箱：chinajpr@163.com
 */
public class ForgetActivity3 extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleLine)
    View titleLine;
    //    @BindView(R.id.phoneNum)
//    TextInputEditText phoneNum;
    @BindView(R.id.code)
    TextInputEditText code;
    @BindView(R.id.send)
    TextView send;
    @BindView(R.id.pwd)
    TextInputEditText pwd;

    //验证码已发送至
    @BindView(R.id.llCodeContent)
    LinearLayout mCodeLayout;

    @BindView(R.id.complete)
    TextView complete;
    @BindView(R.id.rlPassword)
    RelativeLayout mLayout;
    //    @BindView(R.id.country)
//    TextView country;
//    @BindView(R.id.countryTv)
//    TextView countryTv;
//    @BindView(R.id.countryCode)
//    TextView countryCode;
    @BindView(R.id.tvCodePhone)
    TextView tvCodePhone;
    @BindView(R.id.cbPwdHideDisplay)
    CheckBox cbPwdHideDisplay;
    private TimeCount time;
    private String mCountry[] = {"86"};
    private String phoneContent = "";
    private String codeContent = "";
    private String passwordContent = "";
    private String phoneNum;
    private String nationalCode;
    private String phone;
    private String r_fflag;

    @Override
    public int getLayout() {
        return R.layout.activity_forget3;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {

        ButterKnife.bind(this);

        complete.setEnabled(false);

        nationalCode = getIntent().getStringExtra("nationalCode");

        phone = getIntent().getStringExtra("pp");

        r_fflag = getIntent().getStringExtra("FLAG");

        /*tvCodePhone.setText(phone);*/
        if (r_fflag.equals(Constant.REGISTER)) {
            titleNameText.setText(getResources().getString(R.string.register6));
            mLayout.setVisibility(View.VISIBLE);
        } else if (r_fflag.equals(Constant.HAS_PWD_LOGIN)) {
            titleNameText.setText("登录");
            mLayout.setVisibility(View.GONE);
        } else if (r_fflag.equals("login")) {
            mLayout.setVisibility(View.VISIBLE);
            titleNameText.setText("登录");
            //需要隐藏掉  "验证码已发送至" 这个布局
            mCodeLayout.setVisibility(View.GONE);
        }
        time = new TimeCount(60000, 1000);
        time.start();
        initData();
    }

    private void initDataRegister() {//用户注册过，请求验证码与手机后的接口
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("asd", resulte);

                PhoneVefBean loginNewBean = GsonUtils.parseJson(resulte, PhoneVefBean.class);
                Log.i("hm", "onNext: "+loginNewBean);
                Log.i("wh", "onNext: "+loginNewBean.getStatusCode());
                if (loginNewBean.getStatusCode() == Constant.SUCCESS) {
                    Log.i("zd", "onNext: "+loginNewBean.getStatusCode());
                    Log.i("lsd", "1234567890");
                    SharePreferenceUtils.getBaseSharePreference().saveAccount(loginNewBean.getData().getUserInfo().getPhone());
                    SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginNewBean.getData().getUserInfo().getCountryCode());
                    SharePreferenceUtils.getBaseSharePreference().saveUserId(loginNewBean.getData().getBasicInfo().getUserId() + "");

                    SharePreferenceUtils.getBaseSharePreference().saveWorkCount(loginNewBean.getData().getStatisticsInfo().getWorkCount());
                    SharePreferenceUtils.getBaseSharePreference().saveInvitedCode(loginNewBean.getData().getUserInfo().getInvitedCode());
                    SharePreferenceUtils.getBaseSharePreference().saveNickName(loginNewBean.getData().getBasicInfo().getRealName());
//                            aCache.put(TagConstants.LoginTag, loginBean);
//                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
                    SharePreferenceUtils.getBaseSharePreference().saveBasicId(loginNewBean.getData().getBasicInfo().get_id());//用户资料id
                    SharePreferenceUtils.getBaseSharePreference().saveToken(loginNewBean.getToken());
                    SharePreferenceUtils.getBaseSharePreference().saveIMToken(loginNewBean.getRcToken());
                    SharePreferenceUtils.getBaseSharePreference().saveShowEdit(loginNewBean.getData().getBasicInfo().getShowEdit());//用户id
                 //   PhoneVefBean.BasicInfo basicInfoBean = loginNewBean.getData().getBasicInfo();

                    Log.i("lsd", "1234567890");
                    Intent intent = new Intent(ForgetActivity3.this,MainActivity.class);
                    intent.putExtra("type",1);
                    hideInputMethod();
                    startActivity(intent);
                    finish();
                    SharePreferenceUtils.getBaseSharePreference().saveState(true);
                    
                }else{
                    Log.i("poiuytrewq", "ont: ");
                }

            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).getlogin(phone, nationalCode, code.getText().toString().trim());


    }

    private void initData() {

        /*phoneNum = SharePreferenceUtils.getBaseSharePreference().readAccount();*/
//        tvCodePhone.setText("+"+SharePreferenceUtils.getBaseSharePreference().readCountryCode()+"\t"+SharePreferenceUtils.getBaseSharePreference().readAccount());
        tvCodePhone.setText(nationalCode + "\t" + phone);
        /*tvCodePhone.setText("----------------");*/
//        phoneNum.addTextChangedListener(phoneWatcher);
        if (r_fflag.equals(Constant.HAS_PWD_LOGIN)) {
            code.addTextChangedListener(codeWatcher2);
        } else {
            code.addTextChangedListener(codeWatcher);
        }

        pwd.addTextChangedListener(passwordWatcher);
//        sendCode();
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
            phoneContent = editable.toString();
            if (editable.toString().length() > 0 && !TextUtils.isEmpty(passwordContent) && !TextUtils.isEmpty(codeContent)) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    /**
     * 验证码监听
     */
    private TextWatcher codeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            codeContent = editable.toString();
            if (editable.toString().length() > 0 && !TextUtils.isEmpty(passwordContent)) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    private TextWatcher codeWatcher2 = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            codeContent = editable.toString();
            if (editable.toString().length() > 0 && editable.toString().length() == 4) {
                setLoginBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setLoginBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    /**
     * 密码监听
     */
    private TextWatcher passwordWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            passwordContent = editable.toString();
            if (editable.toString().length() >= 6 && !TextUtils.isEmpty(codeContent)) {
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
        complete.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        complete.setBackgroundDrawable(drawable);
    }


    @OnClick({R.id.ll_title_left_view, R.id.send, R.id.complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.send:
                sendCode();
                break;
            case R.id.complete:


                if (r_fflag.equals(Constant.FORGET)) {//忘记密码

//                    new UserTask(new HttpOnNextListener() {//校验验证码
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                            T.showShort(codeBean.getMessage());
//                            if (codeBean.getStatusCode() == Constant.SUCCESS) {
//                                SharePreferenceUtils.getBaseSharePreference().saveUserId(codeBean.getData());
//                                forgetPwd();//忘记密码，设置密码
//                            }
//
//                        }
//                        @Override
//                        public void onError(ApiException e) {
//                            T.showShort(e.getMessage());
//                        }
//                    }, this).checkVrfCode(mCountry[0], phoneNum, code.getText().toString().trim());
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            ForgetBean forgetBean = GsonUtils.parseJson(resulte, ForgetBean.class);
                            if (forgetBean.getStatusCode() == Constant.SUCCESS) {
//                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                                SharePreferenceUtils.getBaseSharePreference().saveToken(forgetBean.getToken());
                                SharePreferenceUtils.getBaseSharePreference().saveAccount(forgetBean.getData().getUserInfo().getPhone());
//                    startActivity(MCLoginActivity.class);
                                startActivity(MCLoginActivity.class);
//                                StartActivityManager.startRegisterCodeActivity(ForgetActivity3.this,nationalCode,phone,code.getText().toString().trim(),pwd.getText().toString().trim());

                            }
                            T.showShort(forgetBean.getMessage());
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                        }
                    }, this).resetPasswordWithVrf(code.getText().toString().trim(), nationalCode, phone, pwd.getText().toString());

                } else if (r_fflag.equals(Constant.REGISTER)) {//未注册
//                    new UserTask(new HttpOnNextListener() {
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            RegisterBean registerBean = GsonUtils.parseJson(resulte, RegisterBean.class);
//                            if (registerBean.getStatusCode() == Constant.SUCCESS) {
////                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
//                                SharePreferenceUtils.getBaseSharePreference().saveToken(registerBean.getToken());
//                                SharePreferenceUtils.getBaseSharePreference().saveAccount(registerBean.getData().getPhone());
////                    startActivity(MCLoginActivity.class);
//                                StartActivityManager.startRegisterCodeActivity(ForgetActivity3.this,nationalCode,phone,code.getText().toString().trim(),pwd.getText().toString().trim());
//
//                            }
//                            T.showShort(registerBean.getMessage());
//                        }
//
//                        @Override
//                        public void onError(ApiException e) {
//                            T.showShort(e.getMessage());
//                        }
//                    }, this).register(SharePreferenceUtils.getBaseSharePreference().readUserId(), "--1", pwd.getText().toString(), code.getText().toString().trim());

                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            Log.e("1234567890", resulte);
                             RegisterNewBeanNew registerNewBean = GsonUtils.parseJson(resulte, RegisterNewBeanNew.class);
                           //  RegisterNewBean registerNewBean = GsonUtils.parseJson(resulte, RegisterNewBean.class);
                           //  RegisterBean registerNewBean = GsonUtils.parseJson(resulte, RegisterBean.class);
                            if (registerNewBean.getStatusCode() == Constant.SUCCESS) {
//                              SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                                SharePreferenceUtils.getBaseSharePreference().saveToken(registerNewBean.getToken());
                               SharePreferenceUtils.getBaseSharePreference().saveUserId(registerNewBean.getData()+"");
                                SharePreferenceUtils.getBaseSharePreference().saveIMToken(registerNewBean.getRcToken());

//                               startActivity(MCLoginActivity.class);
//                                StartActivityManager.startRegisterCodeActivity(ForgetActivity3.this,nationalCode,phone,code.getText().toString().trim(),pwd.getText().toString().trim());

                                new UserTask(new HttpOnNextListener() {
                                    @Override
                                    public void onNext(String resulte, String method) {
                                        JSONObject jsonObject = null;
                                        try {
                                            jsonObject = new JSONObject(resulte);
                                            int statusCode = jsonObject.getInt("statusCode");

                                            Log.e("ccc",resulte);
                                            if (statusCode == Constant.SUCCESS) {
                                               //SharePreferenceUtils.getBaseSharePreference().saveAccount(registerNewBean.getData().get(0).getPhone());
                                               SharePreferenceUtils.getBaseSharePreference().saveAccount(phone);
                                               SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                                                Intent intent = new Intent(ForgetActivity3.this,MainActivity.class);
                                                intent.putExtra("type",1);
                                                hideInputMethod();
                                                startActivity(intent);
                                                T.showShort("注册成功");
                                                finish();
                                                SharePreferenceUtils.getBaseSharePreference().saveState(true);

                                            }else{
                                                T.showShort(jsonObject.getString("message"));
                                            }

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(ApiException e) {

                                        T.showShort(e.getMessage());
                                    }
                                }, ForgetActivity3.this).setInvitedCodes("");
                            }else{
                                T.showShort(registerNewBean.getMessage());
                            }
                          //  T;
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                         //   T.showShort("onError--------->"+e.getMessage());
                        }
                    }, this).checkVrfAndSetPassword(code.getText().toString().trim(),
                            nationalCode, phone, pwd.getText().toString().trim());


                    //在这里设置点击事件为账号验证码登录，并且设置 App.isLogin = true，表示是登录状态 ;
                } else if (r_fflag.equals(Constant.HAS_PWD_LOGIN)) {//注册过

                    initDataRegister();
                    SharePreferenceUtils.getBaseSharePreference().saveState(true);


                } else if (r_fflag.equals("login")) {

                    T.showLong("login");
                }

                break;

        }
    }

    private void sendCode() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                if (codeBean.getStatusCode() == Constant.SUCCESS) {
                    time.start();
                    T.showShort(codeBean.getMessage());
                } else if (codeBean.getStatusCode() == Constant.TIPS1) {
                    T.showShort(codeBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).forgetCode(mCountry[0], phone);
    }

    private void forgetPwd() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
                if (loginBean.getStatusCode() == 0) {
                    SharePreferenceUtils.getBaseSharePreference().saveAccount(phoneNum);
                    SharePreferenceUtils.getBaseSharePreference().savePassword(pwd.getText().toString());
                    T.showShort(getResources().getString(R.string.PasswordModificationSucceeded));
                    startActivity(MCLoginActivity.class);
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).resetPassword(phoneNum, pwd.getText().toString(), mCountry[0]);
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
//            send.setText(getResources().getString(R.string.resend2) + "(" + millisUntilFinished / 1000 + ")");
            send.setText(Html.fromHtml(StringUtil.setStrGray3(millisUntilFinished / 1000 + "s")));
        }

        @Override
        public void onFinish() {
            send.setText(getResources().getString(R.string.RegainValidationCode));
            send.setClickable(true);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_ForgetActivity3);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_ForgetActivity3);
        MobclickAgent.onPause(this);
    }
}
