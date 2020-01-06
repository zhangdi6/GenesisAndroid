package com.iruiyou.pet.bean;

import java.util.List;

public class ShoppingBean {

   /* {
        "statusCode": 0,
            "message": null,
            "error": null,
            "data": {
        "banner": [
        {
            "url": null,
                "_id": "5dd79064d44a7a8297ccb77c",
                "description": "https://maichang-images.oss-cn-beijing.aliyuncs.com/market/24381574331262_.pic.png",
                "isbanner": true,
                "createdAt": "2019-11-22T15:38:12+08:00",
                "updatedAt": "2019-11-22T15:38:12+08:00",
                "__v": 0
        }
        ],
        "other": [
        {
            "url": null,
                "_id": "5dd78fcc4ba96081f6ed35ff",
                "description": "https://maichang-images.oss-cn-beijing.aliyuncs.com/market/24421574334433_.pic.png",
                "isbanner": false,
                "createdAt": "2019-11-22T15:35:40+08:00",
                "updatedAt": "2019-11-22T15:35:40+08:00",
                "__v": 0
        }
        ]
    },
        "csrfToken": null,
            "token": null,
            "rcToken": null
    }*/
   private int statusCode;
   private String message;
   private String error;
   private DataBean data;

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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    private String csrfToken;
   private String token;
   private String rcToken;


    public static class DataBean  {
        private List<BannerHead> banner;

        public List<BannerHead> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerHead> banner) {
            this.banner = banner;
        }

        public List<OtherHead> getOther() {
            return other;
        }

        public void setOther(List<OtherHead> other) {
            this.other = other;
        }

        private List<OtherHead> other;

    }
    public static class BannerHead {
        private String url;
        private String _id;
        private String description;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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

        public boolean isIsbanner() {
            return isbanner;
        }

        public void setIsbanner(boolean isbanner) {
            this.isbanner = isbanner;
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

        private boolean isbanner;
        private String createdAt;
        private String updatedAt;

    }

    public static class OtherHead {
        private String url;
        private String _id;
        private String description;
        private boolean isbanner;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

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

        public boolean isIsbanner() {
            return isbanner;
        }

        public void setIsbanner(boolean isbanner) {
            this.isbanner = isbanner;
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

        private String createdAt;
        private String updatedAt;

    }



}
