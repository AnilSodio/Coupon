package vc.thinker.cabbage.stats.bo;


import vc.thinker.cabbage.stats.model.RegisterStats;
/**
 * 
 * BO 用于返回数据
 *
 */
public class RegisterStatsBO extends RegisterStats{

	private Integer totalNum;

	private String ageDesc;
	
	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getAgeDesc() {
		return ageDesc;
	}

	public void setAgeDesc(String ageDesc) {
		this.ageDesc = ageDesc;
	}
	
}