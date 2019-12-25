package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述:获取群组信息
 * 作者：Created by sgf
 */

public class GroupMembersBean implements Serializable {


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"_id":"5be02429e8884ab18aa7cd34","groupId":3,"userId":457,"__v":0,"basicInfo":{"gender":0,"professionalIdentity":1,"showEdit":1,"_id":"5b767efa7c63c218cb3ee415","userId":457,"realName":"王新亚","school":"","education":0,"company":"","position":"","country":"","number":0,"nature":"","headImg":"/img/upload/userHeadImages/1539068824599_6.jpg","selfDesc":"","createdAt":"2018-08-17T15:53:30+08:00","updatedAt":"2018-10-09T15:07:08+08:00","__v":0},"time":1541415977298},{"_id":"5be1079ca7beaeb6f78c8ed2","groupId":3,"userId":452,"__v":0,"basicInfo":{"gender":0,"professionalIdentity":14,"showEdit":1,"_id":"5b767efa7c63c218cb3ee406","userId":452,"realName":"测试001","school":"次世代","education":7,"company":"次世代","position":"次世代","country":"次世代","number":4,"nature":"次世代","selfDesc":"次世代","createdAt":"2018-08-17T15:53:30+08:00","updatedAt":"2018-10-31T14:27:24+08:00","__v":0,"userInfo":452,"headImg":"/img/upload/userHeadImages/1540967244502_3.jpg"},"time":1541474234025}]
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
         * _id : 5be02429e8884ab18aa7cd34
         * groupId : 3
         * userId : 457
         * __v : 0
         * basicInfo : {"gender":0,"professionalIdentity":1,"showEdit":1,"_id":"5b767efa7c63c218cb3ee415","userId":457,"realName":"王新亚","school":"","education":0,"company":"","position":"","country":"","number":0,"nature":"","headImg":"/img/upload/userHeadImages/1539068824599_6.jpg","selfDesc":"","createdAt":"2018-08-17T15:53:30+08:00","updatedAt":"2018-10-09T15:07:08+08:00","__v":0}
         * time : 1541415977298
         */

        private String _id;
        private int groupId;
        private int userId;
        private int __v;
        private BasicInfoBean basicInfo;
        private long time;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
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

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public static class BasicInfoBean {
            /**
             * gender : 0
             * professionalIdentity : 1
             * showEdit : 1
             * _id : 5b767efa7c63c218cb3ee415
             * userId : 457
             * realName : 王新亚
             * school :
             * education : 0
             * company :
             * position :
             * country :
             * number : 0
             * nature :
             * headImg : /img/upload/userHeadImages/1539068824599_6.jpg
             * selfDesc :
             * createdAt : 2018-08-17T15:53:30+08:00
             * updatedAt : 2018-10-09T15:07:08+08:00
             * __v : 0
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
            private String headImg;
            private String selfDesc;
            private String createdAt;
            private String updatedAt;
            private int __v;

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
        }
    }
}
