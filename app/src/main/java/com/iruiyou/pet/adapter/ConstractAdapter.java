package com.iruiyou.pet.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.ConstractBean;

import java.util.List;

public class ConstractAdapter extends RecyclerView.Adapter {


    private List<ConstractBean.ConData> mlist;
    private OnItemClickListener onItemClickListener;

    public ConstractAdapter(List<ConstractBean.ConData> mlist) {
        this.mlist = mlist;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =   LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_constract,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder holder1 = (ViewHolder) holder;
        ConstractBean.ConData conData = mlist.get(position);
        holder1.title.setText("脉场空间"+"（"+conData.getShop()+")"+conData.getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.getconstract(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.constract_title);

        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getconstract(int position);
    }

}

