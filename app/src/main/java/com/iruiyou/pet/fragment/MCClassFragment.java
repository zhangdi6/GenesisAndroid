package com.iruiyou.pet.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.baijiayun.videoplayer.ui.utils.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.common.utils.TagConstants;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.CombatRankingActivity;
import com.iruiyou.pet.activity.CourseContentActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.CourseContentAdapter;
import com.iruiyou.pet.adapter.FindElitesAdapter;
import com.iruiyou.pet.adapter.PositionListAdapter;
import com.iruiyou.pet.adapter.PositionSpinnerAdapter;
import com.iruiyou.pet.adapter.WorkplaceCourseAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.CourseBean;
import com.iruiyou.pet.bean.DMOptionListBean;
import com.iruiyou.pet.bean.DoumiOptionBean;
import com.iruiyou.pet.bean.GetCourseIntroBean;
import com.iruiyou.pet.bean.HomeRefreshBean;
import com.iruiyou.pet.bean.RecomendsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.GoTopScrollView;
import com.iruiyou.pet.utils.HoverScrollView;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.RecycleViewDivider;
import com.iruiyou.pet.utils.StringUtil;
import com.iruiyou.pet.view.PageNumberRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 脉场课堂
 * Created by shenggaofei on 2018/10/29.
 */
public class MCClassFragment extends BaseFragment implements HoverScrollView.OnScrollListener {
    private static final String TAG = "MCClassFragment";
    private static com.iruiyou.pet.fragment.MCClassFragment MCClassFragment = null;
    private SmartRefreshLayout refreshLayout_find;
    private RecyclerView findRecyclerView, workplace_course_RecyclerView;
    private FindElitesAdapter findElitesAdapter;
    private RecomendsBean recomendsBean;
    private GoTopScrollView scrollView_workplace;
    private LinearLayout resumeLFind, ll_workplace_course;
    private MaxRecyclerView course_content_recyclerView;
    private Integer[] courseText;//职业课程文字
    private Integer[] courseIcon;//职业课程图标
    private List<CourseBean> courseList = new ArrayList<>();
    private WorkplaceCourseAdapter workplaceCourseAdapter;
    private ImageView im_no_courses;
    int selectorPosition = 0;
    private CourseContentAdapter courseContentAdapter;
    private GetCourseIntroBean getCourseIntroBean;
    private LinearLayout hover;
    private ImageView im_top;
    private LinearLayout top_buy_layout;
    private RelativeLayout parent_layout;
    private int lastCount = 0;
    private List<GetCourseIntroBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.rl_back)
    RelativeLayout reBack;

    @BindView(R.id.recycle_position)
    PageNumberRecyclerView recycle_position;

    @BindView(R.id.text_class)
    TextView text_class;

    @BindView(R.id.text_position)
    TextView text_position;

    @BindView(R.id.linear_class)
    LinearLayout linear_class;

    @BindView(R.id.linear_position)
    LinearLayout linear_position;

    @BindView(R.id.spinner_position_quanzhi)
    Spinner spinner_position_quanzhi;

    @BindView(R.id.spinner_position_area)
    Spinner spinner_position_area;

    @BindView(R.id.spinner_position_class)
    Spinner spinner_position_class;

    @BindView(R.id.refreshLayout_position)
    SmartRefreshLayout refreshLayout_position;

    private DoumiOptionBean doumiOptionBean;
    private PositionListAdapter positionListAdapter;

    private DoumiOptionBean.DataValue shengfen;
    private DoumiOptionBean.DataValue currentArea;
    private DoumiOptionBean.DataValue currentQuanzhi;

    /**
     * 单例模式
     *
     * @return
     */
    public static com.iruiyou.pet.fragment.MCClassFragment getInstance() {
        return new MCClassFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_class, null);
        ButterKnife.bind(this, view);
        findRecyclerView = view.findViewById(R.id.findRecyclerView);//精英推荐列表
        workplace_course_RecyclerView = view.findViewById(R.id.workplace_course_RecyclerView);//职场课程列表
        course_content_recyclerView = view.findViewById(R.id.course_content_RecyclerView);//课程内容列表
        resumeLFind = view.findViewById(R.id.resumeLFind);//精英推荐
        ll_workplace_course = view.findViewById(R.id.ll_workplace_course);//职场课程
        scrollView_workplace = view.findViewById(R.id.scrollView_workplace);
        refreshLayout_find = view.findViewById(R.id.refreshLayout_find);
        im_no_courses = view.findViewById(R.id.im_no_courses);
        hover = view.findViewById(R.id.hover);
        top_buy_layout = view.findViewById(R.id.top_buy_layout);
        parent_layout = view.findViewById(R.id.parent_layout);
        im_top = view.findViewById(R.id.im_top);
        reBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
//        ImmersionBar.with(this).statusBarDarkFont(true).init();

//        indexAdapter = new IndexAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(indexAdapter);
//        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_home_head, null);

        //处理scrollView与recycleview冲突，导致scrollView不置顶问题
//        scrollView_workplace.setOnScrollListener(this);
        //当布局的状态或者控件的可见性发生改变回调的接口
        parent_layout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(scrollView_workplace.getScrollY());

            }
        });
        scrollView_workplace.setScrollListener(im_top);//设置一键置顶图标

        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        findRecyclerView.setLayoutManager(linearLayoutManager);
        findRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        MyLinearLayoutManager linearLayoutManager2 = new MyLinearLayoutManager(getActivity());
        linearLayoutManager2.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        workplace_course_RecyclerView.setLayoutManager(linearLayoutManager2);
        workplace_course_RecyclerView.addItemDecoration(new SpacesItemDecoration(5));

        courseText = new Integer[]{R.string.all, R.string.course_category_5, R.string.course_category_6, R.string.course_category_7, R.string.course_category_8, R.string.course_category_9};
        courseIcon = new Integer[]{R.drawable.all_course,
                R.drawable.course_icon_5,
                R.drawable.course_icon_6,
                R.drawable.course_icon_7,
                R.drawable.course_icon_8,
                R.drawable.course_icon_9};
        for (int i = 0; i < courseText.length; i++) {
            courseList.add(new CourseBean(courseText[i], courseIcon[i]));
        }
        //职场课程
        workplaceCourseAdapter = new WorkplaceCourseAdapter(getActivity(), courseList);
        workplace_course_RecyclerView.setAdapter(workplaceCourseAdapter);
        workplaceCourseAdapter.notifyDataSetChanged();

        //课程列表
        courseContentAdapter = new CourseContentAdapter();
        course_content_recyclerView.setLayoutManager(new MyLinearLayoutManager(getActivity()));
        course_content_recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        course_content_recyclerView.setAdapter(courseContentAdapter);

        recycle_position.setPageNumber(1);
        positionListAdapter = new PositionListAdapter();
        recycle_position.setAdapter(positionListAdapter);
        positionListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DMOptionListBean.DataBean dataBean = (DMOptionListBean.DataBean)adapter.getItem(position);
                StartActivityManager.startPositionDetailActivity(getActivity(),dataBean);
//                Toast.makeText(getContext(),"---------"+position,Toast.LENGTH_SHORT).show();
            }
        });
        recycle_position.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycle_position.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL,
                Utils.dip2px(getContext(), 2), getResources().getColor(R.color._f1f1f1)));


        //设置 Footer 为 经典式样
        refreshLayout_position.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_position.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (shengfen != null && currentArea != null && currentQuanzhi != null) {
                    requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(), recycle_position.getPageNumber() + 1, false);
                }
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                recycle_position.setPageNumber(1);
                if (shengfen != null && currentArea != null && currentQuanzhi != null) {
                    requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(), recycle_position.getPageNumber(), false);
                }

            }
        });

        getNewDate(0, 0, true);
        getData();
        setListener();
    }

    //请求课程列表
    private void getNewDate(int num, int lastCounts, boolean isInit) {

        int categoryId = 0;
        if (num != 0) {
            categoryId = num + 4;
        }

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                boolean isSuccess = false;
                getCourseIntroBean = GsonUtils.parseJson(resulte, GetCourseIntroBean.class);
                if (getCourseIntroBean.getStatusCode() == Constant.SUCCESS) {
                    if (getCourseIntroBean.getData() != null) {
                        isSuccess = true;
                        if (lastCounts == 0) {
                            list.clear();
                        }
                        if (getCourseIntroBean != null && getCourseIntroBean.getData() != null)
                            lastCount += getCourseIntroBean.getData().size();//加载时累加请求到的返回数据长度
                        list.addAll(getCourseIntroBean.getData());
                        if (lastCounts == 0) {
                            courseContentAdapter.setNewData(list);
                        } else {
                            courseContentAdapter.notifyDataSetChanged();
                        }
                        if (list.size() > 0) {
                            course_content_recyclerView.setVisibility(View.VISIBLE);
                            im_no_courses.setVisibility(View.GONE);
                        } else {
                            course_content_recyclerView.setVisibility(View.GONE);
                            im_no_courses.setVisibility(View.VISIBLE);
                        }
                    }
//                    List<GetCourseIntroBean.DataBean> data = getCourseIntroBean.getData();
//                    courseContentAdapter.setNewData(data);

                } else {
                    T.showShort(recomendsBean.getMessage());
                    course_content_recyclerView.setVisibility(View.GONE);
                    im_no_courses.setVisibility(View.VISIBLE);

                }

                if (isInit || (lastCounts == 0)) {
                    refreshLayout_find.finishRefresh(isSuccess);
                } else {
                    refreshLayout_find.finishLoadMore(isSuccess);
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
                if (isInit || (lastCounts == 0)) {
                    refreshLayout_find.finishRefresh(false);
                } else {
                    refreshLayout_find.finishLoadMore(false);
                }
            }
        }, (BaseActivity) getContext()).getCourseIntro(categoryId, lastCount);

        String readLanguage = SharePreferenceUtils.getBaseSharePreference().readLanguage();
        if (readLanguage.equalsIgnoreCase(TagConstants.EN)) {//设置相应的中英文图片
            im_no_courses.setImageResource(R.drawable.im_no_courses_en);
        } else {
            im_no_courses.setImageResource(R.drawable.no_courses);
        }
    }

    /**
     * 监听
     */
    private void setListener() {

        //设置 Footer 为 经典式样
        refreshLayout_find.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_find.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData();
                lastCount = 0;
                getNewDate(selectorPosition, lastCount, false);
            }
        });
        //加载
        refreshLayout_find.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getNewDate(selectorPosition, lastCount, false);
            }
        });

        workplaceCourseAdapter.setItemClickListener(new WorkplaceCourseAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                selectCourseBean = workplaceCourseAdapter.getItem(position);
                //把点击的position传递到adapter里面去
                workplaceCourseAdapter.changeState(position);
                selectorPosition = position;
                lastCount = 0;
                getNewDate(position, 0, false);

//                Bundle bundle = new Bundle();
//                bundle.putInt("userid", recomendsBean.getData().get(position).getBasicInfo().getUserId());
//                bundle.putString("realName", recomendsBean.getData().get(position).getBasicInfo().getRealName());//get(position).getBasicInfo().getRealName()
//                Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
        //课程内容监听
        courseContentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetCourseIntroBean.DataBean data = (GetCourseIntroBean.DataBean) adapter.getItem(position);
                if (data != null) {
                    Intent intent = new Intent(getActivity(), CourseContentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("getCourseIntroBean", data);
                    intent.putExtras(bundle);
                    Objects.requireNonNull(getActivity()).startActivity(intent);
                }
            }
        });

    }

    private void getData() {
        String imToken = SharePreferenceUtils.getBaseSharePreference().readIMToken();
        RongIM.connect(imToken, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Log.d(TAG, "###onTokenIncorrect");
            }

            @Override
            public void onSuccess(String s) {
                Log.d(TAG, "###onSuccess---s" + s);
                //设置信息和uerid 匹配
                RongIM.getInstance().setMessageAttachedUserInfo(true);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d(TAG, "###onError--errorCode=" + errorCode);
            }
        });

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                recomendsBean = GsonUtils.parseJson(resulte, RecomendsBean.class);
                if (recomendsBean.getStatusCode() == Constant.SUCCESS) {
                    List<RecomendsBean.DataBean> data = recomendsBean.getData();
                    findElitesAdapter = new FindElitesAdapter(getActivity(), data);
                    findRecyclerView.setAdapter(findElitesAdapter);
                    findElitesAdapter.notifyDataSetChanged();


                    findElitesAdapter.setItemClickListener(new FindElitesAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("userid", recomendsBean.getData().get(position).getBasicInfo().getUserId());
                            bundle.putString("realName", recomendsBean.getData().get(position).getBasicInfo().getRealName());//get(position).getBasicInfo().getRealName()
                            Intent intent = new Intent(getActivity(), UserDetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                } else {
                    T.showShort(recomendsBean.getMessage());
                }
                refreshLayout_find.finishRefresh(true);//传入false表示刷新失败
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_find.finishRefresh(false);//传入false表示刷新失败
                T.showShort(e.getMessage());
            }
        }, (BaseActivity) getContext()).getRecomends();


        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if (StringUtil.isNotEmpty(resulte)) {
                    doumiOptionBean = GsonUtil.GsonToBean(resulte, DoumiOptionBean.class);
                    if (Constant.SUCCESS == doumiOptionBean.getStatusCode()) {

                        shengfen = doumiOptionBean.getData().getDomain().get(0);
                        spinner_position_area.setAdapter(new PositionSpinnerAdapter(getContext(), doumiOptionBean.getData().getDomain()));

                        currentArea = doumiOptionBean.getData().getJob_type().get(0);
                        spinner_position_class.setAdapter(new PositionSpinnerAdapter(getContext(), doumiOptionBean.getData().getJob_type()));

                        currentQuanzhi = doumiOptionBean.getData().getJob_date_type().get(0);
                        spinner_position_quanzhi.setAdapter(new PositionSpinnerAdapter(getContext(), doumiOptionBean.getData().getJob_date_type()));


                        requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(), recycle_position.getPageNumber(), true);
                    }
                }
                Log.e("test", "resulte is :" + resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, (BaseActivity) getActivity()).getPositionOptions();

    }

    private void requestPositionData(String domain, String job_type, int job_date_type, int page, boolean isInit) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if (StringUtil.isNotEmpty(resulte)) {
                    DMOptionListBean dmOptionListBean = GsonUtil.GsonToBean(resulte, DMOptionListBean.class);
                    if (Constant.SUCCESS == dmOptionListBean.getCode()) {
                        if (page == 1) {
                            positionListAdapter.setData(shengfen.getText(), currentQuanzhi.getText(), dmOptionListBean.getData().getData());
                            refreshLayout_position.finishRefresh(true);
                            if (isInit) {

                                spinner_position_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        shengfen = (DoumiOptionBean.DataValue) adapterView.getAdapter().getItem(i);
                                        recycle_position.setPageNumber(1);
                                        requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(),
                                                recycle_position.getPageNumber(), false);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                                spinner_position_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        currentArea = (DoumiOptionBean.DataValue) adapterView.getAdapter().getItem(i);
                                        recycle_position.setPageNumber(1);
                                        requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(),
                                                recycle_position.getPageNumber(), false);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                                spinner_position_quanzhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        currentQuanzhi = (DoumiOptionBean.DataValue) adapterView.getAdapter().getItem(i);
                                        recycle_position.setPageNumber(1);
                                        requestPositionData(shengfen.getValue(), currentArea.getValue(), Integer.valueOf(currentQuanzhi.getValue()).intValue(),
                                                recycle_position.getPageNumber(), false);
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {

                                    }
                                });

                            }
                        } else if (page > 1) {
                            positionListAdapter.addData(dmOptionListBean.getData().getData());
                            refreshLayout_position.finishLoadMore(true);
                            recycle_position.setPageNumber(page);
                        }
                        Log.e("test", "dmOptionListBean is :" + dmOptionListBean);
                    } else {
                        if (page == 1) {
                            refreshLayout_position.finishRefresh(false);
                        } else if (page > 1) {
                            refreshLayout_position.finishLoadMore(false);
                        }
                    }
                }
                Log.e("test", "resulte is :" + resulte);
            }

            @Override
            public void onError(ApiException e) {
                if (page == 1) {
                    refreshLayout_position.finishRefresh(false);
                } else if (page > 1) {
                    refreshLayout_position.finishLoadMore(false);
                }
            }
        }, (BaseActivity) getActivity()).getDoumiPositionList(domain, job_type, job_date_type, page);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.resumeLFind, R.id.text_class, R.id.text_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.resumeLFind:
                String readUserInfo = SharePreferenceUtils.getBaseSharePreference().readUserInfo();
                //json转成bean对象
                HomeRefreshBean.DataBean.UserInfoBean userInfoBean = GsonUtil.GsonToBean(readUserInfo, HomeRefreshBean.DataBean.UserInfoBean.class);
                Intent intent = new Intent(getContext(), CombatRankingActivity.class);
                if (recomendsBean.getData() != null) {
                    intent.putExtra(TagConstants.Combat, userInfoBean.getCombat());
                }
                startActivity(intent);
                break;
            case R.id.text_class:
                text_class.setAlpha(1);
                text_position.setAlpha(0.5f);

                linear_class.setVisibility(View.VISIBLE);
                linear_position.setVisibility(View.GONE);
                break;
            case R.id.text_position:
                text_class.setAlpha(0.5f);
                text_position.setAlpha(1);

                linear_class.setVisibility(View.GONE);
                linear_position.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();//刷新数据
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, hover.getTop());
        top_buy_layout.layout(0, mBuyLayout2ParentTop, top_buy_layout.getWidth(), mBuyLayout2ParentTop + top_buy_layout.getHeight());
    }
}
