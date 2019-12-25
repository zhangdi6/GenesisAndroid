package com.iruiyou.pet.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.utils.CodeUtils;

/**
 * 我的钱包页面 水晶收支记录适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class MyCrystalAdapter extends BaseQuickAdapter<HarvestBean.DataBean.CrystalRecordsBean, BaseViewHolder> {
    private Activity activity;
    public MyCrystalAdapter(Activity activity) {
        super(R.layout.adapter_my_wallet);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarvestBean.DataBean.CrystalRecordsBean item) {
        item.toString();
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getCrystalByCode(item.getType()));

        TextView amount = helper.getView(R.id.amounts);
        if (!TextUtils.isEmpty(item.getAmount())){
//            String pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_Eight);
            amount.setText("+ " + item.getAmount());
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        } else {
            amount.setText(item.getAmount());
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
        String income = item.getIncome();
        if("0".equals(income)){
            amount.setText("+ " + item.getAmount());
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        }else if("1".equals(income)){
            amount.setText("- " + item.getAmount());
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
    }
}
