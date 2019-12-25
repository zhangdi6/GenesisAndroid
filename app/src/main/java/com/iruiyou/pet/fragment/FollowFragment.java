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
import com.iruiyou.pet.adapter.FindFollowAdapter;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.FullyLinearLayoutManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

/**
 * 发现-关注
 * Created by shenggaofei on 2018/10/29.
 */
public class FollowFragment extends BaseFragment {
    private static final String TAG = "FollowFragment";
    private static FollowFragment homeFragment = null;
    //    @BindView(R.id.gemstone)
//    ImageView gemstone;
    private FindFollowAdapter findFollowAdapter;
    private RecyclerView followRecyclerView;
    private List<String> list = new ArrayList<>();
    private ArrayList<String> lists = new ArrayList<>();
    private GetEssaysBean getEssaysBean;

    /**
     * 单例模式
     *
     * @return
     */
    public static FollowFragment getInstance() {
        return new FollowFragment();
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
        followRecyclerView.setNestedScrollingEnabled(false);
        getData();
//        findFollowAdapter = new FindFollowAdapter(getActivity(), getEssaysBean.getData());
//        followRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
//        followRecyclerView.setAdapter(findFollowAdapter);
//        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
//        findFollowAdapter.notifyDataSetChanged();
//        setListener();
    }

    /**
     * 监听
     */
    private void setListener(GetEssaysBean getEssaysBean) {
        //列表监听
        findFollowAdapter.setItemClickListener(new FindFollowAdapter.MyItemClickListener() {
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

        //一张图片
//        findFollowAdapter.setOnTextViewClickListener(new FindFollowAdapter.OnTextViewClickListener() {
//            @Override
//            public void onTextViewClick(int position) {
//                T.showShort("12"+position);
//                //弹出的“保存图片”的Dialog
////                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
////                builder.setItems(new String[]{"保存"}, new DialogInterface.OnClickListener() {
////                    @Override
////                    public void onClick(DialogInterface dialog, int which) {
////
////                    }
////                });
////                builder.show();
//                Intent intent = new Intent(getActivity(), SaveImageActivity.class);
////                List<GetEssaysBean.DataBean.ImagesBean> images = getEssaysBean.getData().get(position).getImages();
//                List<GetEssaysBean.DataBean> data = getEssaysBean.getData();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) data.get(position).getImages());
////                intent.putStringArrayListExtra("imglist", getEssaysBean);
//                bundle.putInt("pic", position);
//                intent.putExtras(bundle);
//                startActivity(intent);
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
                        findFollowAdapter = new FindFollowAdapter(getActivity(), getEssaysBean.getData());
                        followRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                        followRecyclerView.setAdapter(findFollowAdapter);
                        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
                        findFollowAdapter.notifyDataSetChanged();
                        setListener(getEssaysBean);
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, (MainActivity) getContext()).getEssays(2,0,0,0);
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
