package vc.thinker.cabbage.user.vo;

import java.util.Date;

import vc.thinker.cabbage.user.bo.UserMoneyCashBO;

public class UserMoneyCashVO extends UserMoneyCashBO{

	private String cashPassword;
	
	private String cashStatus;
	
	private String startTime;
	
	private String endTime;
	
	private Date ltTime;
	
	private Date gtTime;
	
	private Long userId;
	
	private String userName;
	
	private String loginName;

	private  String contactMobile;
	
	private String refereeName;
	
	private String agentName;
	
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCashStatus() {
		return cashStatus;
	}

	public void setCashStatus(String cashStatus) {
		this.cashStatus = cashStatus;
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

	public String getCashPassword() {
		return cashPassword;
	}

	public void setCashPassword(String cashPassword) {
		this.cashPassword = cashPassword;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getRefereeName() {
		return refereeName;
	}

	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
}