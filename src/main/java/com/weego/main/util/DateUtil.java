package com.weego.main.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuniandxx on 16-3-16.
 * 时间操作通用类
 */
public class DateUtil {
    private static final String yyyyMMddHHmmss = "yyyyMMdd HH:mm:ss";
    private static final String yyyyMMdd = "yyyyMMdd";
    private static final String HHmmss = "HH:mm:ss";

    //Date 转化为 yyyyMMdd
    public static String formatyyyyMMdd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(yyyyMMdd);
        return format.format(date);
    }

    //时间格式转换为Date
    public static  Date yyyyMMddToDate(String date) {
        DateTimeFormatter format = DateTimeFormat.forPattern(yyyyMMdd);
        DateTime dateTime = DateTime.parse(date, format);
        return dateTime.toDate();
    }


    //获取时间格式 yyyyMMdd
    public static Date yyyyMMdd(Date date) {
        DateTime dateTime = new DateTime(date);
        dateTime = new DateTime(dateTime.getYear(),
                                dateTime.getMonthOfYear(),
                                dateTime.getDayOfMonth(),
                                0, 0, 0);
        return dateTime.toDate();
    }

    //n > 0 获取n天后的当前时间 n <= 0 获取n天前的当前时间
    public static Date afterNDays(Date date, int n) {
        DateTime dateTime = new DateTime(date);
        dateTime.plusDays(n);
        return dateTime.toDate();
    }

    //获取昨天的当前时间
    public static Date yesterday(Date date) {
        return afterNDays(date, -1);
    }
}
