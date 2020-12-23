package vc.thinker.cabbage.stats.bo;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;
/**
 * 
 * 报表统计
 *
 */
public class ReportStatsBO{
	//统计类型
	@JSONField(ordinal = 1)
	private String statsType;
	//年总
	@JSONField(ordinal = 2)
	private BigDecimal yearTotal;
	
	@JSONField(ordinal = 3)
	private Map<String,BigDecimal> yearTotalDiffMap = new HashMap<String, BigDecimal>();
	
	//去年总
	@JSONField(ordinal = 4)
	private BigDecimal lastYearTotal;
	@JSONField(ordinal = 5)
	private Map<String,BigDecimal> lastYearTotalDiffMap = new HashMap<String, BigDecimal>();
	
	//年同比
	@JSONField(ordinal = 6)
	private BigDecimal yearAn;
	@JSONField(ordinal = 7)
	private Map<String,BigDecimal> yearAnDiffMap = new HashMap<String, BigDecimal>();
	
	//月总
	@JSONField(ordinal = 8)
	private BigDecimal monthTotal;
	@JSONField(ordinal = 9)
	private Map<String,BigDecimal> monthTotalDiffMap = new HashMap<String, BigDecimal>();
	
	//上月总
	@JSONField(ordinal = 10)
	private BigDecimal lastMonthTotal;
	@JSONField(ordinal = 11)
	private Map<String,BigDecimal> lastMonthTotalDiffMap = new HashMap<String, BigDecimal>();
	
	//月同比
	@JSONField(ordinal = 12)
	private BigDecimal monthAn;
	@JSONField(ordinal = 13)
	private Map<String,BigDecimal> monthAnDiffMap = new HashMap<String, BigDecimal>();
	
	//月环比
	@JSONField(ordinal = 14)
	private BigDecimal monthMom;
	@JSONField(ordinal = 15)
	private Map<String,BigDecimal> monthMomDiffMap = new HashMap<String, BigDecimal>();
	//单位
	@JSONField(ordinal = 16)
	private String unit;

	public String getStatsType() {
		return statsType;
	}

	public void setStatsType(String statsType) {
		this.statsType = statsType;
	}

	public BigDecimal getLastYearTotal() {
		return lastYearTotal;
	}

	public void setLastYearTotal(BigDecimal lastYearTotal) {
		this.lastYearTotal = lastYearTotal;
	}

	public BigDecimal getYearAn() {
		return yearAn;
	}

	public void setYearAn(BigDecimal yearAn) {
		this.yearAn = yearAn;
	}

	public BigDecimal getMonthTotal() {
		return monthTotal;
	}

	public void setMonthTotal(BigDecimal monthTotal) {
		this.monthTotal = monthTotal;
	}

	public BigDecimal getLastMonthTotal() {
		return lastMonthTotal;
	}

	public void setLastMonthTotal(BigDecimal lastMonthTotal) {
		this.lastMonthTotal = lastMonthTotal;
	}

	public BigDecimal getMonthAn() {
		return monthAn;
	}

	public void setMonthAn(BigDecimal monthAn) {
		this.monthAn = monthAn;
	}

	public BigDecimal getMonthMom() {
		return monthMom;
	}

	public void setMonthMom(BigDecimal monthMom) {
		this.monthMom = monthMom;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getYearTotal() {
		return yearTotal;
	}

	public void setYearTotal(BigDecimal yearTotal) {
		this.yearTotal = yearTotal;
	}

	public Map<String, BigDecimal> getYearTotalDiffMap() {
		return yearTotalDiffMap;
	}

	public void setYearTotalDiffMap(Map<String, BigDecimal> yearTotalDiffMap) {
		this.yearTotalDiffMap = yearTotalDiffMap;
	}

	public Map<String, BigDecimal> getLastYearTotalDiffMap() {
		return lastYearTotalDiffMap;
	}

	public void setLastYearTotalDiffMap(Map<String, BigDecimal> lastYearTotalDiffMap) {
		this.lastYearTotalDiffMap = lastYearTotalDiffMap;
	}

	public Map<String, BigDecimal> getYearAnDiffMap() {
		return yearAnDiffMap;
	}

	public void setYearAnDiffMap(Map<String, BigDecimal> yearAnDiffMap) {
		this.yearAnDiffMap = yearAnDiffMap;
	}

	public Map<String, BigDecimal> getMonthTotalDiffMap() {
		return monthTotalDiffMap;
	}

	public void setMonthTotalDiffMap(Map<String, BigDecimal> monthTotalDiffMap) {
		this.monthTotalDiffMap = monthTotalDiffMap;
	}

	public Map<String, BigDecimal> getLastMonthTotalDiffMap() {
		return lastMonthTotalDiffMap;
	}

	public void setLastMonthTotalDiffMap(Map<String, BigDecimal> lastMonthTotalDiffMap) {
		this.lastMonthTotalDiffMap = lastMonthTotalDiffMap;
	}

	public Map<String, BigDecimal> getMonthAnDiffMap() {
		return monthAnDiffMap;
	}

	public void setMonthAnDiffMap(Map<String, BigDecimal> monthAnDiffMap) {
		this.monthAnDiffMap = monthAnDiffMap;
	}

	public Map<String, BigDecimal> getMonthMomDiffMap() {
		return monthMomDiffMap;
	}

	public void setMonthMomDiffMap(Map<String, BigDecimal> monthMomDiffMap) {
		this.monthMomDiffMap = monthMomDiffMap;
	}
}