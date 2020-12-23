package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.mapper.RepairerMapper;
import vc.thinker.cabbage.user.model.Repairer;
import vc.thinker.cabbage.user.bo.RepairerBO;
import vc.thinker.cabbage.user.model.RepairerExample;
import vc.thinker.cabbage.user.vo.RepairerVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class RepairerDao {

	@Autowired
	private RepairerMapper mapper;


   /** generate code begin**/
	public List<RepairerBO> findAll(){
		RepairerExample example=new RepairerExample();
		return mapper.selectByExample(example);
	}
	List<RepairerBO> findAll(Iterable<java.lang.Long> ids){
		RepairerExample example=new RepairerExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		RepairerExample example=new RepairerExample();
		return mapper.countByExample(example);
	}

	public List<Repairer> save(Iterable<Repairer> entities){
		List<Repairer> list=new ArrayList<Repairer>();
		for (Repairer Repairer : entities) {
			list.add(save(Repairer));
		}
		return list;
	}
	
	public Repairer save(Repairer record){
		if(!exists(record.getUid())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Repairer record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public RepairerBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		RepairerExample example=new RepairerExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Repairer entity){
		 mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<Repairer> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Repairer  entity: entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		RepairerExample example=new RepairerExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		RepairerExample example=new RepairerExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<RepairerBO> selectByExample(RepairerExample example) {
		return mapper.selectByExample(example);
	}
	public List<RepairerBO> findPageByVo(MyPage<RepairerBO> page, RepairerVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public RepairerBO findDetailInfo(Long uid) {
		return mapper.findDetailInfo(uid);
	}
}
