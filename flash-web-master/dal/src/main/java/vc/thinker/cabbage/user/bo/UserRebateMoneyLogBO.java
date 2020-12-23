package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.UserRebateMoneyLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class UserRebateMoneyLogBO extends UserRebateMoneyLog{
 
	private String nickname;
	
	private String mobile;
	
	private String sellerName;
	
	private String refereeName;
	
	private String agentName;
	
	private String startTime;
	
	private String endTime;
	
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
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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
	
}