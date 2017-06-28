package com.yangkg.ssm.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getCurDate(String formatStyle) {
		DateFormat format1 = new SimpleDateFormat(formatStyle);
		return format1.format(new Date());
	}
	
	public static String getFormatDate(Date date,String formatStyle) {
		DateFormat format1 = new SimpleDateFormat(formatStyle);
		return format1.format(date);
	}
	public static String getFormatDate(String date,String formatStyle) {
		DateFormat format1 = new SimpleDateFormat(formatStyle);
		return format1.format(date);
	}

	public static Timestamp getCurDate(){
		return new Timestamp(System.currentTimeMillis());
	}

	public static int getCurMoth(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH)+1;
	}
	
	public static Date getDate(Timestamp timestamp){
		return new Date(timestamp.getTime());
	}
	
	public static String dateToString(Timestamp timestamp,String formatStyle){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
		return sdf.format(new Date(timestamp.getTime()));
	}
	
	public static Timestamp getTimestamp(String date){
		return Timestamp.valueOf(date);
	}
	
	public static Timestamp strToTimestamp(String dateStr,String sourceFormatStyle,String targetFormatStyle) throws ParseException{
		SimpleDateFormat format = new SimpleDateFormat(sourceFormatStyle);
		Date date = format.parse(dateStr);
		format = new SimpleDateFormat(targetFormatStyle);
		return getTimestamp(format.format(date));
	}
}
