package vc.thinker.cabbage.sys.service;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.sinco.dic.client.DicContent;

import vc.thinker.cabbage.sys.ResourceContants;
import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.dao.CountryDao;
import vc.thinker.cabbage.sys.dao.ResourceDao;
import vc.thinker.cabbage.sys.exception.CountryNotExitException;
import vc.thinker.cabbage.sys.model.Country;
import vc.thinker.cabbage.sys.vo.CountryVO;

@Service
@Transactional
public class CountryService extends XService<CountryBO, CountryVO, CountryDao> {

	private static Logger logger = LoggerFactory.getLogger(CountryService.class);
	
    @Autowired
    private CountryDao countryDao;

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private DicContent dicContent;
    
    /**
     * 
     * @return
     */
    public Set<String> findCurrencies() {
    	List<CountryBO> countries = countryDao.findAll();
		Set<String> currencies = new HashSet<String>();
		for (CountryBO country : countries) {
			if (StringUtils.isNotBlank(country.getCurrency())) {
				currencies.add(country.getCurrency());
			}
		}
		
		return currencies;
    }
    
    /**
     * 
     * @return
     */
    public Map<String, CountryBO> findCountriesMap() {
    	Map<String, CountryBO> map = new HashMap<String, CountryBO>();
        List<CountryBO> countryList = countryDao.findAll();
        for (CountryBO country : countryList) {
        	map.put(country.getNationCode(), country);
		}
        
        return map;
    }
    
    public List<CountryBO> findAll() {
        return countryDao.findAll();
    }

    public CountryBO findDefault() {
        return countryDao.findDefault();
    }
    
    public List<CountryBO> groupByLanguage(){
    	return countryDao.groupByLanguage();
    }

    /**
     * 根据语言获取对应的语言包
     * @param language
     * @return
     */
    public String queryLanguagePack(String language) {

//    	if(language.contains("-")){
//    		language = language.split("-")[0];
//    	}
    	
        //校验国家
        List<CountryBO> c_list = countryDao.findByLanguage(language);
        
        if (c_list.isEmpty()) {
            throw new CountryNotExitException();
        }

        return findByOneLevel(language);
        
    }


    /**
     * 资源数据处理
     * @param param
     * @return
     */
    public String handlerText(String param) {
        String[] array = param.split("\\.");
        return array[array.length - 1];
    }

  
    /**
     * 查询语言包的最后更新时间
     * @param playType
     * @param language
     * @return
     */
    public Date queryUpdateTimeByLanguage(String playType,String language) {
    	language = language.equals("zh") ? "zh-cn" : language;
		return resourceDao.queryUpdateTimeByLanguage(playType,language);
	}

    /**
     * 设置默认国家
     * @param id
     * @return
     */
    public void setDefault(Long id) {
    	
    	// 取消默认设置
    	countryDao.updateFalseDefault();
    	
    	//设置为默认
    	Country country = new Country();
    	country.setId(id);
    	country.setIsDefault(true);;
    	
    	countryDao.save(country);
    	
    	//刷新缓存
    	dicContent.refreshDicCache(CountryBO.class);
    	
    }

    /**
     * 从缓存中获取默认的语言
     * @return
     */
	public String findCashDat() {
		
		List<CountryBO> list = dicContent.getDics(CountryBO.class);
		
		CountryBO country = list.isEmpty()?null:list.get(0);

		if(null == country){
			country = countryDao.findDefault();
		}
		
		return country.getDefaultLanguage();
	}
	
	/**
	 * 语言包查询(在只有一级菜单的情况下）
	 * @param language
	 * @return
	 */
	public String findByOneLevel(String language){
		
		//用于组装返回数据
		JSONObject respObject = new JSONObject();
		
		//分组查询
		List<ResourceBO> g_moudle = 
        		resourceDao.groupByModule(language,
        				ResourceContants.RESOURCE_PLAY_TYEP_APP);
		
		for (ResourceBO e : g_moudle) {
			
			JSONObject object = new JSONObject();
			
			List<ResourceBO> list = resourceDao.findResource(
							ResourceContants.RESOURCE_PLAY_TYEP_APP,
							language,  
							e.getFunctionModule(), 
							null);
			
			for (ResourceBO r : list) {
				object.put(handlerText(r.getName()), r.getText());
			}
			
			respObject.put(e.getFunctionModule(), object);
		}
		
		return respObject.toString();
	}
	
	public String findByTwoLevel(String language){ 
		//用于组装返回数据
		JSONObject respObject = new JSONObject();
		
		//分组查询
		List<ResourceBO> g_moudle = 
        		resourceDao.groupByModule(language,
        				ResourceContants.RESOURCE_PLAY_TYEP_APP);
		
		for (ResourceBO g : g_moudle) {
			
			JSONObject firstObject = new JSONObject();
			
			List<ResourceBO> g_second = 
            		resourceDao.groupBySecondMenu( language, 
            				ResourceContants.RESOURCE_PLAY_TYEP_APP, 
            				g.getFunctionModule());
			
			for (ResourceBO s : g_second) {
				
				List<ResourceBO> list = 
						resourceDao.findResource(ResourceContants.RESOURCE_PLAY_TYEP_APP, 
						language,s.getFunctionModule(), s.getSecondMenu());
				
				if(s.getFunctionModule().equals(s.getSecondMenu())){
					list.forEach(e->{
						firstObject.put(handlerText(e.getName()), e.getText());
					});
				}else {
					
					JSONObject secondObject = new JSONObject();
					list.forEach(e->{
						secondObject.put(handlerText(e.getName()), e.getText());
					});
					
					firstObject.put(s.getSecondMenu(), secondObject);
				}
			}
			
			respObject.put(g.getFunctionModule(), firstObject);
		}
		return respObject.toString();
	}
	
}
