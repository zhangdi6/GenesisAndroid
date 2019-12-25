package com.iruiyou.pet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baijiayun.videoplayer.util.Utils;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.DataUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.adapter.EssayCommentAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.EssayCommonBean;
import com.iruiyou.pet.bean.EssayDetailBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.MaxRecyclerView;
import com.iruiyou.pet.utils.MyLinearLayoutManager;
import com.iruiyou.pet.utils.SoftKeyBoardListener;
import com.iruiyou.pet.utils.StringUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.iruiyou.pet.utils.SoftKeyBoardListener;

/**
 * 带长文的微博详情页面
 */
public class EssayDetailActivity extends BaseActivity {

    private GetEssaysBean.DataBean dataBean;

    @BindView(R.id.text_content)
    TextView text_content;

    @BindView(R.id.text_essay_title)
    TextView text_essay_title;

    @BindView(R.id.text_author)
    TextView text_author;

    @BindView(R.id.text_data_source)
    TextView text_data_source;

    @BindView(R.id.text_date)
    TextView text_date;

    @BindView(R.id.text_watch_count)
    TextView text_watch_count;

    @BindView(R.id.recycle_comments)
    MaxRecyclerView recyclerView;

    @BindView(R.id.refreshLayout_find)
    SmartRefreshLayout refreshLayout_find;

    @BindView(R.id.linear_comment)
    LinearLayout linear_comment;

    @BindView(R.id.linear_bottom_area)
    LinearLayout linear_bottom_area;

    @BindView(R.id.edit_comment)
    EditText edit_comment;

    @BindView(R.id.image_zan)
    ImageView image_zan;

    private EssayCommentAdapter essayCommentAdapter;

    private boolean isDianZan = false;

    private long lastCommentTime;

    @Override
    public int getLayout() {
        return R.layout.activity_essay_detail;
    }

    boolean isAction = false;

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ButterKnife.bind(this);
        initIntentData();
        if (dataBean != null) {
            text_essay_title.setText(dataBean.getReferenceContent());
//            text_content.setText(Html.fromHtml(dataBean.getReferenceContent()));
        }
        getEssayContent();
        SoftKeyBoardListener.setListener(EssayDetailActivity.this, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                Log.e("test","keyBoardShow height is "+height);
                if(linear_comment.getVisibility()==View.VISIBLE&&edit_comment.isFocusable()){
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linear_bottom_area.getLayoutParams();
                    layoutParams.height =height + Utils.dip2px(EssayDetailActivity.this,203);
                    linear_bottom_area.setLayoutParams(layoutParams);
                    isAction = true;
                }
            }

            @Override
            public void keyBoardHide(int height) {
                Log.e("test","keyBoardHide height is "+height);
                if(isAction){
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linear_bottom_area.getLayoutParams();
                    layoutParams.height =layoutParams.height - height;
                    linear_bottom_area.setLayoutParams(layoutParams);
                    isAction = false;
                }
            }
        });
    }

    @OnClick(value = {R.id.image_comment, R.id.text_publish_comment, R.id.image_zan})
    public void viewOnClick(View view){
        int id =view.getId();
        switch (id){
            case R.id.image_comment:
                if(linear_comment.getVisibility()==View.VISIBLE){
                    linear_comment.setVisibility(View.GONE);
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linear_bottom_area.getLayoutParams();
                    layoutParams.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                    linear_bottom_area.setLayoutParams(layoutParams);
                    linear_bottom_area.invalidate();
                } else {
                    linear_comment.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.text_publish_comment:
                publishComment();
                break;
            case R.id.image_zan:
                myfabulous(!isDianZan);
                break;
        }
    }


    /**
     * 初始化界面传递参数
     */
    private void initIntentData() {
        Intent intent = getIntent();
        dataBean = (GetEssaysBean.DataBean) intent.getSerializableExtra("GetEssaysBean.DataBean");
        recyclerView.setLayoutManager(new MyLinearLayoutManager(EssayDetailActivity.this));
        essayCommentAdapter = new EssayCommentAdapter(EssayDetailActivity.this);
        recyclerView.setAdapter(essayCommentAdapter);
        refreshLayout_find.setEnableRefresh(false);
        refreshLayout_find.setEnableLoadMore(true);
        refreshLayout_find.setOnLoadMoreListener((refreshLayout) -> {
            loadMoreComment(false);
        });
    }

    private void getEssayContent() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("test", "resulte is " + resulte);
                EssayDetailBean essayDetailBean = GsonUtils.parseJson(resulte, EssayDetailBean.class);
                if (essayDetailBean != null) {
                    if (essayDetailBean.getStatusCode() == Constant.SUCCESS) {
                        EssayDetailBean.EssayInfo essayInfo = essayDetailBean.getData().getEs();
                        List<EssayCommonBean> commonBeanList = essayDetailBean.getData().getComments();
                        text_content.setText(Html.fromHtml(essayInfo.getHtmlContent()));
                        text_author.setText("作者: " + essayInfo.getBasicInfo().getRealName());
                        text_data_source.setText("来源: " + essayInfo.getDataSource());
                        String date= DataUtils.getStringDate(essayInfo.getTime()).split(" ")[1];
                        text_date.setText(date);
                        text_watch_count.setText(essayInfo.getReadCount() + "");
                        if (commonBeanList != null && (commonBeanList.size() > 0)) {
                            lastCommentTime = commonBeanList.get(commonBeanList.size() - 1).getTime();
                            essayCommentAdapter.setNewData(commonBeanList);
                        }

                        setImage_zanStatue(essayDetailBean.getData().getIsFabulous());
                    }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, EssayDetailActivity.this).getEssayByIdNew(dataBean.getReferenceId() + "");
    }

    /**
     * 设置点赞按钮状态
     * @param statue 1-点赞 0-取消点赞
     */
    private void setImage_zanStatue(int statue){
        if(statue==1){
            isDianZan = false;
            image_zan.setBackground(getResources().getDrawable(R.drawable.bg_red_round));
        }else{
            isDianZan = true;
            image_zan.setBackground(getResources().getDrawable(R.drawable.bg_gray_round));
        }
    }

    /**
     * 发表评论
     */
    private void publishComment(){
        String comment = edit_comment.getText().toString().trim();
        if(StringUtil.isEmpty(comment)){
            Toast.makeText(EssayDetailActivity.this,"请输入评论内容",Toast.LENGTH_SHORT).show();
            return;
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject = new JSONObject(resulte);
                        String message = jsonObject.optString("message");
                        if(jsonObject.optInt("statusCode")==0){
//                            EssayCommonBean essayCommonBean = GsonUtil.
//                                    GsonToBean(jsonObject.optString("data"),EssayCommonBean.class);
                            linear_comment.setVisibility(View.GONE);
                            loadMoreComment(true);
//                            essayCommentAdapter.addData(essayCommonBean);
                        } else if(StringUtil.isNotEmpty(message)){
                            Toast.makeText(EssayDetailActivity.this,message,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Log.e("test","resulte is "+resulte);
            }

            @Override
            public void onError(ApiException e) {

            }
        }, EssayDetailActivity.this).comment(dataBean.getReferenceId(),comment);
    }

    private void loadMoreComment(boolean isClearData){
        if(isClearData){
            lastCommentTime =0;
        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject =new JSONObject(resulte);
                        String message = jsonObject.optString("message");
                        int statusCode = jsonObject.optInt("statusCode");
                        if(0==statusCode){
                            String array = jsonObject.getJSONObject("data").getString("comments");
                            List<EssayCommonBean> listData = GsonUtil.jsonToList(array, EssayCommonBean.class);
                            if(listData!=null&&listData.size()>0){
                                EssayCommonBean essayCommonBean = listData.get(listData.size()-1);
                                lastCommentTime=essayCommonBean.getTime();
                            }
                            if(isClearData){
                                essayCommentAdapter.getData().clear();
                            }
                            essayCommentAdapter.addData(listData);
                        } else if(StringUtil.isNotEmpty(message)){
                            Toast.makeText(EssayDetailActivity.this,message,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                refreshLayout_find.finishLoadMore();
            }

            @Override
            public void onError(ApiException e) {
                refreshLayout_find.finishLoadMore(false);
            }
        }, EssayDetailActivity.this).getCommentByPageNew(dataBean.getReferenceId()+"",lastCommentTime);
    }

    /**
     * 点赞or取消点赞
     * @param isCancel
     */
    private void myfabulous(boolean isCancel){
        int fabulousOrDelete = 1;
        if(isCancel){
            fabulousOrDelete = 0;
        }

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                if(StringUtil.isNotEmpty(resulte)){
                    try {
                        JSONObject jsonObject =new JSONObject(resulte);
                        int statusCode = jsonObject.optInt("statusCode");
                        String message = jsonObject.optString("message");
                        if(statusCode == Constant.SUCCESS){
                            int status =0;
                            if(isCancel){
                                status = 0;
                            }else{
                                status =1;
                            }
                            setImage_zanStatue(status);
                        } else if(StringUtil.isNotEmpty(message)){
                            Toast.makeText(EssayDetailActivity.this,message,Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, EssayDetailActivity.this).myfabulous(dataBean.getReferenceId()+"",dataBean.getBasicInfo().get_id(),0,"0","0",fabulousOrDelete);
    }


}
