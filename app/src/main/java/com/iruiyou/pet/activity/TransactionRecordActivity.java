package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
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
import com.iruiyou.pet.adapter.CrystalRevenueAdapter;
import com.iruiyou.pet.adapter.IncubationAssetsAdapter;
import com.iruiyou.pet.adapter.MyCrystalAdapter;
import com.iruiyou.pet.adapter.MyWalletAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-交易记录
 * 作者：sgf
 *
 */
public class TransactionRecordActivity extends BaseActivity {
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
    @BindView(R.id.refreshLayout_tansaction)
    SmartRefreshLayout refreshLayout_tansaction;
    @BindView(R.id.recyclerView_pbs)
    RecyclerView recyclerView_pbs;
    @BindView(R.id.recyclerView_crystal)
    RecyclerView recyclerView_crystal;
    @BindView(R.id.recyclerView_incubation_assets)
    RecyclerView recyclerView_incubation_assets;
    @BindView(R.id.recyclerView_crystal_revenue)
    RecyclerView recyclerViewCrystalRevenue;
    @BindView(R.id.llWalletNodata)
    LinearLayout llWalletNodata;
    @BindView(R.id.ll_transaction_pbs)
    LinearLayout ll_transaction_pbs;
    @BindView(R.id.tv_transaction_pbs)
    TextView tv_transaction_pbs;
    @BindView(R.id.tv_transaction_pbs_blue_one)
    TextView tv_transaction_pbs_blue_one;
    @BindView(R.id.ll_transaction_crystal)
    LinearLayout ll_transaction_crystal;
    @BindView(R.id.tv_transaction_crystal)
    TextView tv_transaction_crystal;
    @BindView(R.id.tv_transaction_crystal_blue_two)
    TextView tv_transaction_crystal_blue_two;
    @BindView(R.id.im_top_wallet)
    ImageView im_top_wallet;
    @BindView(R.id.ll_incubation_assets)
    LinearLayout ll_incubation_assets;
    @BindView(R.id.tv_incubation_assets)
    TextView tv_incubation_assets;
    @BindView(R.id.tv_incubation_assets_blue)
    TextView tv_incubation_assets_blue;
    @BindView(R.id.tv_crystal_revenue)
    TextView tv_crystal_revenue;
    @BindView(R.id.tv_crystal_revenue_blue)
    TextView tv_crystal_revenue_blue;
    private HarvestBean harvestBean;
    private IncubationAssetsAdapter incubationAssetsAdapter;
    private MyWalletAdapter myWalletAdapter;
    private MyCrystalAdapter myCrystalAdapter;
    private CrystalRevenueAdapter crystalRevenueAdapter;

    private final int RecordTypeCrystal=0;//水晶
    private final int RecordTypeCrystalRevenue=1;//水晶收益
    private final int RecordTypePBS=2;//PBS
    private final int RecordTypePBSRevenue=3;//PBS收益

    private int selectRecordType=RecordTypeCrystal;
    @Override
    public int getLayout() {
        return R.layout.activity_transaction_record;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.assets3));
        initDta();
        getData();
        getRefresh();
    }

    /**
     * 请求pbs-水晶收支记录
     */
    private void getData() {
        //pbs收支记录
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    harvestBean = GsonUtils.parseJson(resulte, HarvestBean.class);
                    if(harvestBean!=null)
                    {
                        if (harvestBean.getStatusCode() == Constant.SUCCESS&&(harvestBean.getData() != null)) {
//                                setViewGone2();
                                myWalletAdapter.setNewData(harvestBean.getData().getHarvestList());//pbs记录
                                incubationAssetsAdapter.setNewData(harvestBean.getData().getHatches());//孵化记录
                                myCrystalAdapter.setNewData(harvestBean.getData().getCrystalRecords());
                                crystalRevenueAdapter.setNewData(harvestBean.getData().getHatchCrystals());
                                changeContent();
                        }
                        else if(StringUtil.isNotEmpty(harvestBean.getMessage()))
                        {
                            T.showShort(harvestBean.getMessage());
                        }
                    }
                }
                refreshLayout_tansaction.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_tansaction.finishRefresh(false);
            }
        }, this).harvestList();
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_tansaction.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
//                setPbsOrCrystalView(13,14,13,13,
//                        4,0,4,4,8,0,8,8);
            }
        });
    }

    private void initDta() {

        myWalletAdapter = new MyWalletAdapter(this);
        recyclerView_pbs.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_pbs.setAdapter(myWalletAdapter);
        recyclerView_pbs.setNestedScrollingEnabled(false);//禁止滑动

        crystalRevenueAdapter=new CrystalRevenueAdapter();
        recyclerViewCrystalRevenue.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCrystalRevenue.setAdapter(crystalRevenueAdapter);
        recyclerViewCrystalRevenue.setNestedScrollingEnabled(false);//禁止滑动

        incubationAssetsAdapter = new IncubationAssetsAdapter(this);
        recyclerView_incubation_assets.setLayoutManager(new LinearLayoutManager(TransactionRecordActivity.this));
        recyclerView_incubation_assets.setAdapter(incubationAssetsAdapter);
        recyclerView_incubation_assets.setNestedScrollingEnabled(false);//禁止滑动


        //水晶收支记录
        myCrystalAdapter = new MyCrystalAdapter(TransactionRecordActivity.this);
        recyclerView_crystal.setLayoutManager(new LinearLayoutManager(TransactionRecordActivity.this));
        recyclerView_crystal.setAdapter(myCrystalAdapter);
        recyclerView_crystal.setNestedScrollingEnabled(false);//禁止滑动

    }

    @OnClick({R.id.ll_transaction_crystal, R.id.ll_transaction_pbs, R.id.ll_incubation_assets, R.id.ll_crystal_revenue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_transaction_crystal:
                selectRecordType=RecordTypeCrystal;
                changeContent();
                break;
            case R.id.ll_transaction_pbs:
                selectRecordType=RecordTypePBS;
                changeContent();
                break;
            case R.id.ll_incubation_assets:
                selectRecordType=RecordTypePBSRevenue;
                changeContent();
                break;
            case R.id.ll_crystal_revenue:
                selectRecordType=RecordTypeCrystalRevenue;
                changeContent();
                break;
        }
    }


    private void changeContent()
    {
        switch (selectRecordType)
        {
            case RecordTypeCrystal:
                setViewGone2();
                setPbsOrCrystalView(13,14,13,13,
                        0,4,4,4,0,8,8,8);
                break;
            case RecordTypePBS:
                setViewGone();
                setPbsOrCrystalView(13,14,13,13,4,
                        0,4,4,8,0,8,8);
                break;
            case RecordTypePBSRevenue:
                setViewGone3();
                setPbsOrCrystalView(13,13,14,13,4,4,
                        0,4,8,8,0,8);
                break;
            case RecordTypeCrystalRevenue:
                setViewGone4();
                setPbsOrCrystalView(13,13,13,14,4,4,
                        4,0,8,8,8,0);
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
     * 控件的显示隐藏
     */
    private void setViewGone() {
        if ((harvestBean!=null)&&(harvestBean.getData()!=null)&&(harvestBean.getData().getHarvestList()!=null)&&(harvestBean.getData().getHarvestList().size() > 0)) {
            llWalletNodata.setVisibility(View.GONE);
            recyclerView_pbs.setVisibility(View.VISIBLE);
        } else {
            llWalletNodata.setVisibility(View.VISIBLE);
            recyclerView_pbs.setVisibility(View.GONE);
        }
    }
    /**
     * 水晶控件的显示隐藏
     */
    private void setViewGone2() {
        if ((harvestBean!=null)&&(harvestBean.getData()!=null)&&(harvestBean.getData().getCrystalRecords()!=null)&&(harvestBean.getData().getCrystalRecords().size()>0)) {
            llWalletNodata.setVisibility(View.GONE);
            recyclerView_crystal.setVisibility(View.VISIBLE);
        } else {
            llWalletNodata.setVisibility(View.VISIBLE);
            recyclerView_crystal.setVisibility(View.GONE);
        }
    }
    /**
     * 水晶控件的显示隐藏
     */
    private void setViewGone3() {
        if ((harvestBean!=null)&&(harvestBean.getData()!=null)&&(harvestBean.getData().getCrystalRecords()!=null)
                &&(harvestBean.getData().getCrystalRecords().size() > 0)) {
            llWalletNodata.setVisibility(View.GONE);
            recyclerView_incubation_assets.setVisibility(View.VISIBLE);
        } else {
            llWalletNodata.setVisibility(View.VISIBLE);
            recyclerView_incubation_assets.setVisibility(View.GONE);
        }
    }

    /**
     * 水晶收益控件的显示隐藏
     */
    private void setViewGone4()
    {
        if ((harvestBean!=null)&&(harvestBean.getData()!=null)&&(harvestBean.getData().getHatchCrystals()!=null)
                &&(harvestBean.getData().getHatchCrystals().size() > 0)) {
            recyclerViewCrystalRevenue.setVisibility(View.VISIBLE);
            llWalletNodata.setVisibility(View.GONE);
        }
        else
        {
            recyclerViewCrystalRevenue.setVisibility(View.GONE);
            llWalletNodata.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置布局显示隐藏
     * @param crystal_size
     * @param pbs_size
     * @param tvpbsView
     * @param tvcrystalView
     * @param lvpbsView
     * @param lvcrystalView
     */
    private void setPbsOrCrystalView(int crystal_size, int pbs_size, int incubation_size,int crystalrevenue_size,int tvpbsView,
                                     int tvcrystalView, int tvincubationView,int tvcrystalrevenue,
                                     int lvpbsView, int lvcrystalView, int lvincubationView,int lvcrystalrevenue){
        tv_transaction_crystal.setTextSize(TypedValue.COMPLEX_UNIT_SP,crystal_size);
        tv_transaction_pbs.setTextSize(TypedValue.COMPLEX_UNIT_SP,pbs_size);
        tv_incubation_assets.setTextSize(TypedValue.COMPLEX_UNIT_SP,incubation_size);
        tv_crystal_revenue.setTextSize(TypedValue.COMPLEX_UNIT_SP,crystalrevenue_size);
        tv_transaction_crystal_blue_two.setVisibility(tvpbsView);
        tv_transaction_pbs_blue_one.setVisibility(tvcrystalView);
        tv_incubation_assets_blue.setVisibility(tvincubationView);
        tv_crystal_revenue_blue.setVisibility(tvcrystalrevenue);
        recyclerView_crystal.setVisibility(lvpbsView);
        recyclerView_pbs.setVisibility(lvcrystalView);
        recyclerView_incubation_assets.setVisibility(lvincubationView);
        recyclerViewCrystalRevenue.setVisibility(lvcrystalrevenue);
    }
}
