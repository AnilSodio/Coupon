package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.IntegralRule;
/**
 * 
 * BO 用于返回数据
 *
 */
public class IntegralRuleBO extends IntegralRule{

	private String createName;
	
	private String updateName;

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getUpdateName() {
		return updateName;
	}

	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}
	
}