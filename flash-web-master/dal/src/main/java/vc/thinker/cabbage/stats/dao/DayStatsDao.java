package vc.thinker.cabbage.stats.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vc.thinker.cabbage.stats.bo.DayStatsBO;
import vc.thinker.cabbage.stats.mapper.DayStatsMapper;
import vc.thinker.cabbage.stats.model.DayStats;
import vc.thinker.cabbage.stats.model.DayStatsExample;


@Repository
public class DayStatsDao {

	@Autowired
	private DayStatsMapper mapper;


   /** generate code begin**/
	public List<DayStatsBO> findAll(){
		DayStatsExample example=new DayStatsExample();
		return mapper.selectByExample(example);
	}
	
	public long count(){
		DayStatsExample example=new DayStatsExample();
		return mapper.countByExample(example);
	}

	public List<DayStats> save(Iterable<DayStats> entities){
		List<DayStats> list=new ArrayList<DayStats>();
		for (DayStats DayStats : entities) {
			list.add(save(DayStats));
		}
		return list;
	}
	
	public DayStats save(DayStats record){
		
		mapper.insertSelective(record);
		
		return record;
	}
	public void update(DayStats record){
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public List<DayStatsBO> findByOfficeId(java.lang.Long officeId){
		DayStatsExample example=new DayStatsExample();
		example.createCriteria().andOfficeIdEqualTo(officeId);
		return mapper.selectByExample(example);
	}
	
	public int deleteByOfficeId(java.lang.Long officeId){
		DayStatsExample example=new DayStatsExample();
		example.createCriteria().andOfficeIdEqualTo(officeId);
		return mapper.deleteByExample(example);
	}
	public List<DayStatsBO> findByStatsDate(java.util.Date statsDate){
		DayStatsExample example=new DayStatsExample();
		example.createCriteria().andStatsDateEqualTo(statsDate);
		return mapper.selectByExample(example);
	}
	
	public int deleteByStatsDate(java.util.Date statsDate){
		DayStatsExample example=new DayStatsExample();
		example.createCriteria().andStatsDateEqualTo(statsDate);
		return mapper.deleteByExample(example);
	}

	public int delete( java.lang.Long officeId, java.util.Date statsDate){
		return mapper.deleteByPrimaryKey(officeId,statsDate);
	}
	public DayStatsBO findOne( java.lang.Long officeId, java.util.Date statsDate){
		return mapper.selectByPrimaryKey(officeId,statsDate);
	}
	/** generate code end**/

	public void updateByMonth(String month) {
		 mapper.updateByMonth(month);
	}

	public DayStatsBO findByStatsDateAndOid(Date date, Long oid) {
		DayStatsExample example=new DayStatsExample();
		example.createCriteria().andStatsDateEqualTo(date).andOfficeIdEqualTo(oid);
		List<DayStatsBO> list =  mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
}
