package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.ShareSetBO;
import vc.thinker.cabbage.sys.mapper.ShareSetMapper;
import vc.thinker.cabbage.sys.model.ShareSet;
import vc.thinker.cabbage.sys.model.ShareSetExample;
import vc.thinker.cabbage.sys.vo.ShareSetVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class ShareSetDao {

	@Autowired
	private ShareSetMapper mapper;


   /** generate code begin**/
	public List<ShareSetBO> findAll(){
		ShareSetExample example=new ShareSetExample();
		return mapper.selectByExample(example);
	}
	List<ShareSetBO> findAll(Iterable<java.lang.Long> ids){
		ShareSetExample example=new ShareSetExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		ShareSetExample example=new ShareSetExample();
		return mapper.countByExample(example);
	}

	public List<ShareSet> save(Iterable<ShareSet> entities){
		List<ShareSet> list=new ArrayList<ShareSet>();
		for (ShareSet ShareSet : entities) {
			list.add(save(ShareSet));
		}
		return list;
	}
	
	public ShareSet save(ShareSet record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(ShareSet record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public ShareSetBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		ShareSetExample example=new ShareSetExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(ShareSet entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<ShareSet> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (ShareSet  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		ShareSetExample example=new ShareSetExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		ShareSetExample example=new ShareSetExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<ShareSetBO> findPageByVo(MyPage<ShareSetBO> page, ShareSetVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public void updateNoAllowInvite(Long id) {
		mapper.updateNoAllowInvite(id);
	}
	public void updateNoALlowShare(Long id) {
		mapper.updateNoALlowShare(id);
	}
}
