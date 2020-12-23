package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.PortableBatteryTypeMapper;
import vc.thinker.cabbage.se.model.PortableBatteryType;
import vc.thinker.cabbage.se.bo.PortableBatteryTypeBO;
import vc.thinker.cabbage.se.model.PortableBatteryTypeExample;
import vc.thinker.cabbage.se.vo.PortableBatteryTypeVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class PortableBatteryTypeDao {

	@Autowired
	private PortableBatteryTypeMapper mapper;


   /** generate code begin**/
	public List<PortableBatteryTypeBO> findAll(){
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		return mapper.selectByExample(example);
	}
	List<PortableBatteryTypeBO> findAll(Iterable<java.lang.Long> ids){
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		return mapper.countByExample(example);
	}

	public List<PortableBatteryType> save(Iterable<PortableBatteryType> entities){
		List<PortableBatteryType> list=new ArrayList<PortableBatteryType>();
		for (PortableBatteryType PortableBatteryType : entities) {
			list.add(save(PortableBatteryType));
		}
		return list;
	}
	
	public PortableBatteryType save(PortableBatteryType record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PortableBatteryType record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PortableBatteryTypeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PortableBatteryType entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PortableBatteryType> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PortableBatteryType  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<PortableBatteryTypeBO> findPageByVo(MyPage<PortableBatteryTypeBO> page, PortableBatteryTypeVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countByTypeName(String typeName) {
		PortableBatteryTypeExample example=new PortableBatteryTypeExample();
		example.createCriteria().andTypeNameEqualTo(typeName);
		return mapper.countByExample(example);
	}
}
