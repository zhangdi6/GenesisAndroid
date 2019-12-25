package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.DCProductListBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;

import java.util.List;
import java.util.Objects;


/**
 * PBS孵化适配器
 */
public class PbsIncubation2Adapter extends RecyclerView.Adapter<PbsIncubation2Adapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<DCProductListBean.DCProductBean> dataBeanList;

    public PbsIncubation2Adapter(Context context, List<DCProductListBean.DCProductBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;

    }

    public void refreshData(List<DCProductListBean.DCProductBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }


    @Override
    public PbsIncubation2Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.adapter_pbs_incubation, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PbsIncubation2Adapter.MyViewHolder holder, int position) {
//        String string = dataBeanList.get(position);
//        holder.tv_incubation_mode.setText(dataBeanList.get(position));
//        CompanyMembersBean.DataBean dataBean = dataBeanList.get(position);
//        if(dataBean!=null){
//            String company = dataBean.getCompany();
//            String positions = dataBean.getPosition();
//            if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positions)) {
//                holder.tv_team_members_details.setText(positions);
//            } else if (TextUtils.isEmpty(positions) && !TextUtils.isEmpty(company)) {
//                holder.tv_team_members_details.setText(company);
//            } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positions)) {
//                holder.tv_team_members_details.setText(company + "\t\t" + positions);
//            }
//            holder.tv_team_members_name.setText(dataBean.getRealName());
//            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + dataBean.getHeadImg(), holder.im_team_members_headIv);
//        }
        DCProductListBean.DCProductBean productBean = dataBeanList.get(position);
        holder.tv_incubation_time_item.setText(context.getString(R.string.incubation_time)+productBean.getType()+ Constant.DAY);
        String rateString =  BigDecimalUtil.mul(productBean.getDayRate(),"100.0",2);

        holder.tv_expected_daily_rate_item.setText(rateString+ Constant.PERCENT_SIGN);
        holder.tv_release_time.setText(context.getString(R.string.release_time)+productBean.getPublishTime());


        holder.bt_immediate_incubation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mItemClickListener != null)
                    mItemClickListener.onItemClick(position);
            }
        });


    }
    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        Button bt_immediate_incubation;//立即孵化按钮
//        TextView tv_incubation_mode;//孵化方式
        TextView tv_expected_daily_rate_item;//预期日化率
        TextView tv_release_time;//发布时间
        TextView tv_incubation_time_item;//孵化时间


        public MyViewHolder(View itemView) {
            super(itemView);
            bt_immediate_incubation = itemView.findViewById(R.id.bt_immediate_incubation);
//            tv_incubation_mode = itemView.findViewById(R.id.tv_incubation_mode);
            tv_expected_daily_rate_item = itemView.findViewById(R.id.tv_expected_daily_rate_item);
            tv_release_time = itemView.findViewById(R.id.tv_release_time);
            tv_incubation_time_item = itemView.findViewById(R.id.tv_incubation_time_item);

//            itemView.setOnClickListen
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
