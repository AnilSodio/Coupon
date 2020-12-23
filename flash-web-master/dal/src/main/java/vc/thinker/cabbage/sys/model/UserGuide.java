package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class UserGuide extends BaseModel {
    /**  **/
    private Long id;

    /**  **/
    private String title;

    /** 排序 **/
    private Integer sort;

    /** 类型:1.用户指南 2.个人端APP设置 3.维保端APP设置 4,用车服务条例, 5 积分规则 10 充值协议 **/
    private Integer type;

    /**  **/
    private Date createTime;

    /** 语言 zh-cn 中文，en 英语 **/
    private String language;

    /**  **/
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}