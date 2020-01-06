package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class FriendRequestActivity extends BaseActivity {

    private FriendApplicationListAdapter friendApplicationListAdapter;

    private FriendRefuseListAdapter  mineFriendApplicationListAdapter;

    @BindView(R.id.radio_wart)//待处理请求
    RadioButton radio_wart;

    @BindView(R.id.radio_already)//已发送请求
    RadioButton radio_already;

    @BindView(R.id.radio_group)
    RadioGroup radio_group;

    @BindView(R.id.recy_wartting)
    RecyclerView friendApplicationRecyclerView;

    @BindView(R.id.recy_already)
    RecyclerView mineFriendApplicationRecyclerView;

    @BindView(R.id.tvNotData)
    ImageView tvNotData;

    @BindView(R.id.backess)
    ImageView backess;

    @BindView(R.id.textView6)
    TextView textView6;



    @Override
    public int getLayout() {
        return R.layout.activity_friend_request;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mineFriendApplicationListAdapter=new FriendRefuseListAdapter(true, FriendRequestActivity.this);
        friendApplicationListAdapter=new FriendApplicationListAdapter(false, FriendRequestActivity.this);
        friendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mineFriendApplicationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mineFriendApplicationRecyclerView.setAdapter(mineFriendApplicationListAdapter);
        friendApplicationRecyclerView.setAdapter(friendApplicationListAdapter);
        mineFriendApplicationRecyclerView.setNestedScrollingEnabled(false);
        friendApplicationRecyclerView.setNestedScrollingEnabled(false);

        getData();
        backess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_wart:
                        if(friendApplicationRecyclerView.getVisibility()== View.VISIBLE)
                        {
                           // imageRight.setImageResource(R.drawable.icon_shang);
                            friendApplicationRecyclerView.setVisibility(View.GONE);
                            mineFriendApplicationRecyclerView.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                           // imageRight.setImageResource(R.drawable.icon_xia);
                            friendApplicationRecyclerView.setVisibility(View.VISIBLE);
                            mineFriendApplicationRecyclerView.setVisibility(View.GONE);
                        }

                        if (friendApplicationListAdapter.getItemCount()==0){
                            tvNotData.setVisibility(View.VISIBLE);
                            textView6.setVisibility(View.VISIBLE);
                            friendApplicationRecyclerView.setVisibility(View.VISIBLE);
                        }else{
                            tvNotData.setVisibility(View.GONE);
                            textView6.setText(View.GONE);
                        }
                        break;


                    case R.id.radio_already:
                        if(mineFriendApplicationRecyclerView.getVisibility()==View.VISIBLE)
                        {
                          //  imageViewMine.setImageResource(R.drawable.icon_shang);
                            mineFriendApplicationRecyclerView.setVisibility(View.GONE);
                            friendApplicationRecyclerView.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            //imageViewMine.setImageResource(R.drawable.icon_xia);
                            mineFriendApplicationRecyclerView.setVisibility(View.VISIBLE);
                            friendApplicationRecyclerView.setVisibility(View.GONE);
                        }


                        if (mineFriendApplicationListAdapter.getItemCount()==0){
                            tvNotData.setVisibility(View.VISIBLE);
                            textView6.setVisibility(View.VISIBLE);
                            friendApplicationRecyclerView.setVisibility(View.GONE);
                            mineFriendApplicationRecyclerView.setVisibility(View.GONE);
                        }else{
                            tvNotData.setVisibility(View.GONE);
                            textView6.setVisibility(View.GONE);
                            mineFriendApplicationRecyclerView.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });



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



                            if((mineFriendApplicationListAdapter.getItemCount())==0 || friendApplicationListAdapter.getItemCount()==0)
                            {
                               // scrollView.setVisibility(View.GONE);
                                tvNotData.setVisibility(View.VISIBLE);
                                textView6.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                               // scrollView.setVisibility(View.VISIBLE);
                                tvNotData.setVisibility(View.GONE);
                                textView6.setVisibility(View.GONE);
                            }
                        }
                        else
                        {
                            //scrollView.setVisibility(View.GONE);
                            tvNotData.setVisibility(View.VISIBLE);
                            textView6.setVisibility(View.VISIBLE);
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
        }, FriendRequestActivity.this).getApplyMeAndMyApply();
    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }
}
