package com.iruiyou.pet.activity;

import android.annotation.SuppressLint;
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
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BuyVipBean;
import com.iruiyou.pet.bean.event.EventBuyVip;
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
 * 首页-我的钱包-开通会员
 * 作者：sgf
 */
public class OpenMembershipActivity extends BaseActivity {
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
    @BindView(R.id.tv_crystal_balance)
    TextView tv_crystal_balance;
    @BindView(R.id.tv_membership)
    TextView tv_membership;
    @BindView(R.id.tv_expected_daily_rate_item)
    TextView tv_expected_daily_rate_item;

    @BindView(R.id.linear_area)
    LinearLayout linear_area;

    @BindView(R.id.text_year)
    TextView text_year;

    @BindView(R.id.text_count)
    TextView text_count;

    private Context context;
    private String readCrystalAmount;
    private double buyCrystalCount;
    private int membershipType;
    int buyType = 0;
    @Override
    public int getLayout() {
        return R.layout.activity_open_membership;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = OpenMembershipActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
//        et_subscription_quantity_advanced.addTextChangedListener(advancedWatcher);
        membershipType = getIntent().getIntExtra("membershipType",0);
        if(membershipType ==5){
            setTitle(getResources().getString(R.string.primary_member));
            tv_expected_daily_rate_item.setText(getString(R.string.primary_member_price2));
            tv_membership.setText(getString(R.string.primary_member));
            buyCrystalCount=100;
        }else if(membershipType == 1){
            setTitle(getResources().getString(R.string.my_senior_member1));
            tv_expected_daily_rate_item.setText(getString(R.string.senior_member_price2));
            tv_membership.setText(getString(R.string.my_senior_member1));
            buyCrystalCount=1000;
        }else if(membershipType == 2){
            setTitle(getResources().getString(R.string.my_vip_member1));
            tv_expected_daily_rate_item.setText(getString(R.string.vip_member_price2));
            tv_membership.setText(getString(R.string.my_vip_member1));
            buyCrystalCount=5000;
        }else if(membershipType == 3){
            setTitle(getResources().getString(R.string.my_baijin_member1));
            tv_expected_daily_rate_item.setText(getString(R.string.baijin_member_price2));
            tv_membership.setText(getString(R.string.my_baijin_member1));
            buyCrystalCount=10000;
        }else if(membershipType == 4){
            setTitle(getResources().getString(R.string.my_enterprise_member1));
            tv_expected_daily_rate_item.setText(getString(R.string.enterprise_member_price2));
            tv_membership.setText(getString(R.string.my_enterprise_member1));
            buyCrystalCount=50000;
        }/*else if(membershipType ==6){
            setTitle(getResources().getString(R.string.my_vip_jiyukongjian));
            tv_expected_daily_rate_item.setText(getString(R.string.vip_member_price2));
            tv_membership.setText(getString(R.string.my_vip_jiyukongjian));
            buyCrystalCount=10000;
            buyType =1;
//            text_year.setVisibility(View.GONE);
            text_count.setText("购买份数");
        }*/else if(membershipType ==7){
            setTitle(getResources().getString(R.string.my_vip_jiyukongjian_partner));
            tv_expected_daily_rate_item.setText(getString(R.string.enterprise_member_price2));
            tv_membership.setText(getString(R.string.my_vip_jiyukongjian_partner));
            buyCrystalCount=50000;
            buyType =1;
//            text_year.setVisibility(View.GONE);
            text_count.setText("购买份数");
        }
        et_subscription_quantity_advanced.setText("1");
        et_subscription_quantity_advanced.setSelection(et_subscription_quantity_advanced.getText().length());
        readCrystalAmount = SharePreferenceUtils.getBaseSharePreference().readCrystalAmount();
        tv_crystal_balance.setText(readCrystalAmount);
//        tv_crystal_portion.setText("10000" + getString(R.string.crystal_portion));
//        tv_incubation_time_item.setText(getString(R.string.incubation_time) + "180" + getString(R.string.day));
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

    @SuppressLint("StringFormatInvalid")
    @OnClick({R.id.bt_recharge_advanced, R.id.im_reduce, R.id.im_add, R.id.bt_advanced_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_recharge_advanced://充值
                StartActivityManager.startCrystalRechargeActivity(context, readCrystalAmount);
                break;
            case R.id.bt_advanced_confirm://确认
//                String trim = et_subscription_quantity_advanced.getText().toString().trim();
//                if (!TextUtils.isEmpty(trim)) {
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                String title = getString(R.string.prompt2);
                String content = null;
                if(membershipType == 1){
                     content = getString(R.string.my_senior_member2)+"\n"+getString(R.string.prompt3,et_subscription_quantity_advanced.getText().toString().trim());
                }else if(membershipType == 2){
                    content = getString(R.string.my_vip_member2)+"\n"+getString(R.string.prompt3,et_subscription_quantity_advanced.getText().toString().trim());
                }else if(membershipType == 3){
                    content = getString(R.string.my_baijin_member2)+"\n"+getString(R.string.prompt3,et_subscription_quantity_advanced.getText().toString().trim());
                }else if(membershipType == 4){
                    content = getString(R.string.my_enterprise_member2)+"\n"+getString(R.string.prompt3,et_subscription_quantity_advanced.getText().toString().trim());
                }else if(membershipType == 5){
                    content = getString(R.string.primary_member)+"\n"+getString(R.string.prompt3,et_subscription_quantity_advanced.getText().toString().trim());
                }else if(membershipType == 6){
                    content = "脉场空间合伙人";
                }else if(membershipType == 7){
                    content = "脉场空间股东";
                }
//                boolean isVisibleCancel = true;//true显示两个控件，否则1显示个
                int noStrs = R.string.cancel;
                int yesStrs = R.string.set3;
                new SelfDidalog(context, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                    @Override
                    public void onYesClick() {
                        getBuyVip(membershipType);
                    }

                    @Override
                    public void onNoClick() {
                        bt_advanced_confirm.setClickable(true);
                    }
                }).show();
                bt_advanced_confirm.setClickable(false);
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
     * 获取购买会员
     */
    private void getBuyVip(int membershipType) {
        setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
        DialogUtil.getInstance().showLoadingDialog(this);



        new UserTask(new HttpOnNextListener() {
            @Override

            public void onNext(String resulte, String method) {

                BuyVipBean bean = GsonUtils.parseJson(resulte, BuyVipBean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
                    T.showShort(bean.getMessage());
                    SharePreferenceUtils.getBaseSharePreference().saveVipLevel(bean.getData());
                    double readCrystalAmountValue=Double.valueOf(readCrystalAmount)-buyCrystalCount;
                    SharePreferenceUtils.getBaseSharePreference().saveCrystalAmount(readCrystalAmountValue+"");
                    EventBuyVip eventBuyVip=new EventBuyVip();
                    eventBuyVip.setSpendingCrystal(buyCrystalCount);
                    eventBuyVip.setVipLeve(bean.getData());
                    finish();
                } else if (bean.getStatusCode() == Constant.TIPS1) {
                    bt_advanced_confirm.setClickable(true);
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
                setWithdrawalBackground(true, R.drawable.bg_blue_corner);
                DialogUtil.getInstance().closeLoadingDialog();

            }

            @Override
            public void onError(ApiException e) {
                setWithdrawalBackground(true, R.drawable.bg_blue_corner);
                DialogUtil.getInstance().closeLoadingDialog();
                bt_advanced_confirm.setClickable(true);
            }
        }, this).buyVip(membershipType, et_subscription_quantity_advanced.getText().toString().trim(),buyType);

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
    /**
     * 设置提取资产按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setWithdrawalBackground(boolean type, int background) {
        bt_advanced_confirm.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        bt_advanced_confirm.setBackgroundDrawable(drawable);
    }
}
