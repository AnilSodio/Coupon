package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.IntegralLogBO;
import vc.thinker.cabbage.user.mapper.IntegralLogMapper;
import vc.thinker.cabbage.user.model.IntegralLog;
import vc.thinker.cabbage.user.model.IntegralLogExample;
import vc.thinker.cabbage.user.vo.IntegralLogVO;

@Repository
public class IntegralLogDao {

	@Autowired
	private IntegralLogMapper mapper;

	public Long sumByUid(Long uid){
		IntegralLogExample example=new IntegralLogExample();
		example.createCriteria().andUidEqualTo(uid);
		return mapper.sumByExample(example);
	}

   /** generate code begin**/
	public List<IntegralLogBO> findAll(){
		IntegralLogExample example=new IntegralLogExample();
		return mapper.selectByExample(example);
	}
	List<IntegralLogBO> findAll(Iterable<java.lang.Long> ids){
		IntegralLogExample example=new IntegralLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		IntegralLogExample example=new IntegralLogExample();
		return mapper.countByExample(example);
	}

	public List<IntegralLog> save(Iterable<IntegralLog> entities){
		List<IntegralLog> list=new ArrayList<IntegralLog>();
		for (IntegralLog IntegralLog : entities) {
			list.add(save(IntegralLog));
		}
		return list;
	}
	
	public IntegralLog save(IntegralLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(IntegralLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public IntegralLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		IntegralLogExample example=new IntegralLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(IntegralLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<IntegralLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (IntegralLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		IntegralLogExample example=new IntegralLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		IntegralLogExample example=new IntegralLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<IntegralLogBO> findPageByVo(Page<IntegralLogBO> page, IntegralLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
