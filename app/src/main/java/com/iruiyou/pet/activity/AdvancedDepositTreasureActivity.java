package com.iruiyou.pet.activity;

import android.content.Context;
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
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BuyFromOfficial2Bean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SelfDidalog;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-PBS高端定存宝
 * 作者：sgf
 */
public class AdvancedDepositTreasureActivity extends BaseActivity {
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
    @BindView(R.id.et_subscription_quantity_advanced)
    EditText et_subscription_quantity_advanced;
    @BindView(R.id.im_add)
    ImageView im_add;
    @BindView(R.id.im_reduce)
    ImageView im_reduce;
    @BindView(R.id.bt_advanced_confirm)
    Button bt_advanced_confirm;
    @BindView(R.id.bt_recharge_advanced)
    Button bt_recharge_advanced;
    @BindView(R.id.tv_crystal_portion)
    TextView tv_crystal_portion;
    @BindView(R.id.tv_incubation_time_item)
    TextView tv_incubation_time_item;
    @BindView(R.id.tv_crystal_balance)
    TextView tv_crystal_balance;
    private Context context;
    private String readCrystalAmount;

    @Override
    public int getLayout() {
        return R.layout.activity_advanced_deposit_treasure;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.subscribe_pbs_high_deposit_treasury));
        context = AdvancedDepositTreasureActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
//        et_subscription_quantity_advanced.addTextChangedListener(advancedWatcher);
        et_subscription_quantity_advanced.setText("0");
        et_subscription_quantity_advanced.setSelection(et_subscription_quantity_advanced.getText().length());
        readCrystalAmount = SharePreferenceUtils.getBaseSharePreference().readCrystalAmount();
        tv_crystal_balance.setText(readCrystalAmount);
        tv_crystal_portion.setText("10000" + getString(R.string.crystal_portion));
        tv_incubation_time_item.setText(getString(R.string.incubation_time) + "180" + getString(R.string.day));
    }

    /**
     * 认购数量的监听
     */
    private TextWatcher advancedWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(editable.toString())) {
                int parseInt = Integer.parseInt(editable.toString());
                if (editable.toString().length() > 0) {//设置输入框只可以输入1-10的数字
                    if (parseInt > 10) {
                        et_subscription_quantity_advanced.setText("10");
                    }  //                        et_subscription_quantity_advanced.setText("1");

                }
            }  //                et_subscription_quantity_advanced.setText("1");

            et_subscription_quantity_advanced.setSelection(et_subscription_quantity_advanced.getText().length());
        }
    };

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_recharge_advanced, R.id.im_reduce, R.id.im_add, R.id.bt_advanced_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_recharge_advanced://充值
                if (!TextUtils.isEmpty(readCrystalAmount)) {
                    StartActivityManager.startCrystalRechargeActivity(context, readCrystalAmount);
                }
                break;
            case R.id.bt_advanced_confirm://确认
//                String trim = et_subscription_quantity_advanced.getText().toString().trim();
//                if (!TextUtils.isEmpty(trim)) {
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                getFuturesBuyFromOfficial2();
//                }
                break;
            case R.id.im_add:
                int addTime = getTimes();
                if (addTime >= 10) {
                    return;
                }
                addTime++;
                et_subscription_quantity_advanced.setText(addTime + "");
                et_subscription_quantity_advanced.setSelection(et_subscription_quantity_advanced.getText().length());
                break;
            case R.id.im_reduce:
                int lessTime = getTimes();
                if (lessTime < 1) {
//                    T.showShort("最小了!");
                    return;
                }
                lessTime--;
                et_subscription_quantity_advanced.setText(lessTime + "");
                et_subscription_quantity_advanced.setSelection(et_subscription_quantity_advanced.getText().length());
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
     * 获取普通定存宝接口
     */
    private void getFuturesBuyFromOfficial2() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                BuyFromOfficial2Bean bean = GsonUtils.parseJson(resulte, BuyFromOfficial2Bean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
                    T.showShort(bean.getMessage());
                    finish();
                } else if (bean.getStatusCode() == Constant.TIPS1) {
                    String title = getString(R.string.prompt);
                    String content = bean.getMessage();
//                    boolean isVisibleCancel = false;//true显示两个控件，否则1显示个
                    int noStrs = R.string.cancel;
                    int yesStrs = R.string.set3;
                    new SelfDidalog(context, title, content, false, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                        @Override
                        public void onYesClick() {
                        }

                        @Override
                        public void onNoClick() {
                        }
                    }).show();
                } else {
                    T.showShort(bean.getMessage());
                }
//                refreshLayout_pbs_incubation.finishRefresh(true);
            }

            @Override
            public void onError(ApiException e) {
//                refreshLayout_pbs_incubation.finishRefresh(false);
            }
        }, this).futuresBuyFromOfficial2("180", et_subscription_quantity_advanced.getText().toString().trim(), "crystal");

    }

    /**
     * 认购数量
     *
     * @return
     */
    public int getTimes() {

        String timeStr = et_subscription_quantity_advanced.getText().toString();
        if (timeStr.length() < 9) {
            return TextUtils.isEmpty(et_subscription_quantity_advanced.getText()) ? 1 : Integer.parseInt(timeStr);
        } else {
//            T.showShort("已是最大数");
            return 9999;
        }
    }
}
