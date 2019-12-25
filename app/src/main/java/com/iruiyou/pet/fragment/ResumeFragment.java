package com.iruiyou.pet.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.BasicInfoActivity;
import com.iruiyou.pet.activity.BlockChainActivity;
import com.iruiyou.pet.activity.EduExpeActivity;
import com.iruiyou.pet.activity.ResumeActivity2;
import com.iruiyou.pet.activity.WorkExpeActivity;
import com.iruiyou.pet.adapter.EduAdapter;
import com.iruiyou.pet.adapter.WorkAdapter;
import com.iruiyou.pet.bean.BriefRefreshBean;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.bean.HeadEventPost;
import com.iruiyou.pet.bean.LangChildBean;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.makeramen.roundedimageview.RoundedImageView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.util.List;
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
 * 类描述：简历Activity
 * 作者：jiaopeirong on 2018/8/8 22:05
 * 邮箱：chinajpr@163.com
 */
public class ResumeFragment extends TakePhotoFragment {
    @BindView(R.id.text_position_title)
    TextView tvPositionTitle;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.headIv)
    RoundedImageView headIv;
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
//    @BindView(R.id.educationTitle)
//    TextView educationTitle;
    @BindView(R.id.companyTitle)
    TextView companyTitle;
    @BindView(R.id.companyAddr)
    TextView companyAddr;
    @BindView(R.id.companyStyle)
    TextView companyStyle;
    @BindView(R.id.llModify)
    LinearLayout llModify;
    @BindView(R.id.include_foot_data)
    View include_foot_data;
    private WorkAdapter workAdapter;
    private EduAdapter eduAdapter;
    private ResumeActivity2 ct;
    private String switch_type;
    private BriefRefreshBean briefRefreshBean;

    public static ResumeFragment newInstance() {
        ResumeFragment f = new ResumeFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_resume, null, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ct = (ResumeActivity2) getContext();
        switch_type = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("switch");
        workAdapter = new WorkAdapter();
        eduAdapter = new EduAdapter();
        workRecyc.setAdapter(workAdapter);
        workRecyc.setLayoutManager(new LinearLayoutManager(getContext()));
        workRecyc.setNestedScrollingEnabled(false);
        eduRecyc.setAdapter(eduAdapter);
        eduRecyc.setLayoutManager(new LinearLayoutManager(getContext()));
        eduRecyc.setNestedScrollingEnabled(false);include_foot_data.setVisibility(View.GONE);
        if(switch_type.equals(Constant.SWITCH_YES)){
            imgAlert();
        }
        setListener();
    }


    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void setListener() {
        workAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                Bundle bundle = new Bundle();
                bundle.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent = new Intent(ct, WorkExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
//                ct.startActivity(WorkExpeActivity.class);
            }

        });

        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BriefRefreshBean.DataBean.WorkInfosBean workInfosBean = (BriefRefreshBean.DataBean.WorkInfosBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.WORK, workInfosBean);
                bundle.putString(Constant.EDITORS, Constant.SWITCH_YES);
                Intent intent = new Intent(ct, WorkExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        eduAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                ct.startActivity(EduExpeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent = new Intent(ct, EduExpeActivity.class);
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
                bundle.putString(Constant.EDITORS, Constant.SWITCH_YES);
                Intent intent = new Intent(ct, EduExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });





        headIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgAlert();
            }
        });
    }

    private void getData() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                if(StringUtil.isNotEmpty(resulte))
                {
                    briefRefreshBean = GsonUtils.parseJson(resulte, BriefRefreshBean.class);
                    if(briefRefreshBean!=null)
                    {
                        if (briefRefreshBean.getStatusCode() == Constant.SUCCESS) {
                            if (briefRefreshBean.getData().getBasicInfo() != null) {
                                tvPositionTitle.setText(briefRefreshBean.getData().getBasicInfo().getPositionTitle());
                                String company = briefRefreshBean.getData().getBasicInfo().getCompany();
                                String positionbrief = briefRefreshBean.getData().getBasicInfo().getPosition();
                                name.setText(briefRefreshBean.getData().getBasicInfo().getRealName());
//                        educationTitle.setText(CodeUtils.getInstance().getEducationByCode(briefRefreshBean.getData().getBasicInfo().getEducation()));
                                //刷新用户头像到融云上
                                RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID+briefRefreshBean.getData().getBasicInfo().getUserId(), briefRefreshBean.getData().getBasicInfo().getRealName(), Uri.parse(BaseApi.baseUrlNoApi+briefRefreshBean.getData().getBasicInfo().getHeadImg())));//刷新同步头像昵称到融云
                                if(TextUtils.isEmpty(company)&&!TextUtils.isEmpty(positionbrief)){
                                    companyTitle.setText(positionbrief);
                                }else if(TextUtils.isEmpty(positionbrief)&&!TextUtils.isEmpty(company)){
                                    companyTitle.setText(company);
                                }else if(!TextUtils.isEmpty(company)&&!TextUtils.isEmpty(positionbrief)){
                                    companyTitle.setText(company + Constant.LARGE_SPACE + positionbrief);
                                }
//                        companyTitle.setText(briefRefreshBean.getData().getBasicInfo().getCompany()+"\t--\t"+briefRefreshBean.getData().getBasicInfo().getPosition());
//                        companyAddr.setText(briefRefreshBean.getData().getBasicInfo().getCountry());
//                        companyStyle.setText(briefRefreshBean.getData().getBasicInfo().getNature());
                                GlideUtils.displayRound(getContext(), BaseApi.baseUrlNoApi + briefRefreshBean.getData().getBasicInfo().getHeadImg(), headIv);
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
                                blockPosition.setText(briefRefreshBean.getData().getBlockchainInfo().getPosition());
                                blockTime.setText(briefRefreshBean.getData().getBlockchainInfo().getTime());
                                ConfigBean configBean = App.getConfigBean();
                                if(configBean!=null&&configBean.getData()!=null)
                                {
                                    List<LangChildBean.DbSelectInputStandardsBean.BlockchainIdentityLocationsBean> blockchainIdentityLocations = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getBlockchainIdentityLocations();
                                    for (int i = 0; i < blockchainIdentityLocations.size(); i++) {
                                        if (briefRefreshBean.getData().getBlockchainInfo().getIdentityLocation() == blockchainIdentityLocations.get(i).getDbKey()) {
                                            identityLocation.setText(blockchainIdentityLocations.get(i).getLangValue());
                                        }
                                    }
                                }
                            }

                        }
                        else
                        {
                            T.showShort(briefRefreshBean.getMessage());
                        }
                    }
                }




            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, ct).briefRefresh();
    }

    @OnClick({R.id.addWork, R.id.addEdu, R.id.blockLl, R.id.basicInfoLl, R.id.back, R.id.llModify})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addWork:
//                ct.startActivity(WorkExpeActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent3 = new Intent(getActivity(), WorkExpeActivity.class);
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
            case R.id.addEdu:
//                ct.startActivity(EduExpeActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString(Constant.EDITORS, Constant.SWITCH_NO);
                Intent intent2 = new Intent(getActivity(), EduExpeActivity.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.blockLl:
                ct.startActivity(BlockChainActivity.class);
                break;
            case R.id.llModify:
            case R.id.basicInfoLl:
                if(briefRefreshBean!=null&&(briefRefreshBean.getData()!=null))
                {
                    //                ct.startActivity(BasicInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("PositionTitle",briefRefreshBean.getData().getBasicInfo().getPositionTitle());
                    bundle.putString("_id",briefRefreshBean.getData().getBasicInfo().get_id());
                    bundle.putString("Company",briefRefreshBean.getData().getBasicInfo().getCompany());
                    bundle.putInt("ProfessionalIdentity",briefRefreshBean.getData().getBasicInfo().getProfessionalIdentity());
                    bundle.putString("RealName",briefRefreshBean.getData().getBasicInfo().getRealName());
                    bundle.putString("Position",briefRefreshBean.getData().getBasicInfo().getPosition());
                    bundle.putString("School",briefRefreshBean.getData().getBasicInfo().getSchool());
                    bundle.putString("Education",briefRefreshBean.getData().getBasicInfo().getEducation()+"");
                    bundle.putString("Country",briefRefreshBean.getData().getBasicInfo().getCountry());
                    bundle.putString("Number",briefRefreshBean.getData().getBasicInfo().getNumber());
                    bundle.putString("Nature",briefRefreshBean.getData().getBasicInfo().getNature());
                    bundle.putString("HeadImg",briefRefreshBean.getData().getBasicInfo().getHeadImg());
//                bundle.putSerializable("123", briefRefreshBean.getData().getBasicInfo());
                    Intent intent = new Intent(getActivity(), BasicInfoActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.back:
                Objects.requireNonNull(getActivity()).finish();
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
            //相机获取照片并剪裁
            takePhoto.onPickFromCaptureWithCrop(imageUri, builderTake.create());
            //相机获取不剪裁
            //takePhoto.onPickFromCapture(uri);
        }
        if (num == 1) {
            //相册获取照片并剪裁
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
//        GlideUtils.displayFile(getContext(), image.getCompressPath(), headIv);
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
                GlideUtils.displayRound(getContext(), BaseApi.baseUrlNoApi + takePhotoBean.getData(), headIv);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "userHead");

    }

}
