package vc.thinker.cabbage.sys.model;

import java.math.BigDecimal;

public class SysSetting{
    /** 押金 **/
    private BigDecimal deposit;

    /** 公司名称 **/
    private String corporateName;

    /** logo 地址 **/
    private String logoImg;
    
    /** logo 地址 **/
    private String logoRgb;

    /** 应用名称 **/
    private String appName;

    /** 英文版的应用名称 **/
    private String appNameEnglish;

    /** 支付方式 1 微信 ，2 支付宝 **/
    private String payWay;

    /** 默认语言 1 简体中文 ，2 英语 **/
    private String defaultLanguage;

    /** app初始化图片时间（单位s） **/
    private Integer imgTime;

    /** 是否自主注册  true 用户注册 ， false 后台注册 **/
    private Boolean isUserRegister;

    /** 广告位是否开放，true 开放，false 不开放 **/
    private Boolean isOpenAd;

    /** 是否开放会员卡，true  开放，false 不开放 **/
    private Boolean isOpenMemberCard;

    /** 会员卡描述 **/
    private String cardDesc;

    /** 是否开放余额支付 true 开放，false 不开放 **/
    private Boolean isOpenBalance;

    /** 应用的下载地址 **/
    private String appDownloadUrl;

    /** 安卓下载地址 **/
    private String androidDownloadUrl;

    /** ios 下载地址 **/
    private String iosDownloadUrl;

    /** 是否自动退款  true 自动退款， false 后台审核 **/
    private Boolean isAutomaticRefund;

    /** 是否需要认证 true 需要，false 不需要 **/
    private Boolean isNeedAuthen;

    /** 认证类型：身份证 id_card，学生证 student_card **/
    private String authenType;

    /** 是否需要上传证件  true 需要，false 不需要 **/
    private Boolean isNeedUpCertificates;

    /** 是否需要审核，true 需要，false 不需要 **/
    private Boolean isNeedAuthenCheck;
    
//    /** 联系电话 **/
//    private List<String> contactMobile;
    
//    private String callCenterInChine;
//    
//    private String callCenterInSingapore;
//    
//    private String callCenterMalaysia;
    
    //地图搜索范围
    private Integer mapSearchScope;
    
    /** qq 开放平台 appid **/
    private String qqAppId;
    
    private String playformDefaultCurrency;
    
    //通讯ip
    private String communicationIp;
    //通讯端口
    private String communicationPort;
    //通讯域名
    private String communicationDomainName;
    
//    //押金值（人民币）
//    private BigDecimal depositRMB;
//    
//    //押金值 (新加坡币）
//    private BigDecimal depositSingapore;
//    
//    //押金值（马来西亚币）
//    private BigDecimal depositMalaysia;
    
    //平台提成
    private BigDecimal platformExtraction;
    
//    //每笔最大返润值（人民币）
//    private BigDecimal eachMaximumRegurgitantRmb;
//    //每笔最大返润值（新加坡币）
//    private BigDecimal eachMaximumRegurgitantSingapore;
//   //每笔最大返润值（马来西亚币）
//    private BigDecimal eachMaximumRegurgitantMalaysia;
    private Integer isOnlineTime;
    
//    //基础会员费（人民币）
//    private BigDecimal 	basicCostRMB;
//    
//    //基础会员费 (新加坡币）
//    private BigDecimal basicCostSingapore;
//    
//    //基础会员费（马来西亚币）
//    private BigDecimal basicCostMalaysia;
    
    //推荐人
    private BigDecimal introductroRebateRate;
    
//    //雨伞购买（人民币）
//    private BigDecimal 	pbBuyCostRMB;
//    
//    //雨伞购买 (新加坡币）
//    private BigDecimal pbBuyCostSingapore;
//    
//    //雨伞购买（马来西亚币）
//    private BigDecimal pbBuyCostMalaysia;
    
    // 是否开启押金抵扣
    private Boolean isOpenDepositDeduction;
    
    // 抵扣的触发条件(单位天数)
    private Integer deductionDays;

    // 每天有多少小时(单位小时)
    private Integer hourOfDay;
    
    // 抵扣的触发条件(分钟)
    private Integer deductionMinute;
    
    // 是否开启短信提醒
    private Boolean isOpenSendSms;
    
    //短信提醒时长(单位小时)
    private Integer sendSmsHour;
    
    private Integer sendSmsMinute;
    
	public BigDecimal getIntroductroRebateRate() {
		return introductroRebateRate;
	}

	public void setIntroductroRebateRate(BigDecimal introductroRebateRate) {
		this.introductroRebateRate = introductroRebateRate;
	}

	public String getQqAppId() {
		return qqAppId;
	}

	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}
    
//	public List<String> getContactMobile() {
//		return contactMobile;
//	}
//
//	public void setContactMobile(List<String> contactMobile) {
//		this.contactMobile = contactMobile;
//	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

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

	public String getAppNameEnglish() {
		return appNameEnglish;
	}

	public void setAppNameEnglish(String appNameEnglish) {
		this.appNameEnglish = appNameEnglish;
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

	public Integer getImgTime() {
		return imgTime;
	}

	public void setImgTime(Integer imgTime) {
		this.imgTime = imgTime;
	}

	public Boolean getIsUserRegister() {
		return isUserRegister;
	}

	public void setIsUserRegister(Boolean isUserRegister) {
		this.isUserRegister = isUserRegister;
	}

	public Boolean getIsOpenAd() {
		return isOpenAd;
	}

	public void setIsOpenAd(Boolean isOpenAd) {
		this.isOpenAd = isOpenAd;
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

	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}

	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}

	public String getAndroidDownloadUrl() {
		return androidDownloadUrl;
	}

	public void setAndroidDownloadUrl(String androidDownloadUrl) {
		this.androidDownloadUrl = androidDownloadUrl;
	}

	public String getIosDownloadUrl() {
		return iosDownloadUrl;
	}

	public void setIosDownloadUrl(String iosDownloadUrl) {
		this.iosDownloadUrl = iosDownloadUrl;
	}

	public Boolean getIsAutomaticRefund() {
		return isAutomaticRefund;
	}

	public void setIsAutomaticRefund(Boolean isAutomaticRefund) {
		this.isAutomaticRefund = isAutomaticRefund;
	}

	public Boolean getIsNeedAuthen() {
		return isNeedAuthen;
	}

	public void setIsNeedAuthen(Boolean isNeedAuthen) {
		this.isNeedAuthen = isNeedAuthen;
	}

	public String getAuthenType() {
		return authenType;
	}

	public void setAuthenType(String authenType) {
		this.authenType = authenType;
	}

	public Boolean getIsNeedUpCertificates() {
		return isNeedUpCertificates;
	}

	public void setIsNeedUpCertificates(Boolean isNeedUpCertificates) {
		this.isNeedUpCertificates = isNeedUpCertificates;
	}

	public Boolean getIsNeedAuthenCheck() {
		return isNeedAuthenCheck;
	}

	public void setIsNeedAuthenCheck(Boolean isNeedAuthenCheck) {
		this.isNeedAuthenCheck = isNeedAuthenCheck;
	}

	public String getPlayformDefaultCurrency() {
		return playformDefaultCurrency;
	}

	public void setPlayformDefaultCurrency(String playformDefaultCurrency) {
		this.playformDefaultCurrency = playformDefaultCurrency;
	}

	public String getCommunicationIp() {
		return communicationIp;
	}

	public void setCommunicationIp(String communicationIp) {
		this.communicationIp = communicationIp;
	}

	public String getCommunicationPort() {
		return communicationPort;
	}

	public void setCommunicationPort(String communicationPort) {
		this.communicationPort = communicationPort;
	}

	public String getCommunicationDomainName() {
		return communicationDomainName;
	}

	public void setCommunicationDomainName(String communicationDomainName) {
		this.communicationDomainName = communicationDomainName;
	}

//	public BigDecimal getDepositRMB() {
//		return depositRMB;
//	}
//
//	public void setDepositRMB(BigDecimal depositRMB) {
//		this.depositRMB = depositRMB;
//	}

//	public BigDecimal getDepositSingapore() {
//		return depositSingapore;
//	}
//
//	public void setDepositSingapore(BigDecimal depositSingapore) {
//		this.depositSingapore = depositSingapore;
//	}
//
//	public BigDecimal getDepositMalaysia() {
//		return depositMalaysia;
//	}
//
//	public void setDepositMalaysia(BigDecimal depositMalaysia) {
//		this.depositMalaysia = depositMalaysia;
//	}

//	public BigDecimal getEachMaximumRegurgitantRmb() {
//		return eachMaximumRegurgitantRmb;
//	}
//
//	public void setEachMaximumRegurgitantRmb(BigDecimal eachMaximumRegurgitantRmb) {
//		this.eachMaximumRegurgitantRmb = eachMaximumRegurgitantRmb;
//	}
//
//	public BigDecimal getEachMaximumRegurgitantSingapore() {
//		return eachMaximumRegurgitantSingapore;
//	}
//
//	public void setEachMaximumRegurgitantSingapore(BigDecimal eachMaximumRegurgitantSingapore) {
//		this.eachMaximumRegurgitantSingapore = eachMaximumRegurgitantSingapore;
//	}
//
//	public BigDecimal getEachMaximumRegurgitantMalaysia() {
//		return eachMaximumRegurgitantMalaysia;
//	}
//
//	public void setEachMaximumRegurgitantMalaysia(BigDecimal eachMaximumRegurgitantMalaysia) {
//		this.eachMaximumRegurgitantMalaysia = eachMaximumRegurgitantMalaysia;
//	}

	public BigDecimal getPlatformExtraction() {
		return platformExtraction;
	}

	public void setPlatformExtraction(BigDecimal platformExtraction) {
		this.platformExtraction = platformExtraction;
	}

	public Integer getIsOnlineTime() {
		return isOnlineTime;
	}

	public void setIsOnlineTime(Integer isOnlineTime) {
		this.isOnlineTime = isOnlineTime;
	}

//	public BigDecimal getBasicCostRMB() {
//		return basicCostRMB;
//	}
//
//	public void setBasicCostRMB(BigDecimal basicCostRMB) {
//		this.basicCostRMB = basicCostRMB;
//	}
//
//	public BigDecimal getBasicCostSingapore() {
//		return basicCostSingapore;
//	}
//
//	public void setBasicCostSingapore(BigDecimal basicCostSingapore) {
//		this.basicCostSingapore = basicCostSingapore;
//	}
//
//	public BigDecimal getBasicCostMalaysia() {
//		return basicCostMalaysia;
//	}
//
//	public void setBasicCostMalaysia(BigDecimal basicCostMalaysia) {
//		this.basicCostMalaysia = basicCostMalaysia;
//	}

//	public String getCallCenterInChine() {
//		return callCenterInChine;
//	}
//
//	public void setCallCenterInChine(String callCenterInChine) {
//		this.callCenterInChine = callCenterInChine;
//	}
//
//	public String getCallCenterInSingapore() {
//		return callCenterInSingapore;
//	}
//
//	public void setCallCenterInSingapore(String callCenterInSingapore) {
//		this.callCenterInSingapore = callCenterInSingapore;
//	}
//
//	public String getCallCenterMalaysia() {
//		return callCenterMalaysia;
//	}
//
//	public void setCallCenterMalaysia(String callCenterMalaysia) {
//		this.callCenterMalaysia = callCenterMalaysia;
//	}

	public Integer getMapSearchScope() {
		return mapSearchScope;
	}

	public void setMapSearchScope(Integer mapSearchScope) {
		this.mapSearchScope = mapSearchScope;
	}

	public String getLogoRgb() {
		return logoRgb;
	}

	public void setLogoRgb(String logoRgb) {
		this.logoRgb = logoRgb;
	}

	public Boolean getIsOpenDepositDeduction() {
		return isOpenDepositDeduction;
	}

	public void setIsOpenDepositDeduction(Boolean isOpenDepositDeduction) {
		this.isOpenDepositDeduction = isOpenDepositDeduction;
	}

	public Integer getDeductionDays() {
		return deductionDays;
	}

	public void setDeductionDays(Integer deductionDays) {
		this.deductionDays = deductionDays;
	}

	public Integer getHourOfDay() {
		return hourOfDay;
	}

	public void setHourOfDay(Integer hourOfDay) {
		this.hourOfDay = hourOfDay;
	}

	public Integer getDeductionMinute() {
		return deductionMinute;
	}

	public void setDeductionMinute(Integer deductionMinute) {
		this.deductionMinute = deductionMinute;
	}

	public Integer getSendSmsHour() {
		return sendSmsHour;
	}

	public void setSendSmsHour(Integer sendSmsHour) {
		this.sendSmsHour = sendSmsHour;
	}

	public Boolean getIsOpenSendSms() {
		return isOpenSendSms;
	}

	public void setIsOpenSendSms(Boolean isOpenSendSms) {
		this.isOpenSendSms = isOpenSendSms;
	}

	public Integer getSendSmsMinute() {
		return sendSmsMinute;
	}

	public void setSendSmsMinute(Integer sendSmsMinute) {
		this.sendSmsMinute = sendSmsMinute;
	}
	 

//	public BigDecimal getPbBuyCostRMB() {
//		return pbBuyCostRMB;
//	}
//
//	public void setPbBuyCostRMB(BigDecimal pbBuyCostRMB) {
//		this.pbBuyCostRMB = pbBuyCostRMB;
//	}
//
//	public BigDecimal getPbBuyCostSingapore() {
//		return pbBuyCostSingapore;
//	}
//
//	public void setPbBuyCostSingapore(BigDecimal pbBuyCostSingapore) {
//		this.pbBuyCostSingapore = pbBuyCostSingapore;
//	}
//
//	public BigDecimal getPbBuyCostMalaysia() {
//		return pbBuyCostMalaysia;
//	}
//
//	public void setPbBuyCostMalaysia(BigDecimal pbBuyCostMalaysia) {
//		this.pbBuyCostMalaysia = pbBuyCostMalaysia;
//	}
	
}