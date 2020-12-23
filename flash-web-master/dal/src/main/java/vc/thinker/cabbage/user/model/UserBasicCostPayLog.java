package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserBasicCostPayLog extends BaseModel {
    /**  **/
    private Long id;

    /** 用户Id **/
    private Long uid;

    /** 金额 **/
    private BigDecimal amount;

    /** 支付方式 **/
    private String paymentMark;

    /** 创建时间 **/
    private Date createTime;

    /** 外部订单号 **/
    private String outOrderId;

    /** 支付订单号 **/
    private String payOrderCode;

    /** 1:支付中 2:支付成功 **/
    private Integer status;

    /** 用户实际支付金额 **/
    private BigDecimal cashFee;

    /**  **/
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}