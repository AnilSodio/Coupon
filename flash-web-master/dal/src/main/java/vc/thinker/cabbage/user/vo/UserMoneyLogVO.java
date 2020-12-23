package vc.thinker.cabbage.user.vo;

import java.util.Date;

public class UserMoneyLogVO {
	
	private String startTime;
	private String endTime;
	private Long userId;
	private String logType;
	private String loginName;
	private Date ltTime;
	private Long uid;
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
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
}