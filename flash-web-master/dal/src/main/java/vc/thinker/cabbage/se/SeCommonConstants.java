package vc.thinker.cabbage.se;

/**
 * 充电宝相关常量类
 * @author thinker
 *
 */
public class SeCommonConstants {

	/** 充电柜的状态 1 启用，2禁用*/
	public static final int CABINET_STATUS_ENABLE = 1;
	public static final int CABINET_STATUS_DISABLE = 2;
	
	/** 充电柜的投放状态*/
	public static final Boolean CABINET_IS_DELIVERY_TRUE = true;
	public static final Boolean CABINET_IS_DELIVERY_FALSE = false;
	
	/**
	 * code类型
	 */
	public final static String CODE_SYSCODE="sysCode";//syscode
	
	public final static int USER_SELLER_STATUS_1 = 1;
	public final static int USER_SELLER_STATUS_2 = 2;
	
	/** 用户券类型 '1 未使用 2 已使用', */
	public final static int USER_COUPON_STATUS_1 = 1;
	public final static int USER_COUPON_STATUS_2 = 2;
	
	public final static int USER_REPAIRER_STATUS_1 = 1;
	public final static int USER_REPAIRER_STATUS_2 = 2;
	
	/** '0:已处理，1：未处理，2：无需处理' */
	public final static int FEEDBACK_STATUS_0 = 0;
	public final static int FEEDBACK_STATUS_1 = 1;
	public final static int FEEDBACK_STATUS_2 = 2;
	
	/**问题反馈 类型 1：首页，2：行程中，3：已完成 */
	public final static String FEEDBACK_FEED_TYPE_1 = "1";
	public final static String FEEDBACK_FEED_TYPE_2 = "2";
	public final static String FEEDBACK_FEED_TYPE_3 = "3";
	
	
	public static final String FONDY_RETURN_URL = "_fondy_return_URL";
	
}
