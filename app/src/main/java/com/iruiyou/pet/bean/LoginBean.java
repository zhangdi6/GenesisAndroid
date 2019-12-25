package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:
 * 作者：Created by JiaoPeiRong on 2017/5/7 13:42
 * 邮箱：chinajpr@163.com
 */

public class LoginBean implements Serializable {

    /**
     * statusCode : 0
     * message : 登录成功
     * error : null
     * data : {"vcSendTime":0,"registered":true,"inviteCode":"361654","invitedCode":"我被邀请码","inviteCount":0,"petsAmount":0,"lastHarvestAmount":0,"combatBase":0,"combat":0,"_id":10000019,"phone":"13222222222","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-05-24T15:01:33.571Z","updatedAt":"2018-05-24T15:01:49.642Z","__v":0,"name":"kkk","password":"$2a$10$ZKrBpMizkNUG0bM1hV.0AOIoZFmwriXetx53yGU/eOMlxOMcV8ovS"}
     * csrfToken : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiMzYxNjU0IiwiaW52aXRlZENvZGUiOiLmiJHooqvpgoDor7fnoIEiLCJpbnZpdGVDb3VudCI6MCwicGV0c0Ftb3VudCI6MCwibGFzdEhhcnZlc3RBbW91bnQiOjAsImNvbWJhdEJhc2UiOjAsImNvbWJhdCI6MCwiX2lkIjoxMDAwMDAxOSwicGhvbmUiOiIxMzIyMjIyMjIyMiIsInZyZkNvZGUiOiLnlKjkuoblsLHov4fmnJ8iLCJjb3VudHJ5Q29kZSI6Ijg2IiwiY3JlYXRlZEF0IjoiMjAxOC0wNS0yNFQxNTowMTozMy41NzFaIiwidXBkYXRlZEF0IjoiMjAxOC0wNS0yNFQxNTowMTo0OS42NDJaIiwiX192IjowLCJuYW1lIjoia2trIiwicGFzc3dvcmQiOiIkMmEkMTAkWktyQnBNaXprTlVHMGJNMWhWLjBBT0lvWkZtd3JpWGV0eDUzeUdVL2VPTWx4T01jVjhvdlMifQ.Ey6GHqqLW6AJx8Xjndmz_jYVZjmUQi6HBM5Fqs5Xo_c
     */

    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean implements Serializable {
        /**
         * vcSendTime : 0
         * registered : true
         * inviteCode : 361654
         * invitedCode : 我被邀请码
         * inviteCount : 0
         * petsAmount : 0
         * lastHarvestAmount : 0
         * combatBase : 0
         * combat : 0
         * _id : 10000019
         * phone : 13222222222
         * vrfCode : 用了就过期
         * countryCode : 86
         * createdAt : 2018-05-24T15:01:33.571Z
         * updatedAt : 2018-05-24T15:01:49.642Z
         * __v : 0
         * name : kkk
         * password : $2a$10$ZKrBpMizkNUG0bM1hV.0AOIoZFmwriXetx53yGU/eOMlxOMcV8ovS
         */

        private String vcSendTime;
        private boolean registered;
        private String inviteCode;
        private String invitedCode;
        private int inviteCount;
        private String petsAmount;
        private double lastHarvestAmount;
        private int combatBase;
        private int combat;
        private String _id;
        private String phone;
        private String vrfCode;
        private String countryCode;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String name;
        private String password;
        private String realName;
        private String userChannel;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getVcSendTime() {
            return vcSendTime;
        }

        public void setVcSendTime(String vcSendTime) {
            this.vcSendTime = vcSendTime;
        }

        public boolean isRegistered() {
            return registered;
        }

        public void setRegistered(boolean registered) {
            this.registered = registered;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getInvitedCode() {
            return invitedCode;
        }

        public void setInvitedCode(String invitedCode) {
            this.invitedCode = invitedCode;
        }

        public int getInviteCount() {
            return inviteCount;
        }

        public void setInviteCount(int inviteCount) {
            this.inviteCount = inviteCount;
        }

        public String getPetsAmount() {
            return petsAmount;
        }

        public void setPetsAmount(String petsAmount) {
            this.petsAmount = petsAmount;
        }

        public double getLastHarvestAmount() {
            return lastHarvestAmount;
        }

        public void setLastHarvestAmount(double lastHarvestAmount) {
            this.lastHarvestAmount = lastHarvestAmount;
        }

        public int getCombatBase() {
            return combatBase;
        }

        public void setCombatBase(int combatBase) {
            this.combatBase = combatBase;
        }

        public int getCombat() {
            return combat;
        }

        public void setCombat(int combat) {
            this.combat = combat;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

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

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
        public String getUserChannel() {
            return userChannel;
        }

        public void setUserChannel(String userChannel) {
            this.userChannel = userChannel;
        }

    }
}
