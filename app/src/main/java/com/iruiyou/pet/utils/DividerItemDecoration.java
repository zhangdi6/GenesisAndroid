package com.iruiyou.pet.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iruiyou.pet.R;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private int mDividerHeight;  //分割线高度

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;
    private Drawable mDivider_gray;
    private int mPosition;//集合的长度-1

    private int mOrientation;
    private int childAdapterPosition;

    public DividerItemDecoration(Context context, int orientation, int dividerHeight, int position) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider_gray = a.getDrawable(0);
        mDivider = ContextCompat.getDrawable(context, R.drawable.ba_divider);
        mDividerHeight = dividerHeight;
        mPosition = position;
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }


    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            if(mPosition == (childCount-1)){//用于判断已注册用户集合的长度
                final int bottom = top + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }else {
                final int bottom = top + mDivider_gray.getIntrinsicHeight();
                mDivider_gray.setBounds(left, top, right, bottom);
                mDivider_gray.draw(c);
            }

        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            if(mPosition == (childCount-1)){
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }else {
                final int right = left + mDivider_gray.getIntrinsicHeight();
                mDivider_gray.setBounds(left, top, right, bottom);
                mDivider_gray.draw(c);
            }

        }
    }
    //获取分割线尺寸
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        childAdapterPosition = parent.getChildAdapterPosition(view);

        int lastCount = parent.getAdapter().getItemCount() - 1;
        if(childAdapterPosition == mPosition){
            outRect.set(0, 0, 0, mDividerHeight);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
//            if(mPosition == itemPosition){
//                outRect.set(0, 0, 0, mDividerHeight);
//            }else {
//                outRect.set(0, 0, 0, mDivider_gray.getIntrinsicHeight());
//            }

        } else {
            outRect.set(0, 0, mDivider_gray.getIntrinsicWidth(), 0);
        }
    }
}