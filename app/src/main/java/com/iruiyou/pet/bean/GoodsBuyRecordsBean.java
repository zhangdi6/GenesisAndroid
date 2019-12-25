package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 商品订单信息
 */
public class GoodsBuyRecordsBean {
    /**
     * rcToken : null
     * csrfToken : null
     * data : null
     * message : 添加好友成功
     * statusCode : 0
     * error : null
     * token : null
     */

    private String rcToken;
    private String csrfToken;
    private List<DataBean> data;
    private String message;
    private int statusCode;
    private String error;
    private String token;


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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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

    public static class DataBean{
        private int progress ;//:2,
        private String _id;//5c3c0e76a43b7a349ea661fb",
        private String yzTid;//E20190114122214059000007",
        private String status;//WAIT_BUYER_CONFIRM_GOODS",
        private long time;//1547439734855,
        private String createdAt;//2019-01-14T12:22:14+08:00",
        private String updatedAt;//2019-01-14T12:22:34+08:00",
        private int  __v;//:0,
        private int baseCount;//50,
        private String createdTime;//2019-01-14T12:22:14+08:00",
        private int discount;//0,
        private String itemId;//443029617,
        private String itemName;//商品名称
        private int num;//1,
        private double price;//50,
        private int total;//50,
        private String userId;//1134,
        private String payTime;//2019-01-14T12:22:25+08:00",
        private int payment;//50
        private String phone;//下单手机号

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getYzTid() {
            return yzTid;
        }

        public void setYzTid(String yzTid) {
            this.yzTid = yzTid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
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

        public int getBaseCount() {
            return baseCount;
        }

        public void setBaseCount(int baseCount) {
            this.baseCount = baseCount;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }
    }

}
