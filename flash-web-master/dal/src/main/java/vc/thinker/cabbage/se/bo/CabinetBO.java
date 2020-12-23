package vc.thinker.cabbage.se.bo;


import java.util.Date;
import java.util.List;

import vc.thinker.cabbage.se.model.Cabinet;
import vc.thinker.cabbage.se.model.CabinetStatus.ChannelStatus;
/**
 * 
 * BO 用于返回数据
 *
 */
public class CabinetBO extends Cabinet{

	private String createName;
	
	private String typeName;
	
	private String ruleDesc;
	
	private String sellerName;
	
	private String sellerImg;
	
	private String serviceTime;
	
	//是否在线
	private Boolean online;
	
	//空闲的位置
	private Integer idlePositionNum;
	
	//低电量的电池数
	private Integer disablePositionNum;
	
	//可用的电池数
	private Integer existPositionNum;
	
	//总位置数
	private Integer positionTotal;
	
	private Integer batteryType2Count;
	
	private Integer batteryType3Count;
	
	private Integer batteryType4Count;
	
	private List<ChannelStatus> channelStatusList;
	
	private String agentName;
	
	private Date lastHeartbeat;
	
	private Date lastUpdateTime;
	
	private String version;
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getLastHeartbeat() {
		return lastHeartbeat;
	}

	public void setLastHeartbeat(Date lastHeartbeat) {
		this.lastHeartbeat = lastHeartbeat;
	}

	public List<ChannelStatus> getChannelStatusList() {
		return channelStatusList;
	}

	public void setChannelStatusList(List<ChannelStatus> channelStatusList) {
		this.channelStatusList = channelStatusList;
	}

	public Integer getBatteryType2Count() {
		return batteryType2Count;
	}

	public void setBatteryType2Count(Integer batteryType2Count) {
		this.batteryType2Count = batteryType2Count;
	}

	public Integer getBatteryType3Count() {
		return batteryType3Count;
	}

	public void setBatteryType3Count(Integer batteryType3Count) {
		this.batteryType3Count = batteryType3Count;
	}

	public Integer getBatteryType4Count() {
		return batteryType4Count;
	}

	public void setBatteryType4Count(Integer batteryType4Count) {
		this.batteryType4Count = batteryType4Count;
	}

	public String getSellerImg() {
		return sellerImg;
	}

	public void setSellerImg(String sellerImg) {
		this.sellerImg = sellerImg;
	}

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Integer getIdlePositionNum() {
		return idlePositionNum;
	}

	public void setIdlePositionNum(Integer idlePositionNum) {
		this.idlePositionNum = idlePositionNum;
	}

	public Integer getExistPositionNum() {
		return existPositionNum;
	}

	public void setExistPositionNum(Integer existPositionNum) {
		this.existPositionNum = existPositionNum;
	}

	public Integer getPositionTotal() {
		return positionTotal;
	}

	public void setPositionTotal(Integer positionTotal) {
		this.positionTotal = positionTotal;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getRuleDesc() {
		return ruleDesc;
	}

	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	public Integer getDisablePositionNum() {
		return disablePositionNum;
	}

	public void setDisablePositionNum(Integer disablePositionNum) {
		this.disablePositionNum = disablePositionNum;
	}
	
}