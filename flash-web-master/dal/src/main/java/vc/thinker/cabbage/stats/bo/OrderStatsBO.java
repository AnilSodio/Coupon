package vc.thinker.cabbage.stats.bo;


import java.math.BigDecimal;

import vc.thinker.cabbage.stats.model.OrderStats;
/**
 * 
 * BO 用于返回数据
 *
 */
public class OrderStatsBO extends OrderStats{

	private BigDecimal orderNum;

	public BigDecimal getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(BigDecimal orderNum) {
		this.orderNum = orderNum;
	}

}