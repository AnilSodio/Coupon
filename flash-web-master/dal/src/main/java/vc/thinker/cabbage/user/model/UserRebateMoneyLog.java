package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserRebateMoneyLog extends BaseModel {
    /**  **/
    private Long id;

    /** 返利金的uid **/
    private Long userId;

    /** 得到返利金的uid **/
    private Long toUserId;

    /** 订单ID/如果为充值和购买VIP时为空 **/
    private Long orderId;

    /** 返利金额 **/
    private BigDecimal logAmount;

    /** 币种 **/
    private String logCurrency;

    /** 汇率 **/
    private BigDecimal exchangeRate;

    /** 反润金额 **/
    private BigDecimal oldLogAmount;

    /** 原币种/反润时的币种 **/
    private String oldLogCurrency;

    /**  **/
    private String logInfo;

    /**  **/
    private Date createTime;

    /**  **/
    private Integer userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getLogAmount() {
        return logAmount;
    }

    public void setLogAmount(BigDecimal logAmount) {
        this.logAmount = logAmount;
    }

    public String getLogCurrency() {
        return logCurrency;
    }

    public void setLogCurrency(String logCurrency) {
        this.logCurrency = logCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getOldLogAmount() {
        return oldLogAmount;
    }

    public void setOldLogAmount(BigDecimal oldLogAmount) {
        this.oldLogAmount = oldLogAmount;
    }

    public String getOldLogCurrency() {
        return oldLogCurrency;
    }

    public void setOldLogCurrency(String oldLogCurrency) {
        this.oldLogCurrency = oldLogCurrency;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}