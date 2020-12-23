package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;
import com.sinco.dic.client.DicNameMappingHandle;

import vc.thinker.cabbage.user.bo.VipPayLogBO;
import vc.thinker.cabbage.user.mapper.VipPayLogMapper;
import vc.thinker.cabbage.user.model.VipPayLog;
import vc.thinker.cabbage.user.model.VipPayLogExample;
import vc.thinker.cabbage.user.vo.VipPayLogVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class VipPayLogDao {

	@Autowired
	private VipPayLogMapper mapper;
	
	public List<VipPayLogBO> findPageByVo(MyPage<VipPayLogBO> page, VipPayLogVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	
	@Autowired
	private DicNameMappingHandle mappingHandle;
	
	public VipPayLogBO findBySn(String sn){
		VipPayLogExample example=new VipPayLogExample();
		example.createCriteria().andSnEqualTo(sn);
		List<VipPayLogBO> list = mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}
	
	public List<VipPayLogBO> findByPage(Page<VipPayLogBO> page,VipPayLogVO vo){
		VipPayLogExample example=new VipPayLogExample();
		VipPayLogExample.Criteria c = example.createCriteria();
		if(vo.getUid() != null){
			c.andUidEqualTo(vo.getUid());
		}
		if(vo.getStatus() != null){
			c.andStatusEqualTo(vo.getStatus());
		}
		
		if(vo.getLtTime() != null){
			c.andCreateTimeLessThan(vo.getLtTime());
		}
		example.setPage(page);
		example.setOrderByClause("create_time desc");
		
		return mappingHandle.mappinHandle( mapper.selectByExample(example));
	}

   /** generate code begin**/
	public List<VipPayLogBO> findAll(){
		VipPayLogExample example=new VipPayLogExample();
		return mapper.selectByExample(example);
	}
	List<VipPayLogBO> findAll(Iterable<java.lang.Long> ids){
		VipPayLogExample example=new VipPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		VipPayLogExample example=new VipPayLogExample();
		return mapper.countByExample(example);
	}

	public List<VipPayLog> save(Iterable<VipPayLog> entities){
		List<VipPayLog> list=new ArrayList<VipPayLog>();
		for (VipPayLog VipPayLog : entities) {
			list.add(save(VipPayLog));
		}
		return list;
	}
	
	public VipPayLog save(VipPayLog record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(VipPayLog record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public VipPayLogBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		VipPayLogExample example=new VipPayLogExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(VipPayLog entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<VipPayLog> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (VipPayLog  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		VipPayLogExample example=new VipPayLogExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		VipPayLogExample example=new VipPayLogExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	public List<VipPayLogBO> findByStatusAndPayTime(String beginDate, String endDate, Integer status) {
		return mapper.findByStatusAndPayTime(beginDate,endDate,status);
	}

}
