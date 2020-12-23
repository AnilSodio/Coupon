package vc.thinker.cabbage.user;

/**
 * 用户押金记录相关状态
 * @author thinker
 *
 */
public class DepositLogConstants {

	//付款
	public static final String TYPE_1 = "1";
	
	//退款申请中
	public static final String TYPE_2 = "2";
	
	//退款成功
	public static final String TYPE_3 = "3";
	
	/** 1,充值中，2，充值成功，3 已退款 */
	public static final Integer PAY_LOG_STATUS_1 = 1;
	public static final Integer PAY_LOG_STATUS_2 = 2;
	public static final Integer PAY_LOG_STATUS_3 = 3;
	
}
