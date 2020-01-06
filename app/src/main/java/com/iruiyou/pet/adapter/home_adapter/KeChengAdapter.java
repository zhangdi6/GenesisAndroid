package com.iruiyou.pet.adapter.home_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.BaseBean2;
import com.iruiyou.pet.bean.CourseLessonBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.List;

public class KeChengAdapter extends RecyclerView.Adapter {


    private List<GetCourseIntroBean.DataBean> list;
    private OnItemClickListener onItemClickListener;


    private CourseLessonBean courseLessonBean;
    private Context mContext;


    public KeChengAdapter(List<GetCourseIntroBean.DataBean> mlist) {
        this.list = mlist;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View inflate =   LayoutInflater.from(parent.getContext()).inflate(R.layout.home_kecheng,parent,false);
        return new KeChengViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GetCourseIntroBean.DataBean itemsBean =  list.get(position);
        Log.i("onBindViewHolder", "onBindViewHolder: ");
        KeChengViewHolder holder1 = (KeChengViewHolder) holder;
        holder1.home_kecheng_title.setText(itemsBean.getTitle());
        holder1.home_kecheng_name.setText(itemsBean.getDesc());
        holder1.home_kechen_price.setText("￥"+itemsBean.getPrice()+"");
        Glide.with(holder1.home_kechen_price.getContext()).load( BaseApi.baseUrlNoApi + itemsBean.getMinImage()).into(holder1.home_kecheng_img);

        if (list.size()> 1 && position==0){
            getLessons(list.get(0).get_id(),holder1.ke_one,holder1.ke_two);
        }else if (list.size()>1 && position ==1){
            getLessons(list.get(1).get_id(),holder1.ke_one,holder1.ke_two);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener!=null){
                    onItemClickListener.getclicklistener(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size()>2){
            return 2;
        }else{
            return list.size()>0?list.size():0;

        }
    }

    private void getLessons(int i, TextView ke_one, TextView ke_two) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                /*Log.i("lessons", "onNext: "+resulte);*/
                BaseBean2 bean = GsonUtils.parseJson(resulte, BaseBean2.class);

                if (bean.getStatusCode() == 0) {
                    if (bean.getData()!=null && bean.getData().size()>0){
                        ke_one.setText(" · "+bean.getData().get(0).getTitle());
                        if (bean.getData()!=null && bean.getData().size()>1) {
                            ke_two.setText(" · "+bean.getData().get(1).getTitle());
                        }
                    }
                }else{

                }
            }

            @Override
            public void onError(ApiException e) {
                /*T.showShort(e.getMessage());*/
            }
        }, (RxAppCompatActivity) mContext).getLessons(i);


    }


    public class KeChengViewHolder extends RecyclerView.ViewHolder {


        private ImageView home_kecheng_img;

        private TextView home_kecheng_title;
        private TextView home_kecheng_name;
        private TextView home_kechen_price;
        private TextView ke_one;
        private TextView ke_two;


        public KeChengViewHolder(View itemView) {
            super(itemView);
            home_kecheng_img =  itemView.findViewById(R.id.home_kecheng_img);
            home_kecheng_title =  itemView.findViewById(R.id.home_kecheng_title);
            home_kecheng_name =  itemView.findViewById(R.id.home_kecheng_name);
            home_kechen_price =  itemView.findViewById(R.id.home_kechen_price);
            ke_one =  itemView.findViewById(R.id.ke_one);
            ke_two =  itemView.findViewById(R.id.ke_two);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    public interface OnItemClickListener{
        void getclicklistener(int position);
    }




}
