package vc.thinker.cabbage.user.vo;

import java.util.Date;

public class VipPayLogVO {

	private Long uid;
	
	private Date ltTime;
	
	private Date gtTime;
	
	private String nickname;
	
	private String mobile;
	
	private String startTime;
	
	private String endTime;
	
	private Integer status;
	
	private String cardName;
	
	private String name;
	
	private String payOrderCode;
	
	private String outOrderId;
	
	private String paymentMark;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Date getLtTime() {
		return ltTime;
	}
	public void setLtTime(Date ltTime) {
		this.ltTime = ltTime;
	}
	public Date getGtTime() {
		return gtTime;
	}
	public void setGtTime(Date gtTime) {
		this.gtTime = gtTime;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayOrderCode() {
		return payOrderCode;
	}
	public void setPayOrderCode(String payOrderCode) {
		this.payOrderCode = payOrderCode;
	}
	public String getOutOrderId() {
		return outOrderId;
	}
	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}
	public String getPaymentMark() {
		return paymentMark;
	}
	public void setPaymentMark(String paymentMark) {
		this.paymentMark = paymentMark;
	}
}