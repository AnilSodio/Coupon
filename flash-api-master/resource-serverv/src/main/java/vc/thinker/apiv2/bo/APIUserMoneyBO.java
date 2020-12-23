package vc.thinker.apiv2.bo;

import java.math.BigDecimal;

public class APIUserMoneyBO  {

    /**  **/
    private BigDecimal availableBalance;

    /** 币种 **/
    private String currency;

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}