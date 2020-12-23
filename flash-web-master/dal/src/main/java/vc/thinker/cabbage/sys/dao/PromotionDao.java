package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.PromotionBO;
import vc.thinker.cabbage.sys.mapper.PromotionMapper;
import vc.thinker.cabbage.sys.model.Promotion;
import vc.thinker.cabbage.sys.model.PromotionExample;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class PromotionDao {

	@Autowired
	private PromotionMapper mapper;

	public List<PromotionBO> findPromotionPage(MyPage<PromotionBO> page) {
		return mapper.findPromotionPage(page);
	}
	/**
	 * 根据类型查找进行中的活动
	 * @param typeCode
	 * @return
	 */
	public List<PromotionBO> findHaveInHand(String typeCode,String areaId){
		PromotionExample example=new PromotionExample();
		Date date=new Date();
		PromotionExample.Criteria c= example.createCriteria().andProTypeCodeEqualTo(typeCode)
		.andBeginDateLessThanOrEqualTo(date).andEndDateGreaterThanOrEqualTo(date)
		.andIsDeletedEqualTo(false);
		if(StringUtils.isNotBlank(areaId)){
			c.andAreaIdLike("%"+areaId+";%");
		}
		return mapper.selectByExample(example);
	}

   /** generate code begin**/
	public List<PromotionBO> findAll(){
		PromotionExample example=new PromotionExample();
		return mapper.selectByExample(example);
	}
	List<PromotionBO> findAll(Iterable<java.lang.Long> ids){
		PromotionExample example=new PromotionExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PromotionExample example=new PromotionExample();
		return mapper.countByExample(example);
	}

	public List<Promotion> save(Iterable<Promotion> entities){
		List<Promotion> list=new ArrayList<Promotion>();
		for (Promotion Promotion : entities) {
			list.add(save(Promotion));
		}
		return list;
	}
	
	public Promotion save(Promotion record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Promotion record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PromotionBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PromotionExample example=new PromotionExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Promotion entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Promotion> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Promotion  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PromotionExample example=new PromotionExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PromotionExample example=new PromotionExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
