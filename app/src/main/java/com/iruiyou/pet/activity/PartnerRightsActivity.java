package com.iruiyou.pet.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.StringUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 合伙人权益界面
 */
public class PartnerRightsActivity extends BaseActivity {

    @BindView(R.id.image_head)
    RoundedImageView imageHead;

    @BindView(R.id.image_genders)
    ImageView imageGenders;

    @BindView(R.id.tv_vip_level)
    TextView tvVipLevel;

    @BindView(R.id.text_name)
    TextView textName;

 @BindView(R.id.text_position)
    TextView text_position;

    @Override
    public int getLayout() {
        return R.layout.activity_partner_rights;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("合伙人权益");
        Intent intent = getIntent();
        if (intent != null) {
            String realName = intent.getStringExtra("realName");
            int crowdFundLevel = intent.getIntExtra("crowdFundLevel",0);
            int genders = intent.getIntExtra("genders",1);
            String imageHeadUrl = intent.getStringExtra("imageHeadUrl");
            String PositionTitle = intent.getStringExtra("PositionTitle");
            text_position.setText(PositionTitle);

            if(StringUtil.isNotEmpty(imageHeadUrl)){
                GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + imageHeadUrl, imageHead);
            }
            if (StringUtil.isNotEmpty(realName)) {
                textName.setText(realName);
            }

            if(genders == 0){
                imageGenders.setImageResource(R.drawable.icon_males);
            }else if(genders==1){
                imageGenders.setImageResource(R.drawable.icon_females);
            }

            GradientDrawable drawable = (GradientDrawable) tvVipLevel.getBackground();
           /* if(crowdFundLevel==1){ // 消费合伙人
                tvVipLevel.setText("消费合伙人");
                Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_6);
                drawableLeft.setBounds(0,0, Utils.dip2px(PartnerRightsActivity.this,16),Utils.dip2px(PartnerRightsActivity.this,16));
                tvVipLevel.setCompoundDrawables(drawableLeft,null,null,null);

                tvVipLevel.setTextColor(getResources().getColor(R.color._ff2890d1));
                drawable.setStroke(Utils.dip2px(PartnerRightsActivity.this,1),getResources().getColor(R.color._ff2890d1));
                tvVipLevel.setBackground(drawable);
                imageHead.setBorderColor(getResources().getColor(R.color._ff2890d1));
            }else */if(crowdFundLevel==1){ // 股权合伙人
                tvVipLevel.setText("股权合伙人");
                Drawable drawableLeft = getResources().getDrawable(R.drawable.vip_icon_7);
                drawableLeft.setBounds(0,0,Utils.dip2px(PartnerRightsActivity.this,16),Utils.dip2px(PartnerRightsActivity.this,16));
                tvVipLevel.setCompoundDrawables(drawableLeft,null,null,null);

                tvVipLevel.setTextColor(getResources().getColor(R.color._ffffa820));
                drawable.setStroke(Utils.dip2px(PartnerRightsActivity.this,1),getResources().getColor(R.color._ffffa820));
                tvVipLevel.setBackground(drawable);
                imageHead.setBorderColor(getResources().getColor(R.color._feb633));
            }
        }
    }

    @OnClick(value = {R.id.linear_fenhong, R.id.linear_online_text})
    public void viewOnClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.linear_fenhong:
                StartActivityManager.startWebViewNewActivity(PartnerRightsActivity.this,"每月分红","https://www.maichangapp.com/space/dividendList");
                break;
            case R.id.linear_online_text:
              //  StartActivityManager.startShowPDFActivity(PartnerRightsActivity.this);
                Intent intent = new Intent(PartnerRightsActivity.this, ContractActivity.class);
                startActivity(intent);
                break;
        }
    }
}
