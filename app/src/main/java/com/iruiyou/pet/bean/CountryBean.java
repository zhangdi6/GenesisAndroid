package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 类描述:
 * 创建日期:2018/5/28 on 11:45
 * 作者:JiaoPeiRong
 */
public class CountryBean {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * en : Angola
         * zh : 安哥拉
         * locale : AO
         * code : 244
         */

        private String en;
        private String zh;
        private String locale;
        private String code;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }

        public String getZh() {
            return zh;
        }

        public void setZh(String zh) {
            this.zh = zh;
        }

        public String getLocale() {
            return locale;
        }

        public void setLocale(String locale) {
            this.locale = locale;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
