package vc.thinker.cabbage.sys.model;

import java.util.Date;

import com.sinco.mybatis.dal.model.BaseModel;

public class ImageText extends BaseModel {
    /**  **/
    private Long id;

    /** 标题 **/
    private String title;

    /** 封面图片 **/
    private String cover;

    /** 摘要 **/
    private String remark;

    /** 创建时间 **/
    private Date createTime;

    /** 修改时间 **/
    private Date updateTime;

    /** 图文类型 1：普通图文，2：首页广告 **/
    private Integer imageType;

    /** 广告图片的地址 **/
    private String adCover;

    /** 状态：1 未生效，2 生效，3失效 **/
    private Integer status;

    /** 失效时间 **/
    private Date invalidDate;

    /** 生效时间 **/
    private Date startDate;

    /** 内容 **/
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getAdCover() {
        return adCover;
    }

    public void setAdCover(String adCover) {
        this.adCover = adCover;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}