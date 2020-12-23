package vc.thinker.cabbage.stats.vo;

import java.util.Date;

public class StatsVO 
{
	private String statsType;//统计类型 1 年度月统计，2 月度日统计
	
	private String time;
	
	private String cityId;
	private String month;
	private String year;
	
	private Long officeId;
	
	private String officeName;
	
	private Integer status;
	
	private Integer completeStatus;
	
	private String order;
	
	private Boolean isNotComplete;
	
	private Boolean isDeleted;
	
	private Long sellerId;
	
	private String payType;
	
	private Integer limitNum;
	
	private String currency;
	
	private String queryModel;
	
	private Date gtTime;
	
	private String startTime;
	
	private String endTime;
	
	private String incomeType;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getStatsType() {
		return statsType;
	}
	public void setStatsType(String statsType) {
		this.statsType = statsType;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Long getOfficeId() {
		return officeId;
	}
	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCompleteStatus() {
		return completeStatus;
	}
	public void setCompleteStatus(Integer completeStatus) {
		this.completeStatus = completeStatus;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public Boolean getIsNotComplete() {
		return isNotComplete;
	}
	public void setIsNotComplete(Boolean isNotComplete) {
		this.isNotComplete = isNotComplete;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getQueryModel() {
		return queryModel;
	}
	public void setQueryModel(String queryModel) {
		this.queryModel = queryModel;
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
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	
}