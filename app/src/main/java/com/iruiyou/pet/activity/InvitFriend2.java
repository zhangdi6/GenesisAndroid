package com.iruiyou.pet.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：邀请好友
 * 作者：jiaopeirong on 2018/8/11 10:19
 * 邮箱：chinajpr@163.com
 */
public class InvitFriend2 extends BaseActivity {

    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.mCode)
    ImageView mCode;
    @Override
    public int getLayout() {
        return R.layout.activity_invit_friend2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.invitingFriends));
        //获取缓存的数据拿到二维码图片地址
        ConfigBean configBean= App.getConfigBean();
        if(configBean!=null&&configBean.getData()!=null)
        {
            String qrCodePath = configBean.getData().getQrCodePath();
            if(!TextUtils.isEmpty(qrCodePath)){
                GlideUtils.display(BaseApi.baseUrlNoApi + qrCodePath, mCode);//邀请的二维码
            }
        }
        code.setText(SharePreferenceUtils.getBaseSharePreference().readInviteCode());
    }

    @OnClick(R.id.save)
    public void onViewClicked() {
        ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        Objects.requireNonNull(cm).setText(code.getText());
        T.showShort(getResources().getString(R.string.ReplicatingSuccess));
    }

}
