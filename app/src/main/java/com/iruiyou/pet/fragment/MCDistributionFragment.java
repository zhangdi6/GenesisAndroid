package com.iruiyou.pet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.OpportunitiesGoodsAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.GlobalLog;
import com.iruiyou.pet.utils.RecycleViewDivider;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.utils.TypeOnClick;
import com.iruiyou.pet.view.PageNumberRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分销
 */
public class MCDistributionFragment extends BaseFragment implements TypeOnClick {


    @BindView(R.id.image_wait)
    ImageView image_wait;

    @BindView(R.id.recyclerView)
    PageNumberRecyclerView maxRecyclerView;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;


    @BindView(R.id.text_chat)
    TextView text_chat;

    @BindView(R.id.mine_interaction)
    TextView mine_interaction;


    @BindView(R.id.view_top)
    LinearLayout view_top;

    private OpportunitiesGoodsAdapter opportunitiesGoodsAdapter;

    private HashMap<Integer,String> tagIdsMap;

    private String tagIds="106241090";//默认为食品

    /**
     * 单例模式
     *
     * @return
     */
    public static MCDistributionFragment getInstance() {
        return new MCDistributionFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opportunities, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        view_top.setVisibility(View.VISIBLE);
        tagIdsMap=new HashMap<>();
        tagIdsMap.put(R.id.linear_food,"106241090");
        tagIdsMap.put(R.id.linear_all_goods,"106241647");
        tagIdsMap.put(R.id.linear_digital,"106241671");
        tagIdsMap.put(R.id.linear_nursing,"106241502");
        tagIdsMap.put(R.id.linear_perinatal,"106241674");
        opportunitiesGoodsAdapter = new OpportunitiesGoodsAdapter(getContext());
        opportunitiesGoodsAdapter.setTypeOnClick(this);
        maxRecyclerView.setAdapter(opportunitiesGoodsAdapter);
        maxRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置 Footer 为 经典式样
        refreshLayout.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getGoodsList(false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getGoodsList(true);
            }
        });
        getGoodsList(true);
        maxRecyclerView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(getContext(), 4), getResources().getColor(R.color._f1f1f1)));
        opportunitiesGoodsAdapter.setOnItemOnClickListener(new OpportunitiesGoodsAdapter.OnItemOnClickListener() {
            @Override
            public void onItemClick(int position, OpportunitiesGoodsBean.Item item) {
                StartActivityManager.startGoodsDetail(getActivity(), item.getItem_id());
            }
        });
    }




    @OnClick(value = {R.id.text_chat, R.id.mine_interaction})
    public void viewOnClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.text_chat:
                text_chat.setAlpha(1);
                mine_interaction.setAlpha(0.5f);
                refreshLayout.setVisibility(View.VISIBLE);
                image_wait.setVisibility(View.GONE);
                break;
            case R.id.mine_interaction:
                text_chat.setAlpha(0.5f);
                mine_interaction.setAlpha(1);
                refreshLayout.setVisibility(View.GONE);
                image_wait.setVisibility(View.VISIBLE);
                break;
        }
    }


    /**
     * 获取商品列表
     */
    private void getGoodsList(boolean isRefresh) {
        if (isRefresh) {
            maxRecyclerView.setPageNumber(0);
        }

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                GlobalLog.e("test", "getYZGoodsList resulte is :" + resulte);
                if (StringUtil.isNotEmpty(resulte)) {
                    OpportunitiesGoodsBean bean = GsonUtils.parseJson(resulte, OpportunitiesGoodsBean.class);
                    if (bean != null) {
                        if (Constant.SUCCESS == bean.getStatusCode()) {
                            List<OpportunitiesGoodsBean.Item> data = bean.getData().getResponse().getItems();
                            if (isRefresh) {
                                OpportunitiesGoodsBean.Item head = new OpportunitiesGoodsBean.Item();
                                head.setHead(true);
                                data.add(0, head);
                                opportunitiesGoodsAdapter.setNewData(data);
                            } else {
                                opportunitiesGoodsAdapter.addData(data);
                            }
                            if (isRefresh) {
                                refreshLayout.finishRefresh(true);
                            } else {
                                refreshLayout.finishLoadMore(true);
                            }
                            if (!(maxRecyclerView.getAdapter() != null && (maxRecyclerView.getAdapter() instanceof OpportunitiesGoodsAdapter))) {
                                maxRecyclerView.setAdapter(opportunitiesGoodsAdapter);
                            }
                            maxRecyclerView.setPageNumber(maxRecyclerView.getPageNumber() + 1);
                        } else if (StringUtil.isNotEmpty(bean.getMessage())) {
                            T.showShort(bean.getMessage());
                            if (isRefresh) {
                                refreshLayout.finishRefresh(false);
                            } else {
                                refreshLayout.finishLoadMore(false);
                            }
                        }
                    }
                }

            }

            @Override
            public void onError(ApiException e) {
                if (isRefresh) {
                    refreshLayout.finishRefresh(false);
                } else {
                    refreshLayout.finishLoadMore(false);
                }
            }
        }, (RxAppCompatActivity) getActivity()).getYZGoodsListV2(maxRecyclerView.getPageNumber() + 1,tagIds);
    }



    @Override
    public void typeOnClick(int id) {
        tagIds=tagIdsMap.get(id);
        getGoodsList(true);
    }
}
