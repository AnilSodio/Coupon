package vc.thinker.apiv2.bo;

public class ThirdPartyBoundModileBO {

	private String loginName;
	
	private String token;
	
	/**
	 * 是否存在的手机号
	 */
	private Boolean isExistModile;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsExistModile() {
		return isExistModile;
	}

	public void setIsExistModile(Boolean isExistModile) {
		this.isExistModile = isExistModile;
	}
	
	
}
