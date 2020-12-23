package vc.thinker.cabbage.user.bo;

import com.sinco.dic.client.annotation.DicMappingModel;
import com.sinco.dic.client.annotation.DicNameMapping;

import vc.thinker.cabbage.user.model.UserCoupon;
import vc.thinker.sys.bo.DicAreaBO;

/**
 * 
 * BO 用于返回数据
 *
 */
@DicMappingModel
public class UserCouponBO extends UserCoupon{

	@DicNameMapping(type=DicAreaBO.class,codeField="cityId")
    private String cityName;
	
	private String nickname;
	
	private String mobile;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}