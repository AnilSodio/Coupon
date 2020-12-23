package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class PortableBatteryType extends BaseModel {
    /**  **/
    private Long id;

    /** 名称 **/
    private String typeName;

    /** 状态，1:正常，2:禁用 **/
    private Integer status;

    /** 最大电量 **/
    private Integer maxElectricity;

    /** 最大电压 **/
    private Integer maxVoltage;

    /** 是否带充电线 **/
    private Boolean isHasLine;

    /** 充电线类型  normal:IOS/Android通用，Typc-C:安卓Typc-C口 **/
    private String lineType;

    /** 描述 **/
    private String typeDesc;

    /** 创建人 **/
    private Long createBy;

    /** 修改时间 **/
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMaxElectricity() {
        return maxElectricity;
    }

    public void setMaxElectricity(Integer maxElectricity) {
        this.maxElectricity = maxElectricity;
    }

    public Integer getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(Integer maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public Boolean getIsHasLine() {
        return isHasLine;
    }

    public void setIsHasLine(Boolean isHasLine) {
        this.isHasLine = isHasLine;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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
}