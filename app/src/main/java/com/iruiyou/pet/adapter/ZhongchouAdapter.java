package com.iruiyou.pet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.ZhongChouBean;

import java.util.List;

public class ZhongchouAdapter extends RecyclerView.Adapter {

    private ZhongchouAdapter.OnItemClickListener onItemClickListener;
    private List<ZhongChouBean.DataBean> list;


    private int Type;


    public ZhongchouAdapter(List<ZhongChouBean.DataBean> mlist, int i){
        this.list = mlist ;
        this.Type = i;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (Type==1){
            View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhungchou,parent,false);
            return new ViewHolder(inflate);
        }else{
            View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_zhongchou,parent,false);

            return new ViewHolders(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ZhongChouBean.DataBean itemsBean  =  list.get(position);

        if(Type==1){
            ViewHolder holder1 = (ViewHolder) holder;


            holder1.currentNum.setText("参与股东："+itemsBean.getCurrentNum()+"人/");
            holder1.overallNum.setText(itemsBean.getOverallNum()+"人");
            holder1.currentMoney.setText("投资金额："+itemsBean.getCurrentMoney()+"万/");
            holder1.overallMoney.setText(itemsBean.getOverallMoney()+"万");
            holder1.title.setText (itemsBean.getTitle());
            float x = itemsBean.getCurrentMoney();
            float y =itemsBean.getOverallMoney();
            holder1.progressBar.setProgress((int) ((int)x/y*100));

            Log.i("1234567890",itemsBean.getCurrentNum()/itemsBean.getOverallNum()+"");
            int type = itemsBean.getType();

            if (type==0){
                holder1.type.setImageResource(R.drawable.zhongchou_choujian);
            }else if (type==1){
                holder1.type.setImageResource(R.drawable.zhongchou_zhuangxiu);
            }else  if (type==2){
                holder1.type.setImageResource(R.drawable.zhongchou_wancheng);
            }
            else if (type==3){
                holder1.type.setImageResource(R.drawable.zhongchou_weikaiqi);
            }
            Glide.with(holder1.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription()).into(holder1.description);

            // GlideUtils.displayRound(holder1.description.getContext(), BaseApi.baseUrlNoApi + itemsBean.getDescription(), holder1.description);

        }
        else{
            ViewHolders holder2 = (ViewHolders) holder;
            holder2.title.setText(itemsBean.getTitle());
            int types = itemsBean.getType();
            if (types==1){
                holder2.type.setText("正在筹备中");
            }else if (types==2){
                holder2.type.setText("即将开业");
            }

            Glide.with(holder2.title.getContext()).load(BaseApi.baseUrlNoApi + itemsBean.getDescription()).into(holder2.description);
        }



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
        if (Type==1){
            return list.size();
        }else{
            return 2;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //	标题(店铺名)
        private final TextView title;
        //	描述图
        private final ImageView description;
        //	当前人数
        private TextView currentNum;
        //总体人数
        private TextView overallNum;
        //当前资金
        private TextView currentMoney;
        //总体资金
        private TextView overallMoney;
        //众筹状态(0:筹建中,1:装修中,2:已完成,3:未开启)
        private ImageView type;

        private ProgressBar progressBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.zhongchou_iv);
            title = itemView.findViewById(R.id.zhongchou_name);
            currentMoney = itemView.findViewById(R.id.zhongchou_small_tou);
            overallMoney = itemView.findViewById(R.id.zhongchou_big_tou);
            currentNum = itemView.findViewById(R.id.zhongchou_small_join);
            overallNum = itemView.findViewById(R.id.zhongchou_big_join);
            type = itemView.findViewById(R.id.zhongchou_img);
            progressBar = itemView.findViewById(R.id.zhongchou_progressBar);

        }
    }




    public class ViewHolders extends RecyclerView.ViewHolder {
        //	标题(店铺名)
        private final TextView title;
        //	描述图
        private final ImageView description;
        //众筹状态(0:筹建中,1:装修中,2:已完成,3:未开启)
        private TextView type;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.zhongchou_iv);
            title = itemView.findViewById(R.id.zhongchou_name);
            type = itemView.findViewById(R.id.zhongchou_img);

        }
    }



    public void setOnItemClickListener(ZhongchouAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }

}
