package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserMoneyCash extends BaseModel {
    /**  **/
    private Long id;

    /** 提现编号以cash开头 **/
    private String cashSn;

    /** 服务商id  **/
    private Long cashUserId;

    /** '1 商户，2 推荐人，3代理商' **/
    private String cashUserType;

    /** 提现金额 **/
    private BigDecimal cashAmount;

    /** 收款人姓名 **/
    private String cashUserName;

    /** 收款账号 **/
    private String cashAccount;

    /** 收款支付状态，-1为未审核，0为等待支付，1为支付完成，2为审核失败 **/
    private String cashStatus;

    /** 收款银行 **/
    private String cashBank;

    /** 提现备注 **/
    private String cashInfo;

    /** 提现方式 **/
    private String cashPaymentType;

    /** 充值请求处理的管理员 **/
    private Long cashAdminId;

    /** 请求处理备注 **/
    private String cashAdminInfo;

    /**  **/
    private Date createTime;

    /** 0是没删，1是删除 **/
    private Boolean isDeleted;

    /** 审核时间 **/
    private Date checkTime;

    /** 转账时间 **/
    private Date transTime;

    /** 商户订单号 **/
    private String partnerTradeNo;

    /** 微信订单号 **/
    private String paymentNo;

    /** 手续费 **/
    private Integer cmmsAmt;

    /** 转账操作人 **/
    private Long transUid;

    /** 币种 **/
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashSn() {
        return cashSn;
    }

    public void setCashSn(String cashSn) {
        this.cashSn = cashSn;
    }

    public Long getCashUserId() {
        return cashUserId;
    }

    public void setCashUserId(Long cashUserId) {
        this.cashUserId = cashUserId;
    }

    public String getCashUserType() {
        return cashUserType;
    }

    public void setCashUserType(String cashUserType) {
        this.cashUserType = cashUserType;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public String getCashUserName() {
        return cashUserName;
    }

    public void setCashUserName(String cashUserName) {
        this.cashUserName = cashUserName;
    }

    public String getCashAccount() {
        return cashAccount;
    }

    public void setCashAccount(String cashAccount) {
        this.cashAccount = cashAccount;
    }

    public String getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(String cashStatus) {
        this.cashStatus = cashStatus;
    }

    public String getCashBank() {
        return cashBank;
    }

    public void setCashBank(String cashBank) {
        this.cashBank = cashBank;
    }

    public String getCashInfo() {
        return cashInfo;
    }

    public void setCashInfo(String cashInfo) {
        this.cashInfo = cashInfo;
    }

    public String getCashPaymentType() {
        return cashPaymentType;
    }

    public void setCashPaymentType(String cashPaymentType) {
        this.cashPaymentType = cashPaymentType;
    }

    public Long getCashAdminId() {
        return cashAdminId;
    }

    public void setCashAdminId(Long cashAdminId) {
        this.cashAdminId = cashAdminId;
    }

    public String getCashAdminInfo() {
        return cashAdminInfo;
    }

    public void setCashAdminInfo(String cashAdminInfo) {
        this.cashAdminInfo = cashAdminInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    public String getPartnerTradeNo() {
        return partnerTradeNo;
    }

    public void setPartnerTradeNo(String partnerTradeNo) {
        this.partnerTradeNo = partnerTradeNo;
    }

    public String getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Integer getCmmsAmt() {
        return cmmsAmt;
    }

    public void setCmmsAmt(Integer cmmsAmt) {
        this.cmmsAmt = cmmsAmt;
    }

    public Long getTransUid() {
        return transUid;
    }

    public void setTransUid(Long transUid) {
        this.transUid = transUid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}