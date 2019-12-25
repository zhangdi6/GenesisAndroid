package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 我的粉丝
 * 作者：sgf on 2018/10/26 23:43 11
 */
public class FansBean {


    /**
     * rcToken : null
     * csrfToken : null
     * data : [{"updatedAt":"2018-10-18T10:32:13+08:00","time":1539772327991,"_id":"5bc7f0ad8e3ec94209d1362b","basicInfoB":"5bc00d61c47e7c734ecc365f","createdAt":"2018-10-18T10:32:13+08:00","basicInfoA":{"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61},"__v":0,"userInfoB":67,"userInfoA":61,"userIdA":61,"userIdB":67}]
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
         * updatedAt : 2018-10-18T10:32:13+08:00
         * time : 1539772327991
         * _id : 5bc7f0ad8e3ec94209d1362b
         * basicInfoB : 5bc00d61c47e7c734ecc365f
         * createdAt : 2018-10-18T10:32:13+08:00
         * basicInfoA : {"updatedAt":"2018-10-18T10:31:37+08:00","headImg":"img/upload/userHeadImages/null","_id":"5bc7f0848e3ec94209d13629","professionalIdentity":1,"createdAt":"2018-10-18T10:31:32+08:00","userId":61,"__v":0,"gender":0,"realName":"3232323","showEdit":1,"userInfo":61}
         * __v : 0
         * userInfoB : 67
         * userInfoA : 61
         * userIdA : 61
         * userIdB : 67
         */

        private String updatedAt;
        private long time;
        private String _id;
        private String basicInfoB;
        private String createdAt;
        private BasicInfoABean basicInfoA;
        private int __v;
        private int userInfoB;
        private int userInfoA;
        private int userIdA;
        private int userIdB;

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBasicInfoB() {
            return basicInfoB;
        }

        public void setBasicInfoB(String basicInfoB) {
            this.basicInfoB = basicInfoB;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
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
