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
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.OpportunitiesGoodsBean;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.utils.TypeOnClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商机-商品列表适配器
 */
public class OpportunitiesGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;
    private List<OpportunitiesGoodsBean.Item> mDatas;
    private Context context;
    public LayoutInflater mInflater;
    private TypeOnClick typeOnClick;
    private OnItemOnClickListener onItemOnClickListener;


    public OpportunitiesGoodsAdapter(Context context) {
        this.context = context;
        mDatas = new ArrayList<>();
        if (context != null) {
            mInflater = LayoutInflater.from(context);
        }
    }

    public void setOnItemOnClickListener(OnItemOnClickListener onItemOnClickListener) {
        this.onItemOnClickListener = onItemOnClickListener;
    }

    public void setTypeOnClick(TypeOnClick typeOnClick) {
        this.typeOnClick = typeOnClick;
    }

    public void setNewData(List<OpportunitiesGoodsBean.Item> data) {
        mDatas.clear();
        if (data != null) {
            mDatas.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void addData(List<OpportunitiesGoodsBean.Item> data) {
        if (data != null) {
            mDatas.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        OpportunitiesGoodsBean.Item item = mDatas.get(position);
        if (item.isHead()) {
            return TYPE_HEADER;
        } else {
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeadHolder(mInflater.inflate(R.layout.head_distribution, parent, false));
            case TYPE_NORMAL:
                return new ItemHolder(mInflater.inflate(R.layout.item_opportunities_good, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemHolder){
            ((ItemHolder)holder).setData(mDatas.get(position),position);
            if(onItemOnClickListener!=null){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(onItemOnClickListener!=null){
                            onItemOnClickListener.onItemClick(position,mDatas.get(position));
                        }
                    }
                });
            }
        }
    }


    class HeadHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.view_bottom_food)
        View bottomsFood;

        @BindView(R.id.view_bottom_all_goods)
        View bottomAllGoods;

        @BindView(R.id.view_bottom_digital)
        View bottomDigital;

        @BindView(R.id.view_bottom_nursing)
        View bottomNursing;

        @BindView(R.id.view_bottom_perinatal)
        View bottomPerinatal;



        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(value = {R.id.linear_food, R.id.linear_all_goods, R.id.linear_digital, R.id.linear_nursing, R.id.linear_perinatal})
        public void onClick(View view){
            int id=view.getId();
            switch (id){
                case R.id.linear_food:
                    bottomsFood.setVisibility(View.VISIBLE);
                    bottomAllGoods.setVisibility(View.INVISIBLE);
                    bottomDigital.setVisibility(View.INVISIBLE);
                    bottomNursing.setVisibility(View.INVISIBLE);
                    bottomPerinatal.setVisibility(View.INVISIBLE);
                    if(typeOnClick!=null){
                        typeOnClick.typeOnClick(id);
                    }
                    break;
                case R.id.linear_all_goods:
                    bottomsFood.setVisibility(View.INVISIBLE);
                    bottomAllGoods.setVisibility(View.VISIBLE);
                    bottomDigital.setVisibility(View.INVISIBLE);
                    bottomNursing.setVisibility(View.INVISIBLE);
                    bottomPerinatal.setVisibility(View.INVISIBLE);
                    if(typeOnClick!=null){
                        typeOnClick.typeOnClick(id);
                    }
                    break;
                case R.id.linear_digital:
                    bottomsFood.setVisibility(View.INVISIBLE);
                    bottomAllGoods.setVisibility(View.INVISIBLE);
                    bottomDigital.setVisibility(View.VISIBLE);
                    bottomNursing.setVisibility(View.INVISIBLE);
                    bottomPerinatal.setVisibility(View.INVISIBLE);
                    if(typeOnClick!=null){
                        typeOnClick.typeOnClick(id);
                    }
                    break;
                case R.id.linear_nursing:
                    bottomsFood.setVisibility(View.INVISIBLE);
                    bottomAllGoods.setVisibility(View.INVISIBLE);
                    bottomDigital.setVisibility(View.INVISIBLE);
                    bottomNursing.setVisibility(View.VISIBLE);
                    bottomPerinatal.setVisibility(View.INVISIBLE);
                    if(typeOnClick!=null){
                        typeOnClick.typeOnClick(id);
                    }
                    break;
                case R.id.linear_perinatal:
                    bottomsFood.setVisibility(View.INVISIBLE);
                    bottomAllGoods.setVisibility(View.INVISIBLE);
                    bottomDigital.setVisibility(View.INVISIBLE);
                    bottomNursing.setVisibility(View.INVISIBLE);
                    bottomPerinatal.setVisibility(View.VISIBLE);
                    if(typeOnClick!=null){
                        typeOnClick.typeOnClick(id);
                    }
                    break;
            }
        }

    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_lirunlv)
        TextView textLirunlv;

        @BindView(R.id.text_lirun)
        TextView textLirun;

        @BindView(R.id.text_shoujia)
        TextView textShoujia;

        @BindView(R.id.text_title)
        TextView textTitle;

        @BindView(R.id.image_left)
        ImageView imageLeft;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(OpportunitiesGoodsBean.Item item, int position){
            double lirun =(item.getPrice() - item.getCost_price() - (item.getPrice()*0.01));
            double lirunlv;
            if (item.getPrice() != 0) {
                lirunlv = BigDecimalUtil.round(lirun / (double) item.getPrice()*100,2);
            } else {
                lirunlv = -1;
            }
            textLirunlv.setText("利润率:" + lirunlv + "%");
            textLirun.setText("利润:" + BigDecimalUtil.round(lirun/100,2));
            textShoujia.setText("售价:" + BigDecimalUtil.round(((float)item.getPrice())/100,2));
            textTitle.setText(item.getTitle());
            if(StringUtil.isNotEmpty(item.getImage())){
                GlideUtils.display(item.getImage(), imageLeft);
            }
        }

    }

    public interface  OnItemOnClickListener{
        void onItemClick(int position, OpportunitiesGoodsBean.Item item);
    }
}
