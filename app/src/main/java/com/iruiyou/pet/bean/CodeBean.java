package com.iruiyou.pet.bean;

/**
 * 作者：jiaopeirong on 2018/5/23 23:09
 * 邮箱：chinajpr@163.com
 */
public class CodeBean {


    /**
     * statusCode : 0
     * message : 验证码发送成功
     * error : null
     * data : 17601011006
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private Object error;
    private String data;
    private Object csrfToken;
    private Object token;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Object getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(Object csrfToken) {
        this.csrfToken = csrfToken;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}
