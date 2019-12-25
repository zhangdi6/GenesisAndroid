package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.GoodsBuyRecordsAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GoodsBuyRecordsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 购买商品列表界面
 */
public class GoodsBuyRecordsActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.llWalletNodata)
    LinearLayout llWalletNodata;

    private GoodsBuyRecordsAdapter goodsBuyRecordsAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_goods_buy_records;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("购买记录");
        DialogUtil.getInstance().showLoadingDialog(GoodsBuyRecordsActivity.this);
        goodsBuyRecordsAdapter = new GoodsBuyRecordsAdapter();
        recyclerView.setAdapter(goodsBuyRecordsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(GoodsBuyRecordsActivity.this));
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test", "resulte is " + resulte);
                if (StringUtil.isNotEmpty(resulte)) {
                    GoodsBuyRecordsBean buyRecordsBean = GsonUtils.parseJson(resulte, GoodsBuyRecordsBean.class);
                    if (buyRecordsBean != null) {
                        if (Constant.SUCCESS == buyRecordsBean.getStatusCode()) {
                            if (buyRecordsBean.getData() != null) {
                                goodsBuyRecordsAdapter.setNewData(buyRecordsBean.getData());
                            }
                            if(goodsBuyRecordsAdapter.getData().size()==0){
                                recyclerView.setVisibility(View.GONE);
                                llWalletNodata.setVisibility(View.VISIBLE);
                            } else {
                                recyclerView.setVisibility(View.VISIBLE);
                                llWalletNodata.setVisibility(View.GONE);
                            }
                        } else if (StringUtil.isNotEmpty(buyRecordsBean.getMessage())) {
                            T.showShort(buyRecordsBean.getMessage());
                            recyclerView.setVisibility(View.GONE);
                            llWalletNodata.setVisibility(View.VISIBLE);
                        }
                    }
                }
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                e.printStackTrace();
                DialogUtil.getInstance().closeLoadingDialog();
                recyclerView.setVisibility(View.GONE);
                llWalletNodata.setVisibility(View.VISIBLE);
            }
        }, GoodsBuyRecordsActivity.this).goodsBuyRecords();
    }
}
