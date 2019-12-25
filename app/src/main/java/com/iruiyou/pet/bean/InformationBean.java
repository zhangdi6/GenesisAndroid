package com.iruiyou.pet.bean;

import java.util.List;

public class InformationBean {


    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"userInfo":{"vcSendTime":0,"registered":true,"inviteCode":"444555","invitedCode":"我被邀请码","inviteCount":0,"pbsAmount":0,"lastHarvestAmount":0,"pbsFrozen":1,"combat":0,"headImg":"/img/upload/userHeadImages/1534504992258_8.jpg","_id":26,"countryCode":"86","phone":"17601011006","name":"uu","password":"$2a$10$pMjGGVZZEXRfiK0vm2otVOKj928nsyPDvTN8mpX0JPqxMswes2UDK","vrfCode":"用了就过期","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-17T19:23:12+08:00","__v":0},"harvestList":[{"time":1534039171575,"_id":"5b6f94874f5fb36df9eff683","userId":26,"amount":1,"income":0,"type":"PETS及云宠兑换","createdAt":"2018-08-12T09:59:35+08:00","updatedAt":"2018-08-12T09:59:35+08:00","__v":0}]}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private String error;
    private List<DataBean> data;
    private String csrfToken;
    private String token;

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

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
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

    public static class DataBean {
        List<ImageBean> images;
        private int reward;
        private int fabulous;
        private int comment;
        private int _id;
        private int userId;
        private long time;
        private long hot;
        private String htmlContent;
        private String content;
        private BasicInfoBean basicInfo;
        private String sourceURL;
        private int inviteUserId;
        private String dataSource;
        private int readCount;
        private int referenceId;
        private String referenceContent;
        private String referenceAuthorName;

        public List<ImageBean> getImages() {
            return images;
        }

        public void setImages(List<ImageBean> images) {
            this.images = images;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public int getFabulous() {
            return fabulous;
        }

        public void setFabulous(int fabulous) {
            this.fabulous = fabulous;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
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

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getHot() {
            return hot;
        }

        public void setHot(long hot) {
            this.hot = hot;
        }

        public String getHtmlContent() {
            return htmlContent;
        }

        public void setHtmlContent(String htmlContent) {
            this.htmlContent = htmlContent;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public String getSourceURL() {
            return sourceURL;
        }

        public void setSourceURL(String sourceURL) {
            this.sourceURL = sourceURL;
        }

        public int getInviteUserId() {
            return inviteUserId;
        }

        public void setInviteUserId(int inviteUserId) {
            this.inviteUserId = inviteUserId;
        }

        public String getDataSource() {
            return dataSource;
        }

        public void setDataSource(String dataSource) {
            this.dataSource = dataSource;
        }

        public int getReadCount() {
            return readCount;
        }

        public void setReadCount(int readCount) {
            this.readCount = readCount;
        }

        public int getReferenceId() {
            return referenceId;
        }

        public void setReferenceId(int referenceId) {
            this.referenceId = referenceId;
        }

        public String getReferenceContent() {
            return referenceContent;
        }

        public void setReferenceContent(String referenceContent) {
            this.referenceContent = referenceContent;
        }

        public String getReferenceAuthorName() {
            return referenceAuthorName;
        }

        public void setReferenceAuthorName(String referenceAuthorName) {
            this.referenceAuthorName = referenceAuthorName;
        }
    }

    public static class ImageBean {
        private String path;
        private int width;
        private int height;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
