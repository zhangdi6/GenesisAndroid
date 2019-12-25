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
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.CodeUtils;
import com.iruiyou.pet.utils.Constant;

/**
 * 我的钱包页面 孵化收支记录适配器
 * 作者：sgf
 */
public class IncubationAssetsAdapter extends BaseQuickAdapter<HarvestBean.DataBean.HatchesBean, BaseViewHolder> {
    private Activity activity;
    public IncubationAssetsAdapter(Activity activity) {
        super(R.layout.adapter_my_wallet);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, HarvestBean.DataBean.HatchesBean item) {
        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
        TextView tv = helper.getView(R.id.type);
        tv.setText(CodeUtils.getInstance().getHatches(item.getType()));
        String pbsAmount = BigDecimalUtil.round(item.getAmount(), Constant.SCALE_NUM_FOUR);
        TextView amount = helper.getView(R.id.amounts);
        if (!TextUtils.isEmpty(item.getAmount())){
            amount.setText("+ " +pbsAmount);
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        } else {
            amount.setText(pbsAmount);
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
        String income = item.getIncome();
        if("0".equals(income)){
            amount.setText("+ " + pbsAmount);
            amount.setTextColor(Color.parseColor("#e32626"));//红色
        }else if("1".equals(income)){
            amount.setText("- " + pbsAmount);
            amount.setTextColor(Color.parseColor("#72c6ae"));//蓝色
        }
    }
}
