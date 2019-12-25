package com.iruiyou.pet.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CrashRecordBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;

public class WithdrawalRecordsAdapter extends BaseQuickAdapter<CrashRecordBean.Data, BaseViewHolder> {

    private Activity activity;
    public WithdrawalRecordsAdapter(Activity activity) {
        super(R.layout.adapter_my_wallet);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CrashRecordBean.Data item) {
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getCrashRecord(item.getType()));

        TextView amount = helper.getView(R.id.amounts);
        if (item.getAmount() >= 0){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("+ " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        } else {
            amount.setText(item.getAmount() + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
        int income = item.getIncome();
        if(0==income){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("+ " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        }else if(1==income){
            double pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
            amount.setText("- " + pbsAmount + activity.getString(R.string.pbs));
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
    }
}
