package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 验证邮箱
 * 作者：jiaopeirong on 2018/5/14 21:04
 * 邮箱：chinajpr@163.com
 */
public class VerificationActivity extends BaseActivity {

    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.sure)
    Button sure;
    @BindView(R.id.send)
    TextView send;
    private String stringExtra;

    @Override
    public int getLayout() {
        return R.layout.activity_verification;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        stringExtra = getIntent().getStringExtra(TagConstants.verTag);
        email.setText("发送成功："+stringExtra);
    }

    @OnClick({R.id.sure, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sure:
                T.showShort("刷新。。。");
                break;
            case R.id.send:
                T.showShort("重新发送。。。");
                break;
        }
    }
}
