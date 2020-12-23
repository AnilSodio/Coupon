package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.UserMoneyLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class UserMoneyLogBO extends UserMoneyLog{

	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}