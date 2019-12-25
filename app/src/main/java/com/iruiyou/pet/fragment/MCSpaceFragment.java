package com.iruiyou.pet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.TextActivity;
import com.iruiyou.pet.activity.WebViewNewActivity;
import com.iruiyou.pet.activity.ZhongChouActivity;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.HuoDongAdapter;
import com.iruiyou.pet.adapter.JiaMenAdapter;
import com.iruiyou.pet.adapter.ZhongchouAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.iruiyou.pet.bean.HuoDongBean;
import com.iruiyou.pet.bean.JiaMenBean;
import com.iruiyou.pet.bean.ZhongChouBean;
import com.iruiyou.pet.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 脉场空间
 */
public class MCSpaceFragment extends BaseFragment {

    /**
     * 单例模式
     *
     * @return
     */


    @BindView(R.id.recy_huodong)
    RecyclerView recy_huodong;

    @BindView(R.id.recy_zhongchou)
    RecyclerView recy_zhongchou;

    @BindView(R.id.recy_space)
    RecyclerView recy_space;



    private List<HuoDongBean.DataBean> list;
    private List<ZhongChouBean.DataBean> lists;
    private List<JiaMenBean.DataBean> listss;
    HuoDongAdapter huoDongAdapter;
    ZhongchouAdapter zhongchouAdapter;
    JiaMenAdapter jiaMenAdapter;
    private HuoDongBean bean;
    private ZhongChouBean beans;
    private JiaMenBean beanss;




    public static MCSpaceFragment getInstance() {
        return new MCSpaceFragment();
    }
    private String readUserInfo;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spaces, null);
        ButterKnife.bind(this, view);
        readUserInfo = SharePreferenceUtils.getBaseSharePreference().readUserInfo();
        recy_huodong.setHasFixedSize(true);
        recy_huodong.setNestedScrollingEnabled(false);
        recy_space.setHasFixedSize(true);
        recy_space.setNestedScrollingEnabled(false);
        recy_zhongchou.setHasFixedSize(true);
        recy_zhongchou.setNestedScrollingEnabled(false);
        return view;
    }


    @OnClick(value = {R.id.linear_buy_vip, R.id.relay_buy_partener, R.id.linear_diancan, R.id.linear_changdi, R.id.linear_jishi,
           /* R.id.linear_hulianwang,R.id.linear_it_jishu,R.id.linear_shichangyingxiao,R.id.linear_xiaoshouguanli,
            R.id.linear_peixun,R.id.linear_dianshangguanli,R.id.linear_fazhan,R.id.linear_renliziyuan,*/
           R.id.linear_huodong, R.id.text, R.id.zhongchou, R.id.kongjian,
          /*  R.id.linear_haikou,R.id.linear_sanya*/})
    public void viewOnClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.relay_buy_partener:
            case R.id.linear_buy_vip:
                boolean isPartener = false;
                if(id== R.id.relay_buy_partener){
                    isPartener = true;
                }
                String readUserInfo = SharePreferenceUtils.getBaseSharePreference().readUserInfo();
                if(StringUtil.isNotEmpty(readUserInfo)){
                    //json转成bean对象
                    HomeRefreshBean.DataBean.UserInfoBean userInfoBean = GsonUtil.GsonToBean(readUserInfo, HomeRefreshBean.DataBean.UserInfoBean.class);
                    StartActivityManager.startSeniorMemberActivity(getContext(), SharePreferenceUtils.getBaseSharePreference().readNickName(),
                            userInfoBean.getHeadImg(), SharePreferenceUtils.getBaseSharePreference().readVipLevel(),isPartener);
                }
                break;
            case R.id.linear_diancan:
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    StartActivityManager.startWebViewActivity(getActivity(), "点餐", "https://www.maichangapp.com/space/order?userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId() + "&state=app&token=" + SharePreferenceUtils.getBaseSharePreference().readToken() + "&system=Android", false, true);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.linear_changdi:
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    StartActivityManager.startWebViewActivity(getActivity(), "场地预定", "https://www.maichangapp.com/space/mall?userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId() + "&state=app&token=" + SharePreferenceUtils.getBaseSharePreference().readToken() + "&system=Android", false, true);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                   startActivity(intent);
                }
                break;
            case R.id.linear_jishi:
              //  StartActivityManager.startWebViewActivity(getActivity(),"集市","https://shop40984708.youzan.com/v2/feature/ajNgY4peUG?redirect_count=1&sf=wx_sm&is_share=1&from_uuid=1a0fd804-2518-7f00-6662-989d1892a45f&from=groupmessage",false,true);
             //   if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    Intent intent2 = new Intent(getContext(), TextActivity.class);
                    startActivity(intent2);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent);
                }

                break;
           /* case R.id.linear_hulianwang:
                StartActivityManager.startWebViewActivity(getActivity(),"互联网","https://mp.weixin.qq.com/s/TWy511WgiZ2gsCZJtP7ibg",false,true);
                break;
            case R.id.linear_it_jishu:
                StartActivityManager.startWebViewActivity(getActivity(),"IT技术","https://mp.weixin.qq.com/s/1s1TT8DGZ6gpAJvMFmF3nw",false,true);
                break;
            case R.id.linear_shichangyingxiao:
                StartActivityManager.startWebViewActivity(getActivity(),"市场营销","https://mp.weixin.qq.com/s/LNAzWpATuSfi3_1ddCz2Sw",false,true);
                break;
            case R.id.linear_xiaoshouguanli:
                StartActivityManager.startWebViewActivity(getActivity(),"销售管理","https://mp.weixin.qq.com/s/t6g6-GuhjPUHaa9jvhxL2g",false,true);
                break;
            case R.id.linear_peixun:
                StartActivityManager.startWebViewActivity(getActivity(),"店长培训","https://mp.weixin.qq.com/s/uciH3c1NHpZk6gb33zmqKw",false,true);
                break;
            case R.id.linear_dianshangguanli:
                StartActivityManager.startWebViewActivity(getActivity(),"电商管理","https://mp.weixin.qq.com/s/-AyyJhT1SLE7ScSHgq28dw",false,true);
                break;
            case R.id.linear_fazhan:
                StartActivityManager.startWebViewActivity(getActivity(),"职业发展","https://mp.weixin.qq.com/s/lng5XDrI0c8ksdfxbbBfWg",false,true);
                break;
            case R.id.linear_renliziyuan:
                StartActivityManager.startWebViewActivity(getActivity(),"人力资源","https://mp.weixin.qq.com/s/7H4OS7FVbkCKqlMqmI2KEA",false,true);
                break;*/
          /*  case R.id.linear_haikou:
                StartActivityManager.startWebViewActivity(getActivity(),"海口旗舰店","https://mp.weixin.qq.com/s/rn8V4wfGh70jlXDJnCwafQ",false,true);
                break;
            case R.id.linear_sanya:
                StartActivityManager.startWebViewActivity(getActivity(),"三亚旗舰店","https://mp.weixin.qq.com/s/usQFtRBxn4sfmKXFmcTNiA",false,true);
                break;
*/
            case R.id.linear_huodong:
              //  if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    StartActivityManager.startWebViewActivity(getActivity(), "场地预定", "https://www.maichangapp.com/space/mall?userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId() + "&state=app&token=" + SharePreferenceUtils.getBaseSharePreference().readToken() + "&system=Android", false, true);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.text:
              //  if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    Intent intent = new Intent(getContext(), TextActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                   startActivity(intent);
                }

                break;

            case R.id.zhongchou:
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    Intent intent1 = new Intent(getContext(), ZhongChouActivity.class);
                    startActivity(intent1);
                }else{
                        Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                        startActivity(intent);
                    }
                break;

            case R.id.kongjian:

                break;




        }
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        getData();
        getDatas();
        initData();
    }

    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                bean =new Gson().fromJson(resulte, HuoDongBean.class);

                list = bean.getData();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                recy_huodong.setLayoutManager(gridLayoutManager);
                huoDongAdapter = new HuoDongAdapter(list,2);
                recy_huodong.setAdapter(huoDongAdapter);
                huoDongAdapter.notifyDataSetChanged();
                setListener();

            }

            @Override
            public void onError(ApiException e) {

            }
        },(MainActivity)getContext()).getKongianHuoDong();



    }
    private void getDatas() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                beans =new Gson().fromJson(resulte, ZhongChouBean.class);

                lists = beans.getData();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
                recy_zhongchou.setLayoutManager(gridLayoutManager);
                zhongchouAdapter = new ZhongchouAdapter(lists,2);
                recy_zhongchou.setAdapter(zhongchouAdapter);
                zhongchouAdapter.notifyDataSetChanged();
                setListenerss();

            }

            @Override
            public void onError(ApiException e) {

            }
        },(MainActivity)getContext()).getZhongChou();



    }




    private void initData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                beanss =new Gson().fromJson(resulte, JiaMenBean.class);

                Log.e("123456", "onNext: "+"123456");
                listss = beanss.getData();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recy_space.setLayoutManager(linearLayoutManager);
                jiaMenAdapter  = new JiaMenAdapter(listss);
                recy_space.setAdapter(jiaMenAdapter);
                jiaMenAdapter.notifyDataSetChanged();
                setListeners();

            }

            @Override
            public void onError(ApiException e) {

            }
        },(MainActivity)getContext()).getJiaMen();



    }
    public void setListener() {

        huoDongAdapter.setOnItemClickListener(new HuoDongAdapter.OnItemClickListener() {
            @Override
            public void getclicklistener(int position) {
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    Intent intent = new Intent(getContext(), WebViewNewActivity.class);
                    intent.putExtra("title", "visibility");
                    intent.putExtra("url", list.get(position).getUrl());
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                    startActivity(intent);
                }


            }
        });

    }


    public void setListeners() {

      jiaMenAdapter.setOnItemClickListener(new JiaMenAdapter.OnItemClickListener() {
          @Override
          public void getclicklistener(int position) {
             // if (App.isLogin) {
              if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                  Intent intent = new Intent(getContext(), WebViewNewActivity.class);
                  intent.putExtra("title", list.get(position).getTitle());
                  intent.putExtra("url", listss.get(position).getUrl());
                  Log.i("ls", "getclicklistener: " + listss.get(position).getUrl());
                  startActivity(intent);
              }else{
                  Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                  startActivity(intent);
              }

          }
      });

    }


    public void setListenerss() {

        zhongchouAdapter.setOnItemClickListener(new ZhongchouAdapter.OnItemClickListener() {
            @Override
            public void getclicklistener(int position) {
               // if (App.isLogin) {
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                Intent intent = new Intent(getContext(), WebViewNewActivity.class);
                intent.putExtra("title", list.get(position).getTitle());
                intent.putExtra("url",lists.get(position).getUrl());
                startActivity(intent);
            }else{
                Intent intent = new Intent(getContext(), QuickLoginActivity.class);
                startActivity(intent);
            }

            }
        });

    }

}
