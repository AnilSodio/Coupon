package vc.thinker.cabbage.sys.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.druid.util.StringUtils;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.mapper.PlatformRevenueMapper;
import vc.thinker.cabbage.sys.model.PlatformRevenue;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.sys.bo.PlatformRevenueBO;
import vc.thinker.cabbage.sys.model.PlatformRevenueExample;
import vc.thinker.cabbage.sys.vo.PlatformRevenueVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class PlatformRevenueDao {

	@Autowired
	private PlatformRevenueMapper mapper;


   /** generate code begin**/
	public List<PlatformRevenueBO> findAll(){
		PlatformRevenueExample example=new PlatformRevenueExample();
		return mapper.selectByExample(example);
	}
	List<PlatformRevenueBO> findAll(Iterable<java.lang.Long> ids){
		PlatformRevenueExample example=new PlatformRevenueExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PlatformRevenueExample example=new PlatformRevenueExample();
		return mapper.countByExample(example);
	}

	public List<PlatformRevenue> save(Iterable<PlatformRevenue> entities){
		List<PlatformRevenue> list=new ArrayList<PlatformRevenue>();
		for (PlatformRevenue PlatformRevenue : entities) {
			list.add(save(PlatformRevenue));
		}
		return list;
	}
	
	public PlatformRevenue save(PlatformRevenue record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PlatformRevenue record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PlatformRevenueBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PlatformRevenueExample example=new PlatformRevenueExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PlatformRevenue entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PlatformRevenue> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PlatformRevenue  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PlatformRevenueExample example=new PlatformRevenueExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PlatformRevenueExample example=new PlatformRevenueExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<PlatformRevenueBO> findPageByVo(MyPage<PlatformRevenueBO> page, PlatformRevenueVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public BigDecimal sumByDate(String date) {
		return mapper.sumByDate(date);
	}
	public List<CountStatsBO> findIncomeByStats(StatsVO vo) {
		return mapper.findIncomeByStats(vo);
	}
}
