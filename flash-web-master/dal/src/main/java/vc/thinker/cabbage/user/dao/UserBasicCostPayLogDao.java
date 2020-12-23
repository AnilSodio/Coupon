package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.mapper.UserBasicCostPayLogMapper;
import vc.thinker.cabbage.user.model.UserBasicCostPayLog;
import vc.thinker.cabbage.user.bo.UserBasicCostPayLogBO;
import vc.thinker.cabbage.user.model.UserBasicCostPayLogExample;
import vc.thinker.cabbage.user.model.UserBasicCostPayLogExample.Criteria;
import vc.thinker.cabbage.user.vo.UserBasicCostPayLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class UserBasicCostPayLogDao {

	@Autowired
	private UserBasicCostPayLogMapper mapper;


   /** generate code begin**/
	public List<UserBasicCostPayLogBO> findAll(){
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		return mapper.selectByExample(example);
	}
	List<UserBasicCostPayLogBO> findAll(Iterable<java.lang.Long> ids){
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		return mapper.countByExample(example);
	}

	public List<UserBasicCostPayLog> save(Iterable<UserBasicCostPayLog> entities){
		List<UserBasicCostPayLog> list=new ArrayList<UserBasicCostPayLog>();
		for (UserBasicCostPayLog UserBasicCostPayLog : entities) {
			list.add(save(UserBasicCostPayLog));
		}
		return list;
	}
	
	public UserBasicCostPayLog save(UserBasicCostPayLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserBasicCostPayLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserBasicCostPayLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserBasicCostPayLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserBasicCostPayLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (UserBasicCostPayLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public UserBasicCostPayLogBO findByPayOrderCode(String payOrderCode) {
		UserBasicCostPayLogExample example=new UserBasicCostPayLogExample();
		example.createCriteria().andPayOrderCodeEqualTo(payOrderCode);
		List<UserBasicCostPayLogBO> list = mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}
	public List<UserBasicCostPayLogBO> findPageByVo(MyPage<UserBasicCostPayLogBO> page, UserBasicCostPayLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
