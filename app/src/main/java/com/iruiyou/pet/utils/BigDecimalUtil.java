package com.iruiyou.pet.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * 计算工具类
 * sgf
 */
public class BigDecimalUtil {
    /*
      ROUND_CEILING
      大于等于该数的那个最近值
      ROUND_DOWN
      正数是小于等于该数的那个最近数，负数是大于等于该数的那个最近数
      ROUND_FLOOR
      小于等于该数的那个值
      ROUND_HALF_DOWN
      五舍六入
      ROUND_HALF_EVEN
      向（距离）最近的一边舍入，除非两边（的距离）是相等,如果是这样，如果保留位数是奇数，
      使用ROUND_HALF_UP ，如果是偶数，使用ROUND_HALF_DOWN
      ROUND_HALF_UP
      四舍五入
      ROUND_UNNECESSARY
      计算结果是精确的，不需要舍入模式
      ROUND_UP
      和ROUND_DOWN相反
     */
    /**
     * 科学计数保留两位小数转成string
     *
     * @param string 需要转换的数值字符串
     * @return 转换后的数值字符串
     */
    public static String loadIntoUseFitWidth(String string) {
        BigDecimal bg = new BigDecimal(string);
        BigDecimal bigDecimal = bg.setScale(2,BigDecimal.ROUND_HALF_UP);
        String s = bigDecimal.toPlainString();
        return s;
    }

    //默认除法运算精度
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static int add(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).intValue();
    }
    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留scale 位小数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @return 两个参数的和
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.add(b2).toPlainString());
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1    被加数
     * @param v2    加数
     * @param scale 保留scale 位小数
     * @return 两个参数的和
     */
    public static String add(long v1, long v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留scale 位小数
     * @return 两个参数的差
     */
    public static String sub(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
    }
    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @return 两个参数的差
     *
     */
    public static String subNum(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.subtract(b2).toPlainString());
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1    被减数
     * @param v2    减数
     * @param scale 保留scale 位小数
     * @return 两个参数的差
     */
    public static String sub(long v1, long v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(), scale);
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        if (b2.intValue() == 0)
        {
            return removeEndZero("0");

        }
        if (scale == 0)
        {
            return removeEndZero("0");

        }
        return removeEndZero(b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString());
    }
    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @return 两个参数的积
     */
    public static String mul(String v1, String v2) {
        if(StringUtil.isNotEmpty(v1)&&StringUtil.isNotEmpty(v2))
        {
            BigDecimal b1 = new BigDecimal(v1);
            BigDecimal b2 = new BigDecimal(v2);
            return removeEndZero(b1.multiply(b2).toPlainString());
        }
        else
        {
            return "";
        }
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1    被乘数
     * @param v2    乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积
     */
    public static String mul(long v1, long v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后10位，以后的数字四舍五入
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString());//toString 科学算法
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @return 两个参数的商
     */
    public static String div(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).toPlainString());//toPlainString 非科学算法，纯输出
    }

    /**
     * double转变成显示非科学计算法显示(正常显示有效值)
     * @param v1
     * @return
     */
    public static String doubleToShowAll(double v1) {
        String value = String.valueOf(v1);
        BigDecimal bigDecimal = new BigDecimal(value);
        return removeEndZero(bigDecimal.toPlainString());//toPlainString 非科学算法，纯输出
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商
     */
    public static String div(long v1, long v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The number of decimal places retained must be greater than zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toPlainString();//显示所有小数
    }


    /**
     * 取余数
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 小数点后保留几位
     * @return 余数
     */
    public static String remainder(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The number of decimal places retained must be greater than zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return removeEndZero(b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString());
    }

    /**
     * 比较大小
     *
     * @param v1 被比较数
     * @param v2 比较数
     * @return 如果v1 大于v2 则 返回true 否则false
     */
    public static boolean compare(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        res = bj > 0;
        return res;
    }


    /**
     * 去掉数值字符串尾部无效的‘0’
     *
     * @param numStr 需要处理的 数值字符串
     * @return 处理后的数值字符串
     */
    public static String removeEndZero(String numStr){
        if(numStr.isEmpty()||!numStr.contains("."))
            return numStr;
        StringBuilder sb = new StringBuilder(numStr);
        int p= sb.length()-1;
        while(p>0&&sb.charAt(p)=='0'){
            sb.deleteCharAt(p--);
        }
        if(p>=0&&sb.charAt(p)=='.')
            sb.deleteCharAt(p);
        return sb.toString();
    }

    /**
     * 对给定的数值字符串先按指定的精确度进行四舍五入取值，然后去掉尾部无效的‘0’
     *
     * @param numStr 数值字符串
     * @param precision 小数精确度总位数,如2表示两位小数
     * @return 返回按精确度保留值后 去掉尾部无效的“0”的结果
     */
    public static String preciseAndRemoveEndZero(String numStr, int precision){
        BigDecimal bg = new BigDecimal(numStr);
        return removeEndZero(bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString());
    }
    /**
     * 对给定的数值字符串先按指定的精确度进行四舍五入取值，然后去掉尾部无效的‘0’
     *
     * @param numStr 数值
     * @param precision 小数精确度总位数,如2表示两位小数
     * @return 返回按精确度保留值后 去掉尾部无效的“0”的结果
     */
    public static String preciseAndRemoveEndZero(double numStr, int precision){
        BigDecimal bg = new BigDecimal(numStr);
        return removeEndZero(bg.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString());
    }

    public static String numStr_10_18(String num){
        return new BigDecimal(num).multiply(new BigDecimal(Math.pow(10,18))).toPlainString();
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 小数点后保留四位
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma4(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###.0000");
        double parseDouble = Double.parseDouble(str);
        if(parseDouble < 1){
            decimalFormat = new DecimalFormat("0.0000");
        }
        return decimalFormat.format(parseDouble);
    }
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 小数点后保留四位
     *
     * @param parseDouble 需要处理的数值
     * @return 处理完之后的数值
     */
    public static String addComma4(double parseDouble) {
        DecimalFormat decimalFormat = new DecimalFormat(",###.0000");
        if(parseDouble < 1){
            decimalFormat = new DecimalFormat("0.0000");
        }
        return decimalFormat.format(parseDouble);
    }
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 小数点后保留三位
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma3(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###.000");
        double parseDouble = Double.parseDouble(str);
        if(parseDouble < 1){
             decimalFormat = new DecimalFormat("0.000");
        }
        return decimalFormat.format(parseDouble);
    }
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 小数点后不保留
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }
    /**
     * 格式化
     */
    private static DecimalFormat dfs = null;
    public static DecimalFormat format(String pattern) {
        if (dfs == null) {
            dfs = new DecimalFormat();
        }
        dfs.setRoundingMode(RoundingMode.FLOOR);
        dfs.applyPattern(pattern);
        return dfs;
    }
}