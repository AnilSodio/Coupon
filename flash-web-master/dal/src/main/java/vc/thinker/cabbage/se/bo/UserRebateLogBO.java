package vc.thinker.cabbage.se.bo;


import java.util.Date;

import vc.thinker.cabbage.se.model.UserRebateLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class UserRebateLogBO extends UserRebateLog{

	private String nickname;
	private String mobile;
	private String orderCode;
	private String borrowSysCode;
	private int rideTime;
	private Date payTime;
	
	private String sellerName;
	private String concatMobile;
	private String concatUserName;
	private String country;
	private String pbCode;
	
	private Long orderId;
	
	private String agentName;
	private String refereeName;
	
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
	
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getBorrowSysCode() {
		return borrowSysCode;
	}
	public void setBorrowSysCode(String borrowSysCode) {
		this.borrowSysCode = borrowSysCode;
	}
	public int getRideTime() {
		return rideTime;
	}
	public void setRideTime(int rideTime) {
		this.rideTime = rideTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getConcatMobile() {
		return concatMobile;
	}
	public void setConcatMobile(String concatMobile) {
		this.concatMobile = concatMobile;
	}
	public String getConcatUserName() {
		return concatUserName;
	}
	public void setConcatUserName(String concatUserName) {
		this.concatUserName = concatUserName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPbCode() {
		return pbCode;
	}
	public void setPbCode(String pbCode) {
		this.pbCode = pbCode;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getRefereeName() {
		return refereeName;
	}
	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
	
}