package com.iruiyou.pet.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CombatRankingBean;
import com.iruiyou.pet.utils.Constant;

/**
 * 类描述:排行榜适配器
 * 创建日期:2018/9/6 on 17:00
 * 作者:JiaoPeiRong
 */
public class CombatRankingAdapter extends BaseQuickAdapter<CombatRankingBean.DataBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    public CombatRankingAdapter() {
        super(R.layout.adapter_combat_ranking);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    protected void convert(BaseViewHolder helper, CombatRankingBean.DataBean item) {
        int adapterPosition = helper.getAdapterPosition();
        TextView tv = helper.getView(R.id.leftTag);
        tv.setText("");
        switch (adapterPosition) {
            case 1:
                tv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.one));
                break;
            case 2:
                tv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.two));
                break;
            case 3:
                tv.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.three));
                break;
            default:
                tv.setBackgroundDrawable(null);
                tv.setText(String.valueOf(adapterPosition));
                break;
        }

        helper.setText(R.id.name, item.getRealName())
                .setText(R.id.value, item.getCombat());
        String company = item.getCompany();
        String itemPosition = item.getPosition();
        if(TextUtils.isEmpty(company)&&!TextUtils.isEmpty(itemPosition)){
            helper.setText(R.id.tvContent,itemPosition);
        }else if(TextUtils.isEmpty(itemPosition)&&!TextUtils.isEmpty(company)){
            helper.setText(R.id.tvContent,company);
        }else if(!TextUtils.isEmpty(company)&&!TextUtils.isEmpty(itemPosition)){
            helper.setText(R.id.tvContent,company + Constant.LARGE_SPACE + itemPosition);
        }
//        helper.setText(R.id.tvContent,item.getCompany()+"\t--\t"+item.getPosition());
        ImageView iv = helper.getView(R.id.headIv);
        GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getHeadImg(), iv);

        if (onItemClickListener!=null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(helper.getAdapterPosition());
                }
            });
        }

    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
}
