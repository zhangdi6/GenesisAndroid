package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 注册-发送验证码
 */
public class SendForSignupBean {
    /**
     * csrfToken : null
     * data : {"countryCode":"86","phone":"15718807582"}
     * message : 验证码发送成功
     * statusCode : 0
     * error : null
     * token : null
     */

    private Object csrfToken;
    private DataBean data;
    private String message;
    private int statusCode;
    private Object error;
    private Object token;

    public Object getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(Object csrfToken) {
        this.csrfToken = csrfToken;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * countryCode : 86
         * phone : 15718807582
         */

        private String countryCode;
        private String phone;
        private String vrfCode;

        public String getVrfCode() {
            return vrfCode;
        }

        public void setVrfCode(String vrfCode) {
            this.vrfCode = vrfCode;
        }

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


    /**
     * statusCode : 0
     * message : 验证码发送成功
     * error : null
     * data : 17601011006
     * csrfToken : null
     * token : null
     */


}
