package vc.thinker.cabbage.stats.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class OrderStats extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /** 统计日期 **/
    private Date statsDate;

    /** 骑行距离/米 **/
    private BigDecimal distance;

    /** 骑行时长 **/
    private Integer duration;

    /** 实际消费/元 **/
    private BigDecimal actualConsume;

    /** 优惠金额 **/
    private BigDecimal discountAmount;

    /** 行程金额 **/
    private BigDecimal tripAmount;

    /** 区域 **/
    private String area;

    /** 年龄 **/
    private Integer age;

    /** 性别 1:男 2:女 **/
    private Integer sex;

    /** 客户端 ios/android/wx-applet **/
    private String clientType;

    /** 优惠类型:1:无优惠 2:会员卡优惠 3:优惠券优惠 **/
    private Integer discountType;

    /** 行程ID **/
    private Long orderId;

    /** 行程状态：行程结束或者支付结束。 **/
    private Integer orderStatus;

    /** COMMENT '支付类型：免费：free  现金： cash  会员卡：vip  余额：balance ',
COMMENT '支付类型：免费：free  现金： cash  会员卡：vip  余额：balance ',
COMMENT '支付类型：免费：free  现金： cash  会员卡：vip  余额：balance ',
支付类型：免费：free  现金：cash  会员卡：vip  余额：balance **/
    private String payType;

    /** 系统编号 **/
    private String sysCode;

    /** 币种 **/
    private String currency;

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

    public Date getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(Date statsDate) {
        this.statsDate = statsDate;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getActualConsume() {
        return actualConsume;
    }

    public void setActualConsume(BigDecimal actualConsume) {
        this.actualConsume = actualConsume;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getTripAmount() {
        return tripAmount;
    }

    public void setTripAmount(BigDecimal tripAmount) {
        this.tripAmount = tripAmount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}