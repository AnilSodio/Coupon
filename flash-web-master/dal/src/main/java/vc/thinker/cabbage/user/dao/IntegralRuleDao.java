package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.mapper.IntegralRuleMapper;
import vc.thinker.cabbage.user.model.IntegralRule;
import vc.thinker.cabbage.user.bo.IntegralRuleBO;
import vc.thinker.cabbage.user.model.IntegralRuleExample;
import vc.thinker.cabbage.user.model.IntegralRuleExample.Criteria;
import vc.thinker.cabbage.user.vo.IntegralRuleVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class IntegralRuleDao {

	@Autowired
	private IntegralRuleMapper mapper;

	public IntegralRuleBO findByCode(String code){
		IntegralRuleExample example=new IntegralRuleExample();
		example.createCriteria().andRuleCodeEqualTo(code);
		
		List<IntegralRuleBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}

   /** generate code begin**/
	public List<IntegralRuleBO> findAll(){
		IntegralRuleExample example=new IntegralRuleExample();
		return mapper.selectByExample(example);
	}
	List<IntegralRuleBO> findAll(Iterable<java.lang.Long> ids){
		IntegralRuleExample example=new IntegralRuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		IntegralRuleExample example=new IntegralRuleExample();
		return mapper.countByExample(example);
	}

	public List<IntegralRule> save(Iterable<IntegralRule> entities){
		List<IntegralRule> list=new ArrayList<IntegralRule>();
		for (IntegralRule IntegralRule : entities) {
			list.add(save(IntegralRule));
		}
		return list;
	}
	
	public IntegralRule save(IntegralRule record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(IntegralRule record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public IntegralRuleBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		IntegralRuleExample example=new IntegralRuleExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(IntegralRule entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<IntegralRule> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (IntegralRule  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		IntegralRuleExample example=new IntegralRuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		IntegralRuleExample example=new IntegralRuleExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<IntegralRuleBO> findPageByVo(MyPage<IntegralRuleBO> page, IntegralRuleVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countByCode(String ruleCode) {
		IntegralRuleExample example=new IntegralRuleExample();
		example.createCriteria().andRuleCodeEqualTo(ruleCode);
		return mapper.countByExample(example);
	}
}
