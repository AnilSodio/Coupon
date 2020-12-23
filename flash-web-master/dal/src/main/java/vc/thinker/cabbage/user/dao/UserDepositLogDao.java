package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.user.bo.UserDepositLogBO;
import vc.thinker.cabbage.user.mapper.UserDepositLogMapper;
import vc.thinker.cabbage.user.model.UserDepositLog;
import vc.thinker.cabbage.user.model.UserDepositLogExample;
import vc.thinker.cabbage.user.vo.UserDepositLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class UserDepositLogDao {

	@Autowired
	private UserDepositLogMapper mapper;

	public List<UserDepositLogBO> selectPageByUid(MyPage<UserDepositLogBO> page, UserDepositLogVO vo) {
		return mapper.selectPageByUid(page,vo);
	}
	

   /** generate code begin**/
	public List<UserDepositLogBO> findAll(){
		UserDepositLogExample example=new UserDepositLogExample();
		return mapper.selectByExample(example);
	}
	List<UserDepositLogBO> findAll(Iterable<java.lang.Long> ids){
		UserDepositLogExample example=new UserDepositLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserDepositLogExample example=new UserDepositLogExample();
		return mapper.countByExample(example);
	}

	public List<UserDepositLog> save(Iterable<UserDepositLog> entities){
		List<UserDepositLog> list=new ArrayList<UserDepositLog>();
		for (UserDepositLog UserDepositLog : entities) {
			list.add(save(UserDepositLog));
		}
		return list;
	}
	
	public UserDepositLog save(UserDepositLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserDepositLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserDepositLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		UserDepositLogExample example=new UserDepositLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserDepositLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserDepositLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (UserDepositLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		UserDepositLogExample example=new UserDepositLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserDepositLogExample example=new UserDepositLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<UserDepositLogBO> findPageByVO(Page<UserDepositLogBO> page, UserDepositLogVO vo) {
		return mapper.findPageByVO(page,vo);
	}
	
	public List<UserDepositLogBO> findListByUid(Long uid, String type) {
		UserDepositLogExample example=new UserDepositLogExample();
		example.createCriteria().andUidEqualTo(uid).andTypeEqualTo(type);
		return mapper.selectByExample(example);
	}

	public UserDepositLogBO findbyOutRefundNo(String outOrderId) {
		UserDepositLogExample example=new UserDepositLogExample();
		example.createCriteria().andOutRefundNoEqualTo(outOrderId);
		List<UserDepositLogBO> resu_list = mapper.selectByExample(example);
		return null != resu_list && resu_list.size()>0 ? resu_list.get(0):null;
	}


	public List<CountStatsBO> findDepositStats(StatsVO vo) {
		return mapper.findDepositStats(vo);
	}


	public UserDepositLogBO findbyOutRefundNo(String outOrderId, String type) {
			UserDepositLogExample example=new UserDepositLogExample();
			example.createCriteria().andOutRefundNoEqualTo(outOrderId);
			example.createCriteria().andTypeEqualTo(type);
			List<UserDepositLogBO> resu_list = mapper.selectByExample(example);
			return null != resu_list && resu_list.size()>0 ? resu_list.get(0):null;
	}
	
}
