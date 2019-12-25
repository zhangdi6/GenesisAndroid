package com.iruiyou.pet.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.SaveImageActivity;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.InformationBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MultiImageView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.ProfileInfoDialog;
import com.iruiyou.pet.utils.RaiseNumberAnimTextView;
import com.iruiyou.pet.utils.SkipAdDialog;
import com.iruiyou.pet.utils.StringUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessChanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<GetEssaysBean.DataBean> data;
    private BusinessChanceAdapter.OnViewClickListener onViewClickListener;
//    private TypeOnClick typeOnClick;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_HEAD = 1;
    private LayoutInflater inflater;
    private String userId;
    private BusinessChanceAdapter.HeadHolder headHolder;
    public BusinessChanceAdapter(Context context, List<GetEssaysBean.DataBean> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userId= SharePreferenceUtils.getBaseSharePreference().readUserId();
    }

    public void setOnViewClickListener(BusinessChanceAdapter.OnViewClickListener listener) {
        onViewClickListener = listener;
    }


    public void setSourceValue(String totalCounts,String values,String totalUser,String totalVip){
        if(headHolder!=null){
            headHolder.tv_vein_resource_library_number.setText(values);
            headHolder.tv_vein_asset_valuation.setText(totalCounts);
            headHolder.text_total_vip.setText(totalVip);
            headHolder.text_total_user.setText(totalUser);
        }
    }


    public void setCoinStatus(boolean isEnable){
        if(headHolder!=null){
            if(!isEnable){
                headHolder.starLl.setEnabled(false);
                headHolder.gemstone.setAlpha(0.5f);
                headHolder.lastHarvestAmount.setText("正在生成中...");
            }else{
                headHolder.starLl.setEnabled(true);
                headHolder.gemstone.setAlpha(1f);
                headHolder.lastHarvestAmount.setText("看广告得脉点,每六小时看一次");
            }
        }
    }

    public void refreshNewsData(List<InformationBean.DataBean> dataTemp){
        if(headHolder!=null){
            headHolder.infomationAdapter.setNewData(dataTemp);
        }
    }

    public void refreshWallet(String crash,String pbs){
        if(headHolder!=null){
            headHolder.textCrash.setText(crash);
            headHolder.textXuNiBi.setText(pbs);
        }
    }

    public void refreshNotice(String notice,int visiable){
        headHolder.textNoticeTitle.setText(notice);
        headHolder.llNotice.setVisibility(visiable);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).isHead()) {
            return TYPE_HEAD;
        } else {
            return TYPE_NORMAL;
        }
    }

    public GetEssaysBean.DataBean getItem(int position){
        return data.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return new BusinessChanceAdapter.HeadHolder(inflater.inflate(R.layout.view_home_head, parent, false));
            case TYPE_NORMAL:
                return new BusinessChanceAdapter.MyViewHolder(inflater.inflate(R.layout.item_recycler_find_follow, parent, false));
        }
       return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof BusinessChanceAdapter.MyViewHolder){
            BusinessChanceAdapter.MyViewHolder holder= (MyViewHolder) viewHolder;
            GetEssaysBean.DataBean.BasicInfoBean basicInfo = data.get(position).getBasicInfo();
            GetEssaysBean.DataBean dataBean = data.get(position);
            List<GetEssaysBean.DataBean.ImagesBean> images = data.get(position).getImages();
            if (basicInfo != null) {
                String company = basicInfo.getCompany();
                String position_user = basicInfo.getPosition();
                holder.tv_find_name.setText("" + basicInfo.getRealName());
                holder.tv_find_describe.setText(basicInfo.getPositionTitle());
//                if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
//                    holder.tv_find_describe.setText(position_user);
//                } else if (TextUtils.isEmpty(position_user) && !TextUtils.isEmpty(company)) {
//                    holder.tv_find_describe.setText(company);
//                } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
//                    holder.tv_find_describe.setText(company + "\t\t" + position_user);
//                }


                if (dataBean != null) {
                    holder.tv_find_content.setText(dataBean.getContent());
                }
                GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), holder.im_find_head);
            }
            if (images != null) {
                if (images.size() == 0) {
                    holder.gridView_find_pic.setVisibility(View.GONE);
                } else {
                    holder.gridView_find_pic.setList(images);
                }
            }

            if(StringUtil.isNotEmpty(userId)&&(Integer.valueOf(userId).intValue()==dataBean.getUserId())){
                holder.text_delete_essay.setVisibility(View.VISIBLE);
                holder.text_delete_essay.setOnClickListener((view)->{
                    if(onViewClickListener!=null){
                        onViewClickListener.onViewClick(R.id.text_delete_essay,position);
                    }
                });
            } else {
                holder.text_delete_essay.setVisibility(View.INVISIBLE);
                holder.text_delete_essay.setOnClickListener(null);
            }

            if(position==1){
                holder.ll_network_expand.setVisibility(View.VISIBLE);
            } else {
                holder.ll_network_expand.setVisibility(View.GONE);
            }

            if(dataBean.getReferenceId()!=-1){
                holder.relayout_reference.setVisibility(View.VISIBLE);
                holder.text_short_content.setText(dataBean.getReferenceContent());
                holder.text_author.setText("作者: "+dataBean.getReferenceAuthorName());
                holder.gridView_find_pic.setVisibility(View.GONE);
                holder.relayout_reference.setOnClickListener((v) -> {
                    StartActivityManager.startEssayDetail(context,dataBean);
                });
            } else {
                holder.relayout_reference.setVisibility(View.GONE);
                holder.gridView_find_pic.setVisibility(View.VISIBLE);
            }

        } else if(viewHolder instanceof BusinessChanceAdapter.HeadHolder){
            headHolder = (HeadHolder) viewHolder;
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


        @BindView(R.id.text_bi)
        TextView textXuNiBi;

        @BindView(R.id.text_crash)
        TextView textCrash;

        @BindView(R.id.text_notice_title)
        TextView textNoticeTitle;

        @BindView(R.id.ll_pbs_notice)
        LinearLayout llNotice;

        @BindView(R.id.starLl)
        LinearLayout starLl;

        @BindView(R.id.linear_wallet)
        LinearLayout linear_wallet;

        @BindView(R.id.gemstone)
        ImageView gemstone;

        @BindView(R.id.lastHarvestAmount)
        TextView lastHarvestAmount;

        @BindView(R.id.information_recycle)
        MaxRecyclerView information_recycle;

//        @BindView(R.id.linear_network)
//        LinearLayout linear_network;

        private InfomationAdapter infomationAdapter;

        private ObjectAnimator animator;



        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

                infomationAdapter = new InfomationAdapter(context);
                information_recycle.setAdapter(infomationAdapter);
                information_recycle.setLayoutManager(new MyLinearLayoutManager(context));
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
        private void loadAd(){
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



        @OnClick(value = {R.id.starLl, R.id.ll_pbs_notice, R.id.linear_wallet, R.id.linear_network})
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.starLl:
                    DialogUtils.showAdSkipDialog(context, new SkipAdDialog.AdDialogOnClick() {
                        @Override
                        public void onDialogClick(boolean isSkip) {
                            int adView = 0;
                          if(isSkip){
                              adView =0;
                          }else{
                              adView =1;
                          }
                          getCoine(adView);
                        };
                    });
                    break;
                case R.id.ll_pbs_notice://公告
                    StartActivityManager.startWebViewActivity((Activity) context, context.getString(R.string.notice2), BaseApi.baseUrlNoApi + UrlContent.depositNotice);
                    break;
                case R.id.linear_wallet:
                    StartActivityManager.startMyWalletActivity(context);
                    break;
                case R.id.linear_network:
                    StartActivityManager.startNetWorkActivity(context);
                    break;
            }
        }

        /**
         * 获取签到
         */
        private void getCoine(int adview){
            boolean isStart = true;
            ACache aCache = ACache.get(context);
            LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
            if(loginNewBean!=null&&loginNewBean.getData()!=null)
            {
                LoginNewBean.DataBean.BasicInfoBean basicInfo= loginNewBean.getData().getBasicInfo();
                if(StringUtil.isNotEmpty(basicInfo.getPosition())&& StringUtil.isNotEmpty(basicInfo.getHeadImg())&&(basicInfo.getProfessionalIdentity()!=0)){
                    doSetAnim(adview);
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
                        if(adView==1){
                            mttRewardVideoAd.showRewardVideoAd(((BaseActivity)context));
                        }

                        double amount = ((Double)commonBean.getData()).doubleValue();
                        String textValue = textXuNiBi.getText().toString().trim();
                        if(StringUtil.isEmpty(textValue)){
                            textValue="0.0";
                        }
                        textXuNiBi.setText(String.valueOf(Double.valueOf(textValue).doubleValue()+amount));
                        Log.e("test","amount is "+amount);
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

        @BindView(R.id.image_icon)
        ImageView image_icon;

        @BindView(R.id.text_author)
        TextView text_author;

        @BindView(R.id.text_short_content)
        TextView text_short_content;

        @BindView(R.id.tv_find_name)
        TextView tv_find_name;

        @BindView(R.id.im_find_head)
        ImageView im_find_head;

        @BindView(R.id.gridView_find_pic)
        MultiImageView gridView_find_pic;

        ImageView im_pic;//一张图时

        @BindView(R.id.tv_find_content)
        TextView tv_find_content;//发布内容


        @BindView(R.id.tv_find_describe)
        TextView tv_find_describe;//个人描述信息

        @BindView(R.id.tv_find_pbs)
        TextView tv_find_pbs;//pbs数量

        @BindView(R.id.tv_find_message)
        TextView tv_find_message;//消息数量

        @BindView(R.id.text_delete_essay)
        TextView text_delete_essay;//删除短文

        @BindView(R.id.ll_network_expand)
        LinearLayout ll_network_expand;


        @BindView(R.id.relayout_reference)
        RelativeLayout relayout_reference;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            gridView_find_pic.setOnItemClickListener((View view, String position, int po) ->{
                Intent intent = new Intent(context, SaveImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) data.get(getAdapterPosition()).getImages());
//                intent.putStringArrayListExtra("imglist", getEssaysBean);
                bundle.putInt("pic", 0);//暂时传零，点击都从一显示，此po是最后的数量，不是点击对应的条目，还需研究
                intent.putExtras(bundle);
                context.startActivity(intent);
            });

            itemView.setOnClickListener(view -> {
                int position = getPosition();
                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            });

        }
    }

    public void setItemClickListener(FindHotAdapter.MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    private FindHotAdapter.MyItemClickListener mItemClickListener;

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        //        void onItemClick(OccupationBeen firstPageListBean, int position);
        void onItemClick(int position);
    }

    public interface OnViewClickListener {
        void onViewClick(int viewId, int position);
    }

    //保存图片
    private void saveCroppedImage(Bitmap bmp) {
        File file = new File("/sdcard/myFolder");
        if (!file.exists())
            file.mkdir();

        file = new File("/sdcard/temp.jpg".trim());
        String fileName = file.getName();
        String mName = fileName.substring(0, fileName.lastIndexOf("."));
        String sName = fileName.substring(fileName.lastIndexOf("."));

        // /sdcard/myFolder/temp_cropped.jpg
        String newFilePath = "/sdcard/myFolder" + "/" + mName + "_cropped" + sName;
        file = new File(newFilePath);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
