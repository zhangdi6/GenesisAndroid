package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GoodsDetailBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.utils.URIEncoder;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页面
 */
public class GoodsDetailActivity extends BaseActivity {


    @BindView(R.id.image_cover)
    ImageView imageCover;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.text_lirun)
    TextView textLirun;

    @BindView(R.id.text_chengben_bottom)
    TextView textChengbenBottom;

    @BindView(R.id.text_xiaoshoujia)
    TextView textXiaoshoujia;

    @BindView(R.id.text_lirunlv)
    TextView textLirunlv;


    @BindView(R.id.relay_product_detail)
    RelativeLayout relayProductDetail;

    @BindView(R.id.btn_tuangou)
    Button btnTuangou;

    private GoodsDetailBean goodsDetailBean;

//    private PopupWindowShare popupWindowShare;

    private String goodsDetailUrl="http://testflightapp.com.cn/genesis/itemPage?from=groupmessage&isappinstalled=0";
    private String goodsParam="https://h5.youzan.com/v2/showcase/goods?alias=";

    @Override
    public int getLayout() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("产品详情");
        Intent intent = getIntent();
        String itemId = intent.getStringExtra("itemId");
        if (StringUtil.isNotEmpty(itemId)) {
            new UserTask(new HttpOnNextListener() {

                @Override
                public void onNext(String resulte, String method) {
                    if (StringUtil.isNotEmpty(resulte)) {
                        goodsDetailBean = GsonUtil.GsonToBean(resulte, GoodsDetailBean.class);
                        if (goodsDetailBean != null) {
                            if (Constant.SUCCESS == goodsDetailBean.getStatusCode()) {
                                initData();
                            } else if (StringUtil.isNotEmpty(goodsDetailBean.getMessage())) {
                                T.showShort(goodsDetailBean.getMessage());
                            }
                        }
                    }
                }

                @Override
                public void onError(ApiException e) {
                    e.printStackTrace();
                }
            }, GoodsDetailActivity.this).getGoodById(itemId);
        }
    }

    @OnClick(value = {R.id.relay_product_detail, R.id.btn_tuangou})
    public void viewOnClick(View view){
        int id= view.getId();
        switch (id){
            case R.id.relay_product_detail:
                if(goodsDetailBean!=null&&goodsDetailBean.getStatusCode()== Constant.SUCCESS){
                    GoodsDetailBean.ItemDetailOpenModel itemDetailOpenModel = goodsDetailBean.getData().getResponse().getItem();
                    StartActivityManager.startWebViewActivity(GoodsDetailActivity.this,"产品详情",itemDetailOpenModel.getDetail_url(),false,false);
                }
                break;
            case R.id.btn_tuangou:
                if (SharePreferenceUtils.getBaseSharePreference().readVipLevel() < 1) {
                    T.showShort("请开通会员后再分享商品！");
                } else if(goodsDetailBean!=null&&goodsDetailBean.getStatusCode()== Constant.SUCCESS){
                    GoodsDetailBean.ItemDetailOpenModel itemDetailOpenModel = goodsDetailBean.getData().getResponse().getItem();
                    goodsDetailUrl=goodsDetailUrl+"&itemId="+itemDetailOpenModel.getItem_id()+"&shareUserId="+ SharePreferenceUtils.getBaseSharePreference().readUserId()+
                            "&yzGoodUrl="+ URIEncoder.encodeURIComponent(goodsParam+itemDetailOpenModel.getAlias());

                    double xiaoshoujia = BigDecimalUtil.round(((float) itemDetailOpenModel.getPrice()) / 100, 2);
                    DialogUtils.showShareDialog(GoodsDetailActivity.this,itemDetailOpenModel.getTitle(),
                            "销售价：¥"+xiaoshoujia,shareListener,goodsDetailUrl,itemDetailOpenModel.getPic_url());
                }
                break;
        }
    }

    private void initData() {

        GoodsDetailBean.ItemDetailOpenModel itemDetailOpenModel = goodsDetailBean.getData().getResponse().getItem();
        textTitle.setText(itemDetailOpenModel.getTitle());
        GlideUtils.display(itemDetailOpenModel.getPic_url(), imageCover);


        double lirun = (itemDetailOpenModel.getPrice() - itemDetailOpenModel.getCost_price() - (itemDetailOpenModel.getPrice() * 0.01));
        double lirunlv;
        if (itemDetailOpenModel.getPrice() != 0) {
            lirunlv = BigDecimalUtil.round(lirun / (double) itemDetailOpenModel.getPrice() * 100, 2);
        } else {
            lirunlv = -1;
        }
        GlideUtils.display(itemDetailOpenModel.getPic_url(), imageCover);
        double xiaoshoujia = BigDecimalUtil.round(((float) itemDetailOpenModel.getPrice()) / 100, 2);
        textLirunlv.setText(lirunlv + "%");
        textLirun.setText("利润:" + BigDecimalUtil.round(lirun / 100, 2));
        textXiaoshoujia.setText("销售价:" + xiaoshoujia);
        textChengbenBottom.setText("¥" + xiaoshoujia);

    }



    /**
     * @authorc: gaotengfei
     * @time: 2017/9/19
     * @desc: 友盟回调
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            SocializeUtils.safeShowDialog(shareAlertDialog);
            DialogUtil.getInstance().showLoadingDialog(GoodsDetailActivity.this, getString(R.string.loading));//加载动画
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
            finish();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
        }
    };



}
