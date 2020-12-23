package vc.thinker.cabbage.user;

public class UserMoneyConstants {
	
	/** 充值、提现、消费、赠送 */
	public final static String MONEY_LOG_RECHARGE = "recharge";
	public final static String MONEY_LOG_GIVE = "give"; //赠送
	public final static String MONEY_LOG_CASH = "cash";
	public final static String MONEY_LOG_CONSUME = "consume";
	public final static String MONEY_LOG_REBATE = "rebate";
	public final static String MONEY_LOG_SELL = "sell";
	
	/** 收款支付状态，-1为未审核，0为等待支付，1为支付完成, 2审核不通过 */
	public final static String MONEY_CASH_STATUS_NO_CHECK = "-1";
	public final static String MONEY_CASH_STATUS_CHECK_NO = "2";
	public final static String MONEY_CASH_STATUS_NO_PAY = "0";
	public final static String MONEY_CASH_STATUS_PAY = "1";
	
	/**
	 * 会员充值流水状态
	 */
	public final static Integer BALANCE_PAY_LOG_STATUS_1=1;//支付中
	public final static Integer BALANCE_PAY_LOG_STATUS_2=2;//支付成功
	
	/** 支付状态:1 为等待支付，2为支付成功 */
	public final static Integer MONEY_RECHARGE_STATUS_WAIT_PAY = 1;
	public final static Integer MONEY_RECHARGE_STATUS_PAY_SUCCESS = 2;
	
	public final static String LOG_TYPE_CASH = "Cash-Out";
	public final static String LOG_TYPE_ORDER_REBATE = "order_rebate";
	
	public final static String CASH_PAYMENT_TYPE_ALIPAY = "alipay";
	public final static String CASH_PAYMENT_TYPE_CHINABANK = "chinabank";
	
	/**
	 * cash_user_type 
	 * 1 商户
	 * 2 推荐人
	 * 3 代理商
	 */
	public final static String CASH_USER_TYPE_SELLER= "1";
	public final static String CASH_USER_TYPE_REFEREE= "2";
	public final static String CASH_USER_TYPE_AGENT= "3";
}
