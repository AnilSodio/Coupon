package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;
import com.sinco.common.utils.StringUtils;

import vc.thinker.cabbage.user.mapper.AgentMapper;
import vc.thinker.cabbage.user.model.Agent;
import vc.thinker.cabbage.user.bo.AgentBO;
import vc.thinker.cabbage.user.model.AgentExample;
import vc.thinker.cabbage.user.model.AgentExample.Criteria;
import vc.thinker.cabbage.user.vo.AgentVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class AgentDao {

	@Autowired
	private AgentMapper mapper;


   /** generate code begin**/
	public List<AgentBO> findAll(){
		AgentExample example=new AgentExample();
		return mapper.selectByExample(example);
	}
	List<AgentBO> findAll(Iterable<java.lang.Long> ids){
		AgentExample example=new AgentExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		AgentExample example=new AgentExample();
		return mapper.countByExample(example);
	}

	public List<Agent> save(Iterable<Agent> entities){
		List<Agent> list=new ArrayList<Agent>();
		for (Agent Agent : entities) {
			list.add(save(Agent));
		}
		return list;
	}
	
	public Agent save(Agent record){
		if(!exists(record.getUid())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Agent record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public AgentBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		AgentExample example=new AgentExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Agent entity){
		 mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<Agent> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Agent  entity: entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		AgentExample example=new AgentExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		AgentExample example=new AgentExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<AgentBO> findPageByVo(MyPage<AgentBO> page, AgentVO vo) {
		AgentExample example=new AgentExample();
		Criteria c = example.createCriteria();
		if(null != vo.getStatus()){
			c.andStatusEqualTo(vo.getStatus());
		}
		if(!StringUtils.isEmpty(vo.getAgentName())){
			c.andAgentNameLike("%" + vo.getAgentName() + "%");
		}
		if(!StringUtils.isEmpty(vo.getEmail())){
			c.andEmailLike("%" + vo.getEmail() + "%");
		}
		if(!StringUtils.isEmpty(vo.getMobile())){
			c.andMobileLike("%" + vo.getMobile() + "%");
		}
		if(!StringUtils.isEmpty(vo.getCountry())){
			c.andCountryEqualTo(vo.getCountry());
		}
		example.setPage(page);
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}
	
	public int countByAgentName(String agentName) {
		AgentExample example=new AgentExample();
		example.createCriteria().andAgentNameEqualTo(agentName);
		return mapper.countByExample(example);
	}
	
	public int countByMobile(String mobile){
		AgentExample example=new AgentExample();
		example.createCriteria().andMobileEqualTo(mobile);
		return mapper.countByExample(example);
	}
	
	public int countByEmail(String email){
		AgentExample example=new AgentExample();
		example.createCriteria().andEmailEqualTo(email);
		return mapper.countByExample(example);
	}
}
