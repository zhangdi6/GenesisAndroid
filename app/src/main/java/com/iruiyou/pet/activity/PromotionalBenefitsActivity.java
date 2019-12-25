package com.iruiyou.pet.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 首页-我的钱包-推广收益
 * 作者：sgf
 *
 */
public class PromotionalBenefitsActivity extends BaseActivity {
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
    @BindView(R.id.tv_number_of_nodes)
    TextView tv_number_of_nodes;
    @BindView(R.id.tv_total_assets_calculated)
    TextView tv_total_assets_calculated;
    @BindView(R.id.tv_my_node)
    TextView tv_my_node;
    @BindView(R.id.bt_view_hatch_nodes)
    Button bt_view_hatch_nodes;
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_promotional_benefits;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.promotional_benefits));
        context = PromotionalBenefitsActivity.this;
        initDta();
        getRefresh();
    }

    private void initDta() {
    }

    /**
     * 刷新
     */
    private void getRefresh() {
    }

    @OnClick({R.id.bt_view_hatch_nodes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_view_hatch_nodes:
                T.showShort("12");
                break;
//            case R.id.llPraise:
//                break;
        }
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
