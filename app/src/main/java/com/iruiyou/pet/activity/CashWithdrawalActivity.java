package com.iruiyou.pet.activity;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CodeBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.StringUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-提现
 * 作者：sgf
 */
public class CashWithdrawalActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_text2)
    TextView title_right_text2;
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
    @BindView(R.id.et_cash_withdrawal)
    EditText et_cash_withdrawal;
    @BindView(R.id.tv_cash_withdrawal)
    TextView tv_cash_withdrawal;
    @BindView(R.id.bt_cash_withdrawal)
    Button bt_cash_withdrawal;
    private Context context;
    private String pbsAmount;
    private String quantityExtracted;
    private double pbsAmount_double = 0.0;
    private PopupWindow mPopWindow;
    private TextView send;
    private String mCountry[] = {"86"};
    private TimeCount time;

    @Override
    public int getLayout() {
        return R.layout.activity_cash_withdrawal;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.withdrawal_of_assets));
        context = CashWithdrawalActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
        title_right_text2.setText(R.string.assets3);
        title_right_text2.setTextColor(getResources().getColor(R.color._26c68a));
        pbsAmount = getIntent().getStringExtra("pbsAmount");
        et_cash_withdrawal.addTextChangedListener(quantityExtractedWatcher);
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_cash_withdrawal, R.id.tv_cash_withdrawal, R.id.ll_title_left_view, R.id.title_right_text2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_right_text2://交易记录
                StartActivityManager.startTransactionRecordActivity(this);
                break;
            case R.id.bt_cash_withdrawal:
                String quantity_extracted = et_cash_withdrawal.getText().toString().trim();
                if (TextUtils.isEmpty(quantity_extracted)) {
                    bt_cash_withdrawal.setEnabled(false);
                    return;
                } else {
                    bt_cash_withdrawal.setEnabled(true);
                    if(SoftKeyboardUtils.isSoftShowing(CashWithdrawalActivity.this)){
                        SoftKeyboardUtils.hideSoftKeyboard(CashWithdrawalActivity.this);
                    }
                    showPopupWindow();
                }
//                if (!TextUtils.isEmpty(quantity_extracted) && !TextUtils.isEmpty(money_withdrawal_address)) {
//                    String sub_quantity_extracted = BigDecimalUtil.sub(pbsAmount, quantity_extracted, Constant.SCALE_NUM);
//                    getDrawPbs();
//                }
                break;
            case R.id.tv_cash_withdrawal:
                if (!TextUtils.isEmpty(pbsAmount)) {
                    et_cash_withdrawal.setText(pbsAmount);
                }
                break;
            case R.id.ll_title_left_view:
                if (SoftKeyboardUtils.isSoftShowing(this)) {//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(this);
                }
                finish();
                break;
        }
    }

    /**
     * 评论弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(context).inflate(R.layout.message_dialoglayout, null);
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
                time = new TimeCount(60000, 1000);
                sendCode();
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
        View rootview = LayoutInflater.from(context).inflate(R.layout.activity_text_details, null);
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
                } else if (codeBean.getStatusCode() == Constant.TIPS1) {
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


    /**
     * 获取pbs提现接口
     */
//    private void getDrawPbs() {
//
//        new UserTask(new HttpOnNextListener() {
//            @Override
//            public void onNext(String resulte, String method) {
//                DrawPbsBean bean = GsonUtils.parseJson(resulte, DrawPbsBean.class);
//                if (bean.getStatusCode() == Constant.SUCCESS) {
//
//                } else if (bean.getStatusCode() == Constant.TIPS1) {
//                }
//                T.showShort(bean.getMessage());
////                refreshLayout_pbs_incubation.finishRefresh(true);
//            }
//
//            @Override
//            public void onError(ApiException e) {
////                refreshLayout_pbs_incubation.finishRefresh(false);
//            }
//        }, this).drawPbs(et_quantity_extracted.getText().toString().trim(), et_money_withdrawal_address.getText().toString().trim());
//
//    }
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
     * 设置提取资产按钮背景颜色
     *
     * @param type       是否可点击
     * @param background 背景颜色
     */
    private void setWithdrawalBackground(boolean type, int background) {
        bt_cash_withdrawal.setEnabled(type);
//                    btn_importWallet.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.light_blue));
        //設置佈局背景色方法一
        Drawable drawable = getResources().getDrawable(background);
        bt_cash_withdrawal.setBackgroundDrawable(drawable);
    }

    /**
     * 提取数量监听
     */
    private TextWatcher quantityExtractedWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            quantityExtracted = editable.toString();
//            quantityExtracted_Double = Double.parseDouble(quantityExtracted);
            if (editable.toString().length() >= 3) {
                setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
            } else {
                setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
            }

            if (!TextUtils.isEmpty(editable.toString())) {
                String quantityExtracteds = BigDecimalUtil.sub(editable.toString(), "50", Constant.SCALE_NUM);
                if (quantityExtracteds.contains(Constant.BAR)) {
//                    tv_actual_achievement_quantity.setText("0PBS");
                    setWithdrawalBackground(false, R.drawable.bt_psw_details_shape);
                } else {
//                    tv_actual_achievement_quantity.setText(quantityExtracteds + getString(R.string.pbs));
                    setWithdrawalBackground(true, R.drawable.bt_psw_details_shape2);
                }
            }
        }
    };
}
