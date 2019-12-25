package com.iruiyou.pet.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.HeadEventPost;
import com.iruiyou.pet.bean.LoginNewBean;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.iruiyou.pet.bean.event.MineRefreshEvent;
import com.iruiyou.pet.fragment.RegisterLastFragment;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.makeramen.roundedimageview.RoundedImageView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类描述：基本资料
 * 作者：jiaopeirong on 2018/8/17 15:54
 * 邮箱：chinajpr@163.com
 */
public class BasicInfoActivity extends BaseActivity implements TakePhoto.TakeResultListener, InvokeListener {
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
    @BindView(R.id.tvDescribe)
    TextView tvDescribe;
    @BindView(R.id.tvDescribeLeft)
    TextView tvDescribeLeft;
    @BindView(R.id.llDescribe)
    LinearLayout llDescribe;
    @BindView(R.id.companyStyleLl)
    LinearLayout companyStyleLl;
    @BindView(R.id.edit_desic)
    EditText selfDescTv;
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
    RoundedImageView imEditHead;
    @BindView(R.id.positionTitle)
    LinearLayout positionTitle;
    @BindView(R.id.positionTitleTv)
    TextView positionTitleTv;
    @BindView(R.id.genderTv)
    TextView genderTv;
    @BindView(R.id.cityTv)
    TextView cityTv;
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
    private String positionTitleValue;
    private int myUserid;
    private int genders=0;
    private int cityCode =0;
    private String city = "";
    private String selfDesc;
    private ACache aCache;

    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(BasicInfoActivity.this,type,invokeParam,this);
    }

    @Override
    public void takeSuccess(TResult result) {
        TImage image = result.getImage();
        if (image.getCompressPath() == null) {
            T.showShort(getResources().getString(R.string.FailedToGetPhotos));
            return;
        }
        GlideUtils.displayFile(BasicInfoActivity.this, image.getCompressPath(), imEditHead);
        upImg(new File(image.getCompressPath()));
    }

    @Override
    public void takeFail(TResult result,String msg) {
        Toast.makeText(BasicInfoActivity.this,"头像更新操作失败!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        Toast.makeText(BasicInfoActivity.this,"头像更新操作取消!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }

    /**
     * 头像上传
     *
     * @param file
     */
    private void upImg(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("IMAGE", file.getName(), new ProgressRequestBody(requestBody,
                new UploadProgressListener() {
                    @Override
                    public void onProgress(long currentBytesCount, long totalBytesCount) {

                    }
                }));
        new UserTask(new HttpOnNextListener() {

            @Override
            public void onNext(String resulte, String method) {
                TakePhotoBean takePhotoBean = GsonUtils.parseJson(resulte, TakePhotoBean.class);
                if(takePhotoBean.getStatusCode()== Constant.SUCCESS){
                    EventBusUtils.getInstance().postEvent(new HeadEventPost());
                    headImg = takePhotoBean.getData();
                }else{
                    T.showShort(takePhotoBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort("头像上传失败!");
            }
        }, (RxAppCompatActivity) BasicInfoActivity.this).upImg(requestBody, part, "userHead");

    }


    @Override
    public int getLayout() {
        return R.layout.activity_basic_info;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        getTakePhoto().onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.BasicInformation));
        //设置文字内容
        setRightText(getResources().getString(R.string.save));
        showRightBg();//显示背景按钮
        aCache = ACache.get(this);
        init();
//        setRightViewListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new UserTask(new HttpOnNextListener() {
//                    @Override
//                    public void onNext(String resulte, String method) {
//                        CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
////                        T.showShort(commonBean.getMessage());
//                        Toast.makeText(BasicInfoActivity.this, commonBean.getMessage(), Toast.LENGTH_SHORT).show();
//                        if (commonBean.getStatusCode() == 0) {
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ApiException e) {
//                        T.showShort(e.getMessage());
//                    }
//                }, BasicInfoActivity.this).updateBasic(userNameTv.getText().toString(),
//                        schoolTv.getText().toString(),
//                        String.valueOf(educationKey),
//                        companyTv.getText().toString(),
//                        peopleCountsKey+"",//peopleCountsKey  positionTv.getText().toString()
//                        addrTv.getText().toString(),
//                        String.valueOf(manKey),
//                        companyStyleTv.getText().toString(),
//                        "",
//                        tvDescribe.getText().toString(),
//                        peopleCountsKey);
//
//            }
//        });
    }

    private void init() {
//        basicInfoBean = (BriefRefreshBean.DataBean.BasicInfoBean) getIntent().getSerializableExtra("123");
//        if (basicInfoBean == null) {
//            return;
//        }
        positionTitleValue=getIntent().getStringExtra("PositionTitle");
        userid = getIntent().getStringExtra("_id");
//        myUserid = Integer.parseInt(userid);
        company = getIntent().getStringExtra("Company");
        professionalIdentity = getIntent().getIntExtra("ProfessionalIdentity", 0);
        realName = getIntent().getStringExtra("RealName");
        userPosition = getIntent().getStringExtra("Position");
        school = getIntent().getStringExtra("School");
        education = getIntent().getStringExtra("Education");
        country = getIntent().getStringExtra("Country");
        number = getIntent().getStringExtra("Number");
        nature = getIntent().getStringExtra("Nature");
        headImg = getIntent().getStringExtra("HeadImg");
        genders = getIntent().getIntExtra("genders",0);
        cityCode =  getIntent().getIntExtra("cityCode",0);
        city = getIntent().getStringExtra("city");
        selfDesc = getIntent().getStringExtra("selfDesc");
        if(professionalIdentity== RegisterLastFragment.DBKEY_FREE)
        {
            positionTitle.setVisibility(View.GONE);
        }
        else if(StringUtil.isNotEmpty(positionTitleValue))
        {
            positionTitleTv.setText(positionTitleValue);
        }

        //刷新用户头像到融云上
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + userid, realName, Uri.parse(BaseApi.baseUrlNoApi + headImg)));//刷新同步头像昵称到融云


        GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + headImg, imEditHead);
        userNameTv.setText(realName);
        companyTv.setText(company);
//        if(SharePreferenceUtils.getBaseSharePreference().readPositionType()>=0){
//            positionTv.setText(CodeUtils.getInstance().getProfessional(SharePreferenceUtils.getBaseSharePreference().readPositionType()));
//        }else {
        positionTv.setText(CodeUtils.getInstance().getProfessional(professionalIdentity));
//        }
        genderTv.setText(CodeUtils.getInstance().getGenders(genders));
        tvDescribe.setText(selfDesc);
        tvCurrentPosition.setText(userPosition);
        schoolTv.setText(school);
        if(StringUtil.isNotEmpty(city)){
            cityTv.setText(city);
        }
        isUpdate = true;
    }

    /******************************以下为拍照的逻辑****************************************/
    /**
     * 拍照弹出框
     */
    private void imgAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(BasicInfoActivity.this));
        builder.setTitle(getResources().getString(R.string.chooseAPhoto));
        final int[] position = {0};
        builder.setSingleChoiceItems(new String[]{getResources().getString(R.string.Camera), getResources().getString(R.string.gallery)}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position[0] = which;
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                takePhoto(position[0]);
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


    /**
     * 初始化照片
     */
    private void takePhoto(int num) {
        TakePhoto takePhoto = getTakePhoto();
        CropOptions.Builder builderTake = new CropOptions.Builder();
        builderTake.setAspectX(800).setAspectY(800);
        builderTake.setWithOwnCrop(true);
        File file = new File(Environment.getExternalStorageDirectory(),
                "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            boolean mkdirs = file.getParentFile().mkdirs();
            if (!mkdirs) {
                T.showShort(getResources().getString(R.string.FileDirectoryCreationFailed));
            }
        }

        Uri imageUri = Uri.fromFile(file);
        CompressConfig config = new CompressConfig.Builder()
//                .setMaxSize(1024 * 2)
                .setMaxPixel(400)
                .enableReserveRaw(true)
                .create();
        takePhoto.onEnableCompress(config, true);
        if (num == 0) {
            takePhoto.onPickFromCaptureWithCrop(imageUri, builderTake.create());
        }
        if (num == 1) {
            takePhoto.onPickFromDocumentsWithCrop(imageUri, builderTake.create());
        }
    }

    @OnClick({R.id.userNameLl, R.id.schoolLl, R.id.educationLl, R.id.companyLl, R.id.positionLl, R.id.addrLl, R.id.personLl,
            R.id.companyStyleLl, R.id.llTvBg, R.id.title_right_text, R.id.llCurrentPosition, R.id.imEditHead, R.id.llDescribe,
    R.id.positionTitle, R.id.linear_gender})
    public void onViewClicked(View view) {
        Intent intent = new Intent(this, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.BASIC);
        switch (view.getId()) {
            case R.id.linear_gender:
                StartActivityManager.startGendersSelectActivity(BasicInfoActivity.this,genders, Constant.GENDENRS);
                break;
            case R.id.imEditHead:
                imgAlert();
                break;
            case R.id.positionTitle:
                intent.putExtra(Constant.TITLE, getResources().getString(R.string.professional_title));
                intent.putExtra(Constant.DETAIL, positionTitleTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.POSITION_TITLE);
                startActivityForResult(intent, Constant.PROFESSIONAL_TITLE_CDOE);
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
                intent5.putExtra(Constant.TITLE, "身份");
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
            case R.id.llDescribe://个人描述
                intent.putExtra(Constant.TITLE, tvDescribeLeft.getText().toString());
                intent.putExtra(Constant.DETAIL, tvDescribe.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.DESCRIBE);
                startActivityForResult(intent, Constant.MYDESCRIBE);
                break;
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
            case R.id.title_right_text:
            case R.id.llTvBg://保存
                String positionTitle=positionTitleTv.getText().toString().trim();
                SharePreferenceUtils.getBaseSharePreference().saveType(1);
                LoginNewBean loginNewBean = (LoginNewBean) aCache.getAsObject(TagConstants.loginfig);
                LoginNewBean.DataBean.BasicInfoBean basicInfo=null;
                if(loginNewBean!=null&&loginNewBean.getData()!=null)
                {
                    basicInfo= loginNewBean.getData().getBasicInfo();
                }
                if(StringUtil.isNotEmpty(headImg)){
                    basicInfo.setHeadImg(headImg);
                }
                basicInfo.setProfessionalIdentity(professionalIdentity);
                basicInfo.setPosition(positionTitle);
                basicInfo.setGender(genders);
                basicInfo.setSelfDesc(tvDescribe.getText().toString());
                if (!isUpdate) {
//                    basicInfoBean = (BriefRefreshBean.DataBean.BasicInfoBean) getIntent().getSerializableExtra("123");
//                    if (basicInfoBean == null) {
//                        return;
//                    }
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
                    }, BasicInfoActivity.this).updateBasic(userid,
                            userNameTv.getText().toString(),
                            schoolTv.getText().toString(),
                            education + "",
                            companyTv.getText().toString(),
                            tvCurrentPosition.getText().toString(),//tvCurrentPosition.getText().toString()
                            country,
                            number,
                            nature,
                            headImg,
                            tvDescribe.getText().toString(),
                            professionalIdentity,positionTitle);

                } else if(basicInfo!=null){
//                    String headImgs = getIntent().getStringExtra("HeadImg");
//                    if (TextUtils.isEmpty(headImg)) {
//                        new UserTask(new HttpOnNextListener() {
//                            @Override
//                            public void onNext(String resulte, String method) {
//                                CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
//                                if(commonBean!=null)
//                                {
//                                    if(commonBean.getStatusCode()==Constant.SUCCESS)
//                                    {
//                                        finish();
//                                    }
//                                    else if(StringUtil.isNotEmpty(commonBean.getMessage()))
//                                    {
//                                        T.showShort(commonBean.getMessage());
//                                    }
//                                }
//                            }
//
//                            @Override
//                            public void onError(ApiException e) {
//                                T.showShort(e.getMessage());
//                            }
//                        }, BasicInfoActivity.this).updateBasic(userid, userNameTv.getText().toString(),
//                                schoolTv.getText().toString(),
//                                companyTv.getText().toString(),
//                                tvCurrentPosition.getText().toString(),//peopleCountsKey  positionTv.getText().toString()
//                                professionalIdentity,
//                                basicInfo.getGender(),
//                                basicInfo.getShowEdit(),
//                                basicInfo.getUserId(),
//                                basicInfo.getCreatedAt(),
//                                basicInfo.getUpdatedAt(),
//                                basicInfo.get__v(),positionTitle);
//                    } else {
                        new UserTask(new HttpOnNextListener() {
                            @Override
                            public void onNext(String resulte, String method) {
                                CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                                if(commonBean!=null)
                                {
                                    if(commonBean.getStatusCode()== Constant.SUCCESS)
                                    {
                                        aCache.put(TagConstants.loginfig, loginNewBean);
                                        EventBusUtils.getInstance().postEvent(new MineRefreshEvent());
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
                        }, BasicInfoActivity.this).updateBasic(userid, userNameTv.getText().toString(),
                                schoolTv.getText().toString(),
//                            String.valueOf(educationKey),
                                companyTv.getText().toString(),
                                tvCurrentPosition.getText().toString(),//peopleCountsKey  positionTv.getText().toString()
//                            addrTv.getText().toString(),
//                            "",
//                            companyStyleTv.getText().toString(),
//                            "",
//                            "",
                                basicInfo.getHeadImg(),
                                basicInfo.getProfessionalIdentity(),
                                basicInfo.getGender(),
                                basicInfo.getShowEdit(),
                                basicInfo.getUserId(),
                                basicInfo.getCreatedAt(),
                                basicInfo.getUpdatedAt(),
                                basicInfo.get__v(),positionTitle,basicInfo.getSelfDesc());

//                    }
                }
                break;
        }
    }


//    /**
//     * 选择职业身份
//     */
//    private void selectPosition() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getResources().getString(R.string.position));
//
//        ConfigBean configBean = App.getConfigBean();
//        if(configBean!=null)
//        {
//            List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
//            professionalIdentities.remove(0);
//            String strings[] = new String[professionalIdentities.size()];
//            for (int i = 0; i < professionalIdentities.size(); i++) {
//                strings[i] = professionalIdentities.get(i).getLangValue();
//            }
//            builder.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    positionStr = strings[which];
//                    dialogWhich = which;
//                }
//            });
//            builder.setPositiveButton(getResources().getString(R.string.sure), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    positionTv.setText(positionStr);
//                    professionalIdentityKey = professionalIdentities.get(dialogWhich).getDbKey();
//                    dialog.dismiss();
//                }
//            });
//            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                    dialog.dismiss();
//                }
//            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//        }
//
//
//    }
//
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
//
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
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        boolean isGenders = false;
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
                tvDescribe.setText(stringExtra);
                break;
            case Constant.PROFESSIONAL_TITLE_CDOE:
                positionTitleTv.setText(stringExtra);
                break;
            case Constant.GENDENRS: // 性别选择
                isGenders = true;
                genderTv.setText(stringExtra);
                break;
        }

        if(dbKey!=-100&&(!isGenders))
        {
            professionalIdentity=dbKey;
            if(professionalIdentity== RegisterLastFragment.DBKEY_FREE)
            {
                positionTitle.setVisibility(View.GONE);
            }
        }else if(isGenders){
            genders = dbKey;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_BasicInfoActivity);
        MobclickAgent.onPause(this);
    }

}
