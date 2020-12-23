package vc.thinker.apiv2.bo;


import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIPayAmountBO{
	   /**  **/
    private Long id;

    @ApiModelProperty("用户实际支付金额（单位元）")
    private BigDecimal payAmount;

    @ApiModelProperty("赠送金额（单位元）")
    private BigDecimal sendAmount;

    @ApiModelProperty("优惠说明")
    private String remark;
    
    @ApiModelProperty("币种")
    private String currency;
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public BigDecimal getSendAmount() {
		return sendAmount;
	}

	public void setSendAmount(BigDecimal sendAmount) {
		this.sendAmount = sendAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
}