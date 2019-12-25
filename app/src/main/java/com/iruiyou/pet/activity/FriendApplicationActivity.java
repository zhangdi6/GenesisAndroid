package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.FriendApplicationAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.AdoptBean;
import com.iruiyou.pet.bean.GetAppliersBean;
import com.iruiyou.pet.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：好友申请
 * 作者：sgf
 */
public class FriendApplicationActivity extends BaseActivity {

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
    @BindView(R.id.friendApplicationRecyclerView)
    RecyclerView friendApplicationRecyclerView;
    @BindView(R.id.tvNotData)
    TextView tvNotData;
    private FriendApplicationAdapter friendApplicationAdapter;
    private GetAppliersBean getAppliersBean;
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_friend_application;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.friend_application));
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
        context = FriendApplicationActivity.this;
        friendApplicationAdapter = new FriendApplicationAdapter(context);
        friendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendApplicationRecyclerView.setAdapter(friendApplicationAdapter);

        getData();

        friendApplicationAdapter.setOnItemClickListener(new FriendApplicationAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Bundle bundle = new Bundle();
                String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(position).getUserIdA()))) {//自己
                    bundle.putInt("userid", getAppliersBean.getData().get(position).getBasicInfoB().getUserId());
                    bundle.putString("realName", getAppliersBean.getData().get(position).getBasicInfoB().getRealName());
                }else {
                    bundle.putInt("userid", getAppliersBean.getData().get(position).getBasicInfoA().getUserId());
                    bundle.putString("realName", getAppliersBean.getData().get(position).getBasicInfoA().getRealName());
                }
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //申请好友接受按钮
        friendApplicationAdapter.setOnTextViewClickListener(new FriendApplicationAdapter.OnTextViewClickListener(){
            @Override
            public void onTextViewClick(int position) {
//              getAdopt(position);
                SharePreferenceUtils.getBaseSharePreference().saveAccept(1);
                Bundle bundle = new Bundle();
                String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(position).getUserIdA()))) {//自己
                    bundle.putInt("userid", getAppliersBean.getData().get(position).getBasicInfoB().getUserId());
                    bundle.putString("realName", getAppliersBean.getData().get(position).getBasicInfoB().getRealName());
                }else {
                    bundle.putInt("userid", getAppliersBean.getData().get(position).getBasicInfoA().getUserId());
                    bundle.putString("realName", getAppliersBean.getData().get(position).getBasicInfoA().getRealName());
                }
                Intent intent = new Intent(context, UserDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
    int userid;
    /**
     * 通过申请请求
     */
    private void getAdopt(int po) {

        String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
        if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(po).getUserIdA()))) {//自己
            userid = getAppliersBean.getData().get(po).getUserIdB();
        }else {
            userid = getAppliersBean.getData().get(po).getUserIdA();
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                AdoptBean adoptBean = GsonUtils.parseJson(resulte, AdoptBean.class);
                if (adoptBean.getStatusCode() == Constant.SUCCESS) {
                    int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    applicationCount --;
                    SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);
                    FriendApplicationAdapter.setContent();
//                    BroadcastManager.getInstance(context).sendBroadcast(Constant.ADDED_VISIBLE);//发送广播
                    Bundle bundle = new Bundle();
                    String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                    if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(po).getUserIdA()))) {//自己
                        bundle.putInt("userid", getAppliersBean.getData().get(po).getBasicInfoB().getUserId());
                        bundle.putString("realName", getAppliersBean.getData().get(po).getBasicInfoB().getRealName());
                    }else {
                        bundle.putInt("userid", getAppliersBean.getData().get(po).getBasicInfoA().getUserId());
                        bundle.putString("realName", getAppliersBean.getData().get(po).getBasicInfoA().getRealName());
                    }
                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }

            @Override
            public void onError(ApiException e) {
            }
        }, this).adopt(userid);
    }

    /**
     * 获取申请列表
     */
    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                //Log.e("sgf",resulte.toString());
                getAppliersBean = GsonUtils.parseJson(resulte, GetAppliersBean.class);
                if (getAppliersBean.getStatusCode() == Constant.SUCCESS) {
                    if (getAppliersBean.getData().size() > 0) {
                        tvNotData.setVisibility(View.GONE);

                        List<GetAppliersBean.DataBean> applier = getAppliersBean.getData();
                        int applicationCount = 0;
                        for (int i = 0; i < applier.size();i ++) {

                            GetAppliersBean.DataBean item = applier.get(i);
                            int statusA2B = item.getStatusA2B();
                            int statusB2A = item.getStatusB2A();
                            if(statusA2B == 3 && statusB2A == 3){
                                //已添加

                            }else {
                                //未添加
                                applicationCount ++;
                            }

                        }



                        SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);



                        friendApplicationAdapter.setNewData(getAppliersBean.getData());
                    } else {
                        tvNotData.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
            }
        }, this).getAppliers();
    }

}
