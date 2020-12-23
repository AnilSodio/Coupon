package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class OrderPbBuy extends BaseModel {
    /**  **/
    private Long id;

    /** 购买人 **/
    private Long uid;

    /** 伞ID **/
    private Long pbId;

    /** 伞编号 **/
    private String pbCode;

    /** 订单id **/
    private Long orderId;

    /** 订单编号 **/
    private String orderCode;

    /** 创建时间 **/
    private Date createTime;

    /** 订单金额 **/
    private BigDecimal orderAmount;

    /** 支付金额 **/
    private BigDecimal cashFee;

    /** 充电宝金额 **/
    private BigDecimal pbAmount;

    /** 币种 **/
    private String currency;

    /** 1，购买中，2 购买成功 **/
    private Integer status;

    /**  **/
    private String payOrderCode;

    /**  **/
    private String outOrderId;

    /**  **/
    private String paymentMark;

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

    public Long getPbId() {
        return pbId;
    }

    public void setPbId(Long pbId) {
        this.pbId = pbId;
    }

    public String getPbCode() {
        return pbCode;
    }

    public void setPbCode(String pbCode) {
        this.pbCode = pbCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getCashFee() {
        return cashFee;
    }

    public void setCashFee(BigDecimal cashFee) {
        this.cashFee = cashFee;
    }

    public BigDecimal getPbAmount() {
        return pbAmount;
    }

    public void setPbAmount(BigDecimal pbAmount) {
        this.pbAmount = pbAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayOrderCode() {
        return payOrderCode;
    }

    public void setPayOrderCode(String payOrderCode) {
        this.payOrderCode = payOrderCode;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getPaymentMark() {
        return paymentMark;
    }

    public void setPaymentMark(String paymentMark) {
        this.paymentMark = paymentMark;
    }
}