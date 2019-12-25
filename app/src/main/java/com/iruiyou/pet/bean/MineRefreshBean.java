package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/8/18 00:06
 * 邮箱：chinajpr@163.com
 */
public class MineRefreshBean implements Serializable{

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"infoCount":9,"userInfo":{"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T19:23:12+08:00","__v":0},"statisticsInfo":{"basicCount":1,"blockchainCount":1,"workCount":4,"educationCount":1,"_id":"5b6f94874f5fb36df9eff680","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T17:00:47+08:00","__v":0},"basicInfo":{"school":"清华","education":"本科","company":"虎峪","position":"互动","country":"北京","number":"99人以上","nature":"区块链","headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","selfDesc":"","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"顺","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T23:51:26+08:00","__v":0}}
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
         * userInfo : {"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T19:23:12+08:00","__v":0}
         * statisticsInfo : {"basicCount":1,"blockchainCount":1,"workCount":4,"educationCount":1,"_id":"5b6f94874f5fb36df9eff680","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T17:00:47+08:00","__v":0}
         * basicInfo : {"school":"清华","education":"本科","company":"虎峪","position":"互动","country":"北京","number":"99人以上","nature":"区块链","headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","selfDesc":"","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"顺","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T23:51:26+08:00","__v":0}
         */

        private int infoCount;
        private UserInfoBean userInfo;
        private StatisticsInfoBean statisticsInfo;
        private BasicInfoBean basicInfo;

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
             * headImg : /img/upload/userHeadImages/1534504992258_8.jpg
             * _id : 26
             * countryCode : 86
             * phone : 17601011006
             * name : uu
             * password : $2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK
             * vrfCode : 用了就过期
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-17T19:23:12+08:00
             * __v : 0
             */

            private String vcSendTime;
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
            private int vipLevel;//会员等级，0非会员、1高级、2VIP、3企业
            private String vrfCode;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String userChannel;
            private int crystalCount; //水晶数量 ---以当前对象为准
            private double crystalAmount;
            private int crowdFundLevel; // 机遇空间众筹身份等级 0-无身份 1-合伙人 2-股东

            public int getCrowdFundLevel() {
                return crowdFundLevel;
            }

            public void setCrowdFundLevel(int crowdFundLevel) {
                this.crowdFundLevel = crowdFundLevel;
            }

            public int getCrystalCount() {
                return crystalCount;
            }

            public void setCrystalCount(int crystalCount) {
                this.crystalCount = crystalCount;
            }

            public double getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(int crystalAmount) {
                this.crystalAmount = crystalAmount;
            }

            public int getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public void setUserChannel(String userChannel) {
                this.userChannel = userChannel;
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

            public String getPbsAmount() {
                return pbsAmount;
            }
            public String getUserChannel() {
                return userChannel;
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

        public static class StatisticsInfoBean implements Serializable{
            /**
             * basicCount : 1
             * blockchainCount : 1
             * workCount : 4
             * educationCount : 1
             * _id : 5b6f94874f5fb36df9eff680
             * userId : 26
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-17T17:00:47+08:00
             * __v : 0
             */

            private int basicCount;
            private int blockchainCount;
            private int workCount;
            private int educationCount;
            private int photoCount;
            private int crystalCount;
            private int crystalAmount;
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

            public int getCrystalCount() {
                return crystalCount;
            }

            public void setCrystalCount(int crystalCount) {
                this.crystalCount = crystalCount;
            }

            public int getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(int crystalAmount) {
                this.crystalAmount = crystalAmount;
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
             * school : 清华
             * education : 本科
             * company : 虎峪
             * position : 互动
             * country : 北京
             * number : 99人以上
             * nature : 区块链
             * headImg : /img/upload/userHeadImages/1534504992258_8.jpg
             * selfDesc :
             * _id : 5b6f94874f5fb36df9eff681
             * userId : 26
             * realName : 顺
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-17T23:51:26+08:00
             * __v : 0
             */

            private String positionTitle;
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
            private int gender;//
            private String city;
            private String cityCode;

            public String getPositionTitle() {
                return positionTitle;
            }

            public void setPositionTitle(String positionTitle) {
                this.positionTitle = positionTitle;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCityCode() {
                return cityCode;
            }

            public void setCityCode(String cityCode) {
                this.cityCode = cityCode;
            }

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
