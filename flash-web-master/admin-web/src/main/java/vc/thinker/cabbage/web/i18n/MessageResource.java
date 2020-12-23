package vc.thinker.cabbage.web.i18n;

/**
* messageSource自定义代码
*/

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import vc.thinker.cabbage.sys.ResourceContants;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.dao.CountryDao;
import vc.thinker.cabbage.sys.dao.ResourceDao;

public class MessageResource extends AbstractMessageSource implements ResourceLoaderAware
{
    @SuppressWarnings("unused")
    private ResourceLoader resourceLoader;
    
    @Autowired
    private ResourceDao resourceDao;
    
    @Autowired
    private CountryDao countryDao;
    
    /**
     * Map切分字符
     */
    protected final String MAP_SPLIT_CODE = "|";
    private final Map<String, String> properties = new HashMap<String, String>();

    
    @PostConstruct
    public void reload() {
        properties.clear();
        properties.putAll(loadTexts());
    }

    /**
     * 
     * 查询数据 虚拟数据，可以从数据库读取信息，此处省略
     * @return
     */
    private List<ResourceBO> getResource(){
    	
//    	Country country = countryDao.findDefault();
    	
        List<ResourceBO> resources = 
        		resourceDao.findbyPlatType(ResourceContants.RESOURCE_PLAT_TYEP_ADMIN);
        
//        		resourceDao.findByLanguageAnaType(country.getDefaultLanguage(),
//        				ResourceContants.RESOURCE_PLAT_TYEP_ADMIN);
        return resources;
    }

    /**
     * 
     * 加载数据
     * @return
     */
    protected Map<String, String> loadTexts() {
        Map<String, String> mapResource = new HashMap<String, String>();
        List<ResourceBO> resources = this.getResource();
        for (ResourceBO item : resources) {
            String code = item.getName() + MAP_SPLIT_CODE + item.getLanguage();
            mapResource.put(code, item.getText());
        }
        return mapResource;
    }

    /**
     * 
     * @param code   
     * @param locale 本地化语言
     * @return
     */
    private String getText(String code, Locale locale) {
        String localeCode = locale.getLanguage(); 
        String key = code + MAP_SPLIT_CODE + localeCode;
        String localeText = properties.get(key);
        String resourceText = code;

        if(localeText != null) {
            resourceText = localeText;
        }
        else {
            localeCode = Locale.ENGLISH.getLanguage();
            key = code + MAP_SPLIT_CODE + localeCode;
            localeText = properties.get(key);
            if(localeText != null) {
                resourceText = localeText;
            }
            else {
                try {
                    if(getParentMessageSource() != null) {
                        resourceText = getParentMessageSource().getMessage(code, null, locale);
                    }
                } catch (Exception e) {
                    logger.error("Cannot find message with code: " + code);
                }
            }
        }
        return resourceText;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader)
    {
        this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale)
    {
        String msg = getText(code, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = getText(code, locale);
        return result;
    }
  
    
}