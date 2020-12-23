package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.MembershipCardBO;
import vc.thinker.cabbage.user.mapper.MembershipCardMapper;
import vc.thinker.cabbage.user.model.MembershipCard;
import vc.thinker.cabbage.user.model.MembershipCardExample;
import vc.thinker.cabbage.user.vo.MembershipCardVO;

@Repository
public class MembershipCardDao {

	@Autowired
	private MembershipCardMapper mapper;
	
	/**
	 * 查找正常的会员卡
	 * @return
	 */
	public List<MembershipCardBO> findNormal(String currencyCode) {
		MembershipCardExample example=new MembershipCardExample();
		example.createCriteria().andCurrencyEqualTo(currencyCode);
		example.setOrderByClause("sort");
		return mapper.selectByExample(example);
	}
	
	public List<MembershipCardBO> findPageByVo(Page<MembershipCardBO> page, MembershipCardVO vo) {
		return mapper.findPageByVo(page,vo);
	}

	public List<MembershipCardBO> selectByExample(MembershipCardExample example) {
		return mapper.selectByExample(example);
	}
	
   /** generate code begin**/
	public List<MembershipCardBO> findAll(){
		MembershipCardExample example=new MembershipCardExample();
		return mapper.selectByExample(example);
	}
	List<MembershipCardBO> findAll(Iterable<java.lang.Long> ids){
		MembershipCardExample example=new MembershipCardExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		MembershipCardExample example=new MembershipCardExample();
		return mapper.countByExample(example);
	}

	public List<MembershipCard> save(Iterable<MembershipCard> entities){
		List<MembershipCard> list=new ArrayList<MembershipCard>();
		for (MembershipCard MembershipCard : entities) {
			list.add(save(MembershipCard));
		}
		return list;
	}
	
	public MembershipCard save(MembershipCard record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(MembershipCard record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public MembershipCardBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		MembershipCardExample example=new MembershipCardExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(MembershipCard entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<MembershipCard> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (MembershipCard  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		MembershipCardExample example=new MembershipCardExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		MembershipCardExample example=new MembershipCardExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

}
