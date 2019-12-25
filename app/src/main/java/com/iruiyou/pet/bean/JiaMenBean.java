package com.iruiyou.pet.bean;

import java.util.List;

public class JiaMenBean {

   /* "statusCode": 0,
            "message": null,
            "error": null,
            "data": [
    {
        "_id": "5dbbfe39eaf60d33b8efecf9",
            "description": "/img/genesis/bg_joinspace.png",
            "url": "https://mp.weixin.qq.com/s/Jl3lFOSyJf_l3Evbjxiqqg",
            "createdAt": "2019-11-01T17:43:21+08:00",
            "updatedAt": "2019-11-01T17:43:21+08:00",
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

        private String _id;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String get__v() {
            return __v;
        }

        public void set__v(String __v) {
            this.__v = __v;
        }

        private String description;
        private String url;
        private String createdAt;
        private String __v;
    }
}
