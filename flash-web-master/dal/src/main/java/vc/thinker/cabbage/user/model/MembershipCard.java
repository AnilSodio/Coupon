package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class MembershipCard extends BaseModel {
    /**  **/
    private Long id;

    /** 会员卡的名称 **/
    private String cardName;

    /** 购买金额单位元 **/
    private BigDecimal cardAmount;

    /** 会员卡有的期限（单位天） **/
    private Integer cardEffectiveTime;

    /** 会员卡单位  day 天， hour 小时 **/
    private String cardUnit;

    /** 折扣，默认0 不打折 **/
    private BigDecimal discount;

    /** 会员卡说明 **/
    private String cardDesc;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private Long createBy;

    /** 修改时间 **/
    private Date updateTime;

    /** 修改人 **/
    private Long updateBy;

    /** 排序 **/
    private Integer sort;

    /**  **/
    private String currency;

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

    public String getCardUnit() {
        return cardUnit;
    }

    public void setCardUnit(String cardUnit) {
        this.cardUnit = cardUnit;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getCardDesc() {
        return cardDesc;
    }

    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}