package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class CabinetChargeRule extends BaseModel {
    /**  **/
    private Long id;

    /** 费用上限 **/
    private BigDecimal costTop;

    /** 免费时长 **/
    private Integer freeUseTime;

    /** 费用分钟 **/
    private Integer unitMinute;

    /** 费用，单位元 **/
    private BigDecimal unitPrice;

    /** 币种 **/
    private String currency;

    /** 描述 **/
    private String ruleDesc;

    /** 资产类型id **/
    private Long cabinetTypeId;

    /** 创建人 **/
    private Long createBy;

    /** 创建时间 **/
    private Date createTime;

    /** 修改人 **/
    private Long updateBy;

    /** 修改时间 **/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCostTop() {
        return costTop;
    }

    public void setCostTop(BigDecimal costTop) {
        this.costTop = costTop;
    }

    public Integer getFreeUseTime() {
        return freeUseTime;
    }

    public void setFreeUseTime(Integer freeUseTime) {
        this.freeUseTime = freeUseTime;
    }

    public Integer getUnitMinute() {
        return unitMinute;
    }

    public void setUnitMinute(Integer unitMinute) {
        this.unitMinute = unitMinute;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public Long getCabinetTypeId() {
        return cabinetTypeId;
    }

    public void setCabinetTypeId(Long cabinetTypeId) {
        this.cabinetTypeId = cabinetTypeId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}