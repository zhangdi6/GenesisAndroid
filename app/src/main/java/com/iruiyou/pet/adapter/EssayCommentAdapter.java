package com.iruiyou.pet.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BasicInfoBean;
import com.iruiyou.pet.bean.EssayCommonBean;
import com.makeramen.roundedimageview.RoundedImageView;

public class EssayCommentAdapter extends BaseQuickAdapter<EssayCommonBean, BaseViewHolder> {

    private Context context;

    public EssayCommentAdapter(Context context) {
        super(R.layout.item_essay_comments);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, EssayCommonBean item) {
        BasicInfoBean basicInfoBean=item.getBasicInfo();
        helper.setText(R.id.text_name,basicInfoBean.getRealName()).
                setText(R.id.text_commpany_name,basicInfoBean.getCompany()).
                setText(R.id.text_position_title,basicInfoBean.getPositionTitle()).setText(R.id.text_comment_content,item.getContent());
        RoundedImageView imageView= helper.getView(R.id.image_head);
        if(!TextUtils.isEmpty(basicInfoBean.getHeadImg()))
        {
            String url= BaseApi.baseUrlNoApi+basicInfoBean.getHeadImg();
            url=url.replace("//img","/img");
            GlideUtils.displayRound(context,url,imageView);
        }
        else
        {
            imageView.setImageResource(R.drawable.icon_head_default_new);
        }
    }
}
