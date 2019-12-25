package com.iruiyou.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.iruiyou.common.utils.RoundCorner;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.iruiyou.common.R;
import com.iruiyou.common.RyCommon;
import com.iruiyou.http.retrofit_rx.utils.L;

import java.io.File;

/**
 * 类描述:图片加载
 * 作者：Created by JiaoPeiRong on 2017/4/23 14:14
 * 邮箱：chinajpr@163.com
 */

public class GlideUtils {
    /**
     * 标准图片
     *
     * @param url
     * @param imageView
     */
    public static void display(String url, ImageView imageView) {
//        Glide.with(RyCommon.getInstance().getConfiguration().getContext()).load(url).into(imageView);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.head_home2);
        //切圆
        options.autoClone();
        Glide.with(RyCommon.getInstance().getConfiguration().getContext()).load(url).apply(options).into(imageView);
    }



    /**
     * 标准图片
     *
     * @param url
     * @param imageView
     */
    public static void displays(final Context context, final String url, final ImageView imageView, int width, int height) {//,int width, int height
        RequestOptions options = new RequestOptions();
//        options.format(DecodeFormat.PREFER_ARGB_8888);

        options.skipMemoryCache(true);
        options.fitCenter();//让图片全填充
//        options.centerCrop();//居中显示
        options.priority(Priority.HIGH);
//        options.placeholder(R.drawable.white_bg);//需要添加默认时的图片，不然首次加载不出来图片
        options.placeholder(R.drawable.white_bg);//添加方法fitcenter,可能会有压缩或拉伸的情况 .fitCenter()
        L.d("sgf111", "width="+width+"  height="+height+"    w"+px2dp(context,width)+"    h"+px2dp(context,height));
            if(height>8000){
                height=8000;
            }
        options.override(  width,  height);//设置图片像素
//        Glide.with(RyCommon.getInstance().getConfiguration().getContext()).load(url).into(imageView);
        Glide.with(context).load(url).apply(options).into(imageView);
//        Glide.with(context).asBitmap().load(url).apply(options).into(imageView);
//        Glide.with(context).asBitmap().load(url).apply(options).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                int imageHeight = resource.getHeight();
//                if (imageHeight > 4096) {
//                    imageHeight = 4096;
//                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//                    layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
//
//                    layoutParams.height = imageHeight;
//                    imageView.setLayoutParams(layoutParams);
//                    RequestOptions options = new RequestOptions();
//                    options.placeholder(R.drawable.white_bg)
//                            .centerCrop()
//                            .dontAnimate();
//                    Glide.with(context)
//                            .load(url)
//                            .apply(options)
//                            .into(imageView);
//                }else {
//
//                }
//            }
//        });
//        Glide.with(context)
//                .load(url)
//                .apply(options)
//                .listener(new RequestListener<Drawable>() {
//                    @Override
//                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        // 根据图片的实际长宽判断，如果宽大于长则拉伸，如果宽小于等于长则居中显示
//                        int width = resource.getIntrinsicWidth();
//                        int height = resource.getIntrinsicHeight();
////                        LogUtils.i(TAG , "width:" + width + ",height" + height);
//                        if(width > height){
//                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                        }else if(width <= height){
//                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
//                        }
//                        return false;
//                    }
//                })
//                .into(imageView);
    }
    /**
     * px转换成dp
     */
    public static int px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    /**
     * dp转px
     * @param dp
     * @return
     */
    public static int dip2px(Context context,int dp)
    {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp*density+0.5);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayRound(Context context, String url, ImageView imageView) {

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.head_home);
        //切圆
        options.circleCrop();
        Glide.with(context).load(url).apply(options).into(imageView);

//        Glide.with(context).load(url).error(R.drawable.head_home).bitmapTransform(new CropCircleTransformation(context))
//                .into(imageView);
    }

    public static void displayRoundedCorner(Context context, String url, ImageView imageView,int corners){
        Glide.with(context).load(url).
                apply(RequestOptions.bitmapTransform(new RoundCorner(context,corners,corners,corners,corners))).into(imageView);

    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void displayRound(Context context, int url, ImageView imageView) {

        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.head_home);
        //切圆
        options.circleCrop();
        Glide.with(context).load(url).apply(options).into(imageView);

//        Glide.with(context).load(url).error(R.drawable.head_home).bitmapTransform(new CropCircleTransformation(context))
//                .into(imageView);
    }

    /**
     * 加载模糊图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    public static void displayBlur(Context context, String url, ImageView imageView, int radius) {
//        Glide.with(context).load(url).bitmapTransform(new BlurTransformation(context, radius))
//                .into(imageView);
    }

    /**
     * 加载文件图片
     *
     * @param context
     * @param path
     * @param iv
     */
    public static void displayFile(Context context, String path, ImageView iv) {
        if (path == null) {
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(R.drawable.head_home);
        //切圆
        options.circleCrop();
        Glide.with(context).load(new File((path))).apply(options).into(iv);
        //以上是glide 4的代码，一下是2.0.2的代码，由于依赖包的冲突，所以修改了之前的写法
        //compile 'jp.wasabeef:glide-transformations:2.0.2' 与 implementation ('com.github.LuckSiege.PictureSelector:picture_library:v2.1.1')的依赖冲突，后者包含了glide 4
//        Glide.with(context).load(new File(path)).bitmapTransform(new CropCircleTransformation(context)).into(iv);
    }

//    public static void displayGif(Context context , String path , ImageView iv){
//        Glide.with(context).load(R.drawable.mgif).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
//        if (path == null){
//            return;
//        }
//        Glide.with(context).load(new File(path)).bitmapTransform( new CropCircleTransformation(context)).into(iv);
//    }

    public static void displayBitmap(Context context, String path, final PhotoView photoView) {
//        Glide.with(context).load(path).asBitmap().into(new SimpleTarget<Bitmap>(){
//
//            @Override
//            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                photoView.setImageBitmap(resource);
//            }
//        });
    }


}
