package vc.thinker.cabbage.sys.bo;


import java.math.BigDecimal;

import vc.thinker.cabbage.sys.model.Promotion;
/**
 * 
 * BO 用于返回数据
 *
 */
public class PromotionBO extends Promotion{

	private BigDecimal amount;
	private Long dayLimit;
	private String proTypeName;
	private String areaLabel;
	private String currency;
	
	public String getAreaLabel() {
		return areaLabel;
	}
	public void setAreaLabel(String areaLabel) {
		this.areaLabel = areaLabel;
	}
	public String getProTypeName() {
		return proTypeName;
	}
	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Long getDayLimit() {
		return dayLimit;
	}
	public void setDayLimit(Long dayLimit) {
		this.dayLimit = dayLimit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}