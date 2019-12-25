package com.iruiyou.pet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.UserEduAdapter;
import com.iruiyou.pet.adapter.UserWorkAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BriefRefreshBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.utils.Constant;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 用户资料详情2
 * 作者：sgf
 */
public class UserDetails2Activity extends BaseActivity {


    public static void startActio(Activity activity,String realName,int userid)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("userid",userid);
        bundle.putString("realName",realName);
        Intent intent = new Intent(activity, UserDetails2Activity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @BindView(R.id.ll_title_left_view)
    LinearLayout ll_title_left_view;

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.headIv)
    RoundedImageView headIv;
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
    @BindView(R.id.educationTitle)
    TextView educationTitle;
    @BindView(R.id.companyTitle)
    TextView companyTitle;
    @BindView(R.id.companyAddr)
    TextView companyAddr;
    @BindView(R.id.companyStyle)
    TextView companyStyle;
    private UserWorkAdapter workAdapter;
    private UserEduAdapter eduAdapter;
    private BriefRefreshBean briefRefreshBean;
    private Context context;
    private int userid;

    @Override
    public int getLayout() {
        return R.layout.activity_user_details2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initDta();
    }

    private void initDta() {
        context = UserDetails2Activity.this;

        userid = getIntent().getIntExtra("userid", 0);
        String realName = getIntent().getStringExtra("realName");
        setTitle(realName + getString(R.string.user_details));
        workAdapter = new UserWorkAdapter();
        eduAdapter = new UserEduAdapter();
        workRecyc.setAdapter(workAdapter);
        workRecyc.setLayoutManager(new LinearLayoutManager(context));
        workRecyc.setNestedScrollingEnabled(false);
        eduRecyc.setAdapter(eduAdapter);
        eduRecyc.setLayoutManager(new LinearLayoutManager(context));
        eduRecyc.setNestedScrollingEnabled(false);
        if(String.valueOf(userid).equals(SharePreferenceUtils.getBaseSharePreference().readUserId())){
            setTitle(getString(R.string.myResume));
        }
        getData();
        setListener();
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                briefRefreshBean = GsonUtils.parseJson(resulte, BriefRefreshBean.class);
                if (briefRefreshBean.getStatusCode() == Constant.SUCCESS) {
                    if (briefRefreshBean.getData().getBasicInfo() != null) {
                        String company = briefRefreshBean.getData().getBasicInfo().getCompany();
                        String positionbrief = briefRefreshBean.getData().getBasicInfo().getPosition();
                        name.setText(briefRefreshBean.getData().getBasicInfo().getRealName());
//                        educationTitle.setText(CodeUtils.getInstance().getEducationByCode(briefRefreshBean.getData().getBasicInfo().getEducation()));
                        if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionbrief)) {
                            companyTitle.setText(positionbrief);
                        } else if (TextUtils.isEmpty(positionbrief) && !TextUtils.isEmpty(company)) {
                            companyTitle.setText(company);
                        } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionbrief)) {
                            companyTitle.setText(company + Constant.LARGE_SPACE + positionbrief);
                        }
                        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + briefRefreshBean.getData().getBasicInfo().getHeadImg(), headIv);
                    }

                    workAdapter.setNewData(briefRefreshBean.getData().getWorkInfos());
                    eduAdapter.setNewData(briefRefreshBean.getData().getEducationInfos());

                    if (briefRefreshBean.getData().getWorkInfos() == null || briefRefreshBean.getData().getWorkInfos().size() == 0) {
                        addWork.setVisibility(View.VISIBLE);
                        workRecyc.setVisibility(View.GONE);
                    } else {
                        addWork.setVisibility(View.GONE);
                        workRecyc.setVisibility(View.VISIBLE);
                    }

                    if (briefRefreshBean.getData().getEducationInfos() == null || briefRefreshBean.getData().getEducationInfos().size() == 0) {
                        addEdu.setVisibility(View.VISIBLE);
                        eduRecyc.setVisibility(View.GONE);
                    } else {
                        addEdu.setVisibility(View.GONE);
                        eduRecyc.setVisibility(View.VISIBLE);
                    }

                    if (briefRefreshBean.getData().getBlockchainInfo() != null) {
                        ConfigBean configBean = App.getConfigBean();
                        if(configBean!=null)
                        {
                            List<LangChildBean.DbSelectInputStandardsBean.BlockchainIdentityLocationsBean> blockchainIdentityLocations =
                                    configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getBlockchainIdentityLocations();
                            for (int i = 0; i < blockchainIdentityLocations.size(); i++) {
                                //                                identityLocation.setText(blockchainIdentityLocations.get(i).getLangValue());
                            }
                        }
                    }

                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).briefRefresh2(userid);
    }

    @OnClick({R.id.ll_title_left_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
        }
    }

    private void setListener() {
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BriefRefreshBean.DataBean.WorkInfosBean workInfosBean = (BriefRefreshBean.DataBean.WorkInfosBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.WORK, workInfosBean);
                bundle.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent = new Intent(context, WorkExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        eduAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BriefRefreshBean.DataBean.EducationInfosBean educationInfosBean = (BriefRefreshBean.DataBean.EducationInfosBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.EDU, educationInfosBean);
                bundle.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent = new Intent(context, EduExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
