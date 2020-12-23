package vc.thinker.cabbage.stats.vo;

import java.util.Date;

public class TripStatsVO {

	private String startTime;
	
	private String endTime;
	
	private Date ltTime;
	
	private Date gtTime;
	
	private Integer limitNum;
	
	private String queryModel;
	
	private Boolean isNoUse;
	
	private String orderType;

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

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(String queryModel) {
		this.queryModel = queryModel;
	}

	public Boolean getIsNoUse() {
		return isNoUse;
	}

	public void setIsNoUse(Boolean isNoUse) {
		this.isNoUse = isNoUse;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
}