package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.GroupMembersAdapter;
import com.iruiyou.pet.animations.MyLayoutAnimationHelper;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GroupMembersBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息-群组成员
 * 作者：sgf
 */
public class GroupMembersActivity extends BaseActivity {
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
    @BindView(R.id.groupsRecyclerView)
    RecyclerView recommendGroupsRecyclerView;
    @BindView(R.id.refreshLayout_groups)
    RefreshLayout refreshLayout_recommendgroups;
    private Context context;
    private int readCompanyid;
    private GroupMembersAdapter groupMembersAdapter;
    private int num = 0;
    private List<GroupMembersBean.DataBean> list = new ArrayList<>();

    @Override
    public int getLayout() {
        return R.layout.activity_recommend_groups2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = GroupMembersActivity.this;
        int member = getIntent().getIntExtra("member", 0);
        setTitle(getResources().getString(R.string.member_information)+ Constant.BRACKETS_LEFT+member+ Constant.BRACKETS_RIGHT);
        initDta();
        getRefresh();
    }

    private void initDta() {
        readCompanyid = SharePreferenceUtils.getBaseSharePreference().readCompanyid();
        //群成员适配器
        groupMembersAdapter = new GroupMembersAdapter();
        recommendGroupsRecyclerView.setNestedScrollingEnabled(false);
        recommendGroupsRecyclerView.setLayoutManager(new LinearLayoutManager(GroupMembersActivity.this));
        recommendGroupsRecyclerView.setAdapter(groupMembersAdapter);
        LayoutAnimationController controller = MyLayoutAnimationHelper.makeLayoutAnimationController();//添加item动画
        ViewGroup viewGroup = findViewById(R.id.refreshLayout_groups);
        viewGroup.setLayoutAnimation(controller);
        viewGroup.scheduleLayoutAnimation();
        playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromRight());

        requestRecommendGroups(num);
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_recommendgroups.setRefreshFooter(new ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_recommendgroups.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                num = 0;
                requestRecommendGroups(num);
            }
        });
        refreshLayout_recommendgroups.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
//                ++num;
//                num = num-1;
                requestRecommendGroups(num);
            }
        });
    }

    /**
     * 成员信息
     */
    private void requestRecommendGroups(int number) {

        /**
         * 获取群成员的信息
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    GroupMembersBean groupMembersBean = GsonUtils.parseJson(resulte, GroupMembersBean.class);
                    if(groupMembersBean!=null)
                    {
                        if (groupMembersBean.getStatusCode() == Constant.SUCCESS) {
                            refreshLayout_recommendgroups.finishRefresh(true);//传入false表示刷新失败
                            refreshLayout_recommendgroups.finishLoadmore(true);
                            if (number == 0) {
                                list.clear();
                            }
                            if (groupMembersBean != null && groupMembersBean.getData() != null)
                                num += groupMembersBean.getData().size();//加载时累加请求到的返回数据长度
                            list.addAll(groupMembersBean.getData());
                            if (number == 0) {
                                groupMembersAdapter.setNewData(list);
                            } else {
                                groupMembersAdapter.notifyDataSetChanged();
                            }

                            groupMembersAdapter.setOnItemClickListener(new GroupMembersAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(int position) {

                                    if (groupMembersBean.getData() != null) {
                                        Bundle bundle = new Bundle();
//                                bundle.putInt("userid", groupMembersBean.getData().get(position).getBasicInfo().getUserId());
//                                bundle.putString("realName", groupMembersBean.getData().get(position).getBasicInfo().getRealName());
                                        bundle.putInt("userid", list.get(position).getBasicInfo().getUserId());
                                        bundle.putString("realName", list.get(position).getBasicInfo().getRealName());
                                        Intent intent = new Intent(context, UserDetailsActivity.class);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else {
                            refreshLayout_recommendgroups.finishRefresh(true);
                            refreshLayout_recommendgroups.finishLoadmore(true);
                            T.showShort(groupMembersBean.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_recommendgroups.finishRefresh(false);
                refreshLayout_recommendgroups.finishLoadmore(false);
                T.showShort(e.getMessage());
            }
        }, this).getGroupMembers(readCompanyid, num);
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

    /**
     * 播放RecyclerView动画
     *
     * @param animation
     * @param isReverse
     */
    public void playLayoutAnimation(Animation animation, boolean isReverse) {
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.1f);
        controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);

        recommendGroupsRecyclerView.setLayoutAnimation(controller);
        groupMembersAdapter.notifyDataSetChanged();
        recommendGroupsRecyclerView.scheduleLayoutAnimation();
    }

    public void playLayoutAnimation(Animation animation) {
        playLayoutAnimation(animation, false);
    }
}
