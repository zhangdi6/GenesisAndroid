package com.iruiyou.pet.bean;

/**
 * 作者：jiaopeirong on 2018/5/25 22:02
 * 邮箱：chinajpr@163.com
 */
public class AddPetBean {

    /**
     * statusCode : 0
     * message : 添加成功
     * error : null
     * data : {"userId":10000020,"animal":2,"variety":2,"headImg":"/img/upload/petHeadImages/1527256818861_6.jpg","petNick":"yy","ownerNick":"hh","combat":0,"sex":0,"birthday":"未填写","createdAt":"2018-05-25T14:01:38.612Z","updatedAt":"2018-05-25T14:01:38.612Z","_id":10000006,"__v":0}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private String message;
    private Object error;
    private DataBean data;
    private Object csrfToken;
    private Object token;

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

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(Object csrfToken) {
        this.csrfToken = csrfToken;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * userId : 10000020
         * animal : 2
         * variety : 2
         * headImg : /img/upload/petHeadImages/1527256818861_6.jpg
         * petNick : yy
         * ownerNick : hh
         * combat : 0
         * sex : 0
         * birthday : 未填写
         * createdAt : 2018-05-25T14:01:38.612Z
         * updatedAt : 2018-05-25T14:01:38.612Z
         * _id : 10000006
         * __v : 0
         */

        private int userId;
        private int animal;
        private int variety;
        private String headImg;
        private String petNick;
        private String ownerNick;
        private int combat;
        private int sex;
        private String birthday;
        private String createdAt;
        private String updatedAt;
        private int _id;
        private int __v;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getAnimal() {
            return animal;
        }

        public void setAnimal(int animal) {
            this.animal = animal;
        }

        public int getVariety() {
            return variety;
        }

        public void setVariety(int variety) {
            this.variety = variety;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getPetNick() {
            return petNick;
        }

        public void setPetNick(String petNick) {
            this.petNick = petNick;
        }

        public String getOwnerNick() {
            return ownerNick;
        }

        public void setOwnerNick(String ownerNick) {
            this.ownerNick = ownerNick;
        }

        public int getCombat() {
            return combat;
        }

        public void setCombat(int combat) {
            this.combat = combat;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public int get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
