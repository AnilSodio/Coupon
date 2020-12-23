package vc.thinker.apiv2.bo;

import java.math.BigDecimal;

public class APIBasicCostBO {

	private BigDecimal cost;
	private String currency;
	
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
