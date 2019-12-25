package com.iruiyou.pet.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;



import java.util.Objects;


/**
 * 评论弹框
 */
public class KeyMapDailog extends DialogFragment {

    //点击发表，内容不为空时的回调
    public SendBackListener sendBackListener;
    public interface  SendBackListener{
        void sendBack(String inputText);
    }

    private ProgressDialog progressDialog;
    private String texthint;

    private Dialog dialog;
    private EditText inputDlg;
    private int numconut=300;
    private String tag=null;

    public KeyMapDailog() {
    }


    @SuppressLint("ValidFragment")
    public KeyMapDailog(String texthint, SendBackListener sendBackListener){//提示文字
        this.texthint=texthint;
        this.sendBackListener=sendBackListener;

    }




    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(Objects.requireNonNull(getActivity()), R.style.Comment_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        View contentview = View.inflate(getActivity(), R.layout.comment_edittext_dialoglayout, null);
        dialog.setContentView(contentview);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = Objects.requireNonNull(window).getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.5f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        inputDlg = contentview.findViewById(R.id.et_comment);
        inputDlg.setHint(texthint);
        final TextView tv_send = contentview.findViewById(R.id.tv_send);
        inputDlg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.length() > 0) {
//                    tv_send.setBackgroundResource(R.drawable.corners_review_cansend);
//                } else {
//                    tv_send.setBackgroundResource(R.drawable.corners_review_send);
//                }

            }
        });

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(inputDlg.getText().toString())) {
                    T.showShort("输入内容为空");
                    return;
                } else {
                    progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    sendBackListener.sendBack(inputDlg.getText().toString());
                }
            }
        });
        inputDlg.setFocusable(true);
        inputDlg.setFocusableInTouchMode(true);
        inputDlg.requestFocus();
        final Handler hanler = new Handler();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public InputMethodManager mInputMethodManager;

            @Override
            public void onDismiss(DialogInterface dialog) {
                hanler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideSoftkeyboard();
                    }
                }, 200);

            }
        });
        return dialog;
    }
    public void hideProgressdialog(){
        progressDialog.cancel();
    }


    public void hideSoftkeyboard() {
        try {
            ((InputMethodManager) Objects.requireNonNull(getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)))
                    .hideSoftInputFromWindow(Objects.requireNonNull(getActivity().getCurrentFocus()).getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException e) {
                e.fillInStackTrace();
        }
    }


}
