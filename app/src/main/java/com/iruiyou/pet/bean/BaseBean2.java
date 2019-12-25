package com.iruiyou.pet.bean;

import java.util.List;

public class BaseBean2 {

    private int statusCode;
    private String message;
    private String error;
    private List<CourseLessonBean> data;
    private String csrfToken;
    private String token;
    private String rcToken;

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

    public List<CourseLessonBean> getData() {
        return data;
    }

    public void setData(List<CourseLessonBean> data) {
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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }


    @Override
    public String toString() {
        return "BaseBean2{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", error='" + error + '\'' +
                ", data=" + data +
                ", csrfToken='" + csrfToken + '\'' +
                ", token='" + token + '\'' +
                ", rcToken='" + rcToken + '\'' +
                '}';
    }
}
