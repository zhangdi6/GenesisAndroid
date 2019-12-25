package com.iruiyou.pet.fragment;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.EventBusUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.AddPetBean;
import com.iruiyou.pet.bean.AddPetEvent;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.ta.utdid2.android.utils.StringUtils;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类描述:
 * 创建日期:2018/5/21 on 16:14
 * 作者:JiaoPeiRong
 */
public class AddPetFragment2 extends TakePhotoFragment {

    @BindView(R.id.head)
    ImageView head;
    @BindView(R.id.petTypeTv)
    TextView petTypeTv;
    @BindView(R.id.petTypeLl)
    LinearLayout petTypeLl;
    @BindView(R.id.petNameTv)
    TextView petNameTv;
    @BindView(R.id.petNameLl)
    LinearLayout petNameLl;
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
    @BindView(R.id.upImg)
    RelativeLayout upImg;
    @BindView(R.id.petNameEt)
    EditText petNameEt;
    @BindView(R.id.redioMan)
    RadioButton redioMan;
    @BindView(R.id.radioWoman)
    RadioButton radioWoman;
    @BindView(R.id.group)
    RadioGroup group;
    @BindView(R.id.cardView)
    CardView cardView;
    private JSONObject data;
    private HashMap<String, String> animalHashMap;
    private List<String> petNameList;
    private List<String> petNameKeyList;
    private String sex = "1";
    private String petTypeStr;
    private String petNameStr;
    private TakePhotoBean takePhotoBean;
    private String petTypeKey;
    private String petNameKey;


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activity_add_pet, null, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        redioMan.setChecked(true);
        titleNameText.setText("添加宠物");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        T.showShort("获取照片失败");
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        T.showShort("取消");
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
                takePhotoBean = GsonUtils.parseJson(resulte, TakePhotoBean.class);

            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "petHead");

    }


    @OnClick({R.id.ll_title_left_view, R.id.upImg, R.id.petTypeLl, R.id.petNameLl, R.id.redioMan, R.id.radioWoman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.upImg:
                ImgAlert();

                break;
            case R.id.petTypeLl:
                getType();
                break;
            case R.id.petNameLl:
                showChoise("宠物品种", petNameList, 0, null);
                break;
            case R.id.redioMan:
                if (redioMan.isChecked()) {
                    sex = "1";
//                    T.showShort("男");
                }
                break;
            case R.id.radioWoman:
                if (radioWoman.isChecked()) {
                    sex = "0";
//                    T.showShort("女");
                }
                break;
        }
    }

    /**
     * 拍照弹出框
     */
    private void ImgAlert() {
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
     * alertdialog
     *
     * @param title  标题
     * @param list   数据解析map 的 value 集合
     * @param select 默认选中
     * @param keys   数据解析map 的 key 集合
     */
    private void showChoise(String title, final List<String> list, int select, final List<String> keys) {
        if (list == null || list.size() == 0) {
            T.showShort("没有数据~");
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle(title);
        String[] strings = new String[list.size()];
        list.toArray(strings);
        //    指定下拉列表的显示数据
        final int[] position = {select};
        builder.setSingleChoiceItems(strings, select, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                position[0] = which;
                if (keys != null) {
                    String key = keys.get(which);
                    petNameList = new ArrayList<>();
                    petNameKeyList = new ArrayList<>();
                    try {
                        JSONObject jsonObject = data.getJSONObject(key);
                        Iterator<String> keys = jsonObject.keys();
                        while (keys.hasNext()) {
                            String next = keys.next();
                            petNameKeyList.add(next);
                            petNameList.add((String) jsonObject.get(next));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //设置正面按钮
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String s = list.get(position[0]);
                //说明是宠物类型
                if (keys != null) {
                    petTypeKey = keys.get(position[0]);
                    petTypeStr = s;
                    petTypeTv.setText(s);
                    //说明是宠物名称
                } else {
                    petNameKey = petNameKeyList.get(position[0]);
                    petNameStr = s;
                    petNameTv.setText(s);
                }
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 获取宠物类型
     */
    public void getType() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                animalHashMap = new HashMap<>();
//                PetTypeBean petTypeBean = GsonUtils.parseJson(resulte, PetTypeBean.class);
                try {
                    JSONObject jsonObject = new JSONObject(resulte);
                    data = jsonObject.getJSONObject("data");
                    //取出animal里面的值
                    JSONObject jsonAnimal = data.getJSONObject("animal");
                    Iterator<String> keys = jsonAnimal.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        String value = jsonAnimal.getString(key);
                        animalHashMap.put(key, value);
                    }
                    //取出其他几个的值

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<String> values = new ArrayList<>();
                final List<String> keys = new ArrayList<>();
                Iterator<Map.Entry<String, String>> iterator = animalHashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    values.add(next.getValue());
                    keys.add(next.getKey());
                }
                showChoise("选择宠物类型", values, 0, keys);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).petType();
    }

    @OnClick(R.id.cardView)
    public void onViewClicked() {
        if (takePhotoBean == null) {
            T.showShort("请选择照片");
            return;
        }
        if (StringUtils.isEmpty(petNameEt.getText().toString())) {
            T.showShort("宠物名称不能为空");
            return;
        }
        if (StringUtils.isEmpty(petTypeKey)) {
            T.showShort("宠物种类不能为空");
            return;
        }
        if (StringUtils.isEmpty(petNameKey)) {
            T.showShort("宠物品种不能为空");
            return;
        }
        new UserTask(new HttpOnNextListener() {

            @Override
            public void onNext(String resulte, String method) {
                AddPetBean addPetBean = GsonUtils.parseJson(resulte, AddPetBean.class);
                T.showShort(addPetBean.getMessage());
                if (addPetBean.getStatusCode() == 0){
                    EventBusUtils.getInstance().postEvent(new AddPetEvent());
                    Objects.requireNonNull(getActivity()).finish();
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (RxAppCompatActivity) getActivity()).addPet(petTypeKey, petNameKey, takePhotoBean.getData()
                , petNameEt.getText().toString(), sex, SharePreferenceUtils.getBaseSharePreference().readNickName());

    }
}
