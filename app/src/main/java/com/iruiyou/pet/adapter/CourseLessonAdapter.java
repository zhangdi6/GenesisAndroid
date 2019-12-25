package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CourseLessonBean;

public class CourseLessonAdapter extends BaseQuickAdapter<CourseLessonBean, BaseViewHolder> {

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, int type);
    }

    public CourseLessonAdapter() {
        super(R.layout.adapter_course_lesson);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseLessonBean item) {

        helper.setText(R.id.tv_lesson_name, item.getTitle());
        if (item.getType() == 0) {
            helper.setText(R.id.tv_video_sign, "视频");
        } else {
            helper.setText(R.id.tv_video_sign, "音频");
        }
        helper.setText(R.id.tv_duration, "时长：" + item.getDuration());

        ImageView ivPlay = helper.getView(R.id.iv_play);
        TextView tvPlay = helper.getView(R.id.tv_play);

        if (item.isIsfree()){
          ivPlay.setImageResource(R.drawable.lessons_try);
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onClick(helper.getAdapterPosition(),1);

                }
            });
        }else if (!item.isIsfree()){
            ivPlay.setImageResource(R.drawable.lessons_buy);
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onClick(helper.getAdapterPosition(),2);

                }
            });
        }
       /* if (item.getPlaying() == 0) {
            ivPlay.setImageResource(R.drawable.video_play_normal);
            tvPlay.setText("点击播放");
            tvPlay.setTextColor(mContext.getResources().getColor(R.color._dedede));
        } else {
            ivPlay.setImageResource(R.drawable.video_play_play);
            tvPlay.setText("播放中");
            tvPlay.setTextColor(mContext.getResources().getColor(R.color._72c6ae));
        }*/



    }

}
