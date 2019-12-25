package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 币全发送验证码
 */
public class SendForDrawToBqexBean {


    /**
     * statusCode : 0
     * message : 验证码发送成功
     * error : null
     * data : {"countryCode":"86","phone":"15718807582"}
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
         * countryCode : 86
         * phone : 15718807582
         */

        private String countryCode;
        private String phone;

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
