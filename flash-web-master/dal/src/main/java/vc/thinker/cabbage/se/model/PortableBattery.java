package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class PortableBattery extends BaseModel {
    /**  **/
    private Long id;

    /** 资产编号 **/
    private String portableBatteryCode;

    /** 状态，1:正常，2:禁用 **/
    private Integer status;

    /** 所在的位置类型 1 在使用，2在充电柜 **/
    private Integer locationType;

    /** 充电柜id **/
    private Long cabinetId;

    /** 槽位号 **/
    private Integer cabinetChannel;

    /** 电量 **/
    private Integer electricity;

    /** 电压 **/
    private Integer voltage;

    /** 修改时间 **/
    private Date updateTime;

    /**  **/
    private String cable;

    /** 否带充电头 **/
    private Boolean adapter;

    /** 0-不需要充电 1-在充电 2-在充电缓冲区，不动作，即保持原动作 3-读不到电量，也不用充电了 **/
    private String isdamage;

    /**  **/
    private String colorId;

    /** 温度 **/
    private String temperature;

    /** 电池类型 **/
    private String battType;

    /**  **/
    private Long memberId;

    /** 最后位置时间 **/
    private Date lastLocationTime;

    /** 创建时间 **/
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortableBatteryCode() {
        return portableBatteryCode;
    }

    public void setPortableBatteryCode(String portableBatteryCode) {
        this.portableBatteryCode = portableBatteryCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLocationType() {
        return locationType;
    }

    public void setLocationType(Integer locationType) {
        this.locationType = locationType;
    }

    public Long getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Long cabinetId) {
        this.cabinetId = cabinetId;
    }

    public Integer getCabinetChannel() {
        return cabinetChannel;
    }

    public void setCabinetChannel(Integer cabinetChannel) {
        this.cabinetChannel = cabinetChannel;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public void setVoltage(Integer voltage) {
        this.voltage = voltage;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCable() {
        return cable;
    }

    public void setCable(String cable) {
        this.cable = cable;
    }

    public Boolean getAdapter() {
        return adapter;
    }

    public void setAdapter(Boolean adapter) {
        this.adapter = adapter;
    }

    public String getIsdamage() {
        return isdamage;
    }

    public void setIsdamage(String isdamage) {
        this.isdamage = isdamage;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getBattType() {
        return battType;
    }

    public void setBattType(String battType) {
        this.battType = battType;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Date getLastLocationTime() {
        return lastLocationTime;
    }

    public void setLastLocationTime(Date lastLocationTime) {
        this.lastLocationTime = lastLocationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}