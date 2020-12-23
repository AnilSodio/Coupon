package vc.thinker.cabbage.se.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.sinco.data.core.Page;

import vc.thinker.cabbage.se.OrderConstants;
import vc.thinker.cabbage.se.bo.OrderBO;
import vc.thinker.cabbage.se.mapper.OrderMapper;
import vc.thinker.cabbage.se.model.Order;
import vc.thinker.cabbage.se.model.OrderExample;
import vc.thinker.cabbage.se.vo.OrderVO;
import vc.thinker.cabbage.stats.bo.CountStatsBO;
import vc.thinker.cabbage.stats.vo.StatsVO;
import vc.thinker.cabbage.common.MyPage;

@Repository
public class OrderDao {

	@Autowired
	private OrderMapper mapper;
	
	public List<OrderBO> findByStatus(Long uid,Integer status) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusEqualTo(status);
		return mapper.selectByExample(example);
	}
	
	public int countByStatusList(Long uid,List<Integer> statusList) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusIn(statusList);
		return mapper.countByExample(example);
	}
	
	/**
	 * 查询用户没有结束的行程
	 * @param uid
	 * @return
	 */
	public List<OrderBO> findUserNotEnd(Long uid) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusBetween(OrderConstants.ORDER_STATUS_30,
				OrderConstants.ORDER_STATUS_40);
		return mapper.selectByExample(example);
	}
		
	/**
	 * 查找用户的订单
	 * @param page
	 * @param uid
	 * @param ltFinishTime
	 * @return
	 */
	public List<OrderBO> findPageListByUid(Page<OrderBO> page, Long  uid,Date ltFinishTime) {
		OrderExample example=new OrderExample();
		OrderExample.Criteria c = example.createCriteria().andUidEqualTo(uid).andIsDeletedEqualTo(false)
		.andStatusBetween(OrderConstants.ORDER_STATUS_40, OrderConstants.ORDER_STATUS_50);
		if(ltFinishTime != null){
			c.andFinishTimeLessThan(ltFinishTime);
		}
		example.setPage(page);
		example.setOrderByClause("finish_time desc");
		return mapper.selectByExample(example);
	}
	
	/**
	 * 修改优惠劵为 null
	 * @param id
	 * @return
	 */
	public int updateUserCouponNull(Long id){
		return mapper.updateUserCouponNull(id);
	}
	
	public OrderBO findByCode(String orderCode){
		return findByOrderCode(orderCode);
	}
	
	public OrderBO findByOrderCode(String orderCode){
		OrderExample example=new OrderExample();
		example.createCriteria().andOrderCodeEqualTo(orderCode);
		List<OrderBO> list=mapper.selectByExample(example);
		return list.isEmpty() ? null:list.get(0);
	}
	
	/**
	 * 根据借机柜查找订单
	 * @param machineCode
	 * @param status
	 * @return
	 */
	public List<OrderBO> findByBorrowCabinetCode(String borrowSysCode,Integer status) {
		OrderExample example=new OrderExample();
		example.createCriteria().andBorrowSysCodeEqualTo(borrowSysCode).andStatusEqualTo(status);
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}
	
	/**
	 * 查找电池进行中的订单
	 * @param machineCode
	 * @param status
	 * @return
	 */
	public List<OrderBO> findDoingByBattery(String battery) {
		OrderExample example=new OrderExample();
		example.createCriteria().andPbCodeEqualTo(battery).andStatusEqualTo(OrderConstants.ORDER_STATUS_30);
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}
	
	/**
	 * 查询用户没有结束的行程
	 * @param uid
	 * @return
	 */
	public List<OrderBO> findUserNotEndOrder(Long uid) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusBetween(OrderConstants.ORDER_STATUS_30, OrderConstants.ORDER_STATUS_40);
		return mapper.selectByExample(example);
	}
	
	/**
	 * 修改创建订单失败的数据
	 * @param date
	 * @return
	 */
	public int updateOrderFail(Long uid){
		return mapper.updateOrderFail(null,uid);
	}
	/**
	 * 修改创建订单失败的数据
	 * @param date
	 * @return
	 */
	public int updateOrderFail(Date date){
		return mapper.updateOrderFail(date,null);
	}


   /** generate code begin**/
	public List<OrderBO> findAll(){
		OrderExample example=new OrderExample();
		return mapper.selectByExample(example);
	}
	List<OrderBO> findAll(Iterable<java.lang.Long> ids){
		OrderExample example=new OrderExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		return mapper.selectByExample(example);
	}
	
	public long count(){
		OrderExample example=new OrderExample();
		return mapper.countByExample(example);
	}

	public List<Order> save(Iterable<Order> entities){
		List<Order> list=new ArrayList<Order>();
		for (Order Order : entities) {
			list.add(save(Order));
		}
		return list;
	}
	
	public Order save(Order record){
		if(record.getId() == null){
			mapper.insertSelective(record);
		}else{
			mapper.updateByPrimaryKeySelective(record);
		}
		return record;
	}
	

	public void update(Order record) {
		mapper.updateByPrimaryKeySelective(record);
	}
	
	public OrderBO findOne(java.lang.Long id){
		return mapper.selectByPrimaryKey(id);
	}

	public boolean exists(java.lang.Long id){
		if(id == null){
			return false;
		}
		OrderExample example=new OrderExample();
		example.createCriteria().andIdEqualTo(id);
		return mapper.countByExample(example) > 0;
	}
	
	 public void delete(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }
	 
	 public void remove(java.lang.Long id){
		 mapper.deleteByPrimaryKey(id);
	 }

	public void delete(Order entity){
		 mapper.deleteByPrimaryKey(entity.getId());
	}

	public void delete(Iterable<Order> entities){
		List<java.lang.Long> ids=Lists.newArrayList();
		for (Order  entity: entities) {
			ids.add(entity.getId());
		}
		deleteByIds(ids);
	}
	
	public void deleteByIds(Iterable<java.lang.Long> ids){
		OrderExample example=new OrderExample();
		example.createCriteria().andIdIn(Lists.newArrayList(ids));
		 mapper.deleteByExample(example);
	}

	public void deleteAll(){
		OrderExample example=new OrderExample();
		mapper.deleteByExample(example);
	}
	/** generate code end**/
	public List<OrderBO> findPageByVo(MyPage<OrderBO> page, OrderVO vo) {
		return mapper.findPageByVo(page,vo);
	}

	public OrderBO findLastUseByPbId(Long pBid) {
		return mapper.findLastUseByPbId(pBid);
	}

	public BigDecimal sumConsume(String day) {
		return mapper.sumConsume(day);
	}

	public List<CountStatsBO> findConsumeStats(StatsVO vo) {
		return mapper.findConsumeStats(vo);
	}

	public int findRideCountYesToday(String date) {
		return mapper.findRideCountYesToday(date);
	}

	public List<OrderBO> findOnlyIdByStatusAndPayTime(String beginDate, String endDate, Integer status) {
		return mapper.findOnlyIdByStatusAndPayTime(beginDate,endDate,status);
	}

	public List<OrderBO> findAllPbBuyByUid(Long uid) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusEqualTo(50).andPayTypeEqualTo(OrderConstants.PAY_TYPE_PB_BUY);
		example.setOrderByClause("create_time desc");
		return mapper.selectByExample(example);
	}

	public OrderBO findOngoingOrder(Long uid) {
		OrderExample example=new OrderExample();
		example.createCriteria().andUidEqualTo(uid).andStatusEqualTo(OrderConstants.ORDER_STATUS_30);
		List<OrderBO> list = mapper.selectByExample(example);
		return list.isEmpty()?null:list.get(0);
	}

	public List<OrderBO> listByStatus(Integer status) {
		OrderExample example=new OrderExample();
		example.createCriteria().andStatusEqualTo(status);
		return mapper.selectByExample(example);
	}
}
