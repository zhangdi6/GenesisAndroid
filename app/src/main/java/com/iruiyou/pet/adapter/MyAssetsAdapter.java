package com.iruiyou.pet.adapter;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.MyAssetsRecord;

/**
 * 类描述:我的资产适配器
 * 创建日期:2018/5/26 on 15:11
 * 作者:JiaoPeiRong
 */
public class MyAssetsAdapter extends BaseQuickAdapter<MyAssetsRecord.DataBean, BaseViewHolder> {
    public MyAssetsAdapter() {
        super(R.layout.adapter_my_assets);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyAssetsRecord.DataBean item) {
        helper.setText(R.id.name, item.getType())
                .setText(R.id.time, DataUtils.getStringDate(item.getTime()))
                .setText(R.id.value, String.valueOf(item.getAmount()));

        TextView tv = helper.getView(R.id.value);
        //0表示收入 红色显示 前缀+
        if (item.getIncome() == 0){
            tv.setText("+"+String.valueOf(item.getAmount()));
            tv.setTextColor(mContext.getResources().getColor(R.color._912629));
            //1表示支出 绿色显示 前缀-
        } else if (item.getIncome() == 1){
            tv.setText("-"+String.valueOf(item.getAmount()));
            tv.setTextColor(mContext.getResources().getColor(R.color._2b771f));
        }
    }
}
