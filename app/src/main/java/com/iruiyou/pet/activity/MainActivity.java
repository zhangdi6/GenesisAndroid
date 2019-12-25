package com.iruiyou.pet.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.iruiyou.common.utils.AppManager;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.pet.App;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.registered.QuickLoginActivity;
import com.iruiyou.pet.activity.server.broadcast.BroadcastManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.base.BaseFragment;
import com.iruiyou.pet.fragment.MCClassFragment2;
import com.iruiyou.pet.fragment.MCOpportunitiesFragment3;
import com.iruiyou.pet.fragment.MessageFragment;
import com.iruiyou.pet.fragment.MessageFragment2;
import com.iruiyou.pet.fragment.MineFragment2;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.DragPointView;
import com.iruiyou.pet.utils.PageNameConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import io.rong.imkit.RongIM;
import io.rong.imkit.manager.IUnReadMessageObserver;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

//import com.iruiyou.pet.fragment.MCClassFragment;

/**
 * 主界面
 * created by jiao 2016/4/22
 */
public class MainActivity extends BaseActivity implements DragPointView.OnDragListencer,
        IUnReadMessageObserver {

    private final String [] addActions=new String []{Constant.APPLY_VISIBLE, Constant.APPLY_GONE,"DELIVERED_SMS_ACTION","SENT_SMS_ACTION"};
    private Button btn[];
    private View view_top;
    private FrameLayout fl1;
    private FrameLayout fl2;
    private FrameLayout main_fl_pulse_field;
    private FrameLayout fl3,main_fl_news,main_fl_find;
//    private RelationshipsFragment relationshipsFragment;
//    private MCChanceFragment mcChanceFragment;
//    private MCDistributionFragment distributionFragment;
    private MCClassFragment2 spaceFragment;//将原来的空间页面改为了脉友页面  MCSpaceFragment------MCClassFragment2
//    private MCMainOneFragment mcMainOneFragment;
    private MCOpportunitiesFragment3 opportunitiesFragment;
//    private Fragment2 fragment2;
    private MineFragment2 mineFragment;
//    private HomeFragment fragmentHome;
    private MessageFragment2 mcClassFragment;//将原来的机会页面改为了消息页面  MCClassFragment2------MessageFragment2
    private MessageFragment fragmentMessage;
    /*private PulseFieldFragment pulseFieldFragment;*/
//    private NewestFragment newestFragment;
//    private PulseFieldItemFragment pulseFieldItemFragment;
    private int index;
    private int preIndex = 0;
    private int type = 0 ;
    private long exitTime;// 记录退出时间
//    private ACache aCache;
//    private PetList petList;//宠物列表
//    private boolean isLoad;
    private DragPointView dpv_contacts;
    private Conversation.ConversationType[] mConversationsTypes = null;
//    private FragmentManager fragmentManager;
//    private FragmentTransaction beginTransaction;
    @Override
    public int getLayout() {
        initLocaleLanguage();
        return R.layout.activity_main;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("nnn","onCreat");
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
//        getWindow().addFlags(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN); //隐藏状态栏
//        EventBusUtils.getInstance().register(this);
//        isLoad = true;
//        aCache = ACache.get(this);

        Log.e("nnn","onAcctOnCreat");
        type = getIntent().getIntExtra("type",0);
        initView();
        setOnclick();// 设置点击事件

       /* hideTitle();*/

//        setLeftImage(getResources().getDrawable(R.drawable.icon_message));
//        setLeftClickListener((view) -> {
//            Intent intent=new Intent(MainActivity.this,LookForPeopleActivity.class);
//            startActivity(intent);
//        });

//        setTitleRightText2OnClick((view) -> {
//            startActivity(ContactsActivity.class);
//        });
//        setTitleRightText2(getResources().getString(R.string.my_network));
//        setTitleRightText2Color(getResources().getColor(R.color._26c68a));


       /* setRightImageClick((view) -> {

            if (App.isLogin){

            if (SharePreferenceUtils.getBaseSharePreference().readVipLevel() < 1) {
                T.showShort("请开通会员后再发布商机！");
            } else {
                Intent intent = new Intent(MainActivity.this, ReleaseActivity.class);
                startActivity(intent);
            }

            }else{
                Intent intent = new Intent(this, QuickLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        int applicationCount = SharePreferenceUtils.getBaseSharePreference().readApplicationCount();
        if(applicationCount > 0) {//有好友申请，显示红点
            //获取json结果
//            String json = intent.getStringExtra("result");
            //做你该做的事情
            mUnreadNumView.setVisibility(View.VISIBLE);
        }else {
            mUnreadNumView
                    .setVisibility(View.GONE);
        }

        BroadcastManager.getInstance(this).addActions(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action= intent.getAction();
                switch (Objects.requireNonNull(action))
                {
                    case "SENT_SMS_ACTION":
                        switch (getResultCode())
                        {
                            case Activity.RESULT_OK:
                                T.showShort(getResources().getString(R.string.SMSissentsuccessfully));
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                break;
                        }
                        break;
                    case "DELIVERED_SMS_ACTION":
                        T.showShort(getResources().getString(R.string.ReceiveSuccess));
                        /*pulseFieldFragment.setSmsReceive();*/
                        break;
                    case  Constant.APPLY_VISIBLE://有好友申请，显示红点
                        //做你该做的事情
                        dpv_contacts.setVisibility(View.VISIBLE);
                        break;
                    case Constant.APPLY_GONE://隐藏红点
                        dpv_contacts.setVisibility(View.GONE);
                        break;

                }
            }
        },addActions);


    }



    @Override
    protected void onStart() {
        super.onStart();
//        getMyPets();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    /**
     * 国际化
     */
    private void initLocaleLanguage() {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = new Locale(SharePreferenceUtils.getBaseSharePreference().readLanguage());
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());//更新配置
    }

    @Override
    public void OnViewClick(View v) {
        fl1.setVisibility(View.INVISIBLE);
//        fl2.setVisibility(View.INVISIBLE);
        main_fl_pulse_field.setVisibility(View.INVISIBLE);
        fl3.setVisibility(View.INVISIBLE);
        main_fl_news.setVisibility(View.INVISIBLE);
        main_fl_find.setVisibility(View.INVISIBLE);
        switch (v.getId()) {
            case R.id.main_btn1://挖矿
               fl1.setVisibility(View.VISIBLE);
                index = 0;
                setTitle(getResources().getString(R.string.pulse_field));
                hideTitle();
                setRightImageVisiable(View.VISIBLE);
                showRightText();
                setTitleRightText2Visiable(View.GONE);
                view_top.setVisibility(View.VISIBLE);
                setStatueBar(true);

//                setRightImageVisiable(View.GONE);
//                setTitleRightText2Visiable(View.VISIBLE);
                break;
            case R.id.main_btn_pulse_field://脉场
               // if (App.isLogin){
                if (SharePreferenceUtils.getBaseSharePreference().readlogin()){

                main_fl_pulse_field.setVisibility(View.VISIBLE);
                index = 1;
                setTitle(getResources().getString(R.string.renmai));
                setRightImageVisiable(View.GONE);
                setTitleRightText2Visiable(View.GONE);
                view_top.setVisibility(View.GONE);
                setStatueBar(true);

                }else{
                    Intent intent = new Intent(this, QuickLoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
            /*case R.id.main_btn_find://发现
                main_fl_find.setVisibility(View.VISIBLE);
                index = 2;
                setTitle(getResources().getString(R.string.news));
                setRightImageVisiable(View.GONE);
                setTitleRightText2Visiable(View.GONE);
                view_top.setVisibility(View.GONE);
                setStatueBar(true);
                break;*/
            case R.id.main_btn_news://发现

                main_fl_news.setVisibility(View.VISIBLE);
                index = 3;
                setTitle(getResources().getString(R.string.find));
                setRightImageVisiable(View.GONE);
                setTitleRightText2Visiable(View.GONE);
                view_top.setVisibility(View.GONE);
                setStatueBar(true);

                break;
//            case R.id.main_btn2://联系人
//                fl2.setVisibility(View.VISIBLE);
//                index = 3;
//                break;
            case R.id.main_btn3://我的

                //这个判断就是登录和非登录状态下的点击事件
                //在登录的接口那里设置成App.isLogin = true。然后在退出登录那里设置成 App.isLogin = false ;

            //    if (App.isLogin){
                    if (SharePreferenceUtils.getBaseSharePreference().readlogin()){
                    fl3.setVisibility(View.VISIBLE);
                    index = 4;
                    setTitle(getResources().getString(R.string.mine));
                    setRightImageVisiable(View.GONE);
                    setTitleRightText2Visiable(View.GONE);
                    view_top.setVisibility(View.GONE);
                    setStatueBar(false);
                }else{
                    Intent intent = new Intent(this, QuickLoginActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
            default:
                break;
        }
        btn[preIndex].setSelected(false);
        btn[index].setSelected(true);
        preIndex = index;
    }



    // 设置点击事件
    private void setOnclick() {
        setViewClick(R.id.main_btn1);
        setViewClick(R.id.main_btn_pulse_field);
       // setViewClick(R.id.main_btn_find);
        setViewClick(R.id.main_btn_news);
//        setViewClick(R.id.main_btn2);
        setViewClick(R.id.main_btn3);
    }

    public void setFriendsCount(int count){
        mineFragment.setFriendCount(count);
    }

    public void setAccessCount(int accessCount){
        mineFragment.setAccessCount(accessCount);
    }
  /*  public void setFriendsCount2(int count){
        mineFragment2.setFriendCount(count);
    }
    public void setAccessCount2(int accessCount){
        mineFragment2.setAccessCount(accessCount);
    }*/
    // 初始化View

    private void initView() {
        btn = new Button[5];
        view_top = findViewById(R.id.view_top);
        btn[0] = findViewById(R.id.main_btn1);
        btn[1] = findViewById(R.id.main_btn_pulse_field);//脉场
     //   btn[2] = findViewById(R.id.main_btn_find);
        btn[3] = findViewById(R.id.main_btn_news);
//        btn[3] = (Button) findViewById(R.id.main_btn2);
        btn[4] = findViewById(R.id.main_btn3);
        mUnreadNumView = findViewById(R.id.sssss);//消息数量提示
        dpv_contacts = findViewById(R.id.dpv_contacts);//联系人提示
       /* mUnreadNumView.setOnClickListener(this);
        mUnreadNumView.setDragListencer(this);*/
        //获取所有的未读消息
        mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                Conversation.ConversationType.GROUP,
                Conversation.ConversationType.PUBLIC_SERVICE,
                Conversation.ConversationType.APP_PUBLIC_SERVICE,
                Conversation.ConversationType.SYSTEM,
                Conversation.ConversationType.DISCUSSION
        };
        RongIM.getInstance().addUnReadMessageCountChangedObserver(this, mConversationsTypes);

        fl1 = findViewById(R.id.main_fl1);
//        fl2 = (FrameLayout) findViewById(R.id.main_fl2);
        main_fl_pulse_field = findViewById(R.id.main_fl_pulse_field);
        fl3 = findViewById(R.id.main_fl3);
        main_fl_news = findViewById(R.id.main_fl_news);
        main_fl_find = findViewById(R.id.main_fl_find);


//        Fragment conversationList = initConversationList();


        mcClassFragment = MessageFragment2.getInstance();//将原来的机会页面改为了消息页面  MCClassFragment2------MessageFragment2
//        distributionFragment = MCDistributionFragment.getInstance();
//        mcMainOneFragment = MCMainOneFragment.getInstance();
        fragmentMessage = MessageFragment.getInstance();
        /*pulseFieldFragment = PulseFieldFragment.getInstance();*/
        mineFragment = MineFragment2.getInstance();
        opportunitiesFragment= MCOpportunitiesFragment3.getInstance();
//        newestFragment = NewestFragment.getInstance();
//        relationshipsFragment = RelationshipsFragment.getInstance();
//        mcChanceFragment = MCChanceFragment.getInstance();
        spaceFragment = MCClassFragment2.getInstance();
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction beginTransaction= fragmentManager
                .beginTransaction();
        beginTransaction.add(R.id.main_fl1, opportunitiesFragment);//首页
        beginTransaction.add(R.id.main_fl_pulse_field, mcClassFragment);//脉场
        beginTransaction.add(R.id.main_fl3, mineFragment);//我的
        beginTransaction.add(R.id.main_fl_news, spaceFragment);//商机
        beginTransaction.add(R.id.main_fl_find, fragmentMessage);// 消息
        beginTransaction.addToBackStack(null);
        beginTransaction.commit();


/*
        fl1.setVisibility(View.VISIBLE);
        index = 0;

        setTitle(getResources().getString(R.string.pulse_field));

        setRightImageVisiable(View.VISIBLE);
        showRightText();
        setTitleRightText2Visiable(View.GONE);
        view_top.setVisibility(View.VISIBLE);
        setStatueBar(true);

       */
      //  initData();
        OnViewClick(btn[0]);


        btn[0].setSelected(true);


    }


    public void addFragment(int contentId, BaseFragment fragment) {
        addFragment(contentId, fragment);
    }

  /*  public void initData(){
        mineFragment.setOnItemClickListener(new MineFragment.OnItemClickListener() {
            @Override
            public void intemClick() {

                main_fl_find.setVisibility(View.INVISIBLE);
                addFragment(R.id.main_fl3,fragmentMessage);

            }
        });

    }
*/
    /**
     * 按两次返回键退出程序
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                exitTime = System.currentTimeMillis();
                T.showShort(getResources().getString(R.string.PressAgainAndExitTheProgram));
                return true;
            } else {

//                SystemUtil.exitApp();// 退出程序
                AppManager.getAppManager().finishAllActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBusUtils.getInstance().unregister(this);
        try {
            BroadcastManager.getInstance(this).destroy(Arrays.toString(addActions));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onDragOut() {
        mUnreadNumView.setVisibility(View.GONE);
        T.showShort(getString(R.string.clear_success));
//        NToast.shortToast(mContext, getString(R.string.clear_success));
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                if (conversations != null && conversations.size() > 0) {
                    for (Conversation c : conversations) {
                        RongIM.getInstance().clearMessagesUnreadStatus(c.getConversationType(), c.getTargetId(), null);
                    }
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode e) {

            }
        }, mConversationsTypes);
    }

    @Override
    public void onCountChanged(int count) {

        if (count == 0) {
            mUnreadNumView.setVisibility(View.GONE);
        } else if (count > 0 && count < 100) {
            T.showShort(count+"");
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText(String.valueOf(count));
        } else {
            mUnreadNumView.setVisibility(View.VISIBLE);
            mUnreadNumView.setText("..");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        OnViewClick(btn[App.choice]);
        btn[App.choice].setSelected(true);
       Log.e("nnn",
                "onResume"
        );
        MobclickAgent.onPageStart(PageNameConstant.PageName_MainActivity);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        App.choice = index ;
        MobclickAgent.onPageEnd(PageNameConstant.PageName_MainActivity);
        MobclickAgent.onPause(this);
    }

    private void setStatueBar(boolean statueBar){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = MainActivity.this.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(MainActivity.this.getResources().getColor(R.color.white));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * 沉浸式布局
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



}
