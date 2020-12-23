package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class IntegralLog extends BaseModel {
    /**  **/
    private Long id;

    /** 日志操作用户 **/
    private Long uid;

    /**  对应的业务记录id **/
    private Long bizSourceId;

    /** 记录类型 **/
    private String logType;

    /** 日志操作金额 **/
    private Long logIntegral;

    /** 日志信息 **/
    private String logInfo;

    /**  **/
    private Date createTime;

    /** 业务需要code **/
    private String bizSourceCode;

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

    public Long getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(Long bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public Long getLogIntegral() {
        return logIntegral;
    }

    public void setLogIntegral(Long logIntegral) {
        this.logIntegral = logIntegral;
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

    public String getBizSourceCode() {
        return bizSourceCode;
    }

    public void setBizSourceCode(String bizSourceCode) {
        this.bizSourceCode = bizSourceCode;
    }
}