package com.iruiyou.pet.bean;

import java.util.List;

public class EssayDetailBean {

    /**
     * statusCode : -1
     * message : 最小提币数量2000PBS
     * error : null
     * data : null
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
    private String token;
    private Object rcToken;

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

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(Object rcToken) {
        this.rcToken = rcToken;
    }


    public static class DataBean{

        private EssayInfo es;
        private List<EssayCommonBean> comments;
        private int total;
        private int totalFabulous;
        private int isFabulous;

        public EssayInfo getEs() {
            return es;
        }

        public void setEs(EssayInfo es) {
            this.es = es;
        }

        public List<EssayCommonBean> getComments() {
            return comments;
        }

        public void setComments(List<EssayCommonBean> comments) {
            this.comments = comments;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getTotalFabulous() {
            return totalFabulous;
        }

        public void setTotalFabulous(int totalFabulous) {
            this.totalFabulous = totalFabulous;
        }

        public int getIsFabulous() {
            return isFabulous;
        }

        public void setIsFabulous(int isFabulous) {
            this.isFabulous = isFabulous;
        }
    }

    public static class EssayInfo{
        private int userId;// { type: Number, required: true }, // 发布者Id
        private BasicInfoBean basicInfo;// { type: Schema.Types.ObjectId, ref: 'BasicInfo' }, // 关联基本资料表
        private long time;// { type: Number, required: true }, // 发布时间
        private String content;// { type: String }, // 文本
//        private List<GetEssaysBean.DataBean.ImagesBean> images;

//        private String images;// { type: Array, required: true }, // 图片
        private int reward;// { type: Number, required: true, default: 0 }, // 对短文的打赏数
        private int fabulous;// { type: Number, required: true, default: 0 }, // 对短文的点赞数
        private int comment;// { type: Number, required: true, default: 0 }, // 对短文的评论数
        private boolean show;// { type: Boolean, required: true, default: true }, // 后台配置是否显示
        private boolean delete;// { type: Boolean, required: true, default: false }, // 用户自己删除
        private long hot;// { type: Number, required: true }, // 短文热度
        private String htmlContent;// { type: String }, // 富文本内容
        private String sourceURL;// { type:String }, // 原文URL
        private int inviteUserId;// { type: Number }, // 推荐人ID
        private String dataSource;// { type: String }, // 微博来源（哪个平台）
        private int readCount;// { type: Number }, // 阅读数量
        private int referenceId;// { type: Number }, // 关联内容ID
        private String referenceAuthorName;// { type: String }, // 关联内容作者名称
        private String referenceContent;// { type: String }, // 关联的内容
        private boolean isBreakingNews;// {type: Boolean, default: false },

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public BasicInfoBean getBasicInfo() {
            return basicInfo;
        }

        public void setBasicInfo(BasicInfoBean basicInfo) {
            this.basicInfo = basicInfo;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

//        public String getImages() {
//            return images;
//        }
//
//        public void setImages(String images) {
//            this.images = images;
//        }

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

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public boolean isDelete() {
            return delete;
        }

        public void setDelete(boolean delete) {
            this.delete = delete;
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

        public String getReferenceAuthorName() {
            return referenceAuthorName;
        }

        public void setReferenceAuthorName(String referenceAuthorName) {
            this.referenceAuthorName = referenceAuthorName;
        }

        public String getReferenceContent() {
            return referenceContent;
        }

        public void setReferenceContent(String referenceContent) {
            this.referenceContent = referenceContent;
        }

        public boolean isBreakingNews() {
            return isBreakingNews;
        }

        public void setBreakingNews(boolean breakingNews) {
            isBreakingNews = breakingNews;
        }
    }

}
