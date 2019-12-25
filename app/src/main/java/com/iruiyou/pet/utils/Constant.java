package com.iruiyou.pet.utils;

/**
 * 类描述：
 * 作者：jiaopeirong on 2018/8/13 23:19
 * 邮箱：chinajpr@163.com
 */
public class Constant {
//    public static final String MCHID = "1556173081";
    public static final String WEIXIN_APP_ID = "wxf580fa050af6696b";
    public static final String WEIXIN_APP_SEC = "25eeceae3887a0522f7d9670aefc2ec2";
    public static final int SUCCESS = 0;
    public static final int TIPS1 = -1;
    public static final int POSITION = 0x00;//职位
    public static final int COMPANY = 0X01;//公司
    public static final int TIME = 0X02;//时间段
    public static final int UNIVERSITY = 0X03;//学校
    public static final int EDUCATION = 0X04;//学历
    public static final int  MAJOR= 0X05;//专业
    public static final int BLOCKDURATION = 0X06;//区块链从业时间
    public static final int BLOCKEXPE = 0X07;//区块链专长
    public static final int USERNAME = 0X08;//用户名
    public static final int SCHOOLNAME = 0X09;//学校名称
    public static final int ADDR = 0X10;//公司注册地
    public static final int PERSION = 0X11;//公司人数
    public static final int COMPANYSTYLE = 0X12;//公司人数
    public static final int WORKS = 0X13;//工作内容
    public static final int MYDESCRIBE = 0X14;//个人描述
    public static final int EXPERIENCE = 0X15;//在校经历
    public static final int PROFESSIONAL = 0X16;//在校经历
    public static final int PROFESSIONAL_TITLE_CDOE = 0X17;//职位头衔
    public static final int GENDENRS = 0X18; // 性别

    public static final String WORK = "WORK";
    public static final String BLOCK = "BLOCK";
    public static final String EDU = "EDU";
    public static final String BASIC = "BASIC";
    public static final String IDENTITY = "IDENTITY";
    public static final String WORKFLAG = "WORKFLAG";
    public static final String TITLE = "TITLE";
    public static final String DETAIL = "DETAIL";
    public static final String EXPERIENCES = "EXPERIENCE";

    /**
     * 标识工作经验、基本资料等标识
     */
    public static final String TIMES = "TIME";
    public static final String COMPANYS = "COMPANY";
    public static final String POSITIONS = "POSITION";
    public static final String PROFESSIONALS = "PROFESSIONAL";//职业身份
    public static final String USERNAMES = "USERNAME";
    public static final String COMPANYNAME = "COMPANYNAME";
    public static final String DESCRIBE = "DESCRIBE";
    public static final String SCHOOL = "SCHOOL";
    public static final String MAJORS = "MAJOR";
    public static final String POSITION_TITLE = "POSITION_TITLE";//职位头衔
    public static final String DURATION = "DURATION";

    public static final String MAILLIST = "MAILLIST2";
    public static final String MAILMAP = "MAILMAP2";
    public static final String PHONEMAP = "PHONEMAP";
    public static final int Chinese = 1;//中文
    public static final int English = 0;//英文

    /**
     * 用于 注册和忘记密码进入的标记
     */
    public static final String REGISTER = "REGISTER";
    public static final String FORGET = "FORGET";
    public static final String HAS_PWD_LOGIN = "HAS_PWD_LOGIN" ;
    /**
     * 用于 判断是否点调用切换头像功能
     */
    public static final String SWITCH_YES = "YES";
    public static final String SWITCH_NO = "NO";

    /**
     * 显示小数点的后五位数
     */
    public static final int SCALE_NUM = 3;
    public static final int SCALE_NUM_FOUR = 4;
    public static final int SCALE_NUM_Eight = 8;

    /**
     * 是否可以编辑
     */
    public static final String EDITORS = "EDITORS";

    public static final int TIPS0 = 0;

    /**
     * 融云消息类型
     */
    public static final String  PB_FAMSG= "PB:FaMsg";//申请好友的自定义消息
    public static final String PB_FDMSG = "PB:FdMsg";//已通过验证发消息
    public static final String PB_NEWFOLLOWERMSG = "PB:NewFollowerMsg";//我的粉丝提示消息

    /**
     * 融云targetid拼接-单聊 如：prod+90
     */
    public static final String TARGETID = "prod";//单聊测试id前缀  //dev  prod   local
    public static final String TARGETID_FORMAL = "prod";//单聊正式id前缀
    /**
     * 融云targetid拼接-群聊
     * 融云群组ID规则：开发环境localGroup4；测试环境devGroup4；正式环境prodGroup4
     */
    public static final String DEVGROUPID = "prodGroup";//测试群组id前缀-8080接口 //devGroup
    public static final String DEVGROUPID_FORMAL = "prodGroup";//正式群组id前缀
    public static final String DEVGROUPID_DEVE = "localGroup";//开发群组id前缀
    /**
     * 融云好友申请 and 通过验证
     */
    public static final String APPLY_VISIBLE = "Apply_visible";//好友申请显示红点
    public static final String APPLY_GONE = "Apply_gone";//隐藏红点
    public static final String FANS_VISIBLE = "Fans_visible";//粉丝通知显示红点
    public static final String FANS_GONE = "Fans_gone";//粉丝隐藏红点
    public static final String AGREE= "Agree";
    public static final String ADDED_VISIBLE= "added";//显示已添加
    public static final String KEYBOARD= "keyboard";//键盘隐藏

    public static final String IMG_LIST = "img_list"; //第几张图片-查看和删除
    public static final String IMG_LIST_SAVE = "imglist"; //第几张图片-查看和保存
    public static final String POSITION_PIC = "position_pic"; //第几张图片
    public static final String PIC_PATH = "pic_path"; //图片路径
    public static final int MAX_SELECT_PIC_NUM = 9; // 最多上传5张图片
    public static final int REQUEST_CODE_MAIN = 10; //请求码
    public static final int RESULT_CODE_VIEW_IMG = 11; //查看大图页面的结果码

    public static final String LARGE_SPACE = "\t\t"; //空格   Large space
    public static final String ONE_SPACE = " "; //空格   Large space
    public static final String CURRENY = "≈¥";//currency
    public static final String CURRENY2 = "¥";//currency
    public static final String ONE_HUNDRED = "100";//hundred
    public static final String ONE_HUNDRED2 = "100.0";
    public static final String BRACKETS_LEFT = "(";
    public static final String BRACKETS_RIGHT = ")";
    public static final String RMB = "RMB";
    public static final String USDT = "USDT";
    public static final String PERCENT_SIGN = "%";//Percent sign
    public static final String DAY = "天";
    public static final String BAR = "-";
    public static final String ADD = "+";

    public static final String PBS = "pbs";
    public static final String PBS1 = "PBS";
    public static final String DEPOSIT = "deposit";
    public static final String FUTURES = "futures";
    public static final String WALLET = "/wallet";
    public static final String TEAM = "/team";
    public static final String LOGININAPP = "/loginInApp";
    public static final String PROFIT1 = "/profit1";
    public static final String PROFIT2 = "/profit2";
    public static final String PROFIT3 = "/profit3";

    public static final String KAVEN = "kaven";
    public static final String PETS = "pets";
    public static final String NIYANG = "niyang";
    public static final String YUANYUAN = "yuanyuan";

    //融云key  本地：qd46yzrfqi82f  正式：kj7swf8ok1bc2
}
