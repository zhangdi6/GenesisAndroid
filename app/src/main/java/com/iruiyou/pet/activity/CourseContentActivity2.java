package com.iruiyou.pet.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baijiayun.videoplayer.VideoPlayerFactory;
import com.baijiayun.videoplayer.event.BundlePool;
import com.baijiayun.videoplayer.ui.event.UIEventKey;
import com.baijiayun.videoplayer.ui.widget.BJYVideoView;
import com.baijiayun.videoplayer.util.Utils;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.CourseAdapter;
import com.iruiyou.pet.adapter.CourseLessonAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.BaseBean2;
import com.iruiyou.pet.bean.CourseLessonBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SelfDidalog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 职场-课程内容
 * 作者：sgf
 */
public class CourseContentActivity2 extends BaseActivity {
    @BindView(R.id.ll_crystal_number)
    RelativeLayout ll_crystal_number;//水晶数量布局
    @BindView(R.id.tv_crystal_number)
    TextView tv_crystal_number;//水晶数量
    @BindView(R.id.tv_course_sign_up)
    TextView tv_course_sign_up;
    @BindView(R.id.ll_course_sign_up)
    LinearLayout ll_course_sign_up;//立即报名
    @BindView(R.id.im_course_bg)
    ImageView im_course_bg;//头部背景图片
    /*    @BindView(R.id.ll_title_course_back)
        LinearLayout ll_title_course_back;//头部返回按钮*/
    /*@BindView(R.id.tv_course_titles)
    TextView tv_course_titles;//课程标题*/
    /*@BindView(R.id.im_course_head)
    ImageView im_course_head;//用户头像*/
    /*@BindView(R.id.tv_course_name)
    TextView tv_course_name;//用户姓名
    @BindView(R.id.tv_course_information)
    TextView tv_course_information;//用户信息*/
  /*  @BindView(R.id.tv_course_explain)
    TextView tv_course_explain;//课程说明*/
//    @BindView(R.id.im_course_screenshot)
//    ImageView im_course_screenshot;//课程说明图片
    @BindView(R.id.course_RecyclerView)
    MaxRecyclerView course_RecyclerView;
    /*  @BindView(R.id.tv_course_introduce)
      TextView tv_course_introduce;//课程介绍*/
   /* @BindView(R.id.tv_course_Tips)
    TextView tv_course_Tips;//开课提示*/
    /*@BindView(R.id.ll_my_information)
    LinearLayout ll_my_information;*/
   /* @BindView(R.id.im_top_course)
    ImageView im_top_course;*/
   /* @BindView(R.id.headzom_ScrollView)
    HeadZoomScrollView headzom_ScrollView;
*/
    @BindView(R.id.relay_zhekou)
    RelativeLayout relayZhekou;
    @BindView(R.id.text_before_zhekou)
    TextView text_before_zhekou;

    /*@BindView(R.id.activity_new_video_fl)
    BJYPlayerView activity_new_video_fl;*/

    @BindView(R.id.mrv_lessons)
    MaxRecyclerView mrvLessons;

    @BindView(R.id.bjy_video_view)
    BJYVideoView videoView;

    @BindView(R.id.back_white)
    ImageView mback_white;
    @BindView(R.id.explain_line)
    View mExplain_line;
    @BindView(R.id.source_explain)
    TextView mASource_explain;
    @BindView(R.id.list_line)
    View mlist_line;
    @BindView(R.id.source_list)
    TextView mSource_list;

    @BindView(R.id.back_black)
    ImageView mBack_black;
    @BindView(R.id.titlebar)
    TextView mTitlebar;
    @BindView(R.id.tb)
    ConstraintLayout mTb;
    @BindView(R.id.center_count)
    TextView mCenter_count;
    @BindView(R.id.center_time)
    TextView mCenter_time;
    @BindView(R.id.center_title)
    TextView mCenter_tiotle;
    @BindView(R.id.appbar)
    AppBarLayout mAppbar_layout;
    @BindView(R.id.srl_course_lesson)
    SmartRefreshLayout smartRefreshLayout;
    private List<CourseLessonBean> dataSourse;

    private GetCourseIntroBean.DataBean dataBean;
    private int mPosition;
    private boolean hadBuy = false;
    private int vipLevel;
    private double zhekou;
    protected boolean isLandscape;
    private CourseLessonAdapter adapter;
    private int lastPlayPosition = -1;

    @Override
    public int getLayout() {
        return R.layout.class_source_output;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        setTitle(getResources().getString(R.string.aboutUs));
        initDta();
        initTabClick();
        initAppBar();
        setRefresh();
        getLessons();

        checkCourseBuy();
    }

    //滑动监听，处理toorbar的渐变显示隐藏
    private void initAppBar() {
        mTb.setAlpha(0);
        mback_white.setVisibility(View.VISIBLE);

        mAppbar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i2) {
                // i2 参数代表了appbarlayout的偏移量，默认负数，在此我转换了一下好做判断
                int i1 = -i2;

                if (i1 == 0) {
                    //偏移量为0 ，白色返回按钮显示 ，toobar透明度设置为0
                    mback_white.setVisibility(View.VISIBLE);
                    mTb.setAlpha(0);
                } else if (i1 < 5000 && i1 > 0) {
                    //偏移量在一定范围内，让toobar 和白色返回按钮 渐变
                    float distence = (float) i1 / 800;
                    mTb.setVisibility(View.VISIBLE);
                    mTb.setAlpha(distence);
                    //toorbar里有黑色按钮，在其逐渐显示时，让白色返回键逐渐隐藏
                    mback_white.setAlpha(1 - distence);
                } else {
                    //当超过给定范围，toorbar完全显示，白色按钮隐藏
                    mTb.setAlpha(1);
                    mback_white.setVisibility(View.GONE);
                }
            }
        });
        mBack_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mback_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

    private void initVideoView() {

        DisplayMetrics dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        int heightPixels = widthPixels * 9 / 16;
        RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
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
                  setRequestedOrientation(isLandscape ?
                           ActivityInfo.SCREEN_ORIENTATION_PORTRAIT :
                           ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                default:
                    break;
            }
        });
       videoView.setupOnlineVideoWithId(Long.parseLong("15856071"), "lFZram7PsRMk1F2ucfEU1Nz3MWtIgMrEXEolPrOMAGnOlwOrJDqCxQ", true);
    }

    private void playPosition(int position) {
        CourseLessonBean bean = new CourseLessonBean();
        if (lastPlayPosition != -1) {
            dataSourse.get(lastPlayPosition).setPlaying(0);
            bean = dataSourse.get(lastPlayPosition);
            Log.i("lessons","-----------"+ lastPlayPosition+"++++++"+position);
        }
        if (lastPlayPosition != position) {
            Log.i("lessons","-----------"+ lastPlayPosition+"++++++"+position);
            lastPlayPosition = position;
            dataSourse.get(position).setPlaying(0);
            Log.i("lessons","-----------"+ dataSourse.toString());
            adapter.setNewData ( dataSourse ) ;
           bean = dataSourse.get(position);

//                    videoPlayer.setupOnlineVideoWithId(bean.getVideoId(), bean.getToken());

        }
        videoView.setupOnlineVideoWithId(bean.getVideoId(), bean.getToken(), true);
    }

    private void getLessons() {

        initAdapter();
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                /*Log.i("lessons", "onNext: "+resulte);*/

                BaseBean2 bean = GsonUtils.parseJson(resulte, BaseBean2.class);

                if (bean.getStatusCode() == 0) {

                    dataSourse = bean.getData();

                    mCenter_count.setText(dataSourse.size() + "课时");
                    adapter.setNewData(dataSourse);
                    adapter.notifyDataSetChanged();
                    playPosition(0);
                    smartRefreshLayout.finishRefresh(true);

                }

            }

            @Override
            public void onError(ApiException e) {
                /*T.showShort(e.getMessage());*/
            }
        }, this).getLessons(dataBean.get_id());
    }

    private void setRefresh() {
        //刷新操作
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getLessons();
            }
        });
    }

    protected void requestLayout(boolean isLandscape) {
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

    //初始化课程列表
    private void initAdapter() {
        adapter = new CourseLessonAdapter();
        adapter.setOnItemClickListener(new CourseLessonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position , int type) {
                if (type==1){
                    playPosition(position);
                }else {
                    if (hadBuy) {
                    } else {
//                    GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
                        String title = getString(R.string.prompt);
                        int vipLevel = SharePreferenceUtils.getBaseSharePreference().readVipLevel();
                        String content;
                        switch (vipLevel) {
                            case 5:
                                content = "您是高级会员，享受9折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.9) + "水晶";
                                break;
                            case 1:
                                content = "您是高级会员，享受8.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.85) + "水晶";
                                break;
                            case 2:
                                content = "您是商务会员，享受7.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.75) + "水晶";
                                break;
                            case 3:
                                content = "您是白金会员，享受6.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.65) + "水晶";
                                break;
                            case 4:
                                content = "您是企业会员，享受5.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.55) + "水晶";
                                break;
                            default:
                                content = "非会员无折扣优惠，需花费" + Math.ceil(dataBean.getPrice()) + "水晶";
                                break;
                        }
                        int noStrs = R.string.cancel;
                        int yesStrs = R.string.set3;
                        new SelfDidalog(CourseContentActivity2.this, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                            @Override
                            public void onYesClick() {
                                buyCourse();
                            }

                            @Override
                            public void onNoClick() {
                            }
                        }).show();
                    }
                }

            }
        });
        mrvLessons.setLayoutManager(new MyLinearLayoutManager(CourseContentActivity2.this));
        mrvLessons.setNestedScrollingEnabled(false);//禁止滑动
        mrvLessons.setAdapter(adapter);

    }

    //tab切换
    private void initTabClick() {

        //默认
        mExplain_line.setVisibility(View.VISIBLE);
        mASource_explain.setTextColor(Color.parseColor("#333333"));
        mASource_explain.setTextSize(14);
        mrvLessons.setVisibility(View.GONE);
        course_RecyclerView.setVisibility(View.VISIBLE);
        im_course_bg.setVisibility(View.VISIBLE);
        /*activity_new_video_fl.setVisibility(View.GONE);*/
        videoView.setVisibility(View.GONE);
        mlist_line.setVisibility(View.GONE);
        mback_white.setVisibility(View.VISIBLE);
        mSource_list.setTextColor(Color.parseColor("#999999"));
        mSource_list.setTextSize(12);

        //课程说明点击
        mASource_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mExplain_line.setVisibility(View.VISIBLE);
                mASource_explain.setTextColor(Color.parseColor("#333333"));
                mASource_explain.setTextSize(14);
                mrvLessons.setVisibility(View.GONE);
                course_RecyclerView.setVisibility(View.VISIBLE);
                im_course_bg.setVisibility(View.VISIBLE);
                /*     activity_new_video_fl.setVisibility(View.GONE);*/
                videoView.setVisibility(View.GONE);
                mback_white.setVisibility(View.VISIBLE);
                mTb.setVisibility(View.VISIBLE);
                mlist_line.setVisibility(View.GONE);
                mSource_list.setTextColor(Color.parseColor("#999999"));
                mSource_list.setTextSize(12);
            }
        });
        //课程列表说明
        mSource_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlist_line.setVisibility(View.VISIBLE);
                initVideoView();
                mSource_list.setTextColor(Color.parseColor("#333333"));
                mSource_list.setTextSize(14);
                mrvLessons.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                mTb.setVisibility(View.GONE);
                course_RecyclerView.setVisibility(View.GONE);
                /*  activity_new_video_fl.setVisibility(View.VISIBLE);*/
                videoView.setVisibility(View.VISIBLE);
                mback_white.setVisibility(View.GONE);
                im_course_bg.setVisibility(View.GONE);
                mExplain_line.setVisibility(View.GONE);
                mASource_explain.setTextColor(Color.parseColor("#999999"));
                mASource_explain.setTextSize(12);

            }
        });
    }

    private void initDta() {
        dataBean = (GetCourseIntroBean.DataBean) getIntent().getSerializableExtra("getCourseIntroBean");
        mPosition = getIntent().getIntExtra("CourseIntroPosition", 0);
        vipLevel = SharePreferenceUtils.getBaseSharePreference().readVipLevel();
        switch (vipLevel) {
            case 5: //初级会员
                zhekou = 0.9;
                break;
            case 1:
                zhekou = 0.85;
                break;
            case 2:
                zhekou = 0.75;
                break;
            case 3:
                zhekou = 0.65;
                break;
            case 4:
                zhekou = 0.55;
                break;
        }
        //群成员适配器
        CourseAdapter courseAdapter = new CourseAdapter();
        course_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        course_RecyclerView.setNestedScrollingEnabled(false);
        course_RecyclerView.setAdapter(courseAdapter);
        /*headzom_ScrollView.smoothScrollTo(0, 0);//默认置顶*/
/*
        headzom_ScrollView.setScrollListener(im_top_course);//设置一键置顶图标
*/

        if (dataBean != null) {

            mCenter_tiotle.setText(dataBean.getTitle());
            long saleTime = dataBean.getSaleTime();
            String s = DateUtils.formatDateTime(this, saleTime, DateUtils.FORMAT_SHOW_YEAR);

            mCenter_time.setText("上传时间：" + s);

            checkCourseBuy();
//            List<GetCourseIntroBean.DataBean.ImagesBean> images = dataBean.getImages();
            GlideUtils.display(BaseApi.baseUrlNoApi + dataBean.getImages().get(0).getPath(), im_course_bg);
            if (zhekou > 0 && (zhekou < 1)) {
                text_before_zhekou.setText(dataBean.getPrice() + "");
                tv_crystal_number.setText(((int) Math.ceil(dataBean.getPrice() * zhekou)) + "");
                relayZhekou.setVisibility(View.VISIBLE);
            } else {
                tv_crystal_number.setText(dataBean.getPrice() + "");
                relayZhekou.setVisibility(View.GONE);
            }


            //--------------------

//            getLessons();
//                tv_course_Tips.setText(getString(R.string.the_course, TimeUtil.getTimeFormatText(DataUtils.getStringDate(dataBean.getSaleTime()))));
         /*   String stringToday = DataUtils.getStringToday();//现在的时间
            String stringDateDay = DataUtils.getStringDate(dataBean.getSaleTime());//请求的时间
            String twoDay = DataUtils.getTwoDay(stringDateDay, stringToday);//两个时间的间隔天数
            if(twoDay.contains(Constant.BAR)){
                ll_course_tips.setVisibility(View.GONE);
            }else if("0".equals(twoDay)){
                ll_course_tips.setVisibility(View.GONE);
            }else {
                ll_course_tips.setVisibility(View.VISIBLE);
            }
            tv_course_Tips.setText(getString(R.string.the_course,twoDay));*/
//                tv_course_Tips.setText(getString(R.string.the_course, DataUtils.getStringDateDay(dataBean.getSaleTime())));
            mTitlebar.setText(dataBean.getTitle());
            GetCourseIntroBean.DataBean.TeacherBasicBean teacherBasic = dataBean.getTeacherBasic();
            courseAdapter.setNewData(dataBean.getImages());
            courseAdapter.notifyDataSetChanged();
           /* if(teacherBasic!=null){
                ll_my_information.setVisibility(View.GONE);
                GlideUtils.displayRound(this,BaseApi.baseUrlNoApi + teacherBasic.getHeadImg(), im_course_head);
                tv_course_name.setText(teacherBasic.getRealName());
                tv_course_information.setText(teacherBasic.getCompany()+ Constant.LARGE_SPACE + teacherBasic.getPosition());
            }else {
                //隐藏
                ll_my_information.setVisibility(View.GONE);
            }*/
        }

    }

    private void checkCourseBuy() {
//        GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                BaseBean loginBean = GsonUtils.parseJson(resulte, BaseBean.class);
                if (loginBean.getStatusCode() == 0) {
                    tv_course_sign_up.setText(R.string.course_learn_now);
                    ll_crystal_number.setVisibility(View.GONE);
                    hadBuy = true;
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).checkCourseBuy(dataBean.get_id());
    }

    @OnClick({R.id.ll_course_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /*case R.id.ll_title_course_back:
                finish();
                break;*/
            case R.id.ll_course_sign_up:
                if (hadBuy) {
                } else {
//                    GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
                    String title = getString(R.string.prompt);
                    int vipLevel = SharePreferenceUtils.getBaseSharePreference().readVipLevel();
                    String content;
                    switch (vipLevel) {
                        case 5:
                            content = "您是高级会员，享受9折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.9) + "水晶";
                            break;
                        case 1:
                            content = "您是高级会员，享受8.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.85) + "水晶";
                            break;
                        case 2:
                            content = "您是商务会员，享受7.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.75) + "水晶";
                            break;
                        case 3:
                            content = "您是白金会员，享受6.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.65) + "水晶";
                            break;
                        case 4:
                            content = "您是企业会员，享受5.5折优惠，只需花费" + Math.ceil(dataBean.getPrice() * 0.55) + "水晶";
                            break;
                        default:
                            content = "非会员无折扣优惠，需花费" + Math.ceil(dataBean.getPrice()) + "水晶";
                            break;
                    }
                    int noStrs = R.string.cancel;
                    int yesStrs = R.string.set3;
                    new SelfDidalog(CourseContentActivity2.this, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
                        @Override
                        public void onYesClick() {
                            buyCourse();
                        }

                        @Override
                        public void onNoClick() {
                        }
                    }).show();
                }
                break;
        }
    }

    /*  private void gotoLearn() {
          Intent intent = new Intent(this, CourseLessonActivity.class);
          Bundle bundle = new Bundle();
  //        GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
          bundle.putSerializable("CourseIntro", dataBean);
          intent.putExtras(bundle);
          startActivity(intent);
      }
  */
    private void buyCourse() {
//        GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                BaseBean loginBean = GsonUtils.parseJson(resulte, BaseBean.class);
                T.showShort(loginBean.getMessage());
                if (loginBean.getStatusCode() == 0) {
                    tv_course_sign_up.setText(R.string.course_learn_now);
                    ll_crystal_number.setVisibility(View.GONE);
                    hadBuy = true;
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).buyCourse(dataBean.get_id());
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
