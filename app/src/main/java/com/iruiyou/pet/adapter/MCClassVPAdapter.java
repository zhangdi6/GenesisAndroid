package com.iruiyou.pet.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MCClassVPAdapter extends FragmentPagerAdapter {

    private final ArrayList<String> mTitleList;
    private final ArrayList<Fragment> mFragmentList;

    public MCClassVPAdapter(FragmentManager fm, ArrayList<String> mTitleList, ArrayList<Fragment> mFragmentList) {
        super(fm);
        this.mTitleList = mTitleList;
        this.mFragmentList = mFragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}
