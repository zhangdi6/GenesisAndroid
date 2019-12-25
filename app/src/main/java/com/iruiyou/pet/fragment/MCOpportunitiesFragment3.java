package com.iruiyou.pet.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.ReleaseActivity;
import com.iruiyou.pet.activity.TextActivity;
import com.iruiyou.pet.activity.WebViewNewActivity;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.HuoDongAdapter;
import com.iruiyou.pet.adapter.home_adapter.KeChengAdapter;
import com.iruiyou.pet.adapter.home_adapter.RenqiAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.EventBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.iruiyou.pet.bean.HuoDongBean;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.bean.UsdtPbsBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.ProfileInfoDialog;
import com.iruiyou.pet.utils.SkipAdDialog;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.utils.custom.BannerRecyclerView;
import com.iruiyou.pet.utils.custom.BannerScaleHelper;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;


/**
 * 商机界面
 */
public class MCOpportunitiesFragment3 extends BaseFragment {

    private static final String TAG = "MCOpportunitiesFragment";
    private BannerScaleHelper bannerScaleHelper;
    private float mPading = 40 ;

    /**
     * 单例模式
     *
     * @return
     */
    public static MCOpportunitiesFragment3 getInstance() {
        return new MCOpportunitiesFragment3();
    }


    @BindView(R.id.get_money)//签到得奖励
    TextView sign_in_money;

    @BindView(R.id.constraint_backge)//钱包整体
    ConstraintLayout backge;

    @BindView(R.id.goto_money)//进入我的钱包页面
    ImageView gotomoney;

    @BindView(R.id.constraint_order)//现在下单
    ConstraintLayout order;

    @BindView(R.id.constraint_reserve)//会议室预定
    ConstraintLayout reserve;

    @BindView(R.id.constraint_shopping)//脉乐购
    ConstraintLayout shopping;

    @BindView(R.id.constraint_friendpatry)//脉友会
    ConstraintLayout friendpatry;

    @BindView(R.id.linear_text)//线下活动
    LinearLayout ltext;

    @BindView(R.id.mai_money)//脉点现金
    TextView textCrashCount;

    @BindView(R.id.recy_huodong)//活动列表
            BannerRecyclerView recy_huodong;

    @BindView(R.id.linear_recommend)//为你推荐
    LinearLayout recommend;

    @BindView(R.id.recy_recommend)//推荐列表
    RecyclerView recy_recommend;

    @BindView(R.id.linear_class)//线上课程
    LinearLayout lclass;

    @BindView(R.id.linear_foryou)//为你推荐子条目
     LinearLayout linear_foryou;

    @BindView(R.id.recy_class)//课程列表
    RecyclerView recy_class;

    @BindView(R.id.linear_shopping)//脉乐购
    LinearLayout lshopping;

    @BindView(R.id.recy_shopping)//购物列表
    RecyclerView recy_shopping;


    @BindView(R.id.title)//title
    TextView mTitle;
   /* @BindView(R.id.location)//定位数据
    TextView mlocation;*/
    @BindView(R.id.add)//加号
    ImageView mAdd;
    @BindView(R.id.view_top)//布局
    ConstraintLayout mLayout;
    @BindView(R.id.line)//线
    View mLine;

   /* @BindView(R.id.refresh_loading)//刷新
    SmartRefreshLayout refreshLayout_mine;*/

    @BindView(R.id.scroll_view)//scrollview的监听
            ScrollView scroll_view;

    private UsdtPbsBean usdtPbsBean;
    private String usdtPbs, usdCNY, hatchAmount;
    private String pbs_cny;
    private HarvestBean harvestBean;
    private double pbssAmount;
    private double crystalAmount = 0;
    private double rebateCrystal = 0;
    private HuoDongBean bean;
    private List<HuoDongBean.DataBean> list;//活动集合
    HuoDongAdapter huoDongAdapter;//活动适配器
    private List<GetCourseIntroBean.DataBean> kclist;//课程集合
    private GetCourseIntroBean beans;
    private int lastEssayId = 0;
    private OpportunitiesGoodsBean beangoods;//脉乐购
    private List<OpportunitiesGoodsBean.Item> listgoods;
    private HomeRefreshBean homeRefreshBean;
    private List<GetEssaysBean.DataBean> data;
    private float MAX_TEXT_SIZE = 26 ;
    private float MIN_TEXT_SIZE = 20 ;
    private float MAX_SCROLLVIEW_DISTENCE = 1000 ;
    private float WINDOW_WIDTH ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_new, container,false);
        ButterKnife.bind(this, view);
        recy_huodong.setHasFixedSize(true);
        recy_huodong.setNestedScrollingEnabled(false);
        recy_recommend.setHasFixedSize(true);
        recy_recommend.setNestedScrollingEnabled(false);
        recy_class.setHasFixedSize(true);
        recy_class.setNestedScrollingEnabled(false);
        recy_shopping.setHasFixedSize(true);
        recy_shopping.setNestedScrollingEnabled(false);

        return view;
    }



    @SuppressLint("NewApi")
    @Override
    public void OnActCreate(Bundle savedInstanceState) {


        WINDOW_WIDTH = getResources().getDisplayMetrics().widthPixels;


        mTitle.setTextSize(MAX_TEXT_SIZE);
     setViewLayoutParams(mLine,WINDOW_WIDTH - dip2px(getActivity(),40) , (int) dip2px(getActivity(),2));
        /*   layoutParams.height = (int) getResources().getDimension(R.dimen.dp_70);*/


        getPBSData();//我的钱包
        requestData();//资产总额
        getData();//线下活动
        getkeClass(0,0,true);//线上课程
        initdata();//脉乐购



            scroll_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                    if (i1 <= 0){
                        mTitle.setTextSize(MAX_TEXT_SIZE);
                        setViewLayoutParams(mLine, WINDOW_WIDTH - dip2px(getActivity(),40), (int) dip2px(getActivity(),2));
                        /*layoutParams.height = (int) getResources().getDimension(R.dimen.dp_70);*/
                        /*mLayout.setLayoutParams(layoutParams);*/

                    }else if (i1> 0 && i1< MAX_SCROLLVIEW_DISTENCE){

                        float titleSize = (1 - i1 / MAX_SCROLLVIEW_DISTENCE )* MAX_TEXT_SIZE ;
                        float v = WINDOW_WIDTH - dip2px(getContext(), 40);
                        float v2 = ( i1 / MAX_SCROLLVIEW_DISTENCE )* mPading  ;
                        float lineLong = v2+v2+v2 +v;
                        float titleLong = ( 1- i1 / MAX_SCROLLVIEW_DISTENCE )* dip2px(getContext(),70) ;
                        Log.e("uuu",lineLong+"");
                        /*Log.e("nnn",lineLong +"=====");*/

                        if (titleSize <= MIN_TEXT_SIZE){

                            mTitle.setTextSize(MIN_TEXT_SIZE);

                            /*layoutParams.height = 44;*/
                            /*setViewLayoutParams(mLine,0,(int)dip2px(getContext(),2));*/

                        }else{

                            /*layoutParams.height = (int)titleLong ;*/
                            mTitle.setTextSize(titleSize);
                            /*setViewLayoutParams(mLine,lineLong,(int)dip2px(getContext(),2));*/

                        }

                        if (lineLong <= WINDOW_WIDTH-dip2px(getContext(),40)){
                            setViewLayoutParams(mLine,WINDOW_WIDTH-dip2px(getContext(),40),
                                    (int) dip2px(getActivity(),2));
                        }else{
                            setViewLayoutParams(mLine,lineLong,  (int) dip2px(getActivity(),2));
                        }



                       /* if (lineLong<= WINDOW_WIDTH-dip2px(getContext(),40)){
                            setViewLayoutParams(mLine,WINDOW_WIDTH-dip2px(getContext(),40),
                                    2);
                        }else{
                            setViewLayoutParams(mLine,lineLong, 2);
                        }
*/

                    }else{

                    }
                }
            });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    if (SharePreferenceUtils.getBaseSharePreference().readVipLevel() < 1) {
                        T.showShort("请开通会员后再发布商机！");
                    } else {
                        Intent intent = new Intent(getActivity(), ReleaseActivity.class);
                        startActivity(intent);
                    }

                }else{
                    Intent intent = new Intent(getActivity(), QuickLoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        loadAd();





    }
    public static float dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return  (dipValue * scale + 0.5f);
    }

    public static void setViewLayoutParams(View view, float nWidth, int nHeight) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.height != nHeight || lp.width != nWidth) {
            lp.width = (int) nWidth;
            lp.height = nHeight;
            view.setLayoutParams(lp);
        }
    }


    private void initTab() {

        /*mTitle.setTextSize(MAX_TEXT_SIZE);
        mLine.setPadding(20,0,20,0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLayout.getLayoutParams();

        layoutParams.height = (int) getResources().getDimension(R.dimen.dp_70);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scroll_view.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                    Log.e("nnn",i1+"----");
                    if (i1<=0){
                        mTitle.setTextSize(MAX_TEXT_SIZE);
                        mLine.setPadding(20,0,20,0);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLayout.getLayoutParams();
                        layoutParams.height = (int) getResources().getDimension(R.dimen.dp_70);
                    }else if (i1> 0 && i1< MAX_SCROLLVIEW_DISTENCE){

                        float a = (1 - i1 / MAX_SCROLLVIEW_DISTENCE )* MAX_TEXT_SIZE ;
                        Log.e("nnn",a+"=====");
                        mTitle.setTextSize(a);


                    }else{

                    }
                }
            });
        }*/

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
        }, (RxAppCompatActivity) getActivity()).getUsdtPbs();
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
                        //   textXunibiCount.setText(BigDecimalUtil.addComma4(pbssAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount + pbsAmount));
                        double totalPBS = Double.valueOf(pbs_cny).doubleValue() * (pbssAmount + lastHarvestAmount);

                        Log.i("getSellTrade", "onNext: " + add_deposit_futures_tran_sellTrade);
                        String allPrice = BigDecimalUtil.addComma4(rebateCrystal + totalPBS + crystalAmount);
                        // textAllValue.setText(allPrice);
                    }
                }
               /* refreshLayout_mine.finishRefresh(true);//传入false表示刷新失败*/
            }

            @Override
            public void onError(ApiException e) {
         /*       refreshLayout_mine.finishRefresh(false*//*,false*//*);//传入false表示刷新失败*/
            }
        }, (RxAppCompatActivity) getActivity()).harvestList(true);


        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                homeRefreshBean = GsonUtils.parseJson(resulte, HomeRefreshBean.class);
                if (homeRefreshBean.getStatusCode() == Constant.SUCCESS) {
                    /*if(StringUtil.isNotEmpty(homeRefreshBean.getData().getNotice()))
                    {
                        businessChanceAdapter2.refreshNotice(homeRefreshBean.getData().getNotice(),View.VISIBLE);
                    }
                    else
                    {
                        businessChanceAdapter2.refreshNotice("",View.GONE);
                    }*/

                    if (homeRefreshBean.getData().getBasicInfo() != null) {
                        SharePreferenceUtils.getBaseSharePreference().saveBasicCount(homeRefreshBean.getData().getStatisticsInfo().getBasicCount());
                        SharePreferenceUtils.getBaseSharePreference().saveEDU(homeRefreshBean.getData().getStatisticsInfo().getEducationCount());
                        SharePreferenceUtils.getBaseSharePreference().saveWorkCount(homeRefreshBean.getData().getStatisticsInfo().getWorkCount());
                        SharePreferenceUtils.getBaseSharePreference().savePhotoCount(homeRefreshBean.getData().getStatisticsInfo().getPhotoCount());
                        SharePreferenceUtils.getBaseSharePreference().saveUserChannel(homeRefreshBean.getData().getUserInfo().getUserChannel());

                        //刷新用户头像到融云上
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + homeRefreshBean.getData().getBasicInfo().getUserId(),
                                homeRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(BaseApi.baseUrlNoApi + homeRefreshBean.getData().
                                getBasicInfo().getHeadImg())));
                    }
                    if (homeRefreshBean.getData().getUserInfo() != null) {

                        long lastMarkTime = homeRefreshBean.getData().getUserInfo().getLastMarkTime();
                        boolean isShow = false;
                        if((System.currentTimeMillis()-lastMarkTime)>=21600000){
                            isShow = true;
                        }
                        setCoinStatus(isShow);
                        SharePreferenceUtils.getBaseSharePreference().saveLastMarkTime(lastMarkTime);

                        SharePreferenceUtils.getBaseSharePreference().saveInviteCode(homeRefreshBean.getData().getUserInfo().getInviteCode());
                        SharePreferenceUtils.getBaseSharePreference().saveInvitedCode(homeRefreshBean.getData().getUserInfo().getInvitedCode());
//                        amountTv.setText(String.valueOf(homeRefreshBean.getData().getUserInfo().getCombat()));

                        HomeRefreshBean.DataBean.UserInfoBean userInfo = homeRefreshBean.getData().getUserInfo();
                        String userInfos = GsonUtil.GsonString(homeRefreshBean.getData().getUserInfo());//把对象转为JSON格式的字符串
                        SharePreferenceUtils.getBaseSharePreference().saveUserInfo(userInfos);
                        SharePreferenceUtils.getBaseSharePreference().saveVipLevel(homeRefreshBean.getData().getUserInfo().getVipLevel());

//                        double pbsAmountResult = BigDecimalUtil.round(userInfo.getPbsAmount() - userInfo.getLastHarvestAmount(), Constant.SCALE_NUM);

//                        if (pbsAmountResult >0.0||pbsAmountResult>0) {
//                            businessChanceAdapter.setCoinStatus(pbsAmountResult + "脉点");
////                            gemstone.setAlpha(255);
//                        }else {
//                            businessChanceAdapter.setCoinStatus(getString(R.string.mining));
////                            gemstone.setAlpha(90);
//                        }
                    }

                    HomeRefreshBean.DataBean.StatisticsInfoBean statisticsInfo = homeRefreshBean.getData().getStatisticsInfo();
                    if (statisticsInfo != null) {
                        int top = statisticsInfo.getBasicCount() + statisticsInfo.getBlockchainCount() + statisticsInfo.getWorkCount() + statisticsInfo.getEducationCount();
                        twoPoint(((double) top / (double) homeRefreshBean.getData().getInfoCount() * 100));
                    }
                    //公司主页

                    List<HomeRefreshBean.DataBean.CompaniesBean> companies = homeRefreshBean.getData().getCompanies();
                    if (companies != null) {
                        if (companies.size() > 0) {
                            for (int i = 0; i < companies.size(); i++) {
                                //添加群名和人数到map集合
                                StringUtil.addData(companies.get(i).getGroupName(),companies.get(i).getMemberCount());
                            }

                            //添加群组信息到融云库
                            RongIM.setGroupInfoProvider((s) -> {
                                int result = 0;

                                for (int i = 0; i < companies.size(); i++) {
                                    if (s.equals(companies.get(i).get_id() + ""))
                                        result = i;
                                }

                                return new Group(Constant.DEVGROUPID + companies.get(result).get_id() + "",

                                        companies.get(result).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + companies.get(result).getLogo()));

                            },true); //提供一群群组信息
                        }
                    }

                }
               /* refreshLayout_mine.finishRefresh(true);//传入false表示刷新失败*/
            }

            @Override
            public void onError(ApiException e) {
             /*   refreshLayout_mine.finishRefresh(false);*/
                Log.e("MCOpportunities",e.getMessage());
            }
        }, (MainActivity) getContext()).homeRefresh();


    }
    private String twoPoint(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        String format = df.format(d);
        SharePreferenceUtils.getBaseSharePreference().saveResume(format);
        if (format.equals(".00")) {
            SharePreferenceUtils.getBaseSharePreference().saveResume("0");
            return "0";
        }
        EventBusUtils.getInstance().postEvent(new EventBean());
        return format;
    }

    public void setCoinStatus(boolean isEnable) {

            if (!isEnable) {
               sign_in_money.setEnabled(false);
               sign_in_money.setText("正在生成中...");
            } else {
                sign_in_money.setEnabled(true);
                sign_in_money.setText("签到得奖励");

        }
    }




    @OnClick(value = {R.id.constraint_backge, R.id.linear_text, R.id.linear_class, R.id.constraint_order, R.id.constraint_reserve
    , R.id.constraint_shopping, R.id.linear_shopping, R.id.get_money, R.id.constraint_friendpatry, R.id.linear_recommend, R.id.linear_foryou, R.id.readbackge})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.constraint_backge://我的钱包
                StartActivityManager.startMyWalletActivity(getContext());
                break;

            case R.id.linear_text://线下活动
                Intent intent = new Intent(getContext(), TextActivity.class);
                startActivity(intent);
                break;

            case R.id.linear_class://线上课程
              //  if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    StartActivityManager.startHome_KeChengActivity(getContext());

                }else{
                    Intent intent1 = new Intent(getContext(), QuickLoginActivity.class);
                    getContext().startActivity(intent1);
                }
                break;

            case R.id.constraint_order://现在下单
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    String appid = "wxf580fa050af6696b";//AppId
                    IWXAPI wxapi = WXAPIFactory.createWXAPI(getActivity(), appid);
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_a67efc0c0a97";//小程序id
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE ;
                    wxapi.sendReq(req);

                }else{
                    Intent intent3 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent3);
                }
                break;

            case R.id.constraint_reserve://会议室预定
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                   // StartActivityManager.startWebViewActivity(getActivity(), "场地预定", "https://www.maichangapp.com/space/mall?userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId() + "&state=app&token=" + SharePreferenceUtils.getBaseSharePreference().readToken() + "&system=Android", false, true);
                    StartActivityManager.startWebViewActivity(getActivity(), "", "https://weixin.keruyun.com/shop/list?uniqueId=YD&shopId=810396404", false, true);

                }else{
                    Intent intent4 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent4);
                }
                break;
            case  R.id.constraint_shopping://脉乐购
              //  if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    StartActivityManager.startWebViewActivity((Activity) getContext(), "脉乐购", "https://shop40984708.youzan.com/v2/feature/ajNgY4peUG?redirect_count=1&sf=wx_sm&is_share=1&from_uuid=1a0fd804-2518-7f00-6662-989d1892a45f&from=groupmessage", false, true);
                }else{
                    Intent intent5 = new Intent(getContext(), QuickLoginActivity.class);
                    getContext().startActivity(intent5);
                }
                break;
            case R.id.linear_shopping://脉乐购
               // if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    StartActivityManager.startWebViewActivity((Activity) getContext(), "脉乐购", "https://shop40984708.youzan.com/v2/feature/ajNgY4peUG?redirect_count=1&sf=wx_sm&is_share=1&from_uuid=1a0fd804-2518-7f00-6662-989d1892a45f&from=groupmessage", false, true);
                }else{
                    Intent intent5 = new Intent(getContext(), QuickLoginActivity.class);
                    getContext().startActivity(intent5);
                }
                break;

            case R.id.get_money://签到得奖励
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()) {

                    DialogUtils.showAdSkipDialog(getContext(), new SkipAdDialog.AdDialogOnClick() {
                        @Override
                        public void onDialogClick(boolean isSkip) {
                            int adView;
                            if (isSkip) {
                                adView = 0;
                            } else {
                                adView = 1;
                            }
                            getCoine(adView);
                        }


                    });
                }else{
                    Intent intent3 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent3);
                }

                break;


            case R.id.readbackge://签到得奖励
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()) {

                    DialogUtils.showAdSkipDialog(getContext(), new SkipAdDialog.AdDialogOnClick() {
                        @Override
                        public void onDialogClick(boolean isSkip) {
                            int adView;
                            if (isSkip) {
                                adView = 0;
                            } else {
                                adView = 1;
                            }
                            getCoine(adView);
                        }


                    });
                }else{
                    Intent intent3 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.constraint_friendpatry://脉友会
                StartActivityManager.startWebViewActivity(getActivity(), "", "http://mp.weixin.qq.com/s?__biz=Mzg5MTA1NTYzOQ==&mid=100000833&idx=1&sn=50db1e33efe31a9ed39e653987772136&chksm=4fd27f3978a5f62fe074d8e722dcc9c2dcb37f3822476c5153439ca3c5ea0e39ac24c9104afe#rd", false, true);

                break;

            case R.id.linear_recommend://为你推荐
                //http://mp.weixin.qq.com/s?__biz=Mzg5MTA1NTYzOQ==&mid=100000843&idx=1&sn=42fb7f4cf035573c27011257cf3cc806&chksm=4fd27f3378a5f6250cc3c5e02a3fd0abadb5cb1c57ab25373252af6c49d3462371fe731b1c9e#rd
              //  StartActivityManager.startWebViewActivity(getActivity(), "", "http://mp.weixin.qq.com/s?__biz=Mzg5MTA1NTYzOQ==&mid=100000833&idx=1&sn=50db1e33efe31a9ed39e653987772136&chksm=4fd27f3978a5f62fe074d8e722dcc9c2dcb37f3822476c5153439ca3c5ea0e39ac24c9104afe#rd", false, true);
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    String appid = "wxf580fa050af6696b";//AppId
                    IWXAPI wxapi = WXAPIFactory.createWXAPI(getActivity(), appid);
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_a67efc0c0a97";//小程序id
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE ;
                    wxapi.sendReq(req);

                }else{
                    Intent intent3 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent3);
                }

                break;

            case R.id.linear_foryou://为你推荐子条目
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                    String appid = "wxf580fa050af6696b";//AppId
                    IWXAPI wxapi = WXAPIFactory.createWXAPI(getActivity(), appid);
                    WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                    req.userName = "gh_a67efc0c0a97";//小程序id
                    req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE ;
                    wxapi.sendReq(req);

                }else{
                    Intent intent3 = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent3);
                }
                break;

        }


    }


    /**
     * 获取签到
     */
    private void getCoine(int adview) {
        boolean isStart = true;
        ACache aCache = ACache.get(getContext());
        LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
        if (loginNewBean != null && loginNewBean.getData() != null) {
            LoginNewBean.DataBean.BasicInfoBean basicInfo = loginNewBean.getData().getBasicInfo();
            if (StringUtil.isNotEmpty(basicInfo.getPosition()) && StringUtil.isNotEmpty(basicInfo.getHeadImg()) && (basicInfo.getProfessionalIdentity() != 0)) {
                doSetAnim(adview);
                isStart = false;
            }

            if (isStart) {
                DialogUtils.showKnowDialog(((BaseActivity) getContext()), new ProfileInfoDialog.KnowClick() {
                    @Override
                    public void onClick() {
                        StartActivityManager.startBaseicInfoActivity((BaseActivity) getContext(), basicInfo);
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
                    MediaPlayer player = MediaPlayer.create(getContext(), R.raw.sound);
                    player.start();

                    ObjectAnimator move2 = ObjectAnimator.ofFloat(sign_in_money, "translationY", 0, -DensityUtil.dip2px(150));
                    ObjectAnimator move3 = ObjectAnimator.ofFloat(sign_in_money, "alpha", 1, 0);
                    ObjectAnimator move5 = ObjectAnimator.ofFloat(sign_in_money, "alpha", 0, 1);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(move2, move3, move5);
                    animatorSet.setDuration(1500);
                    animatorSet.start();
                    if (adView == 1) {
                        mttRewardVideoAd.showRewardVideoAd(((BaseActivity) getContext()));
                    }
                    double amount = ((Double) commonBean.getData()).doubleValue();
                    /*String textValue = textXuNiBi.getText().toString().trim();*/
                        /*if(StringUtil.isEmpty(textValue)){
                            textValue="0.0";
                        }*/
                    /*textXuNiBi.setText(String.valueOf(Double.valueOf(textValue).doubleValue()+amount));*/
                    Log.e("test", "amount is " + amount);
                    sign_in_money.setEnabled(false);
                 //   gemstone.setAlpha(0.5f);
                    sign_in_money.setText("正在生成中...");
                    SharePreferenceUtils.getBaseSharePreference().saveLastMarkTime(System.currentTimeMillis());
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getContext()).harvest(adView);
    }


    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                bean = new Gson().fromJson(resulte, HuoDongBean.class);

                list = bean.getData();

                LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                recy_huodong.setLayoutManager(gridLayoutManager);
                huoDongAdapter = new HuoDongAdapter(list, 2);
                if (list.size()>2){
                    bannerScaleHelper = new BannerScaleHelper();
                    bannerScaleHelper.setFirstItemPos(1000);
                    bannerScaleHelper.attachToRecyclerView(recy_huodong);
                }

                recy_huodong.setAdapter(huoDongAdapter);
                huoDongAdapter.notifyDataSetChanged();
                setListener();

            }

            @Override
            public void onError(ApiException e) {

            }
        }, (MainActivity) getContext()).getKongianHuoDong();

    }

    public void setListener() {


        huoDongAdapter.setOnItemClickListener(new HuoDongAdapter.OnItemClickListener() {
            @Override
            public void getclicklistener(int position) {
              //  if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    Intent intent = new Intent(getContext(), WebViewNewActivity.class);
                    intent.putExtra("title", "visibility");
                    //intent.putExtra("url", list.get(position).getUrl());
                    intent.putExtra("url","http://www.maichangapp.com/space/activity?id="
                            +list.get(position).get_id()+"&system=Android");
                    intent.putExtra("text",list.get(position).getTitle());
                    intent.putExtra("img",list.get(position).getDescription());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent);
                }


            }
        });

    }


    private void getkeClass(int num, int lastCounts, boolean isInit) {

        int categoryId = 0;
        if (num != 0) {
            categoryId = num + 4;
        }

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.i(TAG, "onNext: "+resulte);
                beans =new Gson().fromJson(resulte, GetCourseIntroBean.class);
                kclist = beans.getData();

                LinearLayoutManager ridLayoutManager = new LinearLayoutManager(getContext());
                recy_class.setLayoutManager(ridLayoutManager);
                KeChengAdapter kechengAdapter = new KeChengAdapter(kclist);
                recy_class.setAdapter(kechengAdapter);
                kechengAdapter.notifyDataSetChanged();
                kechengAdapter.setOnItemClickListener(new KeChengAdapter.OnItemClickListener() {
                    @Override
                    public void getclicklistener(int position) {
                       // if (App.isLogin){
                        if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                            GetCourseIntroBean.DataBean bean = kclist.get(position);

                            StartActivityManager.startCourseContent2Activity(getContext(), bean, position);

                        }else{
                            Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                            getContext().startActivity(intent);
                        }



                    }
                });

                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        },(MainActivity) getContext()).getCourseIntro(categoryId,lastEssayId);



    }

    private  void  initdata(){

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.i("tag", "onNext: "+resulte);
                beangoods =new Gson().fromJson(resulte, OpportunitiesGoodsBean.class);

                /*bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.DataBean.class);*/

                listgoods = beangoods.getData().getResponse().getItems();

                GridLayoutManager ridLayoutManager = new GridLayoutManager(getContext(), 2);
                recy_shopping.setLayoutManager(ridLayoutManager);
                RenqiAdapter renqiAdapter = new RenqiAdapter(listgoods);
                recy_shopping.setAdapter(renqiAdapter);
                renqiAdapter.notifyDataSetChanged();


                //点击事件设置在这里

                renqiAdapter.setOnItemClickListener(new RenqiAdapter.OnItemClickListener() {
                    @Override
                    public void getclicklistener(int position) {

                      //  if(App.isLogin){
                        if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                            Intent intent = new Intent(getContext(), WebViewNewActivity.class);
                            intent.putExtra("title", listgoods.get(position).getTitle());
                            intent.putExtra("url", listgoods.get(position).getDetail_url());
                            getContext().startActivity(intent);

                        }else{
                            Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                            getContext().startActivity(intent);
                        }


                    }
                });

                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        },(MainActivity) getContext()).getYZGoodsListV3();

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
        TTAdSdk.getAdManager().createAdNative(getContext()).loadRewardVideoAd(adSlot, new TTAdNative.RewardVideoAdListener() {
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



}
