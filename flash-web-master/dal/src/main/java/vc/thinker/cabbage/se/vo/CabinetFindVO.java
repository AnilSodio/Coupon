package vc.thinker.cabbage.se.vo;

import org.springframework.data.geo.Point;

public class CabinetFindVO {

	private Point p;
	
	private Integer distance;
	
	private Long typeId;
	
	//是否有空闲的位置
	private Boolean isIdle;
	//是否存在充电宝
	private Boolean isExist;
	
	private Integer count;
	
	private String sysCode;
	
	private String locationDetails;
	
	private Boolean onLine;
	
	private Integer status;
	
	public Boolean getIsExist() {
		return isExist;
	}
	public void setIsExist(Boolean isExist) {
		this.isExist = isExist;
	}
	public Boolean getIsIdle() {
		return isIdle;
	}
	public void setIsIdle(Boolean isIdle) {
		this.isIdle = isIdle;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public String getSysCode() {
		return sysCode;
	}
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
	public String getLocationDetails() {
		return locationDetails;
	}
	public void setLocationDetails(String locationDetails) {
		this.locationDetails = locationDetails;
	}
	public Boolean getOnLine() {
		return onLine;
	}
	public void setOnLine(Boolean onLine) {
		this.onLine = onLine;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
