package com.iruiyou.pet.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.baijiayun.constant.VideoDefinition;
import com.baijiayun.playback.context.LPError;
import com.baijiayun.videoplayer.IBJYVideoPlayer;
import com.baijiayun.videoplayer.VideoPlayerFactory;
import com.baijiayun.videoplayer.event.BundlePool;
import com.baijiayun.videoplayer.listeners.OnBufferingListener;
import com.baijiayun.videoplayer.render.AspectRatio;
import com.baijiayun.videoplayer.render.IRender;
import com.baijiayun.videoplayer.ui.event.UIEventKey;
import com.baijiayun.videoplayer.ui.widget.BJYVideoView;
import com.baijiayun.videoplayer.util.Utils;
import com.baijiayun.videoplayer.widget.BJYPlayerView;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.CourseLessonAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.CourseLessonBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//import com.baijiayun.videoplayer.ui.activity.BaseActivity;

public class CourseLessonActivity extends BaseActivity {


    @BindView(R.id.srl_course_lesson)
    SmartRefreshLayout srlCourseLesson;

    @BindView(R.id.activity_new_video_fl)
    BJYPlayerView activity_new_video_fl;

    @BindView(R.id.mrv_lessons)
    MaxRecyclerView mrvLessons;

    @BindView(R.id.bjy_video_view)
    BJYVideoView videoView;

    private CourseLessonAdapter adapter;
    private GetCourseIntroBean.DataBean courseIntro;
    private List<CourseLessonBean> dataSourse;
    private int lastPlayPosition = -1;
    private IBJYVideoPlayer videoPlayer;

    protected boolean isLandscape;

    @Override
    public void onBackPressed() {
        if (isLandscape) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLandscape = true;
            requestLayout(true);
        } else {
            isLandscape = false;
            requestLayout(false);
        }
    }

    protected void requestLayout(boolean isLandscape){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) videoView.getLayoutParams();
        if (isLandscape) {
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        } else {
            layoutParams.width = Utils.getScreenWidthPixels(this);
            layoutParams.height = layoutParams.width * 9 / 16;
        }
        videoView.setLayoutParams(layoutParams);
        videoView.sendCustomEvent(UIEventKey.CUSTOM_CODE_REQUEST_TOGGLE_SCREEN, BundlePool.obtain(isLandscape));
    }

    @Override
    public int getLayout() {
        return R.layout.activity_course_lesson;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        initData();
        initViews();
//        initPlayer();
        initVideoView();
        initAdapter();
        getLessons();
        setRefresh();
    }

    private void initData() {
        courseIntro = (GetCourseIntroBean.DataBean) getIntent().getSerializableExtra("CourseIntro");
    }

    private void initViews() {
        ButterKnife.bind(this);
        setTitle("课程学习");
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
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = widthPixels * 9 / 16;
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)activity_new_video_fl.getLayoutParams();
        linearParams.height = heightPixels;
        activity_new_video_fl.setLayoutParams(linearParams);
        //设置视频画面裁剪方式，默认16：9
        activity_new_video_fl.setAspectRatio(AspectRatio.AspectRatio_16_9);
//        videoPlayer.setupOnlineVideoWithId(Long.parseLong("15856071"), "lFZram7PsRMk1F2ucfEU1Nz3MWtIgMrEXEolPrOMAGnOlwOrJDqCxQ", "");
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

    private void initVideoView() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = widthPixels * 9 / 16;
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams)videoView.getLayoutParams();
        linearParams.height = heightPixels;
        videoView.setLayoutParams(linearParams);

        videoView.initPlayer(new VideoPlayerFactory.Builder()
                //后台暂停播放
                .setSupportBackgroundAudio(false)
                //开启循环播放
                .setSupportLooping(false)
                //开启记忆播放
                .setSupportBreakPointPlay(true, this)
                //绑定activity生命周期
                .setLifecycle(getLifecycle()).build()
        );


        videoView.setComponentEventListener((eventCode, bundle) -> {
            switch (eventCode) {
                case UIEventKey.CUSTOM_CODE_REQUEST_BACK:
                    if (isLandscape) {
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    } else {
                        finish();
                    }
                    break;
                case UIEventKey.CUSTOM_CODE_REQUEST_TOGGLE_SCREEN:
//                    setRequestedOrientation(isLandscape ?
//                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT :
//                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                default:
                    break;
            }
        });
//        videoView.setupOnlineVideoWithId(Long.parseLong("15856071"), "lFZram7PsRMk1F2ucfEU1Nz3MWtIgMrEXEolPrOMAGnOlwOrJDqCxQ", true);
    }

    private void initAdapter() {
        adapter = new CourseLessonAdapter();
        adapter.setOnItemClickListener(new CourseLessonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position,int type) {
                playPosition(position);
            }
        });
        mrvLessons.setLayoutManager(new MyLinearLayoutManager(CourseLessonActivity.this));
        mrvLessons.setNestedScrollingEnabled(false);//禁止滑动
        mrvLessons.setAdapter(adapter);

    }

    private void playPosition(int position) {
        if (lastPlayPosition != -1) {
            dataSourse.get(lastPlayPosition).setPlaying(0);
        }
        if (lastPlayPosition != position) {
            lastPlayPosition = position;
            dataSourse.get(lastPlayPosition).setPlaying(1);
            adapter.setNewData(dataSourse);
            CourseLessonBean bean = dataSourse.get(lastPlayPosition);
//                    videoPlayer.setupOnlineVideoWithId(bean.getVideoId(), bean.getToken());
            videoView.setupOnlineVideoWithId(bean.getVideoId(), bean.getToken(), true);
        }
    }

    private void getLessons() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.i("lessons", "onNext: "+resulte);
                BaseBean bean = GsonUtils.parseJson(resulte, BaseBean.class);
                if (bean.getStatusCode() == 0) {
                    try {
                        dataSourse = GsonUtils.parseObjectToList(bean.getData(), CourseLessonBean.class);
                        if (lastPlayPosition == -1) {
                            playPosition(0);
                        }
                        adapter.setNewData(dataSourse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                srlCourseLesson.finishRefresh(true);
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).getLessons(courseIntro.get_id());
    }

    private void setRefresh() {
        //刷新操作
        srlCourseLesson.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getLessons();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.onDestroy();
    }
}