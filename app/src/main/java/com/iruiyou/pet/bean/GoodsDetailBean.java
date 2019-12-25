package com.iruiyou.pet.bean;

import java.util.List;

public class GoodsDetailBean {

    private int statusCode;
    private String message;
    private String error;
    private DataBean data;
    private String csrfToken;
    private String token;
    private String rcToken;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
        private ItemDetailOpenModel item;

        public ItemDetailOpenModel getItem() {
            return item;
        }

        public void setItem(ItemDetailOpenModel item) {
            this.item = item;
        }
    }


    public static class ItemDetailOpenModel {
        private int cost_price; //成本价
        private int item_id;// Number 商品id
        private int kdt_id;// Number 店铺id
        private String title;// String 标题
        private String desc;// String 商品内容
        private String origin_price;// String 商品划线价格，可以自定义。例如 促销价：888
        private int buy_quota;// Number 每人限购多少件。0代表无限购，默认为0
        private String created;// String 创建时间
        private String alias;// String 短地址
        private int cid;// Number 商品分类的叶子类目id
        private int[] tag_ids;// Number[] 商品标签id列表
        private String detail_url;// String 适合wap应用的商品详情url
        private String share_url;// String 分享出去的商品详情url
        private String pic_url;// String 商品主图片地址
        private String pic_thumb_url;// String 商品主图片缩略图地址
        private int quantity;// Number 总库存
        private int sold_num;// Number 总销量
        private int price;// Number 价格(分)
        private int item_type;// Number 商品类型 0：普通商品 3：UMP降价拍 5：外卖商品 10：分销商品 20：会员卡商品 21：礼品卡商品 22：团购券 25：批发商品 30：收银台商品 31：知识付费商品 35：酒店商品 40：美业商品 60：虚拟商品 61：电子卡券
        private boolean is_listing;// Boolean 商品上架状态。true 为已上架，false 为已下架
        private boolean is_lock;// Boolean 商品是否锁定。true 为已锁定，false 为未锁定
        private String auto_listing_time;// String 商品定时上架（定时开售）的时间。没设置则为空
        private boolean join_level_discount;// Boolean 是否参加会员折扣
        private boolean purchase_right;// Boolean 是否设置商品购买权限
        private int post_type;// Number 运费类型
        private int post_fee;// Number 运费
        private String item_no;// String 商品货号（商家为商品设置的外部编号，可与商家外部系统对接）
        private ItemPreSaleOpenModel presale_extend;//  商品预售信息
        private FenxiaoExtendOpenModel fenxiao_extend;//  商品分销信息
        private ItemHotelOpenModel hotel_extend;//  商品酒店扩展信息
        private ItemVirtualOpenModel virtual_extend;//  虚拟商品扩展信息
        private DeliveryTemplateOpenModel delivery_template_info;//  运费模板信息
        private List<ItemSkuOpenModel> skus;//  商品规格库存信息
        private List<ItemImageOpenModel> item_imgs;//  图片信息
        private List<ItemGroupOpenModel> item_tags;//[] 分组信息
        private String messages;// String 商品留言
        private TemplateOpenModel template;//  商品详情模板信息
        private PurchaseRightOpenModel purchase_rightList;//  购买权限信息
        private List<SkuImageOpenModel> sku_images;// [] open api商品SKU图片模型
        private int num;// Number 商家排序字段
        private String sell_point;//  商品卖点


        public int getCost_price() {
            return cost_price;
        }

        public void setCost_price(int cost_price) {
            this.cost_price = cost_price;
        }

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getKdt_id() {
            return kdt_id;
        }

        public void setKdt_id(int kdt_id) {
            this.kdt_id = kdt_id;
        }

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

        public String getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(String origin_price) {
            this.origin_price = origin_price;
        }

        public int getBuy_quota() {
            return buy_quota;
        }

        public void setBuy_quota(int buy_quota) {
            this.buy_quota = buy_quota;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int[] getTag_ids() {
            return tag_ids;
        }

        public void setTag_ids(int[] tag_ids) {
            this.tag_ids = tag_ids;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public String getPic_thumb_url() {
            return pic_thumb_url;
        }

        public void setPic_thumb_url(String pic_thumb_url) {
            this.pic_thumb_url = pic_thumb_url;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getSold_num() {
            return sold_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
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

        public boolean isIs_listing() {
            return is_listing;
        }

        public void setIs_listing(boolean is_listing) {
            this.is_listing = is_listing;
        }

        public boolean isIs_lock() {
            return is_lock;
        }

        public void setIs_lock(boolean is_lock) {
            this.is_lock = is_lock;
        }

        public String getAuto_listing_time() {
            return auto_listing_time;
        }

        public void setAuto_listing_time(String auto_listing_time) {
            this.auto_listing_time = auto_listing_time;
        }

        public boolean isJoin_level_discount() {
            return join_level_discount;
        }

        public void setJoin_level_discount(boolean join_level_discount) {
            this.join_level_discount = join_level_discount;
        }

        public boolean isPurchase_right() {
            return purchase_right;
        }

        public void setPurchase_right(boolean purchase_right) {
            this.purchase_right = purchase_right;
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

        public String getItem_no() {
            return item_no;
        }

        public void setItem_no(String item_no) {
            this.item_no = item_no;
        }

        public ItemPreSaleOpenModel getPresale_extend() {
            return presale_extend;
        }

        public void setPresale_extend(ItemPreSaleOpenModel presale_extend) {
            this.presale_extend = presale_extend;
        }

        public FenxiaoExtendOpenModel getFenxiao_extend() {
            return fenxiao_extend;
        }

        public void setFenxiao_extend(FenxiaoExtendOpenModel fenxiao_extend) {
            this.fenxiao_extend = fenxiao_extend;
        }

        public ItemHotelOpenModel getHotel_extend() {
            return hotel_extend;
        }

        public void setHotel_extend(ItemHotelOpenModel hotel_extend) {
            this.hotel_extend = hotel_extend;
        }

        public ItemVirtualOpenModel getVirtual_extend() {
            return virtual_extend;
        }

        public void setVirtual_extend(ItemVirtualOpenModel virtual_extend) {
            this.virtual_extend = virtual_extend;
        }

        public DeliveryTemplateOpenModel getDelivery_template_info() {
            return delivery_template_info;
        }

        public void setDelivery_template_info(DeliveryTemplateOpenModel delivery_template_info) {
            this.delivery_template_info = delivery_template_info;
        }

        public List<ItemSkuOpenModel> getSkus() {
            return skus;
        }

        public void setSkus(List<ItemSkuOpenModel> skus) {
            this.skus = skus;
        }

        public List<ItemImageOpenModel> getItem_imgs() {
            return item_imgs;
        }

        public void setItem_imgs(List<ItemImageOpenModel> item_imgs) {
            this.item_imgs = item_imgs;
        }

        public List<ItemGroupOpenModel> getItem_tags() {
            return item_tags;
        }

        public void setItem_tags(List<ItemGroupOpenModel> item_tags) {
            this.item_tags = item_tags;
        }

        public String getMessages() {
            return messages;
        }

        public void setMessages(String messages) {
            this.messages = messages;
        }

        public TemplateOpenModel getTemplate() {
            return template;
        }

        public void setTemplate(TemplateOpenModel template) {
            this.template = template;
        }

        public PurchaseRightOpenModel getPurchase_rightList() {
            return purchase_rightList;
        }

        public void setPurchase_rightList(PurchaseRightOpenModel purchase_rightList) {
            this.purchase_rightList = purchase_rightList;
        }

        public List<SkuImageOpenModel> getSku_images() {
            return sku_images;
        }

        public void setSku_images(List<SkuImageOpenModel> sku_images) {
            this.sku_images = sku_images;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getSell_point() {
            return sell_point;
        }

        public void setSell_point(String sell_point) {
            this.sell_point = sell_point;
        }
    }

    /**
     * 商品预售信息
     */
    public static class ItemPreSaleOpenModel {
        private String presale_end;// 预售结束时间
        private String etd_start;//  发货开始时间
        private String etd_end;// String 发货结束时间
        private int etd_type;// Number 发货类型: 0, xxx 时间开始发货, 1, 付款 n 天后发货。
        private int etd_days;// Number 付款成功 后发货天数。

        public String getPresale_end() {
            return presale_end;
        }

        public void setPresale_end(String presale_end) {
            this.presale_end = presale_end;
        }

        public String getEtd_start() {
            return etd_start;
        }

        public void setEtd_start(String etd_start) {
            this.etd_start = etd_start;
        }

        public String getEtd_end() {
            return etd_end;
        }

        public void setEtd_end(String etd_end) {
            this.etd_end = etd_end;
        }

        public int getEtd_type() {
            return etd_type;
        }

        public void setEtd_type(int etd_type) {
            this.etd_type = etd_type;
        }

        public int getEtd_days() {
            return etd_days;
        }

        public void setEtd_days(int etd_days) {
            this.etd_days = etd_days;
        }
    }

    /**
     * 商品分销信息
     */
    public static class FenxiaoExtendOpenModel {
        private int supplier_kdt_id;// Number 供货店铺Id
        private int supplier_goods_id;// Number 供货商品Id

        public int getSupplier_kdt_id() {
            return supplier_kdt_id;
        }

        public void setSupplier_kdt_id(int supplier_kdt_id) {
            this.supplier_kdt_id = supplier_kdt_id;
        }

        public int getSupplier_goods_id() {
            return supplier_goods_id;
        }

        public void setSupplier_goods_id(int supplier_goods_id) {
            this.supplier_goods_id = supplier_goods_id;
        }
    }

    /**
     * 商品酒店扩展信息
     */
    public static class ItemHotelOpenModel {
        private String service_tel_code;// String 客服电话区号
        private String service_tel;// String 客服电话

        public String getService_tel_code() {
            return service_tel_code;
        }

        public void setService_tel_code(String service_tel_code) {
            this.service_tel_code = service_tel_code;
        }

        public String getService_tel() {
            return service_tel;
        }

        public void setService_tel(String service_tel) {
            this.service_tel = service_tel;
        }
    }

    /**
     * 虚拟商品扩展信息
     */
    public static class ItemVirtualOpenModel {
        private String item_validity_start;// String 虚拟商品有效期开始时间
        private String item_validity_end;// String 虚拟商品有效期结束时间
        private int effective_type;// Number 电子凭证生效类型，0 立即生效， 1 自定义推迟时间， 2 隔天生效
        private int effective_delay_hours;// Number 电子凭证自定义推迟时间
        private Boolean holidays_available;//  节假日是否可用

        public String getItem_validity_start() {
            return item_validity_start;
        }

        public void setItem_validity_start(String item_validity_start) {
            this.item_validity_start = item_validity_start;
        }

        public String getItem_validity_end() {
            return item_validity_end;
        }

        public void setItem_validity_end(String item_validity_end) {
            this.item_validity_end = item_validity_end;
        }

        public int getEffective_type() {
            return effective_type;
        }

        public void setEffective_type(int effective_type) {
            this.effective_type = effective_type;
        }

        public int getEffective_delay_hours() {
            return effective_delay_hours;
        }

        public void setEffective_delay_hours(int effective_delay_hours) {
            this.effective_delay_hours = effective_delay_hours;
        }

        public Boolean getHolidays_available() {
            return holidays_available;
        }

        public void setHolidays_available(Boolean holidays_available) {
            this.holidays_available = holidays_available;
        }
    }

    /**
     * 运费模板信息
     */
    public static class DeliveryTemplateOpenModel {
        private int delivery_template_id;// Number 运费模板ID
        private String delivery_template_fee;// String 运费的范围
        private String delivery_template_name;// String 运费模板名称
        private int delivery_template_valuationType;// Number 运费模版的计算类型，1 按件 2 按重量 3 按体积

        public int getDelivery_template_id() {
            return delivery_template_id;
        }

        public void setDelivery_template_id(int delivery_template_id) {
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

        public int getDelivery_template_valuationType() {
            return delivery_template_valuationType;
        }

        public void setDelivery_template_valuationType(int delivery_template_valuationType) {
            this.delivery_template_valuationType = delivery_template_valuationType;
        }
    }

    /**
     * 商品规格库存信息
     */
    public static class ItemSkuOpenModel {
        private int item_id;// Number 商品ID
        private int sku_id;// Number 规格ID
        private String sku_unique_code;// String 唯一编码，店铺Id和商品Id组合
        private String properties_name_json;// String Sku所对应的销售属性的Json字符串（需另行解析）。 格式定义： [ { "kid": "20000", "vid": "3275069", "k": "品牌", "v": "盈讯" }, { "kid": "1753146", "vid": "3485013", "k": "型号", "v": "F908" }
        private int with_hold_quantity;// Number 商品在付款减库存的状态下，该Sku上未付款的订单数量
        private int price;// Number 商品的这个Sku的价格，单位 分
        private String created;// String Sku创建日期，时间格式：yyyy-MM-dd HH:mm:ss
        private String modified;// String Sku最后修改日期，时间格式：yyyy-MM-dd HH:mm:ss
        private int quantity;// Number 属于这个Sku的商品的数量
        private String item_no;// String 商家编码（商家为Sku设置的外部编号）
        private int sold_num;// Number 属于这个Sku的销量
        private int cost_price;// Number 属于这个Sku的成本价

        public int getItem_id() {
            return item_id;
        }

        public void setItem_id(int item_id) {
            this.item_id = item_id;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getSku_unique_code() {
            return sku_unique_code;
        }

        public void setSku_unique_code(String sku_unique_code) {
            this.sku_unique_code = sku_unique_code;
        }

        public String getProperties_name_json() {
            return properties_name_json;
        }

        public void setProperties_name_json(String properties_name_json) {
            this.properties_name_json = properties_name_json;
        }

        public int getWith_hold_quantity() {
            return with_hold_quantity;
        }

        public void setWith_hold_quantity(int with_hold_quantity) {
            this.with_hold_quantity = with_hold_quantity;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getModified() {
            return modified;
        }

        public void setModified(String modified) {
            this.modified = modified;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getItem_no() {
            return item_no;
        }

        public void setItem_no(String item_no) {
            this.item_no = item_no;
        }

        public int getSold_num() {
            return sold_num;
        }

        public void setSold_num(int sold_num) {
            this.sold_num = sold_num;
        }

        public int getCost_price() {
            return cost_price;
        }

        public void setCost_price(int cost_price) {
            this.cost_price = cost_price;
        }
    }

    /**
     * 图片信息
     */
    public static class ItemImageOpenModel {
        private String url;// String 图片链接地址
        private String thumbnail;// String 图片缩略图链接地址
        private String medium;// String 中号大小图片链接地址
        private String combine;// String 组合图片链接地址
        private String created;// String 图片创建时间，时间格式：yyyy-MM-dd HH:mm:ss
        private int id;// Number;// 图片ID

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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    /**
     * 分组信息
     */
    public static class ItemGroupOpenModel {
        private int id;// Number id
        private int type;// Number 分组类型
        private String alias;// String 别名
        private String tag_url;// String 分组链接
        private String share_url;// String 分享链接
        private int item_num;// Number 商品数量
        private String created;// String 创建时间
        private String desc;// String 描述
        private String name;// String 分组名称

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTag_url() {
            return tag_url;
        }

        public void setTag_url(String tag_url) {
            this.tag_url = tag_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getItem_num() {
            return item_num;
        }

        public void setItem_num(int item_num) {
            this.item_num = item_num;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    /**
     * 商品详情模板信息
     */
    public static class TemplateOpenModel {
        private int template_id;// Number 模板ID
        private String template_title;// String 模板名称

        public int getTemplate_id() {
            return template_id;
        }

        public void setTemplate_id(int template_id) {
            this.template_id = template_id;
        }

        public String getTemplate_title() {
            return template_title;
        }

        public void setTemplate_title(String template_title) {
            this.template_title = template_title;
        }
    }


    /**
     * 购买权限信息
     */
    public static class PurchaseRightOpenModel {
        private String ump_tags;// String[] 可购买该商品的用户标签id列表
        private String ump_levels;// String[] 可购买该商品的会员等级id列表
        private String ump_tags_text;// String[]可购买该商品的用户标签名称列表
        private String ump_level_text;// String[]可购买该商品的会员等级名称列表

        public String getUmp_tags() {
            return ump_tags;
        }

        public void setUmp_tags(String ump_tags) {
            this.ump_tags = ump_tags;
        }

        public String getUmp_levels() {
            return ump_levels;
        }

        public void setUmp_levels(String ump_levels) {
            this.ump_levels = ump_levels;
        }

        public String getUmp_tags_text() {
            return ump_tags_text;
        }

        public void setUmp_tags_text(String ump_tags_text) {
            this.ump_tags_text = ump_tags_text;
        }

        public String getUmp_level_text() {
            return ump_level_text;
        }

        public void setUmp_level_text(String ump_level_text) {
            this.ump_level_text = ump_level_text;
        }
    }


    /**
     * open api商品SKU图片模型
     */
    public static class SkuImageOpenModel {
        private int v_id;// Number 规格值ID
        private int k_id;// Number 规格项ID，第一级规格项
        private String img_url;// String SKU图片链接

        public int getV_id() {
            return v_id;
        }

        public void setV_id(int v_id) {
            this.v_id = v_id;
        }

        public int getK_id() {
            return k_id;
        }

        public void setK_id(int k_id) {
            this.k_id = k_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }
    }
}
