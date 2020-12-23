package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.MembershipCard;
/**
 * 
 * BO 用于返回数据
 *
 */
public class MembershipCardBO extends MembershipCard{

	//创建人姓名
	private String adminName;
	
	//创建人手机
	private String adminPhone;

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}
	
	
}