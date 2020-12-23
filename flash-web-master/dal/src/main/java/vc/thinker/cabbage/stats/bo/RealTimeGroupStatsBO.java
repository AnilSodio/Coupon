package vc.thinker.cabbage.stats.bo;


import java.math.BigDecimal;

import vc.thinker.cabbage.stats.model.VipStats;
/**
 * 
 * 按类型统计
 *
 */
public class RealTimeGroupStatsBO{
	private String groupName;
	
	private BigDecimal value;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	
	
}