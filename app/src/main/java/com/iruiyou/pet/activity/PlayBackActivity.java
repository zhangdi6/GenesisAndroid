package com.iruiyou.pet.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiayun.constant.VideoDefinition;
import com.baijiayun.playback.context.LPError;
import com.baijiayun.videoplayer.IBJYVideoPlayer;
import com.baijiayun.videoplayer.VideoPlayerFactory;
import com.baijiayun.videoplayer.listeners.OnBufferingListener;
import com.baijiayun.videoplayer.render.AspectRatio;
import com.baijiayun.videoplayer.render.IRender;
import com.baijiayun.videoplayer.widget.BJYPlayerView;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的-测试播放视频
 * 作者：sgf
 *
 */
public class PlayBackActivity extends BaseActivity {
    @BindView(R.id.title_right_text)
    TextView titleRightText;
    @BindView(R.id.title_right_img)
    ImageView titleRightImg;
    @BindView(R.id.title_name_text)
    TextView titleNameText;
    @BindView(R.id.edit)
    ImageView edit;
    @BindView(R.id.ll_title_left_view)
    LinearLayout llTitleLeftView;
    @BindView(R.id.titleview)
    RelativeLayout titleview;
    private IBJYVideoPlayer videoPlayer;


    @BindView(R.id.activity_new_video_fl)
    BJYPlayerView activity_new_video_fl;
    @BindView(R.id.bt_play)
    Button bt_play;
    @BindView(R.id.button_rate)
    Button button_rate;
    @BindView(R.id.bt_stop)
    Button bt_stop;
    @BindView(R.id.button_definition)
    Button button_definition;
    @BindView(R.id.bt_seekTo)
    Button bt_seekTo;
    @BindView(R.id.et_seek_time)
    EditText et_seek_time;

    @Override
    public int getLayout() {
        return R.layout.activity_play_back;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("视频");
        initDta();
        initPlayer();
    }

    private void initDta() {
    }
    private void initPlayer() {
        videoPlayer = new VideoPlayerFactory.Builder()
                //开启循环播放
                .setSupportLooping(true)
                //关闭后台音频播放
                .setSupportBackgroundAudio(false)
                //开启记忆播放
                .setSupportBreakPointPlay(true, this)
                //设置在线播放清晰度匹配规则，这里仅传Audio则优先播放纯音频，如无纯音频则播放服务器返回的默认清晰度
                .setPreferredDefinitions(new ArrayList<VideoDefinition>(){{add(VideoDefinition.Audio);}})
                //绑定activity生命周期
                .setLifecycle(getLifecycle())
                .build();

        videoPlayer.bindPlayerView(activity_new_video_fl);
        //TODO 需在bindPlayerView之后设置，否则报NPE
        //设置视频渲染载体，默认textureView,IRender.RENDER_TYPE_TEXTURE_VIEW和IRender.RENDER_TYPE_SURFACE_VIEW可选
        activity_new_video_fl.setRenderType(IRender.RENDER_TYPE_TEXTURE_VIEW);
        //设置视频画面裁剪方式，默认16：9
        activity_new_video_fl.setAspectRatio(AspectRatio.AspectRatio_16_9);
        videoPlayer.bindPlayerView(activity_new_video_fl);
        videoPlayer.setupOnlineVideoWithId(Long.parseLong("15856071"), "lFZram7PsRMk1F2ucfEU1Nz3MWtIgMrEXEolPrOMAGnOlwOrJDqCxQ", "");
//        videoPlayer.setupLocalVideoWithFilePath("http://b45548056.at.baijiayun.com/web/video/player?vid=15856071&token=lFZram7PsRMk1F2ucfEU1Nz3MWtIgMrEXEolPrOMAGnOlwOrJDqCxQ");
//        videoPlayer.play();
        videoPlayer.addOnPlayerErrorListener(error -> {
            //Log.e("PlayerError", error.getCode() + " " + error.getMessage());
            if(error.getCode() == LPError.ACCESS_KEY_DENY){
                if (!TextUtils.isEmpty(error.getMessage())) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(error.getMessage());
                    intent.setData(content_url);
                    startActivity(intent);
                }
            }
        });

        videoPlayer.addOnPlayerStatusChangeListener(status -> {
//            tvPlayerStatus.setText("status: " + status.name());
            //                ((Button) firstView.findViewById(R.id.button_definition)).setText(videoPlayer.getVideoInfo().getDefinition().name());
        });
//        videoPlayer.addOnBufferUpdateListener(bufferedPercentage -> tvBuffering.setText("buffered: " + bufferedPercentage + "%"));
        videoPlayer.addOnBufferingListener(new OnBufferingListener() {
            @Override
            public void onBufferingStart() {
            }

            @Override
            public void onBufferingEnd() {
            }
        });
//        videoPlayer.addOnPlayingTimeChangeListener((currentTime, duration) ->
//                tvTime.setText("time: " + currentTime + "/" + duration + "  percent: " + currentTime * 100 / duration + "%"));

    }

    @OnClick({R.id.bt_play, R.id.bt_stop, R.id.bt_seekTo, R.id.button_definition, R.id.button_rate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_play:
                if (videoPlayer.isPlaying())
                    videoPlayer.pause();
                else
                    videoPlayer.play();
                break;
            case R.id.bt_stop:
                videoPlayer.stop();
                break;
            case R.id.bt_seekTo:
                String seekTo = et_seek_time.getText().toString();
                int time;
                try {
                    time = Integer.parseInt(seekTo);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
                videoPlayer.seek(time);
                break;
            case R.id.button_definition:
                button_definition.setText(Objects.requireNonNull(videoPlayer.getVideoInfo()).getDefinition().name());
                break;
            case R.id.button_rate:
                String text = button_rate.getText().toString();
                if ("1.0x".equals(text)) {
                    videoPlayer.setPlayRate(1.2f);
                    button_rate.setText("1.2x");
                } else if ("1.2x".equals(text)) {
                    videoPlayer.setPlayRate(1.5f);
                    button_rate.setText("1.5x");
                } else if ("1.5x".equals(text)) {
                    videoPlayer.setPlayRate(2.0f);
                    button_rate.setText("2.0x");
                } else if ("2.0x".equals(text)) {
                    videoPlayer.setPlayRate(.5f);
                    button_rate.setText("0.5x");
                } else if ("0.5x".equals(text)) {
                    videoPlayer.setPlayRate(1.f);
                    button_rate.setText("1.0x");
                }
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(PageNameConstant.PageName_AboutUsActivity);
        MobclickAgent.onPause(this);
    }
}
