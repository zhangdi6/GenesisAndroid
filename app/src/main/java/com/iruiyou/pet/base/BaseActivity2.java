package com.iruiyou.pet.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
//import com.umeng.analytics.MobclickAgent;
//import com.umeng.commonsdk.UMConfigure;

//import com.gyf.barlibrary.ImmersionBar;

/**
 * Activity的基类
 *
 * @author jiao 2015.12.2
 */
public abstract class BaseActivity2 extends RxAppCompatActivity implements
        OnClickListener {

    protected Context mContext;
//    public AsyncTaskManager mAsyncTaskManager;
//    protected SealAction action;

    private ViewFlipper mContentView;
    protected LinearLayout mHeadLayout;
    protected Button mBtnLeft;
    protected Button mBtnRight;
    protected TextView mTitle;
    protected TextView mHeadRightText;
    private Drawable mBtnBackDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        super.setContentView(R.layout.layout_base);
//        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
//        mContext = this;
//
//        // 初始化公共头部
//        mContentView = (ViewFlipper) super.findViewById(R.id.layout_container);
//        mHeadLayout = (LinearLayout) super.findViewById(R.id.layout_head);
//        mHeadRightText = (TextView) findViewById(R.id.text_right);
//        mBtnLeft = (Button) super.findViewById(R.id.btn_left);
//        mBtnRight = (Button) super.findViewById(R.id.btn_right);
//        mTitle = (TextView) super.findViewById(R.id.tv_title);
//        mBtnBackDrawable = getResources().getDrawable(R.drawable.ac_back_icon);
//        mBtnBackDrawable.setBounds(0, 0, mBtnBackDrawable.getMinimumWidth(),
//                mBtnBackDrawable.getMinimumHeight());


//        mAsyncTaskManager = AsyncTaskManager.getInstance(getApplicationContext());
        // Activity管理
//        action = new SealAction(mContext);

    }


//    @Override
//    public void setContentView(View view) {
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
//        mContentView.addView(view, lp);
//    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        View view = LayoutInflater.from(this).inflate(layoutResID, null);
//        setContentView(view);
//    }
//
//
//    /**
//     * 设置头部是否可见
//     *
//     * @param visibility
//     */
//    public void setHeadVisibility(int visibility) {
//        mHeadLayout.setVisibility(visibility);
//    }
//
//    /**
//     * 设置左边是否可见
//     *
//     * @param visibility
//     */
//    public void setHeadLeftButtonVisibility(int visibility) {
//        mBtnLeft.setVisibility(visibility);
//    }
//
//    /**
//     * 设置右边是否可见
//     *
//     * @param visibility
//     */
//    public void setHeadRightButtonVisibility(int visibility) {
//        mBtnRight.setVisibility(visibility);
//    }
//
//    /**
//     * 设置标题
//     */
//    public void setTitle(int titleId) {
//        setTitle(getString(titleId), false);
//    }
//
//    /**
//     * 设置标题
//     */
//    public void setTitle(int titleId, boolean flag) {
//        setTitle(getString(titleId), flag);
//    }
//
//    /**
//     * 设置标题
//     */
//    public void setTitle(String title) {
//        setTitle(title, false);
//    }
//
//    /**
//     * 设置标题
//     *
//     * @param title
//     */
//    public void setTitle(String title, boolean flag) {
//        mTitle.setText(title);
//        if (flag) {
//            mBtnLeft.setCompoundDrawables(null, null, null, null);
//        } else {
//            mBtnLeft.setCompoundDrawables(mBtnBackDrawable, null, null, null);
//        }
//    }
//
//    /**
//     * 点击左按钮
//     */
//    public void onHeadLeftButtonClick(View v) {
//        finish();
//    }
//
//    /**
//     * 点击右按钮
//     */
//    public void onHeadRightButtonClick(View v) {
//
//    }
//
//    public Button getHeadLeftButton() {
//        return mBtnLeft;
//    }
//
//    public void setHeadLeftButton(Button leftButton) {
//        this.mBtnLeft = leftButton;
//    }
//
//    public Button getHeadRightButton() {
//        return mBtnRight;
//    }
//
//    public void setHeadRightButton(Button rightButton) {
//        this.mBtnRight = rightButton;
//    }
//
//    public Drawable getHeadBackButtonDrawable() {
//        return mBtnBackDrawable;
//    }
//
//    public void setBackButtonDrawable(Drawable backButtonDrawable) {
//        this.mBtnBackDrawable = backButtonDrawable;
//    }
//
//    protected void onResume() {
//        super.onResume();
//        MobclickAgent.onResume(this);
//    }
//
//    protected void onPause() {
//        super.onPause();
//        MobclickAgent.onPause(this);
//    }
//
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
//            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
//        }
//        return super.onTouchEvent(event);
//    }
}
