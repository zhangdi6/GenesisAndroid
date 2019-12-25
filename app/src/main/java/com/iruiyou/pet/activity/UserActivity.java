package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.UserFragment;

/**
 * 类描述:用户信息
 * 创建日期:2018/5/26 on 11:14
 * 作者:JiaoPeiRong
 */
public class UserActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        String amount = getIntent().getStringExtra(TagConstants.UserTag0);
        String invite = getIntent().getStringExtra(TagConstants.UserTag1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();
        beginTransaction.add(R.id.fl, UserFragment.newInstance());
        beginTransaction.commit();
    }

}
