package com.iruiyou.pet.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.iruiyou.http.retrofit_rx.utils.L;
import com.iruiyou.pet.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;

/**
 *  sgf 字符串有关工具栏
 */
public class StringUtil {

    private static int counter = 0;
    private static final String TAG = "StringUtil";
    private static final Map<String, Integer> retMap = new HashMap<>();

    public static boolean isEmpty(String str) {
        return null == str || str.trim().equals("");
    }

    public static boolean isNotEmpty(String str) {
        return null != str &&(!str.trim().equals(""));
    }

    public static boolean isEmptyMoney(String str) {
        try {
            if (null == str || str.equals("") || str.equals("0")
                    || str.equals("0.00") || 0 == Double.parseDouble(str)) {
                return true;
            }

            if (Double.parseDouble(str) == 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // String five_before = hitlistsBean.getHit_five().substring(0, hitlistsBean.getHit_five().indexOf(":"));
    //String five_after = hitlistsBean.getHit_five().substring(hitlistsBean.getHit_five().indexOf(":") + 1, hitlistsBean.getHit_five().length());

    /**
     * 截取字符串的前半截
     *
     * @param str
     * @return
     */
    public static String subStringBefore(String str) {
        String beforeStr = str.substring(0, str.indexOf(":"));
        return beforeStr;
    }

    /**
     * 截取字符串的后半截
     *
     * @param str
     * @return
     */
    public static String subStringAfter(String str) {
        String afterStr = str.substring(str.indexOf(":") + 1);
        return afterStr;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_one(String str) {
        String StrArray[] = str.split(":");

        return StrArray;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_two(String str) {
        String[] StrArray = str.split(",");
        return StrArray;
    }

    /**
     * 截取"：" 字符的字符串数组
     *
     * @param str
     * @return 返回一个数组
     */
    public static String[] subStringArray_three(String str) {
        String[] StrArray = subStringArray_two(str);
        //for(){}
        return StrArray;
    }

    /**
     * 判断str1中包含str2的个数
     *
     * @param str1
     * @param str2
     * @return counter
     */
    public static int countStr(char str1, String str2) {

        int countss = 0;
        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) == str1) {
                countss++;
            }
        }

        return countss;
    }

    /**
     * 设置小数点前8后4的显示，如：99999999.9999
     * 设置输入框输入限制 正则[0-9]表示可以输入0到9的数字，{0,8}表示可以输入0-8位整数  {0,4}表示小数点后面设置四位数
     * @param str
     * @return
     */
    public static boolean determineValue_8_4(String str){
        String regex = "^[0-9]{0,20}(\\.[0-9]{0,4})?$";

        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(str).matches();
    }

    /**
     * 处理输入框中输入两个小数点的时候，自动校正为一个，超过两个的话自动回复默认值
     * @param str
     * @return
     */
    public static String calibrationValue(String str){

        if(str.startsWith("."))str = "0"+str;

        String regex2 = "^[0-9]+\\.[0-9]*\\.\\d*$";
        Pattern pattern2 = Pattern.compile(regex2);
        if(pattern2.matcher(str).matches())str = str.substring(0,str.length()-1);

        str = str.replaceAll("^[0-9]+\\.[0-9]*\\.[.0-9]*$","0");//小数点超过两个时替换为0
        return str;
    }


    /**
     * 截取()中的字符串
     *
     * @param str2
     * @return counter
     */
    public static String countStrParentheses(String str2) {
        // TODO SGF ADD
        String str1 = "";
        Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
        Matcher matcher = pattern.matcher(str2);
        while (matcher.find()) {
            str1 = matcher.group();
        }

        return str1;
    }

    /**
     * 得到10的18次方的String数值
     *
     * @return str1
     */
    public static String numStr_10_18() {
        // TODO SGF ADD
        String str1 = "";
        Double token_amount_num = Math.pow(10, 18);//10的18次方
        str1 = token_amount_num.toString();
        return str1;
    }
    /**
     * double保留后四位小数
     *
     * @return str1
     */
    public static double doubleTofour(double a) {
        // TODO SGF ADD
        BigDecimal bg = new BigDecimal(a);
        double f1 = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }
    /**
     * double保留后四位小数
     *
     * @return str1
     */
    public static String stringTofour(double a) {
        // TODO SGF ADD
        String format = "";
        BigDecimal bg = new BigDecimal(a);//当double小于0.001时会显示2.0E-4之类的数据，这时需要下面的转化了
        DecimalFormat dec = new DecimalFormat("0.0000");
        bg.setScale(4,BigDecimal.ROUND_DOWN);
        format = dec.format(bg);
        return format;
    }
    /**
     * 截取String中间用****展示（合约地址）
     *
     * @return str1
     */
    public static String stringShowstar(String str) {
        // TODO SGF ADD
        String a = "";
        a = str.substring(0, 5)+ "****" + str.substring(str.length() - 5);
        return a;
    }

    /**
     * 截取String后四位中间用****展示（合约地址）
     * 0xb25c9F9BEEe12D10BCB58Dcf5ab2d09f1e6c47d3
     * @return str1
     */
    public static String stringShow(String str) {
        // TODO SGF ADD
        String a = "";
        a = str.substring(0, 26)+ "...." + str.substring(str.length() - 4);
        return a;
    }

    /**
     * 截取String后四位中间用****展示（合约地址）
     * 0xb25c9F9BEEe12D10BCB58Dcf5ab2d09f1e6c47d3
     * @return str1
     */
    public static String stringShow2(String str) {
        // TODO SGF ADD
        String a = "";
        a = str.substring(0, 23)+ "...." + str.substring(str.length() - 4);
        return a;
    }

    /**
     * 截取手机号码中间四位*显示
     *
     * @return str1
     */
    public static String showPhonenumber(String str) {
        // TODO SGF ADD
        String a = "";
        a = str.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        return a;
    }

    /**
     * 截取身份证号码中间四位*显示
     *$1、$2、……表示正则表达式里面第一个、第二个、……括号里面的匹配内容
     * @return str1
     */
    public static String showIDCard(String str) {
        // TODO SGF ADD
        String a = "";
        a = str.replaceAll("(\\d{4})\\d{10}(\\w{4})","$1*****$2");
        return a;
    }

    /**
     * 两个Map转JSON
     *
     * @return gameMap
     */
//    public static String mapToJson(String key,String value,String key2,String value2) {
//        // TODO SGF ADD
//        Map<String,String> map=new HashMap<String,String>();
//        //添加元素
//        map.put(key, value);
//        map.put(key2, value2);
//        ObjectMapper mapper = new ObjectMapper();//map转JSON对象
//        String gameMap = null;
//        try {
//            gameMap = mapper.writeValueAsString(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return gameMap;
//    }
    /**
     * Map转JSON
     *
     * @return gameMap
     */
//    public static String mapToJson(String key,String value) {
//        // TODO SGF ADD
//        Map<String,String> map=new HashMap<String,String>();
//        //添加元素
//        map.put(key, value);
//        ObjectMapper mapper = new ObjectMapper();//map转JSON对象
//        String gameMap = null;
//        try {
//            gameMap = mapper.writeValueAsString(map);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return gameMap;
//    }
    /**
     * 多个Map转JSON
     *
     * @return gameMap
     */
    public static String mapToJsonMore(String preminm_lte_key,String preminm_lte_value, String preminm_gte_key,String preminm_gte_value,
                                       String daysToLend_lt_key,String daysToLend_lt_value,
                                       String status_key,String status_value,String premium,String days_to_lend,
                                       String b_token_amount,String b_token_amount_key,String b_token_amount_value) {
        // TODO SGF ADD

//        条件操作符
//        db.col.find({"likes" : {$gt : 100}})  大于
//        db.col.find({"likes" : {$gte : 100}})  大于等于
//        db.col.find({"likes" : {$lt : 100}})    小于
//        db.col.find({"likes" : {$lte : 100}})    小于
//        db.col.find({likes : {$lt :200, $gt : 100}})   100<n<200
//        db.col.find({"likes" : {$ne : 100}})    不等于
//        db.col.find({"likes" : {$eq : 100}})    等于
        String str =null;
        try {
            //{   "status" : "4",   "premium" : {     "$lte" : "0.15",     "$gte" : "0.1"   },   "days_to_lend" : {     "$lt" : "7"   } }
            JSONObject json = new JSONObject();
            JSONObject moneyJson = new JSONObject();//借入总额
            moneyJson.put(b_token_amount_key,b_token_amount_value);
            moneyJson.put("","");
            JSONObject preminmJson = new JSONObject();//借入日利率
//            if(preminm_lte_key!=null||preminm_lte_key!=""){
                preminmJson.put(preminm_lte_key, preminm_lte_value);
//            }else if("".equals(preminm_lte_key)||preminm_lte_key.equals(null)){
//                GlobalLog.e("sgf", preminm_lte_key+ "是空的");
//            }
            preminmJson.put(preminm_gte_key, preminm_gte_value);

            JSONObject daysToLendJson = new JSONObject();//周期
            daysToLendJson.put(daysToLend_lt_key, daysToLend_lt_value);

            json.put(status_key, status_value);
            json.put(b_token_amount, moneyJson);//借入总额

            json.put(premium, preminmJson);
            json.put(days_to_lend, daysToLendJson);
            str =  json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 1:0:1:0:1
     *
     * @param
     * @return
     */

    public static String[] getCount(String a, String b) {
        String[] arrayStr = {};
        arrayStr = a.split(b);
        return arrayStr;
    }

    public static boolean isZero(String m1) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal("0");
        return b1.compareTo(b2) == 0;
    }

    // public static boolean checkPhone(String phone) {
    // if (null == phone || !phone.matches("^1[3-8]+\\d{9}")) {
    // return false;
    // }
    // return true;
    // }
    //
    // public static boolean checkPassword(String password) {
    // if (null == password || !password.matches("[a-f A-F 0-9]{6-15}")) {
    // return false;
    // }
    // return true;
    // }

    /**
     * 将分转化为元
     *
     * @param str
     * @return fen->yuan 112000(分)-> 1120.00(元)
     */
    public static String formatMoney_FenToYuan(String str) {

        try {
            GlobalLog.d(TAG, "formatMoney_FenToYuan : " + str);
            if (!isEmpty(str)) {
                if (str.equals("0")) {
                    return "0";
                } else if (str.endsWith("00")) {
                    GlobalLog.d(TAG, str.substring(0, str.length() - 2));
                    return new BigDecimal(str.substring(0, str.length() - 2))
                            .toString();
                } else {
                    return BigDecimal.valueOf(new BigDecimal(str).longValue(),
                            2).toString();
                }
            }

        } catch (Exception e) {
//            GlobalException.proxy.handle(e, null);
        }

        return "0.00";
    }

    /**
     * @param str
     * @return 5->5.00
     */
    public static String formatMoney_Yuan(String str) {

        if (!isEmpty(str)) {
            return str + ".00";
        }

        return "0.00";
    }

    /**
     * @param str
     * @return 5->500
     */
    public static String formatMoney_Fen(String str) {

        if (!isEmpty(str)) {

            double ret = Double.valueOf(str) * 100;
            return String.valueOf((int) ret);
//            return str + "00";
        }

        return "0";
    }

    /**
     * @param str
     * @return 5->500
     */
    public static String format_FenToYuan(String str) {
        //TODO SGF  ADD

        if (!isEmpty(str)) {

            double ret = Double.valueOf(str) / 100;
            return String.valueOf((int) ret);
//            return str + "00";
        }

        return "0";
    }

    /**
     * @param m1
     * @param m2
     * @return true m1 > m2
     */
    public static boolean greaterThanMoney(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1;
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static boolean greaterEqualMoney(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean greaterThanZero(String m1) {
        return greaterThan(m1, "0");
    }

    public static boolean greaterEqualZero(String m1) {
        return greaterEqual(m1, "0");
    }

    public static boolean greaterEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }

        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean greaterThan(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }

        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean lessEqualZero(String m1) {
        return lessEqual(m1, "0");
    }

    public static boolean lessThanZero(String m1) {
        return lessThan(m1, "0");
    }

    public static boolean lessEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == -1 || b1.compareTo(b2) == 0;
    }

    /**
     * 比较大小
     *
     * @param m1
     * @param m2
     * @return
     */
    public static boolean lessThan(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == -1;
    }

    /**
     * @param m1
     * @param m2
     * @return true m1 >= m2
     */
    public static boolean compareMoneyEqual(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.compareTo(b2) == 1 || b1.compareTo(b2) == 0;
    }

    public static boolean isMoneyZero(String m1) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal("0");
        return b1.compareTo(b2) == 0;
    }

    /**
     * 加法运算
     *
     * @param m1 "1.7"
     * @param m2 "7.85"
     * @return "1.7" + "7.85" => 9.55
     */
    public static String add(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.add(b2).toString();
    }

    public static String add(String m1, String m2, String m3) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        if (isEmpty(m3)) {
            m3 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        BigDecimal b3 = new BigDecimal(m3);
        return b1.add(b2).add(b3).toString();
    }

    /**
     * 减法运算
     *
     * @param m1 2.3
     * @param m2 1.1
     * @return 1.2
     */
    public static String subtract(String m1, String m2) {
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.subtract(b2).toString();
    }

    /**
     * @param m1
     * @param m2
     * @return
     */
    public static String divide(String m1, String m2) {
        GlobalLog.d(TAG, "m1 : " + m1 + " m2 : " + m2);
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "1";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.divide(b2, 2, RoundingMode.HALF_UP).toString();
    }

    /**
     * 乘法运算
     *
     * @param m1 参数1
     * @param m2 参数2
     * @return 两个数相乘
     */
    public static String multiply(String m1, String m2) {
        GlobalLog.d(TAG, "m1 : " + m1 + " ; m2 : " + m2);
        if (isEmpty(m1)) {
            m1 = "0";
        }
        if (isEmpty(m2)) {
            m2 = "0";
        }
        BigDecimal b1 = new BigDecimal(m1);
        BigDecimal b2 = new BigDecimal(m2);
        return b1.multiply(b2).toString();
    }

    public static String getProgress(int progress) {
        return (progress / 20) + "";
    }

    public static String getLimit(String total_unit) {
        double l = Double.valueOf(total_unit);
        return (int) Math.max(Math.round(l * 0.05), 1) + "";
    }

    private static BigDecimal one_hundred_million = new BigDecimal("100000000");
    private static BigDecimal ten_thousand = new BigDecimal("10000");
    private static BigDecimal hundred_thousand = new BigDecimal("100000");
    private static BigDecimal one_million = new BigDecimal("1000000");
    private static BigDecimal ten_million = new BigDecimal("10000000");

    public static String formatMoney_Chinese(String m) {

        BigDecimal b1 = new BigDecimal(m);
        BigDecimal b2 = b1.divide(one_hundred_million);

        if (b2.doubleValue() >= 10) {
            return b2.toString() + "亿";
        } else if (b2.doubleValue() >= 1) {
            return b1.divide(one_hundred_million).setScale(2,
                    RoundingMode.HALF_DOWN)
                    + "亿";
        } else {
            b2 = b1.divide(ten_million);
            if (b2.intValue() > 0) {
                System.out.println(b2);
                return b2.multiply(new BigDecimal(1000))
                        .setScale(1, RoundingMode.HALF_DOWN).toString()
                        + "万";

            } else {
                b2 = b1.divide(one_million);
                if (b2.intValue() > 0) {

                    return b2.multiply(new BigDecimal(100))
                            .setScale(0, RoundingMode.HALF_DOWN).toString()
                            + "万";
                } else {
                    b2 = b1.divide(hundred_thousand);
                    if (b2.intValue() > 0) {
                        return b2.multiply(new BigDecimal(10))
                                .setScale(0, RoundingMode.HALF_DOWN).toString()
                                + "万";
                    } else {
                        b2 = b1.divide(ten_thousand);
                        if (b2.intValue() > 0) {
                            return b2.setScale(2, RoundingMode.HALF_DOWN) + "万";
                        }
                        return m + "元";
                    }
                }
            }
        }

    }

    /**
     * 是否显示出票明细
     *
     * @param issueState
     * @return
     */
    public static boolean isCanShowTicket(String issueState) {

        try {

            if (issueState.startsWith("QC@")) {
                String str = issueState.substring(3);
                if (Integer.parseInt(str) > 1) {
                    return true;
                }
                GlobalLog.d(TAG, str);
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean verifyPassword(String password) {

        if (isEmpty(password)) {
            return false;
        } else return password.matches("^[0-9a-zA-Z]{6,15}$");

    }

    /**
     * 手机号码验证
     */
    public static boolean verifyPhoneNumber(String phone) {
        if (isEmpty(phone)) {
            return false;
        } else if (!phone.startsWith("1")) {
            return false;
        } else return phone.length() == 11;
//        if (phone
//                .matches("^((13[0-9])|(15[^4,\\D])|(18[0,5-9])|(170))\\d{8}$")) {
//            return true;
//        }

    }

    /**
     * 判断手机号码正则表达式
     * @param mobileNums
     * @return
     */
    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    /*** 验证电话号码
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean IsTelephone(String str) {
        String regex = "^0(10|2[0-5789]-|\\d{3})-?\\d{7,8}$";
        return matches(regex, str);
    }

    // /** 数字验证 */
    // public static boolean verifyNumeric(String paramString) {
    // return Pattern.compile("[0-9]*").matcher(paramString).matches();
    // }

    /**
     * 用户名验证 "^[^0-9]\\w{3,16}$"
     */
    public static boolean verifyUsername(String username) {
        if (isEmpty(username)) {
            return false;
        } else return username.matches("[\u4E00-\u9FA5a-zA-Z][\u4E00-\u9FA5a-zA-Z0-9]{2,15}");
    }

    // /** 密码验证 */
    // public static boolean verifyPassword(String paramString) {
    // return Pattern.compile("^[A-Za-z0-9]{6,16}$")
    // .matcher(paramString)
    // .matches();
    // }

    public static boolean verifyEmail(String paramString) {
        return Pattern
                .compile("^\\s*([A-Za-z0-9_-]+(\\.\\w+)*@(\\w+\\.)+\\w+)\\s*$")
                .matcher(paramString).matches();
    }

    public static boolean verifyIDCard(String validateStr) {
        String regex = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(validateStr).matches();
    }


    /**
     * 校验密码有 6到18位 字母和数字 组成 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifyPasswordLen(String passStr) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$";
        return passStr.matches(regex);
    }
    /**
     * 校验输入框小数点后面只能有2位数字 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifTwo(String passStr) {
        String regex = "^\\d{1,8}(\\.\\d{1,2})?$";
        return passStr.matches(regex);
    }

    /**
     * 校验输入框只能输入数字 的正则
     *
     * @param passStr
     * @return
     */
    public static boolean verifNum(String passStr) {
        String regex = "^[0-9]*[1-9][0-9]*$";
        return passStr.matches(regex);
    }

    public static String getLast_2(String issue) {
        if (StringUtil.isEmpty(issue)) {
            return "";
        } else {
            return issue.substring(issue.length() - 2);
        }
    }

    public static String getLast_3(String issue) {
        if (StringUtil.isEmpty(issue)) {
            return "";
        } else {
            return issue.substring(issue.length() - 3);
        }
    }

    public static Map<String, String> parseData(String data) {

        Map<String, String> retMap = new HashMap<>();

        String[] items = data.split("#");
        String[] item = null;
        for (String i : items) {
            item = i.split("=");
            if (item.length >= 2) {
                retMap.put(item[0], item[1]);
            }
        }

//        for (String key : retMap.keySet()) {
//            System.out.println(key + " : " + retMap.get(key));
//        }

        return retMap;
    }

    /**
     * 添加数据
     * @param data1
     * @param data2
     * @return
     */
    public static Map<String, Integer> addData(String data1,int data2) {
        retMap.put(data1, data2);
//        for (String key : retMap.keySet()) {
//            System.out.println(key + " : " + retMap.get(key));
//        }

        return retMap;
    }
    /**
     * 获取数据
     * @param data1
     * @return
     */
    public static int getData(String data1) {
        int num = 0;
        for (String key : retMap.keySet()) {
            System.out.println(key + " : " + retMap.get(key));
            L.d(key + " :::::: " + retMap.get(key));
            if(key.contains(data1)){
                num  = retMap.get(key);
            }
        }

        return num;
    }

    public static String setStrRed(String str) {
        return "<font color='red'>" + str + "</font>";
    }
    //白色
    public static String setStrWhite(String str) {
        return "<font color='#ffffff'>" + str + "</font>";
    }

    public static String setStrMainRed(String str) {
        return "<font color='#f33b3b'>" + str + "</font>";
    }
    //灰色
    public static String setStrGray(String str) {
        return "<font color='#888888'>" + str + "</font>";
    }
    //灰色2
    public static String setStrGray2(String str) {
        return "<font color='#999999'>" + str + "</font>";
    }
    //灰色3
    public static String setStrGray3(String str) {
        return "<font color='#666666'>" + str + "</font>";
    }

    public static String setStrOrenge(String str) {
        return "<font color='#ff9e14'>" + str + "</font>";
    }
    //竞彩篮球-绿色
    public static String setStrGreen(String str) {
        return "<font color='#4bc160'>" + str + "</font>";
    }

    public static String setStrBlack(String str) {
        return "<font color='#0A0A0A'>" + str + "</font>";
    }

    public static String setStrOrange(String str) {
        return "<font color='#FF3D3D'>" + str + "</font>";
    }

    public static String setStrRedBold(String str) {
        return "<b><font color='red'>" + str + "</font></>";
    }

    public static String setStrBold(String str) {
        return "<b>" + str + "</b>";
    }

    public static List<String> getNumList(int start, int end) {

        List<String> list = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            list.add(i + "");
        }
        return list;
    }

    public static String setWinCodeRed(String drawCode, String code) {

        StringBuilder retBuf = new StringBuilder();

        String[] drawCodes = drawCode.split(",");

        List<String> codes = new ArrayList<>();

        for (String c : code.split(",")) {
            codes.add(c);
        }

        for (String d : drawCodes) {

            if (codes.contains(d)) {
                retBuf.append(setStrRed(d));
                retBuf.append(" ");
            } else {
                retBuf.append(d);
                retBuf.append(" ");
            }
        }

        return retBuf.toString();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static String getString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        return str.trim();
    }

    /**
     * 生成创建二维码
     */
//    public static Bitmap createQRImage(String url, int QR_WIDTH, int QR_HEIGHT) throws WriterException {
//
//        // 判断URL合法性
//        if (url == null || "".equals(url) || url.length() < 1) {
//            return null;
//        }
//        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//        // 图像数据转换，使用了矩阵转换
//        BitMatrix bitMatrix = new QRCodeWriter().encode(url,
//                BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
//        int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
//        // 下面这里按照二维码的算法，逐个生成二维码的图片，
//        // 两个for循环是图片横列扫描的结果
//        for (int y = 0; y < QR_HEIGHT; y++) {
//            for (int x = 0; x < QR_WIDTH; x++) {
//                if (bitMatrix.get(x, y)) {
//                    pixels[y * QR_WIDTH + x] = 0xff000000;
//                } else {
//                    // pixels[y * QR_WIDTH + x] = 0xffffffff;
//                }
//            }
//        }
//        // 生成二维码图片的格式，使用ARGB_8888
//        Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
//                Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
//        //TODO WH 显示到一个ImageView上面
//        //iv_codes_qr.setImageBitmap(bitmap);
//        return bitmap;
//
//    }

    /**
     * 根据指定内容生成自定义宽高的二维码图片
     *
     * @param content 需要生成二维码的内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @throws WriterException 生成二维码异常
     */
//    public static Bitmap makeQRImage(String content, int width, int height)
//            throws WriterException {
//        // 判断URL合法性
//        if (!isNoBlankAndNoNull(content))
//            return null;
//
//        Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        // 图像数据转换，使用了矩阵转换
//        BitMatrix bitMatrix = new QRCodeWriter().encode(content,
//                BarcodeFormat.QR_CODE, width, height, hints);
//        int[] pixels = new int[width * height];
//        // 按照二维码的算法，逐个生成二维码的图片，两个for循环是图片横列扫描的结果
//        for (int y = 0; y < height; y++) {
//            for (int x = 0; x < width; x++) {
//                if (bitMatrix.get(x, y))
//                    pixels[y * width + x] = 0xff000000;
//                else {
//                    pixels[y * width + x] = 0xffffffff;
//                }
//            }
//        }
//        // 生成二维码图片的格式，使用ARGB_8888
//        Bitmap bitmap = Bitmap.createBitmap(width, height,
//                Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
//
//        return bitmap;
//    }

    /**
     * 判断字符串是否非空非null
     *
     * @param strParm 需要判断的字符串
     * @return 真假
     */
    public static boolean isNoBlankAndNoNull(String strParm) {
        return !((strParm == null) || (strParm.equals("")));
    }


    /**
     * 指定目录写入文件内容
     *
     * @param filePath 文件路径+文件名
     * @param
     * @throws IOException
     */
    public static void saveAsJPEG(Bitmap bitmap, String filePath)
            throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 是否有内存
     *
     * @return
     */
    public static boolean isMountedSDCard() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }

    /**
     * 截屏
     *
     * @param activity
     * @return
     */
    public static Bitmap activityShot(Activity activity) {
       /*获取windows中最顶层的view*/
        View view = activity.getWindow().getDecorView();
        //允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        WindowManager windowManager = activity.getWindowManager();
        //获取屏幕宽和高
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        //去掉状态栏
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache(), 0, statusBarHeight, width, height - statusBarHeight);
        //销毁缓存信息
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    /**
     * 截取scrollview的屏幕
     * @param scrollView
     * @return
     */
    public static Bitmap getBitmapByView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取listView实际高度
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
            scrollView.getChildAt(i).setBackgroundResource(R.drawable.white_drawable);
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()+
                    "/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
    /**
     *  截图listview
     * **/
    public static Bitmap getbBitmap(ListView listView) {
        int h = 0;
        Bitmap bitmap = null;
        // 获取listView实际高度
        for (int i = 0; i < listView.getChildCount(); i++) {
            h += listView.getChildAt(i).getHeight();
        }

        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(listView.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        listView.draw(canvas);
        // 测试输出
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(Environment.getExternalStorageDirectory().getPath()
                    +"/screen_test.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (null != out) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            // TODO: handle exception
        }
        return bitmap;
    }
    /**
     * 截取RelativeLayout
     **/
    public static Bitmap getRelativeLayoutBitmap(RelativeLayout relativeLayout) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            h += relativeLayout.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(relativeLayout.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        relativeLayout.draw(canvas);
        return bitmap;
    }
    /**
     * 截取LinearLayout
     **/
    public static Bitmap getLinearLayoutBitmap(LinearLayout linearLayout) {
        int h = 0;
        Bitmap bitmap;
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            h += linearLayout.getChildAt(i).getHeight();
        }
        // 创建对应大小的bitmap
        bitmap = Bitmap.createBitmap(linearLayout.getWidth(), h,
                Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);
        return bitmap;
    }
    /**
     * 截取除了导航栏之外的整个屏幕
     */
    public static Bitmap screenShotWholeScreen(Activity activity) {
        View dView = activity.getWindow().getDecorView();
        dView.setDrawingCacheEnabled(true);
        dView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(dView.getDrawingCache());
        return bitmap;
    }
}
