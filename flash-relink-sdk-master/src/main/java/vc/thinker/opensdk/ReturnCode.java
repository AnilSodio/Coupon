package vc.thinker.opensdk;


/**
 * 
 * @author wangdawei
 *
 */
public class ReturnCode {
	
	/**
	 * 错误码
	 */
	private int retCode;
	/**
	 * 错误信息
	 */
	private String retMsg;
	
	// 按照模块定义CodeMsg
	public static ReturnCode SUCCESS = new ReturnCode(0, "成功");
	public static ReturnCode IOT_PAAS_EXCEPTION = new ReturnCode(50000, "中间件平台：服务状态异常");
	public static ReturnCode iOT_PAAS_PARAMS_ERR = new ReturnCode(50001, "中间件平台：接口参数效验不通过");
	public static ReturnCode IOT_PAAS_SN_ERR = new ReturnCode(50002, "中间件平台：sn不存在");
	public static ReturnCode IOT_PAAS_SN_NO_AUTHORIZED = new ReturnCode(50003, "中间件平台：没有权限操作此sn");
	public static ReturnCode IOT_PAAS_CMD_FAILURE = new ReturnCode(50004, "中间件平台：借出/弹出指令操作失败");
	public static ReturnCode IOT_PAAS_NO_SLOT = new ReturnCode(50005, "中间件平台：没有可用的充电宝");
	public static ReturnCode IOT_PAAS_SN_OFFLINE = new ReturnCode(50006, "中间件平台：设备离线");
	// 
	public static ReturnCode IOT_PAAS_TIMEOUT = new ReturnCode(50099, "中间件平台：通信超时");
	public static ReturnCode IOT_PAAS_UNKNOWN_ERR = new ReturnCode(50100, "中间件平台：未知错误");
	
	public static ReturnCode NOT_FIND_POWERBANK_DATA = new ReturnCode(10001, "查找不到对应充电柜数据");
	public static ReturnCode NOT_FIND_SLOT_DATA = new ReturnCode(10002, "查找不到对应充电宝数据");
//	public static ReturnCode COMM_ACTION_FAILED = new ReturnCode(10004, "通信调用失败");
	

	private ReturnCode(int retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public int getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}