package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BriefRefreshBean;

/**
 * 类描述:用户资料教育经历适配器
 */
public class UserEduAdapter extends BaseQuickAdapter<BriefRefreshBean.DataBean.EducationInfosBean, BaseViewHolder> {

    public UserEduAdapter() {
        super(R.layout.adpter_user_edu);
    }

    @Override
    protected void convert(BaseViewHolder helper, BriefRefreshBean.DataBean.EducationInfosBean item) {
        LinearLayout linearLayout = helper.getView(R.id.workLl);

        int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition != 0) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }

//        if (getData() != null && getData().size() >= 3){
//            helper.getView(R.id.addIv).setVisibility(View.GONE);
//        } else {
//            helper.getView(R.id.addIv).setVisibility(View.VISIBLE);
//        }
        helper.addOnClickListener(R.id.addLl);

        helper.setText(R.id.job, item.getSchool());
//        helper.setText(R.id.company, CodeUtils.getInstance().getEducationByCode(item.getEducation()) + "  " + item.getMajor());
        helper.setText(R.id.company, item.getMajor());
        helper.setText(R.id.time, item.getDuration());
    }


}
