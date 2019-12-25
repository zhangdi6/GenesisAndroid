package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：传递url的webview
 * 作者：jiaopeirong on 2018/6/6 22:10
 * 邮箱：chinajpr@163.com
 */
public class UrlWebActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.close)
    LinearLayout close;
    @BindView(R.id.title)
    TextView title;

    @Override
    public int getLayout() {
        return R.layout.activity_web_url;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        title.setText("我的邀请码");
        String stringExtra = getIntent().getStringExtra(TagConstants.WEBURL);
        web.loadUrl(stringExtra);
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @OnClick({R.id.back, R.id.close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                web.goBack();
                break;
            case R.id.close:
                finish();
                break;
        }
    }
}
