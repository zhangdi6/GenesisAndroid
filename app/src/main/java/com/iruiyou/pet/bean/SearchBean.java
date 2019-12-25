package com.iruiyou.pet.bean;

/**
 * 搜索实体类
 */
public class SearchBean {

    private int userId;
    private String invitedCode;
    private String phone;
    private String name;
    private String company;
    private String position;
    private String headImg;
    private String realName;
    private String groupName;
    private String groupLogo;
    private int groupId;
    private int professionalIdentity;

    public String getGroupLogo() {
        return groupLogo;
    }

    public void setGroupLogo(String groupLogo) {
        this.groupLogo = groupLogo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getProfessionalIdentity() {
        return professionalIdentity;
    }

    public void setProfessionalIdentity(int professionalIdentity) {
        this.professionalIdentity = professionalIdentity;
    }

    private boolean registerType;

    public boolean isRegisterType() {
        return registerType;
    }

    public void setRegisterType(boolean registerType) {
        this.registerType = registerType;
    }

    private int type;//0 好友， 1 注册， 2 未注册

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public SearchBean(int type,String groupName, int groupId,String groupLogo) {
        this.type = type;
        this.groupName = groupName;
        this.groupId = groupId;
        this.groupLogo = groupLogo;
    }

    public SearchBean(int userId, int type, String phone, String company, String position, String headImg, String realName, int professionalIdentity) {
        this.userId = userId;
        this.type = type;
        this.phone = phone;
        this.company = company;
        this.position = position;
        this.headImg = headImg;
        this.realName = realName;
        this.professionalIdentity = professionalIdentity;
    }
    public SearchBean(String realName,int type, String phone,String name) {
        this.realName = realName;
        this.type = type;
        this.phone = phone;
        this.name = name;
    }
}
