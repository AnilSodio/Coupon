package vc.thinker.apiv2.bo;

import java.math.BigDecimal;
import java.util.Date;

public class UserDepositLogBO {

    /** 用户ID **/
    private Long uid;

    /** 金额 **/
    private BigDecimal amount;

    /** 类型:1付款成功 2 退款中 3.退款成功 **/
    private String type;

    /**  **/
    private Date createTime;

    /**  **/
    private Date updateTime;



    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

}