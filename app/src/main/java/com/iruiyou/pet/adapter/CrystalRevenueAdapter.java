package com.iruiyou.pet.adapter;

import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.utils.CodeUtils;

public class CrystalRevenueAdapter extends BaseQuickAdapter<HarvestBean.DataBean.CrystalRevenueRecordsBean, BaseViewHolder> {
    public CrystalRevenueAdapter() {
        super(R.layout.adapter_my_wallet);
    }

    @Override
    protected void convert(BaseViewHolder helper, HarvestBean.DataBean.CrystalRevenueRecordsBean item) {
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getCrystalRecordsByCode((int)item.getType()));

        TextView amount = helper.getView(R.id.amounts);
        if (item.getAmount()>0){
//            String pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_Eight);
            amount.setText("+ " + item.getAmount());
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        } else {
            amount.setText(item.getAmount()+"");
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
        long income = item.getIncome();
        if(income==0){
            amount.setText("+ " + item.getAmount());
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        }else if("1".equals(income+"")){
            amount.setText("- " + item.getAmount());
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
    }
}
