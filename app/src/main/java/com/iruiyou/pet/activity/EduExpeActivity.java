package com.iruiyou.pet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.BriefRefreshBean;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：教育经历
 * 作者：jiaopeirong on 2018/8/12 16:34
 * 邮箱：chinajpr@163.com
 */
public class EduExpeActivity extends BaseActivity {
    @BindView(R.id.tv_save)
    TextView tvSave;
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
    @BindView(R.id.scrollTv)
    TextView schoolTv;
    @BindView(R.id.scrollLl)
    LinearLayout scrollLl;
    @BindView(R.id.educationTv)
    TextView educationTv;
    @BindView(R.id.educationLl)
    LinearLayout educationLl;
    @BindView(R.id.majorTv)
    TextView majorTv;
    @BindView(R.id.majorLl)
    LinearLayout majorLl;
    @BindView(R.id.durationTv)
    TextView durationTv;
    @BindView(R.id.durationLl)
    LinearLayout durationLl;
    @BindView(R.id.schoolLeft)
    TextView schoolLeft;
    @BindView(R.id.educationLeft)
    TextView educationLeft;
    @BindView(R.id.majorLeft)
    TextView majorLeft;
    @BindView(R.id.durationLeft)
    TextView durationLeft;
    @BindView(R.id.tvExperience)
    TextView tvExperience;
    @BindView(R.id.tvExperienceLeft)
    TextView tvExperienceLeft;
    @BindView(R.id.llExperience)
    LinearLayout llExperience;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    private String university;
    private String major;
    private String duration;
    private String education;
    private BriefRefreshBean.DataBean.EducationInfosBean educationInfosBean;
    private boolean isUpdate;
    private String educationStr;
    private int educationKey;
    private String manStr;
    private int dialogWhich;
    private int manKey;
    private String experience;


    @Override
    public int getLayout() {
        return R.layout.activity_edu_expe;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.educationalExperience));
        init();
//        ImmersionBar.with(this)
//                .statusBarColor(R.color.colorPrimary)
//                .fitsSystemWindows(true)
//                .init();
    }

    private void init() {

        String editors = getIntent().getStringExtra(Constant.EDITORS);
        if(editors.equals(Constant.SWITCH_NO)){
            //设置TextView中的图片
//            Drawable rightDrawable = getResources().getDrawable(R.drawable.shape_white);
//            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
//            durationTv.setCompoundDrawables(null, null, rightDrawable, null);
            setTextViewImage(R.drawable.shape_white,schoolTv);
            setTextViewImage(R.drawable.shape_white,educationTv);
            setTextViewImage(R.drawable.shape_white,majorTv);
            setTextViewImage(R.drawable.shape_white,durationTv);
            setTextViewImage(R.drawable.shape_white,tvExperience);
            hideRightBg();
            hideRight();
            tvSave.setVisibility(View.VISIBLE);
//            setLinEnabled(scrollLl,educationLl,majorLl,durationLl,llExperience,false);

        }else {
            isUpdate = true;
            setRightText(getResources().getString(R.string.save));
            showRightBg();//显示背景按钮
            setLinEnabled(scrollLl,educationLl,majorLl,durationLl,llExperience,true);
            educationInfosBean = (BriefRefreshBean.DataBean.EducationInfosBean) getIntent().getSerializableExtra(Constant.EDU);
            String cunrrentUid= SharePreferenceUtils.getBaseSharePreference().readUserId();
            if (educationInfosBean == null) {
                return;
            }
            else if(StringUtil.isNotEmpty(cunrrentUid)&&(educationInfosBean.getUserId()==Integer.valueOf(cunrrentUid)))
            {
                tvDelete.setVisibility(View.VISIBLE);
            }
            tvSave.setVisibility(View.GONE);
            schoolTv.setText(educationInfosBean.getSchool());
//            educationTv.setText(CodeUtils.getInstance().getEducationByCode(educationInfosBean.getEducation()));
            majorTv.setText(educationInfosBean.getMajor());
            durationTv.setText(educationInfosBean.getDuration());
            tvExperience.setText(educationInfosBean.getExperience());
        }
    }

    /**
     * 设置控件点击
     * @param llPosition
     * @param llCompany
     * @param llMan
     * @param llTime
     * @param llWork
     * @param bool
     */
    private void setLinEnabled(LinearLayout llPosition, LinearLayout llCompany,
                               LinearLayout llMan, LinearLayout llTime,
                               LinearLayout llWork,
                               Boolean bool) {
        llPosition.setEnabled(bool);
        llCompany.setEnabled(bool);
        llMan.setEnabled(bool);
        llTime.setEnabled(bool);
        llWork.setEnabled(bool);
    }

    private void setTextViewImage(int pic,TextView textView) {
        //设置TextView中的图片
        Drawable rightDrawable = getResources().getDrawable(pic);
        rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
        textView.setCompoundDrawables(null, null, rightDrawable, null);
    }

    @OnClick({R.id.educationLl, R.id.majorLl, R.id.durationLl, R.id.title_right_text, R.id.llTvBg, R.id.scrollLl, R.id.llExperience, R.id.tv_delete, R.id.tv_save})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.EDU);

        switch (view.getId()) {
            case R.id.scrollLl:
                intent.putExtra(Constant.TITLE, schoolLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, schoolTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.SCHOOL);
                intent.putExtra("showLength",false);
                startActivityForResult(intent, Constant.UNIVERSITY);
                break;
            case R.id.educationLl:
//                intent.putExtra(Constant.TITLE, educationLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, educationTv.getText().toString());
//                startActivityForResult(intent, Constant.EDUCATION);
                selectEducation();
                break;
            case R.id.majorLl:
                intent.putExtra(Constant.TITLE, majorLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, majorTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.MAJORS);
                intent.putExtra("showLength",false);
                startActivityForResult(intent, Constant.MAJOR);
                break;
            case R.id.durationLl:
//                intent.putExtra(Constant.TITLE, durationLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, durationTv.getText().toString());
//                intent.putExtra(Constant.WORKFLAG, Constant.DURATION);
//                startActivityForResult(intent, Constant.TIME);

                DialogUtils.showPickTimeDialog(durationTv, EduExpeActivity.this);
                break;
            case R.id.llExperience:
                intent.putExtra(Constant.TITLE, tvExperienceLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, tvExperience.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.EXPERIENCES);
                startActivityForResult(intent, Constant.EXPERIENCE);
                break;
            case R.id.tv_save:
            case R.id.llTvBg:
            case R.id.title_right_text:
//                if(educationKey == Constant.TIPS0&& TextUtils.isEmpty(educationTv.getText().toString())){
//                    T.showShort(getString(R.string.education_tips));
//                    return;
//                }

                if(StringUtil.isEmpty(schoolTv.getText().toString().trim()))
                {
                    T.showShort(getString(R.string.input_education_name));
                    return;
                }

                if(StringUtil.isEmpty(majorTv.getText().toString().trim()))
                {
                    T.showShort(getString(R.string.input_education_position));
                    return;
                }

                if(StringUtil.isEmpty(durationTv.getText().toString().trim()))
                {
                    T.showShort(getString(R.string.input_education_time));
                    return;
                }

                if (isUpdate) {
                    DialogUtil.getInstance().showLoadingDialog(this);
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                            if(commonBean!=null)
                            {
                                if(commonBean.getStatusCode()== Constant.SUCCESS)
                                {
                                    finish();
                                }
                                else if(StringUtil.isNotEmpty(commonBean.getMessage()))
                                {
                                    T.showShort(commonBean.getMessage());
                                }
                            }
                            DialogUtil.getInstance().closeLoadingDialog();
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                            DialogUtil.getInstance().closeLoadingDialog();
                        }
                    }, this).updateEducation(educationInfosBean.get_id(), schoolTv.
                            getText().toString(), SharePreferenceUtils.getBaseSharePreference().readEducationType()+"", majorTv.getText().
                            toString(), durationTv.getText().toString(), tvExperience.getText().toString());
                } else {
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                            if(commonBean!=null)
                            {
                                if(commonBean.getStatusCode()== Constant.SUCCESS)
                                {
                                    finish();
                                }
                                else if(StringUtil.isNotEmpty(commonBean.getMessage()))
                                {
                                    T.showShort(commonBean.getMessage());
                                }
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                        }
                    }, this).addEducation(schoolTv.
                            getText().toString(), educationKey + "", majorTv.getText().
                            toString(), durationTv.getText().toString(), tvExperience.getText().toString());
                }
                break;

            case R.id.tv_delete:
                DialogUtil.getInstance().showLoadingDialog(this);
                new UserTask(new HttpOnNextListener() {
                    @Override
                    public void onNext(String resulte, String method) {
                        DialogUtil.getInstance().closeLoadingDialog();
                        BaseBean bean = GsonUtils.parseJson(resulte, BaseBean.class);
                        if (bean.getStatusCode() == Constant.SUCCESS) {
                            finish();
                        } else {
                            T.showShort(bean.getMessage());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                        DialogUtil.getInstance().closeLoadingDialog();
                    }
                }, this).deleteInfo(1, educationInfosBean.get_id());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String stringExtra = data.getStringExtra(Constant.EDU);
        switch (requestCode) {
            case Constant.UNIVERSITY:
                university = stringExtra;
                schoolTv.setText(university);
                break;
            case Constant.EDUCATION:
                education = stringExtra;
                educationTv.setText(education);
                break;
            case Constant.MAJOR:
                major = stringExtra;
                majorTv.setText(major);
                break;
            case Constant.TIME:
                duration = stringExtra;
                durationTv.setText(duration);
                break;
            case Constant.EXPERIENCE:
                experience = stringExtra;
                tvExperience.setText(experience);
                break;
        }
    }

    /**
     * 选择学历
     */
    private void selectEducation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.selectEducation));
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&(configBean.getData()!=null))
        {
            List<LangChildBean.DbSelectInputStandardsBean.EducationsBean> educationsBeanList =
                    configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getEducations();
            String strings[] = new String[educationsBeanList.size()];
            for (int i = 0; i < educationsBeanList.size(); i++) {
                strings[i] = educationsBeanList.get(i).getLangValue();
            }
            builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    educationStr = strings[which];
                    dialogWhich = which;
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    educationTv.setText(educationStr);
                    educationKey = educationsBeanList.get(dialogWhich).getDbKey();
                    SharePreferenceUtils.getBaseSharePreference().saveEducationType(educationKey);//保存学历
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_EduExpeActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_EduExpeActivity);
        MobclickAgent.onPause(this);
    }

}
