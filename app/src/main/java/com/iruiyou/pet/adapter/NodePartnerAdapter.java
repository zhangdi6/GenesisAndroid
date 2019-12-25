package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.MaichangBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Objects;


/**
 * 节点合伙人 列表
 */
public class NodePartnerAdapter extends RecyclerView.Adapter<NodePartnerAdapter.MyViewHolder> {

    private Context context;
    private View view;//List<MaichangBean.DataBean.NodesBean> nodes
    private List<MaichangBean.DataBean.NodesBean>dataBeanList;

    public NodePartnerAdapter(Context context, List<MaichangBean.DataBean.NodesBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }


    @Override
    public NodePartnerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_node_partner, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NodePartnerAdapter.MyViewHolder holder, int position) {
        MaichangBean.DataBean.NodesBean nodesBean = dataBeanList.get(position);
        if(nodesBean!=null){
            String cityNode = dataBeanList.get(position).getCityNode();
            holder.tvNodePartnerPlaceItem.setText(cityNode);

            MaichangBean.DataBean.NodesBean.BasicInfoBean basicInfo = dataBeanList.get(position).getBasicInfo();
            if (basicInfo!=null) {
                String headImg = basicInfo.getHeadImg();
                String realName = basicInfo.getRealName();
                holder.tvNodePartnerName.setText(realName);
                GlideUtils.display(BaseApi.baseUrlNoApi + headImg, holder.imNodePartnerHeadItem);
            }
        }
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNodePartnerPlaceItem;
        TextView tvNodePartnerName;
        RoundedImageView imNodePartnerHeadItem;


        public MyViewHolder(View itemView) {
            super(itemView);
            imNodePartnerHeadItem = itemView.findViewById(R.id.imNodePartnerHeadItem);
            tvNodePartnerPlaceItem = itemView.findViewById(R.id.tvNodePartnerPlaceItem);
            tvNodePartnerName = itemView.findViewById(R.id.tvNodePartnerName);

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
