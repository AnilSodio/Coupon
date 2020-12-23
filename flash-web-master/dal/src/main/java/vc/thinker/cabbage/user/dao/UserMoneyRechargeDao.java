package vc.thinker.cabbage.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;
import com.sinco.dic.client.DicNameMappingHandle;

import vc.thinker.cabbage.user.bo.UserMoneyRechargeBO;
import vc.thinker.cabbage.user.mapper.UserMoneyRechargeMapper;
import vc.thinker.cabbage.user.model.UserMoneyRecharge;
import vc.thinker.cabbage.user.model.UserMoneyRechargeExample;
import vc.thinker.cabbage.user.vo.UserMoneyRechargeVO;

@Repository
public class UserMoneyRechargeDao {

	@Autowired
	private UserMoneyRechargeMapper mapper;

	public List<UserMoneyRechargeBO> findPageByVo(Page<UserMoneyRechargeBO> page, UserMoneyRechargeVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	
	@Autowired
	private DicNameMappingHandle mappingHandle;

	public UserMoneyRechargeBO findOne(String sn){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		example.createCriteria().andSnEqualTo(sn);
		
		List<UserMoneyRechargeBO> list=mapper.selectByExample(example);
		if(list.isEmpty()){
			return null;
		}else{
			return mappingHandle.mappinHandle(list.get(0));
		}
	}

   /** generate code begin**/
	public List<UserMoneyRechargeBO> findAll(){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		return mapper.selectByExample(example);
	}
	List<UserMoneyRechargeBO> findAll(Iterable<Long> ids){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		return mapper.countByExample(example);
	}

	public List<UserMoneyRecharge> save(Iterable<UserMoneyRecharge> entities){
		List<UserMoneyRecharge> list=new ArrayList<UserMoneyRecharge>();
		for (UserMoneyRecharge UserMoneyRecharge : entities) {
			list.add(save(UserMoneyRecharge));
		}
		return list;
	}
	
	public UserMoneyRecharge save(UserMoneyRecharge record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserMoneyRecharge record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserMoneyRechargeBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserMoneyRecharge entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<UserMoneyRecharge> entities){
		List<Long> ids=Lists.newArrayList();
		for (UserMoneyRecharge  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserMoneyRechargeExample example=new UserMoneyRechargeExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	public int chargedUserNum(){
		return mapper.chargedUserNum();
	}

	/**
	 * 用户充值的总金额
	 * */
	public Double totalCharged(){
		return mapper.totalCharged();
	}

	public BigDecimal sumByDate(String date) {
		return mapper.sumByDate(date);
	}
}
