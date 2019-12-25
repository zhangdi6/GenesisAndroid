package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CompanyMembersBean;

import java.util.List;
import java.util.Objects;


/**
 * 团队成员适配器
 */
public class TeamMembers2Adapter extends RecyclerView.Adapter<TeamMembers2Adapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<CompanyMembersBean.DataBean> dataBeanList;

    public TeamMembers2Adapter(Context context, List<CompanyMembersBean.DataBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }


    @Override
    public TeamMembers2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_adapter_team_members, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamMembers2Adapter.MyViewHolder holder, int position) {
//        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + dataBeanList.get(position).getHeadImg(), holder.imHeadItem);
        CompanyMembersBean.DataBean dataBean = dataBeanList.get(position);
        if(dataBean!=null){
            String company = dataBean.getCompany();
            String positions = dataBean.getPosition();
            if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positions)) {
                holder.tv_team_members_details.setText(positions);
            } else if (TextUtils.isEmpty(positions) && !TextUtils.isEmpty(company)) {
                holder.tv_team_members_details.setText(company);
            } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positions)) {
                holder.tv_team_members_details.setText(company + "\t\t" + positions);
            }
//            holder.tv_team_members_details.setText(dataBean.getCompany()+ Constant.LARGE_SPACE + dataBean.getPosition());
            holder.tv_team_members_name.setText(dataBean.getRealName());
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + dataBean.getHeadImg(), holder.im_team_members_headIv);
        }
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView im_team_members_headIv;
        TextView tv_team_members_name;
        TextView tv_team_members_details;


        public MyViewHolder(View itemView) {
            super(itemView);
            im_team_members_headIv = itemView.findViewById(R.id.im_team_members_headIv);
            tv_team_members_name = itemView.findViewById(R.id.tv_team_members_name);
            tv_team_members_details = itemView.findViewById(R.id.tv_team_members_details);

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
