package vc.thinker.cabbage.se.model;

import com.sinco.mybatis.dal.model.BaseModel;

public class FirmwareModule extends BaseModel {
    /** id **/
    private Long id;

    /** 模块名称 **/
    private String name;

    /**  **/
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}