package com.iruiyou.pet.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.SearchBean;
import com.iruiyou.pet.fragment.Fragment2;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.FilterSearchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 搜索适配器
 * shenggaofei
 */
public class SearchContactsAdapter extends BaseAdapter implements Filterable {

    private List<SearchBean> searchList = new ArrayList<>();
    private Context context;
    private Map<String, Boolean> map;
    private MyFilter filter = null;// 创建MyFilter对象
    private FilterSearchListener listener = null;// 接口对象
    private OnTextViewClickListener onTextViewClickListener;
    private MyClickListener mListener;
    private int po;//搜索时选中的职业类型的索引
    private int readProfessionalIdentity;

    public void setOnTextViewClickListener(OnTextViewClickListener listener){
        onTextViewClickListener=listener;
    }

    public SearchContactsAdapter(List<SearchBean> searchList, Map<String, Boolean> map, Context context, MyClickListener mListener, FilterSearchListener filterListener) {
        this.searchList = searchList;
        this.map = map;
        this.context = context;
        this.mListener = mListener;
        this.listener = filterListener;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub

        return searchList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_search, null);
            holder = new ViewHolder();
            holder.invitation0 = convertView.findViewById(R.id.invitation0);
            holder.invitation1 = convertView.findViewById(R.id.invitation1);
            holder.name = convertView.findViewById(R.id.name);
            holder.details = convertView.findViewById(R.id.details);
            holder.headIv = convertView.findViewById(R.id.headIv);

            convertView.setTag(holder);
        }

        holder = (ViewHolder) convertView.getTag();
        SearchBean searchBean = searchList.get(position);
        int type = searchBean.getType();
        String name = searchList.get(position).getName();
        String realName = searchList.get(position).getRealName();
        String phone = searchList.get(position).getPhone();
        String userPosition = searchList.get(position).getPosition();
        String company = searchList.get(position).getCompany();
        if(type == 0){
            holder.invitation0.setVisibility(View.GONE);
            holder.invitation1.setVisibility(View.GONE);
            if(TextUtils.isEmpty(company)&&!TextUtils.isEmpty(userPosition)){
                holder.details.setText(userPosition);
            }else if(TextUtils.isEmpty(userPosition)&&!TextUtils.isEmpty(company)){
                holder.details.setText(company);
            }else if(!TextUtils.isEmpty(company)&&!TextUtils.isEmpty(userPosition)){
                holder.details.setText(company + Constant.LARGE_SPACE + userPosition);
            }else {
                holder.details.setText(company);
            }
            holder.name.setText(realName);
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + searchList.get(position).getHeadImg(), holder.headIv);
        }else if(type == 1){
            holder.invitation0.setVisibility(View.GONE);
            holder.invitation1.setVisibility(View.GONE);
            if(!TextUtils.isEmpty(phone)&&phone.contains("*")){
                String[] split = phone.split("\\" + Fragment2.SIGN);
                holder.details.setText(split[0]);
                holder.name.setText(realName+"("+split[1]+")");
            }else {
                holder.details.setText(phone);
                holder.name.setText(realName);
            }
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + searchList.get(position).getHeadImg(), holder.headIv);
        }else if(type == 2){
            holder.invitation0.setVisibility(View.VISIBLE);
            holder.invitation1.setVisibility(View.GONE);
            if(!TextUtils.isEmpty(phone)&&phone.contains("*")){
                String[] split = phone.split("\\" + Fragment2.SIGN);
                holder.details.setText(split[0]);
                holder.name.setText(realName+"("+split[1]+")");
            }else {
                holder.details.setText(phone);
                holder.name.setText(realName);
            }
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + searchList.get(position).getHeadImg(), holder.headIv);
        }else if(type == 3){
            holder.invitation0.setVisibility(View.GONE);
            holder.invitation1.setVisibility(View.GONE);
            holder.name.setText(searchList.get(position).getGroupName());
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + searchList.get(position).getGroupLogo(), holder.headIv);
        }

//        if(!TextUtils.isEmpty(name)){
//            holder.name.setText(searchList.get(position).getRealName()+"("+searchList.get(position).getName()+")");
//        }else {
//            holder.name.setText(searchList.get(position).getRealName());
//        }
//        if(!TextUtils.isEmpty(phone)&&phone.contains("*")){
//            String[] split = phone.split("\\" + Fragment2.SIGN);
//            holder.details.setText(split[0]);
//            holder.name.setText(realName+"("+split[1]+")");
//        }

//        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + searchList.get(position).getHeadImg(), holder.headIv);
        holder.invitation0.setOnClickListener(mListener);
        holder.invitation0.setTag(position);//加上这句，不然报空
//        holder.invitation0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onTextViewClickListener.onTextViewClick(position);
//            }
//        });
        return convertView;
    }

    /**
     * 自定义MyAdapter类实现了Filterable接口，重写了该方法
     */
    @Override
    public Filter getFilter() {
        // 如果MyFilter对象为空，那么重写创建一个
        if (filter == null) {
            filter = new MyFilter(searchList);
        }
        return filter;
    }

    /**
     * 创建内部类MyFilter继承Filter类，并重写相关方法，实现数据的过滤
     * @author 邹奇
     *
     */
    class MyFilter extends Filter {

        // 创建集合保存原始数据
        private List<SearchBean> original = new ArrayList<>();

        public MyFilter(List<SearchBean> searchList) {
            this.original = searchList;
        }

        /**
         * 该方法返回搜索过滤后的数据
         */
        @Override
        public FilterResults performFiltering(CharSequence constraint) {
            // 创建FilterResults对象
            FilterResults results = new FilterResults();

            /**
             * 没有搜索内容的话就还是给results赋值原始数据的值和大小
             * 执行了搜索的话，根据搜索的规则过滤即可，最后把过滤后的数据的值和大小赋值给results
             *
             */
            if(TextUtils.isEmpty(constraint)){
                results.values = original;
                results.count = original.size();
            }else {
                //选择条件需同时和输入框的条件放在回调内才可以实时刷新数据
                readProfessionalIdentity = SharePreferenceUtils.getBaseSharePreference().readProfessionalIdentity();
                // 创建集合保存过滤后的数据
                List<SearchBean> mList = new ArrayList<>();
                // 遍历原始数据集合，根据搜索的规则过滤数据
                for(SearchBean s: original){
                    // 这里就是过滤规则的具体实现【规则有很多，大家可以自己决定怎么实现】
                    if(readProfessionalIdentity == 0 || readProfessionalIdentity == -1){
                        if((s.getRealName()!=null&&s.getRealName().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase()))//用户名
                                ||(s.getRealName()!=null&&s.getPhone().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase()))//手机号
                                ||(s.getGroupName()!=null&&s.getGroupName().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())))//组名
                        {//||s.getProfessionalIdentity() == readProfessionalIdentity
                            // 规则匹配的话就往集合中添加该数据
//                        for (int i = 0; i <mList.size() ; i++) {
//                            if(!mList.get(i).getRealName().trim().toLowerCase().contains(s.getRealName())){
//                                mList.add(s);
//                            }
//                        }
                            Log.i("SEARCH", "s.getGroupName()=" + s.getGroupName());
                            mList.add(s);
                        }
                    }else if(readProfessionalIdentity > 0 && readProfessionalIdentity <= 15){
                        if((s.getRealName()!=null&&s.getRealName().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase()))
                                ||s.getRealName()!=null&&s.getPhone().trim().toLowerCase().contains(constraint.toString().trim().toLowerCase())
                                ){//||s.getProfessionalIdentity() == readProfessionalIdentity
                            // 规则匹配的话就往集合中添加该数据
//                        for (int i = 0; i <mList.size() ; i++) {
//                            if(!mList.get(i).getRealName().trim().toLowerCase().contains(s.getRealName())){
//                                mList.add(s);
//                            }
//                        }
                            if(s.getProfessionalIdentity() == readProfessionalIdentity){
                                mList.add(s);
                            }
                        }
                    }

                }
                for (int i = 0; i < mList.size() - 1; i++) {//去重操作
                    for (int j = mList.size() - 1; j > i; j--) {
                        if ((mList.get(j).getRealName()+"").equals(mList.get(i).getRealName())) {
                            if(mList.get(i).getType()!=0||mList.get(i).getType()==2||mList.get(i).getType()==1){
                                mList.remove(j);
//                                for (int k = 0; k <mList.size()-1 ; k++) {
//                                    for (int q = mList.size()-1; q > k; q--) {
//                                        if (mList.get(q).getRealName().equals(mList.get(k).getRealName())) {
//                                            if(mList.get(k).getType()!=0||mList.get(k).getType()==2||mList.get(k).getType()==1) {
//                                                mList.remove(q);
//                                            }
//                                        }
//                                    }
//                                }
                            }
                        }
                    }
                }
                results.values = mList;
                results.count = mList.size();
//                mList.toString();
            }

            // 返回FilterResults对象
            return results;
        }

        /**
         * 该方法用来刷新用户界面，根据过滤后的数据重新展示列表
         */
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // 获取过滤后的数据
            searchList = (List<SearchBean>) results.values;
            // 如果接口对象不为空，那么调用接口中的方法获取过滤后的数据，具体的实现在new这个接口的时候重写的方法里执行
            if(listener != null){
                listener.getFilterData(searchList);
            }
            // 刷新数据源显示
            notifyDataSetChanged();
        }

    }

    /**
     * 控件缓存类
     *
     * @author sgf
     *
     */
    class ViewHolder {
        TextView invitation0;
        TextView invitation1;
        TextView name;
        TextView details;
        ImageView headIv;
    }
    public interface OnTextViewClickListener{
        void onTextViewClick(int position);
    }
    /**
     * 用于回调的抽象类
     * @author sgf
     * 2018-10-26
     */
    public static abstract class MyClickListener implements View.OnClickListener {
        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            myOnClick((Integer) v.getTag(), v);
        }
        public abstract void myOnClick(int position, View v);
    }
}