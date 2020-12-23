package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.sys.bo.SysMessageBO;
import vc.thinker.cabbage.sys.mapper.SysMessageMapper;
import vc.thinker.cabbage.sys.model.SysMessage;
import vc.thinker.cabbage.sys.model.SysMessageExample;
import vc.thinker.cabbage.sys.vo.SysMessageVO;

@Repository
public class SysMessageDao {

	@Autowired
	private SysMessageMapper mapper;
	
	public SysMessageBO findDetails(Long id){
		return mapper.findDetails(id);
	}
	
	public Integer countByImageTextId(Long uid,Long imageTextId){
		SysMessageExample example=new SysMessageExample();
		example.createCriteria().andToUserIdEqualTo(uid).andImageTextIdEqualTo(imageTextId);
		return mapper.countByExample(example);
	}

	public List<SysMessageBO> findPageByVO(Page<SysMessageBO> page, SysMessageVO vo) {
		return mapper.findPageByVO(page,vo);
	}
	
    public List<SysMessageBO> findPageByToUser(Page<SysMessageBO> page,SysMessageVO vo){
    	page.setOrderBy("sm.send_time desc");
    	return mapper.findPageByToUser(page, vo);
    }

   /** generate code begin**/
	public List<SysMessageBO> findAll(){
		SysMessageExample example=new SysMessageExample();
		return mapper.selectByExample(example);
	}
	List<SysMessageBO> findAll(Iterable<java.lang.Long> ids){
		SysMessageExample example=new SysMessageExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		SysMessageExample example=new SysMessageExample();
		return mapper.countByExample(example);
	}

	public List<SysMessage> save(Iterable<SysMessage> entities){
		List<SysMessage> list=new ArrayList<SysMessage>();
		for (SysMessage SysMessage : entities) {
			list.add(save(SysMessage));
		}
		return list;
	}
	
	public SysMessage save(SysMessage record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(SysMessage record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public SysMessageBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		SysMessageExample example=new SysMessageExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(SysMessage entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<SysMessage> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (SysMessage  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		SysMessageExample example=new SysMessageExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		SysMessageExample example=new SysMessageExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	public void insertByList(List<SysMessageBO> listMessage) {
		mapper.insertByList(listMessage);
	}
}
