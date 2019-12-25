package com.iruiyou.pet.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
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

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.ContactsActivity;
import com.iruiyou.pet.activity.ContactsSearchActivity;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.server.broadcast.BroadcastManager;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.MyContanctAdapter;
import com.iruiyou.pet.adapter.SeeMeAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.DeleteBean;
import com.iruiyou.pet.bean.RecommendGroupsBean;
import com.iruiyou.pet.bean.SeeMeLogBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DragPointView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 消息模块聊天列表界面（会话）
 * Created by sgf on 2018/10/19.
 */
public class MessageFragment2 extends BaseFragment {

    private RecommendGroupsBean recommendGroupsBean;
    @BindView(R.id.ll_recommendGroups)
    LinearLayout ll_recommendGroups;//

    @BindView(R.id.linear_friend_request)
    LinearLayout linear_friend_request;//

    @BindView(R.id.ll_title_left_view)
    LinearLayout ll_title_left_view;//

    @BindView(R.id.linear_chat)
    LinearLayout linear_chat;

    @BindView(R.id.linear_mine)
    LinearLayout linear_mine;



    @BindView(R.id.rl_news_contacts)
    RelativeLayout rl_news_contacts;//

    @BindView(R.id.rl_search_message)
    RelativeLayout rl_search_message;//

    @BindView(R.id.rl_im_red_message)
    RelativeLayout rl_im_red_message;//

    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;

    @BindView(R.id.text_see_me)
    TextView text_see_me;

//    @BindView(R.id.text_new_friend)
//    TextView text_new_friend;

    @BindView(R.id.text_chat)
    TextView text_chat;

    @BindView(R.id.mine_interaction)
    TextView mine_interaction;

    @BindView(R.id.view_line_top)
    View view_line_top;

    @BindView(R.id.dpv_contacts)
    DragPointView dpv_contacts;

    @BindView(R.id.recyle_see_me)
    RecyclerView recyle_see_me;

    @BindView(R.id.recycle_mine)
    RecyclerView friendRecyclerView;


    @BindView(R.id.goto_contacts)
    ImageView goto_contacts;
//    @BindView(R.id.recyle_new_friends)
//    RecyclerView recyle_new_friends;

//    @BindView(R.id.refreshLayout_new_friends)
//    SmartRefreshLayout refreshLayout_new_friends;

    @BindView(R.id.refreshLayout_see_me)
    SmartRefreshLayout refreshLayout_see_me;

    private ArrayList<OccupationBeen> occupationBeens;
    private SearchPresenter registerPresenter;
    private MyContanctAdapter myContanctAdapter;
    private SeeMeAdapter seeMeAdapter;
    private CheckFriendsBean checkFriendsBean;
    private OccupationAdapter occupationAdapter;
    private String clickPid;
//    private FriendApplicationListAdapter friendApplicationListAdapter;

    private String TAG = "MessageFragment";
    private String disturb = "";
    /**
     * 单例模式
     *
     * @return
     */
    public static MessageFragment2 getInstance() {
        return new MessageFragment2();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, null);

        ButterKnife.bind(this,view);
        text_chat.setVisibility(View.VISIBLE);
        mine_interaction.setVisibility(View.GONE);
        text_see_me.setVisibility(View.GONE);
        return view;
    }


    @OnClick(value = {R.id.text_chat, R.id.mine_interaction, R.id.text_see_me, R.id.goto_contacts})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.text_chat:
                text_chat.setAlpha(1);
                mine_interaction.setAlpha(0.5f);
                text_see_me.setAlpha(0.5f);
                linear_chat.setVisibility(View.VISIBLE);
                refreshLayout_see_me.setVisibility(View.GONE);
//                relay_interaction.setVisibility(View.GONE);
                view_line_top.setVisibility(View.GONE);
//                linear_send_tab.setVisibility(View.GONE);
                linear_mine.setVisibility(View.GONE);
                break;
            case R.id.mine_interaction:
                text_chat.setAlpha(0.5f);
                mine_interaction.setAlpha(1);
                text_see_me.setAlpha(0.5f);
                linear_mine.setVisibility(View.VISIBLE);
                linear_chat.setVisibility(View.GONE);
//                relay_interaction.setVisibility(View.VISIBLE);
                view_line_top.setVisibility(View.VISIBLE);
//                linear_send_tab.setVisibility(View.VISIBLE);
                refreshLayout_see_me.setVisibility(View.GONE);
                break;
//            case R.id.text_new_friend:
//                text_see_me.setAlpha(0.5f);
//                text_new_friend.setAlpha(1);
//                refreshLayout_see_me.setVisibility(View.GONE);
////                refreshLayout_new_friends.setVisibility(View.VISIBLE);
//                break;
            case R.id.text_see_me:
//                text_new_friend.setAlpha(0.5f);
                text_chat.setAlpha(0.5f);
                mine_interaction.setAlpha(0.5f);
                text_see_me.setAlpha(1);
                linear_chat.setVisibility(View.GONE);
                refreshLayout_see_me.setVisibility(View.VISIBLE);
                linear_mine.setVisibility(View.GONE);
//                refreshLayout_new_friends.setVisibility(View.GONE);
                break;

            case R.id.goto_contacts:
                StartActivityManager.startNetWorkActivity(getActivity());
                /*Intent intent2 = new Intent(getContext(), GoodFriendsActivity.class);
                startActivity(intent2);*/
                break;
        }
    }



    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_see_me.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_see_me.setOnRefreshListener((refreshlayout) ->{
            getSeeMeData();
        });
        refreshLayout_see_me.setEnableLoadMore(false);


//        //设置 Footer 为 经典式样
//        refreshLayout_new_friends.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
//        //刷新操作
//        refreshLayout_new_friends.setOnRefreshListener((refreshlayout) ->{
//            getApplyMeAndMyApply();
//        });
//        refreshLayout_new_friends.setEnableLoadMore(false);
    }



    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        setTitle(getResources().getString(R.string.chat));
//        requestRecommendGroups();
//        recommendGroupsAdapter = new RecommendGroupsAdapter();
//        recommendGroupsRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
        disturb = getString(R.string.donot_disturb);
        //接收广播-隐藏红点
        BroadcastManager.getInstance(getActivity()).addAction(Constant.FANS_GONE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if(!TextUtils.isEmpty(command)){
                    if((Constant.FANS_GONE).equals(command)) {//有好友申请，显示红点
                        rl_im_red_message.setVisibility(View.GONE);
                        dpv_contacts.setVisibility(View.GONE);
                    }
                }
            }
        });
        //接收广播我有新的粉丝了-显示红点
        BroadcastManager.getInstance(getActivity()).addAction(Constant.FANS_VISIBLE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    if ((Constant.FANS_VISIBLE).equals(command)) {
                        rl_im_red_message.setVisibility(View.VISIBLE);
                        dpv_contacts.setVisibility(View.VISIBLE);
                    }
                }
            }
        });





        SubconversationlistFragment fragment = new SubconversationlistFragment();
        Uri uri = Uri.parse("rong://" + Objects.requireNonNull(getActivity()).getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //rong_content 为你要加载的 id
        transaction.add(R.id.conversationlist, fragment);
        transaction.commit();
        /**
         * 设置会话列表界面操作的监听器。
         */
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());

        //我的群组推荐监听
//        recommendGroupsAdapter.setOnItemClickListener(new RecommendGroupsAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                RecommendGroupsBean.DataBean dataBean = recommendGroupsBean.getData().get(position);
//                if (dataBean != null) {
//                    /**
//                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
//                     *
//                     * @param context       应用上下文。
//                     * @param targetGroupId 要聊天的群组 Id。
//                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
//                     */
//                    RongIM.getInstance().startGroupChat(getActivity(), Constant.DEVGROUPID+dataBean.get_id(), dataBean.getGroupName());
//                }
//            }
//        });

        linear_friend_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityManager.startFriendApplication(getActivity());
            }
        });

        ll_recommendGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityManager.startRecommendGroupsActivity(getActivity());
            }
        });
        //消息按钮
        rl_news_contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ContactsActivity.class);
                dpv_contacts.setVisibility(View.GONE);
            }
        });
        rl_search_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ContactsSearchActivity.class);
            }
        });


        //接收广播好友申请-显示红点
        BroadcastManager.getInstance(getActivity()).addAction(Constant.APPLY_VISIBLE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if (!TextUtils.isEmpty(command)) {
                    if ((Constant.APPLY_VISIBLE).equals(command)) {//有好友申请，显示红点
                        //获取json结果
                        /*String json = intent.getStringExtra("result");
                        Log.e("json",json);*/
                        //做你该做的事情
                        dpv_contacts.setVisibility(View.VISIBLE);

                    } else {
                        dpv_contacts.setVisibility(View.GONE);
                    }
                }
            }
        });
        ll_title_left_view.setOnClickListener((view) -> {
            getActivity().finish();
        });
        seeMeAdapter = new SeeMeAdapter(getContext());
        recyle_see_me.setLayoutManager(new MyLinearLayoutManager(getContext()));
        recyle_see_me.setAdapter(seeMeAdapter);
        seeMeAdapter.setOnItemClickListener(((adapter, view, position) -> {
            SeeMeLogBean.SeeLogInfo seeLogInfo = (SeeMeLogBean.SeeLogInfo) adapter.getData().get(position);
            StartActivityManager.startUserDetailsActivity(getContext(),seeLogInfo.getFromInfo().getUserId(),seeLogInfo.getFromInfo().getRealName());
        }));
        myContanctAdapter = new MyContanctAdapter();
        myContanctAdapter.setFromMessage(true);
        friendRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
        friendRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        friendRecyclerView.setAdapter(myContanctAdapter);
        myContanctAdapter.setOnItemClickListener((adapter, view, position) -> {
            CheckFriendsBean.DataBean  dataBean= (CheckFriendsBean.DataBean) adapter.getData().get(position);
            if(dataBean.getBasicInfoA().getUserId() == Integer.valueOf(SharePreferenceUtils.getBaseSharePreference().readUserId()).intValue() ){
                StartActivityManager.startUserDetailsActivity(getActivity(),dataBean.getBasicInfoB().getUserId(),dataBean.getBasicInfoB().getRealName());
            }else{
                StartActivityManager.startUserDetailsActivity(getActivity(),dataBean.getBasicInfoA().getUserId(),dataBean.getBasicInfoA().getRealName());
            }
        });

        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        positionRecyclerView.setLayoutManager(linearLayoutManager);
        positionRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        registerPresenter = new SearchPresenter();
        registerPresenter.IPresenter(getActivity());
        occupationBeens = new ArrayList<>();

        //请求职业
        registerPresenter.getOccupationsList(new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    int indexSelect=0;
                    // 这块儿的逻辑（接受接口数据） 根据你们的实际业务调整
                    ArrayList<OccupationBeen> data = (ArrayList<OccupationBeen>) o;
                    if (data.size() > 0) {
                        if(StringUtil.isEmpty(clickPid)){
                            clickPid=data.get(0).getDbKey()+"";
                            data.get(0).setSelect(true);
                        } else {
                            for(int i=0;i<data.size();i++){
                                if((data.get(i).getDbKey()+"").equals(clickPid)){
                                    data.get(i).setSelect(true);
                                    indexSelect=i;
                                }
                            }
                        }
                    }

                    occupationBeens.addAll(data);
                    occupationAdapter = new OccupationAdapter(getContext(), occupationBeens, 3);
                    occupationAdapter.setHidden(true);
                    occupationAdapter.setPosition(indexSelect);
                    positionRecyclerView.setAdapter(occupationAdapter);
                    occupationAdapter.setItemClickListener((OccupationBeen firstPageListBean, int position) -> {
                        for (int i = 0; i < occupationBeens.size(); i++) {
                            if (occupationBeens.get(i).equals(firstPageListBean)) {
                                occupationBeens.get(i).setSelect(true);
                            } else {
                                occupationBeens.get(i).setSelect(false);
                            }
                        }
                        occupationAdapter.notifyDataSetChanged();
                    });
                }
            }

            @Override
            public void filed(Exception e) {
                e.printStackTrace();
            }
        });



//        friendApplicationListAdapter=new FriendApplicationListAdapter(false, getContext());
//        friendApplicationListAdapter.setNewVersion(true);
//        recyle_new_friends.setAdapter(friendApplicationListAdapter);
//        recyle_new_friends.setLayoutManager(new MyLinearLayoutManager(getContext()));
        getRefresh();
        requestMyFriends();
        getSeeMeData();
//        getApplyMeAndMyApply();
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
                        //上传头像和name
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String id) {
//                        return new UserInfo(checkFriendsBean.getData().get(0).getUserId()+"",homeRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(homeRefreshBean.getData().getBasicInfo().getHeadImg()));
//                    }
//                }, true);
                        List<CheckFriendsBean.DataBean> data = checkFriendsBean.getData();
                        for (int i = 0; i < checkFriendsBean.getData().size(); i++) {
                            //刷新用户头像到融云上
                            String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                            if (readUserId.equals(String.valueOf(data.get(i).getUserIdA()))) {//自己

                            } else {
//                            searchList.clear();
//                            searchList.add(new SearchBean(data.get(i).getBasicInfoA().getUserId(),0,"",data.get(i).getBasicInfoA().getCompany(),data.get(i).getBasicInfoA().getPosition(),data.get(i).getBasicInfoA().getHeadImg(),data.get(i).getBasicInfoA().getRealName(),data.get(i).getBasicInfoA().getProfessionalIdentity()));
                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + data.get(i).getBasicInfoA().getUserId(), data.get(i).getBasicInfoA().getRealName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(i).getBasicInfoA().getHeadImg())));//刷新同步头像昵称到融云
                            }
                        }
//                        tvMyFriends.setText(getString(R.string.my_friend) + "(" + checkFriendsBean.getData().size() + ")");
                        if (!TextUtils.isEmpty(checkFriendsBean.getMessage())) {
                            T.showShort(checkFriendsBean.getMessage());
                        }
                        myContanctAdapter.setNewData(checkFriendsBean.getData());
                        ((MainActivity)getActivity()).setFriendsCount(checkFriendsBean.getData().size());
                    }
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ((RxAppCompatActivity)getActivity())).getFriends();

    }


    /**
     * 群推荐信息
     */
    private void requestRecommendGroups() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                recommendGroupsBean = GsonUtils.parseJson(resulte, RecommendGroupsBean.class);
                if (recommendGroupsBean.getStatusCode() == Constant.SUCCESS) {
                    List<RecommendGroupsBean.DataBean> data = recommendGroupsBean.getData();
                    //添加刷新群组信息到融云上
//                    RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {
//                        @Override
//                        public Group getGroupInfo(String s) {
//                            int result = 0;
//                            for (int i = 0; i < data.size(); i++) {
//                                if (s.equals(data.get(i).get_id() + ""))
//                                    result = i;
//                            }
//                            return new Group(Constant.DEVGROUPID + data.get(result).get_id(),
//
//                                    data.get(result).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(result).getLogo()));
//                        }
//                    }, true); //提供一群群组信息
//
//                    for (int i = 0; i < data.size(); i++) {
//                        RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + data.get(i).get_id(), data.get(i).getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(i).getLogo()))); // 添加一个群组信息
//                    }
//                    recommendGroupsAdapter.setNewData(recommendGroupsBean.getData());
//                    recommendGroupsRecyclerView.setAdapter(recommendGroupsAdapter);
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).recommendGroups();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BroadcastManager.getInstance(getActivity()).destroy(Constant.FANS_GONE);
        BroadcastManager.getInstance(getActivity()).destroy(Constant.FANS_VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();//刷新数据

        int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
        if (applicationCount > 0) {//有好友申请，显示红点
            //做你该做的事情
            rl_im_red_message.setVisibility(View.VISIBLE);
            dpv_contacts.setVisibility(View.VISIBLE);
            dpv_contacts.setText(applicationCount+"");
            dpv_contacts.setTextColor(Color.WHITE);
        } else {
            rl_im_red_message.setVisibility(View.GONE);
            dpv_contacts.setVisibility(View.GONE);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
    }

    public class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener {
        /**
         * 当点击会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String targetId) {
//            Toast.makeText(context,targetId,Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 当长按会话头像后执行。
         *
         * @param context          上下文。
         * @param conversationType 会话类型。
         * @param targetId         被点击的用户id。
         * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String targetId) {
//            Toast.makeText(context,"长按 "+targetId,Toast.LENGTH_SHORT).show();
            return false;
        }

        /**
         * 长按会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 长按时的会话条目。
         * @return 如果用户自己处理了长按会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
            Conversation.ConversationType conversationType = uiConversation.getConversationType();//判断聊天类型，3:群聊、1:单聊等
            int conversationTypeValue = conversationType.getValue();//下面无需判断聊天类型
//            String[] split = uiConversation.getConversationTargetId().split(Constant.TARGETID);
//            int conversationTarget = Integer.parseInt(split[1]);
            //先获取单聊和群聊免打扰的状态值
            if (conversationTypeValue == 3) {//群聊
                RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP,
                        uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                            @Override
                            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                final int value = conversationNotificationStatus.getValue();
                                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                                if (value == 1) {
                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(0);
                                    disturb = getString(R.string.donot_disturb2);
                                } else {
                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(1);
                                    disturb = getString(R.string.cancel_no_bother);
                                }
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                            }
                        });
            } else if (conversationTypeValue == 1) {
                RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.PRIVATE,
                        uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                            @Override
                            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                final int value = conversationNotificationStatus.getValue();
                                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                                if (value == 1) {
                                    disturb = getString(R.string.donot_disturb2);
                                } else {
                                    disturb = getString(R.string.cancel_no_bother);
                                }
                            }

                            @Override
                            public void onError(RongIMClient.ErrorCode errorCode) {

                            }
                        });
            }
            //弹出的“保存图片”的Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
            builder.setItems(new String[]{getString(R.string.pet_detail), disturb, getString(R.string.cancel)}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {
//                            RongIMClient.getInstance().deleteMessages(new int[]{uiConversation.get});
                                    RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
                                        @Override
                                        public void onSuccess(Boolean aBoolean) {
                                            RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {

                                                @Override
                                                public void onSuccess(List<Conversation> conversationeees) {

                                                    //清除对应的用户对话记录
                                                    RongIM.getInstance().clearMessages(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
                                                        @Override
                                                        public void onSuccess(Boolean aBoolean) {

                                                        }

                                                        @Override
                                                        public void onError(RongIMClient.ErrorCode errorCode) {

                                                        }
                                                    });


                                                }

                                                @Override
                                                public void onError(RongIMClient.ErrorCode errorCode) {

                                                }
                                            });

                                        }

                                        @Override
                                        public void onError(RongIMClient.ErrorCode errorCode) {

                                        }
                                    });

                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode errorCode) {

                                }
                            });
                            break;
                        case 1:
                            if (conversationTypeValue == 3) {//群聊
                                RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.GROUP,
                                        uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                            @Override
                                            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                                final int value = conversationNotificationStatus.getValue();
                                                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                                                if (value == 1) {
                                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(0);
//                                                    disturb = getString(R.string.donot_disturb2);
                                                } else {
                                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(1);
//                                                    disturb = getString(R.string.cancel_no_bother);
                                                }
                                                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.GROUP,
                                                        uiConversation.getConversationTargetId(), conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                                            @Override
                                                            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                                            }

                                                            @Override
                                                            public void onError(RongIMClient.ErrorCode errorCode) {
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onError(RongIMClient.ErrorCode errorCode) {

                                            }
                                        });
                            } else if (conversationTypeValue == 1) {
                                RongIM.getInstance().getConversationNotificationStatus(Conversation.ConversationType.PRIVATE,
                                        uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                            @Override
                                            public void onSuccess(final Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                                final int value = conversationNotificationStatus.getValue();
                                                final Conversation.ConversationNotificationStatus conversationNotificationStatus1;
                                                if (value == 1) {
                                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(0);
                                                } else {
                                                    conversationNotificationStatus1 = Conversation.ConversationNotificationStatus.setValue(1);
                                                }
                                                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.PRIVATE,
                                                        uiConversation.getConversationTargetId(), conversationNotificationStatus1, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                                                            @Override
                                                            public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
                                                            }

                                                            @Override
                                                            public void onError(RongIMClient.ErrorCode errorCode) {
                                                            }
                                                        });
                                            }

                                            @Override
                                            public void onError(RongIMClient.ErrorCode errorCode) {

                                            }
                                        });
                            }

                            break;
                        case 2:
                            break;
                    }
                }
            });
            builder.show();

            //删除弹框提示
//            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//            builder.setTitle(getResources().getString(R.string.delete_sure));
////            builder.setMessage("删除吗？");
//            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
//                        @Override
//                        public void onSuccess(Boolean aBoolean) {
////                            RongIMClient.getInstance().deleteMessages(new int[]{uiConversation.get});
//                            RongIM.getInstance().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
//                                @Override
//                                public void onSuccess(Boolean aBoolean) {
//                                    RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//
//                                        @Override
//                                        public void onSuccess(List<Conversation> conversationeees) {
//
//                                            //清除对应的用户对话记录
//                                            RongIM.getInstance().clearMessages(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), new RongIMClient.ResultCallback<Boolean>() {
//                                                @Override
//                                                public void onSuccess(Boolean aBoolean) {
//
//                                                }
//
//                                                @Override
//                                                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                                                }
//                                            });
//
//
//                                        }
//
//                                        @Override
//                                        public void onError(RongIMClient.ErrorCode errorCode) {
//
//                                        }
//                                    });
//
//                                }
//
//                                @Override
//                                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                                }
//                            });
//
//                        }
//
//                        @Override
//                        public void onError(RongIMClient.ErrorCode errorCode) {
//
//                        }
//                    });
//                    dialog.dismiss();
//                }
//            });
//            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();

//            RongIM.getInstance().removeConversation(uiConversation.getConversationType(),uiConversation.getConversationTargetId());//删除会话列表中的消息
//            //删除会话列表
//            RongIM.getInstance().removeConversation(Conversation.ConversationType.PRIVATE, uiConversation.getConversationTargetId());//个人 在自己删除对方好友关系
//            RongIM.getInstance().getRongIMClient().removeConversation(Conversation.ConversationType.PRIVATE, split[1], new RongIMClient.ResultCallback<Boolean>() {
//                @Override
//                public void onSuccess(Boolean aBoolean) {
//                    Toast.makeText(context,aBoolean + "" ,Toast.LENGTH_SHORT).show();
//                    getData(conversationTarget);
//                }
//
//                @Override
//                public void onError(RongIMClient.ErrorCode errorCode) {
//
//                }
//            });
            return true;
        }

        /**
         * 点击会话列表中的 item 时执行。
         *
         * @param context        上下文。
         * @param view           触发点击的 View。
         * @param uiConversation 会话条目。
         * @return 如果用户自己处理了点击会话后的逻辑处理，则返回 true， 否则返回 false，false 走融云默认处理方式。
         */
        @Override
        public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
            return false;
        }
    }

    private void getSeeMeData(){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test","getSeeMeData is "+resulte);
                if(StringUtil.isNotEmpty(resulte)){
                    SeeMeLogBean seeMeLogBean = GsonUtil.GsonToBean(resulte, SeeMeLogBean.class);
                    if(seeMeLogBean.getStatusCode()== Constant.SUCCESS&&(seeMeLogBean.getData().getLogs()!=null) ){
                        seeMeAdapter.setNewData(seeMeLogBean.getData().getLogs());
                    }
                    ((MainActivity)getActivity()).setAccessCount(seeMeLogBean.getData().getLogs().size());
                }

                refreshLayout_see_me.finishRefresh();
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_see_me.finishRefresh();
            }
        }, (MainActivity) getContext()).findFriendLog(SharePreferenceUtils.getBaseSharePreference().readBasicId());
    }

    /**
     * 删除好友
     *
     * @param targetId
     */
    private void deleteFriend(int targetId) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                DeleteBean deleteBean = GsonUtils.parseJson(resulte, DeleteBean.class);
                T.showShort(deleteBean.getMessage());
            }

            @Override
            public void onError(ApiException e) {
            }
        }, (MainActivity) getContext()).deleteFriend(targetId);
    }



//    private void getApplyMeAndMyApply()
//    {
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                if(StringUtil.isNoBlankAndNoNull(resulte))
//                {
//                    GetAppListBean getAppliersBean=GsonUtil.GsonToBean(resulte, GetAppListBean.class);
//                    if(getAppliersBean.getStatusCode()== Constant.SUCCESS)
//                    {
//                        if(getAppliersBean.getData()!=null)
//                        {
//
//                            if(getAppliersBean.getData().getMyApply()!=null)
//                            {
//                                friendApplicationListAdapter.setNewdataAndCount(getAppliersBean.getData().getInfoCount(),getAppliersBean.getData().getApplyMe());
//                            }
//                        }
//                    }
//                    else if(StringUtil.isNoBlankAndNoNull(getAppliersBean.getMessage()))
//                    {
//                        T.showShort(getAppliersBean.getMessage());
//                    }
//                }
//                refreshLayout_new_friends.finishRefresh();
//            }
//
//            @Override
//            public void onError(ApiException e) {
//                refreshLayout_new_friends.finishRefresh();
//            }
//        }, (MainActivity)getActivity()).getApplyMeAndMyApply();
//    }



}
