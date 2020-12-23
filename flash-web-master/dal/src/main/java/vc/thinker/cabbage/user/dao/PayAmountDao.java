package vc.thinker.cabbage.user.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.user.bo.PayAmountBO;
import vc.thinker.cabbage.user.mapper.PayAmountMapper;
import vc.thinker.cabbage.user.model.PayAmount;
import vc.thinker.cabbage.user.model.PayAmountExample;
import vc.thinker.cabbage.user.vo.PayAmountVO;

@Repository
public class PayAmountDao {

	@Autowired
	private PayAmountMapper mapper;

	/**
	 * 查找正常的充值金额
	 * @return
	 */
	public List<PayAmountBO> findNormal(String currencyCode) {
		PayAmountExample example=new PayAmountExample();
		example.createCriteria().andCurrencyEqualTo(currencyCode);
		example.setOrderByClause("sort");
		return mapper.selectByExample(example);
	}
	public List<PayAmountBO> findPageByVo(Page<PayAmountBO> page, PayAmountVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	
	public List<PayAmountBO> selectByExample(PayAmountExample example) {
		return mapper.selectByExample(example);
	}

   /** generate code begin**/
	public List<PayAmountBO> findAll(){
		PayAmountExample example=new PayAmountExample();
		return mapper.selectByExample(example);
	}
	List<PayAmountBO> findAll(Iterable<java.lang.Long> ids){
		PayAmountExample example=new PayAmountExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		PayAmountExample example=new PayAmountExample();
		return mapper.countByExample(example);
	}

	public List<PayAmount> save(Iterable<PayAmount> entities){
		List<PayAmount> list=new ArrayList<PayAmount>();
		for (PayAmount PayAmount : entities) {
			list.add(save(PayAmount));
		}
		return list;
	}
	
	public PayAmount save(PayAmount record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(PayAmount record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public PayAmountBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		PayAmountExample example=new PayAmountExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(PayAmount entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<PayAmount> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (PayAmount  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		PayAmountExample example=new PayAmountExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		PayAmountExample example=new PayAmountExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/

}
