package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
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
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.cards.CardFragmentPagerAdapter;
import com.iruiyou.pet.activity.cards.CardItem;
import com.iruiyou.pet.activity.cards.CardPagerAdapter;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.CrystalGoodsBean;
import com.iruiyou.pet.bean.MineRefreshBean;
import com.iruiyou.pet.bean.UserBean;
import com.iruiyou.pet.bean.WxPayMessage;
import com.iruiyou.pet.utils.AppRegister;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.RuleNoticeDialog;
import com.iruiyou.pet.utils.ShadowTransformer;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的-高级会员 作者：sgf
 */
public class SeniorMemberActivity extends BaseActivity implements CompoundButton
        .OnCheckedChangeListener {
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
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.cardTypeBtn)
    Button mButton;
    @BindView(R.id.tv_membership_content)
    TextView tv_membership_content;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;
    private CrystalGoodsBean crystalGoodsBean;
    //    private String pbs_cny0;
//    private String pbs_cny1;
//    private String pbs_cny3;
    private MineRefreshBean mineRefreshBean;
//    private String pbs_cny2;
//    private String pbs_cny4;


    private String vipOrderId;
    private String keruyunToken = "";
    private boolean isPartner = false;
    private UserBean userBean;
    private String amount;
//    private IWXAPI api;
    @Override
    public int getLayout() {
        return R.layout.activity_senior_member;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        EventBusUtils.getInstance().register(this);
        Intent intent = getIntent();
        if (intent != null) {
            isPartner = intent.getBooleanExtra("isPartner", false);
        }
        ButterKnife.bind(this);
        if(!isPartner){
            setTitle(getResources().getString(R.string.open_membership));
            getKeruyunToken();
        }else{
            setTitle("成为合伙人");
        }
        getUserInfo();
        getGoods();/*        initDta();*/
    }


    private void getGoods() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if (StringUtil.isNotEmpty(resulte)) {
                    crystalGoodsBean = GsonUtils.parseJson(resulte, CrystalGoodsBean.class);
                    if ((crystalGoodsBean != null) && (crystalGoodsBean.getStatusCode() == Constant.SUCCESS) && (crystalGoodsBean.getData() != null) && (crystalGoodsBean.getData().size() > 0)) {
                        List<CrystalGoodsBean.DataBean> dataBeanList = crystalGoodsBean.getData();
                        List<CrystalGoodsBean.DataBean> value = new ArrayList<>();
                        for (CrystalGoodsBean.DataBean dataBean : dataBeanList) {
                            if (dataBean.getVipLevel() != 2 && (dataBean.getVipLevel() != 4)) {
                                value.add(dataBean);
                            }
                        }
                        crystalGoodsBean.setData(value);
                        getData();
                    }
//                     Log.e("test","resulte is "+resulte);
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).memberGoos();
    }

    /**
     * 获取客如云token
     */
    private void getKeruyunToken(){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject = new JSONObject(resulte);
                        if(Constant.SUCCESS == jsonObject.optInt("statusCode")){
                            keruyunToken = jsonObject.getJSONObject("data").getJSONObject("result").optString("token");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
//                GlobalLog.e("test","getGoodById resulte is :"+resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, SeniorMemberActivity.this).keruyunGetToken();
    }


    /**
     * 创建购买客如云订单号
     */
    private void createKeruyunOrderID(){
        vipOrderId = "";
        if(StringUtil.isNotEmpty(keruyunToken)){
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    if(StringUtil.isNotEmpty(resulte)){
                        try {
                            JSONObject jsonObject = new JSONObject(resulte);
                            if(Constant.SUCCESS == jsonObject.optInt("statusCode")){
                                vipOrderId = jsonObject.getString("data");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            vipOrderId = "";
                        }finally {
                            if(StringUtil.isNotEmpty(vipOrderId)){
                                DialogUtils.showRuleDialog(SeniorMemberActivity.this, new RuleNoticeDialog.KnowClick() {
                                    @Override
                                    public void onClick() {
                                        // 统一下单接口
                                        unifiedorderForApp();
                                    }
                                });
                            }
                        }
                    }
//                GlobalLog.e("test","getGoodById resulte is :"+resulte);
                }

                @Override
                public void onError(ApiException e) {

                }
            }, SeniorMemberActivity.this).createKeruyunOrderID("buyMember","810094162",keruyunToken);
        }else{

        }

    }

    // 调起统一下单接口
    private void unifiedorderForApp(){
        if(StringUtil.isNotEmpty(vipOrderId)){
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    if(StringUtil.isNotEmpty(resulte)){
                        try {
                            JSONObject jsonObject = new JSONObject(resulte);
                            if(200 == jsonObject.optInt("status")){
                                AppRegister.sendPay(jsonObject.getJSONObject("data"), SeniorMemberActivity.this);
                            }else{
                                T.showShort("下单失败！");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onError(ApiException e) {
                    T.showShort("下单失败！");
                }
            }, SeniorMemberActivity.this).unifiedorderForApp(userBean.getData().getRealName(),amount,vipOrderId);
        }
    }


    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                mineRefreshBean = GsonUtils.parseJson(resulte, MineRefreshBean.class);
                if ((mineRefreshBean != null) && mineRefreshBean.getStatusCode() == Constant.SUCCESS && (mineRefreshBean.getData() != null)) {
                    if (mineRefreshBean.getData().getUserInfo() != null) {


//                        pbs_cny0 = "18.8";
//                        pbs_cny1 = "1000";
//                        pbs_cny3 = "10000";
//                                pbs_cny2 = "50000";
//                                pbs_cny4 = "250000";

                        /*tv_pbs_rmb.setText(Constant.CURRENY2 + pbs_cny);//1PBS-RMB*/
                        String name = getIntent().getStringExtra("name");
                        String headIm = getIntent().getStringExtra("headIm");
                        tv_membership_content.setText(getString(R.string.membership_content, name));
                        checkBox.setOnCheckedChangeListener(SeniorMemberActivity.this);
                        mCardAdapter = new CardPagerAdapter(SeniorMemberActivity.this);/*
                                        String string1 = getString(R.string.senior_member1,
                                        pbs_cny1); String string2 = getString(R.string
                                        .senior_member1, pbs_cny1); String string3 = getString(R
                                        .string.senior_member1, pbs_cny1); 高级会员*/


                        if (!isPartner) {
                            String chuji1 = getResources().getString(R.string.chuji5);
                            mCardAdapter.addCardItem(new CardItem("5", name, headIm, R.string
                                    .open_membership, R.string.primary_member_rights, R.string
                                    .primary_member, 0, R.string
                                    .primary_member_price2, R.drawable.vip_membership_card, 0, 0, 0, 0, 0, 0, 0, R
                                    .string.senior_member, chuji1, R.string.chuji1, R
                                    .string.chuji3, R.string.chuji4, R.string.chuji2, 0, 0));/*VIP会员*/

                            String chuji2_1 = getResources().getString(R.string.chuji2_1);
                            mCardAdapter.addCardItem(new CardItem("1", name, headIm, R.string
                                    .open_membership, R.string.my_senior_member, R.string
                                    .my_senior_member1, 0, R.string
                                    .senior_member_price2,
                                    R.drawable.senior_member_card, 0, 0, 0, 0, 0, 0, 0, R
                                    .string.senior_member, chuji2_1, R.string.chuji2_2, R
                                    .string.chuji2_3, R.string.chuji2_4, R.string.chuji2_5, R.string.chuji2_6, R.string.chuji2_8));/*VIP会员*/


                            String chuji3_1 = getResources().getString(R.string.chuji3_1) + " " + getResources().getString(R.string.chuji3_2);
                            /*baijin会员*/
                            mCardAdapter.addCardItem(new CardItem("3", name, headIm, R.string
                                    .open_membership, R.string.my_baijin_member, R.string
                                    .my_baijin_member1, 0, R.string
                                    .baijin_member_price2, R.drawable.corporate_membership, 0, 0, 0, 0, 0, 0, 0,
                                    R.string.baijin_membership, chuji3_1, R.string.chuji3_3, R.string.chuji3_4, R.string.chuji3_5, R.string.chuji3_6, R.string.chuji3_7, R.string.chuji3_8));


                            String chuji4_1 = getResources().getString(R.string.chuji4_1) + " " + getResources().getString(R.string.chuji4_2);
                            /*baijin会员*/
                            mCardAdapter.addCardItem(new CardItem("8", name, headIm, R.string
                                    .open_membership, R.string.my_zuanshi_price, R.string
                                    .my_zuanshi, 0, R.string
                                    .zuanshi_member_price, R.drawable.zuanshi_membership, 0, 0, 0, 0, 0, 0, 0,
                                    R.string.baijin_membership, chuji4_1, R.string.chuji4_3, R.string.chuji4_4, R.string.chuji4_5, R.string.chuji4_6, R.string.chuji4_7, R.string.chuji4_8));

                        } else {
                          /*  *//*机遇空间合伙人*//*
                            mCardAdapter.addCardItem(new CardItem("6", name, headIm, R.string
                                    .open_membership, R.string.my_vip_jiyukongjian_quanyi, R.string
                                    .my_vip_jiyukongjian, R.string.string_empty, R.string
                                    .vip_member_price2, R.drawable.icon_jiyu_hh_bg, R.drawable
                                    .icon_xianjin_hongbao, R.drawable.icon_peoples, 0, 0,
                                    0, 0, 0, R
                                    .string.vip_member,
                                    "享受每月实体店利润分成", R.string.vip_jiyu_2, 0, 0, 0, 0, 0));
*/
                            /*机遇空间股东*/
                            mCardAdapter.addCardItem(new CardItem("7", name, headIm, R.string
                                    .open_membership, R.string.my_jiyukongjian_member, R.string
                                    .my_vip_jiyukongjian_partner, R.string.string_empty, R.string
                                    .enterprise_member_price2, R.drawable.icon_jiyu_gd_bg, R
                                    .drawable.icon_xianjin_hongbao, R.drawable.icon_gongshang, R
                                    .drawable.icon_peoples, 0, 0, 0,
                                    0, R
                                    .string.corporate_membership, "享受每月实体店利润分成", R.string
                                    .vip_jiyu_6, R.string.vip_jiyu_5, 0, 0, 0, 0));
                        }





                        /*
                          mCardAdapter.addCardItem(new CardItem(R.string
                                  .corporate_membership, R.string.corporate_membership1, R.string
                                  .corporate_membership2, R.string.corporate_membership3, R
                                  .string.corporate_membership5, R.string.corporate_membership6,
                                  R.string.corporate_membership7,0)); mCardAdapter.addCardItem
                                  (new CardItem(R.string.understand, R.string.understand));*/
                        mFragmentCardAdapter = new CardFragmentPagerAdapter
                                (getSupportFragmentManager(), dpToPixels(2, SeniorMemberActivity
                                        .this));
                        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
                        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager,
                                mFragmentCardAdapter);
                        mViewPager.setAdapter(mCardAdapter);
                        mViewPager.setPageTransformer(false, mCardShadowTransformer);
                        mViewPager.setOffscreenPageLimit(3);
                        mCardShadowTransformer.enableScaling(true);/*设置当前页面突出
                        mFragmentCardShadowTransformer.enableScaling(false);*/
                        mCardAdapter.setOnButtonClickListener(new CardPagerAdapter
                                .OnButtonClickListener() {
                            @Override
                            public void onButtonClick(int position) {
                                CardItem  cardItem = mCardAdapter.getItem(position);
                                if ((crystalGoodsBean != null) && (crystalGoodsBean.getStatusCode() == Constant.SUCCESS) &&
                                        (crystalGoodsBean.getData() != null) && (crystalGoodsBean.getData().size() > position)) {
                                    if(isPartner){
                                        DialogUtils.showRuleDialog(SeniorMemberActivity.this, new RuleNoticeDialog.KnowClick() {
                                            @Override
                                            public void onClick() {
                                                StartActivityManager.startVIPSelectActivity(SeniorMemberActivity.this, crystalGoodsBean.getData().get(position).getVipLevel(), crystalGoodsBean.getData().get(position).getUrl());
                                            }
                                        });

                                   //    StartActivityManager.startVIPSelectActivity(SeniorMemberActivity.this, crystalGoodsBean.getData().get(position).getVipLevel(), crystalGoodsBean.getData().get(position).getUrl());
                                    }else{
                                        switch (cardItem.getType()){
                                            case "5":
                                                amount = "1880";
                                                break;
                                            case "1":
                                                amount = "39900";
                                                break;
                                            case "3":
                                                amount = "1000000";
                                                break;
                                            case "8":
                                                amount = "3000000";
                                                break;
                                                default:
                                                    amount="";
                                                    break;
                                        }
                                        if(userBean!=null&& StringUtil.isNotEmpty(amount)){
                                            createKeruyunOrderID();
                                        }
                                    }
                                }
                            }
                        });

                        int vipLevel = mineRefreshBean.getData().getUserInfo().getVipLevel();
                        mCardAdapter.setVipLevel(vipLevel);
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, SeniorMemberActivity.this).mineRefresh();
    }

    @OnClick({R.id.cardTypeBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardTypeBtn:/*                if (!mShowingFragments) { mButton.setText("Views"); mViewPager.setAdapter(mFragmentCardAdapter); mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer); } else { mButton.setText("Fragments"); mViewPager.setAdapter(mCardAdapter); mViewPager.setPageTransformer(false, mCardShadowTransformer); } mShowingFragments = !mShowingFragments;*/
                break;/*            case R.id.llPraise: StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"http://pbase.io"); break;*/
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

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }

    //接收事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(WxPayMessage wxPayMessage) {
        if(wxPayMessage!=null){
            if(Constant.SUCCESS == wxPayMessage.getCode()){
                T.showShort("支付成功!");
                if(StringUtil.isNotEmpty(vipOrderId)&&userBean!=null){
                    startPollMember();
                }
            }else if(-2 == wxPayMessage.getCode()) {
                T.showShort("用户取消支付!");
            }else if(StringUtil.isNotEmpty(wxPayMessage.getMessage())){
                T.showShort(wxPayMessage.getMessage());
            }else {
                T.showShort("支付错误!");
            }
        }
    }

    @Override
    protected void onDestroy() {
        EventBusUtils.getInstance().unregister(this);
        super.onDestroy();
    }


    private void startPollMember(){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                   BaseBean baseBean = GsonUtils.parseJson(resulte, BaseBean.class);
                   if(Constant.SUCCESS == baseBean.getStatusCode()){
                       T.showShort("购买成功");
                   }else{
                       T.showShort("购买成功");
                   }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, SeniorMemberActivity.this).startPollMember(userBean.getData().getName(),userBean.getData().getPhone(),amount,vipOrderId);
    }


    private void getUserInfo(){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                    userBean = GsonUtils.parseJson(resulte, UserBean.class);
                    if(userBean.getStatusCode() != Constant.SUCCESS){
                        userBean = null;
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                userBean = null;
            }
        }, SeniorMemberActivity.this).getUserInfoNew(SharePreferenceUtils.getBaseSharePreference().readInviteCode());
    }


}
