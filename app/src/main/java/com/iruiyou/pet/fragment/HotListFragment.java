package com.iruiyou.pet.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.MyHotItemActivity;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.FindHotAdapter;
import com.iruiyou.pet.adapter.HotListAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.bean.HomeRefreshBean;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

import static com.ta.utdid2.android.utils.AESUtils.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotListFragment extends BaseFragment implements HotListAdapter.OnViewClickListener{

    @BindView(R.id.rlv)
    PageNumberRecyclerView mRlv;

    @BindView(R.id.refresh_loading)
    SmartRefreshLayout refreshLayout;
    public static final String Person = "$";
    public static final String SIGN = "*";
    private List<GetEssaysBean.DataBean> data;
    private HotListAdapter businessChanceAdapter;
    private int lastEssayId = 0;

    private ACache aCache;
    private RxPermissions rxPermissions;
    private HomeRefreshBean homeRefreshBean;
    private ArrayList<String> list;
    private HashMap<String, Boolean> map;
    private HashMap<String, String> phoneMap;//key=手机号;value=姓名

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getData(list);
        }
    };
    private List<CheckRegisterBean.DataBean> isAccountList;

    public HotListFragment() {
        // Required empty public constructorƒ
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hot_list, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    private void initView() {

        aCache = ACache.get(getContext());
        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));

        RongIM.connect(SharePreferenceUtils.getBaseSharePreference().readIMToken(), new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "###onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {

                //设置信息和uerid 匹配
//                RongIM.getInstance().setCurrentUserInfo(new UserInfo(s,"12121",Uri.parse("")));
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });


        data = new ArrayList<>();
        businessChanceAdapter = new HotListAdapter(getContext(), data);
        businessChanceAdapter.setOnViewClickListener(this);
        mRlv.setAdapter(businessChanceAdapter);
        mRlv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //设置 Footer 为 经典式样
        refreshLayout.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getGoodsList(false);

            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getGoodsList(true);
//                requestData();
            }
        });
        getGoodsList(true);
        mRlv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(getContext(), 2), getResources().getColor(R.color._f1f1f1)));
        businessChanceAdapter.setItemClickListener(new FindHotAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {

                GetEssaysBean.DataBean dataBean = businessChanceAdapter.getItem(position);
              /*  if (dataBean!=null){
                    StartActivityManager.startUserDetailsActivity(getActivity(),dataBean.getUserId(),dataBean.getRealName());

                }else{
                    GetEssaysBean.DataBean.BasicInfoBean dataBean1 = businessChanceAdapter.getItem(position+1).getBasicInfo();
                    StartActivityManager.startUserDetailsActivity(getActivity(),dataBean1.getUserId(),dataBean1.getRealName());

                }*/
              if (dataBean!=null){
                  Intent intent = new Intent(getContext(), MyHotItemActivity.class);
                  intent.putExtra("bean",dataBean);
                  startActivity(intent);
              }else{
                  Toast.makeText(getContext(), "空了", Toast.LENGTH_SHORT).show();
              }

            }
        });
        //删除
        businessChanceAdapter.setOnViewClickListener(new HotListAdapter.OnViewClickListener() {
            @Override
            public void onViewClick(int viewId, int position) {
                switch (viewId) {
                    case R.id.text_delete_essay:
                        deleteEssay(data.get(position).get_id() + "", position);
                        break;
                }

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
                                                businessChanceAdapter.notifyItemRemoved(position);
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
        });

    }

    /**
     * 获取商品列表
     */
    private void getGoodsList(boolean isRefresh) {
        if (isRefresh) {
            mRlv.setPageNumber(1);
        }


        if (isRefresh) {
            lastEssayId = 0;
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("dddd",resulte);
                if (StringUtil.isNotEmpty(resulte)) {
                    GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                    if (Constant.SUCCESS == getEssaysBean.getStatusCode()) {
                        if (getEssaysBean.getData() != null) {
                            if (isRefresh) {
                                data.clear();
                            }
                            ArrayList<GetEssaysBean.DataBean> objects = new ArrayList<>();
                            for (int i = 0; i < getEssaysBean.getData().size(); i++) {
                                if (!getEssaysBean.getData().get(i).isHead()){
                                    data.add(getEssaysBean.getData().get(i));
                                    if ((getEssaysBean.getData().get(i).getUserId()+"").equals(SharePreferenceUtils.getBaseSharePreference().
                                            readUserId())){
                                        objects.add(getEssaysBean.getData().get(i));
                                    }
                                }
                            }
                            ((MainActivity)getActivity()).setHotlist(objects.size());
                            businessChanceAdapter.notifyDataSetChanged();
                            if (isRefresh ) {
                            /*    GetEssaysBean.DataBean bean = new GetEssaysBean.DataBean();
                                bean.setHead(true);
                                data.add(0, bean);*/
                            }
                            lastEssayId = data.get(data.size() - 1).get_id();
                            businessChanceAdapter.notifyDataSetChanged();
                            if (!(mRlv.getAdapter() != null && (mRlv.getAdapter() instanceof HotListAdapter))) {
                                mRlv.setAdapter(businessChanceAdapter);
                                businessChanceAdapter.notifyDataSetChanged();
                            }
                            mRlv.setPageNumber(mRlv.getPageNumber() + 1);
                            businessChanceAdapter.onItemFabuClick(new HotListAdapter.onItemFabuClick() {
                                @Override
                                public void onItemFabu(int position , TextView view) {
                                    if (data.get(position).getIs_fabulous()==1){
                                        view.setTextColor(Color.parseColor("#54D7B4"));
                                        view.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.likes),null,null,null);
                                        T.showShort("已赞");
                                    }else{
                                        new UserTask(new HttpOnNextListener() {
                                            @Override
                                            public void onNext(String resulte, String method) {
                                                view.setText(data.get(position).getTotal_fabulous()+1+"");
                                                view.setTextColor(Color.parseColor("#54D7B4"));
                                                view.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.likes),null,null,null);
                                            }

                                            @Override
                                            public void onError(ApiException e) {

                                            }
                                        }, (RxAppCompatActivity) getActivity()).getdianzan(data.get(position).get_id()+"",0,1,
                                                SharePreferenceUtils.getBaseSharePreference().readUserId());
                                    }

                                }
                            });
                        }

                        if(isRefresh){
                            requestData();
                            getMailList();
                          //  getBreakingNews();
                        }
                    } else if (StringUtil.isNotEmpty(getEssaysBean.getMessage())) {
                        T.showShort(getEssaysBean.getMessage());
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
                T.showShort(e.getMessage());
                if (isRefresh) {
                    refreshLayout.finishRefresh(true);
                } else {
                    refreshLayout.finishLoadMore(true);
                }
            }
        }, (RxAppCompatActivity) getActivity()).getEssays(1, 0, lastEssayId, 0);//type：0我的；1最新；2热门；3关注

    }

/*

    */
/**
     * 获取首页咨询接口
     *//*

    private void getBreakingNews() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test", "getBreakingNews is " + resulte);
                if(StringUtil.isNotEmpty(resulte)){
                    InformationBean informationBean = GsonUtil.GsonToBean(resulte,InformationBean.class);
                    if(Constant.SUCCESS == informationBean.getStatusCode()){
                        if(informationBean.getData().size()>0){
                            businessChanceAdapter.refreshNewsData(informationBean.getData());
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, ((MainActivity) getActivity())).getBreakingNews();
    }
*/

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
                        businessChanceAdapter.refreshWallet(BigDecimalUtil.addComma4(rebateCrystal+crystalAmount), BigDecimalUtil.addComma4(pbsAmount + lastHarvestAmount + pbsFrozen + pbsDrawLockedAmount));
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, (MainActivity) getContext()).harvestList(true);




        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                homeRefreshBean = GsonUtils.parseJson(resulte, HomeRefreshBean.class);
                if (homeRefreshBean.getStatusCode() == Constant.SUCCESS) {
                    if(StringUtil.isNotEmpty(homeRefreshBean.getData().getNotice()))
                    {
                        businessChanceAdapter.refreshNotice(homeRefreshBean.getData().getNotice(),View.VISIBLE);
                    }
                    else
                    {
                        businessChanceAdapter.refreshNotice("",View.GONE);
                    }

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
                        /*businessChanceAdapter.setCoinStatus(isShow);*/
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
                   /* if (statisticsInfo != null) {
                        int top = statisticsInfo.getBasicCount() + statisticsInfo.getBlockchainCount() + statisticsInfo.getWorkCount() + statisticsInfo.getEducationCount();
                        twoPoint(((double) top / (double) homeRefreshBean.getData().getInfoCount() * 100));
                    }*/
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
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).homeRefresh();

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
                    T.showShort(checkRegisterBean.getMessage());
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
//                CheckFriends2Bean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriends2Bean.class);
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
                   /* genesisCuv()*/;
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getFriends();

    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        initView();
    }

    @Override
    public void onViewClick(int viewId, int position) {

    }
}
