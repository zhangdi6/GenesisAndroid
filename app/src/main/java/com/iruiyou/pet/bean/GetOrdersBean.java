package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：sgf
 * 获取水晶充值订单
 */
public class GetOrdersBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"progress":2,"_id":"5be39fc5caa65853139d8717","yzTid":"E20181108103029046800004","status":"WAIT_BUYER_CONFIRM_GOODS","createdAt":"2018-11-08T10:30:29+08:00","updatedAt":"2018-11-08T10:31:13+08:00","__v":0,"createdTime":"2018-11-08T10:30:29+08:00","discount":0,"num":1,"price":0.01,"total":0.01,"userId":86,"payTime":"2018-11-08T10:31:12+08:00","payment":0.01}]
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
         * progress : 2
         * _id : 5be39fc5caa65853139d8717
         * yzTid : E20181108103029046800004
         * status : WAIT_BUYER_CONFIRM_GOODS
         * createdAt : 2018-11-08T10:30:29+08:00
         * updatedAt : 2018-11-08T10:31:13+08:00
         * __v : 0
         * createdTime : 2018-11-08T10:30:29+08:00
         * discount : 0
         * num : 1
         * price : 0.01
         * total : 0.01
         * userId : 86
         * payTime : 2018-11-08T10:31:12+08:00
         * payment : 0.01
         */

        private int progress;
        private String _id;
        private String yzTid;
        private String status;
        private String createdAt;
        private String updatedAt;
        private int __v;
        private String createdTime;
        private int discount;
        private int num;
        private double price;
        private double total;
        private int userId;
        private String payTime;
        private double payment;

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

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPayTime() {
            return payTime;
        }

        public void setPayTime(String payTime) {
            this.payTime = payTime;
        }

        public double getPayment() {
            return payment;
        }

        public void setPayment(double payment) {
            this.payment = payment;
        }
    }
}
