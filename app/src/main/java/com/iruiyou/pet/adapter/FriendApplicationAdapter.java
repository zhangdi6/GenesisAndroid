package com.iruiyou.pet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetAppliersBean;
import com.iruiyou.pet.utils.Constant;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

/**
 * 好友申请 适配器
 * 作者：sgf
 */
public class FriendApplicationAdapter extends BaseQuickAdapter<GetAppliersBean.DataBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;
    private static TextView invitation1;
    private static TextView invitation0;
    private static Context context;

    public FriendApplicationAdapter(Context context) {
//        super(R.layout.adapter_friend_application);
        super(R.layout.adapter_contacts);
        FriendApplicationAdapter.context = context;
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
    protected void convert(BaseViewHolder helper, GetAppliersBean.DataBean item) {
        //接收广播-显示红点
//        BroadcastManager.getInstance(mContext).addAction(Constant.ADDED_VISIBLE, new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                String command = intent.getAction();
//                if(!TextUtils.isEmpty(command)){
//                    if((Constant.ADDED_VISIBLE).equals(command)) {//有好友申请，显示红点
//                        //获取json结果
//                        String json = intent.getStringExtra("result");
//                        //做你该做的事情
//                        invitation0.setVisibility(View.GONE);
//                        invitation1.setTextColor(Color.parseColor("#666666"));
//                        invitation1.setText(context.getString(R.string.added));
//                    }
//                }
//            }
//        });
//        helper.setText(R.id.time, DataUtils.getStringDate(item.getTime()));
//        TextView tv_ship_message = helper.getView(R.id.tv_ship_message);
        ImageView im_new_header = helper.getView(R.id.headIv);
        invitation1 = helper.getView(R.id.invitation1);
        invitation0 = helper.getView(R.id.invitation0);

        int statusA2B = item.getStatusA2B();
        int statusB2A = item.getStatusB2A();
        if(statusA2B == 3 && statusB2A == 3){
            invitation0.setVisibility(View.GONE);
            invitation1.setVisibility(View.VISIBLE);
            invitation1.setTextColor(Color.parseColor("#666666"));
            invitation1.setText(mContext.getString(R.string.added));
        }else {
            invitation1.setVisibility(View.GONE);
            invitation0.setText(mContext.getString(R.string.accept));
        }


        invitation0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onTextViewClickListener.onTextViewClick(helper.getAdapterPosition());
            }
        });
//
//        TextView amount = helper.getView(R.id.amounts);
//        if (item.getAmount() >= 0){
//            amount.setText("+ " +item.getAmount());
//            amount.setTextColor(Color.parseColor("#aa1b1b"));
//        } else {
//            amount.setText(item.getAmount()+"");
//            amount.setTextColor(Color.parseColor("#2b771f"));
//        }
        String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
        if(readUserId.equals(String.valueOf(item.getUserIdA()))){//自己
            helper.setText(R.id.name, item.getBasicInfoB().getRealName())
                    .setText(R.id.details, "");
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoB().getHeadImg(), im_new_header);
        }else {
            helper.setText(R.id.name, item.getBasicInfoA().getRealName())
                    .setText(R.id.details, "");
            GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoA().getHeadImg(), im_new_header);
            //刷新用户头像到融云上
            RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID+item.getBasicInfoA().getUserId(), item.getBasicInfoA().getRealName(), Uri.parse(BaseApi.baseUrlNoApi+item.getBasicInfoA().getHeadImg())));//刷新同步头像昵称到融云

        }

//        helper.setText(R.id.tv_ship_name, item.getBasicInfoB().getRealName())
//                .setText(R.id.tv_ship_state, item.getBasicInfoB().getRealName());
//        GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + item.getBasicInfoB().getHeadImg(), im_new_header);

        if (onItemClickListener!=null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(helper.getAdapterPosition());

                }
            });
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
    public static void setContent(){
        invitation0.setVisibility(View.GONE);
        invitation1.setVisibility(View.VISIBLE);

        invitation1.setTextColor(Color.parseColor("#666666"));
        invitation1.setText(context.getString(R.string.added));
    }
}
