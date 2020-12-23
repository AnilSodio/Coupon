package vc.thinker.apiv2.bo;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APICabinetBO{

    @ApiModelProperty("充电柜编号")
    private String cabinetCode;

    @ApiModelProperty("系统编号")
    private String sysCode;

    /** 类型id **/
    private Long typeId;

    @ApiModelProperty("商户信息")
    private APISellerBO seller;

	//空闲的位置
	private Integer idlePositionNum;
	
	//存在的电池数
	private Integer existPositionNum;
	
	//总位置数
	private Integer positionTotal;
	
    @ApiModelProperty("二合一数量")
	private Integer batteryType2Count;
	
    @ApiModelProperty("type-c 数量")
	private Integer batteryType3Count;
	
    @ApiModelProperty("三合一数量")
	private Integer batteryType4Count;
    
    @ApiModelProperty("收费规则描述")
	private String chargeRuleDesc;
    
    @ApiModelProperty("通道状态")
    private List<APIChannelStatusBO>  channelStatusList;
    
    @ApiModelProperty("最后心跳时间")
	private Date lastHeartbeat;
    
    /**  **/
    private BigDecimal locationLon;

    /**  **/
    private BigDecimal locationLat;

    /** 地址 **/
    private String locationAddress;

    /** 详细地址描述 **/
    private String locationDesc;
    
    
    @ApiModelProperty("机柜别名")
    private String cabinetAlias;
    
    @ApiModelProperty("是否在线")
  	private Boolean online;
    
	public BigDecimal getLocationLon() {
		return locationLon;
	}

	public void setLocationLon(BigDecimal locationLon) {
		this.locationLon = locationLon;
	}

	public BigDecimal getLocationLat() {
		return locationLat;
	}

	public void setLocationLat(BigDecimal locationLat) {
		this.locationLat = locationLat;
	}

	public String getLocationAddress() {
		return locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	public Date getLastHeartbeat() {
		return lastHeartbeat;
	}

	public void setLastHeartbeat(Date lastHeartbeat) {
		this.lastHeartbeat = lastHeartbeat;
	}

	public List<APIChannelStatusBO> getChannelStatusList() {
		return channelStatusList;
	}

	public void setChannelStatusList(List<APIChannelStatusBO> channelStatusList) {
		this.channelStatusList = channelStatusList;
	}

	public String getChargeRuleDesc() {
		return chargeRuleDesc;
	}

	public void setChargeRuleDesc(String chargeRuleDesc) {
		this.chargeRuleDesc = chargeRuleDesc;
	}

	public String getCabinetCode() {
		return cabinetCode;
	}

	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public APISellerBO getSeller() {
		return seller;
	}

	public void setSeller(APISellerBO seller) {
		this.seller = seller;
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

	public String getCabinetAlias() {
		return cabinetAlias;
	}

	public void setCabinetAlias(String cabinetAlias) {
		this.cabinetAlias = cabinetAlias;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}
	
}