package com.iruiyou.pet.bean;

/**
 * 无参数通用事件
 */
public class BroadEvent {

    /**
     * 刷新钱包
     */
    public static final String ACTION_REFRESH_WALLET="refresh_wallet";

    private String action;
    public BroadEvent(String action){
        this.action=action;
    }

    public String getAction() {
        return action;
    }
}
