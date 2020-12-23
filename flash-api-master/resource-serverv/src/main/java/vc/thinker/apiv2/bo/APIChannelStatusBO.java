package vc.thinker.apiv2.bo;

import io.swagger.annotations.ApiModelProperty;

public class APIChannelStatusBO{
	@ApiModelProperty("是否锁住")
	private boolean lock;
	@ApiModelProperty("左传感器在 原点")
	private boolean leftOrigin;
	@ApiModelProperty("右传感器在 原点")
	private boolean rightOrigin;
	@ApiModelProperty("通道按键按")
	private Boolean isButton;
	@ApiModelProperty("读到id")
	private Boolean isReadId;
	@ApiModelProperty("通道号")
	private Integer channel;
	
	private APIPortableBatteryBO portableBattery;
	
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public APIPortableBatteryBO getPortableBattery() {
		return portableBattery;
	}
	public void setPortableBattery(APIPortableBatteryBO portableBattery) {
		this.portableBattery = portableBattery;
	}
	public boolean isLock() {
		return lock;
	}
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	public boolean isLeftOrigin() {
		return leftOrigin;
	}
	public void setLeftOrigin(boolean leftOrigin) {
		this.leftOrigin = leftOrigin;
	}
	public boolean isRightOrigin() {
		return rightOrigin;
	}
	public void setRightOrigin(boolean rightOrigin) {
		this.rightOrigin = rightOrigin;
	}
	public Boolean getIsButton() {
		return isButton;
	}
	public void setIsButton(Boolean isButton) {
		this.isButton = isButton;
	}
	public Boolean getIsReadId() {
		return isReadId;
	}
	public void setIsReadId(Boolean isReadId) {
		this.isReadId = isReadId;
	}
}