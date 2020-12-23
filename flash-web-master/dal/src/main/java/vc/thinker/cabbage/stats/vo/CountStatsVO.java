package vc.thinker.cabbage.stats.vo;


import java.io.Serializable;
import java.math.BigDecimal;

//用户统计
public class CountStatsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -147656481153619295L;

	
	private String statsTime;

	private String feedType;
	
	private Integer userNums;//用户数
	
	private Integer bicycleNums;//车辆
	
	private Integer batteryNums;//电池
	
	private BigDecimal deposit;//押金总量
	
	private BigDecimal consume;//消费总量
	
	private Integer sum;
	
	private String repairerName;
	
	private String sysCode;
	
	private String clientSource;
	
	private Boolean isDeleted;
	
	public Integer getUserNums() {
		return userNums;
	}

	public void setUserNums(Integer userNums) {
		this.userNums = userNums;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getStatsTime() {
		return statsTime;
	}

	public void setStatsTime(String statsTime) {
		this.statsTime = statsTime;
	}

	public Integer getBicycleNums() {
		return bicycleNums;
	}

	public void setBicycleNums(Integer bicycleNums) {
		this.bicycleNums = bicycleNums;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getConsume() {
		return consume;
	}

	public void setConsume(BigDecimal consume) {
		this.consume = consume;
	}

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public String getRepairerName() {
		return repairerName;
	}

	public void setRepairerName(String repairerName) {
		this.repairerName = repairerName;
	}

	public Integer getBatteryNums() {
		return batteryNums;
	}

	public void setBatteryNums(Integer batteryNums) {
		this.batteryNums = batteryNums;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
