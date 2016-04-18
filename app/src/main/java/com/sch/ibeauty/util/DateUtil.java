package com.sch.ibeauty.util;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {
    /**
     * 转换时间格式
     *
     * @param timestamp
     * @param format    MM-DD
     * @return
     */
    public static String getStringByFormat(long timestamp, String format) {
        // Log.d("getStandardTime", "getStandardTime-----" + timestamp);
        if (timestamp != 0)
            return DateFormat.format(format, timestamp).toString();
        return "";
    }

    /**
     * 格式化时间转换为毫秒单位时间
     *
     * @param time
     * @param format
     * @return
     */
    public static long timeformat2million(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0l;
        }
    }

    public static String getShowTime(String timeStr) {
        if (TextUtils.isEmpty(timeStr)) {
            return "";
        } else {
            long time = DateUtil.timeformat2million(timeStr, "yyyy-MM-dd HH:mm:ss");
            return getShowTime(time);
        }
    }

    public static String getShowTime(long time) {
        if (time == 0) {
            return "刚刚";
        } else {
            Calendar localCalendar = Calendar.getInstance();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            long localTime = localCalendar.getTimeInMillis();
            long diff = localTime - time;
            if (diff < 60 * 1000l) {
                return "刚刚";
            } else {
                if (diff < 60 * 60 * 1000l) {//一小时
                    long dat = diff / 1000 / 60;
                    return dat + "分钟前";
                } else if (diff < 24 * 60 * 60 * 1000l) {//一天
                    long dat = diff / 1000 / 60 / 60;
                    return dat + "小时前";
                } else if (diff < 30 * 24 * 60 * 60 * 1000l) {//粗略30天为一个月
                    long dat = diff / 1000 / 60 / 60 / 24;
                    return dat + "天前";
                } else if (diff < 365 * 24 * 60 * 60 * 1000l) {//一年
                    long dat = diff / 1000 / 60 / 60 / 24 / 30;
                    return dat + "个月前";
                } else {
                    long dat = diff / 1000 / 60 / 60 / 24 / 30 / 365;
                    return dat + "年前";
                }
            }
        }
    }

    /**
     * 格式化日期 -> yyyy年MM月
     *
     * @param year  年
     * @param month 月
     * @return yyyy年MM月
     */
    public static String formatDate(int year, int month) {
        return String.format("%d年%d月", year, month);
    }

    /**
     * 是否是以后的日期
     *
     * @param year  年
     * @param month 月
     * @param day   日, 不需要判断日请传入小于0的数
     * @return
     */
    public static boolean afterCurrentDate(int year, int month, int day) {

        Calendar newCalendar = getCurrentDate();
        newCalendar.set(Calendar.YEAR, year);
        newCalendar.set(Calendar.MONTH, month);
        if (day > 0) {
            newCalendar.set(Calendar.DAY_OF_MONTH, day);
        }

        return newCalendar.after(getCurrentDate());
    }

    /**
     * 计算某年某月有多少天
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDayNumOfMonth(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year + 1900);
        time.set(Calendar.MONTH, month);
        return time.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 日期集合转数组
     *
     * @param dateList
     * @return
     */
    public static Date[] dateListToArray(List<Date> dateList) {
        Date[] dates = new Date[dateList.size()];
        dateList.toArray(dates);
        return dates;
    }

    /**
     * @return "今天是*月*日, 星期*"
     */
    public static String getFormatDate() {
        final Calendar c = getCurrentDate();
        // String mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        int week = c.get(Calendar.DAY_OF_WEEK);
        String mWay = "";
        switch (week) {
            case 1:
                mWay = "天";
                break;
            case 2:
                mWay = "一";
                break;
            case 3:
                mWay = "二";
                break;
            case 4:
                mWay = "三";
                break;
            case 5:
                mWay = "四";
                break;
            case 6:
                mWay = "五";
                break;
            case 7:
                mWay = "六";
                break;
        }
        return String.format("今天%s月%s日, 星期%s", mMonth, mDay, mWay);
    }

    /**
     * @return 当前日期
     */
    public static Calendar getCurrentDate() {
        // 当前时间
        return Calendar.getInstance(Locale.CHINA);
    }

    /**
     * 格式化日期
     *
     * @param year        年
     * @param monthOfYear 月
     * @param dayOfMonth  日
     * @return yyyy-MM-dd格式
     */
    public static String formatDate(int year, int monthOfYear, int dayOfMonth) {
        return String.format("%s-%s-%s", year, addPrefix(monthOfYear + 1), addPrefix(dayOfMonth));
    }

    /**
     * 添加前缀"0"
     *
     * @param num 要加前缀的数字
     * @return "0*"或者"**"格式的数字
     */
    public static String addPrefix(int num) {
        return num < 10 ? "0" + num : "" + num;
    }

    /**
     * @return yyyy年MM月dd日
     */
    public static String today() {
        Calendar calendar = getCurrentDate();
        return String.format("%s年%s月%s日", calendar.get(Calendar.YEAR),
                addPrefix(calendar.get(Calendar.MONTH) + 1),
                calendar.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @return 当前月份
     */
    public static String month() {
        return addPrefix(getCurrentDate().get(Calendar.MONTH) + 1);
    }

    /**
     * @return 当前月份的日期号码
     */
    public static String dayOfMonth() {
        return addPrefix(getCurrentDate().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * @return 当前周, 英文
     */
    public static String dayOfWeekEnglish() {
        int mWay = getCurrentDate().get(Calendar.DAY_OF_WEEK);
        switch (mWay) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }
        return "";
    }

}
