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

import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MyAssetsActivity;
import com.iruiyou.pet.activity.UrlWebActivity;
import com.iruiyou.pet.bean.UserBean;
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
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类描述:
 * 创建日期:2018/5/26 on 14:02
 * 作者:JiaoPeiRong
 */
public class UserFragment extends TakePhotoFragment {
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
    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.upImg)
    RelativeLayout upImg;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.petTypeLl)
    LinearLayout petTypeLl;
    Unbinder unbinder;
    @BindView(R.id.codeTv)
    TextView codeTv;
    @BindView(R.id.petCode)
    LinearLayout petCode;
    @BindView(R.id.pets)
    TextView pets;
    @BindView(R.id.myPets)
    LinearLayout myPets;

    public static UserFragment newInstance() {
        UserFragment f = new UserFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user, null, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ACache aCache = ACache.get(getContext());
        titleNameText.setText("用户信息");
        userName.setText(SharePreferenceUtils.getBaseSharePreference().readNickName());
        phone.setText(SharePreferenceUtils.getBaseSharePreference().readAccount());
        UserBean userBean = (UserBean) aCache.getAsObject(TagConstants.UserTag);
        codeTv.setText(userBean.getData().getInviteCode());
        pets.setText(userBean.getData().getPetsAmount() + "");
        GlideUtils.displayRound(getContext(), BaseApi.baseUrl + userBean.getData().getHeadImg(), head);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.head, R.id.ll_title_left_view, R.id.petCode, R.id.myPets})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.head:
                imgAlert();
                break;
            case R.id.ll_title_left_view:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.petCode:
                Intent ii = new Intent(getContext(), UrlWebActivity.class);
                ii.putExtra(TagConstants.WEBURL, BaseApi.baseUrl + UrlContent.invite + "?userId=" +
                        SharePreferenceUtils.getBaseSharePreference().readUserId() + "&token=" +
                        SharePreferenceUtils.getBaseSharePreference().readToken());
                startActivity(ii);
                break;
            case R.id.myPets:
                Intent i = new Intent(getContext() , MyAssetsActivity.class);
                startActivity(i);
                break;
        }
    }

    /******************************以下为拍照的逻辑****************************************/
    /**
     * 拍照弹出框
     */
    private void imgAlert() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setTitle("选择照片");
        final int[] position = {0};
        builder.setSingleChoiceItems(new String[]{"照相机", "图库"}, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position[0] = which;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                takePhoto(position[0]);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
                T.showShort("文件目录创建失败");
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
            T.showShort("获取照片失败");
            return;
        }
        GlideUtils.displayFile(getContext(), image.getCompressPath(), head);
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
//                TakePhotoBean takePhotoBean = GsonUtils.parseJson(resulte, TakePhotoBean.class);
//                EventBusUtils.getInstance().postEvent(new HeadEventPost());

            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "userHead");

    }

}
