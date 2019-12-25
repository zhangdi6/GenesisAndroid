package com.iruiyou.pet.activity.registered;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.RegisterLastActivity2;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.SelfDidalog;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:注册填写邀请码
 * 创建日期:2018/8/27 on 16:21
 * 作者:sgf
 */
public class RegisterCodeActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.tvRegisterComplete)
    TextView tvRegisterComplete;
    @BindView(R.id.edRegisterCode)
    TextInputEditText edRegisterCode;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.llTips)
    LinearLayout llTips;
    private String nationalCode;
    private String phone;
    private String smsCode;
    private String password;
    private Context context;
    private ConfigBean configBean;
    private ACache aCache;

    @Override
    public int getLayout() {
        return R.layout.activity_registered_code;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        aCache = ACache.get(this);
        context = RegisterCodeActivity.this;
//        tvRegisterComplete.setEnabled(false);
        nationalCode = getIntent().getStringExtra("nationalCode");
        phone = getIntent().getStringExtra("phone");
        smsCode = getIntent().getStringExtra("smsCode");
        password = getIntent().getStringExtra("password");
        titleNameText.setText(getResources().getString(R.string.register22));
        //获取缓存的数据-职业
        configBean = App.getConfigBean();
//        if ((configBean != null) && (configBean.getData() != null) && (configBean.getData().isInvitationConfig())) {//InvitationConfig为true表示邀请码必填
//            hideRightBg();//隐藏
//            llTips.setVisibility(View.GONE);
//        } else {
//            showRightBg();//显示背景按钮
//            setRightText(getResources().getString(R.string.skip), "#333333");
//            setRightBg(R.drawable.bg_edit);
//            llTips.setVisibility(View.VISIBLE);
//        }

        showRightBg();//显示背景按钮
        setRightText(getResources().getString(R.string.skip), "#333333");
        setRightBg(R.drawable.bg_edit);
        llTips.setVisibility(View.VISIBLE);
        setRightViewListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterLastActivity2.class);
            }
        });
//        edRegisterCode.addTextChangedListener(codeWatcher);
        setLoginBackground(true, R.drawable.bt_psw_details_shape2);
    }

    /**
     * 手机号码监听
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
            if (editable.toString().length() > 0) {
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
        tvRegisterComplete.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        tvRegisterComplete.setBackgroundDrawable(drawable);
    }

    @OnClick({R.id.ll_title_left_view, R.id.tvRegisterComplete, R.id.title_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.tvRegisterComplete:
                if (StringUtil.isEmpty(edRegisterCode.getText().toString().trim())) {
                    int noStrs = R.string.cancel;
                    int yesStrs = R.string.set3;
                    SelfDidalog selfDidalog = new SelfDidalog(RegisterCodeActivity.this, getString(R.string.prompt), getString(R.string.invite_confirm), true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {

                        @Override
                        public void onYesClick() {
                            //网络请求邀请码
                            setInvitedCode();
                        }

                        @Override
                        public void onNoClick() {

                        }
                    });
                    selfDidalog.show();
                } else {
                    //网络请求邀请码
                    setInvitedCode();
                }

                break;
            case R.id.title_right_text://跳过
                StartActivityManager.startRegisterLastActivity2(context, nationalCode, phone, smsCode, password);
                break;
        }
    }

    /**
     * 网络请求邀请码
     */
    private void setInvitedCode() {
        DialogUtil.getInstance().showLoadingDialog(this);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
//                SetInvitedCodeBean setInvitedCodeBean = GsonUtils.parseJson(resulte, SetInvitedCodeBean.class);
                LoginNewBean setInvitedCodeBean = GsonUtils.parseJson(resulte, LoginNewBean.class);
                T.showShort(setInvitedCodeBean.getMessage());
                //invitedCode
                if (setInvitedCodeBean.getStatusCode() == Constant.SUCCESS && setInvitedCodeBean.getData().getUserInfo().getInviteCount() == Constant.SUCCESS) {
                    aCache.put(TagConstants.loginfig, setInvitedCodeBean);
//                    SharePreferenceUtils.getBaseSharePreference().saveShowEdit(setInvitedCodeBean.getData().getBasicInfo().getShowEdit());
//                    SharePreferenceUtils.getBaseSharePreference().saveShowEdit(setInvitedCodeBean.getData().getBasicInfo().getShowEdit());
////                    SharePreferenceUtils.getBaseSharePreference().saveToken(setInvitedCodeBean.getToken());
                    SharePreferenceUtils.getBaseSharePreference().saveAccount(setInvitedCodeBean.getData().getUserInfo().getPhone());
                    SharePreferenceUtils.getBaseSharePreference().saveIMToken(setInvitedCodeBean.getRcToken());
                    SharePreferenceUtils.getBaseSharePreference().saveToken(setInvitedCodeBean.getToken());
                    SharePreferenceUtils.getBaseSharePreference().saveBasicId(setInvitedCodeBean.getData().getBasicInfo().get_id());//用户资料id
                    SharePreferenceUtils.getBaseSharePreference().saveUserId(setInvitedCodeBean.getData().getUserInfo().get_id());//用户id
                    SharePreferenceUtils.getBaseSharePreference().saveShowEdit(setInvitedCodeBean.getData().getBasicInfo().getShowEdit());//用户id

//                    SharePreferenceUtils.getBaseSharePreference().saveInviteCode(setInvitedCodeBean.getData().getUserInfo().getInviteCode());
////                    SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
////                    SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
//                    SharePreferenceUtils.getBaseSharePreference().saveNickName(setInvitedCodeBean.getData().getRealName());
////                            aCache.put(TagConstants.LoginTag, loginBean);
////                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
//                    SharePreferenceUtils.getBaseSharePreference().saveUserId(setInvitedCodeBean.getData().getBasicInfo().get_id());
////                    String s = SharePreferenceUtils.getBaseSharePreference().readToken();
//                    SharePreferenceUtils.getBaseSharePreference().saveCountryCode(setInvitedCodeBean.getData().getUserInfo().getCountryCode());
                    //注：basicInfo.showEdit < config.minShowEdit进入必填资料编辑页
                    //  >=进首页
                    if (setInvitedCodeBean.getData().getBasicInfo().getShowEdit() < configBean.getData().getMinShowEdit()) {
                        StartActivityManager.startRegisterLastActivity2(context, nationalCode, phone, smsCode, password, edRegisterCode.getText().toString());
                        finish();
                    } else {
                        startActivity(MainActivity.class);
                        finish();
                    }
                }
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
                T.showShort(e.getMessage());
            }
        }, this).setInvitedCodes(edRegisterCode.getText().toString().trim());
    }

}
