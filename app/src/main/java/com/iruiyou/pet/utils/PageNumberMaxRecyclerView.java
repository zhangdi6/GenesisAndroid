package com.iruiyou.pet.utils;

import android.content.Context;
import android.util.AttributeSet;

public class PageNumberMaxRecyclerView extends MaxRecyclerView {
    private int pageNumber;

    public PageNumberMaxRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageNumberMaxRecyclerView(Context context) {
        super(context);
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
