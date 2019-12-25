package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/9/7 09:59
 * 邮箱：chinajpr@163.com
 */
public class CombatRankingBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"vcSendTime":1535511726083,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0.05300000000000003,"lastHarvestAmount":0.001,"pbsFrozen":1,"combat":20,"headImg":"/img/upload/userHeadImages/1535704202874_7.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"张三","password":"$2a$10$knDMkRpnVuKvTgl4qaSLeOXKbv71WznenlkgmXhJ3m7icBsDZ/wxS","vrfCode":"270716","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-09-07T08:00:22+08:00","__v":0},{"vcSendTime":1527178391994,"registered":true,"inviteCode":"123531","invitedCode":"000000","inviteCount":2,"pbsAmount":0.17000000000000004,"lastHarvestAmount":0.14400000000000002,"pbsFrozen":930,"combat":10,"headImg":"/img/upload/userHeadImages/userHead.png","_id":1,"countryCode":"86","phone":"13600000000","name":"00000000","password":"$2a$10$qIfzWPJqkE2fteXbgYCemOPiJNh2KlmfnJUeJEKcy/2Is9JRJAjPW","vrfCode":"763661","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-09-07T08:00:22+08:00","__v":0}]
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private String error;
    private String csrfToken;
    private String token;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * vcSendTime : 1535511726083
         * registered : true
         * inviteCode : 444555
         * invitedCode : 我被邀请码
         * inviteCount : 0
         * pbsAmount : 0.05300000000000003
         * lastHarvestAmount : 0.001
         * pbsFrozen : 1
         * combat : 20
         * headImg : /img/upload/userHeadImages/1535704202874_7.jpg
         * _id : 26
         * countryCode : 86
         * phone : 17601011006
         * name : 张三
         * password : $2a$10$knDMkRpnVuKvTgl4qaSLeOXKbv71WznenlkgmXhJ3m7icBsDZ/wxS
         * vrfCode : 270716
         * createdAt : 2018-08-12T09:59:35+08:00
         * updatedAt : 2018-09-07T08:00:22+08:00
         * __v : 0
         */

        private long vcSendTime;
        private boolean registered;
        private String inviteCode;
        private String invitedCode;
        private int inviteCount;
        private double pbsAmount;
        private double lastHarvestAmount;
        private String pbsFrozen;
        private String combat;
        private String headImg;
        private int _id;
        private String countryCode;
        private String phone;
        private String name;
        private String password;
        private String vrfCode;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String company;
        private String position;
        private String realName;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public long getVcSendTime() {
            return vcSendTime;
        }

        public void setVcSendTime(long vcSendTime) {
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

        public double getPbsAmount() {
            return pbsAmount;
        }

        public void setPbsAmount(double pbsAmount) {
            this.pbsAmount = pbsAmount;
        }

        public double getLastHarvestAmount() {
            return lastHarvestAmount;
        }

        public void setLastHarvestAmount(double lastHarvestAmount) {
            this.lastHarvestAmount = lastHarvestAmount;
        }

        public String getPbsFrozen() {
            return pbsFrozen;
        }

        public void setPbsFrozen(String pbsFrozen) {
            this.pbsFrozen = pbsFrozen;
        }

        public String getCombat() {
            return combat;
        }

        public void setCombat(String combat) {
            this.combat = combat;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
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

        public String getVrfCode() {
            return vrfCode;
        }

        public void setVrfCode(String vrfCode) {
            this.vrfCode = vrfCode;
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
    }
}
