package vc.thinker.apiv2.bo;

import java.math.BigDecimal;

public class APIDepositBO {
	private BigDecimal deposit;
	private String currency;
	public BigDecimal getDeposit() {
		return deposit;
	}
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
}
