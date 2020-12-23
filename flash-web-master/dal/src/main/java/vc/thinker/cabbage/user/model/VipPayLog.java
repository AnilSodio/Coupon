package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class VipPayLog extends BaseModel {
    /**  **/
    private Long id;

    /** 流水号 **/
    private String sn;

    /** 用户Id **/
    private Long uid;

    /** 金额 **/
    private BigDecimal amount;

    /** vip 折扣 **/
    private Double vipDiscount;

    /** vip天数/小时数 **/
    private Integer vipDay;

    /**  **/
    private String paymentMark;

    /** 创建 **/
    private Date createTime;

    /** 支付时间 **/
    private Date payTime;

    /** 外部订单号 **/
    private String outOrderId;

    /** 支付订单号 **/
    private String payOrderCode;

    /** 1:支付中 2:支付成功 **/
    private Integer status;

    /** 用户实际支付金额(单位元） **/
    private BigDecimal cashFee;

    /** 卡名称 **/
    private String vipCardName;

    /** 会员卡单位  day 天， hour 小时 **/
    private String vipCardUnit;

    /**  **/
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public Double getVipDiscount() {
        return vipDiscount;
    }

    public void setVipDiscount(Double vipDiscount) {
        this.vipDiscount = vipDiscount;
    }

    public Integer getVipDay() {
        return vipDay;
    }

    public void setVipDay(Integer vipDay) {
        this.vipDay = vipDay;
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

    public String getVipCardName() {
        return vipCardName;
    }

    public void setVipCardName(String vipCardName) {
        this.vipCardName = vipCardName;
    }

    public String getVipCardUnit() {
        return vipCardUnit;
    }

    public void setVipCardUnit(String vipCardUnit) {
        this.vipCardUnit = vipCardUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}