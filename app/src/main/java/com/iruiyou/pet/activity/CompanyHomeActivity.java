package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.adapter.CommunityAdapter;
import com.iruiyou.pet.adapter.TeamMembers2Adapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CompanyMembersBean;
import com.iruiyou.pet.bean.GroupMembersBean;
import com.iruiyou.pet.bean.JoinGroupBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;

/**
 * 职场-公司主页-公司
 * 作者：sgf
 */
public class CompanyHomeActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;

    @BindView(R.id.refreshLayout_company_home)
    SmartRefreshLayout refreshLayout_company_home;
    @BindView(R.id.im_company_head)
    ImageView im_company_head;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.tv_company_fullname)
    TextView tv_company_fullname;
    @BindView(R.id.bt_add_community)
    Button bt_add_community;
    @BindView(R.id.company_home_recyclerView)
    RecyclerView company_home_recyclerView;
    @BindView(R.id.team_members_recyclerView)
    MaxRecyclerView team_members_recyclerView;
    @BindView(R.id.tv_community_number)
    TextView tv_community_number;
    @BindView(R.id.tv_company_profile)
    TextView tv_company_profile;
    @BindView(R.id.scrollView_company_home)
    ScrollView scrollView_company_home;
    @BindView(R.id.ll_add_community)
    LinearLayout ll_add_community;
    private Context context;
    private int companyid,memberCount;
    private String companyName;
    private String groupName;
    private String readBasicId;

    @Override
    public int getLayout() {
        return R.layout.activity_company_home;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = CompanyHomeActivity.this;
        String companyName = getIntent().getStringExtra("companyName");
        setTitle(companyName);
        initDta();
        getData();
        getCompanyMembers();
        getRefresh();
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_company_home.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getCompanyMembers();
            }
        });
    }

    private void getData() {
        companyid = getIntent().getIntExtra("companyid", 0);
        memberCount = getIntent().getIntExtra("memberCount", 0);//公司人数
        groupName = getIntent().getStringExtra("groupName");
        String desc = getIntent().getStringExtra("desc");
        String logo = getIntent().getStringExtra("logo");
        String nick = getIntent().getStringExtra("nick");
        tv_company_name.setText(nick);//公司简称
        tv_company_fullname.setText(companyName);//公司全称
        tv_company_profile.setText(desc);//公司简介
        GlideUtils.display(BaseApi.baseUrlNoApi + logo, im_company_head);//公司头像
        tv_community_number.setText(memberCount + "/3000");
        SharePreferenceUtils.getBaseSharePreference().saveCompanyid(companyid);
        //添加刷新群组信息
        RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {

            @Override
            public Group getGroupInfo(String s) {

                return new Group(Constant.DEVGROUPID + companyid,

                        groupName, Uri.parse(BaseApi.baseUrlNoApi + logo));
            }
        }, true); //提供一群群组信息

//        RongIM.setGroupInfoProvider(new Group(Constant.DEVGROUPID+companyid, groupName, Uri.parse(logo)));
        RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + companyid, groupName, Uri.parse(BaseApi.baseUrlNoApi + logo))); // 添加一个群组信息

        /**
         * 获取群成员的信息
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                GroupMembersBean groupMembersBean = GsonUtils.parseJson(resulte, GroupMembersBean.class);
                if (groupMembersBean.getStatusCode() == Constant.SUCCESS) {
                    //群成员适配器
                    CommunityAdapter communityAdapter = new CommunityAdapter(context, groupMembersBean.getData());
                    company_home_recyclerView.setAdapter(communityAdapter);
                    communityAdapter.notifyDataSetChanged();

                    //群成员监听
                    communityAdapter.setItemClickListener(new CommunityAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            joinGroupNow();
//                Bundle bundle = new Bundle();
//                bundle.putInt("userid", recomendsBean.getData().get(position).getBasicInfo().getUserId());
//                bundle.putString("realName", recomendsBean.getData().get(position).getBasicInfo().getRealName());//get(position).getBasicInfo().getRealName()
//                Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
                        }
                    });
                    //上传成员信息
//                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                        @Override
//                        public UserInfo getUserInfo(String s) {
//                            return null;
//                        }
//                    });
//                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                        @Override
//                        public UserInfo getUserInfo(String s) {
//                            for (int i = 0; i < groupMembersBean.getData().size(); i++) {
//                                return findUserInfo();
//                            }
//                        }, true); // 提供一群人信息

//                    if(groupMembersBean.getData().size()>0){
//                        if(groupMembersBean.getData()!=null){
//                            for (int i = 0; i < groupMembersBean.getData().size(); i++) {
//                                GroupMembersBean.DataBean.BasicInfoBean basicInfo = groupMembersBean.getData().get(i).getBasicInfo();
//                                String headImg = basicInfo.getHeadImg();
//                                if(TextUtils.isEmpty(headImg)){
//                                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + basicInfo.getUserId() + "", basicInfo.getRealName() + "", Uri.parse(""))); // 提供一个人信息
//                                }else {
//                                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + basicInfo.getUserId() + "", basicInfo.getRealName() + "", Uri.parse(basicInfo.getHeadImg()))); // 提供一个人信息
//                                }
//                            }
//                        }
//                    }

                } else {
                    T.showShort(groupMembersBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).getGroupMembers(companyid, 0);
    }

    /**
     * 获取群成员的信息
     */
    private void getGroupMembersData() {

    }

    /**
     * 获取团队成员的信息
     */
    private void getCompanyMembers() {
        /**
         * 获取团队成员的信息
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CompanyMembersBean companyMembersBean = GsonUtils.parseJson(resulte, CompanyMembersBean.class);
                if (companyMembersBean.getStatusCode() == Constant.SUCCESS) {
                    List<CompanyMembersBean.DataBean> data = companyMembersBean.getData();
                    //群成员适配器
                    TeamMembers2Adapter teamMembers2Adapter = new TeamMembers2Adapter(context, data);
                    team_members_recyclerView.setAdapter(teamMembers2Adapter);
                    teamMembers2Adapter.notifyDataSetChanged();

                    //群成员监听
                    teamMembers2Adapter.setItemClickListener(new TeamMembers2Adapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("userid", companyMembersBean.getData().get(position).getUserId());
                            bundle.putString("realName", companyMembersBean.getData().get(position).getRealName());//get(position).getBasicInfo().getRealName()
                            Intent intent = new Intent(context, UserDetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                } else {
                    T.showShort(companyMembersBean.getMessage());
                }
                refreshLayout_company_home.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_company_home.finishRefresh(false);
                T.showShort(e.getMessage());
            }
        }, this).getCompanyMembers(companyid);
    }

    private void initDta() {

        //处理scrollView与recycleview冲突，导致scrollView不置顶问题
        scrollView_company_home.smoothScrollTo(0, 0);
        team_members_recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        //公司社区列表
        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(context);
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        company_home_recyclerView.setLayoutManager(linearLayoutManager);
        company_home_recyclerView.addItemDecoration(new SpacesItemDecoration(5));

        //团队成员
        team_members_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //用户资料id
        readBasicId = SharePreferenceUtils.getBaseSharePreference().readBasicId();
    }

    @OnClick({R.id.bt_add_community, R.id.ll_add_community})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_add_community:
                joinGroupNow();
                break;
            case R.id.ll_add_community:
                joinGroupNow();
                break;
        }
    }

    /**
     * 加入群聊
     */
    private void joinGroupNow() {
        /**
         * 加入群聊
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                JoinGroupBean joinGroupBean = GsonUtils.parseJson(resulte, JoinGroupBean.class);
                if (joinGroupBean.getStatusCode() == 0 || joinGroupBean.getStatusCode() == 1) {
                    //添加群名和人数到map集合
                    StringUtil.addData(groupName,memberCount);
                    /**
                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
                     *
                     * @param context       应用上下文。
                     * @param targetGroupId 要聊天的群组 Id。
                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                     */
                    RongIM.getInstance().startGroupChat(context, Constant.DEVGROUPID + companyid, groupName);
                    //
//                            RongIM.getInstance().joinChatRoom(groupName, 0, new RongIMClient.OperationCallback() {
//                                @Override
//                                public void onSuccess() {
//                                    T.showShort("进去");
//                                    RongIM.getInstance().startChatRoomChat(context, groupName, true);
//                                }
//
//                                @Override
//                                public void onError(RongIMClient.ErrorCode errorCode) {
//                                    T.showShort("失败");
//
//                                }
//                            });
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).joinGroup(readBasicId, companyid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onPause(this);
    }
}
