package com.iruiyou.pet.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GlideUtils;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.Api.BaseApi;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.registerlast.OccupationBeen;
import com.iruiyou.pet.activity.registerlast.ResultDataLinsenter;
import com.iruiyou.pet.activity.registerlast.SearchPresenter;
import com.iruiyou.pet.activity.server.broadcast.BroadcastManager;
import com.iruiyou.pet.adapter.DynamicAdapter;
import com.iruiyou.pet.adapter.HotListAdapter;
import com.iruiyou.pet.adapter.UserEduAdapter;
import com.iruiyou.pet.adapter.UserWorkAdapter;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.AdoptBean;
import com.iruiyou.pet.bean.ApplyBean;
import com.iruiyou.pet.bean.BaseBean;
import com.iruiyou.pet.bean.BriefRefreshBean;
import com.iruiyou.pet.bean.DynamicBean;
import com.iruiyou.pet.bean.GetEssaysBean;
import com.iruiyou.pet.bean.RoughBean;
import com.iruiyou.pet.rongyun.CustomizeMessage;
import com.iruiyou.pet.utils.BigDecimalUtil;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.SelfDidalog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;
import io.rong.message.TextMessage;

public class PersonalMsgActivity extends BaseActivity {



    @BindView(R.id.head_img_bg)//高斯模糊
    ImageView head_img_bg;

    @BindView(R.id.back)//返回键
    ImageView back;
    @BindView(R.id.headIv)//头像
     ImageView headIv;

    @BindView(R.id.frameLayout)//高斯模糊
    FrameLayout frameLayout;
    @BindView(R.id.head_name)//用户名
    TextView name;
    @BindView(R.id.companyTitle)//公司
     TextView companyTitle;
    @BindView(R.id.educationTitle)//职位
    TextView educationTitle;

    @BindView(R.id.text_postion_name)//身份
    TextView textPositionName;

    @BindView(R.id.image_position_mark)//身份
    ImageView imagePositionMark;

    @BindView(R.id.ll_add_friends)//添加好友
            Button ll_add_friends;


    @BindView(R.id.rlv_user_msg)//动态
            RecyclerView recyclerView;


    private int lastEssayId = 0;

    private DynamicBean dynamicbean;
    private List<DynamicBean.DataBean.EssaysBean> essays;

 /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }*/





    private double percent=0;//资料完成度
    private UserWorkAdapter workAdapter;
    private UserEduAdapter eduAdapter;
    private BriefRefreshBean briefRefreshBean;
    private Context context;
    private int userid;
    private String realName;
    private int followType = 0;
    private RoughBean roughBean;
    private int friendStatus;
    private SearchPresenter registerPresenter;
    private ArrayList<OccupationBeen> data;
    private  ArrayList<GetEssaysBean.DataBean> datady;

    private HotListAdapter businessChanceAdapter;
    private DynamicAdapter dynamicAdapter;

    public static void startAction(Activity activity, int userId, String realName)
    {
        Intent intent=new Intent(activity, UserDetailsActivity.class);
        intent.putExtra("userid",userId);
        intent.putExtra("realName",realName);
        activity.startActivity(intent);
    }


    @Override
    public int getLayout() {
        return R.layout.layout_button_bg;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        initDta();
        //getDy();
        getDys();
    }

    private void getDys() {
        new UserTask(new HttpOnNextListener() {

            private String TAG="925921";

            @Override
            public void onNext(String resulte, String method) {


                Log.i("dynamic", "onNext: "+resulte);
                GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                datady = getEssaysBean.getData();
                Log.i(TAG, "one: ");

                ArrayList<GetEssaysBean.DataBean> objects = new ArrayList<>();
                for (int i = 0; i < datady.size(); i++) {
                    int s = datady.get(i).getBasicInfo().getUserId();
                    Log.i(TAG, "s: "+s);
                    Log.i(TAG, "userid: "+userid);
                    if (s==userid){
                        objects.add(datady.get(i));
                    }
                }
                if (objects!=null && objects.size()>0){
                    businessChanceAdapter = new HotListAdapter(PersonalMsgActivity.this,objects);

                    recyclerView.setLayoutManager(new LinearLayoutManager(PersonalMsgActivity.this));
                    recyclerView.setAdapter(businessChanceAdapter);
                    lastEssayId = datady.get(datady.size() - 1).get_id();
                    businessChanceAdapter.notifyDataSetChanged();

                }


            }

            @Override
            public void onError(ApiException e) {
                Log.i(TAG, "onError: "+e.getMessage());
            }
        },this).getEssays(1, 200, lastEssayId, 0);//type：0我的；1最新；2热门；3关注
    }

    private void getDy() {
        new UserTask(new HttpOnNextListener() {

            private String TAG="925921";

            @Override
            public void onNext(String resulte, String method) {

                Log.i("ggggg", "onNext: "+resulte);
                dynamicbean = GsonUtils.parseJson(resulte, DynamicBean.class);
                //GetEssaysBean getEssaysBean = GsonUtils.parseJson(resulte, GetEssaysBean.class);
                Log.i(TAG, "one: ");
                essays = dynamicbean.getData().getEssays();
                Log.i(TAG, "one: ");

                ArrayList<DynamicBean.DataBean.EssaysBean> objects = new ArrayList<>();
                for (int i = 0; i < essays.size(); i++) {
                    int s = essays.get(i).getBasicInfo().getUserId();
                    Log.i(TAG, "s: "+s);
                    Log.i(TAG, "userid: "+userid);
                    if (s==userid){
                        objects.add(essays.get(i));
                    }
                }
                  //  businessChanceAdapter = new HotListAdapter(PersonalMsgActivity.this,objects);
                dynamicAdapter = new DynamicAdapter(PersonalMsgActivity.this,objects);
                    recyclerView.setLayoutManager(new LinearLayoutManager(PersonalMsgActivity.this));
                    recyclerView.setAdapter(dynamicAdapter);
                  //  lastEssayId = datady.get(datady.size() - 1).get_id();
                dynamicAdapter.notifyDataSetChanged();

            }

            @Override
            public void onError(ApiException e) {
                Log.i("lllll", "onError: "+e.getMessage());
            }
        },this).getminedynamic(1,20,userid,SharePreferenceUtils.getBaseSharePreference().readToken());//type：0我的；1最新；2热门；3关注

    }

    private void initDta() {
        context = PersonalMsgActivity.this;
        registerPresenter = new SearchPresenter();
        registerPresenter.IPresenter(PersonalMsgActivity.this);

        userid = getIntent().getIntExtra("userid", 0);
        realName = getIntent().getStringExtra("realName");
//        tv_user_title.setText(realName + getString(R.string.user_details));

//        getData();
        getDatas();
//        setListener();
        //接收广播-通过验证
        BroadcastManager.getInstance(context).addAction(Constant.AGREE, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String command = intent.getAction();
                if(!TextUtils.isEmpty(command)){
                    if((Constant.AGREE).equals(command)) {//有好友申请，显示红点
                        friendStatus = 3;
                        //添加自定义消息
                        CustomizeMessage customizeMessage = new CustomizeMessage();
                        customizeMessage.setContent(getString(R.string.add_friend));
                        Message message = Message.obtain(Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), Conversation.ConversationType.PRIVATE, customizeMessage);
                        RongIM.getInstance().sendMessage(message, null, null, new IRongCallback.ISendMessageCallback() {

                            @Override
                            public void onAttached(Message message) {
                            }

                            @Override
                            public void onSuccess(Message message) {
                            }

                            @Override
                            public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                            }
                        });
                    }
                }
            }
        });


        registerPresenter.getOccupationsList( new ResultDataLinsenter() {
            @Override
            public void success(Object o) {
                if(o!=null)
                {
                    data=(ArrayList<OccupationBeen>) o;
                }

            }

            @Override
            public void filed(Exception e) {

            }
        });
    }


    private void setPositionMark(int value)
    {
        int imageResourceId=0;
        switch (value)
        {
            case 1:
                imageResourceId= R.drawable.ic_profid_1;
                break;
            case 2:
                imageResourceId= R.drawable.ic_profid_2;
                break;
            case 3:
                imageResourceId= R.drawable.ic_profid_3;
                break;
            case 4:
                imageResourceId= R.drawable.ic_profid_4;
                break;
            case 5:
                imageResourceId= R.drawable.ic_profid_5;
                break;
            case 6:
                imageResourceId= R.drawable.ic_profid_6;
                break;
            case 7:
                imageResourceId= R.drawable.ic_profid_7;
                break;
            case 8:
                imageResourceId= R.drawable.ic_profid_8;
                break;
            case 9:
                imageResourceId= R.drawable.ic_profid_9;
                break;
            case 10:
                imageResourceId= R.drawable.ic_profid_10;
                break;
            case 11:
                imageResourceId= R.drawable.ic_profid_11;
                break;
            case 12:
                imageResourceId= R.drawable.ic_profid_12;
                break;
            case 13:
                imageResourceId= R.drawable.ic_profid_13;
                break;
            case 14:
                imageResourceId= R.drawable.ic_profid_14;
                break;
            case 15:
                imageResourceId= R.drawable.ic_profid_15;
                break;
            case 999:
                imageResourceId= R.drawable.ic_profid_999;
                break;
        }

        if(imageResourceId!=0)
        {
            imagePositionMark.setImageResource(imageResourceId);
            for(OccupationBeen occupationBeen:data)
            {
                if(occupationBeen.getDbKey()==value)
                {
                    textPositionName.setText(occupationBeen.getLangValue());
                    break;
                }
            }
        }
    }

    private void getDatas() {

        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                roughBean = GsonUtils.parseJson(resulte, RoughBean.class);
//                T.showShort(applyBean.getMessage());
                if (roughBean.getStatusCode() == Constant.SUCCESS) {

                    //设置资料完成度
                    if(roughBean.getData().getStatisticsInfo()!=null)
                    {
                        RoughBean.DataBean.StatisticsInfo statisticsInfo=roughBean.getData().getStatisticsInfo();
                        if(roughBean.getData().getInfoCount()>0)
                        {
                            percent= ((double)(statisticsInfo.getBasicCount()+statisticsInfo.getPhotoCount()+statisticsInfo.getWorkCount()+statisticsInfo.getEducationCount()))/roughBean.getData().getInfoCount();
                            if(percent>=1)
                            {
                                /*imageComplete.setImageResource(R.drawable.icon_complete_detail);
                                textComplete.setTextColor(getResources().getColor(R.color._72C6ae));*/
                            }
                            else
                            {
                              /*  imageComplete.setImageResource(R.drawable.icon_un_complete_detail);
                                textComplete.setTextColor(getResources().getColor(R.color.ffca48));*/
                            }
                          //  textComplete.setText(((int)(percent*100))+"%");
                        }

                    }

                    if (roughBean.getData().getBasicInfo() != null) {
                        addFriendsLog(roughBean.getData().getBasicInfo().get_id());

                        RoughBean.DataBean.BasicInfoBean basicInfoBean=roughBean.getData().getBasicInfo();
                        companyTitle.setText(basicInfoBean.getCompany());
                        educationTitle.setText(basicInfoBean.getPosition());
                        setPositionMark(basicInfoBean.getProfessionalIdentity());
//                        String company = roughBean.getData().getBasicInfo().getCompany();
//                        String positionbrief = roughBean.getData().getBasicInfo().getPosition();
                        name.setText(roughBean.getData().getBasicInfo().getRealName());
//
                        //刷新用户头像到融云上
                        RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName(), Uri.parse(BaseApi.baseUrlNoApi+roughBean.getData().getBasicInfo().getHeadImg())));//刷新同步头像昵称到融云

                        //依次为：好友，粉丝，关注，数量，算力
                       /* tvFriendsNum.setText(roughBean.getData().getFriendCount()+"");
                        tvFansNum.setText(roughBean.getData().getFansCount()+"");
                        tvFollowNum.setText(roughBean.getData().getFollowCount()+"");*/
                        double pbsAmount = BigDecimalUtil.round(roughBean.getData().getUserInfo().getPbsAmount(), Constant.SCALE_NUM);
//                        tvPBS.setText(pbsAmount+"");
                       // identity.setText(roughBean.getData().getUserInfo().getCombat()+"");
                        GlideUtils.displayRound(context, BaseApi.baseUrlNoApi + roughBean.getData().getBasicInfo().getHeadImg(), headIv);



                      /*  RequestOptions requestOptions = new RequestOptions();
                        requestOptions.bitmapTransform(new BlurTransformation(this, 14, 3));
                        Glide.with(context).load(BaseApi.baseUrlNoApi + roughBean.getData().getBasicInfo().getHeadImg()).apply(requestOptions).into(head_img_bg);*/

                        Glide.with(context).load(BaseApi.baseUrlNoApi + roughBean.getData().getBasicInfo().getHeadImg()).into(head_img_bg);



                       /* //模糊
                        Resources res = PersonalMsgActivity.this.getResources();
                     //拿到初始图
                        Bitmap bmp= BitmapFactory.decodeResource(res, Integer.parseInt(BaseApi.baseUrlNoApi + roughBean.getData().getBasicInfo().getHeadImg()));
                     //处理得到模糊效果的图
                        Bitmap blurBitmap = ImageFilter.blurBitmap(PersonalMsgActivity.this, bmp, 20f);
                        head_img_bg.setImageBitmap(blurBitmap);
*/

                        friendStatus = roughBean.getData().getFriendStatus();
                        int readAccept = SharePreferenceUtils.getBaseSharePreference().readAccept();
                        if(readAccept == 1){
                            friendStatus = 1;
                            SharePreferenceUtils.getBaseSharePreference().saveAccept(0);
                        }

                       /* im_add_friends.setImageResource(R.drawable.add_friends);
                        tv_add_friends.setText(getString(R.string.add_friends)+" "+basicInfoBean.getSeePrice()+"脉点");*/
                        if(basicInfoBean.getUserId()==Integer.valueOf(SharePreferenceUtils.getBaseSharePreference().readUserId()))//是当前用户自己
                        {
                            ll_add_friends.setVisibility(View.GONE);
                           /* im_add_friends.setImageResource(R.drawable.add_friends);
                            tv_add_friends.setText(getString(R.string.add_friends));*/
                        }
                        else
                        {
                            //0陌生人； 1申请； 2拒绝； 3好友； 4黑名单
                            if(friendStatus == 0){
                                ll_add_friends.setText("添加好友");


                                if(basicInfoBean.getSeePrice()>0&&(!roughBean.getData().isHadBuy()&&(friendStatus!=3)))
                                {

                                    if(percent>0&&(percent<1))
                                    {
                                       // tvSeePrice.setVisibility(View.GONE);
                                    }
                                    else if(percent==1)
                                    {
                                        //tvSeePrice.setVisibility(View.VISIBLE);
                                    }
                                }
                                else
                                {
                                   // tvSeePrice.setVisibility(View.GONE);
                                }
                            }else if(friendStatus == 1){//通过验证

                                ll_add_friends.setText("发送消息");




                            }else if(friendStatus == 2){//添加好友
                                ll_add_friends.setText("添加好友");



                            }else if(friendStatus == 3){//发消息

                                ll_add_friends.setText("发送消息");

                            }else if(friendStatus == 4){

                                ll_add_friends.setText("发送消息");



                            }else if(friendStatus==499)//已经发送好友申请，对方正在审核中
                            {

                                ll_add_friends.setText("已发送");
                                ll_add_friends.setBackgroundColor(Color.parseColor("#ffcaced9"));


                            }
                        }



                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).rough(userid);
    }


    //添加好友访问记录
    private void addFriendsLog(String baseId){
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {

            }

            @Override
            public void onError(ApiException e) {

            }
        }, PersonalMsgActivity.this).addFriendLog(SharePreferenceUtils.getBaseSharePreference().readBasicId(),baseId);
    }


    @OnClick({R.id.back,  R.id.ll_add_friends})
    public void onViewClicked(View view) {
        switch (view.getId()) {
           /* case R.id.ll_back_request:
                //TODO 撤回好友申请
                cancelApply(userid);
                break;*/
            case R.id.back:
                finish();
                break;
         /*   case R.id.bt_view_home_page:
                UserDetails2Activity.startActio(PersonalMsgActivity.this,realName,userid);
                break;*/
           /* case R.id.view_info:
                if(roughBean!=null&&roughBean.getData()!=null&&roughBean.getData().getBasicInfo()!=null)
                {

                    if(roughBean.getData().getBasicInfo().getUserId()!=
                            Integer.valueOf(SharePreferenceUtils.getBaseSharePreference().readUserId()))
                    {
                        int friendStatue=roughBean.getData().getFriendStatus();
                        if(3==friendStatue||roughBean.getData().isHadBuy()||(percent>0&&(percent<1)))//已经成为好友或者已经付费过
                        {
                            UserDetails2Activity.startActio(PersonalMsgActivity.this,realName,userid);
                        }
                        else
                        {
                            payForViewInfo((float)roughBean.getData().getBasicInfo().getSeePrice(),roughBean.getData().getBasicInfo().getUserId()+"");
                        }
                    }
                    else
                    {
                        UserDetails2Activity.startActio(PersonalMsgActivity.this,realName,userid);
                    }
                }



                break;*/
            case R.id.ll_add_friends:
                //设置消息携带用户信息
                RongIM.getInstance().setMessageAttachedUserInfo(true);
                //0陌生人； 1申请； 2拒绝； 3好友； 4黑名单
                if(friendStatus == 0){
                    if(roughBean!=null&&roughBean.getData()!=null&&roughBean.getData().getBasicInfo()!=null)
                    {
                       // payForAddFriend((float)roughBean.getData().getBasicInfo().getFriendPrice());
                        addFriends();
                    }
                }else if(friendStatus == 1){


                    ll_add_friends.setText("发送消息");


                    getAdopt();
                    friendStatus = 3;
//                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName());
                }else if(friendStatus == 2){

//                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName());
                }else if(friendStatus == 3){
                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(0);//单聊与群聊的去区分
//                    String readNickName = SharePreferenceUtils.getBaseSharePreference().readNickName();
//                    String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
//                    RongIM.getInstance().startPrivateChat(context,Constant.TARGETID+readUserId, readNickName);
                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName());
//                    sendMsg(Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId());
                }else if(friendStatus == 4){
                    SharePreferenceUtils.getBaseSharePreference().saveCompanyid(0);
                    RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, Constant.TARGETID+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName());
                }
//                addFriends();
//                RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, "dev"+roughBean.getData().getBasicInfo().getUserId(), roughBean.getData().getBasicInfo().getRealName());
                break;
        }
    }


    /**
     *
     */
    private void cancelApply(int targetUserId) {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(!TextUtils.isEmpty(resulte))
                {
                    BaseBean baseBean= GsonUtils.parseJson(resulte, BaseBean.class);
                    if(baseBean.getStatusCode()== Constant.SUCCESS)
                    {
                        friendStatus=0;
                        roughBean.getData().setFriendStatus(friendStatus);




                        ll_add_friends.setText("添加好友");





                       // linearBackRequest.setVisibility(View.GONE);
                        T.showShort("撤回成功，PBS已返还!");
                    }
                    else if(!TextUtils.isEmpty(baseBean.getMessage()))
                    {
                        T.showShort(baseBean.getMessage());
                    }
                }
            }

            @Override
            public void onError(ApiException e) {

            }
        }, PersonalMsgActivity.this).cancelApply(targetUserId);
    }

    private void payForAddFriend(float payCount) {
        boolean isVisibleCancel = true;//true显示两个控件，否则1显示个
        String title=getString(R.string.prompt);
        String content=String.format(getString(R.string.pay_addfriend_str),payCount);
        int noStrs = R.string.cancel;
        int yesStrs = R.string.set3;
        new SelfDidalog(context, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
            @Override
            public void onYesClick() {
                addFriends();
            }

            @Override
            public void onNoClick() {
            }
        }).show();
    }


    private void payForViewInfo(float payCount,String userId)
    {
        boolean isVisibleCancel = true;//true显示两个控件，否则1显示个
        String title=getString(R.string.prompt);
        String content=String.format(getString(R.string.pay_info_str),payCount);
        int noStrs = R.string.cancel;
        int yesStrs = R.string.set3;
        new SelfDidalog(context, title, content, true, noStrs, yesStrs, new SelfDidalog.onYesAndNoOnclickListener() {
            @Override
            public void onYesClick() {
                new UserTask(new HttpOnNextListener(){
                    @Override
                    public void onNext(String resulte, String method) {
                        if(!TextUtils.isEmpty(resulte))
                        {
                            BaseBean baseBean= GsonUtils.parseJson(resulte, BaseBean.class);
                            if(baseBean.getStatusCode()== Constant.SUCCESS)
                            {
                                roughBean.getData().setHadBuy(true);
                               // tvSeePrice.setVisibility(View.GONE);
                                UserDetails2Activity.startActio(PersonalMsgActivity.this,realName,userid);
                            }
                            else if(!TextUtils.isEmpty(baseBean.getMessage()))
                            {
                                T.showShort(baseBean.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        e.printStackTrace();
                    }
                }, PersonalMsgActivity.this).buyInfo(userId);
            }

            @Override
            public void onNoClick() {
            }
        }).show();
    }

    /**
     * 通过申请请求
     */
    private void getAdopt() {

//        String readUserId = SharePreferenceUtils.getBaseSharePreference().readUserId();
//        if(readUserId.equals(String.valueOf(getAppliersBean.getData().get(po).getUserIdA()))) {//自己
//            userid = getAppliersBean.getData().get(po).getUserIdB();
//        }else {
//            userid = getAppliersBean.getData().get(po).getUserIdA();
//        }
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                AdoptBean adoptBean = GsonUtils.parseJson(resulte, AdoptBean.class);
                if (adoptBean.getStatusCode() == Constant.SUCCESS) {
                    int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
                    applicationCount --;
                    SharePreferenceUtils.getBaseSharePreference().saveApplicationCount(applicationCount);
                    friendStatus=3;
                    roughBean.getData().setFriendStatus(friendStatus);
                   /* im_add_friends.setImageResource(R.drawable.send);
                    tv_add_friends.setText(getString(R.string.send));*/
                }else if(!TextUtils.isEmpty(adoptBean.getMessage()))
                {
                    T.showShort(adoptBean.getMessage());
                }

            }

            @Override
            public void onError(ApiException e) {
            }
        }, this).adopt(userid);
    }


    /**
     * 发送消息
     */
    private void sendMsg(String userId) {
        /**
         * 发送普通消息
         * @param conversationType      会话类型
         * @param targetId              会话ID
         * @param content               消息的内容，一般是MessageContent的子类对象
         * @param pushContent           接收方离线时需要显示的push消息内容
         * @param pushData              接收方离线时需要在push消息中携带的非显示内容
         * @param SendMessageCallback   发送消息的回调
         * @param ResultCallback        消息存库的回调，可用于获取消息实体
         */
        RongIMClient.getInstance().sendMessage(Conversation.ConversationType.PRIVATE, userId, TextMessage.obtain("你好"), "test", null, new RongIMClient.SendMessageCallback() {
            @Override
            public void onSuccess(Integer integer) {
                String str = "消息发送成功";
            }

            @Override
            public void onError(Integer integer, RongIMClient.ErrorCode errorCode) {
                String str = "消息发送失败";
            }
        }, null);
    }

//    /**
//     * 请求关注  1,关注  0，取消关注
//     */
//    private void follow(int type) {
//        if(type == 1){
//            new UserTask(new HttpOnNextListener() {
//                @Override
//                public void onNext(String resulte, String method) {
//                    ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
//                    T.showShort(applyBean.getMessage());
//                    if (applyBean.getStatusCode() == Constant.SUCCESS) {
//                        im_follow.setImageResource(R.drawable.unfollow);
////                        tv_follow.setText(getString(R.string.unfollow));
//                    }
//                }
//
//                @Override
//                public void onError(ApiException e) {
//                    T.showShort(e.getMessage());
//                }
//            }, this).followFriends(userid);
//        }else {
//            new UserTask(new HttpOnNextListener() {
//                @Override
//                public void onNext(String resulte, String method) {
//                    ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
//                    T.showShort(applyBean.getMessage());
//                    if (applyBean.getStatusCode() == Constant.SUCCESS) {
//
//                        im_follow.setImageResource(R.drawable.follow);
////                        tv_follow.setText(getString(R.string.follow));
//                    }
//                }
//
//                @Override
//                public void onError(ApiException e) {
//                    T.showShort(e.getMessage());
//                }
//            }, this).unFollow(userid);
//        }
//
//    }

    /**
     * 发送好友申请
     */
    private void addFriends() {
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                ApplyBean applyBean = GsonUtils.parseJson(resulte, ApplyBean.class);
                T.showShort(applyBean.getMessage());
                if (applyBean.getStatusCode() == Constant.SUCCESS) {
                    friendStatus=499;
                    roughBean.getData().setFriendStatus(friendStatus);
                   // ll_add_friends.setVisibility(View.GONE);
                   // linearBackRequest.setVisibility(View.VISIBLE);



                    ll_add_friends.setText("已发送");
                    ll_add_friends.setBackgroundColor(Color.parseColor("#ffcaced9"));

                    //ll_add_friends.setImageResource(R.drawable.have_apply);
                }
                else if(!TextUtils.isEmpty(applyBean.getMessage()))
                {
                    T.showShort(applyBean.getMessage());
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).apply(userid);
    }

   /* private void setListener() {
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BriefRefreshBean.DataBean.WorkInfosBean workInfosBean = (BriefRefreshBean.DataBean.WorkInfosBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.WORK, workInfosBean);
                bundle.putString(Constant.EDITORS, Constant.SWITCH_YES);
                Intent intent = new Intent(context, WorkExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        eduAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BriefRefreshBean.DataBean.EducationInfosBean educationInfosBean = (BriefRefreshBean.DataBean.EducationInfosBean) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constant.EDU, educationInfosBean);
                bundle.putString(Constant.EDITORS, Constant.SWITCH_YES);
                Intent intent = new Intent(context, EduExpeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }*/

    // 可视 VISIBLE = 0  不可视 gone = 8   设置控件显示隐藏
    public void setViewVisibility(int a, int b, int d, int e, int f, int g) {
       /* llFriendFans.setVisibility(b);
        include_foot_data.setVisibility(d);*/
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        RongIM.getInstance().disconnect();
        BroadcastManager.getInstance(context).destroy(Constant.AGREE);
    }
}
