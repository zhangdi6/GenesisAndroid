package com.iruiyou.pet.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 配置
 * 作者：jiaopeirong on 2018/5/19 09:15
 * 邮箱：chinajpr@163.com
 */
public class PetListBean implements Serializable {

    /**
     * statusCode : 0
     * message : null
     * error : null
     * data : {"funcs":[{"title":"宠链超市","desc":"帮你省钱的宠物超市","img":"http://47.95.237.181:8080/img/clcs.png","url":"https://shop40984708.youzan.com/v2/showcase/homepage?kdt_id=40792540&reft=1526026546685&spm=f71254180&sf=wx_sm&form=groupmessage&is_share=1&from=groupmessage&isappinstalled=0"},{"title":"最萌宠物","desc":"看看谁是最萌宠物","img":"http://47.95.237.181:8080/img/ycsd.png","url":"http://www.youtoupiao.com/vote/review/id/cc321e94a85edb90.html"}],"update":{"require":false,"option":false},"pets":{"1":{"1":"橘猫"},"2":{"1":"哈士奇","2":"贵宾犬","3":"松狮","4":"吉娃娃","5":"博美犬","6":"法国斗牛犬","7":"英国斗牛犬","9999":"其它"},"9999":{"9999":"其它"},"animal":{"1":"猫","2":"狗","9999":"其它"}}}
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

    public static class DataBean implements Serializable{
        /**
         * funcs : [{"title":"宠链超市","desc":"帮你省钱的宠物超市","img":"http://47.95.237.181:8080/img/clcs.png","url":"https://shop40984708.youzan.com/v2/showcase/homepage?kdt_id=40792540&reft=1526026546685&spm=f71254180&sf=wx_sm&form=groupmessage&is_share=1&from=groupmessage&isappinstalled=0"},{"title":"最萌宠物","desc":"看看谁是最萌宠物","img":"http://47.95.237.181:8080/img/ycsd.png","url":"http://www.youtoupiao.com/vote/review/id/cc321e94a85edb90.html"}]
         * update : {"require":false,"option":false}
         * pets : {"1":{"1":"橘猫"},"2":{"1":"哈士奇","2":"贵宾犬","3":"松狮","4":"吉娃娃","5":"博美犬","6":"法国斗牛犬","7":"英国斗牛犬","9999":"其它"},"9999":{"9999":"其它"},"animal":{"1":"猫","2":"狗","9999":"其它"}}
         */

        private UpdateBean update;
        private PetsBean pets;
        private List<FuncsBean> funcs;

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public PetsBean getPets() {
            return pets;
        }

        public void setPets(PetsBean pets) {
            this.pets = pets;
        }

        public List<FuncsBean> getFuncs() {
            return funcs;
        }

        public void setFuncs(List<FuncsBean> funcs) {
            this.funcs = funcs;
        }

        public static class UpdateBean {
            /**
             * require : false
             * option : false
             */

            private boolean require;
            private boolean option;

            public boolean isRequire() {
                return require;
            }

            public void setRequire(boolean require) {
                this.require = require;
            }

            public boolean isOption() {
                return option;
            }

            public void setOption(boolean option) {
                this.option = option;
            }
        }

        public static class PetsBean {
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

        public static class FuncsBean implements Serializable{
            /**
             * title : 宠链超市
             * desc : 帮你省钱的宠物超市
             * img : http://47.95.237.181:8080/img/clcs.png
             * url : https://shop40984708.youzan.com/v2/showcase/homepage?kdt_id=40792540&reft=1526026546685&spm=f71254180&sf=wx_sm&form=groupmessage&is_share=1&from=groupmessage&isappinstalled=0
             */

            private String title;
            private String desc;
            private String img;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
