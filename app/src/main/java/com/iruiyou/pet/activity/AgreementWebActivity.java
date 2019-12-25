package com.iruiyou.pet.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述:
 * 创建日期:2018/5/31 on 10:53
 * 作者:JiaoPeiRong
 */
public class AgreementWebActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.close)
    LinearLayout close;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.progressBar1)
    ProgressBar pg;
    private String url;

    @Override
    public int getLayout() {
        return R.layout.activity_agree;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        String stringExtra = getIntent().getStringExtra(TagConstants.TITLE);
        title.setText(stringExtra);
        web.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        web.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        web.setBackgroundColor(Color.argb(0, 0, 0, 0));
        url = getIntent().getStringExtra(TagConstants.WebTag);
        web.loadUrl(url);
        web.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        web.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    pg.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    pg.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg.setProgress(progress);//设置进度值
                }
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
