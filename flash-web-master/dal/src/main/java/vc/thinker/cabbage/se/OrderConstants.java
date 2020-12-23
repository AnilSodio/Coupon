package vc.thinker.cabbage.se;

/**
 * 雨伞常量类
 * @author thinker
 *
 */
public class OrderConstants {
	/**
	 * 状态  10:创建中 20: 创建失败 30:进行中 40:未支付 50:已支付,押金抵扣
	 */
	public final static Integer ORDER_STATUS_10 = 10;
	public final static Integer ORDER_STATUS_20 = 20;
	public final static Integer ORDER_STATUS_30 = 30;
	public final static Integer ORDER_STATUS_40 = 40;
	public final static Integer ORDER_STATUS_50 = 50;
	public final static Integer ORDER_STATUS_60 = 60;
	
	/**
	 * 还的类型 cabinet:机柜, platform:平台
	 */
	public final static String RETURN_TYPE_CABINET = "cabinet";
	public final static String RETURN_TYPE_PLATFORM = "platform";
	
	/**
	 * 支付类型：免费：free  现金： cash  会员卡：vip ,balance 余额 ,pb_buy 充电宝购买
	 */
	public final static String PAY_TYPE_FREE="free";
	public final static String PAY_TYPE_CASH="cash";
	public final static String PAY_TYPE_VIP="vip";
	public final static String PAY_TYPE_BALANCE="balance";
	public final static String PAY_TYPE_PB_BUY="pb_buy";
}
