package vc.thinker.apiv2.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class MemberBO {

	@ApiModelProperty("姓名 ") 
    private String name;

    @ApiModelProperty("昵称 ") 
    private String nickname;

    @ApiModelProperty("性别 1，男 2，女")
    private Integer sex;

    /** 身高 **/
    private Integer height;

    /** 体重 **/
    private Integer weight;

    /** 出生日期 **/
    private Date birthdate;

    @ApiModelProperty("头像路径 ") 
    private String headImgPath;

    /** 邮箱 **/
    private String email;

    @ApiModelProperty("手机 ") 
    private String mobile;

    @ApiModelProperty("状态 1:正常 2：未激活,3禁用")
    private String status;

    @ApiModelProperty("身份证") 
    private String idCard;

    @ApiModelProperty("备注") 
    private String remark;
    
    @ApiModelProperty("邀请码")
    private String inviteCode;

    @ApiModelProperty("认证状态：0未认证，1认证中，2认证成功，3认证失败")
    private String authStatus;
    
    @ApiModelProperty("认证步骤：1.手机认证,2. 押金充值 3.实名认证 4.认证完成")
    private Integer authStep;
    
    @ApiModelProperty("押金")
    private BigDecimal deposit;
    
    @ApiModelProperty("工号")
    private String jobNumber;
    
    @ApiModelProperty("vip到期时间")
    private Date vipExpiresIn;

    @ApiModelProperty("vip 折扣 ")
    private Double vipDiscount;
    
    @ApiModelProperty("余额")
    private BigDecimal balance;
    
    @ApiModelProperty("是否vip")
    private Boolean isVIP;
    
    @ApiModelProperty("会员到期时间描述")
    private String vipDxpireDateDesc;
    
    @ApiModelProperty("审批备注")
    private String authApplyRemark;

    @ApiModelProperty(" 证件图片")
    private List<String> credentialsImages;

    @ApiModelProperty("学校")
    private String schoolName;

    @ApiModelProperty("学号")
    private String studentId;
    
    @ApiModelProperty("认证审批状态 1 审批中 2审批成功 3审批失败 ")
    private Integer authApplyStatus;
    
    @ApiModelProperty("是否绑定Weixin")
	private Boolean isBindWeixin;
	
    @ApiModelProperty("是否绑定QQ")
	private Boolean isBindQQ;
    
    @ApiModelProperty("是否绑定fasebook")
    private Boolean isBindFasebook;
    
    @ApiModelProperty("是否绑定手机号")
    private Boolean isBindMobile;
    
    @ApiModelProperty("是否绑定google")
    private Boolean isBindGoogl;
    
    @ApiModelProperty("积分")
    private Long integral;
    
    @ApiModelProperty("币种")
    private String currency; 

    @ApiModelProperty("奖励金")
    private Long rewardAmount;
    
    @ApiModelProperty("绑定的充电柜绑定系统编号")
    private String sysCode;

    @ApiModelProperty("是否缴纳基础会员费")
    private Boolean isPayBasicCost;
    
    @ApiModelProperty("绑定的机柜别名")
    private String cabinetAlias;
    
	public Boolean getIsBindMobile() {
		return isBindMobile;
	}

	public void setIsBindMobile(Boolean isBindMobile) {
		this.isBindMobile = isBindMobile;
	}
	public Long getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(Long rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public String getVipDxpireDateDesc() {
		return vipDxpireDateDesc;
	}

	public void setVipDxpireDateDesc(String vipDxpireDateDesc) {
		this.vipDxpireDateDesc = vipDxpireDateDesc;
	}
    
	public Boolean getIsBindWeixin() {
		return isBindWeixin;
	}

	public void setIsBindWeixin(Boolean isBindWeixin) {
		this.isBindWeixin = isBindWeixin;
	}

	public Boolean getIsBindQQ() {
		return isBindQQ;
	}

	public void setIsBindQQ(Boolean isBindQQ) {
		this.isBindQQ = isBindQQ;
	}

	public Integer getAuthApplyStatus() {
		return authApplyStatus;
	}

	public void setAuthApplyStatus(Integer authApplyStatus) {
		this.authApplyStatus = authApplyStatus;
	}

	public String getAuthApplyRemark() {
		return authApplyRemark;
	}

	public void setAuthApplyRemark(String authApplyRemark) {
		this.authApplyRemark = authApplyRemark;
	}

	public List<String> getCredentialsImages() {
		return credentialsImages;
	}

	public void setCredentialsImages(List<String> credentialsImages) {
		this.credentialsImages = credentialsImages;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Boolean getIsVIP() {
		return isVIP;
	}

	public void setIsVIP(Boolean isVIP) {
		this.isVIP = isVIP;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getVipExpiresIn() {
		return vipExpiresIn;
	}

	public void setVipExpiresIn(Date vipExpiresIn) {
		this.vipExpiresIn = vipExpiresIn;
	}

	public Double getVipDiscount() {
		return vipDiscount;
	}

	public void setVipDiscount(Double vipDiscount) {
		this.vipDiscount = vipDiscount;
	}

	

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getHeadImgPath() {
		return headImgPath;
	}

	public void setHeadImgPath(String headImgPath) {
		this.headImgPath = headImgPath;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public Integer getAuthStep() {
		return authStep;
	}

	public void setAuthStep(Integer authStep) {
		this.authStep = authStep;
	}

	public Boolean getIsBindFasebook() {
		return isBindFasebook;
	}

	public void setIsBindFasebook(Boolean isBindFasebook) {
		this.isBindFasebook = isBindFasebook;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Boolean getIsPayBasicCost() {
		return isPayBasicCost;
	}

	public void setIsPayBasicCost(Boolean isPayBasicCost) {
		this.isPayBasicCost = isPayBasicCost;
	}

	public String getCabinetAlias() {
		return cabinetAlias;
	}

	public void setCabinetAlias(String cabinetAlias) {
		this.cabinetAlias = cabinetAlias;
	}

	public Boolean getIsBindGoogl() {
		return isBindGoogl;
	}

	public void setIsBindGoogl(Boolean isBindGoogl) {
		this.isBindGoogl = isBindGoogl;
	}
	
}