package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.UserBasicCostPayLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class UserBasicCostPayLogBO extends UserBasicCostPayLog{

	private String nickname;
	
	private String mobile;

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