package com.baijiayun.videoplayerui.demo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.baijiahulian.common.permission.AppPermissions;
import com.baijiayun.videoplayer.ui.activity.VideoPlayActivity;
import com.baijiayun.videoplayer.ui.component.ComponentManager;
import com.baijiayun.videoplayer.ui.component.ControllerComponent;
import com.baijiayun.videoplayer.ui.component.LoadingComponent;
import com.baijiayun.videoplayer.ui.event.UIEventKey;

import java.io.File;

import rx.android.schedulers.AndroidSchedulers;

/**
 * 点播配置页面
 */
public class VideoLauncherActivity extends AppCompatActivity {

    private final String VIDEO_ID = "videoId";
    private final String TOKEN = "token";

    private Button playBtn;
    private Button offlinePlayBtn;
    private EditText videoIdEt;
    private EditText videoTokenEt;
    private EditText videoPathEt;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_launcher);
        playBtn = findViewById(R.id.play_btn);
        offlinePlayBtn = findViewById(R.id.offline_play_btn);
        videoIdEt = findViewById(R.id.videoId_et);
        videoTokenEt = findViewById(R.id.video_token_et);
        videoPathEt = findViewById(R.id.video_path_et);
        recoverStatus();
        initListener();
    }

    private void recoverStatus(){
        sharedPreferences = getSharedPreferences("video_launcher_sp", MODE_PRIVATE);
        String videoId = sharedPreferences.getString(VIDEO_ID, "0");
        String token = sharedPreferences.getString(TOKEN, "test12345678");
        videoIdEt.setText(videoId);
        videoTokenEt.setText(token);

        videoPathEt.setText(sharedPreferences.getString("videoPath", ""));
    }

    private void initListener(){
        playBtn.setOnClickListener(v -> {
            String videoId = videoIdEt.getText().toString();
            String videoToken = videoTokenEt.getText().toString();
            if(TextUtils.isEmpty(videoId) || TextUtils.isEmpty(videoToken)){
                Toast.makeText(VideoLauncherActivity.this, "视频Id和token不能为空", Toast.LENGTH_LONG).show();
                return;
            }
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(VIDEO_ID, videoId);
            editor.putString(TOKEN, videoToken);
            editor.apply();
            Intent intent = new Intent(VideoLauncherActivity.this, VideoPlayActivity.class);
            intent.putExtra(VIDEO_ID, Long.parseLong(videoId));
            intent.putExtra(TOKEN, videoToken);
            intent.putExtra("isOffline", false);
            startActivity(intent);
        });

        offlinePlayBtn.setOnClickListener(v -> AppPermissions.newPermissions(VideoLauncherActivity.this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            String videoPath = videoPathEt.getText().toString();
                            File file = new File(videoPath);
                            if (file.exists()) {
                                Intent intent = new Intent(VideoLauncherActivity.this, VideoPlayActivity.class);
                                intent.putExtra("videoPath", videoPath);
                                intent.putExtra("isOffline", true);
                                startActivity(intent);
                                sharedPreferences.edit().putString("videoPath", videoPath).apply();
                            } else {
                                Toast.makeText(VideoLauncherActivity.this, videoPath + "不存在的", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(VideoLauncherActivity.this, "找不到存储卡！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(VideoLauncherActivity.this, "没有获取读写sd卡权限", Toast.LENGTH_SHORT).show();
                    }
                }));
    }
}
