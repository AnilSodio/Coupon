package vc.thinker.apiv2.bo;
/**
 *
 * @author ZhangGaoXiang
 * @time Dec 20, 201911:59:08 AM
 */
public class ApplePaymentBO {

	
	private String orderId;
	
	private String amount;
	
	private String currency;
	
	private String serverCallBackUrl;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getServerCallBackUrl() {
		return serverCallBackUrl;
	}

	public void setServerCallBackUrl(String serverCallBackUrl) {
		this.serverCallBackUrl = serverCallBackUrl;
	}
	
}
