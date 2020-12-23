package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;
import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.sys.mapper.ResourceMapper;
import vc.thinker.cabbage.sys.model.Resource;
import vc.thinker.cabbage.sys.bo.ResourceBO;
import vc.thinker.cabbage.sys.model.ResourceExample;
import vc.thinker.cabbage.sys.model.ResourceExample.Criteria;
import vc.thinker.cabbage.sys.vo.ResourceVO;
import vc.thinker.cabbage.x.XDao;

@Repository
public class ResourceDao extends XDao<ResourceBO,ResourceVO,ResourceMapper,Resource> {

	@Autowired
	private ResourceMapper mapper;


   /** generate code begin**/
	public List<ResourceBO> findAll(){
		ResourceExample example=new ResourceExample();
		return mapper.selectByExample(example);
	}
	List<ResourceBO> findAll(Iterable<java.lang.Long> ids){
		ResourceExample example=new ResourceExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		ResourceExample example=new ResourceExample();
		return mapper.countByExample(example);
	}

	public List<Resource> save(Iterable<Resource> entities){
		List<Resource> list=new ArrayList<Resource>();
		for (Resource Resource : entities) {
			list.add(save(Resource));
		}
		return list;
	}
	
	public Resource save(Resource record){
		record.setUpdateTime(new Date());
		if(record.getId() == null){
			record.setCreateTime(new Date());
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Resource record) {
		record.setUpdateTime(new Date());
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public ResourceBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		ResourceExample example=new ResourceExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Resource entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Resource> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Resource  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		ResourceExample example=new ResourceExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		ResourceExample example=new ResourceExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<ResourceBO> findByLanguageAnaType(String language,String platType) {
		ResourceExample example=new ResourceExample();
		example.createCriteria().andLanguageEqualTo(language).andPlatTypeEqualTo(platType);
		return mapper.selectByExample(example);
	}
	
	public List<ResourceBO> findbyPlatType(String platType) {
		ResourceExample example=new ResourceExample();
		example.createCriteria().andPlatTypeEqualTo(platType);
		return mapper.selectByExample(example);
	}
	
	/**
	 * 根据 platType 分组查询
	 * @param platType
	 * @return
	 */
	public List<ResourceBO> groupByModule(String language,String platType) {
		return mapper.groupByModule(platType,language);
	}
	
	public List<ResourceBO> groupBySecondMenu(String language,String platType,String moudle) {
		return mapper.groupBySecondMenu(platType,language,moudle);
	}
	
	public List<ResourceBO> findResource(
			String platType, String language,String moudle,String secondMenu) {
		ResourceExample example=new ResourceExample();
		Criteria c = example.createCriteria();
		c.andPlatTypeEqualTo(platType);
		c.andLanguageEqualTo(language);
		
		if(!StringUtils.isEmpty(moudle)){
			c.andFunctionModuleEqualTo(moudle);
		}
		if(!StringUtils.isEmpty(secondMenu)){
			c.andSecondMenuEqualTo(secondMenu);
		}
		
		return mapper.selectByExample(example);
	}
	
	public Date queryUpdateTimeByLanguage(String playType,String language) {
		ResourceExample example=new ResourceExample();
		example.createCriteria()
		.andPlatTypeEqualTo(playType)
		.andLanguageEqualTo(language);
		example.setOrderByClause("update_time desc");
		List<ResourceBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0).getUpdateTime();
	}
	
}
