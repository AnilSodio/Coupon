package vc.thinker.cabbage.stats.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.mapper.BalanceStatsMapper;
import vc.thinker.cabbage.stats.model.BalanceStats;
import vc.thinker.cabbage.stats.bo.BalanceStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.model.BalanceStatsExample;

@Repository
public class BalanceStatsDao {

	@Autowired
	private BalanceStatsMapper mapper;


   /** generate code begin**/
	public List<BalanceStatsBO> findAll(){
		BalanceStatsExample example=new BalanceStatsExample();
		return mapper.selectByExample(example);
	}
	List<BalanceStatsBO> findAll(Iterable<java.lang.Long> ids){
		BalanceStatsExample example=new BalanceStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		BalanceStatsExample example=new BalanceStatsExample();
		return mapper.countByExample(example);
	}

	public List<BalanceStats> save(Iterable<BalanceStats> entities){
		List<BalanceStats> list=new ArrayList<BalanceStats>();
		for (BalanceStats BalanceStats : entities) {
			list.add(save(BalanceStats));
		}
		return list;
	}
	
	public BalanceStats save(BalanceStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(BalanceStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public BalanceStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		BalanceStatsExample example=new BalanceStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(BalanceStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<BalanceStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (BalanceStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		BalanceStatsExample example=new BalanceStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		BalanceStatsExample example=new BalanceStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public BigDecimal sumByDate(String date) {
		return mapper.sumByDate(date);
	}
	public BigDecimal sumAvgByDate(String beginDate, String endDate) {
		return mapper.sumAvgByDate(beginDate,endDate);
	}
	public List<RealTimeGroupStatsBO> findBalanceByGoupType(String groupType, String date) {
		return mapper.findBalanceByGoupType(groupType,date);
	}
	public int countIsBuyNum() {
		return mapper.countIsBuyNum();
	}
	public List<BalanceStatsBO> sumByDateGouupByCurrency(String date) {
		return mapper.sumByDateGouupByCurrency(date);
	}
	public List<BalanceStatsBO> sumAvgByDateGroupByCurrency(String beginDate, String endDate) {
		return mapper.sumAvgByDateGroupByCurrency(beginDate,endDate);
	}
	
}
