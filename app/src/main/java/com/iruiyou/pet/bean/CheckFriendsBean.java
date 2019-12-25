package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 类描述：我的好友
 * 作者：shenggaofei on 2018/10/16 13:44
 */
public class CheckFriendsBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"_id":"5bc5b2abed27304f781b50ce","userIdA":67,"userIdB":43,"userInfoA":{"vcSendTime":0,"registered":true,"inviteCode":"780361","invitedCode":"123531","inviteCount":0,"pbsAmount":0.016,"lastHarvestAmount":0.012,"pbsFrozen":0,"combat":60,"_id":67,"phone":"15718807582","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-10-12T10:56:05+08:00","updatedAt":"2018-10-18T11:33:42+08:00","__v":0,"password":"$2a$10$ml.KDMYpVfaACCzEHoyWyuQ0o7jKLQsO0p45Sel.stlpA2nli6LNC","basicInfo":"5bc00d61c47e7c734ecc365f","clientIp":"221.221.234.77","statisticsInfo":"5bc00d61c47e7c734ecc365e","headImg":"/img/upload/userHeadImages/1539691122610_8.jpg"},"userInfoB":{"vcSendTime":0,"registered":true,"inviteCode":"944495","invitedCode":"123531","inviteCount":0,"pbsAmount":0.04200000000000002,"lastHarvestAmount":0.04200000000000002,"pbsFrozen":0,"combat":20,"_id":43,"phone":"13699150275","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-09-21T13:52:10+08:00","updatedAt":"2018-10-18T15:20:09+08:00","__v":0,"password":"$2a$10$CqjeHeAHssoc28rzEhFoy.msqyH9l9rqWXVGImlVeTodXVXGuIDSC","basicInfo":"5ba48955677db955ec6394cb","statisticsInfo":"5ba48955677db955ec6394ca","headImg":"/img/upload/userHeadImages/1538035801797_0.jpg"},"basicInfoA":{"gender":0,"professionalIdentity":-1,"showEdit":0,"_id":"5bc00d61c47e7c734ecc365f","userId":67,"createdAt":"2018-10-12T10:56:33+08:00","updatedAt":"2018-10-18T11:33:41+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1539691122610_8.jpg","realName":"Sheng","company":"3q3","position":"3323","school":"323"},"basicInfoB":{"gender":0,"professionalIdentity":999,"showEdit":1,"_id":"5ba48955677db955ec6394cb","userId":43,"userInfo":43,"createdAt":"2018-09-21T14:01:57+08:00","updatedAt":"2018-09-27T16:10:01+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1538035801797_0.jpg","realName":"邀请码如图"},"statusA2B":3,"statusB2A":3,"timeA2B":0,"timeB2A":1539857506220,"createdAt":"2018-10-16T17:43:07+08:00","updatedAt":"2018-10-18T18:12:52+08:00","__v":0}]
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private String error;
    private String csrfToken;
    private String token;
    private String rcToken;
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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * _id : 5bc5b2abed27304f781b50ce
         * userIdA : 67
         * userIdB : 43
         * userInfoA : {"vcSendTime":0,"registered":true,"inviteCode":"780361","invitedCode":"123531","inviteCount":0,"pbsAmount":0.016,"lastHarvestAmount":0.012,"pbsFrozen":0,"combat":60,"_id":67,"phone":"15718807582","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-10-12T10:56:05+08:00","updatedAt":"2018-10-18T11:33:42+08:00","__v":0,"password":"$2a$10$ml.KDMYpVfaACCzEHoyWyuQ0o7jKLQsO0p45Sel.stlpA2nli6LNC","basicInfo":"5bc00d61c47e7c734ecc365f","clientIp":"221.221.234.77","statisticsInfo":"5bc00d61c47e7c734ecc365e","headImg":"/img/upload/userHeadImages/1539691122610_8.jpg"}
         * userInfoB : {"vcSendTime":0,"registered":true,"inviteCode":"944495","invitedCode":"123531","inviteCount":0,"pbsAmount":0.04200000000000002,"lastHarvestAmount":0.04200000000000002,"pbsFrozen":0,"combat":20,"_id":43,"phone":"13699150275","vrfCode":"用了就过期","countryCode":"86","createdAt":"2018-09-21T13:52:10+08:00","updatedAt":"2018-10-18T15:20:09+08:00","__v":0,"password":"$2a$10$CqjeHeAHssoc28rzEhFoy.msqyH9l9rqWXVGImlVeTodXVXGuIDSC","basicInfo":"5ba48955677db955ec6394cb","statisticsInfo":"5ba48955677db955ec6394ca","headImg":"/img/upload/userHeadImages/1538035801797_0.jpg"}
         * basicInfoA : {"gender":0,"professionalIdentity":-1,"showEdit":0,"_id":"5bc00d61c47e7c734ecc365f","userId":67,"createdAt":"2018-10-12T10:56:33+08:00","updatedAt":"2018-10-18T11:33:41+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1539691122610_8.jpg","realName":"Sheng","company":"3q3","position":"3323","school":"323"}
         * basicInfoB : {"gender":0,"professionalIdentity":999,"showEdit":1,"_id":"5ba48955677db955ec6394cb","userId":43,"userInfo":43,"createdAt":"2018-09-21T14:01:57+08:00","updatedAt":"2018-09-27T16:10:01+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1538035801797_0.jpg","realName":"邀请码如图"}
         * statusA2B : 3
         * statusB2A : 3
         * timeA2B : 0
         * timeB2A : 1539857506220
         * createdAt : 2018-10-16T17:43:07+08:00
         * updatedAt : 2018-10-18T18:12:52+08:00
         * __v : 0
         */

        private String _id;
        private int userIdA;
        private int userIdB;
        private UserInfoABean userInfoA;
        private UserInfoBBean userInfoB;
        private BasicInfoABean basicInfoA;
        private BasicInfoBBean basicInfoB;
        private int statusA2B;
        private int statusB2A;
        private long timeA2B;
        private long timeB2A;
        private String createdAt;
        private String updatedAt;
        private int __v;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getUserIdA() {
            return userIdA;
        }

        public void setUserIdA(int userIdA) {
            this.userIdA = userIdA;
        }

        public int getUserIdB() {
            return userIdB;
        }

        public void setUserIdB(int userIdB) {
            this.userIdB = userIdB;
        }

        public UserInfoABean getUserInfoA() {
            return userInfoA;
        }

        public void setUserInfoA(UserInfoABean userInfoA) {
            this.userInfoA = userInfoA;
        }

        public UserInfoBBean getUserInfoB() {
            return userInfoB;
        }

        public void setUserInfoB(UserInfoBBean userInfoB) {
            this.userInfoB = userInfoB;
        }

        public BasicInfoABean getBasicInfoA() {
            return basicInfoA;
        }

        public void setBasicInfoA(BasicInfoABean basicInfoA) {
            this.basicInfoA = basicInfoA;
        }

        public BasicInfoBBean getBasicInfoB() {
            return basicInfoB;
        }

        public void setBasicInfoB(BasicInfoBBean basicInfoB) {
            this.basicInfoB = basicInfoB;
        }

        public int getStatusA2B() {
            return statusA2B;
        }

        public void setStatusA2B(int statusA2B) {
            this.statusA2B = statusA2B;
        }

        public int getStatusB2A() {
            return statusB2A;
        }

        public void setStatusB2A(int statusB2A) {
            this.statusB2A = statusB2A;
        }

        public long getTimeA2B() {
            return timeA2B;
        }

        public void setTimeA2B(long timeA2B) {
            this.timeA2B = timeA2B;
        }

        public long getTimeB2A() {
            return timeB2A;
        }

        public void setTimeB2A(long timeB2A) {
            this.timeB2A = timeB2A;
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

        public static class UserInfoABean {
            /**
             * vcSendTime : 0
             * registered : true
             * inviteCode : 780361
             * invitedCode : 123531
             * inviteCount : 0
             * pbsAmount : 0.016
             * lastHarvestAmount : 0.012
             * pbsFrozen : 0
             * combat : 60
             * _id : 67
             * phone : 15718807582
             * vrfCode : 用了就过期
             * countryCode : 86
             * createdAt : 2018-10-12T10:56:05+08:00
             * updatedAt : 2018-10-18T11:33:42+08:00
             * __v : 0
             * password : $2a$10$ml.KDMYpVfaACCzEHoyWyuQ0o7jKLQsO0p45Sel.stlpA2nli6LNC
             * basicInfo : 5bc00d61c47e7c734ecc365f
             * clientIp : 221.221.234.77
             * statisticsInfo : 5bc00d61c47e7c734ecc365e
             * headImg : /img/upload/userHeadImages/1539691122610_8.jpg
             */

            private String vcSendTime;
            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private int inviteCount;
            private double pbsAmount;
            private double lastHarvestAmount;
            private String pbsFrozen;
            private int combat;
            private int _id;
            private String phone;
            private String vrfCode;
            private String countryCode;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String password;
            private String basicInfo;
            private String clientIp;
            private String statisticsInfo;
            private String headImg;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getBasicInfo() {
                return basicInfo;
            }

            public void setBasicInfo(String basicInfo) {
                this.basicInfo = basicInfo;
            }

            public String getClientIp() {
                return clientIp;
            }

            public void setClientIp(String clientIp) {
                this.clientIp = clientIp;
            }

            public String getStatisticsInfo() {
                return statisticsInfo;
            }

            public void setStatisticsInfo(String statisticsInfo) {
                this.statisticsInfo = statisticsInfo;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }
        }

        public static class UserInfoBBean {
            /**
             * vcSendTime : 0
             * registered : true
             * inviteCode : 944495
             * invitedCode : 123531
             * inviteCount : 0
             * pbsAmount : 0.04200000000000002
             * lastHarvestAmount : 0.04200000000000002
             * pbsFrozen : 0
             * combat : 20
             * _id : 43
             * phone : 13699150275
             * vrfCode : 用了就过期
             * countryCode : 86
             * createdAt : 2018-09-21T13:52:10+08:00
             * updatedAt : 2018-10-18T15:20:09+08:00
             * __v : 0
             * password : $2a$10$CqjeHeAHssoc28rzEhFoy.msqyH9l9rqWXVGImlVeTodXVXGuIDSC
             * basicInfo : 5ba48955677db955ec6394cb
             * statisticsInfo : 5ba48955677db955ec6394ca
             * headImg : /img/upload/userHeadImages/1538035801797_0.jpg
             */

            private String vcSendTime;
            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private int inviteCount;
            private double pbsAmount;
            private double lastHarvestAmount;
            private String pbsFrozen;
            private int combat;
            private int _id;
            private String phone;
            private String vrfCode;
            private String countryCode;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String password;
            private String basicInfo;
            private String statisticsInfo;
            private String headImg;

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

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getBasicInfo() {
                return basicInfo;
            }

            public void setBasicInfo(String basicInfo) {
                this.basicInfo = basicInfo;
            }

            public String getStatisticsInfo() {
                return statisticsInfo;
            }

            public void setStatisticsInfo(String statisticsInfo) {
                this.statisticsInfo = statisticsInfo;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }
        }

        public static class BasicInfoABean {
            /**
             * gender : 0
             * professionalIdentity : -1
             * showEdit : 0
             * _id : 5bc00d61c47e7c734ecc365f
             * userId : 67
             * createdAt : 2018-10-12T10:56:33+08:00
             * updatedAt : 2018-10-18T11:33:41+08:00
             * __v : 0
             * headImg : /img/upload/userHeadImages/1539691122610_8.jpg
             * realName : Sheng
             * company : 3q3
             * position : 3323
             * school : 323
             */

            private int gender;
            private int professionalIdentity;
            private int showEdit;
            private String _id;
            private int userId;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String headImg;
            private String realName;
            private String company;
            private String position;
            private String school;
            private String selfDesc;

            public String getSelfDesc() {
                return selfDesc;
            }

            public void setSelfDesc(String selfDesc) {
                this.selfDesc = selfDesc;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getProfessionalIdentity() {
                return professionalIdentity;
            }

            public void setProfessionalIdentity(int professionalIdentity) {
                this.professionalIdentity = professionalIdentity;
            }

            public int getShowEdit() {
                return showEdit;
            }

            public void setShowEdit(int showEdit) {
                this.showEdit = showEdit;
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

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

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

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }
        }

        public static class BasicInfoBBean {
            /**
             * gender : 0
             * professionalIdentity : 999
             * showEdit : 1
             * _id : 5ba48955677db955ec6394cb
             * userId : 43
             * userInfo : 43
             * createdAt : 2018-09-21T14:01:57+08:00
             * updatedAt : 2018-09-27T16:10:01+08:00
             * __v : 0
             * headImg : /img/upload/userHeadImages/1538035801797_0.jpg
             * realName : 邀请码如图
             */

            private int gender;
            private int professionalIdentity;
            private int showEdit;
            private String _id;
            private int userId;
            private int userInfo;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String headImg;
            private String realName;
            private String company;
            private String position;
            private String school;
            private String selfDesc;
            private String positionTitle;

            public String getPositionTitle() {
                return positionTitle;
            }

            public void setPositionTitle(String positionTitle) {
                this.positionTitle = positionTitle;
            }

            public String getSelfDesc() {
                return selfDesc;
            }

            public void setSelfDesc(String selfDesc) {
                this.selfDesc = selfDesc;
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

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public int getProfessionalIdentity() {
                return professionalIdentity;
            }

            public void setProfessionalIdentity(int professionalIdentity) {
                this.professionalIdentity = professionalIdentity;
            }

            public int getShowEdit() {
                return showEdit;
            }

            public void setShowEdit(int showEdit) {
                this.showEdit = showEdit;
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

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
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

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }
        }
    }
}
