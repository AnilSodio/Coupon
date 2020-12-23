package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class UserMoneyLog extends BaseModel {
    /**  **/
    private Long id;

    /** 日志操作用户 **/
    private Long logUserId;

    /**  对应的预存款记录 **/
    private Long logSourceId;

    /** order_rebate:订单反润、cash:提现 **/
    private String logType;

    /** 日志操作金额 **/
    private BigDecimal logAmount;

    /**  **/
    private Date createTime;

    /**  **/
    private Boolean isDeleted;

    /** 外部充值订单id,用于防止重复充值 **/
    private String outOrderId;

    /** 原金额 **/
    private BigDecimal oldLogAmount;

    /** 原币种 **/
    private String oldLogCurrency;

    /** 汇率 **/
    private BigDecimal exchangeRate;

    /** 币种 **/
    private String logCurrency;

    /** 日志信息 **/
    private String logInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogUserId() {
        return logUserId;
    }

    public void setLogUserId(Long logUserId) {
        this.logUserId = logUserId;
    }

    public Long getLogSourceId() {
        return logSourceId;
    }

    public void setLogSourceId(Long logSourceId) {
        this.logSourceId = logSourceId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public BigDecimal getLogAmount() {
        return logAmount;
    }

    public void setLogAmount(BigDecimal logAmount) {
        this.logAmount = logAmount;
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

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
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

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getLogCurrency() {
        return logCurrency;
    }

    public void setLogCurrency(String logCurrency) {
        this.logCurrency = logCurrency;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
}