package com.iruiyou.pet.activity;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.PDFShowAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DialogUtil;
import com.iruiyou.pet.utils.StringUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PDF展示界面
 */
public class PDFShowActivity extends BaseActivity {
    private PdfRenderer mPdfRenderer;
    private String mSavePath;
    private static final int DOWNLOAD_FINISH = 2;
    private PDFShowAdapter pdfShowAdapter;
    private List<Bitmap> bitmaps;
    private String downloadFileName;
    @BindView(R.id.recycle_content)
    RecyclerView recycle_content;

    @Override
    public int getLayout() {
        return R.layout.activity_pdf_show;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("在线合同");
        recycle_content.setLayoutManager(new LinearLayoutManager(PDFShowActivity.this));
        bitmaps = new ArrayList<>();
        getData();
    }

    private Handler mUpdateProgressHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_FINISH:
                    showDPF();
                    DialogUtil.getInstance().closeLoadingDialog();
                    break;
            }
        }

    };


    @TargetApi(21)
    private void showDPF() {
        if (StringUtil.isNotEmpty(mSavePath)) {
            ParcelFileDescriptor parcelFileDescriptor = null;
            mSavePath += downloadFileName;
            if(bitmaps.size()>0){
                for(Bitmap bitmap:bitmaps){
                    bitmap.recycle();
                }
                bitmaps.clear();
            }
            try {
                parcelFileDescriptor =
                        ParcelFileDescriptor.open(new File(mSavePath), ParcelFileDescriptor.MODE_READ_ONLY);
                mPdfRenderer = new PdfRenderer(parcelFileDescriptor);
                int pages = mPdfRenderer.getPageCount();
                HashMap<Integer, Bitmap> pdfPages = new HashMap<>();
                List<Integer> dataSource = new ArrayList<>();
                for (int index = 0; index < pages; index++) {
                    PdfRenderer.Page mCurrentPage = mPdfRenderer.openPage(index);
                    //Bitmap必须是ARGB，不可以是RGB
                    Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                            Bitmap.Config.ARGB_8888);
                    /*
                     * 调用PdfRender.Page的render方法渲染bitmap
                     *
                     * render的参数说明：
                     * destination : 要渲染的bitmap对象
                     * destClip ：传一个矩形过去 矩形的尺寸不能大于bitmap的尺寸 最后渲染的pdf会是rect的大小 可为null
                     * transform : 一个Matrix bitmap根据该Matrix图像进行转换
                     * renderMode ：渲染模式 可选2种 RENDER_MODE_FOR_DISPLAY 和 RENDER_MODE_FOR_PRINT
                     */
                    mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                    pdfPages.put(index, bitmap);
                    bitmaps.add(bitmap);
                    dataSource.add(index);
                    mCurrentPage.close();
                }

                if (pdfPages.size() > 0) {
                    pdfShowAdapter = new PDFShowAdapter(pdfPages);
                    pdfShowAdapter.setNewData(dataSource);
                    recycle_content.setAdapter(pdfShowAdapter);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (null != mPdfRenderer) {
                    mPdfRenderer.close();
                }
                if (null != parcelFileDescriptor) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    void getData() {
        DialogUtil.getInstance().showLoadingDialog(PDFShowActivity.this, "下载中...");
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.i("con", "onNext: "+resulte);
                if (StringUtil.isNotEmpty(resulte)) {
                    try {
                        JSONObject jsonObject = new JSONObject(resulte);
                        if (jsonObject.optInt("statusCode") == Constant.SUCCESS) {
                            JSONArray pdfUrlData = jsonObject.getJSONArray("data");//.getJSONObject(0).optString("pdf");
                            if(pdfUrlData.length()>0){
                              String pdfUrl =  pdfUrlData.getJSONObject(0).optString("pdf");
                                if (StringUtil.isNotEmpty(pdfUrl)) {
                                    downloadPDF(pdfUrl);
                                }else{
                                    DialogUtil.getInstance().closeLoadingDialog();
                                }
                            }else{
                                DialogUtil.getInstance().closeLoadingDialog();
                            }
//                            String pdfUrl = "https://www.tutorialspoint.com/ios/ios_tutorial.pdf";
//                            downloadPDF(pdfUrl);
                        } else {
                            DialogUtil.getInstance().closeLoadingDialog();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        DialogUtil.getInstance().closeLoadingDialog();
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                DialogUtil.getInstance().closeLoadingDialog();
            }
        }, PDFShowActivity.this).getContractPDF();
    }


    /*
     * 开启新线程下载文件
     */
    private void downloadPDF(String downloadUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
                        mSavePath = sdPath + "maichang/download/";
                        File dir = new File(mSavePath);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }
                        // 下载文件
                        HttpURLConnection conn = (HttpURLConnection) new URL(downloadUrl).openConnection();
                        InputStream is = conn.getInputStream();
                        int status = conn.getResponseCode();
                        String[] data = downloadUrl.split("/");
                        downloadFileName = data[data.length - 1];
                        File apkFile = new File(mSavePath, downloadFileName);
                        boolean isDownload = true;
                        if (status == 200) {
                            if (apkFile.exists()) {
//                                if(apkFile.length() == is.available()){
//                                    isDownload = false;
//                                    mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
//                                }else{
//                                    apkFile.delete();
//                                }
                                isDownload = false;
                                mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            }

                            if (isDownload) {
                                FileOutputStream fos = new FileOutputStream(apkFile);
                                int count = 0, numread = 1;
                                byte[] buffer = new byte[1024];
                                while (numread > 0) {
                                    numread = is.read(buffer);
                                    count += numread;
                                    // 计算进度条的当前位置
//                            mProgress = (int) (((float) count / fileSize) * 100);
                                    // 更新进度条
//                            mUpdateProgressHandler.sendEmptyMessage(DOWNLOADING);

                                    Log.e("test", "numread is " + numread);
                                    // 下载完成
                                    if (numread < 0) {
                                        mUpdateProgressHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                                        break;
                                    }
                                    fos.write(buffer, 0, numread);
                                }
                                fos.close();
                                is.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        if(bitmaps.size()>0){
            for(Bitmap bitmap:bitmaps){
                bitmap.recycle();
            }
        }
        super.onDestroy();
    }
}
