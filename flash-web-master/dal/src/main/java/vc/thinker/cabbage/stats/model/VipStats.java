package vc.thinker.cabbage.stats.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class VipStats extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private Long uid;

    /**  **/
    private Date statsDate;

    /** 购买天数 **/
    private Integer vipDays;

    /** 支付金额 **/
    private BigDecimal pay;

    /** 区域 **/
    private String area;

    /** 年龄 **/
    private Integer age;

    /** 客户端 **/
    private String clientType;

    /** 性别 **/
    private Integer sex;

    /** 会员卡类型 **/
    private String vipType;

    /** 会员卡单位  day 天， hour 小时 **/
    private String vipCardUnit;

    /** 币种 **/
    private String currency;

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

    public Date getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(Date statsDate) {
        this.statsDate = statsDate;
    }

    public Integer getVipDays() {
        return vipDays;
    }

    public void setVipDays(Integer vipDays) {
        this.vipDays = vipDays;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
    }

    public String getVipCardUnit() {
        return vipCardUnit;
    }

    public void setVipCardUnit(String vipCardUnit) {
        this.vipCardUnit = vipCardUnit;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}