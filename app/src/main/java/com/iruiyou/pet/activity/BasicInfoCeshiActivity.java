package com.iruiyou.pet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.jph.takephoto.app.TakePhotoFragmentActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 类描述：基本资料
 * 作者：jiaopeirong on 2018/8/17 15:54
 * 邮箱：chinajpr@163.com
 */
public class BasicInfoCeshiActivity extends TakePhotoFragmentActivity {
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
    @BindView(R.id.userNameTv)
    TextView userNameTv;
    @BindView(R.id.userNameLl)
    LinearLayout userNameLl;
    @BindView(R.id.schoolTv)
    TextView schoolTv;
    @BindView(R.id.schoolLl)
    LinearLayout schoolLl;
    @BindView(R.id.educationTv)
    TextView educationTv;
    @BindView(R.id.educationLl)
    LinearLayout educationLl;
    @BindView(R.id.companyTv)
    TextView companyTv;
    @BindView(R.id.companyLl)
    LinearLayout companyLl;
    @BindView(R.id.positionTv)
    TextView positionTv;
    @BindView(R.id.positionLl)
    LinearLayout positionLl;
    @BindView(R.id.addrTv)
    TextView addrTv;
    @BindView(R.id.addrLl)
    LinearLayout addrLl;
    @BindView(R.id.persionTv)
    TextView persionTv;
    @BindView(R.id.personLl)
    LinearLayout personLl;
    @BindView(R.id.companyStyleTv)
    TextView companyStyleTv;
//    @BindView(R.id.tvDescribe)
//    TextView tvDescribe;
//    @BindView(R.id.tvDescribeLeft)
//    TextView tvDescribeLeft;
//    @BindView(R.id.llDescribe)
//    LinearLayout llDescribe;
    @BindView(R.id.companyStyleLl)
    LinearLayout companyStyleLl;
    //    @BindView(R.id.selfDescTv)
//    EditText selfDescTv;
    @BindView(R.id.userNameLeft)
    TextView userNameLeft;
    @BindView(R.id.schoolLeft)
    TextView schoolLeft;
    @BindView(R.id.educationLeft)
    TextView educationLeft;
    @BindView(R.id.companyLeft)
    TextView companyLeft;
    @BindView(R.id.positionLeft)
    TextView positionLeft;
    @BindView(R.id.addLeft)
    TextView addLeft;
    @BindView(R.id.manLeft)
    TextView manLeft;
    @BindView(R.id.companyStyleLeft)
    TextView companyStyleLeft;
    @BindView(R.id.llCurrentPosition)
    LinearLayout llCurrentPosition;
    @BindView(R.id.tvCurrentPositionLeft)
    TextView tvCurrentPositionLeft;
    @BindView(R.id.tvCurrentPosition)
    TextView tvCurrentPosition;
    @BindView(R.id.imEditHead)
    ImageView imEditHead;
    private String educationStr;
    private int educationKey;
    private String manStr;
    private String positionStr;
    private int dialogWhich;
    private int manKey;
    private int professionalIdentityKey;
    private boolean isUpdate;
//    private BriefRefreshBean.DataBean.BasicInfoBean basicInfoBean;
    private int professionalIdentity;
    private String education;
    private String country;
    private String number;
    private String headImg;
    private String nature;
    private String userid;
    private String company;
    private String realName;
    private String position;
    private String userPosition;
    private String school;
    private int myUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        setTitle(getResources().getString(R.string.BasicInformation));
        //设置文字内容
//        setRightText(getResources().getString(R.string.save));
//        showRightBg();//显示背景按钮
        init();
    }
//    @Override
//    public int getLayout() {
//        return R.layout.activity_basic_info;
//    }

    private void init() {
//        basicInfoBean = (BriefRefreshBean.DataBean.BasicInfoBean) getIntent().getSerializableExtra("123");
//        if (basicInfoBean == null) {
//            return;
//        }
        userid = getIntent().getStringExtra("_id");
//        myUserid = Integer.parseInt(userid);
        company = getIntent().getStringExtra("Company");
        professionalIdentity = getIntent().getIntExtra("ProfessionalIdentity",0);
        realName = getIntent().getStringExtra("RealName");
        userPosition = getIntent().getStringExtra("Position");
        school = getIntent().getStringExtra("School");
        education = getIntent().getStringExtra("Education");
        country = getIntent().getStringExtra("Country");
        number = getIntent().getStringExtra("Number");
        nature = getIntent().getStringExtra("Nature");
        headImg = getIntent().getStringExtra("HeadImg");


        GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + headImg, imEditHead);
        userNameTv.setText(realName);
        companyTv.setText(company);
//        if(SharePreferenceUtils.getBaseSharePreference().readPositionType()>=0){
//            positionTv.setText(CodeUtils.getInstance().getProfessional(SharePreferenceUtils.getBaseSharePreference().readPositionType()));
//        }else {
            positionTv.setText(CodeUtils.getInstance().getProfessional(professionalIdentity));
//        }
        tvCurrentPosition.setText(userPosition);
        schoolTv.setText(school);

        isUpdate = true;
    }

    @OnClick({R.id.userNameLl, R.id.schoolLl, R.id.educationLl, R.id.companyLl, R.id.positionLl, R.id.addrLl, R.id.personLl, R.id.companyStyleLl, R.id.title_right_text, R.id.llCurrentPosition, R.id.imEditHead})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.BASIC);
        switch (view.getId()) {
            case R.id.imEditHead:

                break;
            case R.id.userNameLl://用户名
                intent.putExtra(Constant.TITLE, userNameLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, userNameTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.USERNAMES);
                startActivityForResult(intent, Constant.USERNAME);
                break;
            case R.id.schoolLl://学校
                intent.putExtra(Constant.TITLE, schoolLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, schoolTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.SCHOOL);
                startActivityForResult(intent, Constant.SCHOOLNAME);
                break;
//            case R.id.educationLl://学历
////                intent.putExtra(Constant.TITLE, educationLeft.getText().toString());
////                intent.putExtra(Constant.DETAIL, educationTv.getText().toString());
////                startActivityForResult(intent, Constant.EDUCATION);
//                selectEducation();
//                break;
            case R.id.companyLl://公司名称
                intent.putExtra(Constant.TITLE, companyLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, companyTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.COMPANYNAME);
                startActivityForResult(intent, Constant.COMPANY);
                break;
            case R.id.positionLl://职业身份
//                intent.putExtra(Constant.TITLE, positionLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, positionTv.getText().toString());
//                intent.putExtra(Constant.WORKFLAG, Constant.POSITIONS);
//                startActivityForResult(intent, Constant.POSITION);

//                selectPosition();
//                StartActivityManager.startPositionActivity(this,Constant.PROFESSIONALS);
//                basicInfoBean = (BriefRefreshBean.DataBean.BasicInfoBean) getIntent().getSerializableExtra("123");
                Intent intent5 = new Intent(this, PositionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ProfessionalIdentity", professionalIdentity);
                intent5.putExtras(bundle);
//                intent.putExtra(Constant.WORKFLAG, Constant.PROFESSIONALS);
//                intent.putExtra("ProfessionalIdentity", basicInfoBean.getProfessionalIdentity()+"");
                startActivityForResult(intent5, Constant.PROFESSIONAL);
                break;
            case R.id.llCurrentPosition://职位
                intent.putExtra(Constant.TITLE, tvCurrentPositionLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, tvCurrentPosition.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.POSITIONS);
                startActivityForResult(intent, Constant.POSITION);
                break;
//            case R.id.llDescribe://个人描述
//                intent.putExtra(Constant.TITLE, tvDescribeLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, tvDescribe.getText().toString());
//                intent.putExtra(Constant.WORKFLAG, Constant.DESCRIBE);
//                startActivityForResult(intent, Constant.MYDESCRIBE);
//                break;
//            case R.id.addrLl://公司注册地
//                intent.putExtra(Constant.TITLE, addLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, addrTv.getText().toString());
//                startActivityForResult(intent, Constant.ADDR);
//                break;
//            case R.id.personLl://公司人数
//                selectCompanyPeopleCounts();
//                break;
//            case R.id.companyStyleLl://公司性质
//                intent.putExtra(Constant.TITLE, companyStyleLeft.getText().toString());
//                intent.putExtra(Constant.DETAIL, companyStyleTv.getText().toString());
//                startActivityForResult(intent, Constant.COMPANYSTYLE);
//                break;
            case R.id.title_right_text://保存
                SharePreferenceUtils.getBaseSharePreference().saveType(1);
//                if (isUpdate) {
//                    basicInfoBean = (BriefRefreshBean.DataBean.BasicInfoBean) getIntent().getSerializableExtra("123");
//                    if (basicInfoBean == null) {
//                        return;
//                    }
//                    new UserTask(new HttpOnNextListener() {
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
////                        T.showShort(commonBean.getMessage());
//                            Toast.makeText(BasicInfoCeshiActivity.this, commonBean.getMessage(), Toast.LENGTH_SHORT).show();
//                            if (commonBean.getStatusCode() == 0) {
//                                finish();
//                            }
//                        }
//
//                        @Override
//                        public void onError(ApiException e) {
//                            T.showShort(e.getMessage());
//                        }
//                    }, BasicInfoCeshiActivity.this).updateBasic(userid,
//                            userNameTv.getText().toString(),
//                            schoolTv.getText().toString(),
//                            education+"",
//                            companyTv.getText().toString(),
//                            tvCurrentPosition.getText().toString(),//tvCurrentPosition.getText().toString()
//                            country,
//                            number,
//                            nature,
//                            headImg,
//                            "",//tvDescribe.getText().toString(),
//                            SharePreferenceUtils.getBaseSharePreference().readPositionType());


//                }else {
////                    String headImgs = getIntent().getStringExtra("HeadImg");
//                    new UserTask(new HttpOnNextListener() {
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
////                        T.showShort(commonBean.getMessage());
//                            Toast.makeText(BasicInfoActivity.this, commonBean.getMessage(), Toast.LENGTH_SHORT).show();
//                            if (commonBean.getStatusCode() == 0) {
//                                finish();
//                            }
//                        }
//
//                        @Override
//                        public void onError(ApiException e) {
//                            T.showShort(e.getMessage());
//                        }
//                    }, BasicInfoActivity.this).updateBasic(userNameTv.getText().toString(),
//                            schoolTv.getText().toString(),
//                            String.valueOf(educationKey),
//                            companyTv.getText().toString(),
//                            tvCurrentPosition.getText().toString(),//peopleCountsKey  positionTv.getText().toString()
//                            addrTv.getText().toString(),
//                            String.valueOf(manKey),
//                            companyStyleTv.getText().toString(),
//                            "",
//                            "",
//                            SharePreferenceUtils.getBaseSharePreference().readPositionType());
//
//                }
                break;
        }
    }

    /**
     * 选择职业身份
     */
    private void selectPosition() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.position));
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&(configBean.getData()!=null))
        {
            List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
            professionalIdentities.remove(0);
            String strings[] = new String[professionalIdentities.size()];
            for (int i = 0; i < professionalIdentities.size(); i++) {
                strings[i] = professionalIdentities.get(i).getLangValue();
            }
            builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    positionStr = strings[which];
                    dialogWhich = which;
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    positionTv.setText(positionStr);
                    professionalIdentityKey = professionalIdentities.get(dialogWhich).getDbKey();
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

//    /**
//     * 选择公司人数
//     */
//    private void selectCompanyPeopleCounts() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getResources().getString(R.string.PleaseSelectTheNumberOfCompanies));
//        ConfigBean configBean = (ConfigBean) ACache.get(this).getAsObject(TagConstants.config);
//        List<LangChildBean.DbSelectInputStandardsBean.CompanyPeopleCountsBean> companyPeopleCounts = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getCompanyPeopleCounts();
//        String strings[] = new String[companyPeopleCounts.size()];
//        for (int i = 0; i < companyPeopleCounts.size(); i++) {
//            strings[i] = companyPeopleCounts.get(i).getLangValue();
//        }
//        builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                manStr = strings[which];
//                dialogWhich = which;
//            }
//        });
//        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                persionTv.setText(manStr);
//                manKey = companyPeopleCounts.get(dialogWhich).getDbKey();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

//    /**
//     * 选择学历
//     */
//    private void selectEducation() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getResources().getString(R.string.selectEducation));
//        ConfigBean configBean = (ConfigBean) ACache.get(this).getAsObject(TagConstants.config);
//        List<LangChildBean.DbSelectInputStandardsBean.EducationsBean> educationsBeanList = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getEducations();
//        String strings[] = new String[educationsBeanList.size()];
//        for (int i = 0; i < educationsBeanList.size(); i++) {
//            strings[i] = educationsBeanList.get(i).getLangValue();
//        }
//        builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                educationStr = strings[which];
//                dialogWhich = which;
//            }
//        });
//        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                educationTv.setText(educationStr);
//                educationKey = educationsBeanList.get(dialogWhich).getDbKey();
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//    }

    @Override
    protected void onResume() {
        super.onResume();
//        if(!isUpdate){
//            int position = SharePreferenceUtils.getBaseSharePreference().readPositionType();
//            if(position>=0){
//                positionTv.setText(CodeUtils.getInstance().getProfessional(position));
        MobclickAgent.onPageStart(PageNameConstant.PageName_BasicInfoActivity);
        MobclickAgent.onResume(this);
        positionTv.setText(positionTv.getText().toString());
        tvCurrentPosition.setText(tvCurrentPosition.getText().toString());
//            }
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String stringExtra = data.getStringExtra(Constant.BASIC);
        int dbKey=data.getIntExtra("dbKey",-100);
        switch (requestCode) {
            case Constant.USERNAME://用户名
                userNameTv.setText(stringExtra);
                break;
            case Constant.SCHOOLNAME://学校
                schoolTv.setText(stringExtra);
                break;
            case Constant.EDUCATION://学历
                educationTv.setText(stringExtra);
                break;
            case Constant.COMPANY://公司名称
                companyTv.setText(stringExtra);
                break;
            case Constant.POSITION://职位
//                positionTv.setText(stringExtra);
                tvCurrentPosition.setText(stringExtra);
                break;
            case Constant.PROFESSIONAL://职业身份
//                positionTv.setText(stringExtra);
                positionTv.setText(stringExtra);
                break;
            case Constant.ADDR://公司注册地
                addrTv.setText(stringExtra);
                break;
            case Constant.PERSION://公司人数
                persionTv.setText(stringExtra);
                break;
            case Constant.COMPANYSTYLE://公司性质
                companyStyleTv.setText(stringExtra);
                break;
            case Constant.MYDESCRIBE://个人描述
//                tvDescribe.setText(stringExtra);
                break;
        }
        if(dbKey!=-100)
        {
            professionalIdentity=dbKey;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_BasicInfoActivity);
        MobclickAgent.onPause(this);
    }

}
