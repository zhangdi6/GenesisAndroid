package com.iruiyou.pet.bean;

public class PhoneVefBean {
    /**
     * Copyright 2019 bejson.com
     */


    /**
     * statusCode : 0
     * message : 登录成功
     * error : null
     * data : {"infoCount":4,"userInfo":{"registered":true,"inviteCode":"675165","invitedCode":"324121","inviteCount":0,"pbsAmount":0.007300000000000001,"lastHarvestAmount":0,"pbsFrozen":0,"combat":70,"crystalAmount":0,"crystalTime":1563503714385,"usdtAmount":0,"pbsDepositAmount":0,"pbsTranAmount":0,"pbsDrawLockedAmount":0,"regionNode":0,"hatchAmount":0,"hatchDrawTime":0,"vipLevel":5,"vipStartDate":1563503816182,"vipExpireDate":1595039816182,"rebateCrystal":0,"crystalDrawLockedAmount":0,"highNode":0,"crystalDrawTime":0,"crystalDrawZfbTime":0,"crystalDrawZfbToBankTime":0,"amountCrowdfund":0,"crowdFundLevel":0,"lastMarkTime":1575351240430,"isKeruyun":false,"blackCard":0,"_id":1134,"phone":"18610411247","vrfCode":"用了就过期","countryCode":"86","vcSendTime":0,"createdAt":"2018-09-26T15:36:33+08:00","updatedAt":"2019-12-12T15:27:56+08:00","__v":0,"name":"杨树名","password":"$2a$10$AzLy6I9CCsYd84xjG1ZC/uXJgwkLtt5nUnbsl3vQrQa8ZzUo.hLA2","basicInfo":"5bab3720103c004294c16acc","statisticsInfo":"5bab3701103c004294c16acb","headImg":"/img/upload/userHeadImages/1548729891211_3.jpg","showInCharts":true,"signupTime":1537947393642,"deviceType":"iOS","revoked":""},"basicInfo":{"company":"一啊啊啊","position":"222把啊啊啊","gender":1,"professionalIdentity":1002,"showEdit":1,"seePrice":0,"friendPrice":0,"positionTitle":"一啊啊啊·222把啊啊啊","maidianPrice":0,"isRewarded":true,"_id":"5bab3720103c004294c16acc","userId":1134,"realName":"杨","createdAt":"2018-09-26T15:37:04+08:00","updatedAt":"2019-12-12T15:07:13+08:00","__v":0,"school":"2222啊啊啊","headImg":"/img/upload/userHeadImages/1548729891211_3.jpg","selfDesc":"aa"},"statisticsInfo":{"basicCount":1,"blockchainCount":0,"workCount":1,"educationCount":1,"photoCount":1,"crystalCount":0,"crystalAmount":0,"_id":"5bab3701103c004294c16acb","userId":1134,"createdAt":"2018-09-26T15:36:33+08:00","updatedAt":"2019-01-09T10:23:21+08:00","__v":0,"userInfo":1134}}
     * csrfToken : null
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiNjc1MTY1IiwiaW52aXRlZENvZGUiOiIzMjQxMjEiLCJpbnZpdGVDb3VudCI6MCwicGJzQW1vdW50IjowLjAwNzMwMDAwMDAwMDAwMDAwMSwibGFzdEhhcnZlc3RBbW91bnQiOjAsInBic0Zyb3plbiI6MCwiY29tYmF0Ijo3MCwiY3J5c3RhbEFtb3VudCI6MCwiY3J5c3RhbFRpbWUiOjE1NjM1MDM3MTQzODUsInVzZHRBbW91bnQiOjAsInBic0RlcG9zaXRBbW91bnQiOjAsInBic1RyYW5BbW91bnQiOjAsInBic0RyYXdMb2NrZWRBbW91bnQiOjAsInJlZ2lvbk5vZGUiOjAsImhhdGNoQW1vdW50IjowLCJoYXRjaERyYXdUaW1lIjowLCJ2aXBMZXZlbCI6NSwidmlwU3RhcnREYXRlIjoxNTYzNTAzODE2MTgyLCJ2aXBFeHBpcmVEYXRlIjoxNTk1MDM5ODE2MTgyLCJyZWJhdGVDcnlzdGFsIjowLCJjcnlzdGFsRHJhd0xvY2tlZEFtb3VudCI6MCwiaGlnaE5vZGUiOjAsImNyeXN0YWxEcmF3VGltZSI6MCwiY3J5c3RhbERyYXdaZmJUaW1lIjowLCJjcnlzdGFsRHJhd1pmYlRvQmFua1RpbWUiOjAsImFtb3VudENyb3dkZnVuZCI6MCwiY3Jvd2RGdW5kTGV2ZWwiOjAsImxhc3RNYXJrVGltZSI6MTU3NTM1MTI0MDQzMCwiaXNLZXJ1eXVuIjpmYWxzZSwiYmxhY2tDYXJkIjowLCJfaWQiOjExMzQsInBob25lIjoiMTg2MTA0MTEyNDciLCJ2cmZDb2RlIjoi55So5LqG5bCx6L-H5pyfIiwiY291bnRyeUNvZGUiOiI4NiIsInZjU2VuZFRpbWUiOjAsImNyZWF0ZWRBdCI6IjIwMTgtMDktMjZUMTU6MzY6MzMrMDg6MDAiLCJ1cGRhdGVkQXQiOiIyMDE5LTEyLTEyVDE1OjI3OjU2KzA4OjAwIiwiX192IjowLCJuYW1lIjoi5p2o5qCR5ZCNIiwicGFzc3dvcmQiOiIkMmEkMTAkQXpMeTZJOUNDc1lkODR4akcxWkMvdVhKZ3drTHR0NW5VbmJzbDN2UXJRYThaelVvLmhMQTIiLCJiYXNpY0luZm8iOiI1YmFiMzcyMDEwM2MwMDQyOTRjMTZhY2MiLCJzdGF0aXN0aWNzSW5mbyI6IjViYWIzNzAxMTAzYzAwNDI5NGMxNmFjYiIsImhlYWRJbWciOiIvaW1nL3VwbG9hZC91c2VySGVhZEltYWdlcy8xNTQ4NzI5ODkxMjExXzMuanBnIiwic2hvd0luQ2hhcnRzIjp0cnVlLCJzaWdudXBUaW1lIjoxNTM3OTQ3MzkzNjQyLCJkZXZpY2VUeXBlIjoiaU9TIiwicmV2b2tlZCI6IiJ9.JtVVTKJl5E-H4cNQnU74-xl7EVsCCwq4juCZ1i6FQrw
     * rcToken : NpxFt4kHo/SFzj/DCm69eTZ0Mx9GBihhxWIwGTWO1iz3RgwA6Rpf8ACh2II9gTdSccC8eNzxsdc8ZTFpYYY4ikcMy7mA+iae
     */

    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private String token;
    private String rcToken;

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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public static class DataBean {
        /**
         * infoCount : 4
         * userInfo : {"registered":true,"inviteCode":"675165","invitedCode":"324121","inviteCount":0,"pbsAmount":0.007300000000000001,"lastHarvestAmount":0,"pbsFrozen":0,"combat":70,"crystalAmount":0,"crystalTime":1563503714385,"usdtAmount":0,"pbsDepositAmount":0,"pbsTranAmount":0,"pbsDrawLockedAmount":0,"regionNode":0,"hatchAmount":0,"hatchDrawTime":0,"vipLevel":5,"vipStartDate":1563503816182,"vipExpireDate":1595039816182,"rebateCrystal":0,"crystalDrawLockedAmount":0,"highNode":0,"crystalDrawTime":0,"crystalDrawZfbTime":0,"crystalDrawZfbToBankTime":0,"amountCrowdfund":0,"crowdFundLevel":0,"lastMarkTime":1575351240430,"isKeruyun":false,"blackCard":0,"_id":1134,"phone":"18610411247","vrfCode":"用了就过期","countryCode":"86","vcSendTime":0,"createdAt":"2018-09-26T15:36:33+08:00","updatedAt":"2019-12-12T15:27:56+08:00","__v":0,"name":"杨树名","password":"$2a$10$AzLy6I9CCsYd84xjG1ZC/uXJgwkLtt5nUnbsl3vQrQa8ZzUo.hLA2","basicInfo":"5bab3720103c004294c16acc","statisticsInfo":"5bab3701103c004294c16acb","headImg":"/img/upload/userHeadImages/1548729891211_3.jpg","showInCharts":true,"signupTime":1537947393642,"deviceType":"iOS","revoked":""}
         * basicInfo : {"company":"一啊啊啊","position":"222把啊啊啊","gender":1,"professionalIdentity":1002,"showEdit":1,"seePrice":0,"friendPrice":0,"positionTitle":"一啊啊啊·222把啊啊啊","maidianPrice":0,"isRewarded":true,"_id":"5bab3720103c004294c16acc","userId":1134,"realName":"杨","createdAt":"2018-09-26T15:37:04+08:00","updatedAt":"2019-12-12T15:07:13+08:00","__v":0,"school":"2222啊啊啊","headImg":"/img/upload/userHeadImages/1548729891211_3.jpg","selfDesc":"aa"}
         * statisticsInfo : {"basicCount":1,"blockchainCount":0,"workCount":1,"educationCount":1,"photoCount":1,"crystalCount":0,"crystalAmount":0,"_id":"5bab3701103c004294c16acb","userId":1134,"createdAt":"2018-09-26T15:36:33+08:00","updatedAt":"2019-01-09T10:23:21+08:00","__v":0,"userInfo":1134}
         */

        private int infoCount;
        private UserInfoBean userInfo;
        private BasicInfoBean basicInfo;
        private StatisticsInfoBean statisticsInfo;

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

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public StatisticsInfoBean getStatisticsInfo() {
            return statisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean statisticsInfo) {
            this.statisticsInfo = statisticsInfo;
        }

        public static class UserInfoBean {
            /**
             * registered : true
             * inviteCode : 675165
             * invitedCode : 324121
             * inviteCount : 0
             * pbsAmount : 0.007300000000000001
             * lastHarvestAmount : 0
             * pbsFrozen : 0
             * combat : 70
             * crystalAmount : 0
             * crystalTime : 1563503714385
             * usdtAmount : 0
             * pbsDepositAmount : 0
             * pbsTranAmount : 0
             * pbsDrawLockedAmount : 0
             * regionNode : 0
             * hatchAmount : 0
             * hatchDrawTime : 0
             * vipLevel : 5
             * vipStartDate : 1563503816182
             * vipExpireDate : 1595039816182
             * rebateCrystal : 0
             * crystalDrawLockedAmount : 0
             * highNode : 0
             * crystalDrawTime : 0
             * crystalDrawZfbTime : 0
             * crystalDrawZfbToBankTime : 0
             * amountCrowdfund : 0
             * crowdFundLevel : 0
             * lastMarkTime : 1575351240430
             * isKeruyun : false
             * blackCard : 0
             * _id : 1134
             * phone : 18610411247
             * vrfCode : 用了就过期
             * countryCode : 86
             * vcSendTime : 0
             * createdAt : 2018-09-26T15:36:33+08:00
             * updatedAt : 2019-12-12T15:27:56+08:00
             * __v : 0
             * name : 杨树名
             * password : $2a$10$AzLy6I9CCsYd84xjG1ZC/uXJgwkLtt5nUnbsl3vQrQa8ZzUo.hLA2
             * basicInfo : 5bab3720103c004294c16acc
             * statisticsInfo : 5bab3701103c004294c16acb
             * headImg : /img/upload/userHeadImages/1548729891211_3.jpg
             * showInCharts : true
             * signupTime : 1537947393642
             * deviceType : iOS
             * revoked :
             */

            private boolean registered;
            private String inviteCode;
            private String invitedCode;
            private int inviteCount;
            private double pbsAmount;
            private int lastHarvestAmount;
            private int pbsFrozen;
            private int combat;
            private int crystalAmount;
            private long crystalTime;
            private int usdtAmount;
            private int pbsDepositAmount;
            private int pbsTranAmount;
            private int pbsDrawLockedAmount;
            private int regionNode;
            private int hatchAmount;
            private int hatchDrawTime;
            private int vipLevel;
            private long vipStartDate;
            private long vipExpireDate;
            private int rebateCrystal;
            private int crystalDrawLockedAmount;
            private int highNode;
            private int crystalDrawTime;
            private int crystalDrawZfbTime;
            private int crystalDrawZfbToBankTime;
            private int amountCrowdfund;
            private int crowdFundLevel;
            private long lastMarkTime;
            private boolean isKeruyun;
            private int blackCard;
            private int _id;
            private String phone;
            private String vrfCode;
            private String countryCode;
            private int vcSendTime;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String name;
            private String password;
            private String basicInfo;
            private String statisticsInfo;
            private String headImg;
            private boolean showInCharts;
            private long signupTime;
            private String deviceType;
            private String revoked;

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

            public int getLastHarvestAmount() {
                return lastHarvestAmount;
            }

            public void setLastHarvestAmount(int lastHarvestAmount) {
                this.lastHarvestAmount = lastHarvestAmount;
            }

            public int getPbsFrozen() {
                return pbsFrozen;
            }

            public void setPbsFrozen(int pbsFrozen) {
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

            public long getCrystalTime() {
                return crystalTime;
            }

            public void setCrystalTime(long crystalTime) {
                this.crystalTime = crystalTime;
            }

            public int getUsdtAmount() {
                return usdtAmount;
            }

            public void setUsdtAmount(int usdtAmount) {
                this.usdtAmount = usdtAmount;
            }

            public int getPbsDepositAmount() {
                return pbsDepositAmount;
            }

            public void setPbsDepositAmount(int pbsDepositAmount) {
                this.pbsDepositAmount = pbsDepositAmount;
            }

            public int getPbsTranAmount() {
                return pbsTranAmount;
            }

            public void setPbsTranAmount(int pbsTranAmount) {
                this.pbsTranAmount = pbsTranAmount;
            }

            public int getPbsDrawLockedAmount() {
                return pbsDrawLockedAmount;
            }

            public void setPbsDrawLockedAmount(int pbsDrawLockedAmount) {
                this.pbsDrawLockedAmount = pbsDrawLockedAmount;
            }

            public int getRegionNode() {
                return regionNode;
            }

            public void setRegionNode(int regionNode) {
                this.regionNode = regionNode;
            }

            public int getHatchAmount() {
                return hatchAmount;
            }

            public void setHatchAmount(int hatchAmount) {
                this.hatchAmount = hatchAmount;
            }

            public int getHatchDrawTime() {
                return hatchDrawTime;
            }

            public void setHatchDrawTime(int hatchDrawTime) {
                this.hatchDrawTime = hatchDrawTime;
            }

            public int getVipLevel() {
                return vipLevel;
            }

            public void setVipLevel(int vipLevel) {
                this.vipLevel = vipLevel;
            }

            public long getVipStartDate() {
                return vipStartDate;
            }

            public void setVipStartDate(long vipStartDate) {
                this.vipStartDate = vipStartDate;
            }

            public long getVipExpireDate() {
                return vipExpireDate;
            }

            public void setVipExpireDate(long vipExpireDate) {
                this.vipExpireDate = vipExpireDate;
            }

            public int getRebateCrystal() {
                return rebateCrystal;
            }

            public void setRebateCrystal(int rebateCrystal) {
                this.rebateCrystal = rebateCrystal;
            }

            public int getCrystalDrawLockedAmount() {
                return crystalDrawLockedAmount;
            }

            public void setCrystalDrawLockedAmount(int crystalDrawLockedAmount) {
                this.crystalDrawLockedAmount = crystalDrawLockedAmount;
            }

            public int getHighNode() {
                return highNode;
            }

            public void setHighNode(int highNode) {
                this.highNode = highNode;
            }

            public int getCrystalDrawTime() {
                return crystalDrawTime;
            }

            public void setCrystalDrawTime(int crystalDrawTime) {
                this.crystalDrawTime = crystalDrawTime;
            }

            public int getCrystalDrawZfbTime() {
                return crystalDrawZfbTime;
            }

            public void setCrystalDrawZfbTime(int crystalDrawZfbTime) {
                this.crystalDrawZfbTime = crystalDrawZfbTime;
            }

            public int getCrystalDrawZfbToBankTime() {
                return crystalDrawZfbToBankTime;
            }

            public void setCrystalDrawZfbToBankTime(int crystalDrawZfbToBankTime) {
                this.crystalDrawZfbToBankTime = crystalDrawZfbToBankTime;
            }

            public int getAmountCrowdfund() {
                return amountCrowdfund;
            }

            public void setAmountCrowdfund(int amountCrowdfund) {
                this.amountCrowdfund = amountCrowdfund;
            }

            public int getCrowdFundLevel() {
                return crowdFundLevel;
            }

            public void setCrowdFundLevel(int crowdFundLevel) {
                this.crowdFundLevel = crowdFundLevel;
            }

            public long getLastMarkTime() {
                return lastMarkTime;
            }

            public void setLastMarkTime(long lastMarkTime) {
                this.lastMarkTime = lastMarkTime;
            }

            public boolean isIsKeruyun() {
                return isKeruyun;
            }

            public void setIsKeruyun(boolean isKeruyun) {
                this.isKeruyun = isKeruyun;
            }

            public int getBlackCard() {
                return blackCard;
            }

            public void setBlackCard(int blackCard) {
                this.blackCard = blackCard;
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

            public boolean isShowInCharts() {
                return showInCharts;
            }

            public void setShowInCharts(boolean showInCharts) {
                this.showInCharts = showInCharts;
            }

            public long getSignupTime() {
                return signupTime;
            }

            public void setSignupTime(long signupTime) {
                this.signupTime = signupTime;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getRevoked() {
                return revoked;
            }

            public void setRevoked(String revoked) {
                this.revoked = revoked;
            }
        }

        public static class BasicInfoBean {
            /**
             * company : 一啊啊啊
             * position : 222把啊啊啊
             * gender : 1
             * professionalIdentity : 1002
             * showEdit : 1
             * seePrice : 0
             * friendPrice : 0
             * positionTitle : 一啊啊啊·222把啊啊啊
             * maidianPrice : 0
             * isRewarded : true
             * _id : 5bab3720103c004294c16acc
             * userId : 1134
             * realName : 杨
             * createdAt : 2018-09-26T15:37:04+08:00
             * updatedAt : 2019-12-12T15:07:13+08:00
             * __v : 0
             * school : 2222啊啊啊
             * headImg : /img/upload/userHeadImages/1548729891211_3.jpg
             * selfDesc : aa
             */

            private String company;
            private String position;
            private int gender;
            private int professionalIdentity;
            private int showEdit;
            private int seePrice;
            private int friendPrice;
            private String positionTitle;
            private int maidianPrice;
            private boolean isRewarded;
            private String _id;
            private int userId;
            private String realName;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String school;
            private String headImg;
            private String selfDesc;

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

            public int getSeePrice() {
                return seePrice;
            }

            public void setSeePrice(int seePrice) {
                this.seePrice = seePrice;
            }

            public int getFriendPrice() {
                return friendPrice;
            }

            public void setFriendPrice(int friendPrice) {
                this.friendPrice = friendPrice;
            }

            public String getPositionTitle() {
                return positionTitle;
            }

            public void setPositionTitle(String positionTitle) {
                this.positionTitle = positionTitle;
            }

            public int getMaidianPrice() {
                return maidianPrice;
            }

            public void setMaidianPrice(int maidianPrice) {
                this.maidianPrice = maidianPrice;
            }

            public boolean isIsRewarded() {
                return isRewarded;
            }

            public void setIsRewarded(boolean isRewarded) {
                this.isRewarded = isRewarded;
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

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
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
        }

        public static class StatisticsInfoBean {
            /**
             * basicCount : 1
             * blockchainCount : 0
             * workCount : 1
             * educationCount : 1
             * photoCount : 1
             * crystalCount : 0
             * crystalAmount : 0
             * _id : 5bab3701103c004294c16acb
             * userId : 1134
             * createdAt : 2018-09-26T15:36:33+08:00
             * updatedAt : 2019-01-09T10:23:21+08:00
             * __v : 0
             * userInfo : 1134
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
            private int userInfo;

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

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
            }
        }
    }
}
