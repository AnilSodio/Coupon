package vc.thinker.cabbage.stats.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.mapper.IncomeStatsMapper;
import vc.thinker.cabbage.stats.model.IncomeStats;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.IncomeStatsBO;
import vc.thinker.cabbage.stats.model.IncomeStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

@Repository
public class IncomeStatsDao {

	@Autowired
	private IncomeStatsMapper mapper;


   /** generate code begin**/
	public List<IncomeStatsBO> findAll(){
		IncomeStatsExample example=new IncomeStatsExample();
		return mapper.selectByExample(example);
	}
	List<IncomeStatsBO> findAll(Iterable<java.lang.Long> ids){
		IncomeStatsExample example=new IncomeStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		IncomeStatsExample example=new IncomeStatsExample();
		return mapper.countByExample(example);
	}

	public List<IncomeStats> save(Iterable<IncomeStats> entities){
		List<IncomeStats> list=new ArrayList<IncomeStats>();
		for (IncomeStats IncomeStats : entities) {
			list.add(save(IncomeStats));
		}
		return list;
	}
	
	public IncomeStats save(IncomeStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(IncomeStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public IncomeStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		IncomeStatsExample example=new IncomeStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(IncomeStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<IncomeStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (IncomeStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		IncomeStatsExample example=new IncomeStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		IncomeStatsExample example=new IncomeStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public IncomeStatsBO findbyCurrentDay(String currentDay) {
		IncomeStatsExample example=new IncomeStatsExample();
		example.createCriteria().andCurrentDayEqualTo(currentDay);
		List<IncomeStatsBO> selectByExample = mapper.selectByExample(example);
		return null != selectByExample && selectByExample.size()>0?selectByExample.get(0):null;
	}
	public List<CountStatsBO> findDailyIncome(StatsVO vo) {
		return mapper.findDailyIncome(vo);
	}
}
