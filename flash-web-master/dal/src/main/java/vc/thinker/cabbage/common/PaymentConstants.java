package vc.thinker.cabbage.common;

public class PaymentConstants {
    /**
     * 支付方式标识代码,alipay为支付宝，
     * alipayB 网银支付,
     * alipayC 信用卡支付
		wxqrpay 微信扫码支付
		wx_h5	微信h5支付
     */
	public static final String PAYMENT_MARK_ALIPAY="alipay";
	public static final String PAYMENT_MARK_ALIPAYB="alipayB";
	public static final String PAYMENT_MARK_ALIPAYC="alipayC";
	public static final String PAYMENT_MARK_BALANCE="balance";
	public static final String PAYMENT_MARK_ALIPAY_APP="alipay_app";
	public static final String PAYMENT_MARK_WX_QR_PAY="wxqrpay";
	public static final String PAYMENT_MARK_WX_H5_PAY="wx_h5";
	public static final String PAYMENT_MARK_WX_JS_PAY="wx_js";
	public static final String PAYMENT_MARK_WX_APP="wx_app";
	public static final String PAYMENT_MARK_STRIPE="stripe";
	public static final String PAYMENT_MARK_CASHFREE="cashfree";
	public static final String PAYMENT_MARK_FONDY="fondy";
	public static final String PAYMENT_MARK_APPLE_PAY="apple_pay";
	public static final String PAYMENT_MARK_FONDY_APPLE_PAY="fondy_apple_pay";
	
	
	public static String getPaymentName(String payment){
		if(payment.equals("alipay")){
			return "支付宝";
		}else if(payment.equals("alipayB")){
			return "网银支付";
		}else if(payment.equals("alipayC")){
			return "信用卡支付";
		}else if(payment.equals("wxqrpay")){
			return "微信扫码支付";
		}else if(payment.equals("wx_h5")){
			return "微信h5支付";
		}else if(payment.equals("alipay_app")){
			return "支付宝App支付";
		}else if(payment.equals("wx_app")){
			return "微信App支付";
		}else if(payment.equals("balance")){
			return "余额支付";
		}else{
			return "未知";
		}
	}
}
