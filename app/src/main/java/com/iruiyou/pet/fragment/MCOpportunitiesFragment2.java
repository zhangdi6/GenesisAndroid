package com.iruiyou.pet.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.ReleaseActivity;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.BusinessChanceAdapter;
import com.iruiyou.pet.adapter.BusinessChanceAdapter2;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.bean.DMOptionListBean;
import com.iruiyou.pet.bean.DoumiOptionBean;
import com.iruiyou.pet.bean.EventBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.iruiyou.pet.bean.InformationBean;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.CancelOrOkDialog;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.RecycleViewDivider;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.view.PageNumberRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * 商机界面
 */
public class MCOpportunitiesFragment2 extends BaseFragment implements  BusinessChanceAdapter.OnViewClickListener  {

    private static final String TAG = "MCOpportunitiesFragment";

    /**
     * 单例模式
     *
     * @return
     */
    public static MCOpportunitiesFragment2 getInstance() {
        return new MCOpportunitiesFragment2();
    }


    @BindView(R.id.recyclerView)
    PageNumberRecyclerView maxRecyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    public static final String Person = "$";
    public static final String SIGN = "*";
    private ArrayList<String> list;
    private HashMap<String, Boolean> map;//key=手机号;value=状态
    private HashMap<String, String> phoneMap;//key=手机号;value=姓名
    private HomeRefreshBean homeRefreshBean;
    private List<GetEssaysBean.DataBean> data;
    private List<CheckRegisterBean.DataBean> isAccountList;
    private ACache aCache;
    private RxPermissions rxPermissions;
    private BusinessChanceAdapter2 businessChanceAdapter2;
    private int lastEssayId = 0;


    private List<OpportunitiesGoodsBean.Item> listgoods;
    private List<GetCourseIntroBean.DataBean> listkech;
    private List<DMOptionListBean.DataBean> listzhiwei;



   private OpportunitiesGoodsBean bean;

   private GetCourseIntroBean beans;
   private DMOptionListBean beanss;
   private DoumiOptionBean doumiOptionBean;



    private DoumiOptionBean.DataValue shengfen;
    private DoumiOptionBean.DataValue currentArea;
    private DoumiOptionBean.DataValue currentQuanzhi;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getData(list);
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opportunities, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(value = {R.id.add})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.add:

              //  if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                if (SharePreferenceUtils.getBaseSharePreference().readVipLevel() < 1) {
                    T.showShort("请开通会员后再发布微博！");
                } else {
                    Intent intent = new Intent(getActivity(), ReleaseActivity.class);
                    startActivity(intent);
                }
                }else{
                    Intent intent = new Intent(getActivity(), QuickLoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
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


                        double  crystalAmount = Double.valueOf(harvestBean.getData().getUserInfo().getCrystalAmount());
                        double rebateCrystal = Double.valueOf(harvestBean.getData().getUserInfo().getRebateCrystal());
                        double pbsAmount = Double.valueOf(add_deposit_futures_tran_sellTrade).doubleValue();
                        double lastHarvestAmount =Double.valueOf(harvestBean.getData().getUserInfo().getLastHarvestAmount()).doubleValue();
                        double pbsFrozen = Double.valueOf(harvestBean.getData().getUserInfo().getPbsFrozen()).doubleValue();
                        double pbsDrawLockedAmount = Double.valueOf(harvestBean.getData().getUserInfo().getPbsDrawLockedAmount()).doubleValue();
                        businessChanceAdapter2.refreshWallet(BigDecimalUtil.addComma4(rebateCrystal+crystalAmount), BigDecimalUtil.addComma4(pbsAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount));
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        }, (MainActivity) getContext()).harvestList(true);




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
                        businessChanceAdapter2.setCoinStatus(isShow);
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
                refreshLayout.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout.finishRefresh(false);
                Log.e("MCOpportunities",e.getMessage());
            }
        }, (MainActivity) getContext()).homeRefresh();

    }

    @Override
    public void onResume() {
        super.onResume();

        RongIM.connect(SharePreferenceUtils.getBaseSharePreference().readIMToken(), new RongIMClient.ConnectCallback() {
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
        data = new ArrayList<>();
        listgoods = new ArrayList<>();
        businessChanceAdapter2 = new BusinessChanceAdapter2(getContext(), data);
        /*businessChanceAdapter2.setOnViewClickListener(this);*/
        maxRecyclerView.setAdapter(businessChanceAdapter2);
        maxRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //设置 Footer 为 经典式样
        refreshLayout.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                /*getGoodsList(false);*/
                maxRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                },1000);

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                /*getGoodsList(true);
//                requestData();*/
                maxRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                },1000);
            }
        });
        getGoodsList(true);
        maxRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(getContext(), 2), getResources().getColor(R.color._f1f1f1)));


    }

    private  void  initdata(){

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.i("tag", "onNext: "+resulte);
                bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.class);

                /*bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.DataBean.class);*/

                listgoods = bean.getData().getResponse().getItems();
                businessChanceAdapter2.setlist(listgoods);


                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        },(MainActivity) getContext()).getYZGoodsListV3();

    }


    private  void  initdatas(int num, int lastCounts, boolean isInit){


        int categoryId = 0;
        if (num != 0) {
            categoryId = num + 4;
        }
        //打印log发现在这里数据已经回来了
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                beans =new Gson().fromJson(resulte, GetCourseIntroBean.class);

                /*bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.DataBean.class);*/

                listkech = beans.getData();
                businessChanceAdapter2.setkclist(listkech);


                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        },(MainActivity) getContext()).getCourseIntro(categoryId,lastEssayId);

    }




    private  void  initdatass(String domain, String job_type, int job_date_type, int page, boolean isInit){


        //打印log发现在这里数据已经回来了
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                beanss =new Gson().fromJson(resulte, DMOptionListBean.class);
                listzhiwei = beanss.getData().getData();
                businessChanceAdapter2.setzwlist(listzhiwei);
                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        },(MainActivity) getContext()).getDoumiPositionList(domain, job_type, job_date_type, page);

    }




    /**
     * 获取通讯录
     */
    private void getMailList() {
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
        } else {
            getData(list);
        }

    }



    private void getData(List<String> list) {
        SharePreferenceUtils.getBaseSharePreference().savePhoneRefresh(list.size() > 0 ? 1 : 0);
        List<String> phoneList = new ArrayList<>();
        for (String s : list) {
            String[] split = s.split("\\" + Person);
            phoneList.add(split[1]);
            phoneMap.put(split[1], split[0]);
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckRegisterBean checkRegisterBean = GsonUtils.parseJson(resulte, CheckRegisterBean.class);
                if (!TextUtils.isEmpty(checkRegisterBean.getMessage())) {
                    /*T.showShort(checkRegisterBean.getMessage());*/
                }
                if (checkRegisterBean.getStatusCode() == Constant.SUCCESS) {
                    isAccountList = checkRegisterBean.getData();//设置列表的分割线逻辑处理
                    for (int i = 0; i < isAccountList.size(); i++) {
                        CheckRegisterBean.DataBean dataBean = isAccountList.get(i);
                        list.remove(phoneMap.get(dataBean.getPhone()) + Person + dataBean.getPhone());//添加替换通讯录姓名和账号姓名
                        list.add(i, dataBean.getRealName() + Person + dataBean.getPhone() + SIGN + phoneMap.get(dataBean.getPhone()));
                    }
                    //把联系人的数量加上好友的数量
                    requestMyFriends();
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
                    requestCheckFriends(phoneList,nameList);

                }
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
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
//                CheckFriends2Bean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriends2Bean.class);
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        }, (MainActivity) getContext()).checkFriends(phoneLists,namesLists);

    }



    /**
     * 获取脉场数据
     */
/*
//    private void getMaiChang() {
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                MaichangBean michangBean = GsonUtils.parseJson(resulte, MaichangBean.class);
//                if (michangBean.getStatusCode() == Constant.SUCCESS) {
//                    MaichangBean.DataBean data = michangBean.getData();
//                    if (data != null) {
//                        String totalCount = data.getTotalCount();// 脉场资源库
//                        String totalBase = data.getTotalBase();// 总资源的基数
//                        String vein_asset_valuation = BigDecimalUtil.mul(totalBase, totalCount, Constant.SCALE_NUM);//脉场资产估值
//                        String totalCounts = BigDecimalUtil.addComma(totalCount);
//
//                        String vein_asset_valuations = BigDecimalUtil.addComma(vein_asset_valuation);
//                        businessChanceAdapter.setSourceValue(totalCounts,vein_asset_valuations + Constant.ONE_SPACE + getString(R.string.yuan));
//                    }
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
////                refreshLayout_mine.finishRefresh(false);
//                T.showShort(e.getMessage());
//            }
//        }, (MainActivity) getContext()).maichang();
//
//    }

*/


    private void genesisCuv(){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject =new JSONObject(resulte);
                        int statusCode = jsonObject.optInt("statusCode");
                        if(Constant.SUCCESS == statusCode){
                            JSONObject data = jsonObject.getJSONObject("data");
                            businessChanceAdapter2.setSourceValue(data.optString("total_value"),
                                    data.optString("total_contacts"),data.optString("total_users"),
                                    data.optString("total_vips"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("test","genesisCuv resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        },(MainActivity) getContext()).genesisCuv();
    }



    /**
     * 设置通讯录
     *
     * @param granted
     */
    private void setContacts(Boolean granted) {
        if (granted) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list = getContacts();
                    aCache.put(Constant.MAILLIST, list, ACache.TIME_DAY);
                    aCache.put(Constant.MAILMAP, map, ACache.TIME_DAY);

                    handler.sendEmptyMessage(0);
                }
            }).start();

        } else {
            T.showShort(getResources().getString(R.string.refuseJurisdiction));
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
                                //去掉手机号中的空格
                                String s = num.replaceAll(" ", "");
                                boolean mobileNO = StringUtil.isMobileNO(s);
                                if (mobileNO) {
                                    list.add(name + Person + s);
                                    map.put(s, false);
                                }

                            } while (phonesCusor.moveToNext());
                        }
                    } catch (Exception e) {
                        //
                    } finally {
                        if (phonesCusor != null) {
                            phonesCusor.close();
                        }
                    }
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



    /**
     * 请求我的好友数据
     */
    private void requestMyFriends() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckFriendsBean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriendsBean.class);
                if (checkFriendsBean.getStatusCode() == Constant.SUCCESS) {
//                    List<CheckFriendsBean.DataBean> data = checkFriendsBean.getData();
                    //好友+联系人=我的人脉数
//                    int datasize = data.size();
//                    nums = num + datasize;
//                    getMaiChang();
                    genesisCuv();
                }

            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        }, (MainActivity) getContext()).getFriends();

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


    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        aCache = ACache.get(getContext());
        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));

        /*RongIM.connect(SharePreferenceUtils.getBaseSharePreference().readIMToken(), new RongIMClient.ConnectCallback() {
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
        data = new ArrayList<>();
        listgoods = new ArrayList<>();
        businessChanceAdapter2 = new BusinessChanceAdapter2(getContext(), data);
        *//*businessChanceAdapter2.setOnViewClickListener(this);*//*
        maxRecyclerView.setAdapter(businessChanceAdapter2);
        maxRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //设置 Footer 为 经典式样
        refreshLayout.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                *//*getGoodsList(false);*//*
                maxRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore();
                    }
                },1000);

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                *//*getGoodsList(true);
//                requestData();*//*
                maxRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                },1000);
            }
        });
        getGoodsList(true);
        maxRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(getContext(), 2), getResources().getColor(R.color._f1f1f1)));
*/

    }




    /**
     * 获取商品列表
     */
    private void getGoodsList(boolean isRefresh) {
        if (isRefresh) {
            maxRecyclerView.setPageNumber(0);
        }


        if (isRefresh) {
            lastEssayId = 0;
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if (StringUtil.isNotEmpty(resulte)) {
                    GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                    if (Constant.SUCCESS == getEssaysBean.getStatusCode()) {
                        if (getEssaysBean.getData() != null) {
                            if (isRefresh) {
                                data.clear();
                            }

                            for (int i = 0; i < getEssaysBean.getData().size(); i++) {
                                if(getEssaysBean.getData().get(i).isHead()){
                                    data.add(getEssaysBean.getData().get(i));
                                }
                            }
                            initdata();
                            initdatas(0,0,true);
                            getInitData();
/*

                            initdatass(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(), 1, false);
*/
                            if (isRefresh) {
                                GetEssaysBean.DataBean bean = new GetEssaysBean.DataBean();
                                bean.setHead(true);
                                data.add(0, bean);
                            }
                            lastEssayId = data.get(data.size() - 1).get_id();
                            businessChanceAdapter2.notifyDataSetChanged();
                            if (!(maxRecyclerView.getAdapter() != null && (maxRecyclerView.getAdapter() instanceof BusinessChanceAdapter2))) {
                                maxRecyclerView.setAdapter(businessChanceAdapter2);
                            }
                            maxRecyclerView.setPageNumber(maxRecyclerView.getPageNumber() + 1);

                        }

                        if(isRefresh){
                            requestData();
                            getMailList();
                            getBreakingNews();


                        }
                    } else if (StringUtil.isNotEmpty(getEssaysBean.getMessage())) {
                        Log.e("MCOpportunities",getEssaysBean.getMessage());
                    }
                }
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);
                } else {
                    refreshLayout.finishLoadMore(true);
                }

            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);
                } else {
                    refreshLayout.finishLoadMore(true);
                }
            }
        }, (RxAppCompatActivity) getActivity()).getEssays(1, 0,
                lastEssayId, 0);//type：0我的；1最新；2热门；3关注




    }

    @Override
    public void onViewClick(int viewId, int position) {
        switch (viewId) {
            case R.id.text_delete_essay:
                deleteEssay(data.get(position).get_id() + "", position);
                break;
        }
    }

   private void getInitData(){

       new UserTask(new HttpOnNextListener() {
           @Override
           public void onNext(String resulte, String method) {
               if (StringUtil.isNotEmpty(resulte)) {
                   doumiOptionBean = GsonUtil.GsonToBean(resulte, DoumiOptionBean.class);
                   if (Constant.SUCCESS == doumiOptionBean.getStatusCode()) {

                       shengfen = doumiOptionBean.getData().getDomain().get(0);

                       currentArea = doumiOptionBean.getData().getJob_type().get(0);

                       currentQuanzhi = doumiOptionBean.getData().getJob_date_type().get(0);

                       initdatass(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(), 1, true);
                   }
               }
               Log.e("test", "resulte is :" + resulte);
           }

           @Override
           public void onError(ApiException e) {
               Log.e("MCOpportunities",e.getMessage());
           }
       },(RxAppCompatActivity) getActivity()).getPositionOptions();


   }
    private void deleteEssay(String essayId, int position) {
        if (StringUtil.isNotEmpty(essayId)) {
            CancelOrOkDialog dialog = new CancelOrOkDialog(getContext(), "您确定要删除这条商机吗?") {
                @Override
                public void ok() {
                    super.ok();
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            if (StringUtil.isNotEmpty(resulte)) {
                                BaseBean baseBean = GsonUtil.GsonToBean(resulte, BaseBean.class);
                                if (baseBean != null) {
                                    if (baseBean.getStatusCode() == Constant.SUCCESS) {
                                        data.remove(position);
                                        businessChanceAdapter2.notifyDataSetChanged();
                                    } else if (StringUtil.isNotEmpty(baseBean.getMessage())) {
                                        T.showShort(baseBean.getMessage());
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            e.printStackTrace();
                            T.showShort(getString(R.string.rc_conversation_List_operation_failure));
                        }
                    }, (RxAppCompatActivity) getActivity()).deleteEssay(essayId);
                    dismiss();
                }
            };
            dialog.show();
        }
    }

    /**
     * 获取首页咨询接口
     */
    private void getBreakingNews() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test", "getBreakingNews is " + resulte);
                if(StringUtil.isNotEmpty(resulte)){
                    InformationBean informationBean = GsonUtil.GsonToBean(resulte, InformationBean.class);
                    if(Constant.SUCCESS == informationBean.getStatusCode()){
                        if(informationBean.getData().size()>0){
                            businessChanceAdapter2.refreshNewsData(informationBean.getData());
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                Log.e("MCOpportunities",e.getMessage());
            }
        }, ((MainActivity) getActivity())).getBreakingNews();
    }


}
