package com.iruiyou.pet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类描述：自动适应viewgroup
 * 作者：JiaoPeiRong on 2017/3/21 10:43
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //默认的高度，宽度以及模式
        int widthMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        //viewgroup总的高度和宽度
        int height = 0;
        int width = 0;
        //每一行的宽度和高度
        int lineHeight = 0;
        int lineWidth = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //测量子view
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            //获取子view的宽高
//            LayoutParams layoutParams1 = childAt.getLayoutParams();
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int childWidth = childAt.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;

            //开始测量
            //需要换行
            if (lineWidth + childWidth > widthMeasureSize) {
                width = Math.max(lineWidth, childWidth);
                height += lineHeight;

                //换行后的行高和行宽
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不需要换行
                lineHeight = Math.max(lineHeight, childHeight);
                lineWidth += childWidth;

                if (i == childCount - 1) {
                    height += lineHeight;
                    width = Math.max(width, lineWidth);
                }
            }

            setMeasuredDimension(widthMeasureMode == MeasureSpec.EXACTLY ? widthMeasureSize : width
                    , heightMeasureMode == MeasureSpec.EXACTLY ? heightMeasureSize : height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int lineWidth = 0, lineHeight = 0, left = 0, top = 0;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) childAt.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            int topMargin = layoutParams.topMargin;
            int bottomMargin = layoutParams.bottomMargin;
            int childWidth = childAt.getMeasuredWidth() + leftMargin + rightMargin;
            int childHeight = childAt.getMeasuredHeight() + topMargin + bottomMargin;

            //开始布局
            //换行
            if (lineWidth + childWidth > getMeasuredWidth()) {
                top += lineHeight;
                left = 0;
                //换行后，重新计算lineHeight,lineWidth
                lineWidth = childWidth;
                lineHeight = childHeight;
            } else {
                //不换行
                lineWidth += childWidth;
                lineHeight = Math.max(childHeight , lineHeight);
            }
            int cl = left + leftMargin;
            int cr = cl + childAt.getMeasuredWidth();
            int ct = top + topMargin;
            int cb = ct + childAt.getMeasuredHeight();
            childAt.layout(cl, ct, cr, cb);
            left += childWidth;
        }
    }

    //LayoutParams是viewgroup提供给子view使用的，LayoutParams无法获取margin值，MarginLayoutParams可以获取margin值
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
