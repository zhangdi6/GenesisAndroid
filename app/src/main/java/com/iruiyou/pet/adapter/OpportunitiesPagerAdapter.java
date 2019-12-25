package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.iruiyou.pet.utils.PageNumberMaxRecyclerView;

public class OpportunitiesPagerAdapter extends PagerAdapter {

    private PageNumberMaxRecyclerView[] dataViews;
    private Context context;

    public  OpportunitiesPagerAdapter(Context context){
        dataViews =new PageNumberMaxRecyclerView[getCount()];
        this.context=context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
         return view == object;
    }

    public PageNumberMaxRecyclerView getItemView(int position){
        return dataViews[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if(dataViews[position]==null)
        {
            PageNumberMaxRecyclerView maxRecyclerView =new PageNumberMaxRecyclerView(context);
            maxRecyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
//            maxRecyclerView.setAdapter(new OpportunitiesGoodsAdapter());
            dataViews[position]=maxRecyclerView;
        }
        container.addView(dataViews[position]);
        return dataViews[position];
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView(dataViews[position]);
    }

}
