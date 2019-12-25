package com.iruiyou.pet.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.ViewPagerSaveAdapter;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.CancelOrOkDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * 展示保存图片
 *  sgf
 */
public class SaveImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager; //展示图片的ViewPager
    private TextView positionTv; //图片的位置，第几张图片
    private ArrayList<GetEssaysBean.DataBean.ImagesBean> imgList; //图片的数据源
    private int mPosition; //第几张图片
    private ViewPagerSaveAdapter mAdapter;
    private static final int SAVE_SUCCESS = 0;//保存图片成功
    private static final int SAVE_FAILURE = 1;//保存图片失败
    private static final int SAVE_BEGIN = 2;//开始保存图片
    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SAVE_BEGIN:
                    T.showShort("开始保存图片...");

                    break;
                case SAVE_SUCCESS:
                    T.showShort("图片保存成功,请到相册查找");
                    break;
                case SAVE_FAILURE:
                    T.showShort("图片保存失败,请稍后再试...");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_image);

//        imgList = getIntent().getStringArrayListExtra("imglist");
        imgList = (ArrayList<GetEssaysBean.DataBean.ImagesBean>) getIntent().getSerializableExtra("imglist");
        mPosition = getIntent().getIntExtra("pic", 0);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        positionTv = findViewById(R.id.position_tv);
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.delete_iv).setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);

        mAdapter = new ViewPagerSaveAdapter(this, imgList);
        viewPager.setAdapter(mAdapter);
        positionTv.setText(mPosition + 1 + "/" + imgList.size());
        viewPager.setCurrentItem(mPosition);
    }

    //删除图片
    private void deletePic() {
        CancelOrOkDialog dialog = new CancelOrOkDialog(this, "您确定要保存这张图片吗?") {
            @Override
            public void ok() {
                super.ok();
//                imgList.remove(mPosition); //从数据源移除删除的图片
                setPosition();
//                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.all0);
//                Bitmap bitmap = stringToBitmap("http://img1.3lian.com/2015/w3/66/81.jpg");
                //保存图片必须在子线程中操作，是耗时操作
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.obtainMessage(SAVE_BEGIN).sendToTarget();
                        //保存
//                        Bitmap bp = returnBitMap("http://img1.3lian.com/2015/w3/66/81.jpg");
                        Bitmap bp = returnBitMap(BaseApi.baseUrlNoApi + imgList.get(mPosition).getPath());
                        saveImageToGallery(SaveImageActivity.this,bp);
                    }
                }).start();
//                saveImageToGallery(SaveImageActivity.this,bitmap);
                dismiss();
            }
        };
        dialog.show();
    }

    //设置当前位置
    private void setPosition() {
        positionTv.setText(mPosition + 1 + "/" + imgList.size());
        viewPager.setCurrentItem(mPosition);
        mAdapter.notifyDataSetChanged();
    }

    //返回上一个页面
//    private void back() {
//        Intent intent = getIntent();
//        intent.putStringArrayListExtra(Constant.IMG_LIST_SAVE, imgList);
//        setResult(Constant.RESULT_CODE_VIEW_IMG, intent);
//        finish();
//    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        positionTv.setText(position + 1 + "/" + imgList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                //返回
//                back();
                finish();
                break;
            case R.id.delete_iv:
                //删除图片
                deletePic();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按下了返回键
//            back();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 保存图片
     * @param context
     * @param bmp
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            mHandler.obtainMessage(SAVE_FAILURE).sendToTarget();
            return;
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
        mHandler.obtainMessage(SAVE_SUCCESS).sendToTarget();

    }
    public Bitmap stringToBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * 将URL转化成bitmap形式
     *
     * @param url
     * @return bitmap type
     */
    public final static Bitmap returnBitMap(String url) {
        URL myFileUrl;
        Bitmap bitmap = null;
        try {
            myFileUrl = new URL(url);
            HttpURLConnection conn;
            conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    /**
     * 点击除了按钮的任何一个地方，finish掉
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                finish();
                break;
        }
        return super.onTouchEvent(event);
    }

}
