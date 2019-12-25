package com.iruiyou.pet.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.DataUtils;
import com.iruiyou.pet.activity.utils.GsonUtil;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.adapter.HotItemComentAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.GetEssaysBean2;
import com.iruiyou.pet.utils.MultiImageView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyHotItemActivity extends BaseActivity {




    @BindView(R.id.tv_find_name)
    TextView tv_find_name;

    @BindView(R.id.im_find_head)
    ImageView im_find_head;

    @BindView(R.id.text_work)
    TextView tv_work;

    @BindView(R.id.refresh_loading)
    SmartRefreshLayout refreshLayout;

    @BindView(R.id.edit)
    EditText et_tv;

    @BindView(R.id.commentRlv)
    RecyclerView rlv;

    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.gridView_find_pic)
    MultiImageView gridView_find_pic;

    ImageView im_pic;//一张图时

    @BindView(R.id.tv_find_content)
    TextView tv_find_content;//发布内容

    @BindView(R.id.tv_find_content_below)
    TextView tv_find_content_below;//发布内容


    @BindView(R.id.tv_find_describe)
    TextView tv_find_describe;//个人描述信息

    @BindView(R.id.tv_find_pbs)
    TextView tv_find_pbs;//pbs数量

    @BindView(R.id.tv_find_message)
    TextView tv_find_message;//消息数量

    private GetEssaysBean.DataBean bean2;
    private GetEssaysBean2 bean;
    private String userId;
    private int commentCount = 0;
    private HotItemComentAdapter comentAdapter;


    @Override
    public int getLayout() {

        return R.layout.activity_my_hot_item;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
/*
        gridView_find_pic.setOnItemClickListener((View view, String position, int po) ->{
            Intent intent = new Intent(this, SaveImageActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.IMG_LIST_SAVE, (Serializable) bean.getImages());
//                intent.putStringArrayListExtra("imglist", getEssaysBean);
            bundle.putInt("pic", 0);//暂时传零，点击都从一显示，此po是最后的数量，不是点击对应的条目，还需研究
            intent.putExtras(bundle);
            startActivity(intent);
        });*/
        ButterKnife.bind(this);
        userId = SharePreferenceUtils.getBaseSharePreference().readUserId();
        bean2 = (GetEssaysBean.DataBean) getIntent().getSerializableExtra("bean");
        GetEssaysBean.DataBean.BasicInfoBean basicInfo = bean2.getBasicInfo();
        List<GetEssaysBean.DataBean.ImagesBean> images = bean2.getImages();
        if (basicInfo != null) {

            String company = basicInfo.getCompany();
            String position_user = basicInfo.getPosition();
            String createdAt = basicInfo.getCreatedAt();

          /*  String time = createdAt.substring(createdAt.lastIndexOf("T") + 1, createdAt.lastIndexOf("T") + 6);
            String date = createdAt.substring(5, createdAt.lastIndexOf("T"));
*/


            long time1 = bean2.getTime();
            String dateToString = DataUtils.getDateToString(time1);

            if (basicInfo.getRealName() != null) {
                tv_find_name.setText("" + basicInfo.getRealName());
            }

          //  tv_find_describe.setText(date + " " + time);
           tv_find_describe.setText(dateToString);
            tv_work.setText(basicInfo.getPositionTitle());

            if (bean2 != null) {
                tv_find_content.setText(bean2.getContent());
                tv_find_content_below.setVisibility(View.GONE);
                tv_find_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        StartActivityManager.startUserDetailsActivity(MyHotItemActivity.this,
                                basicInfo.getUserId(), basicInfo.getRealName());
                    }
                });

            }
            GlideUtils.displayRound(this, BaseApi.baseUrlNoApi + basicInfo.getHeadImg(), im_find_head);
        }

        if (images != null) {
            if (images.size() == 0) {
                gridView_find_pic.setVisibility(View.GONE);
            } else {
                gridView_find_pic.setList(images);
            }
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              finish();
            }
        });

        initCommentList();

        //点击回车禁止换行，直接发送，如果要把回车改成发送按钮，就在xml里Edittext的input改为text，impoptions改成actionSend
        et_tv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                if (i== KeyEvent.KEYCODE_UNKNOWN && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

                    new UserTask(new HttpOnNextListener() {
                        @Override
                        public void onNext(String resulte, String method) {

                            //评论发布成功，先刷新评论列表，然后具体做什么吐司操作
                            commentCount++;
                            initCommentList();
                            T.showLong("评论已发布");
                            //清空输入框
                            et_tv.setText("");

                        }

                        @Override
                        public void onError(ApiException e) {

                        }
                    }, MyHotItemActivity.this).sendPinglun(bean2.get_id()+"",et_tv.getText().toString().trim());
                    //拦截掉回车功能
                    return true;
                }
                hideInputMethod();
                return false;


            }
        });




    }

    private void initGetFabulous() {

        //设置成全局来计数
        tv_find_pbs.setText("赞"+"("+bean.getData().getTotalFabulous()+")");
        tv_find_message.setText("回复"+"("+commentCount+")");
        //点赞
        tv_find_pbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点赞

                //此时isFabulous==0是未点赞过，可以点赞，否则已赞过不能再赞
                if (bean.getData().getIsFabulous()==0){
                    initDianZan();
                }else{

                }

            }
        });

    }

    private void initDianZan() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

                //点赞成功，+1
                int fabulous = bean.getData().getTotalFabulous()+1;
                tv_find_pbs.setText("赞"+"("+fabulous+")");
            }

            @Override
            public void onError(ApiException e) {

            }
        },this).getdianzan(bean2.get_id()+"",0,1,
                SharePreferenceUtils.getBaseSharePreference().readUserId());
    }

    private void initCommentList() {


        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Log.e("chu",resulte);
                GetEssaysBean2 bean2 = GsonUtil.GsonToBean(resulte, GetEssaysBean2.class);
                ArrayList<GetEssaysBean2.Comments> comments = (ArrayList<GetEssaysBean2.Comments>) bean2.getData().getComments();

                bean = bean2;
                commentCount = bean.getData().getComments().size();
                //获取原点赞评论数据
                initGetFabulous();
                rlv.setLayoutManager(new LinearLayoutManager(MyHotItemActivity.this));
                 comentAdapter = new HotItemComentAdapter(comments);
                rlv.setAdapter(comentAdapter);
                comentAdapter.notifyDataSetChanged();

                //解决receclerview和srcollview的滑动冲突
                rlv.setHasFixedSize(true);
                rlv.setNestedScrollingEnabled(false);


                comentAdapter.onItemClick(new HotItemComentAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        //如果是自己的账号的评论，就设置可以点击，不是自己的评论无法操作
                        if (SharePreferenceUtils.getBaseSharePreference().readUserId().equals(comments.get(position).getUserId()+"")){
                            new AlertDialog.Builder(MyHotItemActivity.this)
                                    .setTitle("确认删除？")
                                    .setMessage("删除此条评论？")
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //确定删除调用deleteComments（）方法执行删除操作,执行完重新调用initComments()方法刷新列表,
                                            // 然后评论数量-1并重新设置评论的数量
                                            deleteComments(position);
                                            dialogInterface.dismiss();

                                        }
                                    })
                                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    })
                                    .create().show();


                        }

                    }
                });
            }

            @Override
            public void onError(ApiException e) {

            }
        },this).getWeiBo(bean2.get_id()+"");


    }

    private void deleteComments(int position){

        comentAdapter.mList.remove(position);
        comentAdapter.notifyItemRemoved(position);
        commentCount--;
        tv_find_message.setText("回复"+"("+commentCount+")");



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}