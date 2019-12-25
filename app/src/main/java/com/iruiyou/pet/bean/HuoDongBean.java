package com.iruiyou.pet.bean;
import java.util.List;

public class HuoDongBean {

    /*{
        "statusCode": 0,
            "message": null,
            "error": null,
            "data": [
        {
            "peopleNum": 999,
                "_id": "5da83dca0c552a4088d905b8",
                "title": "测试活动1",
                "description": "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1571314387653&di=87e00fb65a11c30d75225d38027f9ab4&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201601%2F08%2F20160108211356_hFxUT.jpeg",
                "createdAt": "2019-10-17T18:09:14+08:00",
                "updatedAt": "2019-10-17T18:09:14+08:00",
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
    private List<DataBean> data;
    private String csrfToken;
    private String token;
    private String rcToken;


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



    public static class DataBean {

        private int peopleNum;
        private String _id;
        private String title;
        private String description;
        private String updatedAt;
        private int __v;

        public DataBean(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String url;



        public int getPeopleNum() {
            return peopleNum;
        }

        public void setPeopleNum(int peopleNum) {
            this.peopleNum = peopleNum;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
