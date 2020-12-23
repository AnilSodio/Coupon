package vc.thinker.cabbage.user.bo;


import com.sinco.dic.client.annotation.DicMappingModel;
import com.sinco.dic.client.annotation.DicNameMapping;

import vc.thinker.cabbage.user.model.VipPayLog;
import vc.thinker.pay.bo.PayConfigBO;
/**
 * 
 * BO 用于返回数据
 *
 */
@DicMappingModel
public class VipPayLogBO extends VipPayLog{

	@DicNameMapping(codeField="paymentMark",type=PayConfigBO.class)
	private String paymentMarkName;

	private String nickname;
	
	private String mobile;
	
	private String name;
	
	private String currency;
	
	public String getPaymentMarkName() {
		return paymentMarkName;
	}

	public void setPaymentMarkName(String paymentMarkName) {
		this.paymentMarkName = paymentMarkName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}