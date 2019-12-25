package com.iruiyou.pet.adapter.home_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.DMOptionListBean;

import java.util.List;

public class ZhiWeiAdapter extends RecyclerView.Adapter {

    private List<DMOptionListBean.DataBean> list;
    private OnItemClickListener onItemClickListener;

    public ZhiWeiAdapter(List<DMOptionListBean.DataBean> mlist) {
        this.list = mlist;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_position,parent,false);
        return new ZhiWeiViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DMOptionListBean.DataBean itemsBean =  list.get(position);
        ZhiWeiViewHolder holder1 = (ZhiWeiViewHolder) holder;
        holder1.text_position_company.setText(itemsBean.getCompany_name());
        holder1.text_position_price.setText(itemsBean.getSalary());
        holder1.text_position_title.setText(itemsBean.getJob_type_title());

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
        return 2;
    }
    public class ZhiWeiViewHolder extends RecyclerView.ViewHolder {


        private TextView text_position_price;
        private TextView text_position_title;
        private TextView text_position_company;


        public ZhiWeiViewHolder(View itemView) {
            super(itemView);
            text_position_price =  itemView.findViewById(R.id.text_position_price);
            text_position_title =  itemView.findViewById(R.id.text_position_title);
            text_position_company =  itemView.findViewById(R.id.text_position_company);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }




}
