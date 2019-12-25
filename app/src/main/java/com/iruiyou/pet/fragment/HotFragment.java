package com.iruiyou.pet.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.MainActivity;
import com.iruiyou.pet.activity.TextDetailsActivity;
import com.iruiyou.pet.adapter.FindHotAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.FullyLinearLayoutManager;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

/**
 * 发现-热门
 * Created by shenggaofei on 2018/10/29.
 */
public class HotFragment extends BaseFragment {
    private static final String TAG = "HotFragment";
    private static HotFragment homeFragment = null;
    //    @BindView(R.id.gemstone)
//    ImageView gemstone;
    private FindHotAdapter findHotAdapter;
//    private MaxRecyclerView followRecyclerView;
    private RecyclerView followRecyclerView;
    private GetEssaysBean getEssaysBean;

    /**
     * 单例模式
     *
     * @return
     */
    public static HotFragment getInstance() {
        return new HotFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_follow, null);
        followRecyclerView = view.findViewById(R.id.followRecyclerView);
        return view;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(Objects.requireNonNull(getActivity()));

        getData();
    }

    /**
     * 监听
     */
    private void setListener(GetEssaysBean getEssaysBean) {
        //列表监听
        findHotAdapter.setItemClickListener(new FindHotAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), TextDetailsActivity.class);
                List<GetEssaysBean.DataBean> data = getEssaysBean.getData();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data", (Serializable) data);
                bundle.putInt("pic", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

//        findFollowAdapter.setOnTextViewClickListener(new FindFollowAdapter.OnTextViewClickListener() {
//            @Override
//            public void onTextViewClick(int position) {
//                T.showShort("12" + position);
//            }
//        });
    }

    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                if (getEssaysBean.getStatusCode() == Constant.SUCCESS) {
                    if (getEssaysBean.getData() != null) {
                        findHotAdapter = new FindHotAdapter(getActivity(), getEssaysBean.getData());
                        followRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                        followRecyclerView.setAdapter(findHotAdapter);
                        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
                        findHotAdapter.notifyDataSetChanged();
                        setListener(getEssaysBean);
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getEssays(2,0,0,0);//type：0我的；1最新；2热门；3关注
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
