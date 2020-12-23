package vc.thinker.apiv2.bo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class APIlanguagePackageBO {

	@ApiModelProperty("语言包")
	private String languagePackage;
	
	@ApiModelProperty("最后更新时间")
	private Date lastUpdateTime;

	public String getLanguagePackage() {
		return languagePackage;
	}

	public void setLanguagePackage(String languagePackage) {
		this.languagePackage = languagePackage;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
}
