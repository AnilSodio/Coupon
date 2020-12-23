package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class Resource extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private String name;

    /**  **/
    private String text;

    /**  **/
    private String language;

    /** 平台类型  后台 admin/移动端app商户端 seller **/
    private String platType;

    /** 功能模块 **/
    private String functionModule;

    /** 二级菜单 **/
    private String secondMenu;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPlatType() {
        return platType;
    }

    public void setPlatType(String platType) {
        this.platType = platType;
    }

    public String getFunctionModule() {
        return functionModule;
    }

    public void setFunctionModule(String functionModule) {
        this.functionModule = functionModule;
    }

    public String getSecondMenu() {
        return secondMenu;
    }

    public void setSecondMenu(String secondMenu) {
        this.secondMenu = secondMenu;
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