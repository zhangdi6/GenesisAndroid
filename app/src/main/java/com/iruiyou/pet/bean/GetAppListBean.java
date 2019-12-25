package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 好友列表的数据对象
 */
public class GetAppListBean {
    private String rcToken;
    private String csrfToken;
    private String message;
    private int statusCode;
    private String error;
    private String token;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int infoCount;
        private List<ApplyBean> applyMe;
        private List<ApplyBean> myApply;

        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public List<ApplyBean> getApplyMe() {
            return applyMe;
        }

        public void setApplyMe(List<ApplyBean> applyMe) {
            this.applyMe = applyMe;
        }

        public List<ApplyBean> getMyApply() {
            return myApply;
        }

        public void setMyApply(List<ApplyBean> myApply) {
            this.myApply = myApply;
        }
    }

    public class ApplyBean {
        private String _id;//":"5c46f32db1326e4caf30330d",
        private int userIdA;//":1605,
        private int userIdB;//1814,
        private int userInfoA;//1605,
        private int userInfoB;//1814,
        private int statusA2B;//0,
        private int statusB2A;//1,
        private int timeA2B;//0,
        private long timeB2A;//1548153645883,
        private String createdAt;//"2019-01-22T18:40:45+08:00",
        private String updatedAt;//"2019-01-22T18:40:45+08:00",
        private int __v;//:0,
        private BasicInfoBean basicInfoA;
        private BasicInfoBean basicInfoB;
        private Statistics statistics;

        public Statistics getStatistics() {
            return statistics;
        }

        public void setStatistics(Statistics statistics) {
            this.statistics = statistics;
        }

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

        public int getUserInfoA() {
            return userInfoA;
        }

        public void setUserInfoA(int userInfoA) {
            this.userInfoA = userInfoA;
        }

        public int getUserInfoB() {
            return userInfoB;
        }

        public void setUserInfoB(int userInfoB) {
            this.userInfoB = userInfoB;
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

        public int getTimeA2B() {
            return timeA2B;
        }

        public void setTimeA2B(int timeA2B) {
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

        public BasicInfoBean getBasicInfoA() {
            return basicInfoA;
        }

        public void setBasicInfoA(BasicInfoBean basicInfoA) {
            this.basicInfoA = basicInfoA;
        }

        public BasicInfoBean getBasicInfoB() {
            return basicInfoB;
        }

        public void setBasicInfoB(BasicInfoBean basicInfoB) {
            this.basicInfoB = basicInfoB;
        }
    }

//    public static class BasicInfo {
//        private int gender;//0,
//        private int professionalIdentity;//1,
//        private int showEdit;//1,
//        private double seePrice;//1,
//        private double friendPrice;//10,
//        private String _id;//"5c15d7825d8a806a9304dbb5",
//        private int userId;//1605,
//        private String realName;//"啊三",
//        private String createdAt;//"2018-12-16T12:41:38+08:00",
//        private String updatedAt;//"2019-01-09T10:56:34+08:00",
//        private int __v;//0,
//        private String headImg;//"/img/upload/userHeadImages/1547002593962_4.jpg",
//        private String company;//"小不点",
//        private int education;//0,
//        private int number;//0,
//        private String position;//"开发",
//        private String school;//"西北大学"
//
//        public int getGender() {
//            return gender;
//        }
//
//        public void setGender(int gender) {
//            this.gender = gender;
//        }
//
//        public int getProfessionalIdentity() {
//            return professionalIdentity;
//        }
//
//        public void setProfessionalIdentity(int professionalIdentity) {
//            this.professionalIdentity = professionalIdentity;
//        }
//
//        public int getShowEdit() {
//            return showEdit;
//        }
//
//        public void setShowEdit(int showEdit) {
//            this.showEdit = showEdit;
//        }
//
//        public double getSeePrice() {
//            return seePrice;
//        }
//
//        public void setSeePrice(double seePrice) {
//            this.seePrice = seePrice;
//        }
//
//        public double getFriendPrice() {
//            return friendPrice;
//        }
//
//        public void setFriendPrice(double friendPrice) {
//            this.friendPrice = friendPrice;
//        }
//
//        public String get_id() {
//            return _id;
//        }
//
//        public void set_id(String _id) {
//            this._id = _id;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public String getRealName() {
//            return realName;
//        }
//
//        public void setRealName(String realName) {
//            this.realName = realName;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public void setCreatedAt(String createdAt) {
//            this.createdAt = createdAt;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public void setUpdatedAt(String updatedAt) {
//            this.updatedAt = updatedAt;
//        }
//
//        public int get__v() {
//            return __v;
//        }
//
//        public void set__v(int __v) {
//            this.__v = __v;
//        }
//
//        public String getHeadImg() {
//            return headImg;
//        }
//
//        public void setHeadImg(String headImg) {
//            this.headImg = headImg;
//        }
//
//        public String getCompany() {
//            return company;
//        }
//
//        public void setCompany(String company) {
//            this.company = company;
//        }
//
//        public int getEducation() {
//            return education;
//        }
//
//        public void setEducation(int education) {
//            this.education = education;
//        }
//
//        public int getNumber() {
//            return number;
//        }
//
//        public void setNumber(int number) {
//            this.number = number;
//        }
//
//        public String getPosition() {
//            return position;
//        }
//
//        public void setPosition(String position) {
//            this.position = position;
//        }
//
//        public String getSchool() {
//            return school;
//        }
//
//        public void setSchool(String school) {
//            this.school = school;
//        }
//    }

    public static class Statistics {
        private int basicCount;//0,
        private int blockchainCount;//0,
        private int workCount;//0,
        private int educationCount;//1,
        private int photoCount;//1,
        private int crystalCount;//0,
        private int crystalAmount;//0,
        private String _id;//5c21b9edd7bc34399985ef4a",
        private int userId;//1814,
        private int userInfo;//1814,
        private String createdAt;//2018-12-25T13:02:37+08:00",
        private String updatedAt;//2018-12-25T13:22:06+08:00",
        private int __v;//0

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

}
