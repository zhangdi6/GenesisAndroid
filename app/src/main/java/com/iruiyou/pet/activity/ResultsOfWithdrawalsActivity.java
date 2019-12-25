package com.iruiyou.pet.activity;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的-提现结果
 * 作者：sgf
 *
 */
public class ResultsOfWithdrawalsActivity extends BaseActivity {
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
    @BindView(R.id.tv_successful_withdrawals)
    TextView tv_successful_withdrawals;
    @BindView(R.id.tv_presentation_prompt)
    TextView tv_presentation_prompt;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_card_number)
    TextView tv_card_number;
    @BindView(R.id.tv_initiation_time)
    TextView tv_initiation_time;
    private PopupWindow mPopWindow;
    private String mCountry[] = {"86"};
    private TimeCount time;
    private TextView send;

    @Override
    public int getLayout() {
        return R.layout.activity_results_of_withdrawals;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.aboutUs));
        initDta();
    }

    private void initDta() {
    }

    @OnClick({R.id.tv_successful_withdrawals})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_successful_withdrawals:
                showPopupWindow();
                break;
//            case R.id.llPraise:
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"http://pbase.io");
//                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onPause(this);
    }
    /**
     * 评论弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ResultsOfWithdrawalsActivity.this).inflate(R.layout.message_dialoglayout, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // pop.dismiss(）方法调用时，回调该函数，点击外部时，也会回调该函数
                //解决键盘与PopupWindow的冲突，导致输入框dismiss后键盘不隐藏
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
        //设置软键盘弹出
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(inputMethodManager).toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
        //设置各个控件的点击响应
        final EditText editText = contentView.findViewById(R.id.et_comment);
        TextView tv_send = contentView.findViewById(R.id.tv_send);
        TextView tv_cancel = contentView.findViewById(R.id.tv_cancel);
        TextInputEditText code = contentView.findViewById(R.id.code);
        send = contentView.findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code.getText().length() > 0) {
                    sendCode();
                }else {
                    T.showShort("请输入验证码");
                }

            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SoftKeyboardUtils.hideSoftKeyboard(ResultsOfWithdrawalsActivity.this);
                mPopWindow.dismiss();//让PopupWindow消失
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = code.getText().toString();
                if (code.getText().length() > 0) {
                    T.showShort(inputString);
//                    SoftKeyboardUtils.hideSoftKeyboard(ResultsOfWithdrawalsActivity.this);
                    mPopWindow.dismiss();//让PopupWindow消失

                } else {
                    T.showShort("请输入验证码");
                }
            }
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(ResultsOfWithdrawalsActivity.this).inflate(R.layout.activity_text_details, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 发送验证码
     */
    private void sendCode() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CodeBean codeBean = GsonUtils.parseJson(resulte, CodeBean.class);
                if (codeBean.getStatusCode() == Constant.SUCCESS) {
                    time.start();
                    T.showShort(codeBean.getMessage());
                }else if(codeBean.getStatusCode() == Constant.TIPS1){
                    T.showShort(codeBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).forgetCode(mCountry[0], "15718807582");
    }
    /**
     * 60s倒计时
     */
    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            send.setClickable(false);
//            send.setText(getResources().getString(R.string.resend2) + "(" + millisUntilFinished / 1000 + ")");
            send.setText(Html.fromHtml(StringUtil.setStrGray3(millisUntilFinished / 1000 + "s")));
        }

        @Override
        public void onFinish() {
            send.setText(getResources().getString(R.string.RegainValidationCode));
            send.setClickable(true);

        }
    }
}
