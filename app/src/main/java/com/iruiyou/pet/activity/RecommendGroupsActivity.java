package com.iruiyou.pet.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
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
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.RecommendGroupsAdapter;
import com.iruiyou.pet.animations.MyLayoutAnimationHelper;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.JoinGroupBean;
import com.iruiyou.pet.bean.RecommendGroupsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.PageNameConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Group;

/**
 * 消息-群聊推荐
 * 作者：sgf
 *
 */
public class RecommendGroupsActivity extends BaseActivity {
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
    @BindView(R.id.recommendGroupsRecyclerView)
    RecyclerView recommendGroupsRecyclerView;
    @BindView(R.id.refreshLayout_recommendgroups)
    SmartRefreshLayout refreshLayout_recommendgroups;

    private RecommendGroupsAdapter recommendGroupsAdapter;
    private RecommendGroupsBean recommendGroupsBean;
    private Context context;
    private String readBasicId;


    @Override
    public int getLayout() {
        return R.layout.activity_recommend_groups;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = RecommendGroupsActivity.this;
        setTitle(getResources().getString(R.string.recommendation));
        initDta();
        getRefresh();
    }

    private void initDta() {
        recommendGroupsAdapter = new RecommendGroupsAdapter();
        recommendGroupsRecyclerView.setLayoutManager(new MyLinearLayoutManager(this));

        LayoutAnimationController controller = MyLayoutAnimationHelper.makeLayoutAnimationController();
        ViewGroup viewGroup = findViewById(R.id.refreshLayout_recommendgroups);
        viewGroup.setLayoutAnimation(controller);
        viewGroup.scheduleLayoutAnimation();
        playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromRight());
        //用户资料id
        readBasicId = SharePreferenceUtils.getBaseSharePreference().readBasicId();
        requestRecommendGroups();
        //我的群组推荐监听
        recommendGroupsAdapter.setOnItemClickListener(new RecommendGroupsAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                RecommendGroupsBean.DataBean dataBean = recommendGroupsBean.getData().get(position);
                if (dataBean != null) {
                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(dataBean.get_id());

                    //添加刷新群组信息
                    RongIM.setGroupInfoProvider(new RongIM.GroupInfoProvider() {

                        @Override
                        public Group getGroupInfo(String s) {

                            return new Group(Constant.DEVGROUPID + dataBean.get_id(),

                                    dataBean.getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + dataBean.getLogo()));
                        }
                    }, true); //提供一群群组信息

                    RongIM.getInstance().refreshGroupInfoCache(new Group(Constant.DEVGROUPID + dataBean.get_id(), dataBean.getGroupName(), Uri.parse(BaseApi.baseUrlNoApi + dataBean.getLogo()))); // 添加一个群组信息
                    joinGroupNow(dataBean.get_id(),dataBean.getGroupName(),dataBean.get_id());
                    /**
                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
                     *
                     * @param context       应用上下文。
                     * @param targetGroupId 要聊天的群组 Id。
                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                     */
//                    RongIM.getInstance().startGroupChat(context, Constant.DEVGROUPID+dataBean.get_id(), dataBean.getGroupName());
                }
            }
        });
    }
    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_recommendgroups.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                requestRecommendGroups();
            }
        });
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
                    recommendGroupsAdapter.setNewData(recommendGroupsBean.getData());
                    recommendGroupsRecyclerView.setAdapter(recommendGroupsAdapter);
                }
                refreshLayout_recommendgroups.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_recommendgroups.finishRefresh(false);//传入false表示刷新失败
                T.showShort(e.getMessage());
            }
        }, this).recommendGroups();
    }

//    @OnClick({R.id.llOfficial, R.id.llPraise})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.llOfficial:
//                break;
//            case R.id.llPraise:
//                break;
//        }
//    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    /**
     * 加入群聊
     */
    private void joinGroupNow(int userid,String groupName,int companyids) {
        /**
         * 加入群聊
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                JoinGroupBean joinGroupBean = GsonUtils.parseJson(resulte, JoinGroupBean.class);
                if (joinGroupBean.getStatusCode() == 0 || joinGroupBean.getStatusCode() == 1) {
                    /**
                     * 启动群组聊天界面。融云群组ID规则：开发环境localGroup4；测试环境devGroup4--8080；正式环境prodGroup4
                     *
                     * @param context       应用上下文。
                     * @param targetGroupId 要聊天的群组 Id。
                     * @param title         聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
                     */
                    RongIM.getInstance().startGroupChat(context, Constant.DEVGROUPID + userid, groupName);
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
        }, this).joinGroup(readBasicId, companyids);
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
        recommendGroupsAdapter.notifyDataSetChanged();
        recommendGroupsRecyclerView.scheduleLayoutAnimation();
    }

    public void playLayoutAnimation(Animation animation) {
        playLayoutAnimation(animation, false);
    }
    /**
     * 动画效果
     *   if (id == R.id.action_left) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromLeft());
     } else if (id == R.id.action_right) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromRight());
     } else if (id == R.id.action_bottom) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromBottom());
     } else if (id == R.id.action_top) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromTop(), true);
     } else if (id == R.id.action_scale_enlarge) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetScaleBig());
     } else if (id == R.id.action_scale_narrow) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetScaleNarrow());
     } else if (id == R.id.action_alpha) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetAlpha());
     } else if (id == R.id.action_rotation) {
     playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetRotation());
     }
     */
}
