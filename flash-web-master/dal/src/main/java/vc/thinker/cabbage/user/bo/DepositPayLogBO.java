package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.DepositPayLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class DepositPayLogBO extends DepositPayLog{

	private String name;
	
	private String nickname;
	
	private String mobile;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}