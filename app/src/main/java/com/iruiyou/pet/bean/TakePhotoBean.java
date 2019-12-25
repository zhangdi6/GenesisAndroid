package com.iruiyou.pet.bean;

/**
 * 类描述:
 * 创建日期:2018/5/25 on 16:52
 * 作者:JiaoPeiRong
 */
public class TakePhotoBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : /img/upload/petHeadImages/1527238109705_8.jpg
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
