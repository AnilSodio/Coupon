package vc.thinker.cabbage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class DateTimeUtils {

	/**
	 * 获取当前的系统时间
	 * @return (yyyy-MM-dd HH:mm:ss)
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前的系统时间
	 * @return (yyyy-MM-dd)
	 */
	public static String getDateMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前的系统时间
	 * @return (yyyyMMddHHmmss)
	 */
	public static String getDateTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前的系统时间
	 * return (yyyyMMdd)
	 */
	public static String getDateMonthString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	
	public static String getDateTimeMils() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	
	public static String dateFormatString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 返回指定时间 指定天数以后的时间 
	 * @param date 目标时间
	 * @param day 目标天数
	 * @return
	 */
	public static Date getDateAfter(Date date, int day) {  
	        Calendar now = Calendar.getInstance();  
	        now.setTime(date);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
	        return now.getTime();  
	}  
	
	 /**
	  * 返回一个59分59秒的时间
	  * @return
	  */
	 public static Date get59SecondDate() {  
	        Calendar para = Calendar.getInstance(java.util.Locale.CHINA);  
	        para.setTime(new Date());  
	        para.set(Calendar.HOUR_OF_DAY, 23);  
	        para.set(Calendar.MINUTE, 59);  
	        para.set(Calendar.SECOND, 58);  

	        return para.getTime();
	    }
	
	public static void main(String[] args) {
		System.out.println(getDateAfter( new Date(),3));
	}
	
	/**
	 * 将制定的字符串转换成日期格式（59分，59秒）
	 * @param date
	 * @return 
	 * @throws ParseException 
	 */
	public static Date getAppoint59SecondDate(String date) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(StringUtils.isEmpty(date)){
			return null;
		}
			
		Date parseDate  = sdf.parse(date);
		parseDate.setHours(23);
		parseDate.setMinutes(59);
		parseDate.setSeconds(59);
		return parseDate;
	}

	public static Date getAppointDateMonth(String dateMonth) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateMonth);
	}
}
