package com.iruiyou.pet.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.RecommendInterpersonalBean;
import com.iruiyou.pet.utils.StringUtil;
import com.makeramen.roundedimageview.RoundedImageView;

public class RecommendInterpersonalAdapter extends BaseQuickAdapter<RecommendInterpersonalBean.ItemBasicsInfo, BaseViewHolder> {

    private Context context;

    public RecommendInterpersonalAdapter(Context context){
        super(R.layout.item_recommend_interpersonal);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendInterpersonalBean.ItemBasicsInfo item) {
        helper.setText(R.id.text_name,item.getRealName());
        helper.setText(R.id.text_commpany_name,item.getCompany());
        helper.setText(R.id.text_zhiwei_name,item.getPosition());
        if(StringUtil.isNotEmpty(item.getSelfDesc())){
            helper.setText(R.id.text_self_desc,item.getSelfDesc());
        } else {
            helper.setText(R.id.text_self_desc,"");
        }


        RoundedImageView imageView= helper.getView(R.id.image_head);
        if(!TextUtils.isEmpty(item.getHeadImg()))
        {
            String url= BaseApi.baseUrlNoApi+item.getHeadImg();
            url=url.replace("//img","/img");
            GlideUtils.displayRound(context,url,imageView);
        }
        else
        {
            imageView.setImageResource(R.drawable.icon_head_default_new);
        }
    }
}
