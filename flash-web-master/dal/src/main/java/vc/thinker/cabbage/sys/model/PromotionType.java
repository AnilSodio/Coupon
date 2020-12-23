package vc.thinker.cabbage.sys.model;

import com.sinco.mybatis.dal.model.BaseModel;
import java.util.Date;

public class PromotionType extends BaseModel {
    /**  **/
    private Long id;

    /** 活动名称 **/
    private String typeName;

    /** 活动code **/
    private String typeCode;

    /**  **/
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}