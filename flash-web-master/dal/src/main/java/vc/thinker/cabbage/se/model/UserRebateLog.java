package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserRebateLog extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /**  **/
    private Long orderId;

    /**  **/
    private String rebateModel;

    /** 反润时的计算比率 **/
    private BigDecimal rebateRate;

    /** 反润时的税率 **/
    private BigDecimal raxRate;

    /**  **/
    private BigDecimal rebateAmount;

    /** 是否发放 **/
    private Boolean sendStatus;

    /** 发放时间 **/
    private Date sendTime;

    /**  **/
    private Date createTime;

    /**  **/
    private String orderCode;

    /** 币种 **/
    private String currency;

    /** 反润类型 基础会员充值:basic_vip, 会员充值 vip，余额充值 recharge **/
    private String rebateType;

    /**  **/
    private Long payUid;

    /** seller 商户，referee 推荐人， agent 代理商 **/
    private String userType;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getRebateModel() {
        return rebateModel;
    }

    public void setRebateModel(String rebateModel) {
        this.rebateModel = rebateModel;
    }

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
    }

    public BigDecimal getRaxRate() {
        return raxRate;
    }

    public void setRaxRate(BigDecimal raxRate) {
        this.raxRate = raxRate;
    }

    public BigDecimal getRebateAmount() {
        return rebateAmount;
    }

    public void setRebateAmount(BigDecimal rebateAmount) {
        this.rebateAmount = rebateAmount;
    }

    public Boolean getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRebateType() {
        return rebateType;
    }

    public void setRebateType(String rebateType) {
        this.rebateType = rebateType;
    }

    public Long getPayUid() {
        return payUid;
    }

    public void setPayUid(Long payUid) {
        this.payUid = payUid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}