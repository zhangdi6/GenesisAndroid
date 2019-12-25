package com.iruiyou.pet.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.MyContanctAdapter;
import com.iruiyou.pet.adapter.RecommendInterpersonalAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.RecommendInterpersonalBean;
import com.iruiyou.pet.utils.ClearEditText;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

public class RelationshipsFragment extends BaseFragment {

    @BindView(R.id.positionRecyclerView)
    RecyclerView positionRecyclerView;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView_search;

    @BindView(R.id.etSearch)
    ClearEditText etSearch;

    @BindView(R.id.text_find_people)
    TextView text_find_people;

    @BindView(R.id.mine_people)
    TextView mine_people;

    @BindView(R.id.refreshLayout_find)
    SmartRefreshLayout refreshLayout_find;

    @BindView(R.id.relay_search)
    RelativeLayout relaySearch;

    @BindView(R.id.recycle_mine)
    RecyclerView friendRecyclerView;

    @BindView(R.id.linear_position)
    LinearLayout linearPosition;

    private MyContanctAdapter myContanctAdapter;
    private ArrayList<OccupationBeen> occupationBeens;
    private OccupationAdapter occupationAdapter;
    private RecommendInterpersonalAdapter relationShipSearchAdapter;
    private boolean searchByServer = true;
    private String clickPid;

    //加载控制器
    private SearchPresenter registerPresenter;
    private int pageIndex = 1;
    private CheckFriendsBean checkFriendsBean;

    /**
     * 单例模式
     *
     * @return
     */
    public static RelationshipsFragment getInstance() {
        return new RelationshipsFragment();
    }


    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        setPositionRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_relationships, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(value = {R.id.text_find_people, R.id.mine_people})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.text_find_people:
                searchByServer=true;
                text_find_people.setAlpha(1);
                mine_people.setAlpha(0.5f);
                relaySearch.setVisibility(View.GONE);
                friendRecyclerView.setVisibility(View.GONE);
                refreshLayout_find.setVisibility(View.VISIBLE);
                linearPosition.setVisibility(View.VISIBLE);
                break;
            case R.id.mine_people:
                searchByServer=false;
                mine_people.setAlpha(1);
                text_find_people.setAlpha(0.5f);
                relaySearch.setVisibility(View.VISIBLE);
                friendRecyclerView.setVisibility(View.VISIBLE);
                refreshLayout_find.setVisibility(View.GONE);
                linearPosition.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 设置职位选择
     */
    private void setPositionRecyclerView() {
        registerPresenter = new SearchPresenter();
        registerPresenter.IPresenter(getActivity());
        occupationBeens = new ArrayList<>();
        myContanctAdapter = new MyContanctAdapter();
        getRefresh();
        friendRecyclerView.setLayoutManager(new MyLinearLayoutManager(getContext()));
//        friendRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
//                Utils.dip2px(getContext(), 5), getResources().getColor(R.color.bg_tab)));
        friendRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        friendRecyclerView.setAdapter(myContanctAdapter);
        myContanctAdapter.setOnItemClickListener((adapter, view, position) -> {
            CheckFriendsBean.DataBean  dataBean= (CheckFriendsBean.DataBean) adapter.getData().get(position);
            StartActivityManager.startUserDetailsActivity(getActivity(),dataBean.getBasicInfoA().getUserId(),dataBean.getBasicInfoA().getRealName());
        });
        requestMyFriends();
        //设置布局管理器
        MyLinearLayoutManager searchLinearManager = new MyLinearLayoutManager(getActivity());
        searchLinearManager.setOrientation(MyLinearLayoutManager.VERTICAL);
        relationShipSearchAdapter = new RecommendInterpersonalAdapter(getContext());
        recyclerView_search.setAdapter(relationShipSearchAdapter);
        recyclerView_search.setLayoutManager(searchLinearManager);
        relationShipSearchAdapter.setOnItemClickListener((adapter,view,position) ->{
            RecommendInterpersonalBean.ItemBasicsInfo itemBasicsInfo = (RecommendInterpersonalBean.ItemBasicsInfo) adapter.getData().get(position);
            StartActivityManager.startUserDetailsActivity(getActivity(),Integer.valueOf(itemBasicsInfo.getUserId()+"").intValue(),itemBasicsInfo.getRealName());
        });
//        recyclerView_search.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
//                Utils.dip2px(getContext(), 5), getResources().getColor(R.color.bg_tab)));
        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
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
                    occupationAdapter = new OccupationAdapter(getContext(), occupationBeens, 3);
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


    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_find.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_find.setOnRefreshListener((refreshlayout) ->{
            pageIndex=1;
            reQuestUserListByPid(clickPid,pageIndex,true);
        });
        //加载
        refreshLayout_find.setOnLoadMoreListener((refreshLayout) -> {
            pageIndex=pageIndex+1;
            reQuestUserListByPid(clickPid,pageIndex,false);
        });
    }



    private void reQuestUserListByPid(String pid,final int pageParam, boolean isRefresh) {
        if(isRefresh){
            DialogUtil.getInstance().showLoadingDialog(getContext());
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
        }, ((RxAppCompatActivity)getActivity())).getRecommendByTag(pid, pageParam);
    }



    /**
     * 请求我的好友数据
     */
    private void requestMyFriends() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                checkFriendsBean = GsonUtils.parseJson(resulte, CheckFriendsBean.class);
                if (checkFriendsBean != null) {
                    if (checkFriendsBean.getStatusCode() == Constant.SUCCESS) {
                        //上传头像和name
//                RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
//                    @Override
//                    public UserInfo getUserInfo(String id) {
//                        return new UserInfo(checkFriendsBean.getData().get(0).getUserId()+"",homeRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(homeRefreshBean.getData().getBasicInfo().getHeadImg()));
//                    }
//                }, true);
                        List<CheckFriendsBean.DataBean> data = checkFriendsBean.getData();
                        for (int i = 0; i < checkFriendsBean.getData().size(); i++) {
                            //刷新用户头像到融云上
                            String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
                            if (readUserId.equals(String.valueOf(data.get(i).getUserIdA()))) {//自己

                            } else {
//                            searchList.clear();
//                            searchList.add(new SearchBean(data.get(i).getBasicInfoA().getUserId(),0,"",data.get(i).getBasicInfoA().getCompany(),data.get(i).getBasicInfoA().getPosition(),data.get(i).getBasicInfoA().getHeadImg(),data.get(i).getBasicInfoA().getRealName(),data.get(i).getBasicInfoA().getProfessionalIdentity()));
                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + data.get(i).getBasicInfoA().getUserId(), data.get(i).getBasicInfoA().getRealName(), Uri.parse(BaseApi.baseUrlNoApi + data.get(i).getBasicInfoA().getHeadImg())));//刷新同步头像昵称到融云
                            }
                        }
//                        tvMyFriends.setText(getString(R.string.my_friend) + "(" + checkFriendsBean.getData().size() + ")");
                        if (!TextUtils.isEmpty(checkFriendsBean.getMessage())) {
                            T.showShort(checkFriendsBean.getMessage());
                        }
                        myContanctAdapter.setNewData(checkFriendsBean.getData());
                        ((MainActivity)getActivity()).setFriendsCount(checkFriendsBean.getData().size());
                    }
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ((RxAppCompatActivity)getActivity())).getFriends();

    }





}
