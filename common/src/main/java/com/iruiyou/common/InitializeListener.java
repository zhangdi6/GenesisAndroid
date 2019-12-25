/*
 * Copyright (c) 2015. iruiyou.com
 *
 * CreatedBy: zhangpeng
 * CreatedDate:  2015/12/3
 */

package com.iruiyou.common;

/**
 * 初始化监听接口
 */
public interface InitializeListener {
    /**
     * 初始化完成回调
     *
     * @param errorCode
     * @param message
     */
    void onInitFinished(Constants.ErrorCodes errorCode, String message);
}
