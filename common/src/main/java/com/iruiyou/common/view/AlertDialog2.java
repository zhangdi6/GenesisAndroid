package com.iruiyou.common.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.iruiyou.common.R;

import java.util.Objects;


public class AlertDialog2 {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private Display display;
    private ImageView iv;
    private TextView msgTv;

    public AlertDialog2(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
    }

    public AlertDialog2 builder() {
        //获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alert_dialog2, null);

        iv = view.findViewById(R.id.dis);
        lLayout_bg = view.findViewById(R.id.layoutBg);
        txt_title = view.findViewById(R.id.title);
        msgTv = view.findViewById(R.id.msg);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        //调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.6), LayoutParams.WRAP_CONTENT));

        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        return this;
    }

    public AlertDialog2 setTitleAndMsg(String title , String msg) {
        txt_title.setText(title);
        msgTv.setText(msg);
        return this;
    }

    public AlertDialog2 setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }


}
