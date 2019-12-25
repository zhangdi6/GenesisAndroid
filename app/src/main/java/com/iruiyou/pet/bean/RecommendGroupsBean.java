package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：获取群组推荐
 * 作者：shenggaofei on 2018/10/16 13:44
 */
public class RecommendGroupsBean implements Serializable{


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"memberCount":16,"show":true,"index":1,"groupIndex":1,"_id":4,"nick":"绿巨人","name":"北京绿巨人科技有限公司","logo":"/img/upload/companyImages/1541155670958_2.png","desc":"公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介","groupName":"绿巨人社群","groupDesc":"针对绿巨人的交流社群","onwerId":28,"createdAt":"2018-11-02T19:51:23+08:00","updatedAt":"2018-11-08T18:41:06+08:00","__v":0},{"memberCount":3,"show":true,"index":1,"groupIndex":1,"_id":5,"nick":"第一神鸭","name":"北京第一神鸭科技有限公司","logo":"/img/upload/companyImages/1541672656141_3.png","desc":"；阿斯利康戴假发设立的房间啊；算力大V啊；老师的风景阿萨德按地方； 发送；定律下的","groupName":"神鸭一群","groupDesc":"哦IIP配偶房间爱搜ID房间爱搜I宫颈癌是;va'spodg 奥斯迪vjap'rdogsd","onwerId":43,"createdAt":"2018-11-08T18:25:44+08:00","updatedAt":"2018-11-09T09:38:36+08:00","__v":0},{"memberCount":3,"show":true,"index":1,"groupIndex":1,"_id":6,"nick":"第二神鸭","name":"北京第二神鸭科技有限公司","logo":"/img/upload/companyImages/1541672656141_3.png","desc":"；阿斯利康戴假发设立的房间啊；算力大V啊；老师的风景阿萨德按地方； 发送；定律下的","groupName":"神鸭二群","groupDesc":"哦IIP配偶房间爱搜ID房间爱搜I宫颈癌是;va'spodg 奥斯迪vjap'rdogsd","onwerId":1,"createdAt":"2018-11-08T18:26:40+08:00","updatedAt":"2018-11-09T09:40:36+08:00","__v":0},{"memberCount":2,"show":true,"index":1,"groupIndex":1,"_id":7,"nick":"第三神鸭","name":"北京第三神鸭科技有限公司","logo":"/img/upload/companyImages/1541672656141_3.png","desc":"；阿斯利康戴假发设立的房间啊；算力大V啊；老师的风景阿萨德按地方； 发送；定律下的","groupName":"神鸭三群","groupDesc":"哦IIP配偶房间爱搜ID房间爱搜I宫颈癌是;va'spodg 奥斯迪vjap'rdogsd","onwerId":1,"createdAt":"2018-11-08T18:30:35+08:00","updatedAt":"2018-11-08T18:30:55+08:00","__v":0},{"memberCount":3,"show":true,"index":1,"groupIndex":1,"_id":8,"nick":"第四神鸭","name":"北京第四神鸭科技有限公司","logo":"/img/upload/companyImages/1541672656141_3.png","desc":"；阿斯利康戴假发设立的房间啊；算力大V啊；老师的风景阿萨德按地方； 发送；定律下的","groupName":"神鸭四群","groupDesc":"哦IIP配偶房间爱搜ID房间爱搜I宫颈癌是;va'spodg 奥斯迪vjap'rdogsd","onwerId":1,"createdAt":"2018-11-08T18:30:50+08:00","updatedAt":"2018-11-09T10:11:28+08:00","__v":0}]
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

    public static class DataBean implements Serializable{
        /**
         * memberCount : 16
         * show : true
         * index : 1
         * groupIndex : 1
         * _id : 4
         * nick : 绿巨人
         * name : 北京绿巨人科技有限公司
         * logo : /img/upload/companyImages/1541155670958_2.png
         * desc : 公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介公司简介
         * groupName : 绿巨人社群
         * groupDesc : 针对绿巨人的交流社群
         * onwerId : 28
         * createdAt : 2018-11-02T19:51:23+08:00
         * updatedAt : 2018-11-08T18:41:06+08:00
         * __v : 0
         */

        private int memberCount;
        private boolean show;
        private int index;
        private int groupIndex;
        private int _id;
        private String nick;
        private String name;
        private String logo;
        private String desc;
        private String groupName;
        private String groupDesc;
        private int onwerId;
        private String createdAt;
        private String updatedAt;
        private int __v;

        public int getMemberCount() {
            return memberCount;
        }

        public void setMemberCount(int memberCount) {
            this.memberCount = memberCount;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getGroupIndex() {
            return groupIndex;
        }

        public void setGroupIndex(int groupIndex) {
            this.groupIndex = groupIndex;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getGroupDesc() {
            return groupDesc;
        }

        public void setGroupDesc(String groupDesc) {
            this.groupDesc = groupDesc;
        }

        public int getOnwerId() {
            return onwerId;
        }

        public void setOnwerId(int onwerId) {
            this.onwerId = onwerId;
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
