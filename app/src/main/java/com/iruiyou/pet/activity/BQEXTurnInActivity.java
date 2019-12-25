package com.iruiyou.pet.activity;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputEditText;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 币全转入界面
 */
public class BQEXTurnInActivity extends BaseActivity {

    @BindView(R.id.edit_account_action)
    EditText editAccount;

    @BindView(R.id.edit_count_pbs)
    EditText editCount;

    @BindView(R.id.btn_action)
    Button btnAction;

    @BindView(R.id.linear_bottom)
    LinearLayout linearBottom;

    private String orderId;
    private PopupWindow mPopWindow;
    private TextView send;
    private TimeCount time;
    private String phone;
    @Override
    public int getLayout() {
        return R.layout.activity_bqex_turn_in;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setEnable(true);
    }

    @OnClick(value = {R.id.btn_action})
    public void viewOnClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn_action:
                createCeiptOrder();
                break;
        }
    }

    private void createCeiptOrder(){
        phone= editAccount.getText().toString().trim();
        String actionCount = editCount.getText().toString().trim();

        if(StringUtil.isEmpty(phone)){
            Toast.makeText(this,"请填写币权账号",Toast.LENGTH_SHORT).show();
            return;
        }

        if(StringUtil.isEmpty(actionCount)){
            Toast.makeText(this,"请填写操作数量",Toast.LENGTH_SHORT).show();
            return;
        }

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test",resulte);
                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject = new JSONObject(resulte);
                        if(Constant.SUCCESS == jsonObject.getInt("statusCode")){
                            orderId =  jsonObject.optString("data");
                            setEnable(false);
                            showPopupWindow();
                            time = new TimeCount(180000, 1000);
                            time.start();
                        }else if(StringUtil.isNotEmpty(jsonObject.optString("message"))){
                            Toast.makeText(BQEXTurnInActivity.this,jsonObject.optString("message"),Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(ApiException e) {
                Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
            }
        },this).createReceiptOrder("86",phone,actionCount);
    }

    private void setEnable(boolean enable){
        GradientDrawable drawable = (GradientDrawable) btnAction.getBackground();
        if(enable){
            drawable.setColor(getResources().getColor(R.color._55D8B1));
        }else{
            drawable.setColor(getResources().getColor(R.color._ffc5e2d9));
        }
        btnAction.setBackground(drawable);
        btnAction.setEnabled(enable);
    }




    /**
     * 评论弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(BQEXTurnInActivity.this).inflate(R.layout.message_dialoglayout, null);
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
        TextView tv_phone_tips = contentView.findViewById(R.id.tv_phone_tips);
        TextInputEditText code = contentView.findViewById(R.id.code);
        send = contentView.findViewById(R.id.send);

        tv_phone_tips.setText(getString(R.string.send_out2, StringUtil.showPhonenumber(SharePreferenceUtils.getBaseSharePreference().readBqPhone())));
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCeiptOrder();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();//让PopupWindow消失
            }
        });
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = code.getText().toString().trim();
                if (inputString.length() > 0) {
                    DialogUtil.getInstance().showLoadingDialog(BQEXTurnInActivity.this);
                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {
                            DialogUtil.getInstance().closeLoadingDialog();
                            if(StringUtil.isNotEmpty(resulte)){
                                try {
                                    JSONObject jsonObject = new JSONObject(resulte);
                                    if(Constant.SUCCESS == jsonObject.optInt("status")){
                                        Toast.makeText(BQEXTurnInActivity.this,"操作成功!",Toast.LENGTH_LONG).show();
                                        finish();
                                    }else if(StringUtil.isNotEmpty(jsonObject.optString("message"))){
                                        Toast.makeText(BQEXTurnInActivity.this,jsonObject.optString("message"),Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onError(ApiException e) {
                            DialogUtil.getInstance().closeLoadingDialog();
                            Toast.makeText(BQEXTurnInActivity.this,"请求异常，请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    }, BQEXTurnInActivity.this).receiptFromBqex(orderId,inputString,phone);
                    mPopWindow.dismiss();//让PopupWindow消失
                }else{
                    Toast.makeText(BQEXTurnInActivity.this,"请输入验证码！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        mPopWindow.showAtLocation(linearBottom,Gravity.BOTTOM, 0, 0);
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
            if(send!=null){
                send.setClickable(false);
                send.setText(Html.fromHtml(StringUtil.setStrGray3(millisUntilFinished / 1000 + "s")));
            }
            setEnable(false);
            btnAction.setText(Html.fromHtml(StringUtil.setStrGray3(millisUntilFinished / 1000 + "s")));
        }

        @Override
        public void onFinish() {
            if(send!=null){
                send.setText(getResources().getString(R.string.RegainValidationCode));
                send.setClickable(true);
            }
            btnAction.setText("确认");
            setEnable(true);
        }
    }

}
