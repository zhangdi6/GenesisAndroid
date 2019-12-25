package com.iruiyou.pet.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CheckFriendsBean;

public class MyContanctAdapter extends BaseQuickAdapter<CheckFriendsBean.DataBean, BaseViewHolder> {

    boolean isFromMessage = false;
    public MyContanctAdapter() {
        super(R.layout.item_relationship_search);
    }

    public boolean isFromMessage() {
        return isFromMessage;
    }

    public void setFromMessage(boolean fromMessage) {
        isFromMessage = fromMessage;
    }


    @Override
    protected void convert(BaseViewHolder helper, CheckFriendsBean.DataBean item) {
        String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
        ImageView iv = helper.getView(R.id.image_head);
        if (readUserId.equals(String.valueOf(item.getUserIdA()))) {//自己
            helper.setText(R.id.text_name, item.getBasicInfoB().getRealName());
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoB().getHeadImg(), iv);
            helper.setText(R.id.text_commpany_name,item.getBasicInfoB().getCompany()).
                    setText(R.id.text_zhiwei_name,item.getBasicInfoB().getPosition()).setText(R.id.text_self_desc,item.getBasicInfoB().getSelfDesc());
            if(isFromMessage){
                helper.setText(R.id.text_commpany_name,item.getBasicInfoB().getPositionTitle());
                helper.setVisible(R.id.text_zhiwei_name,false);
            }

        } else {
            helper.setText(R.id.text_name, item.getBasicInfoA().getRealName());
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoA().getHeadImg(), iv);
            helper.setText(R.id.text_commpany_name,item.getBasicInfoA().getCompany()).
                    setText(R.id.text_zhiwei_name,item.getBasicInfoA().getPosition()).setText(R.id.text_self_desc,item.getBasicInfoA().getSelfDesc());

            if(isFromMessage){
                helper.setText(R.id.text_commpany_name,item.getBasicInfoB().getPositionTitle());
                helper.setVisible(R.id.text_zhiwei_name,false);
            }
        }
    }
}
