package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CheckFriendsBean;
import com.iruiyou.pet.bean.CheckRegisterBean;

import java.util.List;
import java.util.Map;

/**
 * 我的好友 适配器
 * 作者：sgf on 2018/10/16 19:00
 */
public class MyFriendsAdapter extends BaseQuickAdapter<CheckFriendsBean.DataBean, BaseViewHolder> {
    private Map<String, Boolean> map;
    private List<CheckRegisterBean.DataBean> isAcconntList;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public MyFriendsAdapter() {
        super(R.layout.adapter_contacts);
    }

//    public void setNewDatas(List<String> list) {
//        setNewData(list);
//        this.map = map;
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
    protected void convert(BaseViewHolder helper, CheckFriendsBean.DataBean item) {
//        String[] split = item.split("\\" + Fragment2.Person);
//        String[] split1 = item.split("\\" + Fragment2.SIGN);//街区*后的通讯录姓名
        try {
//            helper.setText(R.id.name, split[0])
//                    .setText(R.id.details, split[1]);

            TextView tv0 = helper.getView(R.id.invitation0);
            TextView tv1 = helper.getView(R.id.invitation1);
            ImageView iv = helper.getView(R.id.headIv);
            tv0.setVisibility(View.GONE);
            tv1.setVisibility(View.GONE);
            String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
            if (readUserId.equals(String.valueOf(item.getUserIdA()))) {//自己
                helper.setText(R.id.name, item.getBasicInfoB().getRealName())
                        .setText(R.id.details, "");
                GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoB().getHeadImg(), iv);
            } else {
                helper.setText(R.id.name, item.getBasicInfoA().getRealName())
                        .setText(R.id.details, "");
                GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoA().getHeadImg(), iv);
            }
//                if (map.get(split[1])) {
//                    tv0.setVisibility(View.GONE);
//                    tv1.setVisibility(View.GONE);
//                    tv1.setText(mContext.getResources().getString(R.string.AlreadyInvited));
////                    tv0.setClickable(true);
//                } else {//未邀请
//                    tv0.setVisibility(View.GONE);
//                    tv1.setVisibility(View.GONE);
//                    tv0.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
////                            onItemClickListener.onClick(helper.getAdapterPosition());
//                            onTextViewClickListener.onTextViewClick(helper.getAdapterPosition());
//                        }
//                    });
//                }
//            }

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
