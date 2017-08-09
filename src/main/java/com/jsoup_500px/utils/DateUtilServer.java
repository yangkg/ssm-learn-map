package com.jsoup_500px.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

;
;

/**
 * 日期工具类
 *
 * @author：Kyle.yangkg
 * @create：2017-07-11 上午 9:52
 * ©copyright：www.aisino.com
 */
public class DateUtilServer {

    /**
     * 根据指定日期返回按一定格式格式化的日期字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat dateFormat = getSimpleFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 返回指定日期加上指定天数的日期
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getDateAppend(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) + offset));
        return calendar.getTime();
    }

    public static SimpleDateFormat getSimpleFormat(String pattern) {
        SimpleDateFormat sdft = new SimpleDateFormat(pattern);
        return sdft;
    }


    /**
     * 根据日期格式将字符串转换为日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date stringToDate(String dateStr, String pattern) {
        SimpleDateFormat dateFormat = getSimpleFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static void main(String[] args) {
        Date d = new Date();
        String s = dateToString(d, "HH-mm");
        System.out.println(s);
    }
}
