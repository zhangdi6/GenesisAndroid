package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.RegisterLastFragment;

/**
 * 类描述：简历Activity
 * 作者：jiaopeirong on 2018/8/8 22:05
 * 邮箱：chinajpr@163.com
 */
public class RegisterLastActivity2 extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        String nationalCode = getIntent().getStringExtra("nationalCode");
        String phone = getIntent().getStringExtra("phone");
        String smsCode = getIntent().getStringExtra("smsCode");
        String password = getIntent().getStringExtra("password");
        String invitationCode = getIntent().getStringExtra("invitationCode");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();
        beginTransaction.add(R.id.fl, RegisterLastFragment.newInstance());
        beginTransaction.commit();
    }

}
