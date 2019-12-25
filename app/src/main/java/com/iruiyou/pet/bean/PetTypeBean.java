package com.iruiyou.pet.bean;

import com.google.gson.annotations.SerializedName;

/**
 * 宠物类型
 * 作者：jiaopeirong on 2018/5/21 22:17
 * 邮箱：chinajpr@163.com
 */
public class PetTypeBean  {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"1":{"1":"橘猫"},"2":{"1":"哈士奇","2":"贵宾犬","3":"松狮","4":"吉娃娃","5":"博美犬","6":"法国斗牛犬","7":"英国斗牛犬","9999":"其它"},"9999":{"9999":"其它"},"animal":{"1":"猫","2":"狗","9999":"其它"}}
     * csrfToken : null
     * token : null
     */

    private int statusCode;
    private Object message;
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
         * 1 : {"1":"橘猫"}
         * 2 : {"1":"哈士奇","2":"贵宾犬","3":"松狮","4":"吉娃娃","5":"博美犬","6":"法国斗牛犬","7":"英国斗牛犬","9999":"其它"}
         * 9999 : {"9999":"其它"}
         * animal : {"1":"猫","2":"狗","9999":"其它"}
         */

        @SerializedName("1")
        private _$1Bean _$1;
        @SerializedName("2")
        private _$2Bean _$2;
        @SerializedName("9999")
        private _$9999Bean _$9999;
        private AnimalBean animal;

        public _$1Bean get_$1() {
            return _$1;
        }

        public void set_$1(_$1Bean _$1) {
            this._$1 = _$1;
        }

        public _$2Bean get_$2() {
            return _$2;
        }

        public void set_$2(_$2Bean _$2) {
            this._$2 = _$2;
        }

        public _$9999Bean get_$9999() {
            return _$9999;
        }

        public void set_$9999(_$9999Bean _$9999) {
            this._$9999 = _$9999;
        }

        public AnimalBean getAnimal() {
            return animal;
        }

        public void setAnimal(AnimalBean animal) {
            this.animal = animal;
        }

        public static class _$1Bean {
            /**
             * 1 : 橘猫
             */

            @SerializedName("1")
            private String _$1;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }
        }

        public static class _$2Bean {
            /**
             * 1 : 哈士奇
             * 2 : 贵宾犬
             * 3 : 松狮
             * 4 : 吉娃娃
             * 5 : 博美犬
             * 6 : 法国斗牛犬
             * 7 : 英国斗牛犬
             * 9999 : 其它
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;
            @SerializedName("4")
            private String _$4;
            @SerializedName("5")
            private String _$5;
            @SerializedName("6")
            private String _$6;
            @SerializedName("7")
            private String _$7;
            @SerializedName("9999")
            private String _$9999;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }

            public String get_$4() {
                return _$4;
            }

            public void set_$4(String _$4) {
                this._$4 = _$4;
            }

            public String get_$5() {
                return _$5;
            }

            public void set_$5(String _$5) {
                this._$5 = _$5;
            }

            public String get_$6() {
                return _$6;
            }

            public void set_$6(String _$6) {
                this._$6 = _$6;
            }

            public String get_$7() {
                return _$7;
            }

            public void set_$7(String _$7) {
                this._$7 = _$7;
            }

            public String get_$9999() {
                return _$9999;
            }

            public void set_$9999(String _$9999) {
                this._$9999 = _$9999;
            }
        }

        public static class _$9999Bean {
            /**
             * 9999 : 其它
             */

            @SerializedName("9999")
            private String _$9999;

            public String get_$9999() {
                return _$9999;
            }

            public void set_$9999(String _$9999) {
                this._$9999 = _$9999;
            }
        }

        public static class AnimalBean {
            /**
             * 1 : 猫
             * 2 : 狗
             * 9999 : 其它
             */

            @SerializedName("1")
            private String _$1;
            @SerializedName("2")
            private String _$2;
            @SerializedName("9999")
            private String _$9999;

            public String get_$1() {
                return _$1;
            }

            public void set_$1(String _$1) {
                this._$1 = _$1;
            }

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$9999() {
                return _$9999;
            }

            public void set_$9999(String _$9999) {
                this._$9999 = _$9999;
            }
        }
    }
}
