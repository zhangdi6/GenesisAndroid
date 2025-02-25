package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BriefRefreshBean;

/**
 * 类描述:用户资料工作经历适配器
 */
public class UserWorkAdapter extends BaseQuickAdapter<BriefRefreshBean.DataBean.WorkInfosBean, BaseViewHolder> {

    public UserWorkAdapter() {
        super(R.layout.adpter_user_work);
    }

    @Override
    protected void convert(BaseViewHolder helper, BriefRefreshBean.DataBean.WorkInfosBean item) {
        LinearLayout linearLayout = helper.getView(R.id.workLl);
        int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition == 0) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
//        if (getData() != null && getData().size() >= 3){
//            helper.getView(R.id.add).setVisibility(View.GONE);
//        } else {
//            helper.getView(R.id.add).setVisibility(View.VISIBLE);
//        }
        helper.setText(R.id.job, item.getPosition())
                .setText(R.id.company, item.getCompany())
                .setText(R.id.time, item.getDuration())
                .addOnClickListener(R.id.add);
    }
}
