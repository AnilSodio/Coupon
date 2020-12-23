package vc.thinker.cabbage.common;

import java.math.BigDecimal;

public class PayTotalPriceUtil {

	/**
	 * 统一支付金额
	 * 用于区分环境
	 * @param totalPrice
	 * @return
	 */
	public static BigDecimal getTotalPrice(BigDecimal totalPrice){
		return totalPrice;
		//return new BigDecimal(0.01d);//测试使用
	}
}
