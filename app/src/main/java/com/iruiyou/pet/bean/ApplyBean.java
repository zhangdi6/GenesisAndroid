package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:添加好友
 * 创建日期:2018/10/16 on 17:50
 * 作者:sgf
 */
public class ApplyBean implements Serializable{


    /**
     * rcToken : null
     * csrfToken : null
     * data : null
     * message : 申请已发送
     * statusCode : 0
     * error : null
     * token : null
     */

    private String rcToken;
    private String csrfToken;
    private String data;
    private String message;
    private int statusCode;
    private String error;
    private String token;

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
