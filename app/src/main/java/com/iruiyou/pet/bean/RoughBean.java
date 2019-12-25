package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:获取用户粗略信息
 * 创建日期:2018/10/16 on 17:50
 * 作者:sgf
 */
public class RoughBean implements Serializable {


    /**
     * rcToken : null
     * csrfToken : null
     * data : {"basicInfo":{"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61},"followCount":1,"friendCount":0,"userInfo":{"phone":"18610411247","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5bc7f0848e3ec94209d13628","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$ChwsLqV1oIrL0Z7PfAbn/OX2oDRjeTwa5M00Bkk5YamlFUJZAhjzK","updatedAt":"2018-10-18T10:31:37+08:00","basicInfo":"5bc7f0848e3ec94209d13629","vcSendTime":0,"_id":61,"createdAt":"2018-09-29T15:49:37+08:00","registered":true,"inviteCode":"105709","combat":20,"clientIp":"114.240.89.230","lastHarvestAmount":0,"invitedCode":"657275"},"followed":false,"seePrice":0,"fansCount":0,"friendStatus":1}
     * message : null
     * statusCode : 0
     * error : null
     * token : null
     */

    private String rcToken;
    private String csrfToken;
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
         * basicInfo : {"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61}
         * followCount : 1
         * friendCount : 0
         * userInfo : {"phone":"18610411247","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5bc7f0848e3ec94209d13628","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$ChwsLqV1oIrL0Z7PfAbn/OX2oDRjeTwa5M00Bkk5YamlFUJZAhjzK","updatedAt":"2018-10-18T10:31:37+08:00","basicInfo":"5bc7f0848e3ec94209d13629","vcSendTime":0,"_id":61,"createdAt":"2018-09-29T15:49:37+08:00","registered":true,"inviteCode":"105709","combat":20,"clientIp":"114.240.89.230","lastHarvestAmount":0,"invitedCode":"657275"}
         * followed : false
         * seePrice : 0
         * fansCount : 0
         * friendStatus : 1
         */

        private BasicInfoBean basicInfo;// 用户信息
        private int followCount;//关注数量
        private int friendCount;//好友数目
        private UserInfoBean userInfo;//账号信息
        private boolean followed;//是否已经关注
        private int fansCount;//粉丝数量
        private int friendStatus;// 好友状态
        private int eduCount;//受教育阶段数
        private int workCount;//工作经历数
        private boolean hadBuy;//已购买过资料
        private StatisticsInfo statisticsInfo;//, // 资料完整度分子
        private int infoCount;// // 资料完整度分母

        public int getEduCount() {
            return eduCount;
        }

        public void setEduCount(int eduCount) {
            this.eduCount = eduCount;
        }

        public int getWorkCount() {
            return workCount;
        }

        public void setWorkCount(int workCount) {
            this.workCount = workCount;
        }

        public boolean isHadBuy() {
            return hadBuy;
        }

        public void setHadBuy(boolean hadBuy) {
            this.hadBuy = hadBuy;
        }

        public StatisticsInfo getStatisticsInfo() {
            return statisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfo statisticsInfo) {
            this.statisticsInfo = statisticsInfo;
        }

        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public int getFollowCount() {
            return followCount;
        }

        public void setFollowCount(int followCount) {
            this.followCount = followCount;
        }

        public int getFriendCount() {
            return friendCount;
        }

        public void setFriendCount(int friendCount) {
            this.friendCount = friendCount;
        }

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public boolean isFollowed() {
            return followed;
        }

        public void setFollowed(boolean followed) {
            this.followed = followed;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        public int getFriendStatus() {
            return friendStatus;
        }

        public void setFriendStatus(int friendStatus) {
            this.friendStatus = friendStatus;
        }


        public static class StatisticsInfo {
            private long userId;// 用户ID
            private long basicCount; // 基本资料任务
            private long blockchainCount;// 区块链信息任务
            private long workCount;//// 工作经验任务
            private int educationCount;//// 教育经历任务
            private int photoCount;//职业照任务
            private long crystalCount;// 买水晶任务
            private long crystalAmount;//已购水晶数量


            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public long getBasicCount() {
                return basicCount;
            }

            public void setBasicCount(long basicCount) {
                this.basicCount = basicCount;
            }

            public long getBlockchainCount() {
                return blockchainCount;
            }

            public void setBlockchainCount(long blockchainCount) {
                this.blockchainCount = blockchainCount;
            }

            public long getWorkCount() {
                return workCount;
            }

            public void setWorkCount(long workCount) {
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

            public long getCrystalCount() {
                return crystalCount;
            }

            public void setCrystalCount(long crystalCount) {
                this.crystalCount = crystalCount;
            }

            public long getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(long crystalAmount) {
                this.crystalAmount = crystalAmount;
            }
        }

        public static class BasicInfoBean {
            /**
             * updatedAt : 2018-10-18T10:31:37+08:00
             * headImg : img/upload/userHeadImages/null
             * _id : 5bc7f0848e3ec94209d13629
             * professionalIdentity : 1
             * createdAt : 2018-10-18T10:31:32+08:00
             * userId : 61
             * __v : 0
             * gender : 0
             * realName : 3232323
             * showEdit : 1
             * userInfo : 61
             */
            private double seePrice;//查看资料价格
            private double friendPrice;//添加好友价格
            private String updatedAt;
            private String headImg;
            private String _id;
            private int professionalIdentity;
            private String createdAt;
            private int userId;
            private int __v;
            private int gender;
            private String realName;
            private int showEdit;
            private int userInfo;
            private String company;//公司
            private String position;//职位

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

            public double getSeePrice() {
                return seePrice;
            }

            public void setSeePrice(double seePrice) {
                this.seePrice = seePrice;
            }

            public double getFriendPrice() {
                return friendPrice;
            }

            public void setFriendPrice(double friendPrice) {
                this.friendPrice = friendPrice;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
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

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
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
        }

        public static class UserInfoBean {
            /**
             * phone : 18610411247
             * pbsFrozen : 0
             * pbsAmount : 0
             * inviteCount : 0
             * statisticsInfo : 5bc7f0848e3ec94209d13628
             * __v : 0
             * countryCode : 86
             * vrfCode : 用了就过期
             * password : $2a$10$ChwsLqV1oIrL0Z7PfAbn/OX2oDRjeTwa5M00Bkk5YamlFUJZAhjzK
             * updatedAt : 2018-10-18T10:31:37+08:00
             * basicInfo : 5bc7f0848e3ec94209d13629
             * vcSendTime : 0
             * _id : 61
             * createdAt : 2018-09-29T15:49:37+08:00
             * registered : true
             * inviteCode : 105709
             * combat : 20
             * clientIp : 114.240.89.230
             * lastHarvestAmount : 0
             * invitedCode : 657275
             */

            private String phone;
            private double pbsFrozen;
            private double pbsAmount;
            private int inviteCount;
            private String statisticsInfo;//资料完整度分子
            private int __v;
            private String countryCode;
            private String vrfCode;
            private String password;
            private String updatedAt;
            private String basicInfo;
            private String vcSendTime;
            private int _id;
            private String createdAt;
            private boolean registered;
            private String inviteCode;
            private int combat;
            private String clientIp;
            private double lastHarvestAmount;
            private String invitedCode;

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public double getPbsFrozen() {
                return pbsFrozen;
            }

            public void setPbsFrozen(double pbsFrozen) {
                this.pbsFrozen = pbsFrozen;
            }

            public double getPbsAmount() {
                return pbsAmount;
            }

            public void setPbsAmount(double pbsAmount) {
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

            public String getVcSendTime() {
                return vcSendTime;
            }

            public void setVcSendTime(String vcSendTime) {
                this.vcSendTime = vcSendTime;
            }

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
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

            public double getLastHarvestAmount() {
                return lastHarvestAmount;
            }

            public void setLastHarvestAmount(double lastHarvestAmount) {
                this.lastHarvestAmount = lastHarvestAmount;
            }

            public String getInvitedCode() {
                return invitedCode;
            }

            public void setInvitedCode(String invitedCode) {
                this.invitedCode = invitedCode;
            }
        }
    }
}
