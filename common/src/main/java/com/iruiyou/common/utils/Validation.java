package com.iruiyou.common.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验密码、手机号、验证码的正则
 *
 * @author jiao 2015.12.11
 */
public class Validation {
    //手机号
//    public static final String phoneNum = "^((13[0-9])|(16[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
    public static final String phoneNum = "^[1][3,4,5,7,8][0-9]{9}$";
    //密码
    public static final String password = "^[a-zA-Z0-9]{6,12}$";
    //用户名
    public static final String userName = "^[\\w\\u4e00-\\u9fa5]{3,20}$";
    //Email
    public static final String emails = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    //验证码
    public static final String captchas = "^[0-9A-Za-z]{4,6}$";
    //银行卡号
    public static final String bankCards = "^(\\d{16}|\\d{19})$";
    //身份证号
    public static final String idCards = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

    /**
     * 验证手机号
     *
     * @param str 手机号码
     * @return true--正确，false--错误
     */
    public static boolean isPhoneNum(String str) {
        Pattern p = Pattern
                .compile(phoneNum);

        Matcher m = p.matcher(str);

        return m.matches();
    }

    /**
     * 验证密码
     *
     * @param str 密码
     * @return true--正确，false--错误
     */
    public static boolean isPassword(String str) {
        Pattern p = Pattern.compile(password);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证用户名
     *
     * @param str 用户名
     * @return true--正确，false--错误
     */
    public static boolean isUsername(String str) {
        String patternStr = userName;
        Pattern p = Pattern.compile(patternStr);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 验证邮箱
     *
     * @param email 邮箱
     * @return true--正确，false--错误
     */
    public static boolean isEmail(String email) {
        String str = emails;
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 验证验证码
     *
     * @param captcha 验证码
     * @return true--正确，false--错误
     */
    public static boolean isCaptcha(String captcha) {
        Pattern p = Pattern.compile(captchas);
        Matcher m = p.matcher(captcha);
        return m.matches();
    }

    /**
     * 验证银行卡号
     *
     * @return
     */
    public static boolean isBankCard(String bankCard) {
        Pattern compile = Pattern.compile(bankCards);
        Matcher matcher = compile.matcher(bankCard);
        return matcher.matches();
    }

    /**
     * 验证身份证号
     *
     * @param idCard
     * @return
     */
    public static boolean isIdCard(String idCard) {
        Pattern compile = Pattern.compile(idCards);
        Matcher matcher = compile.matcher(idCard);
        return matcher.matches();

    }
}
