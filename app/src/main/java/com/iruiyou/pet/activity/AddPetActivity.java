package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.AddPetFragment2;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：jiaopeirong on 2018/5/16 21:53
 * 邮箱：chinajpr@163.com
 */
public class AddPetActivity extends BaseActivity {
    private static final int reqCode = 1;
    @BindView(R.id.fl)
    FrameLayout fl;

    @Override
    public int getLayout() {
        return R.layout.activity_add;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fragmentManager
                .beginTransaction();
        beginTransaction.add(R.id.fl, new AddPetFragment2());
        beginTransaction.commit();
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AddPetActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AddPetActivity);
        MobclickAgent.onPause(this);
    }

}
