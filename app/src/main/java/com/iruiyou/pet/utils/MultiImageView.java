package com.iruiyou.pet.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.bean.GetEssaysBean;

import java.util.List;

/**
 * @author sgf
 * @ClassName MultiImageView.java
 * @Description: 显示1~N张图片的View
 */

public class MultiImageView extends LinearLayout {
    public static int MAX_WIDTH = 0;

    // 照片的Url列表
    private List<GetEssaysBean.DataBean.ImagesBean> imagesList;

    /**
     * 长度 单位为Pixel
     **/
    private int pxOneWidth = DensityUtil.dip2px(getContext(), 200); // 单张图时候的宽
    private int pxOneHeight = DensityUtil.dip2px(getContext(), 150); // 单张图时候的高
    private int pxMoreWandH = 0; // 多张图的宽高
    private int pxImagePadding = DensityUtil.dip2px(getContext(), 5);// 图片间的间距

    private int MAX_PER_ROW_COUNT = 3;// 每行显示最大数

    private LayoutParams onePicPara;
    private LayoutParams morePara;
    private LayoutParams rowPara;

    private OnItemClickListener mOnItemClickListener;
    private int po;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public MultiImageView(Context context) {
        super(context);

    }

    public MultiImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setList(List<GetEssaysBean.DataBean.ImagesBean> lists) throws IllegalArgumentException {
        if (lists == null) {
            setVisibility(GONE);
        }
        setVisibility(VISIBLE);
        imagesList = lists;

        if (MAX_WIDTH > 0) {
            pxMoreWandH = MAX_WIDTH / 3 - pxImagePadding;
            // pxOneWidth = MAX_WIDTH / 2;
            // pxOneHeight = MAX_WIDTH * 2 / 3;
            initImageLayoutParams();
        }
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MAX_WIDTH == 0) {
            int width = measureWidth(widthMeasureSpec);
            if (width > 0) {
                MAX_WIDTH = width;
                if (imagesList != null && imagesList.size() > 0) {
                    setList(imagesList);
                }
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * @param measureSpec
     * @return 返回View的宽度
     */
    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private void initImageLayoutParams() {

        onePicPara = new LayoutParams(pxOneWidth, pxOneHeight);

        morePara = new LayoutParams(pxMoreWandH, pxMoreWandH);
        morePara.setMargins(0, 0, pxImagePadding, 0);

        int wrap = LayoutParams.WRAP_CONTENT;
        int match = LayoutParams.MATCH_PARENT;
        rowPara = new LayoutParams(match, wrap);
        rowPara.setMargins(0, 0, 0, pxImagePadding);
    }

    // 根据imageView的数量初始化不同的View布局,还要为每一个View作点击效果
    private void initView() {
        this.setOrientation(VERTICAL);
        this.removeAllViews();
        if (MAX_WIDTH == 0) {
            // 为了触发onMeasure()来测量MultiImageView的最大宽度，MultiImageView的宽设置为match_parent
            addView(new View(getContext()));
            return;
        }

        if (imagesList == null || imagesList.size() == 0) {
            return;
        }

        if (imagesList.size() == 1) {
            for (final GetEssaysBean.DataBean.ImagesBean imagesBean : imagesList) {
                final ImageView imageView = new ImageView(getContext());
                imageView.setId(imagesBean.getPath().hashCode()); // 指定id
                imageView.setLayoutParams(onePicPara);
                imageView.setMinimumWidth(pxMoreWandH);
                imageView.setScaleType(ScaleType.CENTER_CROP);

                Glide.with(getContext()).load(BaseApi.baseUrlNoApi +imagesBean.getPath()).into(imageView);

                imageView.setOnClickListener(mImageViewOnClickListener);
                addView(imageView);
            }

        } else {
            int allCount = imagesList.size();
            if (allCount == 4) {
                MAX_PER_ROW_COUNT = 2;
            } else {
                MAX_PER_ROW_COUNT = 3;
            }
            int rowCount = allCount / MAX_PER_ROW_COUNT + (allCount % MAX_PER_ROW_COUNT > 0 ? 1 : 0);// 行数
            for (int rowCursor = 0; rowCursor < rowCount; rowCursor++) {
                LinearLayout rowLayout = new LinearLayout(getContext());
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);

                rowLayout.setLayoutParams(rowPara);
                if (rowCursor == 0) {
                    rowLayout.setPadding(0, pxImagePadding, 0, 0);
                }

                int columnCount = allCount % MAX_PER_ROW_COUNT == 0 ? MAX_PER_ROW_COUNT : allCount % MAX_PER_ROW_COUNT;// 每行的列数
                if (rowCursor != rowCount - 1) {
                    columnCount = MAX_PER_ROW_COUNT;
                }
                addView(rowLayout);

                int rowOffset = rowCursor * MAX_PER_ROW_COUNT;// 行偏移
                for (int columnCursor = 0; columnCursor < columnCount; columnCursor++) {
                    int position = columnCursor + rowOffset;
                    po = position;
                    String thumbUrl = imagesList.get(position).getPath();

                    final ImageView imageView = new ImageView(getContext());
                    imageView.setId(thumbUrl.hashCode());// 指定id
                    imageView.setLayoutParams(morePara);
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    imageView.setTag(R.string.app_name, position);

                    Glide.with(getContext()).load(BaseApi.baseUrlNoApi +thumbUrl).into(imageView);

                    imageView.setOnClickListener(mImageViewOnClickListener);
                    rowLayout.addView(imageView);
                }
            }
        }
    }

    // 图片点击事件
    private OnClickListener mImageViewOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, view.getTag() + "",po);
            }
        }
    };

    public interface OnItemClickListener {
        void onItemClick(View view, String position ,int po);
    }

}