package com.iruiyou.pet.bean;

import java.util.List;

public class DoumiOptionBean {

    private int code;
    private String msg;
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatusCode() {
        return code;
    }

    public void setStatusCode(int statusCode) {
        this.code = statusCode;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    public static class DataBean{
        List<DataValue> job_type;
        List<DataValue> domain;
        List<DataValue> job_date_type;


        public List<DataValue> getJob_type() {
            return job_type;
        }

        public void setJob_type(List<DataValue> job_type) {
            this.job_type = job_type;
        }

        public List<DataValue> getDomain() {
            return domain;
        }

        public void setDomain(List<DataValue> domain) {
            this.domain = domain;
        }

        public List<DataValue> getJob_date_type() {
            return job_date_type;
        }

        public void setJob_date_type(List<DataValue> job_date_type) {
            this.job_date_type = job_date_type;
        }
    }

    public static class DataValue{
        String value;
        String text;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

}
