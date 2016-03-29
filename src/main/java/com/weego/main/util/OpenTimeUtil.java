package com.weego.main.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.weego.main.model.BasePOIOpenTime;

public class OpenTimeUtil {
	
	public static String getOpenDesc(List<BasePOIOpenTime> openTimes) {
		if(openTimes == null || openTimes.size() == 0) {
			return "歇业中";
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
			return "歇业中";
		}
		
		String openTimeValue = openTimes.get(index).getValue();
		int span = openTimeValue.split(",").length;
		
		int hour = calendar.get(Calendar.HOUR);
		if(span == 1) {
			if(splitTime(openTimeValue, hour)) {
				return "营业中";
			}
			return "歇业中";
		} else if(span == 2) {
			if(splitTime(openTimeValue.split(",")[0], hour) || 
			   splitTime(openTimeValue.split(",")[1], hour)) {
				return "营业中";
			}
			return "歇业中";
			
		} else if(span == 3) {
			if(splitTime(openTimeValue.split(",")[0], hour) || 
			   splitTime(openTimeValue.split(",")[1], hour) || 
			   splitTime(openTimeValue.split(",")[2], hour)) {
					return "营业中";
			}
			return "歇业中";
		}
	
		return "歇业中";
	}
	
	public static boolean splitTime(String openTime,int hour) {
		Integer[] time = new Integer[2];
		time[0] = Integer.parseInt(openTime.split("-")[1]);
		time[1] = Integer.parseInt(openTime.split("-")[2]);
		if(hour >= time[0] && hour <= time[1]) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		System.out.println(Calendar.SATURDAY);
		System.out.println(calendar);
		System.out.println(calendar.get(Calendar.HOUR));
		
	}
}
