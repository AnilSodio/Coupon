package vc.thinker.apiv2.bo;


import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APISellerBO{
	
	@ApiModelProperty("服务商Id")
    private Long uid;

	@ApiModelProperty("服务商名称")
    private String sellerName;

	@ApiModelProperty("商户的头像")
    private String sellerLogo;

    /** 国家 **/
    private String country;

    /** 联系人 **/
    private String contactUserName;

    /** 手机号 **/
    private String contactMobile;

    @ApiModelProperty("经度 ")
    private BigDecimal locationLon;

    @ApiModelProperty("纬度")
    private BigDecimal locationLat;

    @ApiModelProperty("地址")
    private String locationAddress;

    @ApiModelProperty("详细地址")
    private String locationDesc;

    @ApiModelProperty("服务时间")
    private String serviceTime;
	
    @ApiModelProperty("充电柜数量")
	private Integer existCabinetNum;
    
    @ApiModelProperty("可还的通道数量")
    private Integer idlePositionNum;
    
    @ApiModelProperty("二合一数量")
	private Integer batteryType2Count;
	
    @ApiModelProperty("type-c 数量")
	private Integer batteryType3Count;
	
    @ApiModelProperty("三合一数量")
	private Integer batteryType4Count;
    
    @ApiModelProperty("距离")
    private Integer distance;
    
    @ApiModelProperty("封面")
    private List<String> sellerCover;
    
	public List<String> getSellerCover() {
		return sellerCover;
	}

	public void setSellerCover(List<String> sellerCover) {
		this.sellerCover = sellerCover;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getIdlePositionNum() {
		return idlePositionNum;
	}

	public void setIdlePositionNum(Integer idlePositionNum) {
		this.idlePositionNum = idlePositionNum;
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

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerLogo() {
		return sellerLogo;
	}

	public void setSellerLogo(String sellerLogo) {
		this.sellerLogo = sellerLogo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContactUserName() {
		return contactUserName;
	}

	public void setContactUserName(String contactUserName) {
		this.contactUserName = contactUserName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

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

	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getExistCabinetNum() {
		return existCabinetNum;
	}

	public void setExistCabinetNum(Integer existCabinetNum) {
		this.existCabinetNum = existCabinetNum;
	}
}