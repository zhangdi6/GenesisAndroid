package com.iruiyou.pet.activity;

import android.app.Instrumentation;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.MineRefreshBean;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sgf on 2018/5/8.
 * 合约协议
 */

public class WebViewNewActivity extends BaseActivity {

    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.ll_title_left_view2)
    LinearLayout ll_title_left_view2;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    @BindView(R.id.rl_webView)
    RelativeLayout rl_webView;
    @BindView(R.id.progressBar_new_web)
    ProgressBar pg;
    @BindView(R.id.share)
    ImageView mShare_img;
    private WebView webView;
    private MineRefreshBean mineRefreshBean;
    private String id;

    @Override
    public int getLayout() {
        return R.layout.activity_webview_new;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initWebView();
    }

    public void initWebView() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        String text = getIntent().getStringExtra("text");
        String img = getIntent().getStringExtra("img");

         id = getIntent().getStringExtra("idd");
        boolean isCurrentActivity = getIntent().getBooleanExtra("isCurrentActivity",false);
//        contract_flag = getIntent().getStringExtra("contract_flag");
//        setTitle(title);
        ll_title_left_view2.setVisibility(View.VISIBLE);
        ll_title_left_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        llTitleLeftView.setOnClickListener(new View.OnClickListener() {//设置层层返回事件
            @Override
            public void onClick(View view) {
                actionKey(KeyEvent.KEYCODE_BACK);
            }
        });
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if(isCurrentActivity){
            webView = new WebView(WebViewNewActivity.this);
        }else {
            webView = new WebView(getApplicationContext());
        }

        webView.setLayoutParams(lp);
        rl_webView.addView(webView);

        WebSettings wbs = webView.getSettings();
        wbs.setDefaultTextEncodingName("utf-8");

        CookieManager cm = CookieManager.getInstance();
        cm.setAcceptCookie(true);

        JSONObject object = new JSONObject();

        object.put("userId",Integer.parseInt(SharePreferenceUtils.getBaseSharePreference().readUserId()));
        object.put("token", SharePreferenceUtils.getBaseSharePreference().readToken());
        object.put("isLogin",true);

        String string = object.toString();

        cm.setCookie(url, "pbs="+ string);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();

        wbs.setJavaScriptEnabled(true);
        webView.loadUrl(url);

        if (title.equals("visibility")){
            mShare_img.setVisibility(View.VISIBLE);
            mShare_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogUtils.showShareDialog(WebViewNewActivity.this,text,"脉场咖啡带你乐享白领品质生活！"
                            ,shareListener,"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx59e8d7bfd1164813&redirect_uri=https%3a%2f%2fwww.maichangapp.com%2" +
                                    "fspace%2factivity%3fid%3d"+id+"&response_type=code&scope=snsapi_base&state=wx#wechat_redirect",img);


                }
            });
        }else{
            mShare_img.setVisibility(View.GONE);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String title = view.getTitle();//设置网页自带标题
                if (!TextUtils.isEmpty(title)) {
                    titleNameText.setText(title);
                }
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                String str = "javascript:appEnter("+ SharePreferenceUtils.getBaseSharePreference().readUserId()+",'"+ SharePreferenceUtils.getBaseSharePreference().readToken()+"');";
                webView.loadUrl(str);

            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
                                        public void onProgressChanged(WebView view, int progress) {
                                            if (progress == 100) {
                                                pg.setVisibility(View.GONE);//加载完网页进度条消失
//                                                DialogUtil.getInstance().closeLoadingDialog();
                                            } else {
//                                                DialogUtil.getInstance().showLoadingDialog(WebViewNewActivity.this, getString(R.string.loading));//加载动画
                                                pg.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                                                pg.setProgress(progress);//设置进度值
                                            }
                                        }

                                       @Override
                                       public void onReceivedTitle(WebView view, String title) {//设置网页自带的标题
                                           super.onReceivedTitle(view, title);
                                           if (title != null) {
                                               titleNameText.setText(title);
                                           }
                                       }

                                   }
        );
    }
    public void actionKey(final int keyCode) {
        new Thread () {
            public void run () {
                try {
                    Instrumentation inst=new Instrumentation();
                    inst.sendKeyDownUpSync(keyCode);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rl_webView.removeAllViews();
    }
    /**
     * 使点击回退按钮不会直接退出整个应用程序而是返回上一个页面
     *
     *
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
//                finish();
                return true;
            }
            //                Intent intent = new Intent(this, Activity.class);
//                startActivity(intent);
//                finish();
//                System.exit(0);

        }
        return super.onKeyDown(keyCode, event);
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
//            SocializeUtils.safeShowDialog(shareAlertDialog);
            DialogUtil.getInstance().showLoadingDialog(WebViewNewActivity.this, getString(R.string.loading));//加载动画
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
