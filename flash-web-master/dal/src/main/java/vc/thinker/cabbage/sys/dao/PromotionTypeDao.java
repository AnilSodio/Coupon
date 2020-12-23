package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.PromotionTypeBO;
import vc.thinker.cabbage.sys.mapper.PromotionTypeMapper;
import vc.thinker.cabbage.sys.model.PromotionType;
import vc.thinker.cabbage.sys.model.PromotionTypeExample;

@Repository
public class PromotionTypeDao {

	@Autowired
	private PromotionTypeMapper mapper;


   /** generate code begin**/
	public List<PromotionTypeBO> findAll(){
		PromotionTypeExample example=new PromotionTypeExample();
		return mapper.selectByExample(example);
	}
	List<PromotionTypeBO> findAll(Iterable<java.lang.Long> ids){
		PromotionTypeExample example=new PromotionTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PromotionTypeExample example=new PromotionTypeExample();
		return mapper.countByExample(example);
	}

	public List<PromotionType> save(Iterable<PromotionType> entities){
		List<PromotionType> list=new ArrayList<PromotionType>();
		for (PromotionType PromotionType : entities) {
			list.add(save(PromotionType));
		}
		return list;
	}
	
	public PromotionType save(PromotionType record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PromotionType record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PromotionTypeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PromotionTypeExample example=new PromotionTypeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PromotionType entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PromotionType> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PromotionType  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PromotionTypeExample example=new PromotionTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PromotionTypeExample example=new PromotionTypeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
