package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class Seller extends BaseModel {
    /**  **/
    private Long uid;

    /** 服务商名称 **/
    private String sellerName;

    /** 商户logo **/
    private String sellerLogo;

    /** 商户封面 **/
    private String sellerCover;

    /** 邮箱 **/
    private String email;

    /** 邀请码 **/
    private String inviteCode;

    /** 国家 **/
    private String country;

    /** 联系人 **/
    private String contactUserName;

    /** 手机号 **/
    private String contactMobile;

    /** 座机 **/
    private String contactTelephone;

    /** 经度 **/
    private BigDecimal locationLon;

    /** 纬度 **/
    private BigDecimal locationLat;

    /** 地址 **/
    private String locationAddress;

    /** 详细地址 **/
    private String locationDesc;

    /** 反润比例 **/
    private BigDecimal rebateRate;

    /** 状态 **/
    private Integer status;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 服务时间 **/
    private String serviceTime;

    /** 推荐人ID **/
    private Long refereeUid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public String getSellerCover() {
        return sellerCover;
    }

    public void setSellerCover(String sellerCover) {
        this.sellerCover = sellerCover;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContactUserName() {
        return contactUserName;
    }

    public void setContactUserName(String contactUserName) {
        this.contactUserName = contactUserName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
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

    public BigDecimal getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(BigDecimal rebateRate) {
        this.rebateRate = rebateRate;
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

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public Long getRefereeUid() {
        return refereeUid;
    }

    public void setRefereeUid(Long refereeUid) {
        this.refereeUid = refereeUid;
    }
}