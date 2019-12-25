package com.iruiyou.http.retrofit_rx.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.iruiyou.http.R;

import java.util.Objects;


/**
 * 自定义progressbar
 * Created by jiao on 2015/12/1.
 */
public class CustomProgress extends Dialog {
    private static CustomProgress dialog;
    WindowManager.LayoutParams lp;
    private Context context;

    public CustomProgress(Context context) {
        super(context);
        this.context = context;
        dialog = new CustomProgress(context, R.style.CustomProgress);
    }

    public CustomProgress(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static CustomProgress getInstance(Context context) {
        if (null == dialog) {
            synchronized (CustomProgress.class) {
                if (null == dialog) {
                    dialog = new CustomProgress(context, R.style.CustomProgress);
                }
            }
        }
        return dialog;
    }

    /**
     * 显示dialog
     *
     * @param listener
     * @return
     */
    public CustomProgress show(OnCancelListener listener) {
        dialog.setContentView(R.layout.custom_dialog);

        ProgressBar spinKitView = dialog.findViewById(R.id.spin_kit);

        //按返回键是否取消
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        //监听返回键
        dialog.setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                CustomProgress.dialog.hideProgress();
            }
        });
        if (lp == null) {
            lp = Objects.requireNonNull(dialog.getWindow()).getAttributes();
            lp.gravity = Gravity.CENTER;
            lp.dimAmount = 0.2f;
        }

        //防止 Unable to add window -- token android.os.BinderProxy@164db98f is not valid; is your activity running?
        try {
            dialog.getWindow().setAttributes(lp);
            dialog.show();
        } catch (Exception e) {
        }

        return dialog;

    }

    /**
     * 隐藏dialog
     */
    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

}
