package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.List;

public class PulseFieldViewPagerAdapter extends FragmentPagerAdapter {

//    private List<OccupationBeen> listKey;
//    private HashMap<OccupationBeen, PulseFieldItemFragment> data;
//    private PulseFieldFragment pulseFieldFragment;
    private Context mContext;
    private List<Fragment> listfragment;
    public PulseFieldViewPagerAdapter(FragmentManager fm,List<Fragment> listfragment) {
        super(fm);
        this.listfragment=listfragment;
    }

//    public PulseFieldViewPagerAdapter(Context context, PulseFieldFragment pulseFieldFragment, FragmentManager manager) {
//        mContext = context;
//        data = new HashMap<>();
//        this.pulseFieldFragment=pulseFieldFragment;
//        super(manager);
//    }

//    public void setDataSource(List<OccupationBeen> dataSource)
//    {
//        listKey = dataSource;
//        if(data!=null&&data.size()>0)
//        {
//            data.clear();
//        }
//        notifyDataSetChanged();
//    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }

    @Override
    public Fragment getItem(int position) {
//        if (position == 0) {
//            return pulseFieldFragment;
//        } else {
//            OccupationBeen key = listKey.get(position);
//            if (data.containsKey(key)) {
//                return data.get(key);
//            } else {
//                PulseFieldItemFragment pulseFieldItemFragment = new PulseFieldItemFragment();
//                pulseFieldItemFragment.setOccupationBeen(key);
//                data.put(key, pulseFieldItemFragment);
//                return pulseFieldItemFragment;
//            }
//        }
       return listfragment.get(position);

    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//
//    }

}
