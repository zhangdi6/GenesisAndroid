package com.iruiyou.pet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CrashAccountEntity;
import com.iruiyou.pet.bean.SendForSignupBean;
import com.iruiyou.pet.db.dao.CrashAccountDao;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：币全交易
 * 作者：sgf on 2018/9/21
 */
public class BqexTransactionActivity extends BaseActivity {

    public static void startAction(Activity activity,int accountType)
    {
        Intent intent=new Intent(activity, BqexTransactionActivity.class);
        intent.putExtra("accountType",accountType);
        activity.startActivity(intent);
    }

    public static final int REQUEST_CODE=3478;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.edit_account)
    EditText editAccount;

    @BindView(R.id.edit_name)
    EditText editTextName;

    @BindView(R.id.edit_bank_account)
    EditText editBankAccount;

    @BindView(R.id.edit_bank_name)
    EditText editBankName;

    @BindView(R.id.edit_bank_about_name)
    EditText editBankAboutName;

    @BindView(R.id.phoneNum)
    ClearEditText phoneNum;
    @BindView(R.id.complete)
    TextView complete;
    @BindView(R.id.countryTv)
    TextView countryTv;
    @BindView(R.id.countryCode)
    TextView countryCode;

    @BindView(R.id.relay_zhifubao)
    RelativeLayout relayoutZhifubao;

    @BindView(R.id.relay_bank)
    RelativeLayout relayBank;

    @BindView(R.id.llPhone)
    LinearLayout llPhone;

    @BindView(R.id.title_left)
    TextView titleLeft;

    @BindView(R.id.title_right)
    TextView titleRight;

    @BindView(R.id.title_center)
    TextView textCenter;



    @BindView(R.id.coursebackground_right)
    View coursebackgroundRight;

    @BindView(R.id.coursebackground_center)
    View coursebackgroundCenter;

    @BindView(R.id.coursebackground_left)
    View coursebackgroundLeft;

    /**
     * 账号类型
     * 1-支付宝
     */
    public static final int ACCOUNT_TYPE_ALIPAY=1;

    /**
     * 账号类型
     * 2-币全账号
     */
    public static final int ACCOUNT_TYPE_BIQUAN=2;

    /**
     * 账号类型
     * 3-银行账号
     */
    public static final int ACCOUNT_TYPE_BANK=3;


    private int accountType=ACCOUNT_TYPE_ALIPAY;

    private String mCountry[] = {"86"};
    private int num = 0;
    private Context context;
    @Override
    public int getLayout() {
        return R.layout.activity_bqex_transaction;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if(intent!=null)
        {
            accountType=intent.getIntExtra("accountType",-1);
        }

        context = BqexTransactionActivity.this;
        complete.setEnabled(false);
//        time = new TimeCount(60000, 1000);
        initData();
        View clickView=null;
        CrashAccountEntity crashAccountEntity=new CrashAccountDao().getCrashAccountByUid(SharePreferenceUtils.
                getBaseSharePreference().readUserId(),accountType);
        switch (accountType)
        {
            case ACCOUNT_TYPE_ALIPAY:
                textTitle.setText(R.string.alipay_account);
                clickView=titleLeft;
                if(crashAccountEntity!=null)
                {
                    editAccount.setText(crashAccountEntity.getAccount());
                    editTextName.setText(crashAccountEntity.getAccountName());
                }
                break;
            case ACCOUNT_TYPE_BIQUAN:
                textTitle.setText(R.string.biquan_account);
                clickView=titleRight;
                if(crashAccountEntity!=null)
                {
                    phoneNum.setText(crashAccountEntity.getAccount());
                    countryCode.setText(crashAccountEntity.getCountryCode());
                }
                break;
            case ACCOUNT_TYPE_BANK:
                textTitle.setText(R.string.bank_account);
                clickView=textCenter;
                if(crashAccountEntity!=null){
                    editBankAccount.setText(crashAccountEntity.getAccount());
                    editBankAboutName.setText(crashAccountEntity.getAccountName());
                    editBankName.setText(crashAccountEntity.getBankName());
                }
                break;
        }
        if(clickView!=null)
        {
            onViewClicked(clickView);
        }
    }

    private void initData() {
        phoneNum.setSelection(phoneNum.getText().length());
        phoneNum.addTextChangedListener(editWatcher);
        SoftKeyboardUtils.setEditTextState(phoneNum);
        editAccount.addTextChangedListener(editWatcher);
        editBankAccount.addTextChangedListener(editWatcher);
        editBankAboutName.addTextChangedListener(editWatcher);
        editBankName.addTextChangedListener(editWatcher);
        editTextName.addTextChangedListener(editWatcher);
    }

    /**
     * 手机号码监听
     */
    private TextWatcher editWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            String account,name;
            switch (accountType)
            {
                case ACCOUNT_TYPE_ALIPAY:
                    account= editAccount.getText().toString().trim();
                    name=editTextName.getText().toString().trim();
                    if(StringUtil.isNotEmpty(account)&& StringUtil.isNotEmpty(name))
                    {
                        setLoginBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                    else
                    {
                        setLoginBackground(false, R.drawable.bt_psw_details_shape);
                    }
                    break;
                case ACCOUNT_TYPE_BIQUAN:
                    account=phoneNum.getText().toString().trim();
                    if(StringUtil.isNotEmpty(account))
                    {
                        setLoginBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                    else
                    {
                        setLoginBackground(false, R.drawable.bt_psw_details_shape);
                    }
                    break;
                case ACCOUNT_TYPE_BANK:
                    account=editBankAccount.getText().toString().trim();
                    name=editBankAboutName.getText().toString().trim();
                    String bankName=editBankName.getText().toString().trim();
                    if(StringUtil.isNotEmpty(account)&& StringUtil.isNotEmpty(name)&& StringUtil.isNotEmpty(bankName))
                    {
                        setLoginBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                    else
                    {
                        setLoginBackground(false, R.drawable.bt_psw_details_shape);
                    }
                    break;
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
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        complete.setBackground(drawable);
    }

    @OnClick({ R.id.complete, R.id.countryCode, R.id.title_left, R.id.title_right, R.id.image_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.title_left:
//                accountType=ACCOUNT_TYPE_ALIPAY;
                titleLeft.setTextColor(getResources().getColor(R.color._333333));
                titleRight.setTextColor(getResources().getColor(R.color._999999));
                relayoutZhifubao.setVisibility(View.VISIBLE);
                llPhone.setVisibility(View.GONE);
                relayBank.setVisibility(View.GONE);
                coursebackgroundRight.setVisibility(View.INVISIBLE);
                coursebackgroundLeft.setVisibility(View.VISIBLE);
                coursebackgroundCenter.setVisibility(View.INVISIBLE);
                break;
            case R.id.title_right:
//                accountType=ACCOUNT_TYPE_BIQUAN;
                titleRight.setTextColor(getResources().getColor(R.color._333333));
                titleLeft.setTextColor(getResources().getColor(R.color._999999));
                llPhone.setVisibility(View.VISIBLE);
                relayoutZhifubao.setVisibility(View.GONE);
                relayBank.setVisibility(View.GONE);
                coursebackgroundLeft.setVisibility(View.INVISIBLE);
                coursebackgroundRight.setVisibility(View.VISIBLE);
                coursebackgroundCenter.setVisibility(View.INVISIBLE);
                break;
            case R.id.title_center:
//                accountType=ACCOUNT_TYPE_BANK;
                titleLeft.setTextColor(getResources().getColor(R.color._333333));
                titleRight.setTextColor(getResources().getColor(R.color._999999));
                relayoutZhifubao.setVisibility(View.GONE);
                llPhone.setVisibility(View.GONE);
                relayBank.setVisibility(View.VISIBLE);
                coursebackgroundRight.setVisibility(View.INVISIBLE);
                coursebackgroundLeft.setVisibility(View.INVISIBLE);
                coursebackgroundCenter.setVisibility(View.VISIBLE);
                break;
            case R.id.complete:
                switch (accountType)
                {
                    case ACCOUNT_TYPE_BANK:
                        if(StringUtil.isEmpty(editBankAccount.getText().toString().trim())){
                            T.showShort("请输入银行卡账号!");
                            return;
                        }
                        if(StringUtil.isEmpty(editBankAboutName.getText().toString().trim())){
                            T.showShort("请输入开户人姓名!");
                            return;
                        }
                        if(StringUtil.isEmpty(editBankName.getText().toString().trim())){
                            T.showShort("请输入开行名称!");
                            return;
                        }
                        if(accountType>0){
                            CrashAccountEntity crashAccountEntity=new CrashAccountEntity();
                            CrashAccountDao dao=new CrashAccountDao();
                            dao.delCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
                            crashAccountEntity.setUserId(SharePreferenceUtils.getBaseSharePreference().readUserId());
                            Intent intent=new Intent();
                            SharePreferenceUtils.getBaseSharePreference().saveAliPayAccount(editBankAccount.getText().toString().trim());
                            intent.putExtra(SharePreferenceUtils.ALIPAY_ACCOUNT,editBankAccount.getText().toString().trim());
                            intent.putExtra(SharePreferenceUtils.ACCOUNT_TYPE,accountType);
                            crashAccountEntity.setAccountType(accountType);
                            crashAccountEntity.setAccount(editBankAccount.getText().toString().trim());
                            crashAccountEntity.setAccountName(editBankAboutName.getText().toString().trim());
                            crashAccountEntity.setBankName(editBankName.getText().toString().trim());
                            SharePreferenceUtils.getBaseSharePreference().saveTiXianAccountType(accountType);
                            dao.insert(crashAccountEntity);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                        break;
                    case ACCOUNT_TYPE_ALIPAY:
                        if(!TextUtils.isEmpty(editAccount.getText().toString().trim()))
                        {
                            CrashAccountEntity crashAccountEntity=new CrashAccountEntity();
                            CrashAccountDao dao=new CrashAccountDao();
                            dao.delCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
                            crashAccountEntity.setUserId(SharePreferenceUtils.getBaseSharePreference().readUserId());
                            Intent intent=new Intent();
                            SharePreferenceUtils.getBaseSharePreference().saveAliPayAccount(editAccount.getText().toString().trim());
                            intent.putExtra(SharePreferenceUtils.ALIPAY_ACCOUNT,editAccount.getText().toString().trim());
                            intent.putExtra(SharePreferenceUtils.ACCOUNT_TYPE,accountType);
                            crashAccountEntity.setAccountType(accountType);
                            crashAccountEntity.setAccount(editAccount.getText().toString().trim());
                            if(!TextUtils.isEmpty(editTextName.getText().toString().trim()))
                            {
                                SharePreferenceUtils.getBaseSharePreference().saveAlipayUserName(editTextName.getText().toString().trim());
                                intent.putExtra(SharePreferenceUtils.ALIPAY_NAME,editTextName.getText().toString().trim());
                                crashAccountEntity.setAccountName(editTextName.getText().toString().trim());
                            }
                            SharePreferenceUtils.getBaseSharePreference().saveTiXianAccountType(accountType);
                            dao.insert(crashAccountEntity);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                        else
                        {
                            T.showShort("请输入支付宝账号!");
                        }
                        break;
                    case ACCOUNT_TYPE_BIQUAN:
                        if(!TextUtils.isEmpty(phoneNum.getText().toString().trim()))
                        {
                            CrashAccountEntity crashAccountEntity=new CrashAccountEntity();
                            CrashAccountDao dao=new CrashAccountDao();
                            dao.delCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
                            crashAccountEntity.setUserId(SharePreferenceUtils.getBaseSharePreference().readUserId());
                            crashAccountEntity.setAccountType(accountType);
                            crashAccountEntity.setCountryCode(countryCode.getText().toString().trim().substring(1));
                            crashAccountEntity.setAccount(phoneNum.getText().toString().trim());
                            dao.insert(crashAccountEntity);
                            Intent intent=new Intent();
                            intent.putExtra(SharePreferenceUtils.ACCOUNT_TYPE,accountType);
                            SharePreferenceUtils.getBaseSharePreference().saveBqPhone(phoneNum.getText().toString().trim());
                            intent.putExtra(SharePreferenceUtils.BQ_PHONE,phoneNum.getText().toString().trim());
                            SharePreferenceUtils.getBaseSharePreference().saveBqCode(countryCode.getText().toString().trim().substring(1));
                            intent.putExtra(SharePreferenceUtils.BQ_CODE,countryCode.getText().toString().trim().substring(1));
                            SharePreferenceUtils.getBaseSharePreference().saveTiXianAccountType(accountType);
                            setResult(Activity.RESULT_OK,intent);
                            finish();
                        }
                        else
                        {
                            T.showShort("请输入手机号!");
                        }
                        break;
                }
                break;

            case R.id.countryCode:
                DialogUtils.countrySelect(this, countryCode, countryTv, mCountry);
                break;
        }
    }

    /**
     * 注册时判断手机号码是否注册
     */
    private void distinguishCode() {
        //发送短信验证码
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                if (codeBean.getStatusCode() == Constant.SUCCESS) {
////                    startActivity(RegisterActivity3.class);
//                    StartActivityManager.startRegisterActivity3(context,phoneNum.getText().toString().trim(),countryCode.getText().toString().trim().substring(1));
//                }else {
//                    T.showShort(codeBean.getMessage());
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                T.showShort(e.getMessage());
//            }
//        }, this).sendVrfCode(mCountry[0], phoneNum.getText().toString().trim());
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                SendForSignupBean sendForSignupBean = GsonUtils.parseJson(resulte, SendForSignupBean.class);
                T.showShort(sendForSignupBean.getMessage());
                if (sendForSignupBean.getStatusCode() == Constant.SUCCESS) {
//                    time.start();
                    T.showShort(sendForSignupBean.getMessage());
                    StartActivityManager.startForgetActivity3(context, Constant.REGISTER, countryCode.getText().toString().trim().substring(1), phoneNum.getText().toString().trim());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).sendForSignup(mCountry[0], phoneNum.getText().toString().trim());
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
//                T.showShort(codeBean.getMessage());
//                if (codeBean.getStatusCode() == Constant.SUCCESS) {
////                    time.start();
//                    T.showShort(codeBean.getMessage());
//                    StartActivityManager.startForgetActivity3(context, Constant.FORGET,countryCode.getText().toString().trim().substring(1));
//                }else if(codeBean.getStatusCode() == Constant.TIPS1){
//
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                T.showShort(e.getMessage());
//            }
//        }, this).forgetCode(mCountry[0], phoneNum.getText().toString().trim());
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                SendForSignupBean sendForSignupBean = GsonUtils.parseJson(resulte, SendForSignupBean.class);
                T.showShort(sendForSignupBean.getMessage());
                if (sendForSignupBean.getStatusCode() == Constant.SUCCESS) {
//                    time.start();
                    T.showShort(sendForSignupBean.getMessage());
                    StartActivityManager.startForgetActivity3(context, Constant.FORGET, countryCode.getText().toString().trim().substring(1), phoneNum.getText().toString().trim());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).sendForForget(mCountry[0], phoneNum.getText().toString().trim());
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
//            complete.setClickable(false);
            num = 1;
        }

        @Override
        public void onFinish() {
//            complete.setClickable(true);
            num = 0;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_ForgetActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_ForgetActivity);
        MobclickAgent.onPause(this);
    }
}
