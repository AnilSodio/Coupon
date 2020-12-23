package vc.thinker.cabbage.user.vo;

import java.util.Date;

public class UserMoneyRechargeVO {

	private String name;
	
	//用户昵称
	private String nickname;
	
	//用户手机号
	private String mobile;
	
	private Date ltTime;
	
	private Date gtTime;
	
	private String startTime;
	
	private String endTime;
	
	private Integer status;
	
	private String payOrderCode;
	
	private String outOrderId;
	
	private String paymentType;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}