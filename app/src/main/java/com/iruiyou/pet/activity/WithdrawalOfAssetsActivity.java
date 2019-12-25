package com.iruiyou.pet.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CrashAccountEntity;
import com.iruiyou.pet.bean.DrawPbsBean;
import com.iruiyou.pet.bean.SendForDrawToBqexBean;
import com.iruiyou.pet.db.dao.CrashAccountDao;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-提取资产
 * 作者：sgf
 */
public class WithdrawalOfAssetsActivity extends BaseActivity {
    @BindView(R.id.linear_tatol)
    LinearLayout linear_tatol;
    @BindView(R.id.tv_crash_name)
    TextView tv_crash_name;
    @BindView(R.id.text_tixian_time)
    TextView tixianTime;
    @BindView(R.id.text_tixian_name)
    TextView tixianName;
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
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.et_money_withdrawal_address)
    ClearEditText et_money_withdrawal_address;
    @BindView(R.id.et_quantity_extracted)
    EditText et_quantity_extracted;
    @BindView(R.id.tv_withdrawal_all)
    TextView tv_withdrawal_all;
    @BindView(R.id.bt_withdrawal_confirm)
    Button bt_withdrawal_confirm;
    @BindView(R.id.tv_withdrawal_available)
    TextView tv_withdrawal_available;
    @BindView(R.id.tv_withdrawal_service_charge)
    TextView tv_withdrawal_service_charge;
    @BindView(R.id.tv_actual_achievement_quantity)
    TextView tv_actual_achievement_quantity;
    @BindView(R.id.ll_balance_assets)
    LinearLayout ll_balance_assets;
    @BindView(R.id.tv_balance_assets)
    TextView tv_balance_assets;
    @BindView(R.id.ll_fixed_assets)
    LinearLayout ll_fixed_assets;
    @BindView(R.id.tv_fixed_assets)
    TextView tv_fixed_assets;
    @BindView(R.id.tv_transfer_accounts)
    TextView tv_transfer_accounts;
    @BindView(R.id.tv_transfer_accounts_one)
    TextView tv_transfer_accounts_one;
    @BindView(R.id.tv_transfer_accounts_blue_one)
    TextView tv_transfer_accounts_blue_one;
    @BindView(R.id.tv_usdt_two)
    TextView tv_usdt_two;
    @BindView(R.id.tv_usdt_blue_two)
    TextView tv_usdt_blue_two;
    @BindView(R.id.tv_bqex)
    TextView tv_bqex;
    @BindView(R.id.tv_currency_type)
    TextView tv_currency_type;
    @BindView(R.id.ll_usdt_two)
    LinearLayout ll_usdt_two;
    @BindView(R.id.image_logo)
    ImageView imageLogo;
    @BindView(R.id.title_right_text2)
    TextView title_right_text2;
    @BindView(R.id.text_notice)
    TextView textNotice;
    private Context context;
    private String pbsAmount;
    private String hatchAmount;
    private String usdtPbs;
    private String rebateCrystal;
    private String withdrawalAddress;
    private String quantityExtracted;
    private double quantityExtracted_Double;
    private double pbsAmount_double = 0.0;
    private int balanceType = 0;
    private int currencyType = 0;
    private String readBqCode;
    private String readBqPhone;
    private PopupWindow mPopWindow;
    private TextView send;
    private TimeCount time;
    private int drawToBqex;//接口请求类型type(0余额、1定存、2USDT）
    private String usdCNY;
    private String userChannle;
//    private int crashType;
    private CrashAccountDao crashAccountDao;
    private int accountType;
    @Override
    public int getLayout() {
        return R.layout.activity_withdrawal_of_assets;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.withdrawal_of_assets));
        title_right_text2.setText(getResources().getText(R.string.withdrawal_records));
        title_right_text2.setTextColor(getResources().getColor(R.color._26c68a));
//        title_right_text2.setVisibility(View.GONE);
        context = WithdrawalOfAssetsActivity.this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initDta();
        getRefresh();

    }

    private void initDta() {
        crashAccountDao=new CrashAccountDao();
        Intent intent=getIntent();
        accountType=intent.getIntExtra("accountType",-1);
        pbsAmount = getIntent().getStringExtra("pbsAmount");
        hatchAmount = getIntent().getStringExtra("fixedAssetsAmount");
        usdtPbs = getIntent().getStringExtra("usdtPbs");
        rebateCrystal = getIntent().getStringExtra("rebateCrystal");
        usdCNY = getIntent().getStringExtra("UsdCNY");
        userChannle = SharePreferenceUtils.getBaseSharePreference().readUserChannel();//用户渠道
        readBqCode = SharePreferenceUtils.getBaseSharePreference().readBqCode();
        readBqPhone = SharePreferenceUtils.getBaseSharePreference().readBqPhone();
//        if(TextUtils.isEmpty(readBqPhone)){
//            tv_bqex.setText(getString(R.string.bqex,getString(R.string.bqex_tips)));
//        }else {
//            tv_bqex.setText(getString(R.string.bqex, readBqPhone));
//        }
        et_quantity_extracted.addTextChangedListener(quantityExtractedWatcher);
//        et_money_withdrawal_address.addTextChangedListener(withdrawalAddressWatcher);
        if (!TextUtils.isEmpty(pbsAmount)) {
            pbsAmount_double = Double.parseDouble(pbsAmount);
            tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
        }
        String hintStr = getResources().getString(R.string.str_crystals_number);
        switch (accountType)
        {
            case BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY:
                tixianName.setText(R.string.str_tixian_zhifubao);
                tv_bqex.setText(R.string.input_account_zhifubao_str);
                imageLogo.setImageResource(R.drawable.icon_zhifbao);
//                tixianTime.setText("24小时内到账");
                onViewClicked(tv_usdt_two);
                linear_tatol.setVisibility(View.GONE);
                textNotice.setText(getResources().getString(R.string.crash_select_zhifubao));
                break;
            case BqexTransactionActivity.ACCOUNT_TYPE_BANK:
                tixianName.setText(getResources().getString(R.string.str_tixian_bank));
//                tixianTime.setText("24小时内到账");
                onViewClicked(tv_usdt_two);
                linear_tatol.setVisibility(View.GONE);
                textNotice.setText(getResources().getString(R.string.crash_select_bank));
                break;
            case BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN:
                tixianName.setText(getResources().getString(R.string.str_tixian_biquan));
//                tixianTime.setText("10分钟内到账");
                tv_crash_name.setText("PBS");
                onViewClicked(tv_transfer_accounts_one);
                textNotice.setText(R.string.str_crash_pbs);
                hintStr = getResources().getString(R.string.quantity_input_tips);
                break;
        }


        SpannableString ss =  new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(12, true);
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        et_quantity_extracted.setHint(new SpannedString(ss));

        CrashAccountEntity crashAccountEntity= crashAccountDao.getCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
        if(crashAccountEntity!=null)
        {
            switch (crashAccountEntity.getAccountType())
            {
                case  BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY:
                    imageLogo.setImageResource(R.drawable.icon_zhifbao);
                    tv_bqex.setText(crashAccountEntity.getAccount());
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN:
                    imageLogo.setImageResource(R.drawable.bqex_icon);
                    tv_bqex.setText(crashAccountEntity.getAccount());
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BANK:
                    imageLogo.setImageResource(R.drawable.icon_bank);
                    tv_bqex.setText(crashAccountEntity.getAccount());
                    break;
                default:
                    imageLogo.setVisibility(View.GONE);
                    break;
            }
        }
        else
        {
            switch (accountType)
            {
                case  BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY:
                    imageLogo.setImageResource(R.drawable.icon_zhifbao);
                    tv_bqex.setText(getResources().getString(R.string.input_account_zhifubao_str));
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN:
                    imageLogo.setImageResource(R.drawable.bqex_icon);
                    tv_bqex.setText(getResources().getString(R.string.input_account_str));
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BANK:
                    imageLogo.setImageResource(R.drawable.icon_bank);
                    tv_bqex.setText(getResources().getString(R.string.input_account_bank_str));
                    break;
                default:
                    imageLogo.setVisibility(View.GONE);
                    break;
            }
        }
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK)
        {
            switch (requestCode)
            {
                case BqexTransactionActivity.REQUEST_CODE:
                    CrashAccountDao crashAccountDao=new CrashAccountDao();
                    CrashAccountEntity accountEntity=crashAccountDao.getCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
                    if(accountEntity!=null)
                    {
                        if (BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY == accountEntity.getAccountType()) {
                            imageLogo.setImageResource(R.drawable.icon_zhifbao);
                            tv_bqex.setText(accountEntity.getAccount());
                            imageLogo.setVisibility(View.VISIBLE);
                        } else if (BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN == accountEntity.getAccountType()) {
                            imageLogo.setImageResource(R.drawable.bqex_icon);
                            tv_bqex.setText(accountEntity.getAccount());
                            imageLogo.setVisibility(View.VISIBLE);
                        }else if(BqexTransactionActivity.ACCOUNT_TYPE_BANK==accountEntity.getAccountType()) {
                            imageLogo.setImageResource(R.drawable.icon_bank);
                            tv_bqex.setText(accountEntity.getAccount());
                            imageLogo.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.bt_withdrawal_confirm, R.id.tv_withdrawal_all, R.id.ll_title_left_view, R.id.tv_transfer_accounts_one, R.id.tv_usdt_two,
            R.id.ll_balance_assets, R.id.ll_fixed_assets, R.id.ll_frozen_assets, R.id.ll_account, R.id.title_right_text2, R.id.linear_tatol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_tatol:
                //改用系统浏览器
                Uri uri = Uri.parse(UrlContent.pdf_total);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.title_right_text2:
                WithdrawalRecordsActivity.startAction(WithdrawalOfAssetsActivity.this);
                break;
            case R.id.bt_withdrawal_confirm:
                if(SoftKeyboardUtils.isSoftShowing(WithdrawalOfAssetsActivity.this)){
                    SoftKeyboardUtils.hideSoftKeyboard(WithdrawalOfAssetsActivity.this);
                }
                if(BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY==accountType||(BqexTransactionActivity.ACCOUNT_TYPE_BANK==accountType))
                {
                    getDrawPbs("");
                }
                else if(BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN==accountType)
                {
                    showPopupWindow();
                }
                break;
            case R.id.tv_withdrawal_all:
                if(currencyType == 0){
                    if(balanceType ==0){
                        if (!TextUtils.isEmpty(pbsAmount)) {
                            et_quantity_extracted.setText(pbsAmount);
                        }
                    }else if(balanceType ==1){
                        if (!TextUtils.isEmpty(hatchAmount)) {
                            et_quantity_extracted.setText(hatchAmount);
                        }
                    }
                }else if(currencyType == 1){
                    if(balanceType ==0){
                        if (!TextUtils.isEmpty(rebateCrystal)) {
                            String mulUsdt = BigDecimalUtil.mul(rebateCrystal, usdCNY, Constant.SCALE_NUM_FOUR);
                            et_quantity_extracted.setText(rebateCrystal);
                        }
                    }else if(balanceType ==1){
                        if (!TextUtils.isEmpty(hatchAmount)&&!TextUtils.isEmpty(usdtPbs)) {
                            String mulUsdt = BigDecimalUtil.mul(usdtPbs, hatchAmount, Constant.SCALE_NUM_Eight);
                            et_quantity_extracted.setText(mulUsdt);
                        }
                    }
                }
                break;
            case R.id.ll_title_left_view:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.tv_transfer_accounts_one://PBS
                currencyType = 0;
//                tv_transfer_accounts.setText(getString(R.string.transfer_accounts));
                tv_currency_type.setText(Constant.PBS1);
//                et_quantity_extracted.setHint(R.string.quantity_input_tips);
                tv_withdrawal_service_charge.setText("50 PBS");
                if(TextUtils.isEmpty(et_quantity_extracted.getText().toString().trim())){
                    tv_actual_achievement_quantity.setText("PBS");
                }else {
                    if (et_quantity_extracted.getText().toString().trim().contains(Constant.BAR)) {
                        tv_actual_achievement_quantity.setText("0PBS");
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    } else {
                        String quantityExtracteds = BigDecimalUtil.sub(et_quantity_extracted.getText().toString().trim(), "50", Constant.SCALE_NUM);

                        tv_actual_achievement_quantity.setText(quantityExtracteds + Constant.PBS1);
                        setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                }
                tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                tv_usdt_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                tv_transfer_accounts_blue_one.setVisibility(View.VISIBLE);
                tv_usdt_blue_two.setVisibility(View.INVISIBLE);
                tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._333333));
                tv_usdt_two.setTextColor(getResources().getColor(R.color._999999));
                if(balanceType == 0){
                    if (!TextUtils.isEmpty(pbsAmount)) {
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                    }
                }else if(balanceType ==1){
                    if (!TextUtils.isEmpty(hatchAmount)) {
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + hatchAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                    }
                }
                break;
            case R.id.tv_usdt_two://USDT
                currencyType = 1;
                drawToBqex = 2;
//                tv_transfer_accounts.setText(getString(R.string.fixed_assets2));
                tv_currency_type.setText(getString(R.string.crystal));
//                et_quantity_extracted.setHint(R.string.crystals_tips);

                if("yuanyuan".equals(userChannle)){
                    tv_withdrawal_service_charge.setText("1%");// 0 USDT
                }else {
                    if(accountType==BqexTransactionActivity.ACCOUNT_TYPE_BANK) {
                        tv_withdrawal_service_charge.setText("1.5%");// 5 USDT
                    } else{
                        tv_withdrawal_service_charge.setText("1%");// 5 USDT
                    }
                }
                if(TextUtils.isEmpty(et_quantity_extracted.getText().toString().trim())){
                    tv_actual_achievement_quantity.setText("USDT");
                }else {
                    if (et_quantity_extracted.getText().toString().trim().contains(Constant.BAR)) {
                        tv_actual_achievement_quantity.setText("0USDT");
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    } else {
                        ///
                        if("yuanyuan".equals(userChannle)){
                            String div = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                            tv_actual_achievement_quantity.setText(div + Constant.USDT);
                        }else {
                            String div = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                            String quantityExtracteds_crystal = BigDecimalUtil.sub(div, "5", Constant.SCALE_NUM_FOUR);
                            tv_actual_achievement_quantity.setText(quantityExtracteds_crystal + Constant.USDT);
                        }
                        ///
                        setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                }
                tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                tv_usdt_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                tv_transfer_accounts_blue_one.setVisibility(View.INVISIBLE);
                tv_usdt_blue_two.setVisibility(View.VISIBLE);
                tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._999999));
                tv_usdt_two.setTextColor(getResources().getColor(R.color._333333));

                if(balanceType == 0){
                    if (!TextUtils.isEmpty(pbsAmount)&&!TextUtils.isEmpty(usdtPbs)) {
                        if(!TextUtils.isEmpty(rebateCrystal)){
                            if(!TextUtils.isEmpty(et_quantity_extracted.getText().toString().trim())){
                                if("yuanyuan".equals(userChannle)){
                                    String div = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                                    tv_actual_achievement_quantity.setText(div + Constant.USDT);
                                }else {
                                    String div = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                                    String quantityExtracteds_crystal = BigDecimalUtil.sub(div, "5", Constant.SCALE_NUM_FOUR);
                                    tv_actual_achievement_quantity.setText(quantityExtracteds_crystal + Constant.USDT);
                                }
                            }

                            tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + rebateCrystal + Constant.ONE_SPACE + getString(R.string.crystal));
                        }else {
                            tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + "0" + Constant.ONE_SPACE + getString(R.string.crystal));
                        }

                    }
                }else if(balanceType ==1){

                    ll_usdt_two.setVisibility(View.GONE);
                    tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    tv_transfer_accounts_blue_one.setVisibility(View.VISIBLE);
                    tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._333333));
                    tv_currency_type.setText(Constant.PBS1);
//                    et_quantity_extracted.setHint(R.string.quantity_input_tips);
                    tv_withdrawal_service_charge.setText("50 PBS");
                }
                break;
            case R.id.ll_balance_assets://余额资产
                balanceType = 0;
                drawToBqex = 0;
                ll_usdt_two.setVisibility(View.VISIBLE);
                tv_usdt_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                tv_usdt_blue_two.setVisibility(View.INVISIBLE);
                tv_usdt_two.setTextColor(getResources().getColor(R.color._999999));

                setPbsAssetsView(R.drawable.bg_blue_rectangle, R.color.white, R.drawable.bg_blue_rectangle_white, R.color._72c6ae);
                tv_transfer_accounts.setText(getString(R.string.balance_assets));
                if(currencyType == 0){
                    if (!TextUtils.isEmpty(pbsAmount)) {
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                    }
                }else if(currencyType == 1){
                    if (!TextUtils.isEmpty(rebateCrystal)) {
                        String mulUsdt = BigDecimalUtil.mul(usdtPbs, pbsAmount, Constant.SCALE_NUM_Eight);
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + rebateCrystal + Constant.ONE_SPACE + getString(R.string.crystal));
                    }
                }

                break;
            case R.id.ll_fixed_assets://定存资产
                balanceType = 1;
                drawToBqex = 1;
                setPbsAssetsView(R.drawable.bg_blue_rectangle_white, R.color._72c6ae, R.drawable.bg_blue_rectangle, R.color.white);
                tv_transfer_accounts.setText(getString(R.string.fixed_assets));
                if(currencyType == 0){
                    if (!TextUtils.isEmpty(hatchAmount)) {
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + hatchAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                    }
                }else if(currencyType == 1){//USDT没有定存
                    currencyType = 0;
                    ll_usdt_two.setVisibility(View.GONE);

                    tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                    tv_transfer_accounts_blue_one.setVisibility(View.VISIBLE);
                    tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._333333));
                    tv_currency_type.setText(Constant.PBS1);
//                    et_quantity_extracted.setHint(R.string.quantity_input_tips);
                    tv_withdrawal_service_charge.setText("50 PBS");

                    if (!TextUtils.isEmpty(hatchAmount)) {
                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + hatchAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                    }
                    tv_actual_achievement_quantity.setText("PBS");
//                    if (!TextUtils.isEmpty(hatchAmount)&&!TextUtils.isEmpty(usdtPbs)) {
//                        String mulUsdt = BigDecimalUtil.mul(usdtPbs, hatchAmount, Constant.SCALE_NUM_Eight);
//                        tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + mulUsdt + Constant.ONE_SPACE + Constant.USDT);
//                    }
                }
                break;
//            case R.id.ll_frozen_assets://冻结资产
//                balanceType = 2;
//                setPbsAssetsView(R.drawable.bg_blue_rectangle_white,R.color._72c6ae,R.drawable.bg_blue_rectangle_white,R.color._72c6ae,R.drawable.bg_blue_rectangle,R.color.white);
//                tv_transfer_accounts.setText(getString(R.string.frozen_assets));
//                if (!TextUtils.isEmpty(pbsAmount)) {
//                    tv_withdrawal_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
//                }
//                break;
            case R.id.ll_account://账户设置界面
                Intent intentBq=new Intent(getBaseContext(),BqexTransactionActivity.class);
                intentBq.putExtra("accountType",accountType);
                startActivityForResult(intentBq,BqexTransactionActivity.REQUEST_CODE);
                break;
        }
    }

    /**
     * 评论弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.message_dialoglayout, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // pop.dismiss(）方法调用时，回调该函数，点击外部时，也会回调该函数
                //解决键盘与PopupWindow的冲突，导致输入框dismiss后键盘不隐藏
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
        //设置软键盘弹出
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(inputMethodManager).toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
        //设置各个控件的点击响应
        final EditText editText = contentView.findViewById(R.id.et_comment);
        TextView tv_send = contentView.findViewById(R.id.tv_send);
        TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
        TextView tv_phone_tips = contentView.findViewById(R.id.tv_phone_tips);
        TextInputEditText code = contentView.findViewById(R.id.code);
        send = contentView.findViewById(R.id.send);

        tv_phone_tips.setText(getString(R.string.send_out2, StringUtil.showPhonenumber(SharePreferenceUtils.getBaseSharePreference().readBqPhone())));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time = new TimeCount(60000, 1000);
                sendCode();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();//让PopupWindow消失
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = code.getText().toString();
                if (code.getText().length() > 0) {
//                    SoftKeyboardUtils.hideSoftKeyboard(ResultsOfWithdrawalsActivity.this);
                    if (!TextUtils.isEmpty(et_quantity_extracted.getText().toString().trim())) {
                        String sub_quantity_extracted = BigDecimalUtil.sub(pbsAmount, et_quantity_extracted.getText().toString().trim(), Constant.SCALE_NUM);
                        getDrawPbs(code.getText().toString().trim());
                    }

                    mPopWindow.dismiss();//让PopupWindow消失

                }
            }
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_text_details, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 获取pbs提现接口
     */
    private void getDrawPbs(String code) {
        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
        UserTask userTask= new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    DrawPbsBean bean = GsonUtils.parseJson(resulte, DrawPbsBean.class);
                    if(bean!=null)
                    {
                        if (bean.getStatusCode() == Constant.SUCCESS) {
                            setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                            T.showShort("提现请求已发送将在规定时间内受理，请耐心等待！");
                            finish();
                        }
                        else
                        {
                            setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                            if(StringUtil.isNotEmpty(bean.getMessage()))
                            {
                                T.showShort(bean.getMessage());
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            }
        }, this);

        CrashAccountEntity entity= crashAccountDao.getCrashAccountByUid(SharePreferenceUtils.getBaseSharePreference().readUserId(),accountType);
        if(entity!=null)
        {
            switch (entity.getAccountType())
            {
                case BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY:
                    userTask.drawZfbToZfb(entity.getAccount(),entity.getAccountName(),
                            et_quantity_extracted.getText().toString().trim());
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BANK:
                    userTask.drawZfbToBank(entity.getAccount(),entity.getBankName(),entity.getAccountName(),
                            et_quantity_extracted.getText().toString().trim());
                    break;
                case BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN:
                    userTask.drawToBqex(entity.getCountryCode(),entity.getAccount(),
                            code, et_quantity_extracted.getText().toString().trim(),drawToBqex);
                    break;
            }
        }
        else
        {
            T.showShort("请设置账户信息!");
            setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onPause(this);
    }
    /**
     * 设置提取资产按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setWithdrawalBackground(boolean type, int background) {
        bt_withdrawal_confirm.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        bt_withdrawal_confirm.setBackgroundDrawable(drawable);
    }
    /**
     * 提币地址监听
     */
    private TextWatcher withdrawalAddressWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            withdrawalAddress = editable.toString();
            if (editable.toString().length() >= 6 && !TextUtils.isEmpty(quantityExtracted)&&quantityExtracted_Double>=2000 &&pbsAmount_double>50) {
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    /**
     * 提取数量监听
     */
    private TextWatcher quantityExtractedWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //删除“.”后面超过2位后的数据
            if (s.toString().contains(".")) {
                if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                    s = s.toString().subSequence(0,
                            s.toString().indexOf(".") + 3);
                    et_quantity_extracted.setText(s);
                    et_quantity_extracted.setSelection(s.length()); //光标移到最后
                }
            }
            //如果"."在起始位置,则起始位置自动补0
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                et_quantity_extracted.setText(s);
                et_quantity_extracted.setSelection(2);
            }

            //如果起始位置为0,且第二位跟的不是".",则无法后续输入
            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    et_quantity_extracted.setText(s.subSequence(0, 1));
                    et_quantity_extracted.setSelection(1);
                    return;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            quantityExtracted = editable.toString();
//            quantityExtracted_Double = Double.parseDouble(quantityExtracted);
//            if (editable.toString().length() >=3 && !TextUtils.isEmpty(withdrawalAddress)&&withdrawalAddress.length() >= 6 &&pbsAmount_double>50) {

            if(!TextUtils.isEmpty(editable.toString())){

                double quantityExtracted_double = Double.parseDouble(quantityExtracted);
                String div = BigDecimalUtil.div(quantityExtracted, usdCNY, Constant.SCALE_NUM_FOUR);
                String quantityExtracteds_crystal = BigDecimalUtil.mul(div, "0.99");
                double crystal_usdt = Double.parseDouble(quantityExtracteds_crystal);
                String quantityExtracteds = BigDecimalUtil.sub(editable.toString(), "50", Constant.SCALE_NUM);
                if(currencyType == 0){
                    if (quantityExtracteds.contains(Constant.BAR)) {
                        tv_actual_achievement_quantity.setText("0PBS");
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    } else {
                        tv_actual_achievement_quantity.setText(quantityExtracteds + getString(R.string.pbs));
                        setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                    }
                }else if(currencyType == 1){
                    if (quantityExtracteds_crystal.contains(Constant.BAR)) {
                        tv_actual_achievement_quantity.setText("0USDT");
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    } else {
                        if(!TextUtils.isEmpty(usdCNY)&&!TextUtils.isEmpty(rebateCrystal)){
                            if("yuanyuan".equals(userChannle)){
                                String div1 = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                                tv_actual_achievement_quantity.setText(div1 + Constant.USDT);
                            }else {

                                String div1 = BigDecimalUtil.div(et_quantity_extracted.getText().toString().trim(), usdCNY, Constant.SCALE_NUM_FOUR);
                                String quantityExtracteds_crystal1 = BigDecimalUtil.mul(div1, "0.99");
                                tv_actual_achievement_quantity.setText(quantityExtracteds_crystal1 + Constant.USDT);
                            }
                            setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                        }
                    }
                }
                if(currencyType ==0 ){
                    if (editable.toString().length() >3 &&quantityExtracted_double>=2000) {
                        setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                    } else {
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    }
                }else if(currencyType == 1){
                    if (editable.toString().length() >0&&crystal_usdt> 0) {
                        setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                    } else {
                        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    }
                }

            }
        }
    };
    /**
     * 设置布局显示隐藏
     * @param balanceBg
     * @param balanceColor
     * @param fixedBg
     * @param fixedColor
     */
    private void setPbsAssetsView(int balanceBg, int balanceColor, int fixedBg, int fixedColor){
        ll_balance_assets.setBackgroundResource(balanceBg);
        tv_balance_assets.setTextColor(getResources().getColor(balanceColor));
        ll_fixed_assets.setBackgroundResource(fixedBg);
        tv_fixed_assets.setTextColor(getResources().getColor(fixedColor));
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
        DialogUtil.getInstance().showLoadingDialog(this);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                SendForDrawToBqexBean codeBean = GsonUtils.parseJson(resulte, SendForDrawToBqexBean.class);
                if (codeBean.getStatusCode() == Constant.SUCCESS) {
                    time.start();
                    T.showShort(codeBean.getMessage());
                } else if (codeBean.getStatusCode() == Constant.TIPS1) {
                    T.showShort(codeBean.getMessage());
                }
                DialogUtil.getInstance().closeLoadingDialog();

            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();

                T.showShort(e.getMessage());
            }
        }, this).sendForDrawToBqex(SharePreferenceUtils.getBaseSharePreference().readBqCode(), SharePreferenceUtils.getBaseSharePreference().readBqPhone());
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
}
