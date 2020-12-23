package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.user.bo.UserMoneyCashBO;
import vc.thinker.cabbage.user.mapper.UserMoneyCashMapper;
import vc.thinker.cabbage.user.model.UserMoneyCash;
import vc.thinker.cabbage.user.model.UserMoneyCashExample;
import vc.thinker.cabbage.user.vo.UserMoneyCashVO;

@Repository
public class UserMoneyCashDao {

	@Autowired
	private UserMoneyCashMapper mapper;
	
	public UserMoneyCashBO findBySn(String cashSn){
		UserMoneyCashExample example=new UserMoneyCashExample();
		example.createCriteria().andCashSnEqualTo(cashSn);
		List<UserMoneyCashBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}

	public List<UserMoneyCashBO> findPageByVO(UserMoneyCashVO vo, Page<UserMoneyCashBO> page){
		return mapper.findPageByVO(vo, page);
	}

	
   /** generate code begin**/
	public List<UserMoneyCashBO> findAll(){
		UserMoneyCashExample example=new UserMoneyCashExample();
		return mapper.selectByExample(example);
	}
	List<UserMoneyCashBO> findAll(Iterable<Long> ids){
		UserMoneyCashExample example=new UserMoneyCashExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserMoneyCashExample example=new UserMoneyCashExample();
		return mapper.countByExample(example);
	}

	public List<UserMoneyCash> save(Iterable<UserMoneyCash> entities){
		List<UserMoneyCash> list=new ArrayList<UserMoneyCash>();
		for (UserMoneyCash UserMoneyCash : entities) {
			list.add(save(UserMoneyCash));
		}
		return list;
	}
	
	public UserMoneyCash save(UserMoneyCash record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserMoneyCash record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserMoneyCashBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		UserMoneyCashExample example=new UserMoneyCashExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserMoneyCash entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserMoneyCash> entities){
		List<Long> ids=Lists.newArrayList();
		for (UserMoneyCash  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserMoneyCashExample example=new UserMoneyCashExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserMoneyCashExample example=new UserMoneyCashExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	public UserMoneyCashBO findDetailOne(Long id) {
		return mapper.findDetailOne(id);
	}

	public List<UserMoneyCashBO> findPageByVo(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return mapper.findPageByVO(vo, page);
	}

	public List<UserMoneyCashBO> findPageByReferee(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return mapper.findPageByReferee(page,vo);
	}

	public List<UserMoneyCashBO> findPageBySeller(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return mapper.findPageBySeller(page,vo);
	}

	public List<UserMoneyCashBO> findPageByAgent(MyPage<UserMoneyCashBO> page, UserMoneyCashVO vo) {
		return mapper.findPageByAgent(page,vo);
	}
}
