package vc.thinker.cabbage.web.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DataStats {

	private List<BigDecimal> growArr = new ArrayList<BigDecimal>();
	private List<BigDecimal> numArr = new ArrayList<BigDecimal>();
	private List<String> nameArr = new ArrayList<String>();
	private String currency;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public List<BigDecimal> getGrowArr() {
		return growArr;
	}

	public void setGrowArr(List<BigDecimal> growArr) {
		this.growArr = growArr;
	}

	public List<BigDecimal> getNumArr() {
		return numArr;
	}

	public void setNumArr(List<BigDecimal> numArr) {
		this.numArr = numArr;
	}

	public List<String> getNameArr() {
		return nameArr;
	}

	public void setNameArr(List<String> nameArr) {
		this.nameArr = nameArr;
	}
}
