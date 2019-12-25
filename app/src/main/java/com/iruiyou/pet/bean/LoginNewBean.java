package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 类描述:新登陆
 * 作者：sgf
 */

public class LoginNewBean implements Serializable {


    /**
     * csrfToken : null
     * data : {"statisticsInfo":{"updatedAt":"2018-09-30T19:58:53+08:00","_id":"5b9b76de6dad7e37d346b21f","basicCount":1,"createdAt":"2018-09-14T16:52:46+08:00","workCount":7,"userId":875,"educationCount":4,"__v":0,"photoCount":1,"blockchainCount":1,"userInfo":875},"userInfo":{"phone":"15718807582","pbsFrozen":0,"pbsAmount":0.11100000000000004,"inviteCount":0,"statisticsInfo":"5b9b76de6dad7e37d346b21f","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$tZzJE7CJnprNe07jNBAqW.IAnImvivJ4vsNQs8n/blcY7C8bu2lU6","updatedAt":"2018-10-08T10:22:29+08:00","basicInfo":"5b9b77236dad7e37d346b221","vcSendTime":0,"headImg":"/img/upload/userHeadImages/1538127789568_8.jpg","_id":875,"createdAt":"2018-09-14T16:52:46+08:00","registered":true,"name":"你好q","inviteCode":"388593","combat":80,"lastHarvestAmount":0.11100000000000004,"invitedCode":"614829"},"basicInfo":{"position":"开发","professionalIdentity":-1,"__v":0,"number":6,"education":5,"country":"香港","updatedAt":"2018-09-30T20:53:25+08:00","headImg":"/img/upload/userHeadImages/1538127789568_8.jpg","school":"浙大","selfDesc":"","nature":"","_id":"5b9b77236dad7e37d346b221","createdAt":"2018-09-14T16:53:55+08:00","company":"北京","userId":875,"gender":0,"realName":"深深地","showEdit":0},"infoCount":4}
     * message : 登录成功
     * statusCode : 0
     * error : null
     * "rcToken": "8a\/68VddXEIKPWmpYtP8w6O9TsO7\/LpP7ndT6Yid7NTFi4nAme08cuUz3+336LUfjn6CfJSG0uarAhJWWrm2tw==",
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ2Y1NlbmRUaW1lIjowLCJyZWdpc3RlcmVkIjp0cnVlLCJpbnZpdGVDb2RlIjoiMzg4NTkzIiwiaW52aXRlZENvZGUiOiI2MTQ4MjkiLCJpbnZpdGVDb3VudCI6MCwicGJzQW1vdW50IjowLjExMTAwMDAwMDAwMDAwMDA0LCJsYXN0SGFydmVzdEFtb3VudCI6MC4xMTEwMDAwMDAwMDAwMDAwNCwicGJzRnJvemVuIjowLCJjb21iYXQiOjgwLCJfaWQiOjg3NSwicGhvbmUiOiIxNTcxODgwNzU4MiIsInZyZkNvZGUiOiLnlKjkuoblsLHov4fmnJ8iLCJjb3VudHJ5Q29kZSI6Ijg2IiwiaGVhZEltZyI6Ii9pbWcvdXBsb2FkL3VzZXJIZWFkSW1hZ2VzLzE1MzgxMjc3ODk1NjhfOC5qcGciLCJjcmVhdGVkQXQiOiIyMDE4LTA5LTE0VDE2OjUyOjQ2KzA4OjAwIiwidXBkYXRlZEF0IjoiMjAxOC0xMC0wOFQxMDoyMjoyOSswODowMCIsIl9fdiI6MCwibmFtZSI6IuS9oOWlvXEiLCJwYXNzd29yZCI6IiQyYSQxMCR0WnpKRTdDSm5wck5lMDdqTkJBcVcuSUFuSW12aXZKNHZzTlFzOG4vYmxjWTdDOGJ1MmxVNiIsImJhc2ljSW5mbyI6IjViOWI3NzIzNmRhZDdlMzdkMzQ2YjIyMSIsInN0YXRpc3RpY3NJbmZvIjoiNWI5Yjc2ZGU2ZGFkN2UzN2QzNDZiMjFmIn0.NmcfcEOADi5XL5cocKDAh3BDKd0wNaRJVBY7irkuP68
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

    public static class DataBean implements Serializable{
        /**
         * statisticsInfo : {"updatedAt":"2018-09-30T19:58:53+08:00","_id":"5b9b76de6dad7e37d346b21f","basicCount":1,"createdAt":"2018-09-14T16:52:46+08:00","workCount":7,"userId":875,"educationCount":4,"__v":0,"photoCount":1,"blockchainCount":1,"userInfo":875}
         * userInfo : {"phone":"15718807582","pbsFrozen":0,"pbsAmount":0.11100000000000004,"inviteCount":0,"statisticsInfo":"5b9b76de6dad7e37d346b21f","__v":0,"countryCode":"86","vrfCode":"用了就过期","password":"$2a$10$tZzJE7CJnprNe07jNBAqW.IAnImvivJ4vsNQs8n/blcY7C8bu2lU6","updatedAt":"2018-10-08T10:22:29+08:00","basicInfo":"5b9b77236dad7e37d346b221","vcSendTime":0,"headImg":"/img/upload/userHeadImages/1538127789568_8.jpg","_id":875,"createdAt":"2018-09-14T16:52:46+08:00","registered":true,"name":"你好q","inviteCode":"388593","combat":80,"lastHarvestAmount":0.11100000000000004,"invitedCode":"614829"}
         * basicInfo : {"position":"开发","professionalIdentity":-1,"__v":0,"number":6,"education":5,"country":"香港","updatedAt":"2018-09-30T20:53:25+08:00","headImg":"/img/upload/userHeadImages/1538127789568_8.jpg","school":"浙大","selfDesc":"","nature":"","_id":"5b9b77236dad7e37d346b221","createdAt":"2018-09-14T16:53:55+08:00","company":"北京","userId":875,"gender":0,"realName":"深深地","showEdit":0}
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

        public static class StatisticsInfoBean implements Serializable{
            /**
             * updatedAt : 2018-09-30T19:58:53+08:00
             * _id : 5b9b76de6dad7e37d346b21f
             * basicCount : 1
             * createdAt : 2018-09-14T16:52:46+08:00
             * workCount : 7
             * userId : 875
             * educationCount : 4
             * __v : 0
             * photoCount : 1
             * blockchainCount : 1
             * userInfo : 875
             */

            private String updatedAt;
            private String _id;
            private int basicCount;
            private String createdAt;
            private int workCount;
            private int userId;
            private int educationCount;
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

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getEducationCount() {
                return educationCount;
            }

            public void setEducationCount(int educationCount) {
                this.educationCount = educationCount;
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

        public static class UserInfoBean implements Serializable{
            /**
             * phone : 15718807582
             * pbsFrozen : 0
             * pbsAmount : 0.11100000000000004
             * inviteCount : 0
             * statisticsInfo : 5b9b76de6dad7e37d346b21f
             * __v : 0
             * countryCode : 86
             * vrfCode : 用了就过期
             * password : $2a$10$tZzJE7CJnprNe07jNBAqW.IAnImvivJ4vsNQs8n/blcY7C8bu2lU6
             * updatedAt : 2018-10-08T10:22:29+08:00
             * basicInfo : 5b9b77236dad7e37d346b221
             * vcSendTime : 0
             * headImg : /img/upload/userHeadImages/1538127789568_8.jpg
             * _id : 875
             * "userId":67,
             * createdAt : 2018-09-14T16:52:46+08:00
             * registered : true
             * name : 你好q
             * inviteCode : 388593
             * combat : 80
             * lastHarvestAmount : 0.11100000000000004
             * invitedCode : 614829
             */

            private String phone;
            private double pbsFrozen;
            private double pbsAmount;
            private int inviteCount;
            private String statisticsInfo;
            private int __v;
            private String countryCode;
            private String vrfCode;
            private String password;
            private String updatedAt;
            private String basicInfo;
            private long vcSendTime;
            private String headImg;
            private String _id;
            private String userId;
            private String createdAt;
            private boolean registered;
            private String name;
            private String inviteCode;
            private int combat;
            private double lastHarvestAmount;
            private String invitedCode;
            private String userChannel;



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

            public long getVcSendTime() {
                return vcSendTime;
            }

            public void setVcSendTime(long vcSendTime) {
                this.vcSendTime = vcSendTime;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
            public String getUserChannel() {
                return userChannel;
            }

            public void setUserChannel(String userChannel) {
                this.userChannel = userChannel;
            }

        }

        public static class BasicInfoBean implements Serializable{
            /**
             * position : 开发
             * professionalIdentity : -1
             * __v : 0
             * number : 6
             * education : 5
             * country : 香港
             * updatedAt : 2018-09-30T20:53:25+08:00
             * headImg : /img/upload/userHeadImages/1538127789568_8.jpg
             * school : 浙大
             * selfDesc :
             * nature :
             * _id : 5b9b77236dad7e37d346b221
             * createdAt : 2018-09-14T16:53:55+08:00
             * company : 北京
             * userId : 875
             * gender : 0
             * realName : 深深地
             * showEdit : 0
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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public int getProfessionalIdentity() {
                return professionalIdentity;
            }

            public void setProfessionalIdentity(int professionalIdentity) {
                this.professionalIdentity = professionalIdentity;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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
        }
    }
}
