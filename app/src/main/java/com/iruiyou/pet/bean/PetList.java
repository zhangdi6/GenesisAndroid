package com.iruiyou.pet.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 宠物列表
 * 作者：jiaopeirong on 2018/5/21 21:31
 * 邮箱：chinajpr@163.com
 */
public class PetList implements Serializable{

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : [{"combat":0,"sex":0,"_id":10000001,"userId":6,"animal":1,"variety":1,"headImg":"/img/upload/image/1526980154505_9.jpg","petNick":"JM","ownerNick":"bbbfun","createdAt":"2018-05-22T09:09:27.814Z","updatedAt":"2018-05-22T09:09:27.814Z","__v":0}]
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private Object message;
    private Object error;
    private Object csrfToken;
    private Object token;
    private List<DataBean> data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * combat : 0
         * sex : 0
         * _id : 10000001
         * userId : 6
         * animal : 1
         * variety : 1
         * headImg : /img/upload/image/1526980154505_9.jpg
         * petNick : JM
         * ownerNick : bbbfun
         * createdAt : 2018-05-22T09:09:27.814Z
         * updatedAt : 2018-05-22T09:09:27.814Z
         * __v : 0
         */

        private double combat;
        private int sex;
        private String _id;
        private int userId;
        private String animal;
        private String variety;
        private String animalName;
        private String varietyName;
        private String headImg;
        private String petNick;
        private String ownerNick;
        private String createdAt;
        private String updatedAt;
        private int __v;

        public double getCombat() {
            return combat;
        }

        public void setCombat(double combat) {
            this.combat = combat;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getAnimal() {
            return animal;
        }

        public void setAnimal(String animal) {
            this.animal = animal;
        }

        public String getVariety() {
            return variety;
        }

        public void setVariety(String variety) {
            this.variety = variety;
        }

        public String getAnimalName() {
            return animalName;
        }

        public void setAnimalName(String animalName) {
            this.animalName = animalName;
        }

        public String getVarietyName() {
            return varietyName;
        }

        public void setVarietyName(String varietyName) {
            this.varietyName = varietyName;
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

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }
    }
}
