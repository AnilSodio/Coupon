package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.CabinetTypeMapper;
import vc.thinker.cabbage.se.model.CabinetType;
import vc.thinker.cabbage.se.bo.CabinetTypeBO;
import vc.thinker.cabbage.se.model.CabinetTypeExample;
import vc.thinker.cabbage.se.vo.CabinetTypeVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class CabinetTypeDao {

	@Autowired
	private CabinetTypeMapper mapper;


   /** generate code begin**/
	public List<CabinetTypeBO> findAll(){
		CabinetTypeExample example=new CabinetTypeExample();
		return mapper.selectByExample(example);
	}
	List<CabinetTypeBO> findAll(Iterable<java.lang.Long> ids){
		CabinetTypeExample example=new CabinetTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		CabinetTypeExample example=new CabinetTypeExample();
		return mapper.countByExample(example);
	}
	
	public void initSave(Iterable<CabinetType> entities){
		for (CabinetType cabinetType : entities) {
			mapper.insertSelective(cabinetType);
		}
	}

	public List<CabinetType> save(Iterable<CabinetType> entities){
		List<CabinetType> list=new ArrayList<CabinetType>();
		for (CabinetType cabinetType : entities) {
			list.add(save(cabinetType));
		}
		return list;
	}
	
	public CabinetType save(CabinetType record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(CabinetType record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public CabinetTypeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		CabinetTypeExample example=new CabinetTypeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(CabinetType entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<CabinetType> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (CabinetType  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		CabinetTypeExample example=new CabinetTypeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		CabinetTypeExample example=new CabinetTypeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<CabinetTypeBO> findPageByVo(MyPage<CabinetTypeBO> page, CabinetTypeVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public int countByTypename(String typeName) {
		CabinetTypeExample example=new CabinetTypeExample();
		example.createCriteria().andTypeNameEqualTo(typeName);
		return mapper.countByExample(example);
	}
}
