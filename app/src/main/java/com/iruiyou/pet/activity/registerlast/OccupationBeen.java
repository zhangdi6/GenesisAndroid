package com.iruiyou.pet.activity.registerlast;

/**
 * @author： sgf
 * @date： 2018/9/28
 * @describe：职业信息
 */
public class OccupationBeen {
    private String langValue;// "销售与市场营销",
    private int dbKey; //               "dbKey": 13
    private Object picUrlSelectTrue;
    private Object picUrlSelectfalse;
    private boolean isSelect = false;


    @Override
    public String toString() {
        return "OccupationBeen{" +
                "langValue='" + langValue + '\'' +
                ", dbKey=" + dbKey +
                ", picUrlSelectTrue='" + picUrlSelectTrue + '\'' +
                ", picUrlSelectfalse='" + picUrlSelectfalse + '\'' +
                ", isSelect=" + isSelect +

                '}';
    }

    public String getLangValue() {
        return langValue;
    }

    public void setLangValue(String langValue) {
        this.langValue = langValue;
    }

    public int getDbKey() {
        return dbKey;
    }

    public void setDbKey(int dbKey) {
        this.dbKey = dbKey;
    }

    public Object getPicUrlSelectTrue() {
        return picUrlSelectTrue;
    }

    public void setPicUrlSelectTrue(Object picUrlSelectTrue) {
        this.picUrlSelectTrue = picUrlSelectTrue;
    }

    public Object getPicUrlSelectfalse() {
        return picUrlSelectfalse;
    }

    public void setPicUrlSelectfalse(Object picUrlSelectfalse) {
        this.picUrlSelectfalse = picUrlSelectfalse;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public OccupationBeen(String langValue, int dbKey) {
        this.langValue = langValue;
        this.dbKey = dbKey;
//        this.picUrlSelectTrue = picUrlSelectTrue;
//        this.picUrlSelectfalse = picUrlSelectfalse;
//        this.isSelect = isSelect;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OccupationBeen) {
            return this.dbKey == ((OccupationBeen) obj).getDbKey();
        } else {
            return false;
        }


    }
}
