package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.MyCrystalAdapter;
import com.iruiyou.pet.adapter.MyWalletAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BroadEvent;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.CrystalVsPbsBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.bean.UsdtPbsBean;
import com.iruiyou.pet.bean.YzLoginBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.GoTopScrollView;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.ProfileInfoDialog;
import com.iruiyou.pet.utils.SelfDidalog;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：我的钱包
 * 作者：jiaopeirong on 2018/8/7 23:19
 * 邮箱：chinajpr@163.com
 */
public class MyWalletActivity extends BaseActivity {

    @BindView(R.id.text_xunibi_count)
    TextView textXunibiCount;

    @BindView(R.id.text_crash_count)
    TextView textCrashCount;

    @BindView(R.id.ll_fixed_income_wallet)
    LinearLayout ll_fixed_income_wallet;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.llTvBg)
    LinearLayout llTvBg;
//    @BindView(R.id.title_right_text2)
//    TextView title_right_text2;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerView_crystal)
    RecyclerView recyclerView_crystal;
    @BindView(R.id.walletTv)
    TextView walletTv;
    @BindView(R.id.frozenTv)
    TextView frozenTv;
    @BindView(R.id.llWalletNodata)
    LinearLayout llWalletNodata;
    @BindView(R.id.im_help)
    ImageView im_help;//帮助
    @BindView(R.id.tv_assets_valuation)
    TextView tv_assets_valuation;//我的资产估值
    @BindView(R.id.tv_wallet_pbs_num)
    TextView tv_wallet_pbs_num;//我的PBS数量
    @BindView(R.id.tv_wallet_crystal_num)
    TextView tv_wallet_crystal_num;//我的水晶数量
//    @BindView(R.id.ll_exchange_crystal)
//    LinearLayout ll_exchange_crystal;//PBS兑换水晶按钮
    @BindView(R.id.ll_crystal_recharge)
    LinearLayout ll_crystal_recharge;//水晶充值
    @BindView(R.id.tv_understand)
    TextView tv_understand;//了解详情
    @BindView(R.id.im_close)
    ImageView im_close;//关闭兑换水晶界面
    @BindView(R.id.ll_crystal_recharge_all)
    LinearLayout ll_crystal_recharge_all;//要关闭兑换水晶界面
    @BindView(R.id.bt_wallet_exchange)
    Button bt_wallet_exchange;//兑换按钮
    @BindView(R.id.etSearch_wallet)
    EditText etSearch_wallet;//输入PBS数量来兑换水晶
    @BindView(R.id.tv_wallet_exchange_crystal_num)
    TextView tv_wallet_exchange_crystal_num;//计算后的水晶值
    @BindView(R.id.ll_help_wallet)
    LinearLayout ll_help_wallet;//帮助
    @BindView(R.id.ll_all)
    LinearLayout ll_all;
    @BindView(R.id.bt_pbs_wallet)
    Button bt_pbs_wallet;
    @BindView(R.id.bt_crystal_wallet)
    Button bt_crystal_wallet;
    @BindView(R.id.tv_pbs_to_rmb)
    TextView tv_pbs_to_rmb;//pbs兑换人民币
    @BindView(R.id.tv_crystal_to_rmb)
    TextView tv_crystal_to_rmb;//水晶兑换人民币
    @BindView(R.id.refreshLayout_mywallet)
    SmartRefreshLayout refreshLayout_mywallet;
    @BindView(R.id.tv_pbs_usdt)
    TextView tv_pbs_usdt;
    @BindView(R.id.tv_pbs_rmb)
    TextView tv_pbs_rmb;
    @BindView(R.id.tv_pbs_tip)
    TextView tv_pbs_tip;
    @BindView(R.id.bt_wallet_ratio)
    Button bt_wallet_ratio;
    @BindView(R.id.ll_internal_revenue)
    LinearLayout ll_internal_revenue;
    @BindView(R.id.ll_high_save)
    LinearLayout ll_high_save;
    @BindView(R.id.ll_hight_save2)
    LinearLayout ll_hight_save2;
    @BindView(R.id.ll_hight_save)
    LinearLayout ll_hight_save;
    @BindView(R.id.tv_fixed_deposit_crystal)
    TextView tv_fixed_deposit_crystal;
    @BindView(R.id.tv_fixed_deposit_pbs)
    TextView tv_fixed_deposit_pbs;
   /* @BindView(R.id.ll_all_profit)
    LinearLayout ll_all_profit;*/
    @BindView(R.id.tv_total_pbs)
    TextView tv_total_pbs;
    @BindView(R.id.tv_promotional_assets)
    TextView tv_promotional_assets;
    @BindView(R.id.tv_incubation_assets)
    TextView tv_incubation_assets;
    @BindView(R.id.tv_frozen_crystal)
    TextView tv_frozen_crystal;
    @BindView(R.id.tv_text_exchange2)
    TextView tv_text_exchange2;
    @BindView(R.id.tv_text_exchange1)
    TextView tv_text_exchange1;
    @BindView(R.id.ll_pbs_exchange_crystal)
    LinearLayout ll_pbs_exchange_crystal;

    @BindView(R.id.ll_pbs_incubation)// | pbs孵化  section title
            View ll_pbs_incubation;
    @BindView(R.id.ll_pbs_incubation2)// | pbs孵化 列表
            View ll_pbs_incubation2;
    @BindView(R.id.ll_deposit_funds)// | 存入资产  section title
            View ll_deposit_funds;
    @BindView(R.id.ll_index_extraction)// 提取资产  section title
            View ll_index_extraction;

   /*

    @BindView(R.id.linear_promotional_benefits)// 推广收益  section title
            View linear_promotional_benefits;
    */


    @BindView(R.id.ll_invite_friends_wallet)// 邀请好友  section title
            View ll_invite_friends_wallet;

    @BindView(R.id.tv_wallet_expected_daily_rate_30)
    TextView tv_wallet_expected_daily_rate_30;
    @BindView(R.id.tv_incubation_time_30)
    TextView tv_incubation_time_30;

    @BindView(R.id.tv_wallet_expected_daily_rate_90)
    TextView tv_wallet_expected_daily_rate_90;
    @BindView(R.id.tv_incubation_time_90)
    TextView tv_incubation_time_90;

    @BindView(R.id.tv_wallet_expected_daily_rate_180)
    TextView tv_wallet_expected_daily_rate_180;
    @BindView(R.id.tv_incubation_time_180)
    TextView tv_incubation_time_180;

    @BindView(R.id.im_top_wallet)
    ImageView im_top_wallet;
    @BindView(R.id.tv_exchange_introduction)
    TextView tv_exchange_introduction;//兑换介绍
    @BindView(R.id.wallet_GoTopScrollView)
    GoTopScrollView wallet_GoTopScrollView;
    @BindView(R.id.tv_discount_locked_quantity_pbs)
    TextView tv_discount_locked_quantity_pbs;
    @BindView(R.id.tv_discount_locked_quantity_crystal)
    TextView tv_discount_locked_quantity_crystal;
    private MyWalletAdapter myWalletAdapter;
    private String pbsPrice;
    private String yzCrystal;
    private MyCrystalAdapter myCrystalAdapter;
    private String pbs2Crystal;
    private String pbs2Crystal_exchange_rate;
    private HarvestBean harvestBean;
    private int crystalRecharge = 0;
    private int distinguish = 0;//用于区分点击的按钮
    private int pbsCrystalRecharge = 0;
    private Context context;
    private String crystalAmount;
    private String pbs_cny;
    private String div_pbs;
    private String addComma;
    private String pbsAmount1;
    private String userChannle;
    private String hatchAmount;
    private String add_deposit_futures_tran_sellTrade;
    private UsdtPbsBean usdtPbsBean;
    private String rebateCrystal;
    private int vipLevel;

    @Override
    public int getLayout() {
        return R.layout.activity_my_wallet;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        EventBusUtils.getInstance().register(this);
        context = MyWalletActivity.this;
        setTitle(getResources().getString(R.string.myWallet));
//        title_right_text2.setText(R.string.assets3);
//        title_right_text2.setTextColor(getResources().getColor(R.color._26c68a));
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
        /**
         * crystal2Pbs: 9, // 一个水晶-》9个pbs
         pbs2Crystal: 10, // 10个pbs-》一个水晶
         pbsPrice: 0.1, // PBS标价
         */
        wallet_GoTopScrollView.smoothScrollTo(0, 0);//默认置顶
        wallet_GoTopScrollView.setScrollListener(im_top_wallet);//设置一键置顶图标
        //获取缓存的数据-职业
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&(configBean.getData()!=null))
        {
            ConfigBean.DataBean data = configBean.getData();
            pbsPrice = data.getPbsPrice();
            pbs2Crystal = data.getPbs2Crystal();
            yzCrystal = data.getYzCrystal();
        }


//        String crystal2Pbs = data.getCrystal2Pbs();
        if(!TextUtils.isEmpty(pbs2Crystal)){//  1/10
            pbs2Crystal_exchange_rate = BigDecimalUtil.div("1", pbs2Crystal, 1);
//            tv_exchange_introduction.setText(getString(R.string.exchange2) + pbs2Crystal_exchange_rate + getString(R.string.crystal));
//            tv_wallet_exchange_crystal_num.setText(pbs2Crystal_exchange_rate);
            tv_wallet_exchange_crystal_num.setText("0");
        }
        //有赞链接

        etSearch_wallet.addTextChangedListener(pbsWatcher);
        etSearch_wallet.setSelection(etSearch_wallet.getText().length());
//        myWalletAdapter = new MyWalletAdapter(this);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(myWalletAdapter);
//        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
//        recyclerView_crystal.setNestedScrollingEnabled(false);//禁止滑动
//        autoScrollView(ll_all, ll_crystal_recharge_all);//弹出软键盘时滚动视图  处理ScrollView中的EditText被键盘遮挡的问题

        userChannle = SharePreferenceUtils.getBaseSharePreference().readUserChannel();//获取渠道
        if(StringUtil.isNotEmpty(userChannle)){
            setSettingUpChannels(8,8,8,View.VISIBLE,8,View.VISIBLE,8,8,0);
        }else {
          //  linear_promotional_benefits.setVisibility(View.INVISIBLE);
        }
//        if ("".equals(userChannle)) {
//            //无渠道的会员
//        } else if("pets".equals(userChannle) || "niyang".equals(userChannle)){
//            setSettingUpChannels(0,8,8,8,8,0,8,8,8);
//        } else {
//            // yuanyun kaven tianjin hainan
//            setSettingUpChannels(8,8,8,0,8,0,8,8,0);
//        }
        getRefresh();
        ///----------  王新亚 修改 ----start----------------
        timerAction();
        ///----------  王新亚 修改 ----end----------------
    }
    ///----------  王新亚 修改 ----start----------------
    private  void timerAction(){
        getPBSData();
        requestData();
//        getDCProductList();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timerAction();
            }
        },75000);
    }
    ///----------  王新亚 修改 ----end----------------
///**
// * 获取定存产品
// * */
//    private void getDCProductList() {
//
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                if (StringUtil.isNotEmpty(resulte)) {
//                    DCProductListBean bean = GsonUtils.parseJson(resulte, DCProductListBean.class);
//                    if (bean != null) {
//                        if (bean.getStatusCode() == Constant.SUCCESS) {
//                            if (bean != null || bean.getData() != null) {
//
//                                String rateString = BigDecimalUtil.mul(bean.getData().get(0).getDayRate(), Constant.ONE_HUNDRED2, 2);
//
//                                tv_wallet_expected_daily_rate_30.setText(rateString + Constant.PERCENT_SIGN);
//                                tv_incubation_time_30.setText(getString(R.string.incubation_time) + bean.getData().get(0).getType() + Constant.DAY);
//                                rateString = BigDecimalUtil.mul(bean.getData().get(1).getDayRate(), Constant.ONE_HUNDRED2, 2);
////                        rate = Float.parseFloat(bean.getData().get(1).getDayRate()) * 100.0f;
//                                tv_wallet_expected_daily_rate_90.setText(rateString + Constant.PERCENT_SIGN);
//                                tv_incubation_time_90.setText(getString(R.string.incubation_time) + bean.getData().get(1).getType() + Constant.DAY);
//
//                                rateString = BigDecimalUtil.mul(bean.getData().get(2).getDayRate(), Constant.ONE_HUNDRED2, 2);
//                                tv_wallet_expected_daily_rate_180.setText(rateString + Constant.PERCENT_SIGN);
//                                tv_incubation_time_180.setText(getString(R.string.incubation_time) + bean.getData().get(2).getType() + Constant.DAY);
//
//                            }
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
//            }
//        }, this).getDepositGoods();
//
//    }

    /**
     * 获取pbs行情
     */
    private void getPBSData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                usdtPbsBean = GsonUtils.parseJson(resulte, UsdtPbsBean.class);
                if (usdtPbsBean.getStatusCode() == Constant.SUCCESS) {
                    if (usdtPbsBean != null || usdtPbsBean.getData() != null) {
                        String usdCNY = usdtPbsBean.getData().getUsdCNY().getLast();//usdt兑换人民币的汇率
                        String usdtPBS = usdtPbsBean.getData().getUsdtPBS().getLast();
                        String quoteVolume = usdtPbsBean.getData().getUsdtPBS().getQuoteVolume();
                        String percentChange = usdtPbsBean.getData().getUsdtPBS().getPercentChange();
                        if(percentChange.contains(Constant.BAR)){//判断涨跌的背景颜色
                            bt_wallet_ratio.setBackgroundResource(R.drawable.bg_red_corner);
                            bt_wallet_ratio.setText(percentChange+ Constant.PERCENT_SIGN);
                        }else {
//                            String[] split = percentChange.split(Constant.BAR);
                            bt_wallet_ratio.setBackgroundResource(R.drawable.bg_blue_corner);
                            bt_wallet_ratio.setText(Constant.ADD+percentChange+ Constant.PERCENT_SIGN);
                        }
//                        pbs_cny = BigDecimalUtil.mul(usdtPBS, usdCNY,Constant.SCALE_NUM);
                        pbs_cny = BigDecimalUtil.mul(usdtPBS, usdCNY, Constant.SCALE_NUM_Eight);
                        tv_pbs_rmb.setText(Constant.CURRENY2 + pbs_cny);//1PBS-RMB
                        tv_pbs_usdt.setText(usdtPBS + Constant.USDT);//1PBS-USDT
                        //1元 = 1水晶 =  1/(pbs2usdt * usd2cny)PBS
                        div_pbs = BigDecimalUtil.div("1", pbs_cny, Constant.SCALE_NUM_Eight);
                        tv_exchange_introduction.setText(getString(R.string.exchange6, div_pbs));
                        String div_quoteVolume = BigDecimalUtil.div(quoteVolume, "10000");//得到的成交量（万）除去10000
                        tv_pbs_tip.setText(getString(R.string.pbs_tips, BigDecimalUtil.addComma3(div_quoteVolume)));
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, this).getUsdtPbs();
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_mywallet.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                requestData();
                setPbsOrCrystalView(R.drawable.bg_blue_rectangle_white, R.color._72c6ae, R.drawable.bg_blue_rectangle, R.color.white,0,8);
            }
        });
    }

    /**
     * 请求pbs-水晶收支记录
     */
    private void requestData() {
        //pbs收支记录
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                harvestBean = GsonUtils.parseJson(resulte, HarvestBean.class);
                if (harvestBean.getStatusCode() == Constant.SUCCESS) {
                    if (harvestBean != null || harvestBean.getData() != null) {
//                        setViewGone();
//                        myWalletAdapter.setNewData(harvestBean.getData().getHarvestList());
                        //水晶收支记录
//                        myCrystalAdapter = new MyCrystalAdapter(MyWalletActivity.this);
//                        recyclerView_crystal.setLayoutManager(new LinearLayoutManager(MyWalletActivity.this));
//                        recyclerView_crystal.setAdapter(myCrystalAdapter);
//                        recyclerView_crystal.scrollToPosition(0);
//                        myCrystalAdapter.setNewData(harvestBean.getData().getCrystalRecords());
//                        myCrystalAdapter.notifyDataSetChanged();
                        pbsAmount1 = harvestBean.getData().getUserInfo().getLastHarvestAmount();
                        hatchAmount = harvestBean.getData().getUserInfo().getHatchAmount(); // 孵化PBS资产(定存、高级定存、定存收益、人脉收益)
                        rebateCrystal = harvestBean.getData().getUserInfo().getRebateCrystal();// 收益水晶（团队收益、高级定存返现）
                        vipLevel = harvestBean.getData().getUserInfo().getVipLevel();// 会员等级
                        tv_frozen_crystal.setText(getString(R.string.crystal2)+ Constant.ONE_SPACE+ BigDecimalUtil.addComma(rebateCrystal));
                        String pbsDrawLockedAmount = harvestBean.getData().getUserInfo().getPbsDrawLockedAmount();
                        if("0".equals(pbsDrawLockedAmount)){//PBS提现锁定数量 >0显示
//                            tv_discount_locked_quantity_crystal.setVisibility(View.GONE);
                            tv_discount_locked_quantity_pbs.setVisibility(View.GONE);
                        }else {
//                            tv_discount_locked_quantity_crystal.setVisibility(View.VISIBLE);
                            tv_discount_locked_quantity_pbs.setVisibility(View.VISIBLE);
                            String pbsDrawLockedAmounts = BigDecimalUtil.addComma4(pbsDrawLockedAmount);
                            tv_discount_locked_quantity_pbs.setText(getString(R.string.discount_locked_quantity)+ Constant.ONE_SPACE+pbsDrawLockedAmounts);
                        }

                        if(harvestBean.getData().getUserInfo().getCrystalDrawLockedAmount()>0)
                        {
                            tv_discount_locked_quantity_crystal.setText(getString(R.string.discount_locked_quantity)+ Constant.ONE_SPACE+harvestBean.getData().getUserInfo().getCrystalDrawLockedAmount());
                            tv_discount_locked_quantity_crystal.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            tv_discount_locked_quantity_crystal.setVisibility(View.GONE);
                        }

                        String lastHarvestAmount = harvestBean.getData().getUserInfo().getLastHarvestAmount();
                        crystalAmount = harvestBean.getData().getUserInfo().getCrystalAmount();
                        SharePreferenceUtils.getBaseSharePreference().saveCrystalAmount(crystalAmount);//保存水晶数量
                        if (!TextUtils.isEmpty(pbsAmount1)) {
//                            String pbsAmount = BigDecimalUtil.round(pbsAmount1, Constant.SCALE_NUM_FOUR);
                            addComma = BigDecimalUtil.addComma4(pbsAmount1);
                            tv_wallet_pbs_num.setText(addComma);//  walletTv
                            tv_wallet_crystal_num.setText(BigDecimalUtil.addComma(crystalAmount));//水晶数量
                        }
                        if (!TextUtils.isEmpty(crystalAmount)) {
//                            tv_crystal_to_rmb.setText(Constant.CURRENY + BigDecimalUtil.round(crystalAmount, Constant.SCALE_NUM));//水晶兑换人民币 1:1
                            String crystalAmounts = BigDecimalUtil.round(crystalAmount, Constant.SCALE_NUM);
                            String crystal_all = BigDecimalUtil.add(crystalAmounts, rebateCrystal);

                            tv_crystal_to_rmb.setText(Constant.CURRENY + BigDecimalUtil.addComma(crystal_all));//水晶兑换人民币 1:1
                        }
                        String pbsFrozen = harvestBean.getData().getUserInfo().getPbsFrozen();
                        String div_pbsPrice = BigDecimalUtil.div(pbsPrice, Constant.ONE_HUNDRED);//除法
//                        String mul_pbs = BigDecimalUtil.mul(lastHarvestAmount, div_pbsPrice, Constant.SCALE_NUM);//得到pbs的估值（pbs是分，需转换为元） 然后加水晶的估值

//                        tv_pbs_to_rmb.setText(Constant.CURRENY + BigDecimalUtil.mul(div_pbsPrice, pbsAmount1, Constant.SCALE_NUM));//pbs兑换人民币   pbsPrice*pbs
                        String mul_pbsorrmb = BigDecimalUtil.mul(div_pbsPrice, pbsAmount1, Constant.SCALE_NUM);
//                        tv_pbs_to_rmb.setText(Constant.CURRENY + BigDecimalUtil.addComma3(mul_pbsorrmb));//pbs兑换人民币   pbsPrice*pbs
//                        String pbs_CNY_num = BigDecimalUtil.mul(pbs_cny, pbsAmount1, Constant.SCALE_NUM);
                        String last_HarvestAmount_num = BigDecimalUtil.mul(pbs_cny, lastHarvestAmount);

                        String pbsFrozen_num = BigDecimalUtil.mul(pbs_cny, pbsFrozen);//冻结兑换人民币

                        //pbs定存数量
                        HarvestBean.DataBean data = harvestBean.getData();
                        String pbsDeposit = data.getPbsDeposit();// 定存PBS数量(kaven渠道)
                        String pbsFutures = data.getPbsFutures();// 期货兑换的PBS数量（yuanyuan渠道）
                        String pbsTran = data.getPbsTran();//定存PBS数量（niyang渠道）
                        String sellTrade = data.getSellTrade();//冻结PBS在内盘的挂单数量（pets渠道）
                        String userChannle =  SharePreferenceUtils.getBaseSharePreference().readUserChannel();
                        //得到所有渠道的和
                        String add_deposit_futures = BigDecimalUtil.add(pbsDeposit, pbsFutures);
                        String add_deposit_futures_tran = BigDecimalUtil.add(add_deposit_futures, pbsTran);
                        add_deposit_futures_tran_sellTrade = BigDecimalUtil.add(add_deposit_futures_tran, sellTrade);//定存总数
                        setFixedDepositPBS(add_deposit_futures_tran_sellTrade,8,0);//设置pbs定存数量

                        String kavenTeam = data.getKavenTeam();// 团队收益
                        if("yuanyuan".equals(userChannle))
                        {
                            tv_total_pbs.setText(BigDecimalUtil.addComma4(data.getYuanBack()));
                            tv_promotional_assets.setText(BigDecimalUtil.addComma4(data.getYuanRebate()));
                            tv_incubation_assets.setText(BigDecimalUtil.addComma4(data.getYuanTeam()));
                        }
                        else if(TextUtils.isEmpty(userChannle))
                        {
                            ll_fixed_income_wallet.setVisibility(View.GONE);
                        }
                        else
                        {
                            tv_total_pbs.setText(BigDecimalUtil.addComma4(data.getKavenBack()));//定存收益
                            tv_promotional_assets.setText(BigDecimalUtil.addComma4(data.getKavenRebate()));//人脉收益
                            tv_incubation_assets.setText(BigDecimalUtil.addComma4(data.getKavenTeam()));//团队收益
                        }

//                        if("pets".equals(userChannle)){//判断三个收益
////                            setFixedDepositPBS(sellTrade,8,0);
//                        }else if("".equals(userChannle)){
//                            //
//                        }else if("yuanyuan".equals(userChannle)){
//                            tv_total_pbs.setText(BigDecimalUtil.addComma4(data.getYuanBack()));
//                            tv_promotional_assets.setText(BigDecimalUtil.addComma4(data.getYuanRebate()));
//                            tv_incubation_assets.setText(BigDecimalUtil.addComma4(data.getYuanTeam()));
////                            setFixedDepositPBS(pbsFutures,8,0);
//                        }else if("niyang".equals(userChannle)){
////                            setFixedDepositPBS(pbsTran,8,0);
//                        } else {
//                            tv_total_pbs.setText(BigDecimalUtil.addComma4(data.getKavenBack()));//定存收益
//                            tv_promotional_assets.setText(BigDecimalUtil.addComma4(data.getKavenRebate()));//人脉收益
//                            tv_incubation_assets.setText(BigDecimalUtil.addComma4(data.getKavenTeam()));//团队收益
//                        }

                        if(crystalAmount.equals("0")&&last_HarvestAmount_num.equals("0")&&pbsFrozen_num.equals("0")&&addComma.equals("0")){
//                            tv_assets_valuation.setText("0");//相加后的总估值
                        }else {
//                            String mul = BigDecimalUtil.mul(addComma, pbs_cny);
                            String add_pbs_crystal = BigDecimalUtil.add(crystalAmount, last_HarvestAmount_num);//水晶价值+ 非冻结PBS价值

                            String add_all = BigDecimalUtil.add(add_pbs_crystal, pbsFrozen_num, Constant.SCALE_NUM);//加上冻结的价值
                            String mul_pbs_fixed_deposit = BigDecimalUtil.mul(add_deposit_futures_tran_sellTrade, pbs_cny);//定存的pbs和兑换长人民币
                            String mul_pbsDrawLockedAmount = BigDecimalUtil.mul(pbsDrawLockedAmount, pbs_cny);//PBS提现锁定数量兑换人民币
                            String add_all_final = BigDecimalUtil.add(add_all, mul_pbs_fixed_deposit, Constant.SCALE_NUM);//加上所有定存的pbs
                            String add_all_finals = BigDecimalUtil.add(mul_pbsDrawLockedAmount, add_all_final, Constant.SCALE_NUM);//PBS提现锁定数量兑换长人民币加上所有定存的pbs
                            String add_all_finalsz = BigDecimalUtil.add(add_all_finals, rebateCrystal, Constant.SCALE_NUM);//加上收益水晶
                            String addComma_all = BigDecimalUtil.addComma3(add_all_finalsz);
                            if("0".equals(kavenTeam)){
//                                tv_assets_valuation.setText(addComma_all);//相加后的总估值
                            }else {
//                                String add_all_finals_kavenTeam = BigDecimalUtil.add(add_all_finalsz, kavenTeam,Constant.SCALE_NUM);//加上kavenTeam的值
//                                tv_assets_valuation.setText(add_all_finalsz);//相加后的总估值
                            }
                        }
                        String mul_pbsDrawLockedAmount = BigDecimalUtil.mul(pbs_cny, pbsDrawLockedAmount);//提现中的PBS兑换人民币
                        String mul_pbs_fixed_deposit = BigDecimalUtil.mul(add_deposit_futures_tran_sellTrade, pbs_cny);//定存的pbs和兑换长人民币
                        String t_pbs__num = BigDecimalUtil.add(pbsFrozen_num, last_HarvestAmount_num);//冻结PBS价值兑换人民币价+ 非冻结PBS价值兑换人民币价
                        //提现中的PBS兑换人民币
                        String add_mul_pbsDrawLockedAmount = BigDecimalUtil.add(t_pbs__num, mul_pbsDrawLockedAmount);
                        String add_pbs_all = BigDecimalUtil.add(add_mul_pbsDrawLockedAmount, mul_pbs_fixed_deposit);
                        tv_pbs_to_rmb.setText(Constant.CURRENY + BigDecimalUtil.addComma3(add_pbs_all));//pbs兑换人民币   pbsPrice*pbs
//                        frozenTv.setText(getString(R.string.frozen) + Constant.ONE_SPACE + BigDecimalUtil.round(pbsFrozen,Constant.SCALE_NUM_FOUR));
                        frozenTv.setText(getString(R.string.frozen) + Constant.ONE_SPACE + BigDecimalUtil.addComma4(pbsFrozen));
                        if("0".equals(pbsFrozen)){//冻结pbs数大于0则显示
                            frozenTv.setVisibility(View.INVISIBLE);
                        }else {
                            frozenTv.setVisibility(View.VISIBLE);
                        }
                        if("0".equals(rebateCrystal)){
                            tv_frozen_crystal.setVisibility(View.INVISIBLE);
                        }else {
                            tv_frozen_crystal.setVisibility(View.VISIBLE);
                        }
                        if("0".equals(rebateCrystal)&&"0".equals(pbsFrozen)){
                            frozenTv.setVisibility(View.GONE);
                            tv_frozen_crystal.setVisibility(View.GONE);
                        }

                    }
                }
                totalCount();
                refreshLayout_mywallet.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_mywallet.finishRefresh(false/*,false*/);//传入false表示刷新失败
            }
        }, this).harvestList(true);

//        //水晶收支记录
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                L.d("sgf",resulte.toString()+"---sgf---99");
//                GetOrdersBean getOrdersBean = GsonUtils.parseJson(resulte, GetOrdersBean.class);
//                if(getOrdersBean.getStatusCode() == Constant.SUCCESS){
//                    if (getOrdersBean != null || getOrdersBean.getData() != null) {
//
////                        if (getOrdersBean.getData().getHarvestList().size() > 0) {
////                            llWalletNodata.setVisibility(View.GONE);
////                            recyclerView.setVisibility(View.VISIBLE);
////
////                        } else {
////                            llWalletNodata.setVisibility(View.VISIBLE);
////                            recyclerView.setVisibility(View.GONE);
////                        }
//                        List<GetOrdersBean.DataBean> data = getOrdersBean.getData();
//                        myCrystalAdapter.setNewData(data);
//                    }
//                }
//            }
//            @Override
//            public void onError(ApiException e) {
//
//            }
//        }, this).getOrders();
    }

    /**
     * 初始化数据
     */
    private void totalCount() {
        if ((harvestBean != null) && (harvestBean.getStatusCode() == Constant.SUCCESS) && (harvestBean.getData() != null)){
            //pbs定存数量
            HarvestBean.DataBean data = harvestBean.getData();
            String pbsCount = data.getUserInfo().getPbsAmount();
            String pbsDeposit = data.getPbsDeposit();// 定存PBS数量(kaven渠道)
            String pbsFutures = data.getPbsFutures();// 期货兑换的PBS数量（yuanyuan渠道）
            String pbsTran = data.getPbsTran();//定存PBS数量（niyang渠道）
            String sellTrade = data.getSellTrade();//冻结PBS在内盘的挂单数量（pets渠道）

            //得到所有渠道的和
            String shu = BigDecimalUtil.add(pbsDeposit,pbsCount);
            String add_deposit_futures = BigDecimalUtil.add(shu, pbsFutures);
            String add_deposit_futures_tran = BigDecimalUtil.add(add_deposit_futures, pbsTran);
            String add_deposit_futures_tran_sellTrade = BigDecimalUtil.add(add_deposit_futures_tran, sellTrade);//定存总数

            String hatchAmount = harvestBean.getData().getUserInfo().getHatchAmount();
            double crystalAmount = Double.valueOf(harvestBean.getData().getUserInfo().getCrystalAmount());
            double rebateCrystal = Double.valueOf(harvestBean.getData().getUserInfo().getRebateCrystal());

            double pbssAmount = Double.valueOf(add_deposit_futures_tran_sellTrade).doubleValue();
            double lastHarvestAmount = Double.valueOf(harvestBean.getData().getUserInfo().getLastHarvestAmount()).doubleValue();
            double pbsFrozen = Double.valueOf(harvestBean.getData().getUserInfo().getPbsFrozen()).doubleValue();
            double pbsDrawLockedAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsDrawLockedAmount()).doubleValue();

            double pbsAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsAmount()).doubleValue();

            textCrashCount.setText(BigDecimalUtil.addComma4(rebateCrystal));

            String countPBS = BigDecimalUtil.addComma4(pbssAmount + lastHarvestAmount+pbsFrozen + pbsDrawLockedAmount);

            textXunibiCount.setText(String.format("%.4f",pbsAmount));

            double totalPBS = Double.valueOf(pbs_cny).doubleValue() * (Double.valueOf(countPBS.replace(",","")).doubleValue());

            String allPrice = BigDecimalUtil.addComma4(rebateCrystal + pbsAmount + crystalAmount);

            tv_assets_valuation.setText(String.format("%.4f",allPrice));
        }
    }


    /**
     * 控件的显示隐藏
     */
    private void setViewGone() {
        if (harvestBean.getData().getHarvestList().size() > 0) {
            llWalletNodata.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

        } else {
            llWalletNodata.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    /**
     * @param root 最外层的View
     * @param scrollToView 不想被遮挡的View,会移动到这个Veiw的可见位置
     */
    private int scrollToPosition = 0;

    private void autoScrollView(final View root, final View scrollToView) {
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        Rect rect = new Rect();

                        //获取root在窗体的可视区域
                        root.getWindowVisibleDisplayFrame(rect);

                        //获取root在窗体的不可视区域高度(被遮挡的高度)
                        int rootInvisibleHeight = root.getRootView().getHeight() - rect.bottom;

                        //若不可视区域高度大于150，则键盘显示
                        if (rootInvisibleHeight > 150) {

                            //获取scrollToView在窗体的坐标,location[0]为x坐标，location[1]为y坐标
                            int[] location = new int[2];
                            scrollToView.getLocationInWindow(location);

                            //计算root滚动高度，使scrollToView在可见区域的底部
                            int scrollHeight = (location[1] + scrollToView.getHeight()) - rect.bottom;

                            //注意，scrollHeight是一个相对移动距离，而scrollToPosition是一个绝对移动距离
                            scrollToPosition += scrollHeight;

                        } else {
                            //键盘隐藏
                            scrollToPosition = 0;
                        }
                        root.scrollTo(0, scrollToPosition);

                    }
                });
    }

    /**
     * PBS输入监听
     */
    private TextWatcher pbsWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.length() > 0) {
//                String mul = BigDecimalUtil.mul(editable.toString(), pbs2Crystal_exchange_rate, Constant.SCALE_NUM);
//                String mul = BigDecimalUtil.mul(editable.toString(), pbs2Crystal);
                if(!TextUtils.isEmpty(div_pbs)){
                    String mul = BigDecimalUtil.mul(editable.toString(), div_pbs, Constant.SCALE_NUM_Eight);
                    setCrystalText(mul);
                }
            } else {
                tv_wallet_exchange_crystal_num.setText("0");
            }
        }
    };

    /**
     * 设置PBS兑换水晶的数量
     *
     * @param value 币的数量
     */
    private void setCrystalText(String value) {
        etSearch_wallet.removeTextChangedListener(pbsWatcher);
//        etSearch_wallet.setText(value);
        tv_wallet_exchange_crystal_num.setText(value);
        etSearch_wallet.addTextChangedListener(pbsWatcher);
    }

    @OnClick({R.id.linear_zhifubao, R.id.linear_bank_card, R.id.im_close, R.id.ll_pbs_exchange_crash, R.id.bt_wallet_exchange, R.id.tv_understand, R.id.im_help,
            R.id.ll_help_wallet, R.id.ll_crystal_recharge, R.id.bt_crystal_wallet, R.id.bt_pbs_wallet , R.id.ll_money_record,
            R.id.ll_pbs_incubation2, R.id.ll_pbs_incubation, R.id.ll_invite_friends_wallet,
            R.id.ll_index_extraction, R.id.ll_deposit_funds, R.id.ll_pbs_current_price, R.id.ll_internal_revenue, R.id.ll_high_save,
            R.id.ll_hight_save2, R.id.ll_hight_save, R.id.ll_deposit_funds2, R.id.ll_pbs_exchange_crystal, R.id.ll_fixed_income_wallet,
            R.id.ll_network_income_wallet, R.id.ll_team_profit_wallet, R.id.ll_transfer_accounts, R.id.ll_senior_members,
            R.id.linear_bqex_in})
    public void onViewClicked(View view) {
        if (harvestBean == null) {
            T.showShort("数据加载未完成");
            return;
        }
        switch (view.getId()) {
//            case R.id.ll_exchange_crystal://水晶兑换PBS 改 提取资产
//                distinguish = 2;
//                crystalRecharge++;
//                pbsCrystalRecharge = 0;
//                tv_text_exchange1.setText(getString(R.string.use));
//                tv_text_exchange2.setText(getString(R.string.exchange));
//                if(crystalRecharge%2==0){
//                    ll_crystal_recharge_all.setVisibility(View.GONE);
//                }else {
//                    ll_crystal_recharge_all.setVisibility(View.VISIBLE);
//                }
//                if(usdtPbsBean!=null&&(usdtPbsBean.getData()!=null)&&(usdtPbsBean.getData().getUsdtPBS()!=null)&&(usdtPbsBean.getData().getUsdCNY()!=null))
//                {
//                    StartActivityManager.startCrashSelectActivity(this,pbsAmount1,hatchAmount,
//                            usdtPbsBean.getData().getUsdtPBS().getLast(),rebateCrystal,usdtPbsBean.getData().getUsdCNY().getLast());
//                }
//                break;
            case R.id.ll_pbs_exchange_crystal://pbs兑换水晶--改 存入资产
//                distinguish = 1;
//                crystalRecharge = 0;
//                pbsCrystalRecharge++;
//                tv_text_exchange1.setText(getString(R.string.exchange));
//                tv_text_exchange2.setText(getString(R.string.need));
//                if(pbsCrystalRecharge%2==0){
//                    ll_crystal_recharge_all.setVisibility(View.GONE);
//                }else {
//                    ll_crystal_recharge_all.setVisibility(View.VISIBLE);
//                }
                StartActivityManager.startAssetsDeposited1Activity(this,rebateCrystal);
                break;
            case R.id.ll_senior_members://会员
                if(harvestBean!=null&&(harvestBean.getData()!=null)&&(harvestBean.getData().getUserInfo()!=null))
                {
                    StartActivityManager.startSeniorMemberActivity(context, SharePreferenceUtils.getBaseSharePreference().readNickName(),
                            harvestBean.getData().getUserInfo().getHeadImg(),vipLevel);
                }
                break;
            case R.id.im_close:
                if(SoftKeyboardUtils.isSoftShowing(this)){//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                ll_crystal_recharge_all.setVisibility(View.GONE);
                crystalRecharge = 0;
                pbsCrystalRecharge = 0;
                break;
            case R.id.bt_wallet_exchange:
//                T.showShort(getString(R.string.coming_soon));
                ///
                String title = getString(R.string.prompt);
//                int content = R.string.prompt_pbs;
                boolean isVisibleCancel = true;//true显示两个控件，否则1显示个
                int noStrs = R.string.cancel;
                int yesStrs = R.string.exchange;
                new SelfDidalog(MyWalletActivity.this, title, getString(R.string.prompt_pbs), true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                    @Override
                    public void onYesClick() {
                        String pbs_num = etSearch_wallet.getText().toString();
                        if(distinguish == 1){
                            if (!TextUtils.isEmpty(pbs_num)) {
                                getExchangeCrystalData(pbs_num,0);
                            } else {
                                getExchangeCrystalData("0",0);
                            }
                        }else if(distinguish == 2){
                            if (!TextUtils.isEmpty(pbs_num)) {
                                getExchangeCrystalData(pbs_num,1);
                            } else {
                                getExchangeCrystalData("0",1);
                            }
                        }
                    }
                    @Override
                    public void onNoClick() {

                    }
                }).show();
                ///

                break;
            case R.id.tv_understand:
                StartActivityManager.startWebViewNewActivity(this,getString(R.string.help), BaseApi.baseUrlNoApi + UrlContent.walletHelp);
                break;
            case R.id.im_help:
                StartActivityManager.startWebViewActivity(this,getString(R.string.help), BaseApi.baseUrlNoApi + UrlContent.walletHelp);
                break;
            case R.id.ll_help_wallet:
                StartActivityManager.startWebViewActivity(this,getString(R.string.help), BaseApi.baseUrlNoApi + UrlContent.walletHelp);
                break;
            case R.id.bt_crystal_wallet:
                setPbsOrCrystalView(R.drawable.bg_blue_rectangle, R.color.white, R.drawable.bg_blue_rectangle_white, R.color._72c6ae,8,0);
//                if (harvestBean.getData().getCrystalRecords().size() > 0) {
//                    llWalletNodata.setVisibility(View.GONE);
//                    recyclerView_crystal.setVisibility(View.VISIBLE);
//
//                } else {
//                    llWalletNodata.setVisibility(View.VISIBLE);
//                    recyclerView_crystal.setVisibility(View.GONE);
//                }
                break;
            case R.id.bt_pbs_wallet:
                setPbsOrCrystalView(R.drawable.bg_blue_rectangle_white, R.color._72c6ae, R.drawable.bg_blue_rectangle, R.color.white,0,8);
//                setViewGone();
                break;
            case R.id.ll_crystal_recharge:
//                getYzLoginData();
                if (!TextUtils.isEmpty(crystalAmount)) {
                    StartActivityManager.startCrystalRechargeActivity(context,crystalAmount);
                }
                break;
            case R.id.linear_bank_card:
                if(usdtPbsBean!=null&&(usdtPbsBean.getData()!=null)&&(usdtPbsBean.getData().getUsdtPBS()!=null)&&(usdtPbsBean.getData().getUsdCNY()!=null))
                {
                    boolean isStart = true;
                    ACache aCache = ACache.get(context);
                    LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
                    if(loginNewBean!=null&&loginNewBean.getData()!=null)
                    {
                        LoginNewBean.DataBean.BasicInfoBean basicInfo= loginNewBean.getData().getBasicInfo();
                        if(StringUtil.isNotEmpty(basicInfo.getPosition())&& StringUtil.isNotEmpty(basicInfo.getHeadImg())&&(basicInfo.getProfessionalIdentity()!=0)){
                            StartActivityManager.startWithdrawalOfAssetsActivity(MyWalletActivity.this, pbsAmount1,
                                    hatchAmount, usdtPbsBean.getData().getUsdtPBS().getLast(), rebateCrystal+"", usdtPbsBean.getData().getUsdCNY().getLast(), BqexTransactionActivity.ACCOUNT_TYPE_BANK);
                            isStart = false;
                        }

                        if(isStart){
                            DialogUtils.showKnowDialog(((BaseActivity) context), new ProfileInfoDialog.KnowClick() {
                                @Override
                                public void onClick() {
                                    StartActivityManager.startBaseicInfoActivity((BaseActivity)context,basicInfo);
                                }
                            });
                        }
                    }
                }
                break;
            case R.id.ll_pbs_exchange_crash:
                if(usdtPbsBean!=null&&(usdtPbsBean.getData()!=null)&&(usdtPbsBean.getData().getUsdtPBS()!=null)&&(usdtPbsBean.getData().getUsdCNY()!=null)){
                    StartActivityManager.startWithdrawalOfAssetsActivity(MyWalletActivity.this,pbsAmount1,
                            hatchAmount,usdtPbsBean.getData().getUsdtPBS().getLast(),rebateCrystal,usdtPbsBean.getData().getUsdCNY().getLast(), BqexTransactionActivity.ACCOUNT_TYPE_BIQUAN);
                }
                break;
            case R.id.linear_zhifubao:
                if(usdtPbsBean!=null&&(usdtPbsBean.getData()!=null)&&(usdtPbsBean.getData().getUsdtPBS()!=null)&&(usdtPbsBean.getData().getUsdCNY()!=null))
                {
                    boolean isStart = true;
                    ACache aCache = ACache.get(context);
                    LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
                    if(loginNewBean!=null&&loginNewBean.getData()!=null)
                    {
                        LoginNewBean.DataBean.BasicInfoBean basicInfo= loginNewBean.getData().getBasicInfo();
                        if(StringUtil.isNotEmpty(basicInfo.getPosition())&& StringUtil.isNotEmpty(basicInfo.getHeadImg())&&(basicInfo.getProfessionalIdentity()!=0)){
                            StartActivityManager.startWithdrawalOfAssetsActivity(MyWalletActivity.this, pbsAmount1+"",
                                    hatchAmount, usdtPbsBean.getData().getUsdtPBS().getLast(), rebateCrystal+"", usdtPbsBean.getData().getUsdCNY().getLast(), BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY);

                            isStart = false;
                        }

                        if(isStart){
                            DialogUtils.showKnowDialog(((BaseActivity) context), new ProfileInfoDialog.KnowClick() {
                                @Override
                                public void onClick() {
                                    StartActivityManager.startBaseicInfoActivity((BaseActivity)context,basicInfo);
                                }
                            });
                        }
                    }
                }
                break;
//            case R.id.title_right_text2://交易记录
//                StartActivityManager.startTransactionRecordActivity(this);
//                break;
            case R.id.ll_pbs_incubation://pbs孵化(普通定存宝入口)
            case R.id.ll_pbs_incubation2:
                StartActivityManager.startPbsIncubationActivity(this);
                break;
            case R.id.ll_invite_friends_wallet://邀请好友
                startActivity(InvitFriendActivity.class);
                break;




           /* case R.id.linear_promotional_benefits://推广收益
//                startActivity(PromotionalBenefitsActivity.class);
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.promotional_benefits),BaseApi.baseUrlNoApi+"deposit/team");
                if("pets".equals(userChannle) || "niyang".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.promotional_benefits), BaseApi.baseUrlNoApi+ Constant.PBS+ Constant.TEAM,true);
                }else if("".equals(userChannle)){

                }else if("yuanyuan".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.promotional_benefits), BaseApi.baseUrlNoApi+ Constant.FUTURES+ Constant.TEAM,true);
                } else {
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.promotional_benefits), BaseApi.baseUrlNoApi+ Constant.DEPOSIT+ Constant.TEAM,true);
                }
                break;*/




            case R.id.ll_index_extraction://提取资产
//                StartActivityManager.startWithdrawalOfAssetsActivity(this,pbsAmount1);
//                StartActivityManager.startResultsOfWithdrawalsActivity(this);
//                StartActivityManager.startCashWithdrawalActivity(this,"1212");
                break;
//            case R.id.ll_deposit_funds2://存入资产
//                StartActivityManager.startAssetsDeposited1Activity(this,pbsAmount1);
//                break;
            case R.id.ll_deposit_funds://我的定存宝
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.assets_deposited2),BaseApi.baseUrlNoApi+"deposit/wallet");
                if("pets".equals(userChannle) || "niyang".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.my_treasure), BaseApi.baseUrlNoApi+ Constant.PBS+ Constant.WALLET);
                }else if("".equals(userChannle)){
                    //
                }else if("yuanyuan".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.my_treasure), BaseApi.baseUrlNoApi+ Constant.FUTURES+ Constant.WALLET);
                } else {
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.my_treasure), BaseApi.baseUrlNoApi+ Constant.DEPOSIT+ Constant.WALLET);
                }
                break;
            case R.id.ll_pbs_current_price://pbs当前价格
                StartActivityManager.startWebViewNewActivity(this,getString(R.string.bqex_exchange2), UrlContent.bqex_link2);
                break;
            case R.id.ll_internal_revenue://内盘交易
                if("pets".equals(userChannle) || "niyang".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.internal_revenue), BaseApi.baseUrlNoApi+ Constant.PBS);
                }
                break;

            case R.id.ll_hight_save:
            case R.id.ll_hight_save2://高端定存宝

                StartActivityManager.startAdvancedDepositTreasureActivity(this,getString(R.string.pbs_hight_save));
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.pbs_hight_save),BaseApi.baseUrlNoApi+"futures/buy?target=0&type=180&currency=crystal");
                break;
            /*case R.id.ll_fixed_income_wallet://定存收益
                if("yuanyuan".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.total_pbs),BaseApi.baseUrlNoApi+Constant.FUTURES+Constant.PROFIT1);
                } else {
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.total_pbs),BaseApi.baseUrlNoApi+Constant.DEPOSIT+Constant.PROFIT1);
                }
                break;
            case R.id.ll_network_income_wallet://人脉收益
                if("yuanyuan".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.regional_assets),BaseApi.baseUrlNoApi+Constant.FUTURES+Constant.PROFIT2);
                } else {
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.regional_assets),BaseApi.baseUrlNoApi+Constant.DEPOSIT+Constant.PROFIT2);
                }
                break;
            case R.id.ll_team_profit_wallet://团队收益
                if("yuanyuan".equals(userChannle)){
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.incubation_assets),BaseApi.baseUrlNoApi+Constant.FUTURES+Constant.PROFIT3);
                } else {
                    StartActivityManager.startWebViewNewActivity(this,getString(R.string.incubation_assets),BaseApi.baseUrlNoApi+Constant.DEPOSIT+Constant.PROFIT3);
                }
                break;*/
            case R.id.ll_transfer_accounts://pbs转账
                StartActivityManager.startTransferAccountsActivity(this,pbsAmount1,add_deposit_futures_tran_sellTrade);
                break;
            case R.id.linear_bqex_in: // 币全转入
                StartActivityManager.startBQEXTurnInActivity(this);
                break;
            case R.id.ll_money_record: // 收支记录
                StartActivityManager.startTransactionRecordActivity(this);
                break;
        }
    }

    /**
     * 有赞微商城登录
     */
    private void getYzLoginData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    YzLoginBean yzLoginBean = GsonUtils.parseJson(resulte, YzLoginBean.class);
                    if(yzLoginBean != null)
                    {
                        if (yzLoginBean.getStatusCode() == Constant.SUCCESS) {
                            if (yzLoginBean.getData() != null) {
                                SharePreferenceUtils.getBaseSharePreference().saveYZToken(yzLoginBean.getData().getAccess_token());
                                StartActivityManager.startWebViewActivity(MyWalletActivity.this, getString(R.string.recharge1), yzCrystal);
                            }
                        }
                    }
                }

            }
            @Override
            public void onError(ApiException e) {

            }
        }, this).yzLoginNew();
    }

    /**
     * 水晶PBS互换 请求
     */
    private void getExchangeCrystalData(String pbs_num,int type) {
        int pbs_int = Integer.parseInt(pbs_num);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CrystalVsPbsBean crystalVsPbsBean = GsonUtils.parseJson(resulte, CrystalVsPbsBean.class);
                if (crystalVsPbsBean.getStatusCode() == Constant.SUCCESS) {
                    T.showShort(getString(R.string.exchange_success));
                    ll_crystal_recharge_all.setVisibility(View.GONE);
                    crystalRecharge = 0;
                    requestData();
                } else if (crystalVsPbsBean.getStatusCode() == Constant.TIPS1) {
                    T.showShort(crystalVsPbsBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).crystalVsPbs(type, pbs_int);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_MyWalletActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_MyWalletActivity);
        MobclickAgent.onPause(this);
    }

    /**
     * 设置布局显示隐藏
     * @param crystalBg
     * @param crystalColor
     * @param pbsBg
     * @param pbsColor
     * @param pbsView
     * @param crystalView
     */
    private void setPbsOrCrystalView(int crystalBg, int crystalColor, int pbsBg, int pbsColor, int pbsView, int crystalView){
        bt_crystal_wallet.setBackgroundResource(crystalBg);
        bt_crystal_wallet.setTextColor(getResources().getColor(crystalColor));
        bt_pbs_wallet.setBackgroundResource(pbsBg);
        bt_pbs_wallet.setTextColor(getResources().getColor(pbsColor));
        recyclerView.setVisibility(pbsView);
        recyclerView_crystal.setVisibility(crystalView);
    }
    /**
     * 设置渠道布局显示隐藏
     * @param internal_revenue 内盘交易
     * @param pbs_incubation PBS定存宝
     * @param pbs_incubation2 预期日化率
     * @param deposit_funds 我的定存宝
     * @param high_save 高级定存宝
     * @param promotional_benefits 数据报表
     * @param hight_save 高端定存宝
     * @param hight_save2 高端定存宝
     * @param all_profit 三种收益
     */
    private void setSettingUpChannels(int internal_revenue, int pbs_incubation, int pbs_incubation2, int deposit_funds,
                                      int high_save, int promotional_benefits, int hight_save, int hight_save2, int all_profit){
        ll_internal_revenue.setVisibility(internal_revenue);
        ll_pbs_incubation.setVisibility(pbs_incubation);
        ll_pbs_incubation2.setVisibility(pbs_incubation2);
       // ll_deposit_funds.setVisibility(deposit_funds);
        ll_high_save.setVisibility(high_save);
//        ll_promotional_benefits.setVisibility(promotional_benefits);
        ll_hight_save.setVisibility(hight_save);
        ll_hight_save2.setVisibility(hight_save2);
       // ll_all_profit.setVisibility(all_profit);

    }
    /**
     * 设置pbs定存渠道布局显示隐藏
     * @param fixeddepositpbs
     * @param fixeddeposit1
     * @param fixeddeposit2
     */
    private void setFixedDepositPBS(String fixeddepositpbs, int fixeddeposit1, int fixeddeposit2){
        if(TextUtils.isEmpty(fixeddepositpbs)||"0".equals(fixeddepositpbs)){
            tv_fixed_deposit_pbs.setVisibility(fixeddeposit1);
            tv_fixed_deposit_crystal.setVisibility(fixeddeposit1);
        }else {
            tv_fixed_deposit_pbs.setVisibility(fixeddeposit2);
            tv_fixed_deposit_crystal.setVisibility(fixeddeposit2);
            String fixeddepositpbss = BigDecimalUtil.addComma4(fixeddepositpbs);
            tv_fixed_deposit_pbs.setText(getString(R.string.fixed_deposit2)+ Constant.ONE_SPACE +fixeddepositpbss);
        }
    }


    //接收事件
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(BroadEvent bean) {
        if(bean!=null&&bean.getAction().equals(BroadEvent.ACTION_REFRESH_WALLET)){
            requestData();
        }
    }


    /**
     * 设置我的资产预估渠道显示逻辑
     * @param pbsTran
     * @param add_num
     */
    private void setAssetProjection(String pbsTran, String add_num){
        String add_pbsTran = BigDecimalUtil.add(pbsTran, add_num, Constant.SCALE_NUM);//加上定存的pbs
        String addComma_all = BigDecimalUtil.addComma3(add_pbsTran);
        tv_assets_valuation.setText(addComma_all);//相加后的总估值
    }

    @Override
    public void finish() {
        EventBusUtils.getInstance().unregister(this);
        super.finish();
    }
}
