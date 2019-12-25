package com.iruiyou.pet.fragment;

import android.Manifest;
import android.animation.ValueAnimator;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.RxRetrofitApp;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.AppUtil;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ContactsActivity;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.NodePartnerActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.adapter.NodePartnerAdapter;
import com.iruiyou.pet.adapter.PulseFieldAdapter;
import com.iruiyou.pet.adapter.PulseFiledAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CheckFriends2Bean;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.EventBean;
import com.iruiyou.pet.bean.LookForSbBean;
import com.iruiyou.pet.bean.MaichangBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.RaiseNumberAnimTextView;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions.RxPermissions;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 脉场
 * Created by sgf on 2018/12/20.
 */
public class PulseFieldFragment extends BaseFragment {

    //    @BindView(R.id.headIv)
//    ImageView headIv;
//    @BindView(R.id.mHeadIv)
//    ImageView mHeadIv;
    @BindView(R.id.tv_vein_resource_library_number)
    RaiseNumberAnimTextView tv_vein_resource_library_number;
    @BindView(R.id.tv_my_invitation)
    TextView tv_my_invitation;
    @BindView(R.id.tv_my_friendsourcing)
    TextView tv_my_friendsourcing;
    @BindView(R.id.nodePartnerRecyclerView)
    RecyclerView nodePartnerRecyclerView;
    @BindView(R.id.myNetworkBankRecyclerView)
    MaxRecyclerView myNetworkBankRecyclerView;
    @BindView(R.id.ll_vein_my_network_bank)
    LinearLayout ll_vein_my_network_bank;
    @BindView(R.id.tv_vein_asset_valuation)
    RaiseNumberAnimTextView tv_vein_asset_valuation;
    @BindView(R.id.tv_vein_connections_number)
    RaiseNumberAnimTextView tv_vein_connections_number;
    @BindView(R.id.tv_vein_my_asset_valuation)
    TextView tv_vein_my_asset_valuation;
    @BindView(R.id.bt_vein_import_address_book)
    Button bt_vein_import_address_book;
    @BindView(R.id.ll_no_data)
    LinearLayout ll_no_data;
    @BindView(R.id.im_no_data)
    ImageView im_no_data;
    @BindView(R.id.tv_no_data)
    TextView tv_no_data;
    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;

    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.text_my_network)
    TextView textMyNetwork;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.refreshLayout_find)
    SmartRefreshLayout refreshLayout_find;

    @BindView(R.id.image_back)
    ImageView imageBack;

    private Context context;
    private ACache aCache;
    private ArrayList<String> list;
    private HashMap<String, Boolean> map;//key=手机号;value=状态
    private HashMap<String, String> phoneMap;//key=手机号;value=姓名
    private RxPermissions rxPermissions;
    private ProgressDialog progressDialog;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            cancelProgressDialog();
//            adapter.setNewDatas(list, map);
            getData(list);
        }
    };
    public static final String Person = "$";
    public static final String SIGN = "*";
    private PendingIntent sentPI;
    private PulseFieldAdapter adapter;
    private PendingIntent deliverPI;
    private String phoneNum;
    private int namePositon;
    private List<CheckRegisterBean.DataBean> isAccountList;
    private int nums = -1;
    private boolean contactAllow = true;
    private SearchPresenter registerPresenter;
    private ArrayList<OccupationBeen> occupationBeens;
    private OccupationAdapter occupationAdapter;
    private PulseFiledAdapter pulseFiledAdapter;
    private String mLastUserId="0";
    private String clickPid;
    /**
     * 单例模式
     *
     * @return
     */
    public static PulseFieldFragment getInstance() {
        return new PulseFieldFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //Log.e("test","action in onCreate 179");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.e("test","action in onCreateView 186");
        View view = inflater.inflate(R.layout.fragment_pulse_field, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        //Log.e("test","action in OnActCreate 194");
        context = getActivity();
        occupationBeens = new ArrayList<>();
        registerPresenter = new SearchPresenter();
        registerPresenter.setSquare(true);
        registerPresenter.IPresenter(getActivity());
        EventBusUtils.getInstance().register(this);
        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        aCache = ACache.get(getContext());
        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        nodePartnerRecyclerView.setLayoutManager(linearLayoutManager);
        nodePartnerRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        adapter = new PulseFieldAdapter();
        myNetworkBankRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        pulseFiledAdapter=new PulseFiledAdapter(getActivity());
        recyclerView.setAdapter(pulseFiledAdapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(12)));
        pulseFiledAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LookForSbBean.ItemData itemData= (LookForSbBean.ItemData) adapter.getItem(position);
                UserDetailsActivity.startAction(getActivity(),(int) Objects.requireNonNull(itemData).getBasicsInfo().getUserId(),itemData.getBasicsInfo().getRealName());
            }
        });
//        getData();
//        getMaiChang();
        getMailList();
//        View view = refreshLayout_mine.findViewById(R.id.ll_pbs_transaction);
//        view.setVisibility(view.GONE);
//
//        View kaven = refreshLayout_mine.findViewById(R.id.ll_pbs_kaven);
//        kaven.setVisibility(view.GONE);

//        getData();
        getRefresh();

        ///
//        String jsonArrayStr="{\"total\":\"1\",\"rows\":[{\"createdate\":\"2017-10-26 00:00:00\",\"name\":\"aaaa\"},{\"createdate\":\"2017-10-27 13:39:35\",\"name\":\"bbb\"}]}";
//        System.out.println("1---将符合json对象数组格式的字符串jsonArrayStr====="+jsonArrayStr);
//
//        //将符合json对象数组格式的字符串转换为json对象
//        JSONObject jsonObject= JSONObject.fromObject(jsonArrayStr);
//        JSONArray.
//        System.out.println("2---转换为JSONObject====="+jsonObject);
        ///
        //判断是否有网
        if (AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
            setPositionRecyclerView();
        }
    }

    /**
     * 设置int类型数字动画
     *
     * @param userIds
     */
    private void setTextNumAnimation(float userIds) {
        tv_vein_asset_valuation.setDuration(1000 * 5);
        tv_vein_asset_valuation.setAnimInterpolator(new AccelerateInterpolator());
//        tv_vein_asset_valuation.setNumberWithAnim(userIds);
        tv_vein_asset_valuation.setNumberWithAnim1(userIds);
    }
    /**
     * 设置float类型数字动画
     *
     * @param userIds
     */
    private void setTextNumAnimation2(float userIds) {
        NumberFormat nf = new DecimalFormat("#.#");//去除小数点后面的0
        tv_vein_resource_library_number.setDuration(800 * 5);
        tv_vein_resource_library_number.setAnimInterpolator(new AccelerateInterpolator());
        tv_vein_resource_library_number.setNumberWithAnim1(userIds);

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
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, userIds);

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
                /*T.showShort(e.getMessage());*/
            }
        }, (MainActivity) getContext()).maichang();

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
                /*T.showShort(e.getMessage());*/
            }
        }, (MainActivity) getContext()).getFriends();

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
                /*T.showShort(e.getMessage());*/
            }
        }, (MainActivity) getContext()).checkFriends(phoneLists,namesLists);

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
            rxPermissions.request(Manifest.permission.READ_CONTACTS)
                    .subscribe(granted -> {
                        setContacts(granted);
                    });
        }
        if (list == null || map == null) {
            map = new HashMap<>();
            list = new ArrayList<>();
            rxPermissions.request(Manifest.permission.READ_CONTACTS)
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

    /**
     * 刷新
     */
//    private void getRefresh() {
//        //刷新操作
//        refreshLayout_mine.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(RefreshLayout refreshlayout) {
//                getData();
//            }
//        });
//    }

//    private void getData() {
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                HomeRefreshBean homeRefreshBean = GsonUtils.parseJson(resulte, HomeRefreshBean.class);
//                if (homeRefreshBean.getStatusCode() == Constant.SUCCESS) {
//                    if (homeRefreshBean.getData().getBasicInfo() != null) {
////                refreshLayout_mine.finishRefresh(true);//传入false表示刷新失败
//
//                        //公司主页
//                        List<HomeRefreshBean.DataBean.CompaniesBean> companies = homeRefreshBean.getData().getCompanies();
//                        if (companies != null) {
//                            if (companies.size() > 0) {
//                                NodePartnerAdapter nodePartnerAdapter = new NodePartnerAdapter(getActivity(), companies);
//                                nodePartnerRecyclerView.setAdapter(nodePartnerAdapter);
//                                nodePartnerAdapter.notifyDataSetChanged();
//
//                            }
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onError(ApiException e) {
////                refreshLayout_mine.finishRefresh(false);
//                T.showShort(e.getMessage());
//            }
//        }, (MainActivity) getContext()).homeRefresh();
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBusUtils.getInstance().unregister(this);
//        PulseFieldFragment.this.userId.clearAnimator();

    }

    @Override
    public void onResume() {
        super.onResume();
//        getData();
    }

    private void setListener() {


        //发送回执监听
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        deliverPI = PendingIntent.getBroadcast(getContext(), 0, deliverIntent, 0);


        //发送短信监听
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);

        sentPI = PendingIntent.getBroadcast(getContext(), 0, sentIntent, 0);
        adapter.setOnTextViewClickListener(new PulseFieldAdapter.OnTextViewClickListener() {//通讯录中邀请按钮的监听
            @Override
            public void onTextViewClick(int position) {
//                PulseFieldFragment.this.onItemChildClick(adapter, position, sentPI, deliverPI);
                startActivity(ContactsActivity.class);
            }
        });
    }

    /**
     * 适配器子View的点击事件
     *
     * @param adapter
     * @param position
     * @param sentPI
     * @param deliverPI
     */
    private void onItemChildClick(BaseQuickAdapter adapter, int position, PendingIntent sentPI, PendingIntent deliverPI) {
        rxPermissions.request(Manifest.permission.SEND_SMS)
                .subscribe(granted -> {
                    String item = (String) adapter.getData().get(position);
                    String[] split = item.split("\\" + Fragment2.Person);
                    phoneNum = split[1];
                    namePositon = position;
                    ConfigBean configBean = App.getConfigBean();
                    if(configBean!=null)
                    {
                        String smsTemplate = configBean.getData().getSmsTemplate();
                        String[] s = smsTemplate.split("\\$");
                        String text = s[0] + SharePreferenceUtils.getBaseSharePreference().readInviteCode();
                        //这种方式无法监听发送状态
//                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + split[1]));
//                            intent.putExtra("sms_body", text);
//                            startActivity(intent);
                        //这种方式可以监听发送状态
                        SmsManager.getDefault().sendTextMessage(split[1], null, text, sentPI, deliverPI);
                    }
                });
    }

    public void setSmsReceive() {
        map.put(phoneNum, true);
//        String remove = (String) list.remove(namePositon);
//        list.add(0, remove);
        adapter.setNewDatas(list, map, isAccountList);
//        recyclerView.smoothScrollToPosition(0);
        aCache.put(Constant.MAILLIST, list, ACache.TIME_DAY);
        aCache.put(Constant.MAILMAP, map, ACache.TIME_DAY);
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
                /*T.showShort(e.getMessage());*/
            }
        }, (MainActivity) getContext()).checkRegister(phoneList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowMessageEvent(EventBean t) {
//        integrityTv.setText(BigDecimalUtil.removeEndZero(SharePreferenceUtils.getBaseSharePreference().readResume()) + "%");
    }

    @OnClick({R.id.ll_vein_my_network_bank, R.id.ll_vein_node_partner, R.id.image_back,
            R.id.bt_vein_import_address_book, R.id.ll_my_network_bank, R.id.text_my_network})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_my_network:
                startActivity(ContactsActivity.class);
                break;
            case R.id.ll_vein_my_network_bank://我的人脉库
//                startActivity(MyNetworkBankActivity.class);
                startActivity(ContactsActivity.class);
                break;
            case R.id.ll_my_network_bank://我的人脉库
                startActivity(ContactsActivity.class);
                break;
            case R.id.ll_vein_node_partner://节点合伙人
                startActivity(NodePartnerActivity.class);
                break;
            case R.id.bt_vein_import_address_book://导入通讯录
//                rxPermissions.request(android.Manifest.permission.READ_CONTACTS)
//                        .subscribe(granted -> {
//                            setContacts(granted);
//                        });
                startActivity(ContactsActivity.class);
                break;
            case R.id.image_back:
                imageBack.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                refreshLayout_find.setVisibility(View.GONE);
                textMyNetwork.setVisibility(View.VISIBLE);
                textTitle.setText(getResources().getText(R.string.pulse_field));

                for (int i = 0; i < occupationBeens.size(); i++) {
                    if(i==0)
                    {
                        occupationBeens.get(i).setSelect(true);
                    }
                    else
                    {
                        occupationBeens.get(i).setSelect(false);
                    }
                }
                occupationAdapter.notifyDataSetChanged();
                break;
        }
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

    public void buildProgressDialog(String string) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        progressDialog.setMessage(string);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    public void cancelProgressDialog() {
        if (progressDialog != null)
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
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


    /**
     * 设置职位选择
     */
    private void setPositionRecyclerView() {
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
                    if(data.size()>0)
                    {
                        data.get(0).setSelect(true);
                    }
                    occupationBeens.addAll(data);
                    occupationAdapter = new OccupationAdapter(getActivity(), occupationBeens, 3);
                    positionRecyclerView.setAdapter(occupationAdapter);
                    occupationAdapter.setItemClickListener(new OccupationAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(OccupationBeen firstPageListBean, int position) {
                            if(position==0)
                            {
                                scrollView.setVisibility(View.VISIBLE);
                                refreshLayout_find.setVisibility(View.GONE);
                                textMyNetwork.setVisibility(View.VISIBLE);
                                textTitle.setText(getResources().getText(R.string.pulse_field));
                                imageBack.setVisibility(View.GONE);
                            }
                            else
                            {
                                scrollView.setVisibility(View.GONE);
                                refreshLayout_find.setVisibility(View.VISIBLE);
                                textMyNetwork.setVisibility(View.GONE);
                                textTitle.setText(getResources().getText(R.string.look_for_sb));
                                imageBack.setVisibility(View.VISIBLE);

                                clickPid=firstPageListBean.getDbKey()+"";
                                mLastUserId="0";
                                reQuestUserListByPid(clickPid,mLastUserId,true);

                            }
                            for (int i = 0; i < occupationBeens.size(); i++) {
                                if (occupationBeens.get(i).equals(firstPageListBean)) {
                                    occupationBeens.get(i).setSelect(true);
                                } else {
                                    occupationBeens.get(i).setSelect(false);
                                }
                            }
                            occupationAdapter.notifyDataSetChanged();

                        }
                    });


                    MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(MyLinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    getRefresh();
                }
            }

            @Override
            public void filed(Exception e) {
                e.printStackTrace();
            }
        });
    }




    private void reQuestUserListByPid(String pid,String lastUserId,boolean isRefresh)
    {
        new UserTask(new HttpOnNextListener(){
            @Override
            public void onNext(String resulte, String method) {

                LookForSbBean lookForSbBean = GsonUtils.parseJson(resulte, LookForSbBean.class);
                if(lookForSbBean.getStatusCode()!= Constant.SUCCESS)
                {
                    if(!TextUtils.isEmpty(lookForSbBean.getMessage()))
                    {
                        T.showShort(lookForSbBean.getMessage());
                    }
                }
                else if(lookForSbBean.getData()!=null)
                {
                    LookForSbBean.DataBean dataBean=lookForSbBean.getData();
                    if (dataBean.getBasics() != null && dataBean.getBasics().size() > 0) {
                        List<LookForSbBean.ItemData> dataSource=new ArrayList<>();
                        lookForSbBean.setDataSource(dataSource);
                        for (int i=0;i<dataBean.getBasics().size();i++) {
                            LookForSbBean.DataBean.BasicsInfo basicsInfo=dataBean.getBasics().get(i);
                            LookForSbBean.DataBean.StatisticsInfo statisticsInfo=dataBean.getStatistics().get(i);
                            LookForSbBean.ItemData itemData=new LookForSbBean.ItemData();
                            itemData.setBasicsInfo(basicsInfo);
                            itemData.setStatisticsInfo(statisticsInfo);
                            dataSource.add(itemData);
                            if(i==(dataBean.getBasics().size()-1))
                            {
                                mLastUserId=basicsInfo.getUserId()+"";
                            }
                        }
                        pulseFiledAdapter.setInfoCount(dataBean.getInfoCount());
                        if(isRefresh)
                        {
                            pulseFiledAdapter.setNewData(dataSource);
                        }
                        else
                        {
                            pulseFiledAdapter.addData(dataSource);
                        }
                    }
                }
                //Log.e("test","resulte value is "+resulte);
                refreshLayout_find.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_find.finishRefresh(false);//传入false表示刷新失败
            }
        },(MainActivity) getContext()).getUserInfoByPid(pid,lastUserId);
    }


    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_find.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_find.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mLastUserId="0";
                reQuestUserListByPid(clickPid,mLastUserId,true);
            }
        });
        //加载
        refreshLayout_find.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadmore(true);//传入false表示加载失败
//                getNewDate(selectorPosition,lastCount);
                reQuestUserListByPid(clickPid,mLastUserId,false);
            }
        });
    }



}
