package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class PlatformRevenue extends BaseModel {
    /**  **/
    private Long id;

    /** 业务记录 **/
    private Long sourceId;

    /** 记录类型 **/
    private String logType;

    /** 日志操作金额 **/
    private BigDecimal logAmount;

    /**  **/
    private Date createTime;

    /**  **/
    private Boolean isDeleted;

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

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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