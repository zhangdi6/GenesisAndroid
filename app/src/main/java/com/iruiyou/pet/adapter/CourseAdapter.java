package com.iruiyou.pet.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.ScreenUtils;

/**
 * 课程内容图片集合 适配器
 * 作者：sgf on 2018/10/16 19:00
 */
public class CourseAdapter extends BaseQuickAdapter<GetCourseIntroBean.DataBean.ImagesBean, BaseViewHolder> {
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;
    private OnTextViewClickListener onTextViewClickListener;

    public CourseAdapter() {
        super(R.layout.adapter_course);
    }

//    public void setNewDatas(List<FollowBean.DataBean> list) {
////        setNewData(list);
//        this.list = list;
//        this.isAcconntList = isAcconntList;
//    }

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
    protected void convert(BaseViewHolder helper, GetCourseIntroBean.DataBean.ImagesBean item) {
        ImageView headIv_course = helper.getView(R.id.headIv_course);
        String headImg = item.getPath();
        if (helper.getAdapterPosition() != 0) {

            int screenHeight = 0;
            //屏幕宽度去除布局中设置的边距，等于图片所展示的实际宽度--------GlideUtils.dip2px(mContext, 22) 输入的参数与布局中设置的参数同步
            int screenWidth = ScreenUtils.getScreenWidth(mContext) - GlideUtils.dip2px(mContext, 22) * 2;//-GlideUtils.dip2px(mContext,22)*2
//            T.showShort("屏幕宽度: " + screenWidth + "\n屏幕高度： " + screenHeight);
            int width = item.getSize().getWidth();
            int height = item.getSize().getHeight();
            double scale = 0;
            screenHeight = screenWidth * height / width;
//            L.d("sgf", "999---" + "图片宽度: " + width + "\n图片高度： " + height + "------h=" + screenHeight + "  scale=" + scale);
            ViewGroup.LayoutParams para = headIv_course.getLayoutParams();
            para.height = screenHeight;
            para.width = screenWidth;

            GlideUtils.displays(mContext, BaseApi.baseUrlNoApi + item.getPath(), headIv_course, screenWidth, screenHeight);//LinearLayout.LayoutParams.WRAP_CONTENT
//            GlideUtils.displays(mContext,BaseApi.baseUrlNoApi + item.getPath(), headIv_course);

//        GlideUtils.display(BaseApi.baseUrlNoApi + item.getPath(), headIv_course);
//        Glide.with(mContext).load(item.getPath()).into(headIv_course);
//            Glide.with(mContext).load(BaseApi.baseUrlNoApi + item.getPath()).override(width, height).into(headIv_course);


//            RequestOptions options = new RequestOptions();
////        options.format(DecodeFormat.PREFER_ARGB_8888);
//            options.fitCenter();
////        options.placeholder(R.drawable.white_bg);//需要添加默认时的图片，不然首次加载不出来图片
//            options.placeholder(com.iruiyou.common.R.drawable.white_bg);
//            Glide.with(mContext).asBitmap().load(BaseApi.baseUrlNoApi + item.getPath()).apply(options).into(new SimpleTarget<Bitmap>() {
//                @Override
//                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                    int imageWidth = resource.getWidth();
//                    int imageHeight = resource.getHeight();
//                    int width = ScreenUtils.getScreenWidth(mContext);//固定宽度
//                    //宽度固定,然后根据原始宽高比得到此固定宽度需要的高度
//                    int height = width * imageHeight / imageWidth;
//                    ViewGroup.LayoutParams para = headIv_course.getLayoutParams();
//                    para.height = height;
//                    para.width = width;
//                    headIv_course.setImageBitmap(resource);
//                }
//            });

        }

        if (onItemClickListener != null) {
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onItemClickListener.onClick(helper.getAdapterPosition());

                }
            });
        }

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

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public interface OnTextViewClickListener {
        void onTextViewClick(int position);
    }
}
