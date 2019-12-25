package com.iruiyou.pet.bean;

/**
 * 作者：sgf
 * 水晶价格
 */
public class CrystalPriceBean {

    private String rmbPrice;
    private String crystalPrice;

    public CrystalPriceBean(String rmbPrice, String crystalPrice) {
        this.rmbPrice = rmbPrice;
        this.crystalPrice = crystalPrice;
    }

    public String getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(String rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    public String getCrystalPrice() {
        return crystalPrice;
    }

    public void setCrystalPrice(String crystalPrice) {
        this.crystalPrice = crystalPrice;
    }
}
