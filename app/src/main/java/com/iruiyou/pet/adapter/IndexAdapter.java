package com.iruiyou.pet.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.pet.R;

/**
 * 我的页面 适配器
 * 作者：jiaopeirong on 2018/5/9 22:18
 * 邮箱：chinajpr@163.com
 */
public class IndexAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
//    HomeRefreshBean homeRefreshBean;
    public IndexAdapter() {
        super(R.layout.adapter_index);
//        this.homeRefreshBean = homeRefreshBean;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int adapterPosition = helper.getAdapterPosition();
        ImageView iv = helper.getView(R.id.iv);
        TextView tv = helper.getView(R.id.name);
        TextView value = helper.getView(R.id.value);
        TextView tag = helper.getView(R.id.tag);
        ImageView next = helper.getView(R.id.item_go_next);
        ImageView imCompleteType = helper.getView(R.id.imCompleteType);
        int basicCount = SharePreferenceUtils.getBaseSharePreference().readBasicCount();
        int educationCount = SharePreferenceUtils.getBaseSharePreference().readEDU();
        int photoCount = SharePreferenceUtils.getBaseSharePreference().readPhotoCount();
        int workCount = SharePreferenceUtils.getBaseSharePreference().readWorkCount();
        switch (adapterPosition) {
//            case 1:
//                iv.setImageResource(R.drawable.inviting_friends);
//                imCompleteType.setImageResource(R.drawable.star);
//                tv.setText(mContext.getResources().getString(R.string.upload_photos));
//                value.setText("+10~30");
////                tag.setText(item + "/3");
//                break;
//
//            case 2:
//                iv.setImageResource(R.drawable.buy_crystal);
//                tv.setText(mContext.getResources().getString(R.string.upload_photos));
//                value.setText(mContext.getString(R.string.one_crystal));
////                tag.setText(item + "/3");
//                break;
            case 1:
                if(photoCount >= 1){
                    imCompleteType.setImageResource(R.drawable.complete);
                    next.setVisibility(View.INVISIBLE);
                }else {
                    imCompleteType.setImageResource(R.drawable.sigh);
                    next.setVisibility(View.VISIBLE);
                }
                iv.setImageResource(R.drawable.upload_career_photo);
                tv.setText(mContext.getResources().getString(R.string.upload_photos));
                value.setText("+20");
//                tag.setText(item + "/3");
                break;
            case 2:
                if(basicCount >= 1){
                    imCompleteType.setImageResource(R.drawable.complete);
                    next.setVisibility(View.INVISIBLE);
                }else {
                    imCompleteType.setImageResource(R.drawable.sigh);
                    next.setVisibility(View.VISIBLE);
                }
                iv.setImageResource(R.drawable.company);
                tv.setText(mContext.getResources().getString(R.string.addBasicInformation));
                value.setText("+20");
//                tag.setText(item + "/3");
                break;
            case 3:
                if(workCount >= 1){
                    imCompleteType.setImageResource(R.drawable.complete);
                    next.setVisibility(View.INVISIBLE);
                }else {
                    imCompleteType.setImageResource(R.drawable.sigh);
                    next.setVisibility(View.VISIBLE);
                }

                iv.setImageResource(R.drawable.work);
                tv.setText(mContext.getResources().getString(R.string.addWorkExperience));
                value.setText("+10~30");
//                tag.setText(item + "/3");
                break;
            case 4:
                if(educationCount >= 1){
                    imCompleteType.setImageResource(R.drawable.complete);
                    next.setVisibility(View.INVISIBLE);
                }else {
                    imCompleteType.setImageResource(R.drawable.sigh);
                    next.setVisibility(View.VISIBLE);
                }
                iv.setImageResource(R.drawable.edu);
                tv.setText(mContext.getResources().getString(R.string.addEducationalExperience));
                value.setText("+10~30");
//                tag.setText(item + "/3");
                break;
        }
    }
}
