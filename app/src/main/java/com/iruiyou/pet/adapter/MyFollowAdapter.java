package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.FollowBean;

/**
 * 我的关注 适配器
 * 作者：sgf on 2018/10/16 19:00
 */
public class MyFollowAdapter extends BaseQuickAdapter<FollowBean.DataBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public MyFollowAdapter() {
        super(R.layout.adapter_contacts);
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
    protected void convert(BaseViewHolder helper, FollowBean.DataBean item) {
        TextView tv0 = helper.getView(R.id.invitation0);
        TextView tv1 = helper.getView(R.id.invitation1);
        ImageView iv = helper.getView(R.id.headIv);
        tv0.setVisibility(View.GONE);
        tv1.setVisibility(View.GONE);
        if(item.getBasicInfoB()!=null){
            helper.setText(R.id.name, item.getBasicInfoB().getRealName())
                    .setText(R.id.details, "");
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoB().getHeadImg(), iv);
        }

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
