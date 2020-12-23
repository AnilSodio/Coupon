package vc.thinker.cabbage.se.bo;


import java.util.List;

import vc.thinker.cabbage.se.model.CabinetType;
/**
 * 
 * BO 用于返回数据
 *
 */
public class CabinetTypeBO extends CabinetType{

	//创建人
	private String createName;
	
	//修改人
	private String updateName;
	
	private List<CabinetChargeRuleBO> ruleList;

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

	public List<CabinetChargeRuleBO> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<CabinetChargeRuleBO> ruleList) {
		this.ruleList = ruleList;
	}
	
	
}