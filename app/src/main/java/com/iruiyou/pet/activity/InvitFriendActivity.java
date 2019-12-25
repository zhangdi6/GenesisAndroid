package com.iruiyou.pet.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.http.UrlContent;
import com.iruiyou.common.utils.ACache;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.ConfigBean;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.DialogUtils;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类描述：新邀请好友
 * 作者：sgf on 2018
 */
public class InvitFriendActivity extends BaseActivity {

    @BindView(R.id.tv_codeNum)
    TextView code;
    @BindView(R.id.im_Code)
    ImageView mCode;
    private ACache aCache;

    @Override
    public int getLayout() {
        return R.layout.activity_invite_friends;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.invitingFriends));
        setRightText(getResources().getString(R.string.copy_url));
        setRightImage(R.drawable.icon_app_share);
        setRightImageVisiable(View.VISIBLE);
//        showRightBg();//显示背景按钮
        aCache = ACache.get(this);
        //获取缓存的数据拿到二维码图片地址
        ConfigBean configBean = App.getConfigBean();
        if(configBean!=null&&configBean.getData()!=null)
        {
            ConfigBean.DataBean data = configBean.getData();
            String qrCodePath = data.getQrCodePath();
            if(!TextUtils.isEmpty(qrCodePath)){
                GlideUtils.display(BaseApi.baseUrlNoApi + qrCodePath, mCode);//邀请的二维码
            }
        }
        code.setText(SharePreferenceUtils.getBaseSharePreference().readInviteCode());
    }

    @OnClick({R.id.ll_copy, R.id.title_right_img})
    public void onViewClicked(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.ll_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                Objects.requireNonNull(cm).setText(code.getText());
                T.showShort(getResources().getString(R.string.ReplicatingSuccess));
                break;
            case R.id.title_right_img:
                String title= SharePreferenceUtils.getBaseSharePreference().readNickName()+"邀请你加入脉场";
                DialogUtils.showShareDialog(InvitFriendActivity.this,title,
                        "脉场为您提供商机和个人提升，是让人脉更有价值的商务社交平台。",shareListener, UrlContent.invited_url+code.getText(),"");


//                ClipboardManager cmRight = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                // 将文本内容放到系统剪贴板里。
//                Objects.requireNonNull(cmRight).setText(UrlContent.invited_url+code.getText());
//                T.showShort(getResources().getString(R.string.ReplicatingSuccess));
                break;
        }

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
            DialogUtil.getInstance().showLoadingDialog(InvitFriendActivity.this, getString(R.string.loading));//加载动画
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
