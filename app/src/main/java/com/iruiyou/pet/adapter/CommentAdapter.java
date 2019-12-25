package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.pet.R;

import java.util.List;
import java.util.Objects;


/**
 * 发现-评论
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<String> occupationBeens;
    private OnTextViewClickListener onTextViewClickListener;

    public CommentAdapter(Context context, List<String> occupationBeens) {
        this.context = context;
        this.occupationBeens = occupationBeens;

    }
    public void setOnTextViewClickListener(OnTextViewClickListener listener){
        onTextViewClickListener=listener;
    }

    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_find_comment, parent, false);
        return new CommentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommentAdapter.MyViewHolder holder, int position) {
        holder.tv_comment_name.setText("" + occupationBeens.get(position));
//        holder.tv_find_name.setTextColor(context.getResources().getColor(R.color._26c68a));
//            Glide.with(context).load(occupationBeens.get(position).getPicUrlSelectTrue()).into(holder.occupationPic);

    }
    @Override
    public int getItemCount() {
        return occupationBeens.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_comment_name;
        ImageView iv_comment_head;
        TextView tv_comment_time;//时间
        TextView tv_comment_introduce;//个人描述信息
        TextView tv_comment_content;//评论内容


        public MyViewHolder(View itemView) {
            super(itemView);
            iv_comment_head = itemView.findViewById(R.id.iv_comment_head);
            tv_comment_name = itemView.findViewById(R.id.tv_comment_name);
            tv_comment_time = itemView.findViewById(R.id.tv_comment_time);
            tv_comment_introduce = itemView.findViewById(R.id.tv_comment_introduce);
            tv_comment_content = itemView.findViewById(R.id.tv_comment_content);

//            im_pic.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onTextViewClickListener.onTextViewClick(getPosition());
//                }
//            });
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
    public interface OnTextViewClickListener{
        void onTextViewClick(int position);
    }

}
