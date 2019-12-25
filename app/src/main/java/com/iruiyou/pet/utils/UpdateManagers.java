package com.iruiyou.pet.utils;

/**
 * Created by sgf
 */

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.iruiyou.pet.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class UpdateManagers {

    private ProgressBar mProgressBar;
    private Dialog mDownloadDialog;

    private long fileSize;
    private String mSavePath;
    private int mProgress;

    private boolean mIsCancel = false;
    private boolean mIsClose = false;
    private static final int DOWNLOADING = 1;
    private static final int DOWNLOAD_FINISH = 2;
    private Context mContext;
    private String mVersion_apk;


    public UpdateManagers(Context context,long fileSize,boolean mIsClose) {
        mContext = context;
        this.fileSize=fileSize;
        this.mIsClose=mIsClose;
//        this.fileSize = 58720256;
    }


    private Handler mUpdateProgressHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    // 设置进度条
                    mProgressBar.setProgress(mProgress);
                    break;
                case DOWNLOAD_FINISH:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();
                    // 安装 APK 文件
                    installAPK();
            }
        }

    };

    /*
     * 检测软件是否需要更新
     */
    public void checkUpdate(String desc, String flag, String url, String version, String versionid) {
        mVersion_apk = url;
        //showNoticeDialog();
        showDownloadDialog();
    }


    /*
     * 有更新时显示提示对话框
     */
    protected void showNoticeDialog() {
        Builder builder = new Builder(mContext);
        builder.setTitle("提示");
        String message = "软件有更新，要下载安装吗？\n";
        builder.setMessage(message);

        builder.setPositiveButton("更新", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 显示下载对话框
                showDownloadDialog();
            }
        });

        builder.setNegativeButton("下次再说", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    /*
     * 显示正在下载对话框
     */
    protected void showDownloadDialog() {
        Builder builder = new Builder(mContext);
        View view = LayoutInflater.from(mContext).inflate(R.layout.updata_loading, null);
        mProgressBar = view.findViewById(R.id.updata_progress);
        builder.setView(view);
//        if(mIsClose){
//            builder.setNegativeButton("取消", new OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // 隐藏当前对话框
//                    dialog.dismiss();
//                    // 设置下载状态为取消
//                    mIsCancel = true;
//                }
//            });
//        }
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
        mDownloadDialog.setCancelable(false);
        mDownloadDialog.setCanceledOnTouchOutside(false);
        if(fileSize>0){
            // 下载文件
            downloadAPK();
        }
    }

    /*
     * 开启新线程下载文件
     */
    private void downloadAPK() {
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
                        HttpURLConnection conn = (HttpURLConnection) new URL(mVersion_apk).openConnection();
                        InputStream is = conn.getInputStream();
                        int status = conn.getResponseCode();
                        File apkFile = new File(mSavePath, "release");
                        //if (!apkFile.exists())


                        FileOutputStream fos = new FileOutputStream(apkFile);

                        int count = 0;
                        byte[] buffer = new byte[1024];
                        while (!mIsCancel) {
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float) count / fileSize) * 100);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(DOWNLOADING);

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /*
     * 下载到本地后执行安装
     */
    protected void installAPK() {
        File apkFile = new File(mSavePath, "release");
        if (!apkFile.exists())
            return;

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");

        mContext.startActivity(intent);
        // android.os.Process.killProcess(android.os.Process.myPid());
    }
}
