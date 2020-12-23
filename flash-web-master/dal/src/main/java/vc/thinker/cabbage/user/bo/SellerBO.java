package vc.thinker.cabbage.user.bo;


import vc.thinker.cabbage.user.model.Seller;
/**
 * 
 * BO 用于返回数据
 *
 */
public class SellerBO extends Seller{

	private String currency;
	
	private Integer existCabinetNum;
	
	private String registIp;
	
	/*
	 * 
	1：苹果线
	
	2：苹果线/micro-usb，
	
	3：TYPE C线  
	
	4：三合一线      
	 */
	private Integer batteryType2Count;
	
	private Integer batteryType3Count;
	
	private Integer batteryType4Count;
	
	/**
	 * 空闲数
	 */
	private Integer idlePositionNum;
	
	/**
	 * 商户封面
	 */
	private String sellerCover1;
	private String sellerCover2;
	private String sellerCover3;
	private String sellerCover4;
	private String sellerCover5;
	
	private Integer distance;
	
	private String twoCode;
	
	private String refereeName;
	
	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public String getRegistIp() {
		return registIp;
	}

	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getExistCabinetNum() {
		return existCabinetNum;
	}

	public void setExistCabinetNum(Integer existCabinetNum) {
		this.existCabinetNum = existCabinetNum;
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

	public Integer getIdlePositionNum() {
		return idlePositionNum;
	}

	public void setIdlePositionNum(Integer idlePositionNum) {
		this.idlePositionNum = idlePositionNum;
	}

	public String getSellerCover1() {
		return sellerCover1;
	}

	public void setSellerCover1(String sellerCover1) {
		this.sellerCover1 = sellerCover1;
	}

	public String getSellerCover2() {
		return sellerCover2;
	}

	public void setSellerCover2(String sellerCover2) {
		this.sellerCover2 = sellerCover2;
	}

	public String getSellerCover3() {
		return sellerCover3;
	}

	public void setSellerCover3(String sellerCover3) {
		this.sellerCover3 = sellerCover3;
	}

	public String getSellerCover4() {
		return sellerCover4;
	}

	public void setSellerCover4(String sellerCover4) {
		this.sellerCover4 = sellerCover4;
	}

	public String getSellerCover5() {
		return sellerCover5;
	}

	public void setSellerCover5(String sellerCover5) {
		this.sellerCover5 = sellerCover5;
	}

	public String getTwoCode() {
		return twoCode;
	}

	public void setTwoCode(String twoCode) {
		this.twoCode = twoCode;
	}

	public String getRefereeName() {
		return refereeName;
	}

	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}
}