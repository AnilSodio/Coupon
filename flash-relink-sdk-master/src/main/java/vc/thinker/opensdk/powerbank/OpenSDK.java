package vc.thinker.opensdk.powerbank;

import java.io.IOException;
import java.util.List;

import vc.thinker.opensdk.ReturnMsg;

/**
 * 与中间件平台-数据交互接口
 * @author wangdawei
 *
 */
public interface OpenSDK {
	
	/**
	 * 登录中间件平台
	 * @param host
	 * @param callRetrun
	 * @return
	 */
	public ReturnMsg<?> callLink(List<String> sns, String callback) ;
	
	/**
	 * 借充电宝
	 * @param sn
	 * @return
	 */
	public ReturnMsg<?> callBorrow(String sn) ;
	
	/**
	 * 获取充电柜基本信息
	 * @param sn
	 * @return
	 * @throws IOException 
	 */
	public ReturnMsg<?> callInfo(String sn) ;
	
	
	/**
	 * 还充电宝
	 * @param sn
	 * @return
	 */
	public ReturnMsg<?> callRetrun(String data) ;
	
}