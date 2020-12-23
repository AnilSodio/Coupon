package vc.thinker.cabbage.common;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.sinco.common.Hashids;

public class OrderCodeUtils {

	private static final Hashids orderIdHashId = new Hashids("nomo-2.0.0 order code mnbvcxzlkjhgfdsa",4,"12345678900123456789abcdef");
	/**
	 * 产生工单号
	 * @return
	 */
	public static String getOrderCode(Long id){
		//组件id,为2位
		String cidStr=orderIdHashId.encode(id);
		if(cidStr.length() > 5){
			cidStr=cidStr.substring(0,5);
		}
		return DateFormatUtils.format(new Date(), "yMMddHHmmss")+cidStr;
	}
}
