package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.adapter.home_adapter.RenqiAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.bean.ShoppingBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingActivity extends BaseActivity {


    @BindView(R.id.img_one)
    ImageView img_one;
    @BindView(R.id.img_two)
    ImageView img_two;
    @BindView(R.id.recycler_shopping)
    RecyclerView recyclerView;


    private List<OpportunitiesGoodsBean.Item> listgoods;
    private OpportunitiesGoodsBean beangoods;//脉乐购



    @Override
    public int getLayout() {
        return R.layout.activity_shopping;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initData();
        initDatas();
    }

    private void initDatas() {



            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    Log.i("tag", "onNext: "+resulte);
                    beangoods =new Gson().fromJson(resulte, OpportunitiesGoodsBean.class);

                    /*bean =new Gson().fromJson(resulte, OpportunitiesGoodsBean.DataBean.class);*/

                    listgoods = beangoods.getData().getResponse().getItems();

                    GridLayoutManager ridLayoutManager = new GridLayoutManager(getApplication(), 2);
                    recyclerView.setLayoutManager(ridLayoutManager);
                    RenqiAdapter renqiAdapter = new RenqiAdapter(listgoods);
                    recyclerView.setAdapter(renqiAdapter);
                    renqiAdapter.notifyDataSetChanged();


                    //点击事件设置在这里

                    renqiAdapter.setOnItemClickListener(new RenqiAdapter.OnItemClickListener() {
                        @Override
                        public void getclicklistener(int position) {

                            //  if(App.isLogin){
                            if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                                Intent intent = new Intent(getApplication(), WebViewNewActivity.class);
                                intent.putExtra("title", listgoods.get(position).getTitle());
                                intent.putExtra("url", listgoods.get(position).getDetail_url());
                                getApplication().startActivity(intent);

                            }else{
                                Intent intent = new Intent(getApplication(), QuickLoginActivity.class);
                                getApplication().startActivity(intent);
                            }


                        }
                    });

                    Log.e("test","genesisCuv resulte is "+resulte);
                }

                @Override
                public void onError(ApiException e) {
                    Log.e("MCOpportunities",e.getMessage());
                }
            },this).getYZGoodsListV3();

    }

    private void initData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                Log.i("shopping", "onNext: "+resulte);
                ShoppingBean shoppingBean = GsonUtils.parseJson(resulte, ShoppingBean.class);
                ShoppingBean.DataBean data = shoppingBean.getData();
                List<ShoppingBean.BannerHead> banner = data.getBanner();
                List<ShoppingBean.OtherHead> other = data.getOther();
                String description = banner.get(0).getDescription();
                String description1 = other.get(0).getDescription();
                Log.i("one", "onNext: "+description);
                Log.i("two", "onNext: "+description1);
                Glide.with(getApplication()).load(description).into(img_one);
                Glide.with(getApplication()).load(description1).into(img_two);

            }

            @Override
            public void onError(ApiException e) {
                Log.i("123", "onError: "+e.getMessage());

            }
        },this).getshopping();
    }
}
