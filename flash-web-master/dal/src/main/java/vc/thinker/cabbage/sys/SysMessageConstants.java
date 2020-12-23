package vc.thinker.cabbage.sys;

public class SysMessageConstants {
	
	/**
	 * 对手机端的msg type key
	 */
	public static final String MOBILE_MESSAGE_TYPE_KEY="msgType";
	
	/**
	 * app logout 系统消息
	 */
	public static final String MOBILE_MESSAGE_LOGOUT="99999";
	
	/**
	 * 订单创建成功
	 */
	public static final String MOBILE_MESSAGE_UM_ORDER_CREATE="order_create";
	
	/**
	 * 订单结束
	 */
	public static final String MOBILE_MESSAGE_UM_ORDER_END="order_end";
	
	/**
	 * 用户审批消息
	 */
	public static final String MOBILE_MESSAGE_UM_MEMBER_AUTH_APPLY="10003";
	
	/**
	 *  维保消息业务类型（31:工单消息）
	 */
	public static final Integer SYS_MESSAGE_BIZ_TYPE_31=31;
	
	/**
	 * 图文状态 
	 * 1：未生效，2 生效，3已失效
	 */
	public final static Integer IMATE_TEXT_STATUS_1 = 1;
	public final static Integer IMATE_TEXT_STATUS_2 = 2;
	public final static Integer IMATE_TEXT_STATUS_3 = 3;
	
	/**
	 * 图文类型：
	 * 	1：普通图文
	 *  2：首页广告
	 */
	public final static Integer IMAGE_TEXT_TYPE_1 = 1;
	public final static Integer IMAGE_TEXT_TYPE_2 = 2;
}
