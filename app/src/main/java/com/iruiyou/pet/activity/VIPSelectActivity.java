package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.StringUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 购买会员的选择界面
 */
public class VIPSelectActivity extends BaseActivity {
//    @BindView(R.id.relay_zhifubao)
//    RelativeLayout relayZhifubao;

//    @BindView(R.id.relay_crystal)
//    RelativeLayout relayCrystal;

    private String vipUrl;
    private int vipType;

    @Override
    public int getLayout() {
        return R.layout.activity_vip_select;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("购买方式");
        Intent intent = getIntent();
        vipUrl = intent.getStringExtra("url");
        vipType = intent.getIntExtra("vipType", -1);
    }

    @OnClick(value = {R.id.ll_title_left_view, R.id.relay_zhifubao, R.id.relay_crystal})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ll_title_left_view:
                finish();
                break;
            case R.id.relay_zhifubao:
                if (StringUtil.isNotEmpty(vipUrl)) {
                    StartActivityManager.startWebViewActivity(VIPSelectActivity.this, "", vipUrl);
                }
                break;
            case R.id.relay_crystal:
                if (-1 != vipType) {
                    StartActivityManager.startOpenMembershipActivity
                            (VIPSelectActivity.this, vipType);
                }
                break;
        }
    }

}
