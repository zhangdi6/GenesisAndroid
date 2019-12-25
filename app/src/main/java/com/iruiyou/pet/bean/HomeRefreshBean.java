package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:
 * 创建日期:2018/8/14 on 11:54
 * 作者:JiaoPeiRong
 */
public class HomeRefreshBean implements Serializable{

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"infoCount":9,"userInfo":{"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1527659028987_2.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-14T11:32:30+08:00","__v":0},"statisticsInfo":{"basicCount":0,"blockchainCount":0,"workCount":0,"educationCount":0,"_id":"5b6f94874f5fb36df9eff680","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0},"basicInfo":{"school":"学校","education":"学历","company":"公司","position":"职位","country":"公司注册地","number":"公司人数","nature":"公司性质","headImg":"/img/upload/userHeadImages/userHead.png","selfDesc":"无","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"uu","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}}
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

    public static class DataBean implements Serializable{
        /**
         * infoCount : 9
         * userInfo : {"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1527659028987_2.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-14T11:32:30+08:00","__v":0}
         * statisticsInfo : {"basicCount":0,"blockchainCount":0,"workCount":0,"educationCount":0,"_id":"5b6f94874f5fb36df9eff680","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}
         * basicInfo : {"school":"学校","education":"学历","company":"公司","position":"职位","country":"公司注册地","number":"公司人数","nature":"公司性质","headImg":"/img/upload/userHeadImages/userHead.png","selfDesc":"无","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"uu","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}
         */

        private String notice;
        private int infoCount;
        private UserInfoBean userInfo;
        private StatisticsInfoBean statisticsInfo;
        private BasicInfoBean basicInfo;
        private List<CompaniesBean> companies;

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public List<CompaniesBean> getCompanies() {
            return companies;
        }

        public void setCompanies(List<CompaniesBean> companies) {
            this.companies = companies;
        }

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

        public static class CompaniesBean implements Serializable{
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

        public static class UserInfoBean implements Serializable{
            /**
             * vcSendTime : 0
             * registered : true
             * inviteCode : 444555
             * invitedCode : 我被邀请码
             * inviteCount : 0
             * pbsAmount : 0
             * lastHarvestAmount : 0
             * pbsFrozen : 1
             * combat : 0
             * headImg : /img/upload/userHeadImages/1527659028987_2.jpg
             * _id : 26
             * countryCode : 86
             * phone : 17601011006
             * name : uu
             * password : $2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK
             * vrfCode : 用了就过期
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-14T11:32:30+08:00
             * __v : 0
             */

            private String vcSendTime;
            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private String inviteCount;
            private double pbsAmount;
            private double crystalAmount;
            private double crystalDrawLockedAmount;
            private double rebateCrystal;
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
            private String userChannel;
            private int __v;
            private int vipLevel;//0-非会员 1-高级会员，0.85折 2-商务会员，0.75折 3-白金会员，0.65折扣 4-企业，0.55折扣
            private long lastMarkTime;

            public long getLastMarkTime() {
                return lastMarkTime;
            }

            public void setLastMarkTime(long lastMarkTime) {
                this.lastMarkTime = lastMarkTime;
            }

            public double getRebateCrystal() {
                return rebateCrystal;
            }

            public void setRebateCrystal(double rebateCrystal) {
                this.rebateCrystal = rebateCrystal;
            }

            public double getCrystalDrawLockedAmount() {
                return crystalDrawLockedAmount;
            }

            public void setCrystalDrawLockedAmount(double crystalDrawLockedAmount) {
                this.crystalDrawLockedAmount = crystalDrawLockedAmount;
            }

            public double getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(double crystalAmount) {
                this.crystalAmount = crystalAmount;
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

            public String getInviteCount() {
                return inviteCount;
            }

            public void setInviteCount(String inviteCount) {
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


            public String getUserChannel() {
                return userChannel;
            }

            public void setUserChannel(String userChannel) {
                this.userChannel = userChannel;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public int getVipLevel() {
                return vipLevel;
            }
        }

        public static class StatisticsInfoBean implements Serializable{
            /**
             * basicCount : 0
             * blockchainCount : 0
             * workCount : 0
             * educationCount : 0
             * _id : 5b6f94874f5fb36df9eff680
             * userId : 26
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-12T09:59:35+08:00
             * __v : 0
             */

            private int basicCount;
            private int photoCount;//职业照
            private int blockchainCount;
            private int workCount;
            private int educationCount;
            private String _id;
            private int userId;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public int getPhotoCount() {
                return photoCount;
            }

            public void setPhotoCount(int photoCount) {
                this.photoCount = photoCount;
            }
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
        }

        public static class BasicInfoBean implements Serializable{
            /**
             * school : 学校
             * education : 学历
             * company : 公司
             * position : 职位
             * country : 公司注册地
             * number : 公司人数
             * nature : 公司性质
             * headImg : /img/upload/userHeadImages/userHead.png
             * selfDesc : 无
             * _id : 5b6f94874f5fb36df9eff681
             * userId : 26
             * realName : uu
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-12T09:59:35+08:00
             * __v : 0
             * professionalIdentity : 职业的key
             */

            private String school;
            private String education;
            private String company;
            private String position;
            private String country;
            private String number;
            private String nature;
            private String headImg;
            private String selfDesc;
            private String _id;
            private int userId;
            private String realName;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private int professionalIdentity;

            public int getProfessionalIdentity() {
                return professionalIdentity;
            }

            public void setProfessionalIdentity(int professionalIdentity) {
                this.professionalIdentity = professionalIdentity;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public String getEducation() {
                return education;
            }

            public void setEducation(String education) {
                this.education = education;
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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getNature() {
                return nature;
            }

            public void setNature(String nature) {
                this.nature = nature;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getSelfDesc() {
                return selfDesc;
            }

            public void setSelfDesc(String selfDesc) {
                this.selfDesc = selfDesc;
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

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
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
