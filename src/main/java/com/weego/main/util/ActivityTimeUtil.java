package com.weego.main.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivityTimeUtil {
	private static Logger logger = LogManager.getLogger(ActivityTimeUtil.class);
	
	public static String getActivityTimeDesc(Date date1, Date date2) {
		try {
			Date now = new Date();
			if(now.after(date1) && now.before(date2)) {
				return "进行中";
			} else if(DateUtil.daysBetween(now, date1) <= 7) {
				return "7天内开始";
			} else if(DateUtil.daysBetween(now, date1) > 7) {
				return getMonthAndDay(date1, date2);
			}
		} catch (ParseException e) {
			logger.info("POI 活动时间的判断出错");
			e.printStackTrace();
		}
		return "";
	}
	
	private static String getMonthAndDay(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
    	calendar1.setTime(date1);
    	int month1 = calendar1.get(Calendar.MONTH) + 1;
    	int day1 = calendar1.get(Calendar.DATE);
    	
    	Calendar calendar2 = Calendar.getInstance();
    	calendar2.setTime(date2);
    	int month2 = calendar2.get(Calendar.MONTH) + 1;
    	int day2 = calendar2.get(Calendar.DATE);
    	
    	return month1 +"月" + day1 + "日" + "-" + month2 + "月" + day2 + "日";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar calendar1 = Calendar.getInstance();
//		Date date = new Date();
		calendar1.set(2016,5,20,5,1);
		int hour = 0;
		System.out.println(calendar1.get(Calendar.MINUTE));
		if(calendar1.get(Calendar.MINUTE) > 0) {
			hour = calendar1.get(Calendar.HOUR_OF_DAY) + 1;
		} else {
			hour = calendar1.get(Calendar.HOUR_OF_DAY);
		}
		System.out.println(hour);
	}
}
