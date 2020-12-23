package vc.thinker.cabbage.stats.bo;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 
 * 次数统计
 *
 */
public class RealTimeStatsBO{
	private BigDecimal tripTimes;
	
	private BigDecimal distance;
	
	private BigDecimal registNums;
	
	private BigDecimal deposit;
	
//	private BigDecimal cny_deposit;
//	
//	private BigDecimal myr_deposit;
//	
//	private BigDecimal sgd_deposit;
	
	private Map<String, BigDecimal> countryDepositMap;
	
	private BigDecimal consume;
	
//	private BigDecimal cny_consume;
//	
//	private BigDecimal myr_consume;
//	
//	private BigDecimal sgd_consume;
	
//	private List<BigDecimal> countryConsumeList;
	
	private Map<String, BigDecimal> countryConsumeMap;
	
	private BigDecimal vipPay;
	
//	private BigDecimal cny_vipPay;
//	
//	private BigDecimal myr_vipPay;
//	
//	private BigDecimal sgd_vipPay;
	
//	private List<BigDecimal> countryVIPPayList;	
	
	private Map<String, BigDecimal> countryVIPPayMap;
	
	private BigDecimal balance;
	
//	private BigDecimal cny_balance;
//	
//	private BigDecimal myr_balance;
//	
//	private BigDecimal sgd_balance;
	
//	private List<BigDecimal> countryBalanceList;
	
	private Map<String, BigDecimal> countryBalanceMap;
	
	private BigDecimal platform_income;
	
	public BigDecimal getTripTimes() {
		return tripTimes;
	}

	public void setTripTimes(BigDecimal tripTimes) {
		this.tripTimes = tripTimes;
	}

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigDecimal getRegistNums() {
		return registNums;
	}

	public void setRegistNums(BigDecimal registNums) {
		this.registNums = registNums;
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

	public BigDecimal getVipPay() {
		return vipPay;
	}

	public void setVipPay(BigDecimal vipPay) {
		this.vipPay = vipPay;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

//	public BigDecimal getCny_deposit() {
//		return cny_deposit;
//	}
//
//	public void setCny_deposit(BigDecimal cny_deposit) {
//		this.cny_deposit = cny_deposit;
//	}
//
//	public BigDecimal getMyr_deposit() {
//		return myr_deposit;
//	}
//
//	public void setMyr_deposit(BigDecimal myr_deposit) {
//		this.myr_deposit = myr_deposit;
//	}
//
//	public BigDecimal getSgd_deposit() {
//		return sgd_deposit;
//	}
//
//	public void setSgd_deposit(BigDecimal sgd_deposit) {
//		this.sgd_deposit = sgd_deposit;
//	}
//
//	public BigDecimal getCny_consume() {
//		return cny_consume;
//	}
//
//	public void setCny_consume(BigDecimal cny_consume) {
//		this.cny_consume = cny_consume;
//	}
//
//	public BigDecimal getMyr_consume() {
//		return myr_consume;
//	}
//
//	public void setMyr_consume(BigDecimal myr_consume) {
//		this.myr_consume = myr_consume;
//	}
//
//	public BigDecimal getSgd_consume() {
//		return sgd_consume;
//	}
//
//	public void setSgd_consume(BigDecimal sgd_consume) {
//		this.sgd_consume = sgd_consume;
//	}
//
//	public BigDecimal getCny_vipPay() {
//		return cny_vipPay;
//	}
//
//	public void setCny_vipPay(BigDecimal cny_vipPay) {
//		this.cny_vipPay = cny_vipPay;
//	}
//
//	public BigDecimal getMyr_vipPay() {
//		return myr_vipPay;
//	}
//
//	public void setMyr_vipPay(BigDecimal myr_vipPay) {
//		this.myr_vipPay = myr_vipPay;
//	}
//
//	public BigDecimal getSgd_vipPay() {
//		return sgd_vipPay;
//	}
//
//	public void setSgd_vipPay(BigDecimal sgd_vipPay) {
//		this.sgd_vipPay = sgd_vipPay;
//	}
//
//	public BigDecimal getCny_balance() {
//		return cny_balance;
//	}
//
//	public void setCny_balance(BigDecimal cny_balance) {
//		this.cny_balance = cny_balance;
//	}
//
//	public BigDecimal getMyr_balance() {
//		return myr_balance;
//	}
//
//	public void setMyr_balance(BigDecimal myr_balance) {
//		this.myr_balance = myr_balance;
//	}
//
//	public BigDecimal getSgd_balance() {
//		return sgd_balance;
//	}
//
//	public void setSgd_balance(BigDecimal sgd_balance) {
//		this.sgd_balance = sgd_balance;
//	}

	public BigDecimal getPlatform_income() {
		return platform_income;
	}

	public void setPlatform_income(BigDecimal platform_income) {
		this.platform_income = platform_income;
	}

	public Map<String, BigDecimal> getCountryDepositMap() {
		return countryDepositMap;
	}

	public void setCountryDepositMap(Map<String, BigDecimal> countryDepositMap) {
		this.countryDepositMap = countryDepositMap;
	}

	public Map<String, BigDecimal> getCountryConsumeMap() {
		return countryConsumeMap;
	}

	public void setCountryConsumeMap(Map<String, BigDecimal> countryConsumeMap) {
		this.countryConsumeMap = countryConsumeMap;
	}

	public Map<String, BigDecimal> getCountryVIPPayMap() {
		return countryVIPPayMap;
	}

	public void setCountryVIPPayMap(Map<String, BigDecimal> countryVIPPayMap) {
		this.countryVIPPayMap = countryVIPPayMap;
	}

	public Map<String, BigDecimal> getCountryBalanceMap() {
		return countryBalanceMap;
	}

	public void setCountryBalanceMap(Map<String, BigDecimal> countryBalanceMap) {
		this.countryBalanceMap = countryBalanceMap;
	}



}