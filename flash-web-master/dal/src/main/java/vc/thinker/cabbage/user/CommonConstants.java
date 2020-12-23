package vc.thinker.cabbage.user;

import vc.thinker.sys.contants.SysUserContant;

public class CommonConstants extends SysUserContant{
	
	public static final String SMS_SING_NAME_SERVER = "基础平台";
	
	public static final String SMS_VALIDATE_CODE = "sms_validate_code";
	
	public static final String SMS_ILLEGAL_PARKING_NOTICE_CODE = "illegal_parking_notice";

	public static final String SMS_UN_UNLOCK_NOTICE_CODE = "un_unlock_notice";
	
	public static final String SMS_FAULT_NOTICE_CODE = "fault_notice";
	
	/** 用户状态：0正常，1冻结 */
	public static final String USER_STATUS_NORMAL = "1";
	public static final String USER_STATUS_FREEZE = "2";
	
	/**
	 * 商户状态
	 */
	public static final Integer SELLER_STATUS_NORMAL = 1;
	public static final Integer SELLER_STATUS_FREEZE = 2;
	
	
	/** 会员状态：1正常，2未激活 */
	public static final String MEMBER_STATUS_ZC = "1";
	public static final String MEMBER_STATUS_WJH = "2";
	public static final String MEMBER_STATUS_JY = "3";
	
	/** 充值、提现、消费、赠送 */
	public final static String MONEY_LOG_RECHARGE = "recharge";
	public final static String MONEY_LOG_GIVE = "give"; //赠送
	public final static String MONEY_LOG_CASH = "Cash-Out";
	public final static String MONEY_LOG_CONSUME = "consume";
	public final static String MONEY_LOG_REBATE = "Revenue"; //反润
	public final static String MONEY_LOG_SELL = "sell";
	
	/**
	 * 平台收益类型 充值
	 */
	public final static String PLATFORM_REVENUE_LOG_RECHARGE = "recharge";
	
	/**
	 *  平台收益类型 反润
	 */
	public final static String PLATFORM_REVENUE_LOG_REBATE = "rebate";
	
	/**
	 *  平台收益类型 订单
	 */
	public final static String PLATFORM_REVENUE_LOG_ORDER = "order";
	
	/**
	 *  平台收益类型 vip购买
	 */
	public final static String PLATFORM_REVENUE_LOG_VIP = "vip";
	
	/**
	 * 基础会员费
	 */
	public final static String PLATFORM_REVENUE_LOG_BASIC_VIP = "basic_vip";
	
	
	
	/** 收款支付状态，-1为未审核，0为等待支付，1为支付完成, 2审核不通过 */
	public final static String MONEY_CASH_STATUS_NO_CHECK = "-1";
	public final static String MONEY_CASH_STATUS_CHECK_NO = "2";
	public final static String MONEY_CASH_STATUS_NO_PAY = "0";
	public final static String MONEY_CASH_STATUS_PAY = "1";
	
	/** 支付状态:1 为等待支付，2为支付成功 */
	public final static Integer MONEY_RECHARGE_STATUS_WAIT_PAY = 1;
	public final static Integer MONEY_RECHARGE_STATUS_PAY_SUCCESS = 2;
	
	/** 支付状态:d天，m月，y年 */
	public final static String BUY_WORD_UNIT_DAY = "d";
	public final static String BUY_WORD_UNIT_MONTH = "m";
	public final static String BUY_WORD_UNIT_YEAR = "y";
	
	
	/**
	 * 账户类型 1自定义 
	 * 2 为手机, 
	 * 3 微信公共平台 ,
	 * 4 微信开放平台,
	 * 5 微信小程序,
	 * 6 qq ,
	 * 7 微博,
	 * 8 facebook,
	 * 9邮箱
	 * 10 google
	 */
	public final static String ACCOUNT_TYPE_1="1";
	public final static String ACCOUNT_TYPE_2="2";
	public final static String ACCOUNT_TYPE_3="3";
	public final static String ACCOUNT_TYPE_4="4";
	public final static String ACCOUNT_TYPE_5="5";
	public final static String ACCOUNT_TYPE_6="6";
	public final static String ACCOUNT_TYPE_7="7";
	public final static String ACCOUNT_TYPE_8="8";
	public final static String ACCOUNT_TYPE_9="9";
	public final static String ACCOUNT_TYPE_10="10";
	
	/**
	 * 支付类型，押金
	 */
	public final static String PAY_TYPE_DEPOSIT="deposit";
	
	/**
	 * 支付类型，购买vip
	 */
	public final static String PAY_TYPE_VIP="vip";
	
	/**
	 * 支付类型，余额充值
	 */
	public final static String PAY_TYPE_BALANCE="balance";
	
	public final static String PAY_TYPE_ORDER="order";
	
	/**
	 * 支付类型 基础会员费
	 */
	public final static String PAY_TYPE_BASIC_COST = "basic_cost";
	
	/**
	 * 支付类型 充电宝购买
	 */
	public final static String PAY_TYPE_PB_BUY="pb_buy";
	
	/**
	 * 认证状态
	 */
	public final static String MEMBER_AUTH_STATUS_YET="0";//未认证
	public final static String MEMBER_AUTH_STATUS_ING="1";//认证中
	public final static String MEMBER_AUTH_STATUS_SUC="2";//认证成功
	public final static String MEMBER_AUTH_STATUS_FAIL="3";//认证失败
	
	/**
	 * 认证审批状态
	 */
	public final static Integer MEMBER_AUTH_APPLY_STATUS_ING=1;//认证中
	public final static Integer MEMBER_AUTH_APPLY_STATUS_SUC=2;//认证成功
	public final static Integer MEMBER_AUTH_APPLY_STATUS_FAIL=3;//认证失败
	
	/**
	 * 认证步骤
	 */
	public final static Integer MEMBER_AUTH_STEP_1=1;//绑定手机
	public final static Integer MEMBER_AUTH_STEP_2=2;//充值
	public final static Integer MEMBER_AUTH_STEP_3=3;//实名
	public final static Integer MEMBER_AUTH_STEP_4=4;//认证通过
	
	/**
	 * 押金logtype
	 */
	public final static String MEMBER_DEPOSIT_TYPE_1="1";//1付款成功  
	public final static String MEMBER_DEPOSIT_TYPE_2="2";//2 退款中
	public final static String MEMBER_DEPOSIT_TYPE_3="3";//3.退款成功
	public final static String MEMBER_DEPOSIT_TYPE_4="4";//4.订单抵扣
	
	/**
	 * 押金充值流水状态
	 */
	public final static Integer DEPOSIT_LOG_STATUS_1=1;//支付中
	public final static Integer DEPOSIT_LOG_STATUS_2=2;//支付成功
	
	/**
	 * 购买会员卡流水状态
	 */
	public final static Integer VIP_PAY_LOG_STATUS_1=1;//支付中
	public final static Integer VIP_PAY_LOG_STATUS_2=2;//支付成功
	
	/**
	 * 购买会员卡流水状态
	 */
	public final static Integer BALANCE_PAY_LOG_STATUS_1=1;//支付中
	public final static Integer BALANCE_PAY_LOG_STATUS_2=2;//支付成功
	
	/**
	 * 会员卡单位  day 天， hour 小时 
	 */
	public final static String MEMBERSHIP_CARD_UNIT_DAY="day";
	public final static String MEMBERSHIP_CARD_UNIT_HOUR="hour";
	
	/**
	 * 1 表示全国
	 */
	public final static String AREA_1 = "1";
	
	/**
	 * App 客户端 clinet id,个人端
	 */
	public final static String ROLE_MEMBER_USER ="3000001";
	
	/**
	 * 用户类型， 个人用户
	 */
	public final static int USER_TYPE_2=2;
	
	/**
	 * 用户类型， 商户
	 */
	public final static int USER_TYPE_3=3;
	
	/**
	 * 支付方式，余额
	 */
	public final static String PAYMENT_MARK_BALANCE="balance";
	
	/**
	 * http 头 api-agent
	 */
	public final static String HTTP_HEAD_API_AGENT="api-agent";
	
	/**
	 * 启动页广告图片
	 */
	public final static Integer AD_TYPE_START_PAGE = 1;
	
	/**
	 * 首页广告图片
	 */
	public final static Integer AD_TYPE_HOME_PAGE = 2;
	
	/** 押金支付状态，1：支付中，2：支付成功，3：退款审核中，4：退款成功, 5 订单抵扣*/
	public static final Integer DEPOSIT_PAY_STATUS_1 = 1;
	public static final Integer DEPOSIT_PAY_STATUS_2 = 2;
	public static final Integer DEPOSIT_PAY_STATUS_3 = 3;
	public static final Integer DEPOSIT_PAY_STATUS_4 = 4;
	public static final Integer DEPOSIT_PAY_STATUS_5 = 5;
	
	public final static String CODE_TYPE_INVITION="invition";//邀请码
	
	/**
	 * 用户注册渠道 安卓
	 */
	public final static String USER_REGIST_CHANNEL_Android = "Android";
	
	/**
	 * 用户注册渠道 苹果
	 */
	public final static String USER_REGIST_CHANNEL_IOS = "IOS";
	
	/**
	 * 用户注册渠道 平台
	 */
	public final static String USER_REGIST_CHANNEL_PLATFORM = "platform";
	
	public final static int USER_SEX_1 = 1;
	public final static int USER_SEX_2 = 2;
	
	/** 币种 CNY 中国大陆，MYR 马来西亚，SGD 新加坡*/
	public final static String CURRENCY_CNY = "CNY";
	public final static String CURRENCY_MYR = "MYR";
	public final static String CURRENCY_SGD = "SGD";
	
	public final static String INCOME_TYPE_PLATFORM = "platform";
	
	
	public final static Integer REFEREE_STATUS_1 = 1;
	public final static Integer REFEREE_STATUS_2 = 2;
	
	/** 代理商状态  1 正常，2禁用*/
	public final static Integer AGENT_STATUS_1 = 1;
	public final static Integer AGENT_STATUS_2 = 2;
	
	public final static Integer BASIC_COST_PAY_1 = 1;
	public final static Integer BASIC_COST_PAY_2 = 2;
	
	public final static String CLIENT_TYPE_IPHONE = "iPhone";
	public final static String CLIENT_TYPE_ANDROID = "Android";
	public final static String CLIENT_TYPE_WX_APPLET = "wx-applet";
	
	public final static String SUCCESS = "success";
	public final static String BIG_SUCCESS = "SUCCESS";
	public final static String FAIL = "fail";
}
