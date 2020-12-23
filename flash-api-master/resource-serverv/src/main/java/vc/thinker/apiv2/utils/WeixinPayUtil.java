package vc.thinker.apiv2.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import vc.thinker.apiv2.bo.WeiXinPaymetBO;
import vc.thinker.pay.weixin.WeixinConfig;
import vc.thinker.pay.weixin.utils.MD5;
import vc.thinker.pay.weixin.utils.MapUtil;

/**
 * 类型支付工具类
 * @author james
 *
 */
public class WeixinPayUtil {

	public static WeiXinPaymetBO makeWeixinJsPaymet(WeixinConfig config, String content) {
		Document doc = Jsoup.parse(content, "", Parser.xmlParser());

		WeiXinPaymetBO weiXinPaymet = new WeiXinPaymetBO();
		weiXinPaymet.setAppId(config.getWxAppId());
		weiXinPaymet.setPartnerId(config.getTenpayPartner());
		weiXinPaymet.setPrepayId(doc.select("prepay_id").text());
		weiXinPaymet.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
		weiXinPaymet.setTimeStamp(String.valueOf(new Date().getTime() / 1000));
		
		Map<String, String> map = new HashMap<>();
		map.put("appId", weiXinPaymet.getAppId());
		map.put("timeStamp", weiXinPaymet.getTimeStamp());
		map.put("nonceStr", weiXinPaymet.getNonceStr());
		map.put("package","prepay_id="+weiXinPaymet.getPrepayId());
		map.put("signType","MD5");

		String sign = generateSign(map, config.getWxPaysignkey());
		weiXinPaymet.setSign(sign);
		return weiXinPaymet;
	}
	
	public static WeiXinPaymetBO makeWeixinAppPaymet(WeixinConfig config, String content) {
		Document doc = Jsoup.parse(content, "", Parser.xmlParser());

		WeiXinPaymetBO weiXinPaymet = new WeiXinPaymetBO();
		weiXinPaymet.setAppId(config.getWxAppId());
		weiXinPaymet.setPartnerId(config.getTenpayPartner());
		weiXinPaymet.setPrepayId(doc.select("prepay_id").text());
		weiXinPaymet.setNonceStr(UUID.randomUUID().toString().replace("-", ""));
		weiXinPaymet.setTimeStamp(String.valueOf(new Date().getTime() / 1000));

		Map<String, String> map = new HashMap<>();
		map.put("appid", weiXinPaymet.getAppId());
		map.put("partnerid", weiXinPaymet.getPartnerId());
		map.put("prepayid", weiXinPaymet.getPrepayId());
		map.put("noncestr", weiXinPaymet.getNonceStr());
		map.put("timestamp", weiXinPaymet.getTimeStamp());
		
		map.put("package","Sign=WXPay");
		//map.put("signType","MD5");

		String sign = generateSign(map, config.getWxPaysignkey());
		weiXinPaymet.setSign(sign);
		return weiXinPaymet;
	}
	
	public static String generateSign(Map<String, String> map,String key){
    	Map<String, String> orderMap = MapUtil.order(map);

    	String result = MapUtil.mapJoin(orderMap,false,false);
        result += "&key=" + key;
        result = MD5.MD5Encode(result).toUpperCase();

        return result;
    }
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("appId", "wxaf5d44c961355557");
		map.put("timeStamp", "1503475337");
		map.put("nonceStr", "e5e6c601266c48429425c8fba561ac8e");
		map.put("package","prepay_id="+"wx201708231602173cb79149af0253472884");
		map.put("signType","MD5");
		
		System.out.println(generateSign(map, "thinkervcadmin123adminxxxdddffff"));
	}
}
