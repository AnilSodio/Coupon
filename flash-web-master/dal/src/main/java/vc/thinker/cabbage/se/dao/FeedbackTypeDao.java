package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.FeedbackTypeMapper;
import vc.thinker.cabbage.se.model.FeedbackType;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.model.FeedbackTypeExample;
import vc.thinker.cabbage.se.vo.FeedbackTypeVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class FeedbackTypeDao {

	@Autowired
	private FeedbackTypeMapper mapper;
	
	public List<FeedbackTypeBO> findByType(String type,String language){
		FeedbackTypeExample example=new FeedbackTypeExample();
		example.createCriteria().andTypeEqualTo(type).andLanguageEqualTo(language);
		example.setOrderByClause("order_number");
		return mapper.selectByExample(example);
	}


   /** generate code begin**/
	public List<FeedbackTypeBO> findAll(){
		FeedbackTypeExample example=new FeedbackTypeExample();
		return mapper.selectByExample(example);
	}
	List<FeedbackTypeBO> findAll(Iterable<java.lang.Long> ids){
		FeedbackTypeExample example=new FeedbackTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		FeedbackTypeExample example=new FeedbackTypeExample();
		return mapper.countByExample(example);
	}

	public List<FeedbackType> save(Iterable<FeedbackType> entities){
		List<FeedbackType> list=new ArrayList<FeedbackType>();
		for (FeedbackType FeedbackType : entities) {
			list.add(save(FeedbackType));
		}
		return list;
	}
	
	public FeedbackType save(FeedbackType record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(FeedbackType record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public FeedbackTypeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		FeedbackTypeExample example=new FeedbackTypeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(FeedbackType entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<FeedbackType> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (FeedbackType  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		FeedbackTypeExample example=new FeedbackTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		FeedbackTypeExample example=new FeedbackTypeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<FeedbackTypeBO> findPageByVo(MyPage<FeedbackTypeBO> page, FeedbackTypeVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
