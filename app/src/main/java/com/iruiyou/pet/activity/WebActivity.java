package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * web页面
 * 作者：jiaopeirong on 2018/5/27 15:33
 * 邮箱：chinajpr@163.com
 */
public class WebActivity extends BaseActivity {


    @BindView(R.id.web_xie)
    WebView pg;


    @BindView(R.id.title)
    TextView title;


    @Override
    public int getLayout() {
        return R.layout.activity_web2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        title.setText("脉场用户协议");
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        pg.loadUrl(url);

    }

}
