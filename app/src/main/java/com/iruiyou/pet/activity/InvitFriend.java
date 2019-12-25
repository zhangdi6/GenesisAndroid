package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 类描述：邀请好友
 * 作者：jiaopeirong on 2018/8/11 10:19
 * 邮箱：chinajpr@163.com
 */
public class InvitFriend extends BaseActivity {
    @BindView(R.id.webview)
    WebView webview;

    @Override
    public int getLayout() {
        return R.layout.activity_invit_friend;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        webview.loadUrl(BaseApi.baseUrlNoApi + "genesis/invite" + "?token=" + SharePreferenceUtils.getBaseSharePreference().readToken() + "&userId=" + SharePreferenceUtils.getBaseSharePreference().readUserId());
    }

}
