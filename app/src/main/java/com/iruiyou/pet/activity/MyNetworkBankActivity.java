package com.iruiyou.pet.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import com.iruiyou.pet.adapter.MyFriendsAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 脉场-我的人脉库
 * 作者：sgf
 *
 */
public class MyNetworkBankActivity extends BaseActivity {
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
    @BindView(R.id.invitedContactsRecyclerView)
    MaxRecyclerView invitedContactsRecyclerView;
    @BindView(R.id.tv_invited_contactst)
    TextView tv_invited_contactst;
    private int mailList = 0;
    private MyFriendsAdapter myFriendsAdapter;
    private Context context;


    @Override
    public int getLayout() {
        return R.layout.activity_my_network_bank;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.my_network_bank));
        context = MyNetworkBankActivity.this;
        initDta();
    }

    private void initDta() {
        myFriendsAdapter = new MyFriendsAdapter();
        invitedContactsRecyclerView.setLayoutManager(new MyLinearLayoutManager(context));
        invitedContactsRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        requestMyFriends();
    }

    @OnClick({R.id.ll_invited_contacts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_invited_contacts:
               mailList++;
                if (mailList % 2 == 0) {
                    invitedContactsRecyclerView.setVisibility(View.VISIBLE);
                    setTextViewRightIcon(R.drawable.down_arrow2, tv_invited_contactst);
                } else {
                    invitedContactsRecyclerView.setVisibility(View.GONE);
                    setTextViewRightIcon(R.drawable.up_arrow2, tv_invited_contactst);
                }
                break;
//            case R.id.llPraise:
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"http://pbase.io");
//                break;
        }
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
     * 请求我的好友数据
     */
    private void requestMyFriends() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CheckFriendsBean checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriendsBean.class);
                if (checkFriendsBean.getStatusCode() == Constant.SUCCESS) {

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


//                    tvMyFriends.setText(getString(R.string.my_friend) + "(" + checkFriendsBean.getData().size() + ")");
                    if (!TextUtils.isEmpty(checkFriendsBean.getMessage())) {
                        T.showShort(checkFriendsBean.getMessage());
                    }
//                    myFriendsAdapter.setNewDatas(checkRegisterBean.getData());
                    myFriendsAdapter.setNewData(checkFriendsBean.getData());
                    invitedContactsRecyclerView.setAdapter(myFriendsAdapter);
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).getFriends();//(MainActivity) getContext()

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
}
