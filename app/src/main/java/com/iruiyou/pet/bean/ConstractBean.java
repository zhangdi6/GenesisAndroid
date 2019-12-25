package com.iruiyou.pet.bean;

import java.util.List;

public class ConstractBean {
    /*{"statusCode":0,"message":null,"error":null,"data":[{"_id":"5df090f3d74d565b3f1f57ee","userId":5912,
            "userName":"王宝欢","ID":"130104197103081539","type":"股权合伙人","typeCode":2,"shop":"脉场石家庄店",
            "shopCode":"shijiazhuang001","city":"石家庄","amount":2,"price":50000,"createdAt":"2019-12-11T14:47:15+08:00",
            "updatedAt":"2019-12-11T14:47:15+08:00",
            "__v":0,"pdf":"http://47.111.100.29:4009/assets/contract2_21247897328908890.pdf"}],
        "csrfToken":null,"token":null,"rcToken":null}*/
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

    public List<ConData> getData() {
        return data;
    }

    public void setData(List<ConData> data) {
        this.data = data;
    }

    private String csrfToken;
    private String token;
    private String rcToken;
    private List<ConData> data;

 /*   {"_id":"5df090f3d74d565b3f1f57ee","userId":5912,
            "userName":"王宝欢","ID":"130104197103081539","type":"股权合伙人","typeCode":2,"shop":"脉场石家庄店",
            "shopCode":"shijiazhuang001","city":"石家庄","amount":2,"price":50000,"createdAt":"2019-12-11T14:47:15+08:00",
            "updatedAt":"2019-12-11T14:47:15+08:00",
            "__v":0,"pdf":"http://47.111.100.29:4009/assets/contract2_21247897328908890.pdf"}*/
    public static class ConData{
        private String _id;
        private int userId;
        private String userName;
        private String ID;
        private String type;
        private int typeCode;
        private String shop;

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

     public String getUserName() {
         return userName;
     }

     public void setUserName(String userName) {
         this.userName = userName;
     }

     public String getID() {
         return ID;
     }

     public void setID(String ID) {
         this.ID = ID;
     }

     public String getType() {
         return type;
     }

     public void setType(String type) {
         this.type = type;
     }

     public int getTypeCode() {
         return typeCode;
     }

     public void setTypeCode(int typeCode) {
         this.typeCode = typeCode;
     }

     public String getShop() {
         return shop;
     }

     public void setShop(String shop) {
         this.shop = shop;
     }

     public String getShopCode() {
         return shopCode;
     }

     public void setShopCode(String shopCode) {
         this.shopCode = shopCode;
     }

     public String getCity() {
         return city;
     }

     public void setCity(String city) {
         this.city = city;
     }

     public int getAmount() {
         return amount;
     }

     public void setAmount(int amount) {
         this.amount = amount;
     }

     public int getPrice() {
         return price;
     }

     public void setPrice(int price) {
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

     public String getPdf() {
         return pdf;
     }

     public void setPdf(String pdf) {
         this.pdf = pdf;
     }

     private String shopCode;
        private String city;
        private int amount;
        private int price;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String pdf;
    }

}
