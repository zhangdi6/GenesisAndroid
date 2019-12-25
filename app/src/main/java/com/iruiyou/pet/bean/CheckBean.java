package com.iruiyou.pet.bean;

/**
 * Created by sgf on 2018/5/26.
 */

public class CheckBean {
    private boolean isChecked;
    private String str;
    private String msg = "This is a test data";

    private int intValue;
    public CheckBean(){

    }

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public CheckBean(String str , boolean isCheched) {
        this.isChecked = isCheched;
        this.str = str;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
