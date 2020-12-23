package vc.thinker.cabbage.stats.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class RegisterStats extends BaseModel {
    /**  **/
    private Long id;

    /** 加了一个unique的限制，每个用户只统计一次 **/
    private Long uid;

    /** 注册时间 **/
    private Date statsDate;

    /**  **/
    private String area;

    /** 客户端 **/
    private String clientType;

    /** 性别1 男  2 女 **/
    private Integer sex;

    /** 年龄 **/
    private Integer age;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}