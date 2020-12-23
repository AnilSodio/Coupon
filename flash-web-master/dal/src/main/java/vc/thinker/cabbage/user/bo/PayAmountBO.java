package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.PayAmount;
/**
 * 
 * BO 用于返回数据
 *
 */
public class PayAmountBO extends PayAmount{

	//后台创建人姓名
	private String adminName;
	//后台创建人手机号
	private String adminMobile;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminMobile() {
		return adminMobile;
	}

	public void setAdminMobile(String adminMobile) {
		this.adminMobile = adminMobile;
	}
	
	
}