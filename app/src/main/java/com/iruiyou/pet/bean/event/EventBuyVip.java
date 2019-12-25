package com.iruiyou.pet.bean.event;

/**
 * 购买VIP成功的消息
 */
public class EventBuyVip {
    private double spendingCrystal;//消费的水晶
//    private String userId; //开通用户的id
    private int vipLeve; //开通的会员登记

    public double getSpendingCrystal() {
        return spendingCrystal;
    }

    public void setSpendingCrystal(double spendingCrystal) {
        this.spendingCrystal = spendingCrystal;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public int getVipLeve() {
        return vipLeve;
    }

    public void setVipLeve(int vipLeve) {
        this.vipLeve = vipLeve;
    }
}
