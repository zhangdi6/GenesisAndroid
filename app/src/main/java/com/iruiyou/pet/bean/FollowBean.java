package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 我的关注
 * 作者：sgf on 2018/10/26 23:43 11
 */
public class FollowBean {


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"time":1539757078096,"_id":"5bc6e20d0486351e061becf7","userIdA":67,"userIdB":43,"userInfoA":67,"userInfoB":43,"basicInfoA":"5bc00d61c47e7c734ecc365f","basicInfoB":{"gender":0,"professionalIdentity":999,"showEdit":1,"_id":"5ba48955677db955ec6394cb","userId":43,"userInfo":43,"createdAt":"2018-09-21T14:01:57+08:00","updatedAt":"2018-09-27T16:10:01+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1538035801797_0.jpg","realName":"邀请码如图"},"createdAt":"2018-10-17T15:17:33+08:00","updatedAt":"2018-10-17T15:17:33+08:00","__v":0},{"time":1539757078096,"_id":"5bc6e3b20486351e061becf8","userIdA":67,"userIdB":62,"userInfoA":67,"userInfoB":62,"basicInfoA":"5bc00d61c47e7c734ecc365f","basicInfoB":{"gender":0,"professionalIdentity":1,"showEdit":1,"_id":"5baf30731e37115db0b8de88","userId":62,"userInfo":62,"createdAt":"2018-09-29T15:57:39+08:00","updatedAt":"2018-09-29T17:20:10+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1538212810958_5.jpg","realName":"121231"},"createdAt":"2018-10-17T15:24:34+08:00","updatedAt":"2018-10-17T15:24:34+08:00","__v":0}]
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
         * time : 1539757078096
         * _id : 5bc6e20d0486351e061becf7
         * userIdA : 67
         * userIdB : 43
         * userInfoA : 67
         * userInfoB : 43
         * basicInfoA : 5bc00d61c47e7c734ecc365f
         * basicInfoB : {"gender":0,"professionalIdentity":999,"showEdit":1,"_id":"5ba48955677db955ec6394cb","userId":43,"userInfo":43,"createdAt":"2018-09-21T14:01:57+08:00","updatedAt":"2018-09-27T16:10:01+08:00","__v":0,"headImg":"/img/upload/userHeadImages/1538035801797_0.jpg","realName":"邀请码如图"}
         * createdAt : 2018-10-17T15:17:33+08:00
         * updatedAt : 2018-10-17T15:17:33+08:00
         * __v : 0
         */

        private long time;
        private String _id;
        private int userIdA;
        private int userIdB;
        private int userInfoA;
        private int userInfoB;
        private String basicInfoA;
        private BasicInfoBBean basicInfoB;
        private String createdAt;
        private String updatedAt;
        private int __v;

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

        public String getBasicInfoA() {
            return basicInfoA;
        }

        public void setBasicInfoA(String basicInfoA) {
            this.basicInfoA = basicInfoA;
        }

        public BasicInfoBBean getBasicInfoB() {
            return basicInfoB;
        }

        public void setBasicInfoB(BasicInfoBBean basicInfoB) {
            this.basicInfoB = basicInfoB;
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

        public static class BasicInfoBBean {
            /**
             * gender : 0
             * professionalIdentity : 999
             * showEdit : 1
             * _id : 5ba48955677db955ec6394cb
             * userId : 43
             * userInfo : 43
             * createdAt : 2018-09-21T14:01:57+08:00
             * updatedAt : 2018-09-27T16:10:01+08:00
             * __v : 0
             * headImg : /img/upload/userHeadImages/1538035801797_0.jpg
             * realName : 邀请码如图
             */

            private int gender;
            private int professionalIdentity;
            private int showEdit;
            private String _id;
            private int userId;
            private int userInfo;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private String headImg;
            private String realName;

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

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }
        }
    }
}
