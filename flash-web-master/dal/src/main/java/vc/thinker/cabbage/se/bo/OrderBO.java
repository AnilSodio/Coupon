package vc.thinker.cabbage.se.bo;


import java.math.BigDecimal;

import vc.thinker.cabbage.se.model.Order;
/**
 * 
 * BO 用于返回数据
 *
 */
public class OrderBO extends Order{

	private String nickname;
	
	private String name;
	
	private String mobile;
	
	private String sellerName;
	
	private BigDecimal ticketAmount;
	
	private String borrowSellerName;
	
	private String returnSellerName;

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

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public BigDecimal getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(BigDecimal ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBorrowSellerName() {
		return borrowSellerName;
	}

	public void setBorrowSellerName(String borrowSellerName) {
		this.borrowSellerName = borrowSellerName;
	}

	public String getReturnSellerName() {
		return returnSellerName;
	}

	public void setReturnSellerName(String returnSellerName) {
		this.returnSellerName = returnSellerName;
	}
	
}