package com.iruiyou.pet.bean;

import java.util.List;

/**
 * 商机-商品信息
 */
public class OpportunitiesGoodsBean {
    private int statusCode;
    private String message;
    private Object error;
    private String csrfToken;
    private String token;
    private String rcToken;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private Response response;

        public Response getResponse() {
            return response;
        }

        public void setResponse(Response response) {
            this.response = response;
        }
    }

    public static class Response {
        private int count;
        private List<Item> items;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }


    public static class Item {
        private boolean isHead;//是否作为头数据
        private String item_id;//Number商品的数字id
        private String alias;//String商品别名，是一串字符
        private String title;//String商品标题
        private int price;//Number价格，单位分
        private int item_type;//Number商品类型
        private String item_no;//String商家编码，商家给商品设置的商家编码。
        private int quantity;//Number总库存
        private int post_type;//Number运费类型，1 是统一运费，2是运费模板
        private int post_fee;//Number运费，单位分。当post_type为1时的运费
        private String created_time;//String创建时间
        private String update_time;//String更新时间
        private String detail_url;//String商品详情链接
        private DeliveryTemplateOpenModel delivery_template;// DeliveryTemplateOpenModel运费模板信息
        private int num;//Number商家排序字段
        private List<ItemImageOpenModel> item_imgs;// ItemImageOpenModel[]图片信息
        private String origin;//String商品划线价
        private String classId;//String默认为"youzan_goods_selling"
        private String image;//String图片链接
        private String shareIcon;//String同image
        private String shareTitle;//String商品标题
        private String shareDetail;//Number同price
        private String pageUrl;//String小程序路径
        private int cost_price;//成本价格

        public boolean isHead() {
            return isHead;
        }

        public void setHead(boolean head) {
            isHead = head;
        }

        public int getCost_price() {
            return cost_price;
        }

        public void setCost_price(int cost_price) {
            this.cost_price = cost_price;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public String getShareDetail() {
            return shareDetail;
        }

        public void setShareDetail(String shareDetail) {
            this.shareDetail = shareDetail;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getItem_type() {
            return item_type;
        }

        public void setItem_type(int item_type) {
            this.item_type = item_type;
        }

        public String getItem_no() {
            return item_no;
        }

        public void setItem_no(String item_no) {
            this.item_no = item_no;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPost_type() {
            return post_type;
        }

        public void setPost_type(int post_type) {
            this.post_type = post_type;
        }

        public int getPost_fee() {
            return post_fee;
        }

        public void setPost_fee(int post_fee) {
            this.post_fee = post_fee;
        }

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public DeliveryTemplateOpenModel getDelivery_template() {
            return delivery_template;
        }

        public void setDelivery_template(DeliveryTemplateOpenModel delivery_template) {
            this.delivery_template = delivery_template;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public List<ItemImageOpenModel> getItem_imgs() {
            return item_imgs;
        }

        public void setItem_imgs(List<ItemImageOpenModel> item_imgs) {
            this.item_imgs = item_imgs;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getShareIcon() {
            return shareIcon;
        }

        public void setShareIcon(String shareIcon) {
            this.shareIcon = shareIcon;
        }
    }

    public static class DeliveryTemplateOpenModel {
        private String delivery_template_id;//Number;// 运费模板ID
        private String delivery_template_fee;//String ;//运费的范围
        private String delivery_template_name;//String运费模板名称
        private String delivery_template_valuation;//TypeNumber运费模版的计算类型，1 按件 2 按重量 3 按体积

        public String getDelivery_template_id() {
            return delivery_template_id;
        }

        public void setDelivery_template_id(String delivery_template_id) {
            this.delivery_template_id = delivery_template_id;
        }

        public String getDelivery_template_fee() {
            return delivery_template_fee;
        }

        public void setDelivery_template_fee(String delivery_template_fee) {
            this.delivery_template_fee = delivery_template_fee;
        }

        public String getDelivery_template_name() {
            return delivery_template_name;
        }

        public void setDelivery_template_name(String delivery_template_name) {
            this.delivery_template_name = delivery_template_name;
        }

        public String getDelivery_template_valuation() {
            return delivery_template_valuation;
        }

        public void setDelivery_template_valuation(String delivery_template_valuation) {
            this.delivery_template_valuation = delivery_template_valuation;
        }
    }


    public static class ItemImageOpenModel {
        private String url;//String图片链接地址
        private String thumbnail;//String图片缩略图链接地址
        private String medium;//String中号大小图片链接地址
        private String combine;//String组合图片链接地址
        private String created;//String图片创建时间，时间格式：yyyy-MM-dd HH:mm:ss
        private String id;//Number图片ID

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getCombine() {
            return combine;
        }

        public void setCombine(String combine) {
            this.combine = combine;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

}
