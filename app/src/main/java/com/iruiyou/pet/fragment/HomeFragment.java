package com.iruiyou.pet.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.ReleaseActivity;
import com.iruiyou.pet.activity.UserDetailsActivity;
import com.iruiyou.pet.activity.utils.SpacesItemDecoration;
import com.iruiyou.pet.adapter.FindElitesAdapter;
import com.iruiyou.pet.adapter.MyPagerAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.RecomendsBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.WrapContentHeightViewPager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

//import com.androidkun.xtablayout.XTabLayout;

/**
 * 首页(发现)
 * Created by shenggaofei on 2018/10/29.
 */
public class HomeFragment extends BaseFragment {
    private static final String TAG = "HomeFragment";
    private static HomeFragment homeFragment = null;
    @BindView(R.id.im_add_find)
    ImageView im_add_find;

    private SmartRefreshLayout refreshLayout_find;
    private RecyclerView findRecyclerView;
//    private XTabLayout tab_order;
    private WrapContentHeightViewPager vpOrderList;
    private List<Fragment> fragments;
    private List<String> tabNames;
    private List<String> list;
    private FollowFragment followFragment;
    private HotFragment hotFragment;
    private NewestFragment newestFragment;
    private Bundle followBundle;
    private Bundle hotBundle;
    private Bundle newestBundle;
    private MyPagerAdapter pagerAdapter;
    private FindElitesAdapter findElitesAdapter;
    private RecomendsBean recomendsBean;
    private ScrollView scrollView_home;

    /**
     * 单例模式
     *
     * @return
     */
    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        findRecyclerView = view.findViewById(R.id.findRecyclerView);
        scrollView_home = view.findViewById(R.id.scrollView_home);
        refreshLayout_find = view.findViewById(R.id.refreshLayout_find);
        vpOrderList = view.findViewById(R.id.vp_find);
//        tab_order = view.findViewById(R.id.tab_order);

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
        scrollView_home.smoothScrollTo(0, 20);
        fragments = new ArrayList<>();
        tabNames = new ArrayList<>();

        tabNames.add(getResources().getString(R.string.newest));
        tabNames.add(getResources().getString(R.string.hot));
        tabNames.add(getResources().getString(R.string.follow));

//        followFragment = new FollowFragment();
//        followBundle = new Bundle();
//        followBundle.putString("Status", "1");//关注
//        followFragment.setArguments(followBundle);
//        fragments.add(followFragment);
//
//        hotFragment = new HotFragment();
//        hotBundle = new Bundle();
//        hotBundle.putString("Status", "2");//热门
//        hotFragment.setArguments(hotBundle);
//        fragments.add(hotFragment);
//
//        newestFragment = new NewestFragment();
//        newestBundle = new Bundle();
//        newestBundle.putString("Status", "3");//最新
//        newestFragment.setArguments(newestBundle);
//        fragments.add(newestFragment);

        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new NewestFragment();
                        break;
                    case 1:
                        fragment = new HotFragment();
                        break;
                    case 2:
                        fragment = new FollowFragment();
                        break;
                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabNames.get(position);
            }
        };

//        pagerAdapter = new MyPagerAdapter(getChildFragmentManager(), fragments, tabNames);
        vpOrderList.setAdapter(fragmentPagerAdapter);
//        tab_order.setupWithViewPager(vpOrderList);
//        vpOrderList.setCurrentItem(0);

        //设置布局管理器
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(MyLinearLayoutManager.HORIZONTAL);
        findRecyclerView.setLayoutManager(linearLayoutManager);
        findRecyclerView.addItemDecoration(new SpacesItemDecoration(5));

//        findElitesAdapter.setItemClickListener(new FindElitesAdapter.MyItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                T.showShort("" + position);
//            }
//        });
        getData();
        getRefresh();
        setListener();
    }

    /**
     * 刷新
     */
    private void getRefresh() {
        //设置 Footer 为 经典式样
        refreshLayout_find.setRefreshFooter(new ClassicsFooter(Objects.requireNonNull(getActivity())).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新操作
        refreshLayout_find.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(true);//传入false表示刷新失败
//                getData();
            }
        });
        //加载
        refreshLayout_find.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadmore(true);//传入false表示加载失败
            }
        });
    }

    /**
     * 监听
     */
    private void setListener() {


//        tab_order.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(XTabLayout.Tab tab) {
//                String text = (String) tab.getText();
//                if(Objects.requireNonNull(text).equals(getString(R.string.newest))){
//                    vpOrderList.setCurrentItem(0);
//                }else if(text.equals(getString(R.string.hot))){
//                    vpOrderList.setCurrentItem(1);
//                }else if(text.equals(getString(R.string.follow))){
//                    vpOrderList.setCurrentItem(2);
//                }
//            }
//
//            @Override
//            public void onTabUnselected(XTabLayout.Tab tab) {
//                String text = (String) tab.getText();
//            }
//
//            @Override
//            public void onTabReselected(XTabLayout.Tab tab) {
//                String text = (String) tab.getText();
//            }
//        });

        vpOrderList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        vpOrderList.setCurrentItem(0);
                        break;
                    case 1:
                        vpOrderList.setCurrentItem(1);
                        break;
                    case 2:
                        vpOrderList.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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

            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getRecomends();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.im_add_find})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_add_find:
//                startActivity(ReleaseActivity.class);
                Intent intent = new Intent(getActivity(), ReleaseActivity.class);
                startActivity(intent);
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
}
