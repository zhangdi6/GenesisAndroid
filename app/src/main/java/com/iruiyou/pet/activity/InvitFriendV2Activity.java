package com.iruiyou.pet.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.DialogUtils;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请好友界面
 */
public class InvitFriendV2Activity extends BaseActivity {

    @BindView(R.id.text_invite_code)
    TextView text_invite_code;

    @Override
    public int getLayout() {
        return R.layout.activity_invit_friendv2;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.invitingFriends));
        setRightText(getResources().getString(R.string.copy_url));
        setRightImage(R.drawable.icon_app_share);
        setRightImageVisiable(View.VISIBLE);
        SharePreferenceUtils.getBaseSharePreference().readInviteCode();
        text_invite_code.setText(SharePreferenceUtils.getBaseSharePreference().readInviteCode());
    }


    @OnClick({R.id.btn_copy, R.id.title_right_img})
    public void onViewClicked(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.btn_copy:
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                Objects.requireNonNull(cm).setText(text_invite_code.getText());
                T.showShort("已复制邀请码到粘贴板");
                break;
            case R.id.title_right_img:
                String title= SharePreferenceUtils.getBaseSharePreference().readNickName()+"邀请你加入脉场";
                DialogUtils.showShareDialog(InvitFriendV2Activity.this,title,"脉场--提供商业与就业机会的人脉平台",
                        null,"https://www.maichangapp.com/space/invite?userId="+ SharePreferenceUtils.getBaseSharePreference().readUserId(),"");
                break;
        }
    }

}
