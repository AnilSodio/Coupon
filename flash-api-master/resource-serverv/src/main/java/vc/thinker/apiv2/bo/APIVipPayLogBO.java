package vc.thinker.apiv2.bo;


import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIVipPayLogBO {

    /** 流水号 **/
    private String sn;

    @ApiModelProperty(" 金额 ")
    private BigDecimal amount;

    @ApiModelProperty("vip 折扣 ")
    private Double vipDiscount;

    @ApiModelProperty(" vip天数")
    private Integer vipDay;

    /**  **/
    @ApiModelProperty("支付方式")
    private String paymentMark;

    /** 创建 **/
    private Date createTime;

    /** 支付时间 **/
    private Date payTime;

    @ApiModelProperty("1:支付中 2:支付成功")
    private Integer status;

    @ApiModelProperty("卡名称")
    private String vipCardName;
    
    @ApiModelProperty("支付方式名称")
    private String paymentMarkName;
    
	public String getPaymentMarkName() {
		return paymentMarkName;
	}

	public void setPaymentMarkName(String paymentMarkName) {
		this.paymentMarkName = paymentMarkName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVipCardName() {
		return vipCardName;
	}

	public void setVipCardName(String vipCardName) {
		this.vipCardName = vipCardName;
	}
}