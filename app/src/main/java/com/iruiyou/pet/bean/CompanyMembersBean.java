package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:获取公司队员
 * 作者：Created by sgf
 */

public class CompanyMembersBean implements Serializable {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"gender":0,"professionalIdentity":5,"showEdit":1,"_id":"5b767ef87c63c218cb3edf35","userId":1,"realName":"2330","school":"清华大学","education":7,"company":"腾讯","position":"工程师","country":"中国","number":8,"nature":"互联网","selfDesc":"厉害了","createdAt":"2018-08-17T15:53:28+08:00","updatedAt":"2018-11-07T14:03:08+08:00","__v":0,"userInfo":1,"companyId":3,"companyIndex":1}]
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
         * gender : 0
         * professionalIdentity : 5
         * showEdit : 1
         * _id : 5b767ef87c63c218cb3edf35
         * userId : 1
         * realName : 2330
         * school : 清华大学
         * education : 7
         * company : 腾讯
         * position : 工程师
         * country : 中国
         * number : 8
         * nature : 互联网
         * selfDesc : 厉害了
         * createdAt : 2018-08-17T15:53:28+08:00
         * updatedAt : 2018-11-07T14:03:08+08:00
         * __v : 0
         * userInfo : 1
         * companyId : 3
         * companyIndex : 1
         */

        private int gender;
        private int professionalIdentity;
        private int showEdit;
        private String _id;
        private int userId;
        private String realName;
        private String school;
        private int education;
        private String company;
        private String position;
        private String country;
        private int number;
        private String nature;
        private String selfDesc;
        private String createdAt;
        private String updatedAt;
        private String headImg;
        private int __v;
        private int userInfo;
        private int companyId;
        private int companyIndex;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
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

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getCompanyIndex() {
            return companyIndex;
        }

        public void setCompanyIndex(int companyIndex) {
            this.companyIndex = companyIndex;
        }
    }
}
