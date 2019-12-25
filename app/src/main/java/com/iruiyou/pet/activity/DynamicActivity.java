package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.DynamicAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.DynamicBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.llWalletNodata)
    LinearLayout llWalletNodata;

    private DynamicBean dynamicbean;
    private List<DynamicBean.DataBean.EssaysBean> essays;



    private DynamicAdapter businessChanceAdapter;
    @Override
    public int getLayout() {
        return R.layout.activity_dynamic;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("我的动态");

    //    getGoodsList(false);

        new UserTask(new HttpOnNextListener() {

            private String TAG="925921";

            @Override
            public void onNext(String resulte, String method) {

                Log.i("dynamic", "onNext: "+resulte);
                dynamicbean = GsonUtils.parseJson(resulte, DynamicBean.class);
                //GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                Log.i(TAG, "one: ");
                essays = dynamicbean.getData().getEssays();
                ArrayList<DynamicBean.DataBean.EssaysBean> objects = new ArrayList<>();
                for (int i = 0; i < essays.size(); i++) {
                    String s = essays.get(i).getUserId() + "";
                    if (s.equals(SharePreferenceUtils.getBaseSharePreference().readUserId())){
                        objects.add(essays.get(i));
                    }
                }
                businessChanceAdapter = new DynamicAdapter(DynamicActivity.this,objects);
               // businessChanceAdapter.setOnViewClickListener(this);
                recyclerView.setAdapter(businessChanceAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(DynamicActivity.this));

                Log.i(TAG, "three: ");
                Log.i(TAG, "onNext: "+ SharePreferenceUtils.getBaseSharePreference().readUserId());
                if (dynamicbean.getData().getEssays().size()==0){

                    Log.i(TAG, "two: ");
                    llWalletNodata.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);

                }else{
                    llWalletNodata.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "onError: "+e.getMessage());
            }
        },this).getminedynamic(1,1, SharePreferenceUtils.getBaseSharePreference().readUserId());
    }




   /* private void getGoodsList(boolean isRefresh) {
       *//* if (isRefresh) {
            mRlv.setPageNumber(1);
        }

*//*
        if (isRefresh) {
            lastEssayId = 0;
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("dddd",resulte);
                if (StringUtil.isNotEmpty(resulte)) {
                    GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                    if (Constant.SUCCESS == getEssaysBean.getStatusCode()) {
                        if (getEssaysBean.getData() != null) {
                            if (isRefresh) {
                                data.clear();
                            }
                            for (int i = 0; i < getEssaysBean.getData().size(); i++) {
                                if (!getEssaysBean.getData().get(i).isHead()){
                                    data.add(getEssaysBean.getData().get(i));
                                }
                            }
                            businessChanceAdapter.notifyDataSetChanged();
                            if (isRefresh ) {
                            *//*    GetEssaysBean.DataBean bean = new GetEssaysBean.DataBean();
                                bean.setHead(true);
                                data.add(0, bean);*//*
                            }
                            lastEssayId = data.get(data.size() - 1).get_id();
                            businessChanceAdapter.notifyDataSetChanged();
                           *//* if (!(mRlv.getAdapter() != null && (mRlv.getAdapter() instanceof HotListAdapter))) {
                                mRlv.setAdapter(businessChanceAdapter);
                                businessChanceAdapter.notifyDataSetChanged();
                            }
                            mRlv.setPageNumber(mRlv.getPageNumber() + 1);*//*
                            businessChanceAdapter.onItemFabuClick(new HotListAdapter.onItemFabuClick() {
                                @Override
                                public void onItemFabu(int position , TextView view) {
                                    if (data.get(position).getIs_fabulous()==1){
                                        T.showShort("已赞");
                                    }else{
                                        new UserTask(new HttpOnNextListener() {
                                            @Override
                                            public void onNext(String resulte, String method) {
                                                view.setText(data.get(position).getTotal_fabulous()+1+"");
                                            }

                                            @Override
                                            public void onError(ApiException e) {

                                            }
                                        }, (RxAppCompatActivity) getActivity()).getdianzan(data.get(position).get_id()+"",0,1,
                                                SharePreferenceUtils.getBaseSharePreference().readUserId());
                                    }

                                }
                            });
                        }

                    } else if (StringUtil.isNotEmpty(getEssaysBean.getMessage())) {
                        T.showShort(getEssaysBean.getMessage());
                    }
                }


            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());

            }
        }, (RxAppCompatActivity) getActivity()).getEssays(1, 0, lastEssayId, 0);//type：0我的；1最新；2热门；3关注
*/
 //   }
}
