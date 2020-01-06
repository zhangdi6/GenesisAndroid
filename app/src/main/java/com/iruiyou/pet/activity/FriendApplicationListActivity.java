package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.FriendApplicationListAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GetAppListBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 好友申请列表
 */
public class FriendApplicationListActivity extends BaseActivity {

    private FriendApplicationListAdapter friendApplicationListAdapter;

    private FriendRefuseListAdapter mineFriendApplicationListAdapter;

    @BindView(R.id.mineFriendApplicationRecyclerView)
    RecyclerView mineFriendApplicationRecyclerView;

    @BindView(R.id.friendApplicationRecyclerView)
    RecyclerView friendApplicationRecyclerView;

    @BindView(R.id.scroll_content)
    ScrollView scrollView;

    @BindView(R.id.tvNotData)
    TextView tvNotData;

    @BindView(R.id.linear_get)
    LinearLayout linearGet;

    @BindView(R.id.linear_back)
    LinearLayout linearBack;

    @BindView(R.id.image_right)
    ImageView imageRight;

    @BindView(R.id.image_right_mine)
    ImageView imageViewMine;

    @Override
    public int getLayout() {
        return R.layout.activity_friend_application_list;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.new_friend_application_request));
        mineFriendApplicationListAdapter=new FriendRefuseListAdapter(true, FriendApplicationListActivity.this);
        friendApplicationListAdapter=new FriendApplicationListAdapter(false, FriendApplicationListActivity.this);
        friendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mineFriendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mineFriendApplicationRecyclerView.setAdapter(mineFriendApplicationListAdapter);
        friendApplicationRecyclerView.setAdapter(friendApplicationListAdapter);
        mineFriendApplicationRecyclerView.setNestedScrollingEnabled(false);
        friendApplicationRecyclerView.setNestedScrollingEnabled(false);

//        friendApplicationRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
//        mineFriendApplicationRecyclerView.addItemDecoration(new SpacesItemDecoration(10));
        getData();
    }

    @OnClick(value = {R.id.linear_get, R.id.linear_back})
    public void onViewClick(View view)
    {
        switch (view.getId())
        {
            case R.id.linear_get:
                if(friendApplicationRecyclerView.getVisibility()==View.VISIBLE)
                {
                    imageRight.setImageResource(R.drawable.icon_shang);
                    friendApplicationRecyclerView.setVisibility(View.GONE);
                }
                else
                {
                    imageRight.setImageResource(R.drawable.icon_xia);
                    friendApplicationRecyclerView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.linear_back:
                if(mineFriendApplicationRecyclerView.getVisibility()==View.VISIBLE)
                {
                    imageViewMine.setImageResource(R.drawable.icon_shang);
                    mineFriendApplicationRecyclerView.setVisibility(View.GONE);
                }
                else
                {
                    imageViewMine.setImageResource(R.drawable.icon_xia);
                    mineFriendApplicationRecyclerView.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void getData()
    {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNoBlankAndNoNull(resulte))
                {
                    GetAppListBean getAppliersBean= GsonUtil.GsonToBean(resulte, GetAppListBean.class);
                    if(getAppliersBean.getStatusCode()== Constant.SUCCESS)
                    {
                        if(getAppliersBean.getData()!=null)
                        {
                            if(getAppliersBean.getData().getApplyMe()!=null)
                            {
                                mineFriendApplicationListAdapter.setNewdataAndCount(getAppliersBean.getData().getInfoCount(),getAppliersBean.getData().getMyApply());
                            }
                            if(getAppliersBean.getData().getMyApply()!=null)
                            {
                                friendApplicationListAdapter.setNewdataAndCount(getAppliersBean.getData().getInfoCount(),getAppliersBean.getData().getApplyMe());
                            }

                            if((mineFriendApplicationListAdapter.getItemCount()+friendApplicationListAdapter.getItemCount())==0)
                            {
                                scrollView.setVisibility(View.GONE);
                                tvNotData.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                scrollView.setVisibility(View.VISIBLE);
                                tvNotData.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            scrollView.setVisibility(View.GONE);
                            tvNotData.setVisibility(View.VISIBLE);
                        }
                    }
                    else if(StringUtil.isNoBlankAndNoNull(getAppliersBean.getMessage()))
                    {
                        T.showShort(getAppliersBean.getMessage());
                    }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, FriendApplicationListActivity.this).getApplyMeAndMyApply();
    }

}
