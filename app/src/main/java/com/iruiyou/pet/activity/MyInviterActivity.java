package com.iruiyou.pet.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的邀请人界面
 */
public class MyInviterActivity extends BaseActivity {
    @BindView(R.id.image_head)
    RoundedImageView image_head;
    @BindView(R.id.image_genders)
    ImageView image_genders;
    @BindView(R.id.text_name)
    TextView text_name;
    @BindView(R.id.text_position)
    TextView text_position;
    @BindView(R.id.text_phone)
    TextView text_phone;
    @BindView(R.id.text_invite_code)
    TextView text_invite_code;
    @BindView(R.id.text_desic)
    TextView text_desic;
    private String inviteCode;

    @Override
    public int getLayout() {
        return R.layout.activity_my_inviter;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("我的邀请人");
        inviteCode = getIntent().getStringExtra("inviteCode");
        if (StringUtil.isNotEmpty(inviteCode)) {
            text_invite_code.setText(inviteCode);
            new UserTask(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    Log.e("test","resulte is :"+resulte);
                    if(StringUtil.isNotEmpty(resulte)){
                        try {
                            JSONObject jsonObject = new JSONObject(resulte);
                            if(Constant.SUCCESS == jsonObject.optInt("statusCode")){
                                JSONObject data = jsonObject.getJSONObject("data");
                                text_name.setText(data.optString("realName"));
                                String phone = data.optString("phone");
                                if(StringUtil.isNotEmpty(phone)){
                                    String show = phone.substring(0,3)+"*****"+phone.substring(8,11);
                                    text_phone.setText(show);
                                }
                                String positionTitle = data.optString("positionTitle");
                                text_position.setText(positionTitle);
                                String imageHeadUrl = data.optString("headImg");
                                String selfDesc = data.optString("selfDesc");
                                if(StringUtil.isNotEmpty(imageHeadUrl)){
                                    GlideUtils.displayRound(MyInviterActivity.this, BaseApi.baseUrlNoApi + imageHeadUrl, image_head);
                                }
                                if(StringUtil.isNotEmpty(selfDesc)){
                                    text_desic.setText(selfDesc);
                                }
                            }else if(StringUtil.isNotEmpty(jsonObject.optString("message"))){
                                T.showShort(jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onError(ApiException e) {

                }
            }, MyInviterActivity.this).getUserInfoNew(inviteCode);
        }
    }
}
