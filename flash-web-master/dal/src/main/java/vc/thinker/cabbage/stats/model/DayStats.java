package vc.thinker.cabbage.stats.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.math.BigDecimal;
import java.util.Date;

public class DayStats extends BaseModel {
    /** 机构ID  1：全部 **/
    private Long officeId;

    /** 统计时间 **/
    private Date statsDate;

    /** 充电柜总数 **/
    private Integer cabinetNum;

    /** 押金总量 **/
    private BigDecimal deposit;

    /** 充电宝总数 **/
    private Integer pbNum;

    /** 1:天 2:月 **/
    private Integer type;

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public Date getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(Date statsDate) {
        this.statsDate = statsDate;
    }

    public Integer getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(Integer cabinetNum) {
        this.cabinetNum = cabinetNum;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public Integer getPbNum() {
        return pbNum;
    }

    public void setPbNum(Integer pbNum) {
        this.pbNum = pbNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}