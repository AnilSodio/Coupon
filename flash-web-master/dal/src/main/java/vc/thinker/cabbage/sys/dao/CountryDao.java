package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.dic.client.DicContent;
import com.sinco.dic.client.DicLoadData;
import com.sinco.dic.client.model.DicBase;

import vc.thinker.cabbage.sys.bo.CountryBO;
import vc.thinker.cabbage.sys.mapper.CountryMapper;
import vc.thinker.cabbage.sys.model.Country;
import vc.thinker.cabbage.sys.model.CountryExample;
import vc.thinker.cabbage.sys.vo.CountryVO;
import vc.thinker.cabbage.x.XDao;

@Repository
public class CountryDao extends XDao<CountryBO,CountryVO,CountryMapper,Country>{

    @Autowired
    private CountryMapper mapper;
    
    @Autowired
    private DicContent dicContent;
    
    @PostConstruct
    public void init() {
    	dicContent.setDicCache(CountryBO.class, new DicLoadData() {
			@Override
			public List<DicBase> loadDate() {
				List<CountryBO> list = new ArrayList<CountryBO>();
				list.add(findDefault());
				return Lists.newArrayList(list.toArray(new DicBase[list.size()])) ;
			}
		});
	}

    /**
     * generate code begin
     **/
    public List<CountryBO> findAll() {
        CountryExample example = new CountryExample();
        example.setOrderByClause("is_default desc");
        return mapper.selectByExample(example);
    }

    List<CountryBO> findAll(Iterable<java.lang.Long> ids) {
        CountryExample example = new CountryExample();
        example.createCriteria().andIdIn(Lists.newArrayList(ids));
        return mapper.selectByExample(example);
    }

    public long count() {
        CountryExample example = new CountryExample();
        return mapper.countByExample(example);
    }

    public List<Country> save(Iterable<Country> entities) {
        List<Country> list = new ArrayList<Country>();
        for (Country Country : entities) {
            list.add(save(Country));
        }
        return list;
    }

    public Country save(Country record) {
        if (record.getId() == null) {
            record.setCreateTime(new Date());
            mapper.insertSelective(record);
        } else {
            mapper.updateByPrimaryKeySelective(record);
        }
        return record;
    }


    public void update(Country record) {
        mapper.updateByPrimaryKeySelective(record);
    }

    public CountryBO findOne(java.lang.Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    public boolean exists(java.lang.Long id) {
        if (id == null) {
            return false;
        }
        CountryExample example = new CountryExample();
        example.createCriteria().andIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    public void delete(java.lang.Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void remove(java.lang.Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void delete(Country entity) {
        mapper.deleteByPrimaryKey(entity.getId());
    }

    public void delete(Iterable<Country> entities) {
        List<java.lang.Long> ids = Lists.newArrayList();
        for (Country entity : entities) {
            ids.add(entity.getId());
        }
        deleteByIds(ids);
    }

    public void deleteByIds(Iterable<java.lang.Long> ids) {
        CountryExample example = new CountryExample();
        example.createCriteria().andIdIn(Lists.newArrayList(ids));
        mapper.deleteByExample(example);
    }

    public void deleteAll() {
        CountryExample example = new CountryExample();
        mapper.deleteByExample(example);
    }

    /**
     * generate code end
     **/
    public CountryBO findDefault() {
        CountryExample example = new CountryExample();
        example.createCriteria().andIsDefaultEqualTo(true);
        List<CountryBO> list = mapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

	public List<CountryBO> groupByLanguage() {
		return mapper.groupByLanguage();
	}

	public void updateFalseDefault() {
		mapper.updateFalseDefault();
	}

	public List<CountryBO> findByLanguage(String language) {
		 CountryExample example = new CountryExample();
		 example.createCriteria().andDefaultLanguageEqualTo(language);
		 return mapper.selectByExample(example);
	}

	/**
	 * 根据币种查询国家信息
	 * @param currency
	 * @return
	 */
	public CountryBO findByCurrency(String currency) {
		CountryExample example = new CountryExample();
		example.createCriteria().andCurrencyEqualTo(currency);
		List<CountryBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
}
