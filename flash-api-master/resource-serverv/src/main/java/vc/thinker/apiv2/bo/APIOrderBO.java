package vc.thinker.apiv2.bo;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import vc.thinker.cabbage.se.model.Order;
/**
 * 
 * BO 用于返回数据
 *
 */
public class APIOrderBO extends Order{
	
    private String orderCode;

    @ApiModelProperty("借的充电柜编码")
    private String borrowSysCode;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("开始时间")
    private Date beginTime;

    @ApiModelProperty("结束时间")
    private Date finishTime;

    @ApiModelProperty("支付时间")
    private Date payTime;

    @ApiModelProperty("支付方式")
    private String paymentMark;

    @ApiModelProperty("状态  10:创建中 20: 创建失败 30:进行中 40:未支付 50:已支付")
    private Integer status;

    @ApiModelProperty("用户优惠券")
    private Long userCouponId;

    /**  **/
    private BigDecimal beginLocationLon;

    /**  **/
    private BigDecimal beginLocationLat;

    /**  **/
    private BigDecimal endLocationLon;

    /**  **/
    private BigDecimal endLocationLat;

    @ApiModelProperty("订单时长，分钟")
    private Integer rideTime;

    @ApiModelProperty("借的商户")
    private Long borrowSellerId;

    @ApiModelProperty("还的商户")
    private Long returnSellerId;

    @ApiModelProperty("还的机柜sysCode")
    private String returnSysCode;

    @ApiModelProperty("开始的详细地址")
    private String beginLocationDetails;

    @ApiModelProperty("结束的详细地址")
    private String endLocaitonDetails;

    @ApiModelProperty("支付金额")
    private BigDecimal payPrice;

    @ApiModelProperty("支付类型：免费：free  现金： cash  会员卡：vip  余额：balance,押金抵扣，pb_buy")
    private String payType;

    @ApiModelProperty("充电宝 code")
    private String pbCode;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("币种")
    private String currency;

    /**  **/
    private String returnType;

    @ApiModelProperty("机柜借的状态码")
    private String borrowCabinetStatusCode;	
    
    @ApiModelProperty("服务商的名称")
	private String sellerName;
    
    @ApiModelProperty("适合该订单的优惠券")
    private APIUserCouponBO fitCoupon;
    
    @ApiModelProperty("进行中的投诉")
    private List<APIFeedbackBO> doingFeedbacks;
    
    @ApiModelProperty("收费规则描述")
	private String chargeRuleDesc;
    
    @ApiModelProperty("汇率描述")
    private String exchangeRateDesc;
    
    @ApiModelProperty("汇率转换后的价格")
    private BigDecimal ratePrice;
    
	public BigDecimal getRatePrice() {
		return ratePrice;
	}

	public void setRatePrice(BigDecimal ratePrice) {
		this.ratePrice = ratePrice;
	}

	public String getExchangeRateDesc() {
		return exchangeRateDesc;
	}

	public void setExchangeRateDesc(String exchangeRateDesc) {
		this.exchangeRateDesc = exchangeRateDesc;
	}

	public String getChargeRuleDesc() {
		return chargeRuleDesc;
	}

	public void setChargeRuleDesc(String chargeRuleDesc) {
		this.chargeRuleDesc = chargeRuleDesc;
	}

	public List<APIFeedbackBO> getDoingFeedbacks() {
		return doingFeedbacks;
	}

	public void setDoingFeedbacks(List<APIFeedbackBO> doingFeedbacks) {
		this.doingFeedbacks = doingFeedbacks;
	}

	public APIUserCouponBO getFitCoupon() {
		return fitCoupon;
	}

	public void setFitCoupon(APIUserCouponBO fitCoupon) {
		this.fitCoupon = fitCoupon;
	}

	@Override
	public String getOrderCode() {
		return orderCode;
	}

	@Override
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	@Override
	public String getBorrowSysCode() {
		return borrowSysCode;
	}

	@Override
	public void setBorrowSysCode(String borrowSysCode) {
		this.borrowSysCode = borrowSysCode;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public Date getBeginTime() {
		return beginTime;
	}

	@Override
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Override
	public Date getFinishTime() {
		return finishTime;
	}

	@Override
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public Date getPayTime() {
		return payTime;
	}

	@Override
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Override
	public String getPaymentMark() {
		return paymentMark;
	}

	@Override
	public void setPaymentMark(String paymentMark) {
		this.paymentMark = paymentMark;
	}

	@Override
	public Integer getStatus() {
		return status;
	}

	@Override
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Long getUserCouponId() {
		return userCouponId;
	}

	@Override
	public void setUserCouponId(Long userCouponId) {
		this.userCouponId = userCouponId;
	}

	@Override
	public BigDecimal getBeginLocationLon() {
		return beginLocationLon;
	}

	@Override
	public void setBeginLocationLon(BigDecimal beginLocationLon) {
		this.beginLocationLon = beginLocationLon;
	}

	@Override
	public BigDecimal getBeginLocationLat() {
		return beginLocationLat;
	}

	@Override
	public void setBeginLocationLat(BigDecimal beginLocationLat) {
		this.beginLocationLat = beginLocationLat;
	}

	@Override
	public BigDecimal getEndLocationLon() {
		return endLocationLon;
	}

	@Override
	public void setEndLocationLon(BigDecimal endLocationLon) {
		this.endLocationLon = endLocationLon;
	}

	@Override
	public BigDecimal getEndLocationLat() {
		return endLocationLat;
	}

	@Override
	public void setEndLocationLat(BigDecimal endLocationLat) {
		this.endLocationLat = endLocationLat;
	}

	@Override
	public Integer getRideTime() {
		return rideTime;
	}

	@Override
	public void setRideTime(Integer rideTime) {
		this.rideTime = rideTime;
	}

	@Override
	public Long getBorrowSellerId() {
		return borrowSellerId;
	}

	@Override
	public void setBorrowSellerId(Long borrowSellerId) {
		this.borrowSellerId = borrowSellerId;
	}

	@Override
	public Long getReturnSellerId() {
		return returnSellerId;
	}

	@Override
	public void setReturnSellerId(Long returnSellerId) {
		this.returnSellerId = returnSellerId;
	}

	@Override
	public String getReturnSysCode() {
		return returnSysCode;
	}

	@Override
	public void setReturnSysCode(String returnSysCode) {
		this.returnSysCode = returnSysCode;
	}

	@Override
	public String getBeginLocationDetails() {
		return beginLocationDetails;
	}

	@Override
	public void setBeginLocationDetails(String beginLocationDetails) {
		this.beginLocationDetails = beginLocationDetails;
	}

	@Override
	public String getEndLocaitonDetails() {
		return endLocaitonDetails;
	}

	@Override
	public void setEndLocaitonDetails(String endLocaitonDetails) {
		this.endLocaitonDetails = endLocaitonDetails;
	}

	@Override
	public BigDecimal getPayPrice() {
		return payPrice;
	}

	@Override
	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	@Override
	public String getPayType() {
		return payType;
	}

	@Override
	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Override
	public String getPbCode() {
		return pbCode;
	}

	@Override
	public void setPbCode(String pbCode) {
		this.pbCode = pbCode;
	}

	@Override
	public String getCountry() {
		return country;
	}

	@Override
	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String getReturnType() {
		return returnType;
	}

	@Override
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	@Override
	public String getBorrowCabinetStatusCode() {
		return borrowCabinetStatusCode;
	}

	@Override
	public void setBorrowCabinetStatusCode(String borrowCabinetStatusCode) {
		this.borrowCabinetStatusCode = borrowCabinetStatusCode;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
    
//    @ApiModelProperty("进行中的投诉")
//    private List<APIFeedbackBO> doingFeedbacks;
}