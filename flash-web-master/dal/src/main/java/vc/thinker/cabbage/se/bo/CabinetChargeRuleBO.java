package vc.thinker.cabbage.se.bo;


import vc.thinker.cabbage.se.model.CabinetChargeRule;
/**
 * 
 * BO 用于返回数据
 *
 */
public class CabinetChargeRuleBO extends CabinetChargeRule{

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