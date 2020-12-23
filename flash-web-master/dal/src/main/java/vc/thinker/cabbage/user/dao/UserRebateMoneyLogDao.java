package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.mapper.UserRebateMoneyLogMapper;
import vc.thinker.cabbage.user.model.UserRebateMoneyLog;
import vc.thinker.cabbage.user.bo.UserRebateMoneyLogBO;
import vc.thinker.cabbage.user.model.UserRebateMoneyLogExample;
import vc.thinker.cabbage.user.vo.UserRebateMoneyLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class UserRebateMoneyLogDao {

	@Autowired
	private UserRebateMoneyLogMapper mapper;


   /** generate code begin**/
	public List<UserRebateMoneyLogBO> findAll(){
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		return mapper.selectByExample(example);
	}
	List<UserRebateMoneyLogBO> findAll(Iterable<java.lang.Long> ids){
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		return mapper.countByExample(example);
	}

	public List<UserRebateMoneyLog> save(Iterable<UserRebateMoneyLog> entities){
		List<UserRebateMoneyLog> list=new ArrayList<UserRebateMoneyLog>();
		for (UserRebateMoneyLog UserRebateMoneyLog : entities) {
			list.add(save(UserRebateMoneyLog));
		}
		return list;
	}
	
	public UserRebateMoneyLog save(UserRebateMoneyLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserRebateMoneyLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserRebateMoneyLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserRebateMoneyLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserRebateMoneyLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (UserRebateMoneyLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserRebateMoneyLogExample example=new UserRebateMoneyLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<UserRebateMoneyLogBO> findPageByVo(MyPage<UserRebateMoneyLogBO> page, UserRebateMoneyLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
