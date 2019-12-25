package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 作者：sgf
 * 59、获取水晶商品列表
 */
public class CrystalGoodsBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"yuan":49,"crystal":50,"url":"https://detail.youzan.com/show/goods?alias=2orp7ycbe21do"},{"yuan":97,"crystal":100,"url":"https://detail.youzan.com/show/goods?alias=2xlgb0ocimd30"},{"yuan":475,"crystal":500,"url":"https://detail.youzan.com/show/goods?alias=3nj5r81rbbg8c"},{"yuan":920,"crystal":1000,"url":"https://detail.youzan.com/show/goods?alias=2fvfpw3c3et3w"}]
     * csrfToken : null
     * token : null
     * rcToken : null
     */

    private int statusCode;
    private String message;
    private String error;
    private String csrfToken;
    private String token;
    private String rcToken;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * yuan : 49
         * crystal : 50
         * url : https://detail.youzan.com/show/goods?alias=2orp7ycbe21do
         */

        private int yuan;
        private int crystal;
        private String url;
        private int vipLevel;

        public int getVipLevel() {
            return vipLevel;
        }

        public void setVipLevel(int vipLevel) {
            this.vipLevel = vipLevel;
        }

        public int getYuan() {
            return yuan;
        }

        public void setYuan(int yuan) {
            this.yuan = yuan;
        }

        public int getCrystal() {
            return crystal;
        }

        public void setCrystal(int crystal) {
            this.crystal = crystal;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
