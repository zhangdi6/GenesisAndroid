package com.iruiyou.common.view;

import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.iruiyou.common.R;

import java.util.Objects;


public class NumAlertDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private Display display;
    private OnEditTextListener listener;
    private Button button;
    private EditText editText;
    private ImageView iv;

    public NumAlertDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = Objects.requireNonNull(windowManager).getDefaultDisplay();
    }

    public NumAlertDialog builder() {
        //获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.view_alert_dialog, null);

        iv = view.findViewById(R.id.dis);
        lLayout_bg = view.findViewById(R.id.layoutBg);
        txt_title = view.findViewById(R.id.title);
        button = view.findViewById(R.id.btn);
        editText = view.findViewById(R.id.edit);
        //默认只能输入数字
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
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

    public NumAlertDialog setTitle(String title) {
        txt_title.setText(title);
        return this;
    }

    public NumAlertDialog setonEditTextListener(final OnEditTextListener listener) {
        this.listener = listener;
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.callBack(editText.getText().toString());
                dialog.dismiss();
            }
        });
        return this;
    }

    public NumAlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }


    public void show() {
        dialog.show();
    }

    public interface OnEditTextListener {
        void callBack(String string);
    }
}
