package com.iruiyou.pet.fragment;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.adapter.FindFollowAdapter;
import com.iruiyou.pet.base.BaseFragment;

import java.util.Objects;

import butterknife.ButterKnife;

/**
 * 发现-最新
 * Created by shenggaofei on 2018/10/29.
 */
public class NewestFragment extends BaseFragment {
    private static final String TAG = "NewestFragment";
    private static NewestFragment homeFragment = null;
    //    @BindView(R.id.gemstone)
//    ImageView gemstone;
    private FindFollowAdapter findFollowAdapter;
    private RecyclerView followRecyclerView;
//    private List<String> list = new ArrayList<>();

    /**
     * 单例模式
     *
     * @return
     */
    public static NewestFragment getInstance() {
        return new NewestFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, null);
        followRecyclerView = view.findViewById(R.id.followRecyclerView);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
//        ImmersionBar.with(this).statusBarDarkFont(true).init();

//        indexAdapter = new IndexAdapter();
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(indexAdapter);
//        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.view_home_head, null);
        ButterKnife.bind(Objects.requireNonNull(getActivity()));
//        for (int i = 0; i < 6; i++) {
//            list.add("最新的" + i);
//        }
        getData();
//        findFollowAdapter = new FindFollowAdapter(getActivity(), list);
//        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity());
//        followRecyclerView.setLayoutManager(fullyLinearLayoutManager);
//        followRecyclerView.setAdapter(findFollowAdapter);
//        fullyLinearLayoutManager.scrollToPositionWithOffset(0, 0);
//        fullyLinearLayoutManager.setStackFromEnd(false);
//        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
//        findFollowAdapter.notifyDataSetChanged();

//        setListener();
    }

    /**
     * 监听
     */
    private void setListener() {
        //单张图片监听
        findFollowAdapter.setItemClickListener(new FindFollowAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                T.showShort("" + position);
            }
        });

        findFollowAdapter.setOnTextViewClickListener(new FindFollowAdapter.OnTextViewClickListener() {
            @Override
            public void onTextViewClick(int position) {
                T.showShort("12" + position);
            }
        });
    }

    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
//                homeRefreshBean = GsonUtils.parseJson(resulte, HomeRefreshBean.class);
//                if (homeRefreshBean.getStatusCode() == Constant.SUCCESS) {
//                    if (homeRefreshBean.getData().getBasicInfo() != null) {
//
//                    }
//                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getEssays(1,0,0,0);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    @OnClick({R.id.rl, R.id.wallet})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.myWalletView:
//                startActivity(MyWalletActivity.class);
//                break;
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();//刷新数据
        followRecyclerView.setFocusable(false);
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
