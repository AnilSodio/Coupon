package vc.thinker.cabbage.user.vo;

public class MemberVO {
	private String nickname;
	private String name;
	private String mobile;
	private Integer status;
	private Long uid;
	private Boolean isVIP;
	private Integer authApplyStatus;
	private Integer authStep;
	private String country;
	private String currency;
	private Boolean isPayBasicCost;

	private String sysCode;
	// query
	private Long sellerId;
	private Long AgentId;
	private Long refereeId;

	public Long getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(Long refereeId) {
		this.refereeId = refereeId;
	}

	public Long getUid() {
		return uid;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public Long getAgentId() {
		return AgentId;
	}

	public void setAgentId(Long agentId) {
		AgentId = agentId;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}

	public Integer getAuthApplyStatus() {
		return authApplyStatus;
	}

	public void setAuthApplyStatus(Integer authApplyStatus) {
		this.authApplyStatus = authApplyStatus;
	}

	public Integer getAuthStep() {
		return authStep;
	}

	public void setAuthStep(Integer authStep) {
		this.authStep = authStep;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Boolean getIsPayBasicCost() {
		return isPayBasicCost;
	}

	public void setIsPayBasicCost(Boolean isPayBasicCost) {
		this.isPayBasicCost = isPayBasicCost;
	}
}