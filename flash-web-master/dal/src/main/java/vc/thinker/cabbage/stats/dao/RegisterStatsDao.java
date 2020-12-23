package vc.thinker.cabbage.stats.dao;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.bo.RegisterStatsBO;
import vc.thinker.cabbage.stats.mapper.RegisterStatsMapper;
import vc.thinker.cabbage.stats.model.RegisterStats;
import vc.thinker.cabbage.stats.model.RegisterStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RegisterStatsDao {

	@Autowired
	private RegisterStatsMapper mapper;


   /** generate code begin**/
	public List<RegisterStatsBO> findAll(){
		RegisterStatsExample example=new RegisterStatsExample();
		return mapper.selectByExample(example);
	}
	List<RegisterStatsBO> findAll(Iterable<java.lang.Long> ids){
		RegisterStatsExample example=new RegisterStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		RegisterStatsExample example=new RegisterStatsExample();
		return mapper.countByExample(example);
	}

	public List<RegisterStats> save(Iterable<RegisterStats> entities){
		List<RegisterStats> list=new ArrayList<RegisterStats>();
		for (RegisterStats RegisterStats : entities) {
			list.add(save(RegisterStats));
		}
		return list;
	}
	
	public RegisterStats save(RegisterStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(RegisterStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public RegisterStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		RegisterStatsExample example=new RegisterStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(RegisterStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<RegisterStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (RegisterStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		RegisterStatsExample example=new RegisterStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		RegisterStatsExample example=new RegisterStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public BigDecimal countByDate(String date) {
		return mapper.countByDate(date);
	}
	public BigDecimal countAvgByDate(String beginDate, String endDate) {
		return mapper.countAvgByDate(beginDate,endDate);
	}
	public List<RealTimeGroupStatsBO> findRegistInfoByGroupType(String groupType, String date) {
		return mapper.findRegistInfoByGroupType(groupType,date);
	}
	public BigDecimal findRegistByTimeRange(String day,String date1, String date2) {
		return mapper.findRegistByTimeRange(day,date1,date2);
	}
	public RegisterStatsBO findByUid(Long uid) {
		RegisterStatsExample example=new RegisterStatsExample();
		example.createCriteria().andUidEqualTo(uid);
		List<RegisterStatsBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
	public BigDecimal findTotalByTime(String year, String month) {
		return mapper.findTotalByTime(year,month);
	}

	public int saveOrUpdate(RegisterStats registerStats){
		return mapper.saveOrUpdate(registerStats);
	}

	public List<RegisterStatsBO> getAllActiveUser() {
		return mapper.getAllActiveUser();
	}
	public BigDecimal findRegistByAgeRange(Integer age1, Integer age2, String date) {
		return mapper.findRegistByAgeRange(age1, age2,date);
	}
	public boolean existsByUid(Long uid) {
		RegisterStatsExample example=new RegisterStatsExample();
		example.createCriteria().andUidEqualTo(uid);
		return mapper.countByExample(example) > 0;
	}
	public void deleteByDate(String beginDate, String endDate) {
		mapper.deleteByDate(beginDate,endDate);
	}
	public List<RegisterStatsBO> statsBySex() {
		return mapper.statsBySex();
	}
	public List<RegisterStatsBO> countUserByAge() {
		return mapper.countUserByAge();
	}
	public List<CountStatsBO> findUserStats(StatsVO vo) {
		return mapper.findUserStats(vo);
	}
	public void deleteByVo(StatsVO vo) {
		mapper.deleteByVo(vo);
	}
}
