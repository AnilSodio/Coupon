package vc.thinker.cabbage.se;

public class CabinetConstants {

	/**
	 * 
		1：苹果线
		2：苹果线/micro-usb，
		3：TYPE C线  
		4：三合一线     
	 */
	public final static String BATTERY_TYPE_1="1";
	public final static String BATTERY_TYPE_2="2";
	public final static String BATTERY_TYPE_3="3";
	public final static String BATTERY_TYPE_4="4";
	
	
	/**
	 * 充电吧
	 * 0 收到消息确认, 通信成功
	 * 1 借出成功反馈 用户成功取走电池，借出成功。
	 * 3 网络延迟, 借出失败, 超过30s判断延迟。
	 * 6 没有符合要求的电池, 借出失败 不带电池ID。 
	 * 7 红外异常。
	 * 8 借出故障 电机故障。
	 * 9 解锁5V失败 
	 * 30 因同步时间不成功而无法执行借订单。
	 * 31 因上一个订单未完成而无法执行新订单。
	 * 32 设备重发3次STATUS=0都没有收到平台应答，通知平台取消该订单。 
	 */
	public final static String CABINET_BORROW_STATUS_0="0";
	public final static String CABINET_BORROW_STATUS_1="1";
	public final static String CABINET_BORROW_STATUS_3="3";
	public final static String CABINET_BORROW_STATUS_6="6";
	public final static String CABINET_BORROW_STATUS_7="7";
	public final static String CABINET_BORROW_STATUS_8="8";
	public final static String CABINET_BORROW_STATUS_9="9";
	public final static String CABINET_BORROW_STATUS_30="30";
	public final static String CABINET_BORROW_STATUS_31="31";
	public final static String CABINET_BORROW_STATUS_32="32";
	
	
	/**
	 * 伏特加
	 * 	22	借出成功
		41	网络超时
		42	无可借电池
		43	未知故障
	 */
	public final static String CABINET_FUTIEJIA_BORROW_STATUS_22="22";
	public final static String CABINET_FUTIEJIA_BORROW_STATUS_41="41";
	public final static String CABINET_FUTIEJIA_BORROW_STATUS_42="42";
	public final static String CABINET_FUTIEJIA_BORROW_STATUS_43="43";

	
	
	/**
	 * 充电柜的状态  1正常，2禁用
	 */
	public final static int CABINET_STATUS_1 = 1;
	public final static int CABINET_STATUS_2 = 2;
}
