package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.DensityUtil;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.adapter.PulseFiledAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.LookForSbBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 找人
 */
public class LookForPeopleActivity extends BaseActivity {

    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.refreshLayout_find)
    SmartRefreshLayout refreshLayout_find;


    private ArrayList<OccupationBeen> occupationBeens;
    private OccupationAdapter occupationAdapter;
    private String mLastUserId = "0";
    private String clickPid;
    private PulseFiledAdapter pulseFiledAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_look_for_people;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.look_for_sb));
        Intent intent =getIntent();
        clickPid=intent.getStringExtra("clickPid");
        setPositionRecyclerView();
    }

    /**
     * 设置职位选择
     */
    private void setPositionRecyclerView() {

        pulseFiledAdapter = new PulseFiledAdapter(LookForPeopleActivity.this);
        recyclerView.setAdapter(pulseFiledAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(LookForPeopleActivity.this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(12)));
        pulseFiledAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LookForSbBean.ItemData itemData = (LookForSbBean.ItemData) adapter.getItem(position);
                UserDetailsActivity.startAction(LookForPeopleActivity.this, (int) Objects.requireNonNull(itemData).getBasicsInfo().getUserId(), itemData.getBasicsInfo().getRealName());
            }
        });
        getRefresh();

        occupationBeens = new ArrayList<>();

        SearchPresenter registerPresenter = new SearchPresenter();
        registerPresenter.setHidden(true);
        registerPresenter.IPresenter(LookForPeopleActivity.this);

        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(LookForPeopleActivity.this);
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        positionRecyclerView.setLayoutManager(linearLayoutManager);
        positionRecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        //请求职业
        registerPresenter.getOccupationsList(new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    int indexSelect=0;
                    // 这块儿的逻辑（接受接口数据） 根据你们的实际业务调整
                    ArrayList<OccupationBeen> data = (ArrayList<OccupationBeen>) o;
                    if (data.size() > 0) {
                        if(StringUtil.isEmpty(clickPid)){
                            clickPid=data.get(0).getDbKey()+"";
                            data.get(0).setSelect(true);
                        } else {
                            for(int i=0;i<data.size();i++){
                                if((data.get(i).getDbKey()+"").equals(clickPid)){
                                    data.get(i).setSelect(true);
                                    indexSelect=i;
                                }
                            }
                        }
                    }

                    occupationBeens.addAll(data);
                    occupationAdapter = new OccupationAdapter(LookForPeopleActivity.this, occupationBeens, 3);
                    occupationAdapter.setHidden(true);
                    occupationAdapter.setPosition(indexSelect);
                    positionRecyclerView.setAdapter(occupationAdapter);
                    occupationAdapter.setItemClickListener((OccupationBeen firstPageListBean, int position) -> {
                        clickPid = firstPageListBean.getDbKey() + "";
                        mLastUserId = "0";
                        reQuestUserListByPid(clickPid, mLastUserId, true);
                        for (int i = 0; i < occupationBeens.size(); i++) {
                            if (occupationBeens.get(i).equals(firstPageListBean)) {
                                occupationBeens.get(i).setSelect(true);
                            } else {
                                occupationBeens.get(i).setSelect(false);
                            }
                        }
                        occupationAdapter.notifyDataSetChanged();
                    });
                    reQuestUserListByPid(clickPid, mLastUserId, true);
                }
            }

            @Override
            public void filed(Exception e) {
                e.printStackTrace();
            }
        });


    }


    private void reQuestUserListByPid(String pid, String lastUserId, boolean isRefresh) {
        if(isRefresh){
            DialogUtil.getInstance().showLoadingDialog(LookForPeopleActivity.this);
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                LookForSbBean lookForSbBean = GsonUtils.parseJson(resulte, LookForSbBean.class);
                if (lookForSbBean.getStatusCode() != Constant.SUCCESS) {
                    if (!TextUtils.isEmpty(lookForSbBean.getMessage())) {
                        T.showShort(lookForSbBean.getMessage());
                    }
                } else if (lookForSbBean.getData() != null) {
                    LookForSbBean.DataBean dataBean = lookForSbBean.getData();
                    if (dataBean.getBasics() != null && dataBean.getBasics().size() > 0) {
                        List<LookForSbBean.ItemData> dataSource = new ArrayList<>();
                        lookForSbBean.setDataSource(dataSource);
                        for (int i = 0; i < dataBean.getBasics().size(); i++) {
                            LookForSbBean.DataBean.BasicsInfo basicsInfo = dataBean.getBasics().get(i);
                            LookForSbBean.DataBean.StatisticsInfo statisticsInfo = dataBean.getStatistics().get(i);
                            LookForSbBean.ItemData itemData = new LookForSbBean.ItemData();
                            itemData.setBasicsInfo(basicsInfo);
                            itemData.setStatisticsInfo(statisticsInfo);
                            dataSource.add(itemData);
                            if (i == (dataBean.getBasics().size() - 1)) {
                                mLastUserId = basicsInfo.getUserId() + "";
                            }
                        }
                        pulseFiledAdapter.setInfoCount(dataBean.getInfoCount());
                        if (isRefresh) {
                            pulseFiledAdapter.setNewData(dataSource);
                        } else {
                            pulseFiledAdapter.addData(dataSource);
                        }
                    }
                }
                //Log.e("test","resulte value is "+resulte);
                refreshLayout_find.finishRefresh(true);//传入false表示刷新失败
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_find.finishRefresh(false);//传入false表示刷新失败
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, LookForPeopleActivity.this).getUserInfoByPid(pid, lastUserId);
    }




    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_find.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(LookForPeopleActivity.this)).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_find.setOnRefreshListener((refreshlayout) ->{
            mLastUserId="0";
            reQuestUserListByPid(clickPid,mLastUserId,true);
        });
        //加载
        refreshLayout_find.setOnLoadMoreListener((refreshLayout) -> {
                reQuestUserListByPid(clickPid,mLastUserId,false);
        });
    }




}
