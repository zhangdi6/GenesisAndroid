package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:
 * 作者:sgf
 */
public class HomeceshiBean implements Serializable{


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"infoCount":4,"userInfo":{"registered":true,"inviteCode":"798150","invitedCode":"657275","inviteCount":0,"pbsAmount":0.034,"lastHarvestAmount":0.030000000000000002,"pbsFrozen":0,"combat":60,"crystalAmount":0,"_id":86,"phone":"15718807582","vrfCode":"用了就过期","countryCode":"86","vcSendTime":0,"createdAt":"2018-10-23T15:29:31+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0,"password":"$2a$10$oxyDDsmxmf13OPIhJOnpvuAORzBNQQJm30dP5.lQPLK.v0Um36S5e","basicInfo":"5bcecdf1ab12fe2cfa793c64","clientIp":"221.220.227.53","statisticsInfo":"5bcecdf1ab12fe2cfa793c63","headImg":"/img/upload/userHeadImages/1540966743082_1.jpg"},"statisticsInfo":{"basicCount":1,"blockchainCount":0,"workCount":1,"educationCount":0,"photoCount":1,"_id":"5bcecdf1ab12fe2cfa793c63","userId":86,"userInfo":86,"createdAt":"2018-10-23T15:29:53+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0},"basicInfo":{"gender":0,"professionalIdentity":-1,"showEdit":0,"_id":"5bcecdf1ab12fe2cfa793c64","userId":86,"createdAt":"2018-10-23T15:29:53+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1540966743082_1.jpg","realName":"122222222","company":"1111","position":"111","school":"111"},"companies":[{"memberCount":8,"show":true,"index":1,"groupIndex":1,"_id":4,"nick":"绿巨人","name":"北京绿巨人科技有限公司","logo":"/img/upload/companyImages/1541155670958_2.png","desc":"公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介","groupName":"绿巨人社群","groupDesc":"针对绿巨人的交流社群","onwerId":28,"createdAt":"2018-11-02T19:51:23+08:00","updatedAt":"2018-11-05T17:37:49+08:00","__v":0}]}
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
        /**
         * infoCount : 4
         * userInfo : {"registered":true,"inviteCode":"798150","invitedCode":"657275","inviteCount":0,"pbsAmount":0.034,"lastHarvestAmount":0.030000000000000002,"pbsFrozen":0,"combat":60,"crystalAmount":0,"_id":86,"phone":"15718807582","vrfCode":"用了就过期","countryCode":"86","vcSendTime":0,"createdAt":"2018-10-23T15:29:31+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0,"password":"$2a$10$oxyDDsmxmf13OPIhJOnpvuAORzBNQQJm30dP5.lQPLK.v0Um36S5e","basicInfo":"5bcecdf1ab12fe2cfa793c64","clientIp":"221.220.227.53","statisticsInfo":"5bcecdf1ab12fe2cfa793c63","headImg":"/img/upload/userHeadImages/1540966743082_1.jpg"}
         * statisticsInfo : {"basicCount":1,"blockchainCount":0,"workCount":1,"educationCount":0,"photoCount":1,"_id":"5bcecdf1ab12fe2cfa793c63","userId":86,"userInfo":86,"createdAt":"2018-10-23T15:29:53+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0}
         * basicInfo : {"gender":0,"professionalIdentity":-1,"showEdit":0,"_id":"5bcecdf1ab12fe2cfa793c64","userId":86,"createdAt":"2018-10-23T15:29:53+08:00","updatedAt":"2018-11-05T12:06:40+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1540966743082_1.jpg","realName":"122222222","company":"1111","position":"111","school":"111"}
         * companies : [{"memberCount":8,"show":true,"index":1,"groupIndex":1,"_id":4,"nick":"绿巨人","name":"北京绿巨人科技有限公司","logo":"/img/upload/companyImages/1541155670958_2.png","desc":"公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介","groupName":"绿巨人社群","groupDesc":"针对绿巨人的交流社群","onwerId":28,"createdAt":"2018-11-02T19:51:23+08:00","updatedAt":"2018-11-05T17:37:49+08:00","__v":0}]
         */

        private int infoCount;
        private UserInfoBean userInfo;
        private StatisticsInfoBean statisticsInfo;
        private BasicInfoBean basicInfo;
        private List<CompaniesBean> companies;

        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public StatisticsInfoBean getStatisticsInfo() {
            return statisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean statisticsInfo) {
            this.statisticsInfo = statisticsInfo;
        }

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public List<CompaniesBean> getCompanies() {
            return companies;
        }

        public void setCompanies(List<CompaniesBean> companies) {
            this.companies = companies;
        }

        public static class UserInfoBean {
            /**
             * registered : true
             * inviteCode : 798150
             * invitedCode : 657275
             * inviteCount : 0
             * pbsAmount : 0.034
             * lastHarvestAmount : 0.030000000000000002
             * pbsFrozen : 0
             * combat : 60
             * crystalAmount : 0
             * _id : 86
             * phone : 15718807582
             * vrfCode : 用了就过期
             * countryCode : 86
             * vcSendTime : 0
             * createdAt : 2018-10-23T15:29:31+08:00
             * updatedAt : 2018-11-05T12:06:40+08:00
             * __v : 0
             * password : $2a$10$oxyDDsmxmf13OPIhJOnpvuAORzBNQQJm30dP5.lQPLK.v0Um36S5e
             * basicInfo : 5bcecdf1ab12fe2cfa793c64
             * clientIp : 221.220.227.53
             * statisticsInfo : 5bcecdf1ab12fe2cfa793c63
             * headImg : /img/upload/userHeadImages/1540966743082_1.jpg
             */

            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private int inviteCount;
            private double pbsAmount;
            private double lastHarvestAmount;
            private String pbsFrozen;
            private int combat;
            private int crystalAmount;
            private int _id;
            private String phone;
            private String vrfCode;
            private String countryCode;
            private int vcSendTime;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String password;
            private String basicInfo;
            private String clientIp;
            private String statisticsInfo;
            private String headImg;

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

            public int getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(int crystalAmount) {
                this.crystalAmount = crystalAmount;
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

            public int getVcSendTime() {
                return vcSendTime;
            }

            public void setVcSendTime(int vcSendTime) {
                this.vcSendTime = vcSendTime;
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

        public static class StatisticsInfoBean {
            /**
             * basicCount : 1
             * blockchainCount : 0
             * workCount : 1
             * educationCount : 0
             * photoCount : 1
             * _id : 5bcecdf1ab12fe2cfa793c63
             * userId : 86
             * userInfo : 86
             * createdAt : 2018-10-23T15:29:53+08:00
             * updatedAt : 2018-11-05T12:06:40+08:00
             * __v : 0
             */

            private int basicCount;
            private int blockchainCount;
            private int workCount;
            private int educationCount;
            private int photoCount;
            private String _id;
            private int userId;
            private int userInfo;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public int getBasicCount() {
                return basicCount;
            }

            public void setBasicCount(int basicCount) {
                this.basicCount = basicCount;
            }

            public int getBlockchainCount() {
                return blockchainCount;
            }

            public void setBlockchainCount(int blockchainCount) {
                this.blockchainCount = blockchainCount;
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

            public int getPhotoCount() {
                return photoCount;
            }

            public void setPhotoCount(int photoCount) {
                this.photoCount = photoCount;
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
        }

        public static class BasicInfoBean {
            /**
             * gender : 0
             * professionalIdentity : -1
             * showEdit : 0
             * _id : 5bcecdf1ab12fe2cfa793c64
             * userId : 86
             * createdAt : 2018-10-23T15:29:53+08:00
             * updatedAt : 2018-11-05T12:06:40+08:00
             * __v : 0
             * headImg : /img/upload/userHeadImages/1540966743082_1.jpg
             * realName : 122222222
             * company : 1111
             * position : 111
             * school : 111
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

        public static class CompaniesBean {
            /**
             * memberCount : 8
             * show : true
             * index : 1
             * groupIndex : 1
             * _id : 4
             * nick : 绿巨人
             * name : 北京绿巨人科技有限公司
             * logo : /img/upload/companyImages/1541155670958_2.png
             * desc : 公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介
             * groupName : 绿巨人社群
             * groupDesc : 针对绿巨人的交流社群
             * onwerId : 28
             * createdAt : 2018-11-02T19:51:23+08:00
             * updatedAt : 2018-11-05T17:37:49+08:00
             * __v : 0
             */

            private int memberCount;
            private boolean show;
            private int index;
            private int groupIndex;
            private int _id;
            private String nick;
            private String name;
            private String logo;
            private String desc;
            private String groupName;
            private String groupDesc;
            private int onwerId;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public int getMemberCount() {
                return memberCount;
            }

            public void setMemberCount(int memberCount) {
                this.memberCount = memberCount;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getIndex() {
                return index;
            }

            public void setIndex(int index) {
                this.index = index;
            }

            public int getGroupIndex() {
                return groupIndex;
            }

            public void setGroupIndex(int groupIndex) {
                this.groupIndex = groupIndex;
            }

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
            }

            public String getNick() {
                return nick;
            }

            public void setNick(String nick) {
                this.nick = nick;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getGroupName() {
                return groupName;
            }

            public void setGroupName(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupDesc() {
                return groupDesc;
            }

            public void setGroupDesc(String groupDesc) {
                this.groupDesc = groupDesc;
            }

            public int getOnwerId() {
                return onwerId;
            }

            public void setOnwerId(int onwerId) {
                this.onwerId = onwerId;
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
