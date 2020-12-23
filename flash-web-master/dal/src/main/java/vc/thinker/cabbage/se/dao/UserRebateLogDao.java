package vc.thinker.cabbage.se.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.UserRebateLogMapper;
import vc.thinker.cabbage.se.model.UserRebateLog;
import vc.thinker.cabbage.se.bo.UserRebateLogBO;
import vc.thinker.cabbage.se.model.UserRebateLogExample;
import vc.thinker.cabbage.se.vo.UserRebateLogVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.CountStatsVO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class UserRebateLogDao {

	@Autowired
	private UserRebateLogMapper mapper;


   /** generate code begin**/
	public List<UserRebateLogBO> findAll(){
		UserRebateLogExample example=new UserRebateLogExample();
		return mapper.selectByExample(example);
	}
	List<UserRebateLogBO> findAll(Iterable<java.lang.Long> ids){
		UserRebateLogExample example=new UserRebateLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserRebateLogExample example=new UserRebateLogExample();
		return mapper.countByExample(example);
	}

	public List<UserRebateLog> save(Iterable<UserRebateLog> entities){
		List<UserRebateLog> list=new ArrayList<UserRebateLog>();
		for (UserRebateLog UserRebateLog : entities) {
			list.add(save(UserRebateLog));
		}
		return list;
	}
	
	public UserRebateLog save(UserRebateLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserRebateLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserRebateLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		UserRebateLogExample example=new UserRebateLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserRebateLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserRebateLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (UserRebateLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		UserRebateLogExample example=new UserRebateLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserRebateLogExample example=new UserRebateLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<UserRebateLogBO> findPageByVo(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public List<CountStatsBO> findRebateStats(StatsVO vo) {
		return mapper.findRebateStats(vo);
	}
	public BigDecimal sumRebate(Long uid, String date) {
		return mapper.sumRebate(uid,date);
	}
	public List<UserRebateLogBO> sellerIncomeStats(StatsVO vo) {
		return mapper.sellerIncomeStats(vo);
	}
	public List<UserRebateLogBO> refereeIncomeStats(StatsVO vo) {
		return mapper.refereeIncomeStats(vo);
	}
	public List<UserRebateLogBO> agentIncomeStats(StatsVO vo) {
		return mapper.agentIncomeStats(vo);
	}
	public List<UserRebateLogBO> findPageByAgent(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return mapper.findPageByAgent(page,vo);
	}
	public List<UserRebateLogBO> findPageByReferee(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return mapper.findPageByReferee(page,vo);
	}
	public List<UserRebateLogBO> findPageBySeller(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return mapper.findPageBySeller(page,vo);
	}
	public List<UserRebateLogBO> findPageByAdmin(MyPage<UserRebateLogBO> page, UserRebateLogVO vo) {
		return mapper.findPageByAdmin(page,vo);
	}
	public List<UserRebateLogBO> sumByCurrency() {
		return mapper.sumByCurrency();
	}
}
