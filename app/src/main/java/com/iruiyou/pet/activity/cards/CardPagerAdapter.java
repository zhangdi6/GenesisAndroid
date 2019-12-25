package com.iruiyou.pet.activity.cards;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.utils.StringUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    private Context context;
//    private int mPosition;
    private int num;
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnButtonClickListener onButtonClickListener;

    private int vipLevel;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        onItemLongClickListener = listener;
    }

    public void setOnButtonClickListener(OnButtonClickListener listener) {
        onButtonClickListener = listener;
    }

    public CardPagerAdapter(Context context) {
        this.context =context;
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public CardItem getItem(int position){
        if(mData!=null){
            return mData.get(position);
        }
        return null;
    }

    @Override
    public boolean isViewFromObject( View view,  Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
//        mPosition = position;
        bind(mData.get(position), view,position);
        CardView cardView = view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position,  Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view, final int position) {
        RoundedImageView headIv = view.findViewById(R.id.headIv);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_member_description = view.findViewById(R.id.tv_member_description);
        TextView contentTextView = view.findViewById(R.id.contentTextView);
        TextView tv_time_frame = view.findViewById(R.id.tv_time_frame);
        LinearLayout ll_open_membership = view.findViewById(R.id.ll_open_membership);

        LinearLayout linear_one = view.findViewById(R.id.linear_one);
        LinearLayout linear_two = view.findViewById(R.id.linear_two);
        LinearLayout linear_three = view.findViewById(R.id.linear_three);
        LinearLayout linear_four = view.findViewById(R.id.linear_four);
        LinearLayout ll_tv_equity5 = view.findViewById(R.id.ll_tv_equity5);
        LinearLayout ll_equity6 = view.findViewById(R.id.ll_equity6);
        LinearLayout ll_equity7 = view.findViewById(R.id.ll_equity7);

        LinearLayout linear_bottom_area = view.findViewById(R.id.linear_bottom_area);
//        LinearLayout ll_viewing_interests = view.findViewById(R.id.ll_viewing_interests);
        RelativeLayout ll_member_icon = view.findViewById(R.id.ll_member_icon);

        TextView tv_equity3 = view.findViewById(R.id.tv_equity3);
        TextView tv_equity4 = view.findViewById(R.id.tv_equity4);
        TextView tv_equity5 = view.findViewById(R.id.tv_equity5);
        TextView tv_equity6 = view.findViewById(R.id.tv_equity6);
        TextView tv_equity7 = view.findViewById(R.id.tv_equity7);
//        TextView text_bottom = view.findViewById(R.id.text_bottom);
        TextView tv_old_price = view.findViewById(R.id.tv_old_price);
        TextView tv_new_price = view.findViewById(R.id.tv_new_price);
        TextView text_jiyu_small = view.findViewById(R.id.text_jiyu_small);
        TextView text_jiyu = view.findViewById(R.id.text_jiyu);
        TextView tv_vip = view.findViewById(R.id.tv_vip);
        TextView tv_agree=view.findViewById(R.id.text_agree);
        TextView text_agree_hehuo=view.findViewById(R.id.text_agree_hehuo);
        TextView tv_open_membership = view.findViewById(R.id.tv_open_membership);
        TextView tv_membership_type = view.findViewById(R.id.tv_membership_type);
        ImageView im_membershipIcon1 = view.findViewById(R.id.im_membershipIcon1);
        ImageView im_membershipIcon2 = view.findViewById(R.id.im_membershipIcon2);
        ImageView im_membershipIcon3 = view.findViewById(R.id.im_membershipIcon3);
        ImageView im_membershipIcon4 = view.findViewById(R.id.im_membershipIcon4);
        ImageView im_membershipIcon5 = view.findViewById(R.id.im_membershipIcon5);
        ImageView im_membershipIcon6 = view.findViewById(R.id.im_membershipIcon6);
        ImageView im_membershipIcon7 = view.findViewById(R.id.im_membershipIcon7);
        ImageView image_gou=view.findViewById(R.id.image_agree);
        tv_old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//添加删除线
        if(item.getOldPrice()==0){
            tv_old_price.setVisibility(View.GONE);
        }else{
            tv_old_price.setText(item.getOldPrice());
        }
        tv_new_price.setText(item.getNewPrice());
        tv_member_description.setText(item.getmTextResource());
        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + item.getHeadIm(), headIv);
        tv_name.setText(item.getName());
        tv_open_membership.setText(item.getButtonText());
        if(item.getType().equals("6")||item.getType().equals("7")){
            contentTextView.setText(item.getmTitleEquity1());
            linear_bottom_area.setVisibility(View.VISIBLE);
            tv_agree.setVisibility(View.GONE);
            text_agree_hehuo.setVisibility(View.VISIBLE);
            if("6".equals(item.getType())){
//                text_bottom.setText(context.getResources().getText(R.string.zhongchou1));
                tv_open_membership.setText("成为合伙人");
            }else if("7".equals(item.getType())){
//                text_bottom.setText(context.getResources().getText(R.string.zhongchou2));
                tv_open_membership.setText("成为股东");
            }
        } else {
            if(StringUtil.isNotEmpty(item.getmTitleEquity1())){
                contentTextView.setText(item.getmTitleEquity1());
            }else{
                linear_one.setVisibility(View.GONE);
            }
//            contentTextView.setText(context.getString(R.string.senior_member1,item.getmTitleEquity1()));
            linear_bottom_area.setVisibility(View.GONE);
            tv_agree.setVisibility(View.VISIBLE);
            text_agree_hehuo.setVisibility(View.GONE);
        }



        if(item.getmTitleEquity2()>0){
            titleTextView.setText(item.getmTitleEquity2());
        }
        if(item.getmTitleEquity3()>0){
            tv_equity3.setText(item.getmTitleEquity3());
        }else{
            linear_three.setVisibility(View.GONE);
        }
        if(item.getmTitleEquity5()>0){
            tv_equity4.setText(item.getmTitleEquity5());
        }else{
            linear_four.setVisibility(View.GONE);
        }
        ll_member_icon.setBackgroundResource(item.getMembershipType());
        tv_vip.setText(item.getMyMemberText());
        TextPaint tpaint = tv_vip.getPaint();
        tpaint.setFakeBoldText(true);

        tv_membership_type.setText(item.getMyMemberText2());
        if("3".equals(item.getType())){
            tv_membership_type.setTextColor(Color.parseColor("#999999"));
            text_jiyu_small.setTextColor(context.getResources().getColor(R.color.color_66));
            text_jiyu.setTextColor(context.getResources().getColor(R.color.color_66));
        }else{
            text_jiyu_small.setTextColor(context.getResources().getColor(R.color.white));
            text_jiyu.setTextColor(context.getResources().getColor(R.color.white));
        }
        if (item.getmTitleEquity6() != 0) {
            tv_equity5.setText(item.getmTitleEquity6());
        }else {
            ll_tv_equity5.setVisibility(View.GONE);
        }
        if (item.getmTitleEquity7() != 0) {
            tv_equity6.setText(item.getmTitleEquity7());
        }else {
            ll_equity6.setVisibility(View.GONE);
        }
        if (item.getmTitleEquity8() != 0) {
            tv_equity7.setText(item.getmTitleEquity8());
        }else {
            ll_equity7.setVisibility(View.GONE);
        }
        if(item.getMembershipIcon1()==0){
            im_membershipIcon1.setVisibility(View.GONE);
        }else{
            im_membershipIcon1.setImageResource(item.getMembershipIcon1());
        }
        if(item.getMembershipIcon2()==0){
            im_membershipIcon2.setVisibility(View.GONE);
        }else {
            im_membershipIcon2.setImageResource(item.getMembershipIcon2());
        }
        if(item.getMembershipIcon3()==0){
            im_membershipIcon3.setVisibility(View.GONE);
        }else{
            im_membershipIcon3.setImageResource(item.getMembershipIcon3());
        }
        if(item.getMembershipIcon4()==0){
            im_membershipIcon4.setVisibility(View.GONE);
        }else {
            im_membershipIcon4.setImageResource(item.getMembershipIcon4());
        }
        if(item.getMembershipIcon5() != 0){
            im_membershipIcon5.setImageResource(item.getMembershipIcon5());
        }else if(item.getMembershipIcon6() != 0){
            im_membershipIcon6.setImageResource(item.getMembershipIcon6());
        }else if(item.getMembershipIcon7() != 0){
            im_membershipIcon7.setImageResource(item.getMembershipIcon7());
        }

        if(item.isAgree())
        {
            image_gou.setImageResource(R.drawable.icon_gou);
        }
        else
        {
            image_gou.setImageDrawable(null);
        }

        tv_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartActivityManager.startMemberAgreementActivity(context);
            }
        });
        text_agree_hehuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivityManager.startHehuoAgreementActivity(context);
            }
        });
        image_gou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setAgree(!item.isAgree());
                if(item.isAgree())
                {
                    image_gou.setImageResource(R.drawable.icon_gou);
                }
                else
                {
                    image_gou.setImageDrawable(null);
                }
            }
        });

        ll_open_membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.isAgree())
                {
                   /* if(("6".equals(item.getType())||"7".equals(item.getType()))&&(vipLevel!=1&&vipLevel!=2&&vipLevel!=3&&vipLevel!=4&&vipLevel!=5)){
                        T.showShort("请先开通会员！");
                    }else{*/
                        onButtonClickListener.onButtonClick(position);
                   // }
                }
                else
                {
                    if("6".equals(item.getType())||"7".equals(item.getType())){
                        T.showShort("请同意合伙人协议！");
                    }else {
                        T.showShort("请同意会员协议！");
                    }
                }

            }
        });
//        ll_viewing_interests.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                num++;
//                if (num % 2 == 0) {
//                    tv_time_frame.setVisibility(View.VISIBLE);
//                    ll_equity7.setVisibility(View.GONE);
//                } else {
//                    tv_time_frame.setVisibility(View.GONE);
//                    if (item.getmTitleEquity8() != 0) {
//                        ll_equity7.setVisibility(View.VISIBLE);
//                    }else {
//                        ll_equity7.setVisibility(View.GONE);
//                    }
//                }
//
//            }
//        });
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface OnButtonClickListener {
        void onButtonClick(int position);
    }

}
