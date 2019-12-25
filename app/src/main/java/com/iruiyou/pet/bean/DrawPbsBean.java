package com.iruiyou.pet.bean;

/**
 * 提取资产接口
 * sgf
 */
public class DrawPbsBean {


    /**
     * statusCode : -1
     * message : 最小提币数量2000PBS
     * error : null
     * data : null
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private String error;
    private Object data;
    private String csrfToken;
    private String token;
    private Object rcToken;

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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(Object rcToken) {
        this.rcToken = rcToken;
    }
}


