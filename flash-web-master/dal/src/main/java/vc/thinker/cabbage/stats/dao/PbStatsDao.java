package vc.thinker.cabbage.stats.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.mapper.PbStatsMapper;
import vc.thinker.cabbage.stats.model.PbStats;
import vc.thinker.cabbage.stats.bo.PbStatsBO;
import vc.thinker.cabbage.stats.model.PbStatsExample;

@Repository
public class PbStatsDao {

	@Autowired
	private PbStatsMapper mapper;


   /** generate code begin**/
	public List<PbStatsBO> findAll(){
		PbStatsExample example=new PbStatsExample();
		return mapper.selectByExample(example);
	}
	List<PbStatsBO> findAll(Iterable<java.lang.Long> ids){
		PbStatsExample example=new PbStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PbStatsExample example=new PbStatsExample();
		return mapper.countByExample(example);
	}

	public List<PbStats> save(Iterable<PbStats> entities){
		List<PbStats> list=new ArrayList<PbStats>();
		for (PbStats PbStats : entities) {
			list.add(save(PbStats));
		}
		return list;
	}
	
	public PbStats save(PbStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PbStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PbStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PbStatsExample example=new PbStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PbStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PbStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PbStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PbStatsExample example=new PbStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PbStatsExample example=new PbStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public PbStatsBO findByCurrentDay(String currentDay) {
		PbStatsExample example=new PbStatsExample();
		example.createCriteria().andCurrentDayEqualTo(currentDay);
		List<PbStatsBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
}
