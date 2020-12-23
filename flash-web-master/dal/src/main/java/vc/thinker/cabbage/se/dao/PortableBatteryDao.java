package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.common.MyPage;
import vc.thinker.cabbage.se.PortableBatteryConstatns;
import vc.thinker.cabbage.se.bo.PortableBatteryBO;
import vc.thinker.cabbage.se.mapper.PortableBatteryMapper;
import vc.thinker.cabbage.se.model.PortableBattery;
import vc.thinker.cabbage.se.model.PortableBatteryExample;
import vc.thinker.cabbage.se.vo.PortableBatteryVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;

@Repository
public class PortableBatteryDao {

	@Autowired
	private PortableBatteryMapper mapper;
	
	public PortableBatteryBO findByCode(String code){
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andPortableBatteryCodeEqualTo(code);
		List<PortableBatteryBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null : list.get(0);
	}


	public int insertUpdate(PortableBattery pb){
		return mapper.insertUpdateSelective(pb);
	}
	
	public int updateByCode(PortableBattery record,String code) {
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andPortableBatteryCodeEqualTo(code);
		return mapper.updateByExampleSelective(record, example);
	}
	
   /** generate code begin**/
	public List<PortableBatteryBO> findAll(){
		PortableBatteryExample example=new PortableBatteryExample();
		return mapper.selectByExample(example);
	}
	List<PortableBatteryBO> findAll(Iterable<java.lang.Long> ids){
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PortableBatteryExample example=new PortableBatteryExample();
		return mapper.countByExample(example);
	}

	public List<PortableBattery> save(Iterable<PortableBattery> entities){
		List<PortableBattery> list=new ArrayList<PortableBattery>();
		for (PortableBattery PortableBattery : entities) {
			list.add(save(PortableBattery));
		}
		return list;
	}
	
	public PortableBattery save(PortableBattery record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PortableBattery record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PortableBatteryBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PortableBattery entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PortableBattery> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PortableBattery  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PortableBatteryExample example=new PortableBatteryExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<PortableBatteryBO> findPageByVo(MyPage<PortableBatteryBO> page, PortableBatteryVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countByPortableBatteryCode(String portableBatteryCode) {
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andPortableBatteryCodeEqualTo(portableBatteryCode);
		return mapper.countByExample(example);
	}


	public List<PortableBatteryBO> findBycabinetId(Long cabinetId) {
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andCabinetIdEqualTo(cabinetId);
		return mapper.selectByExample(example);
	}


	public List<PortableBatteryBO> findInCabinetByCabinetId(Long cabinetId) {
		PortableBatteryExample example=new PortableBatteryExample();
		example.createCriteria().andCabinetIdEqualTo(cabinetId).andLocationTypeEqualTo(PortableBatteryConstatns.LOCATION_TYPE_IN_CABINET);
		return mapper.selectByExample(example);
	}

	public Integer countByStatus() {
		PortableBatteryExample example=new PortableBatteryExample();
		return mapper.countByExample(example);
	}


	public List<CountStatsBO> statsPb(StatsVO vo) {
		return mapper.statsPb(vo);
	}
}
