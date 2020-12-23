package vc.thinker.cabbage.se.vo;

import java.util.Date;

import vc.thinker.cabbage.user.vo.MemberVO;

public class OrderVO {

	private String orderCode;

	private String sellerName;

	private String nickname;

	private String mobile;

	private Integer status;

	private String pbCode;

	private Date gtTime;

	private Date ltTime;

	private String beginDate;

	private String endDate;

	private Long borrowSellerId;
	private Long returnSellerId;
	private Long agentId;
	private Long refereeId;
	
	private String borrowSysCode;
	
	private String returnSysCode;

	public Long getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(Long refereeId) {
		this.refereeId = refereeId;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getReturnSellerId() {
		return returnSellerId;
	}

	public void setReturnSellerId(Long returnSellerId) {
		this.returnSellerId = returnSellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getPbCode() {
		return pbCode;
	}

	public void setPbCode(String pbCode) {
		this.pbCode = pbCode;
	}

	public Date getGtTime() {
		return gtTime;
	}

	public void setGtTime(Date gtTime) {
		this.gtTime = gtTime;
	}

	public Date getLtTime() {
		return ltTime;
	}

	public void setLtTime(Date ltTime) {
		this.ltTime = ltTime;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getBorrowSellerId() {
		return borrowSellerId;
	}

	public void setBorrowSellerId(Long borrowSellerId) {
		this.borrowSellerId = borrowSellerId;
	}

	public String getBorrowSysCode() {
		return borrowSysCode;
	}

	public void setBorrowSysCode(String borrowSysCode) {
		this.borrowSysCode = borrowSysCode;
	}

	public String getReturnSysCode() {
		return returnSysCode;
	}

	public void setReturnSysCode(String returnSysCode) {
		this.returnSysCode = returnSysCode;
	}

}