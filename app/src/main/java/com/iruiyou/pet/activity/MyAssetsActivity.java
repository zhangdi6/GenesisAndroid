package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.MyAssetsAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.MyAssetsRecord;
import com.iruiyou.pet.bean.UserBean;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:我的资源
 * 创建日期:2018/5/26 on 14:47
 * 作者:JiaoPeiRong
 */
public class MyAssetsActivity extends BaseActivity {
    @BindView(R.id.pestTitle)
    TextView pestTitle;
    @BindView(R.id.pvalue)
    TextView pvalue;
    @BindView(R.id.freeze)
    TextView freeze;
    @BindView(R.id.details)
    TextView details;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.exchange)
    TextView exchange;
    private RecyclerView recyclerView;
    private MyAssetsAdapter adapter;
    private ACache aCache;

    @Override
    public int getLayout() {
        return R.layout.activity_my_assets;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        aCache = ACache.get(this);
        initView();
        getData();
    }

    /**
     * 收支记录
     */
    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                MyAssetsRecord myAssetsRecord = GsonUtils.parseJson(resulte, MyAssetsRecord.class);
                adapter.setNewData(myAssetsRecord.getData());
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).harvestList();

    }

    /**
     * 初始化view
     */
    private void initView() {
        setTitle(getResources().getString(R.string.assets5));
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAssetsAdapter();
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_my_assets_head, null, false);
        ButterKnife.bind(this, inflate);
        titleNameText.setText("我的PETS");
        adapter.addHeaderView(inflate);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        UserBean bean = (UserBean) aCache.getAsObject(TagConstants.UserTag);
        pvalue.setText(String.valueOf(bean.getData().getPetsAmount()));
        freeze.setText("冻结PETS数" + " " + bean.getData().getPetsFrozen());

//        ConfigBean b = (ConfigBean) aCache.getAsObject(TagConstants.config);
        details.setText(SharePreferenceUtils.getBaseSharePreference().readAddr());
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&(configBean.getData().isDiscountAble()))
        {
            exchange.setVisibility(View.VISIBLE);
        }else {
            exchange.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.ll_title_left_view, R.id.exchange})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.exchange:
                Intent intent = new Intent(this, UrlWebActivity.class);
                intent.putExtra(TagConstants.WEBURL, BaseApi.baseUrl + UrlContent.discount +
                        "?userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId() +
                        "&token=" + SharePreferenceUtils.getBaseSharePreference().readToken());
                startActivity(intent);
                break;
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_MyAssetsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_MyAssetsActivity);
        MobclickAgent.onPause(this);
    }

}
