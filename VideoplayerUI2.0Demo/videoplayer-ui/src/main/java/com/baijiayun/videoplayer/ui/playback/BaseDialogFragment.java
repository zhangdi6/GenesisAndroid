package com.baijiayun.videoplayer.ui.playback;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.baijiayun.playback.util.DisplayUtils;
import com.baijiayun.videoplayer.ui.R;
import com.baijiayun.videoplayer.ui.utils.QueryPlus;

import static com.baijiayun.glide.util.Preconditions.checkNotNull;


/**
 * Created by Shubo on 2017/2/15.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    protected View contentView;
    private boolean isEditing;
    private QueryPlus $;
    private int contentBackgroundColor = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.dialog_base, container);
        contentView = inflater.inflate(getLayoutId(), null);
        $ = QueryPlus.with(baseView);
        ((FrameLayout) $.id(R.id.dialog_base_content).view()).addView(contentView);
        init(savedInstanceState, getArguments());

        return baseView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(getActivity()!=null)
        {
            Dialog dialog = new Dialog(getActivity(), R.style.LiveBaseDialogFragment);
            dialog.setCanceledOnTouchOutside(true);
            Window window= checkNotNull(dialog.getWindow());
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            window.getDecorView().setSystemUiVisibility(getActivity().getWindow().getDecorView().getSystemUiVisibility());

            // 处理dialog沉浸式状态栏
            dialog.setOnShowListener(dialog1 -> {
                //Clear the not focusable flag from the window
                window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                //Update the WindowManager with the new attributes (no nicer way I know of to do this)..
                WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
                if(wm!=null)
                {
                    wm.updateViewLayout(window.getDecorView(), getDialog().getWindow().getAttributes());
                }
            });
            return dialog;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Context context=getContext();
        if(context!=null)
        {
            if (contentBackgroundColor == -1)
                contentBackgroundColor = ContextCompat.getColor(context, R.color.live_white);
            Window window = getDialog().getWindow();
            if(window!=null)
            {
                window.setBackgroundDrawable(new ColorDrawable(contentBackgroundColor));
                WindowManager.LayoutParams windowParams = window.getAttributes();
                if(windowParams!=null)
                {
                    windowParams.dimAmount = 0.50f;
                    windowParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    windowParams.windowAnimations = R.style.LiveBaseDialogAnim;
                    setWindowParams(windowParams);
                    window.setAttributes(windowParams);
                }
            }
        }

    }

    protected void setWindowParams(WindowManager.LayoutParams windowParams) {
        int longEdge = Math.max(DisplayUtils.getScreenHeightPixels(getContext()), DisplayUtils.getScreenWidthPixels(getContext()));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            windowParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            windowParams.height = longEdge / 2;
        } else {
            //横屏
            windowParams.width = longEdge / 2;
            windowParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        windowParams.gravity = Gravity.BOTTOM | GravityCompat.END;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams =checkNotNull(window).getAttributes();
        if(windowParams!=null&&(window!=null))
        {
            resetWindowParams(windowParams);
            window.setAttributes(windowParams);
        }
    }

    protected void resetWindowParams(WindowManager.LayoutParams windowParams) {
        setWindowParams(windowParams);
    }

    protected abstract int getLayoutId();

    protected abstract void init(Bundle savedInstanceState, Bundle arguments);

    public BaseDialogFragment title(String title) {
        $.id(R.id.dialog_base_title).text(title);
        return this;
    }

    public BaseDialogFragment hideBackground() {
        $.id(R.id.dialog_base_title_container).gone();
        return this;
    }

    public BaseDialogFragment contentBackgroundColor(int color) {
        contentBackgroundColor = color;
        return this;
    }

    public BaseDialogFragment editable(boolean editable) {
        $.id(R.id.dialog_base_edit).visibility(editable ? View.VISIBLE : View.GONE);
        $.id(R.id.dialog_base_edit).clicked(v -> {
            if (isEditing) {
                disableEdit();
            } else {
                enableEdit();
            }
        });
        return this;
    }

    public BaseDialogFragment editText(String text) {
        $.id(R.id.dialog_base_edit).text(text);
        return this;
    }

    public BaseDialogFragment editColor(int color) {
        ((TextView) $.id(R.id.dialog_base_edit).view()).setTextColor(color);
        return this;
    }

    public BaseDialogFragment editClick(View.OnClickListener listener) {
        $.id(R.id.dialog_base_edit).clicked(listener);
        return this;
    }

    protected void hideTitleBar() {
        $.id(R.id.dialog_base_title_container).gone();
    }

    protected boolean isEditing() {
        return isEditing;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @CallSuper
    protected void disableEdit() {
        isEditing = false;
        $.id(R.id.dialog_base_edit).text(getString(R.string.live_edit));
    }

    @CallSuper
    protected void enableEdit() {
        isEditing = true;
        $.id(R.id.dialog_base_edit).text(getString(R.string.live_cancel));
    }

//    protected void showToast(String msg) {
//        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        $ = null;
    }
}
