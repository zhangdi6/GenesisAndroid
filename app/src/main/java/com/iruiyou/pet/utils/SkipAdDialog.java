package com.iruiyou.pet.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.iruiyou.pet.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 跳过广告的对话框
 */
public class SkipAdDialog extends Dialog {
    private AdDialogOnClick adDialogOnClick;

    public SkipAdDialog(@NonNull Context context, AdDialogOnClick adDialogOnClick) {
        super(context, R.style.whDialog);
        setCancelable(false);
        this.adDialogOnClick = adDialogOnClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_ad_view);
        ButterKnife.bind(this);
    }


    @OnClick(value = {R.id.linear_skip_ad, R.id.linear_watch_ad})
    void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.linear_skip_ad:
                dismiss();
                if (adDialogOnClick != null) {
                    adDialogOnClick.onDialogClick(true);
                }
                break;
            case R.id.linear_watch_ad:
                dismiss();
                if (adDialogOnClick != null) {
                    adDialogOnClick.onDialogClick(false);
                }
                break;
        }
    }


    public interface AdDialogOnClick {
        void onDialogClick(boolean isSkip);
    }
}
