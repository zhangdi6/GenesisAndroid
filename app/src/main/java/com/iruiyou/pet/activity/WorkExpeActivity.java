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
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.StringUtil;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：工作经历
 * 作者：jiaopeirong on 2018/8/12 16:34
 * 邮箱：chinajpr@163.com
 */
public class WorkExpeActivity extends BaseActivity {

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
    @BindView(R.id.positionLl)
    LinearLayout positionLl;
    @BindView(R.id.companyLl)
    LinearLayout companyLl;
    @BindView(R.id.timeLl)
    LinearLayout timeLl;
//    @BindView(R.id.content)
//    EditText content;
//    @BindView(R.id.tvIdentityNum)
//    TextView tvIdentityNum;
    @BindView(R.id.positionTv)
    TextView positionTv;
    @BindView(R.id.companyTv)
    TextView companyTv;
    @BindView(R.id.durationTv)
    TextView durationTv;
    @BindView(R.id.manTv)
    TextView manTv;
    @BindView(R.id.manLl)
    LinearLayout manLl;
    @BindView(R.id.positionLeft)
    TextView positionLeft;
    @BindView(R.id.companyLeft)
    TextView companyLeft;
    @BindView(R.id.manLeft)
    TextView manLeft;
    @BindView(R.id.durationLeft)
    TextView durationLeft;
    @BindView(R.id.tvWorkContent)
    TextView tvWorkContent;
    @BindView(R.id.tvWorkContentLeft)
    TextView tvWorkContentLeft;
    @BindView(R.id.llWorkContent)
    LinearLayout llWorkContent;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    private String position;
    private String company;
    private String number;
    private String duration;
    private String jobDesc;
    private boolean isUpdate;
    private BriefRefreshBean.DataBean.WorkInfosBean workInfosBean;
    private String manStr;
    private int dialogWhich;
    private int manKey;

    @Override
    public int getLayout() {
        return R.layout.activity_work_expe;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.workExperience));
        init();
    }

    private void init() {
        String editors = getIntent().getStringExtra(Constant.EDITORS);
        if(editors.equals(Constant.SWITCH_NO)){
            //设置TextView中的图片
//            Drawable rightDrawable = getResources().getDrawable(R.drawable.shape_white);
//            rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
//            durationTv.setCompoundDrawables(null, null, rightDrawable, null);
            setTextViewImage(R.drawable.shape_white,positionTv);
            setTextViewImage(R.drawable.shape_white,companyTv);
            setTextViewImage(R.drawable.shape_white,manTv);
            setTextViewImage(R.drawable.shape_white,durationTv);
            setTextViewImage(R.drawable.shape_white,tvWorkContent);
            hideRightBg();
            hideRight();
//            setLinEnabled(positionLl,companyLl,manLl,timeLl,llWorkContent,false);
            tvSave.setVisibility(View.VISIBLE);
        }else {
            workInfosBean = (BriefRefreshBean.DataBean.WorkInfosBean) getIntent().getSerializableExtra(Constant.WORK);
            if (workInfosBean == null) {
                return;
            }
            isUpdate = true;
            setRightText(getResources().getString(R.string.save));
            showRightBg();//显示背景按钮
            setLinEnabled(positionLl,companyLl,manLl,timeLl,llWorkContent,true);
            String cunrrentUid= SharePreferenceUtils.getBaseSharePreference().readUserId();
            if(StringUtil.isNotEmpty(cunrrentUid)&&(workInfosBean.getUserId()==Integer.valueOf(cunrrentUid)))
            {
                tvDelete.setVisibility(View.VISIBLE);
            }
            tvSave.setVisibility(View.GONE);

            positionTv.setText(workInfosBean.getPosition());
            companyTv.setText(workInfosBean.getCompany());
            durationTv.setText(workInfosBean.getDuration());
            tvWorkContent.setText(workInfosBean.getJobDesc());
//        content.setText(workInfosBean.getJobDesc());
//        content.setSelection(content.getText().length());
//        tvIdentityNum.setText(String.valueOf(content.getText().length()));
            manTv.setText(CodeUtils.getInstance().getWorkInfoByCode(workInfosBean.getNumber()));
//        saveTv.setText(getResources().getString(R.string.change));

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

    @OnClick({R.id.positionLl, R.id.companyLl, R.id.timeLl, R.id.title_right_text, R.id.llTvBg, R.id.manLl, R.id.llWorkContent, R.id.tv_delete, R.id.tv_save})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.WORK);
        switch (view.getId()) {
            case R.id.positionLl:
                intent.putExtra(Constant.TITLE, positionLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, positionTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.POSITIONS);
                intent.putExtra("showLength",false);
                startActivityForResult(intent, Constant.POSITION);
                break;
            case R.id.companyLl:
                intent.putExtra(Constant.TITLE, companyLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, companyTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.COMPANYS);
                intent.putExtra("showLength",false);
                startActivityForResult(intent, Constant.COMPANY);
                break;
            case R.id.timeLl:
//                intent.putExtra(Constant.TITLE, durationLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, durationTv.getText().toString());
//                intent.putExtra(Constant.WORKFLAG, Constant.TIMES);
//                startActivityForResult(intent, Constant.TIME);
                DialogUtils.showPickTimeDialog(durationTv, WorkExpeActivity.this);
                break;
            case R.id.llWorkContent:
                intent.putExtra(Constant.TITLE, tvWorkContentLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, tvWorkContent.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.WORK);
                startActivityForResult(intent, Constant.WORKS);
                break;
            case R.id.tv_save:
            case R.id.llTvBg:
            case R.id.title_right_text:
                if (StringUtils.isBlank(positionTv.getText().toString())||positionTv.getText().toString().equals(getString(R.string.pleaseFillIn))) {
                    T.showShort(getString(R.string.position_tips));
                    return;
                }
                if (StringUtils.isBlank(companyTv.getText().toString())||companyTv.getText().toString().equals(getString(R.string.pleaseFillIn))) {
                    T.showShort(getString(R.string.company_tips));
                    return;
                }
                if (StringUtils.isBlank(durationTv.getText().toString())||durationTv.getText().toString().equals(getString(R.string.pleaseFillIn))) {
                    T.showShort(getString(R.string.time_slot_tips));
                    return;
                }

//                if (StringUtils.isBlank(tvWorkContent.getText().toString())||tvWorkContent.getText().toString().equals(getString(R.string.pleaseFillIn))) {
//                    T.showShort(getString(R.string.job_content_tips));
//                    return;
//                }

                if (isUpdate) {
                    DialogUtil.getInstance().showLoadingDialog(this);
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                            DialogUtil.getInstance().closeLoadingDialog();
                            if(commonBean!=null)
                            {
                                if (commonBean.getStatusCode() == Constant.SUCCESS) {
                                    finish();
                                }
                                else if(StringUtil.isNotEmpty(commonBean.getMessage()))
                                {
                                    T.showShort(commonBean.getMessage());//这行代码崩溃
                                }
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                            DialogUtil.getInstance().closeLoadingDialog();
                        }
                    }, this).updateWork(workInfosBean.get_id(), positionTv.getText().toString(),
                            companyTv.getText().toString(),
                            SharePreferenceUtils.getBaseSharePreference().readPeopleType(),
                            durationTv.getText().toString(),
                            tvWorkContent.getText().toString());//content
                } else {
//                    if (manKey == Constant.TIPS0 || manTv.getText().toString().equals(getString(R.string.pleaseFillIn))) {
//                        T.showShort(getString(R.string.number_tips));
//                        return;
//                    }
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                            T.showShort(commonBean.getMessage());
                            if (commonBean.getStatusCode() == Constant.SUCCESS) {
                                finish();
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            T.showShort(e.getMessage());
                        }
                    }, this).addWork(positionTv.getText().toString(), companyTv.getText().toString(), manKey, durationTv.getText().toString(), tvWorkContent.getText().toString());
                }
                break;
            case R.id.manLl:
                selectCompanyPeopleCounts();
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
                }, this).deleteInfo(0, workInfosBean.get_id());
                break;
        }
    }

    /**
     * 选择公司人数
     */
    private void selectCompanyPeopleCounts() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.PleaseSelectTheNumberOfCompanies));
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&(configBean.getData()!=null))
        {
            List<LangChildBean.DbSelectInputStandardsBean.CompanyPeopleCountsBean> companyPeopleCounts = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getCompanyPeopleCounts();
            String strings[] = new String[companyPeopleCounts.size()];
            for (int i = 0; i < companyPeopleCounts.size(); i++) {
                strings[i] = companyPeopleCounts.get(i).getLangValue();
            }
            builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    manStr = strings[which];
                    dialogWhich = which;
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    manTv.setText(manStr);
                    manKey = companyPeopleCounts.get(dialogWhich).getDbKey();
                    SharePreferenceUtils.getBaseSharePreference().savePeopleType(manKey);//保存公司人数
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String stringExtra = data.getStringExtra(Constant.WORK);//数据回调保存处理
        switch (requestCode) {
            case Constant.POSITION:
                position = stringExtra;
                positionTv.setText(position);
                break;
            case Constant.COMPANY:
                company = stringExtra;
                companyTv.setText(company);
                break;
            case Constant.TIME:
                duration = stringExtra;
                durationTv.setText(duration);
                break;
            case Constant.WORKS:
                String workContent = stringExtra;
                tvWorkContent.setText(workContent);
                break;
        }
    }
}
