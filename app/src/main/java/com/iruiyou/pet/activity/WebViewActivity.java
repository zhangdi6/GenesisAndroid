package com.iruiyou.pet.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.CreateOrderBean;
import com.iruiyou.pet.bean.YzTokenBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.iruiyou.pet.utils.IsInstallWeChatOrAliPay;
import com.iruiyou.pet.utils.UrlParse;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youzan.androidsdk.YouzanSDK;
import com.youzan.androidsdk.YouzanToken;
import com.youzan.androidsdk.basic.YouzanBrowser;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imageloader.utils.L;

/**
 * web页面
 * 作者：jiaopeirong on 2018/5/27 15:33
 * 邮箱：chinajpr@163.com
 */
public class WebViewActivity extends BaseActivity {
    @BindView(R.id.web)
    YouzanBrowser mWebView;
    final static int REQUEST_CODE_LOGIN = 0x11;
    String stringExtra;
    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.close)
    LinearLayout close;
    @BindView(R.id.title)
    TextView titles;
    @BindView(R.id.image_share)
    ImageView imageShare;
    @BindView(R.id.progressBar1)
    ProgressBar pg;
    private int num = 999;
    private Intent intent1;
    private Uri uri;
    private Map<String, String> urlParams;
    private Boolean canLoadUrl = true;
    private boolean isSetTitle;
    private boolean showShare = false;
    private String titleGloable;

    @Override
    public int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        DialogUtil.getInstance().showLoadingDialog(this, getString(R.string.loading));//加载动画
//        String stringExtra = getIntent().getStringExtra(TagConstants.TITLE);
//        title.setText(stringExtra);
        Intent intent = getIntent();
        this.stringExtra = intent.getStringExtra(TagConstants.WebTag);
        isSetTitle = intent.getBooleanExtra("isSetTitle", true);
        showShare = intent.getBooleanExtra("showShare", false);
//        mWebView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
//        mWebView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
//        mWebView.addJavascriptInterface(new JavaScriptObject(mContext), "myObj");
        if (showShare) {
            imageShare.setVisibility(View.VISIBLE);
        } else {
            imageShare.setVisibility(View.GONE);
        }
        getToken();
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                DialogUtil.getInstance().showLoadingDialog(WebViewActivity.this, getString(R.string.loading));//加载动画
                String title = view.getTitle();//设置网页自带标题
                if (!TextUtils.isEmpty(title) && isSetTitle) {
                    titles.setText(title);
                }

                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
//                L.i("Cookies", "Cookies = " + CookieStr);
                //获得url参数
                urlParams = UrlParse.getUrlParams(url);
                for (Map.Entry<String, String> param : urlParams.entrySet()) {
                    String key = param.getKey();
                    String value = param.getValue();
                    L.i("URL", key + "  :  " + value);
                    if (key.equals("url")) {
                        uri = Uri.parse(value);
                    }
                }
                String uriParam = UrlParse.getUriParam(uri, "order_no");
                if (TextUtils.isEmpty(uriParam)) {
                    uriParam = UrlParse.getUriParam(uri, "request_no");
                }
                L.i("order_no", uriParam);
                if (!TextUtils.isEmpty(uriParam)) {
                    aliPayIntent = null;
                    getCreateOrder(uriParam);
                }
                if (!canLoadUrl) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
//                L.i("Cookies", "Cookies = " + CookieStr);
                //获得url参数
                urlParams = UrlParse.getUrlParams(url);
                for (Map.Entry<String, String> param : urlParams.entrySet()) {
                    String key = param.getKey();
                    String value = param.getValue();
                    L.i("URL", key + "  :  " + value);
                    if (key.equals("url")) {
                        uri = Uri.parse(value);
                    }
                }
                String uriParam = UrlParse.getUriParam(uri, "order_no");
                if (TextUtils.isEmpty(uriParam)) {
                    uriParam = UrlParse.getUriParam(uri, "request_no");
                }
                L.i("order_no", uriParam);
                if (!TextUtils.isEmpty(uriParam)) {
                    getCreateOrder(uriParam);
                }
                super.onPageFinished(view, url);
            }

        });
//        getToken();
        mWebView.setWebChromeClient(new WebChromeClient() {
                                        public void onProgressChanged(WebView view, int progress) {
                                            if (progress == 100) {
                                                pg.setVisibility(View.GONE);//加载完网页进度条消失
                                                DialogUtil.getInstance().closeLoadingDialog();
                                            } else {
                                                DialogUtil.getInstance().showLoadingDialog(WebViewActivity.this, getString(R.string.loading));//加载动画
                                                pg.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                                                pg.setProgress(progress);//设置进度值
                                            }
                                        }

                                        @Override
                                        public void onReceivedTitle(WebView view, String title) {//设置网页自带的标题
                                            super.onReceivedTitle(view, title);
                                            titleGloable=title;
                                            if (title != null && isSetTitle) {
                                                titles.setText(title);
                                            }
                                        }
                                    }
        );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
        }
    }

    /**
     * 获取token
     */
    private void getToken() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                YzTokenBean yzTokenBean = GsonUtils.parseJson(resulte, YzTokenBean.class);
                YouzanToken youzanToken = new YouzanToken();
                youzanToken.setAccessToken(yzTokenBean.getData().getAccess_token());
                youzanToken.setCookieKey(yzTokenBean.getData().getCookie_key());
                youzanToken.setCookieValue(yzTokenBean.getData().getCookie_value());
                YouzanSDK.sync(WebViewActivity.this, youzanToken);
                mWebView.sync(youzanToken);
                mWebView.loadUrl(stringExtra);
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).yzLoginNew();
    }


    private Intent aliPayIntent = null;
    private int aliPayRequestCode;

    @Override
    public boolean startActivityIfNeeded(@NonNull Intent intent, int requestCode) {
        aliPayIntent = intent;
        aliPayRequestCode = requestCode;
        if (!uploadResult) {
            getCreateOrder(orderId);
            return false;
        }
        boolean aliPayInstalled = IsInstallWeChatOrAliPay.checkAliPayInstalled(this);
        if (aliPayInstalled) {
//            T.showShort("2");
            return super.startActivityIfNeeded(intent, requestCode);
        } else {
            intent1 = intent;
//            T.showShort("5");
            return false;

        }
//        return super.startActivityIfNeeded(intent, requestCode);
    }

    private String orderId;
    private Boolean uploadResult = false;

    /**
     * 上传有赞订单号接口
     */
    private void getCreateOrder(String uriParam) {
        if (TextUtils.isEmpty(uriParam)) {
            return;
        }
        if (!uriParam.equals(orderId)) {
            orderId = uriParam;
            canLoadUrl = false;
        } else {
            return;
        }
        DialogUtil.getInstance().showLoadingDialog(WebViewActivity.this, getString(R.string.loading));//加载动画
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                CreateOrderBean bean = GsonUtils.parseJson(resulte, CreateOrderBean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
//                    T.showShort("success");
                    uploadResult = true;
                    num = bean.getStatusCode();
                } else if (bean.getStatusCode() == Constant.TIPS1) {
                    num = bean.getStatusCode();
//                    Intent intent = new Intent();//设置多个（如：1，2，3）activity流程中直接跳转指定的activity5,之后返回不再回到1，2，3这个几个activity中，直接销毁1，2，3activity
//                    intent.setClass(WebViewActivity.this, CrystalRechargeActivity.class);
//                    intent.putExtra("crystal", SharePreferenceUtils.getBaseSharePreference().readCrystalAmount());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//最关键是这句 :多activity中退出整个程序，例如从A->B->C->D，这时我需要从D直接退出程序
//                    startActivity(intent);
                    mWebView.pageGoBack();
                } else {
                    mWebView.pageGoBack();
                }
                urlParams.clear();
                canLoadUrl = true;
                if (aliPayIntent != null) {
                    startActivityIfNeeded(aliPayIntent, aliPayRequestCode);
                } else {
                    mWebView.reload();
                }
                DialogUtil.getInstance().closeLoadingDialog();
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
                mWebView.pageGoBack();
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, this).createOrder(uriParam);
    }

    @OnClick({R.id.back, R.id.close, R.id.image_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_share:
                DialogUtils.showShareDialog(WebViewActivity.this,"脉场",titleGloable
                        ,shareListener,this.stringExtra,"");
                break;
            case R.id.back:
//                mWebView.goBack();
                actionKey(KeyEvent.KEYCODE_BACK);
                break;
            case R.id.close:
                finish();
                break;
        }
    }

    public void actionKey(final int keyCode) {
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 使点击回退按钮不会直接退出整个应用程序而是返回上一个页面
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
//                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        YouzanSDK.userLogout(this);//有赞登出
    }


    /**
     * @authorc: gaotengfei
     * @time: 2017/9/19
     * @desc: 友盟回调
     */
    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            SocializeUtils.safeShowDialog(shareAlertDialog);
            DialogUtil.getInstance().showLoadingDialog(WebViewActivity.this, getString(R.string.loading));//加载动画
        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
            finish();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            SocializeUtils.safeCloseDialog(shareAlertDialog);
            DialogUtil.getInstance().closeLoadingDialog();
        }
    };



}
