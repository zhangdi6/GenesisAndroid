package com.iruiyou.pet.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.SaveImageActivity;
import com.iruiyou.pet.activity.utils.DataUtils;
import com.iruiyou.pet.bean.DynamicBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MultiImageView;
import com.iruiyou.pet.utils.StringUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DynamicAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private Context context;
    private String userId;
    private List<DynamicBean.DataBean.EssaysBean> data;
    private OnViewClickListener onViewClickListener;
    private onItemFabuClick onFabuClick;
    private int total_fabulous;

    public DynamicAdapter(Context context, List<DynamicBean.DataBean.EssaysBean> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userId = SharePreferenceUtils.getBaseSharePreference().readUserId();
    }

    public void setOnViewClickListener(DynamicAdapter.OnViewClickListener listener) {
        onViewClickListener = listener;
    }




    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_find_follow, parent, false);
        return new MyViewHolder(inflate);
    }


    public DynamicBean.DataBean.EssaysBean getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mholder, int position) {

        MyViewHolder holder = (MyViewHolder) mholder;

    /*    GetEssaysBean.DataBean.BasicInfoBean basicInfo = data.get(position).getBasicInfo();

        GetEssaysBean.DataBean dataBean = data.get(position);
*/

        DynamicBean.DataBean.EssaysBean.BasicInfoBean basicInfo = data.get(position).getBasicInfo();

        DynamicBean.DataBean.EssaysBean essaysBean = data.get(position);

      //  List<GetEssaysBean.DataBean.ImagesBean> images = data.get(position).getImages();

        if (basicInfo != null) {

            String company = basicInfo.getCompany();
            String position_user = basicInfo.getPosition();
            String createdAt = basicInfo.getCreatedAt();
            String updatedAt = basicInfo.getUpdatedAt();

         /*   String time = updatedAt.substring(createdAt.lastIndexOf("T")+1, createdAt.lastIndexOf("T") + 6);
            String date = updatedAt.substring(5 , createdAt.lastIndexOf("T"));*/

            long time1 = essaysBean.getTime();
            String dateToString = DataUtils.getCurrentDate(time1);

            holder.tv_find_name.setText("" + basicInfo.getRealName());
            holder.tv_find_describe.setText(dateToString);
            holder.tv_work.setText(basicInfo.getPositionTitle());

//                if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
//                    holder.tv_find_describe.setText(position_user);
//                } else if (TextUtils.isEmpty(position_user) && !TextUtils.isEmpty(company)) {
//                    holder.tv_find_describe.setText(company);
//                } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
//                    holder.tv_find_describe.setText(company + "\t\t" + position_user);
//                }


            if (essaysBean != null) {

                holder.tv_find_content.setText(essaysBean.getContent());
                holder.tv_find_content.setMaxLines(5);
                holder.tv_find_content.setEllipsize(TextUtils.TruncateAt.END);
                int lineCount = holder.tv_find_content.getLineCount();
                if (lineCount >= 5) {
                    holder.tv_find_content_below.setVisibility(View.VISIBLE);
                } else {
                    holder.tv_find_content_below.setVisibility(View.GONE);
                }
            }
            GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), holder.im_find_head);
        }

      /*  if (images != null) {
            if (images.size() == 0) {
                holder.gridView_find_pic.setVisibility(View.GONE);
            } else {
                holder.gridView_find_pic.setList(images);
            }
        }
*/
        if (StringUtil.isNotEmpty(userId) && (Integer.valueOf(userId).intValue() == essaysBean.getUserId())) {
            holder.text_delete_essay.setVisibility(View.VISIBLE);
            holder.text_delete_essay.setOnClickListener((view) -> {
                if (onViewClickListener != null) {
                    onViewClickListener.onViewClick(R.id.text_delete_essay, position);
                }
            });
        } else {
            holder.text_delete_essay.setVisibility(View.INVISIBLE);
            holder.text_delete_essay.setOnClickListener(null);
        }



            total_fabulous = essaysBean.getFabulous();
            holder.relayout_reference.setVisibility(View.GONE);
            holder.gridView_find_pic.setVisibility(View.VISIBLE);
            holder.mLL.setVisibility(View.VISIBLE);
            holder.tv_find_pbs.setText(total_fabulous + "");
            holder.tv_find_message.setText(essaysBean.getComment() + "");
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mItemClickListener != null) {

                        mItemClickListener.onItemClick(position);

                    }
                }
            });
            holder.tv_find_pbs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onFabuClick != null) {
                        onFabuClick.onItemFabu(position,holder.tv_find_pbs);
                    }
                }
            });

        }





    @Override
    public int getItemCount() {
        return data.size();
    }

    public void change(int posi) {
         total_fabulous++;

    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_icon)
        ImageView image_icon;

        @BindView(R.id.text_author)
        TextView text_author;

        @BindView(R.id.text_short_content)
        TextView text_short_content;

        @BindView(R.id.tv_find_name)
        TextView tv_find_name;

        @BindView(R.id.im_find_head)
        ImageView im_find_head;

        @BindView(R.id.text_work)
        TextView tv_work;

        @BindView(R.id.gridView_find_pic)
        MultiImageView gridView_find_pic;

        ImageView im_pic;//一张图时

        @BindView(R.id.tv_find_content)
        TextView tv_find_content;//发布内容

        @BindView(R.id.tv_find_content_below)
        TextView tv_find_content_below;//发布内容

        @BindView(R.id.tv_find_describe)
        TextView tv_find_describe;//个人描述信息

        @BindView(R.id.tv_find_pbs)
        TextView tv_find_pbs;//pbs数量
        @BindView(R.id.ll)
        LinearLayout mLL;//pbs数量

        @BindView(R.id.tv_find_message)
        TextView tv_find_message;//消息数量

        @BindView(R.id.text_delete_essay)
        TextView text_delete_essay;//删除短文

      /*  @BindView(R.id.ll_network_expand)
        LinearLayout ll_network_expand;*/

        @BindView(R.id.relayout_reference)
        RelativeLayout relayout_reference;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            gridView_find_pic.setOnItemClickListener((View view, String position, int po) -> {
                Intent intent = new Intent(context, SaveImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) data.get(getAdapterPosition()).getImages());
//                intent.putStringArrayListExtra("imglist", getEssaysBean);
                bundle.putInt("pic", 0);//暂时传零，点击都从一显示，此po是最后的数量，不是点击对应的条目，还需研究
                intent.putExtras(bundle);
                context.startActivity(intent);
            });


           /* itemView.setOnClickListener(view -> {
                int position = getPosition();
                if (mItemClickListener != null)

            });*/

        }
    }

    public void setItemClickListener(FindHotAdapter.MyItemClickListener
                                             myItemClickListener) {
        this.mItemClickListener = myItemClickListener;
    }

    private FindHotAdapter.MyItemClickListener mItemClickListener;

    /**
     * 创建一个回调接口
     */
    public interface MyItemClickListener {
        //        void onItemClick(OccupationBeen firstPageListBean, int position);
        void onItemClick(int position);
    }

    public interface OnViewClickListener {
        void onViewClick(int viewId, int position);
    }

    public interface onItemFabuClick {
        void onItemFabu(int position, TextView view);
    }

    public void onItemFabuClick(onItemFabuClick onItemFabuClick) {
        onFabuClick = onItemFabuClick;
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
