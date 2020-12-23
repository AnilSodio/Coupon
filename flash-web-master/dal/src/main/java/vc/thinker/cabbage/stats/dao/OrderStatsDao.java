package vc.thinker.cabbage.stats.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.stats.mapper.OrderStatsMapper;
import vc.thinker.cabbage.stats.model.OrderStats;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.bo.RealTimeGroupStatsBO;
import vc.thinker.cabbage.stats.model.OrderStatsExample;
import vc.thinker.cabbage.stats.vo.StatsVO;

@Repository
public class OrderStatsDao {

	@Autowired
	private OrderStatsMapper mapper;


   /** generate code begin**/
	public List<OrderStatsBO> findAll(){
		OrderStatsExample example=new OrderStatsExample();
		return mapper.selectByExample(example);
	}
	List<OrderStatsBO> findAll(Iterable<java.lang.Long> ids){
		OrderStatsExample example=new OrderStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		OrderStatsExample example=new OrderStatsExample();
		return mapper.countByExample(example);
	}

	public List<OrderStats> save(Iterable<OrderStats> entities){
		List<OrderStats> list=new ArrayList<OrderStats>();
		for (OrderStats OrderStats : entities) {
			list.add(save(OrderStats));
		}
		return list;
	}
	
	public OrderStats save(OrderStats record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(OrderStats record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public OrderStatsBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		OrderStatsExample example=new OrderStatsExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(OrderStats entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<OrderStats> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (OrderStats  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		OrderStatsExample example=new OrderStatsExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		OrderStatsExample example=new OrderStatsExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	
	public OrderStatsBO countByDate(String date) {
		return mapper.countByDate(date);
	}
	public OrderStatsBO countAvgByDate(String beginDate, String endDate) {
		return mapper.countAvgByDate(beginDate,endDate);
	}
	public List<RealTimeGroupStatsBO> findTripInfoByGroupType(String groupType, String date, String type) {
		return mapper.findTripInfoByGroupType(groupType,date,type);
	}
	public BigDecimal findTripInfoByTimeRange(String day, String time1, String time2, String type) {
		return mapper.findTripInfoByTimeRange(day,time1, time2, type);
	}
	public BigDecimal findTripInfoByAgeRange(Integer age1, Integer age2, String date, String type) {
		return mapper.findTripInfoByAgeRange(age1,age2,date,type);
	}
	
	public List<OrderStatsBO> findByDate(Calendar calendar) {
		
		OrderStatsExample example=new OrderStatsExample();
		
        calendar.set(Calendar.SECOND, 1);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        Date yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date today = calendar.getTime();

        example.createCriteria().andStatsDateBetween(yesterday, today);
        example.setOrderByClause("stats_date desc");

        return mapper.selectByExample(example);
    }
	
	public List<OrderStatsBO> findRecentDays(int days) {
		
		OrderStatsExample example=new OrderStatsExample();
		
		  Calendar now = Calendar.getInstance();
	      now.add(Calendar.DAY_OF_MONTH, -1 * days);

        example.createCriteria()
                .andStatsDateGreaterThanOrEqualTo(now.getTime());
        example.setOrderByClause("stats_date desc");

        return mapper.selectByExample(example);
	}
	
	public List<OrderStatsBO> groupByPayType() {
		return mapper.groupByPayType();
	}
	public List<CountStatsBO> orderConsumeStats(StatsVO vo) {
		return mapper.orderConsumeStats(vo);
	}
	public OrderStatsBO countByTime(String year, String month) {
		return mapper.countByTime(year,month);
	}
	public List<OrderStatsBO> countByDateGoupByCurrency(String year, String month) {
		return mapper.countByDateGoupByCurrency(year,month);
	}
	public void deleteByDate(String beginDate, String endDate) {
		mapper.deleteByDate(beginDate,endDate);
	}
}
