package vc.thinker.cabbage.user.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class UniqueRadomCode extends BaseModel {
    /**  **/
    private Long code;

    /**  **/
    private String isUse;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }
}