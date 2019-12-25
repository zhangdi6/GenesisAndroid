package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BriefRefreshBean;

/**
 * 类描述:教育经历适配器
 * 创建日期:2018/5/26 on 15:11
 * 作者:JiaoPeiRong
 */
public class EduAdapter extends BaseQuickAdapter<BriefRefreshBean.DataBean.EducationInfosBean, BaseViewHolder> {

    public EduAdapter() {
        super(R.layout.adpter_edu);
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
        helper.addOnClickListener(R.id.workLl);

//        helper.setText(R.id.job, item.getSchool())
//                .setText(R.id.company, CodeUtils.getInstance().getEducationByCode(item.getEducation()) + "  " + item.getMajor())
//                .setText(R.id.time, item.getDuration());

        helper.setText(R.id.job, item.getSchool())
                .setText(R.id.company, item.getMajor())
                .setText(R.id.time, item.getDuration());

    }


}
