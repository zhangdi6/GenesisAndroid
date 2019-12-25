package com.iruiyou.pet.bean;

/**
 * 通过好友申请
 * 作者：sgf on 2018/10/16 23:43 11
 */
public class AdoptBean {


    /**
     * rcToken : null
     * csrfToken : null
     * data : null
     * message : 添加好友成功
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

    public String getRcToken() {
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
