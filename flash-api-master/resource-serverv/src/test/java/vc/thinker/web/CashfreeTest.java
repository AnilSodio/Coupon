package vc.thinker.web;
/**
 *
 * @author ZhangGaoXiang
 * @time Sep 29, 20192:49:50 PM
 */

import java.util.Map;

import org.junit.Test;

import com.alibaba.druid.util.HttpClientUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import vc.thinker.pay.tenpay.util.HttpClientUtil;
import vc.thinker.pay.util.HttpKitUtils;
import vc.thinker.weixin.kit.HttpKit;

public class CashfreeTest {

	@Test
	public void getToken() {

		String url = "https://api.cashfree.com/api/v2/cftoken/order";
		JSONObject object = new JSONObject();
 
		object.put("orderId", "deposit-201910091619011_12");
		object.put("orderAmount", "1");
		object.put("orderCurrency", "INR");
		
		Map<String, String> headers = Maps.newHashMap();
		headers.put("x-client-id", "22988e3703f013ba106ccf48088922");
		headers.put("x-client-secret", "f968044e18451c5920c45afe321cf55f125c4e1f");
		
		try {
			String post = HttpKit.post(url, object.toString(), headers);
			System.out.println(post);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	public static String map2Str(Map<String, Object> param) {
		StringBuffer sb = new StringBuffer();
		param.entrySet().forEach(d -> {
			sb.append("&").append(d.getKey()).append("=").append(d.getValue());
		});
		return sb.toString().substring(1);
	}
}
