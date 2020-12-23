package vc.thinker.cabbage.se.bo;


import java.util.Date;

import vc.thinker.cabbage.se.model.PortableBattery;
/**
 * 
 * BO 用于返回数据
 *
 */
public class PortableBatteryBO extends PortableBattery{

	private String typeName;
	
	private Boolean isHasLine;
	
	private String lineType;
	
	private String createName;
	
	private String cabinetCode;
	
	private String lastUseName;
	
	private Date lastUseTime;
	
	private String lastUseCabinetCode;
	
	private String lastUseMobile;

	private String sysCode;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getLineType() {
		return lineType;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCabinetCode() {
		return cabinetCode;
	}

	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}

	public Boolean getIsHasLine() {
		return isHasLine;
	}

	public void setIsHasLine(Boolean isHasLine) {
		this.isHasLine = isHasLine;
	}

	public String getLastUseName() {
		return lastUseName;
	}

	public void setLastUseName(String lastUseName) {
		this.lastUseName = lastUseName;
	}

	public Date getLastUseTime() {
		return lastUseTime;
	}

	public void setLastUseTime(Date lastUseTime) {
		this.lastUseTime = lastUseTime;
	}

	public String getLastUseCabinetCode() {
		return lastUseCabinetCode;
	}

	public void setLastUseCabinetCode(String lastUseCabinetCode) {
		this.lastUseCabinetCode = lastUseCabinetCode;
	}

	public String getLastUseMobile() {
		return lastUseMobile;
	}

	public void setLastUseMobile(String lastUseMobile) {
		this.lastUseMobile = lastUseMobile;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	
}