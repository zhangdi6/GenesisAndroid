package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:
 * 创建日期:2018/5/26 on 9:35
 * 作者:JiaoPeiRong
 */
public class UserBean implements Serializable{

    /**
     * statusCode : 0
     * message : 登录成功
     * error : null
     * data : {"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"petsAmount":0,"lastHarvestAmount":0,"petsFrozen":0,"combatBase":0,"combat":0,"_id":10000026,"phone":"17601011006","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-05-26T16:06:01.626Z","updatedAt":"2018-05-26T16:26:25.113Z","__v":0,"name":"uu","password":"$2a$10$JvzhWb1hUNewypTPqd2fluRhaUpz5ZCJgQj4XeGZn2ka8NKOBR5BW","headImg":"/img/upload/userHeadImages/1527351985111_6.jpg"}
     * csrfToken : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiNDQ0NTU1IiwiaW52aXRlZENvZGUiOiLmiJHooqvpgoDor7fnoIEiLCJpbnZpdGVDb3VudCI6MCwicGV0c0Ftb3VudCI6MCwibGFzdEhhcnZlc3RBbW91bnQiOjAsInBldHNGcm96ZW4iOjAsImNvbWJhdEJhc2UiOjAsImNvbWJhdCI6MCwiX2lkIjoxMDAwMDAyNiwicGhvbmUiOiIxNzYwMTAxMTAwNiIsInZyZkNvZGUiOiLnlKjkuoblsLHov4fmnJ8iLCJjb3VudHJ5Q29kZSI6Ijg2IiwiY3JlYXRlZEF0IjoiMjAxOC0wNS0yNlQxNjowNjowMS42MjZaIiwidXBkYXRlZEF0IjoiMjAxOC0wNS0yNlQxNjoyNjoyNS4xMTNaIiwiX192IjowLCJuYW1lIjoidXUiLCJwYXNzd29yZCI6IiQyYSQxMCRKdnpoV2IxaFVOZXd5cFRQcWQyZmx1UmhhVXB6NVpDSmdRajRYZUdabjJrYThOS09CUjVCVyIsImhlYWRJbWciOiIvaW1nL3VwbG9hZC91c2VySGVhZEltYWdlcy8xNTI3MzUxOTg1MTExXzYuanBnIn0.Fk6qIYEdCvzhU62NORTZ4SqBtTe2R8jEmX7k-9e4XoE
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

    public static class DataBean implements Serializable{
        /**
         * vcSendTime : 0
         * registered : true
         * inviteCode : 444555
         * invitedCode : 我被邀请码
         * inviteCount : 0
         * petsAmount : 0
         * lastHarvestAmount : 0
         * petsFrozen : 0
         * combatBase : 0
         * combat : 0
         * _id : 10000026
         * phone : 17601011006
         * vrfCode : 用了就过期
         * countryCode : 86
         * createdAt : 2018-05-26T16:06:01.626Z
         * updatedAt : 2018-05-26T16:26:25.113Z
         * __v : 0
         * name : uu  --------弃用
         * password : $2a$10$JvzhWb1hUNewypTPqd2fluRhaUpz5ZCJgQj4XeGZn2ka8NKOBR5BW
         * headImg : /img/upload/userHeadImages/1527351985111_6.jpg  --------------弃用
         */

        private String vcSendTime;
        private boolean registered;
        private String inviteCode;
        private String invitedCode;
        private int inviteCount;
        private double petsAmount;
        private double lastHarvestAmount;
        private int petsFrozen;
        private int combatBase;
        private int combat;
        private int _id;
        private String phone;
        private String vrfCode;
        private String countryCode;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String name;
        private String password;
        private String headImg;
        private int bitfunId;
        private String realName;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getBitfunId() {
            return bitfunId;
        }

        public void setBitfunId(int bitfunId) {
            this.bitfunId = bitfunId;
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

        public double getPetsAmount() {
            return petsAmount;
        }

        public void setPetsAmount(double petsAmount) {
            this.petsAmount = petsAmount;
        }

        public double getLastHarvestAmount() {
            return lastHarvestAmount;
        }

        public void setLastHarvestAmount(double lastHarvestAmount) {
            this.lastHarvestAmount = lastHarvestAmount;
        }

        public int getPetsFrozen() {
            return petsFrozen;
        }

        public void setPetsFrozen(int petsFrozen) {
            this.petsFrozen = petsFrozen;
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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }
}
