package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.CabinetChargeRuleMapper;
import vc.thinker.cabbage.se.model.CabinetChargeRule;
import vc.thinker.cabbage.se.bo.CabinetChargeRuleBO;
import vc.thinker.cabbage.se.model.CabinetChargeRuleExample;
import vc.thinker.cabbage.se.vo.CabinetChargeRuleVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class CabinetChargeRuleDao {

	@Autowired
	private CabinetChargeRuleMapper mapper;
	
//	public List<CabinetChargeRuleBO> findByCabinetTypeId(Long cabinetTypeId){
//		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
//		example.createCriteria().andCabinetTypeIdEqualTo(cabinetTypeId);
//		return mapper.selectByExample(example);
//	}

   /** generate code begin**/
	public List<CabinetChargeRuleBO> findAll(){
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		return mapper.selectByExample(example);
	}
	List<CabinetChargeRuleBO> findAll(Iterable<java.lang.Long> ids){
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		return mapper.countByExample(example);
	}

	public List<CabinetChargeRule> save(Iterable<CabinetChargeRule> entities){
		List<CabinetChargeRule> list=new ArrayList<CabinetChargeRule>();
		for (CabinetChargeRule CabinetChargeRule : entities) {
			list.add(save(CabinetChargeRule));
		}
		return list;
	}
	
	public CabinetChargeRule save(CabinetChargeRule record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(CabinetChargeRule record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CabinetChargeRuleBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(CabinetChargeRule entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<CabinetChargeRule> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (CabinetChargeRule  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CabinetChargeRuleExample example=new CabinetChargeRuleExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<CabinetChargeRuleBO> findPageByVo(MyPage<CabinetChargeRuleBO> page, CabinetChargeRuleVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
