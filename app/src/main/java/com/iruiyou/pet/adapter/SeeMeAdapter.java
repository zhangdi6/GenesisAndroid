package com.iruiyou.pet.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BasicInfoBean;
import com.iruiyou.pet.bean.SeeMeLogBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class SeeMeAdapter extends BaseQuickAdapter<SeeMeLogBean.SeeLogInfo, BaseViewHolder> {
    private Context context;

    public SeeMeAdapter(Context context) {
        super(R.layout.item_see_me);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SeeMeLogBean.SeeLogInfo item) {
        RoundedImageView imageView = helper.getView(R.id.image_head);
        imageView.setImageResource(R.drawable.icon_head_default_new);
        BasicInfoBean basicInfoBean = item.getFromInfo();
        if(basicInfoBean!=null){
            helper.setText(R.id.text_self_desc, basicInfoBean.getSelfDesc()).setText(R.id.text_name, basicInfoBean.getRealName())
                    .setText(R.id.text_commpany_name, basicInfoBean.getCompany()).setText(R.id.text_zhiwei_name, basicInfoBean.getPositionTitle());
            if (!TextUtils.isEmpty(basicInfoBean.getHeadImg())) {
                String url = BaseApi.baseUrlNoApi + basicInfoBean.getHeadImg();
                url = url.replace("//img", "/img");
                GlideUtils.displayRound(context, url, imageView);
            }
        }
    }
}
