package com.iruiyou.pet.bean;

/**
 * 类描述：注册-填写资料
 */
public class MustBasicBean {
    /**
     * csrfToken : null
     * data : {"statisticsInfo":{"updatedAt":"2018-09-29T15:17:15+08:00","_id":"5baf26f41e37115db0b8de7c","basicCount":0,"createdAt":"2018-09-29T15:17:08+08:00","workCount":0,"userId":58,"educationCount":0,"__v":0,"photoCount":1,"blockchainCount":0,"userInfo":58},"userInfo":{"phone":"18610411247","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5baf26f41e37115db0b8de7c","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$u54y6lt1LhC4ZjIpFAPmD.9itLVNsaTtzScDnhzx5xuklmcjgapHO","updatedAt":"2018-09-29T15:17:15+08:00","basicInfo":"5baf26f41e37115db0b8de7d","vcSendTime":0,"headImg":"/img/upload/userHeadImages/1538205435513_4.jpg","_id":58,"createdAt":"2018-09-29T15:16:34+08:00","registered":true,"inviteCode":"741394","combat":10,"clientIp":"221.219.205.164","lastHarvestAmount":0,"invitedCode":"519114"},"basicInfo":{"updatedAt":"2018-09-29T15:17:31+08:00","headImg":"img/upload/userHeadImages/1538205432598.jpg","_id":"5baf26f41e37115db0b8de7d","professionalIdentity":1,"createdAt":"2018-09-29T15:17:08+08:00","userId":58,"__v":0,"gender":0,"realName":"2121212","showEdit":1,"userInfo":58},"infoCount":4}
     * message : null
     * statusCode : 0
     * error : null
     * token : null
     */

//    private String csrfToken;
//    private DataBean data;
//    private String message;
//    private int statusCode;
//    private String error;
//    private String token;
//
//    public String getCsrfToken() {
//        return csrfToken;
//    }
//
//    public void setCsrfToken(String csrfToken) {
//        this.csrfToken = csrfToken;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
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
//    public String getError() {
//        return error;
//    }
//
//    public void setError(String error) {
//        this.error = error;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String token) {
//        this.token = token;
//    }
//
//    public static class DataBean {
//        /**
//         * statisticsInfo : {"updatedAt":"2018-09-29T15:17:15+08:00","_id":"5baf26f41e37115db0b8de7c","basicCount":0,"createdAt":"2018-09-29T15:17:08+08:00","workCount":0,"userId":58,"educationCount":0,"__v":0,"photoCount":1,"blockchainCount":0,"userInfo":58}
//         * userInfo : {"phone":"18610411247","pbsFrozen":0,"pbsAmount":0,"inviteCount":0,"statisticsInfo":"5baf26f41e37115db0b8de7c","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$u54y6lt1LhC4ZjIpFAPmD.9itLVNsaTtzScDnhzx5xuklmcjgapHO","updatedAt":"2018-09-29T15:17:15+08:00","basicInfo":"5baf26f41e37115db0b8de7d","vcSendTime":0,"headImg":"/img/upload/userHeadImages/1538205435513_4.jpg","_id":58,"createdAt":"2018-09-29T15:16:34+08:00","registered":true,"inviteCode":"741394","combat":10,"clientIp":"221.219.205.164","lastHarvestAmount":0,"invitedCode":"519114"}
//         * basicInfo : {"updatedAt":"2018-09-29T15:17:31+08:00","headImg":"img/upload/userHeadImages/1538205432598.jpg","_id":"5baf26f41e37115db0b8de7d","professionalIdentity":1,"createdAt":"2018-09-29T15:17:08+08:00","userId":58,"__v":0,"gender":0,"realName":"2121212","showEdit":1,"userInfo":58}
//         * infoCount : 4
//         */
//
//        private StatisticsInfoBean statisticsInfo;
//        private UserInfoBean userInfo;
//        private BasicInfoBean basicInfo;
//        private int infoCount;
//
//        public StatisticsInfoBean getStatisticsInfo() {
//            return statisticsInfo;
//        }
//
//        public void setStatisticsInfo(StatisticsInfoBean statisticsInfo) {
//            this.statisticsInfo = statisticsInfo;
//        }
//
//        public UserInfoBean getUserInfo() {
//            return userInfo;
//        }
//
//        public void setUserInfo(UserInfoBean userInfo) {
//            this.userInfo = userInfo;
//        }
//
//        public BasicInfoBean getBasicInfo() {
//            return basicInfo;
//        }
//
//        public void setBasicInfo(BasicInfoBean basicInfo) {
//            this.basicInfo = basicInfo;
//        }
//
//        public int getInfoCount() {
//            return infoCount;
//        }
//
//        public void setInfoCount(int infoCount) {
//            this.infoCount = infoCount;
//        }
//
//        public static class StatisticsInfoBean {
//            /**
//             * updatedAt : 2018-09-29T15:17:15+08:00
//             * _id : 5baf26f41e37115db0b8de7c
//             * basicCount : 0
//             * createdAt : 2018-09-29T15:17:08+08:00
//             * workCount : 0
//             * userId : 58
//             * educationCount : 0
//             * __v : 0
//             * photoCount : 1
//             * blockchainCount : 0
//             * userInfo : 58
//             */
//
//            private String updatedAt;
//            private String _id;
//            private int basicCount;
//            private String createdAt;
//            private int workCount;
//            private int userId;
//            private int educationCount;
//            private int __v;
//            private int photoCount;
//            private int blockchainCount;
//            private int userInfo;
//
//            public String getUpdatedAt() {
//                return updatedAt;
//            }
//
//            public void setUpdatedAt(String updatedAt) {
//                this.updatedAt = updatedAt;
//            }
//
//            public String get_id() {
//                return _id;
//            }
//
//            public void set_id(String _id) {
//                this._id = _id;
//            }
//
//            public int getBasicCount() {
//                return basicCount;
//            }
//
//            public void setBasicCount(int basicCount) {
//                this.basicCount = basicCount;
//            }
//
//            public String getCreatedAt() {
//                return createdAt;
//            }
//
//            public void setCreatedAt(String createdAt) {
//                this.createdAt = createdAt;
//            }
//
//            public int getWorkCount() {
//                return workCount;
//            }
//
//            public void setWorkCount(int workCount) {
//                this.workCount = workCount;
//            }
//
//            public int getUserId() {
//                return userId;
//            }
//
//            public void setUserId(int userId) {
//                this.userId = userId;
//            }
//
//            public int getEducationCount() {
//                return educationCount;
//            }
//
//            public void setEducationCount(int educationCount) {
//                this.educationCount = educationCount;
//            }
//
//            public int get__v() {
//                return __v;
//            }
//
//            public void set__v(int __v) {
//                this.__v = __v;
//            }
//
//            public int getPhotoCount() {
//                return photoCount;
//            }
//
//            public void setPhotoCount(int photoCount) {
//                this.photoCount = photoCount;
//            }
//
//            public int getBlockchainCount() {
//                return blockchainCount;
//            }
//
//            public void setBlockchainCount(int blockchainCount) {
//                this.blockchainCount = blockchainCount;
//            }
//
//            public int getUserInfo() {
//                return userInfo;
//            }
//
//            public void setUserInfo(int userInfo) {
//                this.userInfo = userInfo;
//            }
//        }
//
//        public static class UserInfoBean {
//            /**
//             * phone : 18610411247
//             * pbsFrozen : 0
//             * pbsAmount : 0
//             * inviteCount : 0
//             * statisticsInfo : 5baf26f41e37115db0b8de7c
//             * __v : 0
//             * countryCode : 86
//             * vrfCode : 用了就过期
//             * password : $2a$10$u54y6lt1LhC4ZjIpFAPmD.9itLVNsaTtzScDnhzx5xuklmcjgapHO
//             * updatedAt : 2018-09-29T15:17:15+08:00
//             * basicInfo : 5baf26f41e37115db0b8de7d
//             * vcSendTime : 0
//             * headImg : /img/upload/userHeadImages/1538205435513_4.jpg
//             * _id : 58
//             * createdAt : 2018-09-29T15:16:34+08:00
//             * registered : true
//             * inviteCode : 741394
//             * combat : 10
//             * clientIp : 221.219.205.164
//             * lastHarvestAmount : 0
//             * invitedCode : 519114
//             */
//
//            private String phone;
//            private int pbsFrozen;
//            private int pbsAmount;
//            private int inviteCount;
//            private String statisticsInfo;
//            private int __v;
//            private String countryCode;
//            private String vrfCode;
//            private String password;
//            private String updatedAt;
//            private String basicInfo;
//            private int vcSendTime;
//            private String headImg;
//            private int _id;
//            private String createdAt;
//            private boolean registered;
//            private String inviteCode;
//            private int combat;
//            private String clientIp;
//            private int lastHarvestAmount;
//            private String invitedCode;
//
//            public String getPhone() {
//                return phone;
//            }
//
//            public void setPhone(String phone) {
//                this.phone = phone;
//            }
//
//            public int getPbsFrozen() {
//                return pbsFrozen;
//            }
//
//            public void setPbsFrozen(int pbsFrozen) {
//                this.pbsFrozen = pbsFrozen;
//            }
//
//            public int getPbsAmount() {
//                return pbsAmount;
//            }
//
//            public void setPbsAmount(int pbsAmount) {
//                this.pbsAmount = pbsAmount;
//            }
//
//            public int getInviteCount() {
//                return inviteCount;
//            }
//
//            public void setInviteCount(int inviteCount) {
//                this.inviteCount = inviteCount;
//            }
//
//            public String getStatisticsInfo() {
//                return statisticsInfo;
//            }
//
//            public void setStatisticsInfo(String statisticsInfo) {
//                this.statisticsInfo = statisticsInfo;
//            }
//
//            public int get__v() {
//                return __v;
//            }
//
//            public void set__v(int __v) {
//                this.__v = __v;
//            }
//
//            public String getCountryCode() {
//                return countryCode;
//            }
//
//            public void setCountryCode(String countryCode) {
//                this.countryCode = countryCode;
//            }
//
//            public String getVrfCode() {
//                return vrfCode;
//            }
//
//            public void setVrfCode(String vrfCode) {
//                this.vrfCode = vrfCode;
//            }
//
//            public String getPassword() {
//                return password;
//            }
//
//            public void setPassword(String password) {
//                this.password = password;
//            }
//
//            public String getUpdatedAt() {
//                return updatedAt;
//            }
//
//            public void setUpdatedAt(String updatedAt) {
//                this.updatedAt = updatedAt;
//            }
//
//            public String getBasicInfo() {
//                return basicInfo;
//            }
//
//            public void setBasicInfo(String basicInfo) {
//                this.basicInfo = basicInfo;
//            }
//
//            public int getVcSendTime() {
//                return vcSendTime;
//            }
//
//            public void setVcSendTime(int vcSendTime) {
//                this.vcSendTime = vcSendTime;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public int get_id() {
//                return _id;
//            }
//
//            public void set_id(int _id) {
//                this._id = _id;
//            }
//
//            public String getCreatedAt() {
//                return createdAt;
//            }
//
//            public void setCreatedAt(String createdAt) {
//                this.createdAt = createdAt;
//            }
//
//            public boolean isRegistered() {
//                return registered;
//            }
//
//            public void setRegistered(boolean registered) {
//                this.registered = registered;
//            }
//
//            public String getInviteCode() {
//                return inviteCode;
//            }
//
//            public void setInviteCode(String inviteCode) {
//                this.inviteCode = inviteCode;
//            }
//
//            public int getCombat() {
//                return combat;
//            }
//
//            public void setCombat(int combat) {
//                this.combat = combat;
//            }
//
//            public String getClientIp() {
//                return clientIp;
//            }
//
//            public void setClientIp(String clientIp) {
//                this.clientIp = clientIp;
//            }
//
//            public int getLastHarvestAmount() {
//                return lastHarvestAmount;
//            }
//
//            public void setLastHarvestAmount(int lastHarvestAmount) {
//                this.lastHarvestAmount = lastHarvestAmount;
//            }
//
//            public String getInvitedCode() {
//                return invitedCode;
//            }
//
//            public void setInvitedCode(String invitedCode) {
//                this.invitedCode = invitedCode;
//            }
//        }
//
//        public static class BasicInfoBean {
//            /**
//             * updatedAt : 2018-09-29T15:17:31+08:00
//             * headImg : img/upload/userHeadImages/1538205432598.jpg
//             * _id : 5baf26f41e37115db0b8de7d
//             * professionalIdentity : 1
//             * createdAt : 2018-09-29T15:17:08+08:00
//             * userId : 58
//             * __v : 0
//             * gender : 0
//             * realName : 2121212
//             * showEdit : 1
//             * userInfo : 58
//             */
//
//            private String updatedAt;
//            private String headImg;
//            private String _id;
//            private int professionalIdentity;
//            private String createdAt;
//            private int userId;
//            private int __v;
//            private int gender;
//            private String realName;
//            private int showEdit;
//            private int userInfo;
//
//            public String getUpdatedAt() {
//                return updatedAt;
//            }
//
//            public void setUpdatedAt(String updatedAt) {
//                this.updatedAt = updatedAt;
//            }
//
//            public String getHeadImg() {
//                return headImg;
//            }
//
//            public void setHeadImg(String headImg) {
//                this.headImg = headImg;
//            }
//
//            public String get_id() {
//                return _id;
//            }
//
//            public void set_id(String _id) {
//                this._id = _id;
//            }
//
//            public int getProfessionalIdentity() {
//                return professionalIdentity;
//            }
//
//            public void setProfessionalIdentity(int professionalIdentity) {
//                this.professionalIdentity = professionalIdentity;
//            }
//
//            public String getCreatedAt() {
//                return createdAt;
//            }
//
//            public void setCreatedAt(String createdAt) {
//                this.createdAt = createdAt;
//            }
//
//            public int getUserId() {
//                return userId;
//            }
//
//            public void setUserId(int userId) {
//                this.userId = userId;
//            }
//
//            public int get__v() {
//                return __v;
//            }
//
//            public void set__v(int __v) {
//                this.__v = __v;
//            }
//
//            public int getGender() {
//                return gender;
//            }
//
//            public void setGender(int gender) {
//                this.gender = gender;
//            }
//
//            public String getRealName() {
//                return realName;
//            }
//
//            public void setRealName(String realName) {
//                this.realName = realName;
//            }
//
//            public int getShowEdit() {
//                return showEdit;
//            }
//
//            public void setShowEdit(int showEdit) {
//                this.showEdit = showEdit;
//            }
//
//            public int getUserInfo() {
//                return userInfo;
//            }
//
//            public void setUserInfo(int userInfo) {
//                this.userInfo = userInfo;
//            }
//        }
//    }
    /**
     * csrfToken : null
     * data : {"updatedAt":"2018-09-29T14:30:58+08:00","headImg":"img/upload/userHeadImages/null","_id":"5baf1bb41e37115db0b8de74","professionalIdentity":1,"createdAt":"2018-09-29T14:29:08+08:00","userId":55,"__v":0,"gender":0,"realName":"1212","showEdit":1,"userInfo":55}
     * message : 保存成功
     * statusCode : 0
     * error : null
     * token : null
     */

    private String csrfToken;
    private DataBean data;
    private String message;
    private int statusCode;
    private String error;
    private String token;

    public Object getCsrfToken() {
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
         * updatedAt : 2018-09-29T14:30:58+08:00
         * headImg : img/upload/userHeadImages/null
         * _id : 5baf1bb41e37115db0b8de74
         * professionalIdentity : 1
         * createdAt : 2018-09-29T14:29:08+08:00
         * userId : 55
         * __v : 0
         * gender : 0
         * realName : 1212
         * showEdit : 1
         * userInfo : 55
         */

        private String updatedAt;
        private String headImg;
        private String _id;
        private int professionalIdentity;
        private String createdAt;
        private String userId;
        private int __v;
        private int gender;
        private String realName;
        private int showEdit;
        private int userInfo;

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

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
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



}
