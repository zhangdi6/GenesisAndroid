package com.iruiyou.pet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.InformationBean;
import com.iruiyou.pet.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfomationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final int TYPE_NORMAL = 0;
    private final int TYPE_HEAD = 1;
    private LayoutInflater inflater;
    private List<InformationBean.DataBean> data;
    public InfomationAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setNewData(List<InformationBean.DataBean> dataTemp){
        data.clear();
        data.addAll(dataTemp);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return TYPE_HEAD;
        } else {
            return TYPE_NORMAL;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEAD:
                return new HeadHolder(inflater.inflate(R.layout.item_infomation_head, parent, false));
            case TYPE_NORMAL:
                return new MyViewHolder(inflater.inflate(R.layout.item_infomation, parent, false));
        }
        return null;
    }

    class HeadHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image_zixun_head)
        ImageView image_zixun_head;

        @BindView(R.id.text_title)
        TextView text_title;

        @BindView(R.id.text_time)
        TextView text_time;


        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(InformationBean.DataBean itemData){
            text_title.setText(itemData.getContent());
            text_time.setText(TimeUtil.formatForDate(itemData.getTime()));
            if(itemData.getImages()!=null){
                GlideUtils.displayRoundedCorner(context, BaseApi.baseUrlNoApi + itemData.getImages().get(0).getPath(),image_zixun_head,10);
            }
        }

    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.text_zixun_one)
        TextView text_zixun_one;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(InformationBean.DataBean itemData){
            text_zixun_one.setText(itemData.getContent());
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InformationBean.DataBean dataBeanInformation = data.get(position);
        if(holder instanceof HeadHolder){
            ((HeadHolder)holder).setData(dataBeanInformation);
        }else{
            ((MyViewHolder)holder).setData(dataBeanInformation);
        }
        holder.itemView.setOnClickListener(view -> {
            GetEssaysBean.DataBean dataBean = new GetEssaysBean.DataBean();
            GetEssaysBean.DataBean.BasicInfoBean basicInfoBean = new GetEssaysBean.DataBean.BasicInfoBean();
            dataBean.setReferenceId(dataBeanInformation.getReferenceId());
            dataBean.setReferenceContent(dataBeanInformation.getReferenceContent());
            basicInfoBean.set_id(dataBeanInformation.getBasicInfo().get_id());
            dataBean.setBasicInfo(basicInfoBean);
            StartActivityManager.startEssayDetail(context,dataBean);
        });
    }

    public InformationBean.DataBean getItem(int position){
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
