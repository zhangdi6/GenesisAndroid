package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:注册邀请码
 * 作者：Created by sgf
 */

public class SetInvitedCodeBean implements Serializable {
    /**
     * "rcToken":"hAz7nJw99V4jNI6Aq2yGSVo641V9qlzPAsoAIiRtaKUnMG7LtmrGdaNliWfkZIwzsK1wZz/gqbDHBDrZSGX6Hg=="
     * csrfToken : null
     * data : {"statisticsInfo":{"updatedAt":"2018-09-29T11:45:33+08:00","_id":"5baef55d1e37115db0b8de68","basicCount":0,"createdAt":"2018-09-29T11:45:33+08:00","workCount":0,"educationCount":0,"userId":53,"__v":0,"photoCount":0,"blockchainCount":0,"userInfo":53},"userInfo":{"phone":"15718807582","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5baef55d1e37115db0b8de68","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$LUn54WIwzc5A8kGaImPaJOvrnn2OlxgGAhhgwQOQWJ0vmjy0iXOke","updatedAt":"2018-09-29T11:45:33+08:00","basicInfo":"5baef55d1e37115db0b8de69","vcSendTime":0,"_id":53,"createdAt":"2018-09-29T11:44:09+08:00","registered":true,"inviteCode":"490824","combat":0,"clientIp":"221.219.205.164","lastHarvestAmount":0,"invitedCode":"519114"},"basicInfo":{"updatedAt":"2018-09-29T11:45:33+08:00","_id":"5baef55d1e37115db0b8de69","professionalIdentity":0,"createdAt":"2018-09-29T11:45:33+08:00","userId":53,"__v":0,"gender":0,"showEdit":0,"userInfo":53},"infoCount":4}
     * message : null
     * statusCode : 0
     * error : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiNDkwODI0IiwiaW52aXRlZENvZGUiOiI1MTkxMTQiLCJpbnZpdGVDb3VudCI6MCwicGJzQW1vdW50IjowLCJsYXN0SGFydmVzdEFtb3VudCI6MCwicGJzRnJvemVuIjowLCJjb21iYXQiOjAsIl9pZCI6NTMsInBob25lIjoiMTU3MTg4MDc1ODIiLCJ2cmZDb2RlIjoi55So5LqG5bCx6L-H5pyfIiwiY291bnRyeUNvZGUiOiI4NiIsImNyZWF0ZWRBdCI6IjIwMTgtMDktMjlUMTE6NDQ6MDkrMDg6MDAiLCJ1cGRhdGVkQXQiOiIyMDE4LTA5LTI5VDExOjQ1OjMzKzA4OjAwIiwiX192IjowLCJwYXNzd29yZCI6IiQyYSQxMCRMVW41NFdJd3pjNUE4a0dhSW1QYUpPdnJubjJPbHhnR0FoaGd3UU9RV0owdm1qeTBpWE9rZSIsImNsaWVudElwIjoiMjIxLjIxOS4yMDUuMTY0IiwiYmFzaWNJbmZvIjoiNWJhZWY1NWQxZTM3MTE1ZGIwYjhkZTY5Iiwic3RhdGlzdGljc0luZm8iOiI1YmFlZjU1ZDFlMzcxMTVkYjBiOGRlNjgifQ.NWLEyUn3OYi74LDp2EmiLr-z5kdN5BxsBohrkAP26OQ
     */

    private String csrfToken;
    private String rcToken;
    private DataBean data;
    private String message;
    private int statusCode;
    private String error;
    private String token;

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * statisticsInfo : {"updatedAt":"2018-09-29T11:45:33+08:00","_id":"5baef55d1e37115db0b8de68","basicCount":0,"createdAt":"2018-09-29T11:45:33+08:00","workCount":0,"educationCount":0,"userId":53,"__v":0,"photoCount":0,"blockchainCount":0,"userInfo":53}
         * userInfo : {"phone":"15718807582","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5baef55d1e37115db0b8de68","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$LUn54WIwzc5A8kGaImPaJOvrnn2OlxgGAhhgwQOQWJ0vmjy0iXOke","updatedAt":"2018-09-29T11:45:33+08:00","basicInfo":"5baef55d1e37115db0b8de69","vcSendTime":0,"_id":53,"createdAt":"2018-09-29T11:44:09+08:00","registered":true,"inviteCode":"490824","combat":0,"clientIp":"221.219.205.164","lastHarvestAmount":0,"invitedCode":"519114"}
         * basicInfo : {"updatedAt":"2018-09-29T11:45:33+08:00","_id":"5baef55d1e37115db0b8de69","professionalIdentity":0,"createdAt":"2018-09-29T11:45:33+08:00","userId":53,"__v":0,"gender":0,"showEdit":0,"userInfo":53}
         * infoCount : 4
         */

        private StatisticsInfoBean statisticsInfo;
        private UserInfoBean userInfo;
        private BasicInfoBean basicInfo;
        private int infoCount;

        public StatisticsInfoBean getStatisticsInfo() {
            return statisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean statisticsInfo) {
            this.statisticsInfo = statisticsInfo;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public static class StatisticsInfoBean {
            /**
             * updatedAt : 2018-09-29T11:45:33+08:00
             * _id : 5baef55d1e37115db0b8de68
             * basicCount : 0
             * createdAt : 2018-09-29T11:45:33+08:00
             * workCount : 0
             * educationCount : 0
             * userId : 53
             * __v : 0
             * photoCount : 0
             * blockchainCount : 0
             * userInfo : 53
             */

            private String updatedAt;
            private String _id;
            private int basicCount;
            private String createdAt;
            private int workCount;
            private int educationCount;
            private int userId;
            private int __v;
            private int photoCount;
            private int blockchainCount;
            private int userInfo;

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getBasicCount() {
                return basicCount;
            }

            public void setBasicCount(int basicCount) {
                this.basicCount = basicCount;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getWorkCount() {
                return workCount;
            }

            public void setWorkCount(int workCount) {
                this.workCount = workCount;
            }

            public int getEducationCount() {
                return educationCount;
            }

            public void setEducationCount(int educationCount) {
                this.educationCount = educationCount;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public int getPhotoCount() {
                return photoCount;
            }

            public void setPhotoCount(int photoCount) {
                this.photoCount = photoCount;
            }

            public int getBlockchainCount() {
                return blockchainCount;
            }

            public void setBlockchainCount(int blockchainCount) {
                this.blockchainCount = blockchainCount;
            }

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
            }
        }

        public static class UserInfoBean {
            /**
             * phone : 15718807582
             * pbsFrozen : 0
             * pbsAmount : 0
             * inviteCount : 0
             * statisticsInfo : 5baef55d1e37115db0b8de68
             * __v : 0
             * countryCode : 86
             * vrfCode : 用了就过期
             * password : $2a$10$LUn54WIwzc5A8kGaImPaJOvrnn2OlxgGAhhgwQOQWJ0vmjy0iXOke
             * updatedAt : 2018-09-29T11:45:33+08:00
             * basicInfo : 5baef55d1e37115db0b8de69
             * vcSendTime : 0
             * _id : 53
             * createdAt : 2018-09-29T11:44:09+08:00
             * registered : true
             * inviteCode : 490824
             * combat : 0
             * clientIp : 221.219.205.164
             * lastHarvestAmount : 0
             * invitedCode : 519114
             */

            private String phone;
            private String pbsFrozen;
            private int pbsAmount;
            private int inviteCount;
            private String statisticsInfo;
            private int __v;
            private String countryCode;
            private String vrfCode;
            private String password;
            private String updatedAt;
            private String basicInfo;
            private int vcSendTime;
            private String _id;
            private String createdAt;
            private boolean registered;
            private String inviteCode;
            private int combat;
            private String clientIp;
            private int lastHarvestAmount;
            private String invitedCode;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getPbsFrozen() {
                return pbsFrozen;
            }

            public void setPbsFrozen(String pbsFrozen) {
                this.pbsFrozen = pbsFrozen;
            }

            public int getPbsAmount() {
                return pbsAmount;
            }

            public void setPbsAmount(int pbsAmount) {
                this.pbsAmount = pbsAmount;
            }

            public int getInviteCount() {
                return inviteCount;
            }

            public void setInviteCount(int inviteCount) {
                this.inviteCount = inviteCount;
            }

            public String getStatisticsInfo() {
                return statisticsInfo;
            }

            public void setStatisticsInfo(String statisticsInfo) {
                this.statisticsInfo = statisticsInfo;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getVrfCode() {
                return vrfCode;
            }

            public void setVrfCode(String vrfCode) {
                this.vrfCode = vrfCode;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getBasicInfo() {
                return basicInfo;
            }

            public void setBasicInfo(String basicInfo) {
                this.basicInfo = basicInfo;
            }

            public int getVcSendTime() {
                return vcSendTime;
            }

            public void setVcSendTime(int vcSendTime) {
                this.vcSendTime = vcSendTime;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
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

            public int getCombat() {
                return combat;
            }

            public void setCombat(int combat) {
                this.combat = combat;
            }

            public String getClientIp() {
                return clientIp;
            }

            public void setClientIp(String clientIp) {
                this.clientIp = clientIp;
            }

            public int getLastHarvestAmount() {
                return lastHarvestAmount;
            }

            public void setLastHarvestAmount(int lastHarvestAmount) {
                this.lastHarvestAmount = lastHarvestAmount;
            }

            public String getInvitedCode() {
                return invitedCode;
            }

            public void setInvitedCode(String invitedCode) {
                this.invitedCode = invitedCode;
            }
        }

        public static class BasicInfoBean {
            /**
             * updatedAt : 2018-09-29T11:45:33+08:00
             * _id : 5baef55d1e37115db0b8de69
             * professionalIdentity : 0
             * createdAt : 2018-09-29T11:45:33+08:00
             * userId : 53
             * __v : 0
             * gender : 0
             * showEdit : 0
             * userInfo : 53
             */

            private String position;
            private int professionalIdentity;
            private int __v;
            private int number;
            private int education;
            private String country;
            private String updatedAt;
            private String headImg;
            private String school;
            private String selfDesc;
            private String nature;
            private String _id;
            private String createdAt;
            private String company;
            private int userId;
            private int gender;
            private String realName;
            private int showEdit = 0;
            private int userInfo;

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getProfessionalIdentity() {
                return professionalIdentity;
            }

            public void setProfessionalIdentity(int professionalIdentity) {
                this.professionalIdentity = professionalIdentity;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getShowEdit() {
                return showEdit;
            }

            public void setShowEdit(int showEdit) {
                this.showEdit = showEdit;
            }

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getSelfDesc() {
                return selfDesc;
            }

            public void setSelfDesc(String selfDesc) {
                this.selfDesc = selfDesc;
            }

            public String getNature() {
                return nature;
            }

            public void setNature(String nature) {
                this.nature = nature;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
            }
        }
    }


    /**
     * csrfToken : null
     * data : null
     * message : 该用户已激活，不可操作
     * statusCode : -1
     * error : null
     * token : null
     */



//    private Object csrfToken;
//    private Object data;
//    private String message;
//    private int statusCode;
//    private Object error;
//    private Object token;
//
//    public Object getCsrfToken() {
//        return csrfToken;
//    }
//
//    public void setCsrfToken(Object csrfToken) {
//        this.csrfToken = csrfToken;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public int getStatusCode() {
//        return statusCode;
//    }
//
//    public void setStatusCode(int statusCode) {
//        this.statusCode = statusCode;
//    }
//
//    public Object getError() {
//        return error;
//    }
//
//    public void setError(Object error) {
//        this.error = error;
//    }
//
//    public Object getToken() {
//        return token;
//    }
//
//    public void setToken(Object token) {
//        this.token = token;
//    }
}
