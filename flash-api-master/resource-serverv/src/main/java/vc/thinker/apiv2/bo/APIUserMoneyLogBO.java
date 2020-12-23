package vc.thinker.apiv2.bo;


import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIUserMoneyLogBO{


    /** 日志操作用户 **/
    private Long logUserId;

    /**  对应的预存款记录 **/
    private Long logSourceId;

    @ApiModelProperty("操作类型，分为充值、提现、消费、兑换金币、人工操作")
    private String logType;

    @ApiModelProperty(" 日志操作金额 ")
    private BigDecimal logAmount;

    /**  **/
    private Date createTime;

    /**  **/
    private Boolean isDeleted;

    /** 外部充值订单id,用于防止重复充值 **/
    private String outOrderId;

    @ApiModelProperty("日志信息")
    private String logInfo;

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

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
}