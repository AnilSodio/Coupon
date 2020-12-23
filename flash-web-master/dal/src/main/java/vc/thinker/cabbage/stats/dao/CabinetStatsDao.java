package vc.thinker.cabbage.stats.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.mapper.CabinetStatsMapper;
import vc.thinker.cabbage.stats.model.CabinetStats;
import vc.thinker.cabbage.stats.bo.CabinetStatsBO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.model.CabinetStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

@Repository
public class CabinetStatsDao {

	@Autowired
	private CabinetStatsMapper mapper;


   /** generate code begin**/
	public List<CabinetStatsBO> findAll(){
		CabinetStatsExample example=new CabinetStatsExample();
		return mapper.selectByExample(example);
	}
	List<CabinetStatsBO> findAll(Iterable<java.lang.Long> ids){
		CabinetStatsExample example=new CabinetStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CabinetStatsExample example=new CabinetStatsExample();
		return mapper.countByExample(example);
	}

	public List<CabinetStats> save(Iterable<CabinetStats> entities){
		List<CabinetStats> list=new ArrayList<CabinetStats>();
		for (CabinetStats CabinetStats : entities) {
			list.add(save(CabinetStats));
		}
		return list;
	}
	
	public CabinetStats save(CabinetStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(CabinetStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CabinetStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CabinetStatsExample example=new CabinetStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(CabinetStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<CabinetStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (CabinetStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CabinetStatsExample example=new CabinetStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CabinetStatsExample example=new CabinetStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public CabinetStatsBO findByCurrentDay(String currentDay) {
		CabinetStatsExample example=new CabinetStatsExample();
		example.createCriteria().andCurrentDayEqualTo(currentDay);
		List<CabinetStatsBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
	public List<CountStatsBO> statsTotalCabinet(StatsVO vo) {
		return mapper.statsTotalCabinet(vo);
	}
}
