package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.iruiyou.common.http.task.UserTask;
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
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.RecommendInterpersonalAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.RecommendInterpersonalBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.RecycleViewDivider;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 人脉界面
 */
public class NetWorkActivity extends BaseActivity {
    private OccupationAdapter occupationAdapter;
    private SearchPresenter registerPresenter;
    private ArrayList<OccupationBeen> occupationBeens;
    private String clickPid;
    private int pageIndex = 1;
    private boolean searchByServer = true;
    private RecommendInterpersonalAdapter relationShipSearchAdapter;

//    private MyContanctAdapter myContanctAdapter;

    @BindView(R.id.refreshLayout_find)
    SmartRefreshLayout refreshLayout_find;

    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView_search;


    @Override
    public int getLayout() {
        return R.layout.activity_network;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setPositionRecyclerView();
        setTitle("人脉");
    }


    /**
     * 设置职位选择
     */
    private void setPositionRecyclerView() {
        registerPresenter = new SearchPresenter();
        registerPresenter.IPresenter(NetWorkActivity.this);
        occupationBeens = new ArrayList<>();
        //设置布局管理器
        MyLinearLayoutManager searchLinearManager = new MyLinearLayoutManager(NetWorkActivity.this);
        searchLinearManager.setOrientation(MyLinearLayoutManager.VERTICAL);
        relationShipSearchAdapter = new RecommendInterpersonalAdapter(NetWorkActivity.this);
        recyclerView_search.setAdapter(relationShipSearchAdapter);
        recyclerView_search.setLayoutManager(searchLinearManager);
        relationShipSearchAdapter.setOnItemClickListener((adapter,view,position) ->{
            RecommendInterpersonalBean.ItemBasicsInfo itemBasicsInfo = (RecommendInterpersonalBean.ItemBasicsInfo) adapter.getData().get(position);
            StartActivityManager.startUserDetailsActivity(NetWorkActivity.this,Integer.valueOf(itemBasicsInfo.getUserId()+"").intValue(),itemBasicsInfo.getRealName());
        });
        recyclerView_search.addItemDecoration(new RecycleViewDivider(NetWorkActivity.this, LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(NetWorkActivity.this, 5), getResources().getColor(R.color.bg_tab)));
        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(NetWorkActivity.this);
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
                    occupationAdapter = new OccupationAdapter(NetWorkActivity.this, occupationBeens, 3);
                    occupationAdapter.setHidden(true);
                    occupationAdapter.setPosition(indexSelect);
                    positionRecyclerView.setAdapter(occupationAdapter);
                    occupationAdapter.setItemClickListener((OccupationBeen firstPageListBean, int position) -> {

                        if(searchByServer){
                            clickPid = firstPageListBean.getDbKey() + "";
                            pageIndex=1;
                            reQuestUserListByPid(clickPid, pageIndex, true);
                        }else{

                        }

                        for (int i = 0; i < occupationBeens.size(); i++) {
                            if (occupationBeens.get(i).equals(firstPageListBean)) {
                                occupationBeens.get(i).setSelect(true);
                            } else {
                                occupationBeens.get(i).setSelect(false);
                            }
                        }
                        occupationAdapter.notifyDataSetChanged();
                    });
                    reQuestUserListByPid(clickPid, pageIndex, true);
                }
            }

            @Override
            public void filed(Exception e) {
                e.printStackTrace();
            }
        });

    }





    private void reQuestUserListByPid(String pid,final int pageParam, boolean isRefresh) {
        if(isRefresh){
            DialogUtil.getInstance().showLoadingDialog(NetWorkActivity.this);
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                RecommendInterpersonalBean lookForSbBean = GsonUtils.parseJson(resulte, RecommendInterpersonalBean.class);
                if (lookForSbBean.getStatusCode() != Constant.SUCCESS) {
                    if (!TextUtils.isEmpty(lookForSbBean.getMessage())) {
                        T.showShort(lookForSbBean.getMessage());
                    }
                    if(pageIndex>1){
                        pageIndex --;
                    }
                } else if (lookForSbBean.getData() != null) {
                    RecommendInterpersonalBean.DataBean dataBean = lookForSbBean.getData();
                    if (dataBean.getDocs() != null && dataBean.getDocs().size() > 0) {
                        if (isRefresh) {
                            relationShipSearchAdapter.setNewData(dataBean.getDocs());
                        } else {
                            relationShipSearchAdapter.addData(dataBean.getDocs());
                        }
                    }
                }
                if(isRefresh){
                    refreshLayout_find.finishRefresh(true);//传入false表示刷新失败
                    DialogUtil.getInstance().closeLoadingDialog();
                }else {
                    refreshLayout_find.finishLoadMore(true);
                }
                //Log.e("test","resulte value is "+resulte);

            }

            @Override
            public void onError(ApiException e) {
                if(pageIndex>1){
                    pageIndex --;
                }
                if(isRefresh){
                    refreshLayout_find.finishRefresh(true);//传入false表示刷新失败
                    DialogUtil.getInstance().closeLoadingDialog();
                }else {
                    refreshLayout_find.finishLoadMore(true);
                }
            }
        }, (NetWorkActivity.this)).getRecommendByTag(pid, pageParam);
    }




}
