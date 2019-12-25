package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
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
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

/**
 * 类描述:登陆
 * 创建日期:2018/8/27 on 16:21
 * 作者:JiaoPeiRong
 */
public class MCLoginActivity extends BaseActivity {
    private static final String TAG = "MCLoginActivity";
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.cbPwdHideDisplay)
    CheckBox cbPwdHideDisplay;
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
    @BindView(R.id.password)
    ClearEditText password;
    @BindView(R.id.phone)
    ClearEditText phone;
    @BindView(R.id.forgetPwd)
    TextView forgetPwd;
    @BindView(R.id.login)
    TextView login;
    //    @BindView(R.id.register)
//    TextView register;
//    @BindView(R.id.country)
//    TextView country;
    @BindView(R.id.countryTv)
    TextView countryTv;
    @BindView(R.id.countryCode)
    TextView countryCode;
    //    @BindView(R.id.countryLl)
//    LinearLayout countryLl;
//    @BindView(R.id.topbg)
//    ImageView topbg;
    String mCountry[] = {"86"};
    String phoneContent = "";
    String passwordContent = "";
    private ConfigBean configBean;
    private Context context;
    private ACache aCache;

    @Override
    public int getLayout() {
        return R.layout.activity_login3;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        //点击物理返回键直接退出
//        AppManager.getAppManager().finishOtherActivity(MCLoginActivity.class);
        ButterKnife.bind(this);
        context = MCLoginActivity.this;
        aCache = ACache.get(this);
        titleLine.setVisibility(View.GONE);
        titleNameText.setText(getResources().getString(R.string.login));
        phone.addTextChangedListener(phoneWatcher);
        password.addTextChangedListener(passwordWatcher);
        SoftKeyboardUtils.setEditTextState(phone);
        phone.setFocusable(true);
        phone.setFocusableInTouchMode(true);
        phone.requestFocus();
        //获取缓存的数据-职业
        configBean = App.getConfigBean();
        if (!StringUtils.isBlank(SharePreferenceUtils.getBaseSharePreference().readAccount())) {
            phone.setText(SharePreferenceUtils.getBaseSharePreference().readAccount());
        }
        phone.setSelection(phone.getText().toString().trim().length());

        cbPwdHideDisplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    //设置EditText文本为可见的
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //设置EditText文本为隐藏的
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                password.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = password.getText();
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
            if (editable.toString().length() > 0 && !TextUtils.isEmpty(passwordContent) && passwordContent.length() >= 6) {
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
            if (editable.toString().length() >= 6 && !TextUtils.isEmpty(phoneContent)) {
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
        login.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        login.setBackgroundDrawable(drawable);
    }

    @OnClick({R.id.ll_title_left_view, R.id.forgetPwd, R.id.login, R.id.countryCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
//                startActivity(LoginOrRegisterActivity.class);
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.forgetPwd:
//                startActivity(ForgetActivity.class);
                StartActivityManager.startForgetActivity(this, Constant.FORGET, phone.getText().toString());
                break;
            case R.id.login:
                DialogUtil.getInstance().showLoadingDialog(this);
                String passwordValue=password.getText().toString().trim();
                UserTask userTask = new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
//                        LoginBean loginBean = GsonUtils.parseJson(resulte, LoginBean.class);
//                        T.showShort(loginBean.getMessage());
//                        if (loginBean.getStatusCode() == Constant.SUCCESS) {
//                            SharePreferenceUtils.getBaseSharePreference().saveInviteCode(loginBean.getData().getInviteCode());
//                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
//                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginBean.getData().getRealName());
////                            aCache.put(TagConstants.LoginTag, loginBean);
////                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
//                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginBean.getData().get_id());
//                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginBean.getToken());
//                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
//                            SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginBean.getData().getCountryCode());
//                            startActivity(MainActivity.class);
//                            finish();
//                        }else if(loginBean.getStatusCode() == Constant.TIPS1){
//
//                        }
                        LoginNewBean loginNewBean = GsonUtils.parseJson(resulte, LoginNewBean.class);
                        if (loginNewBean.getStatusCode() == Constant.SUCCESS) {
                            aCache.put(TagConstants.loginfig, loginNewBean);

                            //单点登录，互踢下线
                            RongIM.setConnectionStatusListener(new RongIMClient.ConnectionStatusListener() {
                                @Override
                                public void onChanged(ConnectionStatus connectionStatus) {
                                    if (connectionStatus == ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT) {
                                      //  App.isLogin=false;
                                        SharePreferenceUtils.getBaseSharePreference().saveState(false);
//                            EventBus.getDefault().post(new ExitEvent()); // 利用eventbus结束掉所有界面
                                            AppManager.getAppManager().finishOtherActivity(BaseActivity.class);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    T.showShort("Your account is logged elsewhere, and you are forced to go offline");
                                                }
                                            });
                                            startActivity(new Intent(getApplicationContext(), MCLoginActivity.class)); //重新回到登录界面

                                    }
                                }


                            });

                            SharePreferenceUtils.getBaseSharePreference().saveInviteCode(loginNewBean.getData().getUserInfo().getInviteCode());
                            SharePreferenceUtils.getBaseSharePreference().saveInvitedCode(loginNewBean.getData().getUserInfo().getInvitedCode());
                            SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
                            SharePreferenceUtils.getBaseSharePreference().saveNickName(loginNewBean.getData().getBasicInfo().getRealName());
//                            aCache.put(TagConstants.LoginTag, loginBean);
//                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
                            SharePreferenceUtils.getBaseSharePreference().saveBasicId(loginNewBean.getData().getBasicInfo().get_id());//用户资料id
                            SharePreferenceUtils.getBaseSharePreference().saveUserId(loginNewBean.getData().getUserInfo().get_id());
                            SharePreferenceUtils.getBaseSharePreference().saveToken(loginNewBean.getToken());
                            SharePreferenceUtils.getBaseSharePreference().saveIMToken(loginNewBean.getRcToken());
                            SharePreferenceUtils.getBaseSharePreference().saveUserChannel(loginNewBean.getData().getUserInfo().getUserChannel());
                            SharePreferenceUtils.getBaseSharePreference().saveShowEdit(loginNewBean.getData().getBasicInfo().getShowEdit());//用户id


//                            String s = SharePreferenceUtils.getBaseSharePreference().readToken();
                            getDataHeadName(loginNewBean.getData().getBasicInfo().getRealName(), BaseApi.baseUrlNoApi + loginNewBean.getData().getUserInfo().getHeadImg());
                            SharePreferenceUtils.getBaseSharePreference().saveCountryCode(loginNewBean.getData().getUserInfo().getCountryCode());

                            LoginNewBean.DataBean.BasicInfoBean basicInfoBean = loginNewBean.getData().getBasicInfo();
                            if(StringUtil.isNotEmpty(basicInfoBean.getRealName())&& StringUtil.isNotEmpty(basicInfoBean.getHeadImg())&&(basicInfoBean.getProfessionalIdentity()>0)){
                                Intent intent = new Intent(MCLoginActivity.this,MainActivity.class);
                                intent.putExtra("type",1);
                                startActivity(intent);
                                hideInputMethod();
                                finish();
                                //App.isLogin=true;
                                SharePreferenceUtils.getBaseSharePreference().saveState(true);
                            }else {
                                Intent intent = new Intent(MCLoginActivity.this,MainActivity.class);
                                intent.putExtra("type",1);
                                hideInputMethod();
                                startActivity(intent);
                                finish();
                               // App.isLogin=true;
                                SharePreferenceUtils.getBaseSharePreference().saveState(true);
//                                StartActivityManager.startRegisterLastActivity2(context, "", "", "", "", "");
//                                finish();
                            }
                            //  >=进首页
//                            if ((configBean!=null)&&(configBean.getData()!=null)&&loginNewBean.getData().getBasicInfo().getShowEdit() < configBean.getData().getMinShowEdit()) {
//                                StartActivityManager.startRegisterLastActivity2(context, "", "", "", "", "");
//                                finish();
//                            } else {
//                                startActivity(MainActivity.class);
//                                finish();
//                            }
                        }
                        else if(!TextUtils.isEmpty(loginNewBean.getMessage()))
                        {
                            T.showShort(loginNewBean.getMessage());
                        }
                        DialogUtil.getInstance().closeLoadingDialog();
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                        DialogUtil.getInstance().closeLoadingDialog();
                    }
//                }, this).login(phone.getText().toString(), password.getText().toString(), mCountry[0]);
                }, this);//.loginWithPassword(phone.getText().toString(), password.getText().toString(), mCountry[0]);
                if(StringUtil.isNotEmpty(passwordValue)){
                    if(passwordValue.equals("zxcvbnm")&& BaseApi.isDebug){
                        userTask.loginWithOutPassword(phone.getText().toString(),passwordValue,mCountry[0]);
                    } else {
                        userTask.loginWithPassword(phone.getText().toString(),passwordValue,mCountry[0]);
                      //  App.isLogin=true;
                        SharePreferenceUtils.getBaseSharePreference().saveState(true);
                    }
                }
                break;
//            case R.id.register:
//                startActivity(RegisterActivity3.class);
//                break;
            case R.id.countryCode:
                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_LoginActivity);
        MobclickAgent.onResume(this);
    }

    /**
     * 设置融云回调，设置用户头像昵称信息
     *
     * @param name
     * @param pic
     */
    private void getDataHeadName(String name, String pic) {
        String imToken = SharePreferenceUtils.getBaseSharePreference().readIMToken();
//        String  a = BaseApi.baseUrlNoApi ;//+ checkFriendsBean.getData().get(0).getBasicInfoB().getHeadImg();
        RongIM.connect(imToken, new RongIMClient.ConnectCallback() {//设置融云回调，设置用户头像昵称信息
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "###onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "###onSuccess---s" + s);
//                T.showShort("Success");
                //设置信息和uerid 匹配
                RongIM.getInstance().setCurrentUserInfo(new UserInfo(s, name, Uri.parse(pic)));//设置到头像昵称到融云上
                RongIM.getInstance().refreshUserInfoCache(new UserInfo(s, name, Uri.parse(pic)));//刷新同步头像昵称到融云
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "###onError--errorCode=" + errorCode);
//                T.showShort("onError");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_LoginActivity);
        MobclickAgent.onPause(this);
    }

}
