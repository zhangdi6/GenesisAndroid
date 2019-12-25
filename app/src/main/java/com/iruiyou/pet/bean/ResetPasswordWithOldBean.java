package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述: 重置密码
 * sgf
 */

public class ResetPasswordWithOldBean implements Serializable {

    /**
     * csrfToken : null
     * data : null
     * message : 密码设置成功
     * statusCode : 0
     * error : null
     * token : null
     */

    private String csrfToken;
    private String data;
    private String message;
    private int statusCode;
    private String error;
    private String token;

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
