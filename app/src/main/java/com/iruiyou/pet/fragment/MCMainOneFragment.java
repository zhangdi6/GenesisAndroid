package com.iruiyou.pet.fragment;


import android.animation.ObjectAnimator;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.BasicInfoActivity;
import com.iruiyou.pet.activity.CompanyHomeActivity;
import com.iruiyou.pet.activity.EduExpeActivity;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.NodePartnerActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.WorkExpeActivity;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.CompanyHomeAdapter;
import com.iruiyou.pet.adapter.FindElitesAdapter;
import com.iruiyou.pet.adapter.IndexAdapter;
import com.iruiyou.pet.adapter.MainClassAdapter;
import com.iruiyou.pet.adapter.NodePartnerAdapter;
import com.iruiyou.pet.adapter.PulseFieldAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CheckFriends2Bean;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.bean.EventBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.iruiyou.pet.bean.MaichangBean;
import com.iruiyou.pet.bean.RecomendsBean;
import com.iruiyou.pet.rongyun.ConversationListActivity;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.RaiseNumberAnimTextView;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * Created by jiao on 2017/4/22.
 */
public class MCMainOneFragment extends BaseFragment implements IUnReadMessageObserver {
    private static final String TAG = "MCMainOneFragment";
//    private static MCMainOneFragment MCMainOneFragment = null;
    @BindView(R.id.gemstone)
    ImageView gemstone;
    @BindView(R.id.rl)
    RelativeLayout rl;
//    @BindView(R.id.resumeTV)
    TextView resumeTV;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.identity)
    TextView identity;
//    @BindView(R.id.friend0)
    TextView friend0;
//    @BindView(R.id.friendIv)
    ImageView friendIv;
    @BindView(R.id.tv0)
    TextView tv0;
//    @BindView(R.id.power0)
    TextView power0;
//    @BindView(R.id.friend1)
    TextView friend1;
//    @BindView(R.id.friendIv1)
    ImageView friendIv1;
//    @BindView(R.id.tv1)
    TextView tv1;
//    @BindView(R.id.power1)
    TextView power1;
//    @BindView(R.id.friend2)
    TextView friend2;
//    @BindView(R.id.friendIv2)
    ImageView friendIv2;
//    @BindView(R.id.tv2)
    TextView tv2;
//    @BindView(R.id.power2)
    TextView power2;
    Unbinder unbinder;
//    @BindView(R.id.invitFriend0)
    ConstraintLayout invitFriend0;
    Unbinder unbinder1;
//    @BindView(R.id.wallet)
    ImageView wallet;
//    @BindView(R.id.friend)
    ImageView friend;
//    @BindView(R.id.resumeLl)
    LinearLayout resumeLl;
    Unbinder unbinder2;
    Unbinder unbinder3;
//    @BindView(R.id.homeAmount)
    LinearLayout homeAmount;
//    @BindView(R.id.invitFriend1)
    ConstraintLayout invitFriend1;
//    @BindView(R.id.invitFriend2)
    ConstraintLayout invitFriend2;
    Unbinder unbinder4;
//    @BindView(R.id.amountTv)
    TextView amountTv;
    Unbinder unbinder5;
//    @BindView(R.id.integrity)
    TextView integrity;
    @BindView(R.id.starLl)
    LinearLayout starLl;
    //    @BindView(R.id.line)
//    View line;
//    @BindView(R.id.gold)
    ImageView gold;
//    @BindView(R.id.tv_crystal_num)
    TextView tv_crystal_num;//水晶数量
    Unbinder unbinder6;
    @BindView(R.id.myPets)
    TextView myPets;
    @BindView(R.id.lastHarvestAmount)
    TextView lastHarvestAmount;
    ImageView imNews;
    @BindView(R.id.myresumeTv)
    ImageView myresumeTv;
//    @BindView(R.id.companyRecyclerView)
    RecyclerView companyRecyclerView;
//    @BindView(R.id.rankingRecyclerView)
    RecyclerView rankingRecyclerView;
//    @BindView(R.id.cl_inviting_friends)
    ConstraintLayout cl_inviting_friends;
//    @BindView(R.id.classRecyclerView)
    RecyclerView classRecyclerView;
//    @BindView(R.id.linear_class)
    LinearLayout linearClass;
    @BindView(R.id.text_notice_title)
    TextView textNoticeTitle;
    @BindView(R.id.ll_pbs_notice)
    LinearLayout llNotice;
    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;
    @BindView(R.id.tv_vein_resource_library_number)
    RaiseNumberAnimTextView tv_vein_resource_library_number;
    @BindView(R.id.tv_vein_asset_valuation)
    RaiseNumberAnimTextView tv_vein_asset_valuation;
    @BindView(R.id.tv_my_friendsourcing)
    TextView tv_my_friendsourcing;
    @BindView(R.id.tv_my_invitation)
    TextView tv_my_invitation;
    @BindView(R.id.tv_vein_my_asset_valuation)
    TextView tv_vein_my_asset_valuation;
    @BindView(R.id.nodePartnerRecyclerView)
    RecyclerView nodePartnerRecyclerView;
    @BindView(R.id.tv_vein_connections_number)
    RaiseNumberAnimTextView tv_vein_connections_number;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;
    @BindView(R.id.im_no_data)
    ImageView im_no_data;
    @BindView(R.id.myNetworkBankRecyclerView)
    MaxRecyclerView myNetworkBankRecyclerView;
    @BindView(R.id.text_bi)
    TextView textXuNiBi;
    @BindView(R.id.text_crash)
    TextView textCrash;
    private ObjectAnimator animator;
    private MaxRecyclerView recyclerView;
    private IndexAdapter indexAdapter;
    private HomeRefreshBean homeRefreshBean;
    private TextView tv_im_red;
    private RelativeLayout rl_im_red, rlPowerNews;
    private SmartRefreshLayout refreshLayout_power;
    private RecomendsBean recomendsBean;
    private FindElitesAdapter findElitesAdapter;
    private MainClassAdapter mainClassAdapter;
    private GetCourseIntroBean getCourseIntroBean;
    private ArrayList<OccupationBeen> occupationBeens;
    private OccupationAdapter occupationAdapter;


    private ArrayList<String> list;
    private HashMap<String, Boolean> map;//key=手机号;value=状态
    private HashMap<String, String> phoneMap;//key=手机号;value=姓名
    private ACache aCache;
    private RxPermissions rxPermissions;
    private PulseFieldAdapter adapter;
    private List<CheckRegisterBean.DataBean> isAccountList;
    private boolean contactAllow = true;
    private int nums = -1;
    public static final String Person = "$";
    public static final String SIGN = "*";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getData(list);
        }
    };
    /**
     * 单例模式
     *
     * @return
     */
    public static MCMainOneFragment getInstance() {
        return new MCMainOneFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_one, null);
//        ButterKnife.bind(this, view);

        recyclerView = view.findViewById(R.id.recyclerView);
        refreshLayout_power = view.findViewById(R.id.refreshLayout_power);
        imNews = view.findViewById(R.id.imNews);
        rl_im_red = view.findViewById(R.id.rl_im_red);
        rlPowerNews = view.findViewById(R.id.rlPowerNews);
        tv_im_red = view.findViewById(R.id.tv_im_red);
        imNews.setOnClickListener(this);
        rlPowerNews.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {

        aCache = ACache.get(getContext());
        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        adapter = new PulseFieldAdapter();
//        ImmersionBar.with(this).statusBarDarkFont(true).init();
        occupationBeens=new ArrayList<>();
        getData();
//        getDataRanking();
        getRefresh();
        indexAdapter = new IndexAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(indexAdapter);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_home_head, null);
        ButterKnife.bind(this, inflate);
        setPositionRecyclerView();
        indexAdapter.setHeaderView(inflate);
        mainClassAdapter=new MainClassAdapter();
        classRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        classRecyclerView.setAdapter(mainClassAdapter);
        classRecyclerView.setHasFixedSize(true);
//        classRecyclerView.addItemDecoration(new SpacesItemDecoration(15));
//        doSetAnim(0);
//        currencyType = SharePreferenceUtils.getBaseSharePreference().readCurrencyType();//设置pbs显示的文字和状态
//        if (currencyType == Constant.TIPS1) {
//            lastHarvestAmount.setText(getString(R.string.mining));
//            gemstone.setAlpha(90);//设置图片透明度0-255不透明
//        }

        MyLinearLayoutManager horizontalLayoutManager = new MyLinearLayoutManager(getActivity());
        horizontalLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        nodePartnerRecyclerView.setLayoutManager(horizontalLayoutManager);
        nodePartnerRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        //获取所有未读消息
        final Conversation.ConversationType[] conversationTypes = {
                Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP, Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.PUBLIC_SERVICE, Conversation.ConversationType.APP_PUBLIC_SERVICE
        };

        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, conversationTypes);

        //公司主页
        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        companyRecyclerView.setLayoutManager(linearLayoutManager);
        companyRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        MyLinearLayoutManager linearLayoutManager2 = new MyLinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        rankingRecyclerView.setLayoutManager(linearLayoutManager2);
        rankingRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        imNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Map<String, Boolean> supportedConversation = new HashMap<>();
//                supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
//                RongIM.getInstance().startConversationList(getActivity(), supportedConversation);//跳转的界面

                Intent intent = new Intent(getActivity(), ConversationListActivity.class);
                startActivity(intent);

                //上传头像和name
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String id) {
//                        return new UserInfo(homeRefreshBean.getData().getBasicInfo().getUserId()+"",homeRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(homeRefreshBean.getData().getBasicInfo().getHeadImg()));
//                    }
//                }, true);
            }
        });
        doRepeatAnim();

        setListener();
//        getClassData(0,0);

        getMailList();
    }

    /**
     * 获取职场算力排行
     */
    private void getDataRanking() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                recomendsBean = GsonUtils.parseJson(resulte, RecomendsBean.class);
                if(recomendsBean!=null)
                {
                    if (recomendsBean.getStatusCode() == Constant.SUCCESS) {
                        List<RecomendsBean.DataBean> data = recomendsBean.getData();
                        findElitesAdapter = new FindElitesAdapter(getActivity(), data);
                        rankingRecyclerView.setAdapter(findElitesAdapter);
                        findElitesAdapter.notifyDataSetChanged();
                        findElitesAdapter.setItemClickListener(new FindElitesAdapter.MyItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                if(recomendsBean!=null&&(recomendsBean.getData()!=null))
                                {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("userid", recomendsBean.getData().get(position).getBasicInfo().getUserId());
                                    bundle.putString("realName", recomendsBean.getData().get(position).getBasicInfo().getRealName());//get(position).getBasicInfo().getRealName()
                                    Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            }
                        });
                    } else {
                        T.showShort(recomendsBean.getMessage());
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getRecomends();
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_power.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getData();
            }
        });
    }

    /**
     * 监听
     */
    private void setListener() {
        indexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                startActivity(ResumeActivity2.class);
                switch (position) {

//                    case 0:
////                        startActivity(ResumeActivity2.class);
//                        StartActivityManager.startResumeActivity2(getActivity(), Constant.SWITCH_YES);
//                        break;
//                    case 1:
////                        startActivity(ResumeActivity2.class);
//                        StartActivityManager.startResumeActivity2(getActivity(), Constant.SWITCH_YES);
//                        break;
                    case 0:
//                        startActivity(ResumeActivity2.class);
                        if (homeRefreshBean.getData().getStatisticsInfo().getPhotoCount() < 1) {
                            StartActivityManager.startResumeActivity2(getActivity(), Constant.SWITCH_YES);
                        }
                        break;
                    case 1://添加基本资料
//                        startActivity(BasicInfoActivity.class);
//                        BriefRefreshBean briefRefreshBean = (BriefRefreshBean) adapter.getData().get(position);
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("123", briefRefreshBean);

//                        Intent intent = new Intent(getActivity(), BasicInfoActivity.class);
//                        intent.putExtra("HeadImg",homeRefreshBean.getData().getBasicInfo().getHeadImg());
//                        startActivity(intent);

                        if (homeRefreshBean.getData().getStatisticsInfo().getBasicCount() < 1) {
                            Bundle bundle = new Bundle();
                            bundle.putString("_id", homeRefreshBean.getData().getBasicInfo().get_id());
                            bundle.putString("Company", homeRefreshBean.getData().getBasicInfo().getCompany());
                            bundle.putInt("ProfessionalIdentity", homeRefreshBean.getData().getBasicInfo().getProfessionalIdentity());
                            bundle.putString("RealName", homeRefreshBean.getData().getBasicInfo().getRealName());
                            bundle.putString("Position", homeRefreshBean.getData().getBasicInfo().getPosition());
                            bundle.putString("School", homeRefreshBean.getData().getBasicInfo().getSchool());
                            bundle.putString("Education", homeRefreshBean.getData().getBasicInfo().getEducation());
                            bundle.putString("Country", homeRefreshBean.getData().getBasicInfo().getCountry());
                            bundle.putString("Number", homeRefreshBean.getData().getBasicInfo().getNumber());
                            bundle.putString("Nature", homeRefreshBean.getData().getBasicInfo().getNature());
                            bundle.putString("HeadImg", homeRefreshBean.getData().getBasicInfo().getHeadImg());
//                        bundle.putSerializable("123", homeRefreshBean.getData().getBasicInfo());
                            Intent intent = new Intent(getActivity(), BasicInfoActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if (homeRefreshBean.getData().getStatisticsInfo().getWorkCount() < 1) {
                            Bundle bundle2 = new Bundle();
//                        bundle2.putSerializable(Constant.WORK, workInfosBean);
                            bundle2.putString(Constant.EDITORS, Constant.SWITCH_NO);
                            Intent intent2 = new Intent(getActivity(), WorkExpeActivity.class);
                            intent2.putExtras(bundle2);
                            startActivity(intent2);
//                        startActivity(WorkExpeActivity.class);
                        }
                        break;
                    case 3:
                        if (homeRefreshBean.getData().getStatisticsInfo().getEducationCount() < 1) {
                            Bundle bundle3 = new Bundle();
//                        bundle2.putSerializable(Constant.WORK, workInfosBean);
                            bundle3.putString(Constant.EDITORS, Constant.SWITCH_NO);
                            Intent intent3 = new Intent(getActivity(), EduExpeActivity.class);
                            intent3.putExtras(bundle3);
                            startActivity(intent3);
//                        startActivity(EduExpeActivity.class);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void getData() {
        String imToken = SharePreferenceUtils.getBaseSharePreference().readIMToken();
        RongIM.connect(imToken, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "###onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "###onSuccess---s" + s);
                //设置信息和uerid 匹配
//                RongIM.getInstance().setCurrentUserInfo(new UserInfo(s,"12121",Uri.parse("")));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "###onError--errorCode=" + errorCode);
            }
        });
        requestData();
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                homeRefreshBean = GsonUtils.parseJson(resulte, HomeRefreshBean.class);
                if (homeRefreshBean.getStatusCode() == Constant.SUCCESS) {
                    if(StringUtil.isNotEmpty(homeRefreshBean.getData().getNotice()))
                    {
                        textNoticeTitle.setText(homeRefreshBean.getData().getNotice());
                        llNotice.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        llNotice.setVisibility(View.GONE);
                    }
                    if (homeRefreshBean.getData().getBasicInfo() != null) {
                        String company = homeRefreshBean.getData().getBasicInfo().getCompany();
                        String positionhome = homeRefreshBean.getData().getBasicInfo().getPosition();
                        SharePreferenceUtils.getBaseSharePreference().saveBasicCount(homeRefreshBean.getData().getStatisticsInfo().getBasicCount());
                        SharePreferenceUtils.getBaseSharePreference().saveEDU(homeRefreshBean.getData().getStatisticsInfo().getEducationCount());
                        SharePreferenceUtils.getBaseSharePreference().saveWorkCount(homeRefreshBean.getData().getStatisticsInfo().getWorkCount());
                        SharePreferenceUtils.getBaseSharePreference().savePhotoCount(homeRefreshBean.getData().getStatisticsInfo().getPhotoCount());
                        SharePreferenceUtils.getBaseSharePreference().saveUserChannel(homeRefreshBean.getData().getUserInfo().getUserChannel());

                        GlideUtils.displayRound(getContext(), BaseApi.baseUrlNoApi + homeRefreshBean.getData().getBasicInfo().getHeadImg(), iv);
                        //刷新用户头像到融云上
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + homeRefreshBean.getData().getBasicInfo().getUserId(), homeRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(BaseApi.baseUrlNoApi + homeRefreshBean.getData().getBasicInfo().getHeadImg())));
                        name.setText(homeRefreshBean.getData().getBasicInfo().getRealName());
//                        identity.setText(CodeUtils.getInstance().getProfessional(homeRefreshBean.getData().getBasicInfo().getProfessionalIdentity()));
                        if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionhome)) {
                            identity.setText(positionhome);
                        } else if (TextUtils.isEmpty(positionhome) && !TextUtils.isEmpty(company)) {
                            identity.setText(company);
                        } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionhome)) {
                            identity.setText(company + "\t\t" + positionhome);
                        }

                    }
                    if (homeRefreshBean.getData().getUserInfo() != null) {
                        SharePreferenceUtils.getBaseSharePreference().saveInviteCode(homeRefreshBean.getData().getUserInfo().getInviteCode());
                        SharePreferenceUtils.getBaseSharePreference().saveInvitedCode(homeRefreshBean.getData().getUserInfo().getInvitedCode());
                        amountTv.setText(String.valueOf(homeRefreshBean.getData().getUserInfo().getCombat()));
                        friend0.setText(getResources().getString(R.string.invitingFriends) + "\n" + "(" + homeRefreshBean.getData().getUserInfo().getInviteCount() + "/5)");
                        friend1.setText(getResources().getString(R.string.invitingFriends) + "\n" + "(" + homeRefreshBean.getData().getUserInfo().getInviteCount() + "/10)");
                        friend2.setText(getResources().getString(R.string.invitingFriends) + "\n" + "(" + homeRefreshBean.getData().getUserInfo().getInviteCount() + "/20)");

                        HomeRefreshBean.DataBean.UserInfoBean userInfo = homeRefreshBean.getData().getUserInfo();
                        double pbsAmount = BigDecimalUtil.round(userInfo.getPbsAmount(), Constant.SCALE_NUM_FOUR);
                        myPets.setText(BigDecimalUtil.addComma4(pbsAmount) + "");
//                        rankingTv.setText(homeRefreshBean.getData().getUserInfo().getCombat());//我的算力
                        integrity.setText(homeRefreshBean.getData().getUserInfo().getCombat());//我的算力
                        String userInfos = GsonUtil.GsonString(homeRefreshBean.getData().getUserInfo());//把对象转为JSON格式的字符串
                        SharePreferenceUtils.getBaseSharePreference().saveUserInfo(userInfos);
                        SharePreferenceUtils.getBaseSharePreference().saveVipLevel(homeRefreshBean.getData().getUserInfo().getVipLevel());

//                        textXuNiBi.setText(BigDecimalUtil.loadIntoUseFitWidth(String.valueOf(userInfo.getPbsAmount())));

                        double pbsAmountResult = BigDecimalUtil.round(userInfo.getPbsAmount() - userInfo.getLastHarvestAmount(), Constant.SCALE_NUM);
                        double crystalAmount = userInfo.getCrystalAmount();
//                        if (!TextUtils.isEmpty(crystalAmount)) {
////                            tv_crystal_num.setText(BigDecimalUtil.round(crystalAmount, Constant.SCALE_NUM));
//                            tv_crystal_num.setText(BigDecimalUtil.addComma(crystalAmount));
//                        }
                        tv_crystal_num.setText(BigDecimalUtil.addComma(String.valueOf(crystalAmount)));
                        if (pbsAmountResult >0.0||pbsAmountResult>0) {
                            lastHarvestAmount.setText(pbsAmountResult + "PBS");
//                            gemstone.setAlpha(255);
                        }else {
                            lastHarvestAmount.setText(getString(R.string.mining));
//                            gemstone.setAlpha(90);
                        }
//                        textCrash.setText(BigDecimalUtil.addComma(String.valueOf(userInfo.getRebateCrystal())));
                    }
                    HomeRefreshBean.DataBean.StatisticsInfoBean statisticsInfo = homeRefreshBean.getData().getStatisticsInfo();
                    if (statisticsInfo != null) {
                        int top = statisticsInfo.getBasicCount() + statisticsInfo.getBlockchainCount() + statisticsInfo.getWorkCount() + statisticsInfo.getEducationCount();
//                        integrity.setText(BigDecimalUtil.removeEndZero(twoPoint(((double) top / (double) homeRefreshBean.getData().getInfoCount() * 100))) + "%");//资料完整度
                        twoPoint(((double) top / (double) homeRefreshBean.getData().getInfoCount() * 100));
                        List<String> l = new ArrayList<>();
                        l.add(statisticsInfo.getBasicCount() + "");
                        l.add(String.valueOf(statisticsInfo.getBlockchainCount()));
                        l.add(String.valueOf(statisticsInfo.getWorkCount()));
                        l.add(String.valueOf(statisticsInfo.getEducationCount()));
                        indexAdapter.setNewData(l);
                    }
                    //公司主页
                    List<HomeRefreshBean.DataBean.CompaniesBean> companies = homeRefreshBean.getData().getCompanies();
                    if (companies != null) {
                        if (companies.size() > 0) {
                            CompanyHomeAdapter companyHomeAdapter = new CompanyHomeAdapter(getActivity(), companies);
                            companyRecyclerView.setAdapter(companyHomeAdapter);
                            companyHomeAdapter.notifyDataSetChanged();
                            for (int i = 0; i < companies.size(); i++) {
                                //添加群名和人数到map集合
                                StringUtil.addData(companies.get(i).getGroupName(),companies.get(i).getMemberCount());
                            }

                            //公司主页监听
                            companyHomeAdapter.setItemClickListener(new CompanyHomeAdapter.MyItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
//                                    StartActivityManager.startCompanyHomeActivity(getActivity());
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("companyid", companies.get(position).get_id());
                                    bundle.putString("companyName", companies.get(position).getName());
                                    bundle.putString("groupName", companies.get(position).getGroupName());
                                    bundle.putString("nick", companies.get(position).getNick());
                                    bundle.putString("desc", companies.get(position).getDesc());
                                    bundle.putString("logo", companies.get(position).getLogo());
                                    bundle.putInt("memberCount", companies.get(position).getMemberCount());
                                    Intent intent = new Intent(getActivity(), CompanyHomeActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                            //添加群组信息到融云库
                            RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {

                                @Override
                                public Group getGroupInfo(String s) {
                                    int result = 0;

                                    for (int i = 0; i < companies.size(); i++) {
                                        if (s.equals(companies.get(i).get_id() + ""))
                                            result = i;
                                    }

                                    return new Group(Constant.DEVGROUPID + companies.get(result).get_id() + "",

                                            companies.get(result).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + companies.get(result).getLogo()));

                                }
                            }, true); //提供一群群组信息

//                            RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + companies.get(result).get_id() + "", groupList.get(i).name, Uri.parse(groupList.get(i).picture))); // 添加一个群组信息

                        }
                    }

                }
                refreshLayout_power.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_power.finishRefresh(false);
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).homeRefresh();
    }



    /**
     * 请求当前账户所有的资产总额
     */
    private void requestData() {
        //pbs收支记录
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                HarvestBean harvestBean = GsonUtils.parseJson(resulte, HarvestBean.class);
                if (harvestBean.getStatusCode() == Constant.SUCCESS) {
                    if (harvestBean != null || harvestBean.getData() != null) {

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


                        double  crystalAmount = Double.valueOf(harvestBean.getData().getUserInfo().getCrystalAmount());
                        double rebateCrystal = Double.valueOf(harvestBean.getData().getUserInfo().getRebateCrystal());
                        double pbsAmount = Double.valueOf(add_deposit_futures_tran_sellTrade).doubleValue();
                        double lastHarvestAmount =Double.valueOf(harvestBean.getData().getUserInfo().getLastHarvestAmount()).doubleValue();
                        double pbsFrozen = Double.valueOf(harvestBean.getData().getUserInfo().getPbsFrozen()).doubleValue();
                        double pbsDrawLockedAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsDrawLockedAmount()).doubleValue();

                        textCrash.setText(BigDecimalUtil.addComma4(rebateCrystal+crystalAmount));
                        textXuNiBi.setText(BigDecimalUtil.addComma4(pbsAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount));
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, (MainActivity) getContext()).harvestList(true);

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        animator.end();
        animator = null;
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

//    @OnClick({R.id.rl, R.id.wallet, R.id.friend, R.id.starLl, R.id.invitFriend0, R.id.invitFriend1, R.id.invitFriend2,
//            R.id.homeAmount, R.id.myWalletView, R.id.ll_myCombatView, R.id.resumeLl, R.id.cl_inviting_friends,
//            R.id.cl_buying_crystal,R.id.ll_workplace_arithmetic_ranking,R.id.ll_pbs_notice,R.id.bottomCl,
//            R.id.ll_workplace_class,R.id.ll_vein_node_partner,R.id.linear_network_expand,R.id.linear_equity,
//    R.id.linear_wallet})
//    public void onViewClicked(View view) {
//        if (homeRefreshBean == null) {
//            T.showShort("数据加载未完成");
//            return;
//        }
//        switch (view.getId()) {
//            case R.id.linear_wallet:
//                StartActivityManager.startMyWalletActivity(getActivity());
//                break;
//            case R.id.linear_equity:
//                StartActivityManager.startWebViewActivity(getActivity(),"","http://maichang.mixboom.cn/?from=singlemessage&isappinstalled=0",true,true);
//
//                http://maichang.mixboom.cn/?from=singlemessage&isappinstalled=0
////                ++;
//                break;
//            case R.id.linear_network_expand:
//                StartActivityManager.startLookForPeople(getActivity(),"");
//                break;
//            case R.id.ll_vein_node_partner:
//                startActivity(NodePartnerActivity.class);
//                break;
//            case R.id.ll_workplace_class:
//                startActivity(MCClassActivity.class);
//                break;
//            case R.id.bottomCl:
//            case R.id.myWalletView:
//                startActivity(MyWalletActivity.class);
////                startActivity(PlayBackActivity.class);
//                break;
//            case R.id.ll_myCombatView://我的算力排行榜
//                Intent intent = new Intent(getContext(), CombatRankingActivity.class);
//                if (homeRefreshBean.getData().getUserInfo() != null) {
//                    intent.putExtra(TagConstants.Combat, homeRefreshBean.getData().getUserInfo().getCombat());
//                }
//                startActivity(intent);
//                break;
//            case R.id.rl:
//                break;
//            case R.id.cl_inviting_friends:
////                startActivity(InvitFriend2.class);
//                startActivity(InvitFriendActivity.class);
//                break;
//            case R.id.cl_buying_crystal:
//                if (homeRefreshBean.getData().getUserInfo() != null) {
//                    StartActivityManager.startCrystalRechargeActivity(getActivity(), String.valueOf(homeRefreshBean.getData().getUserInfo().getCrystalAmount()));
//                }
//
//                break;
////            case R.id.llIdentity:
////                startActivity(MyWalletActivity.class);
////                break;
////            case R.id.resumeTV:
////                startActivity(ResumeActivity2.class);
////                break;
//            case R.id.wallet:
//                startActivity(MyWalletActivity.class);
//                break;
//            case R.id.friend:
//                startActivity(InvitFriend.class);
//                break;
//            case R.id.starLl:
//                doSetAnim();
//                break;
//            case R.id.resumeLl://我的资料
//                StartActivityManager.startResumeActivity2(getActivity(), Constant.SWITCH_NO);
//                break;
//            case R.id.invitFriend0:
//                startActivity(InvitFriend2.class);
//                break;
//            case R.id.invitFriend1:
//                startActivity(InvitFriend2.class);
//                break;
//            case R.id.invitFriend2:
//                startActivity(InvitFriend2.class);
//                break;
//            case R.id.homeAmount:
//                startActivity(CalculationActivity.class);
//                break;
//            case R.id.ll_pbs_notice://公告
//                StartActivityManager.startWebViewActivity(getActivity(), getString(R.string.notice2), BaseApi.baseUrlNoApi + UrlContent.depositNotice);
//                break;
//            case R.id.ll_workplace_arithmetic_ranking:
//                String readUserInfo = SharePreferenceUtils.getBaseSharePreference().readUserInfo();
//                //json转成bean对象
//                HomeRefreshBean.DataBean.UserInfoBean userInfoBean = GsonUtil.GsonToBean(readUserInfo, HomeRefreshBean.DataBean.UserInfoBean.class);
//                Intent intent2 = new Intent(getContext(), CombatRankingActivity.class);
//                if ((recomendsBean!=null)&&(recomendsBean.getData() != null)) {
//                    intent2.putExtra(TagConstants.Combat, userInfoBean.getCombat());
//                }
//                startActivity(intent2);
//                break;
////            case R.id.llMyData:
////                StartActivityManager.startResumeActivity2(getActivity(), Constant.SWITCH_NO);
////                break;
//
//        }
//    }

    /**
     * 收取能量
     */
//    private void doSetAnim() {
//        //请求接口，收取能量
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
//                if (!TextUtils.isEmpty(commonBean.getMessage())) {
//                    T.showShort(commonBean.getMessage());
//                }
//                SharePreferenceUtils.getBaseSharePreference().saveCurrencyType(commonBean.getStatusCode());
////                if(type == 1){
//                if (commonBean.getStatusCode() == Constant.SUCCESS) {
//                    //添加添加的铃声
//                    MediaPlayer player = MediaPlayer.create(getActivity(), R.raw.sound);
//                    player.start();
//
//                    ObjectAnimator move2 = ObjectAnimator.ofFloat(starLl, "translationY", 0, -DensityUtil.dip2px(150));
//                    ObjectAnimator move3 = ObjectAnimator.ofFloat(starLl, "alpha", 1, 0);
//                    ObjectAnimator move5 = ObjectAnimator.ofFloat(starLl, "alpha", 0, 1);
//
//                    AnimatorSet animatorSet = new AnimatorSet();
//                    animatorSet.playTogether(move2, move3, move5);
//                    animatorSet.setDuration(1500);
//                    animatorSet.start();
//
//                }
//                lastHarvestAmount.setText(getString(R.string.mining));
////                gemstone.setAlpha(90);//设置图片透明度
////                }
//
//            }
//
//            @Override
//            public void onError(ApiException e) {
//
//            }
//        }, (RxAppCompatActivity) getActivity()).harvest();
//    }

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

    @Override
    public void onResume() {
        super.onResume();//刷新数据
        getData();
    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        /***5 开启单聊*/
//        if (RongIM.getInstance()!=null){
//            RongIM.getInstance().startPrivateChat(getActivity(),"dev67","123");
//        }
//        return super.onOptionsItemSelected(item);
//    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }

    @Override
    public void onCountChanged(int count) {//监听未读消息
        if (count == 0) {
            rl_im_red.setVisibility(View.GONE);
        } else if (count > 0 && count < 100) {
            rl_im_red.setVisibility(View.VISIBLE);
            tv_im_red.setText(String.valueOf(count));
        } else {
            rl_im_red.setVisibility(View.VISIBLE);
            tv_im_red.setText("...");
        }
    }

    //请求课程列表
//    private void getClassData(int num,int lastCounts) {
//        int categoryId = 0;
//        if(num!=0)
//        {
//            categoryId = num + 4;
//        }
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                if(StringUtil.isNotEmpty(resulte))
//                {
//                    getCourseIntroBean = GsonUtils.parseJson(resulte, GetCourseIntroBean.class);
//                    if(getCourseIntroBean!=null)
//                    {
//                        if (getCourseIntroBean.getStatusCode() == Constant.SUCCESS) {
//                            if(getCourseIntroBean.getData()!=null){
//                                if((getCourseIntroBean.getData()!=null))
//                                {
//                                    if(getCourseIntroBean.getData().size()>0)
//                                    {
//                                        if(getCourseIntroBean.getData().size()>2)
//                                        {
//                                            getCourseIntroBean.setData(getCourseIntroBean.getData().subList(0,2));
//                                        }
//                                        if(lastCounts==0)
//                                        {
//                                            mainClassAdapter.setNewData(getCourseIntroBean.getData());
//                                        }
//                                        else
//                                        {
//                                            mainClassAdapter.addData(getCourseIntroBean.getData());
//                                        }
//                                    }
//                                }
//                            }
//                        } else if(StringUtil.isNotEmpty(recomendsBean.getMessage())){
//                            T.showShort(recomendsBean.getMessage());
//                        }
//                    }
//                }
//
//                if(lastCounts==0&&mainClassAdapter.getData().size()>0)
//                {
//                    linearClass.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    linearClass.setVisibility(View.GONE);
//                }
//
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                T.showShort(e.getMessage());
//            }
//        }, (MainActivity) getContext()).getCourseIntro(categoryId,mainClassAdapter.getData().size());
//
//        //课程内容监听
//        mainClassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(), CourseContentActivity.class);
//                Bundle bundle = new Bundle();
//                GetCourseIntroBean.DataBean data = getCourseIntroBean.getData().get(position);
//                bundle.putSerializable("getCourseIntroBean", data);
////                bundle.putInt("CourseIntroPosition", position);
//                intent.putExtras(bundle);
//                Objects.requireNonNull(getActivity()).startActivity(intent);
//            }
//        });
//    }



    /**
     * 设置职位选择
     */
    private void setPositionRecyclerView() {

        SearchPresenter registerPresenter = new SearchPresenter();
        registerPresenter.setHidden(true);
        registerPresenter.IPresenter(getActivity());

        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        positionRecyclerView.setLayoutManager(linearLayoutManager);
        positionRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        //请求职业
        registerPresenter.getOccupationsList( new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    // 这块儿的逻辑（接受接口数据） 根据你们的实际业务调整
                    ArrayList<OccupationBeen> data=(ArrayList<OccupationBeen>) o;
//                    if(data.size()>0)
//                    {
//                        data.get(0).setSelect(true);
//                    }
                    occupationBeens.addAll(data);
                    occupationAdapter = new OccupationAdapter(getActivity(), occupationBeens, 3);
                    occupationAdapter.setHidden(true);
                    positionRecyclerView.setAdapter(occupationAdapter);
                    occupationAdapter.setItemClickListener((OccupationBeen firstPageListBean, int position) ->{
//                        for (int i = 0; i < occupationBeens.size(); i++) {
//                            if (occupationBeens.get(i).equals(firstPageListBean)) {
//                                occupationBeens.get(i).setSelect(true);
//                            } else {
//                                occupationBeens.get(i).setSelect(false);
//                            }
//                        }
//                        occupationAdapter.notifyDataSetChanged();
                        StartActivityManager.startLookForPeople(getActivity(),firstPageListBean.getDbKey()+"");
                    });
                }
            }

            @Override
            public void filed(Exception e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * 获取脉场数据
     */
    private void getMaiChang(int number) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                MaichangBean michangBean = GsonUtils.parseJson(resulte, MaichangBean.class);
                if (michangBean.getStatusCode() == Constant.SUCCESS) {
                    MaichangBean.DataBean data = michangBean.getData();
                    if (data != null) {
                        String totalCount = data.getTotalCount();// 脉场资源库
                        String myBase = data.getMyBase();// 我的资源的基数
                        String totalBase = data.getTotalBase();// 总资源的基数
                        String myFriendCount = data.getMyFriendCount();//我的好友数
                        String myContactCount = data.getMyContactCount();// 我的联系人数量
                        String myInviteCount = data.getMyInviteCount();//我的邀请数
                        double myProportion = data.getMyProportion();//我的人脉资源数%
                        double myProportions = BigDecimalUtil.mul(myProportion, 100);
                        NumberFormat nf = new DecimalFormat("#.#");//去除小数点后面的0
                        String vein_asset_valuation = BigDecimalUtil.mul(totalBase, totalCount, Constant.SCALE_NUM);//脉场资产估值
                        String totalCounts = BigDecimalUtil.addComma(totalCount);
                        ///
                        Float aFloat = Float.valueOf(totalCount);

                        setTextNumAnimation2(aFloat);
                        ///
                        tv_vein_resource_library_number.setText(totalCounts);
                        String vein_asset_valuations = BigDecimalUtil.addComma(vein_asset_valuation);
                        ///
//                        Float aFloat = Float.valueOf(vein_asset_valuation);
//                        setTextNumAnimation(aFloat);
                        ///
                        tv_vein_asset_valuation.setText(vein_asset_valuations + Constant.ONE_SPACE + getString(R.string.yuan));

                        tv_my_invitation.setText(getString(R.string.invitation, myInviteCount));//我邀请多少人
//                        if(nums!=-1){
                        String numsString = String.valueOf(number);
                        String mul_my_connections = BigDecimalUtil.mul(myBase, numsString, Constant.SCALE_NUM);//我的资产估值
                        String mul_my_connections_all = BigDecimalUtil.addComma(mul_my_connections);
                        tv_vein_my_asset_valuation.setText(mul_my_connections_all + Constant.ONE_SPACE + Constant.PBS1);//我的资产估值
//                        }

                        tv_my_friendsourcing.setText(getString(R.string.my_user, nf.format(myProportions) + Constant.PERCENT_SIGN));//我的人脉资源
                        List<MaichangBean.DataBean.NodesBean> nodes = michangBean.getData().getNodes();
                        if (michangBean.getData().getNodes() != null) {
                            //节点合伙人数据
                            if (nodes != null) {
                                if (nodes.size() > 0) {
                                    NodePartnerAdapter nodePartnerAdapter = new NodePartnerAdapter(getActivity(), nodes);
                                    nodePartnerRecyclerView.setAdapter(nodePartnerAdapter);
                                    nodePartnerAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < companies.size(); i++) {
//                        //添加群名和人数到map集合
//                        StringUtil.addData(companies.get(i).getGroupName(), companies.get(i).getMemberCount());
//                    }
                                    //节点合伙人列表监听
                                    nodePartnerAdapter.setItemClickListener(new NodePartnerAdapter.MyItemClickListener() {
                                        @Override
                                        public void onItemClick(int position) {
                                            startActivity(NodePartnerActivity.class);
                                        }
                                    });
                                }
                            }
                        }

                    }
                }
            }

            @Override
            public void onError(ApiException e) {
//                refreshLayout_mine.finishRefresh(false);
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).maichang();

    }


    /**
     * 获取通讯录
     */
    private void getMailList() {
//        myNetworkBankRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));//listView形式
        myNetworkBankRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));//GridView形式
        list = (ArrayList<String>) aCache.getAsObject(Constant.MAILLIST);
        map = (HashMap<String, Boolean>) aCache.getAsObject(Constant.MAILMAP);
        phoneMap = new HashMap<>();
        int phoneRefresh = SharePreferenceUtils.getBaseSharePreference().readPhoneRefresh();
        if (phoneRefresh == 1) {//add 再次刷新通讯录
            rxPermissions.request(android.Manifest.permission.READ_CONTACTS)
                    .subscribe(granted -> {
                        setContacts(granted);
                    });
        }
        if (list == null || map == null) {
            map = new HashMap<>();
            list = new ArrayList<>();
            rxPermissions.request(android.Manifest.permission.READ_CONTACTS)
                    .subscribe(granted -> {
                        setContacts(granted);
                    });
//            SharePreferenceUtils.getBaseSharePreference().savePhoneRefresh(1);
        } else {
//            adapter.setNewDatas(list, map);
            getData(list);
        }
        setListener();

        myNetworkBankRecyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST,
//                11,
//                num));
//        adapter.setEmptyView(inflate);

        adapter.notifyDataSetChanged();
//        RxView.clicks(viewById).compose(rxPermissions.ensure(android.Manifest.permission.READ_CONTACTS)).subscribe(granted -> {
//            setContacts(granted);
//        });
    }

    private void getData(List<String> list) {
//        tvMailList.setText(getString(R.string.Contacts) + "(" + list.size() + ")");
        SharePreferenceUtils.getBaseSharePreference().savePhoneRefresh(list.size() > 0 ? 1 : 0);
        List<String> phoneList = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split("\\" + Person);

//            if(split[1].length()>=11){
            phoneList.add(split[1]);
            phoneMap.put(split[1], split[0]);
//            }

        }
//        phoneList.add("17610475350");
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckRegisterBean checkRegisterBean = GsonUtils.parseJson(resulte, CheckRegisterBean.class);
                if (!TextUtils.isEmpty(checkRegisterBean.getMessage())) {
                    T.showShort(checkRegisterBean.getMessage());
                }
                if (checkRegisterBean.getStatusCode() == Constant.SUCCESS) {
                    isAccountList = checkRegisterBean.getData();//设置列表的分割线逻辑处理
//                    myNetworkBankRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                            DividerItemDecoration.VERTICAL_LIST,
//                            11,
//                            isAccountList.size() - 1));
//                for (CheckRegisterBean.DataBean bean : isAccountList) {
//                    list.remove(bean.getName() + Person + bean.getPhone());
//                    list.add(0, bean.getName() + Person + bean.getPhone());
//
//                }
                    for (int i = 0; i < isAccountList.size(); i++) {
                        CheckRegisterBean.DataBean dataBean = isAccountList.get(i);

//                    list.remove(dataBean.getName() + Person + dataBean.getPhone());
//                    if(dataBean.getPhone().length()>=11){
                        list.remove(phoneMap.get(dataBean.getPhone()) + Person + dataBean.getPhone());//添加替换通讯录姓名和账号姓名
                        list.add(i, dataBean.getRealName() + Person + dataBean.getPhone() + SIGN + phoneMap.get(dataBean.getPhone()));
//                    }

                    }
                    adapter.setNewDatas(list, map, isAccountList);
                    if (list.size() <= 0) {
                        if (contactAllow) {
                            tv_no_data.setText(getString(R.string.mail_list2));
                        }
                        im_no_data.setImageResource(R.drawable.no_data2);
                        ll_no_data.setVisibility(View.VISIBLE);
                        myNetworkBankRecyclerView.setVisibility(View.GONE);
                    } else {
                        ll_no_data.setVisibility(View.GONE);
                        myNetworkBankRecyclerView.setVisibility(View.VISIBLE);
                    }
                    //把联系人的数量加上好友的数量
                    requestMyFriends(list.size());
                    String toString = list.toString();

                    ///
//遍历map中的键

                    ArrayList<String> phoneList = new ArrayList<>();
                    ArrayList<String> nameList = new ArrayList<>();
                    phoneList.clear();
                    nameList.clear();
                    for (String key : phoneMap.keySet()) {
                        System.out.println("Key = " + key);
                        phoneList.add(key);
                    }
//遍历map中的值
                    for (String value : phoneMap.values()) {
                        System.out.println("Value = " + value);
                        nameList.add(value);
                    }
                    //集合转化成json
                    Gson g = new Gson();
//                    g.toJson(phoneList);
//                    String phoneJsonString = g.toJson(phoneList);
//                    String nameJsonString = g.toJson(nameList);
                    requestCheckFriends(phoneList,nameList);

                    ///
                }
//                setData(searchList, map);
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).checkRegister(phoneList);
    }


    /**
     * 检查联系人注册状态，并获取好友,上传手机数组和姓名数组
     */
    private void requestCheckFriends(ArrayList<String> phoneLists,ArrayList<String> namesLists) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckFriends2Bean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriends2Bean.class);
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).checkFriends(phoneLists,namesLists);

    }


    /**
     * 请求我的好友数据
     */
    private void requestMyFriends(int num) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckFriendsBean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriendsBean.class);
                if (checkFriendsBean.getStatusCode() == Constant.SUCCESS) {
                    List<CheckFriendsBean.DataBean> data = checkFriendsBean.getData();
                    //好友+联系人=我的人脉数
                    int datasize = data.size();
                    nums = num + datasize;
                    String addComma_all = BigDecimalUtil.addComma(String.valueOf(nums));
                    Float aFloat = Float.valueOf(nums);
                    setTextNumAnimation3(aFloat);
                    tv_vein_connections_number.setText(addComma_all + "");
                    getMaiChang(nums);
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getFriends();

    }

    /**
     * 设置float类型数字动画
     *
     * @param userIds
     */
    private void setTextNumAnimation3(float userIds) {
        NumberFormat nf = new DecimalFormat("#.#");//去除小数点后面的0
        tv_vein_connections_number.setDuration(800 * 5);
        tv_vein_connections_number.setAnimInterpolator(new AccelerateInterpolator());
        tv_vein_connections_number.setNumberWithAnim1(userIds);
    }


    /**
     * 设置float类型数字动画
     *
     * @param userIds
     */
    private void setTextNumAnimation2(float userIds) {
        tv_vein_resource_library_number.setDuration(800 * 5);
        tv_vein_resource_library_number.setAnimInterpolator(new AccelerateInterpolator());
        tv_vein_resource_library_number.setNumberWithAnim1(userIds);
    }


    /**
     * 设置通讯录
     *
     * @param granted
     */
    private void setContacts(Boolean granted) {
        contactAllow = granted;
        if (granted) {
//            buildProgressDialog(getResources().getString(R.string.Loadaddressbookfriends));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list = getContacts();
//                    for (int i = 0; i < contacts.length; i++) {
//                        list.add(i, contacts[i]);
//                    }
//                    list = Arrays.asList(contacts);
                    aCache.put(Constant.MAILLIST, list, ACache.TIME_DAY);
                    aCache.put(Constant.MAILMAP, map, ACache.TIME_DAY);

                    handler.sendEmptyMessage(0);
                }
            }).start();

        } else {
            T.showShort(getResources().getString(R.string.refuseJurisdiction));
            tv_no_data.setText(getString(R.string.mail_list3));
            im_no_data.setImageResource(R.drawable.no_data3);
            ll_no_data.setVisibility(View.VISIBLE);
            myNetworkBankRecyclerView.setVisibility(View.GONE);
            getData(list);
        }
    }



    /**
     * 读取通讯录
     *
     * @return
     */
    private ArrayList<String> getContacts() {

        Cursor cursor = null;
        ArrayList<String> list = new ArrayList<>();

        try {
            //联系人的Uri，也就是content://com.android.contacts/contacts
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            //指定获取_id和display_name两列数据，display_name即为姓名
            String[] projection = new String[]{
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME
            };
            //根据Uri查询相应的ContentProvider，cursor为获取到的数据集
            cursor = Objects.requireNonNull(getContext()).getContentResolver().query(uri, projection, null, null, null);
            String[] arr = new String[Objects.requireNonNull(cursor).getCount()];
            int i = 0;
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Cursor phonesCusor = null;
                    try {
                        Long id = cursor.getLong(0);
                        //获取姓名
                        String name = cursor.getString(1);
                        //指定获取NUMBER这一列数据
                        String[] phoneProjection = new String[]{
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        };
//                map.put(name, false);
//                arr[i] = name + Person;

                        //根据联系人的ID获取此人的电话号码
                        phonesCusor = getContext().getContentResolver().query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                phoneProjection,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                                null,
                                null);

                        //因为每个联系人可能有多个电话号码，所以需要遍历
                        if (phonesCusor != null && phonesCusor.moveToFirst()) {
                            do {
                                String num = phonesCusor.getString(0);
//                        arr[++i] += num;
                                //去掉手机号中的空格
                                String s = num.replaceAll(" ", "");
                                boolean mobileNO = StringUtil.isMobileNO(s);
                                if (mobileNO) {
                                    list.add(name + Person + s);
                                    map.put(s, false);
                                }

                            } while (phonesCusor.moveToNext());
//                    int length = arr[i].length();
//                    arr[i] = arr[i].substring(0, length - 1);
                        }
                    } catch (Exception e) {
                        //
                    } finally {
                        if (phonesCusor != null) {
                            phonesCusor.close();
                        }
                    }
//                i++;
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            //
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }


}
