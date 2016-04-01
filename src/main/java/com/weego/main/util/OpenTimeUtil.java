package com.weego.main.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.weego.main.model.BasePOIOpenTime;

public class OpenTimeUtil {
	
	public static String OPEN = "营业中";
	public static String CLOSE = "歇业中";

	public static String getOpenDesc(List<BasePOIOpenTime> openTimes) {
		if(openTimes == null || openTimes.size() == 0) {
			return OpenTimeUtil.CLOSE;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		// Sunday 对应 1,Monday 对应  2,...,Saturday 对应 7
		int index = 6;
		if(calendar.get(Calendar.DAY_OF_WEEK) != 1) {
			index = calendar.get(Calendar.DAY_OF_WEEK) - 2;
		}
		
		int maxSize = openTimes.size();
		if(index > maxSize) {
			return OpenTimeUtil.CLOSE;
		}
		
		String openTimeValue = openTimes.get(index).getValue();
		
		if(openTimeValue.startsWith(index+1 + "-")) {
			
			int span = openTimeValue.split(",").length;
			int hour = 0;
			if(calendar.get(Calendar.MINUTE) > 0) {
				hour = calendar.get(Calendar.HOUR_OF_DAY) + 1;
			} else {
				hour = calendar.get(Calendar.HOUR_OF_DAY);
			}
			
			if(span == 1) {
				if(splitTime(openTimeValue, hour)) {
					return OpenTimeUtil.OPEN;
				}
			} else if(span == 2) {
				if(splitTime(openTimeValue.split(",")[0], hour) || 
				   splitTime(openTimeValue.split(",")[1], hour)) {
				   return OpenTimeUtil.OPEN;
				}
				
			} else if(span == 3) {
				if(splitTime(openTimeValue.split(",")[0], hour) || 
				   splitTime(openTimeValue.split(",")[1], hour) || 
				   splitTime(openTimeValue.split(",")[2], hour)) {
						return OpenTimeUtil.OPEN;
				}
			}
		}
	
		return OpenTimeUtil.CLOSE;
	}
	
	public static boolean splitTime(String openTime,int hour) {
		System.out.println(openTime);
		Double[] time = new Double[2];
		String temp_time = openTime.split("-")[1];
		
		// 2-close-close 这种特殊情况
		if(temp_time.equals("close")) {
			return false;
		} 
		// 3-allday-0.5 这种特殊情况
		if(temp_time.equals("allday")) {
			return true;
		}
		time[0] = Double.parseDouble(openTime.split("-")[1]);
		time[1] = Double.parseDouble(openTime.split("-")[2]);
		if(hour >= time[0] && hour <= time[1]) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(splitTime("2-allday-12", 10));
		
	}
}
