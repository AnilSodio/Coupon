package vc.thinker.cabbage.user.bo;


import com.sinco.dic.client.annotation.DicMappingModel;
import com.sinco.dic.client.annotation.DicNameMapping;

import vc.thinker.cabbage.user.model.UserMoneyRecharge;
import vc.thinker.pay.bo.PayConfigBO;
/**
 * 
 * BO 用于返回数据
 *
 */
@DicMappingModel
public class UserMoneyRechargeBO extends UserMoneyRecharge{
	
	@DicNameMapping(codeField="paymentType",type=PayConfigBO.class)
	private String paymentTypeName;

	private String nickname;
	
	private String mobile;
	
	private String name;
	
	private String currency;
	
	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
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