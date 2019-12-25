package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetMyGroupsBean;

/**
 * 我的群聊 适配器
 * 作者：sgf on 2018/10/16 19:00
 */
public class MyGroupChatAdapter extends BaseQuickAdapter<GetMyGroupsBean.DataBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public MyGroupChatAdapter() {
        super(R.layout.adapter_group_chat);
    }

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
    protected void convert(BaseViewHolder helper, GetMyGroupsBean.DataBean item) {
        try {
            ImageView iv = helper.getView(R.id.headIv_group);
            helper.setText(R.id.name_group, item.getGroupName());
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getLogo(), iv);

        } catch (Exception e) {
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
