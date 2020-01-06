package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.GridViewAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MyGridView;
import com.iruiyou.pet.utils.PictureSelectorConfig;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.OnClick;

/**
 * 发现-发布
 * 作者：sgf
 */
public class ReleaseActivity extends BaseActivity {
    //    @BindView(R.id.title_right_text)
    TextView titleRightText;
    //    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    //    @BindView(R.id.title_name_text)
    TextView titleNameText;
//    @BindView(R.id.edit)
//    ImageView edit;
//    @BindView(R.id.ll_title_left_view)
//    LinearLayout llTitleLeftView;
//    @BindView(R.id.titleview)
//    RelativeLayout titleview;

    //    @BindView(R.id.gridview_release)
    MyGridView gridview_release;
    //    @BindView(R.id.tv_release_num)
    TextView tv_release_num;
    //    @BindView(R.id.et_release_content)
    EditText et_release_content;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private Context context;
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器

    private String path = Environment.getExternalStorageDirectory() +
            File.separator + Environment.DIRECTORY_DCIM + File.separator;
    public static String DIRECTORY_DCIM = "DCIM";
    private Uri photoUri;

    @Override
    public int getLayout() {
        return R.layout.activity_release;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
//        ButterKnife.bind(this);
        titleNameText = findViewById(R.id.title_name_text);
        titleRightText = findViewById(R.id.title_right_text);
        titleRightImg = findViewById(R.id.title_right_img);
        gridview_release = findViewById(R.id.gridview_release);
        tv_release_num = findViewById(R.id.tv_release_num);
        et_release_content = findViewById(R.id.et_release_content);
        context = ReleaseActivity.this;
//        setTitle(getResources().getString(R.string.release));
//        setRightImage(R.drawable.find_send);

        setRightText("发布");
        setRightBg(R.drawable.shape_329cd7);
//        setRightImage();
        setTitle("发布动态");
        initDta();
        initGridView();
    }
//@Override
//protected void onCreate(Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setContentView(R.layout.activity_release);
//
//    titleNameText = findViewById(R.id.title_name_text);
//    titleRightText = findViewById(R.id.title_right_text);
//    titleRightImg = findViewById(R.id.title_right_img);
//    gridview_release = findViewById(R.id.gridview_release);
//    tv_release_num = findViewById(R.id.tv_release_num);
//    et_release_content = findViewById(R.id.et_release_content);
////    ButterKnife.bind(this);
//        context = ReleaseActivity.this;
////        setTitle(getResources().getString(R.string.release));
//        titleNameText.setText(getString(R.string.release));
////        setRightImage(R.drawable.find_send);
//        titleRightImg.setImageResource(R.drawable.find_send);
//        initDta();
//        initGridView();
//}

    private void initDta() {
        et_release_content.setSelection(et_release_content.getText().toString().length());
        et_release_content.addTextChangedListener(etContentWatcher);
        setRightViewListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<File> uploadFiles=new ArrayList<>();

                if(et_release_content.getText().toString().trim().length()>0){
                    String content=et_release_content.getText().toString().trim();
                    if(mPicList.size()>0){
                        for(String path:mPicList){
                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                            if(bitmap!=null){
                                String fileName = bitmap.getWidth()+"*"+bitmap.getHeight()+".jpg";
                                File file=new File(path);
                                String parentPath=file.getParent()+"/";
                                File uploadFile=new File(parentPath+fileName);
                                file.renameTo(uploadFile);
                                uploadFiles.add(uploadFile);
                            }
//                            GlobalLog.e("test","fileName is "+fileName+"file parent is "+file.getParent());
//                            file.renameTo()
                        }
                    }
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            if(StringUtil.isNotEmpty(resulte)){
                                BaseBean baseBean = GsonUtil.GsonToBean(resulte, BaseBean.class);
                                if(baseBean!=null){
                                    if(Constant.SUCCESS==baseBean.getStatusCode()){
                                        finish();
                                        SoftKeyboardUtils.hideSoftKeyboard(ReleaseActivity.this);
                                    } else if(StringUtil.isNotEmpty(baseBean.getMessage())){
                                        T.showShort(baseBean.getMessage());
                                    }
                                }
//                                Log.e("test","resulte  is "+resulte);
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            e.printStackTrace();
                        }
                    }, ReleaseActivity.this).publishEssaysForm(uploadFiles,content);
                }else {
                    T.showShort("请输入发布的内容");
                }
            }
        });
    }

    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "IMG_" + dateFormat.format(date);
    }

    //初始化展示上传图片的GridView
    private void initGridView() {
        mGridViewAddImgAdapter = new GridViewAdapter(context, mPicList);
        gridview_release.setAdapter(mGridViewAddImgAdapter);
        gridview_release.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == Constant.MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(Constant.MAX_SELECT_PIC_NUM - mPicList.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(context, PlusImageActivity.class);
        intent.putStringArrayListExtra(Constant.IMG_LIST, mPicList);
        intent.putExtra(Constant.POSITION_PIC, position);
        startActivityForResult(intent, Constant.REQUEST_CODE_MAIN);
    }

    /**
     * 发布内容输入框监听
     */
    private TextWatcher etContentWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tv_release_num.setText(String.valueOf(et_release_content.getText().length()));
        }
    };

    @OnClick({R.id.title_right_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 打开相册或者照相机选择凭证图片，最多9张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
//        PictureSelectorConfig.initMultiConfig(this, maxTotal);
        actionSheetDialog(maxTotal);
//        TakePhoto takePhoto = getTakePhoto();
//        CropOptions.Builder builderTake = new CropOptions.Builder();
//        builderTake.setAspectX(800).setAspectY(800);
//        builderTake.setWithOwnCrop(true);
//        File file = new File(Environment.getExternalStorageDirectory(),
//                "/temp/" + System.currentTimeMillis() + ".jpg");
//        if (!file.getParentFile().exists()) {
//            boolean mkdirs = file.getParentFile().mkdirs();
//            if (!mkdirs) {
//                T.showShort(getResources().getString(R.string.FileDirectoryCreationFailed));
//            }
//        }
//
//        Uri imageUri = Uri.fromFile(file);
//        CompressConfig config = new CompressConfig.Builder()
////                .setMaxSize(1024 * 2)
//                .setMaxPixel(400)
//                .enableReserveRaw(true)
//                .create();
//        takePhoto.onEnableCompress(config, true);
//            //相机获取照片并剪裁
//            takePhoto.onPickFromCaptureWithCrop(imageUri, builderTake.create());
        //相机获取不剪裁
        //takePhoto.onPickFromCapture(uri);

    }

    private void actionSheetDialog(int maxTotal) {

        ActionSheet.createBuilder(context, getSupportFragmentManager())
                .setCancelButtonTitle(getString(R.string.cancel))
                .setOtherButtonTitles(getString(R.string.Camera), getString(R.string.gallery))
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                String state = Environment.getExternalStorageState();
                                if (state.equals(Environment.MEDIA_MOUNTED)) {
                                    File file = new File(path);
                                    if (!file.exists()) {
                                        file.mkdir();
                                    }
                                    String fileName = getPhotoFileName() + ".jpg";
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    photoUri = Uri.fromFile(new File(path + fileName));
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                    startActivityForResult(intent, 1);
                                }
                                break;
                            case 1:
                                PictureSelectorConfig.initMultiConfig(ReleaseActivity.this, maxTotal);
                                break;
                            default:
                                break;
                        }
                    }
                })
                .show();
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == Constant.REQUEST_CODE_MAIN && resultCode == Constant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(Constant.IMG_LIST); //要删除的图片的集合
            mPicList.clear();
            mPicList.addAll(toDeletePicList);
            mGridViewAddImgAdapter.notifyDataSetChanged();
        }
        if (requestCode == 1) {//系统相机
            Uri uri = null;
            if (data != null && data.getData() != null) {
                uri = data.getData();
            }
            if (uri == null) {
                if (photoUri != null) {
                    uri = photoUri;
                    mPicList.add(photoUri.getPath());
                    mGridViewAddImgAdapter.notifyDataSetChanged();
                }
            }
        }
    }
//    @Override
//    public void takeSuccess(TResult result) {
//        super.takeSuccess(result);
//        TImage image = result.getImage();
//        if (image.getCompressPath() == null) {
//            T.showShort(getResources().getString(R.string.FailedToGetPhotos));
//            return;
//        }
////        GlideUtils.displayFile(context, image.getCompressPath(), headIv);
//        String compressPath = image.getCompressPath();
//        mPicList.add(compressPath);
//        mGridViewAddImgAdapter.notifyDataSetChanged();
////        upImg(new File(image.getCompressPath()));
//    }
}
