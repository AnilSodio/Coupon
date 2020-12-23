package vc.thinker.cabbage.se.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import vc.thinker.cabbage.se.bo.OrderRebateBO;
import vc.thinker.cabbage.se.mapper.OrderRebateMapper;
import vc.thinker.cabbage.se.model.OrderRebate;
import vc.thinker.cabbage.se.model.OrderRebateExample;

@Repository
public class OrderRebateDao {

	@Autowired
	private OrderRebateMapper mapper;

	/**
	 * 订单id查找
	 * @param orderId
	 * @return
	 */
	public OrderRebateBO findByOrderId(Long orderId){
		OrderRebateExample example=new OrderRebateExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<OrderRebateBO> list=mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

   /** generate code begin**/
	public List<OrderRebateBO> findAll(){
		OrderRebateExample example=new OrderRebateExample();
		return mapper.selectByExample(example);
	}
	List<OrderRebateBO> findAll(Iterable<java.lang.Long> ids){
		OrderRebateExample example=new OrderRebateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		OrderRebateExample example=new OrderRebateExample();
		return mapper.countByExample(example);
	}

	public List<OrderRebate> save(Iterable<OrderRebate> entities){
		List<OrderRebate> list=new ArrayList<OrderRebate>();
		for (OrderRebate OrderRebate : entities) {
			list.add(save(OrderRebate));
		}
		return list;
	}
	
	public OrderRebate save(OrderRebate record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(OrderRebate record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public OrderRebateBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		OrderRebateExample example=new OrderRebateExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(OrderRebate entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<OrderRebate> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (OrderRebate  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		OrderRebateExample example=new OrderRebateExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		OrderRebateExample example=new OrderRebateExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
}
