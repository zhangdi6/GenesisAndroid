package com.iruiyou.pet.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.CheckBean;

import java.util.List;

/**
 * 选择语言
 * Created by sgf on 2018/7/24.
 */

public class SelectLanguageAdapter extends BaseAdapter {
    private List<CheckBean> mDatas;
    private Activity activity;//上下文
    private LayoutInflater inflater;
    //控制CheckBox选中情况



    public SelectLanguageAdapter(Activity context, List<CheckBean> mDatas) {
        this.activity=context;
        this.mDatas = mDatas;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    @Override
    public String getItem(int position) {
        return mDatas.get(position).getStr();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_select_language, null);
            viewHolder = new ViewHolder();
            viewHolder.tvSetLanguageName =  convertView.findViewById(R.id.tvSetLanguageName);
            viewHolder.imSetLanguageType =  convertView.findViewById(R.id.imSetLanguageType);
//            viewHolder.rl_screen_pupopwindow =  convertView.findViewById(R.id.rl_screen_pupopwindow);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        viewHolder.tv_select_language_line.setVisibility(View.VISIBLE);
        viewHolder.tvSetLanguageName.setText(mDatas.get(position).getStr());
        if(mDatas.get(position).isChecked()){//状态选中
            viewHolder.imSetLanguageType.setVisibility(View.VISIBLE);
//            viewHolder.tvSetLanguageName.setTextColor(activity.getResources().getColor(
//                    R.color.blue_one));//设置字体颜色
//            viewHolder.rl_screen_pupopwindow.setBackgroundColor(ContextCompat.getColor(activity, R.color.fengexian_one));
        }else{
            viewHolder.imSetLanguageType.setVisibility(View.INVISIBLE);
//            viewHolder.tvSetLanguageName.setTextColor(activity.getResources().getColor(
//                    R.color.black_one));//设置字体颜色
//            viewHolder.rl_screen_pupopwindow.setBackgroundColor(ContextCompat.getColor(activity, R.color.colorWhite));
        }

        // 根据isSelected来设置checkbox的选中状况
        viewHolder.tvSetLanguageName.setId(position);//对checkbox的id进行重新设置为当前的position

        return convertView;
    }

    public static class ViewHolder {
        public TextView tvSetLanguageName;
        public ImageView imSetLanguageType;
        public RelativeLayout rl_screen_pupopwindow;
//        public TextView tv_select_language_line;
    }
}
