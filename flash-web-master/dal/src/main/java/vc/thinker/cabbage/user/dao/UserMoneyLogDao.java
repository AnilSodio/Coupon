package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.UserMoneyLogBO;
import vc.thinker.cabbage.user.mapper.UserMoneyLogMapper;
import vc.thinker.cabbage.user.model.UserMoneyLog;
import vc.thinker.cabbage.user.model.UserMoneyLogExample;
import vc.thinker.cabbage.user.vo.UserMoneyLogVO;

@Repository
public class UserMoneyLogDao {

	@Autowired
	private UserMoneyLogMapper mapper;
	
	
	public int countByOutOrderId(String outOrderId){
		UserMoneyLogExample example=new UserMoneyLogExample();
		example.createCriteria().andOutOrderIdEqualTo(outOrderId);
		return mapper.countByExample(example);
	}
	
	public List<UserMoneyLogBO> listPageByVo(Page<UserMoneyLogBO> page, UserMoneyLogVO vo){
		return mapper.listPageByVo(page,vo);
	}
	
	public List<UserMoneyLogBO> findPageByVO(UserMoneyLogVO vo, Page<UserMoneyLogBO> page)
	{
		return mapper.findPageByVO(vo, page);
	}

   /** generate code begin**/
	public List<UserMoneyLogBO> findAll(){
		UserMoneyLogExample example=new UserMoneyLogExample();
		return mapper.selectByExample(example);
	}
	List<UserMoneyLogBO> findAll(Iterable<Long> ids){
		UserMoneyLogExample example=new UserMoneyLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserMoneyLogExample example=new UserMoneyLogExample();
		return mapper.countByExample(example);
	}

	public List<UserMoneyLog> save(Iterable<UserMoneyLog> entities){
		List<UserMoneyLog> list=new ArrayList<UserMoneyLog>();
		for (UserMoneyLog UserMoneyLog : entities) {
			list.add(save(UserMoneyLog));
		}
		return list;
	}
	
	public UserMoneyLog save(UserMoneyLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserMoneyLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserMoneyLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		UserMoneyLogExample example=new UserMoneyLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserMoneyLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserMoneyLog> entities){
		List<Long> ids=Lists.newArrayList();
		for (UserMoneyLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserMoneyLogExample example=new UserMoneyLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserMoneyLogExample example=new UserMoneyLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
