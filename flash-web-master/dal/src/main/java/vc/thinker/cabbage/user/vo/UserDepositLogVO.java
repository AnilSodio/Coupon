package vc.thinker.cabbage.user.vo;

import java.util.Date;

public class UserDepositLogVO {
	
	private Date ltdate;

	private Long uid;
	
	private String type;
	
	private String nickname;
	
	private String mobile;
	
	private String startTime;
	
	private String endTime;
	
	private String mark;
	
	private String payMark;
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getLtdate() {
		return ltdate;
	}

	public void setLtdate(Date ltdate) {
		this.ltdate = ltdate;
	}

	public String getPayMark() {
		return payMark;
	}

	public void setPayMark(String payMark) {
		this.payMark = payMark;
	}
	
}