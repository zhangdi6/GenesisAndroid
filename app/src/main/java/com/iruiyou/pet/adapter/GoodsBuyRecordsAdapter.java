package com.iruiyou.pet.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GoodsBuyRecordsBean;
import com.iruiyou.pet.utils.StringUtil;

public class GoodsBuyRecordsAdapter extends BaseQuickAdapter<GoodsBuyRecordsBean.DataBean, BaseViewHolder> {

    public GoodsBuyRecordsAdapter() {
        super(R.layout.item_goods_buy_records);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBuyRecordsBean.DataBean item) {
        helper.setText(R.id.text_order_id, "交易订单ID：" + item.getYzTid());
        String orderStatus = "";
        switch (item.getStatus()) {
            case "WAIT_BUYER_CONFIRM_GOODS": //已发货
                orderStatus = "已发货";
                break;
            case "WAIT_BUYER_PAY": //等待付款
                orderStatus = "待付款";
                break;
        }
        helper.setText(R.id.text_order_state, orderStatus);
        if (StringUtil.isNotEmpty(item.getItemName())) {
            helper.setText(R.id.text_title, item.getItemName());
        }

//        double price = BigDecimalUtil.round(item.getPrice() / 100, 2);
//        double total = BigDecimalUtil.round((double) item.getTotal() / 100, 2);
        helper.setText(R.id.text_buy_price, "¥" + item.getPrice() + "");
        helper.setText(R.id.text_buy_count, "×" + item.getNum() + "");
        helper.setText(R.id.text_phone, "手机号：" + item.getPhone());
        helper.setText(R.id.text_total_price, "¥" + item.getTotal() + "");

    }
}
