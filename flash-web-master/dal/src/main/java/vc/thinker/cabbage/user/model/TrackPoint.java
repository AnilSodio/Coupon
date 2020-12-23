package vc.thinker.cabbage.user.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class TrackPoint extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /** 经度 **/
    private BigDecimal longitude;

    /** 纬度 **/
    private BigDecimal latitude;

    /** 创建时间 **/
    private Date createTime;

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

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}