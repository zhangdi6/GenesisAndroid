package com.iruiyou.pet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetEssaysBean2;

import java.util.ArrayList;
import java.util.List;

public class HotItemComentAdapter extends RecyclerView.Adapter {

    public ArrayList<GetEssaysBean2.Comments> mList;
    private onItemClickListener onItemClick;

    public HotItemComentAdapter(ArrayList<GetEssaysBean2.Comments> comments) {
        this.mList = comments ;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hot_comment, parent, false);

        return new CommentViewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        CommentViewholder holder1 = (CommentViewholder) holder;
        holder1.name.setText(mList.get(position).getBasicInfo().getRealName());
        GlideUtils.displayRound(holder1.comment.getContext(),
                BaseApi.baseUrlNoApi + mList.get(position).getBasicInfo().getHeadImg(), holder1.img);
        holder1.comment.setText(mList.get(position).getContent());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick!=null){
                    onItemClick.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size()> 0 ? mList.size(): 0 ;
    }

    public void addData(List<GetEssaysBean2.Comments> comments) {
        mList.clear();
        mList.addAll(comments);
        notifyDataSetChanged();

    }

    public class CommentViewholder extends RecyclerView.ViewHolder{

        private  ImageView img;
        private  TextView name;
        private TextView comment;

        public CommentViewholder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_head);
            name = itemView.findViewById(R.id.tv_name);
            comment = itemView.findViewById(R.id.tv_comment);
        }
    }
    public interface onItemClickListener{
        void onItemClick(int position);
    }
    public void onItemClick(onItemClickListener listener){
        onItemClick = listener ;
    }
}
