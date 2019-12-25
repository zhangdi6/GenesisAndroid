package com.iruiyou.pet.bean;

import java.util.List;

public class LookForSbBean {

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
    private String token;
    private String rcToken;

    private List<ItemData> dataSource;

    public List<ItemData> getDataSource() {
        return dataSource;
    }

    public void setDataSource(List<ItemData> dataSource) {
        this.dataSource = dataSource;
    }

    public static class ItemData
    {
        private DataBean.BasicsInfo basicsInfo;
        private DataBean.StatisticsInfo statisticsInfo;

        public DataBean.BasicsInfo getBasicsInfo() {
            return basicsInfo;
        }

        public void setBasicsInfo(DataBean.BasicsInfo basicsInfo) {
            this.basicsInfo = basicsInfo;
        }

        public DataBean.StatisticsInfo getStatisticsInfo() {
            return statisticsInfo;
        }

        public void setStatisticsInfo(DataBean.StatisticsInfo statisticsInfo) {
            this.statisticsInfo = statisticsInfo;
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

    public String getRcToken() {
        return rcToken;
    }

    public void setRcToken(String rcToken) {
        this.rcToken = rcToken;
    }

    public class DataBean
    {

        private int infoCount;

        private List<BasicsInfo> basics;

        private List<StatisticsInfo> statistics;


        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public List<BasicsInfo> getBasics() {
            return basics;
        }

        public void setBasics(List<BasicsInfo> basics) {
            this.basics = basics;
        }

        public List<StatisticsInfo> getStatistics() {
            return statistics;
        }

        public void setStatistics(List<StatisticsInfo> statistics) {
            this.statistics = statistics;
        }

        public  class StatisticsInfo {
            private long userId;// 用户ID
            private long basicCount; // 基本资料任务
            private long blockchainCount;// 区块链信息任务
            private long workCount;//// 工作经验任务
            private int educationCount;//// 教育经历任务
            private int photoCount;//职业照任务
            private long crystalCount;// 买水晶任务
            private long crystalAmount;//已购水晶数量
            private String _id;
            private int __v;
            private String updatedAt;
            private String createdAt;
            private long userInfo;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public long getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(long userInfo) {
                this.userInfo = userInfo;
            }

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public long getBasicCount() {
                return basicCount;
            }

            public void setBasicCount(long basicCount) {
                this.basicCount = basicCount;
            }

            public long getBlockchainCount() {
                return blockchainCount;
            }

            public void setBlockchainCount(long blockchainCount) {
                this.blockchainCount = blockchainCount;
            }

            public long getWorkCount() {
                return workCount;
            }

            public void setWorkCount(long workCount) {
                this.workCount = workCount;
            }

            public int getEducationCount() {
                return educationCount;
            }

            public void setEducationCount(int educationCount) {
                this.educationCount = educationCount;
            }

            public int getPhotoCount() {
                return photoCount;
            }

            public void setPhotoCount(int photoCount) {
                this.photoCount = photoCount;
            }

            public long getCrystalCount() {
                return crystalCount;
            }

            public void setCrystalCount(long crystalCount) {
                this.crystalCount = crystalCount;
            }

            public long getCrystalAmount() {
                return crystalAmount;
            }

            public void setCrystalAmount(long crystalAmount) {
                this.crystalAmount = crystalAmount;
            }
        }


        public class BasicsInfo
        {
            private  int gender;//":0,
            private  int professionalIdentity;//":2,
            private  int showEdit;//":1,
            private  double seePrice;//":1,
            private  double friendPrice;//":10,
            private String _id;//"5b767ef87c63c218cb3edf4a",
            private long userId;//8,
            private String realName;//"Mu'roq",
            private String school;//"浙江大学",
            private  int education;//5,
            private String company;//"Blizzard",
            private String position;//"CVO",
            private String country;//"California",
            private  int number;//8,
            private String nature;//"企业",
            private String headImg;//"/img/upload/userHeadImages/1542283329346_0.jpg",
            private String selfDesc;//"",
            private String createdAt;//"2018-08-17T15:53:28+08:00",
            private String updatedAt;//"2018-11-15T20:02:09+08:00",
            private  int __v;//0,
            private  int userInfo;//8


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

            public double getSeePrice() {
                return seePrice;
            }

            public void setSeePrice(double seePrice) {
                this.seePrice = seePrice;
            }

            public double getFriendPrice() {
                return friendPrice;
            }

            public void setFriendPrice(double friendPrice) {
                this.friendPrice = friendPrice;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public long getUserId() {
                return userId;
            }

            public void setUserId(long userId) {
                this.userId = userId;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getSchool() {
                return school;
            }

            public void setSchool(String school) {
                this.school = school;
            }

            public int getEducation() {
                return education;
            }

            public void setEducation(int education) {
                this.education = education;
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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getNature() {
                return nature;
            }

            public void setNature(String nature) {
                this.nature = nature;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getSelfDesc() {
                return selfDesc;
            }

            public void setSelfDesc(String selfDesc) {
                this.selfDesc = selfDesc;
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

            public int getUserInfo() {
                return userInfo;
            }

            public void setUserInfo(int userInfo) {
                this.userInfo = userInfo;
            }
        }
    }




}
