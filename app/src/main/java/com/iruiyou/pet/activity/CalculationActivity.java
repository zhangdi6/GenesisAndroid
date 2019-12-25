package com.iruiyou.pet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.CalculationAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.HarvestBean2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：我的算力
 * 作者：jiaopeirong on 2018/8/7 23:19
 * 邮箱：chinajpr@163.com
 */
public class CalculationActivity extends BaseActivity {

    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.walletTv)
    TextView walletTv;
    private CalculationAdapter calculationAdapter;
    private HarvestBean2 harvestBean;
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_my_combat;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.myCombat));
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
        context = CalculationActivity.this;
        calculationAdapter = new CalculationAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(calculationAdapter);

        getData();

//        calculationAdapter.setOnItemClickListener(new CalculationAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("userid",harvestBean.getData().getCombatList().get(position).getUserId());
//                bundle.putString("realName",harvestBean.getData().getUserInfo().getRealName());
//                Intent intent = new Intent(CalculationActivity.this, UserDetailsActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                harvestBean = GsonUtils.parseJson(resulte, HarvestBean2.class);
                calculationAdapter.setNewData(harvestBean.getData().getCombatList());
                walletTv.setText(harvestBean.getData().getUserInfo().getCombat());
            }

            @Override
            public void onError(ApiException e) {

            }
        }, this).combatList();
    }

}
