package vc.thinker.cabbage.web.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleResolver implements LocaleResolver {

	private static Logger logger = LoggerFactory.getLogger(MyLocaleResolver.class);
	
    
    @Override
    public Locale resolveLocale(HttpServletRequest request) {

//    	String language = countryService.findCashDat();
//    	
//    	if(StringUtils.isEmpty(language)){
//    		return request.getLocale();
//    	}
//    	
//    	if(language.startsWith("zh")){
//    		return new Locale("zh", "CN");
//    	}
        return new Locale("en");
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    	//无需实现此方法
    }

}
