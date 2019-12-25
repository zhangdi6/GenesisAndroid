package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.ContactsFragment;

/**
 * 类描述：联系人搜索-Fragment中提成activity进入方法
 * 作者：sgf
 */
public class ContactsSearchActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();
        beginTransaction.add(R.id.fl, ContactsFragment.getInstance());
        beginTransaction.commit();
    }

}
