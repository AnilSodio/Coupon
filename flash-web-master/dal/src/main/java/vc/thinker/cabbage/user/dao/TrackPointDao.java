package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.bo.TrackPointBO;
import vc.thinker.cabbage.user.mapper.TrackPointMapper;
import vc.thinker.cabbage.user.model.TrackPoint;
import vc.thinker.cabbage.user.model.TrackPointExample;

@Repository
public class TrackPointDao {

	@Autowired
	private TrackPointMapper mapper;


   /** generate code begin**/
	public List<TrackPointBO> findAll(){
		TrackPointExample example=new TrackPointExample();
		return mapper.selectByExample(example);
	}
	List<TrackPointBO> findAll(Iterable<java.lang.Long> ids){
		TrackPointExample example=new TrackPointExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		TrackPointExample example=new TrackPointExample();
		return mapper.countByExample(example);
	}

	public List<TrackPoint> save(Iterable<TrackPoint> entities){
		List<TrackPoint> list=new ArrayList<TrackPoint>();
		for (TrackPoint TrackPoint : entities) {
			list.add(save(TrackPoint));
		}
		return list;
	}
	
	public TrackPoint save(TrackPoint record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(TrackPoint record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public TrackPointBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		TrackPointExample example=new TrackPointExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(TrackPoint entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<TrackPoint> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (TrackPoint  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		TrackPointExample example=new TrackPointExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		TrackPointExample example=new TrackPointExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
