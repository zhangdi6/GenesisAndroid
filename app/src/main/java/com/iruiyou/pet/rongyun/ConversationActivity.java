package com.iruiyou.pet.rongyun;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.iruiyou.common.http.task.UserTask;
import com.iruiyou.common.utils.GsonUtils;
import com.iruiyou.common.utils.SharePreferenceUtils;
import com.iruiyou.common.utils.T;
import com.iruiyou.http.retrofit_rx.exception.ApiException;
import com.iruiyou.http.retrofit_rx.listener.HttpOnNextListener;
import com.iruiyou.pet.R;
import com.iruiyou.pet.activity.utils.StartActivityManager;
import com.iruiyou.pet.base.BaseActivity;
import com.iruiyou.pet.bean.GroupMembersBean;
import com.iruiyou.pet.utils.Constant;
import com.iruiyou.pet.utils.StringUtil;

import java.util.List;
import java.util.Objects;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.UserInfo;

/**
 * 聊天界面（会话）
 * Created by shenggaofei on 2018/10/15.
 */
public class ConversationActivity extends BaseActivity implements RongIM.ConversationClickListener {

    private ImageView title_right_img;
    private String TAG = "ConversationActivity";
    private int number;
    private int readCompanyid;

    @Override
    public int getLayout() {
        return R.layout.conversation;
    }

    private final String GROUP_PATH = "conversation/group";
    private final String PRIVATE_PATH = "conversation/private";

    @Override
    public void OnActCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        Uri uri = intent.getData();
        String uriPath = Objects.requireNonNull(uri).getPath();//获取群聊或单聊的地址，判断区分  conversation/private 单聊，conversation/group群聊
        String sName = uri.getQueryParameter("title");//获取昵称
        title_right_img = findViewById(R.id.title_right_img);
        setTitle(sName);
        String targetId = uri.getQueryParameter("targetId");
        InitData();
        if (uriPath != null && uriPath.contains(GROUP_PATH)) {//判断单聊群聊，群聊按钮显示 反之隐藏
            title_right_img.setVisibility(View.VISIBLE);
            if(!TextUtils.isEmpty(targetId)){
                String[] split = targetId.split(Constant.DEVGROUPID);
                SharePreferenceUtils.getBaseSharePreference().saveCompanyid(Integer.parseInt(split[1]));
            }
            //显示群成员人数
            number = StringUtil.getData(sName);
            setTitle(sName + Constant.BRACKETS_LEFT + number + Constant.BRACKETS_RIGHT);
//            readCompanyid = SharePreferenceUtils.getBaseSharePreference().readCompanyid();
//            requestRecommendGroups();
            ///
        } else if (uriPath != null && uriPath.contains(PRIVATE_PATH)) {
            title_right_img.setVisibility(View.GONE);
        } else {
            title_right_img.setVisibility(View.GONE);
        }
        title_right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //群成员
                StartActivityManager.startGroupMembersActivity(ConversationActivity.this,number);
            }
        });
    }
    private void InitData() {
        //实现 界面ui监听接口
        RongIM.setConversationClickListener(this);
    }

    /**
     * 成员信息
     */
    private void requestRecommendGroups() {

        /**
         * 获取群成员的信息
         */
        new UserTask(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                if(StringUtil.isNotEmpty(resulte))
                {
                    GroupMembersBean groupMembersBean = GsonUtils.parseJson(resulte, GroupMembersBean.class);
                    if(groupMembersBean != null)
                    {
                        if (groupMembersBean.getStatusCode() == Constant.SUCCESS) {

                            if (groupMembersBean.getData() != null) {
                                List<GroupMembersBean.DataBean> data = groupMembersBean.getData();
                                for (int i = 0; i < data.size(); i++) {
                                    GroupMembersBean.DataBean.BasicInfoBean basicInfo = data.get(i).getBasicInfo();
                                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(Constant.TARGETID + basicInfo.getUserId() + "", basicInfo.getRealName() + "", Uri.parse(basicInfo.getHeadImg()))); // 提供一个人信息
                                }
                            }

                        } else if(StringUtil.isNotEmpty(groupMembersBean.getMessage())){
                            T.showShort(groupMembersBean.getMessage());
                        }
                    }
                }
            }

            @Override
            public void onError(ApiException e) {
                T.showShort(e.getMessage());
            }
        }, this).getGroupMembers(readCompanyid, 0);
    }


    /**
     * 当长按用户头像后执行。
     *
     * @param context          上下文。
     * @param conversationType 会话类型。
     * @param user             被点击的用户的信息。
     * @param targetId         会话 id
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
     */
    @Override
    public boolean onUserPortraitLongClick(Context context, Conversation.ConversationType conversationType, UserInfo user, String targetId) {
        return false;
    }

    /**
     * 当点击用户头像后执行。
     *
     * @param context          上下文。
     * @param conversationType 会话类型。
     * @param userInfo         被点击的用户的信息。
     * @param targetId         会话 id
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
     */
    @Override
    public boolean onUserPortraitClick(Context context, Conversation.ConversationType conversationType, UserInfo userInfo, String targetId) {
//        Toast.makeText(ConversationActivity.this, userInfo.getUserId(), Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 当点击消息时执行。
     *
     * @param context 上下文。
     * @param view    触发点击的 View。
     * @param message 被点击的消息的实体信息。
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
     */
    @Override
    public boolean onMessageClick(Context context, View view, Message message) {
//        L.d(TAG, "onMessageClick---conversationType=" + message.getConversationType().getName());
        return false;
    }

    /**
     * 当点击链接消息时执行。
     *
     * @param context 上下文。
     * @param link    被点击的链接。
     * @param message 被点击的消息的实体信息
     * @return 如果用户自己处理了点击后的逻辑处理，则返回 true， 否则返回 false, false 走融云默认处理方式。
     */
    @Override
    public boolean onMessageLinkClick(Context context, String link, Message message) {
        return false;
    }

    /**
     * 当长按消息时执行。
     *
     * @param context 上下文。
     * @param view    触发点击的 View。
     * @param message 被长按的消息的实体信息。
     * @return 如果用户自己处理了长按后的逻辑处理，则返回 true，否则返回 false，false 走融云默认处理方式。
     */
    @Override
    public boolean onMessageLongClick(Context context, View view, Message message) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
