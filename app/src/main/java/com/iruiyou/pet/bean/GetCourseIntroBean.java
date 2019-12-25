package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：获取课程列表
 * 作者：sgf on 2018/10/30 09:59
 */
public class GetCourseIntroBean implements Serializable {


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"show":true,"deleted":false,"images":[{"path":"/img/upload/courseIntroImages/1542275872449_44745075.jpeg","size":{"width":900,"height":600}},{"path":"/img/upload/courseIntroImages/1542275872449_7861181.jpeg","size":{"width":540,"height":4205}},{"path":"/img/upload/courseIntroImages/1542275872449_77080162.jpeg","size":{"width":1276,"height":648}},{"path":"/img/upload/courseIntroImages/1542275872450_91922786.jpeg","size":{"width":648,"height":1339}}],"index":1,"_id":3,"time":1542275872450,"title":"36天成为「理财高手」","desc":"真正的会赚钱是\u201c用钱省钱\u201d","category":3,"saleTime":1544867872450,"price":149,"createdAt":"2018-11-15T17:57:52+08:00","updatedAt":"2018-11-15T17:57:52+08:00","__v":0},{"show":true,"deleted":false,"images":[{"path":"/img/upload/courseIntroImages/1542275864970_17607398.jpg","size":{"width":598,"height":399}},{"path":"/img/upload/courseIntroImages/1542275864970_22836632.jpeg","size":{"width":540,"height":839}},{"path":"/img/upload/courseIntroImages/1542275864970_35902003.jpeg","size":{"width":540,"height":681}},{"path":"/img/upload/courseIntroImages/1542275864970_49912815.jpg","size":{"width":317,"height":960}},{"path":"/img/upload/courseIntroImages/1542275864970_39196440.jpeg","size":{"width":540,"height":791}},{"path":"/img/upload/courseIntroImages/1542275864970_11755844.jpeg","size":{"width":460,"height":960}},{"path":"/img/upload/courseIntroImages/1542275864970_57367168.jpeg","size":{"width":493,"height":960}}],"index":1,"_id":2,"time":1542275864970,"title":"互联网创业实战课","desc":"众位创投大佬亲授创业心法","category":1,"saleTime":1544867864970,"price":199,"createdAt":"2018-11-15T17:57:44+08:00","updatedAt":"2018-11-15T17:57:44+08:00","__v":0},{"show":true,"deleted":false,"images":[{"path":"/img/upload/courseIntroImages/1542275728949_76281051.jpeg","size":{"width":900,"height":600}},{"path":"/img/upload/courseIntroImages/1542275728949_4309086.jpg","size":{"width":434,"height":1754}},{"path":"/img/upload/courseIntroImages/1542275728949_88482479.jpg","size":{"width":434,"height":1619}},{"path":"/img/upload/courseIntroImages/1542275728949_32483321.jpg","size":{"width":434,"height":949}}],"index":1,"_id":1,"time":1542275728949,"title":"「从0到1」全面学透区块链","desc":"错失了比特币，如何找到投资1万回报30万的区块链项目？","category":1,"saleTime":1544867728949,"price":299,"teacherBasic":{"gender":0,"professionalIdentity":0,"showEdit":0,"_id":"5b6f94874f5fb36df9eff681","userId":26,"realName":"张三","school":"清华","education":5,"company":"金恒","position":"开发","country":"北京","number":5,"nature":"技术","headImg":"/img/upload/userHeadImages/1535704202874_7.jpg","selfDesc":"测试","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-10-17T18:42:56+08:00","__v":0,"userInfo":26},"createdAt":"2018-11-15T17:55:28+08:00","updatedAt":"2018-11-15T17:55:28+08:00","__v":0}]
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private Object message;
    private Object error;
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

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
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

    public static class DataBean implements Serializable{
        /**
         * show : true
         * deleted : false
         * images : [{"path":"/img/upload/courseIntroImages/1542275872449_44745075.jpeg","size":{"width
         * ":900,"height":600}},{"path":"/img/upload/courseIntroImages/1542275872449_7861181.jpeg","size
         * ":{"width":540,"height":4205}},{"path":"/img/upload/courseIntroImages/1542275872449_77080162.jpeg
         *
         * ","size":{"width":1276,"height":648}},{"path":"/img/upload/courseIntroImages/1542275872450_91922786.j
         * peg","size":{"width":648,"height":1339}}]
         * index : 1
         * _id : 3
         * time : 1542275872450
         * title : 36天成为「理财高手」
         * desc : 真正的会赚钱是“用钱省钱”
         * category : 3
         * saleTime : 1544867872450
         * price : 149
         * createdAt : 2018-11-15T17:57:52+08:00
         * updatedAt : 2018-11-15T17:57:52+08:00
         * __v : 0
         * teacherBasic : {"gender":0,"professionalIdentity":0,"showEdit":0,"_id":
         * "5b6f94874f5fb36df9eff681","userId":26,"realName":"张三","school":"清华","e
         * ducation":5,"company":"金恒","position":"开发","country":"北京","number":5,"natu
         * re":"技术","headImg":"/img/upload/userHeadImages/1535704202874_7.jpg","selfDesc":"
         * 测试","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-10-17T18:42:56+08:00","__v":0,"u
         *
         * serInfo":26}
         */

        private boolean show;
        private boolean deleted;
        private int index;
        private int _id;
        private long time;
        private String title;
        private String desc;
        private int category;
        private long saleTime;
        private double price;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private TeacherBasicBean teacherBasic;
        private List<ImagesBean> images;

        public String getMinImage() {
            return minImage;
        }

        public void setMinImage(String minImage) {
            this.minImage = minImage;
        }

        private String minImage;
        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public boolean isDeleted() {
            return deleted;
        }

        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public long getSaleTime() {
            return saleTime;
        }

        public void setSaleTime(long saleTime) {
            this.saleTime = saleTime;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
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

        public TeacherBasicBean getTeacherBasic() {
            return teacherBasic;
        }

        public void setTeacherBasic(TeacherBasicBean teacherBasic) {
            this.teacherBasic = teacherBasic;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class TeacherBasicBean implements Serializable{
            /**
             * gender : 0
             * professionalIdentity : 0
             * showEdit : 0
             * _id : 5b6f94874f5fb36df9eff681
             * userId : 26
             * realName : 张三
             * school : 清华
             * education : 5
             * company : 金恒
             * position : 开发
             * country : 北京
             * number : 5
             * nature : 技术
             * headImg : /img/upload/userHeadImages/1535704202874_7.jpg
             * selfDesc : 测试
             * createdAt : 2018-08-12T09:59:35+08:00
             * updatedAt : 2018-10-17T18:42:56+08:00
             * __v : 0
             * userInfo : 26
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
            private int userInfo;

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

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
            }
        }

        public static class ImagesBean implements Serializable{
            /**
             * path : /img/upload/courseIntroImages/1542275872449_44745075.jpeg
             * size : {"width":900,"height":600}
             */

            private String path;
            private SizeBean size;

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public SizeBean getSize() {
                return size;
            }

            public void setSize(SizeBean size) {
                this.size = size;
            }

            public static class SizeBean implements Serializable{
                /**
                 * width : 900
                 * height : 600
                 */

                private int width;
                private int height;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
