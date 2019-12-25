package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 类描述：我的好友
 * 作者：shenggaofei on 2018/10/16 13:44
 */
public class CheckFriends2Bean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"registers":[],"friends":[]}
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private Object message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private Object token;
    private Object rcToken;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
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

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(Object rcToken) {
        this.rcToken = rcToken;
    }

    public static class DataBean {
        private List<?> registers;
        private List<?> friends;

        public List<?> getRegisters() {
            return registers;
        }

        public void setRegisters(List<?> registers) {
            this.registers = registers;
        }

        public List<?> getFriends() {
            return friends;
        }

        public void setFriends(List<?> friends) {
            this.friends = friends;
        }
    }
}
