package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.IntegralLog;
/**
 * 
 * BO 用于返回数据
 *
 */
public class IntegralLogBO extends IntegralLog{

	private String mobile;
	
	private String name;
	
	private String ruleDescribe;
	
	private String nickname;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleDescribe() {
		return ruleDescribe;
	}

	public void setRuleDescribe(String ruleDescribe) {
		this.ruleDescribe = ruleDescribe;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}