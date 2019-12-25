package com.iruiyou.pet.bean;

/**
 * 作者：jiaopeirong on 2018/5/24 23:23
 * 邮箱：chinajpr@163.com
 */
public class SetPwdBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"vcSendTime":0,"registered":true,"inviteCode":"361654","invitedCode":"我被邀请码","inviteCount":0,"petsAmount":0,"lastHarvestAmount":0,"combatBase":0,"combat":0,"_id":10000019,"phone":"13222222222","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-05-24T15:01:33.571Z","updatedAt":"2018-05-24T15:22:33.079Z","__v":0,"name":"kkk","password":"$2a$10$g50LTDLTGjVnnNWx1qi97O9t31Tv7xk9AzVhncgxyGcfjxlWPs2zG"}
     * csrfToken : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiMzYxNjU0IiwiaW52aXRlZENvZGUiOiLmiJHooqvpgoDor7fnoIEiLCJpbnZpdGVDb3VudCI6MCwicGV0c0Ftb3VudCI6MCwibGFzdEhhcnZlc3RBbW91bnQiOjAsImNvbWJhdEJhc2UiOjAsImNvbWJhdCI6MCwiX2lkIjoxMDAwMDAxOSwicGhvbmUiOiIxMzIyMjIyMjIyMiIsInZyZkNvZGUiOiLnlKjkuoblsLHov4fmnJ8iLCJjb3VudHJ5Q29kZSI6Ijg2IiwiY3JlYXRlZEF0IjoiMjAxOC0wNS0yNFQxNTowMTozMy41NzFaIiwidXBkYXRlZEF0IjoiMjAxOC0wNS0yNFQxNToyMjozMy4wNzlaIiwiX192IjowLCJuYW1lIjoia2trIiwicGFzc3dvcmQiOiIkMmEkMTAkZzUwTFRETFRHalZubk5XeDFxaTk3Tzl0MzFUdjd4azlBelZobmNneHlHY2ZqeGxXUHMyekcifQ.E5m5fVDB9ybt-lKs8QYfmDiHjMNZClmoNXNCIIxEQ1E
     */

    private int statusCode;
    private Object message;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
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
         * updatedAt : 2018-05-24T15:22:33.079Z
         * __v : 0
         * name : kkk
         * password : $2a$10$g50LTDLTGjVnnNWx1qi97O9t31Tv7xk9AzVhncgxyGcfjxlWPs2zG
         */

        private String vcSendTime;
        private boolean registered;
        private String inviteCode;
        private String invitedCode;
        private String inviteCount;
        private String petsAmount;
        private String lastHarvestAmount;
        private String combatBase;
        private String combat;
        private int _id;
        private String phone;
        private String vrfCode;
        private String countryCode;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String name;
        private String password;

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

        public String getInviteCount() {
            return inviteCount;
        }

        public void setInviteCount(String inviteCount) {
            this.inviteCount = inviteCount;
        }

        public String getPetsAmount() {
            return petsAmount;
        }

        public void setPetsAmount(String petsAmount) {
            this.petsAmount = petsAmount;
        }

        public String getLastHarvestAmount() {
            return lastHarvestAmount;
        }

        public void setLastHarvestAmount(String lastHarvestAmount) {
            this.lastHarvestAmount = lastHarvestAmount;
        }

        public String getCombatBase() {
            return combatBase;
        }

        public void setCombatBase(String combatBase) {
            this.combatBase = combatBase;
        }

        public String getCombat() {
            return combat;
        }

        public void setCombat(String combat) {
            this.combat = combat;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
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
    }
}
