package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.SingleGridViewAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CrystalGoodsBean;
import com.iruiyou.pet.bean.CrystalPriceBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的钱包-水晶充值
 * 作者：sgf
 */
public class CrystalRechargeActivity extends BaseActivity {
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
    @BindView(R.id.gridView_crytal_recharge)
    GridView gridView_crytal_recharge;
    @BindView(R.id.tv_crystal_balance)
    TextView tv_crystal_balance;
    @BindView(R.id.bt_purchase)
    Button bt_purchase;
    @BindView(R.id.refreshLayout_crystal_recharge)
    SmartRefreshLayout refreshLayout_crystal_recharge;
    private Context context;
    private List<CrystalPriceBean> mList;
    private SingleGridViewAdapter mAdapter;
    int selectorPosition = 0;
    private String[] crystalPrice;//排序ietm对应文字
    private CrystalGoodsBean crystalGoodsBean;

    @Override
    public int getLayout() {
        return R.layout.activity_crystal_recharge;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.recharge));
        context = CrystalRechargeActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
        String crystal = getIntent().getStringExtra("crystal");
        if(!TextUtils.isEmpty(crystal)){
//            tv_crystal_balance.setText(BigDecimalUtil.round(crystal, Constant.SCALE_NUM));
            tv_crystal_balance.setText(crystal);
        }else {
            tv_crystal_balance.setText("0");
        }

        getData();
//        crystalPrice = new String[]{"5","10","30","50","100","500"};
//        //添加数据
//        mList = new ArrayList<>();
//        for (int i = 0; i < crystalPrice.length; i++) {
//            mList.add(new CrystalPriceBean(crystalPrice[i],crystalPrice[i]));
//        }
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_crystal_recharge.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                getData();
            }
        });
    }

    /**
     * 请求水晶的商品列表
     */
    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                crystalGoodsBean = GsonUtils.parseJson(resulte, CrystalGoodsBean.class);
                if ((crystalGoodsBean!=null)&&crystalGoodsBean.getStatusCode() == Constant.SUCCESS) {
                    mAdapter = new SingleGridViewAdapter(context, crystalGoodsBean.getData());
                    gridView_crytal_recharge.setAdapter(mAdapter);
                    //gridView的点击事件
                    gridView_crytal_recharge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //把点击的position传递到adapter里面去
                            mAdapter.changeState(position);
                            selectorPosition = position;
                        }
                    });
                }
                refreshLayout_crystal_recharge.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
                refreshLayout_crystal_recharge.finishRefresh(false);
            }
        }, this).crystalGoods();
    }


    @OnClick({R.id.bt_purchase})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_purchase:
                if ((crystalGoodsBean!=null)&&crystalGoodsBean.getData() != null) {
                    StartActivityManager.startWebViewActivity(CrystalRechargeActivity.this, getString(R.string.recharge1), crystalGoodsBean.getData().get(selectorPosition).getUrl());
                }
                break;
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
     * button的点击监听
     *
     * @param view
     */
    public void buttonFinish(View view) {
        Intent intent = new Intent();
        intent.putExtra("pos", "第 " + selectorPosition + " 个");
        setResult(999, intent);
        finish();
    }
}
