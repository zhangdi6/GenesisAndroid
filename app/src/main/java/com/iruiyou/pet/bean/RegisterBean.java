package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 注册-校验验证码设置密码
 */
public class RegisterBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"vcSendTime":0,"registered":true,"inviteCode":"769060","invitedCode":"我被邀请码","inviteCount":0,"petsAmount":0,"lastHarvestAmount":0,"combatBase":0,"combat":0,"_id":10000018,"phone":"13111111111","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-05-24T14:52:25.991Z","updatedAt":"2018-05-24T14:52:53.273Z","__v":0,"password":"$2a$10$Vd2f/ouoM/DJldzQRFmL0.Tpf.nxVDgFNtqkiqn0TNNHX9.AuIVX2","name":"kk"}
     * csrfToken : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiNzY5MDYwIiwiaW52aXRlZENvZGUiOiLmiJHooqvpgoDor7fnoIEiLCJpbnZpdGVDb3VudCI6MCwicGV0c0Ftb3VudCI6MCwibGFzdEhhcnZlc3RBbW91bnQiOjAsImNvbWJhdEJhc2UiOjAsImNvbWJhdCI6MCwiX2lkIjoxMDAwMDAxOCwicGhvbmUiOiIxMzExMTExMTExMSIsInZyZkNvZGUiOiLnlKjkuoblsLHov4fmnJ8iLCJjb3VudHJ5Q29kZSI6Ijg2IiwiY3JlYXRlZEF0IjoiMjAxOC0wNS0yNFQxNDo1MjoyNS45OTFaIiwidXBkYXRlZEF0IjoiMjAxOC0wNS0yNFQxNDo1Mjo1My4yNzNaIiwiX192IjowLCJwYXNzd29yZCI6IiQyYSQxMCRWZDJmL291b00vREpsZHpRUkZtTDAuVHBmLm54VkRnRk50cWtpcW4wVE5OSFg5LkF1SVZYMiIsIm5hbWUiOiJrayJ9.mv5_ZEqqN57szvKinDRXhJKpoTJbOXyy-IGJC8RgEek
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

    public static class DataBean {
        /**
         * vcSendTime : 0
         * isRegistered : true
         * inviteCode : 769060
         * invitedCode : 我被邀请码
         * inviteCount : 0
         * petsAmount : 0
         * lastHarvestAmount : 0
         * combatBase : 0
         * combat : 0
         * _id : 10000018
         * phone : 13111111111
         * vrfCode : 用了就过期
         * countryCode : 86
         * createdAt : 2018-05-24T14:52:25.991Z
         * updatedAt : 2018-05-24T14:52:53.273Z
         * __v : 0
         * password : $2a$10$Vd2f/ouoM/DJldzQRFmL0.Tpf.nxVDgFNtqkiqn0TNNHX9.AuIVX2
         * name : kk
         */

        private String vcSendTime;
        private boolean isRegistered;
        private String inviteCode;
        private String invitedCode;
        private String inviteCount;

        @Override
        public String toString() {
            return "DataBean{" +
                    "vcSendTime='" + vcSendTime + '\'' +
                    ", registered=" + isRegistered +
                    ", inviteCode='" + inviteCode + '\'' +
                    ", invitedCode='" + invitedCode + '\'' +
                    ", inviteCount='" + inviteCount + '\'' +
                    ", petsAmount='" + petsAmount + '\'' +
                    ", lastHarvestAmount='" + lastHarvestAmount + '\'' +
                    ", combatBase='" + combatBase + '\'' +
                    ", combat='" + combat + '\'' +
                    ", _id=" + _id +
                    ", phone='" + phone + '\'' +
                    ", vrfCode='" + vrfCode + '\'' +
                    ", countryCode='" + countryCode + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", updatedAt='" + updatedAt + '\'' +
                    ", __v=" + __v +
                    ", password='" + password + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        private String petsAmount;
        private String lastHarvestAmount;
        private String combatBase;
        private String combat;
        private int _id;
        private String phone;
        private String vrfCode;



        public String getVcSendTime() {
            return vcSendTime;
        }

        public void setVcSendTime(String vcSendTime) {
            this.vcSendTime = vcSendTime;
        }

        public boolean isRegistered() {
            return isRegistered;
        }


        public void setRegistered(boolean registered) {
            this.isRegistered = registered;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String countryCode;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String password;
        private String name;


    }

    @Override
    public String toString() {
        return "RegisterBean{" +
                "statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", error=" + error +
                ", data=" + data +
                ", csrfToken=" + csrfToken +
                ", token='" + token + '\'' +
                '}';
    }
}
