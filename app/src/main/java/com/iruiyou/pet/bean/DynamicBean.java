package com.iruiyou.pet.bean;


import java.util.List;

public class DynamicBean {
    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"essays":[{"_id":10646,"images":[],"reward":0,"fabulous":0,"comment":0,"show":true,"deleted":true,"isBreakingNews":false,"userId":3141,"basicInfo":{"_id":"5ce161ba011ee76012cbe968","userId":3141,"gender":0,"professionalIdentity":1100,"showEdit":1,"seePrice":0,"friendPrice":0,"createdAt":"2019-05-19T22:01:30+08:00","updatedAt":"2019-10-15T11:16:56+08:00","__v":0,"realName":"王壮益","headImg":"/img/upload/userHeadImages/1558542360118_9.jpg","company":"个体","position":"自由职业者","maidianPrice":0,"positionTitle":"脉场","selfDesc":"确定大概方向，保持充满活力。","isRewarded":true},"time":1575965444151,"content":"什么？你还在用套现机？？？年底风控，多少人信用卡被风控。。不是银联认证机，我不用。没有银联小票，我不用。没有征信系统我不用。报备银联征信 pos 机\u2014《衫德久付 》100天内 vip 通道提额养征信。","hot":1575965444151,"htmlContent":"<!DOCTYPEhtml><html><head><meta name=\"viewport\"content=\"width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\"/><\/head><body>undefined<\/body><\/html>","referenceId":null,"referenceContent":null,"referenceAuthorName":"","createdAt":"2019-12-10T16:10:44+08:00","updatedAt":"2019-12-10T16:11:08+08:00","__v":0}],"total":1035}
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private Object message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private Object token;
    private Object rcToken;

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

    public Object getRcToken() {
        return rcToken;
    }

    public void setRcToken(Object rcToken) {
        this.rcToken = rcToken;
    }

    public static class DataBean {
        /**
         * essays : [{"_id":10646,"images":[],"reward":0,"fabulous":0,"comment":0,"show":true,"deleted":true,"isBreakingNews":false,"userId":3141,"basicInfo":{"_id":"5ce161ba011ee76012cbe968","userId":3141,"gender":0,"professionalIdentity":1100,"showEdit":1,"seePrice":0,"friendPrice":0,"createdAt":"2019-05-19T22:01:30+08:00","updatedAt":"2019-10-15T11:16:56+08:00","__v":0,"realName":"王壮益","headImg":"/img/upload/userHeadImages/1558542360118_9.jpg","company":"个体","position":"自由职业者","maidianPrice":0,"positionTitle":"脉场","selfDesc":"确定大概方向，保持充满活力。","isRewarded":true},"time":1575965444151,"content":"什么？你还在用套现机？？？年底风控，多少人信用卡被风控。。不是银联认证机，我不用。没有银联小票，我不用。没有征信系统我不用。报备银联征信 pos 机\u2014《衫德久付 》100天内 vip 通道提额养征信。","hot":1575965444151,"htmlContent":"<!DOCTYPEhtml><html><head><meta name=\"viewport\"content=\"width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0\"/><\/head><body>undefined<\/body><\/html>","referenceId":null,"referenceContent":null,"referenceAuthorName":"","createdAt":"2019-12-10T16:10:44+08:00","updatedAt":"2019-12-10T16:11:08+08:00","__v":0}]
         * total : 1035
         */

        private int total;
        private List<EssaysBean> essays;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<EssaysBean> getEssays() {
            return essays;
        }

        public void setEssays(List<EssaysBean> essays) {
            this.essays = essays;
        }

        public static class EssaysBean {
            /**
             * _id : 10646
             * images : []
             * reward : 0
             * fabulous : 0
             * comment : 0
             * show : true
             * deleted : true
             * isBreakingNews : false
             * userId : 3141
             * basicInfo : {"_id":"5ce161ba011ee76012cbe968","userId":3141,"gender":0,"professionalIdentity":1100,"showEdit":1,"seePrice":0,"friendPrice":0,"createdAt":"2019-05-19T22:01:30+08:00","updatedAt":"2019-10-15T11:16:56+08:00","__v":0,"realName":"王壮益","headImg":"/img/upload/userHeadImages/1558542360118_9.jpg","company":"个体","position":"自由职业者","maidianPrice":0,"positionTitle":"脉场","selfDesc":"确定大概方向，保持充满活力。","isRewarded":true}
             * time : 1575965444151
             * content : 什么？你还在用套现机？？？年底风控，多少人信用卡被风控。。不是银联认证机，我不用。没有银联小票，我不用。没有征信系统我不用。报备银联征信 pos 机—《衫德久付 》100天内 vip 通道提额养征信。
             * hot : 1575965444151
             * htmlContent : <!DOCTYPEhtml><html><head><meta name="viewport"content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0"/></head><body>undefined</body></html>
             * referenceId : null
             * referenceContent : null
             * referenceAuthorName :
             * createdAt : 2019-12-10T16:10:44+08:00
             * updatedAt : 2019-12-10T16:11:08+08:00
             * __v : 0
             */

            private int _id;
            private int reward;
            private int fabulous;
            private int comment;
            private boolean show;
            private boolean deleted;
            private boolean isBreakingNews;
            private int userId;
            private BasicInfoBean basicInfo;
            private long time;
            private String content;
            private long hot;
            private String htmlContent;
            private Object referenceId;
            private Object referenceContent;
            private String referenceAuthorName;
            private String createdAt;
            private String updatedAt;
            private int __v;
            private List<?> images;

            public int get_id() {
                return _id;
            }

            public void set_id(int _id) {
                this._id = _id;
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

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public boolean isIsBreakingNews() {
                return isBreakingNews;
            }

            public void setIsBreakingNews(boolean isBreakingNews) {
                this.isBreakingNews = isBreakingNews;
            }

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

            public Object getReferenceId() {
                return referenceId;
            }

            public void setReferenceId(Object referenceId) {
                this.referenceId = referenceId;
            }

            public Object getReferenceContent() {
                return referenceContent;
            }

            public void setReferenceContent(Object referenceContent) {
                this.referenceContent = referenceContent;
            }

            public String getReferenceAuthorName() {
                return referenceAuthorName;
            }

            public void setReferenceAuthorName(String referenceAuthorName) {
                this.referenceAuthorName = referenceAuthorName;
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

            public List<?> getImages() {
                return images;
            }

            public void setImages(List<?> images) {
                this.images = images;
            }

            public static class BasicInfoBean {
                /**
                 * _id : 5ce161ba011ee76012cbe968
                 * userId : 3141
                 * gender : 0
                 * professionalIdentity : 1100
                 * showEdit : 1
                 * seePrice : 0
                 * friendPrice : 0
                 * createdAt : 2019-05-19T22:01:30+08:00
                 * updatedAt : 2019-10-15T11:16:56+08:00
                 * __v : 0
                 * realName : 王壮益
                 * headImg : /img/upload/userHeadImages/1558542360118_9.jpg
                 * company : 个体
                 * position : 自由职业者
                 * maidianPrice : 0
                 * positionTitle : 脉场
                 * selfDesc : 确定大概方向，保持充满活力。
                 * isRewarded : true
                 */

                private String _id;
                private int userId;
                private int gender;
                private int professionalIdentity;
                private int showEdit;
                private int seePrice;
                private int friendPrice;
                private String createdAt;
                private String updatedAt;
                private int __v;
                private String realName;
                private String headImg;
                private String company;
                private String position;
                private int maidianPrice;
                private String positionTitle;
                private String selfDesc;
                private boolean isRewarded;

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

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public int getProfessionalIdentity() {
                    return professionalIdentity;
                }

                public void setProfessionalIdentity(int professionalIdentity) {
                    this.professionalIdentity = professionalIdentity;
                }

                public int getShowEdit() {
                    return showEdit;
                }

                public void setShowEdit(int showEdit) {
                    this.showEdit = showEdit;
                }

                public int getSeePrice() {
                    return seePrice;
                }

                public void setSeePrice(int seePrice) {
                    this.seePrice = seePrice;
                }

                public int getFriendPrice() {
                    return friendPrice;
                }

                public void setFriendPrice(int friendPrice) {
                    this.friendPrice = friendPrice;
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

                public String getRealName() {
                    return realName;
                }

                public void setRealName(String realName) {
                    this.realName = realName;
                }

                public String getHeadImg() {
                    return headImg;
                }

                public void setHeadImg(String headImg) {
                    this.headImg = headImg;
                }

                public String getCompany() {
                    return company;
                }

                public void setCompany(String company) {
                    this.company = company;
                }

                public String getPosition() {
                    return position;
                }

                public void setPosition(String position) {
                    this.position = position;
                }

                public int getMaidianPrice() {
                    return maidianPrice;
                }

                public void setMaidianPrice(int maidianPrice) {
                    this.maidianPrice = maidianPrice;
                }

                public String getPositionTitle() {
                    return positionTitle;
                }

                public void setPositionTitle(String positionTitle) {
                    this.positionTitle = positionTitle;
                }

                public String getSelfDesc() {
                    return selfDesc;
                }

                public void setSelfDesc(String selfDesc) {
                    this.selfDesc = selfDesc;
                }

                public boolean isIsRewarded() {
                    return isRewarded;
                }

                public void setIsRewarded(boolean isRewarded) {
                    this.isRewarded = isRewarded;
                }
            }
        }
    }
}
