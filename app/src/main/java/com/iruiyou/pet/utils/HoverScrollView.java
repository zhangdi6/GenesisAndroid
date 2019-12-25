package com.iruiyou.pet.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 悬停效果
 */
public class HoverScrollView extends ScrollView {


    private OnScrollListener onScrollListener;

    public interface OnScrollListener{
        void onScroll(int scrollY);
    }


    public HoverScrollView(Context context) {
        super(context, null, 0);
        // TODO Auto-generated constructor stub
    }


    public HoverScrollView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }


    public HoverScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener = onScrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        // TODO Auto-generated method stub
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener!=null){
            onScrollListener.onScroll(t);
        }
    }
}
