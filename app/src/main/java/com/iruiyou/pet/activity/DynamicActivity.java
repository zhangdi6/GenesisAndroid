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
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.HotListAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.DynamicBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.CancelOrOkDialog;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

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

    private int lastEssayId = 0;
    private  ArrayList<GetEssaysBean.DataBean> data;



    private HotListAdapter businessChanceAdapter;
    @Override
    public int getLayout() {
        return R.layout.activity_dynamic;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("我的动态");

        initData();

    }

    private void initData() {
        new UserTask(new HttpOnNextListener() {

            private String TAG="925921";

            @Override
            public void onNext(String resulte, String method) {


                Log.i("dynamic", "onNext: "+resulte);
                GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                data = getEssaysBean.getData();
                Log.i(TAG, "one: ");

                ArrayList<GetEssaysBean.DataBean> objects = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    String s = data.get(i).getUserId() + "";
                    if (s.equals(SharePreferenceUtils.getBaseSharePreference().readUserId())){
                        objects.add(data.get(i));
                    }
                }
                if (objects!=null && objects.size()>0){
                    recyclerView.setVisibility(View.VISIBLE);
                    llWalletNodata.setVisibility(View.GONE);
                    businessChanceAdapter = new HotListAdapter(DynamicActivity.this,objects);


                    recyclerView.setLayoutManager(new LinearLayoutManager(DynamicActivity.this));
                    recyclerView.setAdapter(businessChanceAdapter);
                    lastEssayId = data.get(data.size() - 1).get_id();
                    businessChanceAdapter.notifyDataSetChanged();

                    //删除
                    businessChanceAdapter.setOnViewClickListener(new HotListAdapter.OnViewClickListener() {
                        @Override
                        public void onViewClick(int viewId, int position) {
                            switch (viewId) {
                                case R.id.text_delete_essay:
                                    deleteEssay(objects.get(position).get_id() + "", position,objects);
                                    break;
                            }

                        }

                        private void deleteEssay(String essayId, int position, ArrayList<GetEssaysBean.DataBean> objects) {

                            if (StringUtil.isNotEmpty(essayId)) {
                                CancelOrOkDialog dialog = new CancelOrOkDialog(DynamicActivity.this, "您确定要删除这条商机吗?") {
                                    @Override
                                    public void ok() {
                                        super.ok();
                                        new UserTask(new HttpOnNextListener() {
                                            @Override
                                            public void onNext(String resulte, String method) {
                                                if (StringUtil.isNotEmpty(resulte)) {
                                                    BaseBean baseBean = GsonUtil.GsonToBean(resulte, BaseBean.class);
                                                    if (baseBean != null) {
                                                        if (baseBean.getStatusCode() == Constant.SUCCESS) {
                                                            businessChanceAdapter.data.remove(position);
                                                            businessChanceAdapter.notifyDataSetChanged();

                                                            if (businessChanceAdapter.data.size()==0){
                                                                recyclerView.setVisibility(View.GONE);
                                                                llWalletNodata.setVisibility(View.VISIBLE);
                                                            }
                                                        } else if (StringUtil.isNotEmpty(baseBean.getMessage())) {
                                                            T.showShort(baseBean.getMessage());
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onError(ApiException e) {
                                                e.printStackTrace();
                                                T.showShort(getString(R.string.rc_conversation_List_operation_failure));
                                            }
                                        }, DynamicActivity.this).deleteEssay(essayId);
                                        dismiss();
                                    }
                                };
                                dialog.show();
                            }
                        }
                    });

                }else{
                    recyclerView.setVisibility(View.GONE);
                    llWalletNodata.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "onError: "+e.getMessage());
            }
        },this).getEssays(1, 0, lastEssayId, 0);//type：0我的；1最新；2热门；3关注
    }


}
