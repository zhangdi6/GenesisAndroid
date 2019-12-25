package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.EduAdapter;
import com.iruiyou.pet.adapter.WorkAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BriefRefreshBean;
import com.iruiyou.pet.utils.Constant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：简历Activity
 * 作者：jiaopeirong on 2018/8/8 22:05
 * 邮箱：chinajpr@163.com
 */
public class ResumeActivity extends BaseActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.headIv)
    ImageView headIv;
    @BindView(R.id.identityLocation)
    TextView identityLocation;
    @BindView(R.id.blockIv)
    ImageView blockIv;
    @BindView(R.id.timeLeft)
    TextView timeLeft;
    @BindView(R.id.blockTime)
    TextView blockTime;
    @BindView(R.id.locatinoLeft)
    TextView locatinoLeft;
    @BindView(R.id.blockPosition)
    TextView blockPosition;
    @BindView(R.id.workExperience)
    TextView workExperience;
    @BindView(R.id.moreEx)
    TextView moreEx;
    @BindView(R.id.addWork)
    ConstraintLayout addWork;
    @BindView(R.id.workRecyc)
    RecyclerView workRecyc;
    @BindView(R.id.eduExperience)
    TextView eduExperience;
    @BindView(R.id.moreEdu)
    TextView moreEdu;
    @BindView(R.id.addEdu)
    ConstraintLayout addEdu;
    @BindView(R.id.eduRecyc)
    RecyclerView eduRecyc;
    @BindView(R.id.basicInfoLl)
    LinearLayout basicInfoLl;
    private WorkAdapter workAdapter;
    private EduAdapter eduAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_resume;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        workAdapter = new WorkAdapter();
        eduAdapter = new EduAdapter();
        workRecyc.setAdapter(workAdapter);
        workRecyc.setLayoutManager(new LinearLayoutManager(this));
        workRecyc.setNestedScrollingEnabled(false);
        eduRecyc.setAdapter(eduAdapter);
        eduRecyc.setLayoutManager(new LinearLayoutManager(this));
        eduRecyc.setNestedScrollingEnabled(false);

        setListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void setListener() {
        workAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(WorkExpeActivity.class);
            }

        });

        eduAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(EduExpeActivity.class);
            }
        });

        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                BriefRefreshBean briefRefreshBean = GsonUtils.parseJson(resulte, BriefRefreshBean.class);
                if (briefRefreshBean.getStatusCode() == Constant.SUCCESS) {
                    name.setText(briefRefreshBean.getData().getBasicInfo().getRealName());
                    workAdapter.setNewData(briefRefreshBean.getData().getWorkInfos());
                    eduAdapter.setNewData(briefRefreshBean.getData().getEducationInfos());

                    if (briefRefreshBean.getData().getWorkInfos() == null || briefRefreshBean.getData().getWorkInfos().size() == 0) {
                        addWork.setVisibility(View.VISIBLE);
                    } else {
                        addWork.setVisibility(View.GONE);
                    }

                    if (briefRefreshBean.getData().getEducationInfos() == null || briefRefreshBean.getData().getEducationInfos().size() == 0) {
                        addEdu.setVisibility(View.VISIBLE);
                    } else {
                        addEdu.setVisibility(View.GONE);
                    }

                    blockPosition.setText(briefRefreshBean.getData().getBlockchainInfo().getPosition());
                    blockTime.setText(briefRefreshBean.getData().getBlockchainInfo().getTime());
                    identityLocation.setText(briefRefreshBean.getData().getBlockchainInfo().getIdentityLocation());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).briefRefresh();
    }

    @OnClick({R.id.addWork, R.id.addEdu, R.id.blockIv, R.id.basicInfoLl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addWork:
                startActivity(WorkExpeActivity.class);
                break;
            case R.id.addEdu:
                startActivity(EduExpeActivity.class);
                break;
            case R.id.blockIv:
                startActivity(BlockChainActivity.class);
                break;
            case R.id.basicInfoLl:
                startActivity(BasicInfoActivity.class);
                break;
        }
    }

}
