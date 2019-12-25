package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.iruiyou.pet.adapter.NodePartnerGridViewAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.AdoptBean;
import com.iruiyou.pet.bean.ApplyBean;
import com.iruiyou.pet.bean.MaichangBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MyGridView;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * 脉场-节点合伙人Node Partner
 * 作者：sgf
 *
 */
public class NodePartnerActivity extends BaseActivity {
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
    @BindView(R.id.nodePartnerGridView)
    MyGridView nodePartnerGridView;
    private Context context;
    private MaichangBean michangBean;
    private int friendStatus;

    @Override
    public int getLayout() {
        return R.layout.activity_node_partner;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.node_partner));
        context = NodePartnerActivity.this;
        initDta();
    }

    private void initDta() {
        getData();
    }

    /**
     * 获取节点人信息
     */
    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                michangBean = GsonUtils.parseJson(resulte, MaichangBean.class);
                if (michangBean.getStatusCode() == Constant.SUCCESS) {
                    NodePartnerGridViewAdapter mAdapter = new NodePartnerGridViewAdapter(context, michangBean.getData().getNodes(),michangBean.getData().getRelations());
                    nodePartnerGridView.setAdapter(mAdapter);
                    //gridView的点击事件
//                    nodePartnerGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                            //把点击的position传递到adapter里面去
////                            mAdapter.changeState(position);
////                            selectorPosition = position;
//                            T.showShort(position+"");
//                        }
//                    });

                    mAdapter.setOnItemClickListener(new NodePartnerGridViewAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(int position) {
                            MaichangBean.DataBean.NodesBean.BasicInfoBean basicInfo = michangBean.getData().getNodes().get(position).getBasicInfo();
                            if (basicInfo != null) {
                                Bundle bundle = new Bundle();
                                String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                                if (readUserId.equals(String.valueOf(basicInfo.getUserId()))) {//自己
                                    bundle.putInt("userid", basicInfo.getUserId());//checkFriendsBean.getData().get(position).getBasicInfoB()getUserIdA()
                                    bundle.putString("realName", basicInfo.getRealName());
                                } else {
                                    bundle.putInt("userid", basicInfo.getUserId());
                                    bundle.putString("realName", basicInfo.getRealName());
                                }
                                Intent intent = new Intent(context, UserDetailsActivity.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        }
                    });
                    //添加好友监听
                    mAdapter.setOnAddClickListener(new NodePartnerGridViewAdapter.OnAddClickListener() {
                        @Override
                        public void onTextViewClick(int position) {
                            MaichangBean.DataBean.NodesBean.BasicInfoBean basicInfo = michangBean.getData().getNodes().get(position).getBasicInfo();
                            if(basicInfo!=null) {
                                int userId = basicInfo.getUserId();
                                //0陌生人； 1申请； 2拒绝； 3好友； 4黑名单
                                if(friendStatus == 0){
                                    addFriends(userId);
                                }else if(friendStatus == 1){
//                                    tv_add_friends.setText(getString(R.string.send));
                                    getAdopt(userId);
                                    friendStatus = 3;
                                }else if(friendStatus == 2){
//                                    tv_add_friends.setText(getString(R.string.add_friends));
                                }else if(friendStatus == 3){
                                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(0);//单聊与群聊的去区分
                                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+basicInfo.getUserId(), basicInfo.getRealName());
                                }else if(friendStatus == 4){
                                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(0);
                                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+basicInfo.getUserId(), basicInfo.getRealName());
                                }
                            }
                            getData();//刷新数据
                        }
                    });
                    //关注监听
                    mAdapter.setOnFollowClickListener(new NodePartnerGridViewAdapter.OnFollowClickListener() {
                        @Override
                        public void onTextViewClick(int position) {
                            List<MaichangBean.DataBean.RelationsBean> relations = michangBean.getData().getRelations();
                            if (relations!=null) {
                                MaichangBean.DataBean.NodesBean.BasicInfoBean basicInfo = michangBean.getData().getNodes().get(position).getBasicInfo();
                                if(basicInfo!=null){
                                    int userId = basicInfo.getUserId();
                                    boolean followed = relations.get(position).isFollowed();
                                    if(followed){
                                        follow(2,userId);
                                    }else {
                                        follow(1,userId);
                                    }
                                    getData();//刷新数据
                                }
                            }
                        }
                    });
                }
//                refreshLayout_crystal_recharge.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
//                refreshLayout_crystal_recharge.finishRefresh(false);
            }
        }, this).maichang();
    }

//    @OnClick({R.id.llOfficial, R.id.llPraise})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.llOfficial:
////                startActivity(WebViewActivity.class);
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.official_website),"http://pbase.io");
//                break;
//            case R.id.llPraise:
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"http://pbase.io");
//                break;
//        }
//    }
    @Override
    protected void onResume() {
        super.onResume();
        getData();
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
     * 请求关注  1,关注  0，取消关注
     */
    private void follow(int type,int userId) {
        if(type == 1){
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
                    T.showShort(applyBean.getMessage());
                    //设置状态
                }

                @Override
                public void onError(ApiException e) {
                    T.showShort(e.getMessage());
                }
            }, this).followFriends(userId);
        }else {
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
                    T.showShort(applyBean.getMessage());
                }

                @Override
                public void onError(ApiException e) {
                    T.showShort(e.getMessage());
                }
            }, this).unFollow(userId);
        }

    }
    /**
     * 添加好友
     */
    private void addFriends(int userid) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
                T.showShort(applyBean.getMessage());
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).apply(userid);
    }
    /**
     * 通过申请请求
     */
    private void getAdopt(int userid) {

//        String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
//        if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(po).getUserIdA()))) {//自己
//            userid = getAppliersBean.getData().get(po).getUserIdB();
//        }else {
//            userid = getAppliersBean.getData().get(po).getUserIdA();
//        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                AdoptBean adoptBean = GsonUtils.parseJson(resulte, AdoptBean.class);
                if (adoptBean.getStatusCode() == Constant.SUCCESS) {
                    int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    applicationCount --;
                    SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);

                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, this).adopt(userid);
    }
}
