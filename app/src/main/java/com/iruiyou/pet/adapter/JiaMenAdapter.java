package com.iruiyou.pet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.JiaMenBean;

import java.util.List;

public class JiaMenAdapter extends RecyclerView.Adapter {



    private List<JiaMenBean.DataBean> list;

    private OnItemClickListener onItemClickListener;
    public JiaMenAdapter(List<JiaMenBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jiamen,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        JiaMenBean.DataBean itemsBean  =  list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        //description
        Glide.with(holder1.description.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription()).into(holder1.description);


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

        if (list.size()>0){

            return list.size();
        }else{
            return 1;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //加盟图片
        private final ImageView description;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.spaces_img);


        }
    }

    public void setOnItemClickListener(JiaMenAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }



}
