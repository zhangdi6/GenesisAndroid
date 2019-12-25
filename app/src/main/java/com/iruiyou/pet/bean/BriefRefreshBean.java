package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:简历
 * 创建日期:2018/8/14 on 15:29
 * 作者:JiaoPeiRong
 */
public class BriefRefreshBean implements Serializable{

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"basicInfo":{"school":"学校","education":"学历","company":"公司","position":"职位","country":"公司注册地","number":"公司人数","nature":"公司性质","headImg":"/img/upload/userHeadImages/userHead.png","selfDesc":"无","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"uu","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0},"blockchainInfo":{"identityLocation":"区块链爱好者","time":"工程师","position":"编程","positionDesc":"区块链","_id":"5b6f94874f5fb36df9eff682","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T11:35:34+08:00","__v":0},"workInfos":[{"_id":"5b72de148adb7b6ed49ea827","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T21:50:12+08:00","updatedAt":"2018-08-14T21:50:12+08:00","__v":0},{"_id":"5b72de268adb7b6ed49ea828","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T21:50:30+08:00","updatedAt":"2018-08-14T21:50:30+08:00","__v":0},{"_id":"5b72f8c98adb7b6ed49ea829","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T23:44:09+08:00","updatedAt":"2018-08-14T23:44:09+08:00","__v":0},{"_id":"5b738c738adb7b6ed49ea82a","userId":26,"position":"0000","company":"11111","number":"99人以上","duration":"22222","jobDesc":"222555","createdAt":"2018-08-15T10:14:11+08:00","updatedAt":"2018-08-15T10:14:11+08:00","__v":0}],"educationInfos":[{"_id":"5b739d448adb7b6ed49ea82b","userId":26,"school":"清华","education":"本科","major":"软工","duration":"2017","experience":"test","createdAt":"2018-08-15T11:25:56+08:00","updatedAt":"2018-08-15T11:25:56+08:00","__v":0}]}
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
         * basicInfo : {"school":"学校","education":"学历","company":"公司","position":"职位","country":"公司注册地","number":"公司人数","nature":"公司性质","headImg":"/img/upload/userHeadImages/userHead.png","selfDesc":"无","_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"uu","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}
         * blockchainInfo : {"identityLocation":"区块链爱好者","time":"工程师","position":"编程","positionDesc":"区块链","_id":"5b6f94874f5fb36df9eff682","userId":26,"createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T11:35:34+08:00","__v":0}
         * workInfos : [{"_id":"5b72de148adb7b6ed49ea827","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T21:50:12+08:00","updatedAt":"2018-08-14T21:50:12+08:00","__v":0},{"_id":"5b72de268adb7b6ed49ea828","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T21:50:30+08:00","updatedAt":"2018-08-14T21:50:30+08:00","__v":0},{"_id":"5b72f8c98adb7b6ed49ea829","userId":26,"position":"总监","company":"京东","number":"99人以上","duration":"2015-2018","jobDesc":"负责系统架构设计","createdAt":"2018-08-14T23:44:09+08:00","updatedAt":"2018-08-14T23:44:09+08:00","__v":0},{"_id":"5b738c738adb7b6ed49ea82a","userId":26,"position":"0000","company":"11111","number":"99人以上","duration":"22222","jobDesc":"222555","createdAt":"2018-08-15T10:14:11+08:00","updatedAt":"2018-08-15T10:14:11+08:00","__v":0}]
         * educationInfos : [{"_id":"5b739d448adb7b6ed49ea82b","userId":26,"school":"清华","education":"本科","major":"软工","duration":"2017","experience":"test","createdAt":"2018-08-15T11:25:56+08:00","updatedAt":"2018-08-15T11:25:56+08:00","__v":0}]
         */

        private BasicInfoBean basicInfo;
        private BlockchainInfoBean blockchainInfo;
        private List<WorkInfosBean> workInfos;
        private List<EducationInfosBean> educationInfos;

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public BlockchainInfoBean getBlockchainInfo() {
            return blockchainInfo;
        }

        public void setBlockchainInfo(BlockchainInfoBean blockchainInfo) {
            this.blockchainInfo = blockchainInfo;
        }

        public List<WorkInfosBean> getWorkInfos() {
            return workInfos;
        }

        public void setWorkInfos(List<WorkInfosBean> workInfos) {
            this.workInfos = workInfos;
        }

        public List<EducationInfosBean> getEducationInfos() {
            return educationInfos;
        }

        public void setEducationInfos(List<EducationInfosBean> educationInfos) {
            this.educationInfos = educationInfos;
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
             */

            private String school;
            private int education;
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
            private String positionTitle;//职位头衔

            public String getPositionTitle() {
                return positionTitle;
            }

            public void setPositionTitle(String positionTitle) {
                this.positionTitle = positionTitle;
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

        public static class BlockchainInfoBean implements Serializable{
            /**
             * identityLocation : 区块链爱好者
             * time : 工程师
             * position : 编程
             * positionDesc : 区块链
             * _id : 5b6f94874f5fb36df9eff682
             * userId : 26
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-08-17T11:35:34+08:00
             * __v : 0
             */

            private int identityLocation;
            private String time;
            private String position;
            private String positionDesc;
            private String _id;
            private int userId;
            private String createdAt;
            private String updatedAt;
            private int __v;

            public int getIdentityLocation() {
                return identityLocation;
            }

            public void setIdentityLocation(int identityLocation) {
                this.identityLocation = identityLocation;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getPositionDesc() {
                return positionDesc;
            }

            public void setPositionDesc(String positionDesc) {
                this.positionDesc = positionDesc;
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

        public static class WorkInfosBean implements Serializable{
            /**
             * _id : 5b72de148adb7b6ed49ea827
             * userId : 26
             * position : 总监
             * company : 京东
             * number : 99人以上
             * duration : 2015-2018
             * jobDesc : 负责系统架构设计
             * createdAt : 2018-08-14T21:50:12+08:00
             * updatedAt : 2018-08-14T21:50:12+08:00
             * __v : 0
             */

            private String _id;
            private int userId;
            private String position;
            private String company;
            private int number;
            private String duration;
            private String jobDesc;
            private String createdAt;
            private String updatedAt;
            private int __v;

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

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getJobDesc() {
                return jobDesc;
            }

            public void setJobDesc(String jobDesc) {
                this.jobDesc = jobDesc;
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

        public static class EducationInfosBean implements Serializable{
            /**
             * _id : 5b739d448adb7b6ed49ea82b
             * userId : 26
             * school : 清华
             * education : 本科
             * major : 软工
             * duration : 2017
             * experience : test
             * createdAt : 2018-08-15T11:25:56+08:00
             * updatedAt : 2018-08-15T11:25:56+08:00
             * __v : 0
             */

            private String _id;
            private int userId;
            private String school;
            private int education;
            private String major;
            private String duration;
            private String experience;
            private String createdAt;
            private String updatedAt;
            private int __v;

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

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getDuration() {
                return duration;
            }

            public void setDuration(String duration) {
                this.duration = duration;
            }

            public String getExperience() {
                return experience;
            }

            public void setExperience(String experience) {
                this.experience = experience;
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
