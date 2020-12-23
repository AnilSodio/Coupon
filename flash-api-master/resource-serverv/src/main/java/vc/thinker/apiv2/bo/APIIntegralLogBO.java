package vc.thinker.apiv2.bo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * BO 用于返回数据
 *
 */
public class APIIntegralLogBO{


    /**  对应的业务记录id **/
    private Long bizSourceId;

    @ApiModelProperty("日志类型")
    private String logType;

    /** 日志操作金额 **/
    private Long logIntegral;

    @ApiModelProperty("描述")
    private String logInfo;
    
    @ApiModelProperty("创建时间")
    private Date createTime;
    
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
}