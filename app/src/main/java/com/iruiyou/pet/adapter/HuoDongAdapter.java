package com.iruiyou.pet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.HuoDongBean;
import com.iruiyou.pet.utils.custom.BannerAdapterHelper;

import java.util.List;

public class HuoDongAdapter extends RecyclerView.Adapter {

    private OnItemClickListener onItemClickListener;

    private List<HuoDongBean.DataBean> list;
    BannerAdapterHelper bannerAdapterHelper = new BannerAdapterHelper();

    private int type;


    public HuoDongAdapter(List<HuoDongBean.DataBean> mlist, int i){
        this.list = mlist ;
        this.type = i;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (type==1){
            View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text,parent,false);
            return new ViewHolder(inflate);
        }else{
            View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_huodong,parent,false);
            if (list.size()>2){
                bannerAdapterHelper.onCreateViewHolder(parent,inflate);
            }

            return new ViewHolders(inflate);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {




        if (list.size()>2){
            HuoDongBean.DataBean itemsBean  =  list.get(position%list.size());

            if (type==1){
                ViewHolder holder1 = (ViewHolder) holder;
                holder1.title.setText (itemsBean.getTitle());
                holder1.peopleNum.setText(itemsBean.getPeopleNum()+"人已参加");
                if (itemsBean.getDescription().contains("https://")){
                    Glide.with(holder1.title.getContext()).load(itemsBean.getDescription())
                            .into(holder1.description);
                }else{
                    Glide.with(holder1.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription())
                            .into(holder1.description);

                }
            }else{
                ViewHolders holder2 = (ViewHolders) holder;
                holder2.title.setText (itemsBean.getTitle());
                holder2.peopleNum.setText(itemsBean.getPeopleNum()+"人已参加");
                if (itemsBean.getDescription().contains("https://")){
                    Glide.with(holder2.title.getContext()).load(itemsBean.getDescription())
                            .into(holder2.description);
                }else{
                    Glide.with(holder2.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription())
                            .into(holder2.description);

                }
                bannerAdapterHelper.onBindViewHolder(holder2.itemView,position,getItemCount());
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null){
                        onItemClickListener.getclicklistener(position % list.size());
                    }
                }
            });

        }else{
            HuoDongBean.DataBean itemsBean  =  list.get(position);
            if (type==1){
                ViewHolder holder1 = (ViewHolder) holder;
                holder1.title.setText (itemsBean.getTitle());
                holder1.peopleNum.setText(itemsBean.getPeopleNum()+"人已参加");
                if (itemsBean.getDescription().contains("https://")){
                    Glide.with(holder1.title.getContext()).load(itemsBean.getDescription())
                            .into(holder1.description);
                }else{
                    Glide.with(holder1.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription())
                            .into(holder1.description);

                }
            }else{
                ViewHolders holder2 = (ViewHolders) holder;
                holder2.title.setText (itemsBean.getTitle());
                holder2.peopleNum.setText(itemsBean.getPeopleNum()+"人已参加");
                if (itemsBean.getDescription().contains("https://")){
                    Glide.with(holder2.title.getContext()).load(itemsBean.getDescription())
                            .into(holder2.description);
                }else{
                    Glide.with(holder2.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription())
                            .into(holder2.description);

                }            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener!=null){
                        onItemClickListener.getclicklistener(position);
                    }
                }
            });
        }


       // GlideUtils.displayRound(holder1.description.getContext(), BaseApi.baseUrlNoApi + itemsBean.getDescription(), holder1.description);



    }

    @Override
    public int getItemCount() {
       /* if (list.size()>2){
            return Integer.MAX_VALUE;
        }else{*/
            if (type==2){
                return Integer.MAX_VALUE;
            }else{

                return list.size()>0?list.size():0;


            //}
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //	活动名称
        private final TextView title;
        //活动详情图
        private final ImageView description;
        //	参加活动人数
        private TextView peopleNum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.item_text_title);
            peopleNum = itemView.findViewById(R.id.item_text_jion);


        }
    }


    public class ViewHolders extends RecyclerView.ViewHolder {
        //	活动名称
        private final TextView title;
        //活动详情图
        private final ImageView description;
        //	参加活动人数
        private TextView peopleNum;


        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.item_text_title);
            peopleNum = itemView.findViewById(R.id.item_text_jion);


        }
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }




}


