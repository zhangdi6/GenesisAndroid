package com.iruiyou.pet.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.iruiyou.pet.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuleNoticeDialog extends Dialog {

    private KnowClick knowClick;
    protected RuleNoticeDialog(@NonNull Context context,KnowClick knowClick) {
        super(context,  R.style.whDialog);
        setCancelable(false);
        this.knowClick=knowClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rule_notice);
        ButterKnife.bind(this);
    }

    @OnClick(value = {R.id.ll_open_membership})
    void viewOnClick(View view){
        int id= view.getId();
        switch (id){
            case R.id.ll_open_membership:
                dismiss();
                if(knowClick!=null){
                    knowClick.onClick();
                }
                break;
        }
    }

    public interface KnowClick{
        void onClick();
    }

}
