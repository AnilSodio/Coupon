package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class Feedback extends BaseModel {
    /**  **/
    private Long id;

    /** 用户id **/
    private Long uid;

    /** 订单id **/
    private Long orderId;

    /** 订单编号 **/
    private String orderCode;

    /** 类型 1：首页，2：行程中，3：已完成 **/
    private String feedType;

    /** 问题表述 **/
    private String feedDesc;

    /** 用户上送的照片 **/
    private String imgUrl1;

    /** 用户上送的照片 **/
    private String imgUrl2;

    /** 用户上送的照片 **/
    private String imgUrl3;

    /** 用户上送的照片 **/
    private String imgUrl4;

    /** 0:已处理，1：未处理，2：无需处理 **/
    private Integer status;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 修改人 **/
    private String updateBy;

    /** 备注 **/
    private String remark;

    /** 处理方式 **/
    private String dealType;

    /** 票券id **/
    private Long ticketId;

    /** 问题类型id **/
    private Long typeId;

    /** 充电柜编码 **/
    private String cabinetCode;

    /** 充电宝编码 **/
    private String pbCode;

    /** 充电宝id **/
    private Long pbId;

    /** 系统编号 **/
    private String sysCode;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public String getFeedDesc() {
        return feedDesc;
    }

    public void setFeedDesc(String feedDesc) {
        this.feedDesc = feedDesc;
    }

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getImgUrl3() {
        return imgUrl3;
    }

    public void setImgUrl3(String imgUrl3) {
        this.imgUrl3 = imgUrl3;
    }

    public String getImgUrl4() {
        return imgUrl4;
    }

    public void setImgUrl4(String imgUrl4) {
        this.imgUrl4 = imgUrl4;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }

    public String getPbCode() {
        return pbCode;
    }

    public void setPbCode(String pbCode) {
        this.pbCode = pbCode;
    }

    public Long getPbId() {
        return pbId;
    }

    public void setPbId(Long pbId) {
        this.pbId = pbId;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getClientSource() {
        return clientSource;
    }

    public void setClientSource(String clientSource) {
        this.clientSource = clientSource;
    }
}