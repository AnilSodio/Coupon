package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.mapper.RefereeMapper;
import vc.thinker.cabbage.user.model.Referee;
import vc.thinker.cabbage.user.bo.RefereeBO;
import vc.thinker.cabbage.user.model.RefereeExample;
import vc.thinker.cabbage.user.vo.RefereeVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class RefereeDao {

	@Autowired
	private RefereeMapper mapper;


   /** generate code begin**/
	public List<RefereeBO> findAll(){
		RefereeExample example=new RefereeExample();
		return mapper.selectByExample(example);
	}
	List<RefereeBO> findAll(Iterable<java.lang.Long> ids){
		RefereeExample example=new RefereeExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		RefereeExample example=new RefereeExample();
		return mapper.countByExample(example);
	}

	public List<Referee> save(Iterable<Referee> entities){
		List<Referee> list=new ArrayList<Referee>();
		for (Referee Referee : entities) {
			list.add(save(Referee));
		}
		return list;
	}
	
	public Referee save(Referee record){
		if(!exists(record.getUid())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Referee record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public RefereeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		RefereeExample example=new RefereeExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Referee entity){
		 mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<Referee> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Referee  entity: entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		RefereeExample example=new RefereeExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		RefereeExample example=new RefereeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<RefereeBO> findPageByVo(MyPage<RefereeBO> page, RefereeVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	
	public int countByRefereeName(String refereeName) {
		RefereeExample example=new RefereeExample();
		example.createCriteria().andRefereeNameEqualTo(refereeName);
		return mapper.countByExample(example);
	}
	
	public int countByMobile(String mobile){
		RefereeExample example=new RefereeExample();
		example.createCriteria().andMobileEqualTo(mobile);
		return mapper.countByExample(example);
	}
	
	public int countByEmail(String email){
		RefereeExample example=new RefereeExample();
		example.createCriteria().andEmailEqualTo(email);
		return mapper.countByExample(example);
	}
}
