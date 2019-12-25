package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baijiayun.playback.util.DisplayUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:职位,学历等信息
 * 创建日期:2018/8/14 on 17:28
 * 作者:JiaoPeiRong
 */
public class IdentityInfoActivity extends BaseActivity {

    @BindView(R.id.linear_length)
    LinearLayout linearLength;
    @BindView(R.id.cancel)
    LinearLayout cancel;
    @BindView(R.id.tvIdentityNum)
    TextView tvIdentityNum;
    @BindView(R.id.tvIdentityEndNum)
    TextView tvIdentityEndNum;
    @BindView(R.id.sure)
    LinearLayout sure;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.etWorkConten)
    EditText etWorkConten;
    private String tag;
    private String workFlag;
    private Context context;
    private Timer timer;

    @Override
    public int getLayout() {
        return R.layout.activity_identity_info;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        context = IdentityInfoActivity.this;
        ImmersionBar.with(this).statusBarDarkFont(false).statusBarColor(R.color.colorPrimary).fitsSystemWindows(true).init();
        Intent intent=getIntent();
        tag = intent.getStringExtra(Constant.IDENTITY);
        workFlag = getIntent().getStringExtra(Constant.WORKFLAG);//获取工作经验中传来的flag,用于判断区分工作内容的布局显示
        String title = getIntent().getStringExtra(Constant.TITLE);
        titleTv.setText(title);
        String detail = getIntent().getStringExtra(Constant.DETAIL);
        if (workFlag.equals(Constant.WORK)
                || workFlag.equals(Constant.DESCRIBE)
                || workFlag.equals(Constant.EXPERIENCES)) {//若是工作内容的入口进来就显示对用的布局形式
            etWorkConten.setVisibility(View.VISIBLE);
            content.setVisibility(View.GONE);
            tvIdentityEndNum.setText("/800");
            if (!detail.equals(getString(R.string.pleaseFillIn))) {
                etWorkConten.setText(detail);
            }
            etWorkConten.setSelection(etWorkConten.getText().toString().length());
            tvIdentityNum.setText(String.valueOf(etWorkConten.getText().length()));
            etWorkConten.addTextChangedListener(etWorKWatcher);
//            setEditTextState(etWorkConten);
            SoftKeyboardUtils.setEditTextState(etWorkConten);
        } else {
            etWorkConten.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);
            tvIdentityEndNum.setText("/20");
            if (!detail.equals(getString(R.string.pleaseFillIn))) {
                content.setText(detail);
            }
            content.setSelection(content.getText().toString().length());
            tvIdentityNum.setText(String.valueOf(content.getText().length()));
            content.addTextChangedListener(etWatcher);
//            setEditTextState(content);
            SoftKeyboardUtils.setEditTextState(content);
        }
        boolean showLength= intent.getBooleanExtra("showLength",true);
        if(!showLength)
        {
            linearLength.setVisibility(View.GONE);
            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) content.getLayoutParams();
            layoutParams.topMargin= DisplayUtils.dip2px(getBaseContext(),10);
            content.setLayoutParams(layoutParams);
        }
    }

    private void showKeyboard() {
//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        inputMethodManager.showSoftInput(etWorkConten, 0);
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(imm).showSoftInput(this.getWindow().getDecorView(), InputMethodManager.SHOW_FORCED);
    }

    /**
     * 进入页面打开软键盘
     * 首先要对指定的输入框请求焦点。然后调用输入管理器弹出软键盘。
     * 警告：对于刚跳到一个新的界面就要弹出软键盘的情况上述代码可能由于界面为加载完全而无法弹出软键盘。
     * 此时应该适当的延迟弹出软键盘如200毫秒（保证界面的数据加载完成）。实例代码如下：
     */
    private void setEditTextState(EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

                           public void run() {
                               InputMethodManager inputManager =
                                       (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                               Objects.requireNonNull(inputManager).showSoftInput(editText, 0);
                           }

                       },
                200);
    }

    /**
     * 共用输入框监听
     */
    private TextWatcher etWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tvIdentityNum.setText(String.valueOf(content.getText().length()));
        }
    };
    /**
     * 工作内容输入框监听
     */
    private TextWatcher etWorKWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            tvIdentityNum.setText(String.valueOf(etWorkConten.getText().length()));
        }
    };

    @OnClick({R.id.cancel, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel:
                if(SoftKeyboardUtils.isSoftShowing(this)){//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                timer = new Timer();//由于返回动作快于键盘隐藏所以设置延时
                timer.schedule(new TimerTask() {
                                   public void run() {
                                       finish();
                                   }

                               },
                        200);
                break;
            case R.id.sure:
                switch (tag) {
                    case Constant.EDU:
                        SoftKeyboardUtils.hideSoftKeyboard(this);
                        Intent intent0 = new Intent(this, EduExpeActivity.class);
                        if (workFlag.equals(Constant.EXPERIENCES)) {
                            intent0.putExtra(Constant.EDU, etWorkConten.getText().toString());
                        } else {
                            intent0.putExtra(Constant.EDU, content.getText().toString());
                        }
                        setResult(0, intent0);
                        finish();
                        break;
                    case Constant.WORK:
                        SoftKeyboardUtils.hideSoftKeyboard(this);
                        Intent intent = new Intent(this, WorkExpeActivity.class);
                        if (workFlag.equals(Constant.WORK)) {
                            intent.putExtra(Constant.WORK, etWorkConten.getText().toString());
                        } else {
                            intent.putExtra(Constant.WORK, content.getText().toString());
                        }
                        setResult(0, intent);
                        finish();
                        break;
                    case Constant.BLOCK:
                        SoftKeyboardUtils.hideSoftKeyboard(this);
                        Intent intent1 = new Intent(this, BlockChainActivity.class);
                        intent1.putExtra(Constant.BLOCK, content.getText().toString());
                        setResult(0, intent1);
                        finish();
                        break;
                    case Constant.BASIC:
                        SoftKeyboardUtils.hideSoftKeyboard(this);
                        Intent intent2 = new Intent(this, BasicInfoActivity.class);
                        if (workFlag.equals(Constant.DESCRIBE)) {
                            intent2.putExtra(Constant.BASIC, etWorkConten.getText().toString());
                        } else {
                            intent2.putExtra(Constant.BASIC, content.getText().toString());
                        }
                        setResult(0, intent2);
                        finish();
                        break;
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_IdentityInfoActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_IdentityInfoActivity);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
        }
    }
}
