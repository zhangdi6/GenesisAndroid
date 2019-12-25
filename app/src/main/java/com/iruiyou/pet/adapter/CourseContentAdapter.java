package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.ZQImageViewRoundOval;

/**
 * 职场-职场课程-课程内容 适配器
 * 作者：sgf on 2018/10/16 19:00
 */
public class CourseContentAdapter extends BaseQuickAdapter<GetCourseIntroBean.DataBean, BaseViewHolder> {
//    private OnItemClickListener onItemClickListener;
//    private OnItemLongClickListener onItemLongClickListener;
//    private OnTextViewClickListener onTextViewClickListener;
    private int vipLevel;
    private double zhekou;
    private double zhekouPrice;
    private CourseContentAdapter courseContentAdapter;
    public CourseContentAdapter() {
        super(R.layout.adapter_course_content);
        courseContentAdapter=this;
        vipLevel= SharePreferenceUtils.getBaseSharePreference().readVipLevel();
        switch (vipLevel)
        {
            case 5: //初级会员
                zhekou=0.9;
                break;
            case 1:
                zhekou=0.85;
                break;
            case 2:
                zhekou=0.75;
                break;
            case 3:
                zhekou=0.65;
                break;
            case 4:
                zhekou=0.55;
                break;
        }
    }

//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

//    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
//        onItemLongClickListener = listener;
//    }

//    public void setOnTextViewClickListener(OnTextViewClickListener listener) {
//        onTextViewClickListener = listener;
//    }

    @Override
    protected void convert(BaseViewHolder helper, GetCourseIntroBean.DataBean item) {
        try {
            GetCourseIntroBean.DataBean.TeacherBasicBean teacherBasic = item.getTeacherBasic();
            ZQImageViewRoundOval im_headIv_course = helper.getView(R.id.im_headIv_course);//课程图片
            im_headIv_course.setType(ZQImageViewRoundOval.TYPE_ROUND);
            im_headIv_course.setRoundRadius(6);//矩形凹行大小
            ImageView iv_course_head = helper.getView(R.id.iv_course_head);//头像
            LinearLayout ll_my = helper.getView(R.id.ll_my);//我的资料布局
            helper.setText(R.id.tv_course_title, item.getTitle());//课程标题
            helper.setText(R.id.tv_course_introduce, item.getDesc());//课程介绍
            if(zhekou>0)
            {
                helper.setText(R.id.text_before_zhekou, item.getPrice() + "");//水晶价格
                helper.setText(R.id.text_zhekou,zhekou+"折");
                helper.setText(R.id.tv_course_crystal,  (int)Math.ceil(item.getPrice()*zhekou) + "");//水晶价格
            }
            else
            {
                helper.setText(R.id.tv_course_crystal, item.getPrice() + "");//水晶价格
            }
            GlideUtils.display(BaseApi.baseUrlNoApi + item.getImages().get(0).getPath(), im_headIv_course);
            if (teacherBasic != null) {
                ll_my.setVisibility(View.GONE);
                helper.setText(R.id.tv_course_name, teacherBasic.getRealName());//姓名
                helper.setText(R.id.tv_course_information, teacherBasic.getCompany() + Constant.LARGE_SPACE + teacherBasic.getPosition());//信息

                GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getImages().get(0).getPath(), iv_course_head);
            } else {
                ll_my.setVisibility(View.GONE);
            }

        } catch (Exception e) {
        }

        helper.setVisible(R.id.linear_zhekou,(zhekou>0));

//        helper.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              getOnItemClickListener().onItemClick(courseContentAdapter,view,helper.getAdapterPosition());
//            }
//        });

//        if (onItemLongClickListener != null) {
//            helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    onItemLongClickListener.onItemLongClick(helper.getAdapterPosition());
//                    return false;
//                }
//            });
//        }
    }


//    public interface OnItemClickListener {
//        void onClick(int position);
//    }

//    public interface OnItemLongClickListener {
//        void onItemLongClick(int position);
//    }

//    public interface OnTextViewClickListener {
//        void onTextViewClick(int position);
//    }
}
