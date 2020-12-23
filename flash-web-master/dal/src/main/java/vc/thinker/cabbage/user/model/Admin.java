package vc.thinker.cabbage.user.model;

import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class Admin extends BaseModel {
    /** 编号 **/
    private Long uid;

    /** 工号 **/
    private String no;

    /** 姓名 **/
    private String name;

    /** 邮箱 **/
    private String email;

    /** 电话 **/
    private String phone;

    /** 手机 **/
    private String mobile;

    /** 用户类型 **/
    private String userType;

    /** 创建者 **/
    private String createBy;

    /** 创建时间 **/
    private Date createTime;

    /** 更新者 **/
    private String updateBy;

    /** 更新时间 **/
    private Date updateTime;

    /** 备注信息 **/
    private String remarks;

    /** 删除标记 **/
    private Boolean isDel;

    /**  **/
    private String idCard;

    /**  **/
    private String sellCode;
    
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSellCode() {
        return sellCode;
    }

    public void setSellCode(String sellCode) {
        this.sellCode = sellCode;
    }
}