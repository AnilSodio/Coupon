package vc.thinker.cabbage.se.bo;


import vc.thinker.cabbage.se.model.Feedback;
/**
 * 
 * BO 用于返回数据
 *
 */
public class FeedbackBO extends Feedback{

	private String nickname;
	
	private String mobile;
	
	private String typeName;
	
	private String typeDesc;
	
	private String country;
	
	private String borrowSysCode;
	
	private Integer num;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBorrowSysCode() {
		return borrowSysCode;
	}

	public void setBorrowSysCode(String borrowSysCode) {
		this.borrowSysCode = borrowSysCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}