package com.iruiyou.pet.fragment;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.iruiyou.pet.activity.RegisterLastActivity2;
import com.iruiyou.pet.activity.registerlast.OccupationAdapter;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.RegisterPresenter;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.GalleryAdapter;
import com.iruiyou.pet.bean.HeadEventPost;
import com.iruiyou.pet.bean.MustBasicBean;
import com.iruiyou.pet.bean.TakePhotoBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.StringUtil;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoFragment;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 注册最后一步
 * Created by jiao on 2017/4/22.
 */
public class RegisterLastFragment extends TakePhotoFragment {

    @BindView(R.id.linear_position)
    LinearLayout linearPosition;
    @BindView(R.id.linear_commpany)
    LinearLayout linearCommpany;
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.ll_title_left_view)
    LinearLayout ll_title_left_view;
    @BindView(R.id.etRegisterCommanyPosition)
    TextInputEditText etRegisterCommanyPosition;
    @BindView(R.id.etRegisterCommanyName)
    TextInputEditText etRegisterCommanyName;
    @BindView(R.id.etRegisterName)
    TextInputEditText etRegisterName;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.tvRegisterCompleteLast)
    TextView tvRegisterCompleteLast;
    @BindView(R.id.llHead)
    LinearLayout llHead;
    @BindView(R.id.imHead)
    ImageView imHead;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    private List<CheckBean> list;
    private List<Integer> mDatas;
    private GalleryAdapter mAdapter;
    private RegisterLastActivity2 ct;
//    private static int b = 0;
    private String[] languageName;
//    private int dbKye = 0;
    private OccupationBeen occupationBeen;
    public static final int DBKEY_FREE=15;
    //加载控制器
    RegisterPresenter registerPresenter = new RegisterPresenter();
    private String fileName;
    private TakePhotoBean takePhotoBean;

    public static RegisterLastFragment newInstance() {
        RegisterLastFragment f = new RegisterLastFragment();
        return f;
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_registered_last, null);
        ButterKnife.bind(this, view);
        //监听back必须设置的
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        //拦截物理返回键
        view.setOnKeyListener(backlistener);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ll_title_left_view.setVisibility(View.INVISIBLE);
        ct = (RegisterLastActivity2) getContext();
        //绑定上下文
        registerPresenter.IPresenter(getActivity());
        String nationalCode = Objects.requireNonNull(getActivity()).getIntent().getStringExtra("nationalCode");
        String phone = getActivity().getIntent().getStringExtra("phone");
        String smsCode = getActivity().getIntent().getStringExtra("smsCode");
        String password = getActivity().getIntent().getStringExtra("password");
        String invitationCode = getActivity().getIntent().getStringExtra("invitationCode");
        titleNameText.setText(getResources().getString(R.string.editing_materials));

        initDatas();
        initData();
    }

    /**
     * 拦截物理返回键
     */
    private View.OnKeyListener backlistener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                //这边判断,如果是back的按键被点击了   就自己拦截实现掉
                //                    Toast.makeText(getActivity(), "BACK拦截", Toast.LENGTH_SHORT).show();
                //表示处理了
                return i == KeyEvent.KEYCODE_BACK;
            }
            return false;
        }
    };
    OccupationAdapter occupationAdapter;
    ArrayList<OccupationBeen> occupationBeens = new ArrayList<>();

    private void initData() {

        //获取缓存的数据-职业
//        ConfigBean configBean = (ConfigBean) ACache.get(getActivity()).getAsObject(TagConstants.config);
//        List<LangChildBean.DbSelectInputStandardsBean.ProfessionalIdentitiesBean> professionalIdentities = configBean.getData().getLang().getCurrentLang().getDbSelectInputStandards().getProfessionalIdentities();
//        professionalIdentities.remove(0);
        etRegisterName.addTextChangedListener(nameWatcher);
        etRegisterCommanyName.addTextChangedListener(nameWatcher);
        etRegisterCommanyPosition.addTextChangedListener(nameWatcher);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //第一个参数横向间距，第二个参数纵向间距，根据需求具体来看
        int spanCount = 3; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = false;
//        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        recyclerView.addItemDecoration(new SpacesItemDecoration(5));

        //请求职业
        registerPresenter.getOccupationsList(new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if (o != null) {
                    // 这块儿的逻辑（接受接口数据） 根据你们的实际业务调整
                    occupationBeens.addAll((ArrayList<OccupationBeen>) o);
                    if (occupationAdapter == null) {
                        occupationAdapter = new OccupationAdapter(getActivity(), occupationBeens,1);
                        recyclerView.setAdapter(occupationAdapter);
                        occupationAdapter.setItemClickListener(new OccupationAdapter.MyItemClickListener() {
                            @Override
                            public void onItemClick(OccupationBeen firstPageListBean, int position) {
                                if(occupationBeen!=null)
                                {
                                    occupationBeen.setSelect(false);
                                }
                                occupationBeen=occupationBeens.get(position);
                                occupationBeen.setSelect(true);
                                if(occupationBeen.getDbKey()==DBKEY_FREE)
                                {
                                    linearPosition.setVisibility(View.GONE);
                                    linearCommpany.setVisibility(View.GONE);
                                }
                                else
                                {
                                    linearPosition.setVisibility(View.VISIBLE);
                                    linearCommpany.setVisibility(View.VISIBLE);
                                }
                                judgeBackground();
                                occupationAdapter.notifyDataSetChanged();
                            }
                        });
                    } else {
                        occupationAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void filed(Exception e) {

            }
        });

        //设置适配器
//        mAdapter = new GalleryAdapter(getActivity(), mDatas,list);
//        recyclerView.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new GalleryAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                mAdapter.selfselect(position);
//            }
//        });

    }

    private void initDatas() {
//        list = new ArrayList<>();
        languageName = new String[]{"123", "434", "434", "434", "434", "434", "434", "434", "434"};
        mDatas = new ArrayList<>(Arrays.asList(R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher,
                R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher));
//        for (int i = 0; i < mDatas.size(); i++) {
//            if (i == 0) {//默认选中第一个
//                list.add(new CheckBean(languageName[i], true));
//                continue;
//            }
//            list.add(new CheckBean(languageName[i], false));
//        }
    }

    /**
     * 手机号码监听
     */
    private TextWatcher nameWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            judgeBackground();
        }
    };

    /**
     * 判断选项显示按钮状态
     */
    private void judgeBackground(){
        String name=etRegisterName.getText().toString().trim();
        String positionName=etRegisterCommanyPosition.getText().toString().trim();
        String commpanyName=etRegisterCommanyName.getText().toString().trim();
        boolean isEnable=false;
        if(occupationBeen!=null)
        {
//            if(StringUtil.isNotEmpty(name)&&(takePhotoBean!=null)&&(StringUtil.isNotEmpty(takePhotoBean.getData())))
            if(StringUtil.isNotEmpty(name))
            {
                if(occupationBeen.getDbKey()==DBKEY_FREE)
                {
                    isEnable=true;
                }
                else if(StringUtil.isNotEmpty(positionName)&& StringUtil.isNotEmpty(commpanyName))
                {
                    isEnable=true;
                }
            }
        }

        if(isEnable)
        {
            setLoginBackground(true, R.drawable.bt_psw_details_shape2);
        }
        else
        {
            setLoginBackground(false, R.drawable.bt_psw_details_shape);
        }
    }
    /**
     * 设置登录按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setLoginBackground(boolean type, int background) {
        tvRegisterCompleteLast.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        tvRegisterCompleteLast.setBackgroundDrawable(drawable);
    }

    @OnClick({R.id.ll_title_left_view, R.id.tvRegisterCompleteLast, R.id.imHead})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                Objects.requireNonNull(getActivity()).finish();
                break;
            case R.id.tvRegisterCompleteLast:
                //网络请求
                setMustBasic();
                break;
            case R.id.imHead:
                imgAlert();
                break;
        }
    }

    /**
     * 请求资料编辑
     */
    private void setMustBasic() {
        if(occupationBeen!=null)
        {
            String headImagePath="";
            if(takePhotoBean!=null&& StringUtil.isNotEmpty(takePhotoBean.getData()))
            {
                headImagePath=takePhotoBean.getData();
            }

            DialogUtil.getInstance().showLoadingDialog(this.getActivity());
            String positionName=etRegisterCommanyPosition.getText().toString().trim();
            String commpanyName=etRegisterCommanyName.getText().toString().trim();
            String name=etRegisterName.getText().toString().trim();
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    DialogUtil.getInstance().closeLoadingDialog();
                    handleResult(resulte);
                }

                @Override
                public void onError(ApiException e) {
                    DialogUtil.getInstance().closeLoadingDialog();
                    T.showShort(e.getMessage());
                }
            }, (RegisterLastActivity2)getContext()).mustBasic(name,headImagePath, occupationBeen.getDbKey(),commpanyName,positionName);
        }
    }

    private void handleResult(String result) {
        //                String a = "img/upload/userHeadImages/"+fileName;
        MustBasicBean mustBasicBean = GsonUtils.parseJson(result, MustBasicBean.class);
        T.showShort(mustBasicBean.getMessage());
        if (mustBasicBean.getStatusCode() == Constant.SUCCESS) {
//                    SharePreferenceUtils.getBaseSharePreference().saveShowEdit(mustBasicBean.getData().getBasicInfo().getShowEdit());
//                    SharePreferenceUtils.getBaseSharePreference().saveToken(mustBasicBean.getToken());
//                    SharePreferenceUtils.getBaseSharePreference().saveAccount(mustBasicBean.getData().getUserInfo().getPhone());
//                    SharePreferenceUtils.getBaseSharePreference().saveInviteCode(mustBasicBean.getData().getUserInfo().getInviteCode());
////                    SharePreferenceUtils.getBaseSharePreference().saveAccount(phone.getText().toString());
////                    SharePreferenceUtils.getBaseSharePreference().savePassword(password.getText().toString());
            SharePreferenceUtils.getBaseSharePreference().saveNickName(mustBasicBean.getData().getRealName());
////                            aCache.put(TagConstants.LoginTag, loginBean);
////                            LoginBean loginBean1 = (LoginBean) aCache.getAsObject(TagConstants.LoginTag);
            SharePreferenceUtils.getBaseSharePreference().saveUserId(mustBasicBean.getData().getUserId());
            SharePreferenceUtils.getBaseSharePreference().saveShowEdit(mustBasicBean.getData().getShowEdit());
//                    String s = SharePreferenceUtils.getBaseSharePreference().readToken();
//                    SharePreferenceUtils.getBaseSharePreference().saveCountryCode(mustBasicBean.getData().getUserInfo().getCountryCode());
            StartActivityManager.startMainActivity(getActivity());
            Objects.requireNonNull(getActivity()).finish();

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
                return;
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
        GlideUtils.displayFile(getContext(), image.getCompressPath(), imHead);
        DialogUtil.getInstance().showLoadingDialog(this.getActivity());
        upImg(new File(image.getCompressPath()));
    }


    /**
     * 头像上传
     *
     * @param file
     */
    private void upImg(File file) {
        fileName = file.getName();
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
                EventBusUtils.getInstance().postEvent(new HeadEventPost());
//                T.showShort(takePhotoBean.getMessage());
                DialogUtil.getInstance().closeLoadingDialog();
//                judgeBackground();
            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, (RxAppCompatActivity) getActivity()).upImg(requestBody, part, "userHead");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

}
