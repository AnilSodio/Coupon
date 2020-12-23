package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class Order extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /** 借的充电柜ID **/
    private Long borrowCabinetId;

    /** 还的充电柜编码 **/
    private String borrowSysCode;

    /** 价格 **/
    private BigDecimal price;

    /**  **/
    private Boolean isDeleted;

    /** 创建时间 **/
    private Date createTime;

    /** 开始时间 **/
    private Date beginTime;

    /** 结束时间 **/
    private Date finishTime;

    /**  **/
    private Date payTime;

    /** 支付方式 **/
    private String paymentMark;

    /** 状态  10:创建中 20: 创建失败 30:进行中 40:未支付 50:已支付 **/
    private Integer status;

    /** 支付的外部写单号 **/
    private String outOrderId;

    /** 支付的内部订单号 **/
    private String payOrderCode;

    /** 用户优惠券 **/
    private Long userCouponId;

    /** 所属城市 **/
    private String cityId;

    /** 所属机构 **/
    private Long officeId;

    /**  **/
    private BigDecimal beginLocationLon;

    /**  **/
    private BigDecimal beginLocationLat;

    /**  **/
    private BigDecimal endLocationLon;

    /**  **/
    private BigDecimal endLocationLat;

    /** 审核人 **/
    private String checkName;

    /** 审核时间 **/
    private Date checkTime;

    /** 审核描述 **/
    private String checkDesc;

    /**  **/
    private String orderCode;

    /** 订单时长 **/
    private Integer rideTime;

    /** 借的代理点 **/
    private Long borrowSellerId;

    /** 还的代理点 **/
    private Long returnSellerId;

    /**  **/
    private Long returnCabinetId;

    /**  **/
    private String returnSysCode;

    /**  **/
    private String beginLocationDetails;

    /**  **/
    private String endLocaitonDetails;

    /** 最终支付的金额 **/
    private BigDecimal payPrice;

    /** 借的通道号 **/
    private Integer borrowChannel;

    /** 还的通道号 **/
    private Integer returnChannel;

    /** 支付类型：免费：free  现金： cash  会员卡：vip  余额：balance   **/
    private String payType;

    /** 充电宝ID **/
    private Long pbId;

    /** 充电宝 code **/
    private String pbCode;

    /**  **/
    private String country;

    /**  **/
    private String currency;

    /**  **/
    private String returnType;

    /** 机柜借的状态码 0 收到消息确认, 通信成功  **/
    private String borrowCabinetStatusCode;

    /** 客户端源 **/
    private String clientSource;

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

    public Long getBorrowCabinetId() {
        return borrowCabinetId;
    }

    public void setBorrowCabinetId(Long borrowCabinetId) {
        this.borrowCabinetId = borrowCabinetId;
    }

    public String getBorrowSysCode() {
        return borrowSysCode;
    }

    public void setBorrowSysCode(String borrowSysCode) {
        this.borrowSysCode = borrowSysCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPaymentMark() {
        return paymentMark;
    }

    public void setPaymentMark(String paymentMark) {
        this.paymentMark = paymentMark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getPayOrderCode() {
        return payOrderCode;
    }

    public void setPayOrderCode(String payOrderCode) {
        this.payOrderCode = payOrderCode;
    }

    public Long getUserCouponId() {
        return userCouponId;
    }

    public void setUserCouponId(Long userCouponId) {
        this.userCouponId = userCouponId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public BigDecimal getBeginLocationLon() {
        return beginLocationLon;
    }

    public void setBeginLocationLon(BigDecimal beginLocationLon) {
        this.beginLocationLon = beginLocationLon;
    }

    public BigDecimal getBeginLocationLat() {
        return beginLocationLat;
    }

    public void setBeginLocationLat(BigDecimal beginLocationLat) {
        this.beginLocationLat = beginLocationLat;
    }

    public BigDecimal getEndLocationLon() {
        return endLocationLon;
    }

    public void setEndLocationLon(BigDecimal endLocationLon) {
        this.endLocationLon = endLocationLon;
    }

    public BigDecimal getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(BigDecimal endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckDesc() {
        return checkDesc;
    }

    public void setCheckDesc(String checkDesc) {
        this.checkDesc = checkDesc;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getRideTime() {
        return rideTime;
    }

    public void setRideTime(Integer rideTime) {
        this.rideTime = rideTime;
    }

    public Long getBorrowSellerId() {
        return borrowSellerId;
    }

    public void setBorrowSellerId(Long borrowSellerId) {
        this.borrowSellerId = borrowSellerId;
    }

    public Long getReturnSellerId() {
        return returnSellerId;
    }

    public void setReturnSellerId(Long returnSellerId) {
        this.returnSellerId = returnSellerId;
    }

    public Long getReturnCabinetId() {
        return returnCabinetId;
    }

    public void setReturnCabinetId(Long returnCabinetId) {
        this.returnCabinetId = returnCabinetId;
    }

    public String getReturnSysCode() {
        return returnSysCode;
    }

    public void setReturnSysCode(String returnSysCode) {
        this.returnSysCode = returnSysCode;
    }

    public String getBeginLocationDetails() {
        return beginLocationDetails;
    }

    public void setBeginLocationDetails(String beginLocationDetails) {
        this.beginLocationDetails = beginLocationDetails;
    }

    public String getEndLocaitonDetails() {
        return endLocaitonDetails;
    }

    public void setEndLocaitonDetails(String endLocaitonDetails) {
        this.endLocaitonDetails = endLocaitonDetails;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public Integer getBorrowChannel() {
        return borrowChannel;
    }

    public void setBorrowChannel(Integer borrowChannel) {
        this.borrowChannel = borrowChannel;
    }

    public Integer getReturnChannel() {
        return returnChannel;
    }

    public void setReturnChannel(Integer returnChannel) {
        this.returnChannel = returnChannel;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getPbId() {
        return pbId;
    }

    public void setPbId(Long pbId) {
        this.pbId = pbId;
    }

    public String getPbCode() {
        return pbCode;
    }

    public void setPbCode(String pbCode) {
        this.pbCode = pbCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getBorrowCabinetStatusCode() {
        return borrowCabinetStatusCode;
    }

    public void setBorrowCabinetStatusCode(String borrowCabinetStatusCode) {
        this.borrowCabinetStatusCode = borrowCabinetStatusCode;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }
}