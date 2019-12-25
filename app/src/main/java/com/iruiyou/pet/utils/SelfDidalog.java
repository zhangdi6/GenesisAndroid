package com.iruiyou.pet.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.pet.R;

/**
 * Created by sgf
 * <p>
 * 自定义弹话框
 */

public class SelfDidalog extends Dialog implements View.OnClickListener {

    private int yesStrs; //设置确定按钮的文字
    private int NoStrs; //设置取消按钮的文字
    private onYesAndNoOnclickListener yesAndNoOnclickListener;
    private Object content; //内容
    private String title; //内容
    private TextView tv_dialgo_title;
    private LinearLayout llayout_dialgo_no;
    private TextView tv_no;
    private LinearLayout llayout_dialgo_yes;
    private TextView tv_yes;
    private boolean isVisibleCancel;
    private boolean isBan;
    private boolean isConfirm;


//    public SelfDidalog(Context context, String title, String content, boolean isVisibleCancel, int noStrs, int yesStrs,
//                       onYesAndNoOnclickListener yesAndNoOnclickListener) {
//        super(context, R.style.whDialog);
//        this.title = title;
//        this.content = content;
//        this.isVisibleCancel = isVisibleCancel;
//        this.NoStrs = noStrs;
//        this.yesStrs = yesStrs;
//        this.yesAndNoOnclickListener = yesAndNoOnclickListener;
//    }
    public SelfDidalog(Context context, String title, String content, boolean isVisibleCancel, int noStrs, int yesStrs,
                       onYesAndNoOnclickListener yesAndNoOnclickListener) {
        super(context, R.style.whDialog);
        this.title = title;
        this.content = content;
        this.isVisibleCancel = isVisibleCancel;
        this.NoStrs = noStrs;
        this.yesStrs = yesStrs;
        this.yesAndNoOnclickListener = yesAndNoOnclickListener;
    }
    public SelfDidalog(Context context, String title, String content, boolean isVisibleCancel, int noStrs, int yesStrs,
                       onYesAndNoOnclickListener yesAndNoOnclickListener, boolean isConfirm) {
        super(context, R.style.whDialog);
        this.title = title;
        this.content = content;
        this.isVisibleCancel = isVisibleCancel;
        this.NoStrs = noStrs;
        this.yesStrs = yesStrs;
        this.yesAndNoOnclickListener = yesAndNoOnclickListener;
        this.isConfirm = isConfirm;
    }

    public SelfDidalog(Context context, String title, String content, boolean isBan) {
        super(context, R.style.whDialog);
        this.title = title;
        this.content = content;
        this.isBan = isBan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ok_and_no);
        initView();
    }

    /**
     * 初始化界面控件
     */
    private void initView() {

        tv_dialgo_title = findViewById(R.id.tv_dialgo_title);
        if(StringUtil.isEmpty(title)){
            tv_dialgo_title.setVisibility(View.GONE);
        } else {
            tv_dialgo_title.setText(title);
        }

        TextView ctv_dialog_msg = findViewById(R.id.ctv_dialog_msg);
        ctv_dialog_msg.setText(content+"");

        llayout_dialgo_no = findViewById(R.id.llayout_dialgo_no);
        tv_no = findViewById(R.id.tv_no);
        if(NoStrs!=0)
            tv_no.setText(NoStrs);

        llayout_dialgo_yes = findViewById(R.id.llayout_dialgo_yes);
        tv_yes = findViewById(R.id.tv_yes);

        if(yesStrs!=0)
            tv_yes.setText(yesStrs);

        if (isVisibleCancel) {
            llayout_dialgo_no.setVisibility(View.VISIBLE);
            llayout_dialgo_yes.setVisibility(View.VISIBLE);
        } else {//显示一个白色背景带圆角的按钮
            llayout_dialgo_yes.setBackgroundResource(R.drawable.bg_white_corner_3);
            findViewById(R.id.lineVertical).setVisibility(View.GONE);
//            ViewGroup.LayoutParams layoutParams = llayout_dialgo_yes.getLayoutParams();
//            layoutParams.height = 48;//设置控件的宽高
//            layoutParams.width = 100;
//            llayout_dialgo_yes.setLayoutParams(layoutParams);
            llayout_dialgo_no.setVisibility(View.GONE);
            llayout_dialgo_yes.setVisibility(View.VISIBLE);
        }

        llayout_dialgo_no.setOnClickListener(this);
        llayout_dialgo_yes.setOnClickListener(this);

        if(isConfirm){
            this.findViewById(R.id.icon).setVisibility(View.GONE);
            int color = Color.parseColor("#0a0a0a");
            tv_dialgo_title.setTextColor(color);
            TextPaint tp = tv_dialgo_title.getPaint();
            tp.setFakeBoldText(true);
            ctv_dialog_msg.setTextColor(color);
            ctv_dialog_msg.setGravity(Gravity.START);
            tv_no.setTextColor(Color.parseColor("#888888"));
            tv_yes.setTextColor(color);
            TextPaint tp1 = tv_yes.getPaint();
            tp1.setFakeBoldText(true);
            int lineColor = Color.parseColor("#E5E5E5");
            findViewById(R.id.lineHorizontal).setBackgroundColor(lineColor);
            findViewById(R.id.lineVertical).setBackgroundColor(lineColor);
        }else if(isBan){
            int color = Color.parseColor("#0a0a0a");
            tv_dialgo_title.setTextColor(color);
            TextPaint tp = tv_dialgo_title.getPaint();
            tp.setFakeBoldText(true);
            ctv_dialog_msg.setTextColor(color);
            ctv_dialog_msg.setGravity(Gravity.START);
            int padding = (int)getContext().getResources().getDimension(R.dimen._10dip);
            ctv_dialog_msg.setPadding(0,0,0,padding*2);
            findViewById(R.id.buttonsLayout).setVisibility(View.GONE);
            setCanceledOnTouchOutside(false);
            setCancelable(true);
            setOnDismissListener(new OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
//                    WalletApplication.getInstance().getCurrentTopActivity().finish();
                }
            });
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llayout_dialgo_no:
                if (yesAndNoOnclickListener != null) {
                    yesAndNoOnclickListener.onNoClick();
                }
                dismiss();
                break;
            case R.id.llayout_dialgo_yes:

                if (yesAndNoOnclickListener != null) {
                    yesAndNoOnclickListener.onYesClick();
                }
                dismiss();
                break;
        }

    }


    /**
     * 设置监听事件
     */
    public interface onYesAndNoOnclickListener {

        /**
         * 回调确定按钮的方法 处理事件
         */
        void onYesClick();

        /**
         * 回调取消按钮的方法 处理事件
         */
        void onNoClick();
    }


}
