package vc.thinker.apiv2.bo;


import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIPortableBatteryBO{

    @ApiModelProperty("资产编号")
    private String portableBatteryCode;

    @ApiModelProperty("槽位号")
    private Integer cabinetChannel;

    @ApiModelProperty("电量")
    private Integer electricity;

    @ApiModelProperty("电压")
    private Integer voltage;

    @ApiModelProperty("充电线类型")
    private String cable;

    @ApiModelProperty("否带充电头")
    private Boolean adapter;

    @ApiModelProperty(" 0-不需要充电 1-在充电 2-在充电缓冲区，不动作，即保持原动作 3-读不到电量，也不用充电了")
    private String isdamage;
    
    /**  **/
    private String colorId;

    @ApiModelProperty("温度 ")
    private String temperature;

    @ApiModelProperty("电池类型")
    private String battType;

    @ApiModelProperty("最后位置时间")
    private Date lastLocationTime;

	public String getPortableBatteryCode() {
		return portableBatteryCode;
	}

	public void setPortableBatteryCode(String portableBatteryCode) {
		this.portableBatteryCode = portableBatteryCode;
	}

	public Integer getCabinetChannel() {
		return cabinetChannel;
	}

	public void setCabinetChannel(Integer cabinetChannel) {
		this.cabinetChannel = cabinetChannel;
	}

	public Integer getElectricity() {
		return electricity;
	}

	public void setElectricity(Integer electricity) {
		this.electricity = electricity;
	}

	public Integer getVoltage() {
		return voltage;
	}

	public void setVoltage(Integer voltage) {
		this.voltage = voltage;
	}

	public String getCable() {
		return cable;
	}

	public void setCable(String cable) {
		this.cable = cable;
	}

	public Boolean getAdapter() {
		return adapter;
	}

	public void setAdapter(Boolean adapter) {
		this.adapter = adapter;
	}

	public String getIsdamage() {
		return isdamage;
	}

	public void setIsdamage(String isdamage) {
		this.isdamage = isdamage;
	}

	public String getColorId() {
		return colorId;
	}

	public void setColorId(String colorId) {
		this.colorId = colorId;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getBattType() {
		return battType;
	}

	public void setBattType(String battType) {
		this.battType = battType;
	}

	public Date getLastLocationTime() {
		return lastLocationTime;
	}

	public void setLastLocationTime(Date lastLocationTime) {
		this.lastLocationTime = lastLocationTime;
	}
}