package com.iruiyou.pet.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.http.retrofit_rx.listener.upload.ProgressRequestBody;
import com.iruiyou.http.retrofit_rx.listener.upload.UploadProgressListener;
import com.iruiyou.http.retrofit_rx.utils.L;
import com.iruiyou.pet.R;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
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
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 类描述:
 * 创建日期:2018/5/21 on 16:14
 * 作者:JiaoPeiRong
 */
public class AddPetFragment extends TakePhotoFragment {
    @BindView(R.id.add)
    ImageView add;
    Unbinder unbinder;
    @BindView(R.id.spinner0)
    Spinner spinner0;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.sure)
    Button sure;
    private Object type;
    private JSONObject data;
    private HashMap<String, String> animalHashMap;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_add, null, false);
        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getType();
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UserTask(new HttpOnNextListener() {

                    @Override
                    public void onNext(String resulte, String method) {
                        T.showShort(resulte);
                        L.json(resulte);
                    }

                    @Override
                    public void onError(ApiException e) {
                        T.showShort(e.getMessage());
                    }
                }, (RxAppCompatActivity) getActivity()).addPet("猫", "橘猫", "\\/img\\/upload\\/image\\/1526979590028_6.jpg", "乐乐", "男", "bbbfun");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.add)
    public void onViewClicked() {
        initTakePhoto();

    }

    private void initTakePhoto() {
        TakePhoto takePhoto = getTakePhoto();
        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setAspectX(800).setAspectY(800);
        builder.setWithOwnCrop(true);
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
                .setMaxSize(1024 * 2)
                .setMaxPixel(400)
                .enableReserveRaw(true)
                .create();
        takePhoto.onEnableCompress(config, true);
//        takePhoto.onPickFromCaptureWithCrop(imageUri, builder.create());
        takePhoto.onPickFromDocumentsWithCrop(imageUri, builder.create());
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        T.showShort("success");
        TImage image = result.getImage();
        Glide.with(this).load(new File(image.getCompressPath())).into(add);
        upImg(new File(image.getCompressPath()));
    }

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
                T.showShort(resulte);
                L.json(resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "");

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        T.showShort("fail");
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        T.showShort("cancel");
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
                setSpinner0();
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (RxAppCompatActivity) getActivity()).petType();
    }

    /**
     * 配置第一个Spinner
     */
    private void setSpinner0() {
        List<String> list = new ArrayList<>();
        final List<String> keys = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = animalHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            list.add(next.getValue());
            keys.add(next.getKey());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner0.setAdapter(adapter);
        spinner0.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                String s = keys.get(pos);
                setSpinner1(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 设置第二个Spinner
     */
    private void setSpinner1(String key) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject jsonObject = data.getJSONObject(key);
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                list.add((String) jsonObject.get(next));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
