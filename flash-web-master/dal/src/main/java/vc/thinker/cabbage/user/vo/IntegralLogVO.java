package vc.thinker.cabbage.user.vo;

import java.util.Date;

public class IntegralLogVO {

	private String name;
	
	private String mobile;
	
	private String logType;
	
	private Date ltTime;
	
	private Long uid;
	
	private String nickname;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}