package com.jebms.comm.utils;

import java.util.Calendar;


/**
 * 用于获取当前 年 月
 *
 * @author samt007@qq.com
 * @version 1.0
 * @date 2017年5月27日
 */

public class DateUtil {
	
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
