package com.iruiyou.pet.bean;

public class onEvent {
    public float titleSize;
    public float lineWidth;
    public String msg ;

    public float getTitleSize() {
        return titleSize;
    }

    public void setTitleSize(float titleSize) {
        this.titleSize = titleSize;
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public onEvent(float titleSize, float lineWidth, String msg) {
        this.titleSize = titleSize;
        this.lineWidth = lineWidth;
        this.msg = msg;
    }
}
