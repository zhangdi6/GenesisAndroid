package com.iruiyou.pet.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.SaveImageActivity;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MultiImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 * 发现-热门 列表
 */
public class FindHotAdapter extends RecyclerView.Adapter<FindHotAdapter.MyViewHolder> {

    private Context context;
    private View view;
    private List<GetEssaysBean.DataBean> data;
    private OnTextViewClickListener onTextViewClickListener;

    public FindHotAdapter(Context context, List<GetEssaysBean.DataBean> data) {
        this.context = context;
        this.data = data;

    }
    public void setOnTextViewClickListener(OnTextViewClickListener listener){
        onTextViewClickListener=listener;
    }

    @Override
    public FindHotAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = Objects.requireNonNull(inflater).inflate(R.layout.item_recycler_find_follow, parent, false);
        return new FindHotAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FindHotAdapter.MyViewHolder holder, int position) {
        GetEssaysBean.DataBean.BasicInfoBean basicInfo = data.get(position).getBasicInfo();
        GetEssaysBean.DataBean dataBean = data.get(position);
        List<GetEssaysBean.DataBean.ImagesBean> images = data.get(position).getImages();
        if(basicInfo!=null){
            String company = basicInfo.getCompany();
            String position_user = basicInfo.getPosition();
            holder.tv_find_name.setText("" + basicInfo.getRealName());
            if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
                holder.tv_find_describe.setText(position_user);
            } else if (TextUtils.isEmpty(position_user) && !TextUtils.isEmpty(company)) {
                holder.tv_find_describe.setText(company);
            } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
                holder.tv_find_describe.setText(company + "\t\t" + position_user);
            }

            if(dataBean!=null){
                holder.tv_find_content.setText(dataBean.getContent());
            }
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), holder.im_find_head);
        }
        if(images!=null){
            if(images.size()==0){
                holder.gridView_find_pic.setVisibility(View.GONE);
            }else{
                holder.gridView_find_pic.setList(images);
            }
        }

    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_find_name;
        ImageView im_find_head;
        MultiImageView gridView_find_pic;
        ImageView im_pic;//一张图时
        TextView tv_find_content;//发布内容
        TextView tv_find_describe;//个人描述信息
        TextView tv_find_pbs;//pbs数量
        TextView tv_find_message;//消息数量


        public MyViewHolder(View itemView) {
            super(itemView);
            im_find_head = itemView.findViewById(R.id.im_find_head);
//            im_pic = (ImageView) itemView.findViewById(R.id.im_pic);
            gridView_find_pic = itemView.findViewById(R.id.gridView_find_pic);
            tv_find_name = itemView.findViewById(R.id.tv_find_name);
            tv_find_content = itemView.findViewById(R.id.tv_find_content);
            tv_find_describe = itemView.findViewById(R.id.tv_find_describe);
            tv_find_pbs = itemView.findViewById(R.id.tv_find_pbs);
            tv_find_message = itemView.findViewById(R.id.tv_find_message);

//            im_pic.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View view) {
//                    saveCroppedImage(((BitmapDrawable) im_pic.getDrawable()).getBitmap());
//                    return false;
//                }
//            });
//            im_pic.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onTextViewClickListener.onTextViewClick(getPosition());
//                }
//            });

            gridView_find_pic.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                @Override
                public void onItemClick(View view, String position ,int po) {
                    T.showShort(""+getAdapterPosition());
                    Intent intent = new Intent(context, SaveImageActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) data.get(getAdapterPosition()).getImages());
//                intent.putStringArrayListExtra("imglist", getEssaysBean);
                    bundle.putInt("pic", 0);//暂时传零，点击都从一显示，此po是最后的数量，不是点击对应的条目，还需研究
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(position);
                }
            });
        }
    }

    public void setItemClickListener(MyItemClickListener myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    private MyItemClickListener mItemClickListener;

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
//        void onItemClick(OccupationBeen firstPageListBean, int position);
        void onItemClick(int position);
    }
    public interface OnTextViewClickListener{
        void onTextViewClick(int position);
    }
    //保存图片
    private void saveCroppedImage(Bitmap bmp) {
        File file = new File("/sdcard/myFolder");
        if (!file.exists())
            file.mkdir();

        file = new File("/sdcard/temp.jpg".trim());
        String fileName = file.getName();
        String mName = fileName.substring(0, fileName.lastIndexOf("."));
        String sName = fileName.substring(fileName.lastIndexOf("."));

        // /sdcard/myFolder/temp_cropped.jpg
        String newFilePath = "/sdcard/myFolder" + "/" + mName + "_cropped" + sName;
        file = new File(newFilePath);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
