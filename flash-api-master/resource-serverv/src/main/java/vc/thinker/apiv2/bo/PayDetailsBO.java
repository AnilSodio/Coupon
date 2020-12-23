package vc.thinker.apiv2.bo;

import io.swagger.annotations.ApiModelProperty;

public class PayDetailsBO {
	
	private String alipaPpaySignature;
	
	private WeiXinPaymetBO weiXinPaymet;
	
	@ApiModelProperty("支付的流水号")
	private String sn;
	
	@ApiModelProperty("cashfree支付参数")
	private CashfreePaymentBO cashfreePaymentBO;
	
	@ApiModelProperty("fondy支付参数")
	private FondyPaymetBO fondyPaymetBO;
	
	@ApiModelProperty("apple pay 支付参数")
	private ApplePaymentBO applePaymentBO;
	
	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getAlipaPpaySignature() {
		return alipaPpaySignature;
	}

	public void setAlipaPpaySignature(String alipaPpaySignature) {
		this.alipaPpaySignature = alipaPpaySignature;
	}

	public WeiXinPaymetBO getWeiXinPaymet() {
		return weiXinPaymet;
	}

	public void setWeiXinPaymet(WeiXinPaymetBO weiXinPaymet) {
		this.weiXinPaymet = weiXinPaymet;
	}

	public CashfreePaymentBO getCashfreePaymentBO() {
		return cashfreePaymentBO;
	}

	public void setCashfreePaymentBO(CashfreePaymentBO cashfreePaymentBO) {
		this.cashfreePaymentBO = cashfreePaymentBO;
	}

	public FondyPaymetBO getFondyPaymetBO() {
		return fondyPaymetBO;
	}

	public void setFondyPaymetBO(FondyPaymetBO fondyPaymetBO) {
		this.fondyPaymetBO = fondyPaymetBO;
	}

	public ApplePaymentBO getApplePaymentBO() {
		return applePaymentBO;
	}

	public void setApplePaymentBO(ApplePaymentBO applePaymentBO) {
		this.applePaymentBO = applePaymentBO;
	}
	
}
