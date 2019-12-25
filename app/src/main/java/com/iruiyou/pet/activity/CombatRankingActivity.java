package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.CombatRankingAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CombatRankingBean;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import org.apache.commons.lang3.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述:算力排行榜
 * 创建日期:2018/9/6 on 17:22
 * 作者:JiaoPeiRong
 */
public class CombatRankingActivity extends BaseActivity {
    @BindView(R.id.mycombat)
    TextView mycombat;
    @BindView(R.id.rangking)
    TextView rangking;
    private RecyclerView recyclerView;
    private CombatRankingAdapter adapter;
    private LinearLayout llCombatNodata;
    private CombatRankingBean combatRankingBean;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean move = false;
    private int mIndex = 0;

    @Override
    public int getLayout() {
        return R.layout.activity_combat_ranking;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        recyclerView = findViewById(R.id.recyclerView);
//        llCombatNodata = (LinearLayout) findViewById(R.id.llCombatNodata);
        init();
        getData();
    }

    private void init() {
        setTitle(getResources().getString(R.string.CombatRanking));
        String stringExtra = getIntent().getStringExtra(TagConstants.Combat);
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_combat_head, null, false);
        ButterKnife.bind(this, inflate);
        if (!StringUtils.isBlank(stringExtra)) {
            mycombat.setText(stringExtra);
        }
        adapter = new CombatRankingAdapter();
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        adapter.setHeaderView(inflate);
        recyclerView.setAdapter(adapter);
        //监听事件
        adapter.setOnItemClickListener(new CombatRankingAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                position = position-1;
                Bundle bundle = new Bundle();
                bundle.putInt("userid",combatRankingBean.getData().get(position).get_id());
                bundle.putString("realName",combatRankingBean.getData().get(position).getRealName());
                Intent intent = new Intent(CombatRankingActivity.this, UserDetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                combatRankingBean = GsonUtils.parseJson(resulte, CombatRankingBean.class);
//                if(combatRankingBean.getData().size()>0){
//                    recyclerView.setVisibility(View.VISIBLE);
//                    llCombatNodata.setVisibility(View.GONE);
//                }else {
//                    recyclerView.setVisibility(View.GONE);
//                    llCombatNodata.setVisibility(View.VISIBLE);
//                }
                adapter.setNewData(combatRankingBean.getData());
                int myRanking = -1;
                for (int i = 0; i < combatRankingBean.getData().size(); i++) {
                    if (SharePreferenceUtils.getBaseSharePreference().readUserId().equals(combatRankingBean.getData().get(i).get_id()+"")) {
                        myRanking = i + 1;
                        rangking.setText(myRanking + "");
                    }
                }

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).combatCharts();
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_CombatRankingActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_CombatRankingActivity);
        MobclickAgent.onPause(this);
    }


    private void move(int n){
        if (n<0 || n>=adapter.getItemCount() ){
            return;
        }
        mIndex = n;
        recyclerView.stopScroll();
        smoothMoveToPosition(n);
    }
    private void smoothMoveToPosition(int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            recyclerView.smoothScrollToPosition(n);
        }else if ( n <= lastItem ){
            int top = recyclerView.getChildAt(n - firstItem).getTop();
            recyclerView.smoothScrollBy(0, top);
        }else{
            recyclerView.smoothScrollToPosition(n);
            move = true;
        }

    }

    private void moveToPosition(int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem ){
            recyclerView.scrollToPosition(n);
        }else if ( n <= lastItem ){
            int top = recyclerView.getChildAt(n - firstItem).getTop();
            recyclerView.scrollBy(0, top);
        }else{
            recyclerView.scrollToPosition(n);
            move = true;
        }

    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE){
                move = false;
                int n = mIndex - mLinearLayoutManager.findFirstVisibleItemPosition();
                if ( 0 <= n && n < recyclerView.getChildCount()){
                    int top = recyclerView.getChildAt(n).getTop();
                    recyclerView.smoothScrollBy(0, top);
                }

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move){
                move = false;
                int n = mIndex - mLinearLayoutManager.findFirstVisibleItemPosition();
                if ( 0 <= n && n < recyclerView.getChildCount()){
                    int top = recyclerView.getChildAt(n).getTop();
                    recyclerView.scrollBy(0, top);
                }
            }
        }
    }
}
