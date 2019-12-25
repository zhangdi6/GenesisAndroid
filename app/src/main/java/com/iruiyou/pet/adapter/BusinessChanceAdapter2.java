package com.iruiyou.pet.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.WebViewNewActivity;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.home_adapter.KeChengAdapter;
import com.iruiyou.pet.adapter.home_adapter.RenqiAdapter;
import com.iruiyou.pet.adapter.home_adapter.ZhiWeiAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.DMOptionListBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.InformationBean;
import com.iruiyou.pet.bean.JiaMenBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.ProfileInfoDialog;
import com.iruiyou.pet.utils.RaiseNumberAnimTextView;
import com.iruiyou.pet.utils.SkipAdDialog;
import com.iruiyou.pet.utils.StringUtil;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessChanceAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<GetEssaysBean.DataBean> data;
    /*private BusinessChanceAdapter2.OnViewClickListener onViewClickListener;*/
//    private TypeOnClick typeOnClick;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_HEAD = 1;
    private LayoutInflater inflater;
    private String userId;
    private BusinessChanceAdapter2.HeadHolder headHolder;
    private BusinessChanceAdapter2.MyViewHolder holder;
    private GetCourseIntroBean.DataBean dataBean;

    //人气单品列表
    OpportunitiesGoodsBean.DataBean bean;

    private JiaMenBean beanss;
    private List<JiaMenBean.DataBean> listss;


    public BusinessChanceAdapter2(Context context, List<GetEssaysBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userId = SharePreferenceUtils.getBaseSharePreference().readUserId();
    }

    public void setSourceValue(String totalCounts, String values, String totalUser, String totalVip) {
        if (headHolder != null) {
            headHolder.tv_vein_resource_library_number.setText(values);
            headHolder.tv_vein_asset_valuation.setText(totalCounts);
            headHolder.text_total_vip.setText(totalVip);
            headHolder.text_total_user.setText(totalUser);

        }
    }


    public void setlist(List<OpportunitiesGoodsBean.Item> mlist) {
        Logger.d("-----------" + mlist.get(0).getCreated_time());
        if (headHolder != null) {
            GridLayoutManager ridLayoutManager = new GridLayoutManager(context, 3);
            headHolder.home_recy_renqi.setLayoutManager(ridLayoutManager);
            RenqiAdapter renqiAdapter = new RenqiAdapter(mlist);
            headHolder.home_recy_renqi.setAdapter(renqiAdapter);
            renqiAdapter.notifyDataSetChanged();


            //点击事件设置在这里

            renqiAdapter.setOnItemClickListener(new RenqiAdapter.OnItemClickListener() {
                @Override
                public void getclicklistener(int position) {

                  //  if(App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        Intent intent = new Intent(context, WebViewNewActivity.class);
                        intent.putExtra("title", mlist.get(position).getTitle());
                        intent.putExtra("url", mlist.get(position).getDetail_url());
                        context.startActivity(intent);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }


                }
            });
            notifyDataSetChanged();
        }
    }


    public void setkclist(List<GetCourseIntroBean.DataBean> kclist) {
        if (headHolder != null) {
            LinearLayoutManager ridLayoutManager = new LinearLayoutManager(context);
            headHolder.home_recy_kecheng.setLayoutManager(ridLayoutManager);
            KeChengAdapter kechengAdapter = new KeChengAdapter(kclist);
            headHolder.home_recy_kecheng.setAdapter(kechengAdapter);
            kechengAdapter.notifyDataSetChanged();


            //点击事件设置在这里

            kechengAdapter.setOnItemClickListener(new KeChengAdapter.OnItemClickListener() {
                @Override
                public void getclicklistener(int position) {
                 //   if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        GetCourseIntroBean.DataBean bean = kclist.get(position);

                        StartActivityManager.startCourseContent2Activity(context, bean, position);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }



                }
            });
            notifyDataSetChanged();
        }
    }

    public void setzwlist(List<DMOptionListBean.DataBean> zwlist) {
        if (headHolder != null) {
            LinearLayoutManager ridLayoutManager = new LinearLayoutManager(context);
            headHolder.home_recy_zhiwei.setLayoutManager(ridLayoutManager);
            ZhiWeiAdapter zhiWeiAdapter = new ZhiWeiAdapter(zwlist);
            headHolder.home_recy_zhiwei.setAdapter(zhiWeiAdapter);
            zhiWeiAdapter.notifyDataSetChanged();


            //点击事件设置在这里

            zhiWeiAdapter.setOnItemClickListener(new ZhiWeiAdapter.OnItemClickListener() {
                @Override
                public void getclicklistener(int position) {

                  //  if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                        StartActivityManager.startPositionDetailActivity((AppCompatActivity)context,zwlist.get(position));
                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }

                }
            });
            notifyDataSetChanged();
        }
    }


    public void setCoinStatus(boolean isEnable) {
        if (headHolder != null) {
            if (!isEnable) {
                headHolder.starLl.setEnabled(false);
                headHolder.gemstone.setAlpha(0.5f);
                headHolder.lastHarvestAmount.setText("正在生成中...");
            } else {
                headHolder.starLl.setEnabled(true);
                headHolder.gemstone.setAlpha(1f);
                headHolder.lastHarvestAmount.setText("看广告得脉点,每六小时看一次");
            }
        }
    }

    public void refreshNewsData(List<InformationBean.DataBean> dataTemp) {
     /*   if(headHolder!=null){
            headHolder.infomationAdapter.setNewData(dataTemp);
        }*/
    }

    public void refreshWallet(String crash, String pbs) {
        if (headHolder != null) {
            /*headHolder.textCrash.setText(crash);*/
            /* headHolder.textXuNiBi.setText(pbs);*/
        }
    }

    /* public void refreshNotice(String notice,int visiable){
         headHolder.textNoticeTitle.setText(notice);
         headHolder.llNotice.setVisibility(visiable);
     }
 */
    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isHead()) {
            return TYPE_HEAD;
        } else {
            return TYPE_NORMAL;
        }
    }

    public GetEssaysBean.DataBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return new BusinessChanceAdapter2.HeadHolder(inflater.inflate(R.layout.view_home_head2, parent, false));
            case TYPE_NORMAL:
                return new BusinessChanceAdapter2.MyViewHolder(inflater.inflate(R.layout.view_home_heads, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof BusinessChanceAdapter2.MyViewHolder) {


        } else if (viewHolder instanceof BusinessChanceAdapter2.HeadHolder) {

            headHolder = (HeadHolder) viewHolder;

          /*  new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {

                    bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.DataBean.class);

                    Log.e("1234", "onNext: "+"1234567890" );
                    listgoods = bean.getResponse().getItems();
                    GridLayoutManager ridLayoutManager = new GridLayoutManager(context,3);
                    headHolder.home_recy_renqi.setLayoutManager(ridLayoutManager);
                    RenqiAdapter renqiAdapter = new RenqiAdapter(listgoods);
                    headHsolder.home_recy_renqi.setAdapter(renqiAdapter);
                    renqiAdapter.notifyDataSetChanged();
                    // setListener();

                }

                @Override
                public void onError(ApiException e) {

                }
            },(MainActivity)context).getYZGoodsListV3();
*/

        }


    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    class HeadHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_vein_resource_library_number)
        RaiseNumberAnimTextView tv_vein_resource_library_number;

        @BindView(R.id.tv_vein_asset_valuation)
        RaiseNumberAnimTextView tv_vein_asset_valuation;

        @BindView(R.id.text_total_vip)
        RaiseNumberAnimTextView text_total_vip;

        @BindView(R.id.text_total_user)
        RaiseNumberAnimTextView text_total_user;


        @BindView(R.id.starLl)
        LinearLayout starLl;

       /* @BindView(R.id.home_kecheng)
        LinearLayout linearLayout_home;*/


        @BindView(R.id.gemstone)
        ImageView gemstone;

        @BindView(R.id.lastHarvestAmount)
        TextView lastHarvestAmount;

        private ObjectAnimator animator;


        @BindView(R.id.home_recy_renqi)
        RecyclerView home_recy_renqi;
        @BindView(R.id.home_recy_kecheng)
        RecyclerView home_recy_kecheng;
        @BindView(R.id.home_recy_zhiwei)
        RecyclerView home_recy_zhiwei;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            doRepeatAnim();
            loadAd();


        }


        /**
         * 重复动画
         */
        private void doRepeatAnim() {
            int padding = DensityUtil.dip2px(10);
            animator = ObjectAnimator.ofFloat(starLl, "translationY", -padding, padding, -padding);
            animator.setRepeatMode(ObjectAnimator.REVERSE);
            animator.setRepeatCount(ObjectAnimator.INFINITE);
            animator.setDuration(1500);
            animator.start();
        }


        private TTRewardVideoAd mttRewardVideoAd;

        private void loadAd() {
            AdSlot adSlot = new AdSlot.Builder()
                    .setCodeId("928392094")
                    .setSupportDeepLink(true)
                    .setAdCount(2)
                    .setImageAcceptedSize(DensityUtil.getScreenWidth(), DensityUtil.getScreenHeight())
                    .setOrientation(TTAdConstant.VERTICAL)  //设置期望视频播放的方向，为TTAdConstant.HORIZONTAL或TTAdConstant.VERTICAL
                    .build();
            TTAdSdk.getAdManager().createAdNative(context).loadRewardVideoAd(adSlot, new TTAdNative.RewardVideoAdListener() {
                @Override
                public void onError(int code, String message) {
//                    Toast.makeText(RewardVideoActivity.this, message, Toast.LENGTH_SHORT).show();
                }

                //视频广告加载后的视频文件资源缓存到本地的回调
                @Override
                public void onRewardVideoCached() {
//                    Toast.makeText(RewardVideoActivity.this, "rewardVideoAd video cached", Toast.LENGTH_SHORT).show();
                }

                //视频广告素材加载到，如title,视频url等，不包括视频文件
                @Override
                public void onRewardVideoAdLoad(TTRewardVideoAd ad) {
//                    Toast.makeText(RewardVideoActivity.this, "rewardVideoAd loaded", Toast.LENGTH_SHORT).show();
                    mttRewardVideoAd = ad;
                    //mttRewardVideoAd.setShowDownLoadBar(false);
                    mttRewardVideoAd.setRewardAdInteractionListener(new TTRewardVideoAd.RewardAdInteractionListener() {

                        @Override
                        public void onAdShow() {
//                            Toast.makeText(RewardVideoActivity.this, "rewardVideoAd show", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdVideoBarClick() {
//                            Toast.makeText(RewardVideoActivity.this, "rewardVideoAd bar click", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdClose() {
//                            Toast.makeText(RewardVideoActivity.this, "rewardVideoAd close", Toast.LENGTH_SHORT).show();
//                            getCoine();
                        }

                        @Override
                        public void onVideoComplete() {
//                            Toast.makeText(RewardVideoActivity.this, "rewardVideoAd complete", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onVideoError() {

                        }

                        @Override
                        public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName) {
//                            Toast.makeText(RewardVideoActivity.this, "verify:"+rewardVerify+" amount:"+rewardAmount+
//                                            " name:"+rewardName,
//                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSkippedVideo() {
//                            getCoine();
                        }
                    });
                    mttRewardVideoAd.setDownloadListener(new TTAppDownloadListener() {
                        @Override
                        public void onIdle() {

                        }

                        @Override
                        public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
//                            getCoine();
                        }

                        @Override
                        public void onDownloadFinished(long totalBytes, String fileName, String appName) {

                        }

                        @Override
                        public void onInstalled(String fileName, String appName) {

                        }
                    });
                }
            });
        }


        //R.id.ll_pbs_notice,R.id.linear_wallet,
        @OnClick(value = {R.id.starLl, R.id.linear_network, R.id.home_kecheng, R.id.home_renqi, R.id.home_zhiwei, R.id.linear_shangcheng, R.id.linear_daxue
                , R.id.linear_jiuye, R.id.linear_jiamen})

        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.starLl:
                    DialogUtils.showAdSkipDialog(context, new SkipAdDialog.AdDialogOnClick() {
                        @Override
                        public void onDialogClick(boolean isSkip) {
                            int adView = 0;
                            if (isSkip) {
                                adView = 0;
                            } else {
                                adView = 1;
                            }
                            getCoine(adView);
                        }


                    });
                    break;
               /* case R.id.ll_pbs_notice://公告
                    StartActivityManager.startWebViewActivity((Activity) context, context.getString(R.string.notice2), BaseApi.baseUrlNoApi + UrlContent.depositNotice);
                    break;
                case R.id.linear_wallet:
                    StartActivityManager.startMyWalletActivity(context);
                    break;*/
                case R.id.linear_network:
                 //   if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                        StartActivityManager.startNetWorkActivity(context);
                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    break;
                case R.id.home_kecheng:
                   // if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        StartActivityManager.startHome_KeChengActivity(context);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }

                    break;
                case R.id.home_zhiwei:
                   // if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                        StartActivityManager.startHome_ZhiWeiActivity(context);
                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    break;
                case R.id.home_renqi:
                   // if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                        StartActivityManager.startWebViewActivity((Activity) context, "集市", "https://shop40984708.youzan.com/v2/feature/ajNgY4peUG?redirect_count=1&sf=wx_sm&is_share=1&from_uuid=1a0fd804-2518-7f00-6662-989d1892a45f&from=groupmessage", false, true);
                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    break;

                case R.id.linear_shangcheng:
                 //   if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        StartActivityManager.startWebViewActivity((Activity) context, "集市", "https://shop40984708.youzan.com/v2/feature/ajNgY4peUG?redirect_count=1&sf=wx_sm&is_share=1&from_uuid=1a0fd804-2518-7f00-6662-989d1892a45f&from=groupmessage", false, true);
                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }

                    break;
                case R.id.linear_daxue:
                  //  if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        StartActivityManager.startHome_KeChengActivity(context);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    break;
                case R.id.linear_jiuye:
                  //  if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        StartActivityManager.startHome_ZhiWeiActivity(context);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    break;

                case R.id.linear_jiamen:
                 //   if (App.isLogin){
                        if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                        StartActivityManager.startWebViewActivity((Activity) context, "加盟政策", " https://mp.weixin.qq.com/s/Jl3lFOSyJf_l3Evbjxiqqg", false, true);

                    }else{
                        Intent intent = new Intent(context, QuickLoginActivity.class);
                        context.startActivity(intent);
                    }
                    //   https://mp.weixin.qq.com/s/Jl3lFOSyJf_l3Evbjxiqqg
                    break;


            }
        }






        /**
         * 获取签到
         */
        private void getCoine(int adview) {
            boolean isStart = true;
            ACache aCache = ACache.get(context);
            LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
            if (loginNewBean != null && loginNewBean.getData() != null) {
                LoginNewBean.DataBean.BasicInfoBean basicInfo = loginNewBean.getData().getBasicInfo();
                if (StringUtil.isNotEmpty(basicInfo.getPosition()) && StringUtil.isNotEmpty(basicInfo.getHeadImg()) && (basicInfo.getProfessionalIdentity() != 0)) {
                    doSetAnim(adview);
                    isStart = false;
                }

                if (isStart) {
                    DialogUtils.showKnowDialog(((BaseActivity) context), new ProfileInfoDialog.KnowClick() {
                        @Override
                        public void onClick() {
                            StartActivityManager.startBaseicInfoActivity((BaseActivity) context, basicInfo);
                        }
                    });
                }
            }
        }


        /**
         * 收取能量
         */
        private void doSetAnim(int adView) {
            //请求接口，收取能量
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                    if (!TextUtils.isEmpty(commonBean.getMessage())) {
                        T.showShort(commonBean.getMessage());
                    }
                    SharePreferenceUtils.getBaseSharePreference().saveCurrencyType(commonBean.getStatusCode());
                    if (commonBean.getStatusCode() == Constant.SUCCESS) {
                        //添加添加的铃声
                        MediaPlayer player = MediaPlayer.create(context, R.raw.sound);
                        player.start();

                        ObjectAnimator move2 = ObjectAnimator.ofFloat(starLl, "translationY", 0, -DensityUtil.dip2px(150));
                        ObjectAnimator move3 = ObjectAnimator.ofFloat(starLl, "alpha", 1, 0);
                        ObjectAnimator move5 = ObjectAnimator.ofFloat(starLl, "alpha", 0, 1);

                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.playTogether(move2, move3, move5);
                        animatorSet.setDuration(1500);
                        animatorSet.start();
                        if (adView == 1) {
                            mttRewardVideoAd.showRewardVideoAd(((BaseActivity) context));
                        }

                        double amount = ((Double) commonBean.getData()).doubleValue();
                        /*String textValue = textXuNiBi.getText().toString().trim();*/
                        /*if(StringUtil.isEmpty(textValue)){
                            textValue="0.0";
                        }*/
                        /*textXuNiBi.setText(String.valueOf(Double.valueOf(textValue).doubleValue()+amount));*/
                        Log.e("test", "amount is " + amount);
                        starLl.setEnabled(false);
                        gemstone.setAlpha(0.5f);
                        lastHarvestAmount.setText("正在生成中...");
                        SharePreferenceUtils.getBaseSharePreference().saveLastMarkTime(System.currentTimeMillis());
                    }
                }

                @Override
                public void onError(ApiException e) {

                }
            }, (RxAppCompatActivity) context).harvest(adView);
        }


    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView home_renqi;
        private RecyclerView home_kecheng;
        private RecyclerView home_zhiwei;


        private FindHotAdapter.MyItemClickListener mItemClickListener;


        public MyViewHolder(View itemView) {
            super(itemView);

            home_renqi = itemView.findViewById(R.id.home_renqi);
            home_kecheng = itemView.findViewById(R.id.home_kecheng);
            home_zhiwei = itemView.findViewById(R.id.home_zhiwei);

        }

        public void setItemClickListener(FindHotAdapter.MyItemClickListener myItemClickListener) {
            this.mItemClickListener = myItemClickListener;
        }


    }
}