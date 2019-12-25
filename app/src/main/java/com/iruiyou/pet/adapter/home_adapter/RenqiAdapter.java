package com.iruiyou.pet.adapter.home_adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;

import java.util.List;

public class RenqiAdapter extends RecyclerView.Adapter {


    private List<OpportunitiesGoodsBean.Item> list;


    public RenqiAdapter(List<OpportunitiesGoodsBean.Item> mlist) {
        this.list = mlist;
    }

    private OnItemClickListener onItemClickListener;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_recommend,parent,false);
        //layout_renqi
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        OpportunitiesGoodsBean.Item itemsBean =  list.get(position);
        ViewHolder holder1 = (ViewHolder) holder;
        holder1.tv_title.setText(itemsBean.getTitle());
        holder1.tv_price.setText("￥"+itemsBean.getPrice()/100+".00");
        Glide.with(holder1.tv_price.getContext()).load(itemsBean.getImage()).into(holder1.img_view);
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
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView img_view;

        private TextView tv_title;
        private TextView tv_price;


        public ViewHolder(View itemView) {
            super(itemView);
            img_view =  itemView.findViewById(R.id.img_view);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_price =  itemView.findViewById(R.id.tv_price);
        }
    }






    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }


}
