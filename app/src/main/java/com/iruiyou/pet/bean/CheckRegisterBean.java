package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/9/9 13:44
 * 邮箱：chinajpr@163.com
 */
public class CheckRegisterBean {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"userId":26,"invitedCode":"我被邀请码","phone":"17601011006","name":"张三","company":"金恒","position":"开发"},{"userId":38,"invitedCode":"我被邀请码","phone":"17610475350","name":"TEST"}]
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private String error;
    private String csrfToken;
    private String token;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userId : 26
         * invitedCode : 我被邀请码
         * phone : 17601011006
         * name : 张三
         * company : 金恒
         * position : 开发
         */

        private int userId;
        private String invitedCode;
        private String phone;
        private String name;
        private String company;
        private String position;
        private String headImg;
        private String realName;

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
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
    }
}
