package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 作者：sgf
 * 有赞登陆
 */
public class YzLoginBean implements Serializable{

    /**
     * statusCode : 0
     * message : 登录成功
     * error : null
     * data : {"access_token":"2b2e3fe8c4093e7a82a50d49563b8373","cookie_key":"open_cookie_d6d97a8ec5c15acb55","cookie_value":"YZ509824202669420544YZ5SkfHCxj"}
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * access_token : 2b2e3fe8c4093e7a82a50d49563b8373
         * cookie_key : open_cookie_d6d97a8ec5c15acb55
         * cookie_value : YZ509824202669420544YZ5SkfHCxj
         */

        private String access_token;
        private String cookie_key;
        private String cookie_value;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getCookie_key() {
            return cookie_key;
        }

        public void setCookie_key(String cookie_key) {
            this.cookie_key = cookie_key;
        }

        public String getCookie_value() {
            return cookie_value;
        }

        public void setCookie_value(String cookie_value) {
            this.cookie_value = cookie_value;
        }
    }
}
