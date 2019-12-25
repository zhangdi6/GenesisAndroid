package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.HuoDongAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.HuoDongBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TextActivity extends BaseActivity {


    @BindView(R.id.iv_back)
    ImageView back;
    @BindView(R.id.recycle_text)
    RecyclerView recycler_text;


    private List<HuoDongBean.DataBean> list;
    private HuoDongBean bean;
    private HuoDongAdapter huoDongAdapter;



    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        getData();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_text;
    }
 /*   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recycler_text.setLayoutManager(layoutManager);
        huoDongAdapter = new HuoDongAdapter();
        recycler_text.setAdapter(huoDongAdapter);
        getData();
    }
*/


    public void getData() {
/*

        OkHttpClient client =new OkHttpClient();
        Request request = new Request.Builder()
                .url(BaseApi.baseUrl+"genesis/getActivityList")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string =response.body().string();

                  bean = new Gson().fromJson(string,HuoDongBean.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
                                list = bean.getData();
                                huoDongAdapter.addData(list);
                                setListener();

                            }
                        });


                    }

        });
*/


        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                Log.i("activity", "onNext: "+resulte);
                /*bean = GsonUtils.parseJson(resulte, HuoDongBean.class);*/

                bean =new Gson().fromJson(resulte, HuoDongBean.class);

                        list = bean.getData();
              //  Toast.makeText(TextActivity.this, list.toString(), Toast.LENGTH_SHORT).show();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(TextActivity.this);
                        recycler_text.setLayoutManager(layoutManager);
                        huoDongAdapter = new HuoDongAdapter(list,1);
                        recycler_text.setAdapter(huoDongAdapter);
                        huoDongAdapter.notifyDataSetChanged();
                        setListener();


            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).getKongianHuoDong();
    }

    public void setListener() {

        huoDongAdapter.setOnItemClickListener(new HuoDongAdapter.OnItemClickListener() {
            @Override
            public void getclicklistener(int position) {
                Intent intent = new Intent(TextActivity.this, WebViewNewActivity.class);
                intent.putExtra("title","visibility");
              //  intent.putExtra("url",list.get(position).getUrl());
                intent.putExtra("url","http://www.maichangapp.com/space/activity?id="
                        +list.get(position).get_id()+"&system=Android");
                intent.putExtra("text",list.get(position).getTitle());
                intent.putExtra("img",list.get(position).getDescription());
                intent.putExtra("idd",list.get(position).get_id());
                startActivity(intent);

            }
        });

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
