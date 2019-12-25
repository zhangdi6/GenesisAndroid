package com.iruiyou.pet.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.HarvestBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;

/**
 * 我的钱包页面 pbs收支记录适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class MyWalletAdapter extends BaseQuickAdapter<HarvestBean.DataBean.HarvestListBean, BaseViewHolder> {
    private Activity activity;
    public MyWalletAdapter(Activity activity) {
        super(R.layout.adapter_my_wallet);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarvestBean.DataBean.HarvestListBean item) {
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getPBSByCode(item.getType()));

        TextView amount = helper.getView(R.id.amounts);
        if (item.getAmount() >= 0){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("+ " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        } else {
            amount.setText(item.getAmount() + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
        String income = item.getIncome();
        if("0".equals(income)){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("+ " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        }else if("1".equals(income)){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("- " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
    }
}
