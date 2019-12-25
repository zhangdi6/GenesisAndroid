package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.ZhongchouAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ZhongChouBean;
import com.j256.ormlite.stmt.query.In;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZhongChouActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.recycle_zhunchou)
    RecyclerView recycle_zhunchou;


    @BindView(R.id.text_contract)
    TextView text_contract;

    private List<ZhongChouBean.DataBean> list;
    private ZhongChouBean bean;
    private ZhongchouAdapter zhongchouAdapter;

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        getData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        text_contract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ZhongChouActivity.this, ContractActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public int getLayout() {
        return R.layout.activity_zhong_chou;
    }



    public void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {



                bean =new Gson().fromJson(resulte, ZhongChouBean.class);

                list = bean.getData();
                //  Toast.makeText(TextActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                LinearLayoutManager layoutManager = new LinearLayoutManager(ZhongChouActivity.this);
                recycle_zhunchou.setLayoutManager(layoutManager);
                zhongchouAdapter = new ZhongchouAdapter(list,1);
                recycle_zhunchou.setAdapter(zhongchouAdapter);
                zhongchouAdapter.notifyDataSetChanged();
                setListener();


            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).getZhongChou();
    }
    public void setListener() {

        zhongchouAdapter.setOnItemClickListener(new ZhongchouAdapter.OnItemClickListener() {
            @Override
            public void getclicklistener(int position) {
                Intent intent = new Intent(ZhongChouActivity.this, WebViewNewActivity.class);
                intent.putExtra("title","");
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);

            }
        });

    }
}
