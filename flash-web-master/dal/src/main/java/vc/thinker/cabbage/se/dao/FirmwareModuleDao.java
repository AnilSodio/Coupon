package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.FirmwareModuleMapper;
import vc.thinker.cabbage.se.model.FirmwareModule;
import vc.thinker.cabbage.se.bo.FirmwareModuleBO;
import vc.thinker.cabbage.se.model.FirmwareModuleExample;

@Repository
public class FirmwareModuleDao {

	@Autowired
	private FirmwareModuleMapper mapper;


   /** generate code begin**/
	public List<FirmwareModuleBO> findAll(){
		FirmwareModuleExample example=new FirmwareModuleExample();
		return mapper.selectByExample(example);
	}
	List<FirmwareModuleBO> findAll(Iterable<java.lang.Long> ids){
		FirmwareModuleExample example=new FirmwareModuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		FirmwareModuleExample example=new FirmwareModuleExample();
		return mapper.countByExample(example);
	}

	public List<FirmwareModule> save(Iterable<FirmwareModule> entities){
		List<FirmwareModule> list=new ArrayList<FirmwareModule>();
		for (FirmwareModule FirmwareModule : entities) {
			list.add(save(FirmwareModule));
		}
		return list;
	}
	
	public FirmwareModule save(FirmwareModule record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(FirmwareModule record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public FirmwareModuleBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		FirmwareModuleExample example=new FirmwareModuleExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(FirmwareModule entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<FirmwareModule> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (FirmwareModule  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		FirmwareModuleExample example=new FirmwareModuleExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		FirmwareModuleExample example=new FirmwareModuleExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
