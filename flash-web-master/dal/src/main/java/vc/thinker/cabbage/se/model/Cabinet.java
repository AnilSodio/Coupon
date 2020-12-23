package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class Cabinet extends BaseModel {
    /**  **/
    private Long id;

    /** 充电柜编号 **/
    private String cabinetCode;

    /** 系统编号，由平台产生 **/
    private String sysCode;

    /** 类型id **/
    private Long typeId;
    
    /** 消费规则id **/
    private Long ruleId;

    /** 商户id **/
    private Long sellerId;

    /** 代理商ID **/
    private Long agentId;

    /** 1,正常,2禁用 **/
    private Integer status;

    /** 投放状态，1,已投放，0未投放 **/
    private Boolean isDelivery;

    /** 投放时间 **/
    private Date deliveryTime;

    /** 投放人 **/
    private Long deliveryId;

    /**  **/
    private BigDecimal locationLon;

    /**  **/
    private BigDecimal locationLat;

    /** 地址 **/
    private String locationAddress;

    /** 详细地址描述 **/
    private String locationDesc;

    /** 创建人 **/
    private Long createBy;

    /** 修改时间 **/
    private Date createTime;

    /** 机柜别名 **/
    private String cabinetAlias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(Boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public BigDecimal getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(BigDecimal locationLon) {
        this.locationLon = locationLon;
    }

    public BigDecimal getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(BigDecimal locationLat) {
        this.locationLat = locationLat;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationDesc() {
        return locationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        this.locationDesc = locationDesc;
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

    public String getCabinetAlias() {
        return cabinetAlias;
    }

    public void setCabinetAlias(String cabinetAlias) {
        this.cabinetAlias = cabinetAlias;
    }

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
}