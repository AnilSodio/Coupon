package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserDepositLog extends BaseModel {
    /**  **/
    private Long id;

    /** 用户ID **/
    private Long uid;

    /** 金额 **/
    private BigDecimal amount;

    /** 类型:1付款成功 2 退款中 3.退款成功 ,4订单抵扣**/
    private String type;

    /**  **/
    private Date createTime;

    /**  **/
    private Date updateTime;

    /** 操作的管理员 **/
    private Long dealBy;

    /** 审核时间 **/
    private Date checkTime;

    /** 审核时，由当前操作员输入 **/
    private String remark;

    /** 商户订单号 **/
    private String outTradeNo;

    /** 商户退款单号，我们平台生成，发送给微信 **/
    private String outRefundNo;

    /** 微信退款单号，微信返回的退款流水 **/
    private String refundId;

    /** 支付方式 **/
    private String payMark;

    /** 退款资金来源，未结算金额：REFUND_SOURCE_UNSETTLED_FUNDS  可用余额 REFUND_SOURCE_RECHARGE_FUNDS **/
    private String refundAccount;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getDealBy() {
        return dealBy;
    }

    public void setDealBy(Long dealBy) {
        this.dealBy = dealBy;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getPayMark() {
        return payMark;
    }

    public void setPayMark(String payMark) {
        this.payMark = payMark;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}