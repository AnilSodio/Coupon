package vc.thinker.opensdk.powerbank.relink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import vc.thinker.opensdk.ReturnCode;
import vc.thinker.opensdk.ReturnMsg;
import vc.thinker.opensdk.powerbank.OpenSDK;
import vc.thinker.opensdk.powerbank.PowerbankStatic;


/**
 * relink充电柜设备的设备接口实现类
 * 通过http与中间件平台通信调用
 * @author wangdawei
 */
public class RelinkOpenSDK implements OpenSDK {
	
	public static final Logger logger = LoggerFactory.getLogger(RelinkOpenSDK.class);
	
	private String _paas_id = StringUtils.EMPTY;
	
	private String _paas_url = StringUtils.EMPTY;
	
	/**
	 * 构造函数
	 */
	public RelinkOpenSDK() {
		
	}
	
	/**
	 * 带参数构造函数
	 */
	public RelinkOpenSDK(String paasId, String paasUrl) {
		this._paas_id = paasId;
		this._paas_url = paasUrl+"relink/";
	}
	
	/**
	 * 登录中间件平台，提交参数
	 * _paas_id，应用id
	 * sns，充电柜id（组）
	 * callback，归还动作的回调地址
	 */
	public ReturnMsg<String> callLink(List<String> sns, String callback) {
		ReturnMsg<String> ret = new ReturnMsg<String>();
		try {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("sid", _paas_id);
			dataMap.put("sns", StringUtils.join(sns, ","));
			dataMap.put("callback", callback);
			dataMap.put("cmd", "login");
			String response = Jsoup.connect(_paas_url).timeout(PowerbankStatic.IOT_PASS_TIMEOUT).data(dataMap).post().html();
			String data = Jsoup.parseBodyFragment(response).text();
			ret= JSON.parseObject(data,new TypeReference<ReturnMsg<String>>(){});
		} catch (Exception e) {
			logger.error(_paas_id + " callLink: ", e);
			ret = new ReturnMsg<String>(ReturnCode.IOT_PAAS_EXCEPTION);
		}
		
		return ret;
	}

	/**
	 * 借出充电宝
	 * _paas_id，应用id
	 * sns，充电柜id（组）
	 */
	public ReturnMsg<RelinkSlot> callBorrow(String sn) {
		ReturnMsg<RelinkSlot>  ret = new ReturnMsg<RelinkSlot>();
		try {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("sid", _paas_id);
			dataMap.put("sn", sn);
			dataMap.put("cmd", "info");
			ret = new ReturnMsg<RelinkSlot>(ReturnCode.IOT_PAAS_NO_SLOT);
			String response = Jsoup.connect(_paas_url).timeout(PowerbankStatic.IOT_PASS_TIMEOUT).data(dataMap).post().html();
			String data = Jsoup.parseBodyFragment(response).text();
			ReturnMsg<RelinkPowerbank> returnMsg = (ReturnMsg<RelinkPowerbank>)JSON.parseObject(data, new TypeReference<ReturnMsg<RelinkPowerbank>>(){});
			// 判断返回码
			if (returnMsg.getRetCode() == 0) {
				RelinkPowerbank rr = returnMsg.getData();
				if (null != rr && !rr.getSlots().isEmpty()) {
					List<RelinkSlot> slots = rr.getSlots();
					for (RelinkSlot slot : slots) {
						 //满足要求的充电宝执行借出命令
						if (slot.getEq() > PowerbankStatic.IOT_EQ_OK) {
							dataMap.put("sid", _paas_id);
							dataMap.put("sn", sn);
							dataMap.put("cmd", "borrow");
							dataMap.put("slot", String.valueOf((slot.getNo())));
							String resBorrow = Jsoup.connect(_paas_url).timeout(PowerbankStatic.IOT_PASS_TIMEOUT).data(dataMap).post().html();
							String dataBorrow = Jsoup.parseBodyFragment(resBorrow).text();
							ret =  JSON.parseObject(dataBorrow, new TypeReference<ReturnMsg<RelinkSlot>>(){});
							break;
						}
					}
				} 
			} 
		} catch (Exception e) {
			logger.error(sn+" callBorrow: ", e);
			ret =  new ReturnMsg<RelinkSlot>(ReturnCode.IOT_PAAS_EXCEPTION);
		}
		
		return ret;
	}

	/**
	 * 获取充电柜的基本信息参数
	 * _paas_id，应用id
	 * sns，充电柜id（组）
	 */
	public ReturnMsg<RelinkPowerbank> callInfo(String sn) {
		ReturnMsg<RelinkPowerbank> ret = new ReturnMsg<RelinkPowerbank>();
		try {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("sid", _paas_id);
			dataMap.put("sn", sn);
			dataMap.put("cmd", "info");
			String response = Jsoup.connect(_paas_url).timeout(PowerbankStatic.IOT_PASS_TIMEOUT).data(dataMap).post().html();
			String data = Jsoup.parseBodyFragment(response).text();
			ret = JSON.parseObject(data, new TypeReference<ReturnMsg<RelinkPowerbank>>(){});
		} catch (Exception e) {
			logger.error(sn+ " callInfo: ", e);
			ret = new ReturnMsg<RelinkPowerbank>(ReturnCode.IOT_PAAS_EXCEPTION);
		}
		
		return ret;
	}

	/**
	 * 弹出充电宝（非用户操作）
	 * data，
	 */
	public ReturnMsg<RelinkSlot> callPop(String sn, int no) {
		ReturnMsg<RelinkSlot> ret = new ReturnMsg<RelinkSlot>();
		try {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("sid", _paas_id);
			dataMap.put("sn", sn);
			dataMap.put("slot", String.valueOf(no));
			dataMap.put("cmd", "pop");
			String response = Jsoup.connect(_paas_url).timeout(PowerbankStatic.IOT_PASS_TIMEOUT).data(dataMap).post().html();
			String data = Jsoup.parseBodyFragment(response).text();
			ret = JSON.parseObject(data, new TypeReference<ReturnMsg<RelinkSlot>>(){});
		} catch (Exception e) {
			logger.error(sn+ " callPop: ", e);
			ret = new ReturnMsg<RelinkSlot>(ReturnCode.IOT_PAAS_EXCEPTION);
		}
		
		return ret;
	}
	
	/**
	 * 归还充电宝
	 * data
	 */
	public ReturnMsg<RelinkPowerbank> callRetrun(String data) {
		ReturnMsg<RelinkPowerbank> ret = new ReturnMsg<RelinkPowerbank>(ReturnCode.NOT_FIND_POWERBANK_DATA);
		if (StringUtils.isNoneBlank(data)) {
			ret= JSON.parseObject(data,new TypeReference<ReturnMsg<RelinkPowerbank>>(){});
			if (ret.getRetCode() == 0) {
				return ret;
			}
		}
		
		return ret;
	}
	
	/**
	 * 主函数-测试区
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		for (int i = 1; i < 9;  i++) {
			String _paas_id = PowerbankStatic.IOT_PAAS_ID;
			String _paas_url = PowerbankStatic.IOT_PAAS_URL;
			RelinkOpenSDK sdk = new RelinkOpenSDK(_paas_id, _paas_url);
			String sn = "RL1H081904660012";
			int no = i;
			ReturnMsg<RelinkSlot> ret = sdk.callPop(sn, no);
			System.err.println(ret);
			Thread.sleep(3000);
		}

		
		
//		RelinkOpenSDK sdk = new RelinkOpenSDK();
//		String _paas_id = "1";
//		String sn = "RL1H081904660012";
//		ReturnMsg<RelinkSlot> ret = sdk.callBorrow(_paas_id, sn);
//		System.err.println(ret);
//		
//		List<String> sns = new ArrayList<String>();
//		sns.add(sn);
//		sns.add(sn);
//		String callback = "";
//		String _paas_id1 = "1";
//		ReturnMsg<String> ret1 = sdk.callLink(_paas_id1,sns,callback);
//		System.err.println(ret1);
	}
}
