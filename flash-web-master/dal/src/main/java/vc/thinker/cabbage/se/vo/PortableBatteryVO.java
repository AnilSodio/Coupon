package vc.thinker.cabbage.se.vo;

public class PortableBatteryVO {
	
	private String portableBatteryCode;
	
	private Integer status;
	
	private Integer locationType;
	
	private String cabinetCode;
	
	private String battType;
	
	private String cable;
	
	private Long cabinetId;
	
	private String sysCode;

	public String getPortableBatteryCode() {
		return portableBatteryCode;
	}

	public void setPortableBatteryCode(String portableBatteryCode) {
		this.portableBatteryCode = portableBatteryCode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLocationType() {
		return locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public String getCabinetCode() {
		return cabinetCode;
	}

	public void setCabinetCode(String cabinetCode) {
		this.cabinetCode = cabinetCode;
	}

	public String getBattType() {
		return battType;
	}

	public void setBattType(String battType) {
		this.battType = battType;
	}

	public String getCable() {
		return cable;
	}

	public void setCable(String cable) {
		this.cable = cable;
	}

	public Long getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(Long cabinetId) {
		this.cabinetId = cabinetId;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}