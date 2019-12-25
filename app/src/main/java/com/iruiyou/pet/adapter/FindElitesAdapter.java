package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.RecomendsBean;

import java.util.List;
import java.util.Objects;


/**
 * 发现-精英推荐 列表
 */
public class FindElitesAdapter extends RecyclerView.Adapter<FindElitesAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<RecomendsBean.DataBean> dataBeanList;

    public FindElitesAdapter(Context context, List<RecomendsBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }


    @Override
    public FindElitesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_elites, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FindElitesAdapter.MyViewHolder holder, int position) {
        holder.tvNameElites.setText(dataBeanList.get(position).getBasicInfo().getRealName());
        holder.tvCompanyElites.setText(dataBeanList.get(position).getBasicInfo().getCompany());
        holder.tvPositionElites.setText(dataBeanList.get(position).getBasicInfo().getPosition());
//        holder.occupationName.setTextColor(context.getResources().getColor(R.color._26c68a));
//            Glide.with(context).load(dataBeanList.get(position).getHeadImg()).into(holder.occupationPic);
        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + dataBeanList.get(position).getHeadImg(), holder.imHeadItem);
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameElites;
        TextView tvCompanyElites;
        TextView tvPositionElites;
        ImageView imHeadItem;


        public MyViewHolder(View itemView) {
            super(itemView);
            imHeadItem = itemView.findViewById(R.id.imHeadElites);
            tvNameElites = itemView.findViewById(R.id.tvNameElites);
            tvCompanyElites = itemView.findViewById(R.id.tvCompanyElites);
            tvPositionElites = itemView.findViewById(R.id.tvPositionElites);

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

}
