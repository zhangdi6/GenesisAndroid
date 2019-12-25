package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.pet.R;

import java.util.List;

/**
 * PBS孵化
 * 作者：sgf on 2018/10/16 19:00
 */
public class PbsIncubationAdapter extends BaseQuickAdapter<List<String>, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public PbsIncubationAdapter() {
        super(R.layout.adapter_pbs_incubation);
    }

//    public void setNewDatas(List<FollowBean.DataBean> list) {
////        setNewData(list);
//        this.list = list;
//        this.isAcconntList = isAcconntList;
//    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    public void setOnTextViewClickListener(OnTextViewClickListener listener) {
        onTextViewClickListener = listener;
    }

    @Override
    protected void convert(BaseViewHolder helper,List<String> item) {
        Button bt_immediate_incubation = helper.getView(R.id.bt_immediate_incubation);//立即孵化按钮
        TextView tv_incubation_mode = helper.getView(R.id.tv_incubation_mode);//孵化方式
        TextView tv_expected_daily_rate_item = helper.getView(R.id.tv_expected_daily_rate_item);//预期日化率
        TextView tv_release_time = helper.getView(R.id.tv_release_time);//发布时间
        TextView tv_incubation_time_item = helper.getView(R.id.tv_incubation_time_item);//孵化时间


        helper.setText(R.id.tv_expected_daily_rate_item, item.get(helper.getAdapterPosition()));
//        if(item.getBasicInfo()!=null){
//            helper.setText(R.id.name, realName);
//            if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionuser)) {
//                helper.setText(R.id.details, positionuser);
//            } else if (TextUtils.isEmpty(positionuser) && !TextUtils.isEmpty(company)) {
//                helper.setText(R.id.details, company);
//            } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionuser)) {
//                helper.setText(R.id.details, company + Constant.LARGE_SPACE + positionuser);
//            }
//            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + headImg, iv);
//        }

        if (onItemClickListener != null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onClick(helper.getAdapterPosition());

                }
            });
        }

        if (onItemLongClickListener != null) {
            helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemLongClickListener.onItemLongClick(helper.getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface OnTextViewClickListener {
        void onTextViewClick(int position);
    }
}
