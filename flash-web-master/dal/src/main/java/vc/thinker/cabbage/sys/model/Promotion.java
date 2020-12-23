package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class Promotion extends BaseModel {
    /**  **/
    private Long id;

    /** 活动名称 **/
    private String proName;

    /** 开始时间 **/
    private Date beginDate;

    /** 结束时间 **/
    private Date endDate;

    /** 地区ID **/
    private String areaId;

    /** 活动类型ID **/
    private Long proTypeId;

    /** 优惠券ID **/
    private Long couponId;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private Long createBy;

    /**  **/
    private Boolean isDeleted;

    /** 活动code **/
    private String proTypeCode;

    /** 是否全国 **/
    private Boolean allCountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public Long getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(Long proTypeId) {
        this.proTypeId = proTypeId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getProTypeCode() {
        return proTypeCode;
    }

    public void setProTypeCode(String proTypeCode) {
        this.proTypeCode = proTypeCode;
    }

    public Boolean getAllCountry() {
        return allCountry;
    }

    public void setAllCountry(Boolean allCountry) {
        this.allCountry = allCountry;
    }
}