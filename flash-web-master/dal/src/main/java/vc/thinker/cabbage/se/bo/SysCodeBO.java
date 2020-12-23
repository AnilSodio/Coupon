package vc.thinker.cabbage.se.bo;


import vc.thinker.cabbage.se.model.SysCode;
/**
 * 
 * BO 用于返回数据
 *
 */
public class SysCodeBO extends SysCode{

	private String twoCode;

	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}
	
}