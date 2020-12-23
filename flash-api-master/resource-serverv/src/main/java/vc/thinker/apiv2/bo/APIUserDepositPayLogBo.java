package vc.thinker.apiv2.bo;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class APIUserDepositPayLogBo {

	@ApiModelProperty("金额 ")
    private BigDecimal amount;
	
	@ApiModelProperty("币种")
    private String currency;

	@ApiModelProperty("类型:1付款成功 2 退款中 3.退款成功 ,4订单抵扣")
    private String type;

	@ApiModelProperty("时间")
    private Date createTime;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
}
