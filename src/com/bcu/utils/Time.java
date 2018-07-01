package com.bcu.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
	//获取当前时间
	 public static Date getNowTime() throws ParseException { 
		 
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 Date dt =new Date();  
		 String dateNowStr = simpleDateFormat.format(dt);
		 Date date = simpleDateFormat.parse(dateNowStr);
		Date nowdate = new java.sql.Date(date.getTime());	
		System.out.println(nowdate);
		return nowdate;
	 }
	 
	
}
