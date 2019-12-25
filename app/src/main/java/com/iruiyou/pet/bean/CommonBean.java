package com.iruiyou.pet.bean;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/8/14 22:03
 * 邮箱：chinajpr@163.com
 */
public class CommonBean {

    /**
     * statusCode : 0
     * message : 保存成功
     * error : null
     * data : null
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private Object error;
    private Object data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
