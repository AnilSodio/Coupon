package vc.thinker.cabbage.stats.bo;

import java.io.Serializable;
import java.math.BigDecimal;

//用户统计
public class CountStatsBO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -147656481153619295L;

	
	private String statsTime;

	private String feedType;
	
	private Integer userNums;//用户数
	
	private BigDecimal deposit;//押金总量
	
	private BigDecimal consume;//消费总量
	
	private Integer sum;
	
	private String clientSource;
	
	private Boolean isDeleted;
	
	private String currency;

	public String getStatsTime() {
		return statsTime;
	}

	public void setStatsTime(String statsTime) {
		this.statsTime = statsTime;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public Integer getUserNums() {
		return userNums;
	}

	public void setUserNums(Integer userNums) {
		this.userNums = userNums;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
