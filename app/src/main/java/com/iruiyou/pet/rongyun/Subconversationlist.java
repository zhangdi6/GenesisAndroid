package com.iruiyou.pet.rongyun;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.SubconversationlistFragment;
import com.iruiyou.pet.utils.Constant;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class Subconversationlist extends BaseActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_subconversationlist);
//        setHeadVisibility(View.GONE);
//        setTitle("与" + "122" + "聊天中");
//        String realName = getIntent().getStringExtra("RealName");
//        int userId = getIntent().getIntExtra("userId",0);
//        ConversationListFragment listFragment = (ConversationListFragment) ConversationListFragment.instantiate(this, ConversationListFragment.class.getName());
//        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversationlist")
//                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
//                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
//                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
//                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
//                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
//                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
//                .build();
//        listFragment.setUri(uri);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        //将融云的Fragment界面加入到我们的页面。
//        transaction.add(R.id.subconversationlist, listFragment);
//        transaction.commitAllowingStateLoss();
//        //为了更加直观，服务器建立连接后进入此界面，直接调用如下代码，执行单人聊天，第二个参数代表对方用户ID，第三个参数代表聊天窗口标题，为了方便测试聊天，需要两个手机测试，所以登陆第一个token的用户与第二个用户"chao"建立聊天，在运行第二个手机之前，记得改"chao"的token登录，然后聊天这里改为第一个的ID"text"。
//        RongIM.getInstance().startPrivateChat(Subconversationlist.this, "dev"+String.valueOf(userId), realName);
//    }

    @Override
    public int getLayout() {
        return R.layout.activity_subconversationlist;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        setTitle(getResources().getString(R.string.chat));
        String realName = getIntent().getStringExtra("RealName");
        int userId = getIntent().getIntExtra("userId",0);
        SubconversationlistFragment listFragment = (SubconversationlistFragment) SubconversationlistFragment.instantiate(this, SubconversationlistFragment.class.getName());
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                .build();
        listFragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //将融云的Fragment界面加入到我们的页面。
        transaction.add(R.id.subconversationlist, listFragment);
        transaction.commitAllowingStateLoss();
        //为了更加直观，服务器建立连接后进入此界面，直接调用如下代码，执行单人聊天，第二个参数代表对方用户ID，第三个参数代表聊天窗口标题，为了方便测试聊天，需要两个手机测试，所以登陆第一个token的用户与第二个用户"chao"建立聊天，在运行第二个手机之前，记得改"chao"的token登录，然后聊天这里改为第一个的ID"text"。
        RongIM.getInstance().startPrivateChat(Subconversationlist.this, Constant.TARGETID+String.valueOf(userId), realName);
    }



}
