package com.iruiyou.pet.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PageNumberRecyclerView extends RecyclerView {
    private int pageNumber;
    public PageNumberRecyclerView(Context context) {
        super(context);
    }

    public PageNumberRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
