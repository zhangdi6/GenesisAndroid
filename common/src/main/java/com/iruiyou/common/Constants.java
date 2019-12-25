/*
 * Copyright (c) 2015. iruiyou.com
 *
 * CreatedBy: zhangpeng
 * CreatedDate:  2015/12/3
 */

package com.iruiyou.common;

/**
 * 平台用常量
 */
public final class Constants {
    /**
     * 用户类型
     */
    public enum UserTypes {
        //一般用户
        COMMON(0),
        //会员
        MEMBERSHIP(1),
        //商铺
        SHOP(2),
        //出租车司机
        TAXI_DRIVER(3);

        private int type;
        UserTypes(int type){
            this.type = type;
        }
    }

    /**
     * Http通信方法
     */
    public enum HttpMethods{
        GET,
        POST,
        PUT,
        HEAD,
        DELETE,
    }

    /**
     * 错误码
     */
    public enum ErrorCodes {
        //成功
        SUCCESS(0),
        //失败
        FAILED(1),
        //取消
        CANCELLED(2),
        //参数为空
        PARAMETER_EMPTY(3),
        //参数错误
        PARAMETER_ERROR(4),
        //密码格式不对
        PASSWORD_FORMAT_ERROR(5),
        //手机号码格式不对
        MOBILE_FORMAT_ERROR(6),
        //操作太频繁
        TOO_FREQUENTLY(7),
        //用户未登录
        NOT_LOGIN(8),
        //已到最后一页
        REACH_END(9),

        //用户验证失败
        SESSION_VERIFY_FAILED(4111),

        //断开连接
        NET_DISCONNECT(20001),
        //网络数据格式不对
        NET_DATA_ERROR(20002),
        //网络异常
        NET_FAULT(20003),
        //校验失败
        NET_VERIFY_FAILED(20004);

        private int error;
        ErrorCodes(int error){
            this.error = error;
        }
    }

    /**
     * 服务端api
     */
    public enum Services {
        //登陆
        USER_LOGIN("User/login"),
        //注销
        USER_LOGOUT("User/logout"),
        //验证token
        USER_CHECK_TOKEN("User/check3rdToken"),
        //获取用户信息
        USER_GET_INFO("User/get"),
        //获取指定用户信息
        USER_GET_USER_INFO("User/getuserinfo"),
        USER_SEARCH_BY_NICK("User/searchUsers"),
        USER_GET_USERS("User/getUsers"),
        //请求验证码
        USER_SEND_SMS("User/sendsms"),
        //验证验证码
        USER_VERIFY_SMS("User/checkPhoneCode"),
        //注册用户
        USER_REG("User/register"),
        //修改密码
        USER_MODIFY_PWD("User/modifypass"),
        //修改头像
        USER_MODIFY_AVATAR("User/modifyhead"),
        //修改个人主页背景
        USER_MODIFY_BG("User/modifybgphoto"),
        //修改昵称
        USER_MODIFY_NICK("User/modifynick"),
        //修改个人签名
        USER_MODIFY_SIGNATURE("User/modifypersonalsignature"),
        //修改性别
        USER_MODIFY_GENDER("User/modifygender"),
        //获取联系人列表
        USER_CONTACT_LIST("getcontacts"),
        //编辑或添加联系人
        USER_CONTACT_EDIT("editcontact"),
        //删除联系人
        USER_CONTACT_DELETE("deletecontact"),

        //添加好友
        USER_ADD_FRIEND("User/addFriends"),
        //删除好友
        USER_DELETE_FRIEND("User/deletefriends"),
        //同步和获取好友信息
        USER_SYNC_FRIENDS("User/syncandgetfriends"),
        //获取好友信息
        USER_GET_FRIENDS("User/getFriends"),
        //更改好友状态
        USER_UPDATE_FRIEND("User/changeFriendStatus"),

        USER_UPLOAD_DEVICE("User/uploaddevice"),

        USER_SETTING_MODIFY("User/modifyUserSettting"),
        USER_SETTING_GET("User/getUserSetting"),

        USER_REPORT_LOCATION("BaiduLbs/updateUserLocation"),

        //////////////////////社区////////////////////;
        //发帖
        NEWS_POST("Community/postnews"),
        //回帖
        NEWS_REPLY("Community/replynews"),
        //点赞
        NEWS_LIKE("Community/likenews"),
        //删除
        NEWS_DELETE("Community/delnews"),
        //删除评论
        NEWS_DELETE_REPLY("Community/delreply"),
        //收藏
        NEWS_COLLECT("Community/collectnews"),
        //取消收藏
        NEWS_UNCOLLECT("Community/uncollectnews"),
        //举报
        NEWS_REPORT("Community/reportnews"),
        //热帖
        NEWS_LIST_HOT("Community/gethottestnews"),
        //最新
        NEWS_LIST_LATEST("Community/getlatestnews"),
        //好友帖子列表
        NEWS_LIST_FRIENDS("Community/getfriendnews"),
        //收藏的帖子列表
        NEWS_LIST_COLLECT("Community/getcollectnews"),
        //回帖列表
        NEWS_LIST_REPLIED("Community/getnewsreply"),
        //用户帖子列表
        NEWS_LIST_USER("Community/getusernews"),
        //查找帖子
        NEWS_SEARCH("Community/searchNews"),

        //获取最新版本信息
        APP_GET_VERSION("Index/version");

        public String getValue(){
            return value;
        }

        private String value;
        Services(String value){
            this.value = value;
        }
    }

    /**
     * 账号管理渠道
     */
    public enum UserPlatforms{
        RY(1),
        QQ(2),
        WEIXIN(3),
        SINA_WEIBO(4);

        private int value;
        public int getValue() {return this.value;}
        UserPlatforms(int value) {
            this.value = value;
        }
    }

    /**
     * 分享平台
     */
    public enum SharePlatforms {
        //微信好友
        WEIXIN,
        //微信朋友圈
        WEIXIN_PENGYOU,
        //QQ好友
        QQ,
        //QQ空间
        QQZONE,
        //腾讯微博
        TENCENT_WEIBO,
        //新浪微博
        SINA_WEIBO,
        //人人网
        RENREN,
        //短信
        SMS,
        //电子邮件
        EMAIL,
    }

    /**
     * 用户性别
     */
    public enum Genders {
        //未知  0
        UNKNOWN,
        //男  1
        MALE,
        //女  2
        FEMALE,
    }

    /**
     * 短信验证码类型
     */
    public enum SmsTypes {
        //注册
        REG,
        //更改密码
        MODIFY_PWD,
    }


    /////////下面定义属性字段/////////
    //密码
    public static final String PROPERTY_PWD = "passwd";
    //用户id
    public static final String PROPERTY_USER_ID = "userid";
    //用户名
    public static final String PROPERTY_USER_NAME = "loginname";

    //登录类型
    public static final String PROPERTY_LOGIN_TYPE = "logintype";
    //令牌
    public static final String PROPERTY_TOKEN = "sessionid";
    //过期时间
    public static final String PROPERTY_EXPIRES = "expires";

    //第三方用户
    public static final String PROPERTY_3RD_USER_ID = "ptuserid";

    //用户类型
    public static final String PROPERTY_USER_TYPE = "utype";

    //用户手机号
    public static final String PROPERTY_MOBILE = "phone";

    //短信验证码类型
    public static final String PROPERTY_SMS_TYPE = "chktype";

    //短信验证码字段
    public static final String PROPERTY_SMS_CODE = "code";

    public static final String PROPERTY_SCORE = "score";
    //用户头像
    public static final String PROPERTY_AVATAR = "image";
    // 激活码
    public static final String PROPERTY_CODE = "code";

    //用户昵称
    public static final String PROPERTY_NICK = "nickname";

    //性别
    public static final String PROPERTY_GENDER = "gender";

    //身份证号
    public static final String PROPERTY_ID_NUMBER = "idnumber";

    //真实名字
    public static final String PROPERTY_REAL_NAME = "realname";

    //经验或积分
    public static final String PROPERTY_EXP = "exp";

    //id
    public static final String PROPERTY_ID = "id";

    //姓名
    public static final String PROPERTY_NAME = "name";

    //个性签名
    public static final String PROPERTY_PERSONAL_SIGNATURE = "person_signature";

    //省份
    public static final String PROPERTY_PROVINCE = "province";

    //城市
    public static final String PROPERTY_CITY = "city";

    //区/县
    public static final String PROPERTY_COUNTY = "county";

    //邮编
    public static final String PROPERTY_ZIP_CODE = "zipcode";

    //详细地址
    public static final String PROPERTY_ADDRESS = "address";

    //联系人
    public static final String PROPERTY_CONTACT = "contact";

    //邀请码
    public static final String PROPERTY_INVITE_CODE = "invitecode";

    //图片类型
    public static final String PROPERTY_IMAGE_TYPE = "imagetype";

    //背景图片
    public static final String PROPERTY_BG_IMAGE = "background";

    //设备
    public static final String PROPERTY_DEVICE = "device";

    public static final String SET_MSG_SOUND = "notify_sound";
    public static final String SET_MSG_VIBRATE = "notify_shake";
    public static final String SET_ALLOW_CONTROL = "control_state";
    public static final String SET_SHOW_DEVICE = "hardware_show";

    //日志标签
    public static final String LOG_TAG = "U9_USER";
    //应用的db名称
    public static final String DATABASE = "u9_user.db";
    //默认使用UTF-8编码
    public static final String ENCODING = "UTF-8";

    //app key参数名
    public static final String APP_PARA_KEY = "app_key";
    //app id参数名
    public static final String APP_PARA_ID = "app_id";
    //app scope参数名
    public static final String APP_PARA_SCOPE = "scope";
    //app redirect url参数名
    public static final String APP_PARA_REDIRECT_URL = "redirect_url";

    //密码最低位数
    public static final int PASSWORD_MIN_LENGTH = 6;
    //密码最大位数
    public static final int PASSWORD_MAX_LENGTH = 12;

    //请求发送短信最小时间间隔60秒
    public static final int SEND_SMS_INTERVAL = 60;

    //短信验证码类型-注册
    public static final int SMS_TYPE_REG = 0;

    //比较器表示小于
    public static final int COMPARE_LT = -1;
    //比较器表示大于
    public static final int COMPARE_GT = 1;
    //比较器表示等于
    public static final int COMPARE_EQ = 0;

    //钱币的最低精度为0.01分
    public static final double MONEY_PRECISION = 0.0001;

    //帖子最多字数
    public static final int NEWS_MAX_LENGTH = 120;

    //umeng
    public static final String UM_DESCRIPTOR_LOGIN = "com.umeng.login";
    public static final String UM_DESCRIPTOR_SHARE = "com.umeng.share";
}
