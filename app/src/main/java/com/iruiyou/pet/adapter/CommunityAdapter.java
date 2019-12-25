package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GroupMembersBean;

import java.util.List;
import java.util.Objects;


/**
 * 群成员适配器
 */
public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<GroupMembersBean.DataBean> dataBeanList;

    public CommunityAdapter(Context context, List<GroupMembersBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }


    @Override
    public CommunityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_community, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommunityAdapter.MyViewHolder holder, int position) {
//        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + dataBeanList.get(position).getHeadImg(), holder.imHeadItem);
        GroupMembersBean.DataBean.BasicInfoBean basicInfo = dataBeanList.get(position).getBasicInfo();
        if(basicInfo!=null){
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), holder.imCommunityHeadItem);
        }
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imCommunityHeadItem;


        public MyViewHolder(View itemView) {
            super(itemView);
            imCommunityHeadItem = itemView.findViewById(R.id.imCommunityHeadItem);

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
