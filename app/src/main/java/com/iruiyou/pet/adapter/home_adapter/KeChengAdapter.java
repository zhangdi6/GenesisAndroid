package com.iruiyou.pet.adapter.home_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetCourseIntroBean;

import java.util.List;

public class KeChengAdapter extends RecyclerView.Adapter {


    private List<GetCourseIntroBean.DataBean> list;
    private OnItemClickListener onItemClickListener;



    public KeChengAdapter(List<GetCourseIntroBean.DataBean> mlist) {
        this.list = mlist;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.home_kecheng,parent,false);
        return new KeChengViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GetCourseIntroBean.DataBean itemsBean =  list.get(position);
        Log.i("onBindViewHolder", "onBindViewHolder: ");
        KeChengViewHolder holder1 = (KeChengViewHolder) holder;
        holder1.home_kecheng_title.setText(itemsBean.getTitle());
        holder1.home_kecheng_name.setText(itemsBean.getDesc());
        holder1.home_kechen_price.setText("￥"+itemsBean.getPrice()+"");
        Glide.with(holder1.home_kechen_price.getContext()).load( BaseApi.baseUrlNoApi + itemsBean.getMinImage()).into(holder1.home_kecheng_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.getclicklistener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size()>2){
            return 2;
        }else{
            return list.size()>0?list.size():0;

        }
    }


    public class KeChengViewHolder extends RecyclerView.ViewHolder {


        private ImageView home_kecheng_img;

        private TextView home_kecheng_title;
        private TextView home_kecheng_name;
        private TextView home_kechen_price;


        public KeChengViewHolder(View itemView) {
            super(itemView);
            home_kecheng_img =  itemView.findViewById(R.id.home_kecheng_img);
            home_kecheng_title =  itemView.findViewById(R.id.home_kecheng_title);
            home_kecheng_name =  itemView.findViewById(R.id.home_kecheng_name);
            home_kechen_price =  itemView.findViewById(R.id.home_kechen_price);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }

}
