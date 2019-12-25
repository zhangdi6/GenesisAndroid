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
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Objects;


/**
 * 挖矿-公司主页 列表
 */
public class CompanyHomeAdapter extends RecyclerView.Adapter<CompanyHomeAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<HomeRefreshBean.DataBean.CompaniesBean> dataBeanList;

    public CompanyHomeAdapter(Context context, List<HomeRefreshBean.DataBean.CompaniesBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }


    @Override
    public CompanyHomeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_company, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CompanyHomeAdapter.MyViewHolder holder, int position) {
        HomeRefreshBean.DataBean.CompaniesBean companiesBean = dataBeanList.get(position);
        if(companiesBean!=null){
            String name = dataBeanList.get(position).getName();
            String logo = dataBeanList.get(position).getLogo();
            int memberCount = dataBeanList.get(position).getMemberCount();
            holder.tvCompanyNameItem.setText(name);
            holder.tvCompanyNum.setText(memberCount+"");
//        holder.tvPositionElites.setText(dataBeanList.get(position).getBasicInfo().getPosition());
            GlideUtils.display(BaseApi.baseUrlNoApi + logo, holder.imCompanyHeadItem);
        }
    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvCompanyNameItem;
        TextView tvCompanyNum;
        RoundedImageView imCompanyHeadItem;


        public MyViewHolder(View itemView) {
            super(itemView);
            imCompanyHeadItem = itemView.findViewById(R.id.imCompanyHeadItem);
            tvCompanyNameItem = itemView.findViewById(R.id.tvCompanyNameItem);
            tvCompanyNum = itemView.findViewById(R.id.tvCompanyNum);

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
