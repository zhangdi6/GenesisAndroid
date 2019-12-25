package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 好友申请
 * 作者：sgf on 2018/10/16 23:43 11
 */
public class GetAppliersBean {

    /**
     * rcToken : null
     * csrfToken : null
     * data : [{"basicInfoB":{"position":"3323","updatedAt":"2018-10-18T11:33:41+08:00","school":"323","headImg":"/img/upload/userHeadImages/1539691122610_8.jpg","_id":"5bc00d61c47e7c734ecc365f","professionalIdentity":-1,"createdAt":"2018-10-12T10:56:33+08:00","company":"3q3","userId":67,"__v":0,"gender":0,"realName":"Sheng","showEdit":0},"basicInfoA":{"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61},"__v":0,"userInfoB":67,"userInfoA":61,"statusB2A":1,"userIdA":61,"statusA2B":0,"userIdB":67,"updatedAt":"2018-10-18T10:32:14+08:00","timeB2A":1539829934334,"_id":"5bc7f0ae8e3ec94209d1362c","createdAt":"2018-10-18T10:32:14+08:00","timeA2B":0}]
     * message : null
     * statusCode : 0
     * error : null
     * token : null
     */

    private String rcToken;
    private String csrfToken;
    private String message;
    private int statusCode;
    private String error;
    private String token;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * basicInfoB : {"position":"3323","updatedAt":"2018-10-18T11:33:41+08:00","school":"323","headImg":"/img/upload/userHeadImages/1539691122610_8.jpg","_id":"5bc00d61c47e7c734ecc365f","professionalIdentity":-1,"createdAt":"2018-10-12T10:56:33+08:00","company":"3q3","userId":67,"__v":0,"gender":0,"realName":"Sheng","showEdit":0}
         * basicInfoA : {"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61}
         * __v : 0
         * userInfoB : 67
         * userInfoA : 61
         * statusB2A : 1
         * userIdA : 61
         * statusA2B : 0
         * userIdB : 67
         * updatedAt : 2018-10-18T10:32:14+08:00
         * timeB2A : 1539829934334
         * _id : 5bc7f0ae8e3ec94209d1362c
         * createdAt : 2018-10-18T10:32:14+08:00
         * timeA2B : 0
         */

        private BasicInfoBBean basicInfoB;
        private BasicInfoABean basicInfoA;
        private int __v;
        private int userInfoB;
        private int userInfoA;
        private int statusB2A;
        private int userIdA;
        private int statusA2B;
        private int userIdB;
        private String updatedAt;
        private long timeB2A;
        private String _id;
        private String createdAt;
        private String timeA2B;

        public BasicInfoBBean getBasicInfoB() {
            return basicInfoB;
        }

        public void setBasicInfoB(BasicInfoBBean basicInfoB) {
            this.basicInfoB = basicInfoB;
        }

        public BasicInfoABean getBasicInfoA() {
            return basicInfoA;
        }

        public void setBasicInfoA(BasicInfoABean basicInfoA) {
            this.basicInfoA = basicInfoA;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public int getUserInfoB() {
            return userInfoB;
        }

        public void setUserInfoB(int userInfoB) {
            this.userInfoB = userInfoB;
        }

        public int getUserInfoA() {
            return userInfoA;
        }

        public void setUserInfoA(int userInfoA) {
            this.userInfoA = userInfoA;
        }

        public int getStatusB2A() {
            return statusB2A;
        }

        public void setStatusB2A(int statusB2A) {
            this.statusB2A = statusB2A;
        }

        public int getUserIdA() {
            return userIdA;
        }

        public void setUserIdA(int userIdA) {
            this.userIdA = userIdA;
        }

        public int getStatusA2B() {
            return statusA2B;
        }

        public void setStatusA2B(int statusA2B) {
            this.statusA2B = statusA2B;
        }

        public int getUserIdB() {
            return userIdB;
        }

        public void setUserIdB(int userIdB) {
            this.userIdB = userIdB;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public long getTimeB2A() {
            return timeB2A;
        }

        public void setTimeB2A(long timeB2A) {
            this.timeB2A = timeB2A;
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

        public String getTimeA2B() {
            return timeA2B;
        }

        public void setTimeA2B(String timeA2B) {
            this.timeA2B = timeA2B;
        }

        public static class BasicInfoBBean {
            /**
             * position : 3323
             * updatedAt : 2018-10-18T11:33:41+08:00
             * school : 323
             * headImg : /img/upload/userHeadImages/1539691122610_8.jpg
             * _id : 5bc00d61c47e7c734ecc365f
             * professionalIdentity : -1
             * createdAt : 2018-10-12T10:56:33+08:00
             * company : 3q3
             * userId : 67
             * __v : 0
             * gender : 0
             * realName : Sheng
             * showEdit : 0
             */

            private String position;
            private String updatedAt;
            private String school;
            private String headImg;
            private String _id;
            private int professionalIdentity;
            private String createdAt;
            private String company;
            private int userId;
            private int __v;
            private int gender;
            private String realName;
            private int showEdit;

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
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
        }

        public static class BasicInfoABean {
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
    }
}
