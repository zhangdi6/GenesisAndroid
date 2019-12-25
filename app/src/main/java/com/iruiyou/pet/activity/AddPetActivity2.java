package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 添加宠物（不再使用）
 * 作者：jiaopeirong on 2018/5/25 00:11
 * 邮箱：chinajpr@163.com
 */
public class AddPetActivity2 extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_add_pet;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("添加宠物");
    }

    @OnClick({R.id.ll_title_left_view, R.id.upImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.upImg:

                break;
        }
    }
}
