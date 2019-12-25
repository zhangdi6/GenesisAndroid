package com.iruiyou.pet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CrystalGoodsBean;
import com.iruiyou.pet.utils.Constant;

import java.util.List;

/**
 * 水晶充值价格单选
 * sgf
 */
public class SingleGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<CrystalGoodsBean.DataBean> mList;
    private int selectorPosition;
    private int num = 0;

    public SingleGridViewAdapter(Context context, List<CrystalGoodsBean.DataBean> mList) {
        this.mContext = context;
        this.mList = mList;

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
        convertView = View.inflate(mContext, R.layout.item_gridview, null);
        RelativeLayout mRelativeLayout = convertView.findViewById(R.id.ll);
        TextView tv_crystal_price = convertView.findViewById(R.id.tv_crystal_price);
        TextView tv_rmb_price = convertView.findViewById(R.id.tv_rmb_price);
        tv_crystal_price.setText(mList.get(position).getCrystal() + mContext.getString(R.string.crystal));
        tv_rmb_price.setText(Constant.CURRENY2 + mList.get(position).getYuan());
        //如果当前的position等于传过来点击的position,就去改变他的状态
        if (selectorPosition == position) {
            mRelativeLayout.setBackgroundResource(R.drawable.crystal_blue_shape);
            tv_crystal_price.setTextColor(Color.parseColor("#72c6ae"));
            tv_rmb_price.setTextColor(Color.parseColor("#72c6ae"));
        } else {
            //其他的恢复原来的状态
            mRelativeLayout.setBackgroundResource(R.drawable.power_gray_shape);
            tv_crystal_price.setTextColor(Color.parseColor("#666666"));
            tv_rmb_price.setTextColor(Color.parseColor("#666666"));
        }
        //添加GridView从右到左平移动画
        if (num == 0) {
            convertView.setVisibility(View.INVISIBLE);
            int count = 3 - position % 3;
            final TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.RELATIVE_TO_SELF,
                    count,
                    Animation.RELATIVE_TO_SELF,
                    0,
                    Animation.RELATIVE_TO_SELF,
                    0,
                    Animation.RELATIVE_TO_SELF,
                    0);
            translateAnimation.setDuration(count * 100);
//   final Animation animation = AnimationUtils.loadAnimation(parent.getContext(), R.anim.slide_in_right);
            final View finalConvertView = convertView;
            convertView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finalConvertView.startAnimation(translateAnimation);
                }
            }, position * 200);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    finalConvertView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
//                if (position == mList.size() - 1) {
//                    translateAnimation.cancel();
//                }
                    num =1;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            translateAnimation.cancel();
        }
        return convertView;
    }


    public void changeState(int pos) {
        selectorPosition = pos;
        notifyDataSetChanged();

    }
}
