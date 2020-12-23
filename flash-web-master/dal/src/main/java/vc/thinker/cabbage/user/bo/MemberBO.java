package vc.thinker.cabbage.user.bo;


import java.math.BigDecimal;
import java.util.Date;

import vc.thinker.cabbage.user.model.Member;
/**
 * 
 * BO 用于返回数据
 *
 */
public class MemberBO extends Member{
	private String loginName;
	private Date lastTripTime;
	private Boolean isVIP;
	
	private Boolean isBindWeixin=false;
	
	private Boolean isBindQQ=false;
    
    private Boolean isBindFasebook=false;
    
    private Boolean isBindMobile=false;
    
    private Boolean isBindGoogle = false;
    
    //积分
    private Long integral;
    
    //余额
    private BigDecimal balance;
    
	//币种
    private String currency; 
    
    private BigDecimal availableBalance;
    
    private BigDecimal rebateMoney;
    
    //机柜别名
    private String cabinetAlias;
    
    public Boolean getIsBindMobile() {
		return isBindMobile;
	}

	public void setIsBindMobile(Boolean isBindMobile) {
		this.isBindMobile = isBindMobile;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Boolean getIsBindFasebook() {
		return isBindFasebook;
	}

	public void setIsBindFasebook(Boolean isBindFasebook) {
		this.isBindFasebook = isBindFasebook;
	}

	public Boolean getIsBindWeixin() {
		return isBindWeixin;
	}

	public void setIsBindWeixin(Boolean isBindWeixin) {
		this.isBindWeixin = isBindWeixin;
	}

	public Boolean getIsBindQQ() {
		return isBindQQ;
	}

	public void setIsBindQQ(Boolean isBindQQ) {
		this.isBindQQ = isBindQQ;
	}

	public Date getLastTripTime() {
		return lastTripTime;
	}

	public void setLastTripTime(Date lastTripTime) {
		this.lastTripTime = lastTripTime;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public Boolean getIsVIP() {
		if(getVipExpiresIn() != null){
			return getVipExpiresIn().after(new Date());
		}
		return false;
	}

	public void setIsVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(BigDecimal rebateMoney) {
		this.rebateMoney = rebateMoney;
	}

	public String getCabinetAlias() {
		return cabinetAlias;
	}

	public void setCabinetAlias(String cabinetAlias) {
		this.cabinetAlias = cabinetAlias;
	}

	public Boolean getIsBindGoogle() {
		return isBindGoogle;
	}

	public void setIsBindGoogle(Boolean isBindGoogle) {
		this.isBindGoogle = isBindGoogle;
	}
	
}