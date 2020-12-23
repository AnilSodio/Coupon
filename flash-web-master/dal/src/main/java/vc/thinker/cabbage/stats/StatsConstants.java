package vc.thinker.cabbage.stats;

import org.apache.commons.lang3.StringUtils;

public class StatsConstants {
	
	/**
	 * 优惠类型 1:无优惠 2:会员卡优惠 3:优惠券优惠
	 */
	public static final Integer DISCOUNT_TYPE_1=1;
	
	public static final Integer DISCOUNT_TYPE_2=2;
	
	public static final Integer DISCOUNT_TYPE_3=3;
	
	/**
	 * 性别 1男 2女
	 */
	public static final Integer SEX_1=1;
	
	public static final Integer SEX_2=2;
	
	/**
	 * 押金 1:充值  2:退款
	 */
	public static final Integer DEPOSIT_TYPE_1=1;
	
	public static final Integer DEPOSIT_TYPE_2=2;
	
	public static final String CLIENT_TYPE_ANDROID="android";
	
	public static final String CLIENT_TYPE_IOS="ios";
	
	public static final String CLIENT_TYPE_IPHONE="iphone";
	
	public static final String CLIENT_TYPE_IPAD="ipad";
	
	public static final String CLIENT_TYPE_WX_APPLET="wx-applet";
	
	/**
	 * 统计类型
	 */
	public static final String STATS_TYPE_TRIP_NUM="tripNum";
	
	public static final String STATS_TYPE_DISTANCE="distance";
	
	public static final String STATS_TYPE_CONSUME="consume";
	
	public static final String STATS_TYPE_REGIST_NUM="registNum";
	
	public static final String STATS_TYPE_DEPOSIT="deposit";
	
	public static final String STATS_TYPE_VIP_PAY="vipPay";
	
	public static String getClientName(String type){
		if(StringUtils.isEmpty(type)){
			return "未知";
		}
		switch(type){
			case CLIENT_TYPE_ANDROID:
				return "安卓";
			case CLIENT_TYPE_IOS:
				return "苹果";
			case CLIENT_TYPE_IPHONE:
				return "苹果";
			case CLIENT_TYPE_IPAD:
				return "苹果ipad";
			case CLIENT_TYPE_WX_APPLET:
				return "微信小程序";
			default:
				return "未知";
		}
	}
	
	public static String getSexName(Integer sex){
		if(sex==null){
			return "未知";
		}
		switch(sex){
			case 1:
				return "男";
			case 2:
				return "女";
			default:
				return "未知";
		}
	}
	
	public static String getTypeName(String type){
		if(StringUtils.isEmpty(type)){
			return "未知";
		}
		switch(type){
			case STATS_TYPE_TRIP_NUM:
				return "行程次数";
			case STATS_TYPE_DISTANCE:
				return "骑行距离";
			case STATS_TYPE_CONSUME:
				return "消费金额";
			case STATS_TYPE_REGIST_NUM:
				return "注册人数";
			case STATS_TYPE_DEPOSIT:
				return "充押金金额";
			case STATS_TYPE_VIP_PAY:
				return "充会员卡金额";
			default:
				return "未知";
		}
	}
	
	public static String getTypeUnit(String type){
		if(StringUtils.isEmpty(type)){
			return "未知";
		}
		switch(type){
			case STATS_TYPE_TRIP_NUM:
				return "次";
			case STATS_TYPE_DISTANCE:
				return "km";
			case STATS_TYPE_CONSUME:
				return "元";
			case STATS_TYPE_REGIST_NUM:
				return "人";
			case STATS_TYPE_DEPOSIT:
				return "元";
			case STATS_TYPE_VIP_PAY:
				return "元";
			default:
				return "未知";
		}
	}
}
