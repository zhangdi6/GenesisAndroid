package com.iruiyou.pet.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.PbsIncubation2Adapter;
import com.iruiyou.pet.adapter.PbsIncubationAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.DCProductListBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.PageNameConstant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页-我的钱包-PBS孵化
 * 作者：sgf
 *
 */
public class PbsIncubationActivity extends BaseActivity {
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
    @BindView(R.id.refreshLayout_pbs_incubation)
    RefreshLayout refreshLayout_pbs_incubation;
    @BindView(R.id.pbs_incubation_RecyclerView)
    RecyclerView pbs_incubation_RecyclerView;
    private PbsIncubationAdapter pbsIncubationAdapter;
    private Context context;
    private PbsIncubation2Adapter pbsIncubation2Adapter;
    private List<DCProductListBean.DCProductBean> productBeans;
    @Override
    public int getLayout() {
        return R.layout.activity_pbs_incubation;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.pbs_hatch));
        context = PbsIncubationActivity.this;
        initDta();
        getRefresh();
        getDCProductList();
    }

    private void initDta() {

       List<DCProductListBean.DCProductBean> list = new ArrayList<>();

        //Pbs孵化适配器
//        pbsIncubationAdapter = new PbsIncubationAdapter();
//        pbs_incubation_RecyclerView.setNestedScrollingEnabled(false);
//        pbs_incubation_RecyclerView.setLayoutManager(new LinearLayoutManager(PbsIncubationActivity.this));
//        pbs_incubation_RecyclerView.setAdapter(pbsIncubationAdapter);
//
//        pbsIncubationAdapter.setNewData(list);

        //Pbs孵化适配器
        pbsIncubation2Adapter = new PbsIncubation2Adapter(context, list);
        pbs_incubation_RecyclerView.setNestedScrollingEnabled(false);
        pbs_incubation_RecyclerView.setLayoutManager(new LinearLayoutManager(PbsIncubationActivity.this));
        pbs_incubation_RecyclerView.setAdapter(pbsIncubation2Adapter);
        pbsIncubation2Adapter.notifyDataSetChanged();

        //群成员监听
        pbsIncubation2Adapter.setItemClickListener(new PbsIncubation2Adapter.MyItemClickListener() {
            @Override
            public void onItemClick(int position) {
                DCProductListBean.DCProductBean productBean = productBeans.get(position);
                    String str = "deposit/buy?target="+productBean.getTarget()+"&type="+productBean.getType()+"&currency="+productBean.getCurrency();
                    StartActivityManager.startOrdinaryDepositTreasureActivity(PbsIncubationActivity.this,"PBS定存",productBean.getType(),productBean.getDayRate(),productBean.getPublishTime());
//                StartActivityManager.startWebViewNewActivity(PbsIncubationActivity.this,getString(R.string.pbs_hatch2), BaseApi.baseUrlNoApi + str);

//                Bundle bundle = new Bundle();
//                bundle.putInt("userid", companyMembersBean.getData().get(position).getUserId());
//                bundle.putString("realName", companyMembersBean.getData().get(position).getRealName());//get(position).getBasicInfo().getRealName()
//                Intent intent = new Intent(context, UserDetailsActivity.class);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
//        LayoutAnimationController controller = MyLayoutAnimationHelper.makeLayoutAnimationController();//添加item动画
//        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.refreshLayout_pbs_incubation);
//        viewGroup.setLayoutAnimation(controller);
//        viewGroup.scheduleLayoutAnimation();
//        playLayoutAnimation(MyLayoutAnimationHelper.getAnimationSetFromRight());
    }


    /**
     * 刷新
     */
    private void getRefresh() {
        //刷新操作
        refreshLayout_pbs_incubation.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
//                num = 0;
//                requestRecommendGroups(num);
                getDCProductList();
            }
        });
//        refreshLayout_pbs_incubation.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshLayout) {
////                ++num;
////                num = num-1;
////                requestRecommendGroups(num);
//            }
//        });
    }
    ///----------  王新亚 修改 ----end----------------
    /**
     * 获取定存产品
     * */
    private void getDCProductList() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                DCProductListBean bean = GsonUtils.parseJson(resulte, DCProductListBean.class);
                if (bean.getStatusCode() == Constant.SUCCESS) {
                    productBeans = bean.getData();
                    pbsIncubation2Adapter.refreshData(bean.getData());
                }
                refreshLayout_pbs_incubation.finishRefresh(true);
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_pbs_incubation.finishRefresh(false);
            }
        }, this).getDepositGoods();

    }


    //    @OnClick({R.id.llOfficial, R.id.llPraise})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.llOfficial:
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.official_website),"http://pbase.io");
//                break;
//            case R.id.llPraise:
//                StartActivityManager.startWebViewNewActivity(this,getString(R.string.praise),"http://pbase.io");
//                break;
//        }
//    }
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
    /**
     * 播放RecyclerView动画
     *
     * @param animation
     * @param isReverse
     */
    public void playLayoutAnimation(Animation animation, boolean isReverse) {
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.1f);
        controller.setOrder(isReverse ? LayoutAnimationController.ORDER_REVERSE : LayoutAnimationController.ORDER_NORMAL);

        pbs_incubation_RecyclerView.setLayoutAnimation(controller);
        pbsIncubation2Adapter.notifyDataSetChanged();
        pbs_incubation_RecyclerView.scheduleLayoutAnimation();
    }

    public void playLayoutAnimation(Animation animation) {
        playLayoutAnimation(animation, false);
    }
}
