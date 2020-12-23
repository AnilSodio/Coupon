package vc.thinker.apiv2.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author ZhangGaoXiang
 * @time Oct 17, 20194:28:46 PM
 */
public class PayNotifyVO {

	@NotBlank
	private String orderId;
	@NotBlank
	private String referenceId;
	@NotBlank
	private String orderAmount;
	@NotBlank
	private String txStatus;
	@NotBlank
	private String txMsg;
	@NotBlank
	private String txTime;
	@NotBlank
	private String paymentMode;
	@NotBlank
	private String signature;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getTxStatus() {
		return txStatus;
	}
	public void setTxStatus(String txStatus) {
		this.txStatus = txStatus;
	}
	public String getTxMsg() {
		return txMsg;
	}
	public void setTxMsg(String txMsg) {
		this.txMsg = txMsg;
	}
	public String getTxTime() {
		return txTime;
	}
	public void setTxTime(String txTime) {
		this.txTime = txTime;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
}
