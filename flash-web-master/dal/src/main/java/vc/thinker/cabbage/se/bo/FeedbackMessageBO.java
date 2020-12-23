package vc.thinker.cabbage.se.bo;


import vc.thinker.cabbage.se.model.FeedbackMessage;
/**
 * 
 * BO 用于返回数据
 *
 */
public class FeedbackMessageBO extends FeedbackMessage{

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