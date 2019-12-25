package com.iruiyou.pet.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.fragment.CommentFragment;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MultiImageView;
import com.iruiyou.pet.utils.PageNameConstant;
import com.iruiyou.pet.utils.SoftKeyboardUtils;
import com.iruiyou.pet.utils.WrapContentHeightViewPager;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发现-发布-正文详情
 * 作者：sgf
 *
 */
public class TextDetailsActivity extends BaseActivity {
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
//    @BindView(R.id.titleview)
//    RelativeLayout titleview;

    @BindView(R.id.im_details_head)
    ImageView im_details_head;
    @BindView(R.id.tv_details_name)
    TextView tv_details_name;
    @BindView(R.id.tv_details_describe)
    TextView tv_details_describe;
    @BindView(R.id.tv_details_content)
    TextView tv_details_content;
//    @BindView(R.id.im_details_pic)
//    ImageView im_details_pic;
    @BindView(R.id.gridView_pic)
MultiImageView gridView_pic;
    @BindView(R.id.vp_details)
    WrapContentHeightViewPager vp_details;
    @BindView(R.id.ll_comment)
    LinearLayout ll_comment;
    @BindView(R.id.ll_fabulous)
    LinearLayout ll_fabulous;
    @BindView(R.id.ll_find_comment_title)
    LinearLayout ll_find_comment_title;//评论标题栏
    @BindView(R.id.tv_find_comment_title)
    TextView tv_find_comment_title;//评论标题名称
    @BindView(R.id.tv_find_comment_num)
    TextView tv_find_comment_num;//评论数量
    @BindView(R.id.tv_find_comment_one)
    TextView tv_find_comment_one;//评论的游标
    @BindView(R.id.scrollView_text)
    NestedScrollView scrollView_text;
    private PopupWindow mPopWindow;
    private ArrayList<GetEssaysBean.DataBean> data; //图片的数据源
    private int mPosition; //第几张图片
    private Context context;

    @Override
    public int getLayout() {
        return R.layout.activity_text_details;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        setTitle(getResources().getString(R.string.text));
        context = TextDetailsActivity.this;
        titleNameText.setText(getString(R.string.text));
        initDta();
    }

    private void initDta() {

        data = (ArrayList<GetEssaysBean.DataBean>) getIntent().getSerializableExtra("data");
        mPosition = getIntent().getIntExtra("pic", 0);
        //处理scrollView与recycleview冲突，导致scrollView不置顶问题
        scrollView_text.smoothScrollTo(0,20);
        GetEssaysBean.DataBean.BasicInfoBean basicInfo = data.get(mPosition).getBasicInfo();
        List<GetEssaysBean.DataBean.ImagesBean> images = data.get(mPosition).getImages();
        tv_details_name.setText(basicInfo.getRealName());
        String position_user = basicInfo.getPosition();
        String company = basicInfo.getCompany();
        if (TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
            tv_details_describe.setText(position_user);
        } else if (TextUtils.isEmpty(position_user) && !TextUtils.isEmpty(company)) {
            tv_details_describe.setText(company);
        } else if (!TextUtils.isEmpty(company) && !TextUtils.isEmpty(position_user)) {
            tv_details_describe.setText(company + "\t\t" + position_user);
        }
        tv_details_content.setText(data.get(mPosition).getContent());
        GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), im_details_head);
        if(images!=null) {
            if (images.size() == 0) {
                gridView_pic.setVisibility(View.GONE);
            } else {
                gridView_pic.setList(images);
            }
        }
        //查看图片
        gridView_pic.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, String position ,int po) {
                Intent intent = new Intent(context, SaveImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) data.get(mPosition).getImages());
                bundle.putInt("pic", 0);//暂时传零，点击都从一显示，此po是最后的数量，不是点击对应的条目，还需研究
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //给ViewPager设置适配器
        vp_details.setAdapter(new FragmentPagerAdapter(
                getSupportFragmentManager()) {

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return 1;
            }

            @Override
            public Fragment getItem(int arg0) {
                //根据ViewPager 索引 返回相应Fragment
                Fragment fragment = null;
                switch (arg0) {
                    case 0:
                        fragment = new CommentFragment();
                        break;
//                    case 1:
//                        break;
//                    case 2:
//                        break;
                    default:
                        break;
                }
                return fragment;
            }
        });

        //给ViewPager设置滑动监听
        vp_details.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                //根据position改变游标状态
                switch (position) {
                    case 0:
                        tv_find_comment_one.setVisibility(View.VISIBLE);//显示游标
//                        tv_find_comment_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);//设置字体大小
//                        tv_find_comment_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    @OnClick({R.id.ll_fabulous, R.id.ll_comment , R.id.ll_title_left_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_fabulous://点赞
                T.showShort("点赞");
                break;
            case R.id.ll_comment://评论
                showPopupWindow();
                break;
            case R.id.ll_find_comment_title://评论标题栏
                vp_details.setCurrentItem(0);
                break;
            case R.id.ll_title_left_view:
                if(SoftKeyboardUtils.isSoftShowing(TextDetailsActivity.this)){//若键盘显示则隐藏键盘
                    SoftKeyboardUtils.hideSoftKeyboard(TextDetailsActivity.this);
                }
                if(mPopWindow!=null){
                    mPopWindow.dismiss();
                }
                finish();
                break;
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

    /**
     * 评论弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(TextDetailsActivity.this).inflate(R.layout.comment_edittext_dialoglayout, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //防止PopupWindow被软件盘挡住（可能只要下面一句，可能需要这两句）
//        mPopWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // pop.dismiss(）方法调用时，回调该函数，点击外部时，也会回调该函数
                //解决键盘与PopupWindow的冲突，导致输入框dismiss后键盘不隐藏
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        });
        //设置软键盘弹出
        InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Objects.requireNonNull(inputMethodManager).toggleSoftInput(1000, InputMethodManager.HIDE_NOT_ALWAYS);//这里给它设置了弹出的时间
        //设置各个控件的点击响应
        final EditText editText = contentView.findViewById(R.id.et_comment);
        TextView tv_send = contentView.findViewById(R.id.tv_send);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputString = editText.getText().toString();
                if(editText.getText().length()>0){
                    T.showShort(inputString);
                    mPopWindow.dismiss();//让PopupWindow消失
                    SoftKeyboardUtils.hideSoftKeyboard(TextDetailsActivity.this);
                }else {
                    T.showShort("请输入评论内容");
                }
            }
        });
        //是否具有获取焦点的能力
        mPopWindow.setFocusable(true);
        //显示PopupWindow
        View rootview = LayoutInflater.from(TextDetailsActivity.this).inflate(R.layout.activity_text_details, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 获取评论
     */
    private void getData() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
//                GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                //                    if (getEssaysBean.getData() != null) {
                //                        findFollowAdapter = new FindFollowAdapter(getActivity(), getEssaysBean.getData());
                //                        followRecyclerView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
                //                        followRecyclerView.setAdapter(findFollowAdapter);
                //                        followRecyclerView.setNestedScrollingEnabled(false);//禁止滑动
                //                        findFollowAdapter.notifyDataSetChanged();
                //                        setListener(getEssaysBean);
                //                    }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).getEssays(2,0,0,0);//type：0我的；1最新；2热门；3关注
    }
}
