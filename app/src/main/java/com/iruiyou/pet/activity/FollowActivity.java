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
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.MyFollowAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.FollowBean;
import com.iruiyou.pet.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：我的关注
 * 作者：sgf
 */
public class FollowActivity extends BaseActivity {

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
    private MyFollowAdapter myFollowAdapter;
    private Context context;
    private FollowBean followBean;

    @Override
    public int getLayout() {
        return R.layout.activity_friend_application;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.my_attention));
        context = FollowActivity.this;

        myFollowAdapter = new MyFollowAdapter();
        friendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendApplicationRecyclerView.setAdapter(myFollowAdapter);
        getData();
        myFollowAdapter.setOnItemClickListener(new MyFollowAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                FollowBean.DataBean.BasicInfoBBean basicInfoB = followBean.getData().get(position).getBasicInfoB();
                if(basicInfoB!=null){
                    Bundle bundle = new Bundle();
                    bundle.putInt("userid",followBean.getData().get(position).getBasicInfoB().getUserId());
                    bundle.putString("realName",followBean.getData().get(position).getBasicInfoB().getRealName());
                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                //Log.e("sgf",resulte.toString());
                followBean = GsonUtils.parseJson(resulte, FollowBean.class);

                if(followBean.getStatusCode() == Constant.SUCCESS){
                    if(followBean.getData().size() > 0){
                        tvNotData.setVisibility(View.GONE);
                        myFollowAdapter.setNewData(followBean.getData());

                    }else {
                        tvNotData.setVisibility(View.VISIBLE);
                    }
                }

            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).getFollows();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
