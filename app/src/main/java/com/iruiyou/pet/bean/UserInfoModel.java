package com.iruiyou.pet.bean;

public class UserInfoModel extends BaseModel {
    private String countryCode;
    private String phone;
    private boolean registered;
    private String inviteCode;
    private String invitedCode;
    private int inviteCount;
    private float pbsAmount;
    private float lastHarvestAmount;
    private float pbsFrozen;
    private int combat;
    private BasicInfoModel basicInfo;
    private String statisticsInfo;
    private String userIdentity;
    private long crystalAMount;
    private String userChannel;
    private float pbsDepositAmount;
    private float pbsTranAmount;
    private float pbsDrawLockedAmount;
    private String cityNode;
    private float hatchAmount;
    private int vipLevel;
    private float rebateCrystal;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public int getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(int inviteCount) {
        this.inviteCount = inviteCount;
    }

    public float getPbsAmount() {
        return pbsAmount;
    }

    public void setPbsAmount(float pbsAmount) {
        this.pbsAmount = pbsAmount;
    }

    public float getLastHarvestAmount() {
        return lastHarvestAmount;
    }

    public void setLastHarvestAmount(float lastHarvestAmount) {
        this.lastHarvestAmount = lastHarvestAmount;
    }

    public float getPbsFrozen() {
        return pbsFrozen;
    }

    public void setPbsFrozen(float pbsFrozen) {
        this.pbsFrozen = pbsFrozen;
    }

    public int getCombat() {
        return combat;
    }

    public void setCombat(int combat) {
        this.combat = combat;
    }

    public BasicInfoModel getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoModel basicInfo) {
        this.basicInfo = basicInfo;
    }

    public String getStatisticsInfo() {
        return statisticsInfo;
    }

    public void setStatisticsInfo(String statisticsInfo) {
        this.statisticsInfo = statisticsInfo;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public long getCrystalAMount() {
        return crystalAMount;
    }

    public void setCrystalAMount(long crystalAMount) {
        this.crystalAMount = crystalAMount;
    }

    public String getUserChannel() {
        return userChannel;
    }

    public void setUserChannel(String userChannel) {
        this.userChannel = userChannel;
    }

    public float getPbsDepositAmount() {
        return pbsDepositAmount;
    }

    public void setPbsDepositAmount(float pbsDepositAmount) {
        this.pbsDepositAmount = pbsDepositAmount;
    }

    public float getPbsTranAmount() {
        return pbsTranAmount;
    }

    public void setPbsTranAmount(float pbsTranAmount) {
        this.pbsTranAmount = pbsTranAmount;
    }

    public float getPbsDrawLockedAmount() {
        return pbsDrawLockedAmount;
    }

    public void setPbsDrawLockedAmount(float pbsDrawLockedAmount) {
        this.pbsDrawLockedAmount = pbsDrawLockedAmount;
    }

    public String getCityNode() {
        return cityNode;
    }

    public void setCityNode(String cityNode) {
        this.cityNode = cityNode;
    }

    public float getHatchAmount() {
        return hatchAmount;
    }

    public void setHatchAmount(float hatchAmount) {
        this.hatchAmount = hatchAmount;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public float getRebateCrystal() {
        return rebateCrystal;
    }

    public void setRebateCrystal(float rebateCrystal) {
        this.rebateCrystal = rebateCrystal;
    }
}
