package com.iruiyou.pet.bean;

import java.util.ArrayList;

public class RecommendInterpersonalBean {

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public static class DataBean{

        private ArrayList<ItemBasicsInfo> docs;

        public ArrayList<ItemBasicsInfo> getDocs() {
            return docs;
        }

        public void setDocs(ArrayList<ItemBasicsInfo> docs) {
            this.docs = docs;
        }
    }

    public class ItemBasicsInfo
    {
        private  int gender;//":0,
        private  int professionalIdentity;//":2,
        private  int showEdit;//":1,
        private  double seePrice;//":1,
        private  double friendPrice;//":10,
        private String _id;//"5b767ef87c63c218cb3edf4a",
        private long userId;//8,
        private String realName;//"Mu'roq",
        private String school;//"浙江大学",
        private  int education;//5,
        private String company;//"Blizzard",
        private String position;//"CVO",
        private String country;//"California",
        private  int number;//8,
        private String nature;//"企业",
        private String headImg;//"/img/upload/userHeadImages/1542283329346_0.jpg",
        private String selfDesc;//"",
        private String createdAt;//"2018-08-17T15:53:28+08:00",
        private String updatedAt;//"2018-11-15T20:02:09+08:00",
        private  int __v;//0,
        private  int userInfo;//8


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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public int getEducation() {
            return education;
        }

        public void setEducation(int education) {
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
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
