package com.iruiyou.pet.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.DMOptionListBean;
import com.iruiyou.pet.utils.StringUtil;

import java.util.List;

public class PositionListAdapter extends BaseQuickAdapter<DMOptionListBean.DataBean, BaseViewHolder> {
    private String tag_area, tag_quanzhi;

    public PositionListAdapter() {
        super(R.layout.item_position);
    }

    public void setData(String tag_area, String tag_quanzhi, List<DMOptionListBean.DataBean> dataBeanList) {
        this.tag_area = tag_area;
        this.tag_quanzhi = tag_quanzhi;
        setNewData(dataBeanList);
    }

    @Override
    protected void convert(BaseViewHolder helper, DMOptionListBean.DataBean item) {
        helper.setText(R.id.text_position_title, item.getTitle()).setText(R.id.text_position_price, item.getSalary()).setText(R.id.text_position_company, item.getCompany_name());
        if (StringUtil.isNotEmpty(tag_area)) {
            helper.setText(R.id.text_tag_area, tag_area);
            helper.setVisible(R.id.text_tag_area, true);
        } else {
            helper.setVisible(R.id.text_tag_area, false);
        }

        if (StringUtil.isNotEmpty(tag_quanzhi)) {
            helper.setText(R.id.text_tag_quanzhi, tag_quanzhi);
            helper.setVisible(R.id.text_tag_quanzhi, true);
        } else {
            helper.setVisible(R.id.text_tag_quanzhi, false);
        }
    }


}
