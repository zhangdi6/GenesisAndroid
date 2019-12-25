package com.iruiyou.pet.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.HarvestBean2;
import com.iruiyou.pet.utils.CodeUtils;

/**
 * 我的算力 适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class CalculationAdapter extends BaseQuickAdapter<HarvestBean2.DataBean.CombatListBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    public CalculationAdapter() {
        super(R.layout.adapter_my_wallet);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    protected void convert(BaseViewHolder helper, HarvestBean2.DataBean.CombatListBean item) {
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getCombatByCode(item.getType()));

        TextView amount = helper.getView(R.id.amounts);
        if (item.getAmount() >= 0){
            amount.setText("+ " +item.getAmount());
            amount.setTextColor(Color.parseColor("#aa1b1b"));
        } else {
            amount.setText(item.getAmount()+"");
            amount.setTextColor(Color.parseColor("#2b771f"));
        }

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
