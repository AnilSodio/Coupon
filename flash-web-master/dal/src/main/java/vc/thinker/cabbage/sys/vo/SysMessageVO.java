package vc.thinker.cabbage.sys.vo;

import java.util.Date;

public class SysMessageVO {
private Long toUserId;
	
	private String toUserType;
	
	private Integer bizType;
	
	private String beginDate;
	
	private String endDate;
	
	private Boolean logIdIsNull;
	
	private Date ltTime;
	
	private Date gtTime;
	
	public Long getToUserId() {
		return toUserId;
	}
	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}
	public String getToUserType() {
		return toUserType;
	}
	public void setToUserType(String toUserType) {
		this.toUserType = toUserType;
	}
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Boolean getLogIdIsNull() {
		return logIdIsNull;
	}
	public void setLogIdIsNull(Boolean logIdIsNull) {
		this.logIdIsNull = logIdIsNull;
	}
}