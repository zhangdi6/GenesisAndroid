package com.iruiyou.pet.base;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

//import com.gyf.barlibrary.ImmersionBar;
import com.iruiyou.common.R;
import com.iruiyou.common.utils.DensityUtil;



import java.util.Objects;

/**
 * fragment基类
 * @author jiao 2015.12.2
 */
public abstract class BaseFragment extends Fragment implements OnClickListener {

    private boolean titleLoaded = false; // 标题是否加载成功
    private View titleLeftView;// 左控件
    private View titleView;// 整体控件
    private TextView tv_title;// 标题
    private TextView tvTitleRight;// 右标题
    private ImageView ivTitleRight;// 右图片
//    private ImmersionBar mImmersionBar;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        translucentStatusBar();
//        initStatusBar();

        loadTitle();
        OnActCreate(savedInstanceState);
        hideLeft();
    }

    /**
     * 沉浸式状态栏
     */
    private void translucentStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = Objects.requireNonNull(getActivity()).getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getActivity().getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = Objects.requireNonNull(getActivity()).getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 初始化状态栏
     */
    private void initStatusBar() {
        //状态栏一体化
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = Objects.requireNonNull(getActivity()).getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;

            winParams.flags |= bits;

            win.setAttributes(winParams);
        }
    }
    /************************* 重写方法区 *****************************/

    /**
     * 子类OnCreate方法
     *
     * @param savedInstanceState
     */
    public abstract void OnActCreate(Bundle savedInstanceState);

    /**
     * 控件的点击事件
     *
     * @param v
     */
    public void OnViewClick(View v){}

    /*********************** 父类方法区 ******************************/

    /**
     * 加载标题
     */
    private void loadTitle() {
        titleLeftView = Objects.requireNonNull(getView()).findViewById(R.id.ll_title_left_view);
        titleView = getView().findViewById(R.id.titleview);
        tv_title = getView().findViewById(R.id.title_name_text);
        tvTitleRight = getView().findViewById(R.id.title_right_text);
        ivTitleRight = getView().findViewById(R.id.title_right_img);
        if (titleView != null) {
            titleLeftView.setOnClickListener(this);
            titleLoaded = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                ViewGroup.LayoutParams params = titleView.getLayoutParams();
                params.height = params.height + DensityUtil.getStatusBarHeight();
                titleView.setLayoutParams(params);
            }
        }
    }

    /**
     * 隐藏输入法
     */
    public void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) Objects.requireNonNull(getActivity())
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (Objects.requireNonNull(imm).isActive() && getActivity().getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onViewCreated( View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    @Override
    public void onClick(View v) {
        // 过滤要处理的控件
//        switch (v.getId()) {
//            case R.id.ll_title_left_view:
//                // 返回按钮
//                getActivity().finish();
//                break;
//
//            default:
//                break;
//        }
        if (v.getId() == R.id.ll_title_left_view){
            Objects.requireNonNull(getActivity()).finish();
        }
        OnViewClick(v);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (titleLoaded) {
            tv_title.setText(title);
        }
    }

    /**
     * 隐藏标题
     */
    public void hideTitle() {
        if (titleLoaded) {
            titleView.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右图片
     *
     * @param resId
     */
    public void setRightImage(int resId) {
        if (titleLoaded) {
            ivTitleRight.setBackgroundResource(resId);
        }
    }

    /**
     * 隐藏右文字
     */
    public void hideRight() {
        if (titleLoaded) {
            tvTitleRight.setVisibility(View.GONE);
        }
    }

    /**
     * 设置右文字监听
     *
     * @param listener
     */
    public void setRightViewListener(OnClickListener listener) {
        if (titleLoaded) {
            tvTitleRight.setOnClickListener(listener);
        }
    }

    /**
     * 显示右文本
     */
    public void showRightText() {
        if (titleLoaded) {
            tvTitleRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        mImmersionBar.destroy();
    }

    /**
     * 设置右文本
     *
     * @param text
     */
    public void setRightText(String text) {
        if (titleLoaded) {
            showRightText();
            tvTitleRight.setText(text);

        }
    }

    public void setRightTextColor() {

        tvTitleRight.setTextColor(Color.parseColor("#ff7400"));
    }

    public void setRightTextListener(OnClickListener oo) {
        if (titleLoaded) {
            tvTitleRight.setOnClickListener(oo);
        }
    }

    public String getRightTextString() {
        return tvTitleRight.getText().toString();
    }

    /**
     * 隐藏左控件
     */
    public void hideLeft() {
        if (titleLoaded) {
            titleLeftView.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置左控件的点击事件
     */
    public void setLeftClickListener(OnClickListener listener) {
        if (titleLoaded) {
            titleLeftView.setOnClickListener(listener);
        }
    }

    /**
     * 给控件设置监听
     *
     * @param resId
     * @param listener
     */
    public View setViewClick(int resId, OnClickListener listener) {
        View view = Objects.requireNonNull(getView()).findViewById(resId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
        return view;
    }

    /**
     * 给控件设置监听
     *
     * @param resId
     */
    public View setViewClick(int resId) {
        return setViewClick(resId, this);
    }

    /**
     * 跳转一个界面不传递数据
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        Intent intent = new Intent();
        intent.setClass(Objects.requireNonNull(getActivity()), clazz);
        startActivity(intent);
    }

    /**
     * 是否全屏和显示标题，true为全屏和无标题，false为无标题，请在setContentView()方法前调用
     *
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        if (fullScreen) {
            Objects.requireNonNull(getActivity()).requestWindowFeature(Window.FEATURE_NO_TITLE);
            getActivity().getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            Objects.requireNonNull(getActivity()).requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

    }

//    /**
//     * Toast
//     */
//    public void showToast(String str) {
//        MyToast.showToast(getActivity(), str);
//    }
//
//    /**
//     * progressDialog
//     */
//    public void showProgressDialog() {
//        CustomProgress.show(getActivity(), "加载中...", true, null);
//    }
//
//    /**
//     * 自己填写信息的ProgressDialog
//     */
//    public void showProgressDialog(String message) {
//        if (message == null || message.equals("")) {
//            showProgressDialog();
//        } else {
//            CustomProgress.show(getActivity(), message, true, null);
//        }
//    }
//
//    /**
//     * AlertDialog
//     */
//    public void showAlertDialog(String title, String message,
//                                OnClickListener listener) {
//        new AlertDialog(getActivity()).builder().setTitle(title)
//                .setMsg(message).setPositiveButton("确认", listener)
//                .setNegativeButton("取消", new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
//    }
//
//    /**
//     * AlertDialog
//     */
//    public void showAlertDialog(String title, String message) {
//        new AlertDialog(getActivity()).builder().setTitle(title).setMsg(message)
//                .setPositiveButton("确认", null)
//                .setNegativeButton("取消", new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
//    }
//
//    /**
//     * AlertDialog
//     */
//    public void showAlertDialog(String message) {
//        new AlertDialog(getActivity()).builder().setMsg(message)
//                .setNegativeButton("确定", null).show();
//    }
//
//    /**
//     * AlertDialog
//     */
//    public void showAlertDialog(String message, OnClickListener listener) {
//        new AlertDialog(getActivity()).builder().setMsg(message)
//                .setNegativeButton("确定", listener).show();
//    }
//
//
//    /**
//     * 停止progressDialog
//     */
//    public void disProgressDialog() {
//        CustomProgress.hideProgress();
//    }
}
