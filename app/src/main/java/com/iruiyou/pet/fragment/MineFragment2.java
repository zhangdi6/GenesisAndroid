package com.iruiyou.pet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.BasicInfoActivity;
import com.iruiyou.pet.activity.BlackCardActivity;
import com.iruiyou.pet.activity.DynamicActivity;
import com.iruiyou.pet.activity.GoodFriendsActivity;
import com.iruiyou.pet.activity.LookMeActivity;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.MessageActivity;
import com.iruiyou.pet.activity.ZhongChouActivity;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.bean.EventBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.HeadEventPost;
import com.iruiyou.pet.bean.MineRefreshBean;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.iruiyou.pet.bean.UsdtPbsBean;
import com.iruiyou.pet.bean.event.MineRefreshEvent;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.RaiseNumberAnimTextView;
import com.iruiyou.pet.utils.StringUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 第二页 我的
 * Created by jiao on 2017/4/22.
 */
public class MineFragment2 extends TakePhotoFragment {

    @BindView(R.id.headIv)
    ImageView headIv;

    @BindView(R.id.frag)
    FrameLayout frag;

    @BindView(R.id.mHeadIv)
    ImageView mHeadIv;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.details)
    LinearLayout details;
    @BindView(R.id.myWallet)
    LinearLayout myWallet;

    @BindView(R.id.userId)
    RaiseNumberAnimTextView userId;

    @BindView(R.id.userId1)
    TextView userId1;
    @BindView(R.id.details1)
    LinearLayout details1;
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.iv_vip_level)
    ImageView ivVipLevel;
    @BindView(R.id.tv_vip_level)
    TextView tvVipLevel;
    @BindView(R.id.positionTv)
    TextView positionTv;

    @BindView(R.id.llFriends)
    LinearLayout llFriends;
    @BindView(R.id.llAbout)
    LinearLayout llAbout;
    @BindView(R.id.refreshLayout_mine)
    SmartRefreshLayout refreshLayout_mine;
    @BindView(R.id.text_friend_count)
    TextView text_friend_count;
    @BindView(R.id.text_dynamic_count)
    TextView text_dynamic_count;
    @BindView(R.id.text_access_count)
    TextView text_access_count;
    @BindView(R.id.linear_top_mine)
    RelativeLayout linear_top_mine;
/*

    @BindView(R.id.linear_my_inviter)
    LinearLayout linear_my_inviter;
*/




    @BindView(R.id.main_message)
    ImageView message;


    @BindView(R.id.text_bi)
    TextView textXunibiCount;

    @BindView(R.id.text_crash)
    TextView textCrashCount;

    @BindView(R.id.rela_maidian)
    RelativeLayout rela_maidian;

    @BindView(R.id.rela_xianjin)
    RelativeLayout rela_xianjin;


    @BindView(R.id.rela_lookme)
    RelativeLayout rela_lookme;

    @BindView(R.id.rela_good_friends)
    RelativeLayout rela_good_friends;


  /*  @BindView(R.id.liner_mai)
    LinearLayout  liner_mai;*/
/*

    @BindView(R.id.linear_my_right)
    LinearLayout linear_my_right;
*/



    private String pbs_cny;

    private Context context;
    private MineRefreshBean mineRefreshBean;
    private String usdtPbs,usdCNY,hatchAmount;

    private double pbssAmount;
    private double crystalAmount = 0;
    private double rebateCrystal =0;
    private UsdtPbsBean usdtPbsBean;


    float[] outerRadii = {20, 20, 40, 40, 60, 60, 80, 80};//外矩形 左上、右上、右下、左下 圆角半径
    private HarvestBean harvestBean;

    /**
     * 单例模式
     *
     * @return
     */
    public static MineFragment2 getInstance() {
        return new MineFragment2();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_mines, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity();
        EventBusUtils.getInstance().register(this);
        getData();
        getPBSData();
        getRefresh();

    }



    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_mine.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
                getPBSData();

            }
        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                mineRefreshBean = GsonUtils.parseJson(resulte, MineRefreshBean.class);
                if ((mineRefreshBean != null) && mineRefreshBean.getStatusCode() == Constant.SUCCESS && (mineRefreshBean.getData() != null)) {
                    if (mineRefreshBean.getData().getBasicInfo() != null) {
                        GlideUtils.displayRound(getContext(), BaseApi.baseUrlNoApi + mineRefreshBean.getData().getBasicInfo().getHeadImg(), mHeadIv);
                        String company = mineRefreshBean.getData().getBasicInfo().getCompany();
                        String positionmine = mineRefreshBean.getData().getBasicInfo().getPosition();
                        nameTv.setText(mineRefreshBean.getData().getBasicInfo().getRealName());
                        int vipLevel = mineRefreshBean.getData().getUserInfo().getVipLevel();
                        int crowdFundLevel = mineRefreshBean.getData().getUserInfo().getCrowdFundLevel();
//                        ivVipLevel.setVisibility(View.VISIBLE);
                        tvVipLevel.setVisibility(View.VISIBLE);
                        GradientDrawable drawable = (GradientDrawable) tvVipLevel.getBackground();

                        if (crowdFundLevel == 1) {
                            ivVipLevel.setImageResource(R.drawable.vip_icon_6);

                            tvVipLevel.setText("合伙人");
                            Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_6);
                            drawableLeft.setBounds(0, 0, Utils.dip2px(getContext(), 16), Utils.dip2px(getContext(), 16));
                            tvVipLevel.setCompoundDrawables(drawableLeft, null, null, null);

                            tvVipLevel.setTextColor(getResources().getColor(R.color._ff2890d1));
                            drawable.setStroke(Utils.dip2px(getContext(), 1), getResources().getColor(R.color._ff2890d1));
                            tvVipLevel.setBackground(drawable);
                             // linear_my_right.setVisibility(View.VISIBLE);
                        } else if (crowdFundLevel == 2) {
                            ivVipLevel.setImageResource(R.drawable.vip_icon_7);
                            tvVipLevel.setText("股东");

                            Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_7);
                            drawableLeft.setBounds(0, 0, Utils.dip2px(getContext(), 16), Utils.dip2px(getContext(), 16));
                            tvVipLevel.setCompoundDrawables(drawableLeft, null, null, null);

                            tvVipLevel.setTextColor(getResources().getColor(R.color._ffffa820));
                            drawable.setStroke(Utils.dip2px(getContext(), 1), getResources().getColor(R.color._ffffa820));
                            tvVipLevel.setBackground(drawable);
                             // linear_my_right.setVisibility(View.VISIBLE);
                        } else if (vipLevel == 1 || vipLevel == 2) {
                            ivVipLevel.setImageResource(R.drawable.vip_icon_1);
                            tvVipLevel.setText(R.string.my_senior_member1);

                            Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_1);
                            drawableLeft.setBounds(0, 0, Utils.dip2px(getContext(), 16), Utils.dip2px(getContext(), 16));
                            tvVipLevel.setCompoundDrawables(drawableLeft, null, null, null);

                            tvVipLevel.setTextColor(getResources().getColor(R.color._ffe66464));
                            drawable.setStroke(Utils.dip2px(getContext(), 1), getResources().getColor(R.color._ffe66464));
                            tvVipLevel.setBackground(drawable);
                        } else if (vipLevel == 3 || vipLevel == 4) {
                            ivVipLevel.setImageResource(R.drawable.vip_icon_3);
                            tvVipLevel.setText(R.string.my_baijin_member1);

                            Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_3);
                            drawableLeft.setBounds(0, 0, Utils.dip2px(getContext(), 16), Utils.dip2px(getContext(), 16));
                            tvVipLevel.setCompoundDrawables(drawableLeft, null, null, null);

                            tvVipLevel.setTextColor(getResources().getColor(R.color._ff7c93a7));
                            drawable.setStroke(Utils.dip2px(getContext(), 1), getResources().getColor(R.color._ff7c93a7));
                            tvVipLevel.setBackground(drawable);
                        } else if (vipLevel == 5) {
                            ivVipLevel.setImageResource(R.drawable.vip_icon_5);
                            tvVipLevel.setText(R.string.primary_member);


                            Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_5);
                            drawableLeft.setBounds(0, 0, Utils.dip2px(getContext(), 16), Utils.dip2px(getContext(), 16));
                            tvVipLevel.setCompoundDrawables(drawableLeft, null, null, null);


                            tvVipLevel.setTextColor(getResources().getColor(R.color._ff999999));
                            drawable.setStroke(Utils.dip2px(getContext(), 1), getResources().getColor(R.color._ff999999));
                            tvVipLevel.setBackground(drawable);
                        } else {
                            ivVipLevel.setVisibility(View.GONE);
                            tvVipLevel.setVisibility(View.GONE);
                        }
                        int userId = mineRefreshBean.getData().getBasicInfo().getUserId();
                        if (TagConstants.ZH.equals(SharePreferenceUtils.getBaseSharePreference().readLanguage())) {
//                        MineFragment.this.userId.setText(userId + "");
                            setTextNumAnimation(userId);
                        } else {
                            int user_id = (userId % 100 % 10);//获取个位数上的数
                            if (user_id == 1) {
                                MineFragment2.this.userId.setText(userId + "st");
                            } else if (user_id == 2) {
                                MineFragment2.this.userId.setText(userId + "nd");
                            } else if (user_id == 3) {
                                MineFragment2.this.userId.setText(userId + "rd");
                            } else if (user_id >= 4) {
                                MineFragment2.this.userId.setText(userId + "th");
                            }
                        }
//                    int a =1234;
//                    int b = (a/100);//获取百位上的数字，包括百位以上的
//                    int c = (a%100/10);//获取十位上的数字
//                    int d = (a%100%10);//获取个位数上的数
                        positionTv.setText(mineRefreshBean.getData().getBasicInfo().getPositionTitle());
//                        if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionmine)) {
//                            positionTv.setText(positionmine);
//                        } else if (TextUtils.isEmpty(positionmine) && !TextUtils.isEmpty(company)) {
//                            positionTv.setText(company);
//                        } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionmine)) {
//                            positionTv.setText(company + Constant.LARGE_SPACE + positionmine);
//                        }
//                    positionTv.setText(mineRefreshBean.getData().getBasicInfo().getCompany()+"\t--\t"+mineRefreshBean.getData().getBasicInfo().getPosition());
                    }
                    if (mineRefreshBean.getData().getUserInfo() != null) {

                        if (mineRefreshBean.getData().getUserInfo().get_id() == 2517) {
                            //   linear_manager.setVisibility(View.VISIBLE);
                        }
                        String pbsAmount = BigDecimalUtil.round(mineRefreshBean.getData().getUserInfo().getLastHarvestAmount(), Constant.SCALE_NUM);
//
                        String userChannle = mineRefreshBean.getData().getUserInfo().getUserChannel();
                        SharePreferenceUtils.getBaseSharePreference().saveCrystalAmount(mineRefreshBean.getData().getUserInfo().getCrystalAmount() + "");
                        SharePreferenceUtils.getBaseSharePreference().saveUserChannel(mineRefreshBean.getData().getUserInfo().getUserChannel());
                        SharePreferenceUtils.getBaseSharePreference().saveVipLevel(mineRefreshBean.getData().getUserInfo().getVipLevel());

                    }

                }
                refreshLayout_mine.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_mine.finishRefresh(false);
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).mineRefresh();
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
        },  (RxAppCompatActivity) getActivity()).getUsdtPbs();
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
                        pbssAmount = Double.valueOf(add_deposit_futures_tran_sellTrade).doubleValue();
                        double lastHarvestAmount = Double.valueOf(harvestBean.getData().getUserInfo().getLastHarvestAmount()).doubleValue();
                        double pbsFrozen = Double.valueOf(harvestBean.getData().getUserInfo().getPbsFrozen()).doubleValue();
                        double pbsDrawLockedAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsDrawLockedAmount()).doubleValue();
                        double pbsAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsAmount()).doubleValue();

                        textCrashCount.setText(BigDecimalUtil.addComma4(rebateCrystal));
                        textXunibiCount.setText(BigDecimalUtil.addComma4(pbssAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount + pbsAmount));
                        double totalPBS = Double.valueOf(pbs_cny).doubleValue() * (pbssAmount + lastHarvestAmount);

                        Log.i("getSellTrade", "onNext: "+add_deposit_futures_tran_sellTrade);
                        String allPrice = BigDecimalUtil.addComma4(rebateCrystal + totalPBS + crystalAmount);
                       // textAllValue.setText(allPrice);
                    }
                }
                refreshLayout_mine.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_mine.finishRefresh(false/*,false*/);//传入false表示刷新失败
            }
        },  (RxAppCompatActivity) getActivity()).harvestList(true);

    }








    /**
     * 设置int类型数字动画
     * @param userIds
     */
    private void setTextNumAnimation(int userIds) {
        MineFragment2.this.userId.setDuration(1000 * 5);
        MineFragment2.this.userId.setAnimInterpolator(new AccelerateInterpolator());
        MineFragment2.this.userId.setNumberWithAnim(userIds);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.getInstance().unregister(this);
        MineFragment2.this.userId.clearAnimator();


    }
    @Override
    public void onResume() {
        super.onResume();
        getData();
        getPBSData();
//        getData();
    }

  /*  @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            getData();
            getPBSData();
        }
    }
*/
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBean t) {

    }


    @OnClick({R.id.myWallet, R.id.mHeadIv, R.id.llFriends, R.id.llAbout,
          R.id.llMember, R.id.linear_buyHistory, R.id.linear_top_mine, R.id.main_message, R.id.main_setting, R.id.rela_maidian,
            R.id.rela_xianjin, R.id.liner_mai, R.id.rela_lookme, R.id.rela_good_friends, R.id.relative_dynamic,
           R.id.linear_eat_zhunchou,R.id.linear_cooperation})

    public void onViewClicked(View view) {
        switch (view.getId()) {

            /*case R.id.linear_my_inviter:
                StartActivityManager.startMyInviterActivity(getActivity(), SharePreferenceUtils.getBaseSharePreference().readInvitedCode());
                break;*/

            case R.id.linear_eat_zhunchou://餐饮众筹
                Intent intent1 = new Intent(getContext(), ZhongChouActivity.class);
                startActivity(intent1);
                break;

            case R.id.linear_cooperation://合作加盟
                StartActivityManager.startWebViewActivity((Activity) context, "加盟政策", " https://mp.weixin.qq.com/s/Jl3lFOSyJf_l3Evbjxiqqg", false, true);
                break;

            case R.id.linear_top_mine: //
                if(mineRefreshBean!=null&&(mineRefreshBean.getData()!=null))
                {
                    //                ct.startActivity(BasicInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PositionTitle",mineRefreshBean.getData().getBasicInfo().getPositionTitle());
                    bundle.putString("_id",mineRefreshBean.getData().getBasicInfo().get_id());
                    bundle.putString("Company",mineRefreshBean.getData().getBasicInfo().getCompany());
                    bundle.putInt("ProfessionalIdentity",mineRefreshBean.getData().getBasicInfo().getProfessionalIdentity());
                    bundle.putString("RealName",mineRefreshBean.getData().getBasicInfo().getRealName());
                    bundle.putString("Position",mineRefreshBean.getData().getBasicInfo().getPosition());
                    bundle.putString("School",mineRefreshBean.getData().getBasicInfo().getSchool());
                    bundle.putString("Education",mineRefreshBean.getData().getBasicInfo().getEducation()+"");
                    bundle.putString("Country",mineRefreshBean.getData().getBasicInfo().getCountry());
                    bundle.putString("Number",mineRefreshBean.getData().getBasicInfo().getNumber());
                    bundle.putString("Nature",mineRefreshBean.getData().getBasicInfo().getNature());
                    bundle.putString("HeadImg",mineRefreshBean.getData().getBasicInfo().getHeadImg());
                    bundle.putInt("genders",mineRefreshBean.getData().getBasicInfo().getGender());
                    bundle.putString("city",mineRefreshBean.getData().getBasicInfo().getCity());
                    bundle.putString("selfDesc",mineRefreshBean.getData().getBasicInfo().getSelfDesc());
                    if(StringUtil.isNotEmpty(mineRefreshBean.getData().getBasicInfo().getCityCode())){
                        bundle.putInt("cityCode",Integer.valueOf(mineRefreshBean.getData().getBasicInfo().getCityCode()).intValue());
                    }

                    Intent intent = new Intent(getActivity(), BasicInfoActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.linear_buyHistory: //我的订单
                StartActivityManager.startGoodsBuyList(context);
                //TODO
                break;
            case R.id.llMember://会员
               /* if (mineRefreshBean !=null&&(mineRefreshBean.getData()!=null)&&(mineRefreshBean.getData().getBasicInfo()!=null)&&(mineRefreshBean.getData().getUserInfo()!=null)) {
                    StartActivityManager.startSeniorMemberActivity(context,mineRefreshBean.getData().getBasicInfo().getRealName(),
                            mineRefreshBean.getData().getBasicInfo().getHeadImg(),mineRefreshBean.getData().getUserInfo().getVipLevel());
                } else  {
                    T.showShort("数据加载未完成");
                }*/
                Intent intent5 = new Intent(getContext(), BlackCardActivity.class);
                startActivity(intent5);
                break;
            case R.id.llAbout://关于我们
                StartActivityManager.startAboutUsActivity(context);
                break;
            case R.id.myWallet://我的钱包
                //CrystalRechargeActivity
                StartActivityManager.startCrystalRechargeActivity(context);
                break;
            case R.id.llFriends://邀请好友
                StartActivityManager.startInvitFriendV2Activity(context);
                break;

            case R.id.mHeadIv:
                imgAlert();
                break;


            case R.id.main_setting://设置
                StartActivityManager.startSetActivity(context);
                break;

            case R.id.main_message://消息
                Intent intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
                break;

            case R.id.rela_maidian://脉点
                StartActivityManager.startMyWalletActivity(context);
                break;
            case R.id.rela_xianjin://现金
                StartActivityManager.startMyWalletActivity(context);
                break;



            case R.id.liner_mai:
                StartActivityManager.startMyWalletActivity(context);
                break;


          /*  case R.id.linear_my_right:
                if (mineRefreshBean != null && (mineRefreshBean.getData() != null)) {
                    StartActivityManager.startPartnerRightsActivity(getActivity(), mineRefreshBean.getData().getBasicInfo().getRealName(),
                            mineRefreshBean.getData().getUserInfo().getCrowdFundLevel(), mineRefreshBean.getData().getBasicInfo().getGender(), mineRefreshBean.getData().getBasicInfo().getHeadImg(),mineRefreshBean.getData().getBasicInfo().getPositionTitle());

                }
                break;
*/

            case R.id.rela_lookme://访问

                Intent intent4 = new Intent(getContext(), LookMeActivity.class);
                startActivity(intent4);
                break;

            case R.id.rela_good_friends://好友

                Intent intent2 = new Intent(getContext(), GoodFriendsActivity.class);
                startActivity(intent2);
                break;

            case R.id.relative_dynamic://动态
                Intent intent3 = new Intent(getContext(), DynamicActivity.class);
                startActivity(intent3);
                break;


        }



    }



    /******************************以下为拍照的逻辑****************************************/
    /**
     * 拍照弹出框
     */
    private void imgAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle(getResources().getString(R.string.chooseAPhoto));
        final int[] position = {0};
        builder.setSingleChoiceItems(new String[]{getResources().getString(R.string.Camera), getResources().getString(R.string.gallery)}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position[0] = which;
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                takePhoto(position[0]);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * 初始化照片
     */
    private void takePhoto(int num) {
        TakePhoto takePhoto = getTakePhoto();
        CropOptions.Builder builderTake = new CropOptions.Builder();
        builderTake.setAspectX(800).setAspectY(800);
        builderTake.setWithOwnCrop(true);
        File file = new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            if (!mkdirs) {
                T.showShort(getResources().getString(R.string.FileDirectoryCreationFailed));
            }
        }

        Uri imageUri = Uri.fromFile(file);
        CompressConfig config = new CompressConfig.Builder()
//                .setMaxSize(1024 * 2)
                .setMaxPixel(400)
                .enableReserveRaw(true)
                .create();
        takePhoto.onEnableCompress(config, true);
        if (num == 0) {
            takePhoto.onPickFromCaptureWithCrop(imageUri, builderTake.create());
        }
        if (num == 1) {
            takePhoto.onPickFromDocumentsWithCrop(imageUri, builderTake.create());
        }
    }
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        TImage image = result.getImage();
        if (image.getCompressPath() == null) {
            T.showShort(getResources().getString(R.string.FailedToGetPhotos));
            return;
        }
        GlideUtils.displayFile(getContext(), image.getCompressPath(), headIv);
        upImg(new File(image.getCompressPath()));
    }
    /**
     * 头像上传
     *
     * @param file
     */
    private void upImg(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("IMAGE", file.getName(), new ProgressRequestBody(requestBody,
                new UploadProgressListener() {
                    @Override
                    public void onProgress(long currentBytesCount, long totalBytesCount) {

                    }
                }));
        new UserTask(new HttpOnNextListener() {

            @Override
            public void onNext(String resulte, String method) {
                TakePhotoBean takePhotoBean = GsonUtils.parseJson(resulte, TakePhotoBean.class);
                EventBusUtils.getInstance().postEvent(new HeadEventPost());
//                T.showShort(takePhotoBean.getMessage());
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "userHead");

    }

    public void setFriendCount(int friendsCount){
        text_friend_count.setText(String.valueOf(friendsCount));
    }

    public void setAccessCount(int accessCount){
        text_access_count.setText(String.valueOf(accessCount));
    }

    @Override
    public void onDestroy() {
        EventBusUtils.getInstance().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(MineRefreshEvent message){
        getData();
    }




    private  OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setHotlist(int count) {
        text_dynamic_count.setText(String.valueOf(count));
    }


    public interface OnItemClickListener{
        void intemClick();
    }

}
