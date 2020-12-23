package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class Member extends BaseModel {
    /** 用户id **/
    private Long uid;

    /** 姓名 **/
    private String name;

    /** 昵称 **/
    private String nickname;

    /** 性别 1，男 2，女 **/
    private Integer sex;

    /** 身高 **/
    private Integer height;

    /** 体重 **/
    private Integer weight;

    /** 出生日期 **/
    private Date birthdate;

    /** 头像路径 **/
    private String headImgPath;

    /** 邮箱 **/
    private String email;

    /** 手机 **/
    private String mobile;

    /** 状态 1:正常 2：未激活,3禁用 **/
    private String status;

    /** 创建者 **/
    private String createBy;

    /** 创建时间 **/
    private Date createTime;

    /** 更新者 **/
    private String updateBy;

    /** 更新时间 **/
    private Date updateTime;

    /** 是否删除 **/
    private Boolean isDeleted;

    /** 身份证 **/
    private String idCard;

    /** 支付所用openid **/
    private String payOpenId;

    /** 备注 **/
    private String remark;

    /** 邀请码 **/
    private String inviteCode;

    /** 认证状态：0未认证，1认证中，2认证成功，3认证失败 **/
    private String authStatus;

    /** 骑行距离/单位M **/
    private Double rideDistance;

    /** 骑行时间/秒 **/
    private Long rideTime;

    /** 押金 **/
    private BigDecimal deposit;

    /** 认证步骤：1.手机认证,2. 押金充值 3.实名认证 4.认证完成 **/
    private Integer authStep;

    /** 受邀的邀请码 **/
    private String invitedInviteCode;

    /** 工号 **/
    private String jobNumber;

    /** 注册渠道 安卓：Android，苹果：ios,微信：wx，平台：platform **/
    private String registChannel;

    /** vip到期时间 **/
    private Date vipExpiresIn;

    /** vip 折扣 **/
    private Double vipDiscount;

    /** 注册的客户端源 **/
    private String clientSource;

    /** 注册ip **/
    private String registeredIp;

    /** 审批备注 **/
    private String authApplyRemark;

    /** 证件图片，json数组 **/
    private String credentialsImages;

    /** 学校 **/
    private String schoolName;

    /** 学号 **/
    private String studentId;

    /** 认证审批状态 1 审批中 2审批成功 3审批失败 **/
    private Integer authApplyStatus;

    /** 电机功率 **/
    private Integer motorPower;

    /** 国家 **/
    private String country;

    /** stripe 支付的客户id **/
    private String stripeCustomerId;

    /** 币种 **/
    private String currency;

    /** 奖励金 **/
    private Long rewardAmount;

    /** 充电柜绑定的系统编号 **/
    private String sysCode;

    /** 是否已经购买基础会员费 **/
    private Boolean isPayBasicCost;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPayOpenId() {
        return payOpenId;
    }

    public void setPayOpenId(String payOpenId) {
        this.payOpenId = payOpenId;
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

    public Double getRideDistance() {
        return rideDistance;
    }

    public void setRideDistance(Double rideDistance) {
        this.rideDistance = rideDistance;
    }

    public Long getRideTime() {
        return rideTime;
    }

    public void setRideTime(Long rideTime) {
        this.rideTime = rideTime;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getAuthStep() {
        return authStep;
    }

    public void setAuthStep(Integer authStep) {
        this.authStep = authStep;
    }

    public String getInvitedInviteCode() {
        return invitedInviteCode;
    }

    public void setInvitedInviteCode(String invitedInviteCode) {
        this.invitedInviteCode = invitedInviteCode;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getRegistChannel() {
        return registChannel;
    }

    public void setRegistChannel(String registChannel) {
        this.registChannel = registChannel;
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

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }

    public String getRegisteredIp() {
        return registeredIp;
    }

    public void setRegisteredIp(String registeredIp) {
        this.registeredIp = registeredIp;
    }

    public String getAuthApplyRemark() {
        return authApplyRemark;
    }

    public void setAuthApplyRemark(String authApplyRemark) {
        this.authApplyRemark = authApplyRemark;
    }

    public String getCredentialsImages() {
        return credentialsImages;
    }

    public void setCredentialsImages(String credentialsImages) {
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

    public Integer getAuthApplyStatus() {
        return authApplyStatus;
    }

    public void setAuthApplyStatus(Integer authApplyStatus) {
        this.authApplyStatus = authApplyStatus;
    }

    public Integer getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(Integer motorPower) {
        this.motorPower = motorPower;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStripeCustomerId() {
        return stripeCustomerId;
    }

    public void setStripeCustomerId(String stripeCustomerId) {
        this.stripeCustomerId = stripeCustomerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Long rewardAmount) {
        this.rewardAmount = rewardAmount;
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
}