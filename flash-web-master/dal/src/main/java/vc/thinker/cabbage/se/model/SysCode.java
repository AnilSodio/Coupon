package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class SysCode extends BaseModel {
    /**  **/
    private Long id;

    /** 系统编号 **/
    private String sysCode;

    /** 打印状态0:未打印，1:已打印 **/
    private Boolean isPrint;

    /** 绑定状态 0:未绑定，1:已绑定 **/
    private Boolean isBinding;

    /** 创建时间 **/
    private Date createTime;

    /** 打印时间 **/
    private Date printTime;

    /** 绑定时间 **/
    private Date bindTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Boolean getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Boolean isPrint) {
        this.isPrint = isPrint;
    }

    public Boolean getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Boolean isBinding) {
        this.isBinding = isBinding;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPrintTime() {
        return printTime;
    }

    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }
}