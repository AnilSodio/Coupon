package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.mapper.OrderPbBuyMapper;
import vc.thinker.cabbage.se.model.OrderPbBuy;
import vc.thinker.cabbage.se.bo.OrderPbBuyBO;
import vc.thinker.cabbage.se.model.OrderPbBuyExample;
import vc.thinker.cabbage.se.model.OrderPbBuyExample.Criteria;
import vc.thinker.cabbage.se.vo.OrderPbBuyVO;

@Repository
public class OrderPbBuyDao {

	@Autowired
	private OrderPbBuyMapper mapper;


   /** generate code begin**/
	public List<OrderPbBuyBO> findAll(){
		OrderPbBuyExample example=new OrderPbBuyExample();
		return mapper.selectByExample(example);
	}
	List<OrderPbBuyBO> findAll(Iterable<java.lang.Long> ids){
		OrderPbBuyExample example=new OrderPbBuyExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		OrderPbBuyExample example=new OrderPbBuyExample();
		return mapper.countByExample(example);
	}

	public List<OrderPbBuy> save(Iterable<OrderPbBuy> entities){
		List<OrderPbBuy> list=new ArrayList<OrderPbBuy>();
		for (OrderPbBuy OrderPbBuy : entities) {
			list.add(save(OrderPbBuy));
		}
		return list;
	}
	
	public OrderPbBuy save(OrderPbBuy record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(OrderPbBuy record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public OrderPbBuyBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		OrderPbBuyExample example=new OrderPbBuyExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(OrderPbBuy entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<OrderPbBuy> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (OrderPbBuy  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		OrderPbBuyExample example=new OrderPbBuyExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		OrderPbBuyExample example=new OrderPbBuyExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<OrderPbBuyBO> findPageByVo(Page<OrderPbBuyBO> page, OrderPbBuyVO vo) {
		return mapper.findPageByVo(page,vo);
	}
	public OrderPbBuyBO findByPayOrderCode(String payOrderCode) {
		OrderPbBuyExample example=new OrderPbBuyExample();
		example.createCriteria().andPayOrderCodeEqualTo(payOrderCode);
		List<OrderPbBuyBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}
}
