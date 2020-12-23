package vc.thinker.cabbage.user.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.user.bo.MemberBO;
import vc.thinker.cabbage.user.bo.UserMoneyBO;
import vc.thinker.cabbage.user.mapper.UserMoneyMapper;
import vc.thinker.cabbage.user.model.UserMoney;
import vc.thinker.cabbage.user.model.UserMoneyExample;

@Repository
public class UserMoneyDao {

	@Autowired
	private UserMoneyMapper mapper;

	/**
	 * 根据用户id count
	 * 
	 * @param uid
	 * @param gtEqAmount
	 * @return
	 */
	public int countByUid(Long uid, BigDecimal gtEqAmount) {
		UserMoneyExample example = new UserMoneyExample();
		example.createCriteria().andAvailableBalanceGreaterThanOrEqualTo(gtEqAmount).andUidEqualTo(uid);
		return mapper.countByExample(example);
	}

	public BigDecimal selectSumBalance() {
		return mapper.selectSumBalance();
	}

	public int subtractMoney(Long uid, BigDecimal money) {
		return mapper.subtractMoney(uid, money);
	}

	public int addMoney(Long uid, BigDecimal money) {
		return mapper.addMoney(uid, money);
	}
	
	public int addRebateMoney(Long id, BigDecimal money) {
		return mapper.addRebateMoney(id,money);
	}
	
	public int substractRebateMoney(Long uid, BigDecimal money) {
		return mapper.substractRebateMoney(uid,money);
	}

   /** generate code begin**/
	public List<UserMoneyBO> findAll(){
		UserMoneyExample example=new UserMoneyExample();
		return mapper.selectByExample(example);
	}
	List<UserMoneyBO> findAll(Iterable<Long> ids){
		UserMoneyExample example=new UserMoneyExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		UserMoneyExample example=new UserMoneyExample();
		return mapper.countByExample(example);
	}

	public List<UserMoney> save(Iterable<UserMoney> entities){
		List<UserMoney> list=new ArrayList<UserMoney>();
		for (UserMoney UserMoney : entities) {
			list.add(save(UserMoney));
		}
		return list;
	}
	
	public UserMoney save(UserMoney record){
		if(!exists(record.getUid())){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(UserMoney record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public UserMoneyBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		UserMoneyExample example=new UserMoneyExample();
		example.createCriteria().andUidEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(UserMoney entity){
		 mapper.deleteByPrimaryKey(entity.getUid());
	}

	public void delete(Iterable<UserMoney> entities){
		List<Long> ids=Lists.newArrayList();
		for (UserMoney  entity: entities) {
			ids.add(entity.getUid());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<Long> ids){
		UserMoneyExample example=new UserMoneyExample();
		example.createCriteria().andUidIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		UserMoneyExample example=new UserMoneyExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

	public List<UserMoneyBO> sumRebateMoney() {
		return mapper.sumRebateMoney();
	}

}
