package com.iruiyou.pet.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间转换格式处理
 * sgf
 * TimeUtil.getYYDDHHMM(orderBean.getOrder_item_times())
 */
public class TimeUtil {
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年
    public static final SimpleDateFormat DATE_INPUT_YYYYMMDDHHMMSS = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    public static final SimpleDateFormat DEFAULT_FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat DEFAULT_FORMAT_YYYYMMDDHHmm = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat DEFAULT_FORMAT_YYYYMMDDHHMM = new SimpleDateFormat(
            "yyyyMMddHHmm");

    public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new
            SimpleDateFormat(
            "yyyy-MM-dd");

    // public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD = new
    // SimpleDateFormat(
    // "yyyy-MM-dd");
    // public static final SimpleDateFormat DATE_FORMAT_YYYY_MM_DD_HH_MM = new
    // SimpleDateFormat(
    // "yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat(
            "yyyyMMdd");
    public static final SimpleDateFormat DATE_FORMAT_MM = new SimpleDateFormat(
            "MM");
    public static final SimpleDateFormat DATE_FORMAT_DD = new SimpleDateFormat(
            "dd");
    public static final SimpleDateFormat DATE_FORMAT_HHmm = new SimpleDateFormat(
            "HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_YYmmdd_HHmm = new SimpleDateFormat(
            "yy-MM-dd HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_MMddHHmm = new SimpleDateFormat(
            "MM-dd HH:mm");

    public static final SimpleDateFormat DATE_FORMAT_MMddHHmmss = new SimpleDateFormat(
            "MM-dd HH:mm:ss");

    public static final SimpleDateFormat DATE_FORMAT_MMdd = new SimpleDateFormat(
            "MM-dd");

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is {@link #DEFAULT_FORMAT_YYYYMMDDHHMMSS}
     * yyyy-MM-dd HH:mm:ss
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_FORMAT_YYYYMMDDHHMMSS);
    }

    public static String getTimeForDate(long timeInMillis){
        return getTime(timeInMillis, DATE_FORMAT_MMdd);
    }

    /**
     * long time to string, format is {@link #DEFAULT_FORMAT_YYYYMMDDHHMMSS}
     * yyyy-MM-dd HH:mm:ss
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(String timeInMillis) {
        try {
            return DATE_FORMAT_MMddHHmmss.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(timeInMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取 MM-dd HH:mm 这样格式的时间字符串
     *
     * @param timeInMillis
     * @return
     */
    public static String getYYDDHHMM(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_MMddHHmm);
    }

    /**
     * 获取 MM-dd HH:mm 这样格式的时间字符串
     *
     * @param timeInMillis
     * @return
     */
    public static String getYYDDHHMM(String timeInMillis) {
        //return getTime(timeInMillis, DATE_FORMAT_MMddHHmm);
        try {
            return DATE_FORMAT_MMddHHmm.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(timeInMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取 yy-MM-dd HH:mm 这样格式的时间字符串
     *
     * @param timeInMillis
     * @return
     */
    public static String getYYMMDDHHMM(long timeInMillis) {
        return getTime(timeInMillis, DATE_FORMAT_YYmmdd_HHmm);
    }


    /**
     * 获取 yy-MM-dd HH:mm 这样格式的时间字符串
     *
     * @param timeInMillis
     * @return
     */
    public static String getYYMMDDHHMM(String timeInMillis) {
        //return getTime(timeInMillis, DATE_FORMAT_YYmmdd_HHmm);
        try {
            return DATE_FORMAT_YYmmdd_HHmm
                    .format(DATE_INPUT_YYYYMMDDHHMMSS.parse(timeInMillis));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String formatYYYYMMDDHHMMSS(String time) {
        try {
            return DEFAULT_FORMAT_YYYYMMDDHHMMSS
                    .format(DATE_INPUT_YYYYMMDDHHMMSS.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param time
     * @return
     */
    public static String formatYYYYMMDDHHmm(String time) {
        //TODO SGF add
        try {
            return DEFAULT_FORMAT_YYYYMMDDHHmm
                    .format(DATE_INPUT_YYYYMMDDHHMMSS.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is
     * {@link #DEFAULT_FORMAT_YYYYMMDDHHMMSS}
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    public static String getData_YYYYMMDD() {
        try {
            return DATE_FORMAT_YYYYMMDD
                    .format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String formatYYYYMMDD(String time) {
        try {
            return DATE_FORMAT_YYYYMMDD.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatMMDD(String time) {
        try {
            return DATE_FORMAT_MMdd.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDD(String time) {
        try {
            return DATE_FORMAT_DD.format(DATE_INPUT_YYYYMMDDHHMMSS.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param time
     * @return HH:mm
     */
    public static String formatHHmm(String time) {
        try {
            return DATE_FORMAT_HHmm.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatYYYYMMDDHHMM(String time) {
        try {
            return DEFAULT_FORMAT_YYYYMMDDHHMM.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatMMddHHmm(String time) {

        try {
            return DATE_FORMAT_MMddHHmm.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatMMdd(String time) {

        try {
            return DATE_FORMAT_MMddHHmm.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static final long one_second = 1000;
    private static final long one_minute = one_second * 60;
    private static final long one_hour = one_minute * 60;
    private static final long one_day = one_hour * 24;

    /**
     * @param time
     * @return 刚刚，1分钟前，1小时前
     */
    public static String formatDate(long time) {
        if (time == 0) {
            return "";
        }
        long now = System.currentTimeMillis();
        long ext = now - time;

        if (ext >= one_day) {
            return getTime(time);
        }
        long m = ext / one_hour;
        if (m == 0) {
            m = ext / one_minute;
            if (m == 0) {
                return "刚刚";
            } else {
                return m + "分钟前";
            }
        } else {
            return m + "小时前";
        }
    }


    /**
     * @param time
     * @return 刚刚，1分钟前，1小时前
     */
    public static String formatForDate(long time) {
        if (time == 0) {
            return "";
        }
        long now = System.currentTimeMillis();
        long ext = now - time;

        if (ext >= one_day) {
            return getTimeForDate(time);
        }
        long m = ext / one_hour;
        if (m == 0) {
            m = ext / one_minute;
            if (m == 0) {
                return "刚刚";
            } else {
                return m + "分钟前";
            }
        } else {
            return m + "小时前";
        }
    }


    private static final long one_second_s = 1;
    private static final long one_minute_s = one_second_s * 60;
    private static final long one_hour_s = one_minute_s * 60;
    private static final long one_day_s = one_hour_s * 24;

    /**
     * @param longTime
     * @return 1天1小时，1小时5分，2分23秒
     */
    public static String formatTimeForIssue(String longTime) {

        return formatTime(longTime, "期次获取中");
    }

    public static String formatTimeEmpty(String longTime) {

        return formatTime(longTime, "");
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }


    /**
     * 设置星期的格式和数据
     *
     * @param time 时间戳 字符串
     * @param flag 除了竞彩足球之外 添加标记
     * @return
     */
    public static String getWeekOfDate(String time, int flag) {
        try {
            String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五",
                    "周六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(DATE_FORMAT_MMddHHmm.parse(time));

            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;

            return weekDays[w];
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 设置星期的格式和数据
     *
     * @param time
     * @return
     */
    public static String getWeekOfDate(String time) {
        try {
            String[] weekDays = {"星期天", "星期一", "星期二", "星期三", "星期四", "星期五",
                    "星期六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(DATE_INPUT_YYYYMMDDHHMMSS.parse(time));

            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;

            return weekDays[w];
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 根据 1,2,3,4,5,6,7 字符串来判断星期几
     *
     * @param time
     * @return 周一 。。。。。
     */
    public static String getWeekStr(String time) {
        if ("1".equals(time)) {
            return "周一";
        }
        if ("2".equals(time)) {
            return "周二";
        }
        if ("3".equals(time)) {
            return "周三";
        }
        if ("4".equals(time)) {
            return "周四";
        }
        if ("5".equals(time)) {
            return "周五";
        }
        if ("6".equals(time)) {
            return "周六";
        }
        if ("7".equals(time)) {
            return "周日";
        } else {
            return "";
        }
    }

    /**
     * 设置星期的格式和数据
     *
     * @param time
     * @return
     */
    public static String getWeekOfDate2(String time) {
        try {
            String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五",
                    "周六"};
            Calendar cal = Calendar.getInstance();
            cal.setTime(DATE_INPUT_YYYYMMDDHHMMSS.parse(time));

            int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0)
                w = 0;

            return weekDays[w];
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * 设置时间的格式YYYY_MM_DD
     *
     * @param time
     * @return
     */
    public static String formatYYYY_MM_DD(String time) {
        try {
            return DATE_FORMAT_YYYY_MM_DD.format(DATE_INPUT_YYYYMMDDHHMMSS
                    .parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String formatTime(String longTime, String errStr) {

        try {
            if (StringUtil.isEmpty(longTime)) {
                return errStr;
            }
            long time = Long.valueOf(longTime);
            long n = time / one_day_s;
            long left;
            String day, hour, minute, second;

            // 取天
            if (n > 0) {
                day = n + "天";
                left = time - n * one_day_s;

                n = left / one_hour_s;
                // 取小时
                if (n > 0) {
                    hour = n + "小时";
                    return day + hour;
                } else {
                    return day;
                }
            } else {
                // 不足一天
                n = time / one_hour_s;
                // 取小时
                if (n > 0) {
                    hour = n + "小时";

                    left = time - n * one_hour_s;

                    n = left / one_minute_s;
                    // 取分钟
                    if (n > 0) {
                        minute = n + "分";
                        return hour + minute;
                    } else {
                        return hour;
                    }

                } else {
                    // 不足一小时
                    n = time / one_minute_s;

                    if (n > 0) {
                        minute = n + "分";
                        second = (time - n * one_minute_s) + "秒";

                        return minute + second;
                    } else {
                        // 不足一分钟
                        return time + "秒";
                    }
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return errStr;
        }
    }

    // private static boolean isNeedCountDown(String longTime) {
    // try {
    // long time = Long.valueOf(longTime);
    // long n = time / one_day_s;
    //
    // // 取天
    // if (n > 0) {
    // return false;
    // } else {
    // // 不足一天
    // n = time / one_hour_s;
    // // 取小时
    // if (n > 0) {
    // return false;
    // } else {
    // // 取分钟
    // n = time / one_minute_s;
    // if (n > 30) {
    // return false;
    // } else {
    // return true;
    // }
    // }
    // }
    // } catch (NumberFormatException e) {
    // e.printStackTrace();
    // return false;
    // }
    // }
    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "个小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static long getTimeStemp_Value(String time) {
        long timeStemp = -1;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd");
            Date date = simpleDateFormat.parse(time);
            timeStemp = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeStemp;
    }


}