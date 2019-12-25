package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.UsdtPbsBean;
import com.iruiyou.pet.bean.UserInfoModel;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新版我的钱包
 */
public class MyWalletV2Activity extends BaseActivity {

    @BindView(R.id.text_all_value)
    TextView textAllValue;

    @BindView(R.id.text_xunibi_count)
    TextView textXunibiCount;

    @BindView(R.id.text_crash_count)
    TextView textCrashCount;

    @BindView(R.id.title_right_text2)
    TextView title_right_text2;

    @BindView(R.id.refreshLayout_mywallet)
    SmartRefreshLayout refreshLayout_mywallet;

    private HarvestBean harvestBean;

    private UsdtPbsBean usdtPbsBean;

    private UserInfoModel userInfoModel;
    String pbs_cny;
//    @BindView(R.id.linear_chongzhi)
//    private LinearLayout linearChongzhi;
//
//    @BindView(R.id.linear_bank_card)
//    private LinearLayout linearBankCard;
//
//    @BindView(R.id.linear_zhifubao)
//    private LinearLayout linearZhifubao;


    private double pbsAmount;
    private double crystalAmount = 0;
    private double rebateCrystal =0;
    private String usdtPbs,usdCNY,hatchAmount;

    @Override
    public int getLayout() {
        return R.layout.activity_my_wallet_v2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.myWallet));
        title_right_text2.setText(R.string.assets3);
        title_right_text2.setTextColor(getResources().getColor(R.color._26c68a));
        getPBSData();
        getRefresh();
    }

    @OnClick(value = {R.id.linear_chongzhi, R.id.linear_bank_card, R.id.linear_zhifubao, R.id.linear_buy_vip, R.id.title_right_text2, R.id.linear_cunru})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.linear_cunru:
                StartActivityManager.startAssetsDeposited1Activity(this,rebateCrystal+"");
                break;
            case R.id.linear_chongzhi:
                StartActivityManager.startCrystalRechargeActivity(MyWalletV2Activity.this, crystalAmount + "");
                break;
            case R.id.linear_bank_card:
                StartActivityManager.startWithdrawalOfAssetsActivity(MyWalletV2Activity.this, pbsAmount+"",
                        hatchAmount, usdtPbs, rebateCrystal+"", usdCNY, BqexTransactionActivity.ACCOUNT_TYPE_BANK);
                break;
            case R.id.linear_zhifubao:
                StartActivityManager.startWithdrawalOfAssetsActivity(MyWalletV2Activity.this, pbsAmount+"",
                        hatchAmount, usdtPbs, rebateCrystal+"", usdCNY, BqexTransactionActivity.ACCOUNT_TYPE_ALIPAY);
                break;
            case R.id.linear_buy_vip:
                if(harvestBean!=null&&harvestBean.getData()!=null&&harvestBean.getData().getUserInfo()!=null){
                    HarvestBean.DataBean.UserInfoBean userInfoBean =harvestBean.getData().getUserInfo();
                    StartActivityManager.startSeniorMemberActivity(MyWalletV2Activity.this,userInfoBean.getName(),
                            userInfoBean.getHeadImg(),userInfoBean.getVipLevel());
                }
                break;
            case R.id.title_right_text2://交易记录
                StartActivityManager.startTransactionRecordActivity(this);
                break;
        }
    }


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
                        usdCNY = usdtPbsBean.getData().getUsdCNY().getLast();//usdt兑换人民币的汇率
                        usdtPbs = usdtPbsBean.getData().getUsdtPBS().getLast();
//                        String quoteVolume = usdtPbsBean.getData().getUsdtPBS().getQuoteVolume();
//                        String percentChange = usdtPbsBean.getData().getUsdtPBS().getPercentChange();
                        pbs_cny = BigDecimalUtil.mul(usdtPbs, usdCNY, Constant.SCALE_NUM);
                        requestData();
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
                getPBSData();
            }
        });
    }

    /**
     * 请求当前账户所有的资产总额
     */
    private void requestData() {
        //pbs收支记录
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                harvestBean = GsonUtils.parseJson(resulte, HarvestBean.class);
                if (harvestBean.getStatusCode() == Constant.SUCCESS) {
                    if (harvestBean != null && harvestBean.getData() != null) {

                        //pbs定存数量
                        HarvestBean.DataBean data = harvestBean.getData();
                        String pbsDeposit = data.getPbsDeposit();// 定存PBS数量(kaven渠道)
                        String pbsFutures = data.getPbsFutures();// 期货兑换的PBS数量（yuanyuan渠道）
                        String pbsTran = data.getPbsTran();//定存PBS数量（niyang渠道）
                        String sellTrade = data.getSellTrade();//冻结PBS在内盘的挂单数量（pets渠道）

                        //得到所有渠道的和
                        String add_deposit_futures = BigDecimalUtil.add(pbsDeposit, pbsFutures);
                        String add_deposit_futures_tran = BigDecimalUtil.add(add_deposit_futures, pbsTran);
                        String add_deposit_futures_tran_sellTrade = BigDecimalUtil.add(add_deposit_futures_tran, sellTrade);//定存总数

                        hatchAmount = harvestBean.getData().getUserInfo().getHatchAmount();
                        crystalAmount = Double.valueOf(harvestBean.getData().getUserInfo().getCrystalAmount());
                        rebateCrystal = Double.valueOf(harvestBean.getData().getUserInfo().getRebateCrystal());
                        pbsAmount = Double.valueOf(add_deposit_futures_tran_sellTrade).doubleValue();
                        double lastHarvestAmount = Double.valueOf(harvestBean.getData().getUserInfo().getLastHarvestAmount()).doubleValue();
                        double pbsFrozen = Double.valueOf(harvestBean.getData().getUserInfo().getPbsFrozen()).doubleValue();
                        double pbsDrawLockedAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsDrawLockedAmount()).doubleValue();

                        textCrashCount.setText(BigDecimalUtil.addComma4(rebateCrystal));
                        textXunibiCount.setText(BigDecimalUtil.addComma4(pbsAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount));
                        double totalPBS = Double.valueOf(pbs_cny).doubleValue() * (pbsAmount + lastHarvestAmount);

                        String allPrice = BigDecimalUtil.addComma4(rebateCrystal + totalPBS + crystalAmount);
                        textAllValue.setText(allPrice);
                    }
                }
                refreshLayout_mywallet.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_mywallet.finishRefresh(false/*,false*/);//传入false表示刷新失败
            }
        }, this).harvestList(true);

    }


}
