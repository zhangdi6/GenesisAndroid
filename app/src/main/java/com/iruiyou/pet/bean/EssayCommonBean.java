package com.iruiyou.pet.bean;

import java.io.Serializable;

/**
 * 博文评论数据对象
 */
public class EssayCommonBean implements Serializable{

    private int essayId;// { type: Number, required: true }, // 所属短文ID
    private int userId;// { type: Number, required: true }, // 评论者Id // 应该是commentId ，建议添加floorId(楼层)）
    private BasicInfoBean basicInfo;// { type: Schema.Types.ObjectId, ref: 'BasicInfo', required: true }, // 评论者资料
    private long time;// { type: Number, required: true }, // 评论时间
    private String content;// { type: String, required: true }, // 文本
    private int  fabulous;// { type: Number, required: true, default: 0 }, // 对评论的点赞数
    private String lastFabulousBasic;// { type: Schema.Types.ObjectId, ref: 'BasicInfo' }, // 最后点赞人的基本资料
    private  int reply;// { type: Number, required: true, default: 0 }, // 对评论的回复数
    private BasicInfoBean lastReplyBasic;// { type: Schema.Types.ObjectId, ref: 'BasicInfo' }, // 最后回复人的基本资料
    private boolean show;// { type: Boolean, required: true, default: true }, // 后台配置是否显示
    private boolean deleted;// { type: Boolean, required: true, default: false }, // 用户自己删除


    public int getEssayId() {
        return essayId;
    }

    public void setEssayId(int essayId) {
        this.essayId = essayId;
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

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }

    public String getLastFabulousBasic() {
        return lastFabulousBasic;
    }

    public void setLastFabulousBasic(String lastFabulousBasic) {
        this.lastFabulousBasic = lastFabulousBasic;
    }

    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public BasicInfoBean getLastReplyBasic() {
        return lastReplyBasic;
    }

    public void setLastReplyBasic(BasicInfoBean lastReplyBasic) {
        this.lastReplyBasic = lastReplyBasic;
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

//    public static class BasicInfoBean implements Serializable {
//        /**
//         * school : 学校
//         * education : 学历
//         * company : 公司
//         * position : 职位
//         * country : 公司注册地
//         * number : 公司人数
//         * nature : 公司性质
//         * headImg : /img/upload/userHeadImages/userHead.png
//         * selfDesc : 无
//         * _id : 5b6f94874f5fb36df9eff681
//         * userId : 26
//         * realName : uu
//         * createdAt : 2018-08-12T09:59:35+08:00
//         * updatedAt : 2018-08-12T09:59:35+08:00
//         * __v : 0
//         */
//
//        private String school;
//        private int education;
//        private String company;
//        private String position;
//        private String country;
//        private String number;
//        private String nature;
//        private String headImg;
//        private String selfDesc;
//        private String _id;
//        private int userId;
//        private String realName;
//        private String createdAt;
//        private String updatedAt;
//        private int __v;
//        private int professionalIdentity;
//        private int gender;
//        private String positionTitle;//职位头衔
//        private int companyId;// { type: Number }, // 任职公司的ID
//        private int  companyIndex;// { type: Number }, // 任职公司的团队排序（从1开始，0不显示）
//        private double seePrice;// { type: Number, default: 1.0 }, // 查看资料价格
//        private double friendPrice;// { type: Number, default: 1.0 }, // 添加好友价格
//
//        public int getGender() {
//            return gender;
//        }
//
//        public void setGender(int gender) {
//            this.gender = gender;
//        }
//
//        public int getCompanyId() {
//            return companyId;
//        }
//
//        public void setCompanyId(int companyId) {
//            this.companyId = companyId;
//        }
//
//        public int getCompanyIndex() {
//            return companyIndex;
//        }
//
//        public void setCompanyIndex(int companyIndex) {
//            this.companyIndex = companyIndex;
//        }
//
//        public double getSeePrice() {
//            return seePrice;
//        }
//
//        public void setSeePrice(double seePrice) {
//            this.seePrice = seePrice;
//        }
//
//        public double getFriendPrice() {
//            return friendPrice;
//        }
//
//        public void setFriendPrice(double friendPrice) {
//            this.friendPrice = friendPrice;
//        }
//
//        public String getPositionTitle() {
//            return positionTitle;
//        }
//
//        public void setPositionTitle(String positionTitle) {
//            this.positionTitle = positionTitle;
//        }
//
//        public int getProfessionalIdentity() {
//            return professionalIdentity;
//        }
//
//        public void setProfessionalIdentity(int professionalIdentity) {
//            this.professionalIdentity = professionalIdentity;
//        }
//
//        public String getSchool() {
//            return school;
//        }
//
//        public void setSchool(String school) {
//            this.school = school;
//        }
//
//        public int getEducation() {
//            return education;
//        }
//
//        public void setEducation(int education) {
//            this.education = education;
//        }
//
//        public String getCompany() {
//            return company;
//        }
//
//        public void setCompany(String company) {
//            this.company = company;
//        }
//
//        public String getPosition() {
//            return position;
//        }
//
//        public void setPosition(String position) {
//            this.position = position;
//        }
//
//        public String getCountry() {
//            return country;
//        }
//
//        public void setCountry(String country) {
//            this.country = country;
//        }
//
//        public String getNumber() {
//            return number;
//        }
//
//        public void setNumber(String number) {
//            this.number = number;
//        }
//
//        public String getNature() {
//            return nature;
//        }
//
//        public void setNature(String nature) {
//            this.nature = nature;
//        }
//
//        public String getHeadImg() {
//            return headImg;
//        }
//
//        public void setHeadImg(String headImg) {
//            this.headImg = headImg;
//        }
//
//        public String getSelfDesc() {
//            return selfDesc;
//        }
//
//        public void setSelfDesc(String selfDesc) {
//            this.selfDesc = selfDesc;
//        }
//
//        public String get_id() {
//            return _id;
//        }
//
//        public void set_id(String _id) {
//            this._id = _id;
//        }
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public String getRealName() {
//            return realName;
//        }
//
//        public void setRealName(String realName) {
//            this.realName = realName;
//        }
//
//        public String getCreatedAt() {
//            return createdAt;
//        }
//
//        public void setCreatedAt(String createdAt) {
//            this.createdAt = createdAt;
//        }
//
//        public String getUpdatedAt() {
//            return updatedAt;
//        }
//
//        public void setUpdatedAt(String updatedAt) {
//            this.updatedAt = updatedAt;
//        }
//
//        public int get__v() {
//            return __v;
//        }
//
//        public void set__v(int __v) {
//            this.__v = __v;
//        }
//    }


}
