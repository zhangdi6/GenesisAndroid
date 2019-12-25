package com.iruiyou.pet.rongyun;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.iruiyou.common.utils.T;
import com.iruiyou.pet.R;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.fragment.SubconversationlistFragment;

import io.rong.imkit.RongIM;
import io.rong.imkit.model.UIConversation;
import io.rong.imlib.model.Conversation;

/**
 * 聊天列表界面（会话）
 * Created by shenggaofei on 2018/10/15.
 */
public class ConversationListActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.conversationlist;
    }

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        //实现会话列表,如果想实现会话列表就加这个代码
//        String sName = getIntent().getData().getQueryParameter("title");//获取昵称
        setTitle(getResources().getString(R.string.chat));
        /**
         * 设置会话列表界面操作的监听器。
         */
        RongIM.setConversationListBehaviorListener(new MyConversationListBehaviorListener());

        SubconversationlistFragment fragment = new SubconversationlistFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话聚合显示
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")//设置讨论组会话非聚合显示
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "false")//设置系统会话非聚合显示
                .build();
        fragment.setUri(uri);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //rong_content 为你要加载的 id
        transaction.add(R.id.conversationlist, fragment);
        transaction.commit();

    }
    public class MyConversationListBehaviorListener implements RongIM.ConversationListBehaviorListener {
        @Override
        public boolean onConversationPortraitClick(Context context, Conversation.ConversationType conversationType, String s) {
            return false;
        }

        @Override
        public boolean onConversationPortraitLongClick(Context context, Conversation.ConversationType conversationType, String s) {
            return false;
        }

        @Override
        public boolean onConversationLongClick(Context context, View view, UIConversation uiConversation) {
            T.showShort("长按  onConversationLongClick");
            return false;
        }

        @Override
        public boolean onConversationClick(Context context, View view, UIConversation uiConversation) {
            T.showShort("点击 hahahahah");
            return false;
        }
    }

}
