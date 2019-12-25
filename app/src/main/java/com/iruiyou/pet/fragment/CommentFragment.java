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
import com.iruiyou.pet.activity.TextDetailsActivity;
import com.iruiyou.pet.adapter.CommentAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.utils.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

/**
 * 发现-正文-评论
 * Created by shenggaofei on 2018/10/29.
 */
public class CommentFragment extends BaseFragment {
    private static final String TAG = "CommentFragment";
    private static CommentFragment homeFragment = null;
    //    @BindView(R.id.gemstone)
//    ImageView gemstone;
    private CommentAdapter commentAdapter;
    private RecyclerView followRecyclerView;
    private List<String> list = new ArrayList<>();

    /**
     * 单例模式
     *
     * @return
     */
    public static CommentFragment getInstance() {
        return new CommentFragment();
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

        for (int i = 0; i < 20; i++) {
            list.add("pinglu" + i);
        }
        getData();
        commentAdapter = new CommentAdapter(getActivity(), list);
        FullyLinearLayoutManager fullyLinearLayoutManager = new FullyLinearLayoutManager(getActivity());
        followRecyclerView.setLayoutManager(fullyLinearLayoutManager);
        followRecyclerView.setAdapter(commentAdapter);
        fullyLinearLayoutManager.scrollToPositionWithOffset(0, 0);
        fullyLinearLayoutManager.setStackFromEnd(false);
        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
        commentAdapter.notifyDataSetChanged();

        setListener();
    }

    /**
     * 监听
     */
    private void setListener() {
        //单张图片监听
        commentAdapter.setItemClickListener(new CommentAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                T.showShort("" + position);
            }
        });

        commentAdapter.setOnTextViewClickListener(new CommentAdapter.OnTextViewClickListener() {
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
        }, (TextDetailsActivity) getContext()).homeRefresh();
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
        getData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
