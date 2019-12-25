package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
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
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BroadEvent;
import com.iruiyou.pet.bean.CheckAmountBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-存入资产1
 * 作者：sgf
 *
 */
public class AssetsDeposited1Activity extends BaseActivity {
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
    @BindView(R.id.bt_withdrawal_confirm_one)
    Button bt_withdrawal_confirm_one;
    @BindView(R.id.et_select_pbs_number)
    EditText et_select_pbs_number;
    @BindView(R.id.tv_pbs_current_price)
    TextView tv_pbs_current_price;
    @BindView(R.id.linear_all_crystal)
    LinearLayout linearAll;
    private Context context;
    private String pbsAmount;

    @Override
    public int getLayout() {
        return R.layout.activity_assets_deposited_one;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.assets_deposited));
        context = AssetsDeposited1Activity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
        pbsAmount = getIntent().getStringExtra("pbsAmount");
        et_select_pbs_number.addTextChangedListener(selectPbsNumberWatcher);
        if(!TextUtils.isEmpty(pbsAmount)){
            tv_pbs_current_price.setText(BigDecimalUtil.addComma4(pbsAmount));
        }
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_withdrawal_confirm_one, R.id.ll_title_left_view, R.id.linear_all_crystal})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.bt_withdrawal_confirm_one://确认
                SoftKeyboardUtils.hideSoftKeyboard(AssetsDeposited1Activity.this);
                String select_pbs_number = et_select_pbs_number.getText().toString().trim();
                if(!TextUtils.isEmpty(select_pbs_number)&& StringUtil.isNotEmpty(pbsAmount)){
                    if(Double.valueOf(select_pbs_number)>Double.valueOf(pbsAmount))
                    {
                        T.showShort("不能超过最大可用余额！");
                        return;
                    }
                    setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                    getCheckAmount();
//                    StartActivityManager.startAssetsDepositedActivity(this,select_pbs_number);
                }
                break;
            case R.id.ll_title_left_view:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
            case R.id.linear_all_crystal:
                String value= BigDecimalUtil.addComma4(pbsAmount);
                et_select_pbs_number.setText(value);
                break;
        }
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
     * 获取存入接口
     */
    private void getCheckAmount() {
        DialogUtil.getInstance().showLoadingDialog(this);

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckAmountBean bean = GsonUtils.parseJson(resulte, CheckAmountBean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
//                    String select_pbs_number = et_select_pbs_number.getText().toString().trim();
//                    if(!TextUtils.isEmpty(select_pbs_number)){
//                    StartActivityManager.startAssetsDepositedActivity(AssetsDeposited1Activity.this,select_pbs_number,bean.getData());
//                    }
                    EventBusUtils.getInstance().postEvent(new BroadEvent(BroadEvent.ACTION_REFRESH_WALLET));
                } else if (bean.getStatusCode() == Constant.TIPS1) {
                    T.showShort(bean.getMessage());
                }
//                refreshLayout_pbs_incubation.finishRefresh(true);
                DialogUtil.getInstance().closeLoadingDialog();
                finish();
            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            }
        }, this).getCheckAmount(et_select_pbs_number.getText().toString().trim());
    }
    /**
     * 提取数量监听
     */
    private TextWatcher selectPbsNumberWatcher = new TextWatcher() {
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
                    et_select_pbs_number.setText(s);
                    et_select_pbs_number.setSelection(s.length()); //光标移到最后
                }
            }
            //如果"."在起始位置,则起始位置自动补0
            if (s.toString().trim().substring(0).equals(".")) {
                s = "0" + s;
                et_select_pbs_number.setText(s);
                et_select_pbs_number.setSelection(2);
            }

            //如果起始位置为0,且第二位跟的不是".",则无法后续输入
            if (s.toString().startsWith("0")
                    && s.toString().trim().length() > 1) {
                if (!s.toString().substring(1, 2).equals(".")) {
                    et_select_pbs_number.setText(s.subSequence(0, 1));
                    et_select_pbs_number.setSelection(1);
                    return;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0) {
                et_select_pbs_number.setSelection(et_select_pbs_number.getText().toString().length());
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
            }
        }
    };
    /**
     * 设置提取资产按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setWithdrawalBackground(boolean type, int background) {
        bt_withdrawal_confirm_one.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        bt_withdrawal_confirm_one.setBackgroundDrawable(drawable);
    }
}
