package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.FeedbackMessageMapper;
import vc.thinker.cabbage.se.model.FeedbackMessage;
import vc.thinker.cabbage.se.bo.FeedbackMessageBO;
import vc.thinker.cabbage.se.model.FeedbackMessageExample;
import vc.thinker.cabbage.se.vo.FeedbackMessageVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class FeedbackMessageDao {

	@Autowired
	private FeedbackMessageMapper mapper;


   /** generate code begin**/
	public List<FeedbackMessageBO> findAll(){
		FeedbackMessageExample example=new FeedbackMessageExample();
		return mapper.selectByExample(example);
	}
	List<FeedbackMessageBO> findAll(Iterable<java.lang.Long> ids){
		FeedbackMessageExample example=new FeedbackMessageExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		FeedbackMessageExample example=new FeedbackMessageExample();
		return mapper.countByExample(example);
	}

	public List<FeedbackMessage> save(Iterable<FeedbackMessage> entities){
		List<FeedbackMessage> list=new ArrayList<FeedbackMessage>();
		for (FeedbackMessage FeedbackMessage : entities) {
			list.add(save(FeedbackMessage));
		}
		return list;
	}
	
	public FeedbackMessage save(FeedbackMessage record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(FeedbackMessage record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public FeedbackMessageBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		FeedbackMessageExample example=new FeedbackMessageExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(FeedbackMessage entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<FeedbackMessage> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (FeedbackMessage  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		FeedbackMessageExample example=new FeedbackMessageExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		FeedbackMessageExample example=new FeedbackMessageExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<FeedbackMessageBO> findPageByVo(MyPage<FeedbackMessageBO> page, FeedbackMessageVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public void updateIsReaded(MyPage<FeedbackMessageBO> page) {
		mapper.updateIsReaded(page);
	}
	public int countByUnRead() {
		FeedbackMessageExample example=new FeedbackMessageExample();
		example.createCriteria().andIsReadEqualTo(false).andIsDeleteEqualTo(false);
		return mapper.countByExample(example);
	}
}
