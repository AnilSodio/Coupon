package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class DepositPayLog extends BaseModel {
    /**  **/
    private Long id;

    /** 用户Id **/
    private Long uid;

    /** 金额 **/
    private BigDecimal amount;

    /**  **/
    private String paymentMark;

    /** 支付时间 **/
    private Date createTime;

    /**  **/
    private Date payTime;

    /** 外部订单号 **/
    private String outOrderId;

    /** 支付订单号 **/
    private String payOrderCode;

    /** 1:支付中 2:支付成功,3:退款审核中，4已退款 **/
    private Integer status;

    /** 用户实际支付金额(单位分） **/
    private BigDecimal cashFee;

    /** 退款申请时间 **/
    private Date refundApplyTime;

    /** 退款单号 **/
    private String refundId;

    /** 退款资金来源，未结算金额：REFUND_SOURCE_UNSETTLED_FUNDS  可用余额 REFUND_SOURCE_RECHARGE_FUNDS **/
    private String refundAccount;

    /** 退款成功时间 **/
    private Date refundSuccTime;

    /** 退款操作人 **/
    private Long refundOperator;

    /** 退款备注 **/
    private String refundRemark;

    /** 错误退款码 **/
    private String refundErrorCode;

    /** 退款错误信息 **/
    private String refundErrorMessage;

    /** 是否锁定 **/
    private Boolean isCapture;

    /** 锁定时间 **/
    private Date captureTime;

    /** 币种 **/
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMark() {
        return paymentMark;
    }

    public void setPaymentMark(String paymentMark) {
        this.paymentMark = paymentMark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getPayOrderCode() {
        return payOrderCode;
    }

    public void setPayOrderCode(String payOrderCode) {
        this.payOrderCode = payOrderCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public Date getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public Date getRefundSuccTime() {
        return refundSuccTime;
    }

    public void setRefundSuccTime(Date refundSuccTime) {
        this.refundSuccTime = refundSuccTime;
    }

    public Long getRefundOperator() {
        return refundOperator;
    }

    public void setRefundOperator(Long refundOperator) {
        this.refundOperator = refundOperator;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public String getRefundErrorCode() {
        return refundErrorCode;
    }

    public void setRefundErrorCode(String refundErrorCode) {
        this.refundErrorCode = refundErrorCode;
    }

    public String getRefundErrorMessage() {
        return refundErrorMessage;
    }

    public void setRefundErrorMessage(String refundErrorMessage) {
        this.refundErrorMessage = refundErrorMessage;
    }

    public Boolean getIsCapture() {
        return isCapture;
    }

    public void setIsCapture(Boolean isCapture) {
        this.isCapture = isCapture;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}