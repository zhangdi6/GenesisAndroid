package com.iruiyou.pet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CourseBean;

import java.util.List;


/**
 * 职场-职场课程 列表
 */
public class WorkplaceCourseAdapter extends RecyclerView.Adapter<WorkplaceCourseAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<CourseBean> dataBeanList;
    private int selectorPosition;

    public WorkplaceCourseAdapter(Context context, List<CourseBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }

    public CourseBean getItem(int position)
    {
        if(dataBeanList!=null&&dataBeanList.size()>position)
        {
            return dataBeanList.get(position);
        }
        return null;
    }


    @Override
    public WorkplaceCourseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_recycler_workplace_course, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WorkplaceCourseAdapter.MyViewHolder holder, int position) {
        holder.tv_coursetext.setText(dataBeanList.get(position).getCourseText());
        //BaseApi.baseUrlNoApi +
        GlideUtils.displayRound(context, dataBeanList.get(position).getCourseIcon(), holder.imHead_course);
        //如果当前的position等于传过来点击的position,就去改变他的状态
        if (selectorPosition == position) {
            holder.tv_coursetext.setTextColor(Color.parseColor("#72c6ae"));
            holder.tv_coursebackground.setVisibility(View.VISIBLE);
        } else {
            //其他的恢复原来的状态
            holder.tv_coursetext.setTextColor(Color.parseColor("#666666"));
            holder.tv_coursebackground.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_coursetext;
        TextView tv_coursebackground;
        ImageView imHead_course;


        public MyViewHolder(View itemView) {
            super(itemView);
            imHead_course = itemView.findViewById(R.id.imHead_course);
            tv_coursetext =  itemView.findViewById(R.id.tv_coursetext);
            tv_coursebackground =  itemView.findViewById(R.id.tv_coursebackground);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(position);
                }
            });
        }
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    private MyItemClickListener mItemClickListener;

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
//        void onItemClick(OccupationBeen firstPageListBean, int position);
        void onItemClick(int position);
    }

    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }

}
