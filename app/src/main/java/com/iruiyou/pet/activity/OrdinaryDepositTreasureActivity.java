package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
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
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SelfDidalog;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-PBS普通定存宝
 * 作者：sgf
 */
public class OrdinaryDepositTreasureActivity extends BaseActivity {
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
    @BindView(R.id.tv_crystal_balance)
    TextView tv_crystal_balance;
    @BindView(R.id.bt_recharge_ordinary)
    Button bt_recharge_ordinary;
    @BindView(R.id.tv_incubation_time_item)
    TextView tv_incubation_time_item;
    @BindView(R.id.tv_expected_daily_rate_item)
    TextView tv_expected_daily_rate_item;
    @BindView(R.id.tv_release_time)
    TextView tv_release_time;
    @BindView(R.id.im_reduce)
    ImageView im_reduce;
    @BindView(R.id.im_add)
    ImageView im_add;
    @BindView(R.id.et_subscription_quantity)
    EditText et_subscription_quantity;
    @BindView(R.id.bt_ordinary_confirm)
    Button bt_ordinary_confirm;
    private Context context;
    private String type;
    private String dayRate;
    private String time;
    private String readCrystalAmount;

    @Override
    public int getLayout() {
        return R.layout.activity_ordinary_deposit_treasure;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.subscribe_pbs_deposit));
        context = OrdinaryDepositTreasureActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
//        et_subscription_quantity.addTextChangedListener(advancedWatcher);
        et_subscription_quantity.setText("0");
        et_subscription_quantity.setSelection(et_subscription_quantity.getText().length());
        type = getIntent().getStringExtra("type");
        dayRate = getIntent().getStringExtra("dayRate");
        time = getIntent().getStringExtra("time");

        readCrystalAmount = SharePreferenceUtils.getBaseSharePreference().readCrystalAmount();
        tv_incubation_time_item.setText(getString(R.string.incubation_time) + type + getString(R.string.day));
        String rateString = BigDecimalUtil.mul(dayRate, "100.0", 2);
        tv_expected_daily_rate_item.setText(rateString + Constant.PERCENT_SIGN);
        tv_release_time.setText("1000" + getString(R.string.crystal_portion));
        tv_crystal_balance.setText(readCrystalAmount);
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
                    if (parseInt > 50) {
                        et_subscription_quantity.setText("50");
                    }  //                        et_subscription_quantity.setText("1");

                }
            }  //                et_subscription_quantity.setText("1");

            et_subscription_quantity.setSelection(et_subscription_quantity.getText().length());
        }
    };

    /**
     * 获取普通定存宝接口
     */
    private void getBuyFromOfficial2() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                BuyFromOfficial2Bean bean = GsonUtils.parseJson(resulte, BuyFromOfficial2Bean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
                    T.showShort(bean.getMessage());
                    Intent intent = new Intent();//设置多个（如：1，2，3）activity流程中直接跳转指定的activity5,之后返回不再回到1，2，3这个几个activity中，直接销毁1，2，3activity
                    intent.setClass(OrdinaryDepositTreasureActivity.this, MyWalletActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//最关键是这句 :多activity中退出整个程序，例如从A->B->C->D，这时我需要从D直接退出程序
                    startActivity(intent);
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
        }, this).buyFromOfficial2(type, et_subscription_quantity.getText().toString().trim(), "crystal");

    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.im_add, R.id.im_reduce, R.id.bt_recharge_ordinary, R.id.bt_ordinary_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_add:
                int addTime = getTimes();
                if (addTime >= 50) {
                    return;
                }
                addTime++;
                et_subscription_quantity.setText(addTime + "");
                et_subscription_quantity.setSelection(et_subscription_quantity.getText().length());
                break;
            case R.id.im_reduce:
                int lessTime = getTimes();
                if (lessTime <= 1) {
                    return;
                }
                lessTime--;
                et_subscription_quantity.setText(lessTime + "");
                et_subscription_quantity.setSelection(et_subscription_quantity.getText().length());
                break;
            case R.id.bt_recharge_ordinary:
                if (!TextUtils.isEmpty(readCrystalAmount)) {
                    StartActivityManager.startCrystalRechargeActivity(context, readCrystalAmount);
                }
                break;
            case R.id.bt_ordinary_confirm:
                if(SoftKeyboardUtils.isSoftShowing(this)){//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                String trim = et_subscription_quantity.getText().toString().trim();
                if (!TextUtils.isEmpty(type)) {
                    getBuyFromOfficial2();
                }
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
     * 认购数量
     *
     * @return
     */
    public int getTimes() {

        String timeStr = et_subscription_quantity.getText().toString();
        if (timeStr.length() < 9) {
            return TextUtils.isEmpty(et_subscription_quantity.getText()) ? 1 : Integer.parseInt(timeStr);
        } else {
//            T.showShort("已是最大倍数");
            return 9999;
        }

    }
}
