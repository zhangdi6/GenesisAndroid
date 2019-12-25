package com.iruiyou.pet.activity.registerlast;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.pet.R;

import java.util.ArrayList;
import java.util.Objects;


/**
 * 联系人搜索职业列表
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {

    private Context context;
    private int typeView;
    private int typeViews = 0;
    private ArrayList<OccupationBeen> occupationBeens;
    private View view;
    //默认第几项
    private int mPosition = 0;

    public void setPosition(int position) {
        mPosition = position;
    }


    public SearchAdapter(Context context, ArrayList<OccupationBeen> occupationBeens, int typeView) {
        this.context = context;
        this.occupationBeens = occupationBeens;
        this.typeView = typeView;

    }


    @Override
    public MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(typeView == 1){
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_register, parent, false);
        }else if(typeView == 2){
             view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_search, parent, false);
        }

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, int position) {
        holder.occupationName.setText("" + occupationBeens.get(position).getLangValue());
//        if(mPosition == 0 && typeView == 2){
//            //获取图片资源的ID
//            int imageId = context.getResources().getIdentifier("all0","drawable", context.getPackageName());
//            holder.occupationPic.setImageResource(imageId);//R.drawable.all0
//            if(typeViews == 0){//强制设置为0时刷新第一个图片，否则不显示，且刷新后不需要再执行，故刷新后赋值为1，不再刷新，否则无法点击
//                new Handler().post(new Runnable() {
//                    @Override
//                    public void run() {
//                        // 刷新操作
//                        notifyDataSetChanged();
//                        typeViews = 1;
//                    }
//                });
//            }
//
//
//        }
        if (occupationBeens.get(position).isSelect()) {
            holder.occupationName.setTextColor(context.getResources().getColor(R.color._26c68a));

            Glide.with(context).load(occupationBeens.get(position).getPicUrlSelectTrue()).into(holder.occupationPic);
            if(typeView == 2 && position == 0){//设置第一张全部的图标，其他图片是拼凑的链接获取的
                holder.occupationPic.setImageResource(R.drawable.all1);
            }
        } else {
            holder.occupationName.setTextColor(context.getResources().getColor(R.color._999999));
            Glide.with(context).load(occupationBeens.get(position).getPicUrlSelectfalse()).into(holder.occupationPic);
            if(typeView == 2 && position == 0){
                holder.occupationPic.setImageResource(R.drawable.all0);
            }
        }

    }


    @Override
    public int getItemCount() {
        return occupationBeens.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView occupationName;
        ImageView occupationPic;


        public MyViewHolder(View itemView) {
            super(itemView);
            occupationPic = itemView.findViewById(R.id.imHeadItem);
            occupationName = itemView.findViewById(R.id.tvNameItem);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(occupationBeens.get(position), position);
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
        void onItemClick(OccupationBeen firstPageListBean, int position);
    }

}
