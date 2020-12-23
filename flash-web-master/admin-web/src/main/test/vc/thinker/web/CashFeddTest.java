package vc.thinker.web;


import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import vc.thinker.pay.util.HttpKitUtils;



/**
 *
 * @author ZhangGaoXiang
 * @time Sep 3, 20194:11:32 PM
 */
public class CashFeddTest {

	public static final String appId = "22988e3703f013ba106ccf48088922";
	
	public static final String secretKey = "f968044e18451c5920c45afe321cf55f125c4e1f";
	
	@Test
	public void createOrder() {
		Map<String, Object> param = Maps.newLinkedHashMap();
		param.put("appId", appId);
		param.put("secretKey", secretKey);
		param.put("orderId", "201909041822-deposit");
		// 金额
		param.put("orderAmount",1);
//		param.put("orderCurrency","CNY");
		//备注
		param.put("orderNote",handlerStr("Depoeit Pay"));
		

		param.put("customerName",handlerStr("Anoop Reddy"));
		param.put("customerPhone","6111122223");
		param.put("customerEmail",handlerStr("zhanggaoxiang@163.com"));
		
		param.put("sellerPhone","");
		
		String notifyUrl= "http://zzx.api.thinker.vc/cashPay/notify";
		String returnUrl= "http://zzx.api.thinker.vc/wx/success";
		
		param.put("notifyUrl",handlerStr(returnUrl));
		param.put("returnUrl",handlerStr(notifyUrl));
		
		param.put("paymentModes","");
		param.put("pc","");
		
		
		String paras = map2Str(param);
		System.out.println(paras);
		String url = "https://api.cashfree.com/api/v1/order/create";
		
		String doPost = HttpKitUtils.doPost(url, paras);
		System.out.println(doPost);
		
		dmeo3(doPost);
		
	}
	
	@Test
	public void getLink() {
		Map<String, Object> param = Maps.newLinkedHashMap();
		param.put("appId", appId);
		param.put("secretKey", secretKey);
		param.put("orderId", "1010101011");
		
		String url = "https://api.cashfree.com/api/v1/order/info/link";

		String map2Str = map2Str(param);
		
		String doPost = HttpKitUtils.doPost(url, map2Str);
		System.out.println(doPost);
	}
	
	@Test
	public void refund() {
		Map<String, Object> param = Maps.newLinkedHashMap();
		param.put("appId", appId);
		param.put("secretKey", secretKey);
		param.put("referenceId", "43358247");
		param.put("refundAmount", "1");
		param.put("refundNote", "test");
		
		String url = "https://api.cashfree.com/api/v1/order/refund";

		String map2Str = map2Str(param);
		
		String doPost = HttpKitUtils.doPost(url, map2Str);
		System.out.println(doPost);
	}
	
	
	@Test
	public void queryRefunds() {
		Map<String, Object> param = Maps.newLinkedHashMap();
		param.put("appId", appId);
		param.put("secretKey", secretKey);
		param.put("startDate", "2019-09-04");
		param.put("endDate", "2019-09-04");
		
		String url = "https://api.cashfree.com/api/v1/refunds";

		String map2Str = map2Str(param);
		
		String doPost = HttpKitUtils.doPost(url, map2Str);
		System.out.println(doPost);
	}
	
	public static String map2Str(Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		param.entrySet().forEach(d->{
			sb.append("&").append(d.getKey()).append("=").append(d.getValue());
		});
		return sb.toString().substring(1);
	}
	
	public String handlerStr(String str) {
		
		if(StringUtils.isNotBlank(str)) {
			return str.replaceAll(":", "%3A").replaceAll("/", "%2F")
					.replaceAll(" ", "%20").replaceAll("@", "%40");
		}
		return null;
	}
	
	public void dmeo3(String resp) {
		JSONObject parseObject = JSONObject.parseObject(resp);
		String status = parseObject.getString("status");
		if("OK".equals(status)) {
			String paymentLink = parseObject.getString("paymentLink");
			paymentLink= paymentLink.replaceAll("\\/", "/");
			System.out.println(paymentLink);
		}
	}
	 
}
