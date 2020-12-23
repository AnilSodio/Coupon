package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.SysMessageLogBO;
import vc.thinker.cabbage.sys.mapper.SysMessageLogMapper;
import vc.thinker.cabbage.sys.model.SysMessageLog;
import vc.thinker.cabbage.sys.model.SysMessageLogExample;
import vc.thinker.cabbage.sys.vo.SysMessageLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class SysMessageLogDao {

	@Autowired
	private SysMessageLogMapper mapper;


   /** generate code begin**/
	public List<SysMessageLogBO> findAll(){
		SysMessageLogExample example=new SysMessageLogExample();
		return mapper.selectByExample(example);
	}
	List<SysMessageLogBO> findAll(Iterable<java.lang.Long> ids){
		SysMessageLogExample example=new SysMessageLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		SysMessageLogExample example=new SysMessageLogExample();
		return mapper.countByExample(example);
	}

	public List<SysMessageLog> save(Iterable<SysMessageLog> entities){
		List<SysMessageLog> list=new ArrayList<SysMessageLog>();
		for (SysMessageLog SysMessageLog : entities) {
			list.add(save(SysMessageLog));
		}
		return list;
	}
	
	public SysMessageLog save(SysMessageLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(SysMessageLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public SysMessageLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		SysMessageLogExample example=new SysMessageLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(SysMessageLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<SysMessageLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (SysMessageLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		SysMessageLogExample example=new SysMessageLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		SysMessageLogExample example=new SysMessageLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<SysMessageLogBO> findPageByVo(MyPage<SysMessageLogBO> page, SysMessageLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
}
