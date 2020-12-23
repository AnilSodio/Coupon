package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.bo.CabinetFirmwareBO;
import vc.thinker.cabbage.se.mapper.CabinetFirmwareMapper;
import vc.thinker.cabbage.se.model.CabinetFirmware;
import vc.thinker.cabbage.se.model.CabinetFirmwareExample;
import vc.thinker.cabbage.se.vo.CabinetFirmwareVO;

@Repository
public class CabinetFirmwareDao {

	@Autowired
	private CabinetFirmwareMapper mapper;
	
	/**
	 * 查找最后的版本
	 * @param mouble
	 * @return
	 */
	public CabinetFirmwareBO findLastByMouble(String mouble) {
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		example.createCriteria().andModuleCodeEqualTo(mouble);
		example.setOrderByClause("firmware_name desc");
		example.setLimit(1);
		List<CabinetFirmwareBO> list= mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}


   /** generate code begin**/
	public List<CabinetFirmwareBO> findAll(){
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		return mapper.selectByExample(example);
	}
	List<CabinetFirmwareBO> findAll(Iterable<java.lang.Long> ids){
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		return mapper.countByExample(example);
	}

	public List<CabinetFirmware> save(Iterable<CabinetFirmware> entities){
		List<CabinetFirmware> list=new ArrayList<CabinetFirmware>();
		for (CabinetFirmware CabinetFirmware : entities) {
			list.add(save(CabinetFirmware));
		}
		return list;
	}
	
	public CabinetFirmware save(CabinetFirmware record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(CabinetFirmware record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CabinetFirmwareBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(CabinetFirmware entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<CabinetFirmware> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (CabinetFirmware  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CabinetFirmwareExample example=new CabinetFirmwareExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<CabinetFirmwareBO> findPageByVo(Page<CabinetFirmwareBO> page, CabinetFirmwareVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
