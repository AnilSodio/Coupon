package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.mapper.SysCodeMapper;
import vc.thinker.cabbage.se.model.SysCode;
import vc.thinker.cabbage.se.bo.SysCodeBO;
import vc.thinker.cabbage.se.model.SysCodeExample;
import vc.thinker.cabbage.se.model.SysCodeExample.Criteria;
import vc.thinker.cabbage.se.vo.SysCodeVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class SysCodeDao {

	@Autowired
	private SysCodeMapper mapper;


   /** generate code begin**/
	public List<SysCodeBO> findAll(){
		SysCodeExample example=new SysCodeExample();
		return mapper.selectByExample(example);
	}
	List<SysCodeBO> findAll(Iterable<java.lang.Long> ids){
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		SysCodeExample example=new SysCodeExample();
		return mapper.countByExample(example);
	}

	public List<SysCode> save(Iterable<SysCode> entities){
		List<SysCode> list=new ArrayList<SysCode>();
		for (SysCode SysCode : entities) {
			list.add(save(SysCode));
		}
		return list;
	}
	
	public SysCode save(SysCode record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	
	//Single
	public SysCode saveSingle(SysCode record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}

	public void update(SysCode record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public SysCodeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(SysCode entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<SysCode> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (SysCode  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		SysCodeExample example=new SysCodeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<SysCodeBO> findPageByVo(MyPage<SysCodeBO> page, SysCodeVO vo) {
		
		SysCodeExample example=new SysCodeExample();
		Criteria c = example.createCriteria();
		if(null != vo.getIsBinding()){
			c.andIsBindingEqualTo(vo.getIsBinding());
		}
		if(null != vo.getIsPrint()){
			c.andIsPrintEqualTo(vo.getIsPrint());
		}
		
		example.setPage(page);
		example.setOrderByClause("sys_code desc");
		
		return mapper.selectByExample(example);
	}
	public List<SysCodeBO> findAllNoPrint() {
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andIsPrintEqualTo(false);
		return mapper.selectByExample(example);
	}
	public SysCodeBO findBySysCode(String sysCode) {
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andSysCodeEqualTo(sysCode);
		List<SysCodeBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
	public List<SysCodeBO> findAllNoBinding() {
		SysCodeExample example=new SysCodeExample();
		example.createCriteria().andIsBindingEqualTo(false);
		return mapper.selectByExample(example);
	}
}
