package vc.thinker.cabbage.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.sys.bo.InitImgBO;
import vc.thinker.cabbage.sys.mapper.InitImgMapper;
import vc.thinker.cabbage.sys.model.InitImg;
import vc.thinker.cabbage.sys.model.InitImgExample;
import vc.thinker.cabbage.sys.vo.InitImgVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class InitImgDao {

	@Autowired
	private InitImgMapper mapper;


   /** generate code begin**/
	public List<InitImgBO> findAll(){
		InitImgExample example=new InitImgExample();
		return mapper.selectByExample(example);
	}
	List<InitImgBO> findAll(Iterable<java.lang.Long> ids){
		InitImgExample example=new InitImgExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		InitImgExample example=new InitImgExample();
		return mapper.countByExample(example);
	}

	public List<InitImg> save(Iterable<InitImg> entities){
		List<InitImg> list=new ArrayList<InitImg>();
		for (InitImg InitImg : entities) {
			list.add(save(InitImg));
		}
		return list;
	}
	
	public InitImg save(InitImg record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(InitImg record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public InitImgBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		InitImgExample example=new InitImgExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(InitImg entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<InitImg> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (InitImg  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		InitImgExample example=new InitImgExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		InitImgExample example=new InitImgExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<InitImgBO> findPageByVo(MyPage<InitImgBO> page, InitImgVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public List<InitImgBO> findNormalImg(Integer imgType) {
		return mapper.findNormalImg(imgType);
	}
}
