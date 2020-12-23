package vc.thinker.cabbage.sys.model;

import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class InitImg extends BaseModel {
    /**  **/
    private Long id;

    /** 名称 **/
    private String name;

    /** 图片地址 **/
    private String initImg;

    /** 图片链接地址 **/
    private String linkUrl;

    /**  **/
    private Boolean isDelete;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 1 启动页广告  ,  2  首页广告 **/
    private Integer imgType;

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

    public String getInitImg() {
        return initImg;
    }

    public void setInitImg(String initImg) {
        this.initImg = initImg;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
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

    public Integer getImgType() {
        return imgType;
    }

    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
}