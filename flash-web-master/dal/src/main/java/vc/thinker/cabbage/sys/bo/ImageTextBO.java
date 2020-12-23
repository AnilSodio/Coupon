package vc.thinker.cabbage.sys.bo;


import vc.thinker.cabbage.sys.model.ImageText;
/**
 * 
 * BO 用于返回数据
 *
 */
public class ImageTextBO extends ImageText{

	private String invalidDateString ;
	
	private String startDateString ;

	public String getInvalidDateString() {
		return invalidDateString;
	}

	public void setInvalidDateString(String invalidDateString) {
		this.invalidDateString = invalidDateString;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}
	
}