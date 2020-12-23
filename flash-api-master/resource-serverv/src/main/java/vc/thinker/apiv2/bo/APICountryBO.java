package vc.thinker.apiv2.bo;

import io.swagger.annotations.ApiModelProperty;

public class APICountryBO {

	private Long id;

//	@ApiModelProperty("中文名称")
//    private String chineseName;
//
//	@ApiModelProperty("英文名称")
//    private String englishName;

	@ApiModelProperty("默认语言")
    private String defaultLanguage;
//
//	@ApiModelProperty("是否默认")
//    private Boolean isDefault;

	@ApiModelProperty("语言描述")
	private String languageDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String getChineseName() {
//		return chineseName;
//	}
//
//	public void setChineseName(String chineseName) {
//		this.chineseName = chineseName;
//	}
//
//	public String getEnglishName() {
//		return englishName;
//	}
//
//	public void setEnglishName(String englishName) {
//		this.englishName = englishName;
//	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}
//
//	public Boolean getIsDefault() {
//		return isDefault;
//	}
//
//	public void setIsDefault(Boolean isDefault) {
//		this.isDefault = isDefault;
//	}

	public String getLanguageDesc() {
		return languageDesc;
	}

	public void setLanguageDesc(String languageDesc) {
		this.languageDesc = languageDesc;
	}

}
