package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BlackCardActivity extends BaseActivity {


    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.into_wx)
    Button into_wx;

    @Override
    public int getLayout() {
        return R.layout.layout_black_card;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        into_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String appid = "wxf580fa050af6696b";//AppId
                IWXAPI wxapi = WXAPIFactory.createWXAPI(getApplication(), appid);
                WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                req.userName = "gh_a67efc0c0a97";//小程序id
                req.miniprogramType = WXLaunchMiniProgram.Req.MINIPTOGRAM_TYPE_RELEASE ;
                wxapi.sendReq(req);
            }
        });

    }
}
