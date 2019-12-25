package com.iruiyou.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.iruiyou.common.RyCommon;

/**
 * SharePreference存储
 *
 * @author jiao 2015.12.2
 */
public class SharePreferenceUtils {

    private static final String MSG = "msg";
    private SharedPreferences sharedPreferences;
    private static SharePreferenceUtils mBaseSharePreference;
    private static final String CONFIG_KEY="KEY_CINFIG";
    private static final String DATABASE_NAME = "BASE_PF";
    //params

    public static final String ACCOUNT_TYPE="ACCOUNT_TYPE";//提现的账号类型
    public static final String ALIPAY_ACCOUNT="ALIPAY_ACCOUNT";//支付宝账号
    public static final String ALIPAY_NAME="ALIPAY_NAME";//支付宝账号关联名称
    private static final String IS_SHOW_WELCOME = "IS_SHOW_WELCOME";// 是否第一次登陆
    private static final String SESSIONID = "SESSIONID";//sessionid
    private static final String USERID = "USERID";//userId
    private static final String BASICID = "BASICID";
    private static final String ACCOUNT = "ACCOUNT2";//account
    public static final String BQ_PHONE = "BQ_PHONE";//account
    private static final String USERCHANNEL = "USERCHANNEL";//userChannel
    private static final String LASTMARKTIME = "LASTMARKTIME";//userChannel
    private static final String DEVICEID = "DEVICEID";//deviceId
    private static final String PASSWORD = "PASSWORD2";//password
    private static final String MAINSENSORID = "MAINSENSORID";//mainSensorid
    public static final String BQ_CODE = "BQ_CODE";
    private static final String CURRENTSENSORID = "CURRENTSENSORID";//currentSensorid
    private static final String MAINSENSORNAME = "MAINSENSORNAME";//mainSensorName
    private static final String CURRENTSENSORNAME = "CURRENTSENSORNAME";//currentSensorName
    private static final String HOUSE_ID = "HOUSE_ID";//houseId
    private static final String ADCODE = "ADCODE";//adcode
    private static final String REALNAME = "REALNAME";//realname
    private static final String ADDR = "ADDR";//地址
    private static final String EMAIL = "EMAIL";//email
    private static final String PHONE = "PHONE";//phone
    private static final String DEVICETYPE = "DEVICETYPE";//devicetype
    private static final String USERINFO = "USERINFO";
    private static final String CRYSTAL_AMOUNT = "CRYSTAL_AMOUNT";
    private static final String TOKEN = "TOKEN";
    private static final String IMTOKEN = "IMTOKEN";
    private static final String YZTOKEN = "YZTOKEN";
    private static final String NickName = "NickName";
    private static final String PET = "PET";
    private static final String InviteCode = "InviteCode";
    private static final String InvitedCode = "InvitedCode";
    private static final String Resume = "Resume";
    private static final String LANGUAGE = "LANGUAGE";
    private static final String LANGUAGES = "LANGUAGES";
    private static final String POSITION = "POSITION";
    private static final String EDUCATION = "EDUCATION";
    private static final String PROFESS = "PROFESS";
    private static final String PEOPLE = "PEOPLE";
    private static final String SAVD = "SAVD";
    private static final String SAVD2 = "SAVD2";
    private static final String BASICCOUNT = "BASICCOUNT";
    private static final String EDU = "EDU";
    private static final String PHOTOCOUNT = "PHOTOCOUNT";
    private static final String WORKCOUNT = "WORKCOUNT";
    private static final String SHOWEDIT = "SHOWEDIT";
    private static final String CURRENCYTYPE = "CurrencyType";
    private static final String REFRESH = "REFRESH";
    private static final String ACCEPT = "ACCEPT";
    private static final String COMPANYID = "Companyid";
    private static final String CountryCode = "CountryCode";
    //五条搜索记录
    private static final String RECORD0 = "Record0";
    private static final String RECORD1 = "Record1";
    private static final String RECORD2 = "Record2";
    private static final String RECORD3 = "Record3";
    private static final String RECORD4 = "Record4";
    //会员等级
    private static final String VIPLEVEl = "VIPLEVEL";



    private static final String ApplicationCount = "ApplicationCount";



    private static final String LOGIN = "islogin";




    /*
    *保存登录状态
    * */

    public void saveState(boolean savelogin) {
        sharedPreferences.edit().putBoolean(LOGIN, savelogin).commit();

    }


    /*
    * 获取登录状态
    * */
    public boolean readlogin() {
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static SharePreferenceUtils getBaseSharePreference() {
        if (mBaseSharePreference == null) {
            synchronized (SharePreferenceUtils.class) {
                if (mBaseSharePreference == null) {
                    mBaseSharePreference = new SharePreferenceUtils();
                }
            }
        }
        return mBaseSharePreference;
    }

    /**
     * 私有化构造
     */
    private SharePreferenceUtils() {
        sharedPreferences = RyCommon.getInstance().getConfiguration().getContext().getSharedPreferences(DATABASE_NAME,
                Context.MODE_PRIVATE);
    }

    /**
     * 存储应用基本数据字典
     * @param dataStr
     */
    public void saveConfigData(String dataStr)
    {
        if(null!=dataStr&&(!"".equals(dataStr)))
        {
            sharedPreferences.edit().putString(CONFIG_KEY,dataStr).commit();
        }
    }

    /**
     * 获取应用数据字典
     * @return
     */
    public String getConfigData()
    {
        return sharedPreferences.getString(CONFIG_KEY,"");
    }

    /**
     * 第一次进入程序
     *
     * @param show
     */
    public void saveIsShowWelcome(String show) {
        sharedPreferences.edit().putString(IS_SHOW_WELCOME, show).commit();
    }

    public String readIsShowWelcome() {
        return sharedPreferences.getString(IS_SHOW_WELCOME, "1");
    }

    /**
     * sessionId
     */
    public void saveSessionId(String sessionId) {
        sharedPreferences.edit().putString(SESSIONID, sessionId).commit();
    }

    public String readSessionId() {
        return sharedPreferences.getString(SESSIONID, "");
    }

    /**
     * userId 用户的id
     */
    public void saveUserId(String userId) {
        sharedPreferences.edit().putString(USERID, userId).commit();
    }

    public String readUserId() {
        return sharedPreferences.getString(USERID, "");
    }

    public int readApplicationCount() {
        return sharedPreferences.getInt(ApplicationCount,0);
    }
    public void saveApplicationCount(int applicationCount) {
        sharedPreferences.edit().putInt(ApplicationCount,applicationCount).commit();
    }



    public void saveVipLevel(int level) {
        sharedPreferences.edit().putInt(VIPLEVEl, level).commit();
    }
    public int readVipLevel() {
        return sharedPreferences.getInt(VIPLEVEl, 0);
    }

    /**
     * userId 用户资料的id
     */
    public void saveBasicId(String basicid) {
        sharedPreferences.edit().putString(BASICID, basicid).commit();
    }

    public String readBasicId() {
        return sharedPreferences.getString(BASICID, "");
    }
    /**
     * PET
     */
    public void savePetUrl(String pet) {
        sharedPreferences.edit().putString(PET, pet).commit();
    }

    public String readPetUrl() {
        return sharedPreferences.getString(PET, "");
    }

    public void  saveInvitedCode(String invitedCode){
        sharedPreferences.edit().putString(InvitedCode, invitedCode).commit();
    }

    public String readInvitedCode() {
        return sharedPreferences.getString(InvitedCode, "");
    }

    public void saveInviteCode(String pet) {
        sharedPreferences.edit().putString(InviteCode, pet).commit();
    }

    public String readInviteCode() {
        return sharedPreferences.getString(InviteCode, "");
    }


    /**
     * houseId
     */
    public void saveHouseId(int houseId) {
        sharedPreferences.edit().putInt(HOUSE_ID, houseId).commit();
    }

    public int readHouseId() {
        return sharedPreferences.getInt(HOUSE_ID, 0);
    }


    public int readTiXianAccountType()
    {
        return sharedPreferences.getInt(ACCOUNT_TYPE,0);
    }

    public void saveTiXianAccountType(int type)
    {
        sharedPreferences.edit().putInt(ACCOUNT_TYPE,type).commit();
    }

    public void saveAliPayAccount(String alipayAccount)
    {
        sharedPreferences.edit().putString(ALIPAY_ACCOUNT, alipayAccount).commit();
    }

    public String readAlipayAccount()
    {
        return sharedPreferences.getString(ALIPAY_ACCOUNT, "");
    }

    public void saveAlipayUserName(String alipayUserName)
    {
        sharedPreferences.edit().putString(ALIPAY_NAME, alipayUserName).commit();
    }

    public String readAlipayName()
    {
        return sharedPreferences.getString(ALIPAY_NAME, "");
    }

    /**
     * account
     */
    public void saveAccount(String userId) {
        sharedPreferences.edit().putString(ACCOUNT, userId).commit();
    }

    public String readAccount() {
        return sharedPreferences.getString(ACCOUNT, "");
    }

    //保存币全手机号
    public String readBqPhone() {
        return sharedPreferences.getString(BQ_PHONE, "");
    }
    public void saveBqPhone(String userId) {
        sharedPreferences.edit().putString(BQ_PHONE, userId).commit();
    }

    /**
     * 保存币全界面的国家码
     */
    public void saveBqCode(String sensorId) {
        sharedPreferences.edit().putString(BQ_CODE, sensorId).commit();
    }

    public String readBqCode() {
        return sharedPreferences.getString(BQ_CODE, "86");
    }

    public long readLastMarkTime(){
        return sharedPreferences.getLong(LASTMARKTIME,0);
    }

    public void saveLastMarkTime(long lastMarkTime){
        sharedPreferences.edit().putLong(LASTMARKTIME, lastMarkTime).commit();
    }

    public void saveUserChannel(String userChannel) {
        sharedPreferences.edit().putString(USERCHANNEL, userChannel).commit();
    }

    public String readUserChannel() {
        return sharedPreferences.getString(USERCHANNEL, "");
    }

    public void saveResume(String resume) {
        sharedPreferences.edit().putString(Resume, resume).commit();
    }

    public String readResume() {
        return sharedPreferences.getString(Resume, "");
    }

    /**
     * Nickname
     */
    public void saveNickName(String nickName) {
        sharedPreferences.edit().putString(NickName, nickName).commit();
    }

    public String readNickName() {
        return sharedPreferences.getString(NickName, "");
    }

    /**
     * password
     */
    public void savePassword(String password) {
        sharedPreferences.edit().putString(PASSWORD, password).commit();
    }

    public String readPassword() {
        return sharedPreferences.getString(PASSWORD, "");
    }

    /**
     * adcode
     */
    public void saveConfit(String adcode) {
        sharedPreferences.edit().putString(ADCODE, adcode).commit();
    }

    public String readConfig() {
        return sharedPreferences.getString(ADCODE, "");
    }

    public void saveCountryCode(String countryCode) {
        sharedPreferences.edit().putString(CountryCode, countryCode).commit();
    }

    public String readCountryCode() {
        return sharedPreferences.getString(CountryCode, "");
    }

    /**
     * deviceId
     */
    public void saveDeviceId(int deviceId) {
        sharedPreferences.edit().putInt(DEVICEID, deviceId).commit();
    }

    public int readDeviceId() {
        return sharedPreferences.getInt(DEVICEID, 0);
    }

    /**
     * mainSensorid
     */
    public void saveMainSensorId(int sensorId) {
        sharedPreferences.edit().putInt(MAINSENSORID, sensorId).commit();
    }

    public int readMainSensorId() {
        return sharedPreferences.getInt(MAINSENSORID, 0);
    }

    /**
     * currentSensorid
     */
    public void saveCurrentSensorId(int currentSensorId) {
        sharedPreferences.edit().putInt(CURRENTSENSORID, currentSensorId).commit();
    }

    public int readCurrentSensorId() {
        return sharedPreferences.getInt(CURRENTSENSORID, 0);
    }

    /**
     * mainSensorName
     */
    public void saveMainSensorName(String mainSensorName) {
        sharedPreferences.edit().putString(MAINSENSORNAME, mainSensorName).commit();
    }

    public String readMainSensorName() {
        return sharedPreferences.getString(MAINSENSORNAME, "");
    }

    /**
     * currentSensorName
     */
    public void saveCurrentSensorName(String currentSensorName) {
        sharedPreferences.edit().putString(CURRENTSENSORNAME, currentSensorName).commit();
    }

    public String readCurrentSensorName() {
        return sharedPreferences.getString(CURRENTSENSORNAME, "未知采集器");
    }

    /**
     * realname
     */
    public void saveRealname(String realname) {
        sharedPreferences.edit().putString(REALNAME, realname).commit();
    }

    public String readRealname() {
        return sharedPreferences.getString(REALNAME, "");
    }

    /**
     * addr
     */
    public void saveAddr(String addr) {
        sharedPreferences.edit().putString(ADDR, addr).commit();
    }

    public String readAddr() {
        return sharedPreferences.getString(ADDR, "");
    }

    /**
     * email
     */
    public void saveEmail(String email) {
        sharedPreferences.edit().putString(EMAIL, email).commit();
    }

    public String readEmail() {
        return sharedPreferences.getString(EMAIL, "");
    }

    /**
     * phone
     */
//    public void savePhone(String phone) {
//        sharedPreferences.edit().putString(PHONE, phone).commit();
//    }
//
//    public String readPhone() {
//        return sharedPreferences.getString(PHONE, "");
//    }

    /**
     * TOKEN
     *
     * @param
     */
    public void saveToken(String token) {
        sharedPreferences.edit().putString(TOKEN, token).commit();
    }

    public String readToken() {
        return sharedPreferences.getString(TOKEN, "");
    }

    /**
     * 融云TOKEN
     *
     * @param
     */
    public void saveIMToken(String token) {
        sharedPreferences.edit().putString(IMTOKEN, token).commit();
    }

    public String readIMToken() {
        return sharedPreferences.getString(IMTOKEN, "");
    }

    /**
     * 有赞 Token
     *
     * @param
     */
    public void saveYZToken(String token) {
        sharedPreferences.edit().putString(YZTOKEN, token).commit();
    }

    public String readYZToken() {
        return sharedPreferences.getString(YZTOKEN, "");
    }

    /**
     * Record0
     */
    public void saveRecord0(String Record0) {
        sharedPreferences.edit().putString(RECORD0, Record0).commit();
    }

    public String readRecord0() {
        return sharedPreferences.getString(RECORD0, "");
    }

    /**
     * Record1
     */
    public void saveRecord1(String Record1) {
        sharedPreferences.edit().putString(RECORD1, Record1).commit();
    }

    public String readRecord1() {
        return sharedPreferences.getString(RECORD1, "");
    }

    /**
     * Record2
     */
    public void saveRecord2(String Record2) {
        sharedPreferences.edit().putString(RECORD2, Record2).commit();
    }

    public String readRecord2() {
        return sharedPreferences.getString(RECORD2, "");
    }

    /**
     * Record3
     */
    public void saveRecord3(String Record3) {
        sharedPreferences.edit().putString(RECORD3, Record3).commit();
    }

    public String readRecord3() {
        return sharedPreferences.getString(RECORD3, "");
    }

    /**
     * Record4
     */
    public void saveRecord4(String Record4) {
        sharedPreferences.edit().putString(RECORD4, Record4).commit();
    }

    public String readRecord4() {
        return sharedPreferences.getString(RECORD4, "");
    }

    /**
     * DEVICETYPE
     */
    public void saveDevicetype(String devicetype) {
        sharedPreferences.edit().putString(DEVICETYPE, devicetype).commit();
    }

    public String readDevicetype() {
        return sharedPreferences.getString(DEVICETYPE, "");
    }

    public void saveLanguage(String language) {
        sharedPreferences.edit().putString(LANGUAGE, language).commit();
    }

    public String readLanguage() {
        return sharedPreferences.getString(LANGUAGE, "zh");
    }

    /**
     * 设置多语言
     * @param type
     */
    public void saveLanguageType(int type) {
        sharedPreferences.edit().putInt(LANGUAGES, type).commit();
    }

    public int readLanguageType() {
        return sharedPreferences.getInt(LANGUAGES, 1);
    }
    /**
     * 设置职业身份
     * @param type
     */
    public void savePositionType(int type) {
        sharedPreferences.edit().putInt(POSITION, type).commit();
    }

    public int readPositionType() {
        return sharedPreferences.getInt(POSITION, -1);
    }

    /**
     * 设置学历
     * @param type
     */
    public void saveEducationType(int type) {
        sharedPreferences.edit().putInt(EDUCATION, type).commit();
    }

    public int readEducationType() {
        return sharedPreferences.getInt(EDUCATION, 0);
    }

    /**
     * 设置公司人数
     * @param type
     */
    public void savePeopleType(int type) {
        sharedPreferences.edit().putInt(PEOPLE, type).commit();
    }

    public int readPeopleType() {
        return sharedPreferences.getInt(PEOPLE, 0);
    }
    /**
     * 设置保存状态
     * @param type
     */
    public void saveType(int type) {
        sharedPreferences.edit().putInt(SAVD, type).commit();
    }

    public int readType() {
        return sharedPreferences.getInt(SAVD, -1);
    }
    /**
     * 设置保存状态2
     * @param type
     */
    public void saveType2(int type) {
        sharedPreferences.edit().putInt(SAVD2, type).commit();
    }

    public int readType2() {
        return sharedPreferences.getInt(SAVD2, -1);
    }

    /**
     * 设置保存基本资料状态
     * @param type
     */
    public void saveBasicCount(int type) {
        sharedPreferences.edit().putInt(BASICCOUNT, type).commit();
    }

    public int readBasicCount() {
        return sharedPreferences.getInt(BASICCOUNT, 0);
    }
    /**
     * 设置保存教育状态
     * @param type
     */
    public void saveEDU(int type) {
        sharedPreferences.edit().putInt(EDU, type).commit();
    }

    public int readEDU() {
        return sharedPreferences.getInt(EDU, 0);
    }
    /**
     * 设置保存职业照状态
     * @param type
     */
    public void savePhotoCount(int type) {
        sharedPreferences.edit().putInt(PHOTOCOUNT, type).commit();
    }

    public int readPhotoCount() {
        return sharedPreferences.getInt(PHOTOCOUNT, 0);
    }
    /**
     * 设置保存职业照状态
     * @param type
     */
    public void saveWorkCount(int type) {
        sharedPreferences.edit().putInt(WORKCOUNT, type).commit();
    }

    public int readWorkCount() {
        return sharedPreferences.getInt(WORKCOUNT, 0);
    }

    /**
     * 设置进入资料填写的判断值
     * @param type
     */
    public void saveShowEdit(int type) {
        sharedPreferences.edit().putInt(SHOWEDIT, type).commit();
    }

    public int readShowEdit() {
        return sharedPreferences.getInt(SHOWEDIT, -1);
    }

    /**
     * 设置首页的币图标的状态颜色
     * @param type
     */
    public void saveCurrencyType(int type) {
        sharedPreferences.edit().putInt(CURRENCYTYPE, type).commit();
    }

    public int readCurrencyType() {
        return sharedPreferences.getInt(CURRENCYTYPE, 0);
    }

    /**
     * 设置通讯录是否刷新
     * @param type
     */
    public void savePhoneRefresh(int type) {
        sharedPreferences.edit().putInt(REFRESH, type).commit();
    }

    public int readPhoneRefresh() {
        return sharedPreferences.getInt(REFRESH, 0);
    }

    /**
     * 是否接受
     * @param accept
     */
    public void saveAccept(int accept) {
        sharedPreferences.edit().putInt(ACCEPT, accept).commit();
    }

    public int readAccept() {
        return sharedPreferences.getInt(ACCEPT, 0);
    }

    /**
     * 设置搜索前选中的职位的索引值
     * @param type
     */
    public void saveProfessionalIdentity(int type) {
        sharedPreferences.edit().putInt(PROFESS, type).commit();
    }

    public int readProfessionalIdentity() {
        return sharedPreferences.getInt(PROFESS, -1);
    }

    /**
     * 保存首页的UserInfo对象的数据供其他的界面使用
     */
    public void saveUserInfo(String devicetype) {
        sharedPreferences.edit().putString(USERINFO, devicetype).commit();
    }

    public String readUserInfo() {
        return sharedPreferences.getString(USERINFO, "");
    }

    /**
     * 保存钱包水晶数
     */
    public void saveCrystalAmount(String crystalAmount) {
        sharedPreferences.edit().putString(CRYSTAL_AMOUNT, crystalAmount).commit();
    }

    public String readCrystalAmount() {
        return sharedPreferences.getString(CRYSTAL_AMOUNT, "");
    }

    /**
     * 公司id  单聊0与群聊的去区分
     * @param companyid
     */
    public void saveCompanyid(int companyid) {
        sharedPreferences.edit().putInt(COMPANYID, companyid).commit();
    }

    public int readCompanyid() {
        return sharedPreferences.getInt(COMPANYID, 0);
    }

    public void saveMeg(int i) {
        sharedPreferences.edit().putInt(MSG, i).commit();
    }
    public int readMsg() {
        return sharedPreferences.getInt(MSG, 0);
    }
}
