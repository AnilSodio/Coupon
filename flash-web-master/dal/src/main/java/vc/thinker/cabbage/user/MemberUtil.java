package vc.thinker.cabbage.user;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class MemberUtil {

	/**
	 * 秒格式化输出会员到期时间
	 * @param second
	 * @return
	 */
	public static String formatSecond(Long second) {
        long day1=second/(24*3600);  
       long hour1=second%(24*3600)/3600;  
       long minute1=second%3600/60;  
       long second1=second%60;
       StringBuilder result= new StringBuilder("");
       if(day1 > 0){
    	   result.append(day1).append("天");
       }
       if(hour1 > 0){
    	   result.append(hour1).append("小时");
       }
       if(minute1 > 0){
    	   result.append(minute1).append("分");
       }
       if(second1 > 0){
    	   if(StringUtils.isBlank(result.toString())){
    		   result.append("不足一分钟");
    		   
    	   }
       }
       return result.toString();
	}
	/**
	 * 秒格式化输出会员到期时间
	 * @param second
	 * @return
	 */
	public static String formatSecond(Long second,Locale locale) {
		long day1=second/(24*3600);  
		long hour1=second%(24*3600)/3600;  
		long minute1=second%3600/60;  
		long second1=second%60;
		StringBuilder result= new StringBuilder("");
		if(day1 > 0){
			 if("en".equals(locale.getLanguage())){
				 result.append(day1).append("d");
			 }else{
				 result.append(day1).append("天");
			 }
		}
		if(hour1 > 0){
			 if("en".equals(locale.getLanguage())){
				 result.append(hour1).append("h");
			 }else{
				 result.append(hour1).append("小时");
			 }
		}
		if(minute1 > 0){
			 if("en".equals(locale.getLanguage())){
				 result.append(minute1).append("min");
			 }else{
				 result.append(minute1).append("分");
			 }
		}
		if(second1 > 0){
			if(StringUtils.isBlank(result.toString())){
				 if("en".equals(locale.getLanguage())){
					 result.append("Less than a minute");
				 }else{
					result.append("不足一分钟");
				 }
			}
		}
		return result.toString();
	}
}
