package vc.thinker.cabbage.sys.vo;

public class CountryVO {
    /**
     * 中文名称
     **/
    private String chineseName;

    /**
     * 英文名称
     **/
    private String englishName;

    /**
     * 默认语言
     **/
    private String defaultLanguage;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }
}