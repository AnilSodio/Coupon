package vc.thinker.cabbage.se.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.FeedbackMapper;
import vc.thinker.cabbage.se.model.Feedback;
import vc.thinker.cabbage.se.bo.FeedbackBO;
import vc.thinker.cabbage.se.bo.FeedbackTypeBO;
import vc.thinker.cabbage.se.model.FeedbackExample;
import vc.thinker.cabbage.se.vo.FeedbackVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.bo.OrderStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class FeedbackDao {

	@Autowired
	private FeedbackMapper mapper;
	
	/**
	 * 根据订单code和行程类型查找行程
	 * @param orderCode
	 * @return
	 */
	public List<FeedbackBO> findByOrderCode(String orderCode,String feedType){
		FeedbackExample example=new FeedbackExample();
		example.createCriteria().andOrderCodeEqualTo(orderCode).andFeedTypeEqualTo(feedType);
		return mapper.selectByExample(example);
	}


   /** generate code begin**/
	public List<FeedbackBO> findAll(){
		FeedbackExample example=new FeedbackExample();
		return mapper.selectByExample(example);
	}
	List<FeedbackBO> findAll(Iterable<java.lang.Long> ids){
		FeedbackExample example=new FeedbackExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		FeedbackExample example=new FeedbackExample();
		return mapper.countByExample(example);
	}

	public List<Feedback> save(Iterable<Feedback> entities){
		List<Feedback> list=new ArrayList<Feedback>();
		for (Feedback Feedback : entities) {
			list.add(save(Feedback));
		}
		return list;
	}
	
	public Feedback save(Feedback record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Feedback record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public FeedbackBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		FeedbackExample example=new FeedbackExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Feedback entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Feedback> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Feedback  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		FeedbackExample example=new FeedbackExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		FeedbackExample example=new FeedbackExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<FeedbackTypeBO> findPageByVo(MyPage<FeedbackBO> page, FeedbackVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public FeedbackBO findDetailedOne(Long id) {
		return mapper.findDetailedOne(id);
	}
	public List<FeedbackBO> findByOrderId(Long id) {
		FeedbackExample example=new FeedbackExample();
		example.createCriteria().andOrderIdEqualTo(id);
		return mapper.selectByExample(example);
	}

	public List<FeedbackBO> countByTypeId() {
		return mapper.countByTypeId();
	}


	public List<FeedbackBO> groupByFeedType() {
		return mapper.groupByFeedType();
	}


	public List<FeedbackBO> findRecentDays(int days){
		
		FeedbackExample example=new FeedbackExample();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -1*days);

		example.createCriteria()
				.andCreateTimeGreaterThanOrEqualTo(now.getTime());
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}


	public List<CountStatsBO> feedbackStats(StatsVO vo) {
		return mapper.feedbackStats(vo);
	}

	public BigDecimal countByTime(String year, String month,Boolean isUsing) {
		return mapper.countByTime(year,month,isUsing);
	}
}
