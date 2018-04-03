package com.jebms.comm.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 用于获取当前 年 月
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年5月27日
 */

public class DateUtil {
	
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
	
	/**
	 * 获取年份
	 * @return
	 */
	public static int getYear() {

		Calendar cal = Calendar.getInstance();
		
		return  cal.get(Calendar.YEAR);
	}
	
	/**
	 * 获取日期
	 * @return
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		
		return  cal.get(Calendar.MONTH)+1;		
	}
	
}
