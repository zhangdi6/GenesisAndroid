package com.iruiyou.pet.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * 机遇fragment
 */
public class MCChanceFragment extends BaseFragment {

    /**
     * 单例模式
     *
     * @return
     */
    public static MCChanceFragment getInstance() {
        return new MCChanceFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_channce, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {

    }
}
