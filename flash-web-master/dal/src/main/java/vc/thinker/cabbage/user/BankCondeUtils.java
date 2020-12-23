package vc.thinker.cabbage.user;

import java.util.HashMap;

import java.util.Map;

public class BankCondeUtils {

	private static Map<String, String> paraMap = new HashMap<String, String>();
	
	static {
		
		paraMap.put("工商银行", "1002");
		paraMap.put("农业银行", "1005");
		paraMap.put("中国银行", "1026");
		paraMap.put("建设银行", "1003");
		paraMap.put("招商银行", "1001");
		paraMap.put("邮储银行", "1066");
		paraMap.put("交通银行", "1020");
		paraMap.put("浦发银行", "1004");
		paraMap.put("民生银行", "1006");
		paraMap.put("兴业银行", "1009");
		paraMap.put("平安银行", "1010");
		paraMap.put("中信银行", "1021");
		paraMap.put("华夏银行", "1025");
		paraMap.put("光大银行", "1022");
		paraMap.put("北京银行", "1032");
		paraMap.put("宁波银行", "1056");
	}
	
	public static String getCode(String name) {
		return paraMap.get(name);
	}
	
}
