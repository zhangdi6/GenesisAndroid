package com.iruiyou.pet.bean;

import java.util.List;

public class SeeMeLogBean  {
    /**
     * statusCode : 0
     * message : 添加成功
     * error : null
     * data : {"userId":10000020,"animal":2,"variety":2,"headImg":"/img/upload/petHeadImages/1527256818861_6.jpg","petNick":"yy","ownerNick":"hh","combat":0,"sex":0,"birthday":"未填写","createdAt":"2018-05-25T14:01:38.612Z","updatedAt":"2018-05-25T14:01:38.612Z","_id":10000006,"__v":0}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private Object token;

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

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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

    public static class DataBean {
        private int total;
        private int page;
        private int limit;
        private List<SeeLogInfo> logs;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public List<SeeLogInfo> getLogs() {
            return logs;
        }

        public void setLogs(List<SeeLogInfo> logs) {
            this.logs = logs;
        }
    }



    public static class SeeLogInfo{
        private BasicInfoBean fromInfo;
        private String _id;
        private String toInfo;

        public BasicInfoBean getFromInfo() {
            return fromInfo;
        }

        public void setFromInfo(BasicInfoBean fromInfo) {
            this.fromInfo = fromInfo;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getToInfo() {
            return toInfo;
        }

        public void setToInfo(String toInfo) {
            this.toInfo = toInfo;
        }
    }
}
