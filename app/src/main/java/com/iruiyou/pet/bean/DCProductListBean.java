package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 获取定存产品
 */
public class DCProductListBean {

    private int statusCode;
    private String message;
    private String error;
    private List<DCProductBean> data;

    public static class DCProductBean {

        private String type;
        private String target;
        private String currency;
        private String dayRate;
        private String publishTime;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public String getDayRate() {
            return dayRate;
        }

        public void setDayRate(String dayRate) {
            this.dayRate = dayRate;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }

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

    public List<DCProductBean> getData() {
        return data;
    }

    public void setData(List<DCProductBean> data) {
        this.data = data;
    }
}


