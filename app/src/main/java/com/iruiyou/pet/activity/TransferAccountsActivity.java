package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.TransferBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SelfDidalog;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-PBS转账
 * 作者：sgf
 */
public class TransferAccountsActivity extends BaseActivity {
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
    @BindView(R.id.et_transfer_to)
    ClearEditText et_transfer_to;
    @BindView(R.id.et_transfer_count)
    EditText et_transfer_count;
    @BindView(R.id.tv_transfer_accounts_all)
    TextView tv_transfer_accounts_all;
    @BindView(R.id.bt_transfer_accounts_confirm)
    Button bt_transfer_accounts_confirm;
    @BindView(R.id.tv_transfer_accounts_available)
    TextView tv_transfer_accounts_available;
    @BindView(R.id.tv_transfer_accounts_one)
    TextView tv_transfer_accounts_one;
    @BindView(R.id.tv_transfer_accounts_blue_one)
    TextView tv_transfer_accounts_blue_one;
    @BindView(R.id.tv_fixed_assets_two)
    TextView tv_fixed_assets_two;
    @BindView(R.id.tv_fixed_assets_blue_two)
    TextView tv_fixed_assets_blue_two;
    @BindView(R.id.tv_transfer_accounts)
    TextView tv_transfer_accounts;
    @BindView(R.id.tv_fixed_assets)
    TextView tv_fixed_assets;
    @BindView(R.id.ll_fixed_assets)
    LinearLayout ll_fixed_assets;
    @BindView(R.id.tv_balance_assets)
    TextView tv_balance_assets;
    @BindView(R.id.ll_balance_assets)
    LinearLayout ll_balance_assets;
    private Context context;
    private String pbsAmount;
    private String fixedAssetsAmount;
    private String withdrawalAddress;
    private String quantityExtracted;
    private int balanceType = 0;
//    private double quantityExtracted_Double;
//    private double pbsAmount_double = 0.0;
//    private double fixedAssetsAmount_double = 0.0;

    @Override
    public int getLayout() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.transfer_accounts));
        context = TransferAccountsActivity.this;
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initDta();
        getRefresh();
    }

    private void initDta() {
        pbsAmount = getIntent().getStringExtra("pbsAmount");
        fixedAssetsAmount = getIntent().getStringExtra("fixedAssetsAmount");
        et_transfer_count.addTextChangedListener(quantityExtractedWatcher);
        et_transfer_to.addTextChangedListener(withdrawalAddressWatcher);
        if (!TextUtils.isEmpty(pbsAmount)) {
//            pbsAmount_double = Double.parseDouble(pbsAmount);
            tv_transfer_accounts_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
        }
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_transfer_accounts_confirm, R.id.tv_transfer_accounts_all, R.id.tv_transfer_accounts_one, R.id.tv_fixed_assets_two,
            R.id.ll_title_left_view, R.id.ll_balance_assets, R.id.ll_fixed_assets})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_transfer_accounts_confirm:
//                ClipboardManager cm =(ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
//                cm.setText(et_wallet_address.getText().toString());
//                T.showShort(getString(R.string.replication_success));
                String quantity_extracted = et_transfer_count.getText().toString().trim();
                String money_withdrawal_address = et_transfer_to.getText().toString().trim();

                if (TextUtils.isEmpty(quantity_extracted)) {
                    bt_transfer_accounts_confirm.setEnabled(false);
                    return;
                }else {
                    bt_transfer_accounts_confirm.setEnabled(true);
                }
                if (TextUtils.isEmpty(quantity_extracted)) {
                    bt_transfer_accounts_confirm.setEnabled(false);
                    return;
                }else {
                    bt_transfer_accounts_confirm.setEnabled(true);
                }
                if (!TextUtils.isEmpty(quantity_extracted) && !TextUtils.isEmpty(money_withdrawal_address)) {
                    String sub_quantity_extracted = BigDecimalUtil.sub(pbsAmount, quantity_extracted, Constant.SCALE_NUM);
                    getTransfer();
                }
                break;
            case R.id.tv_transfer_accounts_all:
                if (!TextUtils.isEmpty(pbsAmount)) {
                    et_transfer_count.setText(pbsAmount);
                }
                break;
            case R.id.tv_transfer_accounts_one://可用PBS
                tv_transfer_accounts.setText(getString(R.string.transfer_accounts));
                tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
                tv_fixed_assets_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
                tv_transfer_accounts_blue_one.setVisibility(View.VISIBLE);
                tv_fixed_assets_blue_two.setVisibility(View.INVISIBLE);
                tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._333333));
                tv_fixed_assets_two.setTextColor(getResources().getColor(R.color._999999));
                if (!TextUtils.isEmpty(pbsAmount)) {
//                    pbsAmount_double = Double.parseDouble(pbsAmount);
                    tv_transfer_accounts_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                }
                break;
            case R.id.tv_fixed_assets_two://定存资产
                tv_transfer_accounts.setText(getString(R.string.fixed_assets2));
                tv_transfer_accounts_one.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                tv_fixed_assets_two.setTextSize(TypedValue.COMPLEX_UNIT_SP,19);
                tv_transfer_accounts_blue_one.setVisibility(View.INVISIBLE);
                tv_fixed_assets_blue_two.setVisibility(View.VISIBLE);
                tv_transfer_accounts_one.setTextColor(getResources().getColor(R.color._999999));
                tv_fixed_assets_two.setTextColor(getResources().getColor(R.color._333333));
                if (!TextUtils.isEmpty(fixedAssetsAmount)) {
//                    fixedAssetsAmount_double = Double.parseDouble(fixedAssetsAmount);
                    tv_transfer_accounts_available.setText(getString(R.string.available) + Constant.ONE_SPACE + fixedAssetsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                }
                break;
            case R.id.ll_title_left_view:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.ll_balance_assets://余额资产
                balanceType = 0;
                setPbsAssetsView(R.drawable.bg_blue_rectangle, R.color.white, R.drawable.bg_blue_rectangle_white, R.color._72c6ae);
                tv_transfer_accounts.setText(getString(R.string.balance_assets));
                if (!TextUtils.isEmpty(pbsAmount)) {
//                    pbsAmount_double = Double.parseDouble(pbsAmount);
                    tv_transfer_accounts_available.setText(getString(R.string.available) + Constant.ONE_SPACE + pbsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                }
                break;
            case R.id.ll_fixed_assets://定存资产
                balanceType = 1;
                setPbsAssetsView(R.drawable.bg_blue_rectangle_white, R.color._72c6ae, R.drawable.bg_blue_rectangle, R.color.white);
                tv_transfer_accounts.setText(getString(R.string.fixed_assets));
                if (!TextUtils.isEmpty(fixedAssetsAmount)) {
//                    fixedAssetsAmount_double = Double.parseDouble(fixedAssetsAmount);
                    tv_transfer_accounts_available.setText(getString(R.string.available) + Constant.ONE_SPACE + fixedAssetsAmount + Constant.ONE_SPACE + getString(R.string.pbs));
                }
                break;
        }
    }

    /**
     * PBS转账接口
     */
    private void getTransfer() {
        DialogUtil.getInstance().showLoadingDialog(this);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                TransferBean bean = GsonUtils.parseJson(resulte, TransferBean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
                    String title = getString(R.string.prompt);
                    String content = getString(R.string.successful_transfer);
//                    boolean isVisibleCancel = false;//true显示两个控件，否则1显示个
                    int noStrs = R.string.cancel;
                    int yesStrs = R.string.set3;
                    new SelfDidalog(context, title, content, false, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                        @Override
                        public void onYesClick() {
                            finish();
                        }

                        @Override
                        public void onNoClick() {
                        }
                    }).show();
                } else if (bean.getStatusCode() == Constant.TIPS1) {
                }else {
                    T.showShort(bean.getMessage());
                }
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, this).transfer(et_transfer_to.getText().toString().trim(), balanceType,et_transfer_count.getText().toString().trim());

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
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
        bt_transfer_accounts_confirm.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        bt_transfer_accounts_confirm.setBackgroundDrawable(drawable);
    }
    /**
     * 转入账号监听
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
            if (editable.toString().length() >= 6 && !TextUtils.isEmpty(quantityExtracted)) {
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    /**
     * 转入PBS数量监听
     */
    private TextWatcher quantityExtractedWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            quantityExtracted = editable.toString();
//            quantityExtracted_Double = Double.parseDouble(quantityExtracted);
            if (editable.toString().length() >=1 && !TextUtils.isEmpty(withdrawalAddress)) {
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
            }

//            if(!TextUtils.isEmpty(editable.toString())){
//                String quantityExtracteds = BigDecimalUtil.sub(editable.toString(), "50", Constant.SCALE_NUM);
//                if (quantityExtracteds.contains(Constant.BAR)) {
//                    tv_actual_achievement_quantity.setText("0PBS");
//                    setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
//                } else {
//                    tv_actual_achievement_quantity.setText(quantityExtracteds + getString(R.string.pbs));
//                    setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
//                }
//            }
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
}
