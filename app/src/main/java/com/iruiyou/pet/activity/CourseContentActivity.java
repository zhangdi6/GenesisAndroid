package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.adapter.CourseAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.HeadZoomScrollView;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SelfDidalog;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 职场-课程内容
 * 作者：sgf
 *
 */
public class CourseContentActivity extends BaseActivity {
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
    @BindView(R.id.ll_title_course_back)
    LinearLayout ll_title_course_back;//头部返回按钮
    @BindView(R.id.tv_course_titles)
    TextView tv_course_titles;//课程标题
    @BindView(R.id.im_course_head)
    ImageView im_course_head;//用户头像
    @BindView(R.id.tv_course_name)
    TextView tv_course_name;//用户姓名
    @BindView(R.id.tv_course_information)
    TextView tv_course_information;//用户信息
    @BindView(R.id.tv_course_explain)
    TextView tv_course_explain;//课程说明
//    @BindView(R.id.im_course_screenshot)
//    ImageView im_course_screenshot;//课程说明图片
    @BindView(R.id.course_RecyclerView)
MaxRecyclerView course_RecyclerView;
    @BindView(R.id.tv_course_introduce)
    TextView tv_course_introduce;//课程介绍
    @BindView(R.id.tv_course_Tips)
    TextView tv_course_Tips;//开课提示
    @BindView(R.id.ll_my_information)
    LinearLayout ll_my_information;
    @BindView(R.id.im_top_course)
    ImageView im_top_course;
    @BindView(R.id.headzom_ScrollView)
    HeadZoomScrollView headzom_ScrollView;
    @BindView(R.id.rl_course_content)
    RelativeLayout rl_course_content;
    @BindView(R.id.ll_course_tips)
    LinearLayout ll_course_tips;
    @BindView(R.id.relay_zhekou)
    RelativeLayout relayZhekou;
    @BindView(R.id.text_before_zhekou)
    TextView text_before_zhekou;
    private GetCourseIntroBean.DataBean dataBean;
    private int mPosition;
    private boolean hadBuy = false;
    private int vipLevel;
    private double zhekou;
    @Override
    public int getLayout() {
        return R.layout.activity_course_content;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        setTitle(getResources().getString(R.string.aboutUs));
        initDta();
        checkCourseBuy();
    }

    private void initDta() {
        dataBean = (GetCourseIntroBean.DataBean) getIntent().getSerializableExtra("getCourseIntroBean");
        mPosition = getIntent().getIntExtra("CourseIntroPosition", 0);
        vipLevel= SharePreferenceUtils.getBaseSharePreference().readVipLevel();
        switch (vipLevel)
        {
            case 5: //初级会员
                zhekou=0.9;
                break;
            case 1:
                zhekou=0.85;
                break;
            case 2:
                zhekou=0.75;
                break;
            case 3:
                zhekou=0.65;
                break;
            case 4:
                zhekou=0.55;
                break;
        }
        //群成员适配器
        CourseAdapter courseAdapter = new CourseAdapter();
        course_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        course_RecyclerView.setNestedScrollingEnabled(false);
        course_RecyclerView.setAdapter(courseAdapter);
        headzom_ScrollView.smoothScrollTo(0, 0);//默认置顶
        /*headzom_ScrollView.setScrollListener(im_top_course);//设置一键置顶图标*/

        if(dataBean!=null){
//            List<GetCourseIntroBean.DataBean.ImagesBean> images = dataBean.getImages();
            GlideUtils.display(BaseApi.baseUrlNoApi + dataBean.getImages().get(0).getPath(), im_course_bg);
            if(zhekou>0&&(zhekou<1))
            {
                text_before_zhekou.setText(dataBean.getPrice()+"");
                tv_crystal_number.setText(((int)Math.ceil(dataBean.getPrice()*zhekou))+"");
                relayZhekou.setVisibility(View.VISIBLE);
            }
            else
            {
                tv_crystal_number.setText(dataBean.getPrice()+"");
                relayZhekou.setVisibility(View.GONE);
            }

//                tv_course_Tips.setText(getString(R.string.the_course, TimeUtil.getTimeFormatText(DataUtils.getStringDate(dataBean.getSaleTime()))));
            String stringToday = DataUtils.getStringToday();//现在的时间
            String stringDateDay = DataUtils.getStringDate(dataBean.getSaleTime());//请求的时间
            String twoDay = DataUtils.getTwoDay(stringDateDay, stringToday);//两个时间的间隔天数
            if(twoDay.contains(Constant.BAR)){
                ll_course_tips.setVisibility(View.GONE);
            }else if("0".equals(twoDay)){
                ll_course_tips.setVisibility(View.GONE);
            }else {
                ll_course_tips.setVisibility(View.VISIBLE);
            }
            tv_course_Tips.setText(getString(R.string.the_course,twoDay));
//                tv_course_Tips.setText(getString(R.string.the_course, DataUtils.getStringDateDay(dataBean.getSaleTime())));
            tv_course_titles.setText(dataBean.getTitle());
            GetCourseIntroBean.DataBean.TeacherBasicBean teacherBasic = dataBean.getTeacherBasic();

            courseAdapter.setNewData(dataBean.getImages());
            courseAdapter.notifyDataSetChanged();
            if(teacherBasic!=null){
                ll_my_information.setVisibility(View.GONE);
                GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + teacherBasic.getHeadImg(), im_course_head);
                tv_course_name.setText(teacherBasic.getRealName());
                tv_course_information.setText(teacherBasic.getCompany()+ Constant.LARGE_SPACE + teacherBasic.getPosition());
            }else {
                //隐藏
                ll_my_information.setVisibility(View.GONE);
            }
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

    @OnClick({R.id.ll_title_course_back, R.id.ll_course_sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_title_course_back:
                finish();
                break;
            case R.id.ll_course_sign_up:
                if (hadBuy) {
                    gotoLearn();
                } else {
//                    GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
                    String title = getString(R.string.prompt);
                    int vipLevel = SharePreferenceUtils.getBaseSharePreference().readVipLevel();
                    String content;
                    switch (vipLevel){
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
                    new SelfDidalog(CourseContentActivity.this, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
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

    private void gotoLearn() {
        Intent intent = new Intent(this, CourseLessonActivity.class);
        Bundle bundle = new Bundle();
//        GetCourseIntroBean.DataBean dataBean = data.get(mPosition);
        bundle.putSerializable("CourseIntro", dataBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

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
