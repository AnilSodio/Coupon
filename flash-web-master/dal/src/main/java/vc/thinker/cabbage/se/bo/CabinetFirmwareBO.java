package vc.thinker.cabbage.se.bo;


import vc.thinker.cabbage.se.model.CabinetFirmware;
/**
 * 
 * BO 用于返回数据
 *
 */
public class CabinetFirmwareBO extends CabinetFirmware{

	private String moubleName;

	public String getMoubleName() {
		return moubleName;
	}

	public void setMoubleName(String moubleName) {
		this.moubleName = moubleName;
	}
	
}