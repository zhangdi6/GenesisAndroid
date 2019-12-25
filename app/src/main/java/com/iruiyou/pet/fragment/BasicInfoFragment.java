package com.iruiyou.pet.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.BasicInfoActivity2;
import com.iruiyou.pet.activity.IdentityInfoActivity;
import com.iruiyou.pet.activity.PositionActivity;
import com.iruiyou.pet.bean.CommonBean;
import com.iruiyou.pet.bean.HeadEventPost;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 基本资料
 */
public class BasicInfoFragment extends TakePhotoFragment {
    @BindView(R.id.positionTitle)
    LinearLayout positionTitle;
    @BindView(R.id.positionTitleTv)
    TextView positionTitleTv;
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
    @BindView(R.id.llTvBg)
    LinearLayout llTvBg;
    @BindView(R.id.llEditHead)
    LinearLayout llEditHead;
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
    private String positionTitleValue;
    private BasicInfoActivity2 ct;
    public static BasicInfoFragment newInstance() {
        BasicInfoFragment f = new BasicInfoFragment();
        return f;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_basic_info, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ct = (BasicInfoActivity2) getContext();
        String nationalCode = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("nationalCode");
        String phone = getActivity().getIntent().getStringExtra("phone");
        String smsCode = getActivity().getIntent().getStringExtra("smsCode");
        String password = getActivity().getIntent().getStringExtra("password");
        String invitationCode = getActivity().getIntent().getStringExtra("invitationCode");
        titleNameText.setText(getResources().getString(R.string.BasicInformation));
        //设置文字内容
        llTvBg.setVisibility(View.VISIBLE);
        titleRightText.setVisibility(View.VISIBLE);
        titleRightText.setText(getResources().getString(R.string.save));

        init();
    }
    private void init() {
        positionTitleValue= Objects.requireNonNull(getActivity()).getIntent().getStringExtra("PositionTitle");
        userid = getActivity().getIntent().getStringExtra("_id");
        company = getActivity().getIntent().getStringExtra("Company");
        professionalIdentity = getActivity().getIntent().getIntExtra("ProfessionalIdentity",0);
        realName = getActivity().getIntent().getStringExtra("RealName");
        userPosition = getActivity().getIntent().getStringExtra("Position");
        school = getActivity().getIntent().getStringExtra("School");
        education = getActivity().getIntent().getStringExtra("Education");
        country = getActivity().getIntent().getStringExtra("Country");
        number = getActivity().getIntent().getStringExtra("Number");
        nature = getActivity().getIntent().getStringExtra("Nature");
        headImg = getActivity().getIntent().getStringExtra("HeadImg");

        GlideUtils.displayRound(getContext(), BaseApi.baseUrlNoApi + headImg, imEditHead);
        userNameTv.setText(realName);
        companyTv.setText(company);
        positionTv.setText(CodeUtils.getInstance().getProfessional(professionalIdentity));
        tvCurrentPosition.setText(userPosition);
        schoolTv.setText(school);
        if(professionalIdentity== RegisterLastFragment.DBKEY_FREE)
        {
            positionTitle.setVisibility(View.GONE);
        }
        else if(StringUtil.isNotEmpty(positionTitleValue))
        {
            positionTitleTv.setText(positionTitleValue);
        }
        isUpdate = true;
    }


    @OnClick({R.id.userNameLl, R.id.schoolLl, R.id.educationLl, R.id.companyLl, R.id.positionLl, R.id.addrLl, R.id.personLl,
            R.id.companyStyleLl, R.id.title_right_text, R.id.llCurrentPosition, R.id.imEditHead, R.id.ll_title_left_view, R.id.positionTitle})
    public void onViewClicked(View view) {
        Intent intent = new Intent(ct, IdentityInfoActivity.class);
        intent.putExtra(Constant.IDENTITY, Constant.BASIC);
        switch (view.getId()) {
            case R.id.positionTitle:
                intent.putExtra(Constant.TITLE, getResources().getString(R.string.professional_title));
                intent.putExtra(Constant.DETAIL, positionTitleTv.getText().toString());
                intent.putExtra(Constant.WORKFLAG, Constant.POSITION_TITLE);
                startActivityForResult(intent, Constant.PROFESSIONAL_TITLE_CDOE);
                break;
            case R.id.imEditHead:
                imgAlert();
                break;
            case R.id.ll_title_left_view:
                ct.finish();
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
                Intent intent5 = new Intent(ct, PositionActivity.class);
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
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
                            if(commonBean!=null)
                            {
                                if(commonBean.getStatusCode()== Constant.SUCCESS)
                                {
                                    Objects.requireNonNull(getActivity()).finish();
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
                    }, ct).updateBasic(userid,
                            userNameTv.getText().toString(),
                            schoolTv.getText().toString(),
                            education+"",
                            companyTv.getText().toString(),
                            tvCurrentPosition.getText().toString(),//tvCurrentPosition.getText().toString()
                            country,
                            number,
                            nature,
                            headImg,
                            "",//tvDescribe.getText().toString(),
                            professionalIdentity,positionTitleTv.getText().toString().trim());

//                }else {
////                    String headImgs = getIntent().getStringExtra("HeadImg");
//                    new UserTask(new HttpOnNextListener() {
//                        @Override
//                        public void onNext(String resulte, String method) {
//                            CommonBean commonBean = GsonUtils.parseJson(resulte, CommonBean.class);
////                        T.showShort(commonBean.getMessage());
//                            Toast.makeText(ct, commonBean.getMessage(), Toast.LENGTH_SHORT).show();
//                            if (commonBean.getStatusCode() == 0) {
//                                getActivity().finish();
//                            }
//                        }
//
//                        @Override
//                        public void onError(ApiException e) {
//                            T.showShort(e.getMessage());
//                        }
//                    }, ct).updateBasic(userNameTv.getText().toString(),
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
    /******************************以下为拍照的逻辑****************************************/
    /**
     * 拍照弹出框
     */
    private void imgAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
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

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        TImage image = result.getImage();
        if (image.getCompressPath() == null) {
            T.showShort(getResources().getString(R.string.FailedToGetPhotos));
            return;
        }
        GlideUtils.displayFile(getContext(), image.getCompressPath(), imEditHead);
        upImg(new File(image.getCompressPath()));
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
                EventBusUtils.getInstance().postEvent(new HeadEventPost());
//                T.showShort(takePhotoBean.getMessage());
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "userHead");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String stringExtra = data.getStringExtra(Constant.BASIC);
        int dbKey=data.getIntExtra("dbKey",-100);
        if(StringUtil.isNotEmpty(stringExtra))
        {

            switch (requestCode)
            {
                case Constant.PROFESSIONAL:
                    positionTv.setText(stringExtra);
                    if(-100!=dbKey)
                    {
                        professionalIdentity=dbKey;
                    }
                    break;
                case Constant.PROFESSIONAL_TITLE_CDOE:
                    positionTitleTv.setText(stringExtra);
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
