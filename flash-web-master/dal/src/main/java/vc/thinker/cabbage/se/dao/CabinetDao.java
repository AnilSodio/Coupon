package vc.thinker.cabbage.se.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.CabinetMapper;
import vc.thinker.cabbage.se.model.Cabinet;
import vc.thinker.cabbage.se.bo.CabinetBO;
import vc.thinker.cabbage.se.model.CabinetExample;
import vc.thinker.cabbage.se.model.CabinetExample.Criteria;
import vc.thinker.cabbage.se.vo.CabinetVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class CabinetDao {

	@Autowired
	private CabinetMapper mapper;
	
	public CabinetBO findByCabinetCode(String code){
		CabinetExample example=new CabinetExample();
		example.createCriteria().andCabinetCodeEqualTo(code);
		List<CabinetBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}
	
	public CabinetBO findBySysCode(String sysCode){
		CabinetExample example=new CabinetExample();
		example.createCriteria().andSysCodeEqualTo(sysCode);
		List<CabinetBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}


   /** generate code begin**/
	public List<CabinetBO> findAll(){
		CabinetExample example=new CabinetExample();
		return mapper.selectByExample(example);
	}
	List<CabinetBO> findAll(Iterable<java.lang.Long> ids){
		CabinetExample example=new CabinetExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CabinetExample example=new CabinetExample();
		return mapper.countByExample(example);
	}

	public List<Cabinet> save(Iterable<Cabinet> entities){
		List<Cabinet> list=new ArrayList<Cabinet>();
		for (Cabinet Cabinet : entities) {
			list.add(save(Cabinet));
		}
		return list;
	}
	
	public Cabinet save(Cabinet record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Cabinet record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CabinetBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CabinetExample example=new CabinetExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Cabinet entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Cabinet> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Cabinet  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CabinetExample example=new CabinetExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CabinetExample example=new CabinetExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<CabinetBO> findPageByVo(MyPage<CabinetBO> page, CabinetVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countByCabinetCode(String cabinetCode) {
		CabinetExample example=new CabinetExample();
		example.createCriteria().andCabinetCodeEqualTo(cabinetCode);
		return mapper.countByExample(example);
	}

	public List<CabinetBO> findByLocation(Point p, int distance) {
		return mapper.findByLocation(p,distance);
	}

	public CabinetBO findDetailsOne(Long id) {
		return mapper.findDetailsOne(id);
	}

	public int countByStatusAndDelivery(int status, Boolean isDelivery) {
		CabinetExample example=new CabinetExample();
		example.createCriteria().andIsDeliveryEqualTo(isDelivery).andStatusEqualTo(status);
		return mapper.countByExample(example);
	}

	public Integer countByStatus(Integer status) {
		CabinetExample example=new CabinetExample();
		Criteria c = example.createCriteria();
		if(null != status){
			c.andStatusEqualTo(status);
		}
		return mapper.countByExample(example);
	}

	public List<CountStatsBO> statsNormalCabinet(StatsVO vo) {
		return mapper.statsNormalCabinet(vo);
	}

	public BigDecimal findTotalByTime(String year, String month) {
		return mapper.findTotalByTime(year,month);
	}

	public CabinetBO findbyCabinetAlias(String cabinetAlias) {
		CabinetExample example=new CabinetExample();
		example.createCriteria().andCabinetAliasEqualTo(cabinetAlias);
		List<CabinetBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

	public int countByCabinetAlias(String cabinetAlias) {
		CabinetExample example=new CabinetExample();
		example.createCriteria().andCabinetAliasEqualTo(cabinetAlias);
		return mapper.countByExample(example);
	}

	public CabinetBO findByAliasOrSysCode(String cabinetAlias) {
		return mapper.findByAliasOrSysCode(cabinetAlias);
	}

	public List<CabinetBO> findCabinetsByType(long cabinetType) {
		return mapper.findCabinetsByType(cabinetType);
	}
}
