package com.iruiyou.pet.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.server.broadcast.BroadcastManager;
import com.iruiyou.pet.bean.MaichangBean;
import com.iruiyou.pet.utils.Constant;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * 脉场节点合伙人适配器
 * sgf
 */
public class NodePartnerGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<MaichangBean.DataBean.NodesBean> mList;
    private List<MaichangBean.DataBean.RelationsBean> relationsList;
    private int selectorPosition;
    private int num = 0;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;
    private OnAddClickListener onAddClickListener;
    private OnFollowClickListener onFollowClickListener;
    private int friendStatus;

    public NodePartnerGridViewAdapter(Context context, List<MaichangBean.DataBean.NodesBean> mList, List<MaichangBean.DataBean.RelationsBean> relationsList) {
        this.mContext = context;
        this.mList = mList;
        this.relationsList = relationsList;

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        onItemLongClickListener=listener;
    }
    public void setOnTextViewClickListener(OnTextViewClickListener listener){
        onTextViewClickListener=listener;
    }
    public void setOnAddClickListener(OnAddClickListener listener){
        onAddClickListener=listener;
    }
    public void setOnFollowClickListener(OnFollowClickListener listener){
        onFollowClickListener=listener;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mList != null ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mList != null ? position : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, R.layout.item_gridview_node_partner, null);
        LinearLayout linearLayout = convertView.findViewById(R.id.ll);
        TextView tv_describe_node_partner = convertView.findViewById(R.id.tv_describe_node_partner);
        TextView tv_name_node_partner = convertView.findViewById(R.id.tv_name_node_partner);
        Button bt_follow_node_partner = convertView.findViewById(R.id.bt_follow_node_partner);
        Button bt_add_friends_node_partner = convertView.findViewById(R.id.bt_add_friends_node_partner);
        TextView tv_company_node_partner = convertView.findViewById(R.id.tv_company_node_partner);
        RoundedImageView im_headIv_node_partner = convertView.findViewById(R.id.im_headIv_node_partner);
        if(mList!=null){
            tv_describe_node_partner.setText(mList.get(position).getCityNode()+mContext.getString(R.string.node_partner2));
            MaichangBean.DataBean.NodesBean.BasicInfoBean basicInfo = mList.get(position).getBasicInfo();
            if(basicInfo!=null){
                String positionuser = basicInfo.getPosition();
                String company = basicInfo.getCompany();
                if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionuser)) {
                    tv_company_node_partner.setText(positionuser);
                } else if (TextUtils.isEmpty(positionuser) && !TextUtils.isEmpty(company)) {
                    tv_company_node_partner.setText(company);
                } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(positionuser)) {
                    tv_company_node_partner.setText(company + Constant.LARGE_SPACE + positionuser);
                }
//                tv_company_node_partner.setText(basicInfo.getCompany()+Constant.LARGE_SPACE+basicInfo.getPosition());
                tv_name_node_partner.setText(basicInfo.getRealName());
                GlideUtils.displayRound(mContext, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), im_headIv_node_partner);
            }
        }
        if (relationsList!=null) {
            //好友
            friendStatus = relationsList.get(position).getFriendStatus();

            //接收广播-通过验证
            BroadcastManager.getInstance(mContext).addAction(Constant.AGREE, new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String command = intent.getAction();
                    if(!TextUtils.isEmpty(command)){
                        if((Constant.AGREE).equals(command)) {//有好友申请，显示红点
                            bt_add_friends_node_partner.setText(mContext.getString(R.string.send));
                            friendStatus = 3;
                            //添加自定义消息
                        }
                    }
                }
            });
            //0陌生人； 1申请； 2拒绝； 3好友； 4黑名单
            if(friendStatus == 0){
                bt_add_friends_node_partner.setText(mContext.getString(R.string.add_friends));
            }else if(friendStatus == 1){
                bt_add_friends_node_partner.setText(mContext.getString(R.string.verification));
            }else if(friendStatus == 2){
                bt_add_friends_node_partner.setText(mContext.getString(R.string.add_friends));
            }else if(friendStatus == 3){
                bt_add_friends_node_partner.setText(mContext.getString(R.string.send));
            }else if(friendStatus == 4){
                bt_add_friends_node_partner.setText(mContext.getString(R.string.send));
            }
            //关注
            boolean followed = relationsList.get(position).isFollowed();
            if(followed){
                bt_follow_node_partner.setText(mContext.getString(R.string.unfollow));
            }else {
                bt_follow_node_partner.setText(mContext.getString(R.string.follow));
            }
        }

        //如果当前的position等于传过来点击的position,就去改变他的状态
//        if (selectorPosition == position) {
//            mRelativeLayout.setBackgroundResource(R.drawable.crystal_blue_shape);
//            tv_crystal_price.setTextColor(Color.parseColor("#72c6ae"));
//            tv_rmb_price.setTextColor(Color.parseColor("#72c6ae"));
//        } else {
//            //其他的恢复原来的状态
//            mRelativeLayout.setBackgroundResource(R.drawable.power_gray_shape);
//            tv_crystal_price.setTextColor(Color.parseColor("#666666"));
//            tv_rmb_price.setTextColor(Color.parseColor("#666666"));
//        }

        //添加
        bt_add_friends_node_partner.setOnClickListener(new View.OnClickListener() {//tv0
            @Override
            public void onClick(View v) {
//                            onItemClickListener.onClick(helper.getAdapterPosition());
                onAddClickListener.onTextViewClick(position);
            }
        });
        //关注
        bt_follow_node_partner.setOnClickListener(new View.OnClickListener() {//tv0
            @Override
            public void onClick(View v) {
//                            onItemClickListener.onClick(helper.getAdapterPosition());
                notifyDataSetChanged();
                onFollowClickListener.onTextViewClick(position);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(position);
            }
        });


        //添加GridView从右到左平移动画
//        if (num == 0) {
//            convertView.setVisibility(View.INVISIBLE);
//            int count = 3 - position % 3;
//            final TranslateAnimation translateAnimation = new TranslateAnimation(
//                    Animation.RELATIVE_TO_SELF,
//                    count,
//                    Animation.RELATIVE_TO_SELF,
//                    0,
//                    Animation.RELATIVE_TO_SELF,
//                    0,
//                    Animation.RELATIVE_TO_SELF,
//                    0);
//            translateAnimation.setDuration(count * 100);
////   final Animation animation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.slide_in_right);
//            final View finalConvertView = convertView;
//            convertView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    finalConvertView.startAnimation(translateAnimation);
//                }
//            }, position * 200);
//            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//                    finalConvertView.setVisibility(View.VISIBLE);
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
////                if (position == mList.size() - 1) {
////                    translateAnimation.cancel();
////                }
//                    num =1;
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//
//                }
//            });
//            translateAnimation.cancel();
//        }
        return convertView;
    }


    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }
    public interface OnItemClickListener {
        void onClick(int position);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int position);
    }
    public interface OnTextViewClickListener{
        void onTextViewClick(int position);
    }
    public interface OnFollowClickListener{
        void onTextViewClick(int position);
    }
    public interface OnAddClickListener{
        void onTextViewClick(int position);
    }
}
