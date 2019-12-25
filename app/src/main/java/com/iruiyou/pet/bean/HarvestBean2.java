package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：jiaopeirong on 2018/5/26 23:43 11
 * 邮箱：chinajpr@163.com
 */
public class HarvestBean2 {


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"userInfo":{"vcSendTime":1527178391994,"registered":true,"inviteCode":"123531","invitedCode":"000000","inviteCount":2,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":930,"combat":10,"headImg":"/img/upload/userHeadImages/userHead.png","_id":1,"countryCode":"86","phone":"13600000000","name":"00000000","password":"$2a$10$qIfzWPJqkE2fteXbgYCemOPiJNh2KlmfnJUeJEKcy/2Is9JRJAjPW","vrfCode":"763661","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-20T21:05:18+08:00","__v":0},"combatList":[{"time":1534503417369,"_id":"5b7abc8e3632621f590b8978","userId":1,"amount":10,"income":0,"type":"添加工作经验","createdAt":"2018-08-20T21:05:18+08:00","updatedAt":"2018-08-20T21:05:18+08:00","__v":0}]}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
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

    public static class DataBean {
        /**
         * userInfo : {"vcSendTime":1527178391994,"registered":true,"inviteCode":"123531","invitedCode":"000000","inviteCount":2,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":930,"combat":10,"headImg":"/img/upload/userHeadImages/userHead.png","_id":1,"countryCode":"86","phone":"13600000000","name":"00000000","password":"$2a$10$qIfzWPJqkE2fteXbgYCemOPiJNh2KlmfnJUeJEKcy/2Is9JRJAjPW","vrfCode":"763661","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-20T21:05:18+08:00","__v":0}
         * combatList : [{"time":1534503417369,"_id":"5b7abc8e3632621f590b8978","userId":1,"amount":10,"income":0,"type":"添加工作经验","createdAt":"2018-08-20T21:05:18+08:00","updatedAt":"2018-08-20T21:05:18+08:00","__v":0}]
         */

        private UserInfoBean userInfo;
        private List<CombatListBean> combatList;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<CombatListBean> getCombatList() {
            return combatList;
        }

        public void setCombatList(List<CombatListBean> combatList) {
            this.combatList = combatList;
        }

        public static class UserInfoBean {
            /**
             * vcSendTime : 1527178391994
             * registered : true
             * inviteCode : 123531
             * invitedCode : 000000
             * inviteCount : 2
             * pbsAmount : 0
             * lastHarvestAmount : 0
             * pbsFrozen : 930
             * combat : 10
             * headImg : /img/upload/userHeadImages/userHead.png
             * _id : 1
             * countryCode : 86
             * phone : 13600000000
             * name : 00000000
             * password : $2a$10$qIfzWPJqkE2fteXbgYCemOPiJNh2KlmfnJUeJEKcy/2Is9JRJAjPW
             * vrfCode : 763661
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-20T21:05:18+08:00
             * __v : 0
             */

            private long vcSendTime;
            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private String inviteCount;
            private String pbsAmount;
            private String lastHarvestAmount;
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

            public String getInviteCount() {
                return inviteCount;
            }

            public void setInviteCount(String inviteCount) {
                this.inviteCount = inviteCount;
            }

            public String getPbsAmount() {
                return pbsAmount;
            }

            public void setPbsAmount(String pbsAmount) {
                this.pbsAmount = pbsAmount;
            }

            public String getLastHarvestAmount() {
                return lastHarvestAmount;
            }

            public void setLastHarvestAmount(String lastHarvestAmount) {
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

        public static class CombatListBean {
            /**
             * time : 1534503417369
             * _id : 5b7abc8e3632621f590b8978
             * userId : 1
             * amount : 10
             * income : 0
             * type : 添加工作经验
             * createdAt : 2018-08-20T21:05:18+08:00
             * updatedAt : 2018-08-20T21:05:18+08:00
             * __v : 0
             */

            private long time;
            private String _id;
            private int userId;
            private double amount;
            private String income;
            private int type;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getIncome() {
                return income;
            }

            public void setIncome(String income) {
                this.income = income;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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
}
