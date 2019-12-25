package com.iruiyou.pet.bean;

/**
 * 类描述:
 * 创建日期:2018/5/31 on 10:33
 * 作者:JiaoPeiRong
 */
public class YzTokenBean {

    /**
     * statusCode : 0
     * message : success
     * error : null
     * data : {"access_token":"5a880f313b90391ca528fb45ba74908c","cookie_key":null,"cookie_value":null}
     * csrfToken : null
     * token : null
     */


    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * access_token : 5a880f313b90391ca528fb45ba74908c
         * cookie_key : null
         * cookie_value : null
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