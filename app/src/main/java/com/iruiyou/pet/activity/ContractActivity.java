package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.ConstractAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConstractBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContractActivity extends BaseActivity {

    @BindView(R.id.recy_contract)
    RecyclerView recy_contract;
    @BindView(R.id.back)
    ImageView ing;

    private ConstractBean conbean;
    private List<ConstractBean.ConData> list;
    private ConstractAdapter constractAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_contract;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        getData();

        ing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                conbean =new Gson().fromJson(resulte, ConstractBean.class);

                list = conbean.getData();
                LinearLayoutManager layoutManager = new LinearLayoutManager(ContractActivity.this);
                recy_contract.setLayoutManager(layoutManager);

                ArrayList<ConstractBean.ConData> conData = new ArrayList<ConstractBean.ConData>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getAmount()>=2){
                        for (int j = 0; j < list.get(i).getAmount(); j++) {
                            conData.add(list.get(i));
                        }
                    }else{
                        conData.add(list.get(i));
                    }
                }
                constractAdapter = new ConstractAdapter(conData);
                recy_contract.setAdapter(constractAdapter);
                constractAdapter.notifyDataSetChanged();
                setListener();

            }

            @Override
            public void onError(ApiException e) {

            }
        }, ContractActivity.this).getContractPDF();
    }
    public void setListener() {

        constractAdapter.setOnItemClickListener(new ConstractAdapter.OnItemClickListener() {
            @Override
            public void getconstract(int position) {
                StartActivityManager.startShowPDFActivity(ContractActivity.this);
            }
        });

    }
}
