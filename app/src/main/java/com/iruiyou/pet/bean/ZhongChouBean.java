package com.iruiyou.pet.bean;

import java.util.List;

public class ZhongChouBean {

    /*{
        "statusCode": 0,
            "message": null,
            "error": null,
            "data": [
        {
            "description": "/img/genesis/bg_crowdfunding.png",
                "currentNum": 10,
                "overallNum": 40,
                "currentMoney": 10,
                "overallMoney": 200,
                "type": 0,
                "show": true,
                "_id": "5daeba86cdd0f71464fcfca4",
                "title": "三亚店",
                "url": "https://mp.weixin.qq.com/s/usQFtRBxn4sfmKXFmcTNiA",
                "createdAt": "2019-10-22T16:15:02+08:00",
                "updatedAt": "2019-10-22T16:15:02+08:00",
                "__v": 0
        }
    ],
        "csrfToken": null,
            "token": null,
            "rcToken": null
    }*/
    private int statusCode;
    private String message;
    private String error;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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

    private List<DataBean> data;
    private String csrfToken;
    private String token;
    private String rcToken;




    public static class DataBean {
        private String description;
        private int currentNum;
        private int overallNum;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getCurrentNum() {
            return currentNum;
        }

        public void setCurrentNum(int currentNum) {
            this.currentNum = currentNum;
        }

        public int getOverallNum() {
            return overallNum;
        }

        public void setOverallNum(int overallNum) {
            this.overallNum = overallNum;
        }

        public int getCurrentMoney() {
            return currentMoney;
        }

        public void setCurrentMoney(int currentMoney) {
            this.currentMoney = currentMoney;
        }

        public int getOverallMoney() {
            return overallMoney;
        }

        public void setOverallMoney(int overallMoney) {
            this.overallMoney = overallMoney;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        private int currentMoney;
        private int overallMoney;
        private int type;
        private boolean show;
        private String _id;
        private String title;
        private String url;
        private String createdAt;
        private String updatedAt;
        private int __v;

    }

}
