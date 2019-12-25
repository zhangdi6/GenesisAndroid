package com.iruiyou.pet.bean;

/**
 * 不带UserInfo的模型
 */
public class BasicInfoModel extends BaseModel {
    private long userId;
    private String realName;
    private String school;
    private int education;
    private String company;
    private String position;
    private String country;
    private int number;
    private String nature;
    private String headImg;
    private String selfDesc;
    private int gender;
    private int professionalIdentity;
    private int showEdit;
    private long userInfo;
    private long companyId;
    private long companyIndex;

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

    public long getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(long userInfo) {
        this.userInfo = userInfo;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public long getCompanyIndex() {
        return companyIndex;
    }

    public void setCompanyIndex(long companyIndex) {
        this.companyIndex = companyIndex;
    }
}
