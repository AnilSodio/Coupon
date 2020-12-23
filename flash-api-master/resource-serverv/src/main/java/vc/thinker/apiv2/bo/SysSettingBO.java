package vc.thinker.apiv2.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SysSettingBO {
	
	@ApiModelProperty("公司名称")
    private String corporateName;

	@ApiModelProperty("logo 地址")
    private String logoImg;

	@ApiModelProperty("应用名称")
    private String appName;

    @ApiModelProperty("1 微信 ，2 支付宝  ， 1,2 微信和支付宝 ")
    private String payWay;

    @ApiModelProperty("1 简体中文 ，2 英语 ")
    private String defaultLanguage;

    @ApiModelProperty("联系电话")
    private String contactMobile;
    
    @ApiModelProperty("联系电话")
    private List<String> contactUs;
    
    @ApiModelProperty("用户是否注册  true 用户注册 ， false 后台注册")
    private Boolean isUserRegister;

    @ApiModelProperty("app 英文")
    private String appNameEnglish;
    
    @ApiModelProperty("是否开放广告")
    private Boolean isOpenAd;
    
    @ApiModelProperty("app初始化图片时间（单位s）")
    private Integer imgTime;
    
    @ApiModelProperty("是否开放会员卡体系  true开放，false 不开放 ")
    private Boolean isOpenMemberCard;

    @ApiModelProperty("会员卡描述")
    private String cardDesc;
    
    @ApiModelProperty("是否开放余额 true 开放，false 不开放")
    private Boolean isOpenBalance;
    
    @ApiModelProperty("基础会员费（人民币）")
    private BigDecimal 	basicCostRMB;
    
    @ApiModelProperty("基础会员费（新加坡币）")
    private BigDecimal basicCostSingapore;
    
    @ApiModelProperty("基础会员费（马来西亚币）")
    private BigDecimal basicCostMalaysia;
    
    @ApiModelProperty("地图搜索范围")
    private Integer mapSearchScope;
    
    @ApiModelProperty("语言包的最后更新时间")
    private Date languageLastUpdateTime;

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}


	public Boolean getIsUserRegister() {
		return isUserRegister;
	}

	public void setIsUserRegister(Boolean isUserRegister) {
		this.isUserRegister = isUserRegister;
	}

	public String getAppNameEnglish() {
		return appNameEnglish;
	}

	public void setAppNameEnglish(String appNameEnglish) {
		this.appNameEnglish = appNameEnglish;
	}

	public Boolean getIsOpenAd() {
		return isOpenAd;
	}

	public void setIsOpenAd(Boolean isOpenAd) {
		this.isOpenAd = isOpenAd;
	}

	public Integer getImgTime() {
		return imgTime;
	}

	public void setImgTime(Integer imgTime) {
		this.imgTime = imgTime;
	}

	public Boolean getIsOpenMemberCard() {
		return isOpenMemberCard;
	}

	public void setIsOpenMemberCard(Boolean isOpenMemberCard) {
		this.isOpenMemberCard = isOpenMemberCard;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	public Boolean getIsOpenBalance() {
		return isOpenBalance;
	}

	public void setIsOpenBalance(Boolean isOpenBalance) {
		this.isOpenBalance = isOpenBalance;
	}

	public BigDecimal getBasicCostRMB() {
		return basicCostRMB;
	}

	public void setBasicCostRMB(BigDecimal basicCostRMB) {
		this.basicCostRMB = basicCostRMB;
	}

	public BigDecimal getBasicCostSingapore() {
		return basicCostSingapore;
	}

	public void setBasicCostSingapore(BigDecimal basicCostSingapore) {
		this.basicCostSingapore = basicCostSingapore;
	}

	public BigDecimal getBasicCostMalaysia() {
		return basicCostMalaysia;
	}

	public void setBasicCostMalaysia(BigDecimal basicCostMalaysia) {
		this.basicCostMalaysia = basicCostMalaysia;
	}

	public Integer getMapSearchScope() {
		return mapSearchScope;
	}

	public void setMapSearchScope(Integer mapSearchScope) {
		this.mapSearchScope = mapSearchScope;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public List<String> getContactUs() {
		return contactUs;
	}

	public void setContactUs(List<String> contactUs) {
		this.contactUs = contactUs;
	}

	public Date getLanguageLastUpdateTime() {
		return languageLastUpdateTime;
	}

	public void setLanguageLastUpdateTime(Date languageLastUpdateTime) {
		this.languageLastUpdateTime = languageLastUpdateTime;
	}

}
