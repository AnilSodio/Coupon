package vc.thinker.cabbage.sys.vo;

public class ResourceVO {

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
}