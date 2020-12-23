package vc.thinker.apiv2.bo;


import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIMembershipCardBO {

	/**  **/
    private Long id;

    /** 会员卡的名称 **/
    private String cardName;

    @ApiModelProperty("购买金额单位元")
    private BigDecimal cardAmount;

    @ApiModelProperty("会员卡有的期限（单位天")
    private Integer cardEffectiveTime;

    @ApiModelProperty(" 会员卡说明")
    private String cardDesc;
    
    @ApiModelProperty("会员卡单位  day 天， hour 小时 ")
    private String cardUnit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public BigDecimal getCardAmount() {
		return cardAmount;
	}

	public void setCardAmount(BigDecimal cardAmount) {
		this.cardAmount = cardAmount;
	}

	public Integer getCardEffectiveTime() {
		return cardEffectiveTime;
	}

	public void setCardEffectiveTime(Integer cardEffectiveTime) {
		this.cardEffectiveTime = cardEffectiveTime;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}
}