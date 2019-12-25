package com.iruiyou.http.retrofit_rx.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * 类描述：继承自EventBus的工具类
 * 项目版本：2.0
 * 作者：JiaoPeiRong on 2017/2/20 11:48
 * 邮箱：mail.dfht.com
 */
public class EventBusUtils extends EventBus {

    public static EventBusUtils getInstance(){
        return new EventBusUtils();
    }
    /**
     * 注册
     * @param context
     */
    public void register(Object context){
        EventBus.getDefault().register(context);
    }

    /**
     * 取消注册
     * @param context
     */
    public void unregister(Object context){
        EventBus.getDefault().unregister(context);
    }

    /**
     * 传递事件
     * @param t
     * @param <T>
     */
    public <T> void postEvent(T t){
        EventBus.getDefault().post(t);
    }

    //接收事件
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onShowMessageEvent(T t) {
//        mMessageView.setText("Message from SecondActivity:" + messageEvent.getMessage());
//    }
}
