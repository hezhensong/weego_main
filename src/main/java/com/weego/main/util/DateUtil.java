package com.weego.main.util;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by liuniandxx on 16-3-16. 时间操作通用类
 */
public class DateUtil {
    private static final String yyyyMMddHHmmss = "yyyyMMdd HH:mm:ss";
    private static final String yyyyMMdd = "yyyyMMdd";
    private static final String HHmmss = "HH:mm:ss";
    private static final String MMdd = "MMdd";
    private static final String utcFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final String slantYMD = "yyyy/MM/dd";
    private static final String slantMD = "MM/dd";
    private static final String slantEEEE = "EEEE";


    public static String formatDate(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    // Date 转化为 yyyyMMdd
    public static String formatyyyyMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
        return format.format(date);
    }

    // Date 转化为 MM/dd
    public static String formatMM_dd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(slantMD);
        return format.format(date);
    }

    // Date 转化为 星期几
    public static String formatDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(slantEEEE);

        String day = format.format(date);
        switch (day) {
            case "Monday":
                day = "星期一";
                break;

            case "Tuesday":
                day = "星期二";
                break;

            case "Wednesday":
                day = "星期三";
                break;

            case "Thursday":
                day = "星期四";
                break;

            case "Friday":
                day = "星期五";
                break;

            case "Saturday":
                day = "星期六";
                break;

            case "Sunday":
                day = "星期日";
                break;

            default:
                break;
        }

        return day;
    }

    // 时间格式转换为Date
    public static Date yyyyMMddToDate(String date) {
        DateTimeFormatter format = DateTimeFormat.forPattern(yyyyMMdd);
        DateTime dateTime = DateTime.parse(date, format);
        return dateTime.toDate();
    }

    // Date 转化为 yyyyMMdd
    public static String formatyyyyMMddHHmmss(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    // 时间格式转换为Date
    public static Date yyyyMMddHHmmssToDate(String datestr) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(datestr);
        return date;
    }

    public static Date getnow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        java.util.Date date = null;
        try {
            date = sdf.parse(sdf.format(new Date()));

        } catch (ParseException e) {

            e.printStackTrace();
        }
        return date;
    }


    // 获取时间格式 yyyyMMdd
    public static Date yyyyMMdd(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = new DateTime(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth(), 0, 0, 0);
        return dateTime.toDate();
    }

    // n > 0 获取n天后的当前时间 n <= 0 获取n天前的当前时间
    public static Date afterNDays(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        dateTime = dateTime.plusDays(n);
        return dateTime.toDate();
    }

    // 获取昨天的当前时间
    public static Date yesterday(Date date) {
        return afterNDays(date, -1);
    }

    /**
     * 获取日期的 月日 格式MMdd
     * 
     * @param date
     * @return
     */
    public static String formatMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(MMdd);
        return format.format(date);
    }

    /**
     * GMT 转化为 UTC
     * 
     * @param date
     * @return
     */
    public static Date covertTimeToUTC(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime.plusHours(8);
        return dateTime.toDate();
    }

    /**
     * 计算两个日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        long time1 = smdate.getTime();
        long time2 = bdate.getTime();
       
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        int days = Integer.parseInt(String.valueOf(between_days));

        if(time1>time2&&days==0){
            return -1;
        }else if(time2>time1&&days==0){
            return 1;
        }else{
            return days;
        }
       
    }

    
    /** 
     * 比较两个日期之间的大小 
     *  
     * @param d1 
     * @param d2 
     * @return 前者大于后者返回true 反之false 
     */  
    public static boolean compareDate(Date d1, Date d2) {  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        c1.setTime(d1);  
        c2.setTime(d2);  
      
        int result = c1.compareTo(c2);  
        if (result >= 0)  
            return true;  
        else  
            return false;  
    }  
    
    /**
     * 计算两个long型日期之间相差的天数
     * 
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(long smdate, long bdate) throws ParseException {
        long between_days = (bdate - smdate) / (3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 将时间戳转换成Date
     * 
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static Date longChangeToDate(long datetime) throws ParseException {
        // 时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String d = format.format(datetime);
        Date date = format.parse(d);
        return date;

    }

    /**
     * 将时间戳转换成时间类型的字符串
     * 
     * @param datetime
     * @return
     * @throws ParseException
     */
    public static String longChangeToDateStr(long datetime) throws ParseException {
        // 时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(datetime);
        // Date date = format.parse(d);
        return dateStr;

    }
    
    /**
     * 将 Date 类型转为字符串: XXXX 年 XX 月 XX 日
     * 
     * @param date
     * @return
     */
    public static String getDiscoveryTimeFormatter(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int year = calendar.get(Calendar.YEAR);
    	int month = calendar.get(Calendar.MONTH) + 1;
    	int day = calendar.get(Calendar.DATE);
    	return year + "年" + month + "月" + day + "日";
    }


    /**
     * 将字符串时间转化为指定时区的对应时间
     * @param date   字符串时间
     * @param format  时间格式， 例如: yyyyMMdd
     * @param timezone  时区, GMT格式，例如: 东八区对应为 GMT+8:00
     * @return
     */
    public static Date formatDateToDate(String date, String format, String timezone) {
        TimeZone timeZone = TimeZone.getTimeZone(timezone);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        Date dateTime = null;

        try {
            dateTime = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateTime;
    }


    /**
     * 获取指定时区的 yyyyMMdd对应日期
     * @param  date    yyyyMMdd 格式
     * @param timezone 时区,如东八区 GMT+8:00
     * @return
     */
    public static Date yyyyMMddToDate(String date, String timezone) {
        return formatDateToDate(date, yyyyMMdd, timezone);
    }

}
