package com.iruiyou.pet.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.RxRetrofitApp;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.utils.AppUtil;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ContactsSearchActivity;
import com.iruiyou.pet.activity.FollowActivity;
import com.iruiyou.pet.activity.FriendApplicationActivity;
import com.iruiyou.pet.activity.MyFansActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.server.broadcast.BroadcastManager;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.adapter.ContactsAdapter;
import com.iruiyou.pet.adapter.MyFriendsAdapter;
import com.iruiyou.pet.adapter.MyGroupChatAdapter;
import com.iruiyou.pet.adapter.SearchContactsAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.GetMyGroupsBean;
import com.iruiyou.pet.bean.SearchBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DividerItemDecoration;
import com.iruiyou.pet.utils.FilterSearchListener;
import com.iruiyou.pet.utils.ListViewForScrollView;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * 联系人搜索
 * Created by sgf on 2018/11/14
 */
public class ContactsFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "ContactsFragment";
    public static final String Person = "$";
    public static final String SIGN = "*";
    public static final String PHONE = ",";
    private RecyclerView positionRecyclerView;
    private ListViewForScrollView recyclerView_search;
    private MaxRecyclerView recyclerView, friendRecyclerView, groupChatRecyclerView;
    private ContactsAdapter adapter;
    private SearchContactsAdapter searchContactsAdapter;
    private MyFriendsAdapter myFriendsAdapter;
    private MyGroupChatAdapter myGroupChatAdapter;
    private RxPermissions rxPermissions;
    private ProgressDialog progressDialog;
    private ArrayList<String> list;
    private HashMap<String, Boolean> map;//key=手机号;value=状态
    private HashMap<String, String> phoneMap;//key=手机号;value=姓名
    private String phoneNum;
    private int namePositon;
    //    private List arrList;
    private ACache aCache;
    private List<CheckRegisterBean.DataBean> isAccountList;
    //加载控制器
    SearchPresenter registerPresenter = new SearchPresenter();
    private int friend = 0;
    private int mailList = 0;
    private int groupChat = 0;
    private List<SearchBean> searchList = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            cancelProgressDialog();
//            adapter.setNewDatas(list, map);
            if(getActivity()!=null&&(!getActivity().isDestroyed()))
            {
                getData(list);
            }
        }
    };
    private TextInputEditText etSearch;
    private TextView tvMailList, tvMyFriends, tvMailListRight, tvMyFriendsRight, tvMyGroupChat, tvMyGroupChatRight;
    private LinearLayout llFriendApplication, llMyattention, llMyfans, llMailList, llMyFriends, ll_switch_search, llMyGroupChat;
    private CheckFriendsBean checkFriendsBean;
    private RelativeLayout rlRed;
    private PendingIntent deliverPI;
    private PendingIntent sentPI;
    private String readRealname;
    private CharSequence charSequences;//同步输入框输入的数据
    private GetMyGroupsBean getMyGroupsBean;
    private ContactsSearchActivity ct;
    private RelativeLayout rl_back;

    /**
     * 单例模式
     *
     * @return
     */
    public static ContactsFragment getInstance() {
        return new ContactsFragment();
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, null);
        recyclerView = view.findViewById(R.id.recyclerView);//通讯录列表
        rl_back = view.findViewById(R.id.rl_back);//返回
        positionRecyclerView = view.findViewById(R.id.positionRecyclerView);//职位选择列表
        friendRecyclerView = view.findViewById(R.id.friendRecyclerView);//我的好友列表
        groupChatRecyclerView = view.findViewById(R.id.groupChatRecyclerView);//我的群聊列表
        recyclerView_search = view.findViewById(R.id.recyclerView_search);//搜索结果列表
        etSearch = view.findViewById(R.id.etSearch);
        llFriendApplication = view.findViewById(R.id.llFriendApplication);
        llMyattention = view.findViewById(R.id.llMyattention);
        ll_switch_search = view.findViewById(R.id.ll_switch_search);//搜索替换的布局
        llMyfans = view.findViewById(R.id.llMyfans);
        llMailList = view.findViewById(R.id.llMailList);//通讯录
        llMyFriends = view.findViewById(R.id.llMyFriends);//我的好友
        tvMailList = view.findViewById(R.id.tvMailList);//通讯录
        tvMyFriends = view.findViewById(R.id.tvMyFriends);//我的好友
        tvMailListRight = view.findViewById(R.id.tvMailListRight);//我的通讯录箭头
        tvMyFriendsRight = view.findViewById(R.id.tvMyFriendsRight);//我的好友箭头
        tvMyGroupChatRight = view.findViewById(R.id.tvMyGroupChatRight);//我的群聊箭头
        tvMyGroupChat = view.findViewById(R.id.tvMyGroupChat);//我的群聊
        llMyGroupChat = view.findViewById(R.id.llMyGroupChat);//我的群聊
        rlRed = view.findViewById(R.id.rlRed);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
//        init();
        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        //绑定上下文
        registerPresenter.IPresenter(getActivity());
        ct = (ContactsSearchActivity) getContext();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_nofriend, null);
        TextView viewById = inflate.findViewById(R.id.syncBtn);
        adapter = new ContactsAdapter();
        myFriendsAdapter = new MyFriendsAdapter();
        myGroupChatAdapter = new MyGroupChatAdapter();
        aCache = ACache.get(getContext());
        etSearch.addTextChangedListener(searchWatcher);
        llFriendApplication.setOnClickListener(this);
        llMyattention.setOnClickListener(this);
        llMyfans.setOnClickListener(this);
        llMyFriends.setOnClickListener(this);
        llMyGroupChat.setOnClickListener(this);
        llMailList.setOnClickListener(this);
        rl_back.setOnClickListener(this);
        //判断是否有网
        if (AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
            setPositionRecyclerView();
            requestMyFriends();
            requestMyGroupChat();
        }
        ll_switch_search.setVisibility(View.GONE);
        readRealname = SharePreferenceUtils.getBaseSharePreference().readNickName();

        int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
        if (applicationCount > 0) {//有好友申请，显示红点
            //做你该做的事情
            rlRed.setVisibility(View.VISIBLE);
        } else {
            rlRed.setVisibility(View.GONE);
        }

        //接收广播-显示红点
        BroadcastManager.getInstance(getActivity()).addAction(Constant.APPLY_VISIBLE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    if ((Constant.APPLY_VISIBLE).equals(command)) {//有好友申请，显示红点
                        //获取json结果
                        String json = intent.getStringExtra("result");
                        //做你该做的事情
                        rlRed.setVisibility(View.VISIBLE);
                    } else {
                        rlRed.setVisibility(View.GONE);
                    }
                }
            }
        });

        //接收广播-刷新好友列表
//        BroadcastManager.getInstance(getActivity()).addAction(Constant.ADDED_VISIBLE, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String command = intent.getAction();
//                if (!TextUtils.isEmpty(command)) {
//                    if ((Constant.ADDED_VISIBLE).equals(command)) {//刷新好友列表
////                        requestMyFriends();
//                    }
//                }
//            }
//        });
        //接收广播-隐藏键盘
//        BroadcastManager.getInstance(getActivity()).addAction(Constant.KEYBOARD, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String command = intent.getAction();
//                if (!TextUtils.isEmpty(command)) {
//                    if ((Constant.KEYBOARD).equals(command)) {
//                        if (SoftKeyboardUtils.isSoftShowing(getActivity())) {//若键盘显示则隐藏键盘
//                            SoftKeyboardUtils.hideSoftKeyboard(getActivity());
//                        }else {
//                            SoftKeyboardUtils.hideSoftKeyboard(getActivity());
//                        }
//                        ll_switch_search.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });

        recyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
        friendRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
        groupChatRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        friendRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        groupChatRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color._e31020)));
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST));


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

        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST,
//                11,
//                num));
        adapter.setEmptyView(inflate);

        adapter.notifyDataSetChanged();
        RxView.clicks(viewById).compose(rxPermissions.ensure(Manifest.permission.READ_CONTACTS)).subscribe(granted -> {
            setContacts(granted);
        });


    }

    /**
     * 请求群聊
     */
    private void requestMyGroupChat() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                getMyGroupsBean = GsonUtils.parseJson(resulte, GetMyGroupsBean.class);
                if (getMyGroupsBean != null) {
                    if (getMyGroupsBean.getStatusCode() == Constant.SUCCESS) {
                        List<GetMyGroupsBean.DataBean> data = getMyGroupsBean.getData();
                        if (data != null) {

                            //添加刷新群组信息到融云上
                            RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
                                @Override
                                public Group getGroupInfo(String s) {
                                    int result = 0;
                                    for (int i = 0; i < data.size(); i++) {
                                        if (s.equals(data.get(i).get_id() + ""))
                                            result = i;
                                    }
                                    return new Group(Constant.DEVGROUPID + data.get(result).get_id(),

                                            data.get(result).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(result).getLogo()));
                                }
                            }, true); //提供一群群组信息

                            for (int i = 0; i < data.size(); i++) {
                                RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + data.get(i).get_id(), data.get(i).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(i).getLogo()))); // 添加一个群组信息
                            }
                            tvMyGroupChat.setText(getString(R.string.my_group) + "(" + data.size() + ")");
                            if (!TextUtils.isEmpty(getMyGroupsBean.getMessage())) {
                                T.showShort(getMyGroupsBean.getMessage());
                            }
//                    myFriendsAdapter.setNewDatas(checkRegisterBean.getData());
                            myGroupChatAdapter.setNewData(data);
                            groupChatRecyclerView.setAdapter(myGroupChatAdapter);
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ct).getMyGroups();
    }

    /**
     * 请求我的好友数据
     */
    private void requestMyFriends() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriendsBean.class);
                if (checkFriendsBean != null) {
                    if (checkFriendsBean.getStatusCode() == Constant.SUCCESS) {
                        List<CheckFriendsBean.DataBean> data = checkFriendsBean.getData();
                        if (data != null) {
                            for (int i = 0; i < checkFriendsBean.getData().size(); i++) {
                                //刷新用户头像到融云上
                                String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                                if (readUserId.equals(String.valueOf(data.get(i).getUserIdA()))) {//自己

                                } else {
                                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + data.get(i).getBasicInfoA().getUserId(), data.get(i).getBasicInfoA().getRealName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(i).getBasicInfoA().getHeadImg())));//刷新同步头像昵称到融云
                                }
                            }

                            tvMyFriends.setText(getString(R.string.my_friend) + "(" + checkFriendsBean.getData().size() + ")");
                            if (!TextUtils.isEmpty(checkFriendsBean.getMessage())) {
                                T.showShort(checkFriendsBean.getMessage());
                            }
                            myFriendsAdapter.setNewData(checkFriendsBean.getData());
                            friendRecyclerView.setAdapter(myFriendsAdapter);
                        }
                    }

                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ct).getFriends();//(MainActivity) getContext()

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llFriendApplication:
                rlRed.setVisibility(View.GONE);
                BroadcastManager.getInstance(getActivity()).sendBroadcast(Constant.APPLY_GONE);
                startActivity(FriendApplicationActivity.class);
                break;
            case R.id.llMyattention:
                startActivity(FollowActivity.class);
                break;
            case R.id.rl_back:
                ct.finish();
                break;
            case R.id.llMyfans:
                startActivity(MyFansActivity.class);
                break;
            case R.id.llMyFriends:
                mailList++;
                if (mailList % 2 == 0) {
                    friendRecyclerView.setVisibility(View.VISIBLE);
                    setTextViewRightIcon(R.drawable.down_arrow2, tvMyFriendsRight);
                } else {
                    friendRecyclerView.setVisibility(View.GONE);
                    setTextViewRightIcon(R.drawable.up_arrow2, tvMyFriendsRight);
                }
                break;
            case R.id.llMailList:
                friend++;
                if (friend % 2 == 0) {//打开时向下
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView_search.setVisibility(View.GONE);
                    setTextViewRightIcon(R.drawable.down_arrow2, tvMailListRight);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    recyclerView_search.setVisibility(View.GONE);
                    setTextViewRightIcon(R.drawable.up_arrow2, tvMailListRight);
                }
                break;
            case R.id.llMyGroupChat:
                groupChat++;
                if (groupChat % 2 == 0) {//打开时向下
                    groupChatRecyclerView.setVisibility(View.VISIBLE);
                    setTextViewRightIcon(R.drawable.down_arrow2, tvMyGroupChatRight);
                } else {
                    groupChatRecyclerView.setVisibility(View.GONE);
                    setTextViewRightIcon(R.drawable.up_arrow2, tvMyGroupChatRight);
                }
                break;
        }
    }

    /**
     * 设置图片状态
     *
     * @param icon
     * @param textView
     */
    private void setTextViewRightIcon(int icon, TextView textView) {
        Drawable nav_up = getResources().getDrawable(icon);
        nav_up.setBounds(0, 0, nav_up.getMinimumWidth(), nav_up.getMinimumHeight());
        textView.setCompoundDrawables(null, null, nav_up, null);
    }

    /**
     * 搜索框监听
     */
    private TextWatcher searchWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        /**
         *
         * 编辑框内容改变的时候会执行该方法
         */
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // 如果adapter不为空的话就根据编辑框中的内容来过滤数据
            charSequences = charSequence;
            if (searchContactsAdapter != null) {
                recyclerView_search.setVisibility(View.VISIBLE);
                searchContactsAdapter.getFilter().filter(charSequence);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 0) {
                recyclerView_search.setVisibility(View.VISIBLE);
                ll_switch_search.setVisibility(View.GONE);

            } else {
                recyclerView_search.setVisibility(View.GONE);
                ll_switch_search.setVisibility(View.GONE);
                if (SoftKeyboardUtils.isSoftShowing(getActivity())) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                }
            }
        }
    };

    OccupationAdapter occupationAdapter;
    ArrayList<OccupationBeen> occupationBeens = new ArrayList<>();

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
        registerPresenter.getOccupationsList(new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    // 这块儿的逻辑（接受接口数据） 根据你们的实际业务调整
                    occupationBeens.addAll((ArrayList<OccupationBeen>) o);
                    if (occupationAdapter == null) {
                        occupationAdapter = new OccupationAdapter(getActivity(), occupationBeens, 2);
                        occupationAdapter.setPosition(0);
                        positionRecyclerView.setAdapter(occupationAdapter);
//                        occupationAdapter.notifyDataSetChanged();
                        occupationAdapter.setItemClickListener(new OccupationAdapter.MyItemClickListener() {
                            @Override
                            public void onItemClick(OccupationBeen firstPageListBean, int position) {
                                SharePreferenceUtils.getBaseSharePreference().saveProfessionalIdentity(position);//保存搜索前选中的索引

                                for (int i = 0; i < occupationBeens.size(); i++) {
                                    if (occupationBeens.get(i).equals(firstPageListBean)) {
                                        occupationBeens.get(i).setSelect(true);
                                    } else {
                                        occupationBeens.get(i).setSelect(false);
                                    }
                                }
                                occupationAdapter.notifyDataSetChanged();
                                //搜索：点击职位选择和输入框内容一样实时刷新显示数据
                                if (searchContactsAdapter != null && etSearch.getText().toString().length() > 0) {
                                    recyclerView_search.setVisibility(View.VISIBLE);
                                    searchContactsAdapter.getFilter().filter(charSequences);
                                }
                                if (SoftKeyboardUtils.isSoftShowing(getActivity())) {//若键盘显示则隐藏键盘
                                    SoftKeyboardUtils.hideSoftKeyboard(getActivity());
                                }

                            }
                        });
                    }  //                        occupationAdapter.notifyDataSetChanged();

                }
            }

            @Override
            public void filed(Exception e) {

            }
        });
    }


    private void setListener() {
        //发送短信监听
        String SENT_SMS_ACTION = "SENT_SMS_ACTION";
        Intent sentIntent = new Intent(SENT_SMS_ACTION);
        sentPI = PendingIntent.getBroadcast(getContext(), 0, sentIntent, 0);
        Objects.requireNonNull(getContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        T.showShort(getResources().getString(R.string.SMSissentsuccessfully));
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        break;
                }
            }
        }, new IntentFilter(SENT_SMS_ACTION));

        //发送回执监听
        String DELIVERED_SMS_ACTION = "DELIVERED_SMS_ACTION";
        Intent deliverIntent = new Intent(DELIVERED_SMS_ACTION);
        deliverPI = PendingIntent.getBroadcast(getContext(), 0, deliverIntent, 0);
        getContext().registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                T.showShort(getResources().getString(R.string.ReceiveSuccess));
                setSmsReceive();
            }
        }, new IntentFilter(DELIVERED_SMS_ACTION));

//        //发送短信
//        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
////                rxPermissions.request(Manifest.permission.SEND_SMS)
////                        .subscribe(granted -> {
////                            String item = (String) adapter.getData().get(position);
////                            String[] split = item.split("\\" + Fragment2.Person);
////                            phoneNum = split[1];
////                            namePositon = position;
////                            ConfigBean configBean = (ConfigBean) ACache.get(getContext()).getAsObject(TagConstants.config);
////                            String smsTemplate = configBean.getData().getSmsTemplate();
////                            String[] s = smsTemplate.split("\\$");
////                            String text = s[0] + SharePreferenceUtils.getBaseSharePreference().readInviteCode();
////                            //这种方式无法监听发送状态
//////                            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + split[1]));
//////                            intent.putExtra("sms_body", text);
//////                            startActivity(intent);
////                            //这种方式可以监听发送状态
////                            SmsManager.getDefault().sendTextMessage(split[1], null, text, sentPI, deliverPI);
//////                            setSmsReceive();
////                        });
//            }
//        }); 

        //通讯录监听
        adapter.setOnItemClickListener(new ContactsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                    StartActivityManager.startUserDetailsActivity(getActivity());//isAccountList
                Bundle bundle = new Bundle();

                bundle.putInt("userid", isAccountList.get(position).getUserId());
                bundle.putString("realName", isAccountList.get(position).getRealName());
                Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

//                Fragment2.this.onItemChildClick(adapter, position, sentPI, deliverPI);
            }
        });

        //我的好友监听
        myFriendsAdapter.setOnItemClickListener(new MyFriendsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
//                    StartActivityManager.startUserDetailsActivity(getActivity());//isAccountList
                CheckFriendsBean.DataBean.BasicInfoBBean basicInfoB = checkFriendsBean.getData().get(position).getBasicInfoB();
                if (basicInfoB != null) {
                    Bundle bundle = new Bundle();
                    String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                    if (readUserId.equals(String.valueOf(checkFriendsBean.getData().get(position).getUserIdA()))) {//自己
                        bundle.putInt("userid", checkFriendsBean.getData().get(position).getBasicInfoB().getUserId());//checkFriendsBean.getData().get(position).getBasicInfoB()getUserIdA()
                        bundle.putString("realName", checkFriendsBean.getData().get(position).getBasicInfoB().getRealName());
                    } else {
                        bundle.putInt("userid", checkFriendsBean.getData().get(position).getBasicInfoA().getUserId());
                        bundle.putString("realName", checkFriendsBean.getData().get(position).getBasicInfoA().getRealName());
                    }

                    Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
//                Fragment2.this.onItemChildClick(adapter, position, sentPI, deliverPI);
            }
        });
        //我的群组监听
        myGroupChatAdapter.setOnItemClickListener(new MyGroupChatAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                GetMyGroupsBean.DataBean dataBean = getMyGroupsBean.getData().get(position);
                if (dataBean != null) {
                    /**
                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
                     *
                     * @param context       应用上下文。
                     * @param targetGroupId 要聊天的群组 Id。
                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                     */
                    RongIM.getInstance().startGroupChat(Objects.requireNonNull(getActivity()), Constant.DEVGROUPID + dataBean.get_id(), dataBean.getGroupName());
                }
            }
        });
//        adapter.setOnItemLongClickListener(new ContactsAdapter.OnItemLongClickListener() {
//            @Override
//            public void onItemLongClick(int position) {
//                Toast.makeText(getActivity(), "66" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
        adapter.setOnTextViewClickListener(new ContactsAdapter.OnTextViewClickListener() {//通讯录中邀请按钮的监听
            @Override
            public void onTextViewClick(int position) {
                ContactsFragment.this.onItemChildClick(adapter, position, sentPI, deliverPI);
            }
        });
//        if (searchContactsAdapter != null) {//搜索邀请的按钮的监听
//        searchContactsAdapter.setOnTextViewClickListener(new SearchContactsAdapter.OnTextViewClickListener() {
//            @Override
//            public void onTextViewClick(int position) {
//                Toast.makeText(getActivity(), "99" + position, Toast.LENGTH_SHORT).show();
//                Fragment2.this.onItemChildClick(adapter, position, sentPI, deliverPI);
//            }
//        });
//    }
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
                    String[] split = item.split("\\" + ContactsFragment.Person);
                    phoneNum = split[1];
                    namePositon = position;
                    ConfigBean configBean = App.getConfigBean();
                    if(configBean!=null&&configBean.getData()!=null)
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
//                            setSmsReceive();
                    }
                });
    }

    private void setSmsReceive() {
        map.put(phoneNum, true);
//        String remove = (String) list.remove(namePositon);
//        list.add(0, remove);
        adapter.setNewDatas(list, map, isAccountList);
//        recyclerView.smoothScrollToPosition(0);
        aCache.put(Constant.MAILLIST, list, ACache.TIME_DAY);
        aCache.put(Constant.MAILMAP, map, ACache.TIME_DAY);
    }

    /**
     * 设置通讯录
     *
     * @param granted
     */
    private void setContacts(Boolean granted) {
        if (granted) {
            buildProgressDialog(getResources().getString(R.string.Loadaddressbookfriends));
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
        }
    }

    /**
     * 初始化
     */
    private void init() {

        rxPermissions = new RxPermissions(Objects.requireNonNull(getActivity()));
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_nofriend, null);
        TextView viewById = inflate.findViewById(R.id.syncBtn);
        adapter = new ContactsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new RecycleViewDivider(
//                getActivity(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color._e31020)));
        //添加Android自带的分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
//                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                DividerItemDecoration.VERTICAL_LIST, 11, isAccountList.size() - 1));

        recyclerView.setAdapter(adapter);

        adapter.setEmptyView(inflate);
        aCache = ACache.get(getContext());
        adapter.notifyDataSetChanged();
        RxView.clicks(viewById).compose(rxPermissions.ensure(Manifest.permission.READ_CONTACTS)).subscribe(granted -> {
            setContacts(granted);
        });

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
            if (cursor.moveToFirst()) {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            SharePreferenceUtils.getBaseSharePreference().saveProfessionalIdentity(-1);
            Objects.requireNonNull(getContext()).unregisterReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    switch (getResultCode()) {
                        case Activity.RESULT_OK:
                            T.showShort(getResources().getString(R.string.SMSissentsuccessfully));
                            break;
                        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                            break;
                        case SmsManager.RESULT_ERROR_RADIO_OFF:
                            break;
                        case SmsManager.RESULT_ERROR_NULL_PDU:
                            break;
                    }
                }
            });
            BroadcastManager.getInstance(getActivity()).destroy(Constant.APPLY_GONE);
//            BroadcastManager.getInstance(getActivity()).destroy(Constant.ADDED_VISIBLE);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private void getData(List<String> list) {
        tvMailList.setText(getString(R.string.Contacts) + "(" + list.size() + ")");
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
                    recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()),
                            DividerItemDecoration.VERTICAL_LIST,
                            11,
                            isAccountList.size() - 1));
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
                    ///添加数据
                    searchList.clear();
                    if (checkFriendsBean != null) {
                        if (checkFriendsBean.getData() != null) {
                            for (int i = 0; i < checkFriendsBean.getData().size(); i++) {//添加好友数据
                                //不添加自己
                                if (readRealname.equals(checkFriendsBean.getData().get(i).getBasicInfoA().getRealName())) {//自己
                                    CheckFriendsBean.DataBean.BasicInfoBBean basicInfoA = checkFriendsBean.getData().get(i).getBasicInfoB();
                                    if (basicInfoA != null) {
                                        searchList.add(new SearchBean(basicInfoA.getUserId(), 0, "", "", "", basicInfoA.getHeadImg(), basicInfoA.getRealName(), basicInfoA.getProfessionalIdentity()));
                                    }
//                            searchList.add(new SearchBean(basicInfoA.getUserId(),0,"","","",basicInfoA.getHeadImg(),basicInfoA.getRealName(),basicInfoA.getProfessionalIdentity()));
                                } else {
                                    CheckFriendsBean.DataBean.BasicInfoABean basicInfoA = checkFriendsBean.getData().get(i).getBasicInfoA();
                                    searchList.add(new SearchBean(basicInfoA.getUserId(), 0, "", basicInfoA.getCompany(), basicInfoA.getPosition(), basicInfoA.getHeadImg(), basicInfoA.getRealName(), basicInfoA.getProfessionalIdentity()));
                                }
                            }
                        }
                    }

                    for (int i = 0; i < isAccountList.size(); i++) {//已注册
                        CheckRegisterBean.DataBean dataBean = isAccountList.get(i);
//                        if(!isAccountList.get(i).getRealName().equals(readRealname)) {//自己
                        searchList.add(new SearchBean(dataBean.getUserId(), 1, dataBean.getPhone(), dataBean.getCompany(), dataBean.getPosition(), dataBean.getHeadImg(), dataBean.getRealName(), 0));
//                        }
                    }
                    for (String s : list) {//通讯录
                        String[] split = s.split("\\" + Person);
                        String[] splits = s.split("\\" + SIGN);
//                        phoneList.add(split[1]);
//                        phoneMap.put(split[1], split[0]);
                        if (!split[0].equals(readRealname)) {//自己
                            searchList.add(new SearchBean(split[0], 2, split[1], ""));
                        }
                    }

                    //添加群组数据
                    if (getMyGroupsBean != null) {
                        if (getMyGroupsBean.getData() != null) {
                            for (int i = 0; i < getMyGroupsBean.getData().size(); i++) {
                                GetMyGroupsBean.DataBean dataBean = getMyGroupsBean.getData().get(i);
                                if (dataBean != null) {
                                    searchList.add(new SearchBean(3, dataBean.getGroupName(), dataBean.get_id(), dataBean.getLogo()));
                                }
                            }
                        }
                    }
                    adapter.setNewDatas(list, map, isAccountList);
                }
                setData(searchList, map);
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ct).checkRegister(phoneList);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharePreferenceUtils.getBaseSharePreference().saveProfessionalIdentity(-1);
        requestMyFriends();
//        rxPermissions.request(android.Manifest.permission.READ_CONTACTS)
//                .subscribe(granted -> {
//                    setContacts(granted);
//                });
        int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
        if (applicationCount > 0) {//有好友申请，显示红点
            //做你该做的事情
            rlRed.setVisibility(View.VISIBLE);
        } else {
            rlRed.setVisibility(View.GONE);
        }

    }

    /**
     * 数据初始化并设置adapter
     */
    private void setData(List<SearchBean> searchList, HashMap<String, Boolean> map) {
        // 这里创建adapter的时候，构造方法参数传了一个接口对象，这很关键，回调接口中的方法来实现对过滤后的数据的获取
        // 回调方法获取过滤后的数据
        // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作
        searchContactsAdapter = new SearchContactsAdapter(searchList, map, getActivity(), mListener, new FilterSearchListener() {
            // 回调方法获取过滤后的数据
            public void getFilterData(List<SearchBean> searchList) {
                // 这里可以拿到过滤后数据，所以在这里可以对搜索后的数据进行操作  接口回调成功
                setItemClick(searchList);
                searchContactsAdapter.notifyDataSetChanged();
                //放在里面：搜索到对应的数据才显示
                recyclerView_search.setAdapter(searchContactsAdapter);
            }
        });
        //放在外面：没有搜索前就显示初始化的数据
//        listView.setAdapter(adapter);

    }

    /**
     * 搜索
     * 给listView添加item的单击事件
     *
     * @param filter_lists 过滤后的数据集
     */
    protected void setItemClick(final List<SearchBean> filter_lists) {
        recyclerView_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 点击对应的item时，弹出toast提示所点击的内容
//                Toast.makeText(getActivity(), filter_lists.get(position).getRealName(), Toast.LENGTH_SHORT).show();
                SearchBean searchBean = filter_lists.get(position);
                if (searchBean.getUserId() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("userid", searchBean.getUserId());
                    bundle.putString("realName", searchBean.getRealName());
                    Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (searchBean.getGroupId() > 0) {//群聊的
                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(searchBean.getGroupId());

                    //添加刷新群组信息
                    RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {

                        @Override
                        public Group getGroupInfo(String s) {

                            return new Group(Constant.DEVGROUPID + searchBean.getGroupId(),

                                    searchBean.getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + searchBean.getGroupLogo()));
                        }
                    }, true); //提供一群群组信息

                    RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + searchBean.getGroupId(), searchBean.getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + searchBean.getGroupLogo()))); // 添加一个群组信息
                    /**
                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
                     *
                     * @param context       应用上下文。
                     * @param targetGroupId 要聊天的群组 Id。
                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                     */
                    RongIM.getInstance().startGroupChat(Objects.requireNonNull(getActivity()), Constant.DEVGROUPID + searchBean.getGroupId(), searchBean.getGroupName());
                }
            }
        });
    }

    /**
     * 搜索中要邀请按钮的监听
     * 实现类，响应按钮点击事件
     */
    private SearchContactsAdapter.MyClickListener mListener = new SearchContactsAdapter.MyClickListener() {
        @Override
        public void myOnClick(int position, View v) {
            ContactsFragment.this.onItemChildClick(adapter, position, sentPI, deliverPI);
        }
    };
}
