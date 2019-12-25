package com.iruiyou.pet.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.ZQImageViewRoundOval;

/**
 * 首页的课程适配器
 */
public class MainClassAdapter extends BaseQuickAdapter<GetCourseIntroBean.DataBean, BaseViewHolder> {
    public MainClassAdapter() {
        super(R.layout.item_main_class);
    }

    @Override
    protected void convert(BaseViewHolder helper, GetCourseIntroBean.DataBean item) {
        GetCourseIntroBean.DataBean.TeacherBasicBean teacherBasic = item.getTeacherBasic();
        ZQImageViewRoundOval im_headIv_course = helper.getView(R.id.im_headIv_course);//课程图片
        im_headIv_course.setType(ZQImageViewRoundOval.TYPE_ROUND);
        im_headIv_course.setRoundRadius(6);//矩形凹行大小
        GlideUtils.display(BaseApi.baseUrlNoApi + item.getImages().get(0).getPath(), im_headIv_course);
        helper.setText(R.id.tv_course_title, item.getTitle());//课程标题
        if(helper.getAdapterPosition()!=0)
        {
            helper.setGone(R.id.view_jiange,true);
        }
        else
        {
            helper.setGone(R.id.view_jiange,false);
        }
    }
}
