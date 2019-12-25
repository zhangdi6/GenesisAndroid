package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：jiaopeirong on 2018/5/27 00:52
 * 邮箱：chinajpr@163.com
 */
public class MyAssetsRecord {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"time":1527352632933,"income":0,"type":"挖矿产量","_id":10000001,"userId":10000026,"amount":0.1,"createdAt":"2018-05-26T16:41:12.230Z","updatedAt":"2018-05-26T16:41:12.230Z","__v":0}]
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private Object message;
    private Object error;
    private Object csrfToken;
    private Object token;
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

    public Object getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(Object csrfToken) {
        this.csrfToken = csrfToken;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * time : 1527352632933
         * income : 0
         * type : 挖矿产量
         * _id : 10000001
         * userId : 10000026
         * amount : 0.1
         * createdAt : 2018-05-26T16:41:12.230Z
         * updatedAt : 2018-05-26T16:41:12.230Z
         * __v : 0
         */

        private long time;
        private int income;
        private String type;
        private int _id;
        private int userId;
        private double amount;
        private String createdAt;
        private String updatedAt;
        private int __v;

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public int getIncome() {
            return income;
        }

        public void setIncome(int income) {
            this.income = income;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
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
