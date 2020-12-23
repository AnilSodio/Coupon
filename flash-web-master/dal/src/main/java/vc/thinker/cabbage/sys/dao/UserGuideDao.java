package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.UserGuideBO;
import vc.thinker.cabbage.sys.mapper.UserGuideMapper;
import vc.thinker.cabbage.sys.model.UserGuide;
import vc.thinker.cabbage.sys.model.UserGuideExample;
import vc.thinker.cabbage.sys.vo.UserGuideVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class UserGuideDao {

	@Autowired
	private UserGuideMapper mapper;

	public List<UserGuideBO> finePageByVo(MyPage<UserGuideBO> page, UserGuideVO vo) {
		return mapper.finePageByVo(page,vo);
	}
	
	public List<UserGuideBO> findListByType(Integer type,String language) {
		UserGuideExample example=new UserGuideExample();
		example.setOrderByClause("sort");
		example.createCriteria().andTypeEqualTo(type).andLanguageEqualTo(language);
		return mapper.selectByExample(example);
	}
   /** generate code begin**/
	public List<UserGuideBO> findAll(){
		UserGuideExample example=new UserGuideExample();
		return mapper.selectByExample(example);
	}
	List<UserGuideBO> findAll(Iterable<java.lang.Long> ids){
		UserGuideExample example=new UserGuideExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserGuideExample example=new UserGuideExample();
		return mapper.countByExample(example);
	}

	public List<UserGuide> save(Iterable<UserGuide> entities){
		List<UserGuide> list=new ArrayList<UserGuide>();
		for (UserGuide UserGuide : entities) {
			list.add(save(UserGuide));
		}
		return list;
	}
	
	public UserGuide save(UserGuide record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserGuide record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserGuideBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		UserGuideExample example=new UserGuideExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserGuide entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserGuide> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (UserGuide  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		UserGuideExample example=new UserGuideExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserGuideExample example=new UserGuideExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	
}
